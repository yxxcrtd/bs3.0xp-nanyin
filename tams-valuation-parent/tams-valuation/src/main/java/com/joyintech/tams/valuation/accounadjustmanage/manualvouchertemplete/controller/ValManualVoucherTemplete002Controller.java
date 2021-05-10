/*
 * 文件名：ValManualVoucher002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete001Service;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 模板示例<br>
 * 手工凭证模板详情查看
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete002Controller
 * @since 2.0
 */
@Controller
@PageId("ValManualVoucherTemplete002")
@RequestMapping("/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002")
public class ValManualVoucherTemplete002Controller extends BaseController {

    /**
     * ValManualVoucherTemplete002Service
     */
    @Autowired
    private ValManualVoucherTemplete002Service valManualVoucherTemplete002Service;
    
    /**
     * ValManualVoucherTemplete001Service
     */
    @Autowired
    private ValManualVoucherTemplete001Service valManualVoucherTemplete001Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String tmpCode = this.getParam("tmpCode");
        model.addAttribute("tmpCode", tmpCode);
        
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if(StringUtil.isNotEmpty(workflowId)){
            ValManualVoucherTemplete001Dto entity= valManualVoucherTemplete001Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("tmpCode", entity.getTmpCode());
        }

        return super.init(model);
    }

    /**
     * 根据模板编号获取对应相同编号的所有数据
     *
     * @return 查询数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValManualVoucherTemplete002Dto> searchList() {
        // 模板编号
        String tmpCode = this.getParam("tmpCode");
        return valManualVoucherTemplete002Service.searchList(tmpCode);
    }
    
}
