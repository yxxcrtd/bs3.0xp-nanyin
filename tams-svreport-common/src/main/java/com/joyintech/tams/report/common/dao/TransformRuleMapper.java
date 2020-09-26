package com.joyintech.tams.report.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.common.dto.TransformRule001Dto;



/**
 * 变形转换规则 Mapper文件
 * 
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRuleMapper
 * @since 1.0
 */

@Repository
public interface TransformRuleMapper {

	/**
	 *
	 * 检查数据是否重复
	 * 
	 * @param targetField
	 *            转换后值
	 * @see
	 * @since 1.0
	 */
	int check(@Param("targetField") String targetField);

	/**
	 *
	 * 数据插入
	 * 
	 * @param transformRule001Dto 
	 * @see
	 * @since 1.0
	 */
	int insertTransField(TransformRule001Dto transformRule001Dto);
	/**
    *
    * 数据插入
    * 
    * @param transformRule001Dto 
    * @see
    * @since 1.0
    */
   int getTransFieldCount(TransformRule001Dto transformRule001Dto);

	/**
	 *
	 * 查询页面数据
	 * 
	 * @param map  查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	List<TransformRule001Dto> selectAllData(Map<String, Object> map);

	/**
	 *
	 * 删除
	 * 
	 * @param deleteList 
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	void deleteByList(@Param("deleteList") List<TransformRule001Dto> deleteList);

	/**
	 *
	 * 根据主键查询
	 * 
	 * @param seqno 
	 * @return TransformRule001Dto
	 * @see
	 * @since 1.0
	 */
	TransformRule001Dto getDtoBySeqno(@Param("seqno") String seqno);

	/**
	 *
	 * 修改保存
	 * 
	 * @param transformRule001Dto 
	 * @see
	 * @since 1.0
	 */
	int doEditSave(TransformRule001Dto transformRule001Dto);
	
	List<String> getObjFiledByTableId(String tableid);

}
