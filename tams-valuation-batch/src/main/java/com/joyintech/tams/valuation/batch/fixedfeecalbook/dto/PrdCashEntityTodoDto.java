/*
 * 文件名：PrdCashEntityTodo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.fixedfeecalbook.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.PrdCashEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;

/**
 * 待计提金融产品现金流Dto
 * 
 * @author weipeng
 * @version 1.0 2018年6月4日
 * @see PrdCashEntityTodoDto
 * @since 1.0
 */
public class PrdCashEntityTodoDto extends NeedOperateEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 起息日
     */
    private Date vdate;
    
    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;

    /**
     * 帐套代码
     */
    private String booksetId;

    /**
     * 帐套日期
     */
    private Date booksetDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 实际到期日
     */
    private Date actmdate;

    /**
     * 会计场景
     */
    private TreeNode bookSetTreeNode;
    
    /**
     * 任务优先级
     */
    private Short taskPri; 

    public PrdCashEntityTodoDto(PrdCashEntity entity) {
        this.setFinprodId(entity.getFinprodId());
        this.setFinprodType(entity.getFinprodType());
        this.setBokDetailId(entity.getBokDetailId());
        this.setVdate(entity.getVdate());
        this.setCashId(entity.getCashId());
        this.setCashType1(entity.getCashType1());
        this.setCashType2(entity.getCashType2());
        this.setBooksetId(entity.getBooksetId());
        this.setBooksetDate(entity.getBooksetDate());
        this.setStandardCcy(entity.getStandardCcy());
        this.setBookDate(entity.getBookDate());
        this.setActmdate(entity.getActmdate());
    }

    /**
     * 取得 金融产品代码
     * 
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * 
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 取得 金融产品类型
     * 
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * 
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 取得 起息日
     * 
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * 
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 取得 现金流代码
     * 
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * 
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 取得 现金流一级类型
     * 
     * @return 现金流一级类型
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置 现金流一级类型
     * 
     * @param cashType1 现金流一级类型
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    /**
     * 取得 现金流二级类型
     * 
     * @return 现金流二级类型
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 现金流二级类型
     * 
     * @param cashType2 现金流二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    /**
     * 取得 帐套代码
     * 
     * @return 帐套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 帐套代码
     * 
     * @param booksetId 帐套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 帐套日期
     * 
     * @return 帐套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 帐套日期
     * 
     * @param booksetDate 帐套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 获取 bookDate
     * 
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 bookDate
     * 
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取 standardCcy
     * 
     * @return standardCcy.
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 standardCcy
     * 
     * @param standardCcy standardCcy
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    /**
     * 获取 actmdate
     * 
     * @return actmdate.
     */
    public Date getActmdate() {
        return actmdate;
    }

    /**
     * 设置 actmdate
     * 
     * @param actmdate actmdate
     */
    public void setActmdate(Date actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 获取 会计场景
     * 
     * @return 会计场景
     */
    public TreeNode getBookSetTreeNode() {
        return bookSetTreeNode;
    }

    /**
     * 设置 会计场景
     * 
     * @param bookSetTreeNode 会计场景
     */
    public void setBookSetTreeNode(TreeNode bookSetTreeNode) {
        this.bookSetTreeNode = bookSetTreeNode;
    }
    
    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 取得  账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
    
    /**
     * 获取 taskPri
     *
     * @return taskPri.
     */
    public Short getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 taskPri
     *
     * @param taskPri taskPri
     */
    public void setTaskPri(Short taskPri) {
        this.taskPri = taskPri;
    }


}
