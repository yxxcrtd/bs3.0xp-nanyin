package com.joyintech.service.valuation.lossmodel.phaseparameter.dto;

import com.joyintech.framework.base.BaseEntity;

public class ModStageParaConfigInfoDto extends BaseEntity{
	
	private static final long serialVersionUID = 0L;
	/**
	 * 唯一标识
	 */
	private String seqno;
	/**
	 * 资产所属阶段(数据字典 减值阶段)
	 */
	private String stageOfAeest;
	/**
	 * 要素绝对性(数据字典 要素绝对性)
	 */
	private String  absoluteOfElements; 
	/**
	 * 要素(数据字典 减值要素)
	 */
	private String elements;
	/**
	 * 要素值
	 */
	private String elementsValue;

	/**
	 * 启用/停用状态
	 */
	private String  useStatus;


	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getStageOfAeest() {
		return stageOfAeest;
	}
	public void setStageOfAeest(String stageOfAeest) {
		this.stageOfAeest = stageOfAeest;
	}
	public String getAbsoluteOfElements() {
		return absoluteOfElements;
	}
	public void setAbsoluteOfElements(String absoluteOfElements) {
		this.absoluteOfElements = absoluteOfElements;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
	public String getElementsValue() {
		return elementsValue;
	}
	public void setElementsValue(String elementsValue) {
		this.elementsValue = elementsValue;
	}
	
	
	
}
