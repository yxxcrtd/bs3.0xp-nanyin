package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.PortfolioHdr;

/** 
 * @ClassName 		AssemblePortfolio
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:12:47 	
 * @Description		[]  
 * @msg				PORTFOLIO(PortfolioCode)
 */

@Service("portfolioMsg")
public class AssemblePortfolio extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Portfolio_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		String portnum=fields.get(0);
		PortfolioHdr portfolio= commService.loadPortfolio(portnum);
		if (portfolio == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(portfolio);
		}
		return;
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/port/portfolio_edit_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "投资组合操作";
	}
}
