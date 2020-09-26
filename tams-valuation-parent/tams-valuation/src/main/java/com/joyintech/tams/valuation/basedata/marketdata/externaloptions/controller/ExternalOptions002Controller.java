package com.joyintech.tams.valuation.basedata.marketdata.externaloptions.controller;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.externaloptions.service.ExternalOptions002Service;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-27 10:58
 */
@Controller
@PageId("ExternalOptions002")
@RequestMapping("/valuation/basedata/marketdata/externaloptions/ExternalOptions002")
public class ExternalOptions002Controller extends BaseController {

    @Autowired
    ExternalOptions002Service externalOptions002Service;

    @Override
    public String init(Model model) {

        String finprodId2 = getParam("finprodId2");
        ExternalOptions002Dto dto = new ExternalOptions002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(finprodId2)) { // 查看 、 修改
            Map map = RequestUtil.getPageParam();
            dto = externalOptions002Service.findByPrimaryKey(RequestUtil.getPageParam());
            //String finprodId = externalOptions002Service.findByBenchmarkId(dto.getFinprodId2());
            //dto.setFinprodId(finprodId);
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = externalOptions002Service.getBussinessEntityByWorkflowId(workflowId);
            //String finprodId = externalOptions002Service.findByBenchmarkId(dto.getFinprodId2());
            //dto.setFinprodId(finprodId);
        }


        model.addAttribute(GlobalConstant.ENTITY, dto);

        super.setSelectOptionToModel(model, DictCode.币种);
        super.setSelectOptionToModel(model, DictCode.估值来源);
        super.setSelectOptionToModel(model, DictCode.估值方法);
        super.setSelectOptionToModel(model, DictCode.维护方式);
        super.setSelectOptionToModel(model, DictCode.金融产品类型);
        return super.init(model);

    }


    @RequestMapping("/getFinprodId")
    @ResponseBody
    public List<SuggestDto> getFinprodId() {
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BENCHMARK_TYPE2",str);
        return externalOptions002Service.getFinprodId(param);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ExternalOptions002Dto dto, BindingResult bindingResult)
            throws Exception {
        String operateType = dto.getOperateType();

        if (!bindingResult.hasErrors()) {
            if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
                // 判断数据库是否存在：主键check
                Map<String, Object> map = Maps.newHashMap();
                map.put("finprodId2", dto.getFinprodId2());
                map.put("valDate", dto.getValDate());
                //  map.put("valmodelId", dto.getValmodelId());
                ExternalOptions002Dto entity = externalOptions002Service.findByPrimaryKey(map);
                if (entity != null) {
                    return new AjaxResult("已存在合约标的_交易编号_估值日相同的数据，请确保数据唯一性。", MessageType.错误消息);
                }
                map.put("branch", 0);
                ExternalOptions002Dto dateCheckDto = externalOptions002Service.dateCheck(map);
                if (dto.getValDate().compareTo(dateCheckDto.getVdate()) < 0 || dto.getValDate().compareTo(dateCheckDto.getMdate()) >= 0) {
                    return new AjaxResult("估值日必须介于交易起息日(含)和到期日（不含）之间", MessageType.错误消息);
                }
            }
        }

        return commonCheck(bindingResult, operateType);
    }

    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ExternalOptions002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<ExternalOptions002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ExternalOptions002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场外期权.getValue());// 业务类型
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment("场外期权", entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            externalOptions002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动外汇估值信息流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }

}
