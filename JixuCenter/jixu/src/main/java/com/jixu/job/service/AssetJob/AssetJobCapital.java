package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DateUtil;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobCommonStock
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:35:13 	
 * @Description		[]  
 */

@Service("assetJobCapital")
public class AssetJobCapital extends AssetJobBasic{
	
	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException {
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
		return clo;
	}
	
	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		ResultContainer resultcontainer=new ResultContainer();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		if(portfolio.isSyzfeFlag()){
			String chartnum=portfolio.getCoaahdr();
			int groupnum=portfolio.getCoaaGroup().getGroupNum();
			String acct=jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_Div2Share);
			double diff=-1*glCloseDao.getCloseBalance(portnum, portfolio.getLastTranClose(), acct);
			
			if(diff!=0){
				HashMap<String, Object> div2ShareMsg=new HashMap<String, Object>();
				div2ShareMsg.put(MsgFields.header, "tranShareDiv2ShareMsg");
				div2ShareMsg.put(MsgFields.operation, "add");
				div2ShareMsg.put(MsgFields.tanum, "");
				div2ShareMsg.put(MsgFields.tradeDate, DateUtil.convertD2YYYY_MM_DD(portfolio.getNextTranClose()));
				div2ShareMsg.put(MsgFields.portnum, String.valueOf(portnum));
				div2ShareMsg.put(MsgFields.shares, String.valueOf(diff));
				
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setFieldsHM(div2ShareMsg);
				msgContainer.setUserID(userID);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				resultcontainer=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, resultcontainer);
				
				if(!resultcontainer.isCorrectFlag()){
					return resultcontainer;
				}
			}
		}
		return resultcontainer;
	}
}
