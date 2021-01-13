/*
 * 文件名：BussRepDepositInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepDepositInfoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 存款中债报送业务验证
 * @author zhangnian
 * @version 1.0 2018年9月18日
 * @see SvrBussRepDepositInfoValidator
 * @since 1.0
 */
@Component
public class SvrBussRepDepositInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        SvrBussRepDepositInfoDto dto = (SvrBussRepDepositInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        String repDepoType = dto.getRepDepoType();
        String repAssetType = dto.getRepAssetType();
        if(StringUtil.isEmpty(dto.getRepAssetType())){
            resultList.add(new ValidMessage("repAssetType",
                    getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repAssetType"))));
        }
        if(StringUtil.isEmpty(dto.getRepTradeMarket())){
            resultList.add(new ValidMessage("repTradeMarket",
                    getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTradeMarket"))));
        }
        //中债新增 存款类型 20200407
        if(StringUtil.equals(repAssetType, DictCodeListExt2.资产_负债二级类别_本行存款.getValue())
                ||StringUtil.equals(repAssetType, DictCodeListExt2.资产_负债二级类别_他行存款.getValue())){
            if(StringUtil.isEmpty(repDepoType)) {
                resultList.add(new ValidMessage("repDepoType",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repDepoType"))));
            }
        }
        if(StringUtil.equals(repDepoType, DictCodeListExt1.存款类型_结构性存款.getValue())){
            if(StringUtil.isEmpty(dto.getRepTarget())) {
                resultList.add(new ValidMessage("repTarget",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTarget"))));
            }
            if(StringUtil.isEmpty(dto.getRepTargetType())) {
                resultList.add(new ValidMessage("repTargetType",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repTargetType"))));
            }
        }
        if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
            if(StringUtil.isEmpty(dto.getRepRemark())){
                resultList.add(new ValidMessage("repRemark",
                        getMessage("COMMON_E_0057", getMessage("BussRepDepositInfo.repRemark"))));
            }

        }
        if(!StringUtil.isEmpty(dto.getRepRemark())){
            if(StringUtil.length(dto.getRepRemark())>300){
                resultList.add(new ValidMessage("repRemark",
                        getMessage("BUSS_COM_E_0010", getMessage("BussRepDepositInfo.repRemark"))));
            }
        }
        return resultList;
    }
}
