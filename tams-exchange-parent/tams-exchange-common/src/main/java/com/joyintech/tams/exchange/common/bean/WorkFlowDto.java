package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName WorkFlowDto
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/7 14:50
 * @Version 1.0
 **/
public class WorkFlowDto {
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
