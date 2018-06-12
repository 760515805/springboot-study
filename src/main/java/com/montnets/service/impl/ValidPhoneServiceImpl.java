/**
 * 
 */
package com.montnets.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.montnets.bean.DataPage;
import com.montnets.bean.ValidPhone;
import com.montnets.config.ApplicationConfiguration;
import com.montnets.config.EsConstans;
import com.montnets.constant.Constans;
import com.montnets.dao.BaseDao;
import com.montnets.dao.ValidPhoneDao;
import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;
import com.montnets.service.IEsService;
import com.montnets.service.ValidPhoneService;
import com.montnets.utils.AESUntil;
import com.montnets.utils.KafkaUtil;
import com.montnets.utils.MyTools;
import com.montnets.utils.RedisUtil;
import com.montnets.utils.TokenUtil;

/**
 * @Title: ValidPhoneServiceImpl
 * @Description: 号码状态实现类
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月23日
 */
@Service
public class ValidPhoneServiceImpl extends BaseServiceImpl<ValidPhone> implements ValidPhoneService {

	private static final Logger logger = LoggerFactory.getLogger(ValidPhoneServiceImpl.class);
	private static final Logger reqlogger = LoggerFactory.getLogger("requestInfo");
	// private static final Logger reslogger =
	// LoggerFactory.getLogger("responseInfo");

	@Autowired
	private ValidPhoneDao vpDao;


	//@Autowired
	//private EsAction esAction;
	
	@Autowired
	private IEsService esService;

	@Autowired
	private ApplicationConfiguration appConfig;

	
	@Resource
	private RedisUtil redisUtil;
	
	/** redis 查询的key */
	private final String REDIS_ONLNE="onlinestate:";
	
	/** redis 查询的值 */
	private final String REDIS_VALUE="online";
	
	
	@Override
	protected BaseDao<ValidPhone> getMapper() {
		return this.vpDao;
	}

