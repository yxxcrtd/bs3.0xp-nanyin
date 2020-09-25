/*
 * 文件名：ValBalanceUnusualQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.financemanage.balanceunusualquery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年1月31日
 * @see ValBalanceUnusualQuery001Dto
 * @since 1.0
 */
public class ValBalanceUnusualQuery001Dto extends BaseEntity{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 序号
     */
    private String orderId;
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;
    
    /**
     * 产品到期日
     */
    private String mDate;
    
    /**
     * 资产类型
     */
    private String finprodType;
    /**
     * 资产代码
     */
    private String finprodId;
    /**
     * 资产名称
     */
    private String finprodName;
    
    
    /**
     * 异常科目代码
     */
    private String subjectNo;

    /**
     * 异常科目名称
     */
    private String subjectName;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 科目余额
     */
    private BigDecimal oAmt;

    
    /**
     * 账套代码
     */
    private String booksetId;
    
    private String BusiId;
    
    
    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return BusiId;
    }



    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        BusiId = busiId;
    }



    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }


    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }


    /**
     * 获取 orderId
     * @return orderId.
     */
    public String getOrderId() {
        return orderId;
    }

    
    /**
     * 设置 orderId
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    
    /**
     * 获取 prodId
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    
    /**
     * 设置 prodId
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    /**
     * 获取 prodName
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    
    /**
     * 设置 prodName
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    
    /**
     * 获取 mDate
     * @return mDate.
     */
    public String getmDate() {
        return mDate;
    }

    
    /**
     * 设置 mDate
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
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
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    
    /**
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    
    /**
     * 获取 subjectName
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    
    /**
     * 设置 subjectName
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    
    /**
     * 获取 balFlag
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }

    
    /**
     * 设置 balFlag
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    /**
     * 获取 oAmt
     * @return oAmt.
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    
    /**
     * 设置 oAmt
     * @param oAmt oAmt
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }
    
    
    

}
