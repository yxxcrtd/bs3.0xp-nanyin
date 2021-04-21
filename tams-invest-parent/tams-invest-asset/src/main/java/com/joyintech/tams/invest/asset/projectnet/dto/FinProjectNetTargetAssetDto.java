/*
 * 文件名：FinPorjectTargetAssetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.asset.projectnet.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PaFinProduct;
import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRate;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.asset.dto.BussFinRecommentDto;
import com.joyintech.tams.component.business.project.dto.BusiFinProjectRatingInfoDto;
import com.joyintech.tams.component.business.project.dto.BussFinProductCreditDto;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepOtherDto;
import com.joyintech.tams.invest.asset.projectnet.validator.FinProjectNetTargetAssetValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 *  权益类资产
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月24日
 * @see FinProjectNetTargetAssetDto
 * @since 1.0
 */
@PageId("FinProjectNetTargetAsset002")
@ValidLogic(targetClass = FinProjectNetTargetAssetValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class FinProjectNetTargetAssetDto extends FinProduct implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 选择数据
     */
    private List<FinProjectNetTargetAssetDto> selectedItem;

    /**
     * 基础组件-标的金融产品
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;

    /**
     * 参与方信息
     */
    @Valid
    private BussParticipantInfoDto bussParticipantInfoDto;



    /**
     * 附件信息DTO
     */
    private List<UploadFile> fileArray;


    /**
     * 法审编号
     */
    private String trialNo;

    /**
     * 金融产品扩展(平安个性)
     */
    private PaFinProduct paFinProduct;

    /**
     * 金融产品买断利率(平安个性)
     */
    private PaFinProductBuyoutRate paFinProductBuyoutRate;
    /**
     * 要素id
     */
    private String elementId;

    /**
     * 投资性质
     */
    private String investNature;

    /**
     * 出账编号
     *//*
    private String cashOutNo;  */


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

    /* *//**
     * 资产附件类型编号--批复文件
     *//*
    private String assetAttachNo1;

    *//**
     * 资产附件类型编号--Cms批复文件
     *//*
    private String assetAttachNo2;*/
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
     * 实际授信主体
     */
    //private String actualCreditProvid;
    /**
     * 是否占用主体授信
     */
    private String creditFlag;

    /**
     * 组合entity
     */
    private PtlPortfolio ptlPortfolio;

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

    private String financierName;

    private String sponsorName;

    /**
     * 中债报送新增
     */
    private BaseRepOtherDto baseRepOtherDto;

    /**
     *  增信信息
     * */
    @Valid
    private BussFinProductCreditDto bussFinProductCreditDto;

    @Valid
    private BusiFinProjectRatingInfoDto busiFinBondRatingInfoDto;

    // 增加推荐方与推荐费率 add by renping 20191014 start
    /**
     * 推荐方
     */
    private String recommender;


    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系方式
     */
    private String contactWay;

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
    @Valid
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
    @NotEmpty(fieldName = "FinProjectNetTargetAsset001.type9" ,groups = {ValidateGroupEdit.class})
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
    /*@NotEmpty(fieldName = "FinProjectTargetAsset002.type10" ,groups = {ValidateGroupEdit.class})*/
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
//    @NotEmpty(fieldName = "FinProjectTargetAsset002.type11" ,groups = {ValidateGroupEdit.class})
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
//    @NotEmpty(fieldName = "FinProjectTargetAsset002.type12" ,groups = {ValidateGroupEdit.class})
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
     * 金融产品代码
     */

    public String getFinprodId(){
        return super.getFinprodId();
    }

    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodMarketId(){
        return super.getFinprodMarketId();
    }
    /**
     * 资产名称
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "FinProjectTargetAsset002.finprodName", max = 100, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinProjectTargetAsset002.finprodName", groups = {ValidateGroupEdit.class})
    public String getFinprodName(){
        return super.getFinprodName();
    }

    @Override
    @Amount(fieldName = "FinProjectTargetAsset002.issueAmt", maxIntBit = 16, maxDecBit = 2,groups = {
            ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinProjectTargetAsset002.issueAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getIssueAmt(){
        return super.getIssueAmt();
    }

    /**
     * 本金收益特征，保本、非保本
     */
    public String getCapiIncomeFeature(){
        return super.getCapiIncomeFeature();
    }

    @NotEmpty(fieldName = "FinProjectTargetAsset002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }

    @NotEmpty(fieldName = "FinProjectNetTargetAsset002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    @NotEmpty(fieldName = "FinProjectNetTargetAsset001.mdate2", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    /**
     * 交易日历
     * {@inheritDoc}
     */
    @NotEmpty(fieldName = "FinProjectTargetAsset002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId(){
        return super.getCalendarId();
    }
    @NotEmpty(fieldName = "FinProjectTargetAsset002.financier",groups={ValidateGroupEdit.class})
    public String getFinancier() {
        return super.getFinancier();
    }
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }



    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }



    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }



    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
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
     * 获取 paFinProductBuyoutRate
     * @return paFinProductBuyoutRate.
     */
    public PaFinProductBuyoutRate getPaFinProductBuyoutRate() {
        return paFinProductBuyoutRate;
    }


    /**
     * 设置 paFinProductBuyoutRate
     * @param paFinProductBuyoutRate paFinProductBuyoutRate
     */
    public void setPaFinProductBuyoutRate(PaFinProductBuyoutRate paFinProductBuyoutRate) {
        this.paFinProductBuyoutRate = paFinProductBuyoutRate;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }



    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * 获取 type6
     * @return type6.
     */
    // 项目类资产五级分类改造 delete by renping 2019-08-09 start
