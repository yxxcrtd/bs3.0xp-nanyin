/**
 * 
 */
package com.joyintech.tams.report.interf.conf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joyintech.tams.report.interf.cnbond.ext.ExtFprDClient;

import ds.dc.api.app.DCconfig;
import ds.dc.api.app.DClient;

/**
 * @author zuoyinyi
 *
 */
@Configuration
public class DCconfiguration {

	@Bean("dclient")
	public DClient dclinet() {
		return new DClient();
	}
	
	@Bean
	public DCconfig dcconfig() {
		return new DCconfig();
	}
	
	@Bean("fprDClient")
	public ExtFprDClient extFprDClient(DClient dclient) throws IllegalAccessException {
		ExtFprDClient client = new ExtFprDClient();
		client.setC(dclient);
		
		// 登记银行代码和中债直连机路径，如果只有一个则不需要配置，默认使用"./AppCfg/DC/cfg"中的DIM_SERVER
		Map<String, String> dimServer = new HashMap<>();
//		dimServer.put("C10001", "http://22.5.251.178:9080/UServer");
//		dimServer.put("C10002", "http://22.5.251.178:9081/UServer");
//		dimServer.put("C10002", "http://22.5.251.178:9082/UServer");
		client.setDimServer(dimServer);
		return client;
	}
}
