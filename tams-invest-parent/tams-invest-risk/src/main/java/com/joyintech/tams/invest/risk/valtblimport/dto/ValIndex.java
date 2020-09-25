package com.joyintech.tams.invest.risk.valtblimport.dto;


public enum ValIndex {
	
	SUBJECTCODE("科目代码"),
	SUBJECTNAME("科目名称"),
	QUANTITY("证券数量"), // 证券数量
	UNIT_COST("单位成本"), // 单位成本
	COST("证券成本"), // 证券成本
	CTNWR("成本占净值比"), // 成本占净值比
	MCP("行情收市价"), // 行情收市价
	SMV("证券市值"), // 证券市值
	MCR("市值占净值比"), // 市值占净值比
	VAL_INC("估值增值"), // 估值增值
	SUSPENSION_INFO("停牌信息"), // 停牌信息
	EQUITY_INFO("权益信息"); // 权益信息
	
	private String desc;
	
	private ValIndex(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}
}
