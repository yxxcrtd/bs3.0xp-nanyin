/*
 * 文件名：OperationManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.operationmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.operationmanage.dto.OperationManageDto;

/**
 * 
 * 运营管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2019年2月18日
 * @see OperationManageMapper
 * @since 1.0
 */
@Repository
public interface OperationManageMapper {
	/**
	 * 
	 * 页面查询
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<OperationManageDto> select(Map<String, Object> pageParam);
	
	/**
	 * 
	 * 组合代码下拉框
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SuggestDto> getPortfolioId(Map<String, Object> pageParam);
	
	/**
	 * 
	 * 组合代码下拉框
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SuggestDto> getPortfolioId1(Map<String, Object> pageParam);
}
