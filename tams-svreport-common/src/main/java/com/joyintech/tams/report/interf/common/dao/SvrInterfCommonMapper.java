/*
 * 文件名：InterfCommonMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月10日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.interf.common.dto.InterfLog;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author lihuadong
 * @version 1.0 2017年2月10日
 * @see SvrInterfCommonMapper
 * @since 1.0
 */
@Repository
public interface SvrInterfCommonMapper {

//    /**
//     * 根据系统参数主键查询参数值
//     *
//     * @param subsystemCode 子系统
//     * @param moduleCode 模块代码
//     * @param paraCode 参数代码
//     * @return List<String> 参数值
//     * @see
//     * @since 1.0
//     */
//    List<String> selectParaByPrimaryKey(@Param(value = "subsystemCode") String subsystemCode,
//                                        @Param(value = "moduleCode") String moduleCode,
//                                        @Param(value = "paraCode") String paraCode);

    /**
     * 登陆接口日志
     *
     * @param log 日志
     * @return 影响记录数
     * @see
     * @since 1.0
     */
    int insertInterfLog(InterfLog log);

    /**
     * 批量登录接口日志数据
     *
     * @param interfLogList 接口日志数据
     * @see
     * @since 1.0
     */
    void insertInterfLogList(@Param("interfLogList") List<InterfLog> interfLogList);

//    /**
//     * 他系统用户映射
//     *
//     * @param map 参数
//     * @return 用户信息
//     * @see
//     * @since 1.0
//     */
//    List<InterfUserMaaperDto> selectInterfUserMaaper(Map<String, String> map);

    void excuteDb2ImportData(@Param("filePath") String filePath, @Param("tableName") String tableName);
}
