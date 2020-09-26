/*
 * 文件名：BookGenerateResult.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 记账凭证生成结果
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see BookDetailSubjectCopyResult
 * @since 1.0
 */
public class BookDetailSubjectCopyResult extends NeedToWriteEntity {


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


}
