/*
 * 文件名：DBUtil.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2017年1月4日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.batch.disclosure.common.db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.dao.BaseCommonMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.dao.TamsBaseMapper;

/**
 * DB util
 *
 * @author qiangchengxi
 * @version 1.0 2017年1月4日
 * @see DBUtil
 * @since 1.0
 */
@Service(value = "disDBUtil")
public class DBUtil {

    /**
     * BaseMapper
     */
    @Autowired
    private TamsBaseMapper baseMapper;

    /**
     * BaseCommonMapper
     */
    @Autowired(required = false)
    private BaseCommonMapper commonMapper;

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     */
    public Date getSysDate() {
        return commonMapper.getNowSysDate();
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
        length = Math.abs(length);
        String formatter = "%0" + length + "d";
        Map<String, String> param = new HashMap<>();
//        String databaseType = (String)((Map<String, Object>)AppCache.getValue(
//            GlobalConstant.SYS_PARA_KEY)).get(GlobalConstant.COM_PROP_DATABASE_TYPE);
//        String databaseType = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.COM_PROP_DATABASE_TYPE);
        String databaseType = "Oracle";
        databaseType = StringUtil.defaultString(databaseType, "Oracle");

        param.put("databaseType", databaseType);
        param.put("sequenceName", sequenceType.getSequenceName());
        Long result = baseMapper.getSequenceIndexData(param);

        String sequenceResult = String.format(formatter, result);
        if (length != 18) {
            return sequenceResult;
        }
        // 加在採番后结果的前缀，以保证各个sequence的採番结果不会出现重复的情况
        String shortName = sequenceType.getShortName();
        sequenceResult = shortName + sequenceResult;

        return sequenceResult;
    }

    /**
     * 根据採番名採番
     *
     * @param sequenceType 採番序列类型
     * @return 採番值
     * @see
     */
    public String getSequenceIndexData(DataIndexSequenceType sequenceType) {
        return getSequenceIndexData(sequenceType, 18);
    }
}
