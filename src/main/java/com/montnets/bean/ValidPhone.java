package com.montnets.bean;

import com.montnets.utils.MyTools;

/**
 * 
* @Title: VaildPhone
* @Description:
* 有效号码 
* @Version:1.0.0  
* @author pancm
* @date 2018年3月23日
 */
public class ValidPhone {
	
	/**
	 * 编号
	 */
	private Long id;
	/**
	 * 手机号
	 */
	private Long phone;
	
	/**
	 * 手机号码状态：
		0:正常
		1:欠费
		2:号码过期
		15:其它
	 */
	private Integer mbstatus;
	
	/**
	 * 短信状态：
		1:表示号码有效，能发短信；
		2:表示号码无效，不能发短信；
	 */
	private Integer smsstatus;
	
	
	/**
	 *运营商富信状态：
		0:未知
		1:可以发
		2:不能发
	 */
	private Integer rmsst_a;
	
	
	/**
	 * 运营商富信下载状态：
		0:未知
		1:可下载
		2:不可下载
	 */
	private Integer rmsst_dla;
	
	
	/**
	 * 手机厂商富信状态:
		0:未知
		1:可以发
		2:不能发
	 */
	private Integer rmsst_b;
	
	
	/**
	 * 手机厂商富信下载状态:
		0:未知
		1:可下载
		2:不可下载
	 */
	private Integer rmsst_dlb;
	
	
	/**
	 * OTT IM富信状态
		0:未知
		1:可以发
		2:不能发
	 */
	private Integer rmsst_c;
	
	
	/**
	 * OTT IM富信下载状态
		0:未知
		1:可下载
		2:不可下载
	 */
	private Integer rmsst_dlc;
	
	
	
	
	/**
	 * 创建时间
	 */
	private String createtm;
	/**
	 * 上一次更新时间
	 */
	private String lastupdatetm;
	
	
	private String startlastupdatetm;
	
	private String endlastupdatetm;
	
	/**
	 * 最近下载时间
	 */
	private String lastdltm;
	
	private String startlastdltm;
	
	
	private String endlastdltm;
	
	
	
	/**
	 * 手机操作系统
	 * 1,苹果
	 * 2,安卓
	 */
	private Integer phoneos;
	/**
	 * 操作系统版本
	 */
	private String osver;
	
	/**
	 * 操作系统名称
	 */
	private String osvername;
	
	/**
	 * 手机品牌
	 */
	private String brand;
	
	/**
	 * 品牌名称
	 */
	private String brandname;
	/**
	 * 手机型号
	 */
	private String model;
	
	/**
	 * 型号名称
	 */
	private String modelname;
	/**
	 * 浏览器类型
	 */
	private String xbrowser;
	/**
	 * 手机串码
	 */
	private String imei;
	/**
	 * SIM卡的电子串号
	 */
	private String imsi;
	
	/**
	 * meid
	 */
	private String meid;
	
	/**
	 * 屏幕分辨率
	 */
	private String resolution;
	
	/**
	 * 设备序列号
	 */
	private String sn;
	
	/**
	 * MAC地址
	 */
	private String mac;
	
	/**
	 * SDK版本
	 */
	private String sdkver;
	
	/**
	 * 启个性化控制
		1:启用
		2:不启用
	 */
	private Integer isupflag;
	
	
	/**
	 * SDK上报频率，单位：秒. 默认值15天
	 */
	private Integer rptfreq;
	
	/**
	 * 上报方式
		0:不上报
		1:任何网络
		2:仅WIFI
		3:移动数据网络
	 */
	private Integer rptway;
	
	
	/**
	 *  手机号码来源：
		0、无效值；
		1、MBOSS上报；
		2、收集中心上报；
		3、网关上报；
		历史库分析所得；
	 */
	private Integer upsrc;
	
	/**
	 * 最近一次上报时间
	 */
	private String lastrpttm;
	
	/**
	 * 手机所属国家
	 */
	private Integer mobilecountry;

	/**
	 * 手机类型
	 */
	private Integer mobiletype;
	
	/**
	 * 省份代码
	 */
	private Integer mobilearea;
	
