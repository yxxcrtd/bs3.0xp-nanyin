/*
 * 文件名：TrdRepoTradeRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.trade.buyoutrepo.validator.TrdOutRightRepoTradeRevokeValidator;

/**
 * 回购投资交易撤销用dto
 *
 * @author 裴家俊
 * @version 1.0 2018年11月12日
 * @see TrdOutRightRepoTrade005Dto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdOutRightRepoTradeRevokeValidator.class)
public class TrdOutRightRepoTrade005Dto {

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 获取 tradeId
     *
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     *
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 operateType
     *
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

}
