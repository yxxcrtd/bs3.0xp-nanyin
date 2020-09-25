package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.data.pojo.Voucher;
import com.orm.obj.Coaa;


/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbVoucher")
public class TbVoucher extends OutDataOperation {
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	protected VoucherDao voucherDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		StringBuffer sb=new StringBuffer();
		sb.append("select ");
		sb.append(" vd.SUBJECT_NO as accountNum,");
		sb.append(" coa.accntname as accountName,");
		sb.append(" vd.AMOUNT as amount,");
		sb.append(" vd.CHARTNUM as chartNum,");
		sb.append(" vd.CR_FUNC as cr_Func,");
		sb.append(" vd.CR_LOCAL as cr_Local,");
		sb.append(" vd.CURFXRATE as curFxRate,");
		sb.append(" vd.DESCRIPTION as description,");
		sb.append(" vd.DR_FUNC as dr_func,");
		sb.append(" vd.DR_LOCAL as dr_Local,");
		sb.append(" vd.FUNCTIONCURRENCY as functionCurrency,");
		sb.append(" vd.FXRATE as fxRate,");
		sb.append(" vd.ISADJUSTED as isadJusted,");
		sb.append(" vd.ITEMNUM as itemNum,");
		sb.append(" vd.LOCALCURRENCY as localCurrency,");
		sb.append(" vd.PRICE as price,");
		sb.append(" vd.detail_subject_no as securityID,");
		sb.append(" vd.SECURITYTYPECODE as securityTypeCode,");
		sb.append(" v.VOUCHERNUM as voucherNum,");
		sb.append(" v.JOURNALSOURCECODE as journalSourceCode,");
		sb.append(" v.LEDGERDATE as ledgerdate,");
		sb.append(" v.PORTFOLIONUM as portNum ");
		sb.append(" from JXV_voucher v, JXV_voucherdetails vd, JXC_Coaa coa");
		sb.append(" where v.VOUCHERNUM=vd.VOUCH_NUM and vd.chartNum=coa.CHARTNUMBER and vd.SUBJECT_NO=coa.accntnum ");
		sb.append(" and v.PORTFOLIONUM=:portNum and v.LEDGERDATE=:closeDate");
		String[] params={"portNum","closeDate"};
		Object[] values={portNum, closeDate};
		
		List<Voucher> voucherList=voucherDao.executeSQLReturnBean(sb.toString(), params, values, Voucher.class);

		List<Coaa> coaList=coaaDao.getLevel1Coaa(portNum);
		
		for(Voucher voucher:voucherList){
			for(Coaa coa:coaList){
				if(voucher.getAccountNum().startsWith(coa.getAccntnum())){
					voucher.setLevel1AccntNum(coa.getAccntnum());
					voucher.setLevel1AccntName(coa.getAccntname());
					voucher.setAccntCategory(coa.getAccntcategory());
					break;
				}
			}
		}
		return voucherList;
	}
}
