package com.montnets;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.alibaba.fastjson.JSONObject;
import com.montnets.utils.MyTools;

/**
 * 
* Title: KafkaProducerTest
* Description: kafka生产者的消息测试
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
public class KafkaProducerTest implements Runnable {

	private final KafkaProducer<String, String> producer;
	private final String topic;
	private int k=10;
//   private final String servers="192.169.0.23:9092,192.169.0.24:9092,192.169.0.25:9092";
    private final String servers="192.169.2.156:9092,192.169.2.98:9092,192.169.2.188:9092";
	/**
	 * @param topic 消息名称
	 * @param
	 */
	public KafkaProducerTest(String topicName) {
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
		//acks=0：如果设置为0，生产者不会等待kafka的响应。
		//acks=1：这个配置意味着kafka会把这条消息写到本地日志文件中，但是不会等待集群中其他机器的成功响应。
		//acks=all：这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证。
		props.put("acks", "all");
		//配置为大于0的值的话，客户端会在消息发送失败时重新发送。
		props.put("retries", 0);
		//当多条消息需要发送到同一个分区时，生产者会尝试合并网络请求。这会提高client和生产者的效率
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", StringSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());
		this.producer = new KafkaProducer<String, String>(props);
		this.topic = topicName;
		System.out.println("开始初始化...");
	}

	@Override
	public void run() {
		int messageNo = 0;
		long k=12945678910L;
		try {
			while (true) {
				List<Object> list=new ArrayList<Object>();
				for(int i=0;i<1000;i++){
					JSONObject json2=new JSONObject();
					json2.put("phone", k++);
					json2.put("datatype", 0);
					json2.put("brand", "苹果");
					json2.put("model", "iPhone 7");
					json2.put("osver", "ios7.0");
					json2.put("phoneos", 1);
					json2.put("mbstatus", 2);
					json2.put("smsstatus", 1);
					json2.put("rmsst_a", 1);
					json2.put("rmsst_dla", 1);
					json2.put("rmsst_b", 0);
					json2.put("rmsst_dlb", 1);
					json2.put("rmsst_c", 1);
					json2.put("rmsst_dlc", 1);
					json2.put("isupflag", 1);
					json2.put("rptway", 1);
					json2.put("upsrc", 0);
					json2.put("createtm", MyTools.getNowTime("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
					json2.put("lastupdatetm", "1900-01-01 00:00:00.0000000");
					json2.put("lastrpttm", "1900-01-01 00:00:00.0000000");
					json2.put("lastuptm", "1900-01-01 00:00:00.0000000");
					json2.put("imei", "IMEI1234567893214564");
					json2.put("imsi", "IMSI1234567893214564");
					json2.put("xbrowser", "IE11");
					json2.put("resolution", "resolution12345");
					json2.put("sn", "sn23235355");
					json2.put("mac", "MAC12334");
					json2.put("mobilecountry", 86);
					json2.put("mobiletype", 255);
					json2.put("mobilearea", 0);
			
					
				/*	json2.put("brand", "苹果");
					json2.put("imei", "IMEI 1111");
					json2.put("imsi", "IMSI 1111");
					json2.put("ischange", "0");
					json2.put("isupflag", "2");
					json2.put("mbstatus", "0");
					json2.put("mobiletype", "0");
					json2.put("model", "iPhone 8");
					json2.put("osver", "ios6.0");
					json2.put("phoneos", "1");
					json2.put("rmsfg", "0");
					json2.put("rmsstatus", "0");
					json2.put("rptway", "2");
					json2.put("smsstatus", "1");
					json2.put("upsrc", "1");
					json2.put("datatype", "0");
					json2.put("xbrowser", "类型1111");
					json2.put("createtm", MyTools.getNowTime("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
					json2.put("lastupdatetm", "1900-01-01 00:00:00.0000000");
					json2.put("lastrpttm", "1900-01-01 00:00:00.0000000");
					json2.put("mobilecountry", "86");
					json2.put("mobiletype", "0");
					json2.put("mobilearea", "0");
					json2.put("phone", "77777780");*/
					
					list.add(json2);
				}
//				System.out.println("----"+MyTools.toString(list));
//				System.out.println("===="+list.toString());
				producer.send(new ProducerRecord<String, String>(topic,MyTools.toString(list)));
				list.clear();
				messageNo++;
				//生产了100条就打印
				if(messageNo%10000==0){
					System.out.println("Send:" +messageNo);
				}
				//生产100条就退出
				if(messageNo%100000==0){
					System.out.println(topic+"成功发送了"+messageNo+"条");
					break;
				}
//				Utils.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
	
	public static void main(String args[]) {
		KafkaProducerTest test = new KafkaProducerTest("pb_basic_phone_demo2");
		Thread thread = new Thread(test);
		thread.start();
	}
	

}