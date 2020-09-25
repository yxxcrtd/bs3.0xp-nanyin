package com.jixu.job.obj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 某个日期每个投资组合的完成状态
 * @author 桃汰猪
 *
 */
public class WorkFlowStepStatus {
	
	//做账日期
	private Date ledgerDate;
	
	//状态, 匹配 WorkFlowStatus
	private int status;
	
	//工作流步骤 --投资组合
	private HashMap<Integer, List<String>> stepPortsMap=new HashMap<Integer, List<String>>();

	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HashMap<Integer, List<String>> getStepPortsMap() {
		return stepPortsMap;
	}

	public void setStepPortsMap(HashMap<Integer, List<String>> stepPortsMap) {
		this.stepPortsMap = stepPortsMap;
	}
}
