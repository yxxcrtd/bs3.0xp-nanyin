/*
 * 文件名：TaxScheme007Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme007Dto;

/**
 * 附加税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme007Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme007Mapper")
public interface TaxScheme007Mapper {

    TaxScheme007Dto selectByPrimaryKey(String seqNo);

    List<SelectOptionDto> getBaseSubjectNature(Map<String, Object> pageParam);

    int countConfigId(String prefix);

    List<SelectOptionDto> getConfigInfo(Map<String, Object> param);

    TaxScheme007Dto getConfigInfoByKey(String configId);
}
