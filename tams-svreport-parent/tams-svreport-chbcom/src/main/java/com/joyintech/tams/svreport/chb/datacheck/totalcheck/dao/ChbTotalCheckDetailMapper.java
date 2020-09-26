package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDetailDto;

/**
 * 功能:中债总体核查明细mapper
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月19日
 * @修改记录：
 */
@Repository
public interface ChbTotalCheckDetailMapper {
    /**
     * 功能:根据主键删除总体核查明细信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param seqno
     * @return 
     * int
     */
    int deleteByPrimaryKey(String seqno);

    /**
     * 功能:插入一条总体核查明细信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int insert(ChbTotalCheckDetailDto record);

    /**
     * 功能:选择性插入总体核查明细信息（空值不插入）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int insertSelective(ChbTotalCheckDetailDto record);

    /**
     * 功能:根据主键查找一条总体核查明细信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param seqno
     * @return 
     * ChbTotalCheckDto
     */
    ChbTotalCheckDetailDto selectByPrimaryKey(String seqno);

    /**
     * 功能:根据主键选择性更新一条总体核查明细信息（空值不更新）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int updateByPrimaryKeySelective(ChbTotalCheckDetailDto record);

    /**
     * 功能:根据主键更新一条总体核查明细信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param record
     * @return 
     * int
     */
    int updateByPrimaryKey(ChbTotalCheckDetailDto record);
    
    /**
     * 功能:根据统计日期删除总体核查明细数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param cdate
     * @return 
     * int
     */
    int deletTotalCheckDeatailByCdate(@Param("cdate")Date cdate);
    
    /**
     * 功能:根据条件查询总体核查明细
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月24日
     * @param param
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    List<ChbTotalCheckDetailDto> selectTotalCheckDetailByPage(Map<String, Object> param);
}