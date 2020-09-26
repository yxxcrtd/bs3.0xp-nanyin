/*
 * 文件名：ValuationMethodScheme003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto.SelectPageOptionDto;

/**
 * 
 * 估值方法方案 - 联动：交易市场 - 证券明细分类 获得证券明细分类
 * @author dongjun
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme00301Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValuationMethodScheme00301Service extends BaseService<SelectPageOptionDto>{

    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     * @throws Exception 
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public Page<SelectPageOptionDto> getLinkSelectOption(Map<String, Object> param) throws Exception {
//        return super.queryForPage(()->valuationMethodScheme001Mapper.getLinkSelectPageOption(param));
    	return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValuationMethodScheme003/getLinkSelectOption",
                param, SelectPageOptionDto.class);
    }
}
