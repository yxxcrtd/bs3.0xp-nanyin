/*
 * 文件名：AuthController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改时间：2018年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.framework.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.navbar.UserNavBarService;
import com.joyintech.framework.security.DuplicateLogonException;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.security.UserCredentialsService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.CryptoJSUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.LogoSelector;
import com.joyintech.framework.web.PlatformDate;

/**
 * 权限认证控制器<br>
 * 跳转用户登录画面和用户登录认证
 *
 * @version 1.0 2018年1月18日
 * @see AuthController
 * @since 1.0
 */
//@Controller
public class AuthController {

    /**
     * 登录画面
     */
    @Value("${shiro.loginUrl}")
    private String loginUrl;
    
    @Autowired(required = false)
    private PlatformDate platformDate;

    /**
     * 登录成功后的跳转页面
     */
    @Value("${shiro.loginSuccessUrl}")
    private String loginSuccessUrl;
    /**
     * 菜单展现形式
     */
    @Value("${application.menuType}")
    private String menuType;
    
    /**
     * 菜单展现形式
     */
    @Value("${application.menuPosition}")
    private String menuPosition;
    
    /**
     * 登录成功后是否跳转到之前请求的画面
     */
    @Value("${shiro.redirectToSavedRequest}")
    private boolean redirectToSavedRequest;

    
    /**
     * 自定义菜单接口
     */
    @Autowired(required = false)
    private UserNavBarService userNavBarService;

    @Autowired
    private LogoSelector logoSelector;

    @Autowired(required = false)
    private UserCredentialsService userCredentialsService;

    /**
     * 定义首页路径
     * 
     * @return 首页
     */
    @RequestMapping("/")
    public String index1() {
        return "redirect:index.html";
    }

    /**
     * 定义首页路径
     * 
     * @param model Model
     * @return 首页
     */
    @RequestMapping({"/index", "/indexDefault"})
    public String index2(Model model) {
        User user = SecurityUtil.getLoginUser();
        if(platformDate!=null) {
        	model.addAttribute("platDate",DateUtil.format(platformDate.getSysDate()));
        }
        String theme = StringUtil.defaultIfEmpty((String)SecurityUtil.getFromSession(GlobalConstant.SESSION_MY_SKIN), LogoSelector.DEFAULT_THEME);
        model.addAttribute("user", user);
        model.addAttribute("applicationLogo", logoSelector.appLogo());
        model.addAttribute("applicationLeftMenuLogo", logoSelector.leftLogo(theme));
        model.addAttribute("applicationTopMenuLogo", logoSelector.topLogo(theme));
        model.addAttribute("bankNameLog", logoSelector.bankLogo(theme));
        model.addAttribute("applicationMinLogo", logoSelector.minLogo(theme));
        // 是否现在主动提醒按钮
        model.addAttribute(GlobalConstant.IS_SHOW_REMIND, StringUtil.equals(CacheUtil.getSysParam(GlobalConstant.IS_SHOW_REMIND), DictCodeList.是否_是.getValue()));
        // 登陆成功后首页中的导航中的主题中心是否展示
        model.addAttribute("isShowTheme", StringUtil.equals(DictCodeList.是否_是.getValue(), CacheUtil.getSysParam(GlobalConstant.IS_SHOW_THEME)));
        /*** 设置菜单配置参数 start */
        // 系统默认菜单类型
        model.addAttribute("menuType", menuType);
        // 系统默认菜单显示位置
        model.addAttribute("defaultMenuPosition", menuPosition);
        // 菜单与菜单之间的间距
        model.addAttribute("menuMenuDistance", CacheUtil.getSysParam(GlobalConstant.MENU_MENU_DISTANCE));
        // 菜单文字字体大小设置
        model.addAttribute("menuFontSize", CacheUtil.getSysParam(GlobalConstant.MENU_FONT_SIZE));
        // 菜单名称和图标是一行展示，还是分行展示
        model.addAttribute("menunameIconOneline", CacheUtil.getSysParam(GlobalConstant.MENUNAME_ICON_ONELINE));
        // 系统名称前是否增加银行的log
        model.addAttribute("sysnamePreIcon", CacheUtil.getSysParam(GlobalConstant.SYSNAME_PRE_ICON));
        // 获取自定义用户菜单
        if(null != userNavBarService) {
            model.addAttribute("umenu", userNavBarService.getUserNavbar());
        }
        
        //密码修改周期
        String pwCycle = CacheUtil.getSysParam(GlobalConstant.PW_CYCLE);
        //初始化是否需要修改密码
        String pwInitEdit = CacheUtil.getSysParam(GlobalConstant.PW_INITEDIT);
        //初始数据密码
        String initPassword = CacheUtil.getSysParam(GlobalConstant.INIT_PASSWORD);
        //密码修改方式 
        //01为不修改 02为必须修改，03 为可选择修改
        String pwTipModle = "01";
        //如果需要初始化就修改密码
        if (StringUtil.isNotEmpty(pwInitEdit)
            && StringUtil.equals(pwInitEdit, DictCodeList.是否_是.getValue())) {
//            String passwordDefault = new SimpleHash(PasswordHelper.ALGORITHM_NAME, initPassword,
//                user.getSalt(), PasswordHelper.HASH_ITERATIONS).toHex();
        	String passwordDefault = userCredentialsService.encryptCredentials(initPassword, user.getSalt());
        	if (StringUtil.equals(passwordDefault, user.getPassword())) {
                pwTipModle = "02";
            }
        }
        else if ((StringUtil.equals(pwTipModle, "01") && StringUtil.isNotEmpty(pwCycle) && Integer.parseInt(pwCycle) > 0)) {
            Date lastPassDate = user.getLastPassDate();
            Date nowDate = DateUtil.platDateFromCache();
            if(platformDate!=null) {
            	nowDate = platformDate.getSysDate();
            }
            if (lastPassDate == null || Integer.parseInt(pwCycle) < Math.abs(
                DateUtil.getBetweenDayNumber(lastPassDate, nowDate))) {
                pwTipModle = "03";
            }
        }
        model.addAttribute("pwTipModle", pwTipModle);

        if (MultilevelDataAuthorityUtil.isEnabled()) {
            model.addAttribute("isShowSelectInstitution", "01");
            model.addAttribute("userOrgType",  LoginUserUtil.getUserOrgType());

            model.addAttribute("isEJFRflag", true);
            model.addAttribute("indexInstitutionCode",user==null?"":user.getOrgCode());
        }else{
            model.addAttribute("isEJFRflag", false);
        }
        // 左侧菜单展示模式（01:现有的一级一级展示、02:光大的子菜单合并展示）
        String leftMenuDisplayType = CacheUtil.getSysParam(GlobalConstant.LEFT_MENU_DISPLAY_TYPE);
        if (StringUtil.equals(leftMenuDisplayType, DictCodeList.是否_否.getValue())) {
            model.addAttribute("isShowBigLeftMenu", true);
        }
        else {
            model.addAttribute("isShowBigLeftMenu", false);
        }
        // 菜单展示级数
        String menuDisplayLevel = CacheUtil.getSysParam(GlobalConstant.MENU_DISPLAY_LEVEL);
        if (StringUtil.equals(menuDisplayLevel, GlobalConstant.MENU_DISPLAY_LEVEL_FOUR)) {
        	model.addAttribute("isFourMenuLevel", true);
        }
        else {
        	model.addAttribute("isFourMenuLevel", false);
        }
        /*** 设置菜单配置参数 end */
        return trimIndex();
    }

