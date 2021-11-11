package com.joyintech.tams.uap.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.navbar.Navbar;
import com.joyintech.framework.navbar.NavbarService;
import com.joyintech.framework.navbar.UserNavBarService;
import com.joyintech.framework.security.DuplicateLogonException;
import com.joyintech.framework.security.PasswordHelper;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.security.UserCredentialsService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.CryptoJSUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.web.LogoSelector;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.system.main.dto.ChangePasswordDto;
import com.joyintech.system.main.service.ChangePasswordService;
import com.joyintech.tams.uap.common.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限认证控制器<br>
 * 跳转用户登录画面和用户登录认证
 *
 * @author yflv
 * @version 1.0 2021年11月05日
 * @see LoginController
 * @since 1.0
 */
@Controller
@Api(tags = "权限认证控制器")
public class LoginController extends BaseController {

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
     * NavbarService
     */
    @Autowired
    private NavbarService navbarService;
    
    @Autowired
    private ChangePasswordService changePassService;



    /**
     * 处理用户登录请求<br>
     * shiro提供的{@link FormAuthenticationFilter}拦截器会自动拦截post方式的登录请求，如果认证失败，则会进入该方法
     * 
     * @param redirectAttributes 画面跳转参数
     * @param exceptionName shiro认证失败后的异常信息
     * @return 登录失败后跳转地址
     */
    @ApiOperation(value = "处理用户登录请求", notes = "处理用户登录请求")
    @ResponseBody
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Message<String> login(RedirectAttributes redirectAttributes,
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
            return new Message<String>().setMessage(msg).success();
        }
//        AjaxResult ajaxResult = new AjaxResult();
//        if (StringUtil.equals(exceptionName, DuplicateLogonException.class.getName())) {
//            ajaxResult.setMessage(msg, MessageType.确认消息);
//        }
//        else {
//            ajaxResult.setMessage(msg, MessageType.错误消息);
//        }
        return new Message<String>().setMessage(msg).fail();
    }
    
    @ApiOperation(value = "处理用户登出请求", notes = "处理用户登出请求")
    @ResponseBody
    @RequestMapping(value = "/auth/logout" , method = RequestMethod.POST)
    public Message<String> logout() {
    	if (SecurityUtil.hasAuthenticated()) {
    		Subject currentUser = SecurityUtils.getSubject();    
    		currentUser.logout(); 
        	return new Message<String>().success();
        }
        return new Message<String>().setMessage("用户未登录!").fail();
    }
    
    @ApiOperation(value = "导航菜单", notes = "导航菜单")
    @ResponseBody
    @RequestMapping(value = "/userNavbar/list" , method = RequestMethod.POST)
    public Message<List<Navbar>> navbars() {
    	List<Navbar> list = navbarService.selectNavbars();
    	return new Message<List<Navbar>>(list).success();
    }
    
    /**
     * 获取当前登录用户信息
     * @return
     */
    @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
    @ResponseBody
    @RequestMapping(value = "/auth/userInfo", method = RequestMethod.POST)
    public Message<User> userInfo() {
        if (SecurityUtil.hasAuthenticated()) {
        	User user = SecurityUtil.getLoginUser();
        	return new Message<User>(user).success();
        }
        return new Message<User>().setMessage("用户未登录!").fail();
    }
    
    
    /**
     * 前后端分离--重构跳转到登录画面
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/auth/login2", method = RequestMethod.GET)
    public Message<String> login2() {
        if (SecurityUtil.hasAuthenticated()) {
        	return new Message<String>().setMessage("用户已登录!").success();
        }
        return new Message<String>().setMessage("用户未登录!").success();
    }
    
    
    
    
    
    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
                              ModelMap model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/home.html");
        return mv;
    }
    
    
    
    
    
    
    /**
     * 前后端分离--重构首页路径
     * @return
     */
    @ResponseBody
    @RequestMapping({"/index", "/indexDefault"})
    public Message<String> index() {
        if (SecurityUtil.hasAuthenticated()) {
        	return new Message<String>().setMessage("用户已登录!").success();
        }
        return new Message<String>().setMessage("用户未登录!").fail();
    }
    
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ResponseBody
    @RequestMapping(value = "/auth/changepassword", method = RequestMethod.POST)
    public Message<List<ValidMessage>> changepassword(@RequestBody ChangePasswordDto dto) {
        if (SecurityUtil.hasAuthenticated()) {
        	List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
            User user = SecurityUtil.getLoginUser();
            com.joyintech.system.user.dto.User newUser = new com.joyintech.system.user.dto.User();
            newUser.setPassword(dto.getOldPassword());
            newUser.setSalt(user.getSalt());
            PasswordHelper.encryptPassword(newUser);
            String oldPassword = newUser.getPassword();
            if (!StringUtil.equals(user.getPassword(), oldPassword)) {
                ValidMessage validMessage = new ValidMessage(
                    this.getMessage("COMMON_E_0016", getMessage("changePassword.old")));
                validMessageList.add(validMessage);
            }
            else if (!StringUtil.equals(dto.getPassword(), dto.getConfirmPassword())) {
                ValidMessage validMessage = new ValidMessage("password",
                    getMessage("PASSWORDCHANGE_E_0002"));
                validMessageList.add(validMessage);
            }
            else {
                // 校验是否与用户名相同
                String pw_nameSame = CacheUtil.getSysParam(GlobalConstant.PW_NAMESAME);
                if (StringUtil.isNotEmpty(pw_nameSame)
                    && StringUtil.equals(pw_nameSame, DictCodeList.是否_是.getValue())) {
                    if (StringUtil.equals(user.getUserName(), dto.getPassword())) {
                        validMessageList.add(
                            new ValidMessage("newPassword", getMessage("PASSWORDCHANGE_E_0005")));
                    }
                }
                // 判断密码的规则
                String pw_rule = CacheUtil.getSysParam(GlobalConstant.PW_RULE);
                if (StringUtil.isNotEmpty(pw_rule)) {
                    Pattern pattern = Pattern.compile(pw_rule);
                    Matcher matcher = pattern.matcher(dto.getPassword());
                    if (!matcher.matches()) {
                        String pw_rule_tip = CacheUtil.getSysParam("pw_rule_tip");
                        validMessageList.add(new ValidMessage("password",
                            getMessage("PASSWORDCHANGE_E_0003", pw_rule_tip)));
                    }
                }
                // 校验历史密码
                String pw_presame = CacheUtil.getSysParam(GlobalConstant.PW_PRESAME);
                if (StringUtil.isNotEmpty(pw_presame) && Integer.parseInt(pw_presame) > 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId", user.getUid());
                    int level = Integer.parseInt(pw_presame) + 1;
                    List<Map<String, String>> pwList = changePassService.getAllPasswordHis(map);
                    // 判断
                    for (Map<String, String> pwTemp : pwList.subList(0,
                        level < pwList.size() ? level : pwList.size())) {
                        if (pwTemp != null) {
//                            String passwordHis = new SimpleHash(PasswordHelper.ALGORITHM_NAME,
//                                    dto.getPassword(), pwTemp.get("SALT") == null ? pwTemp.get("salt") : pwTemp.get("SALT"),
//                                    PasswordHelper.HASH_ITERATIONS).toHex();
                        	String passwordHis = userCredentialsService.encryptCredentials(dto.getPassword(), 
                        			pwTemp.get("SALT") == null ? pwTemp.get("salt") : pwTemp.get("SALT"));
                            if (StringUtil.equals(passwordHis, pwTemp.get("PASSWORD") == null ? pwTemp.get("password") : pwTemp.get("PASSWORD"))) {
                                validMessageList.add(new ValidMessage("password",
                                        getMessage("PASSWORDCHANGE_E_0004", level - 1)));
                                break;
                            }
                        }
                    }
                }
                // 校验是否和系统默认密码相同，相同则报错
                String initPassword = CacheUtil.getSysParam(GlobalConstant.INIT_PASSWORD);
                if (StringUtil.equals(dto.getPassword(), initPassword)) {
                	validMessageList.add(new ValidMessage("password", getMessage("PASSWORDCHANGE_E_0006")));
                }
            }
            if (validMessageList.size() > 0) {
            	return new Message<List<ValidMessage>>(validMessageList).setMessage(validMessageList.get(0).getMessage()).fail();
            }
        	changePassService.doComplete(dto);
        	return new Message<List<ValidMessage>>().success();
        }
        return new Message<List<ValidMessage>>().setMessage("用户未登录!").fail();
    }
    
}
