/*
 * 文件名：ReportContentEnum002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.reportcontentenum.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum002Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum003Dto;
/**
 * 右侧表格mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月7日
 * @see ReportContentEnum002Mapper
 * @since 1.0
 */
@Repository
public interface ReportContentEnum002Mapper {
    /**
     * 查询左侧栏
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     */
	List<ReportContentEnum002Dto> selectIndexDetail(Map<String, Object> pageParam);
    /**
     * 通过指标名称查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param indexName
     * @return
     */
	List<ReportContentEnum002Dto> selectByindexName(@Param("indexName") String indexName);
    /**
     * 右侧栏新建
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
    int insert(ReportContentEnum002Dto dto);
    /**
     * 右侧栏修改
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     */
	int updateByPrimaryKeySelective(ReportContentEnum002Dto dto);
	
    /**
     * 右侧通过id获取修改时的值
     * <一句话功能简述>
     * <功能详细描述>
     * @param id
     * @return
     */
	ReportContentEnum002Dto selectById(String id);
    
	/**
	 * 右侧栏删除
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
   
	/**
	 * 根据作业编号查询报表作业范围数据
	 * @param id
	 * @return
	 */
	List<ReportContentEnum003Dto> findRulePage(String id);
    /**
     * 检查是否被引用
     * 
     * @param idsarr
     * @return
     * @see
     * @since 1.0
     */
    List<ReportContentEnum002Dto> findIndexIsQuoteById(String[] idsarr);
}
