/*
 * 文件名：FinProductBonusPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.FinProductBonusPlanMapper;
import com.joyintech.service.product.common.dto.BussFinProductBonusPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 分红计划信息共通
 * @author zhangliang
 * @version 1.0 2018年10月25日
 * @see FinProductBonusPlanService
 * @since 1.0
 */
@Service("backFinProductBonusPlanService")
public class FinProductBonusPlanService {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductBonusPlanMapper finProductBonusPlanMapper;

    /**
     * 根据金融产品代码和生效日查询分红计划相关信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日
     * @return 分红计划
     */
    public BussFinProductBonusPlanDto queryBonusPlanInfoByFinprodId(String finprodId,
                                                                    String effDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("effDate", effDate);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());

        BussFinProductBonusPlanDto bussFinProductBonusPlanDto = finProductBonusPlanMapper.queryBonusPlanInfoByFinprodId(
            map);

        if (bussFinProductBonusPlanDto != null && StringUtil.isNotEmpty(bussFinProductBonusPlanDto.getPayCycleVal())) {
            bussFinProductBonusPlanDto.setPayCycleUnit(bussFinProductBonusPlanDto.getPayCycle());
            bussFinProductBonusPlanDto.setPayCycle(DictCodeList.分红规则_周期支付.getValue());
        }

        return bussFinProductBonusPlanDto;
    }

}
