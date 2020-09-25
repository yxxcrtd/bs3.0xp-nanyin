/*
 * 文件名：MstCaptialAccountMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstperformancebenchmark.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.service.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
/**
 * 复合基准管理的mapper
 * 
 * @author zhangjun
 * @version 1.0 2018年8月23日
 * @see MstPerformanceBenchMarkMapper
 * @since 1.0
 * 
 */
@Repository
public interface MstPerformanceBenchMarkMapper {
    /**
     * 
     * 模糊查询
     * <功能详细描述>
     * @param pageParam pageParam
     * @return List
     * @see
     * @since 1.0
     */
    List<MstPerformanceBenchMarkDto> select(Map<String, Object> pageParam);
    
    /**
     * 联想下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return List
     * @see
     * @since 1.0
     */
    List<SuggestDto> getIndexName(Map<String,String> map);
    
    /**
     * 
     * 删除基准成分表的数据
     * <功能详细描述>
     * @param indexId indexId
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(String indexId);
    /**
     * 
     * 删除基准信息表的数据
     * <功能详细描述>
     * @param indexId indexId
     * @return int
     * @see
     * @since 1.0
     */
    int deleteInfoByPrimaryKey(String indexId);
    
    /**
     * 
     * 新建
     * <功能详细描述>
     * @param  dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    int insert(MstPerformanceBenchMarkDto dto);
    
    /**
     * 
     * 修改
     * <功能详细描述>
     * @param mstIndexComponent MstIndexComponent
     * @return int
     * @see
     * @since 1.0
     */
    int updateByPrimaryKeySelective(MstIndexComponent mstIndexComponent);
    
    /**
     * 
     * 根据基准编号查询详细信息
     * <功能详细描述>
     * @param indexId String
     * @return MstPerformanceBenchMarkDto
     * @see
     * @since 1.0
     */
    MstPerformanceBenchMarkDto selectDetail(String indexId);
    
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    int checkCodeIsUse(MstPerformanceBenchMarkDto dto);
    
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item MstPerformanceBenchMarkDto
     * @return int
     * @see 
     * @since 1.0
     */
    int checkAllCodeIsUse(List<MstPerformanceBenchMarkDto> item);
    
    /**
     * 
     * 新建检验基准名称是否存在
     * <功能详细描述>
     * @param dto MstPerformanceBenchMarkDto
     * @return int
     * @see
     * @since 1.0
     */
    int selectByIndexName(MstPerformanceBenchMarkDto dto);
    /**
     * 
     * 新建检验基准成分是否存在
     * <功能详细描述>
     * @return int
     * @param dto MstIndexComponent
     * @see
     * @since 1.0
     */
    int selectByPrimaryKey(MstIndexComponent dto);
    /**
     * 查询表格中的数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map
     * @return List
     * @see
     * @since 1.0
     */
    List<MstIndexComponent> selectComponentInfoPage(Map<String, Object> pageParam);
    /**
     * 
     * 通过基准编号查询表格信息
     * <功能详细描述>
     * @param indexId String
     * @return List<ComponentInfoDto>
     * @see
     * @since 1.0
     */
    List<ComponentInfoDto> selectComponentInfoByPrimaryKey(String indexId);
}
