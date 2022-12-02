package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto.AssetInvestmentDetail001Dto;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.service.AssetInvestmentDetail001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AssetInvestmentDetailValidator implements BeanValidator {

    @Autowired
    private AssetInvestmentDetail001Service assetInvestmentDetail001Service;
    @Override
    public List<ValidMessage> valid(Object o) {
        AssetInvestmentDetail001Dto dto = (AssetInvestmentDetail001Dto)o;
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(),"new")){
            HashMap map =new HashMap();
            map.put("prodId", dto.getProdId());
            map.put("pubDate", dto.getPubDateStr());
            map.put("assetCode", dto.getAssetCode());
            AssetInvestmentDetail001Dto assetInvestmentDetail001Dto = assetInvestmentDetail001Service.selectByPrimaryKey(map);
            if (assetInvestmentDetail001Dto!=null && StringUtil.equals(assetInvestmentDetail001Dto.getStatus(),"E")){
                errorList.add(new ValidMessage("", "“产品主代码+日期+资产代码”不能同时与已有数据重复"));
            }
        }
        return errorList;
    }
}
