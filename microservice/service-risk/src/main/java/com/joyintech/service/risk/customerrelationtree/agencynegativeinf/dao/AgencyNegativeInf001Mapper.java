package com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dao;

import com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 机构负面信息查询
 * 〈功能详细描述〉
 *
 * @author 汪海
 * @version 1.0 2019/12/9 0009
 * @see AgencyNegativeInf001Mapper
 * @since 1.0
 */
@Repository
public interface AgencyNegativeInf001Mapper {
    List<AgencyNegativeInf001Dto> queryForPage(Map<String, Object> pageParam);

    List<AgencyNegativeInf001Dto> fuzzyQueryByOrgIdOrName(@Param("orgStr")String orgStr);
}
