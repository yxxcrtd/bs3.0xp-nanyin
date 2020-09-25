package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service("TranJobShareCashDiv")
public class TranJobShareCashDiv extends TranJobBasic {

	@Resource(name = "tranJobStockCashDividend")
	private TranJobService tranJob;

	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList,
			TranBasic tranbasic, PortfolioHdr portfolio, String desc,
			Date ledgerDate) throws JNLException {
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc,
				ledgerDate);
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		GLCOOABase pursaleCooa = null;
		if (tranBasic.getTrantype() == TransTypes.Share_Divident) {
			String chartnum = portfolio.getCoaahdr();
			int groupnum = portfolio.getCoaaGroup().getGroupNum();

			List<BkCashRcpy> rcpyList = bkCashRcpyDao.loadRcpyList(tranBasic.getId());
			pursaleCooa = new GLCOOABase();
			for (BkCashRcpy rcpy : rcpyList) {
				if (rcpy.getDbcr().equals(DBCR.CREDIT)) {
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, rcpy.getOrigbal(), rcpy.getFxorigbal(),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDEND_REC));
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, rcpy.getOrigbal(),
							rcpy.getFxorigbal(), jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INC_ACCRUAL));
				} else {
					pursaleCooa = new GLCOOABase();
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, rcpy.getOrigbal(), rcpy.getFxorigbal(),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDENDS_PAYABLE));
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, rcpy.getOrigbal(),
							rcpy.getFxorigbal(), jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INC_ACCRUAL));
				}
			}
		}

		return pursaleCooa;
	}
}
