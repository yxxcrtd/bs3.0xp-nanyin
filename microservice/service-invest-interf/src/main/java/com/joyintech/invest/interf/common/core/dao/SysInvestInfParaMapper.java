package com.joyintech.invest.interf.common.core.dao;

import com.joyintech.invest.interf.common.core.entity.SysPara;
import com.joyintech.invest.interf.common.core.entity.SysParaKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: tams-base
 * @description:
 * @author: 王波
 * @create: 2020-02-25 16:12
 */
public interface SysInvestInfParaMapper {
    /**
     * 获取接口所有系统参数
     *
     * @return 接口系统参数列表
     * @see
     * @since 1.0
     */
    List<SysPara> selectInfAll();

    /**
     * 检索特定的系统参数
     *
     * @param key 查询条件
     * @return 系统参数
     */
    SysPara selectParaOne(SysParaKey key);

    /**
     * 读取系统日期
     * @return 系统平台日期
     */
    Date getSysDate();

    /**
     * 通过sequence名称获取序列值
     * <功能详细描述>
     * @param sequenceName sequence名称
     * @return 获取的值
     * @see
     * @since 1.0
     */
    String getNextValBySequenceName(@Param("sequenceName")String sequenceName);
}
