package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.dto.ValElectReconciliation002Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valelectreconciliation.service.ValElectReconciliation002Service;

/**
 * 电子对账Controller 〈功能详细描述〉
 * 
 * @author tanxuan
 * @version 1.0 2019年12月9日
 * @see ValElectReconciliation002Controller
 * @since 1.0
 */
@Controller
@PageId("ValElectReconciliation002")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation002")
public class ValElectReconciliation002Controller extends BaseController {

    @Autowired
    private ValElectReconciliation002Service valElectReconciliation002Service;

    @Override
    public String init(Model model) {
        String serialNo = getParam("serialNo");
        String interType = getParam("interType");
        ValElectReconciliation002Dto dto = new ValElectReconciliation002Dto();
        dto.setSerialNo(serialNo);
        dto.setInterType(interType);
        model.addAttribute("entity", dto);
        this.setSelectOptionToModel(model, DictCode.科目级别);
        return super.init(model);
    }

    /**
     * 对账明细 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValElectReconciliation002Dto> findPage()
        throws Exception {
        Page<ValElectReconciliation002Dto> page = valElectReconciliation002Service.findPage(
            RequestUtil.getPageParam());
        return page;
    }

}
