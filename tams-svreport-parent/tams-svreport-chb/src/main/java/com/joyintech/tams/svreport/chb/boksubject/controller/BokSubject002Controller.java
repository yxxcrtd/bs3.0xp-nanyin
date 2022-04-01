package com.joyintech.tams.svreport.chb.boksubject.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.boksubject.service.BokSubjectService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 科目定义controller
 * @author wubingbing
 * @date 2022-03-28 14:36
 */
@Controller
@PageId("CBokSubject002")
@RequestMapping("/chb/boksubject/boksubject002")
public class BokSubject002Controller extends BaseController {
    @Autowired
    private BokSubjectService bokSubjectService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.科目类型);
        this.setSelectOptionToModel(model, DictCode.科目级别);
        this.setSelectOptionToModel(model, DictCode.科目状态);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String subjectNo = getParam("subjectNo");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            BokSubjectDto dto = bokSubjectService.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(subjectNo) ) {
            //通过主键查询数据
            BokSubjectDto dto = bokSubjectService.selcetByKey(subjectNo);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) BokSubjectDto dto,
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
            ValidateGroupEdit.class}) BokSubjectDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<BokSubjectDto> processParameter = new ProcessParameter<BokSubjectDto>();
        WorkFlowParameter<BokSubjectDto> params = new WorkFlowParameter<BokSubjectDto>();
        params.setPageId("CBokSubject002");
        params.setElementId("BokSubject002_btn_submit");
        params.setBusinessCode(item -> item.getSubjectNo());
        params.setBusinessType("SYS");
        params.setComment(entity -> this.getMessage("BokSubject.subjectNo") + "("
                + entity.getSubjectNo() + ")"
                + this.getMessage("BokSubject.subjectName")
                + "(" + entity.getSubjectName() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);

        // 流程开始
        String msg = bokSubjectService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

}
