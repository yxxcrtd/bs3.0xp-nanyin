package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service.RiskPremiumAdjConf002Service;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-07-01
 */
@Controller
@PageId("RiskPremiumAdjConf002")
@RequestMapping("/valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002")
public class RiskPremiumAdjConf002Controller extends BaseController {

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    @Autowired
    private RiskPremiumAdjConf002Service riskPremiumAdjConf002Service;

    /**
     * 页面初始化
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        String seqNo = getParam("seqno");
        String operateType = getParam("operateType");
        RiskPremiumAdjConf002Dto dto = new RiskPremiumAdjConf002Dto();
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);

        if (StringUtils.isNotEmpty(seqNo)) {
            dto = riskPremiumAdjConf002Service.findByPrimaryKey(seqNo);
        }
        else if (StringUtil.isNotEmpty(workflowId)) {
            dto = riskPremiumAdjConf002Service.getBussinessEntityByWorkflowId(workflowId);
        }
        dto.setOperateType(operateType);
        dto.setSpread(ArithmeticUtil.multiply(dto.getSpread(), new BigDecimal(100)));
        dto.setSpreadAdj(ArithmeticUtil.multiply(dto.getSpreadAdj(), new BigDecimal(100)));
        model.addAttribute(GlobalConstant.ENTITY, dto);

        super.setSelectOptionToModel(model, DictCode.评级结果);
        super.setSelectOptionToModel(model, DictCode.评级来源_模型);
        super.setSelectOptionToModel(model, DictCode.资产类型);
        return super.init(model);
    }




    /**
     *
     * 保存时，check操作
     * <功能详细描述>
     * @param dto MstSharePriceDto
     * @param result result
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) RiskPremiumAdjConf002Dto dto, BindingResult result) {
        AjaxResult ar = commonCheck(result, dto.getOperateType());
        return ar;
    }

    /**
     * 新建或修改 并启动流程
     *
     * @param dto
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody RiskPremiumAdjConf002Dto dto) {
        String msg = StringUtil.EMPTY;

        ProcessParameter<RiskPremiumAdjConf002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RiskPremiumAdjConf002Dto> workFlowParameter = new WorkFlowParameter<>();

        if(!StringUtil.isNotEmpty(dto.getSeqno())){
            String code = dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列);
            dto.setSeqno(code);
        }

        try {
            workFlowParameter.setPageId(this.getPageId());
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            workFlowParameter.setBusinessCode(dto.getSeqno());
            workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_风险溢价调整配置.getValue());
            workFlowParameter.setComment(
                    entity -> WorkFlowUtil.concatComment(
                            "组合代码" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getFinprodId(),
                            "资产代码" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getFinprodId(),
                            "资产简称" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getFinprodName(),
                            "交易日期" + com.joyintech.framework.constant.GlobalConstant.COLON + entity.getBasedate()
                    ));
            processParameter.setWorkFlowParameter(workFlowParameter);

            riskPremiumAdjConf002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0005");
        }
        catch (Exception e) {
            this.getLogger().error("启动风险溢价调整配置流程失败：", e);
            msg = e.getMessage();
        }

        return new AjaxResult(msg);
    }

}
