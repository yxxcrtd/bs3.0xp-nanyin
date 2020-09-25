package com.jixu.msg.service.config;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.dao.intf.SmfDivDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.CorpActionCashDividendDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.SmfDiv;

/** 
 * @ClassName 		CorpActionCashDividendDtoMsg 
 * @author  		Wang Zhongyuan 
 * @Date 			
 * @Description		[创建现金股利公司行为]  
 */
@Service("jxCorpActionCashDividendDtoMsg")
public class CorpActionCashDividendDtoMsg extends MsgExecuteService<CorpActionCashDividendDto>{
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired
	private SmfDivDao smfDivDao;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		CorpActionCashDividendDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), CorpActionCashDividendDto.class);
		validateDto(msgcontainer, dto);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		// 验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		HashMap<Integer, String> result =secCoreFactory.getcorpActionNameMap();
		if(!result.containsKey(dto.getCorpActionType())) {
			msgcontainer.setErrorMsg(false, MsgError.genErrorMsg(MsgError.error22,msgcontainer.getHeader(), String.valueOf(dto.getCorpActionType())));
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}

	@Override
	public MsgContainer completeEntity(CorpActionCashDividendDto dto, MsgContainer container) {
		Date recordDate=checkDateFormat(container,msgHeader(),dto.getRecordDate());//股权登记日
		Date divDate=checkDateFormat(container,msgHeader(),dto.getExDividentDate());//除权除息日
		Date payDate=checkDateFormat(container,msgHeader(),dto.getPayDate());//付款日
		
		//如果当天有同一个券的多比公司行为   则更新已经存在的
		IssueInfo sec=getSecurity(dto.getSecid(), container);
		SmfDiv oldSmfDiv=smfDivDao.loadSmfDiv(sec.getId(), divDate,dto.getCorpActionType());
		
		
		SmfDiv div=new SmfDiv();
		div.setCaType(dto.getCorpActionType());
		div.setCusip(sec.getId());
		div.setRecordDate(recordDate);
		div.setExDate(divDate);
		div.setRate(Double.parseDouble(dto.getAlRate()));
		div.setPayDate(payDate);
		
		String operation=dto.getOperation();
		if(operation.toUpperCase().equals(add)){
			if(oldSmfDiv!=null){
				BeanUtils.copyProperties(div, oldSmfDiv, "id");
				smfDivDao.updateEntity(oldSmfDiv);
				container.setEntityID(String.valueOf(oldSmfDiv.getId()));
				container.setEntityObj(oldSmfDiv);
			}else{
				smfDivDao.saveEntity(div);
				container.setEntityID(String.valueOf(div.getId()));
				container.setEntityObj(div);
			}
		}else if(operation.toUpperCase().equals(update)){
			BeanUtils.copyProperties(div, oldSmfDiv, "id");
			smfDivDao.updateEntity(oldSmfDiv);
			container.setEntityID(String.valueOf(oldSmfDiv.getId()));
			container.setEntityObj(oldSmfDiv);
		}else if(operation.toUpperCase().equals(delete)){
			smfDivDao.deleteEntity(oldSmfDiv);
		}
		return container;
	}

	
	@Override
	public String messageForm() {
		return "";
	}
	
	public String messageName(){
		return "公司行为-现金股利";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}
}
