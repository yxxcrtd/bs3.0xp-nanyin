package com.jixu.job.service.CorpAction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CorpActionTypes;
import com.jixu.common.types.TranCloType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.job.service.SmfDivService;
import com.jixu.msg.dto.StockDividendDto;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.orm.obj.IssueInfo;
import com.orm.obj.SmfDiv;
import com.orm.obj.TranClo;

@Service
public class StockDividend implements SmfDivService {
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	@Override
	public int caType() {
		return CorpActionTypes.STOCKDIVIDEND;
	}

	@Override
	public List<TranBaseMsgDto> genCorpActionTranDto(String portnum, SmfDiv div) {
		List<TranBaseMsgDto> tranDtoList=new ArrayList<TranBaseMsgDto>();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(div.getCusip());
		String cusip=sec.getId();
		TranCloType tranCloType=new TranCloType(portnum, cusip, div.getRecordDate());
		
		//查出所有证券账户下的头寸
		List<TranClo> tCloList = secPositionCenter.getSecClo(tranCloType, false);
		//查询所有tsum记录，包括已经关闭的tsum，股利是根据股权登记日来行使公司行为的
		for(TranClo clo:tCloList){
			if(clo.getAmount()==0){
				continue;
			}
			double divunits=RoundUtil.roundMoney2Dec(div.getRate()*clo.getAmount());
			StockDividendDto dto=new StockDividendDto();
			dto.setHeader("jxStockDividendDtoMsg");
			dto.setOperation(MsgFields.add);
			dto.setId("");
			dto.setPort(portnum);
			dto.setSecid(sec.getSecurityid());
			dto.setAssetType(sec.getIssueType());
			dto.setTradeType(String.valueOf(TransTypes.StockDividend));  //股票股利
			dto.setTradeDate(DateUtil.convertD2YYYY_MM_DD(div.getExDate()));	//除权除息日
			dto.setAmount(divunits);
			dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));	//股权登记日
			dto.setAlRate(String.valueOf(div.getRate()));  //分红比例
			dto.setBaseAmount(clo.getAmount());
			dto.setSecAcct(clo.getSecacct());
			tranDtoList.add(dto);
		}
		
		return tranDtoList;
	}

	@Override
	public String description() {
		return "股票股利";
	}
}
