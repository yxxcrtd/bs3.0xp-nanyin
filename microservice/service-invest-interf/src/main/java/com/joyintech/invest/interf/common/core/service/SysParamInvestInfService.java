package com.joyintech.invest.interf.common.core.service;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.common.core.dao.SysInvestInfParaMapper;
import com.joyintech.invest.interf.common.core.entity.SysPara;
import com.joyintech.invest.interf.common.core.entity.SysParaKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tams-base
 * @description: 接口系统参数加载
 * @author: 王波
 * @create: 2020-02-25 16:04
 */
@Service
public class SysParamInvestInfService {
    /**
     * 查询接口系统参数Mapper
     */
    @Autowired
    private SysInvestInfParaMapper sysParaMapper;

    /**
     * 加载系统参数
     *
     * @return 系统参数
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Map<String, Object> interfCache() {
        // 加载系统参数
        List<SysPara> list = sysParaMapper.selectInfAll();
        Map<String, Object> resultMap = new HashMap<>();
        for (SysPara para : list) {
            resultMap.put(para.getParaCode(), para.getParamValue());
        }
        return resultMap;
    }

    /**
     * 加载平台日期
     *
     * @return 平台日期 yyyyMMdd
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getSysDate() {
        // 加载平台日期
        Date dt = sysParaMapper.getSysDate();
        if (dt != null) {
            return DateUtil.format(dt, DateUtil.DEFAULT_OTHER_FORMAT);
        }
        return StringUtil.EMPTY;
    }

    /**
     * 取得特定的参数值
     *
     * @param sysCode 系统代码
     * @param paraCode 参数代码
     * @return 参数值
     */
    public String getSysParaVal(String sysCode, String paraCode) {
        SysParaKey key = new SysParaKey();
        key.setSystemCode(sysCode);
        key.setParaCode(paraCode);
        SysPara entity = sysParaMapper.selectParaOne(key);
        return entity == null ? null : entity.getParamValue();
    }


}
