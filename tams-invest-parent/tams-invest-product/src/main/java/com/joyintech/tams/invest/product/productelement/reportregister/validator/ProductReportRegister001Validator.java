package com.joyintech.tams.invest.product.productelement.reportregister.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.product.productelement.reportregister.dto.ProductReportRegister001Dto;
import com.joyintech.tams.invest.product.productelement.reportregister.service.ProductReportRegister001Service;
/**
 * 产品申报删除修改验证
 * 
 * @author yaozhenguo
 * @version 1.0 2019年10月12日
 * @see ProductReportRegister001Validator
 * @since 1.0
 */
@Component
public class ProductReportRegister001Validator implements BeanValidator {

	@Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	@Autowired
	private ProductReportRegister001Service productReportRegister001Service;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		ProductReportRegister001Dto entity = (ProductReportRegister001Dto)value;
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		//流程验证
		WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
        wflValidInputParameterIssue.setPageId(entity.getPageId());
        wflValidInputParameterIssue.setWorkflowId(entity.getWorkflowId());
        wflValidInputParameterIssue.setBusinessDataPk1(entity.getRepFinprodId());
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
            validList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                "")));
        }
        if(StringUtil.equals(entity.getOperateType(), "delete")) {
        	int i = productReportRegister001Service.selsectByFinprod(entity.getRepFinprodId());
        	if(i > 0) {
        		validList.add(new ValidMessage(getMessage("FIN_PROD_E_0141")));
        	}
        }
		return validList;
	}

}
