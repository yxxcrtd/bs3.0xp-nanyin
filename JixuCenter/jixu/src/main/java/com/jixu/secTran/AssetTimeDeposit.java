package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;
import com.jixu.mvc.control.model.MarketStockDetailModel;

/** 
 * @ClassName 		AssetTimeDeposit
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:03:43 	
 * @Description		[]  
 */

@Service("AssetTimeDeposit")
public class AssetTimeDeposit extends AssetSecurity{
	
	@Resource(name="AssetJobTimeDeposit")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.TimeDeposit;
	}
	
	@Override
	public String assetDesc() {
		return "定期存款";
	}

	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/time_deposit";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		String bkID=smfFields[0];
		String bkName=smfFields[1];
		String smfType=smfFields[2];
		String issuerCode=smfFields[3].split("\\|")[0];
		String countryCode=smfFields[4];
		//------------------------------------------------------
		String priCurCode=smfFields[5];
		String incomeCurCode=smfFields[6];
		String coaGroupCode=smfFields[7];
		String assetType=smfFields[8];
		//------------------------------------------------------
		String sourceCode=smfFields[11];
		//------------------------------------------------------
		String cashFlowCfgID=smfFields[12];
		
		MarketStockDetailModel mm=new MarketStockDetailModel();
		mm.setSecID(bkID);
		mm.setSecName(bkName);
		mm.setSecType(smfType);
		mm.setIssuerCode(issuerCode);
		mm.setCountryID(countryCode);
		mm.setPrinCurID(priCurCode);
		mm.setIncomeCurID(incomeCurCode);
		mm.setCoaNum(Integer.valueOf(coaGroupCode));
		mm.setAssetType3(Integer.valueOf(assetType));
		mm.setSourceID(Integer.valueOf(sourceCode));
		mm.setCashFlowCfgID(cashFlowCfgID);
		
		String msg=genSMFMsg(action,mm);
		
		return msg;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		// TODO Auto-generated method stub
		return null;
	}
}
