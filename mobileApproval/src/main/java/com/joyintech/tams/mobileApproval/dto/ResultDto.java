package com.joyintech.tams.mobileApproval.dto;

public class ResultDto {

    //处理状态
    private String status;

    //提示
    private String msg;
    
    //状态码
    private String code;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
