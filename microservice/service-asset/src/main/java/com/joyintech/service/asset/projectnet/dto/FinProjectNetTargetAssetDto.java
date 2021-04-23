/*
 * 文件名：FinPorjectTargetAssetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.projectnet.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.asset.common.dto.BusiFinBondRatingInfoDto;
import com.joyintech.service.asset.common.dto.BussFinProductCreditDto;
import com.joyintech.service.asset.common.dto.BussFinRecommentDto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  项目类资产标的信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAssetDto
 * @since 1.0
 */
public class FinProjectNetTargetAssetDto extends FinProduct{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 选择数据
     */
    private List<FinProjectNetTargetAssetDto> selectedItem;
    
    /**
     * 出账编号
     */
    private String cashOutNo;
    /**
     * 实际授信主体
     */
    private String actualCreditProvid;
    
    /**
     * 源起息日 
     */
    private Date oldVdate;
    
    /**
     * 资产类别
     */
    private String type6;
    
    /**
     * 资产二级分类
     */
    private String type7;
    
    /**
     * 资产来源，行内、行外
     */
    private String assetSource;

    /**
     * 资产提供行，多选，逗号分隔
     */
    private String assetProvider;
    
    /**
     * 资产来源条线
     */
    private String assetSourceUnit;

    /**
     * 放款分行
     */
    private String loadBank;

    /**
     * 清算时效（工作日）
     */
    private Long settleDays;

    /**
     * 项目投向行业
     */
    private String projInvestIndustry;

    /**
     * 项目投向行业二级分类
     */
    private String projInvestIndustry2;
    
    /**
     * 收益率
     */
    private BigDecimal rate;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 买断利率
     */
    private BigDecimal buyOutRate;
    
    /**
     * 利率类型，固定利率、浮动利率
     */
    private String intType;
    
    /**
     * 资产附件类型编号--批复文件
     */
    private String assetAttachNo1;
    
    /**
     * 资产附件类型编号--Cms批复文件
     */
    private String assetAttachNo2;
    
    /**
     * 基础组件-标的金融产品
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    
    /**
     * 金融产品扩展(平安个性)
     */
    private PaFinProduct paFinProduct;
    

    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 批复流水号
     */
    private String pflsh;

    /**
     * 已占用额度，初始值为0
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hapCreditAmt;

    /**
     * 是否占用主体授信
     */
    private String creditFlag;

    /**
     * 组合entity
     */
    private PtlPortfolio ptlPortfolio;
    
    /**
     * 投资性质
     */
    private String investNature;

    // 项目类资产五级分类改造 add by renping 2019-07-25 start
    /**
     * 资产二级分类
     */
    private String type9;
    
    /**
     * 资产三级分类
     */
    private String type10;
    
    /**
     * 资产四级分类
     */
    private String type11;
    
    /**
     * 资产五级分类
     */
    private String type12;

    /**
     * 资产/负债类别
     */
    private String repAssetType;

    /**
     * 法审编号
     */
    private String trialNo;

    private String financierName;

    private String sponsorName;


    // 增加推荐方与推荐费率 add by renping 20191014 start
    /**
     * 推荐方
     */
    private String recommender;


    /**
     *  增信信息
     * */
    private BussFinProductCreditDto bussFinProductCreditDto;
    /**
     * 债券评级信息
     */
    private BusiFinBondRatingInfoDto busiFinBondRatingInfoDto;

    /**
     * 取得 推荐方
     * @return 推荐方
     */
    public String getRecommender() {
        return recommender;
    }

    /**
     * 设置 推荐方
     * @param recommender 推荐方
     */
    public void setRecommender(String recommender) {
        this.recommender = recommender == null ? null : recommender.trim();
    }
    /**
     * 资产推荐方名称
     */
    private String recommenderName;

    /**
     * 取得 资产推荐方名称
     * @return 资产推荐方名称
     */
    public String getRecommenderName() {
        return recommenderName;
    }

