/*
 * 文件名：RepFinProductRegistCodeMngMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductregistcodemng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductRegistCodeMngMapper {
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);
    
    /**
     * 
     * 产品登记编码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPordRegistCode(Map<String, Object> param);
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<RepFinProductRegistCodeMngDto>
     * @see
     * @since 1.0
     */
    List<RepFinProductRegistCodeMngDto> selectForPage(Map<String, Object> param);
    
    /**
     * 
     * 产品代码在理财产品排期表里面不存在
     * <功能详细描述>
     * @param finprodMarketId String
     * @return int
     * @see
     * @since 1.0
     */
    int check1(@Param("finprodMarketId") String finprodMarketId);
    
    /**
     * 
     * 产品登记编码在理财产品排期表里面已存在
     * <功能详细描述>
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    int check2(@Param(value = "prodRegistCode") String prodRegistCode);
    
    /**
     * 
     * 产品代码在理财产品排期表的产品登记编码和该行的产品登记编码不一样 
     * <功能详细描述>
     * @param finprodMarketId String
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    int check3(@Param(value = "finprodMarketId") String finprodMarketId,@Param(value = "prodRegistCode") String prodRegistCode);
    
    /**
     * 
     * 更新理财产品排期
     * <功能详细描述>
     * @param paPrdIssueSchedule PaPrdIssueSchedule
     * @return int
     * @see
     * @since 1.0
     */
    int updatePaPrdIssueSchedule(PaPrdIssueSchedule dto);
    
    /**
     * 
     * 更新产品申报报送信息
     * <功能详细描述>
     * @param repProdDeclare RepProdDeclare
     * @return int
     * @see
     * @since 1.0
     */
    int updateRepProdDeclare(RepProdDeclare dto);
    
    /**
     * 
     * 更新产品发行报送信息
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
     */
    int updateRepProdIssue(RepProdIssue dto);

}
