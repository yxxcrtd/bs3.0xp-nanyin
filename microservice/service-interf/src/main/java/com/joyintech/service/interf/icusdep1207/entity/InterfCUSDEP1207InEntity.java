/*
 * 文件名：InterfCUSDEP207InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年2月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icusdep1207.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.order.dto.CusdepOrderDto;

/**
 * 接口CUSDEP1207-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年2月13日
 * @see InterfCUSDEP1207InEntity
 * @since 1.0
 */
public class InterfCUSDEP1207InEntity {

    /**
     * 托管网银指令接口参数
     */
    private CusdepOrderDto dto;

    /**
     * 获取 托管网银指令接口参数
     * 
     * @return 托管网银指令接口参数.
     */
    public CusdepOrderDto getDto() {
        return dto;
    }

    /**
     * 设置 托管网银指令接口参数
     * 
     * @param dto 托管网银指令接口参数
     */
    public void setDto(CusdepOrderDto dto) {
        this.dto = dto;
    }

    /**
     * 取得 成交单编号
     * 
     * @return 成交单编号
     */
    @EsbName(value = "TURNOVER_RECEIPT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getNo() {
        return dto == null ? StringUtil.EMPTY : dto.getOrderNo();
    }

    /**
     * 取得 确认标志
     * 
     * @return 确认标志
     */
    @EsbName(value = "CONFIRM_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public String getFlag() {
        return dto == null ? StringUtil.EMPTY : dto.getConfirmFlag();
    }

    /**
     * 取得 确认方向
     * 
     * @return 确认方向
     */
    @EsbName(value = "CONFIRM_DIRECTION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getDirection() {
        return dto == null ? StringUtil.EMPTY : dto.getConfirmDirection();
    }

    /**
     * 取得 指令确认日期
     * 
     * @return 指令确认日期
     */
    @EsbName(value = "ORDER_CONFIRM_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDate() {
        return dto == null ? StringUtil.EMPTY : dto.getConfirmDate();
    }

    /**
     * 取得 账号
     * 
     * @return 账号
     */
    @EsbName(value = "ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getAcct() {
        return dto == null ? StringUtil.EMPTY : dto.getAcctNo();
    }

    /**
     * 取得 附件名称
     * 
     * @return 附件名称
     */
    @EsbName(value = "AFFIX_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getAffix() {
        return dto == null ? StringUtil.EMPTY : dto.getAffixName();
    }

    /**
     * 取得 确认拒绝原因
     * 
     * @return 确认拒绝原因
     */
    @EsbName(value = "CONFIRM_REJECT_REASON")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getReason() {
        return dto == null ? StringUtil.EMPTY : dto.getReason();
    }

    /**
     * 设置 成交单编号
     * 
     * @param no 成交单编号
     */
    @EsbName(value = "TURNOVER_RECEIPT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setNo(String no) {
        if (dto == null) {
            dto = new CusdepOrderDto();
        }
        dto.setOrderNo(no);
    }
}
