package com.montnets.service;

import java.util.Map;

import com.montnets.bean.DataPage;
import com.montnets.bean.ValidPhone;
import com.montnets.bean.ValidPhoneHis;

/**
 * 
* Copyright: Copyright (c) 2018 Montnets
* 
* @ClassName: IEsService.java
* @Description: 该类的功能描述
*es接口
* @version: v1.0.0
* @author: chenhj
 * @param <T>
* @date: 2018年6月6日 下午3:18:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年6月6日     chenhj          v1.0.0               修改原因
 */
public interface IEsService {
    /**
     * ES查询数据方法
     * @param map 条件集,无条件可为null
     * @param esBean es目标库
     * @param includeFields 需要的参数字段,如果全需要全部字段无需设置
     * @return 返回数据集合
     */
	public  DataPage<ValidPhone> searchDoc(ValidPhone  vaildPhone,DataPage<ValidPhone> esBean,String ...includeFields) throws Exception;
    /**
     * ES查询数据方法
     * @param map 条件集,无条件可为null
     * @param esBean es目标库
     * @param includeFields 需要的参数字段,如果全需要全部字段无需设置
     * @return 返回数据集合
     */
	public  DataPage<ValidPhoneHis> searchDoc(ValidPhoneHis  validPhoneHis,DataPage<ValidPhoneHis> esBean,String ...includeFields) throws Exception;
	/**
	 * 根据ID获取文档
     * @param esBean  es目标库
     * @param idvalue ID值
     * @param includeFields 所需字段值,需要全部字段则无需填写
	 */
	public  Map<String,Object> searchDocById(DataPage<?> esBean,String idvalue,String... includeFields) throws Exception;
    /**
     * ES查询总数方法
     * @param map 条件集,无条件可为null
     * @param esBean es目标库
     * @return 返回数据条数
     */
	public  Long count(ValidPhone  vaildPhone,DataPage<?> page) throws Exception;
	
	
	
	
}
