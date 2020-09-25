/*
 * 文件名：BussFinProductLayeredInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月29日
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

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.component.business.prod.BussFinProductLayeredInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductLayeredInfoDto;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 投向描述
 * 
 * @author 叶杰杰
 * @version 1.0 2019年1月23日
 * @see BussFinProductLayeredInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductLayeredInfo")
public class BussFinProductLayeredInfoController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductLayeredInfoService bussFinProductLayeredInfoService;

    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model,DictCode.目标客户类型,DictCode.销售渠道,DictCode.销售部门,DictCode.是否);
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductLayeredInfoService.DEFAULT_ENTITY_NAME));
        // 父画面传入的金融产品代码
        model.addAttribute("finprodId", this.getParam("finprodId"));
        // 父画面传入的工作流代码
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面传入的pageId
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));        
        // 父画面处理模式Id
                model.addAttribute("dealModeElement", StringUtil.defaultString(this.getParam("dealModeElement")));
        // 父画面是否支持代销Id
        model.addAttribute("consignmentFlag", StringUtil.defaultString(this.getParam("consignmentFlag")));
        //父画面收益类型id
        model.addAttribute("profitTypeElement", StringUtil.defaultString(this.getParam("profitTypeElement")));
        return "component/prod/BussFinProductLayeredInfo";
    }

    /**
     * 查询产品分层信息列表
     * 
     * @param finprodId 金融产品代码
     * @param workflowId 工作流代码
     * @return 产品投向信息列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("productLayeredInfoQuery")
    public List<BussFinProductLayeredInfoDto> productLayeredInfoQuery(String finprodId,
                                                                String workflowId) {
        List<BussFinProductLayeredInfoDto> list = new ArrayList<BussFinProductLayeredInfoDto>();

        if (StringUtil.isNotEmpty(workflowId)
            && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            list = bussFinProductLayeredInfoService.productLayeredInfoQueryByWflId(workflowId,
                RequestUtil.getParam("entityName"));
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            list = bussFinProductLayeredInfoService.productLayeredInfoQuery(finprodId);
        }
        return list;
    }

}
