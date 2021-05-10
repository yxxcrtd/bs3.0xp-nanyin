package com.joyintech.tams.valuation.basicdata.asset.option.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.option.dto.FinOptionTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.option.service.FinOptionTargetService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@PageId("FinOptionTarget002")
@Controller
@RequestMapping("/valuation/basicdata/asset/option/FinOptionTarget002")
public class FinOptionTarget002Controller extends BaseController {

    @Autowired
    private FinOptionTargetService finOptionTargetService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.期权资产类型);
        String targetId=getParam("targetId");
        String workflowId=getParam("workflowId");
        if ( StringUtil.isNotEmpty(targetId)){
            FinOptionTargetDto dto= finOptionTargetService.getFinprodcodebyId(targetId);
            model.addAttribute("entity", dto);
            model.addAttribute("businessId", dto.getTargetId());
        }
        else if( StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            FinOptionTargetDto dto = finOptionTargetService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("businessId", workflowId);

            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinOptionTargetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinOptionTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 当进行新建或编辑的时候，估值数据来源设置为手工
        dto.setDataSource(DictCodeListExt3.估值数据来源_手工.getValue());

        ProcessParameter<FinOptionTargetDto> processParameter = new ProcessParameter<FinOptionTargetDto>();
        WorkFlowParameter<FinOptionTargetDto> params = new WorkFlowParameter<FinOptionTargetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getTargetId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_场外期权类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> SpringContextUtil.getTextValue("FinOptionTarget001.targetId") + "(" + entity.getTargetId() + ")" + "-"
                                    + SpringContextUtil.getTextValue("FinOptionTarget001.targetName") + "("
                                    + entity.getTargetName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finOptionTargetService.start(processParameter, dto);
        return new AjaxResult(msg);
    }


}
