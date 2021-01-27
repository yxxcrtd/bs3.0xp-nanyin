/*
 * 文件名：FinDepositTargetValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.validator;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  存款类资产--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月30日
 * @see FinDepositTargetValidator
 * @since 1.0
 */
@Component
public class AssetInfoDetailValidator implements BeanValidator{


    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        AssetInfoDetailDto dto = (AssetInfoDetailDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();

//        if(StringUtil.isEmpty(dto.getAssetCode())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("资产编号"))));
//        }
//        if(StringUtil.isEmpty(dto.getAssetName())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("资产名称"))));
//        }
//        if(!StringUtil.isNotEmpty(dto.getVdate())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("起息日"))));
//        }
//        if(!StringUtil.isNotEmpty(dto.getMdate())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("到期日"))));
//        }
//        if(StringUtil.isEmpty(dto.getOrgAssetType1())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("资产一级分类"))));
//        }
//
//        if(StringUtil.isEmpty(dto.getAssetType2())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("资产负债二级类别"))));
//        }
//
//        if(StringUtil.isEmpty(dto.getCcy())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("币种"))));
//        }
//        if(StringUtil.isEmpty(dto.getTradeMarket())){
//            resultList.add(new ValidMessage("",
//                    getMessage("COMMON_E_0057", getMessage("市场机构代码"))));
//        }

        // add by zhangliang 20210126 中债check调用 start
        resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, null, dto.getAssetType2(), ValidMessage.class);
        // add by zhangliang 20210126 中债check调用 end

         return resultList;
    }

}
