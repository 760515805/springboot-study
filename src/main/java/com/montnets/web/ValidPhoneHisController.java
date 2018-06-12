package com.montnets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;
import com.montnets.service.ValidPhoneHisService;

/**
 * 
* Title: ValidPhoneHisController
* Description:
* 机型变化历史查询
* Version:1.0.0  
* @author pancm
* @date 2018年3月29日
 */
@RestController
@RequestMapping("/montnets")
public class ValidPhoneHisController {
	@Autowired
	private ValidPhoneHisService vsh;
	
	 @RequestMapping(value="/validphonehis",method=RequestMethod.GET)
	  public ResponseResult queryForValidPhone(RequestResult requestResult) {
		 return vsh.findByPhone(requestResult);
	  }
}
