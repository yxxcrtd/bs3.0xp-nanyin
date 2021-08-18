/*
 * 文件名：CeOrgMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.controller.LimitManage002Controller;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.tams.common.base.entity.CreditLines;

/**
 * 
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManageMapper
 * @since 1.0
 */
@Repository
public interface LimitManageMapper {

    List<LimitManageInfoDto> selectForPage(Map<String, Object> param);
    LimitManageInfoDto queryCeOrgBySeqno(Map<String, Object> param);
    List<CreditLines> queryCeOrgByPflshAndCustomId(@Param(value = "pflsh") String pflsh,@Param(value = "customerId") String customerId);
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
    int searchRepeatByPflsh(@Param(value = "pflsh") String pflsh,@Param(value = "seqno") String seqno,@Param(value = "creditMstId")String creditMstId);
    int deleteOrgMx(@Param(value = "pflsh") String pflsh);

    /**
     * 如果当前数据额度已被占用则不允许操作
     * by nixiukuan 20191024
     * @param param
     */
    int searchIsUse(Map<String, Object> param);
    
    
    String getOrgInfo(String customerId);

    int insert(LimitManageInfoDto record);

    int update(LimitManageInfoDto record);

    

}
