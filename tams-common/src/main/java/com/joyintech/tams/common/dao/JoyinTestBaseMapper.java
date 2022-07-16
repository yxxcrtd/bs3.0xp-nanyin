package com.joyintech.tams.common.dao;
/**
 * 
 */


import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.sql.Connection;

/**
 * @author 王波
 * @date 2022-03-04
 * @see JoyinTestBaseMapper
 * @since 1.0
 */
public class JoyinTestBaseMapper {
    private static Logger logger = LoggerFactory.getLogger(JoyinTestBaseMapper.class);
    /**
     * 数据库连接
     */
    private static SqlSession sqlSession;
    /**
     * 执行
     */
    private static Executor executor;
    /**
     * 配置
     */
    private static Configuration configuration;

    static {
        try {
            //定义一个配置
            configuration = new Configuration();
            configuration.setCacheEnabled(false);
            configuration.setLazyLoadingEnabled(false);
            configuration.setAggressiveLazyLoading(true);
            configuration.setDefaultStatementTimeout(20);
            configuration.setLogImpl(Slf4jImpl.class);
            configuration.addInterceptor(new JoyinSqlLogInterceptor());
            //读取测试环境数据库配置
            PropertySource propertySource = new ResourcePropertySource(new ClassPathResource("jdbc.properties"));
            //设置数据库链接
            UnpooledDataSource dataSource = new UnpooledDataSource();
            dataSource.setDriver(propertySource.getProperty("driverClass").toString());
            dataSource.setUrl(propertySource.getProperty("jdbcUrl").toString());
            dataSource.setUsername(propertySource.getProperty("username").toString());
            dataSource.setPassword(propertySource.getProperty("password").toString());
            //设置事务(测试设置事务不提交false)
            Transaction transaction = new JdbcTransaction(dataSource, TransactionIsolationLevel.READ_COMMITTED, false);
            //设置执行
            executor = configuration.newExecutor(transaction);
            //解析mapper文件

            //直接实例化一个默认的sqlSession
            //是做单元测试，那么没必要通过SqlSessionFactoryBuilder构造SqlSessionFactory,再来获取SqlSession
            sqlSession = new DefaultSqlSession(configuration, executor, false);
            Connection connection = sqlSession.getConnection();
        } catch (Exception e) {
            logger.error("初始化失败:", e);
        }
    }

    public static <T> T getMapper(String mapperName, Class<T> mapperClass) {
        try {
            Resource mapperResource = new ClassPathResource(mapperName);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperResource.getInputStream(), configuration, mapperResource.toString(), configuration.getSqlFragments());
            xmlMapperBuilder.parse();
            //将接口实例化成对象
//            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            MapperProxyFactory<T> mapperProxyFactory = new MapperProxyFactory<>(mapperClass);
            T mapper = mapperProxyFactory.newInstance(sqlSession);
            return mapper;
        } catch (Exception e) {
            logger.error("获取Mapper失败：", e);
        }
        return null;
    }

    public static void commit() {
        sqlSession.commit();
    }

    public static void close() {
        sqlSession.clearCache();
        sqlSession.close();
    }

    public static SqlSession getSqlSession() {
        return sqlSession;
    }
}