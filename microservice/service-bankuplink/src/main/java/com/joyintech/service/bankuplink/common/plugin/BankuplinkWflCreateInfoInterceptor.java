/*
 * 文件名：BankuplinkflCreateInfoInterceptor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.common.plugin;

import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.common.pack.common.security.UserUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.plugin.MybatisInterceptor;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.plugin.WflCreateInfoInterceptor;
import com.joyintech.workflow.engine.util.WflCreateInfoUtil;

/**
 * 创建人、创建部门、创建时间等流程创建信息处理
 *
 * @author etliu
 * @version 1.0 2019年10月24日
 * @see WflCreateInfoInterceptor
 * @since 1.0
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class BankuplinkWflCreateInfoInterceptor implements MybatisInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object intercept(Invocation invocation)
        throws Throwable {
        try {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

            // 框架代码中的sql不拦截
            if (mappedStatement.getId().startsWith("com.joyintech.workflow")
                || mappedStatement.getId().startsWith("com.joyintech.framework")
                || mappedStatement.getId().startsWith("com.joyintech.tools")) {
                return invocation.proceed();
            }

            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = boundSql.getSql().toLowerCase();
            
            if (StringUtil.containsIgnoreCase(sql, "insert ")
                && StringUtil.containsIgnoreCase(sql, " into ")
                && StringUtil.containsIgnoreCase(sql, "values")) {
                Object parameterObject = boundSql.getParameterObject();

                if (parameterObject != null && parameterObject instanceof BaseEntity) {
                    BaseEntity parameterObjectBase = (BaseEntity) boundSql.getParameterObject();

                    // 创建人、创建部门、创建时间设置为流程发起时的值，更新人、更新时间修改为当前时间、当前处理用户
                    if (StringUtil.isNotBlank(WflCreateInfoUtil.getWflCreateUser())) {
                        parameterObjectBase.setCreateUser(WflCreateInfoUtil.getWflCreateUser());

                        parameterObjectBase.setUpdateTime(new Date());
                        String loginUserName = LoginUserUtil.getUserName();
                        if (StringUtil.isNotBlank(loginUserName) && !StringUtil.equals(GlobalConstant.SYSTEM_USER, loginUserName)) {
                            parameterObjectBase.setUpdateUser(loginUserName);
                        }
                        else {
                            // 更新人为默认用户时，设置为流程发起人
                            if (StringUtil.isBlank(parameterObjectBase.getUpdateUser())
                                || StringUtil.equals(UserUtil.DEFAULT_USER,
                                    parameterObjectBase.getUpdateUser())) {
                                parameterObjectBase.setUpdateUser(
                                    WflCreateInfoUtil.getWflCreateUser());
                            }
                        }
                    }
                    if (StringUtil.isNotBlank(WflCreateInfoUtil.getWflCreateDept())) {
                        parameterObjectBase.setCreateDept(WflCreateInfoUtil.getWflCreateDept());
                    }
                    if (WflCreateInfoUtil.getWflCreateTime() != null) {
                        parameterObjectBase.setCreateTime(WflCreateInfoUtil.getWflCreateTime());
                    }
                    
                    if (StringUtil.isBlank(parameterObjectBase.getCreateUser())) {
                        parameterObjectBase.setCreateUser(LoginUserUtil.getUserName());
                    }
                    if (StringUtil.isBlank(parameterObjectBase.getUpdateUser())) {
                        parameterObjectBase.setUpdateUser(LoginUserUtil.getUserName());
                    }
                    if (parameterObjectBase.getCreateTime() == null) {
                        parameterObjectBase.setCreateTime(new Date());
                    }
                    if (parameterObjectBase.getUpdateTime() == null) {
                        parameterObjectBase.setUpdateTime(new Date());
                    }
                }
            }
            else if (StringUtil.startsWith(sql, "update")) {
                Object parameterObject = boundSql.getParameterObject();
                // 更新人为默认用户时，设置为流程发起人
                if (parameterObject != null && parameterObject instanceof BaseEntity) {
                    BaseEntity parameterObjectBase = (BaseEntity)boundSql.getParameterObject();
                    if (StringUtil.isBlank(parameterObjectBase.getUpdateUser())
                        || StringUtil.equals(UserUtil.DEFAULT_USER,
                            parameterObjectBase.getUpdateUser()))
                        if (StringUtil.isNotBlank(WflCreateInfoUtil.getWflCreateUser())) {
                            parameterObjectBase.setUpdateUser(
                                WflCreateInfoUtil.getWflCreateUser());
                        }
                        else {
                            parameterObjectBase.setUpdateUser(null);
                        }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return invocation.proceed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperties(Properties properties) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
