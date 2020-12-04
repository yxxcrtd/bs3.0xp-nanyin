/*
 * 文件名：EtrInvestBondInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.validator.EtrInvestBondInfoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 债券投资委托dto
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see EtrInvestBondInfoDto
 * @since 1.0
 */
@PageId("EtrInvestBondInfo002")
@ValidLogic(targetClass = EtrInvestBondInfoValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrInvestBondInfoDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8503717126985211652L;
    
    /**
     * 计算标志
     */
    private String calflag;
    /**
     * 利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;
    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;
    /**
     * 单位利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 单位全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitFprice;

    /**
     * 本方券券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustOwnNum;

    /**
     * 融入券/卖空购回券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustIntegrateNum;
    /**
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 委托开始日Text
     */
    private String vdateText;
    /**
     * 委托截止日Text
     */
    private String mdateText;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 清算速度(Long)
     */
    private Long settleSpeed;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 投资人员信息DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
   
    /**
     * 接收状态
     */
    private String recStatus;
    /**
     * 资产二级分类（原资产一级分类，委托页面的债券一级分类，修改：王贵奇，2019/9/27）
     */
    private String assetType1;
    /**
     * 新增个性分类三、四。王贵奇，2019/9/27 开始
     * 个性分类三，对应资产三级分类
     */
    private String assetType10;
    /**
     * 个性分类四，对应资产四级分类
     */
    private String assetType11;
    /** 新增个性分类三、四。王贵奇，2019/9/27 结束**/
    /**
     * 指定交易员
     */
    private String trader;
    /**
     * 交易对手类型
     */
    private String counterType;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 证券托管户
     */
    private String secAcctId;

    /**
     * 选中的数据
     */
    private transient List<EtrInvestBondInfoDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 组合名称
     */
    private String portfolioName;
    

    /**
     * 金融产品全称
     */
    private String finprodName;


    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 最低成交金额
     */
    private BigDecimal minAmt;

    /**
     * 交易对手名称
     */
    private String counterIdName;


    /**
     * 资产本金（授信用）
     */
    private BigDecimal prinAmt;

    /**
     * 后台取为平台日（授信用）
     */
    private Date tradeDate;

    /**
     * 数量（授信用）
     */
    private BigDecimal shareAmt;

    /**
     * 组合T日可用现金，用于App显示
     * @return
     */
    private String appProAvailableCash;

    /**
     * 组合T+1日可用现金，用于App显示
     * @return
     */
    private String appProAvailableCashT1;

    /**
     * 资产可用持仓，用于App显示
     * @return
     */
    private String appAssetAvailablePosition;

    /**
     * 资产T+1日可用持仓，用于App显示
     * @return
     */
    private String appAssetAvailablePosition1;

    private String inputValue1;
    private String inputValue2;
    private String inputValue3;
    public String getAppProAvailableCash() {
        return appProAvailableCash;
    }

    public void setAppProAvailableCash(String appProAvailableCash) {
        this.appProAvailableCash = appProAvailableCash;
    }

    public String getAppProAvailableCashT1() {
        return appProAvailableCashT1;
    }

    public void setAppProAvailableCashT1(String appProAvailableCashT1) {
        this.appProAvailableCashT1 = appProAvailableCashT1;
    }

    public String getAppAssetAvailablePosition() {
        return appAssetAvailablePosition;
    }

    public void setAppAssetAvailablePosition(String appAssetAvailablePosition) {
        this.appAssetAvailablePosition = appAssetAvailablePosition;
    }

    public String getAppAssetAvailablePosition1() {
        return appAssetAvailablePosition1;
    }

    public void setAppAssetAvailablePosition1(String appAssetAvailablePosition1) {
        this.appAssetAvailablePosition1 = appAssetAvailablePosition1;
    }


    /**
     * 利率审批编号
     */
    private String rateApprovalCode;

    public String getRateApprovalCode() {
        return rateApprovalCode;
    }

    public void setRateApprovalCode(String rateApprovalCode) {
        this.rateApprovalCode = rateApprovalCode;
    }

    /**
     * 获取 counterIdName
     * 
     * @return counterIdName.
     */
    public String getCounterIdName() {
        return counterIdName;
    }

    /**
     * 设置 counterIdName
     * 
     * @param counterIdName counterIdName
     */
    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }

    /**
     * 取得 最低成交金额
     * 
     * @return 最低成交金额
     */
    public BigDecimal getMinAmt() {
        return minAmt;
    }

    /**
     * 设置 最低成交金额
     * 
     * @param minAmt 最低成交金额
     */
    public void setMinAmt(BigDecimal minAmt) {
        this.minAmt = minAmt;
    }

    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;
    
    /**
     * 交易完成度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal transactioncom;

    /**
     * 按钮id
     */
    private String elementId;
    
    /**
     * 行权收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exerYield;
    
    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal matureYield;
    
    /**
     * 清算速度
     */
    private String settleSpreed;
    
    /**
     * 分类6（行内一级类型）
     */
    private String bondType;
    /**
     * 投资处室
     */
    private String room;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    /**
     * 交割日期
     */
    private Date settleDate;
    /**
     * 收盘价
     */
    private BigDecimal closePrice;
    //add by lishuping 20191029 start
    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 序号
     */
    private String indexId;

    /**
     * 交易日历名称
     */
    private String calendarIdName;

    /**
     * 证券托管户名称
     */
    private String secManageAcctIdName;

    /**
     * 通道名称
     */
    private String chlIdName;

    /**
     * 通道可用现金
     */
    private BigDecimal channelAvailableCash;

    /**
     * 获取 indexId
     * 
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 indexId
     * 
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 settleSpreedName
     * 
     * @return settleSpreedName.
     */
    public String getSettleSpreedName() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpreed());
    }

    /**
     * 获取 tradeMarketName
     * 
     * @return tradeMarketName.
     */
    public String getTradeMarketName() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    /**
     * 取得 投资目的
     * 
     * @return 投资目的
     */
    public String getInvAimName() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
    }
    
    /**
     * 取得 交易平台
     * 
     * @return 交易平台
     */
    public String getTradePlatName() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易平台, super.getTradePlat());
    }

    /**
     * 获取 calendarIdName
     * 
     * @return calendarIdName.
     */
    public String getCalendarIdName() {
        return calendarIdName;
    }

    /**
     * 设置 calendarIdName
     * 
     * @param calendarIdName calendarIdName
     */
    public void setCalendarIdName(String calendarIdName) {
        this.calendarIdName = calendarIdName;
    }

    /**
     * 获取 secManageAcctIdName
     * 
     * @return secManageAcctIdName.
     */
    public String getSecManageAcctIdName() {
        return secManageAcctIdName;
    }

    /**
     * 设置 secManageAcctIdName
     * 
     * @param secManageAcctIdName secManageAcctIdName
     */
    public void setSecManageAcctIdName(String secManageAcctIdName) {
        this.secManageAcctIdName = secManageAcctIdName;
    }

    /**
     * 获取 chlIdName
     * 
     * @return chlIdName.
     */
    public String getChlIdName() {
        return chlIdName;
    }

    /**
     * 设置 chlIdName
     * 
     * @param chlIdName chlIdName
     */
    public void setChlIdName(String chlIdName) {
        this.chlIdName = chlIdName;
    }

    /**
     * 获取 channelAvailableCash
     * 
     * @return channelAvailableCash.
     */
    public BigDecimal getChannelAvailableCash() {
        return channelAvailableCash;
    }

    /**
     * 设置 channelAvailableCash
     * 
     * @param channelAvailableCash channelAvailableCash
     */
    public void setChannelAvailableCash(BigDecimal channelAvailableCash) {
        this.channelAvailableCash = channelAvailableCash;
    }

    /**
     * finprodAbbr
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    //add by lishuping 20191029 end




	/**
     * 获取 closePrice
     * @return closePrice.
     */
    public BigDecimal getClosePrice() {
		return closePrice;
	}
    
    /**
     * 设置 closePrice
     * @param closePrice closePrice
     */
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	/**
     * 获取 calflag
     * @return calflag.
     */
    public String getCalflag() {
        return calflag;
    }
    
    /**
     * 设置 calflag
     * @param calflag calflag
     */
    public void setCalflag(String calflag) {
        this.calflag = calflag;
    }








    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }







    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }







    /**
     * 获取 intAmt
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }






    
    /**
     * 设置 intAmt
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }






    
    /**
     * 获取 fpriceAmt
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }






    
    /**
     * 设置 fpriceAmt
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }






    
    /**
     * 获取 cpriceAmt
     * @return cpriceAmt.
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }






    
    /**
     * 设置 cpriceAmt
     * @param cpriceAmt cpriceAmt
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }






    
    /**
     * 获取 unitInt
     * @return unitInt.
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }






    
    /**
     * 设置 unitInt
     * @param unitInt unitInt
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }






    
    /**
     * 获取 unitFprice
     * @return unitFprice.
     */
    public BigDecimal getUnitFprice() {
        return unitFprice;
    }






    
    /**
     * 设置 unitFprice
     * @param unitFprice unitFprice
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }






    /**
     * 获取 riskTrialRetDto
     * @return riskTrialRetDto.
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return riskTrialRetDto;
    }





    
    /**
     * 设置 riskTrialRetDto
     * @param riskTrialRetDto riskTrialRetDto
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }





    /**
     * 获取 finvdate
     * @return finvdate.
     */
    public Date getFinvdate() {
        return finvdate;
    }




    
    /**
     * 设置 finvdate
     * @param finvdate finvdate
     */
    public void setFinvdate(Date finvdate) {
        this.finvdate = finvdate;
    }




    
    /**
     * 获取 finmdate
     * @return finmdate.
     */
    public Date getFinmdate() {
        return finmdate;
    }




    
    /**
     * 设置 finmdate
     * @param finmdate finmdate
     */
    public void setFinmdate(Date finmdate) {
        this.finmdate = finmdate;
    }




    /**
     * 获取 assetAvailablePosition
     * @return assetAvailablePosition.
     */
    public BigDecimal getAssetAvailablePosition() {
        return assetAvailablePosition;
    }



    
    /**
     * 设置 assetAvailablePosition
     * @param assetAvailablePosition assetAvailablePosition
     */
    public void setAssetAvailablePosition(BigDecimal assetAvailablePosition) {
        this.assetAvailablePosition = assetAvailablePosition;
    }



    /**
     * 获取 settleSpeed
     * @return settleSpeed.
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }


    
    /**
     * 设置 settleSpeed
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
    }


    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    
    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }



    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }



    /**
     * 获取 recStatus
     * @return recStatus.
     */
    public String getRecStatus() {
        return recStatus;
    }



    
    /**
     * 设置 recStatus
     * @param recStatus recStatus
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }






    /**
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }

    public String getAssetType1Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, getAssetType1());
    }
    
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    /**
     * 新增个性分类三、四getters和setters。王贵奇，2019/9/27 开始
     * 获取 assetType10
     * @return assetType10.
     */
    public String getAssetType10() {
        return assetType10;
    }
    
    public String getAssetType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, getAssetType10());
    }
    
    /**
     * 设置 assetType10
     * @param assetType10 assetType10
     */
    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }
    /**
     * 获取 assetType11
     * @return assetType11.
     */
    public String getAssetType11() {
        return assetType11;
    }
    public String getAssetType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, getAssetType11());
    }
    /**
     * 设置 assetType11
     * @param assetType11 assetType11
     */
    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }
    /** 新增个性分类三、四getters和setters。王贵奇，2019/9/27 结束 **/




    /**
     * 获取 etrInvestPersonInfo
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }




    
    /**
     * 设置 etrInvestPersonInfo
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
    }




    /**
     * 获取 trader
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }



    
    /**
     * 设置 trader
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }



    
    /**
     * 获取 closeWay
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }



    
    /**
     * 设置 closeWay
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }



    /**
     * 获取 bondType
     * @return bondType.
     */
    public String getBondType() {
        return bondType;
    }


    
    /**
     * 设置 bondType
     * @param bondType bondType
     */
    public void setBondType(String bondType) {
        this.bondType = bondType;
    }


    
    /**
     * 获取 room
     * @return room.
     */
    public String getRoom() {
        return room;
    }


    
    /**
     * 设置 room
     * @param room room
     */
    public void setRoom(String room) {
        this.room = room;
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrInvestBondInfoDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrInvestBondInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }


    /**
     * 获取 finprodType2
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

  
    /**
     * 设置 finprodType2
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    
    /**
     * 获取 finishAmt
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 finishAmt
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    /**
     * 获取 transactioncom
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }

    
    /**
     * 设置 transactioncom
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
    }


    
    /**
     * 获取 exerYield
     * @return exerYield.
     */
    @Rate(fieldName = "EtrInvestBondInfo002.exerYield", groups = {ValidateGroupEdit.class})
    public BigDecimal getExerYield() {
        return exerYield;
    }

    
    /**
     * 设置 exerYield
     * @param exerYield exerYield
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 获取 matureYield
     * @return matureYield.
     */
    @Rate(fieldName = "EtrInvestBondInfo002.matureYield", groups = {ValidateGroupEdit.class})
    public BigDecimal getMatureYield() {
        return matureYield;
    }

    /**
     * 设置 matureYield
     * @param matureYield matureYield
     */
    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }


    
    /**
     * 获取 settleSpreed
     * @return settleSpreed.
     */
    @NotEmpty(fieldName = "EtrInvestBondInfo002.settleSpreed", groups = {ValidateGroupEdit.class})
    public String getSettleSpreed() {
        return settleSpreed;
    }

    /**
     * 设置 settleSpreed
     * @param settleSpreed settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }
    
    /**
     * 
     * 取得组合代码
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId() {
        return super.getPortfolioId();
    }
    
    /**
     * 取得 委托开始日
     * @return 委托开始日
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate() {
        return super.getVdate();
    }
    
    /**
     * 取得 委托结束日
     * @return 委托结束日
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate() {
        return super.getMdate();
    }
    
    /**
     * 取得 币种
     * @return 币种
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }
    
    /**
     * 取得 交易方向
     * @return 交易方向
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.ps", groups = {ValidateGroupEdit.class})
    public String getPs() {
        return super.getPs();
    }
    
    /**
     * 取得 投资目的
     * @return 投资目的
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.invAim", groups = {ValidateGroupEdit.class})
    public String getInvAim() {
        return super.getInvAim();
    }
    /**
     * 
     * 交易市场
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.tradeMarketMode", groups = {ValidateGroupEdit.class})
    public String getTradeMarketMode(){
        return super.getTradeMarketMode();
    }
    /**
     * 取得 委托额度，交易金额或者数量
     * @return 委托额度，交易金额或者数量
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.entrustAmt", groups = {ValidateGroupEdit.class})
    @Amount(fieldName = "EtrInvestBondInfo002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustNum() {
        return super.getEntrustNum();
    }

    @Override
    // 交易所债一级市场时，交易平台非必填 delete by renping 20191105 start
//    @NotEmpty(fieldName = "EtrInvestBondInfo002.tradePlat", groups = {ValidateGroupEdit.class})
    // 交易所债一级市场时，交易平台非必填 delete by renping 20191105 end
    public String getTradePlat() {
        return super.getTradePlat();
    }

    
    @Override
    @Rate(fieldName = "EtrInvestBondInfo002.price", maxValue=100000000, groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice(){
        return super.getPrice();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.priceModel", groups = {ValidateGroupEdit.class})
    public String getPriceModel() {
        return super.getPriceModel();
    }
    
    /**
     * 取得 委托代码
     * @return 委托代码
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId() {
        return super.getEntrustId();
    }
    
    /**
     * 
     * 设置委托状态数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    /**
     * 
     * 设置币种数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());

    }
    /**
     * 
     * 设置交易方向数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }
    /**
     * 
     * 设置交易场所数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusTradeMarket(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }
    /**
     * 
     * 设置交易平台数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusTradePlat(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易平台, super.getTradePlat());
    }
    
    /**
     * 
     * 设置价格模式数据字典
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getPriceModelStr(){
        if (StringUtil.isEmpty(super.getPriceModel())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }

    /**
     * 获取 vdateText
     *
     * @rturn vdateText
     */
    public String getVdateText() {
        vdateText = DateUtil.format(this.getVdate());
        return this.vdateText;
    }
    /**
     * 获取 mdateText
     *
     * @rturn mdateText
     */
    public String getMdateText() {
        mdateText = DateUtil.format(this.getMdate());
        return this.mdateText;
    }

    /**
     * 获取 secAcctId
     *
     * @return secAcctId
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 secAcctId
     *
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 counterType
     *
     * @return counterType
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 counterType
     *
     * @param counterType counterType
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    /**
     * 获取 entrustOwnNum
     *
     * @return entrustOwnNum
     */
