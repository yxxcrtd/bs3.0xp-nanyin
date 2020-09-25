package com.jixu.msg.service.market;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.SmfOperationDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;
import com.orm.obj.RPSecType;
import com.orm.obj.SecContract;

//@Component("jxSmfOperationDtoMsg")
public class SmfOperationDtoMsg  extends MsgExecuteService<SmfOperationDto>{
	
	@Autowired
	private SecContractDao secContractDao;
	
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
		SmfOperationDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), SmfOperationDto.class);
		validateDto(msgcontainer, dto);
		
		Date secExpireDate=DateUtil.convertStr2Date2("2999-12-31");
		
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//completeEntity(dto);
	}

	@Override
	public String messageForm() {
		return "";
	}
	
	public String messageName(){
		return "证券操作";
	}
	
	@Override
	public MsgContainer completeEntity(SmfOperationDto dto, MsgContainer container) {
		IssueInfo sec=new IssueInfo();
		sec.setSecurityid(dto.getSecid());
		sec.setSecurityname(dto.getSecurityName());
		sec.setIssuer(dto.getIssuer());
		sec.setIssueType(dto.getIssuetype());
		sec.setRpSecType(new RPSecType(Integer.parseInt(dto.getAssetType())));
		if(!dto.getPrcurrency().equals("")){
			sec.setPriCur(dto.getPrcurrency());
		}else{
			sec.setPriCur(dto.getLongCurrency());
		}
		sec.setIncomeCur(dto.getIncurrency());
		if(!dto.getPrcurrency2().equals("")){
			sec.setPriCur(dto.getPrcurrency2());
		}
		sec.setIncomeCur2(dto.getIncurrency2());
		sec.setExchangeCode(dto.getExchange());
		sec.setCoaaGroup(dto.getGlgroup());
		sec.setInterfaceSourceID(dto.getSource());
		sec.setCountry(dto.getCountry());
		sec.setFixIncomeType(dto.getFixIncomeType());
		sec.setStartPrice(dto.getStartPrice());
		sec.setEndPrice(dto.getEndPrice());
		sec.setSwapExchangeInd(dto.getSwapExchangeInd());
		sec.setCashFlowCfgID(dto.getCashflowcfg());
		sec.setCashFlowCfgID2(dto.getCashflowcfg2());
		sec.setExpireDate(DateUtil.convertStr2Date2(dto.getSecExpireDate()));
		sec.setContractSize(dto.getSecContractSize());
		sec.setOptionType(dto.getOptionType());
		sec.setUnderCusip(dto.getUnderCusip());
		sec.setActiveDate(DateUtil.convertStr2Date2(dto.getActiveDate()));
		
		//smf辅助表
		SecContract sc=new SecContract();
		sc.setLongCurID(dto.getLongCurrency());
		sc.setLongCash(dto.getLongPrincipal());
		sc.setShortCurID(dto.getShortCurrency());
		sc.setShortCash(dto.getShortPrincipal());
		sc.setFuturesType(dto.getFuturesType());
		sc.setFuturesTarget(dto.getFuturesTarget());
		sc.setMarginRate(dto.getMarginRate());
		
		//TODO 直接写入 type1(01) type3(K01),上线时需要确认
		FinProductType fpd=returnFinProductType(dto.getSecid(),Integer.parseInt(dto.getAssetType()));
		
		if(dto.getOperation().equalsIgnoreCase(add)){
			issueInfoDao.saveSecurity(sec);
			container.setEntityID(sec.getId());
			container.setEntityObj(sec);
			sc.setCusip(sec.getId());
			secContractDao.saveEntity(sc);
			//如果FinProductType表中存在数据则更新
			FinProductType oldfpd=issueInfoDao.loadFinProductType(dto.getSecid());
			if(JixuUserUtil.isJoyinTablesFlag() && oldfpd!=null){
				BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
				issueInfoDao.updateEntity(oldfpd);
			}else{
				issueInfoDao.saveEntity(fpd);
			}
			outDataCenter.exePublicDataSender(sec);
		}else if(dto.getOperation().equalsIgnoreCase(update)){
			IssueInfo oldIssue=issueInfoDao.loadIssueByCusip(dto.getId());
			BeanUtils.copyProperties(sec, oldIssue, "id");
			issueInfoDao.updateIssue(oldIssue);
			container.setEntityID(oldIssue.getId());
			
			//smf辅助表
			SecContract oldSc=secContractDao.loadSecContract(dto.getId());
			if(oldSc!=null){
				sc.setCusip(oldIssue.getId());
				BeanUtils.copyProperties(sc, oldSc, "id");
				issueInfoDao.updateEntity(oldSc);
			}
			//更新金融产品表
			FinProductType oldfpd=issueInfoDao.loadFinProductType(dto.getSecid());
			if(oldfpd!=null){
				BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
				issueInfoDao.updateEntity(oldfpd);
			}else{
				issueInfoDao.saveEntity(fpd);
			}
			outDataCenter.exePublicDataSender(oldIssue);
		}else{
			container.setCorrectFlag(false);
			container.setErrorMsg(MsgError.genErrorMsg(MsgError.error8, dto.getOperation()));
		}
		
		return container;
	}
}
