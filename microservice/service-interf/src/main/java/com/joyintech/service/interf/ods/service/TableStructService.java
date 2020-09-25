/*
 * 文件名：TableStructService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月27日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.service.interf.ods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.ods.dao.TableStructMapper;
import com.joyintech.service.interf.ods.entity.TableColumn;

/**
 * 数据库表结构查询Service
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月27日
 * @see TableStructService
 * @since 1.0
 */
@Service
public class TableStructService {

    /**
     * TableStructMapper
     */
    @Autowired
    private TableStructMapper mapper;

    /**
     * 查询表结构
     * 
     * @param tableName 表名
     * @return 表字段列表
     */
    public List<TableColumn> selectTableStruct(String tableName) {

        if (StringUtil.isNotEmpty(tableName)) {
            String databaseType = "Oracle";
            databaseType = StringUtil.defaultString(databaseType, "Oracle");
            Map<String, Object> param = new HashMap<>();
            param.put("databaseType", databaseType);
            param.put("table", tableName);

            return mapper.selectTableStruct(param);
        }
        return new ArrayList<>();
    }
}
