/*
 * 文件名：FileUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.joyintech.tams.report.interf.common.dto.InterfEntity;

/**
 * Sql处理共通
 * 
 * @author 杨智
 * @version 1.0 2017年4月12日
 * @see SqlUtil
 * @since 1.0
 */
public class SqlUtil {

    /**
     * 生成检索接口表用的SQL文
     * 
     * @param targetClass 目标表Entity类
     * @param tableName 表名
     * @param constraints Where约束
     * @return SQL
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException IllegalAccessException
     * @see
     * @since 1.0
     */
    public static String toStringDBSelect(Class<? extends InterfEntity> targetClass,
                                          String tableName, List<String> constraints)
        throws InstantiationException, IllegalAccessException {

        InterfEntity interfEntity = targetClass.newInstance();
        // 一条sql文
        StringBuffer str = new StringBuffer();
        // 字段列表
        String[] cloumns = interfEntity.getCloumns();
        // DB字段对应的属性列表
        String[] properties = interfEntity.getArrayForDB();
        str.append("SELECT ").append(IntStream.range(0, properties.length).boxed().map(
            i -> cloumns[i] + " AS \"" + properties[i] + "\"").collect(
                Collectors.joining(","))).append(" FROM ").append(tableName);
        if (constraints != null && constraints.size() > 0) {
            str.append(" WHERE ").append(
                constraints.stream().collect(Collectors.joining(" AND ")));
        }
        return str.toString();
    }

}
