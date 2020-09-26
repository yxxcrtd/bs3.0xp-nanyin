/*
 * 文件名：FinBondTargetAssetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.assets.bond.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.component.common.service.ValCommonBusinessService;
import com.joyintech.tams.valuation.assets.bond.dto.ValBondTargetAssetDto;
import com.joyintech.tams.valuation.assets.bond.service.ValBondTargetAssetService;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 债券标的信息业务验证
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetValidator
 * @since 1.0
 */
@Component
public class ValBondTargetAssetValidator implements BeanValidator{
    
    @Autowired
    private ValCommonBusinessService commonBusinessService;
    
    @Autowired
    private ValBondTargetAssetService finBondTargetAssetService;
    @Override
    public List<ValidMessage> valid(Object value) {
        ValBondTargetAssetDto dto = (ValBondTargetAssetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        //选择永续时特殊条款必输
        String isSus =  dto.getIsSus();
        /*String finprodMarketId  =  dto.getFinprodMarketId();*/
        /*String pattern = "[A-Za-z].*[0-9]|[0-9].*[A-Za-z]"; 
        if(!Pattern.matches(pattern, finprodMarketId)){
            resultList.add(new ValidMessage("finprodMarketId",
                getMessage("BUSS_COM_E_0044", getMessage("ValBondTargetAsset002.finprodId"))));
        }*/
        if (StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")) {
            if(dto.getMdate()!=null &&DateUtil.compareDate(dto.getMdate(),DateUtil.platDateFromCache())<0){
                resultList.add(new ValidMessage("mdate",
                        getMessage("BUSS_COM_E_0004",getMessage("FinDepositTarget002.mdate"),getMessage("RskSupervisionPortfPoint002.platDate"))));
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
                for(ValBondTargetAssetDto entity : dto.getSelectedItem()){
                    if(commonBusinessService.isTransInAsset(entity.getFinprodId())!=0){
                        resultList.add(new ValidMessage("",
                            getMessage("资产存在交易,不可删除!")));
                        break;
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
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if(StringUtil.isNotEmpty(dto.getFinprodMarketId())){
                if(finBondTargetAssetService.selectCountByfinprodMarketId(dto.getFinprodMarketId())>0){
                    resultList.add(new ValidMessage("finprodMarketId",
                        getMessage("BUSS_COM_E_0006", getMessage("ValBondTargetAsset002.finprodId"))));
                }
            }
        }
        if(StringUtil.equals(isSus, DictCodeList.是否_是.getValue())){
            // 特殊条款已删除，相关check也删掉
          if(StringUtil.isEmpty(dto.getSustainableRemark())){
              resultList.add(new ValidMessage("sustainableRemark",
                  getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.sustainableRemark"))));
          }
        }
        if(null!=dto.getVdate()&&null!=dto.getMdate()){
            if(DateUtil.compareDate(dto.getVdate(), dto.getMdate())!=-1){
                resultList.add(new ValidMessage("vdate",
                    getMessage("BUSS_COM_E_0035", getMessage("ValBondTargetAsset002.vdate"),getMessage("ValBondTargetAsset002.mdate"))));
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
        //二级分类非空check
        if(StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_金融债.getValue())
            ||StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_短期融资券.getValue())
            ||StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_中期票据.getValue())
            ||StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_企业债.getValue())
            ||StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_公司债.getValue())
            ||StringUtil.equals(dto.getType4(), DictCodeList.WIND一级分类_含债券一级分类等_资产支持证券.getValue())){
            if(StringUtil.isEmpty(dto.getType5())){
                resultList.add(new ValidMessage("type5",
                    getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.type5"))));
            }
        }
        if(StringUtil.isNotEmpty(dto.getFinprodMarketId()) && dto.getFinprodMarketId().indexOf(".")==0){
            resultList.add(new ValidMessage("finprodMarketId",
                getMessage("BUSS_COM_E_0038", getMessage("ValBondTargetAsset002.finprodId"))));
        }

        //是否城投债 为是时，城投债级别必填
        if (StringUtil.equals(dto.getIsCityBond(), DictCodeList.是否_是.getValue()) && StringUtil.isEmpty(dto.getCityBondLev())){
            resultList.add(new ValidMessage("cityBondLev",
                    getMessage("BUSS_COM_E_0038", getMessage("ValBondTargetAsset002.cityBondLev"))));
        }
        //是否展示资产四五级分类
        if(StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"),"01") &&  (StringUtil.equals(dto.getOperateType(), "save") ||  StringUtil.equals(dto.getOperateType(), "submit"))) {
        	if(StringUtil.isEmpty(dto.getType11())) {
        		resultList.add(new ValidMessage("type11",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.type11"))));
        	}
        	if(StringUtil.isEmpty(dto.getType12())) {
        		resultList.add(new ValidMessage("type12",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.type12"))));
        	}
        }
        if(StringUtil.equals(dto.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())
        ||StringUtil.equals(dto.getType4(),DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue())){
            if(StringUtil.isEmpty(dto.getFinProductConvertible().getConFinprodId())) {
                resultList.add(new ValidMessage("conFinprodMarketId",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.conFinprodId"))));
            }
            if(StringUtil.isEmpty(dto.getFinProductConvertible().getConShareId())) {
                resultList.add(new ValidMessage("finProductConvertible.conShareId",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.conShareId"))));
            }
            if(null==dto.getFinProductConvertible().getConVdate()) {
                resultList.add(new ValidMessage("finProductConvertible.conVdate",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.conVdate"))));
            }
            if(null==dto.getFinProductConvertible().getConPrice()) {
                resultList.add(new ValidMessage("finProductConvertible.conPrice",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.conPrice"))));
            }
            if(null==dto.getFinProductConvertible().getResalePrice()) {
                resultList.add(new ValidMessage("finProductConvertible.resalePrice",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.resalePrice"))));
            }
            if(null==dto.getFinProductConvertible().getRedeemPrice()) {
                resultList.add(new ValidMessage("finProductConvertible.redeemPrice",
                        getMessage("COMMON_E_0057", getMessage("ValBondTargetAsset002.finProductConvertible.redeemPrice"))));
            }
        }
        return resultList;
    }
}
