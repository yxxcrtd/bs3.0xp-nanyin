/*
 * 文件名：TaxScheme005Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.taxparametermanage.taxscheme.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme005Dto;

/**
 * 资产应税信息
 * @author zhangwei
 * @version 1.0 2018年10月16日
 * @see TaxScheme005Mapper
 * @since 1.0
 */
public interface TaxScheme005Mapper {

	int insertSelective(TaxScheme005Dto dto);
	
	TaxScheme005Dto selectByPrimaryKey(String seqNo);
	
	int updateByPrimaryKeySelective(TaxScheme005Dto dto);

    /**
     * 获取资产代码 - 下拉框
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<TaxScheme005Dto> selectAsset(Map<String, Object> pageParam);

    /**
     * 通过资产代码，获取资产信息
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    TaxScheme005Dto getAssetInfo(Map<String, Object> pageParam);
}
