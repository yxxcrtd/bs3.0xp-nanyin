package com.joyintech.tams.disclosure.common.common.listener;
///*
// * 文件名：ReportAuthorizationListener.java
// * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管业务监管报送平台
// * 修改人：刘二通
// * 修改时间：2018年7月5日
// * 修改内容：新建
// * 系统名称：理财资管业务监管报送平台
// */
//
//package com.joyintech.tams.disclosure.common.common.listener;
//
//import org.springframework.stereotype.Component;
//
//import com.joyintech.framework.security.AuthorizationListener;
//import com.joyintech.framework.security.SecurityUtil;
//import com.joyintech.framework.security.User;
//import com.joyintech.tams.disclosure.common.common.util.CommonUtil;
//
//@Component
//public class ReportAuthorizationListener implements AuthorizationListener {
//
//    /**
//     * USER_AUTHORITY_DEPT_CODE
//     */
//    public static final String USER_AUTHORITY_DEPT_CODE = "USER_AUTHORITY_DEPT_CODE";
//
//    @Override
//    public void onLoginSucess(User arg0) {
//        SecurityUtil.addSession(USER_AUTHORITY_DEPT_CODE, CommonUtil.getAuthorityDeptByUserName(arg0.getUserName()));
//    }
//
//    @Override
//    public void onLogout(User arg0) {
//
//    }
//}
