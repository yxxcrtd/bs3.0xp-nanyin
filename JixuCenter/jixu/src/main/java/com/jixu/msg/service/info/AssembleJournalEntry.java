package com.jixu.msg.service.info;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.model.LedgerEntryModel;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Coaa;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

/** 
 * @ClassName 		AssembleCollat
 * @author  		Pan Wei 
 * @Date 			2016年8月22日 下午3:28:42 	
 * @Description		[]  
 */

@Service("journalEntry")
public class AssembleJournalEntry extends MsgExecuteService{
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.JournalEntry_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		String header=msgcontainer.getHeader();
		List<String> fields = msgcontainer.getFields();

		String actionType=fields.get(0);
		
		int vouchernum=0;
		if(!fields.get(1).equals(""))
			vouchernum=Integer.valueOf(fields.get(1));

		//如果有vouchernum字段，删除特殊处理
		if(vouchernum>0 && actionType.equalsIgnoreCase(delete) || vouchernum>0 && actionType.equalsIgnoreCase(update)){
			Voucher voucher=(Voucher)voucherDao.loadEntity(Voucher.class, vouchernum);
			if(voucher == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,header,fields));
			}else{
				voucherDao.deleteEntity(voucher);
			}
			if(actionType.equalsIgnoreCase(delete)){
				return;
			}
		}
		
		genMsgContainer(MsgTemplate.Portfolio_Header,fields.get(2),msgcontainer);
		PortfolioHdr portfolio=null;
		if(msgcontainer.isCorrectFlag()){
			portfolio=(PortfolioHdr)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		//OPERATION,VOUCHERNUM,PORTFOLIO(),LedgerDate,JournalSourceCode,LEDGERENTRY(),Description
		String ledgerDateStr=fields.get(3);
		Date ledgerDate=checkDateFormat(msgcontainer,header,ledgerDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		int journalSourceCode=Integer.valueOf(fields.get(4));
		
		genMsgContainer(MsgTemplate.LedgerEntry_Header,fields.get(5),msgcontainer);
		List<LedgerEntryModel> ledgerList=null;
		if(msgcontainer.isCorrectFlag()){
			ledgerList=(List<LedgerEntryModel>)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		String desc=fields.get(6);

		for(LedgerEntryModel ledger:ledgerList){
			Coaa coa=coaaDao.loadCoa(portfolio.getCoaahdr(), ledger.getGlacct());
			if(coa==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorCode(ReturnResult.coa_notexist);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.coa_notexist)+": "+ledger.getGlacct());
				return;
			}
		}

		String username=JixuUserUtil.getLoginUserName();
		
		if(actionType.equalsIgnoreCase(add)){
			Voucher voucher=new Voucher();
			
			voucher.setPortfolio(portfolio.getId());
			voucher.setTradeDate(ledgerDate);
			voucher.setSettlementDate(ledgerDate);
			voucher.setLedgerDate(ledgerDate);
			voucher.setManagerGroupCode(portfolio.getManagerGroup());
			voucher.setJournalSourceCode(journalSourceCode);
			voucher.setStampUser(username); //to-do-pending
			voucher.setStampDate(null);//to-do-pending
			voucher.setAffirmUser(username);//to-do-pending
			voucher.setAffirmDate(null); //to-do-pending
			voucher.setAuditUser(username);//to-do-pending
			voucher.setAuditDate(null);//to-do-pending
			voucher.setImportedUser(username);//to-do-pending
			voucher.setImportedDate(new Date());
			voucher.setIsAudit(0);
			voucher.setIsAuditMsgValid(0);
			voucher.setDescription(desc);
			voucher.setAuditMsg(null);
			
			voucherDao.saveEntity(voucher);
	
			for(int i=0;i<ledgerList.size();i++){
				LedgerEntryModel ledger=ledgerList.get(i);
				VoucherDetails vd=new VoucherDetails();
				vd.setVoucher(voucher);
				vd.setItemNum(String.valueOf(i+1));
				vd.setChartNum(portfolio.getCoaahdr()); 
				vd.setIsAdjusted(0);
				vd.setAccountNum(ledger.getGlacct());
				vd.setCr_func(ledger.getCrfunc());
				vd.setDr_func(ledger.getDrfunc());
				vd.setCr_local(ledger.getCrlocal());
				vd.setDr_local(ledger.getDrlocal());
				vd.setLocalCurrency(ledger.getCur());
				vd.setFunctionCurrency(commService.loadFxCurrencyCode(portfolio.getCurID()));
				vd.setFxRate(ledger.getFxrate());
				vd.setPrice(0);
				vd.setAmount(0);
				vd.setCurFxRate(ledger.getFxrate()); 
				
				voucherDetailsDao.saveEntity(vd);
			}
			
			msgcontainer.setEntityID(voucher.getVouchernum());
		}
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
