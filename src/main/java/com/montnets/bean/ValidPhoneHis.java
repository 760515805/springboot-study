package com.montnets.bean;

/**
 * 
* @Title: ValidPhoneHis
* @Description:
* 机型变化历史实体类 
* @Version:1.0.0  
* @author pancm
* @date 2018年3月28日
 */
public class ValidPhoneHis {

	/**
	 * 手机号
	 */
	private Long phone;
	
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
	 * 手机串码
	 */
	private String imei;
	/**
	 * SIM卡的电子串号
	 */
	private String imsi;
	
	
	/**
	 * 手机型号
	 */
	private String meid;
	
	/**
	 * 设备序列号
	 */
	private String sn;
	/**
	 * MAC地址
	 */
	private String mac;
	/**
	 * 屏幕分辨率
	 */
	private String resolution;
	
	

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
	 *  手机号码来源：
		0、无效值；
		1、MBOSS上报；
		2、收集中心上报；
		3、网关上报；
		历史库分析所得；
	 */
	private Integer upsrc;
	
	
	/**
	 * 创建时间
	 */
	private String createtm;

	
	/**
	 * 数据来源 
	 * 1,sqlserver;
	 * 2,es;
	 */
	private Integer dataType;
	
	
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
	 * 获取手机型号  
	 * @return  meid  
	 */
	public String getMeid() {
		return meid;
	}

	/**  
	 * 设置手机型号  
	 * @param String meid  
	 */
	public void setMeid(String meid) {
		this.meid = meid;
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
	 * 获取数据来源1sqlserver;2es;  
	 * @return  dataType  
	 */
	public Integer getDataType() {
		return dataType;
	}

	/**  
	 * 设置数据来源1sqlserver;2es;  
	 * @param Integer dataType  
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	
	
}
