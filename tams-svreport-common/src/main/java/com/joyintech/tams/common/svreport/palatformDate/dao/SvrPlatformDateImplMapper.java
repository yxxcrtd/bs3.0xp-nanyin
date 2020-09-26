/*
 * 文件名：SysDateUtilMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.svreport.palatformDate.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统日期工具类
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月14日 
 * @see SvrPlatformDateImplMapper
 * @since 1.0
 */
@Repository
public interface SvrPlatformDateImplMapper {
    /**
     * 
     * 获取平台日
     * <功能详细描述>
     * @return 平台日
     * @see
     * @since 1.0
     */
    Date getSysDate();

    /**
     * 获取DB日期
     * @return 返回值
     * @see
     * @since 1.0
     */
    Date getDbDate();

    /**
     * 更新系统时间
     * @param newDate 系统时间
     * @return 更新结果
     */
	int updateSysDate(@Param("newDate") Date newDate);
}
