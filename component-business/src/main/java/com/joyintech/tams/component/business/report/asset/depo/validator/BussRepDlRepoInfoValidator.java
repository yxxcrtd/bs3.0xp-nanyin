/*
 * 文件名：BussRepDlRepoInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDlRepoInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 拆放同业及买入返售报送信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see BussRepDlRepoInfoValidator
 * @since 1.0
 */
@Component
public class BussRepDlRepoInfoValidator implements BeanValidator{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussRepDlRepoInfoDto dto = (BussRepDlRepoInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        String type6 = dto.getType6();
        if(StringUtil.equals(type6, DictCodeList.存款类资产类型_同业借款.getValue())){
            if(StringUtil.isEmpty(dto.getRepAssetType())){
                resultList.add(new ValidMessage("repAssetType",
                    getMessage("COMMON_E_0057", getMessage("BussRepDlRepoInfo.repAssetType"))));
            }
            if(StringUtil.isEmpty(dto.getRepTradeMarket())){
                resultList.add(new ValidMessage("repTradeMarket",
                    getMessage("COMMON_E_0057", getMessage("BussRepDlRepoInfo.repTradeMarket"))));
            }
            if(StringUtil.equals(dto.getRepTradeMarket(), DictCodeList.交易流通场所_其他交易流通场所.getValue())){
                if(StringUtil.isEmpty(dto.getRepRemark())){
                    resultList.add(new ValidMessage("repRemark",
                        getMessage("COMMON_E_0057", getMessage("BussRepDlRepoInfo.repRemark"))));
                }
                
            }
            if(!StringUtil.isEmpty(dto.getRepRemark())){
                if(StringUtil.length(dto.getRepRemark())>300){
                    resultList.add(new ValidMessage("repRemark",
                        getMessage("BUSS_COM_E_0010", getMessage("BussRepDepositInfo.repRemark"))));
                }
            }
        }
        return resultList;
    }
}
