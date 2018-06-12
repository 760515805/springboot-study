package com.montnets.es;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.montnets.bean.ValidPhone;
import com.montnets.bean.ValidPhoneHis;
import com.montnets.utils.MyTools;

public class ConditionEsUtil{
	private ValidPhone  vaildPhone;
	
	private QueryBuilder queryBuilder;

	private ValidPhoneHis validPhoneHis;
	
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	
	private static final String DEFAULT_LASTDLTM="1900-01-01 00:00:00.000";
	
	protected QueryBuilder conditionUtil(ValidPhone  vaildPhone){

		if(vaildPhone==null){
			return null;
		}
		this.vaildPhone=vaildPhone;
		condetionValidPhone();
		return queryBuilder;
	}
	protected QueryBuilder conditionUtil(ValidPhoneHis  validPhoneHis){
		if(validPhoneHis==null){
			return null;
		}
		this.validPhoneHis=validPhoneHis;
		condetionValidPhoneHis();
		return queryBuilder;
	}
	private void condetionValidPhone(){
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		
		if(vaildPhone.getPhone()!=null&& !"-1".equals(vaildPhone.getPhone().toString()))
			boolQueryBuilder.must(QueryBuilders.termQuery("phone",vaildPhone.getPhone()));
		if(vaildPhone.getMbstatus()!=null&&vaildPhone.getMbstatus()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("mbstatus",vaildPhone.getMbstatus()));
		if(vaildPhone.getSmsstatus()!=null&&vaildPhone.getSmsstatus()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("smsstatus",vaildPhone.getSmsstatus()));
		
		if(vaildPhone.getRmsst_a()!=null&&vaildPhone.getRmsst_a()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_a",vaildPhone.getRmsst_a()));
		
		if(vaildPhone.getRmsst_dla()!=null&&vaildPhone.getRmsst_dla()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_dla",vaildPhone.getRmsst_dla()));
		if(vaildPhone.getRmsst_b()!=null&&vaildPhone.getRmsst_b()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_b",vaildPhone.getRmsst_b()));

		if(vaildPhone.getRmsst_dlb()!=null&&vaildPhone.getRmsst_dlb()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_dlb",vaildPhone.getRmsst_dlb()));
		
		if(vaildPhone.getRmsst_c()!=null&&vaildPhone.getRmsst_c()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_c",vaildPhone.getRmsst_c()));
		
		if(vaildPhone.getRmsst_dlc()!=null&&vaildPhone.getRmsst_dlc()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rmsst_dlc",vaildPhone.getRmsst_dlc()));
		
		if(vaildPhone.getIsupflag()!=null&&vaildPhone.getIsupflag()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("isupflag",vaildPhone.getIsupflag()));
		
		if(vaildPhone.getRptfreq()!=null&&vaildPhone.getRptfreq()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rptfreq",vaildPhone.getRptfreq()));
		
		
		if(vaildPhone.getRptway()!=null&&vaildPhone.getRptway()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("rptway",vaildPhone.getRptway()));
		
		if(vaildPhone.getUpsrc()!=null&&vaildPhone.getUpsrc()>-1)
		boolQueryBuilder.must(QueryBuilders.termQuery("upsrc",vaildPhone.getUpsrc()));
		
		
		if(MyTools.isNotEmpty(vaildPhone.getStartlastupdatetm()))
			boolQueryBuilder.must(QueryBuilders.rangeQuery("lastupdatetm").gt(MyTools.complementTime(vaildPhone.getStartlastupdatetm(),FORMAT,1)));//gte() :范围查询将匹配字段值大于或等于此参数值的文档。
		if(MyTools.isNotEmpty(vaildPhone.getEndlastupdatetm()))
			boolQueryBuilder.must(QueryBuilders.rangeQuery("lastupdatetm").lte(MyTools.complementTime(vaildPhone.getEndlastupdatetm(),FORMAT,2)));//lte() :范围查询将匹配字段值小于或等于此参数值的文档。
		
		/// 改为创建时间
		if(MyTools.isNotEmpty(vaildPhone.getStartlastdltm()))
			boolQueryBuilder.must(QueryBuilders.rangeQuery("createtm").gt(MyTools.complementTime(vaildPhone.getStartlastdltm(),FORMAT,1)));	
		if(MyTools.isNotEmpty(vaildPhone.getEndlastdltm()))
			boolQueryBuilder.must(QueryBuilders.rangeQuery("createtm").from(DEFAULT_LASTDLTM).to(MyTools.complementTime(vaildPhone.getEndlastdltm(),FORMAT,2)).includeLower(false));
//			
//		if(vaildPhone.getRmsfg()!=null&&vaildPhone.getRmsfg()>-1)
//			boolQueryBuilder.must(QueryBuilders.termQuery("rmsfg",vaildPhone.getRmsfg()));
		if(vaildPhone.getPhoneos()!=null&&vaildPhone.getPhoneos()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("phoneos",vaildPhone.getPhoneos()));
		if(MyTools.isNotEmpty(vaildPhone.getOsver()))
			boolQueryBuilder.must(QueryBuilders.termQuery("osver",vaildPhone.getOsver()));
		if(MyTools.isNotEmpty(vaildPhone.getBrand()))
			boolQueryBuilder.must(QueryBuilders.matchQuery("brand",vaildPhone.getBrand()).operator(Operator.AND));
		if(MyTools.isNotEmpty(vaildPhone.getModel()))
			boolQueryBuilder.must(QueryBuilders.matchQuery("model",vaildPhone.getModel()).operator(Operator.AND));
//		if(MyTools.isNotEmpty(vaildPhone.getXbrowser()))	
//			boolQueryBuilder.must(QueryBuilders.matchQuery("xbrowser",vaildPhone.getXbrowser()));
//		if(MyTools.isNotEmpty(vaildPhone.getImei()))
//			boolQueryBuilder.must(QueryBuilders.termQuery("imei",vaildPhone.getImei()));
//		if(MyTools.isNotEmpty(vaildPhone.getImsi()))
//			boolQueryBuilder.must(QueryBuilders.termQuery("imsi",vaildPhone.getImsi()));
//		if(vaildPhone.getIschange()!=null&&vaildPhone.getIschange()>-1)
//			boolQueryBuilder.must(QueryBuilders.termQuery("ischange",vaildPhone.getIschange()));
		
		this.queryBuilder=boolQueryBuilder;
	}
	private void condetionValidPhoneHis(){
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		if(validPhoneHis.getPhone()!=null&&validPhoneHis.getPhone()>-1)
			boolQueryBuilder.must(QueryBuilders.termQuery("phone",validPhoneHis.getPhone()));
		
		this.queryBuilder=boolQueryBuilder;
	}	
	public static void main(String[] args) {
		ValidPhone  vaildPhone = new ValidPhone();
		vaildPhone.setUpsrc(1);
	    QueryBuilder queryBuilder =new ConditionEsUtil().conditionUtil(vaildPhone);
		
	   System.out.println(queryBuilder.toString());
	    
	}
}
