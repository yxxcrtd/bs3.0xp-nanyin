/*
 * 文件名：BussRepOtherInvestTargetInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepOtherInvestTargetInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 *  其他资产报送信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepOtherInvestTargetInfoValidator
 * @since 1.0
 */
@Component
public class BussRepOtherInvestTargetInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepOtherInvestTargetInfoDto dto = (BussRepOtherInvestTargetInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他非标准化债权类投资.getValue())
        ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他新增可投资资产.getValue())
        ||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())){
            if(StringUtil.isNotEmpty(dto.getRepAssetType())){
                if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                    resultList.add(new ValidMessage("repTradeMarket",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repTradeMarket"))));
                }
                if(StringUtil.isEmpty(dto.getRepCountryArea())){
                    resultList.add(new ValidMessage("repCountryArea",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repCountryArea"))));
                }
                if(null==dto.getRepIntpayCycle()){
                    resultList.add(new ValidMessage("repIntpayCycle",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repIntpayCycle"))));
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFinancier())){
                    resultList.add(new ValidMessage("repFinancier",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinancier"))));
                }
                }
                if(StringUtil.isEmpty(dto.getRepFinancierOrgCode())){
                    resultList.add(new ValidMessage("repFinancierOrgCode",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinancierOrgCode"))));
                }else{
                    //String pattern = "[A-Z].*[0-9]|[0-9].*[A-Z]";
                    String pattern = "[A-Z0-9]+";
                    if(!Pattern.matches(pattern, dto.getRepFinancierOrgCode())){
                        resultList.add(new ValidMessage("repFinancierOrgCode",
                                getMessage("FIN_ASSET_E_0027", getMessage("BussRepOtherInvestTargetInfo.repFinancierOrgCode"))));
                    }else if(StringUtil.byteLength(dto.getRepFinancierOrgCode())!=9&&StringUtil.byteLength(dto.getRepFinancierOrgCode())!=18){
                        resultList.add(new ValidMessage("repFinancierOrgCode",
                                getMessage("FIN_ASSET_E_0027", getMessage("BussRepOtherInvestTargetInfo.repFinancierOrgCode"))));
                    }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFcrGradeOut())){
                    resultList.add(new ValidMessage("repFcrGradeOut",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFcrGradeOut"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFinancierType1())){
                    resultList.add(new ValidMessage("repFinancierType1",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinancierType1"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFinancierType2())){
                    resultList.add(new ValidMessage("repFinancierType2",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinancierType2"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFinancierType3())){
                    resultList.add(new ValidMessage("repFinancierType3",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinancierType3"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepFinanceProj())){
                    resultList.add(new ValidMessage("repFinanceProj",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repFinanceProj"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepIndustry1())){
                    resultList.add(new ValidMessage("repIndustry1",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repIndustry1"))));
                }
                if(StringUtil.isEmpty(dto.getRepIndustryLevel2())){
                    resultList.add(new ValidMessage("repIndustryLevel2",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repIndustryLevel2"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepProIndustry())){
                    resultList.add(new ValidMessage("repProIndustry",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repProIndustry"))));
                }
                if(StringUtil.isEmpty(dto.getRepProIndustryLevel2())){
                    resultList.add(new ValidMessage("repProIndustryLevel2",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repProIndustryLevel2"))));
                }
                }
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_其他权益类投资.getValue())) {
                if(StringUtil.isEmpty(dto.getRepIndustryFlag())){
                    resultList.add(new ValidMessage("repIndustryFlag",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repIndustryFlag"))));
                }
                }
                if(StringUtil.equals(dto.getRepIndustryFlag(), DictCodeList.是否_是.getValue())){
                    if(StringUtil.isEmpty(dto.getRepMonTypeComm())){
                        resultList.add(new ValidMessage("repMonTypeComm",
                                getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repMonTypeComm"))));
                    }
                    if(StringUtil.isEmpty(dto.getRepMonitType())){
                        resultList.add(new ValidMessage("repMonitType",
                                getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repMonitType"))));
                    }
                }
            /*if(StringUtil.isEmpty(dto.getRepMonTypeComm())){
                resultList.add(new ValidMessage("repMonTypeComm",
                    getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repMonTypeComm"))));
            }*/
                if(StringUtil.isEmpty(dto.getRepOutGrade())){
                    resultList.add(new ValidMessage("repOutGrade",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repOutGrade"))));
                }
                if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                    if(StringUtil.isEmpty(dto.getRepRemark())){
                        resultList.add(new ValidMessage("repRemark",
                                getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repRemark"))));
                    }
                }
                if(null==dto.getRepAssetRegDate()){
                    resultList.add(new ValidMessage("repAssetRegDate",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repAssetRegDate"))));
                }
                /*if(StringUtil.isEmpty(dto.getRepBankAssetTyprExplain())){
                    resultList.add(new ValidMessage("repBankAssetTyprExplain",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repBankAssetTyprExplain"))));
                }*/
				 //                行内资产类别必填，行内资产类别说明选填 by zhuyongkang  20191108
//                if(!StringUtil.equals(dto.getType6(),StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票质押式回购.getValue()))
//                &&!StringUtil.equals(dto.getType6(),StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_其他权益类投资.getValue()))){
                    if(StringUtil.isEmpty(dto.getRepBankAssetType())){
                        resultList.add(new ValidMessage("repBankAssetType",
                                getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repBankAssetType"))));
                    }
//                }
                if(StringUtil.isNotEmpty(dto.getRepBankAssetType())
                    && dto.getRepBankAssetType().equals(DictCodeListExt2.行内资产类别_其他.getValue())&&StringUtil.isEmpty(dto.getRepBankAssetTyprExplain())){
                    resultList.add(new ValidMessage("repBankAssetTyprExplain",
                            getMessage("COMMON_E_0057", getMessage("BussRepOtherInvestTargetInfo.repBankAssetTyprExplain"))));
                }
            }

    }
        /*if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票质押式回购.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_其他权益类投资.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商债权收益权投资.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商收益凭证.getValue())){

        }*/
        return resultList;
    }
}
