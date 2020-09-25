package com.joyintech.tams.invest.product.messageadjust.bmquotation.controller;

import java.util.HashMap;
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
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.bmquotation.service.FinProductBmQuotationService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 产品业绩基准调整
 * 支持产品业绩基准调整的查询
 * @author 王泽慧
 * @version 1.0 2018年10月15日
 * @see FinProductBmQuotationController002
 * @since 1.0
 */
@Controller
@PageId("FinProductBmQuotation002")
@RequestMapping("invest/product/messageadjust/bmquotation/FinProductBmQuotation002")
public class FinProductBmQuotationController002 extends BaseController {
    /**
     * finProductBmQuotationService
     */
    @Autowired
    private FinProductBmQuotationService finProductBmQuotationService;
    /**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 
     * 新建页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String finprodId = getParam("finprodId");
        String effDate = getParam("effDate");
        String baseRuleId = getParam("baseRuleId");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        //add by wm 2019-09-25 业绩基准调整（增加周期型修改） start
        model.addAttribute("DEAL_MODE_10", DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("DEAL_MODE_11", DictCodeList.处理模式_分档.getValue());
        //add by wm 2019-09-25 业绩基准调整（增加周期型修改） end
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinProductBmQuotationDto dto = finProductBmQuotationService.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
          //add by menghongwei 支持分档产品 20190626 start
        	if(StringUtil.isNotEmpty(dto.getpFinprodId())) {
        		dto.setProdStyle(dto.getFinprodId());
        		dto.setFinprodId(dto.getpFinprodId());
        	}
        	//add by menghongwei 支持分档产品 20190626 end
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodId", finprodId);
            map.put("effDate", effDate);
            map.put("baseRuleId", baseRuleId);
            //根据金融产品代码和生效日获得页面展示元素
            FinProductBmQuotationDto dto = finProductBmQuotationService.selectByFinprodId(map);
            dto.setBaseRuleId(baseRuleId);
            dto.setOpType("edit");
          //add by menghongwei 支持分档产品 20190626 start
        	if(StringUtil.isNotEmpty(dto.getpFinprodId())) {
        		//modify wm 针对周期型进行修改 2019-09-25 start
        		if(StringUtil.equals(dto.getDealMode(), DictCodeList.处理模式_分档.getValue())){
            		dto.setProdStyle(dto.getFinprodId());
        		}
        		model.addAttribute("PAGE_finprodId", dto.getFinprodId());
        		//modify wm 针对周期型进行修改 2019-09-25 end
        		dto.setFinprodId(dto.getpFinprodId());
        	}
        	else {
        		model.addAttribute("PAGE_finprodId", finprodId);
        	}
        	//add by menghongwei 支持分档产品 20190626 end
            model.addAttribute("entity", dto);
        }
        else if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(finprodId)){
            FinProductBmQuotationDto dto = new FinProductBmQuotationDto();
            dto.setProfitType(DictCodeList.收益类型_净值型.getValue());
            dto.setOpType("new");
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }
    
    /**
     * 新建产品代码联想框 
     * @return List List
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdToNew")
    public List<SelectOptionDto> getFinprodMarketIdToNew()
        throws Exception {
        return finProductBmQuotationService.getFinprodMarketIdToNew(RequestUtil.getPageParam());
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
    	ValidateGroupEdit.class}) FinProductBmQuotationDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
    	ValidateGroupEdit.class}) FinProductBmQuotationDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductBmQuotationDto> processParameter = new ProcessParameter<FinProductBmQuotationDto>();
        WorkFlowParameter<FinProductBmQuotationDto> params = new WorkFlowParameter<FinProductBmQuotationDto>();
        params.setPageId("FinProductBmQuotation002");
        params.setElementId(dto.getElementId());
      //修改时为画面所选记录的金融产品代码。新建时调用各种业务共通组件.获取金融产品代码

//  BSTOZ-1171 新建业绩基准保存到审核界面，待审核数据的业务编号格式不对 guyunfei 20191204 start
//        params.setBusinessCode(entity -> {
//            BussinessCodeParameter param = new BussinessCodeParameter();
//            param.setCommonBusinessType(DictCodeList.共通业务类型_产品业绩基准调整);
//            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
//                param) : IDGenerator.generate();
//            return code;
//        });
        params.setBusinessCode(FinProductBmQuotationDto::getFinprodMarketId);
//  BSTOZ-1171 新建业绩基准保存到审核界面，待审核数据的业务编号格式不对 guyunfei 20191204 end

        params.setBusinessType(DictCodeList.共通业务类型_产品业绩基准调整.getValue());
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
          //设置业绩基准代码
        	//modify by wm 2019-09-25 start 业绩基准代码规则修改
//            dto.getBussBenchMarkInfoDto().setBaseRuleId(dto.getFinprodId());
            String branch = CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE);
//            dto.getBussBenchMarkInfoDto().setBaseRuleId(dto.getFinprodId()+"_"+DictCodeListExt1.业绩基准类型_产品披露.getValue());
            dto.getBussBenchMarkInfoDto().setBaseRuleId(CommonUtil.generateCashFlowCode(dto.getFinprodId(), null, Long.parseLong(branch), DictCodeListExt1.业绩基准类型_产品披露.getValue()));
        	//modify by wm 2019-09-25 end 业绩基准代码规则修改
            dto.setBranch(Long.parseLong(branch));
        }
        // 设置流程业务说明, entity即传入的dto
        // 业务说明："产品代码" + "(" + 选中记录.市场代码 + ")" + "-"  + "业绩基准报价生效日期" + "(" + 选中记录.生效日 + ")" 
        params.setComment(entity -> "产品代码" + "(" + entity.getFinprodMarketId() + ")" + "-" + "业绩基准报价生效日期" + "("
                                    + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductBmQuotationService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
