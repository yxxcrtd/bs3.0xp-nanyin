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

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductInvestPeriodService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestPeriodDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 投向描述
 * 
 * @author 叶杰杰
 * @version 1.0 2019年1月23日
 * @see BussFinProductInvestPeriodController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductInvestPeriod")
public class BussFinProductInvestPeriodController extends BaseController {

    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private BussFinProductInvestPeriodService bussFinProductInvestPeriodService;
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model,DictCode.期限类型);
        model.addAttribute("entityName", "bussFinProductInvestPeriodDto");
        model.addAttribute("TERM_TYPE_01", DictCodeListExt2.期限类型_单一固定期限.getValue());
        model.addAttribute("TERM_TYPE_02", DictCodeListExt2.期限类型_多固定期限.getValue());
        model.addAttribute("TERM_TYPE_03", DictCodeListExt2.期限类型_无固定期限.getValue());
        //add by wm 2019-10-09 start 增加产品处理模式-周期型
        model.addAttribute("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        //add by wm 2019-10-09 end 增加产品处理模式-周期型
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
        return "component/prod/BussFinProductInvestPeriod";
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
    @RequestMapping("queryBussFinProductInvestPeriodByFinprodId")
    public List<BussFinProductInvestPeriodDto> queryBussFinProductInvestPeriodByFinprodId(String finprodId,
                                                                String workflowId) {
        if(StringUtil.isNotEmpty(this.getParam(GlobalConstant.WORKFLOW_ID))){
        	BussFinProductInvestPeriodDto wflDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                this.getParam(GlobalConstant.WORKFLOW_ID),
                StringUtil.defaultString(this.getParam("entityName"),
                    "bussFinProductInvestPeriodDto"), BussFinProductInvestPeriodDto.class);
            if(wflDto!=null && !StringUtil.isEmptyList(wflDto.getBussFinProductInvestPeriodDtoList())){
                return wflDto.getBussFinProductInvestPeriodDtoList();
            }
        }
        else if(StringUtil.isNotEmpty(this.getParam("finprodId"))){
            return bussFinProductInvestPeriodService.queryPeriodList(RequestUtil.getPageParam());
            
        }

        return new ArrayList<BussFinProductInvestPeriodDto>();
    }

}
