/*
 * 文件名：DBUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统
 * 修改人：王波
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统
 */
 
package com.joyintech.tams.valuation.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.dao.BaseCommonMapper;
/**
 * DB util
 * 
 * @author 王波
 * @version 1.0 2018年5月9日
 * @see DBUtil
 * @since 1.0
 */
@Service(value = "dBUtil")
public class DBUtil {
	
	/**
     * baseCommonMapper
     */
    @Autowired
	private BaseCommonMapper baseCommonMapper;
    
    /**
     * 获取当前系统平台日期
     * 
     * @return 当前系统平台日期
     */
    public Date getSysDate() {
        return baseCommonMapper.getNowSysDate();
    }
    
	/**
     * 根据採番名採番，长度为length
     *
     * @param sequenceType 採番序列类型
     * @param length 生成的长度
     * @return 採番值
     * @see
     */
    public String getSequenceIndexData(DataIndexSequenceType sequenceType, int length) {
        // 采番
        length = Math.abs(length);
        String formatter = "%0" + length + "d";
        String sequenceResult = String.format(formatter, this.getSequenceIndex(sequenceType));
        // 日期
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        // 前缀
        String shortName = sequenceType.getShortName();
        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
        sequenceResult = shortName + date + sequenceResult;
        return sequenceResult;
    }

    /**
     * 
     * 获取
     * @param sequenceType
     * @return
     * @see
     * @since 1.0
     */
    public long getSequenceIndex(DataIndexSequenceType sequenceType) {
        Map<String, String> param = new HashMap<String, String>();
        // 获取数据库类型暂定Oracle
        param.put("databaseType", "Oracle");
        param.put("sequenceName", sequenceType.getSequenceName());
        Long result = baseCommonMapper.getSequenceIndexData(param);
        return result;
    }
    
    
    /**
     * 根据採番名採番
     *
     * @param sequenceType 採番序列类型
     * @return 採番值
     * @see
     */
    public String getSequenceIndexData(DataIndexSequenceType sequenceType) {
        return getSequenceIndexData(sequenceType, 8);
    }

}
