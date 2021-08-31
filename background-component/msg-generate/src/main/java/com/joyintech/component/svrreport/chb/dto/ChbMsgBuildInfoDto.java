/*
 * 文件名：ChbMsgBuildInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月27日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.dto;

import java.util.ArrayList;
import java.util.List;

public class ChbMsgBuildInfoDto {
	
	/**
	 * 报送的文件压缩路径
	 */
	private String filePath;

	private ChbBondSubmitPara chbBondSubmitPara;

	/**
	 * 日志列表
	 */
	private List<InterfLog> logList = new ArrayList<>();

	/**
	 * 日志信息
	 */
	private InterfLog interfLog;

	public InterfLog getInterfLog() {
		return interfLog;
	}

	public void setInterfLog(InterfLog interfLog) {
		this.interfLog = interfLog;
	}

	public List<InterfLog> getLogList() {
		return logList;
	}

	public void setLogList(List<InterfLog> logList) {
		this.logList = logList;
	}

	public ChbBondSubmitPara getChbBondSubmitPara() {
		return chbBondSubmitPara;
	}

	public void setChbBondSubmitPara(ChbBondSubmitPara chbBondSubmitPara) {
		this.chbBondSubmitPara = chbBondSubmitPara;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

}
