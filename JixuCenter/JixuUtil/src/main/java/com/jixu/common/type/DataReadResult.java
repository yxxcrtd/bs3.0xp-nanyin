package com.jixu.common.type;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName 		DataReadResult
 * @author  		Pan Wei 
 * @Date 			2016年12月5日 下午2:28:46 	
 * @Description		[]  
 */

public class DataReadResult {
	
	private List<String> dataHeader=new ArrayList<String>();
	
	private List<List<Object>> dataResult=new ArrayList<List<Object>>();
	
	private List<String> filePathList=new ArrayList<String>();
	
	private DataCounter dataCounter;
	
	private String writeFilePath; 
	
	public List<String> getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(List<String> dataHeader) {
		this.dataHeader = dataHeader;
	}

	public List<List<Object>> getDataResult() {
		return dataResult;
	}

	public void setDataResult(List<List<Object>> dataResult) {
		this.dataResult = dataResult;
	}

	public DataCounter getDataCounter() {
		return dataCounter;
	}

	public void setDataCounter(DataCounter dataCounter) {
		this.dataCounter = dataCounter;
	}

	public List<String> getFilePathList() {
		return filePathList;
	}

	public void setFilePathList(List<String> filePathList) {
		this.filePathList = filePathList;
	}

	public String getWriteFilePath() {
		return writeFilePath;
	}

	public void setWriteFilePath(String writeFilePath) {
		this.writeFilePath = writeFilePath;
	}
}
