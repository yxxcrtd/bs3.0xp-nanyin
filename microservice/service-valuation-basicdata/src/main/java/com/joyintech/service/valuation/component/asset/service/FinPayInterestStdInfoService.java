/*
 * 文件名：FinPayInterestStdInfoService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月8日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.asset.service;

import com.joyintech.service.valuation.component.asset.dao.FinPayInterestStdInfoMapper;
import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.service.valuation.component.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 付息信息 〈功能详细描述〉
 *
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see FinPayInterestStdInfoService
 * @since 1.0
 */
@Service(value="serviceFinPayInterestStdInfoService")
public class FinPayInterestStdInfoService {

    @Autowired
    private FinPayInterestStdInfoMapper finPayInterestStdInfoMapper;

    public FinPayInterestStdInfoDto getFinPayInterestStdInfoByProductId(String finprodId, String vdate, String cashType,
                                                                        String finprodType) {
        FinPayInterestStdInfoDto finPayInterestStdInfoDto= finPayInterestStdInfoMapper.getFinPayInterestStdInfoByProductId(finprodId, vdate, cashType,
                finprodType);
        if(finPayInterestStdInfoDto!=null && cashType.equals(DictCodeListExt1.现金流二级类型_本金.getValue())){
            String repayWithoutInt =finPayInterestStdInfoMapper.getRepayWithoutIntByProductId(finprodId,cashType);
            finPayInterestStdInfoDto.setIsPayInt(repayWithoutInt);//界面是否还本不带息
        }
        return finPayInterestStdInfoDto;
    }

    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finCashBusinessType,
                                                          String finprodType) {
        if (DictCodeList.共通业务类型_付息计划调整.getValue().equals(finprodType)) {
            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", finprodId);
            params.put("cashType", cashType);
            params.put("finCashBusinessType", finCashBusinessType);
            params.put("finprodType", finprodType);
            return finPayInterestStdInfoMapper.getFinCashPlanByProductIdOther(params);
        }
        return finPayInterestStdInfoMapper.getFinCashPlanByProductId(finprodId, cashType, finprodType);
    }
}
