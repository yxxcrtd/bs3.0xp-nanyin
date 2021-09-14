package com.jixu.common.type;

import java.util.ArrayList;
import java.util.List;

public class ResultInterface {
	
	/**
	 * 是否正确
	 */
	private boolean correctFlag;
	
	/**
	 * 接口传入的数量
	 */
	private int inNums;
	
	/**
	 * 导入成功数量
	 */
	private int successNums;
	
	/**
	 * 导入失败数量
	 */
	private int failedNums;
	
	/**
	 * 错误信息
	 */
	private List<String> errorMsgs=new ArrayList<String>();

	/**
	 * 是否陈工
	 * @return
	 */
	public boolean isCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(boolean correctFlag) {
		this.correctFlag = correctFlag;
	}

	/**
	 * 传入数量
	 * @return
	 */
	public int getInNums() {
		return inNums;
	}

	public void setInNums(int inNums) {
		this.inNums = inNums;
	}

	/**
	 * 成功数量
	 * @return
	 */
	public int getSuccessNums() {
		return successNums;
	}

	public void setSuccessNums(int successNums) {
		this.successNums = successNums;
	}

	/**
	 * 失败数量
	 * @return
	 */
	public int getFailedNums() {
		return failedNums;
	}

	public void setFailedNums(int failedNums) {
		this.failedNums = failedNums;
	}

	/**
	 * 错误信息
	 * @return
	 */
	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}
}
