package com.joyintech.sso.login.service;

/**
 * @auther zhangnian
 * @date 2021/5/11
 */

import com.joyintech.sso.config.pojo.SsoPlatform;
import com.joyintech.sso.login.dao.SsoLoginMapper;
import com.joyintech.sso.login.dto.Platform;
import com.joyintech.sso.login.dto.SsoUser;
import com.joyintech.sso.util.CookieUtil;
import com.joyintech.sso.util.ServletUtil;
import com.joyintech.sso.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class SsoLoginService {

    @Autowired
    private SsoLoginMapper ssoLoginMapper;

    public SsoUser selectUserInfoBySSOName(String username){
        return ssoLoginMapper.selectUserInfoBySSOName(username);
    }

    public List<Platform> selectPlatformByUserName(String username){
        return ssoLoginMapper.selectPlatformByUserName(username);
    }

    public SsoPlatform selectPlatformById(String id){
        return ssoLoginMapper.selectPlatformById(id);
    }

    public boolean isExcookie(){
        //是否有cookie
        boolean isExcookie = false;
        Cookie[] cookies = ServletUtil.getRequest().getCookies();
        if(null!=cookies){
            for (Cookie cookie : cookies) {
                if(null!=cookie&&cookie.getName().equals(CookieUtil.USER_NAME_KEY)){
                    String value = cookie.getValue();
                    if(StringUtils.isNotEmpty(value)){
                        isExcookie = true;
                        break;
                    }
                }
            }
        }
        return isExcookie;
    }

}

