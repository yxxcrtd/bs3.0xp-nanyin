package com.joyintech.sso.interf.controller;

import com.joyintech.sso.interf.dto.InterfInstitution;
import com.joyintech.sso.interf.service.InterfSsoService;
import com.joyintech.sso.login.dto.Platform;
import com.joyintech.sso.login.dto.SsoUser;
import com.joyintech.sso.login.dto.UserPlatFormParm;
import com.joyintech.sso.login.service.SsoLoginService;
import com.joyintech.sso.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@RestController
@RequestMapping("/sso/sso")
@Api(tags = "提供外部接口")
public class InterfSsoController {

    @Autowired
    private SsoLoginService ssoLoginService;

    @Autowired
    private InterfSsoService interfSsoService;

    @ApiOperation(value = "机构信息", notes = "机构信息")
    @RequestMapping(value = "/selectInstitution", method = RequestMethod.POST)
    public List<InterfInstitution> selectInstitution(){
        return interfSsoService.selectInstitution();
    }

    @ApiOperation(value = "用户信息", notes = "用户信息")
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public List<SsoUser> selectUser(){
        return interfSsoService.selectUser();
    }

    @ApiOperation(value = "用户获取授权的应用平台", notes = "用户获取授权的应用平台")
    @RequestMapping(value = "/selectPlatformByUserName", method = RequestMethod.POST)
    public List<Platform> selectPlatformByUserName(@RequestBody UserPlatFormParm userPlatFormParm){
        if(StringUtils.isNotEmpty(userPlatFormParm.getUsername())){
            return ssoLoginService.selectPlatformByUserName(userPlatFormParm.getUsername());
        }
        return new ArrayList<>();
    }

}

