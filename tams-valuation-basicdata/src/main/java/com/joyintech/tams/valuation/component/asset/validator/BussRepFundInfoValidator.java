/*
 * 文件名：BussRepFundInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.asset.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.component.asset.dto.BussRepFundInfoDto;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月8日
 * @see BussRepFundInfoValidator
 * @since 1.0
 */
@Component
public class BussRepFundInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepFundInfoDto dto = (BussRepFundInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        String  finprodType2  = dto.getFinprodType2();
        String  type6 = dto.getType6();
        if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_基金.getValue())
            ||(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                &&StringUtil.equals(dto.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_基础设施基金.getValue()))
            ){
            if(StringUtil.isEmpty(dto.getRepAssetType())){
                resultList.add(new ValidMessage("repAssetType",
                    getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repAssetType"))));
            }
            if(StringUtil.isNotEmpty(dto.getRepAssetType())){
                if(StringUtil.isEmpty(dto.getRepIndustry())){
                    resultList.add(new ValidMessage("repIndustry",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repIndustry"))));
                }

                if(StringUtil.isEmpty(dto.getRepIndustryLevel2())){
                    resultList.add(new ValidMessage("repIndustryLevel2",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repIndustryLevel2"))));
                }

                if(StringUtil.isEmpty(dto.getRepRegistOrg())){
                    resultList.add(new ValidMessage("repRegistOrg",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repRegistOrg"))));
                }

                if(StringUtil.isEmpty(dto.getRepFundInvestAsset())){
                    resultList.add(new ValidMessage("repFundInvestAsset",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repFundInvestAsset"))));
                }

            /*if(StringUtil.isEmpty(dto.getRepFundCompName())){
                resultList.add(new ValidMessage("repFundCompName",
                    getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repFundCompName"))));
            }*/
            /*if(StringUtil.isEmpty(dto.getRepMngPartyName())){
                resultList.add(new ValidMessage("repMngPartyName",
                    getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repMngPartyName"))));
            }*/
                if(StringUtil.isEmpty(dto.getRepTrustPartyName())){
                    resultList.add(new ValidMessage("repTrustPartyName",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repTrustPartyName"))));
                }
                if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                    resultList.add(new ValidMessage("repTradeMarket",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repTradeMarket"))));
                }
                if(dto.isGovInvest()){
                    if( "".equals(dto.getRepGovInvestFor()) || null == dto.getRepGovInvestFor()){
                        resultList.add(new ValidMessage("repGovInvestFor",
                                getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repGovInvestFor"))));
                    }
                }
                if(dto.getRepRemark() == null || StringUtil.equals("", dto.getRepRemark())
                        && StringUtil.equals(dto.getRepTradeMarket(),DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                    resultList.add(new ValidMessage("repRemark",
                            getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repRemark"))));
                }
            }
        }
        if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())
                &&StringUtil.equals(dto.getRepAssetType(), DictCodeListExt2.资产_负债二级类别_基础设施基金.getValue())
        ){
            if(StringUtil.isEmpty(dto.getRepFundCompName())){
                resultList.add(new ValidMessage("repFundCompName",
                        getMessage("COMMON_E_0057", getMessage("BussRepFundInfo.repFundCompName"))));
            }
        }
        return resultList;
    }

}