    /**
     * 设置 资产推荐方名称
     * @param recommenderName 资产推荐方名称
     */
    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommender == null ? null : recommenderName.trim();
    }

    /**
     * 资产推荐方及费率信息
     */
    private BussFinRecommentDto bussFinRecommentDto;

    /**
     * 获取 bussFinRecommentDto
     * 
     * @return bussFinRecommentDto.
     */
    public BussFinRecommentDto getBussFinRecommentDto() {
        return bussFinRecommentDto;
    }

    /**
     * 设置 bussFinRecommentDto
     * 
     * @param bussFinRecommentDto bussFinRecommentDto
     */
    public void setBussFinRecommentDto(BussFinRecommentDto bussFinRecommentDto) {
        this.bussFinRecommentDto = bussFinRecommentDto;
    }
    // 增加推荐方与推荐费率 add by renping 20191014 end
    /**
     * 获取 type9
     * 
     * @return type9.
     */
    public String getType9() {
        return type9;
    }

    /**
     * 设置 type9
     * 
     * @param type9
     *            type9
     */
    public void setType9(String type9) {
        this.type9 = type9;
    }

    /**
     * 获取 type10
     * 
     * @return type10.
     */
    public String getType10() {
        return type10;
    }

    /**
     * 设置 type10
     * 
     * @param type10
     *            type10
     */
    public void setType10(String type10) {
        this.type10 = type10;
    }

    /**
     * 获取 type11
     * 
     * @return type11.
     */
    public String getType11() {
        return type11;
    }

    /**
     * 设置 type11
     * 
     * @param type11
     *            type11
     */
    public void setType11(String type11) {
        this.type11 = type11;
    }

    /**
     * 获取 type12
     * 
     * @return type12.
     */
    public String getType12() {
        return type12;
    }

    /**
     * 设置 type12
     * 
     * @param type12
     *            type12
     */
    public void setType12(String type12) {
        this.type12 = type12;
    }

    /**
     * 获取 type9Name
     * 
     * @return type9Name.
     */
    public String getType9Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, this.getType9());
    }

    /**
     * 获取 type10Name
     * 
     * @return type10Name.
     */
    public String getType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, this.getType10());
    }

    /**
     * 获取 type11Name
     * 
     * @return type11Name.
     */
    public String getType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, this.getType11());
    }

    /**
     * 获取 Type12Name
     * 
     * @return Type12Name.
     */
    public String getType12Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产五级分类, this.getType12());
    }
    // 项目类资产五级分类改造 add by renping 2019-07-25 end
    /**
     * 获取 creditFlag
     * @return creditFlag.
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    
    /**
     * 设置 creditFlag
     * @param creditFlag creditFlag
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }
    
    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }

    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    
    
    /**
     * 获取 type7
     * @return type7.
     */
    public String getType7() {
        return type7;
    }


    
    /**
     * 设置 type7
     * @param type7 type7
     */
    public void setType7(String type7) {
        this.type7 = type7;
    }


    
    /**
     * 获取 assetSource
     * @return assetSource.
     */
    public String getAssetSource() {
        return assetSource;
    }


    
    /**
     * 设置 assetSource
     * @param assetSource assetSource
     */
    public void setAssetSource(String assetSource) {
        this.assetSource = assetSource;
    }


    
    /**
     * 获取 assetProvider
     * @return assetProvider.
     */
    public String getAssetProvider() {
        return assetProvider;
    }


    
    /**
     * 设置 assetProvider
     * @param assetProvider assetProvider
     */
    public void setAssetProvider(String assetProvider) {
        this.assetProvider = assetProvider;
    }


    
    /**
     * 获取 assetSourceUnit
     * @return assetSourceUnit.
     */
    public String getAssetSourceUnit() {
        return assetSourceUnit;
    }


    
    /**
     * 设置 assetSourceUnit
     * @param assetSourceUnit assetSourceUnit
     */
    public void setAssetSourceUnit(String assetSourceUnit) {
        this.assetSourceUnit = assetSourceUnit;
    }


    
    /**
     * 获取 loadBank
     * @return loadBank.
     */
    public String getLoadBank() {
        return loadBank;
    }


    
    /**
     * 设置 loadBank
     * @param loadBank loadBank
     */
    public void setLoadBank(String loadBank) {
        this.loadBank = loadBank;
    }


    
    /**
     * 获取 settleDays
     * @return settleDays.
     */
    public Long getSettleDays() {
        return settleDays;
    }


    
    /**
     * 设置 settleDays
     * @param settleDays settleDays
     */
    public void setSettleDays(Long settleDays) {
        this.settleDays = settleDays;
    }


    
    /**
     * 获取 projInvestIndustry
     * @return projInvestIndustry.
     */
    public String getProjInvestIndustry() {
        return projInvestIndustry;
    }


    
    /**
     * 设置 projInvestIndustry
     * @param projInvestIndustry projInvestIndustry
     */
    public void setProjInvestIndustry(String projInvestIndustry) {
        this.projInvestIndustry = projInvestIndustry;
    }


    
    /**
     * 获取 projInvestIndustry2
     * @return projInvestIndustry2.
     */
    public String getProjInvestIndustry2() {
        return projInvestIndustry2;
    }


    
    /**
     * 设置 projInvestIndustry2
     * @param projInvestIndustry2 projInvestIndustry2
     */
    public void setProjInvestIndustry2(String projInvestIndustry2) {
        this.projInvestIndustry2 = projInvestIndustry2;
    }


    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }


    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }




    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinProjectNetTargetAssetDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinProjectNetTargetAssetDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    
    /**
     * 获取 paFinProduct
     * @return paFinProduct.
     */
    public PaFinProduct getPaFinProduct() {
        return paFinProduct;
    }


    
    /**
     * 设置 paFinProduct
     * @param paFinProduct paFinProduct
     */
    public void setPaFinProduct(PaFinProduct paFinProduct) {
        this.paFinProduct = paFinProduct;
    }


    /**
     * 获取 oldVdate
     * @return oldVdate.
     */
    public Date getOldVdate() {
        return oldVdate;
    }


    
    /**
     * 设置 oldVdate
     * @param oldVdate oldVdate
     */
    public void setOldVdate(Date oldVdate) {
        this.oldVdate = oldVdate;
    }


    
    /**
     * 获取 buyOutRate
     * @return buyOutRate.
     */
    public BigDecimal getBuyOutRate() {
        return buyOutRate;
    }


    
    /**
     * 设置 buyOutRate
     * @param buyOutRate buyOutRate
     */
    public void setBuyOutRate(BigDecimal buyOutRate) {
        this.buyOutRate = buyOutRate;
    }


    
    /**
     * 获取 cashOutNo
     * @return cashOutNo.
     */
    public String getCashOutNo() {
        return cashOutNo;
    }


    
    /**
     * 设置 cashOutNo
     * @param cashOutNo cashOutNo
     */
    public void setCashOutNo(String cashOutNo) {
        this.cashOutNo = cashOutNo;
    }


    
    
    /**
     * 获取 actualCreditProvid
     * @return actualCreditProvid.
     */
    public String getActualCreditProvid() {
        return actualCreditProvid;
    }


    
    /**
     * 设置 actualCreditProvid
     * @param actualCreditProvid actualCreditProvid
     */
    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }

    /**
     * 获取 assetAttachNo1
     * @return assetAttachNo1.
     */
    public String getAssetAttachNo1() {
        return assetAttachNo1;
    }


    
    /**
     * 设置 assetAttachNo1
     * @param assetAttachNo1 assetAttachNo1
     */
    public void setAssetAttachNo1(String assetAttachNo1) {
        this.assetAttachNo1 = assetAttachNo1;
    }


    
    /**
     * 获取 assetAttachNo2
     * @return assetAttachNo2.
     */
    public String getAssetAttachNo2() {
        return assetAttachNo2;
    }


    
    /**
     * 设置 assetAttachNo2
     * @param assetAttachNo2 assetAttachNo2
     */
    public void setAssetAttachNo2(String assetAttachNo2) {
        this.assetAttachNo2 = assetAttachNo2;
    }


    
    /**
     * 获取 intType
     * @return intType.
     */
    public String getIntType() {
        return intType;
    }


    
    /**
     * 设置 intType
     * @param intType intType
     */
    public void setIntType(String intType) {
        this.intType = intType;
    }


    
    /**
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }


    
    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }


    
    /**
     * 获取 creditMstId
     * @return creditMstId.
     */
    public String getCreditMstId() {
        return creditMstId;
    }


    
    /**
     * 设置 creditMstId
     * @param creditMstId creditMstId
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId;
    }


    
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }


    
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    
    /**
     * 获取 pflsh
     * @return pflsh.
     */
    public String getPflsh() {
        return pflsh;
    }


    
    /**
     * 设置 pflsh
     * @param pflsh pflsh
     */
    public void setPflsh(String pflsh) {
        this.pflsh = pflsh;
    }


    
    /**
     * 获取 hapCreditAmt
     * @return hapCreditAmt.
     */
    public BigDecimal getHapCreditAmt() {
        return hapCreditAmt;
    }


    
    /**
     * 设置 hapCreditAmt
     * @param hapCreditAmt hapCreditAmt
     */
    public void setHapCreditAmt(BigDecimal hapCreditAmt) {
        this.hapCreditAmt = hapCreditAmt;
    }

    /**
     * 获取 investNature
     *
     * @return investNature
     */
    public String getInvestNature() {
        return investNature;
    }

	public PtlPortfolio getPtlPortfolio() {
		return ptlPortfolio;
	}


	public void setPtlPortfolio(PtlPortfolio ptlPortfolio) {
		this.ptlPortfolio = ptlPortfolio;
	}
    
    /**
     * 设置 investNature
     *
     * @param investNature investNature
     */
    public void setInvestNature(String investNature) {
        this.investNature = investNature;     
    }

    public String getRepAssetType() {
        return repAssetType;
    }

    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    public String getTrialNo() {
        return trialNo;
    }

    public void setTrialNo(String trialNo) {
        this.trialNo = trialNo;
    }

    public String getFinancierName() {
        return financierName;
    }

    public void setFinancierName(String financierName) {
        this.financierName = financierName;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public BussFinProductCreditDto getBussFinProductCreditDto() {
        return bussFinProductCreditDto;
    }

    public void setBussFinProductCreditDto(BussFinProductCreditDto bussFinProductCreditDto) {
        this.bussFinProductCreditDto = bussFinProductCreditDto;
    }

    public BusiFinBondRatingInfoDto getBusiFinBondRatingInfoDto() {
        return busiFinBondRatingInfoDto;
    }

    public void setBusiFinBondRatingInfoDto(BusiFinBondRatingInfoDto busiFinBondRatingInfoDto) {
        this.busiFinBondRatingInfoDto = busiFinBondRatingInfoDto;
    }
}
