/*
 * 文件名：EtrInvestBondInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrinvestbondinfo.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 债券投资委托微服务端dto
 * 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see EtrInvestBondInfoDto
 * @since 1.0
 */
public class EtrInvestBondInfoDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8503717126985211652L;
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
     * 投资人员信息DTO
     */
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;

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
     * 完成额度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 交易完成度
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal transactioncom;

    /**
     * 按钮id
     */
    private String elementId;

    /**
     * 行权收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
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
     * 证券托管户
     */
    private String secAcctId;

    //add by lishuping 20191029 start
    /**
     * 资产简称
     */
    private String finprodAbbr;
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
     * 最低成交金额
     */
    private BigDecimal minAmt;

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
     * 获取 settleSpeed
     *
     * @return settleSpeed.
     */
    public Long getSettleSpeed() {
        return settleSpeed;
    }


    /**
     * 设置 settleSpeed
     *
     * @param settleSpeed settleSpeed
     */
    public void setSettleSpeed(Long settleSpeed) {
        this.settleSpeed = settleSpeed;
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


    /**
     * 获取 recStatus
     *
     * @return recStatus.
     */
    public String getRecStatus() {
        return recStatus;
    }


    /**
     * 设置 recStatus
     *
     * @param recStatus recStatus
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
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


    /**
     * 新增个性分类三、四getters和setters。王贵奇，2019/9/27 开始
     * 获取 assetType10
     * @return assetType10.
     */
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
     * 获取 trader
     *
     * @return trader.
     */
    public String getTrader() {
        return trader;
    }


    /**
     * 获取 etrInvestPersonInfo
     *
     * @return etrInvestPersonInfo.
     */
    public BussEtrInvestPersonInfoDto getEtrInvestPersonInfo() {
        return etrInvestPersonInfo;
    }


    /**
     * 设置 etrInvestPersonInfo
     *
     * @param etrInvestPersonInfo etrInvestPersonInfo
     */
    public void setEtrInvestPersonInfo(BussEtrInvestPersonInfoDto etrInvestPersonInfo) {
        this.etrInvestPersonInfo = etrInvestPersonInfo;
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
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<EtrInvestBondInfoDto> getSelectedItem() {
        return selectedItem;
    }


    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrInvestBondInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 exerYield
     *
     * @return exerYield.
     */
    public BigDecimal getExerYield() {
        return exerYield;
    }


    /**
     * 设置 exerYield
     *
     * @param exerYield exerYield
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }


    /**
     * 获取 matureYield
     *
     * @return matureYield.
     */
    public BigDecimal getMatureYield() {
        return matureYield;
    }


    /**
     * 设置 matureYield
     *
     * @param matureYield matureYield
     */
    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }


    /**
     * 获取 settleSpreed
     *
     * @return settleSpreed.
     */
    public String getSettleSpreed() {
        return settleSpreed;
    }


    /**
     * 设置 settleSpreed
     *
     * @param settleSpreed settleSpreed
     */
    public void setSettleSpreed(String settleSpreed) {
        this.settleSpreed = settleSpreed;
    }


    /**
     * 获取 bondType
     *
     * @return bondType.
     */
    public String getBondType() {
        return bondType;
    }

    /**
     * 设置 bondType
     *
     * @param bondType bondType
     */
    public void setBondType(String bondType) {
        this.bondType = bondType;
    }


    /**
     * 获取 room
     *
     * @return room.
     */
    public String getRoom() {
        return room;
    }


    /**
     * 设置 room
     *
     * @param room room
     */
    public void setRoom(String room) {
        this.room = room;
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
     * 获取 finprodType2
     *
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }


    /**
     * 设置 finprodType2
     *
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }


    /**
     * 获取 finishAmt
     *
     * @return finishAmt.
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }


    /**
     * 设置 finishAmt
     *
     * @param finishAmt finishAmt
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }


    /**
     * 获取 transactioncom
     *
     * @return transactioncom.
     */
    public BigDecimal getTransactioncom() {
        return transactioncom;
    }


    /**
     * 设置 transactioncom
     *
     * @param transactioncom transactioncom
     */
    public void setTransactioncom(BigDecimal transactioncom) {
        this.transactioncom = transactioncom;
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
     * 获取 entrustOwnNum
     *
     * @return entrustOwnNum
     */
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
}
