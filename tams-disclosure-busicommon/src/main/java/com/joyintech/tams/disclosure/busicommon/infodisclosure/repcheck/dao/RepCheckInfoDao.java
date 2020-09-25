/*
 * 文件名：RepCheckInfoDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 信批内容校验
 * @author wanghb
 * @version 1.0 2019年3月20日
 * @see RepCheckInfoDao
 * @since 1.0
 */
@Repository
public interface RepCheckInfoDao {
    @Select("${sql}")
    List<Object> selectOne(@Param("sql") String sql);
}
