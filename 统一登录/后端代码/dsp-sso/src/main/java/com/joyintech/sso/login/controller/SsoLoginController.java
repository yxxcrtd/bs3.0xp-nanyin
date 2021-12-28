package com.joyintech.sso.login.controller;

import com.joyintech.sso.common.Message;
import com.joyintech.sso.common.MessageCode;
import com.joyintech.sso.config.pojo.SsoPlatform;
import com.joyintech.sso.login.dto.LoginParam;
import com.joyintech.sso.login.dto.Platform;
import com.joyintech.sso.login.dto.SsoUser;
import com.joyintech.sso.login.dto.UserPlatFormParm;
import com.joyintech.sso.login.service.SsoLoginService;
import com.joyintech.sso.util.AesEncryptUtil;
import com.joyintech.sso.util.CookieUtil;
import com.joyintech.sso.util.ServletUtil;
import com.joyintech.sso.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * sso统一认证(simple)
 *
 * @auther zhangnian
 * @date 2021/5/11
 */
@Controller
@RequestMapping("/sso/auth")
@Api(tags = "登录")
public class SsoLoginController {

    private static final Logger logger = LoggerFactory.getLogger(SsoLoginController.class);

    @Resource
    private SsoLoginService ssoLoginService;

    /**
     * sso登录
     *
     * @param loginParam
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/ssoLogin", method = RequestMethod.POST)
    @ResponseBody
    public Message<String> ssoLogin(@RequestBody LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        try {
            if (StringUtils.isEmpty(username)) {
                return new Message<String>(MessageCode.FAIL_USERNAME_NULL);
            }
            if (StringUtils.isEmpty(password)) {
                return new Message<String>(MessageCode.FAIL_PASSWORD_NULL);
            }
            password = AesEncryptUtil.desEncrypt(password).trim();
            // 通过用户名获取用户信息
            SsoUser user = ssoLoginService.selectUserInfoBySSOName(username);
            if (null != user) {
                String newPassword = (new SimpleHash("md5", password, user.getSalt(), 2)).toHex();
                if (!user.getPassword().equals(newPassword)) {
                    return new Message<String>(MessageCode.FAIL_PASSWORD_ERROR);
                }
            } else {
                return new Message<String>(MessageCode.FAIL_USERNAME_NOT_EXISTS);
            }
            //username放到cookie
            HttpServletResponse response = ServletUtil.getResponse();
            Cookie cookie = new Cookie(CookieUtil.USER_NAME_KEY, username);
            Cookie cookie1 = new Cookie(CookieUtil.REAL_NAME_KEY, user.getRealName());
            response.addCookie(CookieUtil.setCookie(cookie));
            response.addCookie(CookieUtil.setCookie(cookie1));
        } catch (Exception e) {
            logger.error("sso登录异常:" + e.getMessage());
            return new Message<String>(MessageCode.FAIL);
        }
        return new Message<String>().success();
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/ssoLoginOut", method = RequestMethod.POST)
    @ResponseBody
    public Message<String> ssoLoginOut() {
        try {
            //username放到cookie
            HttpServletResponse response = ServletUtil.getResponse();
            Cookie cookie = new Cookie(CookieUtil.USER_NAME_KEY, "");
            Cookie cookie1 = new Cookie(CookieUtil.REAL_NAME_KEY, "");
            response.addCookie(CookieUtil.rmCookie(cookie));
            response.addCookie(CookieUtil.rmCookie(cookie1));
        } catch (Exception e) {
            logger.error("退出登录异常:" + e.getMessage());
            return new Message<String>(MessageCode.FAIL);
        }
        return new Message<String>().success();
    }


    @ApiOperation(value = "获取用户应用平台", notes = "获取用户应用")
    @RequestMapping(value = "/getPlatformList", method = RequestMethod.POST)
    @ResponseBody
    public Message<List<Platform>> getPlatformList(@RequestBody UserPlatFormParm userPlatFormParm) {
        String username = CookieUtil.getValueByKey();
        if (StringUtils.isEmpty(username)) {
            return new Message<List<Platform>>(MessageCode.LOGIN_TIMEOUT);
        }
        List<Platform> result = ssoLoginService.selectPlatformByUserName(username);
        if (StringUtils.isEmpty(result)) {
            return new Message<List<Platform>>(MessageCode.PLATFORM_NULL);
        }
        return new Message<List<Platform>>().setData(result).success();
    }


    @ApiOperation(value = "应用平台重定向", notes = "应用平台重定向")
    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    @ResponseBody
    public Message<String> redirect(String id) {
        try {
            String username = CookieUtil.getValueByKey();
            if (StringUtils.isEmpty(username)) {
                return new Message<String>(MessageCode.LOGIN_TIMEOUT);
            }
            if (StringUtils.isEmpty(id)) {
                return new Message<String>(MessageCode.FAIL_PLATFORMID_NULL);
            }
            //查看资源信息
            SsoPlatform sysPlatform = ssoLoginService.selectPlatformById(id);
            if (null != sysPlatform) {
                String cookiekey = StringUtils.defaultString(sysPlatform.getCookiekey(), CookieUtil.USER_NAME_KEY);
                //重新设置cookie
                /*HttpServletResponse response = ServletUtil.getResponse();
                String cookiekey = StringUtils.defaultString(sysPlatform.getCookiekey(),CookieUtil.USER_NAME_KEY);
                Cookie cookie = new Cookie(cookiekey,username);
                //cookie.setDomain("192.168.10.108");
                response.addCookie(CookieUtil.setCookie(cookie));
                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("username",username);*/
                //response.setHeader("Access-Control-Allow-Origin", "http://192.168.10.108:9999");
                //  仅支持同IP
                // 重定向
                /*response.sendRedirect(sysPlatform.getPlatformurl()+"?"+cookiekey+"="+username);*/
                //return new Message<String>().setData(sysPlatform.getPlatformurl()).success();
                return new Message<String>().setData(sysPlatform.getPlatformurl() + "?" + cookiekey + "=" + username).success();
            } else {
                logger.warn("应用平台已禁用,请重新登录");
                return new Message<String>(MessageCode.PLATFORM_DIABLED);
            }
        } catch (Exception e) {
            logger.error("应用平台重定向异常:" + e.getMessage());
            return new Message<String>(e.getMessage()).fail();
        }
    }

}

