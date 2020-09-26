/*
 * 文件名：VatBatchResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.TaxBokBalance;
import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.common.base.entity.TaxBokDetail;
import com.joyintech.tams.common.base.entity.TaxDeductedBalance;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 增值税计提批处理结果集
 * @author weipeng
 * @version 1.0 2018年8月7日
 * @see VatBatchResult
 * @since 1.0
 */
public class VatBatchResult extends NeedToWriteEntity {
    /**
     * 账务明细（当前表）+科目明细
     */
    private BookGenerateResult bookGenerateResult = new  BookGenerateResult();
    
    /**
     * 增值税汇总余额
     */
    private List<TaxBokBalanceSum> listTaxBokBalanceSum = new ArrayList<TaxBokBalanceSum>();
    
    /**
     * 增值税账务明细余额
     */
    private List<TaxBokBalance> listTaxBokBalance = new ArrayList<TaxBokBalance>();

    /**
     * 增值税账务明细流水
     */
    private List<TaxBokDetail> listValTaxBokDetail = new ArrayList<TaxBokDetail>();
    
    /**
     * 增值税已抵扣增值额
     */
    private List<TaxDeductedBalance> listTaxDeductedBalance = new ArrayList<TaxDeductedBalance>();
    
    
    /**
     * 获取 账务明细（当前表）+科目明细
     * @return  账务明细（当前表）+科目明细
     */
    public BookGenerateResult getBookGenerateResult() {
        return bookGenerateResult;
    }


    
    /**
     * 设置 账务明细（当前表）+科目明细
     * @param bookGenerateResult 账务明细（当前表）+科目明细
     */
    public void setBookGenerateResult(BookGenerateResult bookGenerateResult) {
        this.bookGenerateResult = bookGenerateResult;
    }


    /**
     * 获取 增值税汇总余额
     * @return 增值税汇总余额
     */
    public List<TaxBokBalanceSum> getListTaxBokBalanceSum() {
        return listTaxBokBalanceSum;
    }

    
    /**
     * 设置 增值税汇总余额
     * @param listTaxBokBalanceSum 增值税汇总余额
     */
    public void setListTaxBokBalanceSum(List<TaxBokBalanceSum> listTaxBokBalanceSum) {
        this.listTaxBokBalanceSum = listTaxBokBalanceSum;
    }


    
    /**
     * 获取 增值税账务明细余额
     * @return 增值税账务明细余额
     */
    public List<TaxBokBalance> getListTaxBokBalance() {
        return listTaxBokBalance;
    }


    
    /**
     * 设置 增值税账务明细余额
     * @param listTaxBokBalance 增值税账务明细余额
     */
    public void setListTaxBokBalance(List<TaxBokBalance> listTaxBokBalance) {
        this.listTaxBokBalance = listTaxBokBalance;
    }


    
    /**
     * 获取 增值税账务明细流水
     * @return 增值税账务明细流水
     */
    public List<TaxBokDetail> getListValTaxBokDetail() {
        return listValTaxBokDetail;
    }


    
    /**
     * 设置 增值税账务明细流水
     * @param listValTaxBokDetail 增值税账务明细流水
     */
    public void setListValTaxBokDetail(List<TaxBokDetail> listValTaxBokDetail) {
        this.listValTaxBokDetail = listValTaxBokDetail;
    }


    /**
     * 取得 增值税已抵扣增值额
     * @return 增值税已抵扣增值额
     */
	public List<TaxDeductedBalance> getListTaxDeductedBalance() {
		return listTaxDeductedBalance;
	}


	/**
	 * 设置 增值税已抵扣增值额
	 * @param listTaxDeductedBalance 增值税已抵扣增值额
	 */
	public void setListTaxDeductedBalance(List<TaxDeductedBalance> listTaxDeductedBalance) {
		this.listTaxDeductedBalance = listTaxDeductedBalance;
	}
}
