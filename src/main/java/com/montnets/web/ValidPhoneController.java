package com.montnets.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;
import com.montnets.service.ValidPhoneService;

/**
 * 
* Title: ValidPhoneController
* Description:
* 有效手机号码
* Version:1.0.0  
* @author pancm
* @date 2018年3月23日
 */
@RestController
@RequestMapping("/montnets")
public class ValidPhoneController {
	@Autowired
	private ValidPhoneService vs;
	
	 @RequestMapping(value="/validphone",method=RequestMethod.GET)
	  public ResponseResult queryForValidPhone(RequestResult rsq) {
		 return vs.findValidPhone(rsq);
	  }
	 
	 @RequestMapping(value="/validphone",method=RequestMethod.POST)
	  public ResponseResult insertForValidPhone(@RequestBody RequestResult rsq) {
		 return vs.insertValidPhone(rsq);
	  } 
	   
	 @RequestMapping(value="/validphone",method=RequestMethod.PUT)
	  public ResponseResult updateForValidPhone(@RequestBody RequestResult rsq) {
		 return vs.updateValidPhone(rsq);
	  } 
	 
	 
}
