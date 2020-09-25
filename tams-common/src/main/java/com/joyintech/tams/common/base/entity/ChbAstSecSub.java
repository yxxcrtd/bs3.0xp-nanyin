package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbAstSecSub extends ChbAstSecSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所 参照数据字典
     */
    private String tradeMarket;

    /**
     * 债券代码
     */
    private String code;

    /**
     * 债券名称
     */
    private String name;

    /**
     * 具体类别(中债)  参照数据字典
     */
    private String chbSectype;

    /**
     * 发行方式(中债)  参照数据字典
     */
    private String issueMode;

    /**
     * 主体评级
     */
    private String mainGrade;

    /**
     * 发行机构类型（按规模划分） 参照数据字典
     */
    private String partyType1;

    /**
     * 发行机构类型（按经济类型划分） 参照数据字典
     */
    private String partyType2;

    /**
     * 发行机构类型（按技术领域划分） 参照数据字典
     */
    private String partyType3;

    /**
     * 发行机构所属行业 参照数据字典
     */
    private String industry;

    /**
     * 发行机构所属行业二级分类 参照数据字典
     */
    private String industryLevel2;

    /**
     * 登记托管机构 参照数据字典
     */
    private String registOrg;

    /**
     * 登记托管机构说明
     */
    private String registorgComment;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所 参照数据字典
     * @return 交易流通场所 参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所 参照数据字典
     * @param tradeMarket 交易流通场所 参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 债券代码
     * @param code 债券代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 债券名称
     * @param name 债券名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 具体类别(中债)  参照数据字典
     * @return 具体类别(中债)  参照数据字典
     */
    public String getChbSectype() {
        return chbSectype;
    }

    /**
     * 设置 具体类别(中债)  参照数据字典
     * @param chbSectype 具体类别(中债)  参照数据字典
     */
    public void setChbSectype(String chbSectype) {
        this.chbSectype = chbSectype == null ? null : chbSectype.trim();
    }

    /**
     * 取得 发行方式(中债)  参照数据字典
     * @return 发行方式(中债)  参照数据字典
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 发行方式(中债)  参照数据字典
     * @param issueMode 发行方式(中债)  参照数据字典
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 取得 主体评级
     * @return 主体评级
     */
    public String getMainGrade() {
        return mainGrade;
    }

    /**
     * 设置 主体评级
     * @param mainGrade 主体评级
     */
    public void setMainGrade(String mainGrade) {
        this.mainGrade = mainGrade == null ? null : mainGrade.trim();
    }

    /**
     * 取得 发行机构类型（按规模划分） 参照数据字典
     * @return 发行机构类型（按规模划分） 参照数据字典
     */
    public String getPartyType1() {
        return partyType1;
    }

    /**
     * 设置 发行机构类型（按规模划分） 参照数据字典
     * @param partyType1 发行机构类型（按规模划分） 参照数据字典
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1 == null ? null : partyType1.trim();
    }

    /**
     * 取得 发行机构类型（按经济类型划分） 参照数据字典
     * @return 发行机构类型（按经济类型划分） 参照数据字典
     */
    public String getPartyType2() {
        return partyType2;
    }

    /**
     * 设置 发行机构类型（按经济类型划分） 参照数据字典
     * @param partyType2 发行机构类型（按经济类型划分） 参照数据字典
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2 == null ? null : partyType2.trim();
    }

    /**
     * 取得 发行机构类型（按技术领域划分） 参照数据字典
     * @return 发行机构类型（按技术领域划分） 参照数据字典
     */
    public String getPartyType3() {
        return partyType3;
    }

    /**
     * 设置 发行机构类型（按技术领域划分） 参照数据字典
     * @param partyType3 发行机构类型（按技术领域划分） 参照数据字典
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3 == null ? null : partyType3.trim();
    }

    /**
     * 取得 发行机构所属行业 参照数据字典
     * @return 发行机构所属行业 参照数据字典
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 发行机构所属行业 参照数据字典
     * @param industry 发行机构所属行业 参照数据字典
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 发行机构所属行业二级分类 参照数据字典
     * @return 发行机构所属行业二级分类 参照数据字典
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 发行机构所属行业二级分类 参照数据字典
     * @param industryLevel2 发行机构所属行业二级分类 参照数据字典
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 登记托管机构 参照数据字典
     * @return 登记托管机构 参照数据字典
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构 参照数据字典
     * @param registOrg 登记托管机构 参照数据字典
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 登记托管机构说明
     * @return 登记托管机构说明
     */
    public String getRegistorgComment() {
        return registorgComment;
    }

    /**
     * 设置 登记托管机构说明
     * @param registorgComment 登记托管机构说明
     */
    public void setRegistorgComment(String registorgComment) {
        this.registorgComment = registorgComment == null ? null : registorgComment.trim();
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
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}