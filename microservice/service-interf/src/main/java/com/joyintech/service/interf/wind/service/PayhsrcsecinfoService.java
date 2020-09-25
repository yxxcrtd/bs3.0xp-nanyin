/*
 * 文件名：PayhsrcsecinfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.joyintech.tams.common.base.dao.InterfPayhsrcsecinfoMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.PayhsrcsecinfoMapper;

/**
 * 债券基本资料数据接入详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月13日
 * @see PayhsrcsecinfoService
 * @since 1.0
 */
@Service
public class PayhsrcsecinfoService extends WindService<InterfPayhsrcsecinfo> {

    /**
     * 查询数据Mapper
     */
    @Autowired
    private PayhsrcsecinfoMapper selectMapper;

    // /**
    // * 登录数据Mapper
    // */
    // @Autowired
    // private InterfPayhsrcsecinfoMapper insertMapper;

    /**
     * {@inheritDoc}
     */
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int insert(InterfPayhsrcsecinfo e) {
        // // 接口表删除
        // insertMapper.deleteByPrimaryKey(e.getObjectId());
        // // 接口表登录
        // return insertMapper.insertSelective(e);

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @DataSource(InterfDataSourceType.WIND)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<InterfPayhsrcsecinfo> getDataList(String sysDate, long startNum, long endNum) {
        return selectMapper.select(sysDate, startNum, endNum);
    }
}
