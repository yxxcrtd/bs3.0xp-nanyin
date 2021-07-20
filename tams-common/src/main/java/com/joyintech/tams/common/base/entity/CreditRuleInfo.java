package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CreditRuleInfo extends BaseEntity {
    /**
     * 额度规则代码
     */
    private String creditRuleId;

    /**
     * 额度规则类型,资产大类、单笔资产
     */
    private String creditRuleType;

    /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 资产大类
     */
    private String astType1;

    /**
     * 资产二级分类
     */
    private String astType2;

    /**
     * 资产三级分类
     */
    private String astType3;

    /**
     * 资产四级分类
     */
    private String astType4;

    /**
     * 资产五级分类
     */
    private String astType5;

    /**
     * 机构大类
     */
    private String orgType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 扣减对象
     */
    private String deductObjType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 额度规则代码
     * @return 额度规则代码
     */
    public String getCreditRuleId() {
        return creditRuleId;
    }

    /**
     * 设置 额度规则代码
     * @param creditRuleId 额度规则代码
     */
    public void setCreditRuleId(String creditRuleId) {
        this.creditRuleId = creditRuleId == null ? null : creditRuleId.trim();
    }

    /**
     * 取得 额度规则类型,资产大类、单笔资产
     * @return 额度规则类型,资产大类、单笔资产
     */
    public String getCreditRuleType() {
        return creditRuleType;
    }

    /**
     * 设置 额度规则类型,资产大类、单笔资产
     * @param creditRuleType 额度规则类型,资产大类、单笔资产
     */
    public void setCreditRuleType(String creditRuleType) {
        this.creditRuleType = creditRuleType == null ? null : creditRuleType.trim();
    }

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAstType1() {
        return astType1;
    }

    /**
     * 设置 资产大类
     * @param astType1 资产大类
     */
    public void setAstType1(String astType1) {
        this.astType1 = astType1 == null ? null : astType1.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getAstType2() {
        return astType2;
    }

    /**
     * 设置 资产二级分类
     * @param astType2 资产二级分类
     */
    public void setAstType2(String astType2) {
        this.astType2 = astType2 == null ? null : astType2.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getAstType3() {
        return astType3;
    }

    /**
     * 设置 资产三级分类
     * @param astType3 资产三级分类
     */
    public void setAstType3(String astType3) {
        this.astType3 = astType3 == null ? null : astType3.trim();
    }

    /**
     * 取得 资产四级分类
     * @return 资产四级分类
     */
    public String getAstType4() {
        return astType4;
    }

    /**
     * 设置 资产四级分类
     * @param astType4 资产四级分类
     */
    public void setAstType4(String astType4) {
        this.astType4 = astType4 == null ? null : astType4.trim();
    }

    /**
     * 取得 资产五级分类
     * @return 资产五级分类
     */
    public String getAstType5() {
        return astType5;
    }

    /**
     * 设置 资产五级分类
     * @param astType5 资产五级分类
     */
    public void setAstType5(String astType5) {
        this.astType5 = astType5 == null ? null : astType5.trim();
    }

    /**
     * 取得 机构大类
     * @return 机构大类
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置 机构大类
     * @param orgType 机构大类
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 扣减对象
     * @return 扣减对象
     */
    public String getDeductObjType() {
        return deductObjType;
    }

    /**
     * 设置 扣减对象
     * @param deductObjType 扣减对象
     */
    public void setDeductObjType(String deductObjType) {
        this.deductObjType = deductObjType == null ? null : deductObjType.trim();
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
}