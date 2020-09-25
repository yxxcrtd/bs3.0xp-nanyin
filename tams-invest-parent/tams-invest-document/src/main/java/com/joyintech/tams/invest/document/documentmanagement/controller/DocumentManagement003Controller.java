/*
 * 文件名：DocumentManagement003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.tams.invest.document.documentmanagement.service.DocumentManagementService;


/**
 * 附件详情
 * 此功能可进行查询结果的文档详细信息的查看
 * @author wangzehui
 * @version 1.0 2018年7月26日
 * @see DocumentManagement003Controller
 * @since 1.0
 */
@Controller
@PageId("DocumentManagement003")
@RequestMapping("/invest/document/documentmanagement/DocumentManagement003")
public class DocumentManagement003Controller extends BaseController{
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
     * 附件详情查询
     * @return Page<DocumentManagementDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<DocumentManagementDto> findPage()
        throws Exception {
        return documentManagementService.selectForDetails(RequestUtil.getParameters(getRequest()));
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
    @RequestMapping("/delete")
    public AjaxResult save(@RequestBody DocumentManagementDto dto) {
        //删除操作
        documentManagementService.delete(dto);
        return new AjaxResult(getMessage("FIN_PROD_E_0067"));
    }
}
