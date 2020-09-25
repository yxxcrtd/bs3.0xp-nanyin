/*
 * 文件名：TaxScheme007Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage002Dto;

/**
 * 场景计税配置管理
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxSchBokCfgManage002Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxSchBokCfgManage002Mapper")
public interface TaxSchBokCfgManage002Mapper {

    TaxSchBokCfgManage002Dto selectByPrimaryKey(String seqNo);

    List<SelectOptionDto> getBaseSubjectNature(Map<String, Object> pageParam);

    int countConfigId(String prefix);
}
