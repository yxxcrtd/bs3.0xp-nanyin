/*
 * 文件名：BookDetailSubjectAllReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 更新账务明细（历史表）科目余额批处理 ，reader返回实体类
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailCompareIntoReadResult
 * @since 1.0
 */
@SuppressWarnings("serial")
public class BookDetailSubjectAllReadResult extends NeedOperateEntity implements Serializable {

    /**
     * 账套代码
     */
    private String booksetId;

//    /**
//     * 业务明细代码(资产代码)
//     */
//    private String busiId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 该账套下的账务明细数据
     */
    private List<ValBokBookdetailBatch> valBokBookdetailList;

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

//    /**
//     * 获取 busiId
//     * 
//     * @return busiId.
//     */
//    public String getBusiId() {
//        return busiId;
//    }

//    /**
//     * 设置 busiId
//     * 
//     * @param busiId busiId
//     */
//    public void setBusiId(String busiId) {
//        this.busiId = busiId;
//    }

    /**
     * 获取 bokDetailId
     * 
     * @return bokDetailId.
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 bokDetailId
     * 
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * 获取 baseSubjectNature
     * 
     * @return baseSubjectNature.
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 baseSubjectNature
     * 
     * @param baseSubjectNature baseSubjectNature
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
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
     * 获取 valBokBookdetailList
     * 
     * @return valBokBookdetailList.
     */
    public List<ValBokBookdetailBatch> getValBokBookdetailList() {
        return valBokBookdetailList;
    }

    /**
     * 设置 valBokBookdetailList
     * 
     * @param valBokBookdetailList valBokBookdetailList
     */
    public void setValBokBookdetailList(List<ValBokBookdetailBatch> valBokBookdetailList) {
        this.valBokBookdetailList = valBokBookdetailList;
    }

}
