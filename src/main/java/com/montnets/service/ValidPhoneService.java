package com.montnets.service;

import com.montnets.bean.ValidPhone;
import com.montnets.dao.BaseDao;
import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;

/**
 * @Title: ValidPhoneService
 * @Description: 有效手机接口层
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月23日
 */
public interface ValidPhoneService extends BaseDao<ValidPhone> {
	
	/**
	 * 查询有效手机的数据
	 * @return
	 */
	ResponseResult findValidPhone(RequestResult requestResult);
	
	
	/**
	 * 添加有效手机的数据
	 * @return
	 */
	ResponseResult insertValidPhone(RequestResult requestResult);
	
	
	/**
	 * 修改有效手机的数据
	 * @return
	 */
	ResponseResult updateValidPhone(RequestResult requestResult);
}
