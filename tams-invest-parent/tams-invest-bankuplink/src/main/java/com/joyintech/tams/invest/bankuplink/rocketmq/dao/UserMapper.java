/*
 * 文件名：UserMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.rocketmq.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import  com.joyintech.system.user.dto.User;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Jiaoxujin
 * @version 1.0 2020年5月21日
 * @see UserMapper
 * @since 1.0
 */
@Repository("adminUserMapper")
public interface UserMapper {
    /**
     * 查询所有具有管理员权限的用户列表
     * @return 管理员用户列表
     */
    List<User> selectAdminUserList();
}
