/*
 * 文件名：CbondcurvecnbdService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.InterfCbondcurvecnbdMapper;
import com.joyintech.tams.common.base.dao.MstYieldCurveInfoMapper;
import com.joyintech.tams.common.base.entity.InterfCbondcurvecnbd;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCode;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.CbondcurvecnbdMapper;

/**
 * 中债登收益率曲线处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see CbondcurvecnbdService
 * @since 1.0
 */
@Service
public class CbondcurvecnbdService extends WindService<InterfCbondcurvecnbd> {

    /**
     * Wind查询Mapper
     */
    @Autowired
    private CbondcurvecnbdMapper selectMapper;

    /**
     * 接口表登录Mapper
     */
    @Autowired
    private InterfCbondcurvecnbdMapper insertMapper;

    /**
     * 收益率曲线信息Mapper
     */
    @Autowired
    private MstYieldCurveInfoMapper yieldCurveMapper;

    /**
     * {@inheritDoc}
     */
    @DataSource(InterfDataSourceType.WIND)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<InterfCbondcurvecnbd> getDataList(String sysDate, long startNum, long endNum) {
        return selectMapper.select(sysDate, startNum, endNum);
    }

    /**
     * {@inheritDoc}
     */
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int insert(InterfCbondcurvecnbd entity)
        throws Exception {
        // 删除重复数据
        insertMapper.deleteByPrimaryKey(entity.getObjectId());
        // 登陆接口表
        insertMapper.insertSelective(entity);
        // 业务表登录
        return saveBusinessData(entity);
    }

    /**
     * 登录业务表
     * 
     * @param entity 中债登收益率曲线
     * @return 成功行数
     * @see
     * @since 1.0
     */
    private int saveBusinessData(InterfCbondcurvecnbd entity) {
        MstYieldCurveInfo info = new MstYieldCurveInfo();
        // 曲线代码
        info.setCurveCode(StringUtil.numberToString(entity.getbAnalCurvenumber()));
        // 曲线日期
        info.setCurveDate(DateUtil.parse(entity.getTradeDt()));
        // 关键年限点
        info.setKeyPoint(entity.getbAnalCurveterm());
        // 收益率
        info.setYield(entity.getbAnalYield());
        // 曲线类型
        info.setCurveType(changeRateType(InterfDictCode.收益率曲线类型, InterfDictCode.收益率曲线类型.getValue(),
            entity.getbAnalCurvetype()));
        // 更新人
        info.setUpdateUser(WIND_USER);
        // 更新时间
        Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
        info.setUpdateTime(dateTime);
        if (yieldCurveMapper.selectByPrimaryKey(info) == null) {
            // 创建人
            info.setCreateUser(WIND_USER);
            // 创建时间
            info.setCreateTime(dateTime);
            return yieldCurveMapper.insertSelective(info);
        }
        else {
            return yieldCurveMapper.updateByPrimaryKeySelective(info);
        }
    }
}
