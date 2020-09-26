/*
 * 文件名：TableStructMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.db.struct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.db.struct.dto.TableColumn;

/**
 * 获取表结构
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see SvrTableStructMapper
 * @since 1.0
 */
@Repository
public interface SvrTableStructMapper {

    /**
     * 查询数据库中指定表的表结构
     * @param param 参数
     * @return 表字段列表
     */
    List<TableColumn> selectTableStruct(Map<String, Object> param);
}
