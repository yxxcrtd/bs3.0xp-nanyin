package com.joyintech.tams.invest.period.trdstockconnectbonus.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @see TrdStockConnectBonus002Service
 */
@Service
public class TrdStockConnectBonus002Service extends BaseService< TrdStockConnectBonus002Dto>
	implements BaseProcessService< TrdStockConnectBonus002Dto>{

	
	@Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;
	
	@Autowired
	private  TrdStockConnectBonusDealEntity dealEntityService;
	
	/**
     * 划款指令共通
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    /**
     * jixu接口
     */
    @Autowired
    private ValuationInterfService valuationInterfService;

	@Autowired
	private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

	/**
	 * 分红明细计算
	 */
	public void calNumAndAmt(TrdStockConnectBonus002Dto dto) {

		for( FinAssetIntInfo finAssetIntInfo: dto.getFinAssetIntInfoList()){
			//送转
			if(StringUtil.equals(dto.getPs(),DictCodeList.股票分红类型_送转.getValue()) && dto.getUnitCprice() != null) {
				finAssetIntInfo.setShareAmt(finAssetIntInfo.getRegDateAmt().multiply(dto.getUnitCprice()).setScale( 0, BigDecimal.ROUND_DOWN ));
			}
			//分红
			else if(StringUtil.equals(dto.getPs(),DictCodeList.股票分红类型_现金分红.getValue())){
				if(StringUtil.equals(dto.getIfDividend(), DictCodeList.是否_是.getValue())){
					if(finAssetIntInfo.getReplaceAmt() != null &&  dto.getReplacePrice()!=null &&  dto.getExchangeRate() !=null){
						finAssetIntInfo.setShareAmt(finAssetIntInfo.getReplaceAmt().multiply(dto.getUnitInt()).divide(dto.getReplacePrice(),14,BigDecimal.ROUND_HALF_UP).setScale( 0, BigDecimal.ROUND_DOWN ));
						finAssetIntInfo.setFpriceAmt((dto.getUnitInt().multiply(finAssetIntInfo.getRegDateAmt())
								.subtract(finAssetIntInfo.getShareAmt().multiply(dto.getReplacePrice())))
								.multiply(dto.getExchangeRate()));
					}
					else {
						finAssetIntInfo.setShareAmt(null);
						finAssetIntInfo.setFpriceAmt(null);
					}

				}
				else if(StringUtil.equals(dto.getIfDividend(), DictCodeList.是否_否.getValue())){
					if(dto.getUnitInt() !=null  && dto.getExchangeRate()!=null){
						finAssetIntInfo.setFpriceAmt(finAssetIntInfo.getRegDateAmt().multiply(dto.getUnitInt()).multiply(dto.getExchangeRate()));
					}
					else{
						finAssetIntInfo.setFpriceAmt(null);
					}
				}
			}
		}
	}

	/**
	 * 获取待处理股票分红主体信息
	 */
	public  TrdStockConnectBonus002Dto findNeedDealInfo(Map<String, Object> param) {
		return restTemplate.postForObject(
				ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findNeedDealInfo",
				param,  TrdStockConnectBonus002Dto.class);
	}

	/**
	 * 获取有效、撤销状态股票分红主体信息
	 */
	public  TrdStockConnectBonus002Dto findEffectiveInfo(Map<String, Object> param) {
		return restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findEffectiveInfo",
	            param,  TrdStockConnectBonus002Dto.class);
	}

	/**
	 * 获取流程中股票分红主体信息
	 */
	public  TrdStockConnectBonus002Dto findProcessInfo(Map<String, Object> param) {
		return restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findProcessInfo",
	            param,  TrdStockConnectBonus002Dto.class);
	}
	
	
	/**
	 * 获取金融产品交易Entity
	 */
	public List<TrdProductDeal> finPrdDealInfo(String busiCode) {
		return restTemplate.postForList(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/finPrdDealInfo",
	            busiCode, TrdProductDeal.class);
	}


	/**
	 * 获取组合交易Entity
	 */
	public PtlTrade findPtlTradeInfo(String tradeId) {
		return restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findPtlTradeInfo",
	            tradeId, PtlTrade.class);
	}


	/**
	 * 获取金融产品交易附属Entity
	 */
	public TrdProductDealAdd findPrdDealAddInfo(String tradeId) {
		return restTemplate.postForObject(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findPrdDealAddInfo",
	            tradeId, TrdProductDealAdd.class);
	}


	/**
	 * 获取划款指令Entity
	 */
	public List<StlPayOrders> findStlPayOrdInfo(String tradeId) {
		return restTemplate.postForList(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findStlPayOrdInfo",
	            tradeId, StlPayOrders.class);
	}


	/**
	 * 待处理明细分红明细
	 */
	public List<FinAssetIntInfo> findNeedDealDetailInfo(Map<String, Object> param) {
		return restTemplate.postForList(
				ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findNeedDealDetailInfo",
				param, FinAssetIntInfo.class);
	}

	/**
	 * 查看页面分红明细
	 */
	public List<FinAssetIntInfo> findDividDetailInfo(String busiCode) {
		return restTemplate.postForList(
	            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findDividDetailInfo",
	            busiCode, FinAssetIntInfo.class);
	}

	/**
	 * 流程中明细分红明细
	 */
	public List<FinAssetIntInfo> findProcessDividInfo(String busiCode) {
		return restTemplate.postForList(
				ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findProcessDividInfo",
				busiCode, FinAssetIntInfo.class);
	}


	/**
	 * 新建-分红明细查询
	 */
	public List<FinAssetIntInfo> findividInfo(Map<String, Object> pageParam) {
		pageParam.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        return restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/findividInfo", pageParam,
				FinAssetIntInfo.class);
	}

	/**
	 * 流程
	 */
	@Override
	public void doInsert( TrdStockConnectBonus002Dto entity, OperationType type) {
		saveDataWhenStartWorkflow(entity, type);
	}

	/**
	 * 工作流 流程中
	 */
	@Override
	public void doUpdate( TrdStockConnectBonus002Dto entity) {
		entity.setCreateDept(LoginUserUtil.getDeptCode());
		ProcessMark newEntity = null;
		saveDataWhenStartWorkflow(entity, OperationType.NEW);
	}

	/**
	 * 代办中提交及保存操作处理
	 */
	private void saveDataWhenStartWorkflow( TrdStockConnectBonus002Dto entity, OperationType type) {
		entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
		if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
			restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/doInsert",
					entity.getBaseTotalTradeInfoDto(), String.class);

			dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_流程中);
		}else if (type == OperationType.REVOKE){
			restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/doRevoke", entity,
	                String.class);
		}
		
	}

	@Override
	public void doComplete( TrdStockConnectBonus002Dto entity, OperationType type) {
		entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
		entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
		TrdCommDto trdcom = new TrdCommDto();
        if (OperationType.REVOKE == type) {
        	dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销);
            entity.setOperateType(BussinessConstant.REVOKE);
            trdcom.setOperateType(BussinessConstant.OPERATE_TYPE_DELETE);
        }
        else {
        	dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_有效);
            trdcom.setOperateType(BussinessConstant.OPERATE_TYPE_ADD);
        }
        // 新建交易撤销数据 或 插入Entity到对应的业务DB中, 并删除流程表中的数据
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/doComplete", entity,
            String.class);
        //调用jixu接口
        trdcom.setTrdFinProductDealTotal(entity.getBaseTotalTradeInfoDto().getTrdFinProductDealTotal());
        List<BaseTradeInfoDto> baseTradeInfoDtoList = entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
        // 子组合jixu出账bug对应 modify by renping 20200325 start
        List< TrdStockConnectBonus002Dto> trdProductDeals =  new ArrayList<>();
        if(OperationType.REVOKE == type){
            Map<String, Object> map = new HashMap<>();
            map.put("totleTradeId", entity.getpTradeId());
    		trdProductDeals = restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockConnectBonus/getRTrdProductDeals", map,
    		     TrdStockConnectBonus002Dto.class);
        }
        // 子组合jixu出账bug对应 modify by renping 20200325 end
        for (BaseTradeInfoDto baseTradeInfoDto:baseTradeInfoDtoList
        ) {
            if(StringUtil.equals(DictCodeList.交易方向_现金分红.getValue(),baseTradeInfoDto.getTrdProductDeal().getPs())
                    ||StringUtil.equals(DictCodeList.交易方向_红利再投.getValue(),baseTradeInfoDto.getTrdProductDeal().getPs())) {
                if(baseTradeInfoDto.getStlPayOrders()!=null){
                	trdcom.setStlPayOrdersList(Arrays.asList(baseTradeInfoDto.getStlPayOrders()));
                }
                if(OperationType.REVOKE == type&&trdProductDeals!=null && !trdProductDeals.isEmpty()){
                    // 子组合jixu出账bug对应 modify by renping 20200325 start
                    for ( TrdStockConnectBonus002Dto deal : trdProductDeals) {
                        // 交易方向相同、组合相同、证券账户相同才能算是同一种交易
                        if (StringUtil.equals(deal.getPs(),
                            baseTradeInfoDto.getTrdProductDeal().getPs())
                            && StringUtil.equals(deal.getPortfolioId(),
                                baseTradeInfoDto.getTrdProductDealAdd().getPortfolioId())
                            && ((StringUtil.isEmpty(deal.getSecManageAcctId())
                                 && StringUtil.isEmpty(
                                     baseTradeInfoDto.getTrdProductDeal().getSecManageAcctId()))
                                || StringUtil.equals(deal.getSecManageAcctId(),
                                    baseTradeInfoDto.getTrdProductDeal().getSecManageAcctId()))) {
                            baseTradeInfoDto.getTrdProductDeal().setTradeId(
                                (String)deal.getBusiCode());
                            trdcom.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
                        }
                    }
                    // 子组合jixu出账bug对应 modify by renping 20200325 end
                }else{
                	trdcom.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
                }
                trdcom.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
                trdcom.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
//                valuationInterfService.runValInvest2Jixu(trdcom);
            }
            // 子组合jixu出账bug对应 add by renping 20200325 start
            // 如果有子组合交易时，子组合交易也需要调用jixu
            if (StringUtil.isEmptyList(baseTradeInfoDto.getBaseTradeInfoDtoList())) {
                continue;
            }
            for (BaseTradeInfoDto baseTradeInfoDtoSub : baseTradeInfoDto.getBaseTradeInfoDtoList()) {
                if (baseTradeInfoDtoSub.getTrdProductDeal() == null) {
                    continue;
                }
                if (StringUtil.equals(DictCodeList.交易方向_现金分红.getValue(),
                    baseTradeInfoDtoSub.getTrdProductDeal().getPs())
                    || StringUtil.equals(DictCodeList.交易方向_红利再投.getValue(),
                        baseTradeInfoDtoSub.getTrdProductDeal().getPs())) {
                    if (baseTradeInfoDtoSub.getStlPayOrders() != null) {
                        trdcom.setStlPayOrdersList(
                            Arrays.asList(baseTradeInfoDtoSub.getStlPayOrders()));
                    }
                    if (OperationType.REVOKE == type && trdProductDeals != null
                        && !trdProductDeals.isEmpty()) {
                        for ( TrdStockConnectBonus002Dto deal : trdProductDeals) {
                            // 交易方向相同、组合相同、证券账户相同才能算是同一种交易
                            if (StringUtil.equals(deal.getPs(),
                                baseTradeInfoDtoSub.getTrdProductDeal().getPs())
                                && StringUtil.equals(deal.getPortfolioId(),
                                    baseTradeInfoDtoSub.getTrdProductDealAdd().getPortfolioId())
                                && ((StringUtil.isEmpty(deal.getSecManageAcctId())
                                     && StringUtil.isEmpty(
                                         baseTradeInfoDtoSub.getTrdProductDeal().getSecManageAcctId()))
                                    || StringUtil.equals(deal.getSecManageAcctId(),
                                        baseTradeInfoDtoSub.getTrdProductDeal().getSecManageAcctId()))) {
                                baseTradeInfoDtoSub.getTrdProductDeal().setTradeId(
                                    (String)deal.getBusiCode());
                                trdcom.setTrdProductDeal(baseTradeInfoDtoSub.getTrdProductDeal());
                            }
                        }
                    }
                    else {
                        trdcom.setTrdProductDeal(baseTradeInfoDtoSub.getTrdProductDeal());
                    }
                    trdcom.setTrdProductDealAdd(baseTradeInfoDtoSub.getTrdProductDealAdd());
                    trdcom.setPtlTradeList(baseTradeInfoDtoSub.getPtlTrade());
//                    valuationInterfService.runValInvest2Jixu(trdcom);
                }
            }
            // 子组合jixu出账bug对应 add by renping 20200325 end
        }
		
	}

	
	
	/**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * 汇总费用明细 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 费用明细 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
	public BaseTotalTradeInfoDto genFinProductTradeDto( TrdStockConnectBonus002Dto entity) {
		BaseTotalTradeInfoDto result = dealEntityService.genBaseTotalTradeInfoDto(entity);
        return result;
	}

	
	/**
	 * 生成划款指令
	 * <功能详细描述>
	 * @param stlPayOrdersList 划款指令list
	 * @param dealAdd 交易附属
	 * @param entity 业务entity
	 * @return 生成结果
	 * @see
	 * @since 1.0
	 */
	private StlPayOrders gen划款指令流程2(List<BussTrdTransferOrderDto> stlPayOrdersList, TrdProductDealAdd dealAdd,
			 TrdStockConnectBonus002Dto entity) {
		StlPayOrders stlPayOrders = null;
        for (BussTrdTransferOrderDto dto : stlPayOrdersList) {
            if (dto.getPortfolioId().equals(dealAdd.getPortfolioId())) {
                stlPayOrders = dto;
                stlPayOrders.setOrderStatus(DictCodeList.清算操作类型_新建.getValue());
                stlPayOrders.setTradeId(dealAdd.getTradeId());
                stlPayOrders.setWorkflowId(entity.getWorkflowId());
            }
        }
        return stlPayOrders;
	}


	@Override
	public void doBack( TrdStockConnectBonus002Dto entity) {
		if (baseEtrCashPositionCommService != null) {
			baseEtrCashPositionCommService.revokePosition(entity.getTotleTradeId());
		}
	}

	@Override
	public void doRevoke( TrdStockConnectBonus002Dto entity) {
		if (baseEtrCashPositionCommService != null) {
			baseEtrCashPositionCommService.revokePosition(entity.getTotleTradeId());
		}
	}

	@Override
	public void doDelete(String workflowId) {
		baseTradeProductDealCommServiceImpl.delete(workflowId);
	}
}
