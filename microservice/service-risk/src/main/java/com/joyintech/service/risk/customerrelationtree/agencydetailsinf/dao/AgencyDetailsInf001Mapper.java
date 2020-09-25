/*
 * 文件名：AgencyDetailsInf001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto.AgencyDetailsInf001Dto;


/**
 * 
 * @author wangkang
 * @version 1.0 2019年12月10日
 * @see AgencyDetailsInf001Mapper
 * @since 1.0
 */
@Repository
public interface AgencyDetailsInf001Mapper {
    /**
     * 持仓信息查询
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AgencyDetailsInf001Dto> getPositionInf(Map<String, Object> map);
    
    /**
     * 机构联想框
     * @param agencyId
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAgency(@Param("agencyId")String agencyId);
    
    /**
     * 查询机构树
     * @param agencyId
     * @return
     * @see
     * @since 1.0
     */
    List<AgencyDetailsInf001Dto> getTreeData(@Param("agencyId")String agencyId);
    
    /**
     * 删除机构
     * @param agencyId
     * @return
     * @see
     * @since 1.0
     */
    int delNode(@Param("seqNo")String seqNo );
}
