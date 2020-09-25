/*
 * 文件名：FinProductDurDealCashCalcRuleDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.dto;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;

/**
 * 现金流计算规则表相关信息dto
 *
 * @author etliu
 * @version 1.0 2018年12月24日
 * @see FinProductDurDealCashCalcRuleDto
 * @since 1.0
 */
public class FinProductDurDealCashCalcRuleDto extends FinCashCalcRule {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1086474446485972265L;
    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 获取 pFinprodId
     *
     * @return pFinprodId.
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 pFinprodId
     *
     * @param pFinprodId pFinprodId
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

}