	@Override
	public ResponseResult findValidPhone(RequestResult req) {
		reqlogger.info("有效号码查询请求参数:" + req);
		ResponseResult result = new ResponseResult();
		result.setSeqid(req.getSeqid());
		result.setTimestamp(MyTools.getNowTime(Constans.TIME_FORMAT5));
		// token校验
		if (!TokenUtil.checkToken(req)) {
			result.setResultcode("-1");
			result.setResultmsg("该token已经失效!");
			return result;
		}
		String svccont = req.getSvccont();
		// 如果是加密的数据
		if (req.getEncryptsign() == 1) {
			try {
				svccont = AESUntil.aesDecode(svccont);
			} catch (Exception e) {
				logger.error("解密失败!原因是:" + e.getMessage());
				result.setResultcode("-1");
				result.setResultmsg("解密失败!原因是:" + e.getMessage());
				return result;
			}
		}
		reqlogger.info("有效号码查询请求内容:" + svccont);
		ValidPhone validPhone = MyTools.toBean(svccont, ValidPhone.class);
		if (validPhone == null) {
			result.setResultcode("-1");
			result.setResultmsg("请求内容不能为空!");
			return result;
		}
		if (validPhone.getQuerytype() == null || validPhone.getDatatype() == null) {
			result.setResultcode("-1");
			result.setResultmsg("请求参数不能为空!");
			return result;
		}
		List<ValidPhone> vp = null;
		long count = 0;
		try {

			// 1,top查询;2,分页查询;3,总数查询
			if (validPhone.getQuerytype() == 1) {
				// 如果是查询sqlServer的话
				if (validPhone.getDatatype() == 1) {
					// 参数说明:第几页，每页的条数
					// PageHelper.startPage(1,validPhone.getPagesize());
					/*
					 * 时间补齐
					 */
					validPhone.setStartlastdltm(
							MyTools.complementTime(validPhone.getStartlastdltm(), Constans.TIME_FORMAT, 1));
					validPhone.setEndlastdltm(
							MyTools.complementTime(validPhone.getEndlastdltm(), Constans.TIME_FORMAT, 2));
					validPhone.setStartlastupdatetm(
							MyTools.complementTime(validPhone.getStartlastupdatetm(), Constans.TIME_FORMAT, 1));
					validPhone.setEndlastupdatetm(
							MyTools.complementTime(validPhone.getEndlastupdatetm(), Constans.TIME_FORMAT, 2));
					// vp = vpDao.findVaildPhone(validPhone);
					vp = vpDao.queryTop(validPhone);
				} else if (validPhone.getDatatype() == 2) {
					
					// 查询es
					DataPage<ValidPhone> dataPage = new DataPage<ValidPhone>(1, validPhone.getPagesize())// 第100页,每页50条数据
							.addSort(Constans.ORDERING_RULE, EsConstans.DESC);// 排序的字段名和排序方式,只能根据一个字段来排序
					// 查询库的名称
					dataPage.setIndexName(Constans.TABLE_NAME);
					//dataPage = esAction.queryPage(validPhone, dataPage, ValidPhone.class);
					dataPage = esService.searchDoc(validPhone, dataPage);
					// 取响应数据
					vp = dataPage.getDataList();// 本页数据
				}

			} else if (validPhone.getQuerytype() == 2) {
				// 如果是查询sqlServer的话
				if (validPhone.getDatatype() == 1) {
					// 参数说明:第几页，每页的条数
					Page<?> page = PageHelper.startPage(validPhone.getIndex(), validPhone.getPagesize());
					/*
					 * 时间补齐
					 */
					validPhone.setStartlastdltm(
							MyTools.complementTime(validPhone.getStartlastdltm(), Constans.TIME_FORMAT, 1));
					validPhone.setEndlastdltm(
							MyTools.complementTime(validPhone.getEndlastdltm(), Constans.TIME_FORMAT, 2));
					validPhone.setStartlastupdatetm(
							MyTools.complementTime(validPhone.getStartlastupdatetm(), Constans.TIME_FORMAT, 1));
					validPhone.setEndlastupdatetm(
							MyTools.complementTime(validPhone.getEndlastupdatetm(), Constans.TIME_FORMAT, 2));
					vp = vpDao.findVaildPhone(validPhone);
					// 总条数
					count = page.getTotal();
				} else if (validPhone.getDatatype() == 2) {
					// 查询es
					DataPage<ValidPhone> dataPage = new DataPage<ValidPhone>(validPhone.getIndex(),
							validPhone.getPagesize())// 第100页,每页50条数据
									.addSort(Constans.ORDERING_RULE, EsConstans.DESC);// 排序的字段名和排序方式,只能根据一个字段来排序
					// 查询库的名称
					dataPage.setIndexName(Constans.TABLE_NAME);
					//dataPage = esAction.queryPage(validPhone, dataPage, ValidPhone.class);
					dataPage = esService.searchDoc(validPhone, dataPage);
					// 取响应数据
					vp = dataPage.getDataList();// 本页数据
					count = dataPage.getTotalCount();// 数据总条数
				}
				if (MyTools.isNotEmpty(vp)) {
					vp.get(vp.size() - 1).setCount(count);
				}
			} else if (validPhone.getQuerytype() == 3) {
				// 如果是查询sqlServer的话
				vp = new ArrayList<ValidPhone>();
				if (validPhone.getDatatype() == 1) {
					count = vpDao.queryCount(validPhone);
					validPhone = new ValidPhone();
					validPhone.setCount(count);
					vp.add(validPhone);
				} else if (validPhone.getDatatype() == 2) {
					// 查询es
					DataPage<ValidPhone> dataPage = new DataPage<ValidPhone>();
					// 查询库的名称
					dataPage.setIndexName(Constans.TABLE_NAME);
					//count = esAction.queryCount(validPhone, dataPage);
					count = esService.count(validPhone, dataPage);
					validPhone = new ValidPhone();
					validPhone.setCount(count);
					vp.add(validPhone);
				}
			}

			// 添加在线状态
			dealData(vp);

			reqlogger.info("有效号码查询返回求内容:" + JSON.toJSONString(vp));
			result.setSvccont(AESUntil.aesEncode(JSON.toJSONString(vp)));
			result.setResultcode("0");
		} catch (Exception e) {
			String message = MyTools.isEmpty(e.getMessage()) ? "NULL POINTER" : e.getMessage();
			if (validPhone.getDatatype() == 1) {
				message = message.indexOf("NULL POINTER") > -1 ? message : "查询SQL SERVER失败!";
			} else if (validPhone.getDatatype() == 2) {
				message = message.indexOf("NULL POINTER") > -1 ? message : "查询ES失败!";
			}
			logger.error("有效号码查询查询失败!", e);
			result.setResultcode("-1");
			result.setResultmsg("有效号码查询查询失败!原因是:" + message);
		}
		reqlogger.info("有效号码查询响应参数:" + result);
		return result;
	}

