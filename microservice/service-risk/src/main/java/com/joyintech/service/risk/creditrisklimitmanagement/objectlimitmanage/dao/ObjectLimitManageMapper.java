/*
 * 文件名：CeOrgMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.objectlimitmanage.dto.ObjectLimitManageInfoDto;
import com.joyintech.tams.common.base.entity.CreditLines;

/**
 * 
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see ObjectLimitManageMapper
 * @since 1.0
 */
@Repository
public interface ObjectLimitManageMapper {
	
    int deleteByPrimaryKey(String seqno);

    List<LimitManageInfoDto> selectForPage(Map<String, Object> param);
    
    LimitManageInfoDto queryCeOrgBySeqno(Map<String, Object> param);
    
    List<CreditLines> queryCeOrgByPflshAndCustomId(@Param(value = "pflsh") String pflsh,@Param(value = "customerId") String customerId);
    /**
     * 客户代码联想下拉框
     * @return List<SuggestDto>
     */
    List<SelectOptionDto> getCustomerId(Map<String, Object> map);
    
   
    String getOrgInfo(String customerId);

    List<SuggestDto> getApporId(Map<String,Object> map);

    List<SuggestDto> getCustomerName(Map<String,Object> map);

    ObjectLimitManageInfoDto selectByPrimaryKey(@Param(value = "seqno")String seqno);
    
    int insert(ObjectLimitManageInfoDto record);
    
    int updateByPrimaryKeySelective(ObjectLimitManageInfoDto record);

    int updateByPrimaryKey(ObjectLimitManageInfoDto record);
    
	/*根据信用代码查询机构代码*/
	List<String> queryCustomerIdByCode(@Param("insitituteCode")String insitituteCode);

    int checkBusi(@Param(value = "busi")String busi);
}
