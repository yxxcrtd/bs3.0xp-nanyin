/*
 * 文件名：EtrBoutRepoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrboutrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrboutrepo.validator.EtrBoutRepoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月10日
 * @see EtrBoutRepoDto
 * @since 1.0
 */
@PageId("EtrBoutRepo002")
@ValidLogic(targetClass = EtrBoutRepoValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrBoutRepoDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1770266413189669438L;
    /**
     * 委托开始日Text
     */
    private String vdateText;
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
     * 委托截止日Text
     */
    private String mdateText;
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
     * 资产起息日
     */
    private Date finvdate;
    /**
     * 资产到期日
     */
    private Date finmdate;
    /**
     * 期限1
     */
    private BigDecimal termDays;
    /**
     * 期限2
     */
    private BigDecimal termDays2;
    /**
     * 添加质押券信息
     */
    private BussRepoSecDto bussRepoSec;
    /**
     * 首次净价
     */
    private BigDecimal firstprice;
    
    /**
     * 首次净价
     */
    private BigDecimal firstprice2;
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 证券托管户
     */
    private String securitiesCust;
    /**
     * 资金可用日
     */
    private Date availableDay;
    /**
     * 到期日
     */
    private Date mdateAdd;
    /**
     * 起息日
     */
    private Date vdateAdd;
    /**
     * 清算速度
     */
    private String settleSpreed;
    /**
     * 清算速度（long）
     */
    private Long settleSpeed;
    /**
     * 利率
     */
    private String rate;
    /**
     * 回购期限
     */
    private String repoTerm;
    /**
     * 回购期限2
     */
    private String repoTerm2;
    /**
     * 期限品种
     */
    private String termType;
    /**
     * 资产可用持仓
     */
    private BigDecimal assetAvailablePosition;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 人员信息业务组件DTO
     */
    @Valid
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 按钮id
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<EtrBoutRepoDto> selectedItem;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产一级分类
     */
    private String assetType1;
    /**
     * 金融产品全称
     */
    private String finprodName;
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
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
   // 委托列表新增字段 by 赵东东 20200111 start
    /**
     * 序号
     */
    private String indexId;
    /**
     * 证券账户名称
     */
    private String securitiesCustName;
    /**
     * 通道名称
     */
    private String chlIdName;
    /**
     * 交易日历名称
     */
    private String calendarIdName;
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
    
    private String inputValue1;
    private String inputValue2;
    private String inputValue3;
     /**
      * 
      * 获取交易对手名称
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
    public String getCounterIdName() {
        return counterIdName;
    }
    /**
     * 
     * 设置交易对手名称
     * <功能详细描述>
     * @param counterIdName
     * @see
     * @since 1.0
     */
    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }
    /**
      * 
               * 获取交易日历名称
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
    public String getCalendarIdName() {
        return calendarIdName;
    }
    /**
     * 
             * 设置交易日历名称
     * <功能详细描述>
     * @param calendarIdName
     * @see
     * @since 1.0
     */
    public void setCalendarIdName(String calendarIdName) {
        this.calendarIdName = calendarIdName;
    }
    /**
      * 
               * 获取通道名称
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
    public String getChlIdName() {
        return chlIdName;
    }
    /**
     * 
             * 设置通道名称
     * <功能详细描述>
     * @param chlIdName
     * @see
     * @since 1.0
     */
    public void setChlIdName(String chlIdName) {
        this.chlIdName = chlIdName;
    }
    /**
      * 
               * 获取证券账户名称
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
    public String getSecuritiesCustName() {
        return securitiesCustName;
    }
    /**
     * 
             * 设置证券账户名称
     * <功能详细描述>
     * @param securitiesCustName
     * @see
     * @since 1.0
     */
    public void setSecuritiesCustName(String securitiesCustName) {
        this.securitiesCustName = securitiesCustName;
    }
    /**
      * 
      * 获取序号
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
    public String getIndexId() {
        return indexId;
    }
     /**
      * 
      * 设置序号
      * <功能详细描述>
      * @param indexId
      * @see
      * @since 1.0
      */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
 // 委托列表新增字段 by 赵东东 20200111 end

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
     * 获取 repoTerm2
     * @return repoTerm2.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.repoTerm2", groups = {ValidateGroupEdit.class})
    public String getRepoTerm2() {
        return repoTerm2;
    }

    /**
     * 设置 repoTerm2
     * @param repoTerm2 repoTerm2
     */
    public void setRepoTerm2(String repoTerm2) {
        this.repoTerm2 = repoTerm2;
    }


    /**
     * 获取 termDays
     * @return termDays.
     */
    public BigDecimal getTermDays() {
        return termDays;
    }

    
    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
    }

    /**
     * 获取 termDays2
     * @return termDays2.
     */
    public BigDecimal getTermDays2() {
        return termDays2;
    }

    /**
     * 设置 termDays2
     * @param termDays2 termDays2
     */
    public void setTermDays2(BigDecimal termDays2) {
        this.termDays2 = termDays2;
    }


    /**
     * 获取 bussRepoSec
     * @return bussRepoSec.
     */
    public BussRepoSecDto getBussRepoSec() {
        return bussRepoSec;
    }

    /**
     * 设置 bussRepoSec
     * @param bussRepoSec bussRepoSec
     */
    public void setBussRepoSec(BussRepoSecDto bussRepoSec) {
        this.bussRepoSec = bussRepoSec;
    }


    
    /**
     * 获取 firstprice2
     * @return firstprice2.
     */
    //@NotEmpty(fieldName = "EtrBoutRepo002.firstprice2", groups = {ValidateGroupEdit.class})
    public BigDecimal getFirstprice2() {
        return firstprice2;
    }




    
    /**
     * 设置 firstprice2
     * @param firstprice2 firstprice2
     */
    public void setFirstprice2(BigDecimal firstprice2) {
        this.firstprice2 = firstprice2;
    }




    /**
     * 获取 firstprice
     * @return firstprice.
     */
    //@NotEmpty(fieldName = "EtrBoutRepo002.firstprice1", groups = {ValidateGroupEdit.class})
    public BigDecimal getFirstprice() {
        return firstprice;
    }


    
    /**
     * 设置 firstprice
     * @param firstprice firstprice
     */
    public void setFirstprice(BigDecimal firstprice) {
        this.firstprice = firstprice;
    }


    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.finprodId", groups = {ValidateGroupEdit.class})
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
     * 获取 securitiesCust
     * @return securitiesCust.
     */
    public String getSecuritiesCust() {
        return securitiesCust;
    }
    
    /**
     * 设置 securitiesCust
     * @param securitiesCust securitiesCust
     */
    public void setSecuritiesCust(String securitiesCust) {
        this.securitiesCust = securitiesCust;
    }
    
    /**
     * 获取 availableDay
     * @return availableDay.
     */
    public Date getAvailableDay() {
        return availableDay;
    }
    
    /**
     * 设置 availableDay
     * @param availableDay availableDay
     */
    public void setAvailableDay(Date availableDay) {
        this.availableDay = availableDay;
    }
    
    /**
     * 获取 mdateAdd
     * @return mdateAdd.
     */
    public Date getMdateAdd() {
        return mdateAdd;
    }
    
    /**
     * 设置 mdateAdd
     * @param mdateAdd mdateAdd
     */
    public void setMdateAdd(Date mdateAdd) {
        this.mdateAdd = mdateAdd;
    }
    
    /**
     * 获取 vdateAdd
     * @return vdateAdd.
     */
    public Date getVdateAdd() {
        return vdateAdd;
    }
    
    /**
     * 设置 vdateAdd
     * @param vdateAdd vdateAdd
     */
    public void setVdateAdd(Date vdateAdd) {
        this.vdateAdd = vdateAdd;
    }
    
    /**
     * 获取 settleSpreed
     * @return settleSpreed.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.settleSpreed", groups = {ValidateGroupEdit.class})
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
     * 获取 rate
     * @return rate.
     */
    public String getRate() {
        return rate;
    }
    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }
    
    /**
     * 获取 repoTerm
     * @return repoTerm.
     */
    @NotEmpty(fieldName = "EtrBoutRepo002.repoTerm1", groups = {ValidateGroupEdit.class})
    public String getRepoTerm() {
//        if(this.getTermDays() != null && this.getTermDays2() == null){
//            return this.getTermDays().toString();
//        }else if(this.getTermDays() != null && this.getTermDays2() != null){
//            return this.getTermDays().toString()+"-"+this.getTermDays2().toString();
//        }else{
            return repoTerm;
//        }
    }
    
    /**
     * 设置 repoTerm
     * @param repoTerm repoTerm
     */
    public void setRepoTerm(String repoTerm) {
        this.repoTerm = repoTerm;
    }
    
    /**
     * 获取 termType
     * @return termType.
     */
    public String getTermType() {
        return termType;
    }
    
    /**
     * 设置 termType
     * @param termType termType
     */
    public void setTermType(String termType) {
        this.termType = termType;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrBoutRepoDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrBoutRepoDto> selectedItem) {
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
     * 获取 assetType1
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }
    
    /**
     * 设置 assetType1
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
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
    
    @Override
    @PrimaryKey(value = Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.portfolioId", groups = {ValidateGroupEdit.class})
    public String getPortfolioId(){
        return super.getPortfolioId();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.vdate", groups = {ValidateGroupEdit.class})
    public Date getVdate(){
        return super.getVdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.mdate", groups = {ValidateGroupEdit.class})
    public Date getMdate(){
        return super.getMdate();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.ps", groups = {ValidateGroupEdit.class})
    public String getPs(){
        return super.getPs();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.entrustAmt", groups = {ValidateGroupEdit.class})
    public BigDecimal getEntrustAmt(){
        return super.getEntrustAmt();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket(){
        return super.getTradeMarket();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy(){
        return super.getCcy();
    }
    
    @Override
    //@NotEmpty(fieldName = "EtrBoutRepo002.price", groups = {ValidateGroupEdit.class})
    public BigDecimal getPrice(){
        return super.getPrice();
    }
    
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId(){
        return super.getCalendarId();
    }
    
    // 增加获取交易场所和清算速度数据字典名称方法 by zhaodongdong 20200110 end
    public String getTradeMarketStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }
    
    public String getSettleSpreedStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.清算速度, this.getSettleSpreed());
    }
    // 增加获取交易场所和清算速度数据字典名称方法 by zhaodongdong 20200110 end
    
    public String getBusCcy(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
    }
    
    public String getBusPs(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
    }
    
    public String getBusTermType(){
        return SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, this.getTermType());
    }
    
    public String getBusEntrustStatus(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }
    public String getPriceModelStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }
    @Override
    @NotEmpty(fieldName = "EtrBoutRepo002.priceModel", groups = {ValidateGroupEdit.class})
    public String getPriceModel() {
        return super.getPriceModel();
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
