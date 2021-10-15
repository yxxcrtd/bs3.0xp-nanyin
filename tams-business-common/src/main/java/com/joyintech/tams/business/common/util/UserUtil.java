package com.joyintech.tams.business.common.util;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;

public class UserUtil {

    /**
     * 获取用户信息
     * @return
     */
    public static User getUserInfo(){
        return SecurityUtil.getLoginUser();
    }
}
