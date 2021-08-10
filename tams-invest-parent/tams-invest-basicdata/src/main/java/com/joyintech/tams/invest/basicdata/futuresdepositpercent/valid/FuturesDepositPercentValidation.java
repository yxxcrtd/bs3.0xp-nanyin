package com.joyintech.tams.invest.basicdata.futuresdepositpercent.valid;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.service.FuturesDepositPercentService;
import com.joyintech.framework.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname FuturesDepositPercentValidation
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 14:31 
 * @version 1.0 
 */
@Component
public class FuturesDepositPercentValidation implements BeanValidator {
    @Autowired
    private FuturesDepositPercentService futuresDepositPercentService;
    
    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object o) {

        FuturesDepositPercentDto futuresDepositPercentDto = (FuturesDepositPercentDto)o;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        //校验是否存在待确认的流程
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("FuturesDepositPercent002");
        parameter.setBusinessDataPk1(futuresDepositPercentDto.getFuturesContractId());
        parameter.setBusinessDataPk2(futuresDepositPercentDto.getFuturesCompany());
        parameter.setBusinessDataPk3(futuresDepositPercentDto.getEffDateStr());
        parameter.setBusinessDataPk4(futuresDepositPercentDto.getOperateType());
        if (mstWorkflowCommonService.isInWflProcess(parameter)) {
        	resultList.add(new ValidMessage("同一合约标的，同一期货公司，同一生效日已存在流程中数据，不能重复提交"));
    	}
        if (StringUtil.isNotEmpty(futuresDepositPercentDto.getPageModel())&&futuresDepositPercentDto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("futuresType",futuresDepositPercentDto.getFuturesType());
            map.put("futuresContractId",futuresDepositPercentDto.getFuturesContractId());
            map.put("futuresCompany",futuresDepositPercentDto.getFuturesCompany());
            map.put("effDate",futuresDepositPercentDto.getEffDateStr());
            futuresDepositPercentDto = futuresDepositPercentService.selectByPrimaryKey(map);
            if (futuresDepositPercentService.selectByPrimaryKey(map) != null){
                resultList.add(new ValidMessage("同一合约标的，同一期货公司，同一生效日已有记录"));
            }
        }
        return resultList;
    }
}
