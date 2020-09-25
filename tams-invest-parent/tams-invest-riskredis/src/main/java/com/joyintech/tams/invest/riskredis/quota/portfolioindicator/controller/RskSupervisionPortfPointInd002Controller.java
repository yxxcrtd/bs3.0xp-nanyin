package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPointMainService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;

import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.service.RskSupervisionPortfPoint002Service;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskPortfolioIndicatorDto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicatorDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;



/**
 * 组合关联监控指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionPortfPointInd002Controller
 * @since 1.0
 */
@Controller
@PageId("RskSupervisionPortfPointInd002")
@RequestMapping("/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPointInd002")
public class RskSupervisionPortfPointInd002Controller extends BaseController {
	
    /**
     * RskSupervisionPortfPointMainService
     */
    @Autowired(required = false)
    private RskSupervisionPortfPointMainService rskSupervisionPortfPointMainService;
    
    @Autowired(required = false)
	private RskSupervisionPortfPoint002Service rskSupervisionPortfPoint002Service;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 初始化的查询条件
     */
   private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[] {BussinessDictCodeRds.组合层次,
	        BussinessDictCodeRds.分类维度,BussinessDictCodeRds.收益类型,BussinessDictCodeRds.开放类型,BussinessDictCodeRds.募集方式,
	        BussinessDictCodeRds.投资性质
	       };

