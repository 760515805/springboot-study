package com.montnets.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * 
* @Title: PageHelperConfiguration
* @Description:
* 分页 帮助类
* @Version:1.0.0  
* @author pancm
* @date 2018年3月27日
 */
@Configuration  
public class PageHelperConfiguration {  
     
	/**
	 * 注册MyBatis的分页插件PageHelper 
	 * @return
	 */
    @Bean  
    public PageHelper pageHelper() {  
        PageHelper pageHelper = new PageHelper();  
        Properties p = new Properties(); 
        //是否将参数offset作为PageNum使用
        p.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        p.setProperty("rowBoundsWithCount", "true");  
        //是否分页合理化
        p.setProperty("reasonable", "false");  
        pageHelper.setProperties(p);  
        return pageHelper;  
    }  
}  
