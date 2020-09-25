/*
 * 文件名：SystemActorRoleService001.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：杨智
 * 修改时间：2018年3月12日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
package com.joyintech.tams.systemtools.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.dao.TamsBaseMapper;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.dto.SystemActorRoleDto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 角色权限管理Service
 * 
 * @author 杨智
 * @version 1.0 2018年3月12日
 * @see SystemToolService
 * @since 1.0
 */
@Service
public class SystemToolService {

    /**
     * systemActorRoleMapper001
     */
    @Autowired
    private TamsBaseMapper tamsBaseMapper;

    @DataSource(DataSourceType.OTHER)
    @Transactional
    public List<SystemActorRoleDto> selectDataModuleByUserName(String userName) {
        return tamsBaseMapper.selectDataModuleByUserName(userName);
    }

    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public String getNameFromDataDict(final BaseDictCode dictCode, String itemCode) {
        if (StringUtil.isNotEmpty(dictCode) && StringUtil.isNotEmpty(itemCode)) {
            String dictValue = tamsBaseMapper.selectSysDatadictValue(dictCode.getValue(), itemCode);
            if (null != dictValue) {
                return dictValue;
            }
        }
        return null;
    }

    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public DataDictItem getDataDictItem(String dictCode, String itemCode) {
        if (StringUtil.isNotEmpty(itemCode) && StringUtil.isNotEmpty(dictCode)) {
            String dictValue = tamsBaseMapper.selectSysDatadictValue(dictCode, itemCode);
            if (null != dictValue) {
                return new DataDictItem() {
                    @Override
                    public String getDictCode() {
                        return dictCode;
                    }

                    @Override
                    public String getItemCode() {
                        return itemCode;
                    }

                    @Override
                    public String getItemValue() {
                        return dictValue;
                    }

                    @Override
                    public String getOrderNo() {
                        return "";
                    }

                    @Override
                    public String getDefaultValue() {
                        return "";
                    }

                    @Override
                    public String getParentId() {
                        return "";
                    }

                    @Override
                    public Set<String> getPageIds() {
                        return null;
                    }
 
                    public String getInstitutionCode() {
                        return null;
                    }
                };
            }
        }
        return null;
    }

    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public Map<String, String> getDataDictItems(DictCode dictCode) {
        if (StringUtil.isNotEmpty(dictCode)) {
            List<SysDataDict> sysDataDicts = tamsBaseMapper.selectSysDatadictValueList(dictCode.getValue());
            if(sysDataDicts != null && !sysDataDicts.isEmpty()) {
                return sysDataDicts.stream().collect(JoyinCollectors.toMap(sysDataDict -> sysDataDict.getItemCode(),
                        sysDataDict -> sysDataDict.getItemValue()));
            }
        }
        return null;
    }
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public String getCodeFromDataDictByName(final BaseDictCode dictCode, String itemName) {
        if (StringUtil.isNotEmpty(dictCode) && StringUtil.isNotEmpty(itemName)) {
            String itemCode = tamsBaseMapper.selectSysDatadictItemCode(dictCode.getValue(), StringUtil.trim(itemName));
            if (null != itemCode) {
                return itemCode;
            }
        }
        return null;
    }
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public String getSysParamValue(String systemCode, String paramCode) {
        if (StringUtil.isNotEmpty(paramCode)) {
            String paramValue = tamsBaseMapper.selectSysParamValue(systemCode, StringUtil.trim(paramCode));
            if (null != paramValue) {
                return paramValue;
            }
        }
        return null;
    }
}
