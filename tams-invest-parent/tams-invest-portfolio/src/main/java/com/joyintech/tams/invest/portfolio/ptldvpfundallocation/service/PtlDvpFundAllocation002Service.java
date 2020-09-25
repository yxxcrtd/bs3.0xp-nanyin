/*
 * 文件名：PtlDvpFundAllocation002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptldvpfundallocation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.StlDvpOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.invest.interf.service.InterfComponentService;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DVP资金调拨新建Service
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlDvpFundAllocation002Service
 * @since 1.0
 */
@Service("PtlDvpFundAllocation002Service")
@Transactional(rollbackFor = Exception.class)
public class PtlDvpFundAllocation002Service extends BaseService<PtlDvpFundAllocationDto>
		implements BaseProcessService<PtlDvpFundAllocationDto> {
	/**
	 * 业务编号生成类
	 */
	@Autowired(required = false)
	public BaseGetBusinessId baseGetBussinessId;

	/**
	 * 划款指令
	 */
	@Autowired(required = false)
	private BussTrdTransferOrderService bussTrdTransferOrderService;

	/**
	 * StlDvpOrdersMapper
	 */
	@Autowired(required = false)
	private StlDvpOrdersMapper stlDvpOrdersMapper;

	/**
	 * TrdProductDealMapper
	 */
	@Autowired(required = false)
	private TrdProductDealMapper trdProductDealMapper;

	/**
	 * CommonBusinessService
	 */
	@Autowired
	private CommonBusinessService commonBusinessService;
	/**
	 * {@linkplain}
	 */
	@Autowired
	private InterfComponentService interfComponentService;
	
	/**
	 * BussTrdAvailableCashQueryService
	 */
    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

	/**
	 * 查询画面详细信息
	 *
	 * @param orderId 指令编号
	 * @return 画面详细信息
	 * @see
	 * @since 1.0
	 */
	public PtlDvpFundAllocationDto getTradeDetailData(String orderId) {
		PtlDvpFundAllocationDto dto = new PtlDvpFundAllocationDto();

		// 查询交易编号(根据指令编号)
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId", orderId);

		String tradeId = restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/getTradeId", paramMap, String.class);

		StlDvpOrders stlDvpOrders = stlDvpOrdersMapper.selectByPrimaryKey(orderId);
		BeanUtil.copyPropertiesIgnoreNull(dto, stlDvpOrders);
		if (StringUtil.equals(BussinessDictCodeList.收支方向_支出.getValue(), stlDvpOrders.getPs())) {
			dto.setMyAcctNo(stlDvpOrders.getMyAcctNo());
			dto.setCounterAcctNo(stlDvpOrders.getCounterAcctNo());
		} else {
			dto.setMyAcctNo(stlDvpOrders.getCounterAcctNo());
			dto.setCounterAcctNo(stlDvpOrders.getMyAcctNo());
		}

		TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);
		dto.setPayType(trdProductDeal.getPs());
		dto.setRemark(trdProductDeal.getRemark());

		dto.setBusiNo(orderId);
		dto.setPortfolioId(stlDvpOrders.getPortfolioId());

		// 获取Dvp指令
		paramMap.put("bizCode", tradeId);
		List<BussTrdTransferOrderDto> orderList = restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/selectOrdersByBizCode", paramMap,
				BussTrdTransferOrderDto.class);
		dto.setBussTrdTransferOrder(orderList);

		return dto;
	}

	/**
	 * 查询收款账号
	 * 
	 * @return 收款账号集合
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getCounterAcctNo(Map<String, Object> map) {
		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/getCounterAcctNo", map,
				SelectOptionDto.class);
	}

	/**
	 * 查询付款账号
	 * 
	 * @return 付款账号集合
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getMyAcctNo(Map<String, Object> map) {
		return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/getMyAcctNo",
				map, SelectOptionDto.class);
	}

	/**
	 * 获取账户余额
	 * 
	 * @return 账户余额
	 * @see
	 * @since 1.0
	 */
	public BigDecimal getAmount(Map<String, Object> map) {
		String portfolioId = (String) map.get("portfolioId");
		String ccy = (String) map.get("ccy");
		String acctId = (String) map.get("acctId");
		String cdate = (String) map.get("cdate");
		String payType = (String) map.get("payType");
		String calcAcctName = (String) map.get("calcAcctName");
		
		// 托管账户的情况
		if (StringUtil.equals(BussinessDictCodeList.交易方向_托管户_上清DVP.getValue(), payType)
				|| StringUtil.equals(BussinessDictCodeList.交易方向_托管户_中债DVP.getValue(), payType)) {
			if (StringUtil.equals("myAcctNo", calcAcctName)) {
				return this.getTuoGuanHuAmout(portfolioId, ccy, cdate, acctId);
			}
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_上清DVP_托管户.getValue(), payType)
				|| StringUtil.equals(BussinessDictCodeList.交易方向_中债DVP_托管户.getValue(), payType)) {
			if (StringUtil.equals("counterAcctNo", calcAcctName)) {
			    return this.getTuoGuanHuAmout(portfolioId, ccy, cdate, acctId);
			}
		}

		return commonBusinessService.getDvpAcctAmount(portfolioId, ccy, acctId, cdate);
	}
	
	/**
	 * 获取托管户余额
	 *
	 * @param portfolioId 组合代码
	 * @param ccy 币种
	 * @param tradeDate 交易日
	 * @param acctId 账户代码
	 * @return 托管户余额
	 * @see
	 * @since 1.0
	 */
	private BigDecimal getTuoGuanHuAmout(String portfolioId, String ccy, String tradeDate, String acctId) {
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("portfolioId", portfolioId);
        paramMap.put("ccy", ccy);
        paramMap.put("tradeDate", tradeDate);
        paramMap.put("acctTypeList", BussinessDictCodeList.资金账户类型_托管账户.getValue());
        paramMap.put("acctId", acctId);
        
        @SuppressWarnings("unchecked")
        Map<String, String> resultMap = bussTrdAvailableCashQueryService.getAvailableCash(paramMap);
        if (resultMap != null && resultMap.containsKey("T_0")) {
            String tempResult = (String) resultMap.get("T_0");
            if (StringUtil.isNotBlank(tempResult)) {
                return new BigDecimal(tempResult);
            }
        }

        return null;
	}

	/**
	 * 根据收/付款账户同步3025接口对应的数据
	 * 
	 * @param acctNo 收/付款账户
	 */
