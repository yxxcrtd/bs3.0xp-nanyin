/*
 * 文件名：ChannelAssetQueryDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.report.asset.plan.dto.BussRepAssetMngProdInfo;
import com.joyintech.tams.invest.asset.channel.validate.ChannelAssetValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 通道资产查询
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelAssetDto
 * @since 1.0
 */
@PageId("ChannelAsset002")
@ValidLogic(targetClass = ChannelAssetValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ChannelAssetDto extends FinProduct implements ITableRow,ProcessEntity{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3308020867788413818L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;

    /**
     * 选择数据
     */
    private List<ChannelAssetDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;
    
    
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 通道名称
     */
    private String secAcctName;

    /**
     * 持仓类型
     */
    private String holdingType;

    /**
     * 日期
     */
    private String cdate;
    
    /**
     * 资产一级类型
     */
    private String type8;
    
    /**
     * 资产二级类型
     */
    private String type6;
   
    /**
     * 持仓：持仓表中的数量
     */
    private String amount;
    
    /**
     * 划款方式
     */
    private String payType;
    
    /**
     * 资金账户
     */
    private String busiAcctNo;
    
    /**
     * 旧资金账户
     */
    private String oldBusiAcctNo;
    
    /**
     * 登记编号
     */
    private String prodRegistCode;
    
    /**
     * 管理人名称
     */
    private String managerName;
    
    /**
     * 托管人名称
     */
    private String trusteeName;
    
    /**
     * 基础组件-标的金融产品
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    /**
     * 费用dto
     */
    @Valid
    private BussFinProductFeeInfoDto bussFinProductFeeInfoDto;
    
    /**
     * 资产管理计划报送信息输入组件
     */
    @Valid
    private BussRepAssetMngProdInfo bussRepAssetMngProdInfo;
    
    /**
     * 金融产品账户
     */
    private StlFinProductAcct stlFinProductAcct;
    
    /**
     * 资产负债报送信息entity
     */
    private RepAssetDept repAssetDept;
    /**
     * 资产管理计划报送信息entity
     */
    private RepAssetMngPlan repAssetMngPlan;
    /**
     * 中债资产其他要素entity
     */
    private RepAssetOther repAssetOther;
    
    /**
     * 通道费用类型
     */
    private String feeName;
    
    /**
     * 通道费用收取方式
     */
    private String collectType;
    
    /**
     * 附件临时Id add by yanya 20190903
     */
    private String fjId;
    
    /**
     * 附件信息DTO
     */
    private List<FileCommonDto> fileCommonList;

    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
    /**
     * 增值税税率
     */
    private BigDecimal vatTaxRate;

    /**
     * 附加税税率
     */
    private BigDecimal adtTaxRate;

    /**
     * 获取 vatTaxRate
     * 
     * @return vatTaxRate.
     */
    public BigDecimal getVatTaxRate() {
        return vatTaxRate;
    }

    /**
     * 设置 vatTaxRate
     * 
     * @param vatTaxRate vatTaxRate
     */
    public void setVatTaxRate(BigDecimal vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
    }

    /**
     * 获取 adtTaxRate
     * 
     * @return adtTaxRate.
     */
    public BigDecimal getAdtTaxRate() {
        return adtTaxRate;
    }

    /**
     * 设置 adtTaxRate
     * 
     * @param adtTaxRate adtTaxRate
     */
    public void setAdtTaxRate(BigDecimal adtTaxRate) {
        this.adtTaxRate = adtTaxRate;
    }
    // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end

    /**
     * 市场代码
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName="ChannelAsset002.finprodId",groups = {ValidateGroupEdit.class})
    @PrimaryKey(Field.PK_1)
    public String getFinprodMarketId(){
        return super.getFinprodMarketId();
    }
    
    /**
     * 资产名称
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "ChannelAsset002.finprodName", max = 200, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ChannelAsset002.finprodName", groups = {ValidateGroupEdit.class})
    public String getFinprodName(){
        return super.getFinprodName();
    }
    
    /**
     * 起息日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "ChannelAsset002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    /**
     * 到期日
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "ChannelAsset002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    /**
     * 管理人
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "ChannelAsset002.manager", groups = {ValidateGroupEdit.class})
    public String getManager(){
        return super.getManager();
    }
    
    
    /**
     * 托管人
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "ChannelAsset002.trusteeId", groups = {ValidateGroupEdit.class})
    public String getTrusteeId(){
        return super.getTrusteeId();
    }
    
    /**
     * 交易日历
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "ChannelAsset002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId(){
        return super.getCalendarId();
    }
    
    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    
    /**
     * 获取 amount
     * @return amount.
     */
    public String getAmount() {
        return amount;
    }
    
    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    /**
     * 获取 type8
     * @return type8.
     */
    public String getType8() {
        return type8;
    }
    
    /**
     * 设置 type8
     * @param type8 type8
     */
    public void setType8(String type8) {
        this.type8 = type8;
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
     * 获取 secAcctName
     * @return secAcctName.
     */
    public String getSecAcctName() {
        return secAcctName;
    }


    /**
     * 设置 secAcctName
     * @param secAcctName secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }
    /**
     * 资产类型查询
     * @return 资产类型
     * @return
     * @see
     * @since 1.0
     */
    public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getType8());
    }
    /**
     * 
     * 资产一级类型查询
     * @return 资产一级类型
     * @see
     * @since 1.0
     */
    public String getAssetSecTypeStr(){
        if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_基金.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_存款.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_计划类资产_净值型.getValue())
            || StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_计划类资产_利率型.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_项目类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_质押式回购.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.回购类型, this.getType6());
        }
        else if(StringUtil.equals(this.getType8(), DictCodeList.平安资产类型_拆借.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, this.getType6());
        }
        return null;
    }


    


    
    
    /**
     * 获取 payType
     * @return payType.
     */
    @NotEmpty(fieldName = "ChannelAsset002.payType", groups = {ValidateGroupEdit.class})
    public String getPayType() {
        return payType;
    }


    
    /**
     * 设置 payType
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }


    /**
     * 获取 busiAcctNo
     * @return busiAcctNo.
     */
    @NotEmpty(fieldName = "ChannelAsset002.busiAcctNo", groups = {ValidateGroupEdit.class})
    public String getBusiAcctNo() {
        return busiAcctNo;
    }


    
    /**
     * 设置 busiAcctNo
     * @param busiAcctNo busiAcctNo
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
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
    public List<ChannelAssetDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ChannelAssetDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 prodRegistCode
     * @return prodRegistCode.
     */
    @NotEmpty(fieldName = "ChannelAsset002.prodRegistCode", groups = {ValidateGroupEdit.class})
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    
    /**
     * 设置 prodRegistCode
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
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
     * 获取 bussFinProductFeeInfoDto
     * @return bussFinProductFeeInfoDto.
     */
    public BussFinProductFeeInfoDto getBussFinProductFeeInfoDto() {
        return bussFinProductFeeInfoDto;
    }

    
    /**
     * 设置 bussFinProductFeeInfoDto
     * @param bussFinProductFeeInfoDto bussFinProductFeeInfoDto
     */
    public void setBussFinProductFeeInfoDto(BussFinProductFeeInfoDto bussFinProductFeeInfoDto) {
        this.bussFinProductFeeInfoDto = bussFinProductFeeInfoDto;
    }

    
    /**
     * 获取 bussRepAssetMngProdInfo
     * @return bussRepAssetMngProdInfo.
     */
    public BussRepAssetMngProdInfo getBussRepAssetMngProdInfo() {
        return bussRepAssetMngProdInfo;
    }

    
    /**
     * 设置 bussRepAssetMngProdInfo
     * @param bussRepAssetMngProdInfo bussRepAssetMngProdInfo
     */
    public void setBussRepAssetMngProdInfo(BussRepAssetMngProdInfo bussRepAssetMngProdInfo) {
        this.bussRepAssetMngProdInfo = bussRepAssetMngProdInfo;
    }

    
    /**
     * 获取 stlFinProductAcct
     * @return stlFinProductAcct.
     */
    public StlFinProductAcct getStlFinProductAcct() {
        return stlFinProductAcct;
    }

    
    /**
     * 设置 stlFinProductAcct
     * @param stlFinProductAcct stlFinProductAcct
     */
    public void setStlFinProductAcct(StlFinProductAcct stlFinProductAcct) {
        this.stlFinProductAcct = stlFinProductAcct;
    }

    
    /**
     * 获取 oldBusiAcctNo
     * @return oldBusiAcctNo.
     */
    public String getOldBusiAcctNo() {
        return oldBusiAcctNo;
    }

    
    /**
     * 设置 oldBusiAcctNo
     * @param oldBusiAcctNo oldBusiAcctNo
     */
    public void setOldBusiAcctNo(String oldBusiAcctNo) {
        this.oldBusiAcctNo = oldBusiAcctNo;
    }

    
    /**
     * 获取 repAssetDept
     * @return repAssetDept.
     */
    public RepAssetDept getRepAssetDept() {
        return repAssetDept;
    }

    
    /**
     * 设置 repAssetDept
     * @param repAssetDept repAssetDept
     */
    public void setRepAssetDept(RepAssetDept repAssetDept) {
        this.repAssetDept = repAssetDept;
    }

    
    /**
     * 获取 repAssetMngPlan
     * @return repAssetMngPlan.
     */
    public RepAssetMngPlan getRepAssetMngPlan() {
        return repAssetMngPlan;
    }

    
    /**
     * 设置 repAssetMngPlan
     * @param repAssetMngPlan repAssetMngPlan
     */
    public void setRepAssetMngPlan(RepAssetMngPlan repAssetMngPlan) {
        this.repAssetMngPlan = repAssetMngPlan;
    }

    
    /**
     * 获取 managerName
     * @return managerName.
     */
    public String getManagerName() {
        return managerName;
    }

    
    /**
     * 设置 managerName
     * @param managerName managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    
    /**
     * 获取 trusteeName
     * @return trusteeName.
     */
    public String getTrusteeName() {
        return trusteeName;
    }

    
    /**
     * 设置 trusteeName
     * @param trusteeName trusteeName
     */
    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    
    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }

    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }
    
    public String getVdateStr(){
        return DateUtil.format(super.getVdate());
    }
    
    public String getMdateStr(){
        return DateUtil.format(super.getMdate());
    }

    /**
     * 获取 holdingTypeString
     *
     * @return holdingType
     */
    public String getHoldingTypeString() {
        if (StringUtil.isNotEmpty(holdingType)) {
            return SysDataUtil.getNameFromDataDict(DictCode.持仓类型, holdingType);
        }
        return holdingType;
    }

    /**
     * 获取 holdingType
     *
     * @return holdingType
     */
    public String getHoldingType() {
        return holdingType;
    }

    /**
     * 设置 holdingType
     *
     * @param holdingType holdingType
     */
    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }

    /**
     * 获取 cdate
     *
     * @return cdate
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 cdate
     *
     * @param cdate cdate
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

	public String getFjId() {
		return fjId;
	}

	public void setFjId(String fjId) {
		this.fjId = fjId;
	}

	public List<FileCommonDto> getFileCommonList() {
		return fileCommonList;
	}

	public void setFileCommonList(List<FileCommonDto> fileCommonList) {
		this.fileCommonList = fileCommonList;
	}

	@NotEmpty(fieldName="ChannelAsset002.collectType",groups = {ValidateGroupEdit.class})
	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public RepAssetOther getRepAssetOther() {
		return repAssetOther;
	}

	public void setRepAssetOther(RepAssetOther repAssetOther) {
		this.repAssetOther = repAssetOther;
	}
    
}
