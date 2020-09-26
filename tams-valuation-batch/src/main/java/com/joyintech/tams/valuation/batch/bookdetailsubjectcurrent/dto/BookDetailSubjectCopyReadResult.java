/*
 * 文件名：BookDetailSubjectCopyReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 更新账务明细（当前表）科目余额批处理 ，reader返回实体类
 * 
 * @author chengzhong
 * @version 1.0 2018年6月28日
 * @see BookDetailCompareIntoReadResult
 * @since 1.0
 */
@SuppressWarnings("serial")
public class BookDetailSubjectCopyReadResult extends NeedOperateEntity implements Serializable {

    /**
     * 查询结果
     */
    List<ValBokBookdetailBatch> listValBokBookdetailBatch;

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账务明细代码
     */
    private String bokDetailId;
 
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
     * 获取 listValBokBookdetailBatch
     * @return listValBokBookdetailBatch.
     */
    public List<ValBokBookdetailBatch> getListValBokBookdetailBatch() {
        return listValBokBookdetailBatch;
    }



    
    /**
     * 设置 listValBokBookdetailBatch
     * @param listValBokBookdetailBatch listValBokBookdetailBatch
     */
    public void setListValBokBookdetailBatch(List<ValBokBookdetailBatch> listValBokBookdetailBatch) {
        this.listValBokBookdetailBatch = listValBokBookdetailBatch;
    }


}
