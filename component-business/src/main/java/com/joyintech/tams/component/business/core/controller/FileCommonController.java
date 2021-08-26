/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;

/**
 * 附件信息--业务组件
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see FileCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/core/FileCommon")
public class FileCommonController extends BaseController{

    /**
     * FileAttachmentProcess
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    @Override
    public String init(Model model) {
        String businessId = getParam("businessId");
        if(null != fileAttachmentProcess && StringUtil.isNotEmpty(businessId)) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(businessId);
            model.addAttribute("attachmentFiles", JsonUtils.object2Json(files));
        }
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), "fileCommonDto"));
        return "component/core/FileCommon";
    }
}
