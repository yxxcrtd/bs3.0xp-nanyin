package com.joyintech.tams.invest.common.dto;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import org.springframework.validation.BindingResult;

public class InvAjaxResult extends AjaxResult{
	@Override
    public void setMessageType(MessageType messageType) {
    	if(messageType instanceof MessageType){
    		super.setMessageType(messageType);
    	}else{
    		MessageType mt = MessageType.valueOf(messageType.toString());
    		super.setMessageType(mt);
    	}
    }
    public void setMessageType(String messageType) {
    	MessageType mt = MessageType.错误消息;
    	if(messageType.equals(MessageType.错误消息.getCode())){
    		mt = MessageType.错误消息;
    	}else if (messageType.equals(MessageType.提示消息.getCode())){
    		mt = MessageType.提示消息;
    	}else if (messageType.equals(MessageType.确认消息.getCode())){
    		mt = MessageType.确认消息;
    	}else if (messageType.equals(MessageType.警告消息.getCode())){
    		mt = MessageType.警告消息;
    	}
    	super.setMessageType(mt);
    }

	public InvAjaxResult() {
		super();
    }
	public InvAjaxResult(String message) {
		super(message);
    }

    public InvAjaxResult(String message, MessageType messageType) {
        super(message,messageType);
    }

    public InvAjaxResult(BindingResult result) {
        super(result);
    }

}
