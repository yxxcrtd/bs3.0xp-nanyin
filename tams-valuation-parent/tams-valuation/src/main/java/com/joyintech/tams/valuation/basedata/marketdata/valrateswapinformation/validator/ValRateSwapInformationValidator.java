package com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
//import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
//import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.dto.ValRateSwapInformationDto;
import com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.service.ValRateSwapInformationService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: ValRateSwapInformationValidator
 * @package com.joyintech.tams.invest.basicdata.rateswapinformation.validator
 * @description: 利率行情互换行情信息新建校验
 * @author: daizhiwei
 * @date: 2019-07-22 14:56
 */
@Component
public class ValRateSwapInformationValidator implements BeanValidator {

    @Autowired
    private ValRateSwapInformationService valRateSwapInformationService;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
        List<ValidMessage> errorList = new ArrayList<>();
        ValRateSwapInformationDto dto = (ValRateSwapInformationDto)value;
        //从列表中取第一条数据作为实体
		/*
		 * ValRateSwapInformationDto entity = dto.getValRateSwapInformationDtoList().get(0);
		 * if (entity!= null && (dto.getCurveCode() == null || dto.getCurveDate() ==
		 * null)){ dto.setCurveCode(entity.getCurveCode());
		 * dto.setCurveDate(entity.getCurveDate()); }
		 */
        //未输入曲线品种或曲线日期则返回
        if(dto.getCurveType() == null || dto.getCurveDate() == null) {
        	return errorList;
        }
        //验证是否存在流程中数据
        errorList.addAll(valRateSwapInformationService.checkIsInWfl(dto));

        //验证数据库中是否存在与操作数据相同的数据
        boolean flag = valRateSwapInformationService.check(dto);
        if (StringUtil.equals(dto.getOperateType(), "delete")){
            return errorList;
        }else if (StringUtil.equals(dto.getOperateType(), "edit")){
            //编辑时验证数据库中是否存在对应数据
            if (!flag){
                errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0003")));
                return errorList;
            }
        }else{

            List<ValRateSwapInformationDto> rateSwapInformationDtoList = dto.getValRateSwapInformationDtoList();
            //添加时重复性验证,编辑时不验证
            if (flag && dto.isValidExist()){
                errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0006")));
                return errorList;
            }
            WflValidInputParameter parameter = new WflValidInputParameter();
            parameter.setPageId("RateSwapInformation002");
            parameter.setBusinessDataPk1(dto.getCurveCode());
            parameter.setBusinessDataPk2(dto.getCurveDateStr());
            boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
            if (isInWfl){
            	if(StringUtil.isNotEmpty(dto.getWorkflowId())){
            		ValRateSwapInformationDto workFlow = valRateSwapInformationService.getBussinessEntityByWorkflowId(dto.getWorkflowId());
                    if(!StringUtil.equals(dto.getCurveCode(), workFlow.getCurveCode())
                    		||!StringUtil.equals(dto.getCurveDateStr(), workFlow.getCurveDateStr())
                    		){
                    	errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0007",dto.getCurveCode(),dto.getCurveDateStr())));
                    }
            	}else{
            		errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0007",dto.getCurveCode(),dto.getCurveDateStr())));
            	}
            }

            // 保存时数据列表为空验证
            if (rateSwapInformationDtoList == null || rateSwapInformationDtoList.size() == 0){
                errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0001")));
            }else {
                //验证期限品种的每一项不能为空 -> 改为只验证收益率	王贵奇	2019/9/27
                rateSwapInformationDtoList.forEach(dtoItem ->{
                    String termTypeIrs = dtoItem.getTermTypeIrs();
                    if (dtoItem.getYield() == null){
                        errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0002",termTypeIrs,this.getMessage("RateSwapInformation002.yield"))));
                    }
                    /*if (dtoItem.getBidPrice() == null){
                        errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0002",termTypeIrs,this.getMessage("RateSwapInformation002.bidPrice"))));
                    }
                    if (dtoItem.getOfferPrice() == null){
                        errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0002",termTypeIrs,this.getMessage("RateSwapInformation002.offerPrice"))));
                    }
                    if(dtoItem.getAvgPrice() == null){
                        errorList.add(new ValidMessage(this.getMessage("MST_RateSwapInfo_0002",termTypeIrs,this.getMessage("RateSwapInformation002.avgPrice"))));
                    }*/
                });
            }
        }
        return errorList;
    }
}
