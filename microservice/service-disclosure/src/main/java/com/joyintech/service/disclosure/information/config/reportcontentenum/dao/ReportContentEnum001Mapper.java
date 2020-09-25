/*
 * 文件名：ReportContentEnum001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.disclosure.information.config.reportcontentenum.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;

/**
 * 自定义指标管理查询mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月6日
 * @see ReportContentEnum001Mapper
 * @since 1.0
 */
@Repository(value="serviceReportContentEnum001Mapper")
public interface ReportContentEnum001Mapper {
    /**
     * 查询指标库
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     */
	List<ReportContentEnum001Dto> selectIndex(Map<String, Object> pageParam);
	/**
	 * 根据指标名称查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param indexId
	 * @return
	 */
	List<ReportContentEnum001Dto> selectByIndexId(@Param("id") String indexName);
	/**
	 * 左侧栏新增
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	void insert(ReportContentEnum001Dto dto);
	
	/**
	 * 左侧通过id获取修改时的值
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param id
	 * @return
	 */
	ReportContentEnum001Dto selectById(String id);
	
	/**
	 * 左侧栏修改
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 */
	int updateByPrimaryKeySelective(ReportContentEnum001Dto dto);
	
	/**
	 * 左侧删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	/**
     * 根据作业编号删除作业范围数据
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    int deleteRangeInfo(String jobId);
	List<ReportContentEnum001Dto> findbyIndexName(@Param("name") String name);
	
	/**
	 * 从REP_INDEX_SUB获取jobId
	 * @param id 指标代码
	 * @return jobId jobId
	 */
	List<String> getId(String id);
	/**
	 * 通过priId从REP_INDEX_SUB删除数据
	 * @param id
	 */
	void deleteByPriId(String id);
  
}
