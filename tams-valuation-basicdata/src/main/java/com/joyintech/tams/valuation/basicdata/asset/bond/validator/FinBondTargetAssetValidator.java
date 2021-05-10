/*
 * 文件名：FinBondTargetAssetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.bond.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.bond.service.FinBondTargetAssetService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 债券标的信息业务验证
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetValidator
 * @since 1.0
 */
@Component
public class FinBondTargetAssetValidator implements BeanValidator{
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    @Autowired
    private FinBondTargetAssetService finBondTargetAssetService;
    @Override
    public List<ValidMessage> valid(Object value) {
        FinBondTargetAssetDto dto = (FinBondTargetAssetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        //选择永续时特殊条款必输
        String isSus =  dto.getIsSus();
        /*String finprodMarketId  =  dto.getFinprodMarketId();*/
        /*String pattern = "[A-Za-z].*[0-9]|[0-9].*[A-Za-z]"; 
        if(!Pattern.matches(pattern, finprodMarketId)){
            resultList.add(new ValidMessage("finprodMarketId",
                getMessage("BUSS_COM_E_0044", getMessage("FinBondTargetAsset002.finprodId"))));
        }*/
        if (StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")) {
            if(dto.getMdate()!=null &&DateUtil.compareDate(dto.getMdate(),DateUtil.platDateFromCache())<0){
                resultList.add(new ValidMessage("mdate",
                        getMessage("BUSS_COM_E_0004",getMessage("FinDepositTarget002.mdate"),getMessage("FinInterestFactor.platDate"))));
            }
            // 资产必须有还本计划
            if (!DictCodeList.息票品种_零息.getValue().equals(dto.getFinInterestFactorDto().getCouponSpecies())
                && !DictCodeList.息票品种_贴现.getValue().equals(dto.getFinInterestFactorDto().getCouponSpecies())
                && ((dto.getBussFinRepaymentDto() == null || dto.getBussFinRepaymentDto().getFinCashPlanList() == null
                     || dto.getBussFinRepaymentDto().getFinCashPlanList().size() == 0))) {
                resultList.add(new ValidMessage(getMessage("还本计划不能为空!")));
            }
            //含权债
            if (DictCodeList.是否_是.getValue().equals(dto.getIsRight())) {
            	if (dto.getBusiFinExercisePlanDto() == null 
            			|| dto.getBusiFinExercisePlanDto().getFinExercisePlanList() == null
            			|| dto.getBusiFinExercisePlanDto().getFinExercisePlanList().size() == 0) {
            		resultList.add(new ValidMessage(getMessage("含权债的行权计划不能为空!")));
            	}
            }
        }
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
                for(FinBondTargetAssetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId()) != 0){
                        if(StringUtil.equals(DictCodeList.是否_是.getValue(), dto.getBatchDelFlag())) {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_002", entity.getFinprodId())));
                            //break;
                        }
                        else {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_003")));
                            //break;
                        }
                        continue;
                    }
                    if(!StringUtil.equals(DictCodeListExt3.估值数据来源_手工.getValue(), entity.getDataSource())) {
                        if(StringUtil.equals(DictCodeList.是否_是.getValue(), dto.getBatchDelFlag())) {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_001", entity.getFinprodId())));
                            //break;
                        }
                        else {
                            resultList.add(new ValidMessage("", getMessage("VAL_ASSET_DEL_INFO_004")));
                            //break;
                        }
                        continue;
                    }

                }
            }
        }
        else{
            if(StringUtil.equals(dto.getCreditFlag(), DictCodeList.是否_是.getValue())
                && (dto.getActualCreditProvid() == null || StringUtil.equals(dto.getActualCreditProvid(), ""))){
                resultList.add(
                    new ValidMessage("actualCreditProvid",this.getMessage("FW_E_0006", getMessage("FinPlanAssetC002.actualCreditProvid"))));
            }
        }
        //校验金融产品代码是否重复
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
                if(finBondTargetAssetService.selectCountByfinprodId(dto.getFinprodMarketId())>0){
                    resultList.add(new ValidMessage("finprodMarketId",
                            getMessage("BUSS_COM_E_0006", getMessage("FinBondTargetAsset002.finprodId"),
                                    dto.getFinprodMarketId())));
                }
            }
            if(StringUtil.isNotEmpty(dto.getCirculationCode())){
                if(finBondTargetAssetService.selectCountByCirculationCode(dto.getCirculationCode(), "") > 0){
                    resultList.add(new ValidMessage("circulationCode",
                            getMessage("BUSS_COM_E_0006", getMessage("FinBondTargetAsset002.circulationCode"),
                                    dto.getCirculationCode())));
                }
            }
        }
        else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            if(StringUtil.isNotEmpty(dto.getCirculationCode())){
                if(finBondTargetAssetService.selectCountByCirculationCode(dto.getCirculationCode(), dto.getFinprodId()) > 0){
                    resultList.add(new ValidMessage("circulationCode",
                            getMessage("BUSS_COM_E_0006", getMessage("FinBondTargetAsset002.circulationCode"),
                                    dto.getCirculationCode())));
                }
            }
        }
        if(StringUtil.equals(isSus, DictCodeList.是否_是.getValue())){
            // 特殊条款已删除，相关check也删掉
          if(StringUtil.isEmpty(dto.getSustainableRemark())){
              resultList.add(new ValidMessage("sustainableRemark",
                  getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.sustainableRemark"))));
          }
        }
        if(null!=dto.getVdate()&&null!=dto.getMdate()){
            if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                resultList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0035", getMessage("FinBondTargetAsset002.vdate"),getMessage("FinBondTargetAsset002.mdate"))));
            }
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                Date maxTradeDate = commonBusinessService.getMaxTradeDate(dto.getFinprodId());
                if(null!=maxTradeDate){
                    if(DateUtil.compareDate(dto.getMdate(), maxTradeDate) < 0){
                        resultList.add(new ValidMessage("mdate",
                            getMessage("BUSS_COM_E_0004", getMessage("FinDepositTarget002.mdate"), "最大交易日:" + DateUtil.format(maxTradeDate))));
                    }
                }
            }
        }
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId()) && dto.getFinprodMarketId().indexOf(".")==0){
            resultList.add(new ValidMessage("finprodMarketId",
                getMessage("BUSS_COM_E_0038", getMessage("FinBondTargetAsset002.finprodId"))));
        }

        if(StringUtil.equals(dto.getAssetType(),DictCodeList.债券类型_可转债.getValue())
        ||StringUtil.equals(dto.getAssetType(),DictCodeList.债券类型_可交换债.getValue())){
            if(StringUtil.isEmpty(dto.getFinProductConvertible().getConFinprodId())) {
                resultList.add(new ValidMessage("conFinprodMarketId",
                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.conFinprodId"))));
            }
//            if(StringUtil.isEmpty(dto.getFinProductConvertible().getConShareId())) {
//                resultList.add(new ValidMessage("finProductConvertible.conShareId",
//                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.conShareId"))));
//            }
//            if(null==dto.getFinProductConvertible().getConVdate()) {
//                resultList.add(new ValidMessage("finProductConvertible.conVdate",
//                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.conVdate"))));
//            }
            if(null==dto.getFinProductConvertible().getConPrice()) {
                resultList.add(new ValidMessage("finProductConvertible.conPrice",
                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.conPrice"))));
            }
//            if(null==dto.getFinProductConvertible().getResalePrice()) {
//                resultList.add(new ValidMessage("finProductConvertible.resalePrice",
//                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.resalePrice"))));
//            }
            if(null==dto.getFinProductConvertible().getRedeemPrice()) {
                resultList.add(new ValidMessage("finProductConvertible.redeemPrice",
                        getMessage("COMMON_E_0057", getMessage("FinBondTargetAsset002.finProductConvertible.redeemPrice"))));
            }
        }
        return resultList;
    }
}
