/*
 * 文件名：TaxScheme006Mapper.java
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

import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme006Dto;

/**
 * 附加税则表
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme006Mapper
 * @since 1.0
 */
@Repository(value="serviceTaxScheme006Mapper")
public interface TaxScheme006Mapper {

	int insertSelective(TaxScheme006Dto dto);
	
	TaxScheme006Dto selectByPrimaryKey(String seqNo);
	
	int updateByPrimaryKeySelective(TaxScheme006Dto dto);
	
	List<TaxScheme006Dto> selectProject(Map<String, Object> pageParam);
	
	int deleteByPrimaryKey(String seqId);
}
