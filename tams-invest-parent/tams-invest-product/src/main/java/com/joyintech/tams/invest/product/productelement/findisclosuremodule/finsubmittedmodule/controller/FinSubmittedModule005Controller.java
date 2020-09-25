package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto.FinSubmittedModule002Dto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.service.FinSubmittedModule002Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: FinSubmittedModule005Controller
 * @author: liuwei
 * @create: 2018-11-05 17:20
 **/
@Controller
@PageId("FinSubmittedModule005")
@RequestMapping("/invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule005")
public class FinSubmittedModule005Controller extends BaseController {

    /**
     * 申报方案附件Service
     */
    @Autowired
    private FinSubmittedModule002Service finSubmittedModule002Service;
    /**
     * ProgressBarService
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

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
        String businessId = getParam("businessId");
        model.addAttribute("businessId", businessId);
        return super.init(model);
    }

    /**
     * 附件模板添加保存按钮点击时的check
     *
     * @param dto    dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid FinSubmittedModule002Dto dto, BindingResult result) {
        AjaxResult wr = new AjaxResult();
        if(dto.getOperateType()!="save"&&StringUtil.isNotEmpty(finSubmittedModule002Service.selectSeqNo(dto.getSeqno()))){
            List<JoyinMessage> list = new ArrayList<>();
            list.add(new JoyinMessage(getMessage("FIN_PROD_E_0008"),MessageType.确认消息));
            wr.setJoyinMessages(list);
            return wr;
        }
        else {return commonCheck(result, dto.getOperateType());}
    }

    /**
     * 上传文件
     *
     * @param fileNo 文件编号
     * @param businessId  业务编号
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public AjaxResult uploadFile(String fileNo, String businessId) {
        if (StringUtil.isNotEmpty(fileNo)) {
            // 将附件和当前业务对象绑定（更新附件的业务代码）
            finSubmittedModule002Service.deleteFile(businessId);
            fileAttachmentProcess.updateBussinessNo(Arrays.asList(fileNo), businessId);
        }
        // 更新文件上传进度为100%
        progressBarService.removePercent(this.getPageId(), ProgressBarService.FILE_UPLOAD_ID);
        return new AjaxResult();
    }
}
