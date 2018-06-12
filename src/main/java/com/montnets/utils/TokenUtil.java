package com.montnets.utils;

import com.montnets.result.RequestResult;

/**
 * 
* @Title: TokenUtil
* @Description: 
* token工具类
* @Version:1.0.0  
* @author pancm
* @date 2018年3月28日
 */
public class TokenUtil {
	
	/**
	 * token检查
	 */
	public static boolean checkToken(RequestResult rsq){
		String token =rsq.getToken();
    	String seqid=rsq.getSeqid();
    	String timestamp=rsq.getTimestamp();
    	String md5token=MyTools.md5Encode(seqid+","+timestamp);
		return md5token.equals(token);
	}
}
