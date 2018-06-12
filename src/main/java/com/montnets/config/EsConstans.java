package com.montnets.config;



import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.montnets.es.client.RestClientFactory;

@Component
//@ConfigurationProperties(prefix = "es")
//@PropertySource(value = "classpath:es.properties")
public class EsConstans {
	
	public final static String DESC = "desc";
	public final static String ASC = "asc";
	/**集群地址,多个用|隔开*/
	@Value("${es.servers}")
	private String servers;
	/**通信协议*/
	@Value("${es.scheme}")
	private String scheme;
	/**集群名称*/
	@Value("${es.clusterName}")
	private String clusterName;
	/**索引INDEX名*/
	@Value("${es.index}")
	private String index;

	public static  HttpHost[] HTTP_HOST = null;
	/**RestClientFactory连接(高级封装Rest)*/
	public static RestClientFactory client = null;
	/**ES是否初始化成功标志*/
	public static Boolean cacheFalg=false;
	/**ES重连次数,连接那么多次还是连接不上就程序停止*/
	public static Integer reconnectNum=5;
	
	public String getScheme() {
		return scheme;
	}
	public String[] getHostNames(){
		String[] aa = servers.split(",");
		return aa;
	}
	public String[] getIndexs(){
		String[] aa = index.split(",");
		return aa;
	}
	public String getClusterName() {
		return clusterName;
	}
	
}

