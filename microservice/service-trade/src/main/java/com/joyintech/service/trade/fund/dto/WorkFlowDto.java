/*
 * 文件名：WorkFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chengzhong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.fund.dto;

/**
 * WorkFlowDto
 * 〈功能详细描述〉
 * @author chengzhong
 * @version 1.0 2018年10月19日
 * @see WorkFlowDto
 * @since 1.0
 */
public class WorkFlowDto  {

    /**
     * 流程代码
     */
    private String seqNo;
    /**
     * 流程实例代码
     */
    private String workflowId;
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
 
}
