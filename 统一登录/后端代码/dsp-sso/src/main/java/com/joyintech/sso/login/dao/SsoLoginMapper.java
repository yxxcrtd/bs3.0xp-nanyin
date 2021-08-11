package com.joyintech.sso.login.dao;

import com.joyintech.sso.config.pojo.SsoPlatform;
import com.joyintech.sso.login.dto.Platform;
import com.joyintech.sso.login.dto.SsoUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/5/11
 */
@Repository
public interface SsoLoginMapper {

    SsoUser selectUserInfoBySSOName(@Param("username") String username);

    List<Platform> selectPlatformByUserName(@Param("username") String username);

    SsoPlatform selectPlatformById(@Param("id") String id);
}

