/*
 * 文件名：RskValAssetCodeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.risklevel.risklevelimport.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.risk.risklevel.risklevelimport.dto.RiskLevelImportDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;

/**
 *  资产科目映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RiskLevelImportMapper
 * @since 1.0
 */
@Repository
public interface RiskLevelImportMapper {
    
    List<RiskLevelImportDto> selectForPage(Map<String, Object> pageParam);

    void deleteById(Map<String, Object> pageParam);

    void insert(RiskLevelImportDto riskLevelImportDto);

    String IsInFinproduct(Map<String, Object> pageParam);

    String queryIsExit(Map<String, Object> pageParam);
}
