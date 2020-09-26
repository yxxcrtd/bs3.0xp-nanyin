/*
 * 文件名：PrdValuationFeeBeforeReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.expreturnprdvaluationfeebefore.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * （预期收益型产品估值计算_费前）-reader返回值
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月28日
 * @see ExpReturnPrdValuationFeeBeforeReaderDto
 * @since 1.0
 */
public class ExpReturnPrdValuationFeeBeforeReaderDto extends NeedOperateEntity {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 分层代码
     */
    private String layeringId;
    /**
     * 上层代码
     */
    private String fLayeringId;

    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;

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
     * 母金融产品代码
     */
    private String pfinprodId;

    /**
     * 收益类型
     */
    private String profitType;
    /**add by leipeng 20181017 **/

    /**
     * 特殊核算模型
     */
    private String bokSpecMode;
    
    /**
     * 分层类别
     */
    private String  layeringType;
    
    /**
     * 核算类型
     */
    private String acctmode;
    
    
    
    /**
     * 业务明细类型
     */
    private String  busiDetailType;
    /**
     * 业务明细代码
     */
    private String  busiId;
    /**
     * 分支序号
     */
    private String  branch;
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
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 actMdate
     * 
     * @return actMdate.
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 actMdate
     * 
     * @param actMdate actMdate
     */
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
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
     * 获取 booksetDate
     * 
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 booksetDate
     * 
     * @param booksetDate booksetDate
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
     * 获取 happenDate
     * 
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 happenDate
     * 
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 获取 taskPri
     * 
     * @return taskPri.
     */
    public Long getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 taskPri
     * 
     * @param taskPri taskPri
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    /**
     * 获取 pfinprodId
     * 
     * @return pfinprodId.
     */
    public String getPfinprodId() {
        return pfinprodId;
    }

    /**
     * 设置 pfinprodId
     * 
     * @param pfinprodId pfinprodId
     */
    public void setPfinprodId(String pfinprodId) {
        this.pfinprodId = pfinprodId;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode;
    }

    
    public String getLayeringType() {
        return layeringType;
    }

    
    public void setLayeringType(String layeringType) {
        this.layeringType = layeringType;
    }

    
    public String getAcctmode() {
        return acctmode;
    }

    
    public void setAcctmode(String acctmode) {
        this.acctmode = acctmode;
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
    public String getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(String branch) {
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
    
    

}
