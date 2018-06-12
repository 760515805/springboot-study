package com.montnets.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.montnets.bean.ValidPhone;

/**
 * 
* Title: VaildPhoneDao
* Description:
* 有效手机数据层 
* Version:1.0.0  
* @author pancm
* @date 2018年3月23日
 */
@Mapper
public interface ValidPhoneDao extends BaseDao<ValidPhone> {
	/**
	 * 根据请求对象进行查询
	 * @param vaildPhoneDao
	 * @return
	 */
	List<ValidPhone> findVaildPhone(ValidPhone vaildPhoneDao);
	
	/**
	 * top查询
	 * @param vaildPhoneDao
	 * @return
	 */
	List<ValidPhone> queryTop(ValidPhone vaildPhoneDao);
	
	/**
	 * 总数查询
	 * @param vaildPhoneDao
	 * @return
	 */
	Long queryCount(ValidPhone vaildPhoneDao);
	
	
}
