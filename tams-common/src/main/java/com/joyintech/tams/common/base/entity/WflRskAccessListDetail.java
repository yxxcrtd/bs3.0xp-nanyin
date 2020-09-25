package com.joyintech.tams.common.base.entity;

public class WflRskAccessListDetail extends WflRskAccessListDetailKey {
    /**
     * 名单主体代码
     */
    private String listDetailId;

    /**
     * 名单主体类型，机构、资产
     */
    private String listDetailType;

    /**
     * 操作方式，手工、批量
     */
    private String operateType;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 名单主体代码
     * @return 名单主体代码
     */
    public String getListDetailId() {
        return listDetailId;
    }

    /**
     * 设置 名单主体代码
     * @param listDetailId 名单主体代码
     */
    public void setListDetailId(String listDetailId) {
        this.listDetailId = listDetailId == null ? null : listDetailId.trim();
    }

    /**
     * 取得 名单主体类型，机构、资产
     * @return 名单主体类型，机构、资产
     */
    public String getListDetailType() {
        return listDetailType;
    }

    /**
     * 设置 名单主体类型，机构、资产
     * @param listDetailType 名单主体类型，机构、资产
     */
    public void setListDetailType(String listDetailType) {
        this.listDetailType = listDetailType == null ? null : listDetailType.trim();
    }

    /**
     * 取得 操作方式，手工、批量
     * @return 操作方式，手工、批量
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作方式，手工、批量
     * @param operateType 操作方式，手工、批量
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    /**
     * 机构代码 add by songyawei
     */
    private String insitituteCode;
    
    public String getInsitituteCode() {
		return insitituteCode;
	}
	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
}