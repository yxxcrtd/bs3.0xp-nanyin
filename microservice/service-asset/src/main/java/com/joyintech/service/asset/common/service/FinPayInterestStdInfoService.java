/*
 * 文件名：FinPayInterestStdInfoService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月8日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.common.service;

import com.joyintech.service.asset.common.dao.FinPayInterestStdInfoMapper;
import com.joyintech.service.asset.common.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 付息信息 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see FinPayInterestStdInfoService
 * @since 1.0
 */
@Service
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
}
