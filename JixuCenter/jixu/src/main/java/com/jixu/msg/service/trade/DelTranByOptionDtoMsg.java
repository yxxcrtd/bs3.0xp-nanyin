package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.msg.dto.DelTranByOptionDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.TranBasic;

/** 
 * 	@ClassName 		RemoveTranMsg.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 上午10:37:18
 *	@Description	[逐一删除某一资产类别某个日期下的所有交易]
 */
@Service("jxDelTranByOptionDtoMsg")
public class DelTranByOptionDtoMsg extends MsgExecuteService<DelTranByOptionDto>{
	
	@Override
	public String msgHeader() {
		return  null;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		DelTranByOptionDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), DelTranByOptionDto.class);
		//验证删除交易的参数是否满足
		validateDto(msgcontainer, dto);
		//dto验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
		
		completeEntity(dto);
	}

	@Override
	public String messageForm() {
		return null;
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "删除交易操作";
	}
	
	public MsgContainer completeEntity(DelTranByOptionDto dto) {
		String assetType=dto.getAssettype();
		Date tradeDate=dto.getTradedate();
		//根据条件查询出需要删除的交易
		List<TranBasic> list=tranBasicDao.loadTranBasicList(assetType, tradeDate);
		MsgContainer msgcontainer=new MsgContainer();
		for (TranBasic tranBasic : list) {
			msgcontainer=checkTanum(delete, tranBasic.getId(), msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				break;
			}
		}
		return msgcontainer;
	}
}
