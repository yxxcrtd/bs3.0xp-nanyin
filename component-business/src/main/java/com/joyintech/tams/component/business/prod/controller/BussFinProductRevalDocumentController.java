/*
 * 文件名：BussFinProductRevalDocumentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.prod.BussFinProductRevalDocumentService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;
import com.joyintech.tams.constant.DictCode;

/**
 * 信息披露文档组件
 * @author qiangchengxi
 * @version 1.0 2018年10月19日
 * @see BussFinProductRevalDocumentController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductRevalDocument")
public class BussFinProductRevalDocumentController extends BaseController {
    
    /**
     * 信息披露业务组件Service
     */
    @Autowired(required = false)
    private BussFinProductRevalDocumentService bussFinProductRevalDocumentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        
        // 设置数据字典项
        setSelectOptionToModel(model, new DictCode[] {DictCode.文件类型});
        
        model.addAttribute(GlobalConstant.PAGE_ID, getParam(GlobalConstant.PAGE_ID));
        model.addAttribute(GlobalConstant.WORKFLOW_ID, getParam(GlobalConstant.WORKFLOW_ID));
        model.addAttribute("finprodId", getParam("finprodId"));
        model.addAttribute("entityName", getParam("entityName"));
        return "component/prod/BussFinProductRevalDocument";
    }
    
    /**
     * 获取信息披露列表
     * @return List<BussFinProductRevalDocumentDto>
     */
    @RequestMapping("getDocumentList")
    @ResponseBody
    public List<BussFinProductRevalDocumentDto> getAllProdRevalDocument() {
        
        List<BussFinProductRevalDocumentDto> list = new ArrayList<BussFinProductRevalDocumentDto>();
        // 金融产品代码
        String finprodId = RequestUtil.getParam("finprodId");
        // 工作流代码
        String workflowId = RequestUtil.getParam(GlobalConstant.WORKFLOW_ID);
        
        if (StringUtil.isNotEmpty(workflowId) && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            list = bussFinProductRevalDocumentService.selectByWorkflowId(workflowId, RequestUtil.getParam("entityName"));
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            list = bussFinProductRevalDocumentService.selectByProductId(finprodId);
        }
        
        return list;
    }
    
    /**
     * 获取信息披露列表
     * @param fileType 文档类型
     * @param search 初始值
     * @return List<BussFinProductRevalDocumentDto>
     */
    @RequestMapping("queryMstFileByType")
    @ResponseBody
    public List<SelectOptionDto> queryMstFileByType(String fileType, String search) {
        return bussFinProductRevalDocumentService.selectByFileType(fileType, search);
    }
}
