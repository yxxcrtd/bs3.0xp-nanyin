/*
 * 文件名：ValMstCapAcctInfo002Contoller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.service.ValMstCapAcctInfoService;

@Controller
@PageId("ValMstCapAcctInfo002")
@RequestMapping("/valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo002")
public class ValMstCapAcctInfo002Contoller extends BaseController {

//    /**
//     * <意义，目的和功能，以及可能被用到的地方>
//     */
//    @Autowired
//    private ValMstCapAcctInfoMapper valMstCapAcctInfoMapper;
    
    @Autowired
    private ValMstCapAcctInfoService valMstCapAcctInfoService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种, DictCode.是否, DictCode.境内外, DictCode.行内行外);
        String acctNo = getParam("acctNo");
        if (StringUtil.isNotEmpty(acctNo)) {
            ValMstCapAcctInfoDto dto = valMstCapAcctInfoService.queryValMstAcctInfoByAcctno(acctNo);
            String dealType = getParam("dealType");
            if (StringUtil.isNotEmpty(acctNo)) {
                // 由修改页面点击而来
                dto.setDealType(dealType);
            }
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody ValMstCapAcctInfoDto dto) {
        String msg = "";
        msg = valMstCapAcctInfoService.save(dto);
        return new AjaxResult(msg);
    }
}
