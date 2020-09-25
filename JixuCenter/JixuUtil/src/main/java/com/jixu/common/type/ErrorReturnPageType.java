package com.jixu.common.type;

/** 
 * @ClassName 		ErrorReturnPageType
 * @author  		Pan Wei 
 * @Date 			2018年4月2日 下午12:56:35 	
 * @Description		[]  
 * @return  
 */

public class ErrorReturnPageType {
	
	private String issue;
	
	private int code;
	
	private String detail;
	
	public ErrorReturnPageType(int code, String errorDetail) {
		this.setIssue("fail");
		this.setCode(code);
		this.setDetail(errorDetail);
	}
	
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
