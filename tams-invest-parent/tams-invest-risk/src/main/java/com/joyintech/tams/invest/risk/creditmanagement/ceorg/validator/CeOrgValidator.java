package com.joyintech.tams.invest.risk.creditmanagement.ceorg.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.common.base.dao.CreditMstInfoMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditMstInfo;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.service.CeOrg002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 机构授信验证
 * @author 裴家俊
 * @version 1.0 2018年11月4日
 * @see CeOrgValidator
 * @since 1.0
 */
@Component
public class CeOrgValidator implements BeanValidator{
    @Autowired
	CeOrg002Service ceOrg002Service;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
    	CreditLinesOrgDto dto = (CreditLinesOrgDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)||(/*!WorkFlowUtil.isNotFromToDoList() &&*/StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT))) {
        	if(StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
                if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())==1){
                    resultList.add(new ValidMessage("",
                        getMessage("BUSS_COM_E_0003", getMessage("CeOrg002.vdate"),getMessage("CeOrg002.mdate"))));
                }
            }

			if(StringUtil.isNotEmpty(dto.getVdate())){
				List<CreditLines> list = ceOrg002Service.selectCreditLines(dto.getCreditMstId(),dto.getCustomerId(),dto.getVdate());
				if(list != null && list.size()>0){
					resultList.add(new ValidMessage("vdate", "该机构在当前确认日已存在授信！"));
				}
			}
        	if(StringUtil.isNotEmpty(dto.getCustomerId())&&StringUtil.isNotEmpty(dto.getVdate())&&StringUtil.isNotEmpty(dto.getMdate())){
	           /* int vcount= ceOrg002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(), dto.getVdate());
	            if(vcount>0){
	            	resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0039",getMessage("CeOrg002.vdate"))));
	            }
	            int mcount= ceOrg002Service.searchRepeatByVmDate(dto.getSeqno(),dto.getCustomerId(),  dto.getMdate());
	            if(mcount>0){
	            	resultList.add(new ValidMessage("mdate", getMessage("RSK_W_0039",getMessage("CeOrg002.mdate"))));
	            }
	            if(vcount<=0&&mcount<=0){
		            int mscount= ceOrg002Service.searchRepeatByVmDateArea(dto.getSeqno(),dto.getCustomerId(),dto.getVdate(),  dto.getMdate());
		            if(mscount>0){
		            	resultList.add(new ValidMessage("", getMessage("RSK_W_0039",getMessage("CeOrg002.vmdate"))));
		            }
	            }*/

	           //去除以上原有逻辑  ；当前业务逻辑： 修改时如果当前数据额度已被占用则不允许操作 20191024
				boolean ispass= ceOrg002Service.searchIsUse(dto.getSeqno(),dto.getCustomerId(), dto.getVdate(),dto.getMdate(),dto.getPflsh());
				if(ispass){
					resultList.add(new ValidMessage("vdate", getMessage("RSK_W_0049",getMessage("CeOrg002.vdate"))));
				}

        	}
        	if(StringUtil.isNotEmpty(dto.getPflsh())){
        		int count= ceOrg002Service.searchRepeatByPflsh(dto.getPflsh(),dto.getSeqno(),dto.getCreditMstId());
 	            if(count>0){
 	            	resultList.add(new ValidMessage("pflsh", getMessage("BUSS_COM_I_0017",getMessage("CeOrg002.pflsh"))));
 	            }
	            //判断流程中是否存在相同的数据
	            WflValidInputParameter param = new WflValidInputParameter();
	            if(!WorkFlowUtil.isNotFromToDoList() &&StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
	            	param.setWorkflowId(dto.getWorkflowId());//去除自身这个流程
	            }
	            param.setBusinessDataPk2(dto.getPflsh());
	            param.setPageId("CeOrg002");
	            if(null != mstWorkflowCommonService && 
	                mstWorkflowCommonService.isInWflProcess(param)){
	                resultList.add(
	                    new ValidMessage(this.getMessage("COMMON_E_0012",dto.getPflsh())));
	            }
        	}
			if(StringUtil.isNotEmpty(dto.getCreditAmt()) && !StringUtil.isEmptyList(dto.getCreditQuoteItem())){
				BigDecimal creditAmt = BigDecimal.ZERO;
				Map<String, Object> pageParam =new HashMap<>();
				Page<CreditMstInfoDto> pag = ceOrg002Service.getTreeCeOrgQuote(pageParam);
				Map<String,List<CreditMstInfoDto>> sssMap = pag.getRoot().stream().collect(JoyinCollectors.groupingBy(CreditMstInfoDto::getUpperCreditQuoteTypeCode));
				Map<String,List<CreditMstInfoDto>>  mapmap = new HashMap<>();
				Map<String,BigDecimal>  maptwo = new HashMap<>();
				for (CreditLines item:dto.getCreditQuoteItem()){
					for (CreditMstInfoDto dto1:sssMap.get("root")) {
						if (dto1.getCreditMstId().equals(item.getCreditMstId())) {
							creditAmt = ArithmeticUtil.doAdd(creditAmt, item.getCreditAmt());
						}
					}
					for (CreditMstInfoDto dto1:pag.getRoot()){
						if (dto1.getCreditMstId().equals(item.getCreditMstId())){
							maptwo.put(dto1.getCreditMstId(),item.getCreditAmt());
							mapmap.put(dto1.getCreditMstId(),sssMap.get(dto1.getCreditQuoteTypeCode()));
						}
					}
				}
				if (StringUtil.isNotEmpty(mapmap)){
 					for (String str: mapmap.keySet()){
						List<CreditMstInfoDto> listlist = mapmap.get(str);
						if (StringUtil.isEmptyList(listlist)){
							continue;
						}
						BigDecimal creditAmtsum1 = BigDecimal.ZERO;
						for (CreditLines item : dto.getCreditQuoteItem()) {
						     for (CreditMstInfoDto dto2:listlist) {
								if (dto2.getCreditMstId().equals(item.getCreditMstId())) {
									creditAmtsum1 = ArithmeticUtil.doAdd(creditAmtsum1, item.getCreditAmt());
								}
							}
						}
						if (maptwo.get(str).compareTo(creditAmtsum1) < 0){
							resultList.add(new ValidMessage("授信品种分项各明细额度不能大于分项额度"));
						}
					}
				}

				if (creditAmt.compareTo(dto.getCreditAmt()) > 0){
					resultList.add(new ValidMessage("授信品种额度不能大于总额度"));
				}
			}

        }
        if(StringUtil.equals(dto.getOperateType(), "delete")) {
        	 if(CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
         		  dto.getSelectedItem().forEach(item -> {
  		            if (ceOrg002Service.checkBusinessDataInFlow(item)) {
  		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0012", item.getPflsh())));
  		            }
  		            if (!StringUtil.isNotEmpty(ceOrg002Service.getBussinessEntityByIndId(item.getSeqno()) )) {
  		            	resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
  		            }
  		            if(StringUtil.isNotEmpty(item.getHapCreditAmt())&&item.getHapCreditAmt().compareTo(BigDecimal.ZERO)>0){
  		          		 resultList.add(
  		   	                    new ValidMessage(this.getMessage("RSK_W_0049",getMessage("CeOrg001"))));
  		          		}
                   });
         		
                 return resultList;
         	  }
        	/*if(StringUtil.isNotEmpty(dto.getHapCreditAmt())&&dto.getHapCreditAmt().compareTo(BigDecimal.ZERO)>0){
        		 resultList.add(
 	                    new ValidMessage(this.getMessage("RSK_W_0040",getMessage("CeOrg001"))));
        	}*/
        }
        return resultList;
    }
    
}
