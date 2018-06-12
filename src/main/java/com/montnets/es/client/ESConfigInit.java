package com.montnets.es.client;






import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.montnets.config.EsConstans;
import com.montnets.utils.MyTools;
import com.montnets.utils.NetAddrUtil;

/**
 * 
* Copyright: Copyright (c) 2018 Montnets
* 
* @ClassName: ESConfigInit.java
* @Description: 该类的功能描述
*主要对一些索引数据模板这些的检查
* @version: v1.0.0
* @author: chenhj
* @date: 2018年4月9日 下午2:50:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年4月9日     chenhj          v1.0.0               修改原因
 */
@Component
public class ESConfigInit {
	@Autowired
	private EsConstans Constans;
	@Autowired
	private ESConfigInit es;
	 
	private static final Logger logger = LoggerFactory.getLogger(ESConfigInit.class);
	public ESConfigInit(){
	}
	/**
	 * 初始化一些配置
	 */
	public synchronized boolean initEsConfig() throws Exception{
		
		boolean  falg=true;

		// 通信协议
		 String scheme= Constans.getScheme();
		 //集群IP
		 String hostname[]=Constans.getHostNames();
		 //indexs
		 String indexs[] = Constans.getIndexs();
		 // 集群名称
		 String clusterName= Constans.getClusterName();
    	/****************ES初始化*******************/
		
		 //集群IP
		if (hostname==null) {
			logger.error("集群IP不能为空!");
			falg=false;
			return falg;
		}
		//集群名称
		if (MyTools.isEmpty(clusterName)) {
			logger.error("集群名称不能为空!");
			falg=false;
			return falg;
		}
		
		//数据INDEX
		if (indexs==null) {
			logger.error("索引INDEX不能为空!");
			falg=false;
			return falg;
		}
		//初始化ES集群IP
		 EsConstans.HTTP_HOST=new HttpHost[hostname.length];
    	 for(int i=0;i<hostname.length;i++){
    		 try {
	    		 String url = hostname[i];
	    		 NetAddrUtil addr = new  NetAddrUtil();  
	    	     addr.IpPortFromUrl(url);
	        	 HttpHost httpHost = new HttpHost(addr.getIp(),addr.getPort(),scheme);
	        	 EsConstans.HTTP_HOST[i]=httpHost;
 			} catch (Exception e) {
				throw new RuntimeException("执行异常",e);
			}
    	 }
		//初始化client
    	allClose();//管他三七二十一,先走关闭一波
		if (EsConstans.client==null||EsConstans.client.getRhlClient()==null) {
			//5次重连
			for(int i=0;i<EsConstans.reconnectNum;i++){
				EsConstans.client =RestClientFactory.build(60,20,EsConstans.HTTP_HOST);
				if(EsConstans.client!=null)EsConstans.client.init();
	    		//重连5次还是失败直接中止程序
	    		if(EsConstans.client==null||EsConstans.client.getRhlClient()==null){
	    			logger.debug(EsConstans.reconnectNum+"次重连ES失败!"+EsConstans.reconnectNum+"失败后程序停止");
	    		 if(i==EsConstans.reconnectNum-1){
	    				logger.error("连接ES集群出错");
	    				falg=false;
	    				return falg;
	    		 }
	    			//为避免太频繁线程睡眠会
	    		//	Thread.sleep(3000);
	    		}else{
	    			break;//初始化成功,退出循环
	    		}
    		}
		}
		//判断index是否存在,不存在的话创建一个
		for(String index:indexs){
			boolean exist = isExistsIndex(index);
			if(!exist){
				logger.error("ES索引库不存在:"+index);
				return false;
//				if(!createIndex(index)){
//					logger.error("索引创建失败");
//					allClose();//关闭连接
//					falg=false;
//					return falg;
//			};
		  }
		}
		logger.info("ES系统配置初始化完成..."+"初始状态为:"+falg);
		return falg;
		/*******************ES初始化结束***************************/
	}
	 /**
     * 判断指定的索引名是否存在
     * @return  存在：true; 不存在：false;
     */
	public boolean isExistsIndex(String index){
        boolean isExists=true;
		try {
			RestClient restClient = EsConstans.client.getRhlClient().getLowLevelClient();
	        Response response = restClient.performRequest("HEAD","/"+index,Collections.<String, String>emptyMap());
	        isExists =response.getStatusLine().getReasonPhrase().equals("OK");
		} catch (IOException e) {
			logger.error("检查INDEX是否存在报错",e);
			isExists=false;
		}
        return isExists;
    }
    /**
     * 如果索引库不存在则创建一个
     * @return  成功：true; 失败：false;
     */
//  public boolean createIndex(String index){
//    	boolean falg = true;
//		//数据模版
//		 String mapping_file_path[] = conf.get("es.mappingfile").split(",");
//    	//数据模板
//		if (mapping_file_path==null) {
//			logger.error("数据模板文件路径配置不能为空!");
//			return false;
//		}else{
//			Constant.MAPPING_ARR=new JSONArray();
//			for(int i=0;i<mapping_file_path.length;i++){
//				String pathName = mapping_file_path[i];
//					InputStream in = ESConfigInit.class.getClassLoader().getResourceAsStream(pathName);
//					String mapping = MyTools.inputstr2Str(in,"UTF-8");
//					if(MyTools.isEmpty(mapping)){
//							logger.error("数据模板文件不存在！,检查路径是否输入正确,异常文件名:"+pathName);
//							return false;
//					}
//					JSONObject json = JSON.parseObject(mapping);
//					Constant.MAPPING_ARR.add(json);
//			}
//		}
//	String indexflag = null;//标志位
//	JSONObject mapping = null;
//	//循环找出对应index的数据模板
//	getMapping:for(Object f:Constant.MAPPING_ARR){
//		mapping = (JSONObject) f;
//		if(mapping.getString("index").equalsIgnoreCase(index)){
//			indexflag=mapping.getString("index");
//			break getMapping;//跳出内循环
//		}
//	}
//	if(null==indexflag||null==mapping||mapping.isEmpty()){
//		logger.error(index+"找不到指定的数据模板,查看文件是否正确...索引名:"+index);
//		return false;
//	}
//	//开始创建库
//    CreateIndexRequest request = new CreateIndexRequest(index); 
//    	try {
//			//加载数据类型
//	    	request.mapping(index,mapping.getString("mappings"),XContentType.JSON);
//	    	//分片数
//	    	request.settings(mapping.getString("settings"),XContentType.JSON);
//			CreateIndexResponse createIndexResponse = Constant.client.getRhlClient().indices().create(request);
//			falg = createIndexResponse.isAcknowledged();
//			if(falg){
//				//设置查询单次返回最大值
//				maxResultWindow(index);
//			}
//			logger.info("创建索引库"+index+",状态为:"+falg);
//		} catch (IOException e) {
//			logger.error("创建INDEX报错",e);
//			falg=false;
//		}catch (NullPointerException e) {
//			logger.error("模板文件中的mappings或settings不能为空",e);
//			falg=false;
//		}
//    	return falg;
//    }
    /**
     * 设置每次可最大取多少数据，超过此数据条数报错
     * @throws IOException 
     */
//    private void maxResultWindow(String index) throws IOException{
//    	RestClient restClient = Constant.client.getRhlClient().getLowLevelClient();
//        try {
//        	JSONObject json = new JSONObject();
//        	JSONObject json1 = new JSONObject();
//        	json1.put("max_result_window", Constant.MAX_RESULT_WINDOW+"");
//        	json.put("index",json1);
//   		 	String source =json.toJSONString();
//   		 	HttpEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
//			restClient.performRequest("PUT","/"+index+"/_settings",Collections.<String, String>emptyMap(),entity);
//		} catch (IOException e) {
//			throw e;
//		}
//    }
    /**
     * 关闭ES客户端
     */
	public void allClose() {
		synchronized(this){
			if(EsConstans.client!=null){
				EsConstans.client.closeRestHighLevelClient();
				EsConstans.client = null;
			}
		}
	}
	/**
	 *检查ES的状态
	 * @param  index 索引库
	 * @param  isQuery 是否是查询  true：是查询    false：非查询操作
	 * @return 
	 * @throws Exception 
	 */
 public boolean inspectEsStatus(String index,boolean isQuery) throws Exception{
		boolean flag = true;
		//ES需要重新初始化
		if(!EsConstans.cacheFalg){
			synchronized(this){
				logger.debug("ES开始重新初始化当前状态:"+EsConstans.cacheFalg);
			  //再次检查,双重锁
			    try {
			    	EsConstans.cacheFalg =es.initEsConfig();
				   flag=EsConstans.cacheFalg;
			  } catch (Exception e) {
				  flag=false;
				  throw e;
			   }
			}
		}
		//如果不存在,而且又是非查询的,注意:只有插入的时候才执行以下,如果是查询直接忽略该报错还是报错
//		if(!es.isExistsIndex(index)&&flag&&!isQuery){
//			flag=es.createIndex(index);
//			logger.debug("ES创建索引库:"+index+">>创建状态:"+flag);
//		}
		return flag;
	}
}
