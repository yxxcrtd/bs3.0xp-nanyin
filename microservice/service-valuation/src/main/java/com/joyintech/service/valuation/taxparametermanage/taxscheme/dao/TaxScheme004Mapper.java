/*
 * 文件名：TaxScheme004Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme004Dto;

/**
 * 附加税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme004Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme004Mapper")
public interface TaxScheme004Mapper {

	int insertSelective(TaxScheme004Dto dto);

	TaxScheme004Dto selectByPrimaryKey(String seqNo);

	int updateByPrimaryKeySelective(TaxScheme004Dto dto);

	String getPrimaryKey();

}
