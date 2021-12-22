package com.joyintech.tams.exchange.common;

import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.domain.Message;

public class BusinessResultException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	private Message<Object> resultMessage;

    public BusinessResultException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    public BusinessResultException(Message<Object> resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public Message<Object> getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(Message<Object> resultMessage) {
		this.resultMessage = resultMessage;
	}

}
