
package com.joyintech.tams.invest.entrust.etrstockconnect.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrexchangetrade.service.EtrExchangeTrade001Service;
import com.joyintech.tams.invest.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectPoDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  港股通指令
 * @see EtrStockConnect001Service
 * @since 1.0
 */
@Service
public class EtrStockConnect001Service extends CommonExportService<EtrStockConnectDto> {

	@Autowired
	private IPlatformDate iPlatformDate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 市场行情接口地址
	 */
	/*@Value("${MarketInfoInterf.BaseUrl}")
	private String MarketInfoInterf_BaseUrl;*/
	/**
	 * 交易所行情接口地址
	 */
	@Value("${MarketInfoInterf.exchangeUrl}")
	private String MarketInfoInterf_exchangeUrl;

	/**
	 * 指令共通Service
	 */
	@Autowired
	private EtrCommonService etrCommonService;

	@Autowired
	private EtrExchangeTrade001Service etrExchangeTrade001Service;

	@Autowired
    EtrEntrustMapper etrEntrustMapper;

	/**
	 * 分页查询产品指令信息
	 * <功能详细描述>
	 * @param param 查询参数
	 * @return 分页结果
	 * @see
	 * @since 1.0
	 */
	public List<EtrStockConnectDto> selectEtrStockConnect(Map<String, Object> param) {
//		//主动调用B45沪深交易所指令查询接口查询执行进度 start  add by houzk at 20210524 start
//		try{
//			String sysDate = iPlatformDate.getSysDateStr();//平台日日期
//			//获取未执行完成的指令
//			List<EtrInvestAllInfoDto> listEtrInvestAllInfoReaderDto = etrEntrustMapper.getUnfinishedInvestInfo(sysDate);
//			logger.info("港股通主动请求B45获取沪深交易所交易信息条数为：" + listEtrInvestAllInfoReaderDto.size());
//			etrExchangeTrade001Service.updateProcessPercent(listEtrInvestAllInfoReaderDto);
//		}catch (Exception e){
//
//		}
//		param.put("sysDate", iPlatformDate.getSysDateStr());
//		//主动调用B45沪深交易所指令查询接口查询执行进度 start  add by houzk at 20210524 end

		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect001/selectEtrStockConnect",
						param, EtrStockConnectDto.class);
			}
		}catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		return null;
	}
	
    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param param 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
	public Page<EtrStockConnectPoDto> findPositionsPage(Map<String, Object> param) {
		try {
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				return restTemplate.postForPage(
						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect001/findPositionsPage", param,
						EtrStockConnectPoDto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	/** 查询港股通行情信息 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> findQuotationsPage(Map<String, Object> map) {
//		String finprodMarketId = (String) map.get("finprodMarketId");
//		String param = "?instrumentID=" + finprodMarketId;
//		String url = MarketInfoInterf_exchangeUrl+"/rest/markinfo/exchange/getHKBiddingDealMarkinfoByInstrumentID"+param;
//
//		return etrCommonService.getMarketInfo(url);
		return new HashMap<>();
	}

}
