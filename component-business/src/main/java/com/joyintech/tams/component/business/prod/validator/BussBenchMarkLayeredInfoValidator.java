/*
 * 文件名：BussBenchMarkLayeredInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月25日
 * @see BussBenchMarkLayeredInfoValidator
 * @since 1.0
 */
@Component
public class BussBenchMarkLayeredInfoValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object paramObject) {
        BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto = (BussBenchMarkLayeredInfoDto)paramObject;
        List<ValidMessage> validList = null;
        if(null !=bussBenchMarkLayeredInfoDto && !StringUtil.isEmptyList(bussBenchMarkLayeredInfoDto.getBussBenchMarkLayeredInfoDtoList())){
            validList = new ArrayList<ValidMessage>();
            List<BussBenchMarkLayeredInfoDto> list = bussBenchMarkLayeredInfoDto.getBussBenchMarkLayeredInfoDtoList();
            for(int i = 0; i < list.size(); i++ ){
                BussBenchMarkLayeredInfoDto bussBenchMarkInfoDto = list.get(i);
                if(DictCodeListExt1.披露类型_区间披露.getValue().equals(bussBenchMarkInfoDto.getPublishType()))
                	continue;
                if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())) {
                    validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].rateType",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateType"))));
                }
                if (StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())) {
                    if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                        DictCodeList.利率类型_固定利率.getValue())) {
                        if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRate())) {
                            validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].rate",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                        }
                    }
                    else if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                        DictCodeList.利率类型_浮动利率.getValue())) {
                        /*if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getIndexId())) {
                            validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].indexId", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.indexId"))));
                        }*/
                        if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getCoefficient())) {
                            validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].coefficient", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.msg.coefficient"))));
                        }
                        if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getSpreadRate())) {
                            validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].spreadRate", getMessage("BUSS_COM_E_0038",
                                getMessage("BussBenchMarkInfo.msg.spreadRate"))));
                        }
                        if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getResetType())) {
                            validList.add(new ValidMessage("bussBenchMarkLayeredInfoDtoList[" + (i) + "].resetType",
                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                        }
                    }
                }
                
            }
           // 判断费用类型是否重复
//            Map<String, BussBenchMarkLayeredInfoDto> goalInfoMap = list.stream().collect(
//                Collectors.toMap(BussBenchMarkLayeredInfoDto:: getProdStyle, a -> a, (k1, k2) -> k1));
//            if (goalInfoMap.size() != list.size()) {
//                validList.add(new ValidMessage("bussFinProductFeeInfoDtoList",
//                    this.getMessage("FIN_PROD_E_0076")));
//            }
        }
        return validList;
    }
    
    
}
