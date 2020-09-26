/*
 * 文件名：ValInertfStartUp001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.inertfStartUp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ValInterfTask;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年7月5日
 * @see ValInertfStartUp001Mapper
 * @since 1.0
 */
@Repository
public interface ValInertfStartUp001Mapper {
    /**
     * 
     * 查询
     * @param name name
     * @return List<ValInterfTask>
     * @see
     * @since 1.0
     */
    List<ValInterfTask> selectInertfTask(String name);
}
