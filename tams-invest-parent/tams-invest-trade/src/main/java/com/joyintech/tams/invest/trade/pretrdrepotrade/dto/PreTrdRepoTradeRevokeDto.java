/*
 * 文件名：PreTrdRepoTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdrepotrade.dto;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.trade.trdrepotrade.validator.TrdRepoTradeRevokeDtoValidator;

/**
 * 质押式回购预录入交易撤销用dto
 * @author 王伟
 * @version 1.0 2019年12月30日
 * @see PreTrdRepoTradeRevokeDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdRepoTradeRevokeDtoValidator.class)
public class PreTrdRepoTradeRevokeDto {

    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 取得 汇总交易编号
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    /**
     * 获取 操作类型
     * @return 操作类型.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

}
