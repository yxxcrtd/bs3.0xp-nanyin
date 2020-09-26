package com.joyintech.tams.svreport.interf.chb.conf;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Component;

import com.joyintech.framework.config.ConnectionPoolProperties;
import com.joyintech.framework.jdbc.datasource.DatabaseInfo;
import com.joyintech.framework.jdbc.datasource.DatabaseType;
import com.joyintech.framework.jdbc.datasource.DatabaseTypeHelper;
import com.joyintech.framework.jdbc.helper.DataSourceAppender;
import com.joyintech.framework.util.StringUtil;

/**
 * @author zhangzhongliang
 * @version 1.0 2019年2月18日
 * @see InterfDataSourceAppender
 * @since 1.0
 */
@Component
public class InterfDataSourceAppender extends DataSourceAppender {

	@ConfigurationProperties(prefix = "interf.datasource")
	class InterfDataSourceProperties extends ConnectionPoolProperties {

	}

	@EnableConfigurationProperties(InterfDataSourceProperties.class)
	class InterfDataSource {
		/**
		 * @return DataSource
		 * @throws SQLException
		 */
		@Bean(name = "interfDataSource", destroyMethod = "close")
		public DataSource dataSource(InterfDataSourceProperties properties) throws SQLException {
			DataSource dataSource = null;
			if (StringUtil.isNotEmpty(properties.getJndiName())) {
				JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
				dataSourceLookup.setResourceRef(true);
				try {
					dataSource = dataSourceLookup.getDataSource(properties.getJndiName());
				} catch (DataSourceLookupFailureException e) {
					logger.error("无法获取JNDI数据源：" + properties.getJndiName(), e);
					throw e;
				}
			} else {
				dataSource = properties.initializeDataSourceBuilder().build();
			}

			if (dataSource instanceof BasicDataSource) {
				DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
				String validationQuery = databaseDriver.getValidationQuery();
				if (validationQuery != null) {
					((BasicDataSource) dataSource).setTestOnBorrow(true);
					((BasicDataSource) dataSource).setValidationQuery(validationQuery);
					bindPropertiesForBasicDataSource((BasicDataSource) dataSource, properties);
				}
			}
			initDataSource("interfDataSource", dataSource);
			return dataSource;
		}
	}

}
