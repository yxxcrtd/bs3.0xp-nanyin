package com.test.jixu;

import java.util.HashMap;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.jixu.common.type.MsgContainer;
import com.jixu.framework.msg.MsgFactory;

/** 
 * @ClassName 		UtilTest
 * @author  		Pan Wei 
 * @Date 			2018年6月25日 下午2:29:10 	
 * @Description		[]  
 */

public class UtilTest extends BaseTest {
	
	@Test
	@Rollback(false)
	public void testCopyPortfolio(){
		HashMap<String, Object> fieldsMap=new HashMap<String, Object>();
		fieldsMap.put("header", "portfolioCopyMsg");
		fieldsMap.put("port", "1");
		fieldsMap.put("portname", "社会主义好");
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(4);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
	}
}
