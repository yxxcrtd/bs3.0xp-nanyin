package com.test.jixu;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.Coaahdr;

/** 
 * @ClassName 		TestConfigFunc
 * @author  		Pan Wei 
 * @Date 			2017年12月18日 上午9:51:40 	
 * @Description		[]  
 * @return  
 */

public class TestConfigFunc extends BaseTest{
	@Resource(name="configService")  
    private ConfigService cfgService;  
	
	@Test
	@Rollback(false)
	public void testCreateCoa() {
		Coaahdr aa = new Coaahdr();
		aa.setChartname("1111111111111");
		cfgService.createCoaahdr(aa);
	}
}
