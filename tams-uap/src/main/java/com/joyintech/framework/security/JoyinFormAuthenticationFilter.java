/*
 * 文件名：JoyinFormAuthenticationFilter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.framework.security;

import com.joyintech.framework.config.ShiroProperties;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.session.dao.SessionDaoMapper;
import com.joyintech.framework.security.session.entity.SysSession;
import com.joyintech.framework.sso.SSOUsernameToken;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;


/**
 * 权限认证拦截器
 * @version 1.0 2019年8月29日
 * @see JoyinFormAuthenticationFilter
 * @since 1.0
 */
public class JoyinFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * SessionDaoMapper
     */
    private SessionDaoMapper sessionDaoMapper;

    private AuthorizationService authorizationService;

    private String cookieKey;
    
    private boolean ihsEnabled;

    /**
     * @param sessionDaoMapper SessionDaoMapper
     */
    public JoyinFormAuthenticationFilter(SessionDaoMapper sessionDaoMapper, ShiroProperties shiroProperties) {
        this.sessionDaoMapper = sessionDaoMapper;
        this.cookieKey = shiroProperties.getSessionId();
        this.ihsEnabled = shiroProperties.isIhsEnable();
    }

    @Override
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        Subject subject = super.getSubject(request, response);
        if(ihsEnabled && !subject.isAuthenticated() && !isLoginRequest(request, response) && isHttpRequest(request)) {
            // 获取所有cookie
            Cookie[] cookies = ((HttpServletRequest)request).getCookies();
            if(null != cookies) {
                for(Cookie cookie : cookies) {
                    if(StringUtil.equals(cookie.getName(), cookieKey)) {
                        try {
                            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
                            SysSession sysSession = this.sessionDaoMapper.findSession(cookie.getValue());
                            DynamicDataSourceHolder.clearDataSource();
                            if(sysSession != null) {
                                Session session = SerializationUtils.deserialize(sysSession.getSessionData());
                                if(null != session && session.getHost() != null) {
                                    Collection<Object> keys = session.getAttributeKeys();
                                    if(null != keys && !keys.isEmpty() && keys.contains(GlobalConstant.CURRENT_USER)) {
                                        subject = createSubject(session);
                                        ThreadContext.bind(subject);
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                        break;
                    }
                }
            }
        }
        return subject;
    }

    private Subject createSubject(Session session) {
        SubjectContext context = new DefaultSubjectContext();
        context.setAuthenticated(true);
        context.setSessionId(session.getId());
        context.setHost(session.getHost());
        context.setSession(session);
        return SecurityUtils.getSecurityManager().createSubject(context);
    }

    private boolean isHttpRequest(ServletRequest request) {
        return request instanceof HttpServletRequest;
    }

    // APP 登录拦截
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        boolean app = StringUtil.isNotEmpty("appType") && "web.app".equals(request.getParameter("appType"));
        boolean appFrom = req.getHeader("appType") != null && !req.getHeader("appType").equals("") && req.getHeader("appType").equals("web.app");
        if (appFrom || app) {
            AuthenticationToken token = createToken(request, response);
            if (null == token) { //  || !SecurityUtil.hasAuthenticated()
                String msg = "Token 已失效！";
                throw new IllegalStateException(msg);
            }
            try {
                // 将用户信息保存到session中
                Cookie[] cookie = ((HttpServletRequest)request).getCookies();
                if(null != cookie) {
                    for (Cookie c : cookie) {
                        // 先用 session 中获取用户信息
                        SessionDAO sessionDao = SpringContextUtil.getBean(SessionDAO.class);
                        Session session = sessionDao.readSession(c.getValue());
                        String isApp = (String)session.getAttribute(c.getValue());
                        if (null == isApp) {
                            // 通过用户名获取用户对象
                            if (null == authorizationService) {
                                authorizationService = SpringContextUtil.getBean(AuthorizationService.class);
                            }
                            User user = authorizationService.getUser(c.getName());
                            if (null != user) {
                            	String orgType = authorizationService.getUserOrgType(user.getOrgCode());
                                // 防止重复登录
                                session.setAttribute(session.getId(), "true");

                                // 执行登录
                                SSOUsernameToken ssoUsernameToken = new SSOUsernameToken();
                                ssoUsernameToken.setUsername(c.getName());
                                ssoUsernameToken.setPassword(user.getPassword().toCharArray());

//                                token = createToken(c.getName(), user.getPassword(), request, response);

                                Subject subject = getSubject(request, response);
                                subject.login(ssoUsernameToken);

//                                ((UsernamePasswordToken)token).setUsername(c.getName());
//                                ((UsernamePasswordToken)token).setPassword("111111".toCharArray());
//                                subject.login(token);

                                session.setAttribute("__" + session.getId(), user); // 将用户对象放到 cookie 的 session
                                session.setAttribute("__sk_user_orgType_", orgType); // 将用户对象的机构类型放到 cookie 的 session
                                if(MultilevelDataAuthorityUtil.isEnabled()) {
                                    String userName = user.getUserName();
                                    String institutionCode = MultilevelDataAuthorityUtil.getOrganizationCode(userName);
                                    session.setAttribute(GlobalConstant.SESSION_INSTITUTION_CODE, institutionCode);
                                }
                            } else {
                                throw new BusinessException("用户不存在！");
                            }
                        }
                    }
                }
                return true;
            } catch (AuthenticationException e) {
                return onLoginFailure(token, e, request, response);
            }
        }
        return super.onAccessDenied(request, response);
    }

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            Session session = subject.getSession();
            final LinkedHashMap<Object, Object> attributes = new LinkedHashMap<Object, Object>();
            final Collection<Object> keys = session.getAttributeKeys();
            for (Object key : keys) {
                final Object value = session.getAttribute(key);
                if (value != null) { 
                	attributes.put(key, value); 
                }
            }
            session.stop();
            subject.login(token);
            session = subject.getSession();
            for (final Object key : attributes.keySet()) { 
            	session.setAttribute(key, attributes.get(key)); 
            }
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
        	//TISHKBS-1607 重定向后登录失败报错 start
        	Class clazz = request.getClass();
 	        Field field = clazz.getDeclaredField("session");
 	        if(field!=null) {
 	        	field.setAccessible(true);
 	        	field.set(request, null);
 	        }
 	        //TISHKBS-1607 重定向后登录失败报错 end
            return onLoginFailure(token, e, request, response);
        }
	}
    
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
//		WebUtils.getAndClearSavedRequest(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write("{\"code\":\"000\",\"message\":\"用户登录成功！\",\"data\":null}");
		writer.flush();
		writer.close();
		return false;
	}
}
