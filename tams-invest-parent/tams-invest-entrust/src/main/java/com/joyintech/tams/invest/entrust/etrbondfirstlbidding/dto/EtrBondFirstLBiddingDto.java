/*
 * 文件名：EtrBondFirstLBidDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
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
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.validator.EtrBondFirstLBiddingDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 债券一级投标委托dto 〈功能详细描述〉
 * 
 * @author yangjm
 * @version 1.0 2019年5月6日
 * @see EtrBondFirstLBiddingDto
 * @since 1.0
 */
@PageId("EtrBondFirstLBidding002")
@ValidLogic(targetClass = EtrBondFirstLBiddingDtoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class EtrBondFirstLBiddingDto extends EtrEntrust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7300212795949457812L;
    /**
     * 控件Id
     */
    private String elementId;
    // by zhaodongdong 20191116 start 
    /**
     * 一级新债日历进入委托界面的标识
     */
     private String mId;
     /**
      * 获取mId
      * @return
      */
    public String getmId() {
		return mId;
	}
    /**
     * 设置mId
     * @param mId
     */
	public void setmId(String mId) {
		this.mId = mId;
	}
	// by zhaodongdong 20191116 end
	/**
     * 资产二级分类（原资产一级分类，委托页面的债券一级分类，修改：王贵奇，2019/9/27）
     */
    private String assetType1;
    /* add by zhangpuyu 2019/11/15 start */
    /**
     * 投标日Text
     */
    private String entrustDateText;
    public String getEntrustDateText() {
        entrustDateText = DateUtil.format(this.getEntrustDate());
        return this.entrustDateText;
    }
    /**
     * 委托开始日Text
     */
    private String vdateText;
    /**
     * 委托截止日Text
     */
    private String mdateText;

    /**
     * 缴款日期
     */
    private Date paymentDate;

    /**
     * 上市日期
     */
    private Date listingDate;

    /**
     * 主承销商
     */
    @NotEmpty(fieldName = "EtrBondFirstLBidding002.mainConsignee", groups = {ValidateGroupEdit.class})
    private String mainConsignee;

    /**
     * 担保人
     */
    private String warrantor;

    /**
     * 投标区间%
     */
    private String biddingRange;
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
    /* add by zhangpuyu 2019/11/15 end */
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
     * 预计委托可用现金
     */
    private BigDecimal expectAvailableCash;
    
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
    * 中标状态
    */
    private String isWin;
    
    /**
     * 指定交易员
     */
    private String trader;
    
    /**
     * 委托关闭方式
     */
    private String CloseWay;
    
    /**
     * 选中的数据
     */
    private transient List<EtrBondFirstLBiddingDto> selectedItem;
   
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
     * 缴款方式
     */
    private String etrPayType;
    
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    
    /**
     * 资产起息日
     */
    private Date finvdate;
    
    /**
     * 资产到期日
     */
    private Date finmdate;
   
    /**
     * 投标券面总额
     */
    private BigDecimal bidPriceTotal;
    
    /**
     * 中标券面总额
     */
    private BigDecimal winPriceTotal;
    
    /**
     * 投标标位
     */
    private String bidPrice;
    
    /**
     * 投标券面 
     */
    private BigDecimal bidPrinAmt;
    /**
     * 投标标位表dto
     */
    private List<EtrEntrustBidPrice> bidPriceList;
    
    /**
     * 中标标位
     */
    private BigDecimal winPrice;

    /**
     * 中标标位名称
     */
    private String winPriceStr;
    
    /**
     * 中标券面
     */
    private BigDecimal winPrinAmt;

    //add by lishuping 20191030 start
    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 最低成交金额
     */
    private BigDecimal dealAmount;

    /**
     * 指令可用现金
     */
    private BigDecimal etrAvailableCash;

    /**
     * 指令可用持仓
     */
    private BigDecimal etrAvailablePosition;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 序号
     */
    private String indexId;

    /**
     * 交易场所名称
     */
    private String tradeMarketName;

    /**
     * 投资目的名称
     */
    private String invAimName;

    /**
     * 币种名称
     */
    private String ccyName;

    /**
     * 资产二级分类名称
     */
    private String assetType1Name;

    /**
     * 资产三级分类名称
     */
    private String assetType10Name;

    /**
     * 资产四级分类名称
     */
    private String assetType11Name;

    /**
     * 缴款方式名称
     */
    private String etrPayTypeName;

    /**
     * 交易对手名称
     */
    private String counterIdName;
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
    //add by lishuping 20191030 end
    
    /**
     * 获取 assetType1
     * @return assetType1.
     */
    @NotEmpty(fieldName = "EtrInvestBondInfo001.type9", groups = {ValidateGroupEdit.class})
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
     * 新增个性分类三、四getters和setters。王贵奇，2019/9/27 开始
     * 获取 assetType10
     * @return assetType10.
     */
    @NotEmpty(fieldName = "EtrInvestBondInfo001.type10", groups = {ValidateGroupEdit.class})
    public String getAssetType10() {
        return assetType10;
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
    /**
     * 设置 assetType11
     * @param assetType11 assetType11
     */
    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }
    /** 新增个性分类三、四getters和setters。王贵奇，2019/9/27 结束 **/
    
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
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "EtrInvestAllInfo001.finprodId", groups = {ValidateGroupEdit.class})
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrBondFirstLBiddingDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrBondFirstLBiddingDto> selectedItem) {
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
     * 获取 bidDate
     * @return bidDate.
     */
    @Override
    @NotEmpty(fieldName = "EtrBondFirstLBidding002.bidDate", groups = {ValidateGroupEdit.class})
     public Date getEntrustDate() {
        return super.getEntrustDate();
    }
    
    /**
     * 获取 etrPayType
     * @return etrPayType.
     */
    @NotEmpty(fieldName = "EtrBondFirstLBidding002.payType", groups = {ValidateGroupEdit.class})
    public String getEtrPayType() {
        return etrPayType;
    }
    /**
     * 设置 etrPayType
     * @param etrPayType etrPayType
     */
    public void setEtrPayType(String etrPayType) {
        this.etrPayType = etrPayType;
    }
    /**
     * 获取 expectAvailableCash
     * @return expectAvailableCash.
     */
    public BigDecimal getExpectAvailableCash() {
        return expectAvailableCash;
    }
    /**
     * 设置 expectAvailableCash
     * @param expectAvailableCash expectAvailableCash
     */
    public void setExpectAvailableCash(BigDecimal expectAvailableCash) {
        this.expectAvailableCash = expectAvailableCash;
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
     * 取得 投资目的
     * @return 投资目的
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestBondInfo002.invAim", groups = {ValidateGroupEdit.class})
    public String getInvAim() {
        return super.getInvAim();
    }
    /**
     * 取得 交易场所
     * @return 交易场所
     */
    @Override
    @NotEmpty(fieldName = "EtrInvestAllInfo001.tradeMarket", groups = {ValidateGroupEdit.class})
    public String getTradeMarket() {
        return super.getTradeMarket();
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
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.委托状态, super.getEntrustStatus());
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
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.币种, super.getCcy());

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
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易场所, super.getTradeMarket());
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
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易平台, super.getTradePlat());
    }
    
    /**
     * 获取 bidPriceTotal
     * @return bidPriceTotal.
     */
    public BigDecimal getBidPriceTotal() {
        return bidPriceTotal;
    }
    
    /**
     * 设置 bidPriceTotal
     * @param bidPriceTotal bidPriceTotal
     */
    public void setBidPriceTotal(BigDecimal bidPriceTotal) {
        this.bidPriceTotal = bidPriceTotal;
    }
    
    /**
     * 获取 winPriceTotal
     * @return winPriceTotal.
     */
    public BigDecimal getWinPriceTotal() {
        return winPriceTotal;
    }
    
    /**
     * 设置 winPriceTotal
     * @param winPriceTotal winPriceTotal
     */
    public void setWinPriceTotal(BigDecimal winPriceTotal) {
        this.winPriceTotal = winPriceTotal;
    }
    
    /**
     * 设置中标状态数据字典
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getBusIsWin() {
        if (!StringUtil.isEmpty(this.getIsWin())) {
            return SysDataUtil.getNameFromDataDict(BussinessDictCode.中标状态, this.getIsWin());
        }
        return null;
    }
    
    /**
     * 获取 isWin
     * @return isWin.
     */
    public String getIsWin() {
        return isWin;
    }
    
    /**
     * 设置 isWin
     * @param isWin isWin
     */
    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }
    
    /**
     * 获取 bidPrice
     * @return bidPrice.
     */
    public String getBidPrice() {
        return bidPrice;
    }
    
    /**
     * 设置 bidPrice
     * @param bidPrice bidPrice
     */
    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }
    
    /**
     * 获取 bidPrinAmt
     * @return bidPrinAmt.
     */
    public BigDecimal getBidPrinAmt() {
        return bidPrinAmt;
    }
    
    /**
     * 设置 bidPrinAmt
     * @param bidPrinAmt bidPrinAmt
     */
    public void setBidPrinAmt(BigDecimal bidPrinAmt) {
        this.bidPrinAmt = bidPrinAmt;
    }
    
    /**
     * 获取 winPrice
     * @return winPrice.
     */
    public BigDecimal getWinPrice() {
        return winPrice;
    }
    
    /**
     * 设置 winPrice
     * @param winPrice winPrice
     */
    public void setWinPrice(BigDecimal winPrice) {
        this.winPrice = winPrice;
    }
    /**
     * 获取 winPrinAmt
     * @return winPrinAmt.
     */
    public BigDecimal getWinPrinAmt() {
        return winPrinAmt;
    }
    /**
     * 设置 winPrinAmt
     * @param winPrinAmt winPrinAmt
     */
    public void setWinPrinAmt(BigDecimal winPrinAmt) {
        this.winPrinAmt = winPrinAmt;
    }
    
    /**
     * 缴款方式数据字典
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getBusEtrPayType() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.缴款方式, this.getEtrPayType());
    }
    /**
     * 获取 bidPriceList
     * @return bidPriceList.
     */
    public List<EtrEntrustBidPrice> getBidPriceList() {
        return bidPriceList;
    }
    /**
     * 设置 bidPriceList
     * @param bidPriceList bidPriceList
     */
    public void setBidPriceList(List<EtrEntrustBidPrice> bidPriceList) {
        this.bidPriceList = bidPriceList;
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
     * 获取 winPriceStr
     * @return winPriceStr.
     */
    public String getWinPriceStr() {
        return winPriceStr;
    }
    /**
     * 设置 winPriceStr
     * @param winPriceStr winPriceStr
     */
    public void setWinPriceStr(String winPriceStr) {
        this.winPriceStr = winPriceStr;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public BigDecimal getEtrAvailableCash() {
        return etrAvailableCash;
    }

    public void setEtrAvailableCash(BigDecimal etrAvailableCash) {
        this.etrAvailableCash = etrAvailableCash;
    }

    public BigDecimal getEtrAvailablePosition() {
        return etrAvailablePosition;
    }

    public void setEtrAvailablePosition(BigDecimal etrAvailablePosition) {
        this.etrAvailablePosition = etrAvailablePosition;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    /**
     * 获取 一级新债日历进入委托界面的标识
     *
     * @return mId 一级新债日历进入委托界面的标识
     */
    public String getMId() {
        return this.mId;
    }

    /**
     * 设置 一级新债日历进入委托界面的标识
     *
     * @param mId 一级新债日历进入委托界面的标识
     */
    public void setMId(String mId) {
        this.mId = mId;
    }

    /**
     * 获取 序号
     *
     * @return indexId 序号
     */
    public String getIndexId() {
        return this.indexId;
    }

    /**
     * 设置 序号
     *
     * @param indexId 序号
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 交易场所名称
     *
     * @return tradeMarketName 交易场所名称
     */
    public String getTradeMarketName() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, getTradeMarket());
    }

    /**
     * 设置 交易场所名称
     *
     * @param tradeMarketName 交易场所名称
     */
    public void setTradeMarketName(String tradeMarketName) {
        this.tradeMarketName = tradeMarketName;
    }

    /**
     * 获取 投资目的名称
     *
     * @return invAimName 投资目的名称
     */
    public String getInvAimName() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, getInvAim());
    }

    /**
     * 设置 投资目的名称
     *
     * @param invAimName 投资目的名称
     */
    public void setInvAimName(String invAimName) {
        this.invAimName = invAimName;
    }

    /**
     * 获取 币种名称
     *
     * @return ccyName 币种名称
     */
    public String getCcyName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
    }

    /**
     * 设置 币种名称
     *
     * @param ccyName 币种名称
     */
    public void setCcyName(String ccyName) {
        this.ccyName = ccyName;
    }

    /**
     * 获取 资产二级分类名称
     *
     * @return assetType1Name 资产二级分类名称
     */
    public String getAssetType1Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, assetType1);
    }

    /**
     * 设置 资产二级分类名称
     *
     * @param assetType1Name 资产二级分类名称
     */
    public void setAssetType1Name(String assetType1Name) {
        this.assetType1Name = assetType1Name;
    }

    /**
     * 获取 资产三级分类名称
     *
     * @return assetType10Name 资产三级分类名称
     */
    public String getAssetType10Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, assetType10);
    }

    /**
     * 设置 资产三级分类名称
     *
     * @param assetType10Name 资产三级分类名称
     */
    public void setAssetType10Name(String assetType10Name) {
        this.assetType10Name = assetType10Name;
    }

    /**
     * 获取 资产四级分类名称
     *
     * @return assetType11Name 资产四级分类名称
     */
    public String getAssetType11Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, assetType11);
    }

    /**
     * 设置 资产四级分类名称
     *
     * @param assetType11Name 资产四级分类名称
     */
    public void setAssetType11Name(String assetType11Name) {
        this.assetType11Name = assetType11Name;
    }

    /**
     * 获取 缴款方式名称
     *
     * @return etrPayTypeName 缴款方式名称
     */
    public String getEtrPayTypeName() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.缴款方式, getEtrPayType());
    }

    /**
     * 设置 缴款方式名称
     *
     * @param etrPayTypeName 缴款方式名称
     */
    public void setEtrPayTypeName(String etrPayTypeName) {
        this.etrPayTypeName = etrPayTypeName;
    }

    /**
     * 获取 交易对手名称
     *
     * @return counterIdName 交易对手名称
     */
    public String getCounterIdName() {
        return this.counterIdName;
    }

    /**
     * 设置 交易对手名称
     *
     * @param counterIdName 交易对手名称
     */
    public void setCounterIdName(String counterIdName) {
        this.counterIdName = counterIdName;
    }

    /**
     * 获取 缴款日期
     *
     * @return paymentDate 缴款日期
     */
    public Date getPaymentDate() {
        return this.paymentDate;
    }

    /**
     * 设置 缴款日期
     *
     * @param paymentDate 缴款日期
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 获取 上市日期
     *
     * @return listingDate 上市日期
     */
    public Date getListingDate() {
        return this.listingDate;
    }

    /**
     * 设置 上市日期
     *
     * @param listingDate 上市日期
     */
    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    /**
     * 获取 主承销商
     *
     * @return mainConsignee 主承销商
     */
    public String getMainConsignee() {
        return this.mainConsignee;
    }

    /**
     * 设置 主承销商
     *
     * @param mainConsignee 主承销商
     */
    public void setMainConsignee(String mainConsignee) {
        this.mainConsignee = mainConsignee;
    }

    /**
     * 获取 担保人
     *
     * @return warrantor 担保人
     */
    public String getWarrantor() {
        return this.warrantor;
    }

    /**
     * 设置 担保人
     *
     * @param warrantor 担保人
     */
    public void setWarrantor(String warrantor) {
        this.warrantor = warrantor;
    }

    /**
     * 获取 投标区间%
     *
     * @return biddingRange 投标区间%
     */
    public String getBiddingRange() {
        return this.biddingRange;
    }

    /**
     * 设置 投标区间%
     *
     * @param biddingRange 投标区间%
     */
    public void setBiddingRange(String biddingRange) {
        this.biddingRange = biddingRange;
    }
}
