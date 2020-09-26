/*
 * 文件名：FinProductDealInfoResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * (产品估值计算) - reader返回值
 * 
 * @author yangjian
 * @version 1.0 2018年8月13日
 * @see BokstatbokpositionReaderDto
 * @since 1.0
 */
public class BokstatbokpositionReaderDto extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 实际到期日
     */
    private Date actMdate;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 收益类型
     */
    private String profitType;
    
    
    /**add by leipeng 20181017 **/
    
    /**
     * 特殊核算模型
     */
    private String  bokSpecMode;
    /**
     * 特殊核算代码
     */
    private String  acctSubjectId;
    
    
    /**
     * 核算主体类别
     */
    private String acctSubjectType;
    
    /**
     * 核算方式
     */
    private String  acctmode;
    /**
     * 分层代码
     */
    private String  layeringId;
    /**
     * 金融产品类型
     */
    private String  finprodType;
    /**
     * 分层类别
     */
    private String layeringType;
    /**
     * 业务明细代码
     */
    private String  busiId;
    /**
     * 分支序号
     */
    private Long  branch;
    /**
     * 级别序号
     */
    private String  lev;
    /**
     * 当前级别
     */
    private String  curLev;
    /**
     * 下级最新序号
     */
    private String  sBranch;
    /**
     * 业务明细类型
     */
    private String  busiDetailType;
    /**
     * 上层代码代码
     */
    private String fLayeringId;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 获取 acctSubjectId
     * @return acctSubjectId.
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }


    
    /**
     * 设置 acctSubjectId
     * @param acctSubjectId acctSubjectId
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }


    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getActMdate() {
        return actMdate;
    }

    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    public String getStandardCcy() {
        return standardCcy;
    }

    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public Long getTaskPri() {
        return taskPri;
    }

    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode;
    }

    
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    
    public String getAcctmode() {
        return acctmode;
    }

    
    public void setAcctmode(String acctmode) {
        this.acctmode = acctmode;
    }

    
    /**
     * 获取 layeringId
     * @return layeringId.
     */
    public String getLayeringId() {
        return layeringId;
    }

    
    /**
     * 设置 layeringId
     * @param layeringId layeringId
     */
    public void setLayeringId(String layeringId) {
        this.layeringId = layeringId;
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
     * 获取 layeringType
     * @return layeringType.
     */
    public String getLayeringType() {
        return layeringType;
    }

    
    /**
     * 设置 layeringType
     * @param layeringType layeringType
     */
    public void setLayeringType(String layeringType) {
        this.layeringType = layeringType;
    }

    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    
    /**
     * 获取 lev
     * @return lev.
     */
    public String getLev() {
        return lev;
    }

    
    /**
     * 设置 lev
     * @param lev lev
     */
    public void setLev(String lev) {
        this.lev = lev;
    }

    
    /**
     * 获取 curLev
     * @return curLev.
     */
    public String getCurLev() {
        return curLev;
    }

    
    /**
     * 设置 curLev
     * @param curLev curLev
     */
    public void setCurLev(String curLev) {
        this.curLev = curLev;
    }

    
    /**
     * 获取 sBranch
     * @return sBranch.
     */
    public String getsBranch() {
        return sBranch;
    }

    
    /**
     * 设置 sBranch
     * @param sBranch sBranch
     */
    public void setsBranch(String sBranch) {
        this.sBranch = sBranch;
    }

    
    /**
     * 获取 busiDetailType
     * @return busiDetailType.
     */
    public String getBusiDetailType() {
        return busiDetailType;
    }

    
    /**
     * 设置 busiDetailType
     * @param busiDetailType busiDetailType
     */
    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }

    
    /**
     * 获取 fLayeringId
     * @return fLayeringId.
     */
    public String getfLayeringId() {
        return fLayeringId;
    }

    
    /**
     * 设置 fLayeringId
     * @param fLayeringId fLayeringId
     */
    public void setfLayeringId(String fLayeringId) {
        this.fLayeringId = fLayeringId;
    }

    
    /**
     * 获取 bokDetailId
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    
    /**
     * 设置 bokDetailId
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
    
    

}
