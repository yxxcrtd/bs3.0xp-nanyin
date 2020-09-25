package com.joyintech.tams.common.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FilterInformationDetailDto;


/**
 * 业务信息查询配置过滤器详情操作层
 * @author gaojian
 *
 */
@Repository
public interface FilterInformationDetailMapper {
	/**
	 * 插入过滤器详情信息
	 * @param filterInformationDetail 过滤器详情
	 * @return 插入的行数
	 */
	int insertFilterInfoDetail(FilterInformationDetailDto filterInformationDetail);
	
	/**
	 * 根据过滤器ID删除对应的过滤器详情
	 * @param filterId 过滤器ID
	 * @return 删除的条数
	 */
	int deleteFilterInfoDetail(@Param("filterId") String filterId);
	
	/**
	 * 根据过滤器ID查询对应的过滤器详情列表
	 * @param filterId 过滤器ID
	 * @return 过滤器详情列表
	 */
	List<FilterInformationDetailDto> queryFilterInfoDetailByFilterId(@Param("filterId") String filterId);
}
