/*
 * 文件名：ValBokBalanceGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbokbalance.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.ValBokBalanceDto;

/**
 * 科目余额生成结果
 * 
 * @author weipeng
 * @version 1.0 2018年5月29日
 * @see ValBokBalanceGenerateResult
 * @since 1.0
 */
public class ValBokBalanceGenerateResult extends NeedToWriteEntity {

    /**
     * 余额明细
     */
    private List<ValBokBalanceDto> valBokBalanceBatch;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private Date bookDate;
    
    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 产品名称
     */
    private String finprodName;
    
    /**
     * 收益类型
     */
    private String profitType;
    
    
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
    /**
     * 取得 科目余额明细
     * 
     * @return 余额明细
     */
	public List<ValBokBalanceDto> getValBokBalanceBatch() {
		return valBokBalanceBatch;
	}
    
	/**
     * 设置 科目余额明细
     * 
     * @param valBokBalanceBatch
     *            余额明细
     */
	public void setValBokBalanceBatch(List<ValBokBalanceDto> valBokBalanceBatch) {
		this.valBokBalanceBatch = valBokBalanceBatch;
	}

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }


    
    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
    
    
}
