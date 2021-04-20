package com.joyintech.service.bankuplink.common.service;

import com.joyintech.background.component.uplink.interbank.business.dto.ResultBaseDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.bankuplink.common.dao.CstpAutoTradeMapper;
import com.joyintech.service.bankuplink.common.dao.CstpTradeMapper;
import com.joyintech.service.bankuplink.common.dto.TrdRepoTrade002Dto;
import com.joyintech.service.bankuplink.common.util.BusinessTypeUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.impl.dao.BaseGetBussinessIdMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.cstp.dao.CstpCommonMatchMapper;
import com.joyintech.tams.invest.common.cstp.service.CstpCommonMatchService;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.dto.cstp.EtrEntrustExt;
import com.joyintech.tams.invest.common.dto.cstp.InterfTradRepoasstDto;
import com.netflix.discovery.converters.Auto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行间回购下行自动录单service
 */
@Service
public class CstpRepoAutoTradeService extends BaseService<TrdRepoTrade002Dto> {

	/**
	 * 日志
	 */
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CstpRepoAutoTradeService.class);

	/**
	 * 接口抵押品信息
	 */
	@Autowired
	private CstpAutoTradeMapper autoTradeMapper;

	/**
	 * 接口处理cstp
	 */
	@Autowired
	private CstpTradeMapper cstpMapper;

	/**
	 * 业务编号生成规则mapper
	 */
	@Autowired
	private BaseGetBussinessIdMapper bussinessCodeMapper;

	/**
	 * 平台日
	 */
	@Autowired
	private IPlatformDate iPlatformDate;

	/**
	 * 交易对手
	 */
	@Autowired
	private MstCounterPartyMapper mstCounterPartyMapper;

	@Autowired
	private StlSecAcctInfoMapper secAcctInfoMapper;

	@Autowired
	private TrdMatchInfoMapper trdMatchInfoMapper;

	@Autowired
	private TrdPledgeMatchInfoMapper trdPledgeMatchInfoMapper;

	@Autowired
	private InterfOpicsTradRepoMapper repoMapper;

	@Autowired
	private CstpCommonMatchMapper cstpCommonMatchMapper;

	@Autowired
	private CstpCommonMatchService cstpCommonMatchService;

	/**
	 * 生成交易对手编号
	 * 
	 * @return
	 */
	public String genBussinessId() {
		BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
		bussinessCodeParameter.setCommonBusinessType(BussinessDictCodeList.共同业务类型_交易对手管理);
		List<MstBusinessIdRule> mstBusinessIdRules = bussinessCodeMapper.selectRuleByParameter(bussinessCodeParameter);
		MstBusinessIdRule rule = null!=mstBusinessIdRules&&mstBusinessIdRules.size()>0?mstBusinessIdRules.get(0):new MstBusinessIdRule();
		Date sysDate = iPlatformDate.getSysDate();
		// 根据名字获取前缀序列号
		String prefixSequence = null;
		// 后缀生成用序列名称
		String postfixSequence = null;
		// 前缀生成用序列名称不为空
		if (StringUtil.isNotEmpty(rule.getPrefixSequenceName())) {
			// 根据名字获取前缀序列号
			prefixSequence = getFormatCode(bussinessCodeMapper.getNextValBySequenceName(rule.getPrefixSequenceName()),
					rule.getPrefixSequenceLen().toString());
		}
		// 后缀生成用序列名称不为空
		if (StringUtil.isNotEmpty(rule.getPostfixSequenceName())) {
			// 根据名字获取后缀序列号
			postfixSequence = getFormatCode(bussinessCodeMapper.getNextValBySequenceName(rule.getPostfixSequenceName()),
					rule.getPostfixSequenceLen().toString());
		}
		// 生成的业务编号
		StringBuilder bussinessCode = new StringBuilder();
		// 前缀名 + 前缀生成用序列名称采番的序列号 + 上面取得的日期 + 后缀名 + 后缀生成用序列名称采番的序列号
		bussinessCode.append(nvl(rule.getPrefixName())).append(nvl(prefixSequence))
				.append(DateUtil.format(sysDate, DateUtil.DEFAULT_OTHER_FORMAT)).append(nvl(rule.getPostfixName()))
				.append(nvl(postfixSequence));
		return bussinessCode.toString();
	}

	/**
	 * 根据当前值和长度生成格式化长度
	 *
	 * @param code 待格式化数据
	 * @param rule 格式化长度
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	private String getFormatCode(String code, String rule) {
		rule = "%0" + rule + "d";
		return String.format(rule, Long.valueOf(code));
	}

	/**
	 * 获取默认字符串 <功能详细描述>
	 *
	 * @param code 目标值
	 * @param def  默认值
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	private String nvl(String code, String def) {
		return StringUtil.isNotEmpty(code) ? code : def != null ? def : "";
	}

	/**
	 * 默认非空方法 <功能详细描述>
	 *
	 * @param code 非空字段
	 * @return 返回值
	 * @see
	 * @since 1.0
	 */
	private String nvl(String code) {
		return nvl(code, "");
	}

	public void repoAutoTrade(InterfCstpTrade interfCstpTrade,InterfOpicsTradRepo interfcstpTradRepo, ResultBaseDto resultBaseDto) {
		String matchType = DictCodeListExt3.下行交易匹配类型_自动匹配.getValue();
		/**
		 * 入接口表(INTERF_OPICS_TRAD_REPO)成功后，根据原始交易编号（INTERF_OPICS_TRAD_REPO.ORITRADEID）匹配质押式回购银行间报价表(IBU_QUOTE_CREP)中的委托代码，
		 * 匹配上继续生成交易数据，匹配不上不生成交易。再判断抵押品债券标的是否存在，存在则进行后续操作
		 *
		 */
		// 成交编号
		String oritradeId = interfCstpTrade.getExecid();
		// 匹配委托(根据成交单编号查询组合以及委托编号)
		// 上行报价完之后往外汇发，外汇返回成交编号和下发成交回报数据给投组，这两个是同步进行，这中间存在一个时间差，导致下行这边无法匹配上行报价的委托编号,这里隔1s查一次
		int QueryCount = 1;
		EtrEntrustExt entrust = null;
		while (QueryCount <= 10 && entrust == null) {
			logger.info("下行自动匹配（回购）第" + QueryCount + "次查询上行报价指令信息！！！");
			entrust = autoTradeMapper.queryRepoEntrustInfo(oritradeId);
			if(null!=entrust){
				String isDzh = cstpMapper.getIsDzh(entrust.getQuoteId());
				if(StringUtil.equals(isDzh,"Y")||(StringUtil.equals(isDzh,"N")&&StringUtil.isNotEmpty(entrust.getPortfolioId()))){
					break;
				}
			}
			else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					logger.error("下行（回购）自动录单匹配指令信息失败！！！");
				}
				QueryCount++ ;
			}
		}
		// 查询抵押品债券信息
		List<InterfTradRepoasstDto> repoasst = autoTradeMapper.queryRepoAsst(oritradeId);
		if (StringUtil.isNotEmpty(entrust)
				&& !StringUtil.isEmptyList(repoasst)) {
			// 判断是否已经生成了交易
			TrdProductDeal deal = autoTradeMapper.isExistsTrade(oritradeId);
			if (!StringUtil.isNotEmpty(deal)) {
				// 判断交易对手是否存在
				InterfOpicsTradRepo interfOpicsTradRepo = cstpMapper.selectTradRepo(oritradeId,
						DateUtil.format(iPlatformDate.getSysDate(), DateUtil.DEFAULT_OTHER_FORMAT),
						BusinessTypeUtil.BUSINESSTYPE_REPO);
				/*if (StringUtil.isNotEmpty(interfOpicsTradRepo.getSeller())
						&& autoTradeMapper.queryCountPartyByName(interfOpicsTradRepo.getSeller()) == null) {
					MstCounterParty mstCounterParty = new MstCounterParty();
					// 生成交易对手编号
					String bussinessId = this.genBussinessId();
					mstCounterParty.setCounterId(bussinessId);
					mstCounterParty.setCounterName(interfOpicsTradRepo.getSeller());
					mstCounterParty.setCounterType(BussinessDictCodeList.交易对手类型_资管产品.getValue());
					// 资管产品管理人员默认为空
					mstCounterPartyMapper.insertSelective(mstCounterParty);
				}*/
				// 组装交易实体类
				TrdRepoTrade002Dto repoTradeDto = new TrdRepoTrade002Dto();
				if(StringUtil.isNotEmpty(entrust.getCpPartyId())){
					repoTradeDto.setCounterId(cstpMapper.selectCounterId(entrust.getCpPartyId()));
				}
				repoTradeDto.setCounterTrader(entrust.getCpTrader());
				repoTradeDto.setTrader(entrust.getOuTrader());
				repoTradeDto.setPs(entrust.getPs());
				repoTradeDto.setfTradeId(oritradeId);
				// 交易与委托的自动匹配
				repoTradeDto.setEntrustId(entrust.getEntrustId());
				// 组合
				repoTradeDto.setPortfolioId(entrust.getPortfolioId());
				// 查询上行报价中的账户来源信息 IBU_PORTFOLIO_ACCT_RELA
				if (StringUtil.isNotEmpty(interfOpicsTradRepo.getOurdepositaccountnumber())) {
					StlSecAcctInfo stlSecAcctInfo = secAcctInfoMapper.selectByPrimaryKey(interfOpicsTradRepo.getOurdepositaccountnumber());
					if (StringUtil.isNotEmpty(stlSecAcctInfo)) {
						repoTradeDto.setSecAcctSource(stlSecAcctInfo.getSecAcctSource());
					}
				}
				repoTradeDto.setInterfOpicsTradRepo(interfOpicsTradRepo);
				repoTradeDto.setInterfTradRepoassts(repoasst);
				repoTradeDto.setQuoteId(entrust.getQuoteId());
				repoTradeDto.setCbFpriceAmt(entrust.getCbFpriceAmt());
				repoTradeDto.setScFpriceAmt(entrust.getScFpriceAmt());
				try{
					Map<String,Object> resultMap = restTemplate.postForObject(
							ServiceApplication.PINGAN_SERVICE_NAME+ "/invest/trade/trdrepotrade/TrdRepoTrade002/repoAutoTrade",
							repoTradeDto, Map.class);
					//登录匹配情况 ptlAllocationDtos  add by zhangnian 20210316 start
					List<PtlAllocationDto> ptlAllocations = (List<PtlAllocationDto>)resultMap.get("ptlAllocationDtos");
					if(!StringUtil.isEmptyList(ptlAllocations)){
						ptlAllocations.forEach(item -> {
							TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
							trdMatchInfo.setSeqno(cstpMapper.selectSeqNo());
							trdMatchInfo.setCstpTradeId(oritradeId);
							trdMatchInfo.setEntrustId(item.getEntrustId());
							trdMatchInfo.setMatchNum(item.getFpriceAmt());
							trdMatchInfo.setMatchType(matchType);
							trdMatchInfo.setTradeDate(DateUtil.parse(interfCstpTrade.getTradedate()));
							trdMatchInfoMapper.insert(trdMatchInfo);
						});
					}
					//登录匹配情况 ptlAllocationDtos  add by zhangnian 20210316 end
					//登录匹配质押券
					List<PledgeBondInfoDto> pledgeBondInfoDtos = (List<PledgeBondInfoDto>)resultMap.get("pledgeBondInfoDtos");
					if(!StringUtil.isEmptyList(pledgeBondInfoDtos)){
						pledgeBondInfoDtos.forEach(item -> {
							TrdPledgeMatchInfo trdPledgeMatchInfo = new TrdPledgeMatchInfo();
							trdPledgeMatchInfo.setSeqno(cstpCommonMatchMapper.selectSeqNo(DateUtil.getSysDate()));
							trdPledgeMatchInfo.setCstpTradeId(oritradeId);
							trdPledgeMatchInfo.setEntrustId(item.getEntrustId());
							trdPledgeMatchInfo.setSecAcctId(item.getSecAcctId());
							trdPledgeMatchInfo.setPledgeFinprodId(item.getFinprodId());
							trdPledgeMatchInfo.setMatchNum(item.getPledgePrin());
							trdPledgeMatchInfo.setMatchType(matchType);
							trdPledgeMatchInfo.setTradeDate(DateUtil.parse(interfCstpTrade.getTradedate()));
							trdPledgeMatchInfoMapper.insert(trdPledgeMatchInfo);
						});
					}
 					//登录匹配质押券
					//更新交易手续费和结算手续费
					InterfOpicsTradRepo repoRecord = new InterfOpicsTradRepo();
					repoRecord.setTradeid(interfcstpTradRepo.getTradeid());
					repoRecord.setDealFeeamt(resultMap.containsKey("dealFeeamt")?(BigDecimal) resultMap.get("dealFeeamt"):null);
					repoRecord.setSettFeeamt(resultMap.containsKey("settFeeamt")?(BigDecimal) resultMap.get("settFeeamt"):null);
					repoMapper.updateByPrimaryKeySelective(repoRecord);
					// 最后修改接口表中的数据来源,用于撤销
					autoTradeMapper.updateInterfCstpTrade(oritradeId, BusinessTypeUtil.BUSINESSTYPE_REPO);
				}
				catch (Exception e){
					logger.info("下行交易处理失败,自动生成匹配信息开始");
					Map<String,Object> map = new HashMap<>();
					map.put("execId",oritradeId);
					map.put("matchType", matchType);
					cstpCommonMatchService.dealMatchEntrust(map);
					logger.info("下行交易处理失败,自动生成匹配信息结束");
					resultBaseDto.setErrMsg(e.getMessage());
					logger.error(this.getClass()+"质押式回购成交明细接口异常:"+e.getMessage());
				}
			} else {
				String msg = "成交单编号：" + oritradeId + "，已经存在有效交易！";
				resultBaseDto.setErrMsg(msg);
				logger.info(msg);
			}
		} else {
			String msg = "无报价的指令代码或抵押品债券不存在！";
			resultBaseDto.setErrMsg(msg);
			logger.info(msg);
		}

	}
}
