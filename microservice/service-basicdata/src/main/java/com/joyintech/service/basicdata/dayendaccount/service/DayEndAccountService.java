package com.joyintech.service.basicdata.dayendaccount.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.dayendaccount.dao.DayEndAccountMapper;
import com.joyintech.service.basicdata.dayendaccount.dto.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.reconciliation.dto.AccountFeePlan;
import com.joyintech.tams.invest.common.reconciliation.dto.AccountPlanSearchInfo;
import com.joyintech.tams.invest.common.reconciliation.dto.DayEndAccountDaichuliDto;
import com.joyintech.tams.invest.common.reconciliation.service.ReconciliationCommonService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengzhong
 */
@Service("backDayEndAccountService")
public class DayEndAccountService extends BaseService<DayEndAccountDto>{
    /**
     * 定义mapper(mine)
     */
    @Autowired
    private DayEndAccountMapper dayEndAccountMapper;

    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private StlSecReconMapper stlSecReconMapper;

    @Autowired
    private SettleTransDataMapper settleTransDataMapper;


    @Autowired
    private StlSecReconDealMapper stlSecReconDealMapper;

    @Autowired
    private ReconciliationCommonService reconciliationCommonService;

    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<DayEndAccountDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        Object accountObj = pageParam.get("account");
        if (accountObj != null) {
            pageParam.put("accountList", StringUtil.splitToList(GlobalConstant.COMMA, (String)accountObj));
        }
        else {
            pageParam.put("accountList", new ArrayList<>());
        }
        Object finprodIdObj = pageParam.get("finprodId");
        if (finprodIdObj != null) {
            pageParam.put("finprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)finprodIdObj));
        }
        else {
            pageParam.put("finprodIdList", new ArrayList<>());
        }
        String sysDate = iPlatformDate.getSysDateStr();
        pageParam.put("sysDate", sysDate);
        pageParam.put("PARA_CODE_02", DictCodeListStl.参数代码_对账维度.getValue());
        pageParam.put("PARA_VALUE_01", DictCodeListStl.对账维度_证券汇总.getValue());
        pageParam.put("PARA_VALUE_02", DictCodeListStl.对账维度_委托汇总.getValue());
        pageParam.put("PARA_VALUE_03", DictCodeListStl.对账维度_成交明细.getValue());
        pageParam.put("CHECK_RESULT_2", DictCodeListExt3.对账状态结果_待处理.getValue());
        Page<DayEndAccountDto> result = super.queryForPage(page, length, () -> dayEndAccountMapper.findAccountInfo(pageParam));
        for(DayEndAccountDto dto:result.getRoot()){
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                FinProduct finProduct = finProductMapper.selectByPrimaryKey(dto.getFinprodId());
                dto.setFinprodMarketId(finProduct.getFinprodMarketId());
            }
        }
        return result;
    }

    public Page<SelectOptionDto> queryAssetCode(int page, int length,
                                                  Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return queryForPageForSelectReturn(page, length,
                () -> dayEndAccountMapper.queryAssetCode(param));
    }

    public AccountResult qryAccount(Map<String, Object> param) {
        Object portfolioIdObj = param.get("portfolioId");
        if (portfolioIdObj != null) {
            param.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            param.put("portfolioIdList", new ArrayList<>());
        }
        AccountResult result = new AccountResult();
        //对账前check
//        if (!checkAccount(result)) {
//            return result;
//        }
        //处理对账逻辑
        chuliAccount(param);
        return result;
    }

    private boolean checkAccount(AccountResult result) {
        Map<String, Object> param = new HashMap<String, Object>();
        String sysDate = iPlatformDate.getSysDateStr();
        //券商对账单是否解析
        param.put("sysdate", sysDate);
        if (dayEndAccountMapper.queryStlSecReconDealCount(param) <= 0) {
            result.setMessage("请读入券商对账单");
            return false;
        }
        //清算数据是否解析
        if (dayEndAccountMapper.querySettleTransDataCount(param) <= 0) {
            result.setMessage("请读入中登清算数据");
            return false;
        }
        //是否设置对账维度
        param.put("paraCode", DictCodeListStl.参数代码_对账维度.getValue());
        List<StlSecReconPara> stlSecReconParaList = dayEndAccountMapper.queryAccountPara(param);
        if (stlSecReconParaList == null || stlSecReconParaList.size() <= 0) {
            result.setMessage("对账参数未设置，请设置后再进行对账");
            return false;
        }
        return true;
    }

    /**
     * 处理对账
     * @param param
     */
    private void chuliAccount(Map<String, Object> param) {
        String sysDate = iPlatformDate.getSysDateStr();
        param.put("sysdate", sysDate);
        param.put("PARA_CODE_02", DictCodeListStl.参数代码_对账维度.getValue());
        param.put("PARA_VALUE_01", DictCodeListStl.对账维度_证券汇总.getValue());
        param.put("PARA_VALUE_02", DictCodeListStl.对账维度_委托汇总.getValue());
        param.put("PARA_VALUE_03", DictCodeListStl.对账维度_成交明细.getValue());
        param.put("CHECK_RESULT_0", DictCodeListExt3.对账状态结果_不一致.getValue());
        //获取待处理的对账数据
        List<DayEndAccountDaichuliDto> list = dayEndAccountMapper.queryAccountDaichuli(param);
        //处理对账逻辑
        reconciliationCommonService.executeReconciliation(list);
    }

    public DayEndAccount002Dto getReconDealInfo(Map<String, Object> param) {
        DayEndAccount002Dto dto = new DayEndAccount002Dto();
        //获取券商单数据
        String seqNo = (String) param.get("seqNo");
        StlSecReconDeal stlSecReconDeal = stlSecReconDealMapper.selectByPrimaryKey(seqNo);
        List<DayEndAccountDto> reconDealInfoList = new ArrayList<DayEndAccountDto>();
        DayEndAccountDto reconDealInfo = new DayEndAccountDto();
        reconDealInfo.setSeqNo(stlSecReconDeal.getSeqNo());
        reconDealInfo.setPortfolioId(stlSecReconDeal.getPortfolioId());
        reconDealInfo.setSettleDate(stlSecReconDeal.getTradeDate());
        reconDealInfo.setFinprodId(stlSecReconDeal.getMarketId());
        reconDealInfo.setTradeMarket(stlSecReconDeal.getTradeMarket());
        reconDealInfo.setFinprodType(stlSecReconDeal.getFinprodType());
        reconDealInfo.setCommissionAmt(stlSecReconDeal.getCommissionAmt());
        reconDealInfo.setJsfAmt(stlSecReconDeal.getJsfAmt());
        reconDealInfo.setZgfAmt(stlSecReconDeal.getZgfAmt());
        reconDealInfo.setStampFee(stlSecReconDeal.getStampFee());
        reconDealInfo.setSettleFee(stlSecReconDeal.getSettleFee());
        reconDealInfo.setSxfAmt(stlSecReconDeal.getSxfAmt());
        reconDealInfo.setGhfAmt(stlSecReconDeal.getGhfAmt());
        reconDealInfo.setTradeAmt(stlSecReconDeal.getTradeAmt());
        reconDealInfo.setTradeNum(stlSecReconDeal.getTradeNum());
        reconDealInfoList.add(reconDealInfo);

        List<SettleTransData> listSettleTransData = dayEndAccountMapper.selectAllSettleTransData(param);
        List<DayEndAccountDto> dealDetailList = new ArrayList<DayEndAccountDto>();

        //查询证券账户信息
        StlSecAcctInfo stlSecAcctInfo = stlSecAcctInfoMapper.selectByPrimaryKey(stlSecReconDeal.getSecAcctId());
        //变量.标的分类信息
        FinProductTypeKey finProductTypeKey = new FinProductTypeKey();
        finProductTypeKey.setBranch(0L);
        finProductTypeKey.setFinprodId(stlSecReconDeal.getFinprodId());
        FinProductType finProductType = finProductTypeMapper.selectByPrimaryKey(finProductTypeKey);

        //有差额费用统计
        List<String> feeList = new ArrayList<String>();
        feeList.add(DictCodeListExt1.一次性费用类型_印花税.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_经手费.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_过户费.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_证管费.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_结算费.getValue());
        feeList.add(DictCodeListExt1.一次性费用类型_佣金.getValue());

        //费用加减标识
        BigDecimal stampFeeFlg = BigDecimal.ZERO;
        BigDecimal jsfAmtFlg = BigDecimal.ZERO;
        BigDecimal ghfAmtFlg = BigDecimal.ZERO;
        BigDecimal zgfAmtFlg = BigDecimal.ZERO;
        BigDecimal sxfAmtFlg = BigDecimal.ZERO;
        BigDecimal settleFeeFlg = BigDecimal.ZERO;
        BigDecimal commissionAmtFlg = BigDecimal.ZERO;

        for (String feeType : feeList) {
            AccountPlanSearchInfo localPlanSearchInfo = new AccountPlanSearchInfo();
            // 交易场所
            if (StringUtil.isNotEmpty(stlSecReconDeal.getTradeMarket())) {
                localPlanSearchInfo.setTradeMarket(stlSecReconDeal.getTradeMarket());
            }
            // 交易平台
            if (StringUtil.isNotEmpty(stlSecReconDeal.getTradePlat())) {
                localPlanSearchInfo.setTradePlatform(stlSecReconDeal.getTradePlat());
            }
            // 费用类型
            if (StringUtil.isNotEmpty(feeType)) {
                localPlanSearchInfo.setFeeType(feeType);
            }
            // 登记托管机构
            if (StringUtil.isNotEmpty(stlSecAcctInfo.getRegistOrg())) {
                localPlanSearchInfo.setRegistOrg(stlSecAcctInfo.getRegistOrg());
            }
            // 交易品种
            String tradeType = stlSecReconDeal.getFinprodType();
            if (StringUtil.isNotEmpty(tradeType)) {
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(), tradeType)) {
                    tradeType = DictCodeList.平安资产类型_债券.getValue();
                }
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), tradeType)
                        || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(), tradeType)) {
                    tradeType = DictCodeList.平安资产类型_质押式回购.getValue();
                }
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue(), tradeType)) {
                    tradeType = DictCodeList.平安资产类型_基金.getValue();
                }
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(), tradeType)) {
                    tradeType = DictCodeListExt2.平安资产类型_股票.getValue();
                }
                localPlanSearchInfo.setTradeType(tradeType);
            }
            // 交易方向
            if (StringUtil.isNotEmpty(stlSecReconDeal.getPs())) {
                localPlanSearchInfo.setPs(stlSecReconDeal.getPs());
            }
            // 期限品种
            if (StringUtil.isNotEmpty(stlSecReconDeal.getTermType())) {
                localPlanSearchInfo.setTermType(stlSecReconDeal.getTermType());
            }
            // 证券账户
            if (StringUtil.isNotEmpty(stlSecReconDeal.getSecAcctId())) {
                localPlanSearchInfo.setSecAcctId(stlSecReconDeal.getSecAcctId());
            }
            // 券商代码
            if (StringUtil.isNotEmpty(stlSecReconDeal.getCustomerId())) {
                localPlanSearchInfo.setBrokerageNo(stlSecReconDeal.getCustomerId());
            }
            // 证券分类1
            if (finProductType != null && StringUtil.isNotEmpty(finProductType.getType9())) {
                localPlanSearchInfo.setAssetClassify1(finProductType.getType9());
            }
            // 证券分类2
            if (finProductType != null && StringUtil.isNotEmpty(finProductType.getType10())) {
                localPlanSearchInfo.setAssetClassify2(finProductType.getType10());
            }
            // 证券分类3
            if (finProductType != null && StringUtil.isNotEmpty(finProductType.getType11())) {
                localPlanSearchInfo.setAssetClassify3(finProductType.getType11());
            }
            // 组合代码
            if (StringUtil.isNotEmpty(stlSecReconDeal.getPortfolioId())) {
                localPlanSearchInfo.setPortfolioId(stlSecReconDeal.getPortfolioId());
            }
            //调用共通方法【费用方案获取】
            AccountFeePlan feePlan = reconciliationCommonService.getFeePlan(localPlanSearchInfo, DateUtil.format(stlSecReconDeal.getTradeDate()));
            String isPay = feePlan == null || feePlan.getTrdFeeConfig() == null ? null : feePlan.getTrdFeeConfig().getIsPayWithTrade();
            //费用信息.是否随交易支付 = 是 and 费用信息.费用类型 = 佣金
            if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_佣金.getValue().equals(feeType)) {
                //if 待对账数据.交易方向 = 买入 or 正回购
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    commissionAmtFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    commissionAmtFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_经手费.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.交易方向_申购.getValue().equals(stlSecReconDeal.getPs())) {
                    jsfAmtFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    jsfAmtFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_证管费.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    zgfAmtFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    zgfAmtFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_印花税.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    stampFeeFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    stampFeeFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_结算费.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    settleFeeFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    settleFeeFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_过户费.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    ghfAmtFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    ghfAmtFlg = BigDecimal.ONE.negate();
                }
            } else if (DictCodeListExt1.是否_是.getValue().equals(isPay)
                    && DictCodeListExt1.一次性费用类型_交易手续费.getValue().equals(feeType)) {
                if (DictCodeList.交易方向_买入.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_正回购.getValue().equals(stlSecReconDeal.getPs())) {
                    sxfAmtFlg = BigDecimal.ONE;
                } else if (DictCodeList.交易方向_卖出.getValue().equals(stlSecReconDeal.getPs())
                        || DictCodeList.回购拆借方向_逆回购.getValue().equals(stlSecReconDeal.getPs())) {
                    sxfAmtFlg = BigDecimal.ONE.negate();
                }
            }
        }
        if (listSettleTransData != null && listSettleTransData.size() > 0) {
            for (SettleTransData settleTransData : listSettleTransData) {
                DayEndAccountDto dealDetail = new DayEndAccountDto();
                dealDetail.setCommissionAmt(settleTransData.getCommissionAmt());
                dealDetail.setJsfAmt(settleTransData.getJsfAmt());
                dealDetail.setZgfAmt(settleTransData.getZgfAmt());
                dealDetail.setStampFee(settleTransData.getStampFee());
                dealDetail.setSettleFee(settleTransData.getSettleFee());
                dealDetail.setSxfAmt(settleTransData.getSxfAmt());
                dealDetail.setGhfAmt(settleTransData.getGhfAmt());
                dealDetail.setTradeAmt(settleTransData.getFpriceAmt());
                dealDetail.setTradeNum(settleTransData.getShareAmt());
                dealDetail.setTradeId(settleTransData.getTradeId());
                //费用标识，没有0，增加1，扣减-1
                dealDetail.setCommissionAmtFlg(commissionAmtFlg);
                dealDetail.setJsfAmtFlg(jsfAmtFlg);
                dealDetail.setZgfAmtFlg(zgfAmtFlg);
                dealDetail.setStampFeeFlg(stampFeeFlg);
                dealDetail.setSettleFeeFlg(settleFeeFlg);
                dealDetail.setSxfAmtFlg(sxfAmtFlg);
                dealDetail.setGhfAmtFlg(ghfAmtFlg);
                //隐藏用于记录调整值字段
                dealDetail.setCommissionAmt2(settleTransData.getCommissionAmt());
                dealDetail.setJsfAmt2(settleTransData.getJsfAmt());
                dealDetail.setZgfAmt2(settleTransData.getZgfAmt());
                dealDetail.setStampFee2(settleTransData.getStampFee());
                dealDetail.setSettleFee2(settleTransData.getSettleFee());
                dealDetail.setSxfAmt2(settleTransData.getSxfAmt());
                dealDetail.setGhfAmt2(settleTransData.getGhfAmt());
                dealDetail.setTradeAmt2(BigDecimal.ZERO);
                dealDetailList.add(dealDetail);
            }
        }
        dto.setReconDealInfoList(reconDealInfoList);
        dto.setDealDetailList(dealDetailList);
        return dto;
    }

    public DayEndAccount002Dto getDealDetailInfo(Map<String, Object> param) {
        DayEndAccount002Dto dto = new DayEndAccount002Dto();
        //费用加减标识
        BigDecimal stampFeeFlg = BigDecimal.ZERO;
        BigDecimal jsfAmtFlg = BigDecimal.ZERO;
        BigDecimal ghfAmtFlg = BigDecimal.ZERO;
        BigDecimal zgfAmtFlg = BigDecimal.ZERO;
        BigDecimal sxfAmtFlg = BigDecimal.ZERO;
        BigDecimal settleFeeFlg = BigDecimal.ZERO;
        BigDecimal commissionAmtFlg = BigDecimal.ZERO;
        List<SettleTransData> listSettleTransData = dayEndAccountMapper.selectSettleTransData(param);
        List<DayEndAccountDto> dealDetailList = new ArrayList<DayEndAccountDto>();
        if (listSettleTransData != null && listSettleTransData.size() > 0) {
            for (SettleTransData settleTransData : listSettleTransData) {
                DayEndAccountDto dealDetail = new DayEndAccountDto();
                dealDetail.setCommissionAmt(settleTransData.getCommissionAmt());
                dealDetail.setJsfAmt(settleTransData.getJsfAmt());
                dealDetail.setZgfAmt(settleTransData.getZgfAmt());
                dealDetail.setStampFee(settleTransData.getStampFee());
                dealDetail.setSettleFee(settleTransData.getSettleFee());
                dealDetail.setSxfAmt(settleTransData.getSxfAmt());
                dealDetail.setGhfAmt(settleTransData.getGhfAmt());
                dealDetail.setTradeAmt(settleTransData.getFpriceAmt());
                dealDetail.setTradeNum(settleTransData.getShareAmt());
                dealDetail.setTradeId(settleTransData.getTradeId());
                //费用标识，没有0，增加1，扣减-1
                dealDetail.setCommissionAmtFlg(commissionAmtFlg);
                dealDetail.setJsfAmtFlg(jsfAmtFlg);
                dealDetail.setZgfAmtFlg(zgfAmtFlg);
                dealDetail.setStampFeeFlg(stampFeeFlg);
                dealDetail.setSettleFeeFlg(settleFeeFlg);
                dealDetail.setSxfAmtFlg(sxfAmtFlg);
                dealDetail.setGhfAmtFlg(ghfAmtFlg);
                //隐藏用于记录调整值字段
                dealDetail.setCommissionAmt2(settleTransData.getCommissionAmt());
                dealDetail.setJsfAmt2(settleTransData.getJsfAmt());
                dealDetail.setZgfAmt2(settleTransData.getZgfAmt());
                dealDetail.setStampFee2(settleTransData.getStampFee());
                dealDetail.setSettleFee2(settleTransData.getSettleFee());
                dealDetail.setSxfAmt2(settleTransData.getSxfAmt());
                dealDetail.setGhfAmt2(settleTransData.getGhfAmt());
                dealDetail.setTradeAmt2(BigDecimal.ZERO);
                dealDetailList.add(dealDetail);
            }
        }
        dto.setDealDetailList(dealDetailList);
        return dto;
    }

    /**
     * 实现docomplement方法
     */
    public void doComplete(DayEndAccount002Dto entity, String type) {
        //更新明细
        if (entity.getDealDetailList() !=null && entity.getDealDetailList().size() > 0) {
            for (DayEndAccountDto dto : entity.getDealDetailList()) {
                SettleTransData data = new SettleTransData();
                data.setCommissionAmt(dto.getCommissionAmt() == null ? BigDecimal.ZERO : dto.getCommissionAmt());
                if (dto.getCommissionAmtFlg() != null && StringUtil.isEmpty(data.getIsPayWithTrade())) {
                    data.setIsPayWithTrade(dto.getCommissionAmtFlg().compareTo(BigDecimal.ZERO) == 0
                            ? DictCodeListExt1.是否_否.getValue() : DictCodeListExt1.是否_是.getValue());
                }
                data.setJsfAmt(dto.getJsfAmt() == null ? BigDecimal.ZERO : dto.getJsfAmt());
                data.setZgfAmt(dto.getZgfAmt() == null ? BigDecimal.ZERO : dto.getZgfAmt());
                data.setStampFee(dto.getStampFee() == null ? BigDecimal.ZERO : dto.getStampFee());
                data.setSettleFee(dto.getSettleFee() == null ? BigDecimal.ZERO : dto.getSettleFee());
                data.setGhfAmt(dto.getGhfAmt() == null ? BigDecimal.ZERO : dto.getGhfAmt());
                data.setShareAmt(dto.getTradeNum() == null ? BigDecimal.ZERO : dto.getTradeNum());
                data.setFpriceAmt(dto.getTradeAmt() == null ? BigDecimal.ZERO : dto.getTradeAmt());
                //2021-07-13修改，原交易金额为0
                //data.setTradeAmt(ArithmeticUtil.doAdd(data.getTradeAmt(), dto.getTradeAmt2()));
                data.setTradeAmt(ArithmeticUtil.doAdd(dto.getTradeAmt(), dto.getTradeAmt2()));
                data.setTradeId(dto.getTradeId());
                settleTransDataMapper.updateByPrimaryKeySelective(data);
            }

            //新数据进行对账
            //处理对账逻辑
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("seqNo", entity.getSeqNo());
            chuliAccount(param);
        }
    }

    public Page<SelectOptionDto> selectCustomerId(int page, int length,Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page, length, () -> dayEndAccountMapper.selectCustomerId(param));
    }

    public Page<SelectOptionDto> queryBusiAcctNo(int page, int length,Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page, length, () -> dayEndAccountMapper.queryBusiAcctNo(param));
    }

    public Page<DayEndAccountDto> selectPage(int page, int length, Map<String, Object> param) {
        Object busiAcctNo = param.get("busiAcctNo");
        Page<DayEndAccountDto> result;
        if (busiAcctNo != null) {
            param.put("busiAcctNoList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)busiAcctNo));
        }
        else {
            param.put("busiAcctNoList", new ArrayList<>());
        }
        if(StringUtil.isNotEmpty(param.get("parentId")) && !StringUtil.equals(param.get("parentId"),"root")) {
            String param1 = (String) param.get("parentId");
            String[] s = param1.split(",");
            if(!"null".equals(s[0]))return new Page<>();

            if("1".equals(s[1])){
                param.put("busiAcctNo",s[2]);
                param.put("settleDate",s[3]);
                param.put("ccy",s[4]);
                return super.queryForPage(page, length, () -> dayEndAccountMapper.selectPage1(param));
            }else{
                param.put("finprodId",s[2]);
                param.put("settleDate",s[3]);
                param.put("secAcctId",s[4]);
                if(StringUtil.isNotEmpty(s[5])&&!"null".equals(s[5])){
                    param.put("termType",s[5]);
                }
                return  super.queryForPage(page, length, () -> dayEndAccountMapper.selectPage2(param));
            }
        }
        return super.queryForPage(page, length, () -> dayEndAccountMapper.selectPage(param));
    }

    private void setFinprodMarketId(Page<DayEndAccountDto> page){
        for(DayEndAccountDto dto:page.getRoot()){
            if(StringUtil.isNotEmpty(dto.getFinprodId())){
                FinProduct finProduct = finProductMapper.selectByPrimaryKey(dto.getFinprodId());
                dto.setFinprodMarketId(finProduct.getFinprodMarketId());
            }
        }
    }
}
