/*
 * 文件名：ValVoucherQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.voucherquery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery003SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.service.ValVoucherQuery003Service;
import com.joyintech.tams.common.base.entity.BokVouchMain;

/**
 * 模板示例<br>
 * 凭证详情查看
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery003Controller
 * @since 2.0
 */
@Controller
@PageId("ValVoucherQuery003")
@RequestMapping("/valuation/accounadjustmanage/voucherquery/ValVoucherQuery003")
public class ValVoucherQuery003Controller extends BaseController {

    @Autowired
    private ValVoucherQuery003Service valVoucherQuery003Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String vouchNum = this.getParam("vouchNum");
        String vouchRemark = "";
        if(StringUtil.isNotEmpty(vouchNum)){
            BokVouchMain valVouchMain = valVoucherQuery003Service.getByVouchNum(vouchNum);
            vouchRemark = valVouchMain.getVouchRemark();
        }
        model.addAttribute("vouchNum",vouchNum);
        model.addAttribute("vouchRemark", vouchRemark);
        return super.init(model);
    }
    
    /**
     * 修改凭证摘要
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("updateVouch")
    public AjaxResult updateVouch(@Valid @RequestBody ValVoucherQuery003SearchDto dto,
            BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        valVoucherQuery003Service.updateVouch(dto.getVouchNum(), dto.getVouchRemark());
        return new AjaxResult(getMessage("COMMON_I_0001"));
    }
}
