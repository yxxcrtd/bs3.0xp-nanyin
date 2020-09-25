package com.jixu.common.tool;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.jixu.common.type.DataIndexSequenceType;
import com.jixu.common.util.DBTypeSelect;
import com.jixu.common.util.DateUtil;

/**
 * @ClassName GenerateMyID
 * @author Pan Wei
 * @Date 2018年3月13日 上午10:42:34
 * @Description []
 * @return
 */

public class GenCustomCharIDByDate implements Configurable, IdentifierGenerator {

	private static HashMap<String, DataIndexSequenceType> seqMap;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor arg0,
			Object object) throws HibernateException {
		// 日期
        String date=DateUtil.convertD2YYYYMMDD(new Date());
        date=date.substring(2, 8);
		
		String curClassName = object.getClass().getName();
		DataIndexSequenceType seqobj=seqMap.get(curClassName);
		String sql = "select "+seqobj.getSeq_name()+".nextval from dual";
		if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2))
			sql = "select next value for " + seqobj.getSeq_name() + " from Sysibm.sysdummy1";
			
		NativeQuery query = arg0.getFactory().getCurrentSession().createSQLQuery(sql.toString());
		Object obj=query.uniqueResult();
		long curID=Long.parseLong(obj.toString());
		String formatter = "%0" + 4 + "d";
		String curIDStr=String.format(formatter, curID);
		
		String sequenceResult=date+String.valueOf(curIDStr);
		return sequenceResult;
	}
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		if (seqMap == null) {
			seqMap = new HashMap<String, DataIndexSequenceType>();
		}
		
		String className = params.get("entity_name").toString();
		
		if(!seqMap.containsKey(className)){
			String seq_name = params.get("seq_name").toString();
			DataIndexSequenceType seqType=new DataIndexSequenceType();
			seqType.setSeq_name(seq_name);
			
			seqMap.put(className, seqType);
		}
		
	}
}
