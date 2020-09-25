/*
 * 文件名：ValFinprodTradeQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.trademanage.finprodtradequery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.productinfoquery.service.ValProductInfoQuery001Service;
import com.joyintech.service.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.service.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 金融产品交易删除
 * 
 * @author QuJianZhong
 * @version 1.0 2018年11月30日
 * @see ValFinprodTradeQuery002Contoller
 * @since 1.0
 */
@RestController(value="serviceValFinprodTradeQuery002Contoller")
@RequestMapping("/ValFinprodTradeQuery002")
public class ValFinprodTradeQuery002Contoller extends BaseController {

    /**
     * ValFinprodTradeQuery001Service
     */
    @Autowired
    private ValFinprodTradeQuery001Service valFinprodTradeQuery001Service;

    @RequestMapping("searchList")
    @ResponseBody
    public List<ValFinprodTradeQuery001Dto> searchList() {
        List<ValFinprodTradeQuery001Dto> list = new ArrayList<ValFinprodTradeQuery001Dto>();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            ValFinprodTradeQuery001Dto dto = valFinprodTradeQuery001Service.getBussinessEntityByWorkflowId(workflowId);
            if (dto != null) {
                list.add(dto);
            }
        }
        return list;
    }
}
