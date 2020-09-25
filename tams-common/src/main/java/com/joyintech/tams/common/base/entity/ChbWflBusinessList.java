package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbWflBusinessList extends BaseEntity {
    /**
     * 流程代码（自动采番）
     */
    private String seqno;

    /**
     * 流程实例代码
     */
    private String workflowId;

    /**
     * 画面代码
     */
    private String pageId;

    /**
     * 要素代码
     */
    private String elementId;

    /**
     * 任务创建人
     */
    private String crtUser;

    /**
     * 任务创建日期
     */
    private Date crtDate;

    /**
     * 当前步骤处理人
     */
    private String curUser;

    /**
     * 当前节点代码
     */
    private String curNodeId;

    /**
     * 下一步骤处理人
     */
    private String nextUser;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 操作说明
     */
    private String title;

    /**
     * 操作类型
     */
    private String actionType;

    /**
     * 流程状态 参照数据字典
     */
    private String processState;

    /**
     * 是否可作废 参照数据字典
     */
    private String cancelFlg;

    /**
     * 是否已签收 参照数据字典
     */
    private String signFlg;

    /**
     * 是否已完成 参照数据字典
     */
    private String completeFlg;

    /**
     * 取得 流程代码（自动采番）
     * @return 流程代码（自动采番）
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流程代码（自动采番）
     * @param seqno 流程代码（自动采番）
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 流程实例代码
     * @return 流程实例代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 流程实例代码
     * @param workflowId 流程实例代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 画面代码
     * @return 画面代码
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 画面代码
     * @param pageId 画面代码
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 要素代码
     * @return 要素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 要素代码
     * @param elementId 要素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 任务创建人
     * @return 任务创建人
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * 设置 任务创建人
     * @param crtUser 任务创建人
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    /**
     * 取得 任务创建日期
     * @return 任务创建日期
     */
    public Date getCrtDate() {
        return crtDate;
    }

    /**
     * 设置 任务创建日期
     * @param crtDate 任务创建日期
     */
    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    /**
     * 取得 当前步骤处理人
     * @return 当前步骤处理人
     */
    public String getCurUser() {
        return curUser;
    }

    /**
     * 设置 当前步骤处理人
     * @param curUser 当前步骤处理人
     */
    public void setCurUser(String curUser) {
        this.curUser = curUser == null ? null : curUser.trim();
    }

    /**
     * 取得 当前节点代码
     * @return 当前节点代码
     */
    public String getCurNodeId() {
        return curNodeId;
    }

    /**
     * 设置 当前节点代码
     * @param curNodeId 当前节点代码
     */
    public void setCurNodeId(String curNodeId) {
        this.curNodeId = curNodeId == null ? null : curNodeId.trim();
    }

    /**
     * 取得 下一步骤处理人
     * @return 下一步骤处理人
     */
    public String getNextUser() {
        return nextUser;
    }

    /**
     * 设置 下一步骤处理人
     * @param nextUser 下一步骤处理人
     */
    public void setNextUser(String nextUser) {
        this.nextUser = nextUser == null ? null : nextUser.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置 业务类型
     * @param businessType 业务类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 操作说明
     * @return 操作说明
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 操作说明
     * @param title 操作说明
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * 设置 操作类型
     * @param actionType 操作类型
     */
    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    /**
     * 取得 流程状态 参照数据字典
     * @return 流程状态 参照数据字典
     */
    public String getProcessState() {
        return processState;
    }

    /**
     * 设置 流程状态 参照数据字典
     * @param processState 流程状态 参照数据字典
     */
    public void setProcessState(String processState) {
        this.processState = processState == null ? null : processState.trim();
    }

    /**
     * 取得 是否可作废 参照数据字典
     * @return 是否可作废 参照数据字典
     */
    public String getCancelFlg() {
        return cancelFlg;
    }

    /**
     * 设置 是否可作废 参照数据字典
     * @param cancelFlg 是否可作废 参照数据字典
     */
    public void setCancelFlg(String cancelFlg) {
        this.cancelFlg = cancelFlg == null ? null : cancelFlg.trim();
    }

    /**
     * 取得 是否已签收 参照数据字典
     * @return 是否已签收 参照数据字典
     */
    public String getSignFlg() {
        return signFlg;
    }

    /**
     * 设置 是否已签收 参照数据字典
     * @param signFlg 是否已签收 参照数据字典
     */
    public void setSignFlg(String signFlg) {
        this.signFlg = signFlg == null ? null : signFlg.trim();
    }

    /**
     * 取得 是否已完成 参照数据字典
     * @return 是否已完成 参照数据字典
     */
    public String getCompleteFlg() {
        return completeFlg;
    }

    /**
     * 设置 是否已完成 参照数据字典
     * @param completeFlg 是否已完成 参照数据字典
     */
    public void setCompleteFlg(String completeFlg) {
        this.completeFlg = completeFlg == null ? null : completeFlg.trim();
    }
}