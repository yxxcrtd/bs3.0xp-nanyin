/*
 * 文件名：BussRepEquityInfoDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 权益类中债报送--业务组件
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see //BussRepEquityInfoDtoValidator
 * @since 1.0
 */
@Component
public class BussRepEquityInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepEquityInfoDto dto = (BussRepEquityInfoDto)value;//value是object类型，此处把value的值转成dto
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_股权.getValue())
        		||StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())){
            if(StringUtil.isNotEmpty(dto.getRepAssetType())){
                if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                    resultList.add(new ValidMessage("repTradeMarket",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repTradeMarket"))));
                }
                if(StringUtil.isEmpty(dto.getRepSharesType())){
                    resultList.add(new ValidMessage("repSharesType",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repSharesType"))));
                }
                if(StringUtil.isEmpty(dto.getRepInvestStage())){
                    resultList.add(new ValidMessage("repInvestStage",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repInvestStage"))));
                }
                if(StringUtil.isEmpty(dto.getRepPartyType1())){
                    resultList.add(new ValidMessage("repPartyType1",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType1"))));
                }
                if(StringUtil.isEmpty(dto.getRepPartyType2())){
                    resultList.add(new ValidMessage("repPartyType2",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType2"))));
                }
                if(StringUtil.isEmpty(dto.getRepPartyType3())){
                    resultList.add(new ValidMessage("repPartyType3",
                            getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType3"))));
                }
                //中债新增 20200407 start
                //股权退出安排
                if(StringUtil.equals(dto.getRepAssetType(),DictCodeListExt2.资产_负债二级类别_股权.getValue())) {
                    if (null == dto.getRepEquityQuit()) {
                        resultList.add(new ValidMessage("repEquityQuit",
                                getMessage("COMMON_E_0057", getMessage("BussRepOther.repEquityQuit"))));
                    }
                }
                //中债新增 20200407 end
                if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                    if(StringUtil.isEmpty(dto.getRepRemark())){
                        resultList.add(new ValidMessage("repRemark",
                                getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repRemark"))));
                    }
                }
            }
        }
       else if(StringUtil.equals(dto.getType8(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())){
            if(StringUtil.isEmpty(dto.getRepPartyType1())){
                resultList.add(new ValidMessage("repPartyType1",
                    getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType1"))));
            }
            if(StringUtil.isEmpty(dto.getRepPartyType2())){
                resultList.add(new ValidMessage("repPartyType2",
                    getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType2"))));
            }
            if(StringUtil.isEmpty(dto.getRepPartyType3())){
                resultList.add(new ValidMessage("repPartyType3",
                    getMessage("COMMON_E_0057", getMessage("BussRepEquityInfo.repPartyType3"))));
            }
        }
        return resultList;
    }

}
