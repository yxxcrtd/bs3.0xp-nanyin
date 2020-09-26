package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDto;

/**
 * 功能:中债总体核查mapper
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月19日
 * @修改总体核查信息：
 */
@Repository
public interface ChbTotalCheckMapper {
    /**
     * 功能:根据主键删除总体核查信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param seqno
     * @return 
     * int
     */
    int deleteByPrimaryKey(String seqno);

    /**
     * 功能:插入一条总体核查信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int insert(ChbTotalCheckDto record);

    /**
     * 功能:选择性插入总体核查信息（空值不插入）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int insertSelective(ChbTotalCheckDto record);

    /**
     * 功能:根据主键查找一条总体核查信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param seqno
     * @return 
     * ChbTotalCheckDto
     */
    ChbTotalCheckDto selectByPrimaryKey(String seqno);

    /**
     * 功能:根据主键选择性更新一条总体核查信息（空值不更新）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int updateByPrimaryKeySelective(ChbTotalCheckDto record);

    /**
     * 功能:根据主键更新一条总体核查信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int updateByPrimaryKey(ChbTotalCheckDto record);
    
    /**
     * 功能:根据统计日期删除总体核查数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param cdate
     * @return 
     * int
     */
    int deletTotalCheckByCdate(@Param("cdate")Date cdate);

    /**
     * 功能:根据查询条件查询总体核查数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月24日
     * @param param
     * @return 
     * List<ChbTotalCheckDto>
     */
    List<ChbTotalCheckDto> selectData(Map<String, Object> param);
}