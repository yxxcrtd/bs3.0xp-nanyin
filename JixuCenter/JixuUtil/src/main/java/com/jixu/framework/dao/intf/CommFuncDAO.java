package com.jixu.framework.dao.intf;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.BasicEntity;


public interface CommFuncDAO {
	
	
	/**
	 * 保存实例数据
	 * @param saveEntity	实例数据
	 */
	public <T extends BasicEntity> void saveEntity(T entity);
	
	public void saveEntityBean(Object entity);
	
	/**
	 * 报错所有实例
	 * @param entityList 实例数据list
	 */
	public <T extends BasicEntity> void saveAllEntity(List<T> entityList);
	
	/**
	 * 删除实例
	 * @param deleteEntity 实例
	 */
	public void deleteEntity(Object deleteEntity);
	
	/**
	 * 删除实例
	 * @param deleteEntity	实例类型
	 * @param id	实例主键编号
	 */
	public void deleteEntity(Object deleteEntity, @Param("id")int id);
	
	/**
	 * 更新实例
	 * @param updateEntity 实例数据
	 */
	public <T extends BasicEntity> void updateEntity(T entity);
	
	public void updateEntityBean(Object updateEntity);
	
	/**
	 * 根据主键获取某个实例
	 * @param obj	实例
	 * @param id	实例主键ID
	 * @return	实例
	 */
	public <T> T loadEntity(Class<T> obj, @Param("id")Serializable id);
	
	/**
	 * 获取表某张表所有记录
	 * @param obj	orm表映射的实例
	 * @return	该表的所有数据
	 */
	public <T> List<T> loadEntityAll(Class<T> obj);
	
	/**
	 * 如果sql的查询字段和bean的字段相匹配,则把查询结果set到bean里面。注意:是sql语句的查询结果字段和bean的变量名相同,区分大小写
	 * 比如select amount,xxx as units, xxx.price from 这句sql语句, amount,units,price会被作为返回bean的变量字段存入。不区分大小写
	 * @param sql
	 * @param params
	 * @param values
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> executeSQLReturnBean(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass);
	
	/**
	 * 如果sql的查询字段和bean的字段相匹配,则把查询结果set到bean里面。注意:是sql语句的查询结果字段和bean的变量名相同,区分大小写
	 * 比如select amount,xxx as units, xxx.price from 这句sql语句, amount,units,price会被作为返回bean的变量字段存入。不区分大小写
	 * @param sql
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> executeSQLReturnBean(final String sql, final Class<T> entityClass);
	
	public int executeSQLInsert(String sql);
	
	public void executeSQLUpdate(String sql);
	
	public Connection getConnection();
	
	public void executeSQLdelete(String sql, String[] params, Object[] values);
	
	public int executeSQLInsert(String sql, String[] params, Object[] values);
	
	public <T> List<T> executeSQL(final String sql, final Class<T> entityClass);
	
	public <T> List<T> executeSQL(final String sql, final String[] params, final Object[] values, final Class<T> entityClass) ;

	/**
	 * 根据表名获取字段名
	 * @param tablename
	 * @return
	 */
	public List<String> getColumnNames(String tablename); 
	
	public <T extends BasicEntity> void mergeEntity(T entity);
}
