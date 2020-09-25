package com.jixu.msg.service.config;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.BankCfgService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.PortfolioHdr;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class BankAccountMsg extends MsgExecuteService{
	
	@Resource(name="bankCfgService")
	private BankCfgService bankCfgService;
	
	@Resource(name="bkCashAcctDao")
	private  BkCashAcctDao bkDao;

	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		String port=getFieldValue(fieldsHM, MsgFields.portnum);
		
		
		int accID=0;
		BkCashAcct oldBkAcct=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			accID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
			oldBkAcct=bankCfgService.loadAcctDetail(accID);
		}
		
		if(operation.toUpperCase().equals(update)&&!fieldsHM.containsKey(MsgFields.id)){
			
			String bankCode=getFieldValue(fieldsHM, "code");
			oldBkAcct=bkDao.loadBankAcctByBankSeatNumber(bankCode);
			if(oldBkAcct!=null) {
				PortfolioHdr pdr=portfolioHdrDao.loadPortfolioByPortCode(port);;
				oldBkAcct.setPortfolio(pdr);
			}
			bkCashAcctDao.updateEntity(oldBkAcct);
			msgcontainer.setObjID(oldBkAcct.getId());
			msgcontainer.setEntityObj(oldBkAcct);
			return;
		}
		
		
		String acctName=getFieldValue(fieldsHM, "acct");
		
		String desc=getFieldValue(fieldsHM, "desc");
		
		String bank=getFieldValue(fieldsHM, "bank");
		
		String code=getFieldValue(fieldsHM, "code");
		
		String currencyID=getFieldValue(fieldsHM,  "currency");
		getCurrency(currencyID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String status=getFieldValue(fieldsHM, "status");
		
		String settlement=getFieldValue(fieldsHM, "settlement");
		
		String interest=getFieldValue(fieldsHM, "interest");
		
		
		BkCashAcct BkAcct=new BkCashAcct();
		BkAcct.setExternalacctnumber(acctName);
		BkAcct.setBankdesc(desc);
		BkAcct.setBkinfo(new BkInfo(bank));
		BkAcct.setSttlmntcurrdefault("1");
		BkAcct.setSeatNumber(code);
		BkAcct.setFxcurrency(currencyID);
		BkAcct.setStatus(Integer.valueOf(status));
		BkAcct.setSttlmntcurrdefault(settlement);
		BkAcct.setPortfolio(new PortfolioHdr(port));
		BkAcct.setIntCshflwMasterRec(Integer.valueOf(interest));
		
		if(operation.toUpperCase().equals(add)){
			ResultContainer resultContainer=bankCfgService.createBankAcct(BkAcct);
			commService.copyResult2MsgContainer(resultContainer, msgcontainer);
			if(msgcontainer.isCorrectFlag()){
				msgcontainer.setObjID(BkAcct.getId());
				msgcontainer.setEntityObj(BkAcct);
			}
		}else{
			oldBkAcct.setExternalacctnumber(BkAcct.getExternalacctnumber());
			oldBkAcct.setBankdesc(BkAcct.getBankdesc());
			oldBkAcct.setBkinfo(BkAcct.getBkinfo());
			oldBkAcct.setSttlmntcurrdefault(BkAcct.getSttlmntcurrdefault());
			oldBkAcct.setSeatNumber(BkAcct.getSeatNumber());
			oldBkAcct.setFxcurrency(BkAcct.getFxcurrency());
			oldBkAcct.setStatus(BkAcct.getStatus());
			oldBkAcct.setPortfolio(BkAcct.getPortfolio());
			oldBkAcct.setIntCshflwMasterRec(BkAcct.getIntCshflwMasterRec());
			bkCashAcctDao.updateEntity(oldBkAcct);
			msgcontainer.setObjID(oldBkAcct.getId());
			msgcontainer.setEntityObj(oldBkAcct);
		}
	}

	@Override
	public String messageForm() {
		return "/forms/config/bankAccount_add_form";
	}
	
	public String messageName(){
		return "银行账户操作";
	}
	
}
