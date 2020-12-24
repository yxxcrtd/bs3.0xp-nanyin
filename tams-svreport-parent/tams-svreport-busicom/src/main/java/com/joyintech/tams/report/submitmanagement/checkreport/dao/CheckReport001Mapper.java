/*
 * 文件名：CheckReport001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmanagement.checkreport.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.ChbReportCheck;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;

/**
 * 报送前检查mapper
 * 
 * @author zuoyinyi
 * @version 1.0 2017年10月11日
 * @see CheckReport001Mapper
 * @since 1.0
 */
@Repository
public interface CheckReport001Mapper {

    /**
     * 请求产品结果list <功能详细描述>
     * 
     * @param prodId 参数
     * @return 产品结果list
     * @see
     * @since 1.0
     */
    List<SuggestDto> queryPrdNameList(String prodId);
    /**
     * 产品登记编码新建产品代码
     * 
     * @param pageParam 参数
     * @return 结果list
     * @see
     * @since 1.0
     */
    List<SuggestDto> getProdInfo(String prodId);
    
    /**
     * 查询所有产品代码
     * @param map
     * @return
     */
    List<SelectOptionDto> queryPrdNames(Map<String, Object> map);
    
    List<SuggestDto> queryPrdNameListOld(String prodId);

    /**
     * 查询报送前检查数据结果
     * 
     * @param pageParam 查询条件
     * @return 结果集
     * @see
     * @since 1.0
     */
    List<CheckReport001Dto> searchTable(Map<String, Object> pageParam);

    /**
     * 批量插入报送前检查结果
     * 
     * @param resultList 数据集
     * @see
     * @since 1.0
     */
    void batchInsert(List<CheckReport001Dto> resultList);

    /**
     * 根据批量检查序列号查询结果信息
     * 
     * @param isCheckedDataList
     * @return
     * @see
     * @since 1.0
     */
    List<ExcelValidDto> selectByCheckSeqno(List<String> isCheckedDataList);

    /**
     * 根据检查序列号删除报送前检查已有的检查结果
     * 
     * @param checkSeqnoList 检查序列号
     * @see
     * @since 1.0
     */
    void delReportCheckDatas(List<String> checkSeqnoList);




    
    /**
     * 同一条数据，如果报送检查通过，则将之前检查不通过的删除
     * 
     * @param indentifyMarkList 识别标识
     * @see
     * @since 1.0
     */
    void delReportUnCheckDatas(List<String> indentifyMarkList);

    /**
     * 根据检查序列号删除报送前检查已有的检查结果
     * 
     * @param reportCode 报表名称
     * @see
     * @since 1.0
     */
    void delReportAllCheckDatas(String reportCode);
    /**
     * 根据检查序列号删除报送前检查已有的检查结果
     * 
     * @param reportCode 报表名称
     * @see
     * @since 1.0
     */
    void delReportDatasByCodeAndDate(@Param("reportCode")String reportCode,@Param("reportDate")Date reportDate);
    
    /**
     * 根据关联序列号查询结果信息
     * @param isCheckedDataList
     * @return
     */
    List<CheckReport001Dto> selectByRefSeqno (List<String> isCheckedDataList);

	void batchDelete(@Param("list")List<CheckReport001Dto> subList);
}
