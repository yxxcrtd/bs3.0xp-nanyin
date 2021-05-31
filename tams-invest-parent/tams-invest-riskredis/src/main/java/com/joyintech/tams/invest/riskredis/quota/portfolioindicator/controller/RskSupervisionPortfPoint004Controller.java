package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.controller;

import java.io.IOException;
import java.util.List;

import com.google.json.JsonSanitizer;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 编辑指标有效区间
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPoint004Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionPortfPoint004")
@RequestMapping("/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004")
public class RskSupervisionPortfPoint004Controller extends BaseController {

    /**
     * RskSupervisionPortfPoint002Service
     */
    @Autowired(required = false)
    private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        RskPortfolioIndicator002Dto rskPortfolioIndicator002Dto = new RskPortfolioIndicator002Dto();
        // 待办跳转
        if (StringUtil.isNotEmpty(getParam("workflowId")) && !WorkFlowUtil.isNotFromToDoList()) {
            String workflowId = getParam("workflowId");
            model.addAttribute("workflowId", workflowId);
            rskPortfolioIndicator002Dto = rskSupervisionPortfPoint002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute(GlobalConstant.ENTITY, rskPortfolioIndicator002Dto);

            model.addAttribute("formTodoList", true);
        }
        else if (StringUtil.isNotEmpty(this.getParam("portfolioId"))
                 && (getParam("pageModel").equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
                     || getParam("pageModel").equals(
                         GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE))) {
            rskPortfolioIndicator002Dto = rskSupervisionPortfPoint002Service.getBussinessEntityByPortfId(
                this.getParam("portfolioId"), this.getParam("indId"));
            model.addAttribute(GlobalConstant.ENTITY, rskPortfolioIndicator002Dto);
        }
        else {
            rskPortfolioIndicator002Dto.setPortfolioId(getParam("portfolioId"));
            rskPortfolioIndicator002Dto.setPortfolioName(getParam("portfolioName"));
            rskPortfolioIndicator002Dto.setIndName(getParam("indName"));
            model.addAttribute(GlobalConstant.ENTITY, rskPortfolioIndicator002Dto);
        }
        if (StringUtil.equals(rskPortfolioIndicator002Dto.getPortfolioLevel(),
            BussinessDictCodeListRds.组合层次_组合包.getValue())) {
            model.addAttribute("portfolioMixName",
                rskSupervisionPortfPoint002Service.getPortfolioConst(
                    rskPortfolioIndicator002Dto.getPortfolioId()));
        }
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     * 
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskPortfolioIndicator002Dto dto, BindingResult result) {
        AjaxResult ajaxResult = commonCheck(result, dto.getOperateType());
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(dto.getPortfolioId())) {
            List<ValidMessage> returnMes = checkWorkFlowDataPointIndUpd(dto,
                ajaxResult.getErrorList());
            ajaxResult.setErrorList(returnMes);
        }
        return ajaxResult;
    }

    /**
     * 检查修改指标关联组合的有效区间是否再次发生
     */
    private List<ValidMessage> checkWorkFlowDataPointIndUpd(RskPortfolioIndicator002Dto dto,
                                                            List<ValidMessage> resultList) {
        // 判断指标关联组合流程中是否存在相同的组合数据
        WflValidInputParameter param = new WflValidInputParameter();
        param.setBusinessDataPk1(dto.getPortfolioId());
        param.setPageId("RskSupervisionPortfPoint004");
        if (null != mstWorkflowCommonService) {
            ProcessMark processMark = mstWorkflowCommonService.getDataEntityFromProcessMark(param);
            if (StringUtil.isNotEmpty(processMark)
                && StringUtil.isNotEmpty(processMark.getBusinessData())) {
                try {
                    RskPortfolioIndicator002Dto havRsk002Dto = JsonUtils.OBJECT_MAPPER.readValue(
                            JsonSanitizer.sanitize(processMark.getBusinessData()), RskPortfolioIndicator002Dto.class);
                    if (havRsk002Dto.getIndId().equals(dto.getIndId())) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012",
                            dto.getPortfolioName() + GlobalConstant.COLON + dto.getIndName())));
                    }

                }
                catch (IOException e) {
                    resultList.add(new ValidMessage(e.getMessage()));
                }
            }
        }
        return resultList;
    }

    /**
     * 保存
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskPortfolioIndicator002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_限额组合关联监督指标管理);
        ProcessParameter<RskPortfolioIndicator002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskPortfolioIndicator002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 指标编号作为业务编号
        String businessCode = dto.getIndId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_限额组合关联监督指标管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionPortfPoint004.indName"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getIndName(),
                GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionPortfPoint004.portfolioName"),
                GlobalConstant.OPEN_PARENTHESIS, entity.getPortfolioName(),
                GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionPortfPoint002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
