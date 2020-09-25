/*
 * 文件名：EtrFundDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：huwenbin 修改时间：2018年10月8日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrfund.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 基金委托Dto 〈功能详细描述〉
 * 
 * @author huwenbin
 * @version 1.0 2018年10月8日
 * @see EtrFundDto
 * @since 1.0
 */
public class EtrFundDto extends EtrEntrust {

    /**
     * 指定交易员
     */
    private String trader;

    /**
     * 页面提交模式
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 选中的数据
     */
    private transient List<EtrFundDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产名称
     */
    private String finprodName;

    // 资产代码
    private String finprodMarketId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 已完成额度
     */
    private long finishAmt;

    /**
     * 交易完成度
     */
    private long transactioncom;

    /**
     * 委托操作类型
     */
    private String entrustOperationType;

    /**
     * 基金类型
     */
    private String assetType1;

    /** 新增个性分类三、四 王贵奇 2019/7/29 开始 **/
    /**
     * 个性分类三
     */
    private String assetType10;

    /**
     * 个性分类四
     */
    private String assetType11;

    /** 新增个性分类三、四 王贵奇 2019/7/29 结束 **/

    /**
     * 投资人员Dto
     */
    private BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto;

    /**
     * 
     */
    private List<SysUploadFileInfo> fileArray;

    // add by lishuping 20191029 start
    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 交易所基金简称
     */
    private String trmFundAbbr;

    /**
     * 交易所基金类型
     */
    private String trmFundType;

    /**
     * 转入金融产品代码
     */
    private String rFinprodId;

    /**
     * 转入资产代码
     */
    private String changeToFinprodMarketId;

    /**
     * 转入资产名称
     */
    private String changeToFinprodName;

    /**
     * finprodAbbr
     * 
     * @return finprodAbbr
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }
    // add by lishuping 20191029 end

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<EtrFundDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrFundDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 closeWay
     * 
     * @return closeWay.
     */
    public String getCloseWay() {
        return CloseWay;
    }

    /**
     * 设置 closeWay
     * 
     * @param closeWay closeWay
     */
    public void setCloseWay(String closeWay) {
        CloseWay = closeWay;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 finprodName
     * 
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * 
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 finishAmt
     * 
     * @return finishAmt.
     */
    public long getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 finishAmt
     * 
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(long finishAmt) {
        this.finishAmt = finishAmt;
    }

    /**
     * 获取 transactioncom
     * 
     * @return transactioncom.
     */
    public long getTransactioncom() {
        return transactioncom;
    }

    /**
     * 设置 transactioncom
     * 
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(long transactioncom) {
        this.transactioncom = transactioncom;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 trader
     * 
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 trader
     * 
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 entrustOperationType
     * 
     * @return entrustOperationType.
     */
    public String getEntrustOperationType() {
        return entrustOperationType;
    }

    /**
     * 设置 entrustOperationType
     * 
     * @param entrustOperationType entrustOperationType
     */
    public void setEntrustOperationType(String entrustOperationType) {
        this.entrustOperationType = entrustOperationType;
    }

    /**
     * 获取 assetType1
     * 
     * @return assetType1.
     */
    public String getAssetType1() {
        return assetType1;
    }

    /**
     * 设置 assetType1
     * 
     * @param assetType1 assetType1
     */
    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    /** 新增个性分类三、四 王贵奇 2019/7/29 开始 **/
    /**
     * 获取 assetType10
     * 
     * @return assetType10.
     */
    public String getAssetType10() {
        return assetType10;
    }

    /**
     * 设置 assetType10
     * 
     * @param assetType10 assetType10
     */
    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }

    /**
     * 获取 assetType11
     * 
     * @return assetType11.
     */
    public String getAssetType11() {
        return assetType11;
    }

    /**
     * 设置 assetType11
     * 
     * @param assetType11 assetType11
     */
    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }

    /** 新增个性分类三、四 王贵奇 2019/7/29 结束 **/

    /**
     * 获取 bussEtrInvestPersonInfoDto
     * 
     * @return bussEtrInvestPersonInfoDto.
     */
    public BussEtrInvestPersonInfoDto getBussEtrInvestPersonInfoDto() {
        return bussEtrInvestPersonInfoDto;
    }

    /**
     * 设置 bussEtrInvestPersonInfoDto
     * 
     * @param bussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto
     */
    public void setBussEtrInvestPersonInfoDto(BussEtrInvestPersonInfoDto bussEtrInvestPersonInfoDto) {
        this.bussEtrInvestPersonInfoDto = bussEtrInvestPersonInfoDto;
    }

    /**
     * 获取 fileArray
     * 
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * 
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getTrmFundAbbr() {
        return trmFundAbbr;
    }

    public void setTrmFundAbbr(String trmFundAbbr) {
        this.trmFundAbbr = trmFundAbbr;
    }

    public String getTrmFundType() {
        return trmFundType;
    }

    public void setTrmFundType(String trmFundType) {
        this.trmFundType = trmFundType;
    }

    /**
     * 获取 rFinprodId
     *
     * @return rFinprodId
     */
    public String getrFinprodId() {
        return rFinprodId;
    }

    /**
     * 设置 rFinprodId
     *
     * @param rFinprodId rFinprodId
     */
    public void setrFinprodId(String rFinprodId) {
        this.rFinprodId = rFinprodId;
    }

    /**
     * 获取 changeToFinprodMarketId
     *
     * @return changeToFinprodMarketId
     */
    public String getChangeToFinprodMarketId() {
        return changeToFinprodMarketId;
    }

    /**
     * 设置 changeToFinprodMarketId
     *
     * @param changeToFinprodMarketId changeToFinprodMarketId
     */
    public void setChangeToFinprodMarketId(String changeToFinprodMarketId) {
        this.changeToFinprodMarketId = changeToFinprodMarketId;
    }

    /**
     * 获取 changeToFinprodName
     *
     * @return changeToFinprodName
     */
    public String getChangeToFinprodName() {
        return changeToFinprodName;
    }

    /**
     * 设置 changeToFinprodName
     *
     * @param changeToFinprodName changeToFinprodName
     */
    public void setChangeToFinprodName(String changeToFinprodName) {
        this.changeToFinprodName = changeToFinprodName;
    }
}
