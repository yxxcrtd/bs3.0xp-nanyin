package com.joyintech.tams.common.basicdata.marketdata.externaloptions.controller;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.service.ComExternalOptions002Service;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.common.basicdata.marketdata.externaloptions.dto.ComExternalOptions002Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
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

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 12-27 10:58
 */
@Controller
@PageId("ComExternalOptions002")
@RequestMapping("/common/basicdata/marketdata/externaloptions/ComExternalOptions002")
public class ComExternalOptions002Controller extends BaseController {

    @Autowired
    ComExternalOptions002Service comExternalOptions002Service;

    @Override
    public String init(Model model) {

        String finprodId2 = getParam("finprodId2");
        ComExternalOptions002Dto dto = new ComExternalOptions002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID

        if (StringUtils.isNotEmpty(finprodId2)) { // 查看 、 修改
            Map map = RequestUtil.getPageParam();
            dto = comExternalOptions002Service.findByPrimaryKey(RequestUtil.getPageParam());
            //String finprodId = externalOptions002Service.findByBenchmarkId(dto.getFinprodId2());
            //dto.setFinprodId(finprodId);
        }
        else if (StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = comExternalOptions002Service.getBussinessEntityByWorkflowId(workflowId);
            //String finprodId = externalOptions002Service.findByBenchmarkId(dto.getFinprodId2());
            //dto.setFinprodId(finprodId);
        }


        model.addAttribute(GlobalConstant.ENTITY, dto);

        super.setSelectOptionToModel(model, DictCode.币种);
        super.setSelectOptionToModel(model, DictCode.估值来源);
        super.setSelectOptionToModel(model, DictCode.估值方法);
        super.setSelectOptionToModel(model, DictCode.维护方式);
        super.setSelectOptionToModel(model, DictCode.金融产品类型);
        super.setSelectOptionToModel(model, DictCode.期权资产类型);
        return super.init(model);

    }


    @RequestMapping("/getFinprodId")
    @ResponseBody
    public List<SuggestDto> getFinprodId() {
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("BENCHMARK_TYPE2",str);
        return comExternalOptions002Service.getFinprodId(param);
    }
    @ResponseBody
    @RequestMapping("/getTargetInfo")
    public ComExternalOptions002Dto getTargetInfo(@RequestBody Map<String, Object> param){
        return comExternalOptions002Service.getTargetInfo(param);
    }


    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ComExternalOptions002Dto dto, BindingResult bindingResult)
            throws Exception {
        String operateType = dto.getOperateType();

        if (!bindingResult.hasErrors()) {
            if (GlobalConstant.DETAIL_PAGE_MODEL_NEW.equals(dto.getPageModel())) {
                // 判断数据库是否存在：主键check
                Map<String, Object> map = Maps.newHashMap();
                map.put("finprodId2", dto.getFinprodId2());
                map.put("valDate", dto.getValDate());
                //  map.put("valmodelId", dto.getValmodelId());
                ComExternalOptions002Dto entity = comExternalOptions002Service.findByPrimaryKey(map);
                if (entity != null) {
                    return new AjaxResult("已存在交易编号_估值日相同的数据，请确保数据唯一性。", MessageType.错误消息);
                }
                map.put("branch", 0);
                ComExternalOptions002Dto dateCheckDto = comExternalOptions002Service.dateCheck(map);
                if (dto.getValDate().compareTo(dateCheckDto.getVdate()) < 0 || dto.getValDate().compareTo(dateCheckDto.getMdate()) >= 0) {
                    return new AjaxResult("估值日必须介于交易起息日(含)和到期日（不含）之间", MessageType.错误消息);
                }
            }
        }

        return commonCheck(bindingResult, operateType);
    }

    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ComExternalOptions002Dto dto) throws WorkFlowException {
        String msg = StringUtil.EMPTY;

        ProcessParameter<ComExternalOptions002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<ComExternalOptions002Dto> workFlowParameter = new WorkFlowParameter<>();

        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getFinprodId2());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场外期权.getValue());// 业务类型
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment("场外期权", entity.getFinprodId2()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数

            comExternalOptions002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        }
        catch (Exception e) {
            this.getLogger().error("启动外汇估值信息流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }

}
