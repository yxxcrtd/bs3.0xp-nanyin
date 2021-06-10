/*
 * 文件名：BussFinRepaymentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.asset.service;

import com.joyintech.service.valuation.component.asset.dao.BussFinRepaymentMapper;
import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  还本信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BussFinRepaymentService
 * @since 1.0
 */
@Service(value="serviceBussFinRepaymentService")
public class BussFinRepaymentService {


    @Autowired
    private BussFinRepaymentMapper bussFinRepaymentMapper;
    /**
     * 初始化还本计划
     * <功能详细描述>
     * @param finprodId
     * @param cashType
     * @param finprodType--业务类型(金融产品、资金帐号)
     * @return
     * @see
     * @since 1.0
     */
    public List<FinCashPlanDto> getFinCashPlanByProductId(String finprodId, String cashType, String finCashBusinessType, String finprodType){
        if (DictCodeList.共通业务类型_付息计划调整.getValue().equals(finprodType)) {
            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", finprodId);
            params.put("cashType", cashType);
            params.put("finCashBusinessType", finCashBusinessType);
            params.put("finprodType", finprodType);
            return bussFinRepaymentMapper.getFinCashPlanByProductIdOther(params);
        }
        return bussFinRepaymentMapper.getFinCashPlanByProductId(finprodId,cashType,finprodType);
    }

    /**
     * 查询资产付息营业日准则
     *
     * @param paramMap 参数
     * @return 资产付息营业日准则
     * @see
     * @since 1.0
     */
    public String selectAssetPayTdayRule(Map<String, Object> paramMap) {
        paramMap.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        return bussFinRepaymentMapper.selectAssetPayTdayRule(paramMap);
    }
}
