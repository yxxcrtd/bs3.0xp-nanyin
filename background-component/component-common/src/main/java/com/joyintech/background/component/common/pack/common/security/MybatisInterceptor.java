package com.joyintech.background.component.common.pack.common.security;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * @author 何阳阳
 */
@Component
@Intercepts({
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
//                Object.class, RowBounds.class, ResultHandler.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
//                Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class})
})
public class MybatisInterceptor implements Interceptor {

    @Autowired
    protected void initConfig(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addInterceptor(this);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        String method = invocation.getMethod().getName();
        if (args.length > 1 && StringUtil.equals(method, "update")) {
            MappedStatement mappedStatement = (MappedStatement) args[0];
            Object parameters = invocation.getArgs()[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameters);
            Object parameterObject = boundSql.getParameterObject();

            // Modified by JIAOXUJIN on 2020-06-02
            String sql = boundSql.getSql();
            if(isUpdate(sql) || isInsert(sql)) {
                // MyBaits方法可能有多个参数
                if (parameters instanceof Map<?, ?> && ((Map<?, ?>)parameters).size() > 1
                    && parameterObject instanceof Map<?, ?>) {
                   Iterator<?> paramIt = ((Map<?, ?>)parameterObject).entrySet().iterator();
                   while (paramIt.hasNext()) {
                       setParameterUserInfo(((Entry<?, ?>)paramIt.next()).getValue(), sql);
                   }
                }
                else {
                    setParameterUserInfo(parameterObject, sql);
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 将UserInfo信息设置到MyBatis方法的单个参数中 Added by JIAOXUJIN on 2020-06-15
     * @param parameterObject
     * @param sql
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    private void setParameterUserInfo(Object parameterObject, String sql)
        throws InvocationTargetException, IllegalAccessException {
        if (parameterObject instanceof BaseEntity) {
            setUserInfoToEntity((BaseEntity)parameterObject, isInsert(sql));
        }
        else if (parameterObject instanceof Map<?, ?>) {
            // 遍历MapValue
            ((Map<?, ?>)parameterObject).values().parallelStream().forEach(p -> {
                if (p instanceof BaseEntity) {
                    try {
                        setUserInfoToEntity((BaseEntity)p, isInsert(sql));
                    }
                    catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                else if (p instanceof Collection<?>) {
                    // 遍历List
                    for (Object item : (Collection<?>)p) {
                        if (item instanceof BaseEntity) {
                            try {
                                setUserInfoToEntity((BaseEntity)item, isInsert(sql));
                            }
                            catch (InvocationTargetException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        else if (parameterObject instanceof Collection<?>) {
            // 遍历List
            for (Object item : (Collection<?>)parameterObject) {
                if (item instanceof BaseEntity) {
                    setUserInfoToEntity((BaseEntity)item, isInsert(sql));
                }
            }
        }
    }

    private void setUserInfoToEntity(BaseEntity parameter, boolean isNew) throws InvocationTargetException, IllegalAccessException {
        if(null != parameter) {
            // edit by etliu 20210113 start
            // 若参数中的更新者不为空，且UserUtil获取的用户名为默认用户，则不重置parameter中的更新者，其他场景更新
//            parameter.setUpdateUser(UserUtil.getUserName());
            String currUserName = UserUtil.getUserName();
            if (!(!StringUtil.isBlank(parameter.getUpdateUser())
                && StringUtil.equals(UserUtil.DEFAULT_USER, currUserName))
                && StringUtil.isNotBlank(currUserName)) {
                parameter.setUpdateUser(currUserName);
            }
            // edit by etliu 20210113 end
            parameter.setUpdateTime(new Date());
            if(isNew) {
                // modify by zhangrui1 start
                //parameter.setCreateUser(UserUtil.getUserName());
                //parameter.setCreateDept(UserUtil.getDeptCode());
                //parameter.setCreateTime(new Date());
                if (StringUtil.isEmpty(parameter.getCreateUser())) {
                    parameter.setCreateUser(UserUtil.getUserName());
                }
                if (StringUtil.isEmpty(parameter.getCreateDept())) {
                    parameter.setCreateDept(UserUtil.getDeptCode());
                }
                if (!StringUtil.isNotEmpty(parameter.getCreateTime())) {
                    parameter.setCreateTime(new Date());
                }
                // modify by zhangrui1 end
            }
        }
    }

    private boolean isInsert(String sql) {
        // Modified by JIAOXUJIN on 2020-06-04
        String realSql = StringUtil.trim(sql);
        realSql = StringUtil.removeStartIgnoreCase(realSql, "begin");
        realSql = StringUtil.trim(realSql);

        return StringUtil.startsWithIgnoreCase(realSql, "insert ")
                && StringUtil.containsIgnoreCase(realSql, " into ");
        // Commented by JIAOXUJIN on 2020-06-02
                //&& StringUtil.containsIgnoreCase(sql, " values ")
    }

    private boolean isUpdate(String sql) {
        return StringUtil.containsIgnoreCase(sql, "update ")
                && StringUtil.containsIgnoreCase(sql, " set ");
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
