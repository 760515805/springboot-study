package com.montnets;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
public class KafkaProducerTest2 implements Runnable {

	private final KafkaProducer<String, String> producer;
	private final String topic;
	private int k=10;
   private final String servers="192.169.0.23:9092,192.169.0.24:9092,192.169.0.25:9092";
//    private final String servers="192.169.2.156:9092,192.169.2.98:9092,192.169.2.188:9092";
    
	public static void main(String args[]) {
		KafkaProducerTest2 test = new KafkaProducerTest2("pb_basic_phone_demo2");
		Thread thread = new Thread(test);
		thread.start();

		
	}
	/**
	 * @param topic 消息名称
	 * @param
	 */
	public KafkaProducerTest2(String topicName) {
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
		long k=8613045678910L;
		try {
			 java.util.Random ran=new java.util.Random();
			 int randomInt = 3;
			while (true) {
				List<Object> list=new ArrayList<Object>();
				for(int i=0;i<1;i++){
					JSONObject json2=new JSONObject();
					json2.put("phone", k++);
					json2.put("datatype", 0);
					json2.put("brand",getBrand());
					json2.put("model", "iPhone 7");
					json2.put("osver", "ios7.0");
					json2.put("phoneos", ran.nextInt(randomInt));
					json2.put("mbstatus", ran.nextInt(randomInt));
					json2.put("smsstatus", ran.nextInt(randomInt));
					json2.put("rmsst_a", ran.nextInt(randomInt));
					json2.put("rmsst_dla", ran.nextInt(randomInt));
					json2.put("rmsst_b", ran.nextInt(randomInt));
					json2.put("rmsst_dlb", ran.nextInt(randomInt));
					json2.put("rmsst_c", ran.nextInt(randomInt));
					json2.put("rmsst_dlc", ran.nextInt(randomInt));
					json2.put("isupflag", ran.nextInt(randomInt));
					json2.put("rptway", ran.nextInt(randomInt));
					json2.put("upsrc", ran.nextInt(randomInt));
					json2.put("createtm", MyTools.getNowTime("yyyy-MM-dd HH:mm:ss.SSSSSSS"));
					json2.put("lastupdatetm", "1900-01-01 00:00:00.0000000");
					json2.put("lastrpttm", "1900-01-01 00:00:00.0000000");
					json2.put("lastuptm", "1900-01-01 00:00:00.0000000");
					json2.put("imei", getIMEI());
					json2.put("imsi", getImsi());
					json2.put("xbrowser", genRandomNum(32));
					json2.put("resolution", genRandomNum(16));
					json2.put("rptfreq", 1296000);
					json2.put("sn", genRandomNum(32));
					json2.put("mac", getMac());
					json2.put("mobilecountry", 86);
					json2.put("mobiletype",getMobileType());
					json2.put("mobilearea",getMobileArea());
					list.add(json2);
				}
//				System.out.println("----"+MyTools.toString(list));
//				System.out.println("===="+list.toString());
				producer.send(new ProducerRecord<String, String>(topic,MyTools.toString(list)));
				list.clear();
				messageNo++;
				//生产了10000条就打印
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
	
    private static String getIMEI() {// calculator IMEI  
        int r1 = 1000000 + new java.util.Random().nextInt(9000000);  
        int r2 = 1000000 + new java.util.Random().nextInt(9000000);  
        String input = r1 + "" + r2;  
        char[] ch = input.toCharArray();  
        int a = 0, b = 0;  
        for (int i = 0; i < ch.length; i++) {  
            int tt = Integer.parseInt(ch[i] + "");  
            if (i % 2 == 0) {  
                a = a + tt;  
            } else {  
                int temp = tt * 2;  
                b = b + temp / 10 + temp % 10;  
            }  
        }  
        int last = (a + b) % 10;  
        if (last == 0) {  
            last = 0;  
        } else {  
            last = 10 - last;  
        }  
        return input + last;  
    }  
      
    private static String getImsi() {  
        // 460022535025034  
        String title = "4600";  
        int second = 0;  
        do {  
            second = new java.util.Random().nextInt(8);  
        } while (second == 4);  
        int r1 = 10000 + new java.util.Random().nextInt(90000);  
        int r2 = 10000 + new java.util.Random().nextInt(90000);  
        return title + "" + second + "" + r1 + "" + r2;  
    }  
    private static String getMac(){  
        char[] char1 = "abcdef".toCharArray();  
        char[] char2 = "0123456789".toCharArray();  
        StringBuffer mBuffer = new StringBuffer();  
        for (int i = 0; i < 6; i++) {  
            int t = new java.util.Random().nextInt(char1.length);  
            int y = new java.util.Random().nextInt(char2.length);  
            int key = new java.util.Random().nextInt(2);  
            if (key ==0) {  
                mBuffer.append(char2[y]).append(char1[t]);  
            }else {  
                mBuffer.append(char1[t]).append(char2[y]);  
            }  
              
            if (i!=5) {  
                mBuffer.append(":");  
            }  
        }  
        return mBuffer.toString();  
    }  
    private static Integer getMobileArea(){
    	int [] arr = {10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,255};
    	//产生0-(arr.length-1)的整数值,也是数组的索引
    	int index=(int)(Math.random()*arr.length);
    	int rand = arr[index];
    	return rand;
    }
    private static Integer getMobileType(){
    	int [] arr = {0,1,21,2,5,255};
    	//产生0-(arr.length-1)的整数值,也是数组的索引
    	int index=(int)(Math.random()*arr.length);
    	int rand = arr[index];
    	return rand;
    }
    private static String getBrand(){
    	String [] arr = {"苹果","华为","小米","爱立信","三星","诺基亚"};
    	//产生0-(arr.length-1)的整数值,也是数组的索引
    	int index=(int)(Math.random()*arr.length);
    	String rand = arr[index];
    	return rand;
    }
    public static String genRandomNum(int pwd_len) {  
    	// 35是因为数组是从0开始的，26个字母+10个数字  
    	final int maxNum = 36;  
    	int i; // 生成的随机数  
    	int count = 0; // 生成的密码的长度  
    	char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  
    		    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',  
    		    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
    	StringBuffer pwd = new StringBuffer("");  
    	Random r = new Random();  
    	while (count < pwd_len) {  
    	// 生成随机数，取绝对值，防止生成负数，  
    	i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1  
    	if (i >= 0 && i < str.length) {  
    		pwd.append(str[i]);  
    		count++;  
    	}  
    	}  
    	return pwd.toString();  
    	}  
}