/*
 * 文件名：PtlDvpFundAllocationRevokeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.validate.PtlChlFundAllocation001RevokeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

/**
 * 通道资金划拨管理撤销用dto
 *
 * @author etliu
 * @version 1.0 2019年4月3日
 * @see PtlChlFundAllocationRevokeDto
 * @since 1.0
 */
@PageId("PtlChlFundAllocation002")
@ValidLogic(targetClass = PtlChlFundAllocation001RevokeValidator.class)
public class PtlChlFundAllocationRevokeDto extends StlPayOrders {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -703663081038987952L;

    /**
     * 指令编号
     */
    @PrimaryKey(Field.PK_1)
    private String orderId;

    /**
     * 节点ID
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 获取 orderId
     * 
     * @return orderId.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 orderId
     * 
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
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
