/*
 * 文件名：ProfitDistributionResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 利润分配批处理返回结果集
 * 
 * @author weipeng
 * @version 1.0 2018年8月29日
 * @see ProfitDistributionResult
 * @since 1.0
 */
public class ProfitDistributionResult extends NeedToWriteEntity {

    /**
     * 记账凭证生成结果
     */
    private BookGenerateResult bookGenerateResult;

    /**
     * 当日账套资产负债信息
     */
    private List<BokStatBokPosition> listBokStatBokPosition;

    /**
     * 获取 记账凭证生成结果
     * 
     * @return 记账凭证生成结果
     */
    public BookGenerateResult getBookGenerateResult() {
        return bookGenerateResult;
    }

    /**
     * 设置 记账凭证生成结果
     * 
     * @param returnResult 记账凭证生成结果
     */
    public void setBookGenerateResult(BookGenerateResult bookGenerateResult) {
        this.bookGenerateResult = bookGenerateResult;
    }

    /**
     * 获取 当日账套资产负债信息
     * 
     * @return 当日账套资产负债信息
     */
    public List<BokStatBokPosition> getListBokStatBokPosition() {
        return listBokStatBokPosition;
    }

    /**
     * 设置 当日账套资产负债信息
     * 
     * @param listBokStatBokPosition 当日账套资产负债信息
     */
    public void setListBokStatBokPosition(List<BokStatBokPosition> listBokStatBokPosition) {
        this.listBokStatBokPosition = listBokStatBokPosition;
    }

}
