package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PbcMonthAssetPositiondetail extends BaseEntity {
    /**
     * 主键uuid
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * null
     */
    private String term;

    /**
     * null
     */
    private String detailno;

    /**
     * null
     */
    private String detailno2;

    /**
     * 类型，枚举值：01 资产、02 负债、03 科目余额
     */
    private String positionType;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号
     */
    private String assetCode;

    /**
     * 资产一级分类，人行资产分类
     */
    private String assetType1;

    /**
     * 资产二级分类，人行资产分类
     */
    private String assetType2;

    /**
     * 资产三级分类，人行资产分类
     */
    private String assetType3;

    /**
     * null
     */
    private String subjectName;

    /**
     * 交易对手名称
     */
    private String rivalParty;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 币种，如CNY等
     */
    private String ccy;

    /**
     * 金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 累计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 剩余期限:0表示无固定期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays;

    /**
     * 待清算款
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal delayPayAmt;

    /**
     * 当日成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costAmt;

    /**
     * 当日公允价值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal floatIngpl;

    /**
     * 当日累计应计利息余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intincexp;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intadjexpAdd;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lossAmt;

    /**
     * 科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产
     */
    private String balFlag;

    /**
     * 资产起息日
     */
    private Date vdate;

    /**
     * 资产到期日
     */
    private Date mdate;

    /**
     * 最近开放计划结束日/行权日
     */
    private Date openplan;

    /**
     * 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industry;

    /**
     * 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryLevel2;

    /**
     * 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    private String partyType1;

    /**
     * 发行机构类型（按技术领域划分）01 高新技术企业/99 其他
     */
    private String partyType3;

    /**
     * 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    private String partyType2;

    /**
     * 投管一级分类
     */
    private String orgAssetType1;

    /**
     * null
     */
    private String orgAssetType2;

    /**
     * null
     */
    private String orgAssetType3;

    /**
     * 除回购和拆借外贷款所属地区
     */
    private String buyPlace;

    /**
     * 贷款-借款人类型
     */
    private String borrowerType;

    /**
     * 贷款-行业信息
     */
    private String industryInfo;

    /**
     * 贷款-企业规模
     */
    private String enterpriseScale;

    /**
     * 贷款-借款人地区(市)
     */
    private String borrowerCity;

    /**
     * 贷款-基础资产投向分类
     */
    private String assetsInvest;

    /**
     * 贷款-各比例
     */
    private String eachProportion;

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 设置 主键uuid
     * @param posuuid 主键uuid
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 null
     * @param term null
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDetailno() {
        return detailno;
    }

    /**
     * 设置 null
     * @param detailno null
     */
    public void setDetailno(String detailno) {
        this.detailno = detailno == null ? null : detailno.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDetailno2() {
        return detailno2;
    }

    /**
     * 设置 null
     * @param detailno2 null
     */
    public void setDetailno2(String detailno2) {
        this.detailno2 = detailno2 == null ? null : detailno2.trim();
    }

    /**
     * 取得 类型，枚举值：01 资产、02 负债、03 科目余额
     * @return 类型，枚举值：01 资产、02 负债、03 科目余额
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * 设置 类型，枚举值：01 资产、02 负债、03 科目余额
     * @param positionType 类型，枚举值：01 资产、02 负债、03 科目余额
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号
     * @return 资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号
     * @param assetCode 资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 资产一级分类，人行资产分类
     * @return 资产一级分类，人行资产分类
     */
    public String getAssetType1() {
        return assetType1;
    }

    /**
     * 设置 资产一级分类，人行资产分类
     * @param assetType1 资产一级分类，人行资产分类
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1 == null ? null : assetType1.trim();
    }

    /**
     * 取得 资产二级分类，人行资产分类
     * @return 资产二级分类，人行资产分类
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 资产二级分类，人行资产分类
     * @param assetType2 资产二级分类，人行资产分类
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 资产三级分类，人行资产分类
     * @return 资产三级分类，人行资产分类
     */
    public String getAssetType3() {
        return assetType3;
    }

    /**
     * 设置 资产三级分类，人行资产分类
     * @param assetType3 资产三级分类，人行资产分类
     */
    public void setAssetType3(String assetType3) {
        this.assetType3 = assetType3 == null ? null : assetType3.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 null
     * @param subjectName null
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 取得 交易对手名称
     * @return 交易对手名称
     */
    public String getRivalParty() {
        return rivalParty;
    }

    /**
     * 设置 交易对手名称
     * @param rivalParty 交易对手名称
     */
    public void setRivalParty(String rivalParty) {
        this.rivalParty = rivalParty == null ? null : rivalParty.trim();
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率
     * @param rate 利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 币种，如CNY等
     * @return 币种，如CNY等
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种，如CNY等
     * @param ccy 币种，如CNY等
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）
     * @return 金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）
     * @param amt 金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 取得 累计利息
     * @return 累计利息
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 累计利息
     * @param intAmt 累计利息
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 剩余期限:0表示无固定期限
     * @return 剩余期限:0表示无固定期限
     */
    public BigDecimal getEndDays() {
        return endDays;
    }

    /**
     * 设置 剩余期限:0表示无固定期限
     * @param endDays 剩余期限:0表示无固定期限
     */
    public void setEndDays(BigDecimal endDays) {
        this.endDays = endDays;
    }

    /**
     * 取得 待清算款
     * @return 待清算款
     */
    public BigDecimal getDelayPayAmt() {
        return delayPayAmt;
    }

    /**
     * 设置 待清算款
     * @param delayPayAmt 待清算款
     */
    public void setDelayPayAmt(BigDecimal delayPayAmt) {
        this.delayPayAmt = delayPayAmt;
    }

    /**
     * 取得 当日成本
     * @return 当日成本
     */
    public BigDecimal getCostAmt() {
        return costAmt;
    }

    /**
     * 设置 当日成本
     * @param costAmt 当日成本
     */
    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    /**
     * 取得 当日公允价值变动
     * @return 当日公允价值变动
     */
    public BigDecimal getFloatIngpl() {
        return floatIngpl;
    }

    /**
     * 设置 当日公允价值变动
     * @param floatIngpl 当日公允价值变动
     */
    public void setFloatIngpl(BigDecimal floatIngpl) {
        this.floatIngpl = floatIngpl;
    }

    /**
     * 取得 当日累计应计利息余额
     * @return 当日累计应计利息余额
     */
    public BigDecimal getIntincexp() {
        return intincexp;
    }

    /**
     * 设置 当日累计应计利息余额
     * @param intincexp 当日累计应计利息余额
     */
    public void setIntincexp(BigDecimal intincexp) {
        this.intincexp = intincexp;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getIntadjexpAdd() {
        return intadjexpAdd;
    }

    /**
     * 设置 null
     * @param intadjexpAdd null
     */
    public void setIntadjexpAdd(BigDecimal intadjexpAdd) {
        this.intadjexpAdd = intadjexpAdd;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getLossAmt() {
        return lossAmt;
    }

    /**
     * 设置 null
     * @param lossAmt null
     */
    public void setLossAmt(BigDecimal lossAmt) {
        this.lossAmt = lossAmt;
    }

    /**
     * 取得 科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产
     * @return 科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 设置 科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产
     * @param balFlag 科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag == null ? null : balFlag.trim();
    }

    /**
     * 取得 资产起息日
     * @return 资产起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 资产起息日
     * @param vdate 资产起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 资产到期日
     * @return 资产到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 资产到期日
     * @param mdate 资产到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 最近开放计划结束日/行权日
     * @return 最近开放计划结束日/行权日
     */
    public Date getOpenplan() {
        return openplan;
    }

    /**
     * 设置 最近开放计划结束日/行权日
     * @param openplan 最近开放计划结束日/行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenplan(Date openplan) {
        this.openplan = openplan;
    }

    /**
     * 取得 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param industry 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param industryLevel2 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @return 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public String getPartyType1() {
        return partyType1;
    }

    /**
     * 设置 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @param partyType1 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1 == null ? null : partyType1.trim();
    }

    /**
     * 取得 发行机构类型（按技术领域划分）01 高新技术企业/99 其他
     * @return 发行机构类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public String getPartyType3() {
        return partyType3;
    }

    /**
     * 设置 发行机构类型（按技术领域划分）01 高新技术企业/99 其他
     * @param partyType3 发行机构类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3 == null ? null : partyType3.trim();
    }

    /**
     * 取得 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @return 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public String getPartyType2() {
        return partyType2;
    }

    /**
     * 设置 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @param partyType2 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2 == null ? null : partyType2.trim();
    }

    /**
     * 取得 投管一级分类
     * @return 投管一级分类
     */
    public String getOrgAssetType1() {
        return orgAssetType1;
    }

    /**
     * 设置 投管一级分类
     * @param orgAssetType1 投管一级分类
     */
    public void setOrgAssetType1(String orgAssetType1) {
        this.orgAssetType1 = orgAssetType1 == null ? null : orgAssetType1.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOrgAssetType2() {
        return orgAssetType2;
    }

    /**
     * 设置 null
     * @param orgAssetType2 null
     */
    public void setOrgAssetType2(String orgAssetType2) {
        this.orgAssetType2 = orgAssetType2 == null ? null : orgAssetType2.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOrgAssetType3() {
        return orgAssetType3;
    }

    /**
     * 设置 null
     * @param orgAssetType3 null
     */
    public void setOrgAssetType3(String orgAssetType3) {
        this.orgAssetType3 = orgAssetType3 == null ? null : orgAssetType3.trim();
    }

    /**
     * 取得 除回购和拆借外贷款所属地区
     * @return 除回购和拆借外贷款所属地区
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 设置 除回购和拆借外贷款所属地区
     * @param buyPlace 除回购和拆借外贷款所属地区
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace == null ? null : buyPlace.trim();
    }

    /**
     * 取得 贷款-借款人类型
     * @return 贷款-借款人类型
     */
    public String getBorrowerType() {
        return borrowerType;
    }

    /**
     * 设置 贷款-借款人类型
     * @param borrowerType 贷款-借款人类型
     */
    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType == null ? null : borrowerType.trim();
    }

    /**
     * 取得 贷款-行业信息
     * @return 贷款-行业信息
     */
    public String getIndustryInfo() {
        return industryInfo;
    }

    /**
     * 设置 贷款-行业信息
     * @param industryInfo 贷款-行业信息
     */
    public void setIndustryInfo(String industryInfo) {
        this.industryInfo = industryInfo == null ? null : industryInfo.trim();
    }

    /**
     * 取得 贷款-企业规模
     * @return 贷款-企业规模
     */
    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    /**
     * 设置 贷款-企业规模
     * @param enterpriseScale 贷款-企业规模
     */
    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale == null ? null : enterpriseScale.trim();
    }

    /**
     * 取得 贷款-借款人地区(市)
     * @return 贷款-借款人地区(市)
     */
    public String getBorrowerCity() {
        return borrowerCity;
    }

    /**
     * 设置 贷款-借款人地区(市)
     * @param borrowerCity 贷款-借款人地区(市)
     */
    public void setBorrowerCity(String borrowerCity) {
        this.borrowerCity = borrowerCity == null ? null : borrowerCity.trim();
    }

    /**
     * 取得 贷款-基础资产投向分类
     * @return 贷款-基础资产投向分类
     */
    public String getAssetsInvest() {
        return assetsInvest;
    }

    /**
     * 设置 贷款-基础资产投向分类
     * @param assetsInvest 贷款-基础资产投向分类
     */
    public void setAssetsInvest(String assetsInvest) {
        this.assetsInvest = assetsInvest == null ? null : assetsInvest.trim();
    }

    /**
     * 取得 贷款-各比例
     * @return 贷款-各比例
     */
    public String getEachProportion() {
        return eachProportion;
    }

    /**
     * 设置 贷款-各比例
     * @param eachProportion 贷款-各比例
     */
    public void setEachProportion(String eachProportion) {
        this.eachProportion = eachProportion == null ? null : eachProportion.trim();
    }
}