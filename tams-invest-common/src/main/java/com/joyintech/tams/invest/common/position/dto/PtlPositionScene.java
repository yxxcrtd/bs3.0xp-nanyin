package com.joyintech.tams.invest.common.position.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;

public class PtlPositionScene extends BaseEntity {
    /**
     * 场景Id
     */
    private String sceneId;

    /**
     * 序号
     */
    private String sceneNumber;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易状态
     */
    private String workflowStatus;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 有无指令编号
     */
    private String isEntrustId;

    /**
     * 是否指令完成
     */
    private String isFinish;

    /**
     * 动态参数
     */
    private String dynamicCondition;

    /**
     * 场景说明
     */
    private String sceneExplain;

    /**
     * 取得 场景Id
     * @return 场景Id
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 场景Id
     * @param sceneId 场景Id
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSceneNumber() {
        return sceneNumber;
    }

    /**
     * 设置 序号
     * @param sceneNumber 序号
     */
    public void setSceneNumber(String sceneNumber) {
        this.sceneNumber = sceneNumber == null ? null : sceneNumber.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 交易状态
     * @return 交易状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 交易状态
     * @param workflowStatus 交易状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
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
     * 取得 操作类型
     * @return 操作类型
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置 操作类型
     * @param operationType 操作类型
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * 取得 交易类型
     * @return 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 交易类型
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 有无指令编号
     * @return 有无指令编号
     */
    public String getIsEntrustId() {
        return isEntrustId;
    }

    /**
     * 设置 有无指令编号
     * @param isEntrustId 有无指令编号
     */
    public void setIsEntrustId(String isEntrustId) {
        this.isEntrustId = isEntrustId == null ? null : isEntrustId.trim();
    }

    /**
     * 取得 是否指令完成
     * @return 是否指令完成
     */
    public String getIsFinish() {
        return isFinish;
    }

    /**
     * 设置 是否指令完成
     * @param isFinish 是否指令完成
     */
    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish == null ? null : isFinish.trim();
    }

    /**
     * 取得 动态参数
     * @return 动态参数
     */
    public String getDynamicCondition() {
        return dynamicCondition;
    }

    /**
     * 设置 动态参数
     * @param dynamicCondition 动态参数
     */
    public void setDynamicCondition(String dynamicCondition) {
        this.dynamicCondition = dynamicCondition == null ? null : dynamicCondition.trim();
    }

    /**
     * 取得 场景说明
     * @return 场景说明
     */
    public String getSceneExplain() {
        return sceneExplain;
    }

    /**
     * 设置 场景说明
     * @param sceneExplain 场景说明
     */
    public void setSceneExplain(String sceneExplain) {
        this.sceneExplain = sceneExplain == null ? null : sceneExplain.trim();
    }

	public String toKey() {StringBuffer sb = new  StringBuffer();
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
	return sb.toString();}
}