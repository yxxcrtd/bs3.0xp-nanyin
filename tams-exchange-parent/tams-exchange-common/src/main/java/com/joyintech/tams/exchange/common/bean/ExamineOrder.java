package com.joyintech.tams.exchange.common.bean;


import com.joyintech.tams.exchange.common.db.domain.OrderInterface;

public class ExamineOrder {
    private String instructionsStatus;
    private String approveReason;
    private OrderInterface order;
    private int processType;

    public String getInstructionsStatus() {
        return instructionsStatus;
    }

    public void setInstructionsStatus(String instructionsStatus) {
        this.instructionsStatus = instructionsStatus;
    }

    public String getApproveReason() {
        return approveReason;
    }

    public void setApproveReason(String approveReason) {
        this.approveReason = approveReason;
    }

    public OrderInterface getOrder() {
        return order;
    }

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }

    public void setOrder(OrderInterface order) {
        this.order = order;
    }

    public ExamineOrder(String instructionsStatus, String approveReason, OrderInterface order, int processType) {
        this.instructionsStatus = instructionsStatus;
        this.approveReason = approveReason;
        this.order = order;
        this.processType = processType;
    }
}
