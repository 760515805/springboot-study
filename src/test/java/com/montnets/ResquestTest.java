package com.montnets;

import com.alibaba.fastjson.JSONObject;
import com.montnets.utils.HttpClientUtil;

/**
 * 
* @Title: ResquestTest
* @Description:
* 模拟请求测试 
* @Version:1.0.0  
* @author pancm
* @date 2018年4月11日
 */
public class ResquestTest {
	static String postUrl="http://localhost:8080/montnets/validphone";
	
	public static void main(String[] args) {
		
		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void test() throws Exception{
		JSONObject json=new JSONObject();
		JSONObject json2=new JSONObject();
		json2.put("brand", "苹果");
		json2.put("imei", "IMEI 1111");
		json2.put("imsi", "IMSI 1111");
		json2.put("ischange", 0);
		json2.put("isupflag", "2");
		json2.put("mbstatus", "0");
		json2.put("mobiletype", "0");
		json2.put("model", "iPhone 8");
		json2.put("osver", "ios6.0");
		json2.put("model", "iPhone 8");
		json2.put("phoneos", 1);
		json2.put("rmsfg", 0);
		json2.put("rmsstatus", 0);
		json2.put("rptway", 2);
		json2.put("smsstatus", 1);
		json2.put("upsrc", 1);
		json2.put("xbrowser", "类型1111");
		
		json.put("seqid", "201707052020666666");
		json.put("timestamp", "20170705202015666");
		json.put("token", "890ecc582bc0512df9e3548178782eda");
		json.put("encryptsign", 0);
		long k=98765433101L;
		for(int i=0;i<100000;i++){
			k=k+i;
			json2.put("phone", k);
			json.put("svccont", json2.toJSONString());
			HttpClientUtil.post(postUrl, json.toJSONString());	
		}
	}
	
	
	
}
