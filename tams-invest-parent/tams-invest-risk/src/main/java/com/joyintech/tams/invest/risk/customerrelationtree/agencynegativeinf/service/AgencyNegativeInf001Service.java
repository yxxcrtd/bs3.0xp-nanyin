/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;
import org.springframework.stereotype.Service;

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
public class AgencyNegativeInf001Service extends CommonExportService<AgencyNegativeInf001Dto> {
    public Page<AgencyNegativeInf001Dto> selectForPage(Map<String, Object> param){
        Page<AgencyNegativeInf001Dto> page =  restTemplate.postForPage(
                ServiceApplication.RISK_SERVICE_NAME + "/AgencyNegativeInf001/findPage", param,
                AgencyNegativeInf001Dto.class);
        return page;
    }

    public List<SelectOptionDto> fuzzyQueryOrgByIdOrName(Map<String, Object> param){
        return restTemplate.postForList(
                ServiceApplication.RISK_SERVICE_NAME + "/AgencyNegativeInf001/fuzzyQueryOrgByIdOrName",
                param, SelectOptionDto.class);
    }
}
