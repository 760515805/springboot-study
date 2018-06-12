package com.montnets.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ContentTooLongException;
import org.apache.http.conn.ConnectTimeoutException;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.montnets.bean.DataPage;
import com.montnets.bean.ValidPhone;
import com.montnets.bean.ValidPhoneHis;
import com.montnets.config.EsConstans;
import com.montnets.es.ConditionEsUtil;
import com.montnets.es.SearchIndexAction;
import com.montnets.es.client.ESConfigInit;
import com.montnets.es.exception.EsInitMonException;
import com.montnets.service.IEsService;
/**
 * 
* Copyright: Copyright (c) 2018 Montnets
* 
* @ClassName: EsServiceImpl.java
* @Description: 该类的功能描述
*ES操作接口实现类
* @version: v1.0.0
* @author: chenhj
* @date: 2018年6月6日 下午3:18:44 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月6日     chenhj          v1.0.0               修改原因
 */
@Service
public class EsServiceImpl extends ConditionEsUtil  implements IEsService {
	private static final Logger LOG = LoggerFactory.getLogger(EsServiceImpl.class);
	RestHighLevelClient client = null;
	@Autowired
	private ESConfigInit es;
	private long total=0l;
	@Override
	public DataPage<ValidPhone> searchDoc(ValidPhone vaildPhone, DataPage<ValidPhone> dataPage, String... includeFields) throws Exception {
		//是否需要分页数据
		if (dataPage.isNeedData()) {
			if (dataPage.isNeedTotalCount()) {
					//条件处理
					QueryBuilder queryQange = conditionUtil(vaildPhone);
					//取出该分页下的数据
					List<Map<String, Object>> resultList = searchDoc(queryQange,dataPage,includeFields);
					List<ValidPhone> list = new ArrayList<ValidPhone>();
					for(Map<String,Object> map:resultList){
						JSONObject json=(JSONObject) JSON.toJSON(map);
						ValidPhone va = JSON.parseObject(json.toJSONString(), ValidPhone.class);
						list.add(va);
					}
					dataPage.setDataList(list);
					dataPage.setTotalCount(total);
			}
		}
		return dataPage;
	}
	@Override
	public DataPage<ValidPhoneHis> searchDoc(ValidPhoneHis validPhoneHis, DataPage<ValidPhoneHis> page, String... includeFields) throws Exception {
		//是否需要分页数据
		if (page.isNeedData()) {
			if (page.isNeedTotalCount()) {
					//条件处理
					QueryBuilder queryQange = conditionUtil(validPhoneHis);
					//取出该分页下的数据
					List<Map<String, Object>> resultList = searchDoc(queryQange,page,includeFields);
					List<ValidPhoneHis> list = new ArrayList<ValidPhoneHis>();
					for(Map<String,Object> map:resultList){
						JSONObject json=(JSONObject) JSON.toJSON(map);
						ValidPhoneHis va = JSON.parseObject(json.toJSONString(), ValidPhoneHis.class);
						list.add(va);
					}
					page.setDataList(list);
					page.setTotalCount(total);
			}
		}
		return page;
	}
	private <T> List<Map<String,Object>> searchDoc(QueryBuilder queryQange,DataPage<T> page,String ...includeFields) throws Exception {
		if(page==null)throw new NullPointerException("DataPage 不能为null");
		//检查连接是否正常,不正常重新初始化
		if(!inspectEsStatus(page.getIndexName()))throw new EsInitMonException("ES重新初始化失败...当前状态:"+EsConstans.cacheFalg);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	 	try {
	 		//QueryBuilder queryQange =conditionUtil(map);
	 		SearchIndexAction search = new SearchIndexAction(client,page).setQueryBuilder(queryQange)
	 				.addSort(page.getSortField(), page.getOrder());
	 		if(includeFields!=null&&includeFields.length>0)search.fetchSource(includeFields, null);
	 		list =search.sraech();	
	 		total=search.getTotalCount();
	 		return list;
	 		 //请求超时，大概出问题了，初始化走一波
		 	} catch (ConnectTimeoutException  e) {
				EsConstans.cacheFalg=false;
				throw e;
			 
			}catch (ContentTooLongException e) {
				LOG.error("单次获取的的数据条数设置过大,请设置小一点:"+e);
				return list;
			}catch (IOException e) {
				//该异常不处理
				if(e.getMessage().contains("listener timeout after waiting")){
					LOG.error("listener timeout after waiting:"+e);
					return list;
				}else{
					throw e;
				}
			} catch (IllegalStateException e) {
				
				if(e.getMessage().contains("STOPPED")){
					EsConstans.cacheFalg=false;
					LOG.error("ES运行异常,需要重新初始化,初始化失败则程序停止:"+e);
					//throw e;
				}else{
					 throw e;
				 }
			}
		return list;
	}
	@SuppressWarnings("all")
	@Override
	public Map searchDocById(DataPage esBean, String idvalue, String... includeFields) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if(esBean==null)throw new NullPointerException("esBean 不能为null");
		//检查连接是否正常,不正常重新初始化
		if(!inspectEsStatus(esBean.getIndexName()))throw new EsInitMonException("ES重新初始化失败...当前状态:"+EsConstans.cacheFalg);
		try {
	 		SearchIndexAction search = new SearchIndexAction(client,esBean).fetchSource(includeFields, null);
	 		map=search.sraechById(idvalue);
		} catch (IOException e) {
			//该异常不处理
			if(e.getMessage().contains("listener timeout after waiting")){
				LOG.error("listener timeout after waiting:"+e);
				return map;
			}else{
				throw e;
			}
		} catch (IllegalStateException e) {
				//连接关闭了,赶紧初始化打开,异常不抛出
			if(e.getMessage().contains("STOPPED")){
					EsConstans.cacheFalg=false;
					LOG.error("ES运行异常,需要重新初始化,初始化失败则程序停止:"+e);
				}
			}
		return map;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Long count(ValidPhone  vaildPhone, DataPage esBean) throws Exception {
		if(esBean==null)throw new NullPointerException("esBean 不能为null");
		//检查连接是否正常,不正常重新初始化
		if(!inspectEsStatus(esBean.getIndexName()))throw new EsInitMonException("ES重新初始化失败...当前状态:"+EsConstans.cacheFalg);
		try {
			QueryBuilder queryQange =conditionUtil(vaildPhone);
			SearchIndexAction search = new SearchIndexAction(client,esBean).setQueryBuilder(queryQange);
			Long count =search.count();
			return count;
	 		 //请求超时，大概出问题了，初始化走一波
		} catch (ConnectTimeoutException  e) {
				EsConstans.cacheFalg=false;
				throw e;
			 //响应超时
		}catch (IOException e) {
			//该异常不处理
			if(e.getMessage().contains("listener timeout after waiting")){
				LOG.error("listener timeout after waiting:"+e);
				return null;
			}else{
				throw e;
			}	
		}catch (IllegalStateException e) {
				//连接关闭了,赶紧初始化打开
			if(e.getMessage().contains("STOPPED")){
				EsConstans.cacheFalg=false;
				LOG.error("ES运行异常,需要重新初始化,初始化失败则程序停止:"+e);
			 }else{
				 throw e;
			 }
		}
		return 0l;
	}
	/**
	 *检查ES的状态
	 * @param  index 索引库
	 * @param  isQuery 是否是查询  true：是查询    false：非查询操作
	 * @return 
	 * @throws Exception 
	 */
 private  boolean inspectEsStatus(String index) throws Exception{
		//检查连接
		try {
			client=EsConstans.client.getRhlClient();
		} catch (NullPointerException e) {
			EsConstans.cacheFalg=false;
		}
		if(null==client)EsConstans.cacheFalg=false;
		boolean flag = true;
		//ES需要重新初始化
		if(!EsConstans.cacheFalg){
			synchronized(this){
			  LOG.debug("ES开始开始初始化当前状态:"+EsConstans.cacheFalg);
			  //再次检查,双重锁
			  if(EsConstans.cacheFalg)return flag;
			    try {
				   EsConstans.cacheFalg =es.initEsConfig();
				   flag=EsConstans.cacheFalg;
				   if(EsConstans.client!=null)client=EsConstans.client.getRhlClient();
			  } catch (Exception e) {
				  flag=false;
				  throw e;
			   }
			}
		}
		//如果不存在,而且又是非查询的,注意:只有插入的时候才执行以下,如果是查询直接忽略该报错还是报错
		//if(!es.isExistsIndex(index)&&flag&&!isQuery){
			//flag=es.createIndex(index);
		//	LOG.debug("ES创建索引库:"+index+">>创建状态:"+flag);
		//}
		//如果索引库不存在,直接报错
		if(!es.isExistsIndex(index)){
			//flag=es.createIndex(index);
			flag=false;
			LOG.error("ES创索引库不存在:"+index+">>禁止往下执行..");
		}
		return flag;
	}
}
