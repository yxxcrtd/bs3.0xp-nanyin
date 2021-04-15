package com.joyintech.tams.invest.common.position.dto;

import com.joyintech.framework.util.StringUtil;

import java.util.HashMap;

public class PostionParameter extends HashMap<String, Object>{
	
	// 交易场所  交易方向  交易状态  业务类型  操作类型 自定义参数  来源  委托编号  是否已完成
	private static final String[] dist = {"tradeMarket","ps","workflowStatus","businessType","operationType","tradeType","custom"};

	public String getTradeMarket() {
		return (String)super.get(dist[0]);
	}

	public void setTradeMarket(String tradeMarket) {
		this.put(dist[0], tradeMarket) ;
	}

	public String getPs() {
		return (String)super.get(dist[1]);
	}

	public void setPs(String ps) {
		this.put(dist[1], ps) ;
	}

	public String getWorkflowStatus() {
		return (String)super.get(dist[2]);
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.put(dist[2], workflowStatus) ;
	}

	public String getBusinessType() {
		return (String)super.get(dist[3]);
	}

	public void setBusinessType(String businessType) {
		this.put(dist[3], businessType) ;
	}

	public String getOperationType() {
		return (String)super.get(dist[4]);
	}

	public void setOperationType(String operationType) {
		this.put(dist[4], operationType) ;
	}

	public String getTradeType() {
		return (String)super.get(dist[5]);
	}

	public void setTradeType(String tradeType) {
		this.put(dist[5], tradeType) ;
	}

	public Object getCustom() {
		return (String)super.get(dist[6]);
	}

	public void setCustom(Object custom) {
		this.put(dist[6], custom) ;
	}


    
	public String toKey(){
		StringBuffer sb = new  StringBuffer();
		if(StringUtil.isNotEmpty(getTradeMarket())){
			sb.append(getTradeMarket());
		}else{
			sb.append("null");
		}
		if(StringUtil.isNotEmpty(getPs())){
			sb.append(getPs());
		}else{
			sb.append("null");
		}
		if(StringUtil.isNotEmpty(getWorkflowStatus())){
			sb.append(getWorkflowStatus());
		}else{
			sb.append("null");
		}
		if(StringUtil.isNotEmpty(getBusinessType())){
			sb.append(getBusinessType());
		}else{
			sb.append("null");
		}
		if(StringUtil.isNotEmpty(getOperationType())){
			sb.append(getOperationType());
		}else{
			sb.append("null");
		}
		if(StringUtil.isNotEmpty(getTradeType())){
			sb.append(getTradeType());
		}else{
			sb.append("null");
		}
		return sb.toString();
	}
    
}