//    @NotEmpty(fieldName = "FinProjectTargetAsset002.type6" ,groups = {ValidateGroupEdit.class})
    // 项目类资产五级分类改造 delete by renping 2019-08-09 end
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

    @Override
    @Length(fieldName = "FinProjectTargetAsset002.remark", max = 1000, groups = {ValidateGroupEdit.class})
    public String getRemark(){
        return super.getRemark();
    }

    /**
     * 获取 settleDays
     * @return settleDays.
     */
    @Length(fieldName = "FinProjectTargetAsset002.settleDays", max = 8, groups = {ValidateGroupEdit.class})
    /*@NotEmpty(fieldName = "FinProjectTargetAsset002.settleDays", groups = {ValidateGroupEdit.class})*/
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
     * 获取 buyOutRate
     * @return buyOutRate.
     */
    //@NotEmpty(fieldName = "FinProjectTargetAsset002.buyOutRate", groups = {ValidateGroupEdit.class})
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
     * 币种name
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getCcyName(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }

    /**
     * 计息基础name
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBasisName(){
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.getBasis());
    }

    /**
     * 资产类别
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getType6Name(){
        return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getType6());
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
     * 获取 bussParticipantInfoDto
     * @return bussParticipantInfoDto.
     */
    public BussParticipantInfoDto getBussParticipantInfoDto() {
        return bussParticipantInfoDto;
    }


    /**
     * 设置 bussParticipantInfoDto
     * @param bussParticipantInfoDto bussParticipantInfoDto
     */
    public void setBussParticipantInfoDto(BussParticipantInfoDto bussParticipantInfoDto) {
        this.bussParticipantInfoDto = bussParticipantInfoDto;
    }





    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }



    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
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

    public String getRateTypeName(){
        return SysDataUtil.getNameFromDataDict(DictCode.利率类型, this.getIntType());
    }

    public String getVdateStr(){
        return DateUtil.format(this.getVdate());
    }

    public String getMdateStr(){
        return DateUtil.format(this.getMdate());
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


    public PtlPortfolio getPtlPortfolio() {
        return ptlPortfolio;
    }
    /**
     * 获取 investNature
     *
     * @return investNature
     */
    public String getInvestNature() {
        return investNature;
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

    //    @NotEmpty(fieldName = "BussRepEquityInfo.repAssetType", groups = {ValidateGroupEdit.class})
    public String getRepAssetType() {
        return repAssetType;
    }

    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }

    @Override
    @Length(fieldName = "FinProjectTargetAsset002.contractNo", max = 400, groups = {ValidateGroupEdit.class})
//    @NotEmpty(fieldName = "FinProjectTargetAsset002.contractNo", groups = {ValidateGroupEdit.class})
    public String getContractNo(){
        return super.getContractNo();
    }

    @Length(fieldName = "FinProjectTargetAsset002.trialNo", max = 100, groups = {ValidateGroupEdit.class})
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

    public BusiFinProjectRatingInfoDto getBusiFinBondRatingInfoDto() {
        return busiFinBondRatingInfoDto;
    }

    public void setBusiFinBondRatingInfoDto(BusiFinProjectRatingInfoDto busiFinBondRatingInfoDto) {
        this.busiFinBondRatingInfoDto = busiFinBondRatingInfoDto;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }
}
