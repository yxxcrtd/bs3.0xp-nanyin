package com.joyintech.service.period.trdlendduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trdlendduration.dao.TrdLendDurationMapper;
import com.joyintech.service.period.trdlendduration.dto.TrdLendDurationDto;
import com.joyintech.service.period.trdrepoduration.dao.TrdRepoDurationMapper;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * 拆借存续期查询 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年9月15日
 * @see TrdLendDurationService
 * @since 1.0
 */
@Service
public class TrdLendDurationService extends BaseService<TrdLendDurationDto> {
    /**
     * mapper
     */
    @Autowired
    private TrdLendDurationMapper trdLendDurationMapper;

    /**
     * 系统Dao
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    /**
     * 系统Dao
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    /**
     * 系统Dao(流程表)
     */
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    @Autowired
    private TrdRepoDurationMapper trdRepoDurationMapper;
    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private RepTradeMapper repTradeMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;
	/**
	 * 交易附属
	 */
	@Autowired
	private TrdProductDealAddMapper trdProductDealAddMapper;
    /**
     * 平台日
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    /**
     * 查询组合代码 <功能详细描述>
     * 
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> map) {
        return trdLendDurationMapper.getPortfolioId(map);
    }

    /**
     * 交易对手下拉菜单 <功能详细描述>
     * 
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterId(Map<String, Object> map) {
        return trdLendDurationMapper.getCounterId(map);
    }

    /**
     * 请求画面 <功能详细描述>
     * 
     * @param length
     * @param page
     * @param map    参数
     * @return 结果值
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<TrdLendDurationDto> selectForPage(int page, int length, Map<String, Object> map) throws Exception {
        setParams(map);
        return super.queryForPage(page, length, () -> trdLendDurationMapper.findPage(map));
    }

    /**
     * 查询所有数据
     *
     * @param map 参数
     * @return 所有数据
     * @see
     * @since 1.0
     */
    public List<TrdLendDurationDto> selectAllDataList(Map<String, Object> map) {
        setParams(map);
        return trdLendDurationMapper.findPage(map);
    }

