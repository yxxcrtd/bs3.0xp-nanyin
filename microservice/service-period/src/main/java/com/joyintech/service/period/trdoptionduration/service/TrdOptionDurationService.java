package com.joyintech.service.period.trdoptionduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.period.trdoptionduration.dao.TrdOptionDurationMapper;
import com.joyintech.service.period.trdrepoduration.dao.TrdRepoDurationMapper;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdoptionduration.OptionDurationDetailDto;
import com.joyintech.tams.invest.common.dto.period.trdoptionduration.TrdOptionDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.TrdStockBonusDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * @author panbin
 * @version 2019年6月19日
 * @see TrdOptionDurationService
 */
@Service
public class TrdOptionDurationService extends BaseService<TrdOptionDurationDto>{

	@Autowired
	private TrdOptionDurationMapper trdOptionDurationMapper;
	@Autowired
	private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
	@Autowired
	private TrdProductDealMapper trdProductDealMapper;
	@Autowired
	private TrdProductDealAddMapper trdProductDealAddMapper;
	@Autowired
	private StlPayOrdersMapper stlPayOrdersMapper;
	@Autowired
	private PtlTradeMapper ptlTradeMapper;
	@Autowired
	private TrdRepoDurationMapper trdRepoDurationMapper;
	@Autowired
	private InvestPeriodCommonService periodCommonService;
	@Autowired
    private RepTradeMapper repTradeMapper;
	
	@Autowired
    private IPlatformDate platformDateImpl;
	@Autowired
	private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
	@Autowired
	private BasePtlSecPositionInfoService basePtlSecPositionInfoServiceImpl;
	@Autowired
	private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

	public Page<TrdOptionDurationDto> findPage(int page, int length, Map<String, Object> param) {
		setParams(param);
		setParamsList(param);
		//导出用，没有设置会报错
		if(param != null && !param.containsKey("xqzt")){
			param.put("xqzt", "");
		}
        return queryForPageForSelectReturn(page, length, () -> trdOptionDurationMapper.findPage(param));
	}

	private void setParamsList(Map<String, Object> param) {
        if(StringUtil.isNotEmpty((String)param.get("secManageAcctId"))){
        	param.replace("secManageAcctId", Arrays.asList(param.get("secManageAcctId").toString().split(",")));
        }else{
        	param.replace("secManageAcctId", null);
        }
        if(StringUtil.isNotEmpty((String)param.get("finprodMarketId"))){
        	param.replace("finprodMarketId", Arrays.asList(param.get("finprodMarketId").toString().split(",")));
        }else{
        	param.replace("finprodMarketId", null);
        }
        if(StringUtil.isNotEmpty((String)param.get("chlId"))){
            param.replace("chlId", Arrays.asList(param.get("chlId").toString().split(",")));
        }else{
            param.replace("chlId", null);
        }
        if(StringUtil.isNotEmpty((String)param.get("bonusMode"))){
        	param.replace("bonusMode", Arrays.asList(param.get("bonusMode").toString().split(",")));
        }else{
        	param.replace("bonusMode", null);
        }
	}
	
