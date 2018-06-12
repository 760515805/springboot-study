package com.montnets;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.montnets.utils.MyTools;

public class Test {

	public static void main(String[] args) {
//		test();
		test1();
	}
	
	private static void test() {
		List<Object> list=new ArrayList<Object>();
		for(int i=0;i<100;i++){
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
			json2.put("phone", i++);
			list.add(json2);
		}
	    
		String msg=MyTools.toString(list);
		System.out.println("====="+msg);
		list=JSON.parseArray(msg, Object.class);
		System.out.println("+++++"+MyTools.toString(list));
	}

	private static void test1() {
		String msg="[{\"DATATYPE\":\"0\",\"PHONE\":\"18998900059\",\"MBSTATUS\":\"1\",\"SMSSTATUS\":\"1\",\"RMSST_A\":\"1\",\"RMSST_DLA\" : \"1\" ,\"RMSST_B\" : \"1\" ,\"RMSST_DLB\" : \"1\" ,\"RMSST_C\" : \"1\" ,\"RMSST_DLC\" : \"1\" ,\"PHONEOS\" : \"0\" ,\"OSVER\" : \"\" ,\"BRAND\" : \"\" ,\"MODEL\" : \"\" ,\"XBROWSER\" : \"\" ,\"IMEI\" : \"\" ,\"MEID\" : \"\" ,\"IMSI\" : \"\" ,\"RESOLUTION\" : \"\" ,\"SN\" : \"\" ,\"MAC\" : \"\" ,\"SDKVER\" : \"\" ,\"ISUPFLAG\" : \"2\" ,\"RPTFREQ\" : \"1296000\" ,\"RPTWAY\" : \"2\" ,\"UPSRC\" : \"1\" ,\"CREATETM\" : \"2018-04-12 10��15��30\" ,\"LASTUPDATETM\" : \"1990-01-01\" ,\"LASTDLTM\" : \"1990-01-01\" ,\"LASTUPTM\" : \"1990-01-01\"}]";
		List list=JSON.parseArray(msg, JSON.class);
		System.out.println(MyTools.toString(list));
		
	}
	
}
