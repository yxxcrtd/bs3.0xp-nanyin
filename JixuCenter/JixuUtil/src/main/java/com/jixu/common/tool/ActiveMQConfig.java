package com.jixu.common.tool;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ConfigurationProperties(prefix = "jixu.activeMQ")
@EnableJms
public class ActiveMQConfig {
    private String serverURL="localhost";
    private String serverPort="61616";
    private String username="admin";
    private String password="admin";
    private int maxCon=100;
    private String defaultDestinationName="jixuqueue";
    
    @Bean
    public ActiveMQConnectionFactory targetConnectionFactory(){
    	ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory();
    	factory.setBrokerURL("tcp://"+serverURL+":"+serverPort);
    	factory.setUserName(username);
    	factory.setPassword(password);
    	factory.setTrustAllPackages(true);
    	return factory;
    }
    
    @Bean
    public PooledConnectionFactory pooledConnectionFactory(){
    	PooledConnectionFactory factory=new PooledConnectionFactory();
    	factory.setMaxConnections(maxCon);
    	factory.setConnectionFactory(targetConnectionFactory());
    	return factory;
    }
    
    @Bean
    public SingleConnectionFactory connectionFactory(){
    	SingleConnectionFactory factory=new SingleConnectionFactory();
    	factory.setTargetConnectionFactory(pooledConnectionFactory());
    	return factory;
    }
    
    @Bean
    public JmsTemplate activeMqJmsTemplate(){
    	JmsTemplate template=new JmsTemplate();
    	template.setPubSubDomain(false);
    	template.setConnectionFactory(connectionFactory());
    	template.setDefaultDestinationName(defaultDestinationName);
    	return template;
    }
    
    public String getServerURL() {
		return serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMaxCon() {
		return maxCon;
	}
	public void setMaxCon(int maxCon) {
		this.maxCon = maxCon;
	}
	public String getDefaultDestinationName() {
		return defaultDestinationName;
	}
	public void setDefaultDestinationName(String defaultDestinationName) {
		this.defaultDestinationName = defaultDestinationName;
	}
}
