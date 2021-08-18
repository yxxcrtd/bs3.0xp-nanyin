package com.joyintech.tams.invest.risk.namelistmanage.relatedparty.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;
import com.joyintech.tams.invest.risk.namelistmanage.relatedparty.service.RskRelatedParty001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @Classname RsKRelatedParty002Controller
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/5 20:50 
 * @version 1.0 
 */

@Controller
@PageId("RskRelatedParty002")
@RequestMapping("/invest/risk/namelistmanage/relatedparty/RskRelatedParty002")
public class RsKRelatedParty002Controller extends BaseController {

    @Autowired
    RskRelatedParty001Service rskRelatedParty001Service;

    @Override
    @RequestMapping("init")
    public String init(Model model) {
        String instituteCode = getParam("instituteCode");
        String instituteName = getParam("instituteName");
        String limitAmt = getParam("limitAmt");
         if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(instituteCode)){
             RskRelatedPartyDto entity = new RskRelatedPartyDto();
             entity.setInstituteCode(instituteCode);
             entity.setInstituteName(instituteName);
             entity.setLimitAmt(new BigDecimal(limitAmt));
             model.addAttribute("entity",entity);
         }
        return super.init(model);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) RskRelatedPartyDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     *
     * @Description //修改功能
     * @Param [dto]
     * @return com.joyintech.framework.ajax.AjaxResult
     * @version 1.0
     * @see
    **/
    @ResponseBody
    @RequestMapping("edit")
    public AjaxResult edit(@RequestBody RskRelatedPartyDto dto) {
        rskRelatedParty001Service.edit(dto);
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }

}
