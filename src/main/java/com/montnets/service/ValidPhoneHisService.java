package com.montnets.service;

import com.montnets.bean.ValidPhoneHis;
import com.montnets.dao.BaseDao;
import com.montnets.result.RequestResult;
import com.montnets.result.ResponseResult;

/**
 * @Title: VaildPhoneHisService
 * @Description: 机型变化历史接口层
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月28日
 */
public interface ValidPhoneHisService extends BaseDao<ValidPhoneHis> {
	
	/**
	 * 查询机型变化历史的数据
	 * @return
	 */
	ResponseResult findByPhone(RequestResult requestResult);
}
