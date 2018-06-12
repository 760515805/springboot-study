package com.montnets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.montnets.bean.ValidPhone;
import com.montnets.result.ResponseResult;
import com.montnets.utils.AESUntil;
import com.montnets.utils.HttpClientUtil;
import com.montnets.utils.MyTools;


/**
 * 
* Title: HttpPostUtil
* Description: Http Post 请求工具类
* Version:1.0.0  
 */
public class HttpPostUtil {
	private static final Logger LOG = LoggerFactory.getLogger(HttpPostUtil.class.getClass());
	
	/**
	 * post 请求
	 * @param parameters
	 * @param postUrl
	 * @return
	 * @throws Exception
	 */
	public static String post(String parameters, String postUrl) throws Exception {
		CloseableHttpClient httpClient= null;
		HttpPost method = null;
		String body = null;
		try {
			httpClient = HttpClientBuilder.create().build();
			method = new HttpPost(postUrl);
		if (method != null & MyTools.isNotEmpty(parameters)) {
				// 建立一个NameValuePair数组，用于存储欲传送的参数
				method.addHeader("Content-type",
						"application/json; charset=utf-8");
				method.setHeader("Accept", "application/json");
				method.setEntity(new StringEntity(parameters, Charset
						.forName("UTF-8")));

				HttpResponse response = httpClient.execute(method);

				int statusCode = response.getStatusLine().getStatusCode();

				// logger.debug("statusCode:" + statusCode);
				if (statusCode != HttpStatus.SC_OK) {
					LOG.error("Method failed:" + response.getStatusLine());
					LOG.error("Request:"+parameters);
					throw new Exception("statusCode：" + statusCode);
				}

				// Read the response body
				body = EntityUtils.toString(response.getEntity());
			}	
		} catch (IOException e) {
			// 网络错误
			throw e;
		} finally {
			httpClient.close();
			httpClient= null;
			method = null;
			// logger.debug("调用接口状态：" + status);
		}
		return body;
	}
	
	public static void main(String[] args) throws Exception {
		String url="http://localhost:8080/montnets/validphone";
		JSONObject json=new JSONObject();
		JSONObject json2=new JSONObject();
		json2.put("datatype", 2);
		json2.put("index", 2);
		json2.put("pagesize", 3);
		json2.put("querytype", 2);//"querytype":2
		json2.put("smsstatus", 1);
		
		json.put("seqid", "201707052020666666");
		json.put("timestamp", "20170705202015666");
		json.put("token", "890ecc582bc0512df9e3548178782eda");
		json.put("encryptsign", 1);
     	json.put("svccont",AESUntil.aesEncode(json2.toJSONString()));
     	
//		json.put("svccont", "ae08d332734715fc5edc6194e37cac3beb3e72457cd25790ce6435e000cf4bb738991338b85eb32656136f0500acfb2edc7392f95c23878778aafd5bec725f1942c2e0c33fd2f2783d4f0fc992e8c9255cf6be097cc14e3b2fd345ec178cfa4af67f9e08a238a38ccc48ff1b989aee4165ab7ae90dfcf08c1fbaaf09c425c9cbadeefac1108fc78ec6d0fe2cd0cde7ddcf0132aba927a9c26d82060e8c3f98e42b56898794dc1fcf847125c490bce35bd55799514c70714677a4829b1e589a19414c89f83737bfc5dfe9fb1023e4aa6052eab4bd498c4f9c5f71c4cc8f70ce2db56d3902d685c9810555e7eac5ff1aac");
		
		try {
			System.out.println("============"+HttpClientUtil.get(url,json));
//			String str=post(json.toJSONString(),url);
//			ResponseResult res=MyTools.toBean(str, ResponseResult.class);
//			String svccont=res.getSvccont();
//			List<ValidPhone> list = JSON.parseObject(svccont,List.class);
//			System.out.println(list);
//			System.out.println(list.get(list.size()-1));
		    
//			System.out.println(post(json.toJSONString(),url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
