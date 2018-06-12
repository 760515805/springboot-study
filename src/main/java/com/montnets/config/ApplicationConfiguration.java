package com.montnets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.montnets.constant.Constans;

/**
 * 
* @Title: ApplicationConfiguration
* @Description:
*  Application参数获取
* @Version:1.0.0  
* @author pancm
* @date 2018年4月3日
 */
@Component
public class ApplicationConfiguration {
	@Value("${kafka.topic_name}")
    private String topicName; 
		
		
	@Value("${kafka.bootstrap_servers}")
    private String bootstrap_servers;

	@Value("${vaildphone.status.size}")
    private Integer size;
	
		
	/**  
	 * 获取topicName  
	 * @return  topicName  
	 */
	public String getTopicName() {
		return topicName;
	}



	/**  
	 * 获取bootstrap_servers  
	 * @return  bootstrap_servers  
	 */
	public String getBootstrap_servers() {
		return bootstrap_servers.replaceAll(Constans.SEPERATOR_SPLIT, Constans.COMMA_SIGN);
	}



	/**  
	 * 获取size  
	 * @return  size  
	 */
	public Integer getSize() {
		return size;
	}


	
	
}
