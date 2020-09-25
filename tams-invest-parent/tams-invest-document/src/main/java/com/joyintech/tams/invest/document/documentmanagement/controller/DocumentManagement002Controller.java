/*
 * 文件名：DocumentManagement002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.document.documentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.tams.invest.document.documentmanagement.service.DocumentManagementService;

/**
 * 附件上传
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2019年1月17日
 * @see DocumentManagement002Controller
 * @since 1.0
 */
@Controller
@PageId("DocumentManagement002")
@RequestMapping("/invest/document/documentmanagement/DocumentManagement002")
public class DocumentManagement002Controller  extends BaseController{
    /**
     * documentManagementService
     */
    @Autowired
    private DocumentManagementService documentManagementService;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String businessId = getParam("businessId");
        if(StringUtil.isNotEmpty(businessId)){
            DocumentManagementDto entity = new DocumentManagementDto();
            entity.setBusinessId(businessId);
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }
    /**
     * 保存操作
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
    public AjaxResult save(@RequestBody DocumentManagementDto dto) {
        //上传操作
        documentManagementService.insert(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0065"));
    }
}
