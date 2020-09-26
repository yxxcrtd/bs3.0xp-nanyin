package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValFxTargetInfo extends BaseEntity {
    /**
     * 逾期资产代码
     */
    private String assetCode;

    /**
     * 逾期资产类型
     */
    private String finprodType;

    /**
     * 分支类型
     */
    private String branchType;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 是否计提罚息
     */
    private String isFxdraw;

    /**
     * 截止后是否冲回
     */
    private String isCorrect;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 罚息计提基数
     */
    private String penaltyBaseType;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 取得 逾期资产代码
     * @return 逾期资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 逾期资产代码
     * @param assetCode 逾期资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 逾期资产类型
     * @return 逾期资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 逾期资产类型
     * @param finprodType 逾期资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 分支类型
     * @return 分支类型
     */
    public String getBranchType() {
        return branchType;
    }

    /**
     * 设置 分支类型
     * @param branchType 分支类型
     */
    public void setBranchType(String branchType) {
        this.branchType = branchType == null ? null : branchType.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 是否计提罚息
     * @return 是否计提罚息
     */
    public String getIsFxdraw() {
        return isFxdraw;
    }

    /**
     * 设置 是否计提罚息
     * @param isFxdraw 是否计提罚息
     */
    public void setIsFxdraw(String isFxdraw) {
        this.isFxdraw = isFxdraw == null ? null : isFxdraw.trim();
    }

    /**
     * 取得 截止后是否冲回
     * @return 截止后是否冲回
     */
    public String getIsCorrect() {
        return isCorrect;
    }

    /**
     * 设置 截止后是否冲回
     * @param isCorrect 截止后是否冲回
     */
    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect == null ? null : isCorrect.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 罚息计提基数
     * @return 罚息计提基数
     */
    public String getPenaltyBaseType() {
        return penaltyBaseType;
    }

    /**
     * 设置 罚息计提基数
     * @param penaltyBaseType 罚息计提基数
     */
    public void setPenaltyBaseType(String penaltyBaseType) {
        this.penaltyBaseType = penaltyBaseType == null ? null : penaltyBaseType.trim();
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}