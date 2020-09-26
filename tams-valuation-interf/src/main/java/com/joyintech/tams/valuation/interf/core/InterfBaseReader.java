/*
 * 文件名：InterfBaseReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.core;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;

/**
 * 接口BaseReader
 * 
 * @author 王波
 * @version 1.0 2018年6月11日
 * @see InterfBaseReader
 * @since 1.0
 */
public abstract class InterfBaseReader<T extends InterfNeedOperateEntity> implements ItemReader<T> {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 参数
     */
    private Map<String, Object> params;
    
    /**
     * 提供给processor处理的list
     */
    private List<T> list;

    @Override
    public synchronized T read() {
        if (list == null) {
            try {
                list = createList();
            }
            catch (Exception e) {
                logger.error(this.getClass().getSimpleName(), e);
            }
        }
        if (list != null && !list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }

    /**
     * 子类需要实现的方法，创建提供给processor处理的list
     *
     * @return 创建的list
     * @throws Exception
     * @see
     * @since 1.0
     */
    public abstract List<T> createList();

    public void init() {
        list = null;
    }
    
    /**
     * 获取 params
     * @return params.
     */
    public Map<String, Object> getParams() {
        return params;
    }

    
    /**
     * 设置 params
     * @param params params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
