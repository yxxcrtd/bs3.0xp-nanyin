/*
 * 文件名：TamsAuthorizationListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.systemtools.listener;

import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Component;

import com.joyintech.framework.security.AuthorizationListener;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;

@Component
public class TamsAuthorizationListener implements AuthorizationListener  {
	 /**
     * USER_AUTHORITY_DEPT_CODE
     */
    public static final String USER_AUTHORITY_DEPT_CODE = "USER_AUTHORITY_DEPT_CODE";

    @Override
    public void onLoginSucess(User arg0) {
        SecurityUtil.addSession(USER_AUTHORITY_DEPT_CODE, CommonUtil.getAuthorityDeptByUserName(arg0.getUserName()));
    }

    @Override
    public void onLogout(User arg0) {

    }

}
