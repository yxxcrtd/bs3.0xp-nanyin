/*
 * 文件名：ParentAssetManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ZhouYu
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.parentassetmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.portfolio.parentassetmanage.dto.ParentAssetManageDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ZhouYu
 * @version 1.0 2019年12月11日
 * @see ParentAssetManageMapper
 * @since 1.0
 */
@Repository
public interface ParentAssetManageMapper {
	
	/**
	 * 查询母资管计划
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<ParentAssetManageDto> getParentPlanInfos(Map<String, Object> param);
}