	@Override
	public ResponseResult insertValidPhone(RequestResult req) {
		reqlogger.info("有效号码新增请求参数:" + req);
		ResponseResult result = new ResponseResult();
		result.setSeqid(req.getSeqid());
		result.setTimestamp(MyTools.getNowTime(Constans.TIME_FORMAT5));
		// token校验
		if (!TokenUtil.checkToken(req)) {
			result.setResultcode("-1");
			result.setResultmsg("该token已经失效!");
			return result;
		}
		String svccont = req.getSvccont();
		if (MyTools.isEmpty(svccont)) {
			result.setResultcode("-1");
			result.setResultmsg("请求内容不能为空!");
			return result;
		}
		// 如果是加密的数据
		if (req.getEncryptsign() == 1) {
			try {
				svccont = AESUntil.aesDecode(svccont);
			} catch (Exception e) {
				logger.error("解密失败!原因是:" + e.getMessage());
				result.setResultcode("-1");
				result.setResultmsg("解密失败!原因是:" + e.getMessage());
				return result;
			}
		}
		reqlogger.info("有效号码新增请求内容:" + svccont);
		ValidPhone validPhone = MyTools.toBean(svccont, ValidPhone.class);
		if (validPhone == null) {
			result.setResultcode("-1");
			result.setResultmsg("请求内容格式不正确!");
			return result;
		}
		// 这里dataType 的0 表示新增
		validPhone.setDatatype(0);
		validPhone.setCreatetm(MyTools.getNowTime(Constans.TIME_FORMAT));
		validPhone.setLastupdatetm(Constans.DEFAULT_TIME);
		validPhone.setLastrpttm(Constans.DEFAULT_TIME);
		validPhone.setLastdltm(Constans.DEFAULT_TIME);
		List<Object> list = new ArrayList<Object>();
		try {
			// String msg= MyTools.toJson(validPhone).toJSONString();
			list.add(validPhone.toString());
			KafkaUtil.sendMessage(list.toString(), appConfig.getBootstrap_servers(), appConfig.getTopicName());
			result.setResultcode("0");
		} catch (Exception e) {
			String message = MyTools.isEmpty(e.getMessage()) ? "NULL POINTER" : e.getMessage();
			logger.error("新增失败!", e);
			result.setResultcode("-1");
			result.setResultmsg("新增失败!原因是:" + message);
		}
		reqlogger.info("有效号码新增响应参数:" + result);
		return result;
	}

