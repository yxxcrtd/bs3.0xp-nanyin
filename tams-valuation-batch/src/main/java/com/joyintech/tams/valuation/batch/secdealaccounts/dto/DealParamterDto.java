/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.secdealaccounts.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 交易金额计算参数
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see DealParamterDto
 * @since 1.0
 */
public class DealParamterDto {

    /**
     * 变量.成本科目余额
     */
    private BigDecimal varCostSubjectAmt;

    /**
     * 变量.成本科目数量
     */
    private BigDecimal varCostSubjectNum;

    /**
     * 变量.应计利息科目余额
     */
    private BigDecimal varLxSubjectAmt;

    /**
     * 变量.公允价值变动科目余额
     */
    private BigDecimal varGySubjectAmt;

    /**
     * 变量.溢折价科目余额
     */
    private BigDecimal varYzjSubjectAmt;

    /**
     * 变量.其他应收款-利息科目余额 = MAP（KEY：分支序号，VALUE:科目余额）
     */
    private Map<String, BigDecimal> varQtyskxSubjectAmt;

    /**
     * 变量.收息已结清份额 = MAP（KEY：分支序号，VALUE:收息已结清份额）
     */
    private Map<String, BigDecimal> cleanShareMap;
    
    /**
     * 日期重算集合
     * ADD BY WUJIE ON 20191216
     */
    private Map<Date,Boolean> retryDateMap;

    /**
     * 获取 变量.成本科目余额
     * 
     * @return varCostSubjectAmt.
     */
    public BigDecimal getVarCostSubjectAmt() {
        return varCostSubjectAmt;
    }

    /**
     * 设置 变量.成本科目余额
     * 
     * @param varCostSubjectAmt 变量.成本科目余额
     */
    public void setVarCostSubjectAmt(BigDecimal varCostSubjectAmt) {
        this.varCostSubjectAmt = varCostSubjectAmt;
    }

    /**
     * 获取 变量.成本科目数量
     * 
     * @return varCostSubjectNum.
     */
    public BigDecimal getVarCostSubjectNum() {
        return varCostSubjectNum;
    }

    /**
     * 设置 变量.成本科目数量
     * 
     * @param varCostSubjectNum 变量.成本科目数量
     */
    public void setVarCostSubjectNum(BigDecimal varCostSubjectNum) {
        this.varCostSubjectNum = varCostSubjectNum;
    }

    /**
     * 获取 变量.应计利息科目余额
     * 
     * @return varLxSubjectAmt.
     */
    public BigDecimal getVarLxSubjectAmt() {
        return varLxSubjectAmt;
    }

    /**
     * 设置 变量.应计利息科目余额
     * 
     * @param varLxSubjectAmt 变量.应计利息科目余额
     */
    public void setVarLxSubjectAmt(BigDecimal varLxSubjectAmt) {
        this.varLxSubjectAmt = varLxSubjectAmt;
    }

    /**
     * 获取 变量.公允价值变动科目余额
     * 
     * @return varGySubjectAmt.
     */
    public BigDecimal getVarGySubjectAmt() {
        return varGySubjectAmt;
    }

    /**
     * 设置 变量.公允价值变动科目余额
     * 
     * @param varGySubjectAmt 变量.公允价值变动科目余额
     */
    public void setVarGySubjectAmt(BigDecimal varGySubjectAmt) {
        this.varGySubjectAmt = varGySubjectAmt;
    }

    /**
     * 获取 变量.溢折价科目余额
     * 
     * @return varYzjSubjectAmt.
     */
    public BigDecimal getVarYzjSubjectAmt() {
        return varYzjSubjectAmt;
    }

    /**
     * 设置 变量.溢折价科目余额
     * 
     * @param varYzjSubjectAmt 变量.溢折价科目余额
     */
    public void setVarYzjSubjectAmt(BigDecimal varYzjSubjectAmt) {
        this.varYzjSubjectAmt = varYzjSubjectAmt;
    }

    /**
     * 获取 变量.其他应收款-利息科目余额
     * 
     * @return varQtyskxSubjectAmt.
     */
    public Map<String, BigDecimal> getVarQtyskxSubjectAmt() {
        return varQtyskxSubjectAmt;
    }

    /**
     * 设置 变量.其他应收款-利息科目余额
     * 
     * @param varQtyskxSubjectAmt 变量.其他应收款-利息科目余额
     */
    public void setVarQtyskxSubjectAmt(Map<String, BigDecimal> varQtyskxSubjectAmt) {
        this.varQtyskxSubjectAmt = varQtyskxSubjectAmt;
    }

    /**
     * 获取 收息已结清份额
     * 
     * @return 收息已结清份额
     */
    public Map<String, BigDecimal> getCleanShareMap() {
        return cleanShareMap;
    }

    /**
     * 设置 收息已结清份额
     * 
     * @param cleanShareMap 收息已结清份额
     */
    public void setCleanShareMap(Map<String, BigDecimal> cleanShareMap) {
        this.cleanShareMap = cleanShareMap;
    }
    
    
    public Map<Date, Boolean> getRetryDateMap() {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		return retryDateMap;
	}



	public void setRetryDateMap(Map<Date, Boolean> retryDateMap) {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		this.retryDateMap = retryDateMap;
	}

}
