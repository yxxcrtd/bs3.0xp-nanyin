package com.joyintech.tams.exchange;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * controller
 * @author huwei
 * @version 1.0 2018年10月25日
 * @see ExchangeLoginController
 * @since 1.0
 */
@Controller
public class ExchangeLoginController {
    /**
     * 登录成功后是否跳转到之前请求的画面
     */
    @Value("${shiro.redirectToSavedRequest}")
    private boolean redirectToSavedRequest;
	
    /**
     * 登录画面
     */
    @Value("${shiro.loginUrl}")
    private String loginUrl;
    /**
     * 登录成功后的跳转页面
     */
    @Value("${shiro.loginSuccessUrl}")
    private String loginSuccessUrl;
    
    /**
     * logo
     */
    @Value("${application.logo}")
    private String applicationLogo;
    
    /**
     * logo
     */
    @Value("${application.logoPA}")
    private String bankNameLog;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ExchangeLoginController.class);
    
    
    
    
    /**
     * 跳转到登录画面
     * 
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param model {@link ModelMap}
     * @return 登录画面
     */
    @RequestMapping(value = "/exchange/login", method = RequestMethod.GET)
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
            	logger.error("",e);
            }
            return null;
        }
        if(!redirectToSavedRequest) {
            WebUtils.getAndClearSavedRequest(request);
        }
        if("01".equals(CacheUtil.getSysParam("sysname_pre_icon"))){
            model.addAttribute("bankNameLog", bankNameLog);
        }
        
                model.addAttribute("applicationLogo", applicationLogo);
        mv.setViewName("login");
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
    @RequestMapping(value = "/exchange/login", method = RequestMethod.POST)
    public String login(RedirectAttributes redirectAttributes,
                        @RequestAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) String exceptionName) {
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
        else {
            msg = SpringContextUtil.getTextValue("FW_LOGIN_E_008", exceptionName);
        }
        MessageBox.error(msg, redirectAttributes);
        // 登录失败后的跳转画面
        return "redirect:" + loginUrl;
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
