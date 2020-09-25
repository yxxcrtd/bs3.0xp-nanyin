package com.joyintech.interf.bankname.sale.dto;
/** 
 * @ClassName 		ExecuteResult
 * @author  		Pan Wei 
 * @Date 			2016年9月4日 上午8:19:27 	
 * @Description		[]  
 * @return  
 */

public class ResultContainer {
	/**
	 * 是否执行成功
	 */
	private boolean correctFlag;
	private String header;
	/**
	 * 错误代码
	 */
	private int errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	private String entityID;
	private int objID;
	private Object entityObj;
	private int userID;
	
	public ResultContainer() {
		setCorrectFlag(true);
		setErrorMsg("-");
	}
	
	/**
	 * 
	 * @return 执行是否成成
	 */
	public boolean isCorrectFlag() {
		return correctFlag;
	}
	public void setCorrectFlag(boolean correctFlag) {
		this.correctFlag = correctFlag;
	}
	
	/**
	 * @return 错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setErrorMsg(boolean isCorrect, String errorMsg) {
		this.correctFlag=isCorrect;
		this.errorMsg = errorMsg;
	}
	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	public int getObjID() {
		return objID;
	}
	public void setObjID(int objID) {
		this.objID = objID;
	}
	public Object getEntityObj() {
		return entityObj;
	}
	public void setEntityObj(Object entityObj) {
		this.entityObj = entityObj;
	}

	/**
	 * @return 错误代码
	 */
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * 获取 userID
	 */
	
	public int getUserID() {
		return userID;
	}

	/**
	 * 设置 userID
	 */
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
