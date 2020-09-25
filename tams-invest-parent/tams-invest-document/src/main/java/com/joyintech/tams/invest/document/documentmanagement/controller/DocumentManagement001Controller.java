/*
 * 文件名：DocumentManagement001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.document.documentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.tams.invest.document.documentmanagement.service.DocumentManagementService;


/**
 * 文档管理
 * 支持对系统所有资产、交易信息等挂载的附件进行查询
 * @author wangzehui
 * @version 1.0 2018年7月26日
 * @see DocumentManagement001Controller
 * @since 1.0
 */
@Controller
@PageId("DocumentManagement001")
@RequestMapping("/invest/document/documentmanagement/DocumentManagement001")
public class DocumentManagement001Controller extends BaseController{
    /**
     * documentManagementService
     */
    @Autowired
    private DocumentManagementService documentManagementService;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.共通业务类型);
        return super.init(model);
    }
    /**
     * 
     * 获得组合代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public Page<SelectOptionDto> getPortfolioId(){
        return documentManagementService.getPortfolioId(RequestUtil.getPageParam());
    }
    /**
     * 
     * 获得共通业务类型
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBusinessType")
    public Page<SelectOptionDto> getBusinessType(){
        return documentManagementService.getBusinessType(RequestUtil.getPageParam());
    }
    @ResponseBody
    @RequestMapping("/getDeptInstitutionCode")
    public Page<SelectOptionDto> getDeptInstitutionCode(){
        return documentManagementService.getDeptInstitutionCode(RequestUtil.getPageParam());
    }
    /**
     * 分页查询
     * @return Page<DocumentManagementDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<DocumentManagementDto> findPage()
        throws Exception {
        return documentManagementService.selectForPage(RequestUtil.getParameters(getRequest()));
    }
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return documentManagementService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}