	@Override
	public ResponseResult updateValidPhone(RequestResult req) {
		reqlogger.info("有效号码修改请求参数:" + req);
		ResponseResult result = new ResponseResult();
		result.setSeqid(req.getSeqid());
		result.setTimestamp(MyTools.getNowTime(Constans.TIME_FORMAT5));
		// token校验
		if (!TokenUtil.checkToken(req)) {
			result.setResultcode("-1");
			result.setResultmsg("该token已经失效!");
			return result;
		}
		String svccont = req.getSvccont();
		if (MyTools.isEmpty(svccont)) {
			result.setResultcode("-1");
			result.setResultmsg("请求内容不能为空!");
			return result;
		}

		// 如果是加密的数据
		if (req.getEncryptsign() == 1) {
			try {
				svccont = AESUntil.aesDecode(svccont);
			} catch (Exception e) {
				logger.error("解密失败!原因是:" + e.getMessage());
				result.setResultcode("-1");
				result.setResultmsg("解密失败!原因是:" + e.getMessage());
				return result;
			}
		}
		reqlogger.info("有效号码修改请求内容:" + svccont);
		// ValidPhone validPhone = MyTools.toBean(svccont,ValidPhone.class);
		// //这里dataType 的1 表示修改
		// validPhone.setDataType(1);
		//
		List<JSON> list = JSON.parseArray(svccont, JSON.class);
		// List<ValidPhone> list2=JSON.parseArray(svccont, ValidPhone.class);
		List<JSON> list3 = new ArrayList<>();
		for (JSON json : list) {
			JSONObject jsonObject = (JSONObject) json;
			jsonObject.put("lastupdatetm", MyTools.getNowTime(Constans.TIME_FORMAT));
			list3.add(jsonObject);
		}

		try {
			KafkaUtil.sendMessage(list3.toString(), appConfig.getBootstrap_servers(), appConfig.getTopicName());
			result.setResultcode("0");
		} catch (Exception e) {
			String message = MyTools.isEmpty(e.getMessage()) ? "NULL POINTER" : e.getMessage();
			logger.error("更新失败!", e);
			result.setResultcode("-1");
			result.setResultmsg("更新失败!原因是:" + message);
		}
		reqlogger.info("有效号码更新响应参数:" + result);
		return result;
	}

	
	/**
	 * 对有效号码的数据进行添加在线状态
	 * 
	 * @param vp
	 */
	private void dealData(List<ValidPhone> vp) {
		if(MyTools.isEmpty(vp)){
			return;
		}
		//设置的条数
		int size=appConfig.getSize();
		//查询的数据条数
		int  count=vp.size();
		/*
		 * 如果查询的条数大于所配置的条数,则只对设置条数进行添加查询的状态 ，其余的添加-2
		 * 否则全部添加
		 */
		if (count > size) {
			addStatus(vp.subList(0, size), null);
			addStatus(vp.subList(size, count), "-2");
		} else {
			addStatus(vp, null);
		}
	}

	/**
	 * 对有效号码的数据进行添加在线状态
	 * @param vp
	 * @param status
	 */
	private void addStatus(List<ValidPhone> vp, String status) {
		if (status == null) {
			try {
				/*
				 * 遍历数据,通过设备ID在redis进行查询在线状态
				 * 若设备ID没有值，则直接将状态给 0， 否则添加到该条数据中 
				 * 若在redis中查询失败,则直接将状态给 -1
				 * 
				 */
				for (ValidPhone v : vp) {
					
					//获取设备ID
//					String dev_uid = v.getDev_uid();
					String dev_uid = v.getImei();
					if(MyTools.isNotEmpty(dev_uid)){
						String key=REDIS_ONLNE+dev_uid;
						String value=REDIS_VALUE;
//						System.out.println("---"+redisUtil.hasKey(key));
						// 在redis中获取在线状态
						Object obj = redisUtil.hget(key, value);
						String online = obj==null?"0":obj.toString();
						// 如果未出异常,则直接获取值
						v.setOnline(online);
					}else{
						v.setOnline("0");
					}
				}
			} catch (Exception e) {
				logger.error("获取在线状态异常!", e);
				for (ValidPhone v : vp) {
					v.setOnline("-1");
				}
			}
		} else {
			for (ValidPhone v : vp) {
				v.setOnline(status);
			}
		}
	}

}
