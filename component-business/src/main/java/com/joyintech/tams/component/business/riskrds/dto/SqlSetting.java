/*
 * 文件名：SalSetting.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.dto;

import java.util.Map.Entry;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.StringUtil;

/**
 * sql配置 
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月22日
 * @see SqlSetting
 * @since 1.0
 */
public class SqlSetting extends Setting<String> {

    /**
     * @param isResult
     * @param sql
     */
    public SqlSetting(boolean isResult, String sql) {
        super(TYPE_SQL, isResult, sql);
    }

    @Override
    public Object execute(Function<String, Object> func) {
        return super.execute(sql -> {
            if (StringUtil.isEmpty(sql)) {
                return null;
            }
            // 参数存在则执行参数替换
            if (!CollectionUtils.isEmpty(getParamSet())) {
                for (Entry<String, Object> en : getParamSet().entrySet()) {
                    String key = "${" + en.getKey() + "}";
                    Object val = en.getValue();
                    if (val != null) {
                        sql = StringUtil.replaceAll(sql, key, val.toString());
                    }
                }
            }
            // 执行sql
            if (null != func) {
                return func.apply(sql);
            }
            return null;
        });
    }
}
