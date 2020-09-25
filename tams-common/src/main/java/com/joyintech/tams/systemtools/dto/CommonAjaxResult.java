package com.joyintech.tams.systemtools.dto;

import org.springframework.validation.BindingResult;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;

public class CommonAjaxResult extends AjaxResult{
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
	
	public  CommonAjaxResult() {
		super();
    }
	public  CommonAjaxResult(String message) {
		super(message);
    }

    public CommonAjaxResult(String message, MessageType messageType) {
        super(message,messageType);
    }

    public CommonAjaxResult(BindingResult result) {
        super(result);
    }

}