//    @NotEmpty(fieldName = "EtrInvestBondInfo002.entrustOwnNum", groups = {ValidateGroupEdit.class})
//    @Amount(fieldName = "EtrInvestBondInfo002.entrustOwnNum", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustOwnNum() {
        return entrustOwnNum;
    }

    /**
     * 设置 entrustOwnNum
     *
     * @param entrustOwnNum entrustOwnNum
     */
    public void setEntrustOwnNum(BigDecimal entrustOwnNum) {
        this.entrustOwnNum = entrustOwnNum;
    }

    /**
     * 获取 entrustIntegrateNum
     *
     * @return entrustIntegrateNum
     */
//    @NotEmpty(fieldName = "EtrInvestBondInfo002.entrustIntegrateNum", groups = {ValidateGroupEdit.class})
//    @Amount(fieldName = "EtrInvestBondInfo002.entrustIntegrateNum", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustIntegrateNum() {
        return entrustIntegrateNum;
    }

    /**
     * 设置 entrustIntegrateNum
     *
     * @param entrustIntegrateNum entrustIntegrateNum
     */
    public void setEntrustIntegrateNum(BigDecimal entrustIntegrateNum) {
        this.entrustIntegrateNum = entrustIntegrateNum;
    }

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public String getInputValue1() {
		return inputValue1;
	}

	public void setInputValue1(String inputValue1) {
		this.inputValue1 = inputValue1;
	}

	public String getInputValue2() {
		return inputValue2;
	}

	public void setInputValue2(String inputValue2) {
		this.inputValue2 = inputValue2;
	}

	public String getInputValue3() {
		return inputValue3;
	}

	public void setInputValue3(String inputValue3) {
		this.inputValue3 = inputValue3;
	}

}
