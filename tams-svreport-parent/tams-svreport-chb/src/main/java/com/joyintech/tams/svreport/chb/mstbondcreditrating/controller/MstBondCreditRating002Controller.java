package com.joyintech.tams.svreport.chb.mstbondcreditrating.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.dto.MstBondCreditRatingDto;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.service.MstBondCreditRateService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
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
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-02 9:38
 */
@Controller
@PageId("MstBondCreditRating002")
@RequestMapping("/chb/mstbondcreditrating/mstbondcreditrating002")
public class MstBondCreditRating002Controller extends BaseController {
    @Autowired
    private MstBondCreditRateService mstBondCreditRateService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.评级类型);
        this.setSelectOptionToModel(model, DictCode.长期短期);
        this.setSelectOptionToModel(model, DictCode.评级机构);
        this.setSelectOptionToModel(model, DictCode.评级结果);
//        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        this.setSelectOptionToModel(model, DictCode.评级来源类型);

        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String oCode = getParam("oCode");
        String oObject = getParam("oObject");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            MstBondCreditRatingDto dto = mstBondCreditRateService.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(oCode) ) {
            //通过主键查询数据
            MstBondCreditRatingDto dto = mstBondCreditRateService.selcetByKey(oCode,oObject);
            model.addAttribute(GlobalConstant.ENTITY, dto);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class})  MstBondCreditRatingDto dto,
                            BindingResult result) {

        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 数据保存操作
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) MstBondCreditRatingDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<MstBondCreditRatingDto> processParameter = new ProcessParameter<MstBondCreditRatingDto>();
        WorkFlowParameter<MstBondCreditRatingDto> params = new WorkFlowParameter<MstBondCreditRatingDto>();
        params.setPageId("MstBondCreditRating002");
        params.setElementId("MstBondCreditRating002_btn_submit");
        params.setBusinessCode(item -> item.getoCode()+item.getoObject());
        params.setBusinessType(DictCodeList.债券评级.getValue());
        params.setComment(entity -> this.getMessage("MstBondCreditRating001.oCode") + "("
                + entity.getoCode() + ")"
                + this.getMessage("MstBondCreditRating001.oName")
                + "(" + entity.getoName() + ")"+this.getMessage("MstBondCreditRating001.oObject")
                +"("+entity.getoObject()+")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);

        // 流程开始
        String msg = mstBondCreditRateService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
