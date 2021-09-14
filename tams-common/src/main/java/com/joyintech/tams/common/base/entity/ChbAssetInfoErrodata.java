package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAssetInfoErrodata extends BaseEntity {
    /**
     * uuid主键
     */
    private String datauuid;

    /**
     * 行内资产负债编码
     */
    private String assetCode;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    private String assetType2;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 到期日
     */
    private String mdate;

    /**
     * 上游资产一级分类
     */
    private String orgAssetType1;

    /**
     * 上游资产二级分类
     */
    private String orgAssetType2;

    /**
     * 上游资产三级分类
     */
    private String orgAssetType3;

    /**
     * 上游资产四级分类
     */
    private String orgAssetType4;

    /**
     * 上游资产五级分类
     */
    private String orgAssetType5;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 交易流通场所
     */
    private String tradeMarket;

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 取得 uuid主键
     * @return uuid主键
     */
    public String getDatauuid() {
        return datauuid;
    }

    /**
     * 设置 uuid主键
     * @param datauuid uuid主键
     */
    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid == null ? null : datauuid.trim();
    }

    /**
     * 取得 行内资产负债编码
     * @return 行内资产负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产负债编码
     * @param assetCode 行内资产负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型
     * @param assetType 资产类型
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     * @return 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     * @param assetType2 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate == null ? null : vdate.trim();
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate == null ? null : mdate.trim();
    }

    /**
     * 取得 上游资产一级分类
     * @return 上游资产一级分类
     */
    public String getOrgAssetType1() {
        return orgAssetType1;
    }

    /**
     * 设置 上游资产一级分类
     * @param orgAssetType1 上游资产一级分类
     */
    public void setOrgAssetType1(String orgAssetType1) {
        this.orgAssetType1 = orgAssetType1 == null ? null : orgAssetType1.trim();
    }

    /**
     * 取得 上游资产二级分类
     * @return 上游资产二级分类
     */
    public String getOrgAssetType2() {
        return orgAssetType2;
    }

    /**
     * 设置 上游资产二级分类
     * @param orgAssetType2 上游资产二级分类
     */
    public void setOrgAssetType2(String orgAssetType2) {
        this.orgAssetType2 = orgAssetType2 == null ? null : orgAssetType2.trim();
    }

    /**
     * 取得 上游资产三级分类
     * @return 上游资产三级分类
     */
    public String getOrgAssetType3() {
        return orgAssetType3;
    }

    /**
     * 设置 上游资产三级分类
     * @param orgAssetType3 上游资产三级分类
     */
    public void setOrgAssetType3(String orgAssetType3) {
        this.orgAssetType3 = orgAssetType3 == null ? null : orgAssetType3.trim();
    }

    /**
     * 取得 上游资产四级分类
     * @return 上游资产四级分类
     */
    public String getOrgAssetType4() {
        return orgAssetType4;
    }

    /**
     * 设置 上游资产四级分类
     * @param orgAssetType4 上游资产四级分类
     */
    public void setOrgAssetType4(String orgAssetType4) {
        this.orgAssetType4 = orgAssetType4 == null ? null : orgAssetType4.trim();
    }

    /**
     * 取得 上游资产五级分类
     * @return 上游资产五级分类
     */
    public String getOrgAssetType5() {
        return orgAssetType5;
    }

    /**
     * 设置 上游资产五级分类
     * @param orgAssetType5 上游资产五级分类
     */
    public void setOrgAssetType5(String orgAssetType5) {
        this.orgAssetType5 = orgAssetType5 == null ? null : orgAssetType5.trim();
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
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }
}