/*
 * 文件名：SysDataDictService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.cache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.riskrdsinf.cache.dao.InterfDataDictMapper;
import com.joyintech.service.riskrdsinf.cache.entity.InterfDataDictInfo;

/**
 * 接口数据字典处理Service（外围系统-投组系统数据字典对应关系）
 * 
 * @author zhangrui1
 * @version 1.0 2019年3月13日
 * @see InterfDataDictService
 * @since 1.0
 */
@Service
public class InterfDataDictService {

    /**
     * 接口数据字典查询Mapper
     */
    @Autowired
    private InterfDataDictMapper dictMapper;

    /**
     * 查询数据字典关系
     * 
     * @param info 查询条件
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public InterfDataDictInfo selectDataDictInfo(InterfDataDictInfo info) {
        return dictMapper.selectDataDictInfo(info);
    }

    /**
     * 查询数据字典关系
     * 
     * @param isOthToTz 查询类型，true：根据外围数据字典查询投组数据字典；false：反之
     * @param dictCode 数据字典代码
     * @param itemValue 数据字典项目值
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String selectDataDictInfo(boolean isOthToTz, String dictCode, String itemValue) {
        InterfDataDictInfo info = new InterfDataDictInfo();
        if (isOthToTz) {
            info.setDctCode(dictCode);
            info.setItemVal(itemValue);
        }
        else {
            info.setLnkDict(dictCode);
            info.setLnkdVal(itemValue);
        }
        return selectDataDictValue(isOthToTz, info);
    }

    /**
     * 查询数据字典关系
     * 
     * @param isOthToTz 查询类型，true：根据外围数据字典查询投组数据字典；false：反之
     * @param info 查询条件
     * @return 数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String selectDataDictValue(boolean isOthToTz, InterfDataDictInfo info) {
        InterfDataDictInfo result = selectDataDictInfo(info);
        if (isOthToTz) {
            return result == null ? info.getItmCode() : result.getLnkItem();
        }
        return result == null ? info.getLnkItem() : result.getItmCode();
    }

    /**
     * 查询所有数据字典关系
     * 
     * @return 所有数据字典关系
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<InterfDataDictInfo> selectAllInfDictList() {
        return dictMapper.selectInfDataDictList();
    }
}
