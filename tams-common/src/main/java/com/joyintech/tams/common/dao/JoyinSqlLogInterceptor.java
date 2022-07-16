/**
 * 
 */
package com.joyintech.tams.common.dao;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.jdbc.plugin.MybatisInterceptor;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class JoyinSqlLogInterceptor implements MybatisInterceptor {
    private final Logger logger = LoggerFactory.getLogger(JoyinSqlLogInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject instanceof BaseEntity
                && StringUtil.containsIgnoreCase(boundSql.getSql(), "update ")
                && StringUtil.containsIgnoreCase(boundSql.getSql(), " set ")) {
            BeanUtil.setProperty(parameterObject, "updateTime", new Date());
        }

        Configuration configuration = mappedStatement.getConfiguration();
        String method = invocation.getMethod().getName();
        String sql = showSql(configuration, boundSql);
        String opttype = "查询数据记录";

        if (sql != null && StringUtil.equals(method, "update")) {
                String tmpSql = sql.toLowerCase();
                if (StringUtil.containsIgnoreCase(tmpSql, "update ") && StringUtil.containsIgnoreCase(tmpSql, " set ")) {
                    opttype = "更新数据记录";

                } else if (StringUtil.containsIgnoreCase(tmpSql, "insert ") && StringUtil.containsIgnoreCase(tmpSql, " into ") && StringUtil.containsIgnoreCase(tmpSql, "values")) {
                    opttype = "插入数据记录";

                } else if (StringUtil.containsIgnoreCase(tmpSql, "delete ") && StringUtil.containsIgnoreCase(tmpSql, " from ")) {
                    opttype = "删除数据记录";

                }
            }
        long start = System.currentTimeMillis();
        try {
                return invocation.proceed();
            }
        catch (Throwable e) {
            long time = (System.currentTimeMillis() - start);
            logger.error("\n\tID [" + mappedStatement.getId() + "]\n\tSQL：[" + sql + "]\n\t 执行时间：[" + time + "毫秒]\n====================");
            throw e;
        }
        finally {
            long time = (System.currentTimeMillis() - start);

            logger.info("\n\tID [" + mappedStatement.getId() + "]\n\tSQL：[" + sql + "]\n\t 执行时间：[" + time + "毫秒]\n====================");
//
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public int getOrder() {
        return 0;
    }

    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            }
            else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                    else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        }
        else if (obj instanceof Date) {
            value = "'" + DateUtil.format((Date)obj) + "'";
        }
        else if (obj != null) {
            value = obj.toString();
        }
        else {
            value = "";
        }
        // 处理特殊字符$, 会引发正则group引用
        if (value.contains("$")) {
            StringBuilder sb = new StringBuilder();
            char[] chs = value.toCharArray();
            for (char ch : chs) {
                if (ch == '$') {
                    sb.append("\\");
                }
                sb.append(ch);
            }
            value = sb.toString();
        }

        return value;
    }
}