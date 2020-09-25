package com.jixu.job.service.CorpAction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CorpActionTypes;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.job.service.SmfDivService;
import com.jixu.msg.dto.StockSplitDto;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.orm.obj.IssueInfo;
import com.orm.obj.SmfDiv;

@Service
public class StockSplit implements SmfDivService {
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Override
	public int caType() {
		return CorpActionTypes.STOCKSPLIT;
	}

	@Override
	public List<TranBaseMsgDto> genCorpActionTranDto(String portnum, SmfDiv div) {
		List<TranBaseMsgDto> dtoList=new ArrayList<TranBaseMsgDto>();
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(div.getCusip());
		StockSplitDto dto=new StockSplitDto();
		dto.setHeader("jxStockSplitDtoMsg");
		dto.setOperation(MsgFields.add);
		dto.setId("");
		dto.setPort(portnum);
		dto.setSecid(sec.getSecurityid());
		dto.setAssetType(sec.getIssueType());
		dto.setTradeType(String.valueOf(TransTypes.STOCKSPLIT));
		dto.setTradeDate(DateUtil.convertD2YYYY_MM_DD(div.getExDate()));	//除权除息日
		dto.setDivDate(DateUtil.convertD2YYYY_MM_DD(div.getRecordDate()));	//股权登记日
		dto.setAlRate(String.valueOf(div.getRate()));
		dtoList.add(dto);
		
		return dtoList;
	}

	@Override
	public String description() {
		return "股票分割";
	}

}
