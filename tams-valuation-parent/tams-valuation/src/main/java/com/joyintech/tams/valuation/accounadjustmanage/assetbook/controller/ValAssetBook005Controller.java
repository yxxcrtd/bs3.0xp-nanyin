/*
 * 文件名：ValAssetBook002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetbook002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 模板示例<br>
 * 新建账套
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook005Controller
 * @since 1.0
 */
@Controller
@PageId("ValAssetBook005")
@RequestMapping("/valuation/accounadjustmanage/assetbook/ValAssetBook005")
public class ValAssetBook005Controller extends BaseController {

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private ValAssetBook002Service valAssetBook002Service;

    @Autowired
    private BaseCommonService baseCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String standardCcy = DictCodeList.币种_人民币_CNY.getValue();
        String initDate = DateUtil.format(baseCommonService.getSysDate(), "yyyy-MM-dd");
        this.setSelectOptionToModel(model, DictCode.是否);
        this.setSelectOptionToModel(model, DictCode.账套类别);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        // 会计准则
        model.addAttribute("ZJ",JsonUtils.objectArray2Json(valAssetBook002Service.getAcctStandard().toArray()));
        model.addAttribute("standardCcy", standardCcy);
        model.addAttribute("initDate", initDate);

        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
        	ValAssetbook002Dto entity = valAssetBook002Service.getBussinessEntityByWorkflowId(workflowId);
        	ValAssetbook002Dto dto = valAssetBook002Service.getBookSetById(entity.getBooksetId());
        	model.addAttribute("endDate", DateUtil.format(dto.getEndDate(), "yyyy-MM-dd"));
            model.addAttribute(GlobalConstant.ENTITY, entity);
        }
        return super.init(model);
    }
}
