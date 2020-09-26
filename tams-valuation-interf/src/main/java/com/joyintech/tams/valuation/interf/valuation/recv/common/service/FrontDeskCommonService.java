package com.joyintech.tams.valuation.interf.valuation.recv.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.util.StringUtil;

/**
 * 通道核算信息_几许接口
 * @author zhangquan
 * 20200107
 */
@Service
@Scope("prototype")
public class FrontDeskCommonService {
	
	@Autowired
	private Joyin2JixuInterface joyin2JixuInterface;
	
	/**
	 * 几许接口推送产品信息
	 */
	public void jixuPushProductInfo(List<JJPortfolioDto> jJPortfolioDto) {
		if(!StringUtil.isEmptyList(jJPortfolioDto) && jJPortfolioDto.size()>0) {
			joyin2JixuInterface.createPortfolios(jJPortfolioDto);
		}
	}
	
	/**
	 *几许接口推送资金账户信息
	 */
	public void jixuPushFundAccountInfo(List<JJBkCashAcctDto> jJBkCashAcctDto) {
		if(!StringUtil.isEmptyList(jJBkCashAcctDto) && jJBkCashAcctDto.size()>0) {
			joyin2JixuInterface.updateBkCashAccts(jJBkCashAcctDto);
		}
	}
	
	
	
}
