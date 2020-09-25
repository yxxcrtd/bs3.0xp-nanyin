package com.joyintech.tams.common.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FilterInformationDto;


/**
 * 业务信息查询配置过滤器操作层
 * @author gaojian
 *
 */
@Repository
public interface FilterInformationMapper {
	
	/**
	 * 插入一条新的过滤器信息
	 * @param filterInformationDto 传入的过滤器信息
	 * @return 插入的条数
	 * @throws Exception 插入异常
	 */
	int insertFilterInfo(FilterInformationDto filterInformationDto);
	
	/**
	 * 根据主键删除一条过滤器基础信息
	 * @param filterId 过滤器主键
	 * @return 删除的条数
	 * @throws Exception 删除异常
	 */
	int deleteFilterInfo(@Param("filterId") String filterId);
	
	/**
	 * 根据过滤器ID修改过滤器信息
	 * @param filterInformationDto 待修改的过滤器对象
	 * @return 修改的条数
	 * @throws Exception 修改异常
	 */
	int updateFilterInfo(FilterInformationDto filterInformationDto);
	
	/**
	 * 根据页面参数查询方案信息
	 * @param pageParam 页面参数
	 * @return 方案信息
	 */
	List<FilterInformationDto> queryFilterInfoByUserId(Map<String, Object> pageParam);
	
	
	/**
	 * 根据用户和方案名查询对应方案条数
	 * @param userId 用户id
	 * @param planName 方案名称
	 * @return 条数
	 * @throws Exception 查询异常
 	 */
	int checkSave(@Param("userId") String userId, @Param("planName") String planName);
	
	/**
	 * 根据过滤器ID获取过滤器名称
	 * @param filterId 过滤器ID
	 * @return 过滤器名称
	 */
	String queryFilterNameById(@Param("filterId") String filterId);

	/**
	 * 根据方案名查询对应方案条数
	 * @param userId 用户id
	 * @param planName 方案名称
	 * @return 条数
	 * @throws Exception 查询异常
 	 */
	int checkSaveByName(@Param("planName") String planName);
}
