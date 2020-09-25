package com.jixu.msg.service.info;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Nav;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssembleCollat
 * @author  		Pan Wei 
 * @Date 			2016年8月22日 下午3:28:42 	
 * @Description		[]  
 */

@Service("AssembleJournal")
public class AssembleJournal extends MsgExecuteService{

	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Autowired
	private NavDao navDao;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Journal_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();

		String header=getFieldValue(fieldsHM, MsgFields.header);
		String actionType=getFieldValue(fieldsHM, MsgFields.operation);
		String vouchernumStr=getFieldValue(fieldsHM, MsgFields.vouchernum);
		
		String vouchernum=vouchernumStr;
		
		//如果有vouchernum字段，删除特殊处理
		if(!vouchernum.equals("") && actionType.equalsIgnoreCase(delete) || !vouchernum.equals("") && actionType.equalsIgnoreCase(update)){
			Voucher voucher=(Voucher)voucherDao.loadEntity(Voucher.class, vouchernum);
			if(voucher == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,header,vouchernumStr));
			}else if(voucher.getJournalSourceCode()!=JournalTypes.MANNUAL){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error20,header,"只允许删除手工分录"));
			}else{
				String portnum=voucher.getPortfolio();
				Date ledgerDate=voucher.getLedgerDate();
				Nav nav=navDao.loadNAVByDate(portnum, ledgerDate);
				if(nav!=null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg("当天净值已生成,不允许删除该凭证",header,vouchernumStr));
				}else{
					voucherDetailsDao.deleteVoucherDetailsByVoucherNum(vouchernum);
					voucherDao.deleteEntity(voucher);					
				}
			}
			if(actionType.equalsIgnoreCase(delete)){
				return;
			}
		}
		return;
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "table";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "凭证详情";
	}
}
