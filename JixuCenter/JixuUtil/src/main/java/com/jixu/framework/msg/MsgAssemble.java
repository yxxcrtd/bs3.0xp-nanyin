package com.jixu.framework.msg;

import com.jixu.common.type.MsgContainer;

public interface MsgAssemble {

	public void genMsgContainer(String msg, MsgContainer container);
	
	public void genMsgContainer(String requiredHeader,String msg, MsgContainer container);
}