    @Override
    public String init(Model model) {
    	 setSelectOptionToModel(model, DICT_CODES);
         SelectDto selectZhccDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.组合层次, null);
         List<SelectOptionDto> optionZhcc = selectZhccDto.getOptions();
         Map<String,List<SelectOptionDto>> mapZhcc = optionZhcc.stream().collect(JoyinCollectors.groupingBy(x->x.getId().toString()));
         model.addAttribute("mapZhcc",mapZhcc);
         model.addAttribute("PORTFOLIO_LEVEL_01", BussinessDictCodeListRds.组合层次_单组合.getValue());
         model.addAttribute("CLASSIFY_DIMENSION_01",BussinessDictCodeListRds.分类维度_收益类型.getValue());
         // 待办跳转
         if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
        	 String workflowId = getParam("workflowId");
        	 model.addAttribute("workflowId", workflowId);
             RskSupervisionIndicator002Dto rskSupervisionIndicator002Dto = rskSupervisionPortfPointMainService.getBussinessEntityByWorkflowId(workflowId);
             RskSupervisionIndicatorDto rskSupervisionIndicatorDto=new RskSupervisionIndicatorDto();
        	 rskSupervisionIndicatorDto.setIndName(rskSupervisionIndicator002Dto.getRskPortfolioIndicatorDto().getIndName());
        	 rskSupervisionIndicator002Dto.setRskSupervisionIndicatorDto(rskSupervisionIndicatorDto);
             model.addAttribute(GlobalConstant.ENTITY, rskSupervisionIndicator002Dto);
         } else{
        	 RskPortfolioIndicatorDto rskHavedDto=new RskPortfolioIndicatorDto();
        	 rskHavedDto.setIndId(getParam("indId"));
        	 rskHavedDto.setIndName(getParam("indName"));
        	 RskSupervisionIndicatorDto rskSupervisionIndicatorDto=new RskSupervisionIndicatorDto();
        	 rskSupervisionIndicatorDto.setIndName(getParam("indName"));
        	 RskSupervisionIndicator002Dto rskSupervisionIndicator002Dto=new RskSupervisionIndicator002Dto();
        	 rskSupervisionIndicator002Dto.setRskPortfolioIndicatorDto(rskHavedDto);
        	 rskSupervisionIndicator002Dto.setRskSupervisionIndicatorDto(rskSupervisionIndicatorDto);
             model.addAttribute(GlobalConstant.ENTITY,rskSupervisionIndicator002Dto);
         }
         return super.init(model);
    }
    
    /**
     * 保存按钮点击时的check
     * @return check结果
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody RskSupervisionIndicator002Dto dto, BindingResult result ) {
    	
    	 AjaxResult ajaxResult = new AjaxResult();
         List<ValidMessage> errorList = new ArrayList<ValidMessage>();
         if(CollectionUtils.isNotEmpty(dto.getSelectedPortfIndItem())) {
             checkWorkFlowDataPointInd(dto,errorList);
        	 checkConfirmInfo(dto,errorList);
         }
         ajaxResult.setErrorList(errorList);
         return ajaxResult;
    }

    /**
     * 检查指标关联组合流程中是否存在相同的组合数据
     */
    private void checkWorkFlowDataPointInd(RskSupervisionIndicator002Dto dto,List<ValidMessage> resultList){
        //判断指标关联组合流程中是否存在相同的组合数据
        WflValidInputParameter param = new WflValidInputParameter();
        param.setBusinessDataPk2(dto.getRskPortfolioIndicatorDto().getIndId());
        param.setPageId("RskSupervisionPortfPointInd002");
        if(WorkFlowUtil.isNotFromToDoList()&&null != mstWorkflowCommonService){
            ProcessMark processMark=mstWorkflowCommonService.getDataEntityFromProcessMark(param);
            if (StringUtil.isNotEmpty(processMark)&&StringUtil.isNotEmpty(processMark.getBusinessData())){
                try {
                    RskSupervisionIndicator002Dto havRsk002Dto =  JsonUtils.OBJECT_MAPPER.readValue(processMark.getBusinessData(),RskSupervisionIndicator002Dto.class);
                    for(RskPortfolioIndicatorDto item:dto.getSelectedPortfIndItem()){
                        List<RskPortfolioIndicatorDto> filterList=havRsk002Dto.getSelectedPortfIndItem().stream().filter(subItem->subItem.getPortfolioId().equals(item.getPortfolioId())).collect(Collectors.toList());
                        if(filterList.size()>0){
                            resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012",item.getPortfolioName())));
                            break;
                        }
                    };

                } catch (IOException e) {
                    resultList.add(new ValidMessage(e.getMessage()));
                }
            }
        }
    }
    private void checkConfirmInfo(RskSupervisionIndicator002Dto dto,List<ValidMessage> resultList) {
 		 for(RskPortfolioIndicatorDto item:dto.getSelectedPortfIndItem()) {
 			   if(BussinessDictCodeListRds.组合层次_单组合.getValue().equals(dto.getRskPortfolioIndicatorDto().getPortfolioLevel())){
		           if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortf(dto.getRskPortfolioIndicatorDto().getIndId(),item.getPortfolioId())>0){
		        	   resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0038",item.getPortfolioName())));
		           }
 			   }
 			   else{
 				   if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBagSingle(dto.getRskPortfolioIndicatorDto().getIndId(),item.getPortfolioId())>0){
 					   resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0040",item.getPortfolioName())));
		           }
 				   if(rskSupervisionPortfPoint002Service.searchRelatedIndByPortfBag(dto.getRskPortfolioIndicatorDto().getIndId(),item.getPortfolioId())>0){
 					   resultList.add(new ValidMessage(this.getMessage("RSKRDS_W_0039",item.getPortfolioName())));
		           }
 			   }
       };
   }
    
    /**
     * 保存
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskSupervisionIndicator002Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(null != dto.getRskPortfolioIndicatorDto()) {
	        if (!StringUtil.isEmpty(dto.getRskPortfolioIndicatorDto().getIndId())) {
	        	dto.setIndId(dto.getRskPortfolioIndicatorDto().getIndId());
	        }
        }
        // 监督指标代码生成
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_限额组合关联监督指标管理);
        ProcessParameter<RskSupervisionIndicator002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<RskSupervisionIndicator002Dto> params = new WorkFlowParameter<>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        // 金融产品代码作为业务编号
        String businessCode = dto.getRskPortfolioIndicatorDto().getIndId();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_限额组合关联监督指标管理.getValue());

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicator001.indId"),
                GlobalConstant.OPEN_PARENTHESIS, businessCode, GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicator002.indName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getRskPortfolioIndicatorDto().getIndName(), GlobalConstant.CLOSE_PARENTHESIS,
                GlobalConstant.OPEN_PARENTHESIS,getMessage("RskSupervisionPortfPoint001.indicatorTree"), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionPortfPointMainService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    
}