    /**
     * 跳转到登录画面
     * 
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param model {@link ModelMap}
     * @return 登录画面
     */
    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                              ModelMap model) {
        ModelAndView mv = new ModelAndView();
        // 如果已经登录，直接跳到index页面
        if (SecurityUtil.hasAuthenticated()) {
            mv.setViewName("redirect:/" + trimIndex());
            return mv;
        }
        
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (StringUtils.equalsIgnoreCase("XMLHttpRequest", xRequestedWith)) {
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setStatuCode(401);
            // ajax请求
            response.setHeader("Content-Type", "text/html;charset=utf-8");
            try {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getOutputStream().write(
                    JsonUtils.object2Json(ajaxResult).getBytes("UTF-8"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        if(!redirectToSavedRequest) {
            WebUtils.getAndClearSavedRequest(request);
        }
        model.addAttribute("applicationLogo", logoSelector.appLogo());
        model.addAttribute("privateKey", CryptoJSUtil.PRIVATE_KEY);
        model.addAttribute("loginSuccessUrl", loginSuccessUrl);

        mv.setViewName("auth/login");
        return mv;
    }

    /**
     * 处理用户登录请求<br>
     * shiro提供的{@link FormAuthenticationFilter}拦截器会自动拦截post方式的登录请求，如果认证失败，则会进入该方法
     * 
     * @param redirectAttributes 画面跳转参数
     * @param exceptionName shiro认证失败后的异常信息
     * @return 登录失败后跳转地址
     */
    @ResponseBody
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public AjaxResult login(RedirectAttributes redirectAttributes,
                        @RequestAttribute(name = FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, required = false) String exceptionName) {
        String msg = StringUtil.EMPTY;
        if(StringUtil.equals(exceptionName, IncorrectCredentialsException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_001");
        }
        else if(StringUtil.equals(exceptionName, ExcessiveAttemptsException.class.getName())) {
            String loginFailLockTime = CacheUtil.getSysParam(GlobalConstant.LOGIN_FAIL_LOCK_TIME);
            long lockTime = 1800;
            if(StringUtil.isNotBlank(loginFailLockTime)) {
                lockTime = Long.valueOf(loginFailLockTime.trim());
            }
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_002", String.valueOf(lockTime));
        }
        else if(StringUtil.equals(exceptionName, LockedAccountException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_003");
        }
        else if(StringUtil.equals(exceptionName, DisabledAccountException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_004");
        }
        else if(StringUtil.equals(exceptionName, ExpiredCredentialsException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_005");
        }
        else if(StringUtil.equals(exceptionName, UnknownAccountException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_006");
        }
        else if(StringUtil.equals(exceptionName, UnauthorizedException.class.getName())) {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_007");
        }
        else if (StringUtil.equals(exceptionName, DuplicateLogonException.class.getName())){
            msg = SpringContextUtil.getTextValue("FW_LOGIN_W_001");
        }
        else {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_008", exceptionName);
        }
        AjaxResult ajaxResult = new AjaxResult();
        if (StringUtil.equals(exceptionName, DuplicateLogonException.class.getName())) {
            ajaxResult.setMessage(msg, MessageType.确认消息);
        }
        else {
            ajaxResult.setMessage(msg, MessageType.错误消息);
        }
        return ajaxResult;
    }
    
    /**
     * 
     * @return 模板
     */
    private String trimIndex() {
        String indexTemplate = loginSuccessUrl;
        if(null != indexTemplate) {
            if(indexTemplate.startsWith("/")) {
                indexTemplate = indexTemplate.substring(1);
            }
            if(indexTemplate.endsWith(".html")) {
                indexTemplate = indexTemplate.substring(0, indexTemplate.length() - 5);
            }
        }
        return indexTemplate;
    }
}
