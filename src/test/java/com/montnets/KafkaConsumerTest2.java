package com.montnets;



import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 
* Title: KafkaConsumerTest2
* Description:
* kafka手动提交 
* Version:1.0.0  
* @author pancm
* @date 2018年1月25日
 */
public class KafkaConsumerTest2 extends Thread {

	private final KafkaConsumer<String, String> consumer;
	private final String topic;
	private static final String GROUPID = "groupC";
	ConsumerRecords<String, String> msgList;
	static {
		// 修改kafka日志输出级别
		Logger.getLogger("kafka.client").setLevel(Level.OFF);
		Logger.getLogger("kafka.producer").setLevel(Level.OFF);
		Logger.getLogger("kafka.utils").setLevel(Level.OFF);
		Logger.getLogger("kafka.consumer").setLevel(Level.OFF);
		Logger.getLogger("kafka.network").setLevel(Level.OFF);
		Logger.getLogger("org.apache.zookeeper").setLevel(Level.OFF);
	}
	
	public KafkaConsumerTest2(String topic) {
		Properties props = new Properties();
//		props.put("bootstrap.servers", "192.169.2.156:9092,192.169.2.98:9092,192.169.2.188:9092");
//		props.put("bootstrap.servers", "192.169.0.23:9092,192.169.0.24:9092,192.169.0.25:9092");
		props.put("bootstrap.servers", "192.169.2.158:9092");
		props.put("group.id", GROUPID);
		props.put("enable.auto.commit", "false");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		this.consumer = new KafkaConsumer<String, String>(props);
		this.topic = topic;
		//订阅者主题
//		this.consumer.subscribe(Arrays.asList(topic));
		
		// 指定消费topic的那个分区
		
		// 指定从topic的分区的某个offset开始消费
		// consumer.seekToBeginning(Arrays.asList(p));
	}

	@Override
	public void run() {
//		this.consumer.subscribe(Arrays.asList(topic));
		int messageNo = 1;
		System.out.println("消费开始---------");
		TopicPartition partition = new TopicPartition(topic, 1);
		consumer.assign(Arrays.asList(partition));
		consumer.seek(partition, 0);
		try {
			while (true) {
				msgList = consumer.poll(100);
				if(null!=msgList&&msgList.count()>0){
//					System.out.println("msgList:"+msgList.count());
				for (ConsumerRecord<String, String> record : msgList) {
					//消费100条就打印 ,但打印的数据不一定是这个规律的
//					if(messageNo%100==0){
						System.out.println(topic+"     "+"=======receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
//						consumer.commitAsync();
//					}
//					if(messageNo==101){
//						System.out.println("=======receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
//						break;
//					}
//					//当消费了1000条就退出
//					if(messageNo%1000==0){
//						break;
//					}
					messageNo++;
				}
			}else{	
				Thread.sleep(3000);
				System.out.println("休眠中...");
			}
		 }
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
   
	public static void main(String args[]) {
		KafkaConsumerTest2 test1 = new KafkaConsumerTest2("PB_BASIC_PHONE");
		Thread thread1 = new Thread(test1);
		thread1.start();
	}
}

