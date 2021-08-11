package com.joyintech.sso.util;

//import com.github.pagehelper.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
public class CookieUtil {

    public static String USER_NAME_KEY = "username";

    public static Cookie  setCookie(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public static Cookie  rmCookie(Cookie cookie){
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }


    public static  String  getValueByKey(){
        HttpServletRequest request = ServletUtil.getRequest();
        Cookie[] cookies = request.getCookies();
        if(null==cookies){
            return null;
        }else{
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(StringUtils.equals(name,USER_NAME_KEY)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}

