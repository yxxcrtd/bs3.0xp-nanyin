package com.joyintech.tams.common.base.entity;

public class ChbSubCheckItemRule extends ChbSubCheckItemRuleKey {
    /**
     * 项目编号
     */
    private String itemCode;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 检查规则编号
     */
    private String checkRuleId;

    /**
     * 检查规则参数
     */
    private String checkRulePara;

    /**
     * null
     */
    private String remark;

    /**
     * 取得 项目编号
     * @return 项目编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 项目编号
     * @param itemCode 项目编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 取得 项目名称
     * @return 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置 项目名称
     * @param itemName 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 取得 检查规则编号
     * @return 检查规则编号
     */
    public String getCheckRuleId() {
        return checkRuleId;
    }

    /**
     * 设置 检查规则编号
     * @param checkRuleId 检查规则编号
     */
    public void setCheckRuleId(String checkRuleId) {
        this.checkRuleId = checkRuleId == null ? null : checkRuleId.trim();
    }

    /**
     * 取得 检查规则参数
     * @return 检查规则参数
     */
    public String getCheckRulePara() {
        return checkRulePara;
    }

    /**
     * 设置 检查规则参数
     * @param checkRulePara 检查规则参数
     */
    public void setCheckRulePara(String checkRulePara) {
        this.checkRulePara = checkRulePara == null ? null : checkRulePara.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}