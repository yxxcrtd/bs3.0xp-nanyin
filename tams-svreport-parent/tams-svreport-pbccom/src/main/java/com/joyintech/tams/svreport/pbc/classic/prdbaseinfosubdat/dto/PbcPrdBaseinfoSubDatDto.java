package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCellNumber;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:  jiyangyang
 * @创建时间: 2021年7月29日10:43:50
 * 产品基本信息导入excel中字段信息
 */
public class PbcPrdBaseinfoSubDatDto {
    //序号
    @ExcelCellNumber(cellNumber = 0)
    private String number;
    //人行产品代码
    @ExcelCellNumber(cellNumber = 1)
    private String pbcProdId;
    //发行机构代码
    @ExcelCellNumber(cellNumber = 2)
    private String issuePartyId;
    //产品名称
    @ExcelCellNumber(cellNumber = 3)
    private String prodName;
    //产品品种
    @ExcelCellNumber(cellNumber = 4)
    private String prodType;
    //产品类型
    @ExcelCellNumber(cellNumber = 5)
    private String ivstorientation;
    //产品品牌
    @ExcelCellNumber(cellNumber = 6)
    private String prodBrand;
    //产品期次
    @ExcelCellNumber(cellNumber = 7)
    private BigDecimal prodStage;
    //发行机构内部产品代码,理财产品代码
    @ExcelCellNumber(cellNumber = 8)
    private String prodId;
    //募集方式
    @ExcelCellNumber(cellNumber = 9)
    private String raiseType;
    //管理方式
    @ExcelCellNumber(cellNumber = 10)
    private String manageType;
    //运行方式
    @ExcelCellNumber(cellNumber = 11)
    private String operateMode;
    //募集起始日期
    @ExcelCellNumber(cellNumber = 12)
    private Date raiseVdate01;
    //募集结束日期
    @ExcelCellNumber(cellNumber = 13)
    private Date raiseEnddate01;
    //发行机构提前终止权标识
    @ExcelCellNumber(cellNumber = 14)
    private String termRightFlag;
    //客户赎回权标识
    @ExcelCellNumber(cellNumber = 15)
    private String cusRedeemFlag;
    //产品增信标识
    @ExcelCellNumber(cellNumber = 16)
    private String creditFlag;
    //境内托管机构代码
    @ExcelCellNumber(cellNumber = 17)
    private String inDepCode;
    //境外托管机构国别
    @ExcelCellNumber(cellNumber = 18)
    private String outDepCtry;
    //境外托管机构名称
    @ExcelCellNumber(cellNumber = 19)
    private String inDepName;
    //产品起始日期
    @ExcelCellNumber(cellNumber = 20)
    private Date vdate;
    //产品预计终止日期
    @ExcelCellNumber(cellNumber = 21)
    private Date mdate;
    //受托职责
    @ExcelCellNumber(cellNumber = 22)
    private String fiduciaryDuty;
    //分级产品标识
    @ExcelCellNumber(cellNumber = 23)
    private String isConstracte;
    //收益权转让产品标识
    @ExcelCellNumber(cellNumber = 24)
    private String transferType;
    //货币市场基金标识
    @ExcelCellNumber(cellNumber = 25)
    private String transferType1;
    //其他产品标识1
    @ExcelCellNumber(cellNumber = 26)
    private String transferType2;
    //其他产品标识2
    @ExcelCellNumber(cellNumber = 27)
    private String transferType3;
    //其他产品标识3
    @ExcelCellNumber(cellNumber = 28)
    private String transferType4;
    //募集资金币种
    @ExcelCellNumber(cellNumber = 29)
    private String raiseCcy;
    //兑付本金币种
    @ExcelCellNumber(cellNumber = 30)
    private String cashPrinCcy;
    //兑付收益币种
    @ExcelCellNumber(cellNumber = 31)
    private String cashProfCcy;
    //增信机构类型
    @ExcelCellNumber(cellNumber = 32)
    private String creditType;
    //增信形式
    @ExcelCellNumber(cellNumber = 33)
    private String creditMode;

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getIvstorientation() {
        return ivstorientation;
    }

    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public BigDecimal getProdStage() {
        return prodStage;
    }

    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public Date getRaiseVdate01() {
        return raiseVdate01;
    }

    public void setRaiseVdate01(Date raiseVdate01) {
        this.raiseVdate01 = raiseVdate01;
    }

    public Date getRaiseEnddate01() {
        return raiseEnddate01;
    }

    public void setRaiseEnddate01(Date raiseEnddate01) {
        this.raiseEnddate01 = raiseEnddate01;
    }

    public String getTermRightFlag() {
        return termRightFlag;
    }

    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag;
    }

    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getInDepCode() {
        return inDepCode;
    }

    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode;
    }

    public String getOutDepCtry() {
        return outDepCtry;
    }

    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry;
    }

    public String getInDepName() {
        return inDepName;
    }

    public void setInDepName(String inDepName) {
        this.inDepName = inDepName;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty;
    }

    public String getIsConstracte() {
        return isConstracte;
    }

    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferType1() {
        return transferType1;
    }

    public void setTransferType1(String transferType1) {
        this.transferType1 = transferType1;
    }

    public String getTransferType2() {
        return transferType2;
    }

    public void setTransferType2(String transferType2) {
        this.transferType2 = transferType2;
    }

    public String getTransferType3() {
        return transferType3;
    }

    public void setTransferType3(String transferType3) {
        this.transferType3 = transferType3;
    }

    public String getTransferType4() {
        return transferType4;
    }

    public void setTransferType4(String transferType4) {
        this.transferType4 = transferType4;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    public String getCashProfCcy() {
        return cashProfCcy;
    }

    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getCreditMode() {
        return creditMode;
    }

    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }
}