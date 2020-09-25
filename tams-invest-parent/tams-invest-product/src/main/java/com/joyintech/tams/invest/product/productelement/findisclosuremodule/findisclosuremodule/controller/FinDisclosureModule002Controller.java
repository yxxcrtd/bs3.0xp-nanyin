package com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.service.FinDisclosureModuleService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 信息披露文件上传 〈功能详细描述〉
 * 
 * @author wangzehui
 * @version 1.0 2018年8月23日
 * @see FinDisclosureModule002Controller
 * @since 1.0
 */
@Controller
@PageId("FinDisclosureModule002")
@RequestMapping("/invest/product/productelement/findisclosuremodule/findisclosuremodule/FinDisclosureModule002")
public class FinDisclosureModule002Controller extends BaseController {

    /**
     * 
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 定义service
     */
    @Autowired
    private FinDisclosureModuleService finDisclosureModuleService;

    /**
     * 定义service
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.文件类型_投管);
        FinDisclosureModuleDto dto = null;
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {// 判断是否存在于工作流中
            
            dto = finDisclosureModuleService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("workflowId", dto.getWorkflowId());
            model.addAttribute("revealFileId", dto.getRevealFileId());
        }
        // 判断初始化时是否有费用代码
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(getParam("revealFileId"))) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("revealFileId", getParam("revealFileId"));
            dto = finDisclosureModuleService.selectByRevealFileId(map);// 根据画面费用代码查询数据
            
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isEmpty(getParam("revealFileId"))) {
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setSeqNo("9997");
            model.addAttribute("revealFileId", baseGetBusinessId.buildBussinessCode(parameter));
        }

        if(null != dto) {
            model.addAttribute(GlobalConstant.ENTITY, dto);
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(dto.getRevealFileId());
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        // 设置附件初始值
        return super.init(model);
    }

    /**
     * 画面点击保存按钮时check
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
        ValidateGroupEdit.class}) FinDisclosureModuleDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存
     * 
     * @param dto 实体类
     * @param br 结果
     * @return 结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinDisclosureModuleDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinDisclosureModuleDto> processParameter = new ProcessParameter<FinDisclosureModuleDto>();
        WorkFlowParameter<FinDisclosureModuleDto> params = new WorkFlowParameter<FinDisclosureModuleDto>();
        params.setPageId(getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getRevealFileId());
        params.setBusinessType(DictCodeList.共通业务类型_信息披露模板管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：文档类型 + "(" + 文档类型 + ")" + "-" + "文件名" + "-" + "文件名"
        params.setComment(entity -> "文档类型" + "(" + entity.getFileTypeStr() + ")" + "-" + "文件名"
                                    + "(" + entity.getFileName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finDisclosureModuleService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 上传文件
     * 
     * @param fileNo 文件编号
     * @param revealFileId 披露文件号
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(String fileNo, String revealFileId) {
        if(StringUtil.isNotEmpty(fileNo) && StringUtil.isNotEmpty(revealFileId)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            fileAttachmentProcess.updateBussinessNo(Arrays.asList(fileNo), revealFileId);
        }
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
}
