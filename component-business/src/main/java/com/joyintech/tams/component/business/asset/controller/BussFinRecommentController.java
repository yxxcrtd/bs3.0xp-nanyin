/*
 * 文件名：BussFinRecommentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.asset.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductRecomment;
import com.joyintech.tams.component.business.asset.BussFinRecommentService;
import com.joyintech.tams.component.business.asset.dto.BussFinRecommentDto;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 资产推荐方及费率信息--业务组建
 *
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/asset/BussFinRecomment")
@SubPageId("BussFinRecomment")
public class BussFinRecommentController extends BaseController {

    @Autowired
    private BussFinRecommentService bussFinRecommentService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        // 设置画面初始值
        String entityName = StringUtil.defaultString(this.getParam("entityName"),
            BussFinRecommentService.DEFAULT_ENTITY_NAME);
        // 共通业务类型--付息计划调整
        model.addAttribute("entityName", entityName);
        return "component/asset/BussFinRecomment";
    }

    /**
     * 构建推荐方下拉框
     * 
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("qryRecommenderSelect")
    @ResponseBody
    public Page<SelectOptionDto> qryRecommenderSelect() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return bussFinRecommentService.qryRecommenderSelect(param);
    }

    /**
     * 生成资产推荐方及费率信息check
     * 
     * @param previewParam
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) BussFinRecommentDto previewParam, BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 初始化资产推荐方及费率信息
     * 
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/initInfo")
    @ResponseBody
    public List<FinProductRecomment> initInfo()
        throws ParseException {
        List<FinProductRecomment> finProductRecommentList = new ArrayList<FinProductRecomment>();
        // 初始化--修改、查看、代办
        String finprodId = this.getParam("finprodId");
        String workflowId = this.getParam("workflowId");
        if (!WorkFlowUtil.isNotFromToDoList() && workflowId != null) {
            String entityName = StringUtil.defaultString(this.getParam("entityName"),
                BussFinRecommentService.DEFAULT_ENTITY_NAME);
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            BussFinRecommentDto bussFinRecommentDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                workflowId, entityName, BussFinRecommentDto.class);
            if (null != bussFinRecommentDto) {
                finProductRecommentList = bussFinRecommentDto.getFinProductRecommentList();
            }
        }
        else {
            if (StringUtil.isNotEmpty(finprodId)) {
                finProductRecommentList = bussFinRecommentService.getFinProductRecommentByProductId(
                    finprodId);
            }
        }
        return finProductRecommentList;
    }
}
