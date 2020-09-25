package com.joyintech.tams.invest.entrust.etrinstruction.validator; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;

import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrinstruction.dto.EtrInstructionDto;
import com.joyintech.tams.invest.entrust.etrinstruction.service.EtrInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EtrInstructionValidator implements EtrCommonValidator {

    @Autowired EtrInstructionService instructionService;

    @Override public List<ValidMessage> bussValid(Object value) {

        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        EtrInstructionDto etrInstructionDto =(EtrInstructionDto)value;
        if (StringUtil.equals(etrInstructionDto .getOperateType(), "delete")) {//删除
            List<EtrInstructionDto> selectItem = etrInstructionDto .getSelectedItem();
            for(EtrInstructionDto temp:selectItem){
                temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(instructionService.check(temp));
            }
        }
        if (StringUtil.equals(etrInstructionDto.getOperateType(), "recall")) {//撤销
            List<EtrInstructionDto> selectItem = etrInstructionDto .getSelectedItem();
            for(EtrInstructionDto temp:selectItem){
                temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(instructionService.check(temp));
            }
        }
        if (StringUtil.equals(etrInstructionDto.getOperateType(), "close")) {//关闭
            List<EtrInstructionDto> selectItem = etrInstructionDto .getSelectedItem();
            for(EtrInstructionDto temp:selectItem){
                temp.setEntrustOperationType(DictCodeListExt1.委托操作类型_关闭.getValue());
                temp.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
                errorList.addAll(instructionService.check(temp));
            }

        }
        if(StringUtil.equals(etrInstructionDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) ||StringUtil.equals(etrInstructionDto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT) ) {//新建
            //校验必填项
            if(StringUtil.isEmpty(etrInstructionDto.getAssetType1())){
                errorList.add(new ValidMessage("assetType1", getMessage("ETR_EI_001")));
            }
            if(StringUtil.isEmpty(etrInstructionDto.getAssetType10())){
                errorList.add(new ValidMessage("assetType10", getMessage("ETR_EI_002")));
            }
            if(StringUtil.isEmpty(etrInstructionDto.getRepFinancier())){
                errorList.add(new ValidMessage("repFinancier", getMessage("ETR_EI_003")));
            }
            if(etrInstructionDto.getEntrustAmt() ==null){
                errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_EI_004")));
            }
            if(StringUtil.isEmpty(etrInstructionDto.getCcy())){
                errorList.add(new ValidMessage("ccy", getMessage("ETR_EI_005")));
            }
        }
        //签署合同时必填项
        if(StringUtil.equals(etrInstructionDto.getPageModel(),"3")){
            if(etrInstructionDto.getContractResult() ==null){
                errorList.add(new ValidMessage("contractResult", getMessage("ETR_EI_010")));
            }
            if(StringUtil.isEmpty(etrInstructionDto.getContractNo())){
                errorList.add(new ValidMessage("contractNo", getMessage("ETR_EI_009")));
            }
        }

        return errorList;
    }
}
