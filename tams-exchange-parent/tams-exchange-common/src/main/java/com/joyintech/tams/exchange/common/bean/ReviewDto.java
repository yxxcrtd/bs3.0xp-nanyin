package com.joyintech.tams.exchange.common.bean;

/**
 * @ClassName ReviewDto
 * @Description TODO
 * @Author swwan
 * @Date 2020/7/30 17:57
 * @Version 1.0
 **/
public class ReviewDto extends OrderPlaceBean {
    /**
     * 订单编号
     */
    private String orderID;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 审批人
     */
    private String users;

    /**
     * 审批角色
     */
    private String roles;

    /**
     * 操作类型
     */
    private String operType;

    /**
     * 页面ID
     */
    private String pageId;

    /**
     * 工作流参数
     */
    private String workflowParameter;

    /**
     * 元素ID
     */
    private String elementId;

    /**
     * 提交标识
     */
    private String submit;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getWorkflowParameter() {
        return workflowParameter;
    }

    public void setWorkflowParameter(String workflowParameter) {
        this.workflowParameter = workflowParameter;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
}
