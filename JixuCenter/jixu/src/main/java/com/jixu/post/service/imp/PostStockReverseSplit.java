package com.jixu.post.service.imp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.TranCloType;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranSumDao;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

@Service("jixuPostStockReverseSplit")
public class PostStockReverseSplit extends PostServiceImpl {

	@Resource(name = "commfuncService")
	private CommFuncService commFuncService;

	@Autowired
	private TranSumDao tranSumDao;

	@Autowired
	private TranDao tranDao;

	@Override
	@Transactional(value = "jixuTXManager", rollbackFor = Exception.class)
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		// 一次股利的公司行为,会影响所有证券账户下的头寸

		String portnum = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		double divRate = tranAdvice.getAltRate();
		//获取股权登记日
		Date divDate = tranAdvice.getDivBasicDate();

		TranCloType tranCloType = new TranCloType(portnum, cusip, divDate);
		// 查出所有证券账户下的头寸
		List<TranClo> tCloList = secPositionCenter.getSecClo(tranCloType, false);
		// 查询所有tsum记录，包括已经关闭的tsum，股利是根据股权登记日来行使公司行为的
		List<TranSum> tsumList = tranSumDao.loadTSumByCusip(portnum, cusip);
		for (TranClo clo : tCloList) {
			if (clo.getAmount() == 0) {
				continue;
			}

			//零股，暂时约定保留两位
			//头寸数量*比例=最终数量
			//成交数量=头寸数量*比例-头寸数量
			double divunits = RoundUtil.roundMoney2Dec(divRate * clo.getAmount());
			double tranUnits = RoundUtil.roundMoney2Dec(clo.getAmount()-divunits);
			for (TranSum tSum : tsumList) {
				if (tSum.getLotnum() == clo.getLotnum()) {
					// 查询clo和sum相同lot的一笔记录
					Tran tran = new Tran();
					tran.setCusip(tranAdvice.getCusip());
					tran.setPortfolio(tranAdvice.getPortfolio());
					tran.setLotnum(tSum.getLotnum());
					tran.setTrannum(tSum.getLstTrannum() + 1);
					tran.setTrantype(tranAdvice.getTrantype());
					tran.setTanum(tranAdvice);
					tran.setTradeDate(tranAdvice.getTradedate());
					tran.setSettleDate(tranAdvice.getSettledate());
					tran.setLedgerDate(tranAdvice.getLedgerdate());
					tran.setAmount(tranUnits);
					tran.setSecacct(tSum.getSecacct());
					tran.setManagerID(tSum.getManagerID());

					tSum = CaltSum(tran, tSum);

					tranSumDao.updateEntity(tSum);
					tranDao.saveEntity(tran);
				}
			}
		}

		return null;
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}

	@Override
	public void delete(TranBasic tranAdvice, ResultContainer container) {
		// TODO Auto-generated method stub
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		double tsumAmount = RoundUtil.roundMoney2Dec(tSum.getAmount()-tran.getAmount());
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		
		return tSum;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		double tsumAmount = RoundUtil.roundMoney2Dec(tSum.getAmount()+tran.getAmount());
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setAmount(tsumAmount);
		if(tsumAmount==0) {
			tSum.setPosclosed(PostStatusType.sumClosed);
		}
		return tSum;
	}

	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		
	}
}
