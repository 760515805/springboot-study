package com.montnets.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.montnets.bean.ValidPhoneHis;
import com.montnets.constant.Constans;
import com.montnets.dao.BaseDao;
import com.montnets.dao.ValidPhoneHisDao;
import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;
import com.montnets.service.ValidPhoneHisService;
import com.montnets.utils.AESUntil;
import com.montnets.utils.MyTools;
import com.montnets.utils.TokenUtil;

/**
 * 
* @Title: VaildPhoneHisServiceImpl
* @Description: 
* 机型变化历史实现类
* @Version:1.0.0  
* @author pancm
* @date 2018年3月28日
 */
@Service
public class ValidPhoneHisServiceImpl extends BaseServiceImpl<ValidPhoneHis> 
	implements ValidPhoneHisService {

	private static final Logger logger = LoggerFactory.getLogger(ValidPhoneHisServiceImpl.class);
	private static final Logger reqlogger = LoggerFactory.getLogger("requestInfo");
//	private static final Logger reslogger = LoggerFactory.getLogger("responseInfo");
	
	
	@Autowired
	private ValidPhoneHisDao vphDao;
	@Override
	protected BaseDao<ValidPhoneHis> getMapper() {
		return this.vphDao;
	}
	
	
	@Override
	public ResponseResult findByPhone(RequestResult req) {
		reqlogger.info("机型变化历史查询请求参数:"+req);
		ResponseResult result = new ResponseResult();
		result.setSeqid(req.getSeqid());
		result.setTimestamp(MyTools.getNowTime(Constans.TIME_FORMAT5));
		if(!TokenUtil.checkToken(req)){
			result.setResultcode("-1");
			result.setResultmsg("该token已经失效!");
			return result;
		}
		String svccont=req.getSvccont();
		//如果是加密的数据
		if(req.getEncryptsign()==1){
			try {
				svccont=AESUntil.aesDecode(svccont);
			} catch (Exception e) {
				String message = MyTools.isEmpty(e.getMessage()) ? "NULL POINTER" : e.getMessage();
				logger.error("解密失败!原因是:"+message);
				result.setResultcode("-1");
				result.setResultmsg("解密失败!原因是:"+message);
				return result;
			}
		}
		reqlogger.info("机型变化历史查询请求内容:"+svccont);
		ValidPhoneHis validPhoneHis = MyTools.toBean(svccont,ValidPhoneHis.class);
		if(validPhoneHis==null){
			result.setResultcode("-1");
			result.setResultmsg("请求内容不能为空!");
			return result;
		}
		List<ValidPhoneHis> vp = null;
		try {
			//如果是查询sqlServer的话
//			if(validPhoneHis.getDataType()==1){
				vp = vphDao.findByPhone(validPhoneHis.getPhone());
//			}else if(validPhoneHis.getDataType()==2){
//				    DataPage<ValidPhoneHis> dataPage = new DataPage<ValidPhoneHis>().addSort("createtm",EsConstans.ASC);//排序的字段名和排序方式,只能根据一个字段来排序
//				    dataPage.setNeedPaging(false);//不需要分页
//				    //查询库的名称
//			        dataPage.setIndexName("pb_phone_his");
//			        dataPage=esAction.queryPage(validPhoneHis,dataPage,ValidPhoneHis.class);
//			        //取响应数据
//			        vp = dataPage.getDataList();//本页数据
//			}
			result.setSvccont(AESUntil.aesEncode(JSON.toJSONString(vp)));
			result.setResultcode("0");
		} catch (Exception e) {
			String message = MyTools.isEmpty(e.getMessage()) ? "NULL POINTER" : e.getMessage();
			message=message.indexOf("NULL POINTER")>-1?message:"查询SQL SERVER失败!";
			logger.error("机型变化历史查询失败!",e);
			result.setResultcode("-1");
			result.setResultmsg("机型变化历史查询失败!原因是:"+message);
		}
		reqlogger.info("机型变化历史响应参数:"+result);
		return result;
	}

	
}
