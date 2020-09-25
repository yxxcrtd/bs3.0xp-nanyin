package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.ResultContainer;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobSpotCurrency
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:35:13 	
 * @Description		[外汇即期]  
 */

@Service("assetJobSpotCurrency")
public class AssetJobSpotCurrency extends AssetJobBasic{
	
	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap,Date ledgerDate) throws JNLException {
		return null;
	}
	
	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResultContainer autoJob(int userID, String portnum, java.util.Date ledgerDate) throws JNLException {
		return null;
	}
}
