/*
 * 文件名：CePreOccupancyManagementMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditmanagement.preoccupancymanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditLinesPre;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.service.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;

/**
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CePreOccupancyManagementMapper
 * @since 1.0
 */
@Repository
public interface CePreOccupancyManagementMapper {

    List<CreditLinesOrgDto> selectForPage(Map<String, Object> param);
    List<CreditMstInfoDto> getTreeCeOrgQuote(Map<String, Object> param);
    CePreOccupancyManagementDto queryCeOrgBySeqno(Map<String, Object> param);
    List<CreditLinesPre> queryCeOrgByCustomId(@Param(value = "seqno") String seqno,@Param(value = "customerId") String customerId);
    /**
     * 客户代码联想下拉框
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getCustomerId(Map<String, Object> map);
    
    @SuppressWarnings("rawtypes")
	List<TreeNode> getAllCeOrgQuote(@Param(value = "rank") String rank);
    
    @SuppressWarnings("rawtypes")
	List<TreeNode> getParentCeOrgQuote(@Param(value = "rank") String rank);
    
    int searchRepeatByVmDate(Map<String, Object> param);
    int searchRepeatByVmDateArea(Map<String, Object> param);
    int searchRepeatByPflsh(@Param(value = "pflsh") String pflsh,@Param(value = "seqno") String seqno);
    int deletePreOccupaMx(@Param(value = "seqno") String seqno);
}
