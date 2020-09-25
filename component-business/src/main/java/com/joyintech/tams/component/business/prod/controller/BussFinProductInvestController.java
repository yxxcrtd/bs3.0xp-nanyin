package com.joyintech.tams.component.business.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.BussFinProductInvestService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductInvestDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 产品投向组件
 * @author yanya
 * @version 1.0 2019年8月22日
 * @see BussFinProductInvestController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/prod/BussFinProductInvest")
public class BussFinProductInvestController extends BaseController{
	
	private final static String ASSET_CLASSIFY = "1";//PTL_GOAL表中asset_classify当前默认存储1
	 /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductInvestService investInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,new DictCode[] {DictCode.是否, DictCode.尾数处理方式,DictCode.亏损处理方式});
//        model.addAttribute("YES", DictCodeList.是否_是.getValue());
//        model.addAttribute("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
//        model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
//        model.addAttribute("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
//        model.addAttribute("MANTISSA_PROCESS_MODE_01", DictCodeList.尾数处理方式_四舍五入.getValue());
        
        model.addAttribute("assetClassify", ASSET_CLASSIFY);
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute(GlobalConstant.PAGE_ID, this.getParam(GlobalConstant.PAGE_ID));
        //model.addAttribute("profitTypeElement", this.getParam("profitTypeElement"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussFinProductInvestService.DEFAULT_ENTITY_NAME));
        model.addAttribute(GlobalConstant.DETAIL_PAGE_MODEL_KEY, this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY));
        return "component/prod/BussFinProductInvest";
    }

    /**
     * 设置产品投向信息
     * @param model {@link Model}
     */
    private void setEntity(Model model) {
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussFinProductInvestService.DEFAULT_ENTITY_NAME);
        if (!WorkFlowUtil.isNotFromToDoList()) {
        	BussFinProductInvestDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId, entityName, BussFinProductInvestDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else {
            if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
                if (null != investInfoService) {
                	BussFinProductInvestDto param = investInfoService.getByProductId(this.getParam("finprodId"));
                    model.addAttribute(GlobalConstant.ENTITY, param);
                }
            }
        }
    }
    
    /**
     * 根据金融产品代码查询产品投向信息
     * @param finprodId 金融产品代码
     * @return 估值信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryValParamByFinprodId")
    public BussFinProductInvestDto queryValParamByFinprodId(String finprodId){
        return investInfoService.getByProductId(finprodId);
    }
}
