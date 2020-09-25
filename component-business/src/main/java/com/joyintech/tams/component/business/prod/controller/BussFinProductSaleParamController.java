/*
 * 文件名：BussFinProductSaleParamController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductSaleParamService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductSaleParamDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 销售参数业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussFinProductSaleParamController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductSaleParam")
public class BussFinProductSaleParamController extends BaseController {
    
    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductSaleParamService bussFinProductSaleParamService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.销售渠道, DictCode.行政区划_省, DictCode.币种钞汇标志);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("NOTE_EXCHANGE_FLAG_01", "01");
        // 父画面传入的pageId
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        // 父画面传入的金融产品代码
        model.addAttribute("finprodId", this.getParam("finprodId"));
        
        
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussFinProductSaleParamService.DEFAULT_ENTITY_NAME));
        
        if (StringUtil.equals(RequestUtil.getParam("isTodoList"), "true")) {
            // 父画面传入的工作流代码
            BussFinProductSaleParamDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                this.getParam(GlobalConstant.WORKFLOW_ID),
                StringUtil.defaultString(this.getParam("entityName"),
                    BussFinProductSaleParamService.DEFAULT_ENTITY_NAME),
                BussFinProductSaleParamDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))){
            model.addAttribute(GlobalConstant.ENTITY, bussFinProductSaleParamService.querySaleParamInfoByFinprodId(this.getParam("finprodId")));
        }
        
        return "component/prod/BussFinProductSaleParam";
    }
    
    /**
     * 根据金融产品代码查询销售参数信息
     * 
     * @param finprodId 金融产品代码
     * @return 销售参数信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySaleParamInfoByFinprodId")
    public BussFinProductSaleParamDto querySaleParamInfoByFinprodId(String finprodId){
        return bussFinProductSaleParamService.querySaleParamInfoByFinprodId(finprodId);
    }
}
