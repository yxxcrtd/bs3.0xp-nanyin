/*
 * 文件名：PtlActivityPeriod001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.validator.CommonValidator;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlActivityPeriod001Validator implements CommonValidator {
    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;
    @Override
    public List<ValidMessage> bussValid(Object value){
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlActivityPeriod001Dto dto = (PtlActivityPeriod001Dto)value;
        String operateType = dto.getOperateType();
        if(StringUtil.equals(operateType, "submit") || StringUtil.equals(operateType, "save")) {
            if(dto.getTradeAmt() !=null && dto.getTradeAmt().compareTo(BigDecimal.ZERO)<=0) {
                errorList.add(new ValidMessage("tradeAmt",getMessage("BUSS_COM_E_0004", getMessage("PtlActivityPeriod001.tradeAmts"),"0")));
            }
            
            if(StringUtil.isEmptyList(dto.getDetailsList())){
                errorList.add(new ValidMessage("",getMessage("PTL_COM_E_0031")));
            }
            //同个账户，同个本次收息日新建是否存在流程数据
            if (WorkFlowUtil.isNotFromToDoList()) {
            	List<String> busiAcctNolists = StringUtil.splitToList(null, dto.getBusiAcctNo());
            	for(int i=0;i<busiAcctNolists.size();i++){
            		ProcessMark processMarkb = new ProcessMark();
                    processMarkb.setPageId("PtlActivityPeriod002");
                    processMarkb.setBusinessDataPk3(busiAcctNolists.get(i));
                    processMarkb.setBusinessDataPk4(dto.getMdateStr());
                    List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
                    if (!StringUtil.isEmptyList(resultListb)) {
                        errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", busiAcctNolists.get(i),dto.getMdateStr())));
                    }
            	}
            }
        } else {
            if(StringUtil.equals(dto.getTradeStatus(), DictCodeListExt1.交易流程状态_撤销.getValue())) {
                errorList.add(new ValidMessage("",getMessage("FIN_ASSET_E_0006")));  
            } else {
                //判断方案是否在流程中
                ProcessMark processMarkb = new ProcessMark();
                processMarkb.setPageId("FinProductFeePay004");
                processMarkb.setBusinessDataPk1(dto.getBusiNo());
                processMarkb.setBusinessDataPk2(dto.getBusiType());
                List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
                if (!StringUtil.isEmptyList(resultListb)) {
                    errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getBusiNo(),dto.getBusiType())));
                }
            }
        }


      return errorList;
    }
}
