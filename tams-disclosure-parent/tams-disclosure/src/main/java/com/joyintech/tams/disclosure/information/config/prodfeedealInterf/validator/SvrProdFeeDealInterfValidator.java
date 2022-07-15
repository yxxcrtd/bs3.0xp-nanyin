package com.joyintech.tams.disclosure.information.config.prodfeedealInterf.validator;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.service.SvrProdFeeDealInterfService;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SvrProdFeeDealInterfValidator implements BeanValidator {
    @SuppressWarnings("rawtypes")
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;
    @Autowired
    private SvrProdFeeDealInterfService svrProdFeeDealInterfService;
    @Override
    public List<ValidMessage> valid(Object o) {
        SvrProdFeeDealInterfDto dto = (SvrProdFeeDealInterfDto) o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
//        if (WorkFlowUtil.isNotFromToDoList()) {
//            if("delete".equals(dto.getOperateType())){
//                List<SvrProdFeeDealInterfDto> dtList = dto.getSelectedItem();
//                for (SvrProdFeeDealInterfDto dtp:dtList){
//                    ProcessMark processMarkb = new ProcessMark();
//                    processMarkb.setPageId("SvrProdFeeDealInterf002");
//                    processMarkb.setBusinessDataPk1(dtp.getProdId());
//                    processMarkb.setBusinessDataPk2(dtp.getPayDate());
//                    processMarkb.setBusinessDataPk3(dtp.getFeeType());
//                    List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
//                    if (!StringUtil.isEmptyList(resultListb)) {
//                        errorList.add(new ValidMessage("", "存在流程中数据"));
//                    }
//                }
//                if(!StringUtil.isEmptyList(errorList)){
//                    return errorList;
//                }
//            }
//            else{
//                ProcessMark processMarkb = new ProcessMark();
//                processMarkb.setPageId("SvrProdFeeDealInterf002");
//                processMarkb.setBusinessDataPk1(dto.getProdId());
//                processMarkb.setBusinessDataPk2(dto.getPayDate());
//                processMarkb.setBusinessDataPk3(dto.getFeeType());
//                List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
//                if (!StringUtil.isEmptyList(resultListb)) {
//                    errorList.add(new ValidMessage("", "存在流程中数据"));
//                    return errorList;
//                }
//            }
//        }

        if(!"delete".equals(dto.getOperateType())) {
            SvrProdFeeDealInterfDto svrProdFeeDealInterfDto= svrProdFeeDealInterfService.selectByProdID(dto);
            if(svrProdFeeDealInterfDto != null){
                errorList.add(new ValidMessage("", "“产品主代码+费用类型+支付日”不能同时与已有数据重复"));
            }
            if(StringUtil.isEmpty(dto.getProdId())){
                errorList.add(new ValidMessage("", "产品主代码不能为空"));
            }
            if(StringUtil.isEmpty(dto.getProdName())){
                errorList.add(new ValidMessage("", "产品名称不能为空"));
            }
            if(StringUtil.isEmpty(dto.getFeeType())){
                errorList.add(new ValidMessage("", "费用类型不能为空"));
            }else if(dto.getFeeType().equals("05")||dto.getFeeType().equals("06")||dto.getFeeType().equals("07")){
                if(dto.getPaidFee() == null){
                    errorList.add(new ValidMessage("", "当费用类型选择“申购费”“赎回费”“客户收益”时费用金额不可为空"));
                }
            }else if(dto.getFeeType().equals("01")||dto.getFeeType().equals("02")||dto.getFeeType().equals("03")||dto.getFeeType().equals("04")){
                if(dto.getAccrual() == null){
                    errorList.add(new ValidMessage("", "当费用类型选择“申购费”“赎回费”“客户收益”时自成立以来发生额（贷方）不可为空"));
                }
            }
            if(dto.getPayDate()== null){
                errorList.add(new ValidMessage("", "支付日不能为空"));
            }else{
                Date platDate = DateUtil.platDateFromCache();
                if(DateUtil.compareDate(dto.getPayDate(), platDate) > 0){
                    errorList.add(new ValidMessage("", "支付日不能大于平台日"));
                }
            }
        }

        return errorList;
    }
}
