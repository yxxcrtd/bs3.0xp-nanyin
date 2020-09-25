/*
 * 文件名：EtrOptionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etroption.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author likai
 * @version 1.0 2019年12月23日
 * @see EtrOptionDto
 * @since 1.0
 */
public class EtrOptionDto extends EtrEntrust {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3314619126979170188L;
    /**
     * 文件上传dto
     */
    private List<SysUploadFileInfo> fileArray;
    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 接收状态
     */
    private String recStatus;
    /**
     * 投资人员信息DTO
     */
    private BussEtrInvestPersonInfoDto etrInvestPersonInfo;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 表格所选数据
     */
    private List<EtrOptionDto> selectedItem;

    /**
     * 委托关闭方式
     */
    private String CloseWay;

    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产一级分类，委托页面的债券一级分类
     */
    private String assetType1;
    /**
     * 期限(天)
     */
    private Long termDays;
    /**
     * 合约标的
     */
    private String assetType10;
    private String assetType10Str;
    /**
     * 合约类型
     */
    private String assetType11;
    /**
     * 行权方式
     */
    private String  exerciseType;
    /**
     * 行权日基准日
     */
    private Date execBaseDate;
    /**
     * 期权费类型
     */
    private String optFeePayType;
    /**
     * 期权费率
     */
    private String optFeePayType2;
    /**
     * 执行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exePrice;
    /**
     * 敲出价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal knockPrice;
    /**
     * 低障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowHindPrice;
    /**
     * 低行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowExercPrice;
    /**
     * 高障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highHindPrice;
    /**
     * 高行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highExercPrice;

    public BigDecimal getHighHindPrice() {
        return highHindPrice;
    }

    public void setHighHindPrice(BigDecimal highHindPrice) {
        this.highHindPrice = highHindPrice;
    }

    public BigDecimal getHighExercPrice() {
        return highExercPrice;
    }

    public void setHighExercPrice(BigDecimal highExercPrice) {
        this.highExercPrice = highExercPrice;
    }

    /**
     * 起息日
     */
    private Date vdateAdd;
    /**
     * 到期日
     */
    private Date mdateAdd;
    public String getAssetType10() {
        return assetType10;
    }

    public void setAssetType10(String assetType10) {
        this.assetType10 = assetType10;
    }

    public String getAssetType10Str() {
        return assetType10Str;
    }

    public void setAssetType10Str(String assetType10Str) {
        this.assetType10Str = assetType10Str;
    }

    public String getAssetType11() {
        return assetType11;
    }

    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Date getExecBaseDate() {
        return execBaseDate;
    }

    public void setExecBaseDate(Date execBaseDate) {
        this.execBaseDate = execBaseDate;
    }

    public String getOptFeePayType() {
        return optFeePayType;
    }

    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }

    public String getOptFeePayType2() {
        return optFeePayType2;
    }

    public void setOptFeePayType2(String optFeePayType2) {
        this.optFeePayType2 = optFeePayType2;
    }

    public BigDecimal getExePrice() {
        return exePrice;
    }

    public void setExePrice(BigDecimal exePrice) {
        this.exePrice = exePrice;
    }

    public BigDecimal getKnockPrice() {
        return knockPrice;
    }

    public void setKnockPrice(BigDecimal knockPrice) {
        this.knockPrice = knockPrice;
    }

    public BigDecimal getLowHindPrice() {
        return lowHindPrice;
    }

    public void setLowHindPrice(BigDecimal lowHindPrice) {
        this.lowHindPrice = lowHindPrice;
    }

    public BigDecimal getLowExercPrice() {
        return lowExercPrice;
    }

    public void setLowExercPrice(BigDecimal lowExercPrice) {
        this.lowExercPrice = lowExercPrice;
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
     * 获取 termDays
     * @return termDays.
     */
    public Long getTermDays() {
        return termDays;
    }






    /**
     * 设置 termDays
     * @param termDays termDays
     */
    public void setTermDays(Long termDays) {
        this.termDays = termDays;
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
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<EtrOptionDto> getSelectedItem() {
        return selectedItem;
    }



    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<EtrOptionDto> selectedItem) {
        this.selectedItem = selectedItem;
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


//    /**
//     * 获取 finishAmt
//     * @return finishAmt.
//     */
//    public BigDecimal getFinishAmt() {
//        return finishAmt;
//    }
//
//
//    /**
//     * 设置 finishAmt
//     * @param finishAmt finishAmt
//     */
//    public void setFinishAmt(BigDecimal finishAmt) {
//        this.finishAmt = finishAmt;
//    }
//
//
//    /**
//     * 获取 transactioncom
//     * @return transactioncom.
//     */
//    public BigDecimal getTransactioncom() {
//        return transactioncom;
//    }
//
//
//    /**
//     * 设置 transactioncom
//     * @param transactioncom transactioncom
//     */
//    public void setTransactioncom(BigDecimal transactioncom) {
//        this.transactioncom = transactioncom;
//    }



}
