package com.montnets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
* @Title: App
* @Description: 
* 主程序 程序入口
* @Version:1.0.0  
* @author pancm
* @date 2018年3月22日
 */
@SpringBootApplication
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(App.class, args);
		logger.info("程序成功启动!");
    }                                                                                            
}