    private void setParams(Map<String, Object> param) {
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        param.put("COMMON_BUSINESS_TYPETRD11", DictCodeList.共通业务类型_拆借存续期交易.getValue());
        param.put("FINPROD_TYPE_TGF08", DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
        param.put("FINPROD_TYPE_TGF09", DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
        param.put("FINPROD_TYPE_TGF25", DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue());
        param.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        param.put("PS_T07", DictCodeList.交易方向_到期.getValue());
        param.put("PS_T09", DictCodeList.交易方向_收息.getValue());
        param.put("PS_HBDZ", DictCodeList.交易方向_还本到账.getValue());
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        param.put("PST07", DictCodeList.交易方向_到期.getValue());
        param.put("SysDate", iPlatformDate.getSysDate());
        // 一体化对应etliu start
//        param.put("COM_TYPE_TRD03", DictCodeList.共通业务类型_拆借投资交易.getValue());
        param.put("COM_TYPE_TRD03", DictCodeList.业务类型_拆借交易.getValue());
        // 一体化对应etliu end
        if (StringUtil.isNotEmpty((String) param.get("portfolioId"))) {
            param.replace("portfolioId", Arrays.asList(param.get("portfolioId").toString().split(",")));
        } else {
            param.replace("portfolioId", null);
            if (StringUtil.isNotEmpty((String) param.get("portfolioId"))) {
                param.replace("portfolioId", Arrays.asList(param.get("portfolioId").toString().split(",")));
            } else {
                param.replace("portfolioId", null);
            }
        }
    }

    /**
     * 获取entity <功能详细描述>
     * 
     * @param map 参数
     * @return 返回值
     * @see
     * @since 1.0
     */
    public TrdLendDurationDto getEntityByMap(Map<String, Object> map) {
        setParams(map);
        return trdLendDurationMapper.getEntityByMap(map);
    }



	/**
	 * 工作流结束
	 * <功能详细描述>
	 * @param trdLendDurationDto dto
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
    @Transactional(rollbackFor = Exception.class)
	public String doComplete(TrdLendDurationDto trdLendDurationDto) {
		String workflowId = trdLendDurationDto.getWorkflowId();
		//撤销时查找授信交易编号
        String tradeid = trdLendDurationMapper.findCeTradeIdByWorkflowId(workflowId);
		String updateUser = trdLendDurationDto.getUpdateUser();
        Date updateTime = new Date();
        if (BussinessConstant.REVOKE.equalsIgnoreCase(trdLendDurationDto.getOperateType())) {
            TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService
                    .revoke汇总和交易相关表(trdLendDurationDto.getpTradeId(), updateUser, trdLendDurationDto.getCancelRemark(), null, null);
            deleteWflByWorkflowId(workflowId);
            // 删除质押券持仓
            WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
            BeanUtils.copyProperties(trdFinProductDealTotal, wflTrdFinProductDealTotal);
            deletePledgeSecPosition(wflTrdFinProductDealTotal);
            
            //删除授信占用
            riskCreditListCommonValidatorService.deleteCredit(tradeid);

            return Boolean.TRUE.toString();
        }
        // 金融产品交易Entity
        List<WflTrdProductDeal> wflTrdProductDeals = trdRepoDurationMapper.findDealByWorkflowId(workflowId);
        // 组合交易
        List<PtlTrade> ptlTrades = gen组合交易(trdLendDurationDto, wflTrdProductDeals);
        // 金融产品交易汇总
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdRepoDurationMapper.findTotalByWorkflowId(workflowId);
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        BeanUtils.copyProperties(wflTrdFinProductDealTotal, trdFinProductDealTotal);
        trdFinProductDealTotal.setUpdateTime(updateTime);
        trdFinProductDealTotal.setUpdateUser(updateUser);
        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);

        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            TrdProductDeal deal = new TrdProductDeal();
            BeanUtils.copyProperties(wflTrdProductDeal, deal);
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
            trdProductDeal.setUpdateTime(updateTime);
            trdProductDeal.setUpdateUser(updateUser);
            trdProductDealMapper.insertSelective(trdProductDeal);

            if (StringUtil.isNotEmpty(trdLendDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdLendDurationDto.getPortfolioId(), trdLendDurationDto.getActPortfolioId())) {
                TrdProductDeal subdeal = new TrdProductDeal();
                BeanUtils.copyProperties(trdProductDeal, subdeal);
                subdeal.setTradeId(trdProductDeal.getTradeId() + "S");
                trdProductDealMapper.insertSelective(subdeal);
            }
        });
        // 金融产品交易Entity
        List<WflPaTrdProductDeal> wflPaTrdProductDeals = trdRepoDurationMapper.findPaDealByWorkflowId(workflowId);
        wflPaTrdProductDeals.forEach(wflTrdProductDeal -> {
            PaTrdProductDeal deal = new PaTrdProductDeal();
            BeanUtils.copyProperties(wflTrdProductDeal, deal);
            deal.setUpdateTime(updateTime);
            deal.setUpdateUser(updateUser);
            deal.setUpdateTime(updateTime);
            deal.setUpdateUser(updateUser);
            paTrdProductDealMapper.insertSelective(deal);
        });

        List<TrdProductDealAdd> adds = new ArrayList<>();
        //插入金融产品交易附属表
        List<WflTrdProductDealAdd> trdProductDealAdds = trdRepoDurationMapper.findAddDealByWorkflowId(workflowId);
        trdProductDealAdds.forEach(trdProductDealAdd -> {
            TrdProductDealAdd addDeal = new TrdProductDealAdd();
        	BeanUtils.copyProperties(trdProductDealAdd, addDeal);
        	addDeal.setUpdateTime(updateTime);
        	addDeal.setUpdateUser(updateUser);
        	trdProductDealAddMapper.insertSelective(addDeal);
            adds.add(addDeal);
            if (StringUtil.isNotEmpty(trdLendDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdLendDurationDto.getPortfolioId(), trdLendDurationDto.getActPortfolioId())) {
                TrdProductDealAdd add = new TrdProductDealAdd();
                BeanUtils.copyProperties(trdProductDealAdd, add);
                add.setTradeId(addDeal.getTradeId() + "S");
                add.setPortfolioId(trdLendDurationDto.getActPortfolioId());
                trdProductDealAddMapper.insertSelective(add);
                adds.add(add);
            }
        });

        // 金融产品交易汇总扩展Entity
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = trdRepoDurationMapper.findPaTotalByWorkflowId(workflowId);
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        BeanUtils.copyProperties(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
        paTrdFinProductDealTotal.setUpdateTime(updateTime);
        paTrdFinProductDealTotal.setUpdateUser(updateUser);
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
            if (StringUtil.isNotEmpty(trdLendDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdLendDurationDto.getPortfolioId(), trdLendDurationDto.getActPortfolioId())) {
                PtlTrade deal1 = new PtlTrade();
                BeanUtils.copyProperties(deal, deal1);
                deal1.setPortfolioId(trdLendDurationDto.getActPortfolioId());
                deal1.setBusiNo(deal.getBusiNo() + "S");
                ptlTradeMapper.insertSelective(deal1);
                ptlTradeList.add(deal1);
            }
        });

        // 7.7 交易报送信息
        List<RepTrade> repTrades = gen交易报送信息(trdLendDurationDto, wflTrdProductDeals);
        repTrades.forEach(repTrade -> {
            RepTrade deal = new RepTrade();
            BeanUtils.copyProperties(repTrade, deal);
            deal.setUpdateTime(updateTime);
            deal.setUpdateUser(updateUser);
            repTradeMapper.insertSelective(deal);
        });
        // 7.2 删除原业务流程表中的交易记录
        deleteWflByWorkflowId(workflowId);

        // 一体化对应etliu start
        if (!StringUtil.isEmptyList(trdProductDeals)) { // 母组合交易
            // 拆入到期 调用lendMdateAdd估值接口
            String finprodType2 = trdProductDeals.get(0).getFinprodType2();
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_拆入.getValue(), finprodType2)
                    || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue(), finprodType2)) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(trdLendDurationDto.getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDealList(trdProductDeals);
                AllTradeCommonUtil.lendMdateAdd(inputDto);
                if (StringUtil.isNotEmpty(trdLendDurationDto.getActPortfolioId())
                        && !StringUtil.equals(trdLendDurationDto.getPortfolioId(), trdLendDurationDto.getActPortfolioId())) {
                	trdProductDeals.forEach(item->{
                        item.setTradeId(item.getTradeId()+'S');
                    });
                    inputDto.setProdid(trdLendDurationDto.getActPortfolioId());
                    AllTradeCommonUtil.lendMdateAdd(inputDto);
                }
                return Boolean.TRUE.toString();
            }
            // 所有交易生效后调用的共通方法
            for (TrdProductDeal deal : trdProductDeals) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(trdLendDurationDto.getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(deal);
                adds.forEach(tradeAdd -> {
                    if (deal.getTradeId().equals(tradeAdd.getTradeId())) {
                        inputDto.setTrdProductDealAdd(tradeAdd);
                    }
                });
                if (StringUtil.isNotEmpty(trdLendDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdLendDurationDto.getPortfolioId(), trdLendDurationDto.getActPortfolioId())) {
                    inputDto.setActPortfolioId(trdLendDurationDto.getActPortfolioId());
                }
                List<PtlTrade> ptlTrades1 = new ArrayList<>();
                ptlTradeList.forEach(ptlTrade -> {
                    if (ptlTrade.getBusiNo().equals(deal.getTradeId() + "S") || deal.getTradeId().equals(ptlTrade.getBusiNo())) {
                        ptlTrades1.add(ptlTrade);
                    }
                });
                inputDto.setPtlTradeList(ptlTrades1);
                StlPayOrders order = null;
                if (stlPayOrdersDtos != null && !stlPayOrdersDtos.isEmpty()) {
                    order = new StlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(order, stlPayOrdersDtos.get(0));
                }
                if (order != null) {
                    inputDto.setStlPayOrders(order);
                }
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }
        }
        // 一体化对应etliu end

        return Boolean.TRUE.toString();
	}

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param trdLendDurationDto
     * @param wflTrdProductDeals
     * @return
     * @see
     * @since 1.0
     */
    private List<PaTrdProductDeal> gen交易扩展信息(TrdLendDurationDto trdLendDurationDto,
            List<WflTrdProductDeal> wflTrdProductDeals) {
        // TODO Auto-generated method stub
        return null;
    }

    private String deleteWflByWorkflowId(String workflowId) {
        investPeriodCommonService.deleteByWorkflowId(workflowId);
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        return Boolean.TRUE.toString();
    }

    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoServiceImpl;

    /**
     * 删除原业务流程表中的交易记录
     *
     * @param trdCommonDeleteDto TrdCommonDeleteDto
     * @return true
     */
    public String doDelete(TrdCommonDeleteDto trdCommonDeleteDto) {
        deleteWflByWorkflowId(trdCommonDeleteDto.getWorkflowId());
        if (trdCommonDeleteDto.getPtlCashPositions() != null) {
            trdCommonDeleteDto.getPtlCashPositions().forEach(ptlCashPositionFlowDto -> {
                basePtlCashPositionInfoService.deleteCashPosition(ptlCashPositionFlowDto);
            });
        }
        if (trdCommonDeleteDto.getPtlSecPositions() != null) {
            trdCommonDeleteDto.getPtlSecPositions().forEach(ptlSecPositionFlowDto -> {
                basePtlSecPositionInfoService.deleteSecPosition(ptlSecPositionFlowDto);
            });
        }
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdCommonDeleteDto.getWflTrdFinProductDealTotal();
        deletePledgeSecPosition(wflTrdFinProductDealTotal);
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

    private List<RepTrade> gen交易报送信息(TrdLendDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
        List<RepTrade> result = new ArrayList<>();
        // 产品登记编码
        Map<String, Object> params = new HashMap<>();
        params.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        params.put("portfolioId", entity.getPortfolioId());
        String 产品登记编码 = trdRepoDurationMapper.findPrdRegCode(params);
        if (!StringUtil.isNotEmpty(产品登记编码)) {
            return result;
        }
        BigDecimal 最低收盘价 = BigDecimal.ONE;
        if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
            最低收盘价 = investPeriodCommonService.find最低收盘价(entity.getCcy(), DateUtil.format(entity.getSettleDate()));
        }
        BigDecimal 最低收盘价F = 最低收盘价;
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            RepTrade repTrade = new RepTrade();
            repTrade.setRepIssuePartyId(entity.getRepIssuePartyId());
            repTrade.setRepFinprodRegId(产品登记编码);
            repTrade.setRepBankTradeCode(wflTrdProductDeal.getTradeId());
            // 行内资产/负债编码存原始交易编号
            repTrade.setRepBankAssetCode(wflTrdProductDeal.getFinprodId());
            repTrade.setRepCcy(entity.getCcy());
            repTrade.setRepCount(wflTrdProductDeal.getShareAmt());
            repTrade.setRepTradeDate(entity.getSettleDate());
            repTrade.setRepTradeComponent(entity.getCounterId());
            repTrade.setRepDelCode(wflTrdProductDeal.getTradeId());
            repTrade.setRepRemark(entity.getRemark());
            repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
            repTrade.setRepStatusDate(iPlatformDate.getSysDate());
            repTrade.setCreateUser(wflTrdProductDeal.getCreateUser());
            repTrade.setCreateDept(wflTrdProductDeal.getCreateDept());
            repTrade.setCreateTime(wflTrdProductDeal.getCreateTime());
            // 根据原始交易编号检索拆借方向
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tradeId", wflTrdProductDeal.getFinprodId());
            String backLendDirection = trdLendDurationMapper.getBackLendDirection(map);
            if (DictCodeList.交易方向_还本到账.getValue().equals(wflTrdProductDeal.getPs())) {
                repTrade.setRepOccurMoney(wflTrdProductDeal.getPrinAmt());
                // 折算人民币金额（元）
                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getPrinAmt(), 最低收盘价F));
                if(StringUtil.equals(backLendDirection, DictCodeList.回购拆借方向_拆入.getValue())) {
                	repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_同业拆入和卖出回购到期.getValue());
                    repTrade.setRepMoneyFlowTypeExp("拆借拆入到期本金");
                } else {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_拆放同业和买入返售到期.getValue());
                    repTrade.setRepMoneyFlowTypeExp("拆借拆出到期本金");
                }
            } else if (DictCodeList.交易方向_收息到账.getValue().equals(wflTrdProductDeal.getPs())) {
                repTrade.setRepOccurMoney(wflTrdProductDeal.getIntAmt());
                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getIntAmt(), 最低收盘价F));
                if (DictCodeList.回购拆借方向_拆入.getValue().equals(backLendDirection)) {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产支出.getValue());
                    repTrade.setRepMoneyFlowTypeExp("拆借拆入到期利息支出");
                } else {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
                    repTrade.setRepMoneyFlowTypeExp("拆借拆出到期利息收入");
                }
            }
            result.add(repTrade);
        });
        return result;
    }

    private List<PtlTrade> gen组合交易(TrdLendDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
        List<PtlTrade> result = new ArrayList<>();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            PtlTrade ptlTrade2 = new PtlTrade();
            ptlTrade2.setPortfolioId(entity.getPortfolioId());
            ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade2.setBusiId(entity.getFinprodId());
            ptlTrade2.setBusiType(DictCodeList.共通业务类型_拆借存续期交易.getValue());
            ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
            ptlTrade2.setSettleDate(entity.getSettleDate());
            result.add(ptlTrade2);
        });
        return result;
    }

    public List<RepoInfo> findRepoInfo(Map<String, Object> params) {
        return trdRepoDurationMapper.findRepoInfo(params);
    }

    /**
     * 根据工作流Id获取交易流程信息 <功能详细描述>
     * 
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    public List<WflTrdProductDeal> findDealByWorkflowId(String workflowId) {
        return trdRepoDurationMapper.findDealByWorkflowId(workflowId);
    }
    //add by liupengfei for 多级法人改造 S
    public Page<SelectOptionDto> getBusiCode(int page, int length, Map<String, Object> param){
        param.put("TRADE_STATUS_CX", DictCodeListExt1.交易状态_撤销.getValue());
        return queryForPageForSelectReturn(page, length, () -> trdLendDurationMapper.getBusiCode(param));
    }
    //add by liupengfei for 多级法人改造 E
}
