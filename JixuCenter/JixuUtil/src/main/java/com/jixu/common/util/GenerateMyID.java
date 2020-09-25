package com.jixu.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.jixu.common.type.IDGenerateType;

/**
 * @ClassName GenerateMyID
 * @author Pan Wei
 * @Date 2018年3月13日 上午10:42:34
 * @Description []
 * @return
 */

public class GenerateMyID implements Configurable, IdentifierGenerator {

	private HashMap<String, IDGenerateType> idHM;

	@Override
	public Serializable generate(SharedSessionContractImplementor session,
			Object object) throws HibernateException {
		String curClassName = object.getClass().getName();
		IDGenerateType idGen = idHM.get(curClassName);
		int idValue=0;
		if (idGen != null) {
			if(idGen.getLastID()>0){
				//如果已经计算过lastID,内存里已经有了lastid值,直接按照步长累加就可以了。
				idValue=idGen.getLastID()+idGen.getIdLength();
			}else{
				//如果lastid=0，则去数据库去最大的id值
				StringBuffer sql = new StringBuffer("select max(").append(idGen.getIdName()).append(") from ").append(idGen.getTableName());
				Query query = session.getFactory().getCurrentSession().createSQLQuery(sql.toString());
				Object obj=query.list().get(0);
				int curID=0;
				if(obj!=null)
					curID=(Integer)obj;
				
				//获取了数据库里最大的id值
				if(curID<idGen.getIdInitValue()){
					idValue=idGen.getIdInitValue();
				}else{
					int diff=(curID-idGen.getIdInitValue())/idGen.getIdLength();
					idValue=idGen.getIdInitValue()+idGen.getIdLength()*(diff+1);;
				}
			}
			idGen.setLastID(idValue);
		}
		return idValue;
	}
	
	@Override
	public void configure(Type type, Properties params,
			ServiceRegistry serviceRegistry) throws MappingException {
		if (idHM == null) {
			idHM = new HashMap<String, IDGenerateType>();
		}
		int idInitValue = Integer.valueOf(params.get("idInitValue").toString());
		int idLength = Integer.valueOf(params.get("idLength").toString());
		String tableName = params.get("identity_tables").toString();
		String idName = params.get("target_column").toString();
		String className = params.get("entity_name").toString();

		IDGenerateType idType = new IDGenerateType();
		idType.setIdInitValue(idInitValue);
		idType.setIdLength(idLength);
		idType.setClassName(className);
		idType.setIdName(idName);
		idType.setTableName(tableName);

		idHM.put(className, idType);
	}
}
