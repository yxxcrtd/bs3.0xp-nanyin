package com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.service.CePreOccupancyManagement002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 预占用授信验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see CePreOccupancyManagementValidator
 * @since 1.0
 */
@Component
public class CePreOccupancyManagementValidator implements BeanValidator{
    @Autowired
    CePreOccupancyManagement002Service cePreOccupancyManagement002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
    	CePreOccupancyManagementDto dto = (CePreOccupancyManagementDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))) {
        	if(StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
                if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())==1){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0003", getMessage("CeOrg002.vdate"),getMessage("CeOrg002.mdate"))));
                }
            }
        	if(StringUtil.isNotEmpty(dto.getCustomerId())&&StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
	            int vcount= cePreOccupancyManagement002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(), dto.getVdate());
	            if(vcount>0){
	            	resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0039",getMessage("CeOrg002.vdate"))));
	            }
	            int mcount= cePreOccupancyManagement002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(),  dto.getMdate());
	            if(mcount>0){
	            	resultList.add(new ValidMessage("mdate", getMessage("RSK_W_0039",getMessage("CeOrg002.mdate"))));
	            }
	            if(vcount<=0&&mcount<=0){
		            int mscount= cePreOccupancyManagement002Service.searchRepeatByVmDateArea(dto.getSeqno(),dto.getCustomerId(),dto.getVdate(),  dto.getMdate());
		            if(mscount>0){
		            	resultList.add(new ValidMessage("", getMessage("RSK_W_0039",getMessage("CeOrg002.vmdate"))));
		            }
	            }
        	}
        	
        }
        if(StringUtil.equals(dto.getOperateType(), "delete")) {
        	 if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
         		  dto.getSelectedItem().forEach(item -> {
  		            if (!StringUtil.isNotEmpty(cePreOccupancyManagement002Service.getBussinessEntityByIndId(item.getSeqno()) )) {
  		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
  		            }
  		           
                   });
         		
                 return resultList;
         	  }
        	
        }
        return resultList;
    }
    
}
