/*
 * 文件名：CommonMapperDelegateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.mapper.dao;

import org.apache.ibatis.annotations.DeleteProvider;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CommonMapperDelegateMapper {

    @DeleteProvider(type = CommonMapperDelegateSqlProvider.class, method = "deleteBySpecificKey")
    int deleteBySpecificKey(Map<String, Object> param);
}
