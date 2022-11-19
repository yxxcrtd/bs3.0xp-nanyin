package com.joyintech.tams.disclosure.information.config.investmentInfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.service.InvestmentInfo001Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@PageId("InvestmentInfo002")
@Controller
@RequestMapping("/disclosure/information/config/investmentInfo/InvestmentInfo002")
public class InvestmentInfo002Controller extends BaseController {
    @Autowired
    private InvestmentInfo001Service investmentInfo001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        this.setSelectOptionToModel(model, DictCode.前十大投资者信息类别);
        this.setSelectOptionToModel(model, DictCode.前十大投资者信息指标类型);
        String id = getParam("id");
        HashMap map = new HashMap();
        map.put("id",id);
        if (StringUtil.isNotEmpty(id)) {
            // 根据主键获取业务实体
            InvestmentInfo001Dto entity = investmentInfo001Service.selectByPrimaryKey(map);
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) InvestmentInfo001Dto dto, BindingResult result) {
        if (result.hasErrors()) {
            return new AjaxResult(result);
        }
        return commonCheck(result, dto.getOperateType());
    }

    @ResponseBody
    @RequestMapping("findProdId")
    public List<SelectOptionDto> findProdId() {
        return investmentInfo001Service.findProdId(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) InvestmentInfo001Dto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());
        ProcessParameter<InvestmentInfo001Dto> processParameter = new ProcessParameter<InvestmentInfo001Dto>();
        WorkFlowParameter<InvestmentInfo001Dto> params = new WorkFlowParameter<InvestmentInfo001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        //params.setBusinessCode(item -> item.getSubjectNo());
        params.setBusinessType(DictCodeListExt3.共通业务类型_前十大投资者信息维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        /*params.setComment(
                entity -> "科目号"+ "(" + entity.getSubjectNo()+ ")"
                        +  "-" + "产品主代码"+ "(" + entity.getBooksetId() + ")"
                        +  "-" + "开始日期"+ "(" + entity.getDateStartStr() + ")"
                        +  "-" + "结束日期"+ "(" + entity.getDateEndStr() + ")");*/
        processParameter.setWorkFlowParameter(params);
        dto.setFlag(false);
        String msg = investmentInfo001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