	private void setParams(Map<String, Object> param) {
        param.put("TRADE_FLOW_STATUS01", DictCodeListExt1.交易流程状态_流程中.getValue());
        param.put("TRADE_FLOW_STATUS02", DictCodeListExt1.交易流程状态_有效.getValue());
        param.put("TRADE_FLOW_STATUS03", DictCodeListExt1.交易流程状态_撤销.getValue());
        param.put("F10", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
		param.put("PS_T11", DictCodeListExt1.交易方向_提前行权.getValue());
		param.put("PS_T12", DictCodeListExt1.交易方向_到期行权.getValue());
        param.put("PS_T13", DictCodeListExt1.交易方向_放弃行权.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("YES_NO_NO", DictCodeList.是否_否.getValue());
		param.put("YES_NO_YES", DictCodeList.是否_是.getValue());
		param.put("PAGE_DATA_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
		param.put("PAGE_DATA_STATUS02", DictCodeListExt1.画面数据状态_流程中.getValue());
		param.put("PAGE_DATA_STATUS03", DictCodeListExt1.画面数据状态_有效.getValue());
		param.put("PAGE_DATA_STATUS04", DictCodeListExt1.画面数据状态_撤销.getValue());
    }

	/**
	 * 
	 * TODO 业务编码
	 * @return Page<SelectOptionDto>
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */
	public Page<SelectOptionDto> getBusiCode(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TGF01", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return queryForPageForSelectReturn(page, length, () -> trdOptionDurationMapper.getBusiCode(param));
    }

	/**
	 * 
	 * TODO 有效、撤销
	 * @return TrdRepoDurationDto
	 * @param param
	 * @return
	 */
	public TrdStockBonusDto findEffectiveInfo(Map<String, Object> param) {
		setParams(param);
		return trdOptionDurationMapper.findEffectiveInfo(param);
	}

	/**
	 * 
	 * TODO 流程中
	 * @return TrdRepoDurationDto
	 * @param param
	 * @return
	 */
	public TrdStockBonusDto findProcessInfo(Map<String, Object> param) {
		setParams(param);
		return trdOptionDurationMapper.findProcessInfo(param);
	}

	/**
	 * 
	 * TODO 金融产品交易
	 * @return List<TrdProductDeal>
	 * @param busiCode
	 * @return
	 */
	public List<TrdProductDeal> finPrdDealInfo(String busiCode) {
		return trdOptionDurationMapper.finPrdDealInfo(busiCode);
	}

	/**
	 * 
	 * TODO 组合交易
	 * @return PtlTrade
	 * @param tradeId
	 * @return
	 */
	public PtlTrade findPtlTradeInfo(String tradeId) {
		return trdOptionDurationMapper.findPtlTradeInfo(tradeId);
	}


	/**
	 * 
	 * TODO 金融产品交易附属
	 * @return TrdProductDealAdd
	 * @param tradeId
	 * @return
	 */
	public TrdProductDealAdd findPrdDealAddInfo(String tradeId) {
		return trdOptionDurationMapper.findPrdDealAddInfo(tradeId);
	}


	/**
	 * 工作流结束
	 * <功能详细描述>
	 * @param optionDurationDetailDto dto
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
	@Transactional(rollbackFor = Exception.class)
	public String doComplete(OptionDurationDetailDto optionDurationDetailDto) {
		String workflowId = optionDurationDetailDto.getWorkflowId();
		String updateUser = optionDurationDetailDto.getUpdateUser();
		Date updateTime = new Date();
		if (BussinessConstant.REVOKE.equalsIgnoreCase(optionDurationDetailDto.getOperateType())) {
			TrdFinProductDealTotal trdFinProductDealTotal = periodCommonService
					.revoke汇总和交易相关表(optionDurationDetailDto.getpTradeId(), updateUser, optionDurationDetailDto.getRevokeReason(), null, null);
			deleteWflByWorkflowId(workflowId);
			// 删除质押券持仓
			WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
			BeanUtils.copyProperties(trdFinProductDealTotal, wflTrdFinProductDealTotal);
			deletePledgeSecPosition(wflTrdFinProductDealTotal);

			return Boolean.TRUE.toString();
		}

		// 金融产品交易Entity
		List<WflTrdProductDeal> wflTrdProductDeals = trdRepoDurationMapper.findDealByWorkflowId(workflowId);
		// 组合交易
		List<PtlTrade> ptlTrades = gen组合交易(optionDurationDetailDto, wflTrdProductDeals);
		// 金融产品交易汇总
		WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdRepoDurationMapper.findTotalByWorkflowId(workflowId);
		TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
		BeanUtils.copyProperties(wflTrdFinProductDealTotal, trdFinProductDealTotal);
		trdFinProductDealTotal.setUpdateTime(updateTime);
		trdFinProductDealTotal.setUpdateUser(updateUser);
		trdFinProductDealTotal.setPayDate(optionDurationDetailDto.getZfDate());//支付日期
        trdFinProductDealTotal.setSettleDate(optionDurationDetailDto.getZfDate());//支付日期
		trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);

		List<TrdProductDeal> trdProductDeals = new ArrayList<>();
		wflTrdProductDeals.forEach(wflTrdProductDeal -> {
			TrdProductDeal deal = new TrdProductDeal();
			BeanUtils.copyProperties(wflTrdProductDeal, deal);
			deal.setPsPayment(optionDurationDetailDto.getSffx());
			deal.setUpdateTime(updateTime);
			deal.setUpdateUser(updateUser);
			trdProductDeals.add(deal);
		});

		// 划款指令
		List<WflStlPayOrders> stlPayOrdersDtos = trdRepoDurationMapper.findOrdersByWorkflowId(workflowId);
		stlPayOrdersDtos.forEach(order -> {
			StlPayOrders deal = new StlPayOrders();
			BeanUtils.copyProperties(order, deal);
			deal.setUpdateTime(updateTime);
			deal.setUpdateUser(updateUser);
			stlPayOrdersMapper.insertSelective(deal);
		});

		// 7.4 插入金融产品交易表
		trdProductDeals.forEach(trdProductDeal -> {
			trdProductDeal.setBusiType(DictCodeListExt3.共通业务类型_场外期权.getValue());
			trdProductDeal.setUpdateTime(updateTime);
			trdProductDeal.setUpdateUser(updateUser);
			trdProductDealMapper.insertSelective(trdProductDeal);

			if (StringUtil.isNotEmpty(optionDurationDetailDto.getActPortfolioId())
					&& !StringUtil.equals(optionDurationDetailDto.getPortfolioId(), optionDurationDetailDto.getActPortfolioId())) {
				TrdProductDeal subdeal = new TrdProductDeal();
				BeanUtils.copyProperties(trdProductDeal, subdeal);
				subdeal.setTradeId(trdProductDeal.getTradeId() + "S");
				trdProductDealMapper.insertSelective(subdeal);
			}
		});

		List<TrdProductDealAdd> adds = new ArrayList<>();
		//插入金融产品交易附属表
		List<WflTrdProductDealAdd> trdProductDealAdds = trdRepoDurationMapper.findAddDealByWorkflowId(workflowId);
		trdProductDealAdds.forEach(trdProductDealAdd -> {
			TrdProductDealAdd addDeal = new TrdProductDealAdd();
			BeanUtils.copyProperties(trdProductDealAdd, addDeal);
			addDeal.setPortfolioId(optionDurationDetailDto.getActPortfolioId());
			addDeal.setUpdateTime(updateTime);
			addDeal.setUpdateUser(updateUser);
			addDeal.setPortfolioId(optionDurationDetailDto.getPortfolioId());
			addDeal.setGenType(DictCodeListExt1.生成方式_画面录入.getValue()); // 生成方式
			addDeal.setWorkflowId(optionDurationDetailDto.getWorkflowId()); // 工作流代码
			trdProductDealAddMapper.insertSelective(addDeal);
			adds.add(addDeal);
			if (StringUtil.isNotEmpty(optionDurationDetailDto.getActPortfolioId())
					&& !StringUtil.equals(optionDurationDetailDto.getPortfolioId(), optionDurationDetailDto.getActPortfolioId())) {
				TrdProductDealAdd add = new TrdProductDealAdd();
				BeanUtils.copyProperties(trdProductDealAdd, add);
				add.setTradeId(addDeal.getTradeId() + "S");
				addDeal.setGenType(DictCodeListExt1.生成方式_画面录入.getValue()); // 生成方式
				addDeal.setWorkflowId(optionDurationDetailDto.getWorkflowId()); // 工作流代码
				add.setPortfolioId(optionDurationDetailDto.getActPortfolioId());
				trdProductDealAddMapper.insertSelective(add);
				adds.add(add);
			}
		});

		//金融产品交易汇总扩展业务流程表
		PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
		paTrdFinProductDealTotal.setTotleTradeId(wflTrdFinProductDealTotal.getTotleTradeId()); // 汇总交易编号
		paTrdFinProductDealTotal.setPaBussType(wflTrdFinProductDealTotal.getTgBusiType());
		paTrdFinProductDealTotal.setWorkflowId(wflTrdFinProductDealTotal.getWorkflowId()); // 工作流代码
		paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);

		// 7.3 插入组合交易
		List<PtlTrade> ptlTradeList = new ArrayList<>();
		ptlTrades.forEach(ptlTrade -> {
			PtlTrade deal = new PtlTrade();
			BeanUtils.copyProperties(ptlTrade, deal);
			deal.setUpdateTime(updateTime);
			deal.setUpdateUser(updateUser);
			ptlTradeMapper.insertSelective(deal);
			ptlTradeList.add(deal);
			if (StringUtil.isNotEmpty(optionDurationDetailDto.getActPortfolioId())
					&& !StringUtil.equals(optionDurationDetailDto.getPortfolioId(), optionDurationDetailDto.getActPortfolioId())) {
				PtlTrade deal1 = new PtlTrade();
				BeanUtils.copyProperties(deal, deal1);
				deal1.setPortfolioId(optionDurationDetailDto.getActPortfolioId());
				deal1.setBusiNo(deal.getBusiNo() + "S");
				ptlTradeMapper.insertSelective(deal1);
				ptlTradeList.add(deal1);
			}
		});

		// 7.7 交易报送信息
		List<RepTrade> repTrades = gen交易报送信息(optionDurationDetailDto, wflTrdProductDeals);
		repTrades.forEach(repTrade -> {
			RepTrade deal = new RepTrade();
			BeanUtils.copyProperties(repTrade, deal);
			deal.setUpdateTime(updateTime);
			deal.setUpdateUser(updateUser);
			repTradeMapper.insertSelective(deal);
		});
		// 7.2 删除原业务流程表中的交易记录
		deleteWflByWorkflowId(workflowId);


		// 调接口

		// 一体化对应etliu start
		if (!StringUtil.isEmptyList(trdProductDeals)) { // 母组合交易
			// 调接口
			try {
				List<String> tradeIds = new ArrayList<String>();
				tradeIds.add(optionDurationDetailDto.getTotleTradeId());
				restTemplate.postForObject(
						ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/SettleTransAccountInterf/TransAccountInfo", tradeIds,
						Map.class);
			}catch(Exception e) {

			}

			// 所有交易生效后调用的共通方法
			for (TrdProductDeal deal : trdProductDeals) {
				AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
				inputDto.setProdid(optionDurationDetailDto.getPortfolioId());
				inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
				inputDto.setTrdProductDeal(deal);
				adds.forEach(tradeAdd -> {
					if (deal.getTradeId().equals(tradeAdd.getTradeId())) {
						inputDto.setTrdProductDealAdd(tradeAdd);
					}
				});
				if (StringUtil.isNotEmpty(optionDurationDetailDto.getActPortfolioId())
						&& !StringUtil.equals(optionDurationDetailDto.getPortfolioId(), optionDurationDetailDto.getActPortfolioId())) {
					inputDto.setActPortfolioId(optionDurationDetailDto.getActPortfolioId());
				}
				inputDto.setPtlTradeList(ptlTradeList);
				AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
			}
		}
		// 一体化对应etliu end

		return Boolean.TRUE.toString();
	}

	private void deletePledgeSecPosition(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
		if (wflTrdFinProductDealTotal != null) {
			Map<String, Object> params = new HashMap<>();
			params.put("finprodId", wflTrdFinProductDealTotal.getFinprodId());
			List<RepoInfo> repoInfos = findRepoInfo(params);
			repoInfos.forEach(repoInfo -> {
				PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
				ptlSecPositionFlowDto.setPortfolioId(wflTrdFinProductDealTotal.getPortfolioId());
				ptlSecPositionFlowDto.setSecAcctId(wflTrdFinProductDealTotal.getSecManageAcctId());
				ptlSecPositionFlowDto.setFinprodId(repoInfo.getPledgeFinprodId());
				ptlSecPositionFlowDto.setBranch(wflTrdFinProductDealTotal.getBranch());
				ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_可用.getValue());
				ptlSecPositionFlowDto.setCdate(wflTrdFinProductDealTotal.getSettleDate());
				ptlSecPositionFlowDto.setCcy(wflTrdFinProductDealTotal.getCcy());
				ptlSecPositionFlowDto.setTradeId(wflTrdFinProductDealTotal.getTotleTradeId());
				basePtlSecPositionInfoServiceImpl.deleteSecPosition(ptlSecPositionFlowDto);
			});
		}
	}

	public List<RepoInfo> findRepoInfo(Map<String, Object> params) {
		return trdRepoDurationMapper.findRepoInfo(params);
	}

	private String deleteWflByWorkflowId(String workflowId) {
		periodCommonService.deleteByWorkflowId(workflowId);
		wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
		return Boolean.TRUE.toString();
	}

	private List<PtlTrade> gen组合交易(OptionDurationDetailDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
		List<PtlTrade> result = new ArrayList<>();
		wflTrdProductDeals.forEach(wflTrdProductDeal -> {
			PtlTrade ptlTrade2 = new PtlTrade();
			ptlTrade2.setPortfolioId(entity.getPortfolioId());
			ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
			ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
			ptlTrade2.setBusiId(entity.getFinprodId());
			if(StringUtil.equals(entity.getXqType(),"DQXQ")){
				ptlTrade2.setBusiType(DictCodeListExt2.共通业务类型_期权存续期到期交易.getValue());
			}else if(StringUtil.equals(entity.getXqType(),"TQXQ")){
				ptlTrade2.setBusiType(DictCodeListExt2.共通业务类型_期权存续期提前行权交易.getValue());
			}else{
				ptlTrade2.setBusiType(DictCodeListExt2.共通业务类型_期权存续期放弃行权交易.getValue());
			}
			ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
			ptlTrade2.setSettleDate(wflTrdProductDeal.getSettleDate());
			result.add(ptlTrade2);
		});
		return result;
	}

	/**
	 * 处理交易报送的entity
	 *
	 * @param entity 数据entity
	 * @param wflTrdProductDeals 交易entity
	 * @return 报送数据
	 */
	private List<RepTrade> gen交易报送信息(OptionDurationDetailDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
		// 折算人民币金额（元）
		BigDecimal 最低收盘价 = BigDecimal.ONE;
		if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
			最低收盘价 = periodCommonService.find最低收盘价(entity.getCcy(), DateUtil.format(entity.getSettleDate()));
		}
		BigDecimal 最低收盘价F = 最低收盘价;
		List<RepTrade> repTrades = new ArrayList<>();
		wflTrdProductDeals.forEach(deal -> {
			RepTrade repTrade = new RepTrade();
			//repTrade.setRepFinprodRegId(产品登记编码);
			// 发行机构代码
			//repTrade.setRepIssuePartyId(entity.getRepIssuePartyId());
			// 行内交易编码
			repTrade.setRepBankTradeCode(deal.getTradeId());
			// 行内资产/负债编码
			//repTrade.setRepBankAssetCode(entity.getFinprodMarketId());
			// 币种
			repTrade.setRepCcy(entity.getCcy());
			// 对手方 隐藏发行人
			//repTrade.setRepTradeComponent(entity.getIssuer());
			// 合同号
			repTrade.setRepDelCode(deal.getTradeId());
			// 备注
			repTrade.setRepRemark(entity.getRemark());
			// 报送状态
			repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
			// 数据日期
			repTrade.setRepStatusDate(platformDateImpl.getSysDate());
			// 所有场景生成第一种报送数据
			if (!StringUtil.equals(entity.getOperateType(), "04")) {
				// 第一笔
				// 数量
				repTrade.setRepCount(deal.getPrinAmt());
				// 交易日
				repTrade.setRepTradeDate(deal.getTradeDate());
				// 折算人民币
				repTrade.setRepMoneyCny(ArithmeticUtil.multiply(deal.getIntAmt(), 最低收盘价F));
				// 发生金额
				repTrade.setRepOccurMoney(deal.getIntAmt());
				// 资金流动类型
				repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
				repTrades.add(repTrade);
			}
			// 货币基金红利再投及非货币红利再投时，生成第二种报送数据
			if (StringUtil.equals(entity.getOperateType(), "01") || StringUtil.equals(entity.getOperateType(), "04")) {
				RepTrade repTrade2 = new RepTrade();
				BeanUtil.copyPropertiesIgnoreNull(repTrade2, repTrade);
				// 第二笔
				repTrade2.setRepBankTradeCode(deal.getTradeId());
				// 数量
				repTrade.setRepCount(deal.getShareAmt());
				// 交易日
				repTrade.setRepTradeDate(deal.getSettleDate());
				// 折算人民币
				repTrade2.setRepMoneyCny(ArithmeticUtil.multiply(deal.getIntAmt(), 最低收盘价F));
				// 发生金额
				repTrade2.setRepOccurMoney(deal.getIntAmt());
				repTrade2.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_资产买入.getValue());
				repTrades.add(repTrade2);
			}
		});
		return repTrades;
	}

	public Page<SelectOptionDto> getoption(int page, int length, Map<String, Object> param) {
		return this.queryForPageForSelectReturn(page, length,
			() -> trdOptionDurationMapper.getoption(param));
	}

	/**
	 * 获取entity <功能详细描述>
	 *
	 * @param map 参数
	 * @return 返回值
	 * @see
	 * @since 1.0
	 */
	public OptionDurationDetailDto getEntityByMap(Map<String, Object> map) {
		map.put("PAGE_DATA_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
		return trdOptionDurationMapper.getEntityByMap(map);
	}
	
	public int revokeCheck(Map<String, Object> param){
		param.put("TRADE_STATUS03", "03");
		return trdOptionDurationMapper.revokeCheck(param);
	}

}