//	private void cashInfSel(String acctNo) {
//		CashDto cashDto = new CashDto();
//		cashDto.setAcctNo(acctNo);
//		List<CashDto> cashList = new ArrayList<>();
//		CashDto cash = new CashDto();
//		cash.setAcctNo(cashDto.getAcctNo());
//		cashList.add(cash);
//		cashDto.setCashList(cashList);
//		interfComponentService.cashInfSel(cashDto);
//	}

	/**
	 * 实体类的合成
	 * 
	 * @param dto 资金划拨实体类
	 * @return 资金划拨实体类
	 * @see
	 * @since 1.0
	 */
	public PtlDvpFundAllocationDto setEntity(PtlDvpFundAllocationDto dto) {
		dto.setActPortfolioId(dto.getPortfolioId());
		dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));

		PtlDvpFundAllocationDto ptlDvpFundAllocationDto = new PtlDvpFundAllocationDto();
		BeanUtil.copyPropertiesIgnoreNull(ptlDvpFundAllocationDto, dto);

		// 先处理付款账户，生成可用持仓流水，生效后处理成实际及收款账户
		/**
		 * 组合现金流水
		 */
		List<PtlCashPositionFlow> ptlCashPositionFlows = new ArrayList<>();
		PtlCashPositionFlow ptlCashPositionFlow = new PtlCashPositionFlow();
		ptlCashPositionFlow.setPortfolioId(dto.getPortfolioId());
		ptlCashPositionFlow.setAcctId(dto.getMyAcctNo());
		ptlCashPositionFlow.setAmount(ArithmeticUtil.multiply(dto.getSettleAmt(), new BigDecimal("-1")));
		ptlCashPositionFlow.setCcy(dto.getCcy());
		ptlCashPositionFlow.setHodingType(BussinessDictCodeList.持仓类型_可用.getValue());
		ptlCashPositionFlow.setCdate(dto.getSettleDate());
		ptlCashPositionFlow.setOrderId(dto.getBussTrdTransferOrder().get(0).getOrderId());
		BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
		bussinessCodeParameter.setSeqNo("9999");
		String seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter)
				: IDGenerator.generate();
		ptlCashPositionFlow.setSeqno(seqno);
		ptlCashPositionFlows.add(ptlCashPositionFlow);
		if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
			PtlCashPositionFlow ptlCashPositionFlow1 = new PtlCashPositionFlow();
			BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlow1, ptlCashPositionFlow);
			ptlCashPositionFlow1.setSeqno(baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter)
					: IDGenerator.generate());
			ptlCashPositionFlow1.setPortfolioId(dto.getActPortfolioId());
			ptlCashPositionFlows.add(ptlCashPositionFlow1);
		}

		ptlDvpFundAllocationDto.setPtlCashPositionFlow(ptlCashPositionFlows);

		/**
		 * 组合现金余额
		 */
		List<PtlCashPosition> ptlCashPositions = new ArrayList<>();
		PtlCashPosition ptlCashPosition = new PtlCashPosition();
		ptlCashPosition.setAcctId(dto.getMyAcctNo());
		ptlCashPosition.setAmount(ptlCashPositionFlow.getAmount());
		ptlCashPosition.setCcy(dto.getCcy());
		ptlCashPosition.setPortfolioId(dto.getPortfolioId());
		ptlCashPosition.setHodingType(BussinessDictCodeList.持仓类型_实际.getValue());
		ptlCashPosition.setCdate(dto.getSettleDate());
		ptlCashPositions.add(ptlCashPosition);

		if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
			PtlCashPosition ptlCashPosition1 = new PtlCashPosition();
			BeanUtil.copyPropertiesIgnoreNull(ptlCashPosition1, ptlCashPosition);
			ptlCashPosition1.setPortfolioId(dto.getActPortfolioId());
			ptlCashPositions.add(ptlCashPosition1);
		}

		ptlDvpFundAllocationDto.setPtlCashPosition(ptlCashPositions);

		/**
		 * 组合交易
		 */
		List<PtlTrade> ptlTrades = new ArrayList<>();
		PtlTrade ptlTrade = new PtlTrade();
		ptlTrade.setPortfolioId(dto.getPortfolioId());
		ptlTrade.setBusiNo(dto.getTradeId());
		ptlTrade.setBusiTable(BussinessDictCodeList.业务表_交易资金指令表.getValue());
		ptlTrade.setBusiType(BussinessDictCodeList.平安划款类型_DVP划款.getValue());
		ptlTrades.add(ptlTrade);

		if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
			PtlTrade ptlTrade1 = new PtlTrade();
			BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
			ptlTrade1.setPortfolioId(dto.getActPortfolioId());
			ptlTrades.add(ptlTrade1);
		}
		ptlDvpFundAllocationDto.setPtlTrade(ptlTrades);

		/**
		 * 金融产品交易
		 */
		TrdProductDeal trdProductDeal = new TrdProductDeal();
		trdProductDeal.setTradeId(dto.getTradeId());
		trdProductDeal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
		trdProductDeal.setCcy(dto.getCcy());
		trdProductDeal.setPs(dto.getPayType());
		trdProductDeal.setIsCancel(BussinessDictCodeList.是否_否.getValue());
		trdProductDeal.setTradeDate(dto.getSettleDate());
		trdProductDeal.setSettleDate(dto.getSettleDate());
		trdProductDeal.setRemark(dto.getRemark());
		trdProductDeal.setTradeAmt(dto.getSettleAmt());
		ptlDvpFundAllocationDto.setTrdProductDeal(trdProductDeal);

		// 交易附属表
		TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
		trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
		trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
		trdProductDealAdd.setGenType(BussinessDictCodeList.生成方式_画面录入.getValue());
		ptlDvpFundAllocationDto.setTrdProductDealAdd(trdProductDealAdd);

		/**
		 * 金融产品交易扩展表
		 */
		PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
		paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
		if (StringUtil.equals(BussinessDictCodeList.交易方向_托管户_上清DVP.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_托管户_上清DVP.getValue());
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_托管户_中债DVP.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_托管户_中债DVP.getValue());
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_上清DVP_托管户.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_上清DVP_托管户.getValue());
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_中债DVP_托管户.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_中债DVP_托管户.getValue());
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_上清DVP_中债DVP.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_上清DVP_中债DVP.getValue());
		} else if (StringUtil.equals(BussinessDictCodeList.交易方向_中债DVP_上清DVP.getValue(), dto.getPayType())) {
			paTrdProductDeal.setPaBussType(BussinessDictCodeList.平安业务类型_DVP资金调拨_中债DVP_上清DVP.getValue());
		}
		ptlDvpFundAllocationDto.setPaTrdProductDeal(paTrdProductDeal);

		if (bussTrdTransferOrderService != null) {
			dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
			StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
			BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, dto.getBussTrdTransferOrder().get(0));
			stlPayOrdersDto.setTradeId(dto.getTradeId());
			// 本方账户、对手方账户
			stlPayOrdersDto.setMyAcctNo(dto.getMyAcctNo());
			stlPayOrdersDto.setCounterAcctNo(dto.getCounterAcctNo());

			List<StlPayOrdersDto> list = new ArrayList<>();
			list.add(stlPayOrdersDto);
			ptlDvpFundAllocationDto.setStlPayOrdersList(list);
		}
		ptlDvpFundAllocationDto.setOperateType("NEW");
		ptlDvpFundAllocationDto.setRemark(dto.getRemark());

		return ptlDvpFundAllocationDto;
	}

	@Override
	public void doInsert(PtlDvpFundAllocationDto entity, OperationType operationType) {
	    if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
	        entity.setOperateType(operationType.name());
	        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/doInsert",
	            setEntity(entity), Integer.class);
	    }
	}

	@Override
	public void doUpdate(PtlDvpFundAllocationDto entity) {
		restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/doUpdate",
				setEntity(entity), Integer.class);
	}

	@Override
	public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
