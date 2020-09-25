package com.jixu.msg.service.trade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.dao.intf.GlCoaaMapDao;
import com.jixu.msg.dto.ValManualVoucherDetailDto;
import com.jixu.msg.dto.ValManualVoucherDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.GlCoaaMap;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		TranSecPositionUpdateDtoMsg
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jxTranValManualVoucherDtoMsg")
public class TranValManualVoucherDtoMsg extends MsgTranExecuteService<ValManualVoucherDto>{
	
	@Autowired
	private GlCoaaMapDao glCoaaMapDao;
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		ValManualVoucherDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ValManualVoucherDto.class);
		if(!msgcontainer.isCorrectFlag() || dto.getOperation().equalsIgnoreCase(delete)){
			return;
		}
		
		//产品/投资组合
		getPortfolio(dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}

	@Override
	public TranBasic dto2TranBasic(ValManualVoucherDto dto, MsgContainer msgcontainer) {
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		IssueInfo sec=getSecurity(dto.getSecid(), msgcontainer);
		
		//如果是股票，则需要设置证券账户
		//TODO 临时处理放入默认证券账户
		String secacct=portfolio.getDefaultSecAcct();
		
		//获取改证券的所有总账栏-科目映射 colacctmap
		List<GlCoaaMap> coamapList=glCoaaMapDao.loadGlColAcctMap(portfolio.getCoaahdr(), sec.getCoaaGroup());
		HashMap<Integer, String> colacctMap=new HashMap<Integer, String>();
		for(GlCoaaMap coamap:coamapList) {
			colacctMap.put(coamap.getColumnNum(), coamap.getAcctNum());
		}
		
		//这里对表字段的分配，是支持joyin手工凭证的特殊处理，不具有普遍性
		//amount		数量
		//principal		成本原币
		//principal2	成本本币
		//cash			估增原币
		//cash2 		估增本币
		//intatmat		应收利息原币
		//intatmat2		应收利息本币
				
		
		double amount=0;
		double prinipal=0;
		double prinipal2=0;
		double cash=0;
		double cash2=0;
		double intatmat=0;
		double intatmat2=0;
		
		List<ValManualVoucherDetailDto> detaildtos=dto.getListAddDto();
		for(ValManualVoucherDetailDto detaildto:detaildtos) {
			//1.获取数量
			if(detaildto.getHappenNumber()!=null) {
				amount=detaildto.getHappenNumber().doubleValue();
			}
			
			String acctnum=detaildto.getSubjectNo();
			
			double ybamount=0;
			double bbamount=0;
			if(detaildto.getdAmt()!=null) {
				ybamount=detaildto.getdAmt().doubleValue();
			}else if(detaildto.getcAmt()!=null) {
				ybamount=detaildto.getcAmt().doubleValue()*-1;
			}
			
			if(detaildto.getBdHappenAmt()!=null) {
				bbamount=detaildto.getBdHappenAmt().doubleValue();
			}else if(detaildto.getBcHappenAmt()!=null) {
				bbamount=detaildto.getBcHappenAmt().doubleValue()*-1;
			}
			
			
			
			if(acctnum.equals(colacctMap.get(GLACCT.BOOK_VALUE)) 
					|| acctnum.equals(colacctMap.get(GLACCT.BOOK_VALUE_SHORT))) {
				//2.获取成本科目
				prinipal=ybamount;
				prinipal2=bbamount;
			}else if(acctnum.equals(colacctMap.get(GLACCT.CARRY_VAL_ADJ))) {
				//3.获取估增科目
				cash=ybamount;
				cash2=bbamount;
			}else if(acctnum.equals(colacctMap.get(GLACCT.INC_DUE))
					|| acctnum.equals(colacctMap.get(GLACCT.INC_REC))
					|| acctnum.equals(colacctMap.get(GLACCT.INC_PAID))
					|| acctnum.equals(colacctMap.get(GLACCT.ACCRUED_INT_EXP_PAY_SHORT))){
				//4.获取应收利息科目
				intatmat=ybamount;
				intatmat2=bbamount;
			}
		}
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);				//证券id
		tran.setTrantype(TransTypes.ValMannulPositionUpdate);		
		tran.setTradedate(dto.getBookDate());			//手工凭证日期即交易、结算和入账日							
		tran.setSettledate(dto.getBookDate());								
		tran.setLedgerdate(dto.getBookDate());					
		tran.setPortfolio(portfolio.getId());
		tran.setSecacct(secacct);
		tran.setAmount(amount);			//数量
		tran.setPrincipal(prinipal);	//成本原币
		tran.setPrincipal2(prinipal2);	//成本本币
		tran.setIntatmat(intatmat);		//利息原币
		tran.setIntatmat2(intatmat2);	//利息本币
		tran.setCash(cash);				//市值原币
		tran.setCash(cash2);			//市值本币
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}
	
	@Override
	public String messageName() {
		return "手工凭证更新头寸";
	}
}
