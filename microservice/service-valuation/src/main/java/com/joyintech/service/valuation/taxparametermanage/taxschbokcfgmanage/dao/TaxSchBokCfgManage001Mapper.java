/*
 * 文件名：TaxScheme001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage001Dto;

/**
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxSchBokCfgManage001Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxSchBokCfgManage001Mapper")
public interface TaxSchBokCfgManage001Mapper {
    
    /**
     * 会计场景计税配置
     * @param pageParam
     * @return
     */
    List<TaxSchBokCfgManage001Dto> selectTaxSchaBokConfig(Map<String, Object> pageParam);

    /**
     * 统计配置使用数
     * @param configId
     * @return
     */
    int countUsed(String configId);
}