//		restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/doDelete", workflowId, CommonResult.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(PtlDvpFundAllocationDto entity, OperationType operationType) {
		entity.setOperateType(operationType.name());
		
		PtlDvpFundAllocationDto tempParaDto = entity;
		if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
		    tempParaDto = this.setEntity(entity);
		}

		restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlDvpFundAllocation/doComplete",
		    tempParaDto, Integer.class);
		if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
			// 调用托管网银接口
			List<StlDvpOrders> stlDvpOrdersList = new ArrayList<>();
			entity.getBussTrdTransferOrder().forEach(item -> {
			    StlDvpOrders stlDvpOrders = new StlDvpOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlDvpOrders, item);
                // 处理ps，收支方向，以及是否需要发送
                /*if (StringUtil.equals(BussinessDictCodeList.交易方向_上清DVP_托管户.getValue(), entity.getPayType())
                        || StringUtil.equals(BussinessDictCodeList.交易方向_中债DVP_托管户.getValue(), entity.getPayType())) {
                    stlDvpOrders.setMyAcctNo(item.getCounterAcctNo());
                    stlDvpOrders.setCounterAcctNo(item.getMyAcctNo());
                    stlDvpOrders.setPs(BussinessDictCodeList.收支方向_收入.getValue());
                } else {
                    stlDvpOrders.setMyAcctNo(item.getMyAcctNo());
                    stlDvpOrders.setCounterAcctNo(item.getCounterAcctNo());
                    stlDvpOrders.setPs(BussinessDictCodeList.收支方向_支出.getValue());
                }*/
			    stlDvpOrdersList.add(stlDvpOrders);
			});
			interfComponentService.sendCusdepOrderDvp(stlDvpOrdersList, false, entity.getPayType());
		}
	}

	@Override
	public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
		if (isMutiApproval && entity.getCurrentUser() != null && entity.getCurrentUser().equals(entity.getNextUser())) {
			return getMessage("BUSS_COM_E_0100", entity.getTradeId());
		}
		return null;
	}

	@Override
	public void doBack(PtlDvpFundAllocationDto entity) {
		AllTradeCommonUtil.deletePositionByOrderId(entity.getOrderId());
	}

	@Override
	public void doRevoke(PtlDvpFundAllocationDto entity) {
		AllTradeCommonUtil.deletePositionByOrderId(entity.getOrderId());
	}
}
