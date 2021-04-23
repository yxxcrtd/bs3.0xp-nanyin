/*
 * 文件名：ProfitDistributionNetResult.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1
 * 修改人：WUJIE
 * 修改时间：2020年4月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1
 */
package com.joyintech.tams.valuation.batch.profitdistributionnet.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokStatBokPosition;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 净值型产品润分配批处理返回结果集
 * 
 * @author WUJIE
 * @version 1.0 2020年4月1日
 * @see ProfitDistributionNetResult
 * @since 1.0
 */
public class ProfitDistributionNetResult extends NeedToWriteEntity {

    /**
     * 记账凭证生成结果
     */
    private BookGenerateResult bookGenerateResult;

    /**
     * 账务汇总表（账套）
     */
    private BokStatBokPosition bokStatBokPosition;

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
    
    public BokStatBokPosition getBokStatBokPosition() {
        return bokStatBokPosition;
    }

    public void setBokStatBokPosition(BokStatBokPosition bokStatBokPosition) {
        this.bokStatBokPosition = bokStatBokPosition;
    }
    
    
    
    

}
