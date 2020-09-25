package com.jixu.common.type;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jixu.common.pojo.InterfaceSource;

public class MsgContainer extends ResultContainer{
	
	private List<String> fields;
	private HashMap<String, Object> fieldsHM;
	private InterfaceSource source;
	private int operatoionStep;
	private Date ledgerDate;
	private String portid;
	private boolean verifyFlag;
	private String jsonMsg;
	private String msg;
	private boolean newProcessFlag=false;
	//交易导入方式 参考JixuDictCode的IMP_TRADE_TYPE字段
	private int tradeImportType=1;
	
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public InterfaceSource getSource() {
		return source;
	}
	public void setSource(InterfaceSource source) {
		this.source = source;
	}
	public int getOperatoionStep() {
		return operatoionStep;
	}
	public void setOperatoionStep(int operatoionStep) {
		this.operatoionStep = operatoionStep;
	}
	public Date getLedgerDate() {
		return ledgerDate;
	}
	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	public String getPortid() {
		return portid;
	}
	public void setPortid(String portid) {
		this.portid = portid;
	}
	/**
	 * 获取 fieldsHM
	 */
	
	public HashMap<String, Object> getFieldsHM() {
		return fieldsHM;
	}
	/**
	 * 设置 fieldsHM
	 */
	
	public void setFieldsHM(HashMap<String, Object> fieldsHM) {
		this.fieldsHM = fieldsHM;
	}
	public boolean isVerifyFlag() {
		return verifyFlag;
	}
	public void setVerifyFlag(boolean verifyFlag) {
		this.verifyFlag = verifyFlag;
	}
	public String getJsonMsg() {
		return jsonMsg;
	}
	public void setJsonMsg(String jsonMsg) {
		this.jsonMsg = jsonMsg;
	}
	public boolean isNewProcessFlag() {
		return newProcessFlag;
	}
	public void setNewProcessFlag(boolean newProcessFlag) {
		this.newProcessFlag = newProcessFlag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTradeImportType() {
		return tradeImportType;
	}
	public void setTradeImportType(int tradeImportType) {
		this.tradeImportType = tradeImportType;
	}
}
