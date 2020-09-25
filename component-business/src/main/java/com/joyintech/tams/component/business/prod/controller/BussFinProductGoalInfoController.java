/*
 * 文件名：BussFinProductGoalInfoController.java
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

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.prod.BussFinProductGoalInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductGoalInfoDto;

/**
 * 投向描述
 * 
 * @author 张亮
 * @version 1.0 2018年9月17日
 * @see BussFinProductGoalInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductGoalInfo")
public class BussFinProductGoalInfoController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductGoalInfoService bussFinProductGoalInfoService;

    @Override
    public String init(Model model) {
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussFinProductGoalInfoService.DEFAULT_ENTITY_NAME));
        // 父画面传入的金融产品代码
        model.addAttribute("finprodId", this.getParam("finprodId"));
        // 父画面传入的工作流代码
        model.addAttribute(GlobalConstant.WORKFLOW_ID, this.getParam(GlobalConstant.WORKFLOW_ID));
        // 父画面传入的pageId
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));

        return "component/prod/BussFinProductGoalInfo";
    }

    /**
     * 查询产品投向信息列表
     * 
     * @param finprodId 金融产品代码
     * @param workflowId 工作流代码
     * @return 产品投向信息列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("productGoalInfoQuery")
    public List<BussFinProductGoalInfoDto> productGoalInfoQuery(String finprodId,
                                                                String workflowId) {
        List<BussFinProductGoalInfoDto> list = new ArrayList<BussFinProductGoalInfoDto>();

        if (StringUtil.isNotEmpty(workflowId)
            && !StringUtil.equals(this.getParam("quickFlag"), "true")) {
            list = bussFinProductGoalInfoService.productGoalInfoQueryByWflId(workflowId,
                RequestUtil.getParam("entityName"));
        }
        else if (StringUtil.isNotEmpty(finprodId)) {
            list = bussFinProductGoalInfoService.productGoalInfoQuery(finprodId);
        }
        return list;
    }

}
