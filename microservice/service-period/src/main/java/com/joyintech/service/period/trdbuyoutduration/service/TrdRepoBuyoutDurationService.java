package com.joyintech.service.period.trdbuyoutduration.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trdbuyoutduration.dao.TrdRepoBuyoutDurationMapper;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.CeLinesUpdate;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;

@Service
public class TrdRepoBuyoutDurationService extends BaseService<TrdRepoDurationDto> {

    @Autowired
    private TrdRepoBuyoutDurationMapper trdRepoBuyoutDurationMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

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
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;

    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;
    
    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

    public Page<TrdRepoDurationDto> findPage(int page, int length, Map<String, Object> param) {
        setParams(param);
        return queryForPageForSelectReturn(page, length, () -> trdRepoBuyoutDurationMapper.findPage(param));
    }

    public List<TrdRepoDurationDto> selectAllData(Map<String, Object> param) {
        setParams(param);
        return trdRepoBuyoutDurationMapper.findPage(param);
    }

    private void setParams(Map<String, Object> param) {
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        param.put("COMMON_BUSINESS_TYPETRD16", DictCodeList.共通业务类型_买断式回购存续期.getValue());
        param.put("COMMON_BUSINESS_TYPETRD08", DictCodeList.共通业务类型_买断式回购交易.getValue());
        param.put("FINPROD_TYPE_TGF03", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        param.put("FINPROD_TYPE_TGF02", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        param.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        param.put("PS_T07", DictCodeList.交易方向_到期.getValue());
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        param.put("C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        param.put("PST07", DictCodeList.交易方向_到期.getValue());
        param.put("PST22", DictCodeList.交易方向_收息到账.getValue());
        param.put("PST23", DictCodeList.交易方向_还本到账.getValue());
        param.put("SysDate", platformDateImpl.getSysDate());
    }

    public TrdRepoDurationDto findEffectiveData(Map<String, Object> params) {
        setParams(params);
        return trdRepoBuyoutDurationMapper.findEffectiveData(params);
    }

    /**
     * 流程结束时处理
     *
     * @param trdRepoDurationDto 参数
     * @return
     */
    public String doComplete(TrdRepoDurationDto trdRepoDurationDto) {
        String workflowId = trdRepoDurationDto.getWorkflowId();
        // 金融产品交易Entity
        List<WflTrdProductDeal> wflTrdProductDeals = trdRepoBuyoutDurationMapper.findDealByWorkflowId(workflowId);
        // 组合交易
        List<PtlTrade> ptlTrades = gen组合交易(trdRepoDurationDto, wflTrdProductDeals);
        String updateUser = trdRepoDurationDto.getUpdateUser();
        Date updateTime = new Date();
        if (BussinessConstant.REVOKE.equalsIgnoreCase(trdRepoDurationDto.getOperateType())) {
            TrdFinProductDealTotal trdFinProductDealTotal = investPeriodCommonService.revoke汇总和交易相关表(trdRepoDurationDto.getBusiCode(),
                    updateUser, trdRepoDurationDto.getRevokeReason(), null, null);
            // 删除质押券持仓
            WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
            BeanUtils.copyProperties(trdFinProductDealTotal, wflTrdFinProductDealTotal);
            deletePledgeSecPosition(wflTrdFinProductDealTotal);
            deleteWflByWorkflowId(workflowId);
            return Boolean.TRUE.toString();
        }

        // 金融产品交易汇总
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdRepoBuyoutDurationMapper.findTotalByWorkflowId(workflowId);
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        BeanUtils.copyProperties(wflTrdFinProductDealTotal, trdFinProductDealTotal);
        trdFinProductDealTotal.setUpdateTime(updateTime);
        trdFinProductDealTotal.setUpdateUser(updateUser);
        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);

        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        TrdTradeFeeDetail trdTradeFeeDetail = new TrdTradeFeeDetail();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            TrdProductDeal deal = new TrdProductDeal();
            BeanUtils.copyProperties(wflTrdProductDeal, deal);
            deal.setUpdateTime(updateTime);
            deal.setUpdateUser(updateUser);
            trdProductDeals.add(deal);
        });


        // 划款指令
        List<WflStlPayOrders> stlPayOrdersDtos = trdRepoBuyoutDurationMapper.findOrdersByWorkflowId(workflowId);
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
            if (StringUtil.isNotEmpty(trdRepoDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdRepoDurationDto.getPortfolioId(), trdRepoDurationDto.getActPortfolioId())) {
                String tradeId = trdProductDeal.getTradeId();
                trdProductDeal.setTradeId(trdProductDeal.getTradeId() + "S");
                trdProductDealMapper.insertSelective(trdProductDeal);
                trdProductDeal.setTradeId(tradeId);
            }
            // 交易附属表添加 2019年3月15日14:50:18 start
            TrdProductDealAdd add = new TrdProductDealAdd();
            add.setTradeId(trdProductDeal.getTradeId());
            add.setPortfolioId(trdRepoDurationDto.getPortfolioId());
            trdProductDealAddMapper.insertSelective(add);
            if (StringUtil.isNotEmpty(trdRepoDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdRepoDurationDto.getPortfolioId(), trdRepoDurationDto.getActPortfolioId())) {
                add.setTradeId(trdProductDeal.getTradeId() + "S");
                add.setPortfolioId(trdRepoDurationDto.getActPortfolioId());
                trdProductDealAddMapper.insertSelective(add);
            }
            // 交易附属表添加 2019年3月15日14:50:18 end
            // 通道费入库费用明细 表 2019-10-15
            if(StringUtil.equals(DictCodeList.交易方向_收息到账.getValue(), trdProductDeal.getPs())
            		&&StringUtil.isNotEmpty(trdRepoDurationDto.getChannelFee())){
            	
                 trdTradeFeeDetail.setTradeId(trdProductDeal.getTradeId());
                 // 交易费用明细表.费用类型 = “通道费”
                 trdTradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
                 // 交易费用明细表.是否随交易支付  根据收取方式判断
             	if(StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), trdRepoDurationDto.getCollectType())){
                 	trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
                 }else{
                 	trdTradeFeeDetail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
                 }
                 // 交易费用明细表.交易金额 = 交易.通道费
                 trdTradeFeeDetail.setFeeAmt(trdRepoDurationDto.getChannelFee());
                 trdTradeFeeDetailMapper.insertSelective(trdTradeFeeDetail);
            }
            
            // 通道费入库费用明细 表 2019-10-15 end
        });

        // 金融产品交易汇总扩展Entity
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = trdRepoBuyoutDurationMapper.findPaTotalByWorkflowId(workflowId);
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        BeanUtils.copyProperties(wflPaTrdFinDealTotal, paTrdFinProductDealTotal);
        paTrdFinProductDealTotal.setUpdateTime(updateTime);
        paTrdFinProductDealTotal.setUpdateUser(updateUser);
        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);

        Map<String, Object> params = new HashMap<>();
        if (DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue().equals(trdRepoDurationDto.getFinprodType2())) {
            params.put("holdingType", DictCodeList.持仓类型_融出.getValue());
        }
        else {
            params.put("holdingType", DictCodeList.持仓类型_融入.getValue());
        }

        params.put("finprodId", wflTrdFinProductDealTotal.getFinprodId());
        params.put("mDate", wflTrdFinProductDealTotal.getMdate());
        List<PtlSecPositionFlow> flows = trdRepoBuyoutDurationMapper.selectSecFlow(params);
        if (flows != null && !flows.isEmpty()) {
            flows.forEach(flow -> {
                flow.setCdate(wflTrdFinProductDealTotal.getMdate());
                trdRepoBuyoutDurationMapper.insertSelect04Sec(flow);
                flow.setSeqno(IDGenerator.generate());
                flow.setTradeId(wflTrdFinProductDealTotal.getTotleTradeId());
                ptlSecPositionFlowMapper.insert(flow);
            });
        }

        // 7.3 插入组合交易
        ptlTrades.forEach(ptlTrade -> {
            PtlTrade deal = new PtlTrade();
            BeanUtils.copyProperties(ptlTrade, deal);
            deal.setUpdateTime(updateTime);
            deal.setUpdateUser(updateUser);
            ptlTradeMapper.insertSelective(deal);

            if (StringUtil.isNotEmpty(trdRepoDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdRepoDurationDto.getPortfolioId(), trdRepoDurationDto.getActPortfolioId())) {
                deal.setPortfolioId(trdRepoDurationDto.getActPortfolioId());
                deal.setBusiNo(deal.getBusiNo() + "S");
                ptlTradeMapper.insertSelective(deal);
            }
        });

        // 7.7 交易报送信息
        List<RepTrade> repTrades = gen交易报送信息(trdRepoDurationDto, wflTrdProductDeals);
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
        if (!StringUtil.isEmptyList(trdProductDeals)) {
            // 回购到期 调用lendMdateAdd估值接口
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(trdRepoDurationDto.getPortfolioId());
            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
            inputDto.setTrdProductDealList(trdProductDeals);
            inputDto.setRepoType(trdRepoDurationDto.getType6());
            inputDto.setTrdTradeFeeDetail(trdTradeFeeDetail);
            AllTradeCommonUtil.buyRepoMdateAdd(inputDto);
            for (TrdProductDeal trdProductDeal : inputDto.getTrdProductDealList()) {
                AllTradeCommonInputDto input = new AllTradeCommonInputDto();
                //使用抵押品信息代替交易编号
                String finprodid = trdRepoBuyoutDurationMapper.getFinprodIdByTradeId(trdProductDeal.getFinprodId());
                BigDecimal prinamt = trdRepoBuyoutDurationMapper.getPrinAmtByTradeId(trdProductDeal.getFinprodId());
                if (null != prinamt && prinamt.compareTo(BigDecimal.ZERO) > 0) {
                    trdProductDeal.setPrinAmt(prinamt);
                }
                trdProductDeal.setFinprodId(finprodid);
                input.setTrdProductDeal(trdProductDeal);
                input.setRepoType(inputDto.getRepoType());
                CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                if (!(DictCodeListExt1.交易状态_初始.getValue().equals(trdProductDeal.getTradeStatus())
                      && (DictCodeList.交易方向_买入.getValue().equals(trdProductDeal.getPs())
                          || DictCodeList.交易方向_申购.getValue().equals(trdProductDeal.getPs())
                          || DictCodeListExt2.交易方向_新股中签.getValue().equals(trdProductDeal.getPs())
                          || DictCodeListExt2.交易方向_配股.getValue().equals(trdProductDeal.getPs())
                          || DictCodeListExt2.交易方向_增发.getValue().equals(trdProductDeal.getPs())))) {
                    ceLinesUpdate.isNeedOccupyCre(input, true, true, false);
                }
            }
            
            if (StringUtil.isNotEmpty(trdRepoDurationDto.getActPortfolioId())
                    && !StringUtil.equals(trdRepoDurationDto.getPortfolioId(), trdRepoDurationDto.getActPortfolioId())) {
            	trdProductDeals.forEach(item->{
                    item.setTradeId(item.getTradeId()+'S');
                });
            	trdTradeFeeDetail.setTradeId(trdTradeFeeDetail.getTradeId()+'S');
                inputDto.setProdid(trdRepoDurationDto.getActPortfolioId());
                AllTradeCommonUtil.buyRepoMdateAdd(inputDto);
                for (TrdProductDeal trdProductDeal : inputDto.getTrdProductDealList()) {
                    AllTradeCommonInputDto input = new AllTradeCommonInputDto();
                    //使用抵押品信息代替交易编号
                    String finprodid = trdRepoBuyoutDurationMapper.getFinprodIdByTradeId(trdProductDeal.getFinprodId());
                    trdProductDeal.setFinprodId(finprodid);
                    input.setTrdProductDeal(trdProductDeal);
                    input.setRepoType(inputDto.getRepoType());
                    CeLinesUpdate ceLinesUpdate = SpringContextUtil.getBean(CeLinesUpdate.class);
                    if (!(DictCodeListExt1.交易状态_初始.getValue().equals(trdProductDeal.getTradeStatus())
                          && (DictCodeList.交易方向_买入.getValue().equals(trdProductDeal.getPs())
                              || DictCodeList.交易方向_申购.getValue().equals(trdProductDeal.getPs())
                              || DictCodeListExt2.交易方向_新股中签.getValue().equals(trdProductDeal.getPs())
                              || DictCodeListExt2.交易方向_配股.getValue().equals(trdProductDeal.getPs())
                              || DictCodeListExt2.交易方向_增发.getValue().equals(trdProductDeal.getPs())))) {
                        ceLinesUpdate.isNeedOccupyCre(input, true, true, false);
                    }
                }
            }
//            for (TrdProductDeal trdProductDeal : trdProductDeals) {
//                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
//                inputDto.setProdid(wflTrdFinProductDealTotal.getPortfolioId());
//                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
//                inputDto.setTrdProductDeal(trdProductDeal);
//                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
//            }
        }
        // 一体化对应etliu end

        return Boolean.TRUE.toString();
    }

    private String deleteWflByWorkflowId(String workflowId) {
        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
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
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = trdCommonDeleteDto.getWflTrdFinProductDealTotal();
        deletePledgeSecPosition(wflTrdFinProductDealTotal);
        return Boolean.TRUE.toString();
    }

    private void deletePledgeSecPosition(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
        if (wflTrdFinProductDealTotal != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", wflTrdFinProductDealTotal.getTotleTradeId().substring(0, wflTrdFinProductDealTotal.getTotleTradeId().length() - 1));
            if (DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue().equals(wflTrdFinProductDealTotal.getFinprodType2())) {
                params.put("holdingType", DictCodeList.持仓类型_融出.getValue());
            } else {
                params.put("holdingType", DictCodeList.持仓类型_融入.getValue());
            }
            params.put("mDate", wflTrdFinProductDealTotal.getMdate());
            List<PtlSecPositionFlow> flows = trdRepoBuyoutDurationMapper.selectSecFlow(params);
            if (flows != null && !flows.isEmpty()) {
                flows.forEach(flow -> {
                    flow.setAmount(flow.getAmount());
                    trdRepoBuyoutDurationMapper.insertSelect04Sec(flow);
                    ptlSecPositionFlowMapper.deleteByPrimaryKey(flow.getSeqno());
                });
            }
        }
    }

    private List<RepTrade> gen交易报送信息(TrdRepoDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
        List<RepTrade> result = new ArrayList<>();
        // 产品登记编码
        Map<String, Object> params = new HashMap<>();
        params.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        params.put("portfolioId", entity.getPortfolioId());
        String 产品登记编码 = trdRepoBuyoutDurationMapper.findPrdRegCode(params);
        if (StringUtil.isEmpty(产品登记编码)) {
            return result;
        }
        BigDecimal 最低收盘价 = BigDecimal.ONE;
        if (!DictCodeList.币种_人民币_CNY.getValue().equals(entity.getCcy())) {
            最低收盘价 = investPeriodCommonService.find最低收盘价(entity.getCcy(), entity.getSettleDate());
        }
        BigDecimal 最低收盘价F = 最低收盘价;
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            RepTrade repTrade = new RepTrade();
            repTrade.setRepIssuePartyId(CacheUtil.getSysParam("issue_party_id"));
            repTrade.setRepFinprodRegId(产品登记编码);
            repTrade.setRepBankTradeCode(wflTrdProductDeal.getTradeId());
            repTrade.setRepBankAssetCode(wflTrdProductDeal.getTradeId());
            repTrade.setRepCcy(entity.getCcy());
            repTrade.setRepCount(wflTrdProductDeal.getShareAmt());
            repTrade.setRepTradeDate(DateUtil.parse(entity.getSettleDate()));
            repTrade.setRepTradeComponent(entity.getCounterId());
            repTrade.setRepDelCode(wflTrdProductDeal.getTradeId());
            repTrade.setRepRemark(entity.getRemark());
            repTrade.setRepSendStatus(DictCodeList.是否_是.getValue());
            repTrade.setRepStatusDate(platformDateImpl.getSysDate());
            repTrade.setCreateUser(wflTrdProductDeal.getCreateUser());
            repTrade.setCreateDept(wflTrdProductDeal.getCreateDept());
            repTrade.setCreateTime(wflTrdProductDeal.getCreateTime());
            if (DictCodeList.交易方向_到期.getValue().equals(wflTrdProductDeal.getPs())) {
                repTrade.setRepOccurMoney(wflTrdProductDeal.getPrinAmt());
                // 折算人民币金额（元）
                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getPrinAmt(), 最低收盘价F));
                if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())) {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_同业拆入和卖出回购到期.getValue());
                    repTrade.setRepMoneyFlowTypeExp("债券质押式正回购到期本金");
                } else {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_拆放同业和买入返售到期.getValue());
                    repTrade.setRepMoneyFlowTypeExp("债券质押式逆回购到期本金");
                }
            } else if (DictCodeList.交易方向_收息.getValue().equals(wflTrdProductDeal.getPs())) {
                repTrade.setRepOccurMoney(wflTrdProductDeal.getIntAmt());
                repTrade.setRepMoneyCny(ArithmeticUtil.multiply(wflTrdProductDeal.getIntAmt(), 最低收盘价F));
                if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())) {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产支出.getValue());
                    repTrade.setRepMoneyFlowTypeExp("债券质押式正回购到期利息支出");
                } else {
                    repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_投资资产所得收益.getValue());
                    repTrade.setRepMoneyFlowTypeExp("债券质押式逆回购到期利息收入");
                }
            }
        });
        return result;
    }

    private List<PtlTrade> gen组合交易(TrdRepoDurationDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
        List<PtlTrade> result = new ArrayList<>();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            PtlTrade ptlTrade2 = new PtlTrade();
            ptlTrade2.setPortfolioId(entity.getPortfolioId());
            ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade2.setBusiId(entity.getFinprodId());
            ptlTrade2.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
            ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
            ptlTrade2.setSettleDate(DateUtil.parse(entity.getSettleDate()));
            result.add(ptlTrade2);
        });
        return result;
    }

    public List<RepoInfo> findRepoInfo(Map<String, Object> params) {
        return trdRepoBuyoutDurationMapper.findRepoInfo(params);
    }

    public List<FinCashPlan> findFinCashInfo(String finprodId) {
        return trdRepoBuyoutDurationMapper.findFinCashInfo(finprodId);
    }

    public Page<SelectOptionDto> getBusiCode(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TGF03", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        param.put("FINPROD_TYPE_TGF02", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        return queryForPageForSelectReturn(page, length, () -> trdRepoBuyoutDurationMapper.getBusiCode(param));
    }

    public BigDecimal genPrinAmtByVal(Map<String, Object> param) {
        return trdRepoBuyoutDurationMapper.genPrinAmtByVal(param);
    }
}