	/**
	 * 数据来源 
	 * 1,sqlserver;
	 * 2,es;
	 */
	private Integer datatype;
	
	/**
	 * 查询方式
	 * 1,top查询
	 * 2,分页查询
	 * 3,总数查询
	 */
	private Integer querytype;
	
	/**  
	 * 获取数据来源1sqlserver;2es;  
	 * @return  datatype  
	 */
	public Integer getDatatype() {
		return datatype;
	}

	/**  
	 * 设置数据来源1sqlserver;2es;  
	 * @param Integer datatype  
	 */
	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}

	/**
	 * 分页下标
	 */
	private Integer index;
	
	/**
	 * 分页大小
	 */
	private Integer pagesize;
	
	/**
	 * 总数
	 */
	private Long count;

	/**  
	 * 获取编号  
	 * @return  id  
	 */
	public Long getId() {
		return id;
	}

	/**  
	 * 设置编号  
	 * @param Long id  
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	
	/** 设备ID */
	private String dev_uid;
	
	/** 在线状态  1,在线;2,离线;-1,查询异常;-2,超出10条*/
	private String online;
	
	/**  状态 1，有效;2,无效 */
	private String state;
	
	
	
	

	/**  
	 * 获取dev_uid  
	 * @return  dev_uid  
	 */
	public String getDev_uid() {
		return dev_uid;
	}

	/**  
	 * 设置dev_uid  
	 * @param String dev_uid  
	 */
	public void setDev_uid(String dev_uid) {
		this.dev_uid = dev_uid;
	}

	
	
	

	/**  
	 * 获取online  
	 * @return  online  
	 */
	public String getOnline() {
		return online;
	}

	/**  
	 * 设置online  
	 * @param String online  
	 */
	public void setOnline(String online) {
		this.online = online;
	}

	/**  
	 * 获取state  
	 * @return  state  
	 */
	public String getState() {
		return state;
	}

	/**  
	 * 设置state  
	 * @param String state  
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**  
	 * 获取手机号  
	 * @return  phone  
	 */
	public Long getPhone() {
		return phone;
	}

	/**  
	 * 设置手机号  
	 * @param Long phone  
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}

	/**  
	 * 获取手机号码状态：0:正常1:欠费2:号码过期15:其它  
	 * @return  mbstatus  
	 */
	public Integer getMbstatus() {
		return mbstatus;
	}

	/**  
	 * 设置手机号码状态：0:正常1:欠费2:号码过期15:其它  
	 * @param Integer mbstatus  
	 */
	public void setMbstatus(Integer mbstatus) {
		this.mbstatus = mbstatus;
	}

	/**  
	 * 获取短信状态：1:表示号码有效，能发短信；2:表示号码无效，不能发短信；  
	 * @return  smsstatus  
	 */
	public Integer getSmsstatus() {
		return smsstatus;
	}

	/**  
	 * 设置短信状态：1:表示号码有效，能发短信；2:表示号码无效，不能发短信；  
	 * @param Integer smsstatus  
	 */
	public void setSmsstatus(Integer smsstatus) {
		this.smsstatus = smsstatus;
	}

	/**  
	 * 获取运营商富信状态：0:未知1:可以发2:不能发  
	 * @return  rmsst_a  
	 */
	public Integer getRmsst_a() {
		return rmsst_a;
	}

	/**  
	 * 设置运营商富信状态：0:未知1:可以发2:不能发  
	 * @param Integer rmsst_a  
	 */
	public void setRmsst_a(Integer rmsst_a) {
		this.rmsst_a = rmsst_a;
	}

	/**  
	 * 获取运营商富信下载状态：0:未知1:可下载2:不可下载  
	 * @return  rmsst_dla  
	 */
	public Integer getRmsst_dla() {
		return rmsst_dla;
	}

	/**  
	 * 设置运营商富信下载状态：0:未知1:可下载2:不可下载  
	 * @param Integer rmsst_dla  
	 */
	public void setRmsst_dla(Integer rmsst_dla) {
		this.rmsst_dla = rmsst_dla;
	}

	/**  
	 * 获取手机厂商富信状态:0:未知1:可以发2:不能发  
	 * @return  rmsst_b  
	 */
	public Integer getRmsst_b() {
		return rmsst_b;
	}

	/**  
	 * 设置手机厂商富信状态:0:未知1:可以发2:不能发  
	 * @param Integer rmsst_b  
	 */
	public void setRmsst_b(Integer rmsst_b) {
		this.rmsst_b = rmsst_b;
	}

	/**  
	 * 获取手机厂商富信下载状态:0:未知1:可下载2:不可下载  
	 * @return  rmsst_dlb  
	 */
	public Integer getRmsst_dlb() {
		return rmsst_dlb;
	}

	/**  
	 * 设置手机厂商富信下载状态:0:未知1:可下载2:不可下载  
	 * @param Integer rmsst_dlb  
	 */
	public void setRmsst_dlb(Integer rmsst_dlb) {
		this.rmsst_dlb = rmsst_dlb;
	}

	/**  
	 * 获取OTTIM富信状态0:未知1:可以发2:不能发  
	 * @return  rmsst_c  
	 */
	public Integer getRmsst_c() {
		return rmsst_c;
	}

	/**  
	 * 设置OTTIM富信状态0:未知1:可以发2:不能发  
	 * @param Integer rmsst_c  
	 */
	public void setRmsst_c(Integer rmsst_c) {
		this.rmsst_c = rmsst_c;
	}

	/**  
	 * 获取OTTIM富信下载状态0:未知1:可下载2:不可下载  
	 * @return  rmsst_dlc  
	 */
	public Integer getRmsst_dlc() {
		return rmsst_dlc;
	}

	/**  
	 * 设置OTTIM富信下载状态0:未知1:可下载2:不可下载  
	 * @param Integer rmsst_dlc  
	 */
	public void setRmsst_dlc(Integer rmsst_dlc) {
		this.rmsst_dlc = rmsst_dlc;
	}

	/**  
	 * 获取创建时间  
	 * @return  createtm  
	 */
	public String getCreatetm() {
		return createtm;
	}

	/**  
	 * 设置创建时间  
	 * @param String createtm  
	 */
	public void setCreatetm(String createtm) {
		this.createtm = createtm;
	}

	/**  
	 * 获取上一次更新时间  
	 * @return  lastupdatetm  
	 */
	public String getLastupdatetm() {
		return lastupdatetm;
	}

	/**  
	 * 设置上一次更新时间  
	 * @param String lastupdatetm  
	 */
	public void setLastupdatetm(String lastupdatetm) {
		this.lastupdatetm = lastupdatetm;
	}

	/**  
	 * 获取startlastupdatetm  
	 * @return  startlastupdatetm  
	 */
	public String getStartlastupdatetm() {
		return startlastupdatetm;
	}

	/**  
	 * 设置startlastupdatetm  
	 * @param String startlastupdatetm  
	 */
	public void setStartlastupdatetm(String startlastupdatetm) {
		this.startlastupdatetm = startlastupdatetm;
	}

	/**  
	 * 获取endlastupdatetm  
	 * @return  endlastupdatetm  
	 */
	public String getEndlastupdatetm() {
		return endlastupdatetm;
	}

	/**  
	 * 设置endlastupdatetm  
	 * @param String endlastupdatetm  
	 */
	public void setEndlastupdatetm(String endlastupdatetm) {
		this.endlastupdatetm = endlastupdatetm;
	}

	/**  
	 * 获取最近下载时间  
	 * @return  lastdltm  
	 */
	public String getLastdltm() {
		return lastdltm;
	}

	/**  
	 * 设置最近下载时间  
	 * @param String lastdltm  
	 */
	public void setLastdltm(String lastdltm) {
		this.lastdltm = lastdltm;
	}

	/**  
	 * 获取startlastdltm  
	 * @return  startlastdltm  
	 */
	public String getStartlastdltm() {
		return startlastdltm;
	}

	/**  
	 * 设置startlastdltm  
	 * @param String startlastdltm  
	 */
	public void setStartlastdltm(String startlastdltm) {
		this.startlastdltm = startlastdltm;
	}

	/**  
	 * 获取endlastdltm  
	 * @return  endlastdltm  
	 */
	public String getEndlastdltm() {
		return endlastdltm;
	}

	/**  
	 * 设置endlastdltm  
	 * @param String endlastdltm  
	 */
	public void setEndlastdltm(String endlastdltm) {
		this.endlastdltm = endlastdltm;
	}

	/**  
	 * 获取手机操作系统1苹果2安卓  
	 * @return  phoneos  
	 */
	public Integer getPhoneos() {
		return phoneos;
	}

	/**  
	 * 设置手机操作系统1苹果2安卓  
	 * @param Integer phoneos  
	 */
	public void setPhoneos(Integer phoneos) {
		this.phoneos = phoneos;
	}

	/**  
	 * 获取操作系统版本  
	 * @return  osver  
	 */
	public String getOsver() {
		return osver;
	}

	/**  
	 * 设置操作系统版本  
	 * @param String osver  
	 */
	public void setOsver(String osver) {
		this.osver = osver;
	}

	/**  
	 * 获取操作系统名称  
	 * @return  osvername  
	 */
	public String getOsvername() {
		return osvername;
	}

	/**  
	 * 设置操作系统名称  
	 * @param String osvername  
	 */
	public void setOsvername(String osvername) {
		this.osvername = osvername;
	}

	/**  
	 * 获取手机品牌  
	 * @return  brand  
	 */
	public String getBrand() {
		return brand;
	}

	/**  
	 * 设置手机品牌  
	 * @param String brand  
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**  
	 * 获取品牌名称  
	 * @return  brandname  
	 */
	public String getBrandname() {
		return brandname;
	}

	/**  
	 * 设置品牌名称  
	 * @param String brandname  
	 */
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	/**  
	 * 获取手机型号  
	 * @return  model  
	 */
	public String getModel() {
		return model;
	}

	/**  
	 * 设置手机型号  
	 * @param String model  
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**  
	 * 获取型号名称  
	 * @return  modelname  
	 */
	public String getModelname() {
		return modelname;
	}

	/**  
	 * 设置型号名称  
	 * @param String modelname  
	 */
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	/**  
	 * 获取浏览器类型  
	 * @return  xbrowser  
	 */
	public String getXbrowser() {
		return xbrowser;
	}

	/**  
	 * 设置浏览器类型  
	 * @param String xbrowser  
	 */
	public void setXbrowser(String xbrowser) {
		this.xbrowser = xbrowser;
	}

	/**  
	 * 获取手机串码  
	 * @return  imei  
	 */
	public String getImei() {
		return imei;
	}

	/**  
	 * 设置手机串码  
	 * @param String imei  
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**  
	 * 获取SIM卡的电子串号  
	 * @return  imsi  
	 */
	public String getImsi() {
		return imsi;
	}

	/**  
	 * 设置SIM卡的电子串号  
	 * @param String imsi  
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**  
	 * 获取meid  
	 * @return  meid  
	 */
	public String getMeid() {
		return meid;
	}

	/**  
	 * 设置meid  
	 * @param String meid  
	 */
	public void setMeid(String meid) {
		this.meid = meid;
	}

	/**  
	 * 获取屏幕分辨率  
	 * @return  resolution  
	 */
	public String getResolution() {
		return resolution;
	}

	/**  
	 * 设置屏幕分辨率  
	 * @param String resolution  
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**  
	 * 获取设备序列号  
	 * @return  sn  
	 */
	public String getSn() {
		return sn;
	}

	/**  
	 * 设置设备序列号  
	 * @param String sn  
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**  
	 * 获取MAC地址  
	 * @return  mac  
	 */
	public String getMac() {
		return mac;
	}

	/**  
	 * 设置MAC地址  
	 * @param String mac  
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**  
	 * 获取SDK版本  
	 * @return  sdkver  
	 */
	public String getSdkver() {
		return sdkver;
	}

	/**  
	 * 设置SDK版本  
	 * @param String sdkver  
	 */
	public void setSdkver(String sdkver) {
		this.sdkver = sdkver;
	}

	
	
	
	/**  
	 * 获取启个性化控制1:启用2:不启用  
	 * @return  isupflag  
	 */
	public Integer getIsupflag() {
		return isupflag;
	}

	/**  
	 * 设置启个性化控制1:启用2:不启用  
	 * @param Integer isupflag  
	 */
	public void setIsupflag(Integer isupflag) {
		this.isupflag = isupflag;
	}

	
	

	/**  
	 * 获取SDK上报频率，单位：秒.默认值15天  
	 * @return  rptfreq  
	 */
	public Integer getRptfreq() {
		return rptfreq;
	}

	/**  
	 * 设置SDK上报频率，单位：秒.默认值15天  
	 * @param Integer rptfreq  
	 */
	public void setRptfreq(Integer rptfreq) {
		this.rptfreq = rptfreq;
	}

	/**  
	 * 获取上报方式0:不上报1:任何网络2:仅WIFI3:移动数据网络  
	 * @return  rptway  
	 */
	public Integer getRptway() {
		return rptway;
	}

	/**  
	 * 设置上报方式0:不上报1:任何网络2:仅WIFI3:移动数据网络  
	 * @param Integer rptway  
	 */
	public void setRptway(Integer rptway) {
		this.rptway = rptway;
	}

	/**  
	 * 获取手机号码来源：0、无效值；1、MBOSS上报；2、收集中心上报；3、网关上报；历史库分析所得；  
	 * @return  upsrc  
	 */
	public Integer getUpsrc() {
		return upsrc;
	}

	/**  
	 * 设置手机号码来源：0、无效值；1、MBOSS上报；2、收集中心上报；3、网关上报；历史库分析所得；  
	 * @param Integer upsrc  
	 */
	public void setUpsrc(Integer upsrc) {
		this.upsrc = upsrc;
	}

	
	

	

	/**  
	 * 获取最近一次上报时间  
	 * @return  lastrpttm  
	 */
	public String getLastrpttm() {
		return lastrpttm;
	}

	/**  
	 * 设置最近一次上报时间  
	 * @param String lastrpttm  
	 */
	public void setLastrpttm(String lastrpttm) {
		this.lastrpttm = lastrpttm;
	}

	/**  
	 * 获取手机所属国家  
	 * @return  mobilecountry  
	 */
	public Integer getMobilecountry() {
		return mobilecountry;
	}

	/**  
	 * 设置手机所属国家  
	 * @param Integer mobilecountry  
	 */
	public void setMobilecountry(Integer mobilecountry) {
		this.mobilecountry = mobilecountry;
	}

	/**  
	 * 获取手机类型  
	 * @return  mobiletype  
	 */
	public Integer getMobiletype() {
		return mobiletype;
	}

	/**  
	 * 设置手机类型  
	 * @param Integer mobiletype  
	 */
	public void setMobiletype(Integer mobiletype) {
		this.mobiletype = mobiletype;
	}

	/**  
	 * 获取省份代码  
	 * @return  mobilearea  
	 */
	public Integer getMobilearea() {
		return mobilearea;
	}

	/**  
	 * 设置省份代码  
	 * @param Integer mobilearea  
	 */
	public void setMobilearea(Integer mobilearea) {
		this.mobilearea = mobilearea;
	}

	/**  
	 * 获取分页下标  
	 * @return  index  
	 */
	public Integer getIndex() {
		return index;
	}

	/**  
	 * 设置分页下标  
	 * @param Integer index  
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**  
	 * 获取分页大小  
	 * @return  pagesize  
	 */
	public Integer getPagesize() {
		return pagesize;
	}

	/**  
	 * 设置分页大小  
	 * @param Integer pagesize  
	 */
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	/**  
	 * 获取总数  
	 * @return  count  
	 */
	public Long getCount() {
		return count;
	}

	/**  
	 * 设置总数  
	 * @param Long count  
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	
	
	/**  
	 * 获取查询方式1top查询2分页查询3总数查询  
	 * @return  querytype  
	 */
	public Integer getQuerytype() {
		return querytype;
	}

	/**  
	 * 设置查询方式1top查询2分页查询3总数查询  
	 * @param Integer querytype  
	 */
	public void setQuerytype(Integer querytype) {
		this.querytype = querytype;
	}

	@Override
	public String toString() {
		return MyTools.toString(this);
	}
	
	

}
