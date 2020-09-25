package com.jixu.common.func;

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
import com.orm.obj.Broker;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.Issuer;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.Voucher;

/**
 * @ClassName GenerateMyID
 * @author Pan Wei
 * @Date 2018年3月13日 上午10:42:34
 * @Description []
 * @return
 */

public class GenCustomTBID_Joyin implements Configurable, IdentifierGenerator {

	private static HashMap<String, DataIndexSequenceType> seqMap;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor arg0,
			Object object) throws HibernateException {
		// 日期
        String date="";
		
		if(object instanceof PortfolioHdr){
			PortfolioHdr portfolio=(PortfolioHdr)object;
			if(portfolio.getId()!=null && !portfolio.getId().equals("")) 
				return portfolio.getId();
		}else if(object instanceof Issuer) {
			Issuer issuer=(Issuer)object;
			if(issuer.getIssuerID()!=null && !issuer.equals(""))
				return issuer.getIssuerID();
		}else if(object instanceof Broker) {
			Broker broker=(Broker)object;
			if(broker.getBrokerID()!=null && !broker.getBrokerID().equals(""))
				return broker.getBrokerID();
		}else if(object instanceof CashFlowCfg) {
			CashFlowCfg cfc=(CashFlowCfg)object;
			if(cfc.getCashFlowID()!=null && !cfc.getCashFlowID().equals("") && !cfc.getCashFlowID().equals("0"))
				return cfc.getCashFlowID();
		}else if(object instanceof SecAcct) {
			SecAcct secacct=(SecAcct)object;
			if(secacct.getAcctCode()!=null && !secacct.getAcctCode().equals(""))
				return secacct.getAcctCode();
		}else if(object instanceof Voucher) {
			Voucher voucher=(Voucher)object;
			date=DateUtil.convertD2YYYYMMDD(voucher.getLedgerDate());
		}else if(object instanceof TranClo) {
			TranClo clo=(TranClo)object;
			date=DateUtil.convertD2YYYYMMDD(clo.getCloseDate());
		}else if(object instanceof TranBasic) {
			TranBasic tran=(TranBasic)object;
			if(tran.getId()!=null && !tran.getId().equals("")) {
				//如果外部系统带进了主键，直接使用
				return tran.getId();
			}
			date=DateUtil.convertD2YYYYMMDD(tran.getTradedate());
		}
		
		if(date.equals("")) {
			date=DateUtil.convertD2YYYYMMDD(new Date());
		}
		
		String curClassName = object.getClass().getName();
		DataIndexSequenceType seqobj=seqMap.get(curClassName);
		String sql = "select "+seqobj.getSeq_name()+".nextval from dual";
		if(DBTypeSelect.curDBType.equals(DBTypeSelect.db2))
			sql = "select next value for " + seqobj.getSeq_name() + " from Sysibm.sysdummy1";
			
		NativeQuery query = arg0.getFactory().getCurrentSession().createSQLQuery(sql.toString());
		Object obj=query.uniqueResult();
		long curID=Long.parseLong(obj.toString());
		
		String sequenceResult="";
		if(seqobj.getShort_name()!=null) {
	        String formatter = "%0" + 8 + "d";
	        sequenceResult = String.format(formatter, curID);
	        // 前缀
	        sequenceResult = seqobj.getShort_name() + date + sequenceResult;
		}else {
			sequenceResult = String.valueOf(curID);
		}
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
			String short_name=null;
			if(params.get("short_name")!=null)
				short_name=params.get("short_name").toString();
			DataIndexSequenceType seqType=new DataIndexSequenceType();
			seqType.setSeq_name(seq_name);
			seqType.setShort_name(short_name);
			
			seqMap.put(className, seqType);
		}
		
	}
}
