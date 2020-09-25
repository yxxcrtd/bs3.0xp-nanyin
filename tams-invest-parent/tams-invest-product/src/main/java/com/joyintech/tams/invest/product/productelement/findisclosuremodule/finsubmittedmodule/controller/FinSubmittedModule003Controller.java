/*
 * 文件名：FinSubmittedModule003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.core.service.MstFileCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 申报附件模板Controller 可以完成添加申报附件模板和上传附件
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule003Controller
 * @since 1.0
 */
@Controller
@PageId("FinSubmittedModule003")
@RequestMapping("/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule003")
public class FinSubmittedModule003Controller extends BaseController {

    /**
     * ProgressBarService
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 申报方案附件Service
     */
    @Autowired
    private FinSubmittedModule002Service finSubmittedModule002Service;

    /**
     * 附件上传共通Service
     */
    @Autowired
    private MstFileCommonService mstFileCommonService;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 附件模板类型界面的初始化
     * 
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        FinSubmittedModule002Dto dto = null;
        String seqno = getParam("seqno");
        String schemeId = getParam("schemeId");
        // 附件类型下拉框初始化
        this.setSelectOptionToModel(model, DictCode.附件类型, DictCode.模板类型);
            FinSubmittedModule002Dto dto1 = new FinSubmittedModule002Dto();
            dto1.setDocType(DictCodeList.附件类型_报告主文件.getValue());
            dto1.setTplType(DictCodeList.模板类型_静态模板.getValue());
            model.addAttribute("entity", dto1);
            // 业务代码生成参数
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeList.共通业务类型_申报附件方案管理);
            String param = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                    parameter) : IDGenerator.generate();
            model.addAttribute("seqno",param);
        //设置附件初始值
        if(null != dto) {
            model.addAttribute(GlobalConstant.ENTITY, dto);
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(dto.getSeqno());
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        model.addAttribute("schemeId",schemeId);
        return super.init(model);
    }

    /**
     * 行删除点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("dcheck")
    public AjaxResult dcheck(@RequestBody @Validated({
            ValidateGroupDelete.class}) FinSubmittedModule002Dto dto, BindingResult result) {
        AjaxResult wr = new AjaxResult();
        //删除附件模板时验证该附件模板是否被理财产品使用
        if(StringUtil.equals(dto.getOperateType(), "delete")){
            if(finSubmittedModule002Service.selectFinProdId(dto.getSchemeId())>0){
                List<JoyinMessage> list = new ArrayList<>();
                list.add(new JoyinMessage(getMessage("FIN_PROD_E_0069"), MessageType.确认消息));
                wr.setJoyinMessages(list);
                return wr;
            }
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 附件模板添加保存按钮点击时的check
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
        ValidateGroupEdit.class}) FinSubmittedModule002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 附件模板添加保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) FinSubmittedModule002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 文件信息的业务编码设为申报附件模板的流水号
        dto.setBusinessId(dto.getSeqno());
        ProcessParameter<FinSubmittedModule002Dto> processParameter = new ProcessParameter<FinSubmittedModule002Dto>();
        processParameter.setOperationType(OperationType.NEW);
        processParameter.setPageId("FinSubmittedModule003");
        String msg = finSubmittedModule002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 删除按钮点击事件
     *
     * @param entity 删除参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDocAndTpl(@RequestBody FinSubmittedModule002Dto entity) {
        // 批量删除
        for (FinSubmittedModule002Dto dto : entity.getSelectedItem().toArray(
            new FinSubmittedModule002Dto[entity.getSelectedItem().size()])) {
            finSubmittedModule002Service.delete(dto.getSeqno());
        }
        String msg = getMessage("FIN_PROD_E_0067");
        return new AjaxResult(msg);
    }

    /**
     * 行删除事件
     *
     * @param dto 删除参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteScheme")
    public AjaxResult deleteScheme(@RequestBody FinSubmittedModule002Dto dto) {
        finSubmittedModule002Service.delete(dto.getSeqno());
        return new AjaxResult(getMessage("FIN_PROD_E_0067"));
    }
    /**
     * 上传文件
     *
     * @param fileNo 文件编号
     * @param businessId 流水号
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(String fileNo,String businessId) {
        if(StringUtil.isNotEmpty(fileNo)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            fileAttachmentProcess.updateBussinessNo(Arrays.asList(fileNo),businessId);
        }
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
}
