package com.joyintech.tams.invest.riskredis.quota.superviseindicator.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.service.RskSupervisionIndicator002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 监督指标验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see RskSupervisionIndicatorValidator
 * @since 1.0
 */
@Component
public class RskSupervisionIndicatorValidator implements BeanValidator{
    @Autowired
	RskSupervisionIndicator002Service rskSupervisionIndicator002Service;
    
    @Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
    	RskSupervisionIndicator002Dto dto = (RskSupervisionIndicator002Dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            //判断指标名称是否重复
            int count = rskSupervisionIndicator002Service.searchRepeatByIndName(StringUtil.EMPTY, dto.getRskSupervisionIndicatorDto().getIndName());
            if(count>0){
            	resultList.add(new ValidMessage("indName", getMessage("RSKRDS_W_0019")));
            }
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            param.setBusinessDataPk1(dto.getRskSupervisionIndicatorDto().getIndName());
            param.setPageId("RskSupervisionIndicator002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(param)){
                resultList.add(
                    new ValidMessage(this.getMessage("COMMON_E_0012",dto.getRskSupervisionIndicatorDto().getIndName())));
            }
                
        }
        if(!StringUtil.equals(dto.getOperateType(), "edit")&&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            int count = rskSupervisionIndicator002Service.searchRepeatByIndName(dto.getRskSupervisionIndicatorDto().getIndId(), dto.getRskSupervisionIndicatorDto().getIndName());
            if(count>0){
            	resultList.add(new ValidMessage("indName", getMessage("RSKRDS_W_0019")));
            }
            //判断流程中是否存在相同的数据
            WflValidInputParameter param = new WflValidInputParameter();
            param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
            param.setBusinessDataPk1(dto.getRskSupervisionIndicatorDto().getIndName());
            param.setPageId("RskSupervisionIndicator002");
            if(null != mstWorkflowCommonService && 
                mstWorkflowCommonService.isInWflProcess(param)){
                resultList.add(
                    new ValidMessage(this.getMessage("COMMON_E_0012", dto.getRskSupervisionIndicatorDto().getIndName())));
            }
        }
        // 修改时验证
        if(WorkFlowUtil.isNotFromToDoList() && StringUtil.equals(dto.getOperateType(), "edit")) {
            if (rskSupervisionIndicator002Service.checkBusinessDataInFlow(dto)) {
            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getIndName())));
            }
            if (!StringUtil.isNotEmpty(rskSupervisionIndicator002Service.getBussinessEntityByIndId(dto.getIndId()))) {
            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
            }
            return resultList;
        }
        if(StringUtil.equals(dto.getOperateType(), "delete")) {
        	  if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
        		  dto.getSelectedItem().forEach(item -> {
		            if (rskSupervisionIndicator002Service.checkBusinessDataInFlow(item)) {
		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getIndName())));
		            }
		            if (!StringUtil.isNotEmpty(rskSupervisionIndicator002Service.getBussinessEntityByIndId(item.getIndId()) )) {
		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
		            }
                  });
        		  
        		  //检查删除前监督指标是否已关联组合
                if (checkIndIds(dto) > 0) {
                	String str = "该指标已绑定组合/组合包";
                	resultList.add(new ValidMessage(getMessage("BUSS_COM_E_0005",
                       str,StringUtil.EMPTY)));
                }
                  //检查删除前监督指标是否已关联组合
                  if (checkRuleIds(dto) > 0) {
                      String str = "该指标已绑定规则";
                      resultList.add(new ValidMessage(getMessage("BUSS_COM_E_0005",
                              str,StringUtil.EMPTY)));
                  }
                  return resultList;
        	  }
            
        }
        if(StringUtil.equals(dto.getOperateType(), "play")||StringUtil.equals(dto.getOperateType(), "stop")) {
      	  if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
      		  dto.getSelectedItem().forEach(item -> {
		            if (rskSupervisionIndicator002Service.checkBusinessDataInFlow(item)) {
		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getIndName())));
		            }
		            if (!StringUtil.isNotEmpty(rskSupervisionIndicator002Service.getBussinessEntityByIndId(item.getIndId()) )) {
		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
		            }
                });
      		  
              return resultList;
      	  }
          
      }
        return resultList;
    }
    
    private int checkIndIds(Object value) {
    	RskSupervisionIndicator002Dto dto = (RskSupervisionIndicator002Dto)value;
        List<String> id = dto.getSelectedItem().stream().map(
            item -> item.getIndId()).collect(Collectors.toList());
        return rskSupervisionIndicator002Service.searchReleteCountByIndId(id.get(0));
    }

    /**
     * 验证规则信息
     * @param value
     * @return
     */
    private int checkRuleIds(Object value) {
        RskSupervisionIndicator002Dto dto = (RskSupervisionIndicator002Dto)value;
        List<String> id = dto.getSelectedItem().stream().map(
                item -> item.getIndId()).collect(Collectors.toList());
        return rskSupervisionIndicator002Service.searchReleteCountByRuleId(id.get(0));
    }
}
