package com.jixu.common.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class DAOUtil<T> extends HibernateDaoSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(DAOUtil.class); 
	
	@Resource
  	public void setMySessionFactory(SessionFactory jixuSessionFactory){
		super.setSessionFactory(jixuSessionFactory);
	}
	
	/** */
	/**
	 * 查询整个实例
	 * 
	 * @param sql
	 *            jdbc执行sql语句
	 * @param backClass
	 *            返回实例类型
	 * @return 返回List<实体类型>
	 */
	public <T> List<T> executeSQL(final String sql, final Class<T> entityClass) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = null;
		if (entityClass == null)
			query = session.createSQLQuery(sql);
		else
			query = session.createSQLQuery(sql).addEntity(entityClass);
		return query.list();
	}

	/**
	 * 查询整个实例
	 * 
	 * @param sql
	 *            jdbc执行sql语句
	 * @param backClass
	 *            返回实例类型
	 * @return 返回List<实体类型>
	 */
	public <T> List<T> executeSQL(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = null;
		if (entityClass == null)
			query = session.createSQLQuery(sql);
		else
			query = session.createSQLQuery(sql).addEntity(entityClass);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(params[i], values[i]);
		}
		return query.list();
	}
	
	public Connection getConnection(){
		try {
			return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询整个实例
	 * 
	 * @param sql
	 *            jdbc执行sql语句
	 * @param backClass
	 *            返回实例类型
	 * @return 返回List<实体类型>
	 */
	public Object executeSQLNo1Record(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass) {
		Object obj=getNo1DataBasic(sql, params, values, false, true, entityClass);
		return obj;
	}
	
	/**
	 * 查询整个实例
	 * 
	 * @param sql
	 *            jdbc执行sql语句
	 * @param backClass
	 *            返回实例类型
	 * @return 返回List<实体类型>
	 */
	public List<HashMap<String, Object>> executeSQLMAP(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = null;
		if (entityClass == null)
			query = session.createSQLQuery(sql);
		else
			query = session.createSQLQuery(sql).addEntity(entityClass);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(params[i], values[i]);
		}
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public <T> List<T> executeHQL(String hql, String param, Object value) {
		String[] params = { param };
		Object[] values = { value };
		return executeHQL(hql, params, values);
	}

	public <T> List<T> executeHQL(String hql, String[] params, Object[] values) {
		return (List<T>) this.getHibernateTemplate().findByNamedParam(hql, params, values);
	}

	public int exeFetchCount(String hql, String param, Object value){
		String[] params={param};
		Object[] values={value};
		return exeFetchCount(hql, params, values);
	}
	
	public int exeFetchCount(String hql, String[] params, Object[] values) {
		List<T> countList = (List<T>) this.getHibernateTemplate().findByNamedParam(hql, params, values);
		int countNum = Integer.valueOf(countList.get(0).toString());
		return countNum;
	}

	public <T> List<T> executeHQL(String hql) {
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	public <T> List<T> executeHQL(String hql, Object[] values) {
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}

	public <T> List<T> executeHQL(String hql, Object value) {
		Object[] values = new Object[1];
		values[0] = value;
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}

	public T executeHQLNo1Record(String hql) {
		List<T> returnList = executeHQL(hql);
		if (returnList.size() > 0) {
			return returnList.get(0);
		} else {
			return null;
		}
	}

	public T executeHQLNo1Record(String hql, String[] params, Object[] values) {
		List<T> returnList = executeHQL(hql, params, values);
		if (returnList.size() > 0) {
			return returnList.get(0);
		} else {
			return null;
		}
	}
	
	public T executeHQLNo1Record(String hql, String param, Object value) {
		List<T> returnList = executeHQL(hql, param, value);
		if (returnList.size() > 0) {
			return returnList.get(0);
		} else {
			return null;
		}
	}

	/** */
	/**
	 * 返回某一段数据
	 * 
	 * @param hql
	 *            hql语句
	 * @param firstResult
	 *            从第几条数据取值
	 * @param maxResult
	 *            一共获取几条数据
	 * @param params
	 *            参数字段
	 * @param Value
	 *            参数值
	 * @param entityClass
	 *            实体Bean
	 */
	
	public Pages<T> getPageDataBasic(final String sql, final String[] params,
			final Object[] values, boolean cached, Pages<T> pages, boolean sqlFlag, final Class<T> entityClass) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Query query = null;
		if(sqlFlag){
			String totalsql = "select count(*) from (" + sql+")";
			query = session.createSQLQuery(totalsql);
		}else{
			String totalsql = sql.substring(sql.indexOf("from"), sql.length());
			if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2)) {
				//db2最后有order之后，不能再count()
				int ordersqlpoint=totalsql.toLowerCase().lastIndexOf(" order ");
				int wheresqlpoint=totalsql.toLowerCase().lastIndexOf(" where ");
				int fromsqlpoint=totalsql.toLowerCase().lastIndexOf(" from ");
				if(ordersqlpoint>0 && ordersqlpoint>wheresqlpoint && ordersqlpoint>fromsqlpoint) {
					totalsql=totalsql.substring(0, ordersqlpoint);
				}
			}
			totalsql = "select count(*) as tcount " + totalsql;
			query = session.createQuery(totalsql);
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(params[i], values[i]);
		}

		int count = ((Number) query.uniqueResult()).intValue();
		if (count > 0) {
			pages.setTotalPages((count + pages.getPageMaxRecords() - 1) / pages.getPageMaxRecords());
			pages.setTotalCount(count);
		}
		
		if(sqlFlag){
			if(entityClass==null)
				query = session.createSQLQuery(sql);
			else
				query = session.createSQLQuery(sql).addEntity(entityClass);
		}else{
			query = session.createQuery(sql);
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(params[i], values[i]);
		}
		query.setFirstResult((pages.getCurrentPage() - 1) * pages.getPageMaxRecords());
		query.setMaxResults(pages.getPageMaxRecords());
		List<T> resultList = query.list();
		pages.setResultList(resultList);
		return pages;
	}
	
	/** */
	/**
	 * 返回第一条数据
	 * 
	 * @param hql
	 *            hql语句
	 * @param firstResult
	 *            从第几条数据取值
	 * @param maxResult
	 *            一共获取几条数据
	 * @param params
	 *            参数字段
	 * @param Value
	 *            参数值
	 * @param entityClass
	 *            实体Bean
	 */
	public T getNo1DataBasic(final String sql, final String[] params,
			final Object[] values, boolean cached, boolean sqlFlag, final Class<T> entityClass) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = null;
		if(sqlFlag){
			if(entityClass==null){
				query = session.createSQLQuery(sql);
			}else{
				query = session.createSQLQuery(sql).addEntity(entityClass);
			}
		}else{
			query = session.createQuery(sql);
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(params[i], values[i]);
		}
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<T> resultList = query.list();
		if(resultList!=null && resultList.size()>0){
			return resultList.get(0);
		}else{
			return null;
		}
	}
	
	public Pages<T> getPageHqlData(final String hql, final String[] params,
			final Object[] values, boolean cached, Pages<T> pages) {
		return getPageDataBasic(hql, params, values, cached, pages, false, null);
	}
	
	public Pages<T> getPageHqlData(final String hql, final String param,
			final Object value, boolean cached, Pages<T> pages) {
		String[] params={param};
		Object[] values={value};
		return getPageDataBasic(hql, params, values, cached, pages, false, null);
	}
	
	public Pages<T> getPageSqlData(final String sql, final String[] params,
			final Object[] values, boolean cached, Pages<T> pages, final Class<T> entityClass) {
		return getPageDataBasic(sql, params, values, cached, pages, true, entityClass);
	}
	
	public List<String> getColumnNames(String tablename){
		List<String> heads=new ArrayList<String>();
		Statement stmt= null;
		ResultSet rs = null;
		try{
			stmt=getConnection().createStatement();
			String sql="select * from "+tablename;
			rs =stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData(); //获得结果集结构信息,元数据
			for( int i=1; i<=rsmd.getColumnCount(); i++ ){
				String field = rsmd.getColumnName(i);
				heads.add(field );
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally {
			try {
				if (stmt != null){
					stmt.close();
				}
				if (rs != null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return heads;
	}

	public Pages<T> getPageData(final String hql, boolean cached, Pages<T> pages) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String totalhql = hql.substring(hql.indexOf("from"), hql.length());
		totalhql = "select count(*) " + totalhql;

		Query query = session.createQuery(totalhql);
		int count = ((Number) query.uniqueResult()).intValue();
		if (count > 0) {
			pages.setTotalPages((count + pages.getPageMaxRecords() - 1) / pages.getPageMaxRecords());
		}

		query = session.createQuery(hql);
		query.setFirstResult((pages.getCurrentPage() - 1) * pages.getPageMaxRecords());
		query.setMaxResults(pages.getPageMaxRecords());
		List<T> resultList = query.list();
		pages.setResultList(resultList);
		return pages;
	}

	public void saveEntityBean(Object entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * replicate，通过复制来持久化当前已经脱离session的托管对象，这点上看有点像merge，但是当传入一个临时对象则会报错，而且replicate后的传入的那个对象将是持久的，
	 * 这也是跟merge不同。 
		通过参数ReplicationMode可以控制遇到相同记录时的行为。 
		如果是 ReplicationMode.EXCEPTION,则在复制时如果有重复的行数据，则抛出异常。ReplicationMode.IGNORE则忽略 异常。 
		ReplicationMode.OVERWRITE则会覆盖掉已有行数据。ReplicationMode.LATEST_VERSION则是在有重复时使用最新的版本进行控制。 
		可以看出，replicate在执行复制的时候，比起merge还是有更多可控行为，ReplicationMode.LATEST_VERSION还可以保证并发修改时只更新最新的版本。
	 * @param saveEntity
	 */
	public void replicateEntity(Object entity) {
		this.getHibernateTemplate().replicate(entity, ReplicationMode.EXCEPTION);
	}
	
	public void replicateUpdateEntity(Object entity) {
		this.getHibernateTemplate().replicate(entity, ReplicationMode.OVERWRITE);
	}
	
	/**
	 * merge，从名字也能猜到是合并，跟saveOrUpdate相似，可以持久化托管和临时对象。 
		不同的是：在更新时候，merge将托管对象的属性复制到session中有相同标识符（但不相等）的持久对象（如果不存在，就从db load），也就是先select 后 update；
		倘若找不到相同标识符的对象，则置id为null，保存临时对象。返回的是持久对象，但是merge传入的对象还是托管的。 
		可以看出，只有在session中已经存在一个具有相同标识符的持久对象的时候，应该采用merge，此时用saveOrUpdate会报错。当然如果碰到这类情形，
		将对象属性copy到已经持久化的那个相同标识符对象上也是可以的，正因为如此我一般都没用过merge。再者merge在session中不存在相同标识对象时，会多一条select，
		往往我们并不需要。 
	 * @param saveEntity
	 */
	public void mergeEntityBean(Object entity) {
		this.getHibernateTemplate().merge(entity);
	}

	public void saveOrUpdateEntity(Object entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void updateEntityBean(Object updateEntity) {
		this.getHibernateTemplate().update(updateEntity);
	}

	public void deleteEntity(Object deleteEntity) {
		this.getHibernateTemplate().delete(deleteEntity);
	}

	public void deleteEntity(Object classname, int id) {
		Object classObj = this.loadEntityBean((Class<T>) classname, id);
		if (classObj != null) {
			this.deleteEntity(classObj);
		}
	}

	public <T> T loadEntityBean(Class<T> obj, Serializable id) {
		return this.getHibernateTemplate().get(obj, id);
	}

	public <T> List<T> loadEntityAll(Class<T> obj) {
		return this.getHibernateTemplate().loadAll(obj);
	}
	
	/**
	 * 如果sql的查询字段和bean的字段相匹配,则把查询结果set到bean里面。注意:是sql语句的查询结果字段和bean的变量名相同,区分大小写
	 * 比如select amount,xxx as units, xxx.price from 这句sql语句, amount,units,price会被作为返回bean的变量字段存入。不区分大小写
	 * @param sql
	 * @param params
	 * @param values
	 * @param entityClass
	 * @return
	 */
	public List<Object> executeSQLReturnBean(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass){
		List<Object> beanList=new ArrayList<Object>();
		List<HashMap<String, Object>> results=this.executeSQLMAP(sql, params, values, null);
		for(HashMap<String, Object> beanHM:results){
			try {
				Object bean = getObject(beanHM, entityClass);
				beanList.add(bean);
			} catch (IllegalAccessException e) {
				logger.error("Error", e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				logger.error("Error", e);
			}
		}
		
		return beanList;
	}
	
	/**
	 * 如果sql的查询字段和bean的字段相匹配,则把查询结果set到bean里面。注意:是sql语句的查询结果字段和bean的变量名相同,区分大小写
	 * 比如select amount,xxx as units, xxx.price from 这句sql语句, amount,units,price会被作为返回bean的变量字段存入。不区分大小写
	 * @param sql
	 * @param entityClass
	 * @return
	 */
	public List<Object> executeSQLReturnBean(final String sql, final Class<T> entityClass){
		List<Object> beanList=new ArrayList<Object>();
		String[] params={};
		Object[] values={};
		List<HashMap<String, Object>> results=this.executeSQLMAP(sql, params, values, null);
		for(HashMap<String, Object> beanHM:results){
			try {
				Object bean = getObject(beanHM, entityClass);
				beanList.add(bean);
			} catch (IllegalAccessException e) {
				logger.error("Error", e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				logger.error("Error", e);
			}
		}
		
		return beanList;
	}
	
	private Object getObject(HashMap<String, Object> fieldsHM, Class cls)
			throws IllegalAccessException, InstantiationException {
		Object object = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for(Field field:fields){
			String fieldName=field.getName();
			for (String keyName : fieldsHM.keySet()) {
				if(keyName.equalsIgnoreCase(fieldName)){
					field.setAccessible(true);
					setFieldValue(object, fieldsHM.get(keyName), field);
				}
			}
		}
		return object;
	}
	
	public List<Object> executeSQLOrderReturnBean(final String sql, final String[] params,
			final Object[] values, final Class<T> entityClass){
		List<Object> beanList=new ArrayList<Object>();
		List<T> results=this.executeSQL(sql, params, values, null);
		for(int i=0;i<results.size();i++){
			Object[] objArr=(Object[])results.get(i);
			try {
				Object bean = getObject(objArr, entityClass);
				beanList.add(bean);
			} catch (IllegalAccessException e) {
				logger.error("Error", e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				logger.error("Error", e);
			}
		}
		
		return beanList;
	}

	private Object getObject(Object[] results, Class cls)
			throws IllegalAccessException, InstantiationException {
		Object object = null;
		Field[] fields = cls.getDeclaredFields();
		int columnCount = results.length;
		for (int i = 0; i < columnCount; i++) {
			Field field = fields[i+2];
			if (field != null) {
				if (object == null) {
					object = cls.newInstance();
				}
				field.setAccessible(true);
				setFieldValue(object, results[i], field);
			}
		}
		return object;
	}

	private static void setFieldValue(Object obj, Object value, Field field)
			throws IllegalArgumentException, IllegalAccessException {
		if (value == null) {
			return;
		}
		if(field.getType()==int.class){
			field.set(obj, Integer.valueOf(value.toString()));
		}else if(field.getType()==double.class){
			field.set(obj, Double.valueOf(value.toString()));
		}else if(field.getType()==java.sql.Date.class){
			field.set(obj, DateUtil.ConvertUtil2Sql(DateUtil.convertStr2Date2(value.toString())));
		}else{
			field.set(obj, value);
		}
	}
	
	public void deleteHql(String hql, String[] params, Object[] values){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql);
		
		for(int i=0;i<params.length;i++){
			query.setParameter(params[i], values[i]);
		}
		query.executeUpdate();
	}
	
	public void deleteHql(String hql, String param, Object value){
		String[] params={param};
		Object[] values={value};
		deleteHql(hql, params, values);
	}
	
	public void executeSQLupdate(String sql, String[] params, Object[] values){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			query.setParameter(params[i], values[i]);
		}
		query.executeUpdate();
	}
	
	public void executeSQLdelete(String sql, String[] params, Object[] values){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createSQLQuery(sql);
		for(int i=0;i<params.length;i++){
			query.setParameter(params[i], values[i]);
		}
		query.executeUpdate();
	}
	
	public int executeSQLInsert(String sql, String[] params, Object[] values) {
		Object result=getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

				SQLQuery query = session.createSQLQuery(sql);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(params[i], values[i]);
					}
				}
				int result = query.executeUpdate();
				return Integer.valueOf(result);
			}
		});
		return Integer.parseInt(result.toString());
	}
	
	public int executeSQLInsert(String sql){
		Object result=getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

				SQLQuery query = session.createSQLQuery(sql);
				int result = query.executeUpdate();
				return Integer.valueOf(result);
			}
		});
		return Integer.parseInt(result.toString());
	}
	
	public void executeSQLUpdate(String sql){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	public void executeSQLdelete(String sql){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	public void executeHQLupdate(String hql, Object[] values){
		this.getHibernateTemplate().bulkUpdate(hql, values);
	}
}
