package com.jixu.adapter.types;

public class AdapterJobResultDto {
	
	private int adapterID;
	
	/**
	 * yyyy-mm-dd hh24:mi:ss
	 */
	private String startTime;
	
	/**
	 * yyyy-mm-dd hh24:mi:ss
	 */
	private String endTime;
	
	/**
	 * 1. 成功
	 * 2. 失败
	 * 3. 执行中
	 */
	private int executeStatus;
	
	/**
	 * 新增/更新成功数量
	 */
	private int successNum;
	
	/**
	 * 新增/更新失败数量
	 */
	private int failedNum;
	
	/**
	 * 删除的记录
	 */
	private int deleteNum;
	
	/**
	 * 错误消息
	 */
	private String errorMsg;
	
	/**
	 * 	执行传入的参数
	 */
	private String outParams;
	
	/**
	 * 生成的文件路径
	 */
	private String writeFilepath;
	
	/**
	 * 生成的文件大小
	 */
	private long writeFileSize;
	
	
	public int getAdapterID() {
		return adapterID;
	}

	public void setAdapterID(int adapterID) {
		this.adapterID = adapterID;
	}

	/**
	 * 开始时间
	 * @return 开始时间
	 */
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 结束时间
	 * @return	结束时间
	 */
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 1.成功
	 * 2.失败
	 * 3.执行中
	 * @return 执行结果
	 */
	public int getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(int executeStatus) {
		this.executeStatus = executeStatus;
	}

	/**
	 * 新增/更新成功数量
	 * @return 新增/更新成功数量
	 */
	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

	/**
	 * 新增/更新失败数量
	 * @return 新增/更新失败数量
	 */
	public int getFailedNum() {
		return failedNum;
	}

	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}

	/**
	 * 删除的记录
	 * @return 删除的记录
	 */
	public int getDeleteNum() {
		return deleteNum;
	}

	public void setDeleteNum(int deleteNum) {
		this.deleteNum = deleteNum;
	}

	/**
	 * 错误信息
	 * @return 错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 执行传入的参数
	 * @return 执行传入的参数
	 */
	public String getOutParams() {
		return outParams;
	}

	/**
	 * 执行传入的参数
	 * @param outParams 执行传入的参数
	 */
	public void setOutParams(String outParams) {
		this.outParams = outParams;
	}

	/**
	 *  生成的文件路径
	 * @return 生成的文件路径
	 */
	public String getWriteFilepath() {
		return writeFilepath;
	}

	/**
	 * 生成的文件路径
	 * @param writeFilepath 生成的文件路径
	 */
	public void setWriteFilepath(String writeFilepath) {
		this.writeFilepath = writeFilepath;
	}

	/**
	 *  生成的文件大小
	 * @return	生成的文件大小
	 */
	public long getWriteFileSize() {
		return writeFileSize;
	}

	/**
	 * 生成的文件大小
	 * @param writeFileSize 生成的文件大小
	 */
	public void setWriteFileSize(long writeFileSize) {
		this.writeFileSize = writeFileSize;
	}
}
