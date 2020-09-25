/*
 * 文件名：JixuDate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.jixudate.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Mapper
 * @since 1.0
 */
@Repository
public interface JixuDate001Mapper {
    /**
     * 
     * 获取平台日
     * <功能详细描述>
     * @return 平台日
     * @see
     * @since 1.0
     */
    Date getJixuDate();

    /**
     * 更新系统时间
     * @param newDate 系统时间
     * @return 更新结果
     */
    int updateJixuDate(@Param("newDate") Date newDate);
}
