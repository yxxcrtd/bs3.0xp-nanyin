package com.test.jixu;

import java.util.HashMap;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.jixu.common.type.MsgContainer;
import com.jixu.framework.msg.MsgFactory;

/** 
 * @ClassName 		UtilTest
 * @author  		Pan Wei 
 * @Date 			2018骞�6鏈�25鏃� 涓嬪崍2:29:10 	
 * @Description		[]  
 */

public class UtilTest extends BaseTest {
	
	@Test
	@Rollback(false)
	public void testCopyPortfolio(){
		HashMap<String, Object> fieldsMap=new HashMap<String, Object>();
		fieldsMap.put("header", "portfolioCopyMsg");
		fieldsMap.put("port", "1");
		fieldsMap.put("portname", "绀句細涓讳箟濂�");
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(4);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
	}
}
