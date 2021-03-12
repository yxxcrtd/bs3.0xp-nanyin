package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroup.service.CeAssetGroup002Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产系列授信验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see CeAssetGroupValidator
 * @since 1.0
 */
@Component
public class CeAssetGroupValidator implements BeanValidator{
	@Autowired
	CeAssetGroup002Service ceAssetGroup002Service;
	    
    @Autowired
   	CeOrg002Service ceOrg002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
    	CreditLinesAstGrpDto dto = (CreditLinesAstGrpDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||(/*!WorkFlowUtil.isNotFromToDoList() &&*/StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))) {
        	if(StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
                if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())==1){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0003", getMessage("CeAssetGroup002.vdate"),getMessage("CeAssetGroup002.mdate"))));
                }
            }

			if(dto.getOperateType().equals("submit") && StringUtil.isNotEmpty(dto.getVdate())){
				List<CreditLines> list = ceOrg002Service.selectCreditLines(dto.getCreditMstId(),dto.getCustomerId(),dto.getVdate(),dto.getSeqno());
				if(list != null && list.size()>0){
					resultList.add(new ValidMessage("vdate", "生效日必须大于已存在的授信生效日！"));
				}
			}

			if(dto.getOperateType().equals("submit") && StringUtil.isNotEmpty(dto.getMdate())){
				if (dto.getMdate().compareTo(DateUtil.platDateFromCache()) < 0){
					resultList.add(new ValidMessage("mdate", "到期日必须大于等于平台日！"));
				}
			}
//        	if(StringUtil.isNotEmpty(dto.getCustomerId())&&StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
//	            int vcount= ceOrg002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(), dto.getVdate());
//	            if(vcount>0){
//	            	resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0039",getMessage("CeAssetGroup002.vdate"))));
//	            }
//	            int mcount= ceOrg002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(),  dto.getMdate());
//	            if(mcount>0){
//	            	resultList.add(new ValidMessage("mdate", getMessage("RSK_W_0039",getMessage("CeAssetGroup002.mdate"))));
//	            }
//	            if(vcount<=0&&mcount<=0){
//		            int mscount= ceOrg002Service.searchRepeatByVmDateArea(dto.getSeqno(),dto.getCustomerId(),dto.getVdate(),  dto.getMdate());
//		            if(mscount>0){
//		            	resultList.add(new ValidMessage("", getMessage("RSK_W_0039",getMessage("CeOrg002.vmdate"))));
//		            }
//	            }
//        	}
        	if(StringUtil.isNotEmpty(dto.getPflsh())){
        		int count= ceOrg002Service.searchRepeatByPflsh(dto.getPflsh(),dto.getSeqno(),"");
 	            if(count>0){
 	            	resultList.add(new ValidMessage("pflsh", getMessage("BUSS_COM_I_0017",getMessage("CeAssetGroup002.pflsh"))));
 	            }
	            //判断流程中是否存在相同的数据
	            WflValidInputParameter param = new WflValidInputParameter();
	            if(!WorkFlowUtil.isNotFromToDoList() &&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
	            	param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
	            }
	            param.setBusinessDataPk2(dto.getPflsh());
	            param.setPageId("CeAssetGroup002");
	            if(null != mstWorkflowCommonService && 
	                mstWorkflowCommonService.isInWflProcess(param)){
	                resultList.add(
	                    new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPflsh())));
	            }
        	}
        }
        if(StringUtil.equals(dto.getOperateType(), "delete")) {
        	 if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
          		  dto.getSelectedItem().forEach(item -> {
   		            if (ceAssetGroup002Service.checkBusinessDataInFlow(item)) {
   		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getPflsh())));
   		            }
   		            if (!StringUtil.isNotEmpty(ceOrg002Service.getBussinessEntityByIndId(item.getSeqno()) )) {
   		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
   		            }
	                    });
          		
                  return resultList;
          	  }
        }
        return resultList;
    }
    
}
