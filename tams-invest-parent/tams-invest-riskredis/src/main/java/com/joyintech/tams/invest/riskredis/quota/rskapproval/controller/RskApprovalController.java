/*
 * 文件名：RskApprovalController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.rskapproval.controller;

import java.util.Date;

import javax.validation.Valid;

import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;

/**
 * 审批Controller
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalController
 * @since 1.0
 */
public abstract class RskApprovalController extends BaseController {

    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

    /**
     * 画面内容check
     * 
     * @param dto 画面内容
     * @param br check结果
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid RskApproval001Dto dto, BindingResult br) {
        return commonCheck(br, dto.getOperateType());
    }

    /**
     * 取得 业务类型
     * 
     * @param assetType 资产大类
     * @param ps 交易方向
     * @param eventType 0:精确委托、1:投资交易、2:交易拆分、3:模糊委托
     * @return 业务类型
     * @see
     * @since 1.0
     */
    protected String getBusinessType(String assetType, String ps, String eventType) {
        StringBuilder builder = new StringBuilder("RSK_APR_");
        if (StringUtil.equals(eventType, "0") || StringUtil.equals(eventType, "1")
            || StringUtil.equals(eventType, "2")) {
            builder.append(assetType).append(GlobalConstant.UNDER_LINE);
        }
        builder.append(eventType);
        return builder.toString();
    }

    /**
     * 取得 平台日期
     * 
     * @return 平台日期
     */
    public Date getSysDate(){
        return platformDateService.getSysDate();
    }
}
