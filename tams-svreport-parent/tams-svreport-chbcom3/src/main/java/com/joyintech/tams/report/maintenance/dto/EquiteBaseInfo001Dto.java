/*
 * 文件名：EquiteBaseInfo001Dto.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.maintenance.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.report.common.base.TableRow;


/**
 * 〈一句话功能简述〉
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月14日
 * @see EquiteBaseInfo001Dto
 * @since 1.0
 */
public class EquiteBaseInfo001Dto extends TableRow {
    /**
     * excle行
     */
    private String excelNum;
    /**
     * 机构代码
     */
    private String orgCode;

    
    /**
     * 获取 orgCode
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode;
    }

    
    /**
     * 设置 orgCode
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    /**
     * 数据状态
     */
    private String status;

    /**
     * 登记银行代码
     */
    private String bankCode;

    /**
     * 产品名称
     */
    private String prdName;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 识别标志
     */
    private String indentifyMark;

    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 币种
     */
    private String ccyCode;

    /**
     * 可用数量
     */
    private BigDecimal availableQuantity;

    /**
     * 冻结数量
     */
    private BigDecimal frozenQuantity;

    /**
     * 质押数量
     */
    private BigDecimal pledgedQuantity;

    /**
     * 初值日期
     */
    private Date initValDate;

    /**
     * 初值登记日期
     */
    private Date initValRegistDate;
    
    /**
     * 金额
     */
    private BigDecimal amount;
    
    /**
     * 总折算人民币金额（元）
     */
    private BigDecimal converCnyAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getIndentifyMark() {
        return indentifyMark;
    }

    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public BigDecimal getFrozenQuantity() {
        return frozenQuantity;
    }

    public void setFrozenQuantity(BigDecimal frozenQuantity) {
        this.frozenQuantity = frozenQuantity;
    }

    public BigDecimal getPledgedQuantity() {
        return pledgedQuantity;
    }

    public void setPledgedQuantity(BigDecimal pledgedQuantity) {
        this.pledgedQuantity = pledgedQuantity;
    }

    public Date getInitValDate() {
        return initValDate;
    }

    public void setInitValDate(Date initValDate) {
        this.initValDate = initValDate;
    }

    public Date getInitValRegistDate() {
        return initValRegistDate;
    }

    public void setInitValRegistDate(Date initValRegistDate) {
        this.initValRegistDate = initValRegistDate;
    }


    /**
     * 获取 converCnyAmount
     * @return converCnyAmount.
     */
    public BigDecimal getConverCnyAmount() {
        return converCnyAmount;
    }


    /**
     * 设置 converCnyAmount
     * @param converCnyAmount converCnyAmount
     */
    public void setConverCnyAmount(BigDecimal converCnyAmount) {
        this.converCnyAmount = converCnyAmount;
    }


    
    /**
     * 获取 ccyCode
     * @return ccyCode.
     */
    public String getCcyCode() {
        return ccyCode;
    }


    
    /**
     * 设置 ccyCode
     * @param ccyCode ccyCode
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }


    
    /**
     * 获取 excelNum
     * @return excelNum.
     */
    public String getExcelNum() {
        return excelNum;
    }


    
    /**
     * 设置 excelNum
     * @param excelNum excelNum
     */
    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }


    /**
     * 获取 amount
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }


    /**
     * 设置 amount
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
