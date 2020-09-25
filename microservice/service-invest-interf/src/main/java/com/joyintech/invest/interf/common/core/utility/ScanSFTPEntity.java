package com.joyintech.invest.interf.common.core.utility;

public class ScanSFTPEntity {
	/*
	 * 扫描线程开始时间
	 */
	private long startTime;
	/*
	 * 扫描线程终止时间
	 */
	private long endTime;
	/*
	 * 接口id
	 */
	private String interfId;
	
	/*
	 * 请求报文的序列号
	 */
	private String requestSeqNo;
	
	/*
	 * 上传文件的sftp路径
	 */
	private String sftpFilePath;
	
	/*
	 * 扫描状态 ：成功，失败
	 */
	private String scanStatus;
	
	/*
	 * 扫描状态码： 1-成功 ， 0-失败
	 */
	private String scanCode;
	
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getInterfId() {
		return interfId;
	}

	public void setInterfId(String interfId) {
		this.interfId = interfId;
	}

	public String getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(String requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public String getSftpFilePath() {
		return sftpFilePath;
	}

	public void setSftpFilePath(String sftpFilePath) {
		this.sftpFilePath = sftpFilePath;
	}


	

	public String getScanStatus() {
		return scanStatus;
	}

	public void setScanStatus(String scanStatus) {
		this.scanStatus = scanStatus;
	}

	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	
	
	

}
