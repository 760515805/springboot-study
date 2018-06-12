package com.montnets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.montnets.bean.ValidPhoneHis;


/**
 * 
* Title: ValidPhoneHisDao
* Description:
* 机型变化历史Dao层 
* Version:1.0.0  
* @author pancm
* @date 2018年3月23日
 */
@Mapper
public interface ValidPhoneHisDao extends BaseDao<ValidPhoneHis>{
	
	
	/**
	 * 通过手机号查询
	 * @return
	 */
	List<ValidPhoneHis> findByPhone(long phone);
	
}
