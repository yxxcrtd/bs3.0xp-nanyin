package com.jixu.framework.msg;


import com.jixu.common.type.MsgContainer;
import com.joyintech.workflow.engine.service.BaseProcessService;

/** 
 * @ClassName 		MsgService
 * @author  		Pan Wei 
 * @Date 			2017年5月29日 上午11:50:14 	
 * @Description		[]  
 */

public interface MsgService<T extends BaseMsgDto> extends BaseProcessService<T>{
//public interface MsgService{
	
	public String msgHeader();
	
	public String msgFields();
	
	public String messageName();

	public void executeMessage(String msg, MsgContainer msgcontainer);
	
	public void executeMessage(MsgContainer msgcontainer);
	
	void executeMessageNoTx(MsgContainer msgcontainer);
	
}
