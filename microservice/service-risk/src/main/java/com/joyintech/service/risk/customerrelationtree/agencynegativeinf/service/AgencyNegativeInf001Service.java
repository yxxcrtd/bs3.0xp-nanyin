/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.customerrelationtree.agencynegativeinf.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dao.AgencyNegativeInf001Mapper;
import com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 机构负面信息查询
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019/12/9 0009
 * @see AgencyNegativeInf001Service
 * @since 1.0
 */
@Service
public class AgencyNegativeInf001Service extends BaseService<AgencyNegativeInf001Dto> {

    @Autowired
    private AgencyNegativeInf001Mapper agencyNegativeInf001Mapper;

    /**
     * 查询机构负面信息
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    @DataSource(DataSourceType.STRESSTEST)
    public Page<AgencyNegativeInf001Dto> queryForPage(int page, int length, Map<String, Object> pageParam)throws Exception{
        Page<AgencyNegativeInf001Dto> pageDate = super.queryForPage(page, length, () -> agencyNegativeInf001Mapper.queryForPage(pageParam));
        return pageDate;
    }

    /**
     * 根据机构名或机构代码模糊查询机构信息
     * @param orgStr
     * @return
     */
    @DataSource(DataSourceType.STRESSTEST)
    public List<AgencyNegativeInf001Dto> fuzzyQueryByOrgIdOrName(String orgStr){
        return agencyNegativeInf001Mapper.fuzzyQueryByOrgIdOrName(orgStr);
    }

}
