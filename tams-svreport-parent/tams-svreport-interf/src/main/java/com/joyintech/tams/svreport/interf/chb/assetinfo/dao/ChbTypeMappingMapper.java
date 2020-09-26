package com.joyintech.tams.svreport.interf.chb.assetinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbTypeMapping;


/**
 * 功能:类型映射Mapping
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年2月28日
 * @修改记录：
 */
@Repository
public interface ChbTypeMappingMapper {
    int deleteByPrimaryKey(String mappingid);

    int insert(ChbTypeMapping record);

    int insertSelective(ChbTypeMapping record);

    ChbTypeMapping selectByPrimaryKey(String mappingid);

    int updateByPrimaryKeySelective(ChbTypeMapping record);

    int updateByPrimaryKey(ChbTypeMapping record);

    /**
     * 功能:通过SrcDataType获取对应的映射关系
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月28日
     * @param srcDataType 源表数据定义类型
     * @return 
     * List<ChbTypeMapping>
     */
	List<ChbTypeMapping> selectMappingBySrcDataType(@Param("srcDataType")String srcDataType);
	
	/**
	 * 功能:通过映射表转换源表数据成基础表所需要的数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月25日
	 * @param srcDataType 源表数据定义类型
	 * @param objectDataType 对应基础表数据定义类型
	 * @param srcValue 源表数据结果
	 * @return 基础表所需数据结果
	 * String
	 */
	List<String> selectMappingObjectValue(@Param("srcDataType")String srcDataType,@Param("objDataType")String objDataType
			,@Param("srcValue")String srcValue);
}