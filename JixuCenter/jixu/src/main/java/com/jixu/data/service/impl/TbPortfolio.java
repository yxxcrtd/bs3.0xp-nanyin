package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.data.pojo.Portfolio;
import com.orm.obj.PortfolioHdr;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbPortfolio")
public class TbPortfolio extends OutDataOperation {
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		PortfolioHdr portfolio = comService.loadPortfolio(portNum);
		String activeDate=DateUtil.convertD2YYYYMMDD(portfolio.getLastActiveDate());
		String curDate=DateUtil.convertD2YYYYMMDD(new java.util.Date());
		List<Portfolio> spList=null;
		if (activeDate.equals(curDate)) {
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append(" ph.PORTFOLIO as portID,");
			sb.append(" ph.PORT_NAME as portName,");
			sb.append(" ph.SINGLELOT as singleLot,");
			sb.append(" ph.CHARTNUMBER as chartNum,");
			sb.append(" ph.CURRENCYCODE as currencyCode,");
			sb.append(" ph.GLGROUP as glGroup,");
			sb.append(" ph.holidaySD as holidaySD,");
			sb.append(" ph.CLASSID as classID,");
			sb.append(" ph.inceptionDate as inceptionDate,");
			sb.append(" ph.portfolioClient as portfolioClient,");
			sb.append(" ph.portfolioType as portfolioType,");
			sb.append(" ph.shortCode as shortCode,");
			sb.append(" ph.shortName as shortName,");
			sb.append(" ph.province as province,");
			sb.append(" ph.navPrecision as navPrecision,");
			sb.append(" ph.bookNBDays as bookNBDays,");
			sb.append(" ph.lotSelection as lotSelection,");
			sb.append(" ph.wkndValuationClose as wkndValuationClose,");
			sb.append(" ph.TradeSettleLedger as tradeSettleLedger,");
			sb.append(" ph.managerGroup as managerGroup,");
			sb.append(" ph.defaultSecAcct as defaultSecAcct,");
			sb.append(" pc.clientName as clientName,");
			sb.append(" p.proName as proName,");
			sb.append(" fc.CURR_NAME as currencyName,");
			sb.append(" 1 as createMethod,");
			sb.append(" p.country as countryID");
			sb.append(" from portfolio_hdr ph,portfolio_client pc,province p,fx_currency fc");
			sb.append(" where pc.clientid=ph.portfolioClient and ph.province=p.proID and ph.CURRENCYCODE=fc.CURR_ID and ph.PORTFOLIO=:portNum and ph.lastTranClose=:closeDate");
			String[] params = { "portNum", "closeDate" };
			Object[] values = { portNum, closeDate };

			spList = portfolioHdrDao.executeSQLReturnBean(sb.toString(),params, values, Portfolio.class);
		} 
		return spList;
	}
}
