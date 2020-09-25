/*
 * 文件名：ServiceInstitutionService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：杨智
 * 修改时间：2018年2月6日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.systemtools.service;

import java.util.List;

import com.joyintech.tams.systemtools.dao.ServiceInstitutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;

@Service("serviceInstitutionService")
@Transactional
public class ServiceInstitutionService extends BaseService {

    @Autowired
    private ServiceInstitutionMapper institutionMapper;

    @DataSource(DataSourceType.OTHER)
    public List<SuggestDto> getInstitutionCode(String param) {
        return queryForSuggest(institutionMapper::getInstitutionName, param, null);
    }

    @DataSource(DataSourceType.OTHER)
    public List<SuggestDto> getAllInstitutionCode(String param) {
        return queryForSuggest(institutionMapper::getAllInstitutionName, param, null);
    }
}
