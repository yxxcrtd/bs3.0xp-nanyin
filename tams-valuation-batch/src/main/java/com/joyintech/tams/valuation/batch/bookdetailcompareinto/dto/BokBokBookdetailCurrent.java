/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * key
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BokBokBookdetailCurrent
 * @since 1.0
 */
public class BokBokBookdetailCurrent extends NeedOperateEntity {
    
    /**
     * 账务明细（当前表）
     */
    private List<ValBokBookdetailCurrentExt> listValBokBookdetailCurrent;
    
    /**
     * 账务明细（历史表）
     */
    private List<ValBokBookdetailAllExt> listValBokBookdetailAll;
    
    /**
     * 凭证编号
     */
    private String  bookId;

    
    /**
     * 获取 listValBokBookdetailCurrent
     * @return listValBokBookdetailCurrent.
     */
    public List<ValBokBookdetailCurrentExt> getListValBokBookdetailCurrent() {
        return listValBokBookdetailCurrent;
    }

    
    /**
     * 设置 listValBokBookdetailCurrent
     * @param listValBokBookdetailCurrent listValBokBookdetailCurrent
     */
    public void setListValBokBookdetailCurrent(List<ValBokBookdetailCurrentExt> listValBokBookdetailCurrent) {
        this.listValBokBookdetailCurrent = listValBokBookdetailCurrent;
    }

    
    /**
     * 获取 listValBokBookdetailAll
     * @return listValBokBookdetailAll.
     */
    public List<ValBokBookdetailAllExt> getListValBokBookdetailAll() {
        return listValBokBookdetailAll;
    }

    
    /**
     * 设置 listValBokBookdetailAll
     * @param listValBokBookdetailAll listValBokBookdetailAll
     */
    public void setListValBokBookdetailAll(List<ValBokBookdetailAllExt> listValBokBookdetailAll) {
        this.listValBokBookdetailAll = listValBokBookdetailAll;
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


    
}
