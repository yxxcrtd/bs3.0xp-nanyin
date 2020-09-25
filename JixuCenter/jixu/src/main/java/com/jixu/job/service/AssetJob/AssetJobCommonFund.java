package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobCommonStock
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description []
 */

@Service("AssetJobCommonFund")
public class AssetJobCommonFund extends AssetJobBasic {

	@Resource(name="assetJobCommonStock")
	private AssetJobService jobService; 
	
	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj costWrap, Date ledgerDate) throws JNLException {
		return jobService.exeGeneralJob(portfolio, costWrap, ledgerDate);
	}

	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException {
		return null;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec,TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		// TODO Auto-generated method stub
		return clo;
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		return null;
	}
}
