/*
 * 文件名：CalCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.dto.calc;

import java.math.BigDecimal;
import java.util.List;

/**
 * 通用计算结果返回
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see CalCommonResult
 * @since 1.0
 */
public class CalCommonResult {
    
    /**
     * 分段明细列表
     */
    private List<SecIntRange> secIntRangeList;
    
    /**
     * 总利息金额
     */
    private BigDecimal totalInterestAmt;

    
    /**
     * 获取 分段明细列表
     * @return 分段明细列表.
     */
    public List<SecIntRange> getSecIntRangeList() {
        return secIntRangeList;
    }

    
    /**
     * 设置 分段明细列表
     * @param secIntRangeList 分段明细列表
     */
    public void setSecIntRangeList(List<SecIntRange> secIntRangeList) {
        this.secIntRangeList = secIntRangeList;
    }

    
    /**
     * 获取 总利息金额
     * @return 总利息金额.
     */
    public BigDecimal getTotalInterestAmt() {
        return totalInterestAmt;
    }

    /**
     * 设置 总利息金额
     * @param totalInterestAmt 总利息金额
     */
    public void setTotalInterestAmt(BigDecimal totalInterestAmt) {
        this.totalInterestAmt = totalInterestAmt;
    }
}
