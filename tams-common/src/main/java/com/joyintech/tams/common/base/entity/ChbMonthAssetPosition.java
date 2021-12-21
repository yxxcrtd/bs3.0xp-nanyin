package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbMonthAssetPosition extends BaseEntity {
    /**
     * 头寸编号
     */
    private String posuuid;

    /**
     * 统计日期
     */
    private Date bookdate;

    /**
     * 开始日期
     */
    private Date startdate;

    /**
     * 结束日期
     */
    private Date enddate;

    /**
     * 投资品种
     */
    private String investVariety;

    /**
     * 资产ID
     */
    private String assetCode;

    /**
     * 资产一级分类
     */
    private String assettype;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 穿透前资产价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetvalueA;

    /**
     * 穿透后资产价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetvalueB;

    /**
     * 所属行业
     */
    private String industry1;

    /**
     * 行类一级分类
     */
    private String inAssetType;

    /**
     * 公共PPP
     */
    private String pppflag;

    /**
     * 是否投向地方政府平台
     */
    private String monitType;

    /**
     * 管理方式
     */
    private String manageType;

    /**
     * 是否政府投资基金
     */
    private String isGovernFund1;

    /**
     * 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    private String privatefundFlag;

    /**
     * 是否投向金融资产投资公司发行的私募资产管理产品
     */
    private String privateprdFlag;

    /**
     * 是否投向市场化债转股相关产品的部分
     */
    private String bondtostockFlag;

    /**
     * 评级结果
     */
    private String rating;

    /**
     * 资产二级分类
     */
    private String assettype2;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 穿透前原始资产代码
     */
    private String oldAssetCode;

    /**
     * 是否合规新产品
     */
    private String isFhxgcp;

    /**
     * 转型表资产一级分类
     */
    private String sunassettype;

    /**
     * 转型表资产二级分类
     */
    private String sunassettype2;

    /**
     * 剩余期限
     */
    private String terms;

    /**
     * 取得 头寸编号
     * @return 头寸编号
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 设置 头寸编号
     * @param posuuid 头寸编号
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 取得 统计日期
     * @return 统计日期
     */
    public Date getBookdate() {
        return bookdate;
    }

    /**
     * 设置 统计日期
     * @param bookdate 统计日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    /**
     * 取得 开始日期
     * @return 开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置 开始日期
     * @param startdate 开始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 取得 结束日期
     * @return 结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 设置 结束日期
     * @param enddate 结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 取得 投资品种
     * @return 投资品种
     */
    public String getInvestVariety() {
        return investVariety;
    }

    /**
     * 设置 投资品种
     * @param investVariety 投资品种
     */
    public void setInvestVariety(String investVariety) {
        this.investVariety = investVariety == null ? null : investVariety.trim();
    }

    /**
     * 取得 资产ID
     * @return 资产ID
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产ID
     * @param assetCode 资产ID
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 资产一级分类
     * @return 资产一级分类
     */
    public String getAssettype() {
        return assettype;
    }

    /**
     * 设置 资产一级分类
     * @param assettype 资产一级分类
     */
    public void setAssettype(String assettype) {
        this.assettype = assettype == null ? null : assettype.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 穿透前资产价值
     * @return 穿透前资产价值
     */
    public BigDecimal getAssetvalueA() {
        return assetvalueA;
    }

    /**
     * 设置 穿透前资产价值
     * @param assetvalueA 穿透前资产价值
     */
    public void setAssetvalueA(BigDecimal assetvalueA) {
        this.assetvalueA = assetvalueA;
    }

    /**
     * 取得 穿透后资产价值
     * @return 穿透后资产价值
     */
    public BigDecimal getAssetvalueB() {
        return assetvalueB;
    }

    /**
     * 设置 穿透后资产价值
     * @param assetvalueB 穿透后资产价值
     */
    public void setAssetvalueB(BigDecimal assetvalueB) {
        this.assetvalueB = assetvalueB;
    }

    /**
     * 取得 所属行业
     * @return 所属行业
     */
    public String getIndustry1() {
        return industry1;
    }

    /**
     * 设置 所属行业
     * @param industry1 所属行业
     */
    public void setIndustry1(String industry1) {
        this.industry1 = industry1 == null ? null : industry1.trim();
    }

    /**
     * 取得 行类一级分类
     * @return 行类一级分类
     */
    public String getInAssetType() {
        return inAssetType;
    }

    /**
     * 设置 行类一级分类
     * @param inAssetType 行类一级分类
     */
    public void setInAssetType(String inAssetType) {
        this.inAssetType = inAssetType == null ? null : inAssetType.trim();
    }

    /**
     * 取得 公共PPP
     * @return 公共PPP
     */
    public String getPppflag() {
        return pppflag;
    }

    /**
     * 设置 公共PPP
     * @param pppflag 公共PPP
     */
    public void setPppflag(String pppflag) {
        this.pppflag = pppflag == null ? null : pppflag.trim();
    }

    /**
     * 取得 是否投向地方政府平台
     * @return 是否投向地方政府平台
     */
    public String getMonitType() {
        return monitType;
    }

    /**
     * 设置 是否投向地方政府平台
     * @param monitType 是否投向地方政府平台
     */
    public void setMonitType(String monitType) {
        this.monitType = monitType == null ? null : monitType.trim();
    }

    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式
     * @param manageType 管理方式
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 是否政府投资基金
     * @return 是否政府投资基金
     */
    public String getIsGovernFund1() {
        return isGovernFund1;
    }

    /**
     * 设置 是否政府投资基金
     * @param isGovernFund1 是否政府投资基金
     */
    public void setIsGovernFund1(String isGovernFund1) {
        this.isGovernFund1 = isGovernFund1 == null ? null : isGovernFund1.trim();
    }

    /**
     * 取得 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     * @return 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    public String getPrivatefundFlag() {
        return privatefundFlag;
    }

    /**
     * 设置 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     * @param privatefundFlag 是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金
     */
    public void setPrivatefundFlag(String privatefundFlag) {
        this.privatefundFlag = privatefundFlag == null ? null : privatefundFlag.trim();
    }

    /**
     * 取得 是否投向金融资产投资公司发行的私募资产管理产品
     * @return 是否投向金融资产投资公司发行的私募资产管理产品
     */
    public String getPrivateprdFlag() {
        return privateprdFlag;
    }

    /**
     * 设置 是否投向金融资产投资公司发行的私募资产管理产品
     * @param privateprdFlag 是否投向金融资产投资公司发行的私募资产管理产品
     */
    public void setPrivateprdFlag(String privateprdFlag) {
        this.privateprdFlag = privateprdFlag == null ? null : privateprdFlag.trim();
    }

    /**
     * 取得 是否投向市场化债转股相关产品的部分
     * @return 是否投向市场化债转股相关产品的部分
     */
    public String getBondtostockFlag() {
        return bondtostockFlag;
    }

    /**
     * 设置 是否投向市场化债转股相关产品的部分
     * @param bondtostockFlag 是否投向市场化债转股相关产品的部分
     */
    public void setBondtostockFlag(String bondtostockFlag) {
        this.bondtostockFlag = bondtostockFlag == null ? null : bondtostockFlag.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getRating() {
        return rating;
    }

    /**
     * 设置 评级结果
     * @param rating 评级结果
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getAssettype2() {
        return assettype2;
    }

    /**
     * 设置 资产二级分类
     * @param assettype2 资产二级分类
     */
    public void setAssettype2(String assettype2) {
        this.assettype2 = assettype2 == null ? null : assettype2.trim();
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
     * 取得 穿透前原始资产代码
     * @return 穿透前原始资产代码
     */
    public String getOldAssetCode() {
        return oldAssetCode;
    }

    /**
     * 设置 穿透前原始资产代码
     * @param oldAssetCode 穿透前原始资产代码
     */
    public void setOldAssetCode(String oldAssetCode) {
        this.oldAssetCode = oldAssetCode == null ? null : oldAssetCode.trim();
    }

    /**
     * 取得 是否合规新产品
     * @return 是否合规新产品
     */
    public String getIsFhxgcp() {
        return isFhxgcp;
    }

    /**
     * 设置 是否合规新产品
     * @param isFhxgcp 是否合规新产品
     */
    public void setIsFhxgcp(String isFhxgcp) {
        this.isFhxgcp = isFhxgcp == null ? null : isFhxgcp.trim();
    }

    /**
     * 取得 转型表资产一级分类
     * @return 转型表资产一级分类
     */
    public String getSunassettype() {
        return sunassettype;
    }

    /**
     * 设置 转型表资产一级分类
     * @param sunassettype 转型表资产一级分类
     */
    public void setSunassettype(String sunassettype) {
        this.sunassettype = sunassettype == null ? null : sunassettype.trim();
    }

    /**
     * 取得 转型表资产二级分类
     * @return 转型表资产二级分类
     */
    public String getSunassettype2() {
        return sunassettype2;
    }

    /**
     * 设置 转型表资产二级分类
     * @param sunassettype2 转型表资产二级分类
     */
    public void setSunassettype2(String sunassettype2) {
        this.sunassettype2 = sunassettype2 == null ? null : sunassettype2.trim();
    }

    /**
     * 取得 剩余期限
     * @return 剩余期限
     */
    public String getTerms() {
        return terms;
    }

    /**
     * 设置 剩余期限
     * @param terms 剩余期限
     */
    public void setTerms(String terms) {
        this.terms = terms == null ? null : terms.trim();
    }
}