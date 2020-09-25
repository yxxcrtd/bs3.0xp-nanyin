/*
 * 文件名：TaxScheme003Mapper.java
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

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme003Dto;

/**
 * 增值税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme003Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme003Mapper")
public interface TaxScheme003Mapper {

	int insertSelective(TaxScheme003Dto dto);
	
	TaxScheme003Dto selectByPrimaryKey(String seqNo);
	
	int updateByPrimaryKeySelective(TaxScheme003Dto dto);
	
    /**
     * 增值税则表check(去除本金收益特征不能为空的校验后添加本方法)
     * @param parameters
     * @return
     */
    List<TaxScheme003Dto> checkVatTax(Map<String, Object> parameters);
}
