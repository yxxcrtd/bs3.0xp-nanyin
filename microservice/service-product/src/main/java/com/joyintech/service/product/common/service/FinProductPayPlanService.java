/*
 * 文件名：FinProductPayPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.FinProductPayPlanMapper;
import com.joyintech.service.product.common.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 付息计划信息共通
 * @author zhangliang
 * @version 1.0 2018年10月25日
 * @see FinProductPayPlanService
 * @since 1.0
 */
@Service("backFinProductPayPlanService")
public class FinProductPayPlanService {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductPayPlanMapper finProductPayPlanMapper;

    /**
     * 根据金融产品代码和生效日查询分红计划相关信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日
     * @return 分红计划
     */
    public BussFinProductPayPlanDto queryPayPlanInfoByFinprodId(String finprodId,
                                                                    String effDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("effDate", effDate);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());

        BussFinProductPayPlanDto bussFinProductPayPlanDto = finProductPayPlanMapper.queryPayPlanInfoByFinprodId(
            map);

        if (bussFinProductPayPlanDto != null && StringUtil.isNotEmpty(bussFinProductPayPlanDto.getPayCycleVal())) {
            bussFinProductPayPlanDto.setPayCycleUnit(bussFinProductPayPlanDto.getPayCycle());
            bussFinProductPayPlanDto.setPayCycle(DictCodeList.分红规则_周期支付.getValue());
        }

        return bussFinProductPayPlanDto;
    }
    
    /**
     * 根据金融产品代码查询付息计划相关信息
     * @param map 金融产品代码
     * @return 付息计划
     */
    public List<FinCashPlan> queryPayPlanListByFinprodId(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        
        return finProductPayPlanMapper.queryPayPlanListByFinprodId(map);
    }

}
