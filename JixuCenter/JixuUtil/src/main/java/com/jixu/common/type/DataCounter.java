package com.jixu.common.type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataCounter {
	
	//数据总量为多少
	private int totalRecords;
	
	//取数结束后，最后重新查一次数据总量是多少
	private int afterCheckTotalRecords;
	
	//截至目前取了多少数据
	private int fetchRecords;
	
	//一次取多少数据
	private int onePageRecords=10000;
	
	//是否分段取数
	private boolean pageFalg;

	//查询失败的次数，超过几次则停止查询，判定失败
	private int failedNum;
	
	//是否执行成功
	private boolean checkFlag=true;
	
	//错误信息
	private String errorMsg;
	
	/**
	 * 数据库连接
	 */
	private Connection conn;
	
	/**
	 * 数据查询语句
	 */
	private PreparedStatement preparedStatement;
	
	/**
	 * 暂时用来存放每次的最大值，用来给下次读取
	 */
	private HashMap<Integer, Object> maxCycleMap=new HashMap<Integer, Object>();
	
	/**
	 * 外部传入的参数
	 */
	private HashMap<String, Object> outParamsMap;
	
	/**
	 * 刚开始执行的时候，取一次最大值。结束时取一次最大值,两次做比较，如果发现有变动，判定为失败
	 */
	private HashMap<Integer, Object> initMaxCycleMap;
	
	/**
	 * 多文件读取，每读一个文件处理结束后，再读另一个文件
	 */
	private Set<String> readedFiles=new HashSet<String>();
	
	/**
	 * 是否已经全部读取，目前给分批次读多文件使用
	 */
	private boolean allDataReadyFlag=true;
	
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getFetchRecords() {
		return fetchRecords;
	}

	public void setFetchRecords(int fetchRecords) {
		this.fetchRecords = fetchRecords;
	}

	public int getOnePageRecords() {
		return onePageRecords;
	}

	public void setOnePageRecords(int onePageRecords) {
		this.onePageRecords = onePageRecords;
	}

	public boolean isPageFalg() {
		return pageFalg;
	}

	public void setPageFalg(boolean pageFalg) {
		this.pageFalg = pageFalg;
	}

	public int getFailedNum() {
		return failedNum;
	}

	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}
	

	public boolean isCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public HashMap<Integer, Object> getMaxCycleMap() {
		return maxCycleMap;
	}

	public void setMaxCycleMap(HashMap<Integer, Object> maxCycleMap) {
		this.maxCycleMap = maxCycleMap;
	}

	public HashMap<Integer, Object> getInitMaxCycleMap() {
		return initMaxCycleMap;
	}

	public void setInitMaxCycleMap(HashMap<Integer, Object> initMaxCycleMap) {
		this.initMaxCycleMap = initMaxCycleMap;
	}

	public int getAfterCheckTotalRecords() {
		return afterCheckTotalRecords;
	}

	public void setAfterCheckTotalRecords(int afterCheckTotalRecords) {
		this.afterCheckTotalRecords = afterCheckTotalRecords;
	}

	public HashMap<String, Object> getOutParamsMap() {
		return outParamsMap;
	}

	public void setOutParamsMap(HashMap<String, Object> outParamsMap) {
		this.outParamsMap = outParamsMap;
	}

	public Set<String> getReadedFiles() {
		return readedFiles;
	}

	public void setReadedFiles(Set<String> readedFiles) {
		this.readedFiles = readedFiles;
	}

	public boolean isAllDataReadyFlag() {
		return allDataReadyFlag;
	}

	public void setAllDataReadyFlag(boolean allDataReadyFlag) {
		this.allDataReadyFlag = allDataReadyFlag;
	}
}
