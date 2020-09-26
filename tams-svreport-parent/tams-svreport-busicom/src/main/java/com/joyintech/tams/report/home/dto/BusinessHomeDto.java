package com.joyintech.tams.report.home.dto;

/**
 * @Description 业务首页dto
 * @author jiangxiao
 * @version 1.7 2019年5月16日
 * @since 1.7
 */
public class BusinessHomeDto {
	/**
	 * 接口文件类型
	 */
	private String interfFileType;
	
	/**
	 * 接入文件个数
	 */
	private int accessFilenum;
	
	/**
	 * 异常结束个数
	 */
	private int exceptionnum;
	
	/**
	 * 正常结束个数
	 */
	private int normalnum;
	
	/**
	 * 数据接入总数
	 */
	private int datanum;
	
	/**
	 * 检查通过个数
	 */
	private int checkSuccessnum;
	
	/**
	 * 检查不通过个数
	 */
	private int checkfailnum;
	
	/**
	 * 待处理个数
	 */
	private int processednum;
	
	/**
	 * 产品登记编码
	 */
	private int prodRegistnum;
	
	/**
	 * 识别标识合并
	 */
	private int mergenum;
	
	/**
	 * 接口文件总数
	 */
	private int accessFiletotal;
	
	/**
	 * 数据检查总数
	 */
	private int checkDatatotal;
	
	/**
	 * 数据维护总数
	 */
	private int processedDatatotal;

	public String getInterfFileType() {
		return interfFileType;
	}

	public void setInterfFileType(String interfFileType) {
		this.interfFileType = interfFileType;
	}

	public int getAccessFilenum() {
		return accessFilenum;
	}

	public void setAccessFilenum(int accessFilenum) {
		this.accessFilenum = accessFilenum;
	}

	public int getExceptionnum() {
		return exceptionnum;
	}

	public void setExceptionnum(int exceptionnum) {
		this.exceptionnum = exceptionnum;
	}

	public int getNormalnum() {
		return normalnum;
	}

	public void setNormalnum(int normalnum) {
		this.normalnum = normalnum;
	}

	public int getDatanum() {
		return datanum;
	}

	public void setDatanum(int datanum) {
		this.datanum = datanum;
	}

	public int getCheckSuccessnum() {
		return checkSuccessnum;
	}

	public void setCheckSuccessnum(int checkSuccessnum) {
		this.checkSuccessnum = checkSuccessnum;
	}

	public int getCheckfailnum() {
		return checkfailnum;
	}

	public void setCheckfailnum(int checkfailnum) {
		this.checkfailnum = checkfailnum;
	}

	public int getProcessednum() {
		return processednum;
	}

	public void setProcessednum(int processednum) {
		this.processednum = processednum;
	}

	public int getProdRegistnum() {
		return prodRegistnum;
	}

	public void setProdRegistnum(int prodRegistnum) {
		this.prodRegistnum = prodRegistnum;
	}

	public int getMergenum() {
		return mergenum;
	}

	public void setMergenum(int mergenum) {
		this.mergenum = mergenum;
	}

	public int getAccessFiletotal() {
		return accessFiletotal;
	}

	public void setAccessFiletotal(int accessFiletotal) {
		this.accessFiletotal = accessFiletotal;
	}

	public int getCheckDatatotal() {
		return checkDatatotal;
	}

	public void setCheckDatatotal(int checkDatatotal) {
		this.checkDatatotal = checkDatatotal;
	}

	public int getProcessedDatatotal() {
		return processedDatatotal;
	}

	public void setProcessedDatatotal(int processedDatatotal) {
		this.processedDatatotal = processedDatatotal;
	}
	
}
