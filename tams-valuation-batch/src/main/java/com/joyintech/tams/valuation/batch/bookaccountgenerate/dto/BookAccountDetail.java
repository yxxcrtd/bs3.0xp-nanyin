/*
 * 文件名：BookAccountDetail.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.bookaccountgenerate.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BookAccountDetail {
    private String booksetId;
    private String tableId;
    private String tradeId;
    private String busiId;
    private Date happenDate;
    private Date bookDate;
    private String subjectNo;
    private String detailSubjectNo;
    private String cdFlag;
    private String oCcy;
    private BigDecimal oHappenAmt;
    private String offsetFlag;
    private String seqno;
    private BigDecimal happenNumber;
    private String fTradeId;
    private String tableName;
    private String bokDetailId;
    private String bookId;
    private String bookRemark;
    /**
     * 凭证类型
     */
    private String bookType;

    /*start 20190520 add by chufengjie*/
    //本位币
    private String bCcy;
    //本位币发生额
    private BigDecimal bHappenAmt;
    /*end 20190520 add by chufengjie*/

    /*start 20191010 add by zhangzhao*/
    private String isexeZeroAmt;
    /*end 20191010 add by zhangzhao*/

    public BigDecimal getHappenNumber() {
        return happenNumber;
    }

    
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }
    
    public String getBokDetailId() {
        return bokDetailId;
    }
    
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }


    public String getBooksetId() {
        return booksetId;
    }
    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    public String getTableId() {
        return tableId;
    }
    
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    
    public String getTradeId() {
        return tradeId;
    }
    
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    public String getBusiId() {
        return busiId;
    }
    
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }
    
    public Date getHappenDate() {
        return happenDate;
    }
    
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }
    
    public Date getBookDate() {
        return bookDate;
    }
    
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
    
    public String getSubjectNo() {
        return subjectNo;
    }
    
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }
    
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }
    
    public String getCdFlag() {
        return cdFlag;
    }
    
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }
    
    public String getoCcy() {
        return oCcy;
    }
    
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }
    
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }
    
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }
    
    public String getOffsetFlag() {
        return offsetFlag;
    }
    
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }
    
    public String getSeqno() {
        return seqno;
    }
    
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }
    
    public String getfTradeId() {
        return fTradeId;
    }
    
    public void setfTradeId(String pTradeId) {
        this.fTradeId = pTradeId;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    
    /**
     * 获取 bookId
     * @return bookId.
     */
    public String getBookId() {
        return bookId;
    }


    
    /**
     * 设置 bookId
     * @param bookId bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    
    /**
     * 获取 bookType
     * @return bookType.
     */
    public String getBookType() {
        return bookType;
    }


    
    /**
     * 设置 bookType
     * @param bookType bookType
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }


	public String getBookRemark() {
		return bookRemark;
	}


	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}




    public String getbCcy() {
        return bCcy;
    }


    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }


    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }


    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }


    public String getIsexeZeroAmt() {
        return isexeZeroAmt;
    }

    public void setIsexeZeroAmt(String isexeZeroAmt) {
        this.isexeZeroAmt = isexeZeroAmt;
    }
}
