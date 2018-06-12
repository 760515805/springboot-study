package com.montnets.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.montnets.bean.DataPage;
import com.montnets.config.EsConstans;
import com.montnets.es.exception.EsInitMonException;
import com.montnets.utils.MyTools;


/**
 * 
* Copyright: Copyright (c) 2018 Montnets
* 
* @ClassName: SearchIndexAction.java
* @Description: 该类的功能描述
* es查询工具类
* @version: v1.0.0
* @author: chenhj
* @date: 2018年6月6日 下午3:17:38 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月6日     chenhj          v1.0.0               修改原因
 */
public class SearchIndexAction {
	  private String index;
	  private String type;	  
	  private RestHighLevelClient rhlClient;
	  private	QueryBuilder queryBuilder;
	  private String sortfield;
	  private String order;
	  
	  @SuppressWarnings("rawtypes")
	private DataPage dataPage;

	  private String[] includeFields =null;
	  private String[] excludeFields = null;
	  
	  private static final Logger LOG = LoggerFactory.getLogger(SearchIndexAction.class);

	  /**
	   * 只有在执行一次查询之后才会有总数
	   */
	private long totalCount;
	@SuppressWarnings("rawtypes")
	public SearchIndexAction(RestHighLevelClient rhlClient,DataPage dataPage){
		this.index=dataPage.getIndexName();
		this.type =dataPage.getIndexName();
		this.dataPage=dataPage;
		this.rhlClient=rhlClient;
	}
	 /**
	  * 设置过滤条件
	  */
	 public SearchIndexAction setQueryBuilder(QueryBuilder queryBuilder) {
			this.queryBuilder = queryBuilder;
			return this;
	 }
	 /**
	  * 设置排序(可选)
	  * @param field 排序的参数
	  * @param order 排序方法
	  */
	 public SearchIndexAction addSort(String field, String order){
		 this.sortfield=field;
		 this.order=order;
		 return this;
	 }
	 /**
	  * 字段过滤(可选)
	  * @param includeFields 需要的字段
	  * @param excludeFields 不需要的字段
	  */
	 public SearchIndexAction fetchSource(String[] includeFields,String[] excludeFields){
		 this.excludeFields=excludeFields;
		 this.includeFields=includeFields;
		 return this;
	 }
	/**
	 * 查询方法
	* @author chenhongjie 
	 */
	public  List<Map<String, Object>>  sraech() throws Exception{
		
		 List<Map<String, Object>>  list = new ArrayList<Map<String, Object>>();
		 try {
			 SearchRequest searchRequest = new SearchRequest(index); 
			 searchRequest.types(type);
			 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
			 	//是否需要分页
	    		if(dataPage.isNeedPaging()){
			        searchSourceBuilder.from(dataPage.getStartIndex()).size(dataPage.getPageSize());  
	    		}else{
	    			searchSourceBuilder.size(1000);
	    		}
			//是否需要排序,没有则不需要
		    if(MyTools.isNotEmpty(sortfield)&&MyTools.isNotEmpty(order)){
					   if("desc".equals(order)){
						   searchSourceBuilder.sort(new FieldSortBuilder(sortfield).order(SortOrder.DESC));
					   }else if("asc".equals(order)){
						   searchSourceBuilder.sort(new FieldSortBuilder(sortfield).order(SortOrder.ASC));
			      }
			  }
		     //设置过滤字段
			 if(includeFields!=null||excludeFields!=null){
				 searchSourceBuilder.fetchSource(includeFields,excludeFields);
			 }
			 //不需要解释
			 searchSourceBuilder.explain(false);
			 //不需要版本号
			 searchSourceBuilder.version(false);
		     //是否有自定义条件
		     if(queryBuilder!=null)searchSourceBuilder.query(queryBuilder); 
		     LOG.info("查询条件:"+searchSourceBuilder.toString());
			 searchRequest.source(searchSourceBuilder); 
			 SearchResponse searchResponse = rhlClient.search(searchRequest);
			 SearchHits hits = searchResponse.getHits();
		     for (SearchHit hit : hits) {
		    	 list.add(hit.getSourceAsMap());
		      }
			// RestStatus status = searchResponse.status();
			 totalCount=count();
			// System.out.println(totalCount);
		 } catch (Exception e) {			
				throw e;
		}
	    return list; 
	}
	/**
	 * 根据ID查询
	* @author chenhongjie 
	 */
	public  Map<String, Object>  sraechById(String idvalue) throws Exception{
				
		Map<String, Object> map =new HashMap<String, Object>();
		 try {
			 GetRequest getRequest = new GetRequest(index,type,idvalue);
		     //设置过滤字段
			 if(includeFields!=null||excludeFields!=null){
				 FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includeFields, excludeFields);
				 getRequest.fetchSourceContext(fetchSourceContext);
			 }
			 GetResponse getResponse = rhlClient.get(getRequest);
			 if(getResponse.isExists()){
				 map=getResponse.getSourceAsMap();
			 };
		 } catch (Exception e) {			
				throw e;
		}
	    return map; 
	}
    /**  
     * 查询总数
     * @return  
     * @throws Exception  
     */  
    public Long count() throws Exception {  
    	     	
    	 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
	     //是否有自定义条件
	     if(queryBuilder!=null)searchSourceBuilder.query(queryBuilder);

	     LOG.info("总数查询条件:"+searchSourceBuilder.toString());
	     //取低级客户端API来执行这步操作
	     RestClient restClient = rhlClient.getLowLevelClient();
		 String endPoint = "/" + index + "/" + type +"/_count";
		 //删除的条件
		 String source = searchSourceBuilder.toString();
		 HttpEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
		 Response response =restClient.performRequest("GET", endPoint,Collections.<String, String> emptyMap(),entity);
		 String responseBody = EntityUtils.toString(response.getEntity());
		 JSONObject json =JSON.parseObject(responseBody);
		 //"count": 2014889998,
		 Long count = json.getLong("count");
		 return count;
    }
    /**
     * 根据ID查询数据是否存在
     * @param ID值
     */
    public  boolean existsDocById(String idvalue){
    	boolean isExists=false;
 		try {
 			RestClient restClient = EsConstans.client.getRhlClient().getLowLevelClient();
 			String endPoint = "/" + index + "/" + type +"/"+idvalue.trim();
 	        Response response = restClient.performRequest("HEAD",endPoint,Collections.<String, String>emptyMap());
 	        isExists =response.getStatusLine().getReasonPhrase().equals("OK");
 		} catch (IOException e) {
 			isExists=false;
 		}
         return isExists;
    }
	/**
     * 获取当前请求的所有条数
     */
	public long getTotalCount() {
		return totalCount;
	}
	public static void main(String[] args) {
		throw new EsInitMonException("初始化失败");
	}
}
