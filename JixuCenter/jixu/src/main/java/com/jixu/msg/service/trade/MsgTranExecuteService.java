package com.jixu.msg.service.trade;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.TranMsgDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

public abstract class MsgTranExecuteService<T extends TranMsgDto> extends MsgTranBaseExecuteService<T>{
	
	@Override
	protected MsgContainer completeTranEntity(T dto, TranBasic tran, TranBasic oldTran, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
		List<TranBasicFee> feeList=genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());

		if(operation.equalsIgnoreCase(add)){
			if(tanum!=null && !tanum.equals("")){
				tran.setId(tanum);
			}
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			//保存交易费用
			for(TranBasicFee fee:feeList){
				fee.setTanum(tran);
				tranBasicFeeDao.saveEntity(fee);
			}
			
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(tran, oldTran,new String[]{"id","createDate","createUser"});
			tranBasicDao.updateEntity(oldTran);
			//保存交易费用
			for(TranBasicFee fee:feeList){
				fee.setTanum(oldTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			tranBasicDao.updateEntity(oldTran);
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		
		msgcontainer.setPortid(dto.getPort());
		return msgcontainer;
	}
	
	//交易基本信息验证
	@Override
	protected void validDtoDataFunc(T dto, MsgContainer msgcontainer) throws MsgFieldLackException {
		// 获取operation
		String operation = dto.getOperation();

		// 根据operation，检查以前的交易是否存在
		msgcontainer = checkOldTran(operation, dto.getId(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}

		if (operation.equalsIgnoreCase(delete)) {
			msgcontainer.setEntityObj(dto);
			return;
		}

		// 交易类型
		String assetType = dto.getAssetType();
		int tranType = Integer.valueOf(dto.getTradeType());
		if (!SecurityCoreFactory.getAssetTypeHM().get(assetType).containsKey(tranType)) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(
			MsgError.genErrorMsg(MsgError.error13,assetType + ":" + dto.getTradeType()));
			return;
		}

		// 验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}

		// 结算日期
		checkDateFormat(msgcontainer, msgHeader(), dto.getSettleDate());
		if (!msgcontainer.isCorrectFlag())
			return;

		// 券商
		if(dto.getBroker()!=null && dto.getBroker()!=""){
			getBroker(dto.getBroker(), msgcontainer);
			if (!msgcontainer.isCorrectFlag()) {
				return;
			}
		}

		// 获取现金账户
		getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}

		// 产品/投资组合
		PortfolioHdr portfolio = getPortfolio(dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}

		// 投资经理
		getManagers(portfolio.getManagerGroup(), Integer.parseInt(dto.getManager()), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
	}
}
