package com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.dao.CfetsBondAutoDurDealGenerateMapper;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.repo.AutoPeriodRepoCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 回购存续期自动生成
 * @author zznn
 * @date 2022/1/11
 */
@Service
@Scope("prototype")
public class CfetsRepoAutoDurDealGenerateProcessorService extends BaseService {

    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    @Autowired
    private CfetsBondAutoDurDealGenerateMapper cfetsBondAutoDurDealGenerateMapper;
    
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    public void execute(Date batchDate) {
        Map<String, Object> paramMap1 = new HashMap<String, Object>();
        paramMap1.put("busiType", DictCodeList.共通业务类型_质押式回购存续期.getValue());
        paramMap1.put("finProdType2", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()));
        paramMap1.put("updateUser", "system");
        paramMap1.put("batchDate", batchDate);
        List<String> totalTradeIds = cfetsBondAutoDurDealGenerateMapper.selectTrdFinProductDealTotalData(paramMap1);
        for(String totalTradeId : totalTradeIds){
            PositionHttpRequest.sendRevoke(totalTradeId,DictCodeListExt1.子系统代码_交易.getValue(),"REPO001");
        }
        //删除当日数据
        autoPeriodCommonService.deleteActData(DictCodeList.共通业务类型_质押式回购存续期.getValue(),
                DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),batchDate);
        autoPeriodCommonService.deleteActData(DictCodeList.共通业务类型_质押式回购存续期.getValue(),
                DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),batchDate);
        //查询待处理数据
        List<TrdRepoDurationDto> trdRepoDurationDtos = listTrdRepoDuration(batchDate);
        //处理待处理数据明细
        if(!CollectionUtils.isEmpty(trdRepoDurationDtos)){
            List<TrdBondDurDbInertDto> trdBondDurDbInserts = new ArrayList<>();
            for (TrdRepoDurationDto item : trdRepoDurationDtos) {
                if(StringUtil.isNotEmpty(item.getFinprodId())){
                    TrdRepoDurationDto trdRepoDuration = getTrdRepoDuration(BeanUtil.object2Map(item));
                    BussinessCodeParameter parameter = new BussinessCodeParameter();
                    parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
                    trdRepoDuration.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));
                    // 画面设置的项目
                    trdRepoDuration.setSettleDate(trdRepoDuration.getmDate());
                    trdRepoDuration.setTradeAmt(trdRepoDuration.getTheoryAmt());
                    // 组合转换
                    trdRepoDuration.setActPortfolioId(trdRepoDuration.getPortfolioId());
                    trdRepoDuration.setPortfolioId(
                            AllTradeCommonUtil.exchangePortfolio(trdRepoDuration.getPortfolioId()));
                    trdBondDurDbInserts.add(genDateForDb(trdRepoDuration));
                }
            }
            //入库并处理头寸
            insertandposition(trdBondDurDbInserts);
        }
    }

    /**
     * 查询待处理数据
     * @param batchDate
     */
    public  List<TrdRepoDurationDto> listTrdRepoDuration(Date batchDate){
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("statusValid", Arrays.asList(DictCodeListExt1.画面数据状态_待处理.getValue()));
        paramMap.put("ps", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()));
        paramMap.put("notChannelFlag", "1");
        String mdate = DateUtil.format(batchDate);
        paramMap.put("mdateStart", mdate);
        paramMap.put("mdateEnd", mdate);
        List<TrdRepoDurationDto> tradeDataList = restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/selectAllData", paramMap,
                TrdRepoDurationDto.class);
        return  tradeDataList;
    }

    /**
     * 查询待处理交易详细数据
     * @param param
     * @return
     */
    public TrdRepoDurationDto getTrdRepoDuration(Map<String,Object> param){
        return restTemplate.postForObject(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findEffectiveData",
                param, TrdRepoDurationDto.class);
    }


    public TrdBondDurDbInertDto genDateForDb(TrdRepoDurationDto entity){
        TrdBondDurDbInertDto trdBondDurDbInertDto = new TrdBondDurDbInertDto();
        trdBondDurDbInertDto.setTradeId(entity.getFinprodId());
        //汇总
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(entity.getFinprodType2());
        trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(entity.getPs()==null ? DictCodeList.交易方向_到期.getValue() : entity.getPs());
        trdFinProductDealTotal.setSecManageAcctId(entity.getSecManageAcctId());
        trdFinProductDealTotal.setBranch(
                AutoPeriodCommonService.changePsToBranch(entity.getmDate(), DictCodeList.交易方向_到期.getValue()));
        trdFinProductDealTotal.setTradeDate(DateTime.parse(entity.getSettleDate()).toDate());
        trdFinProductDealTotal.setVdate(DateTime.parse(entity.getvDate()).toDate());
//        终止日存在使用终止日
        if(com.joyintech.framework.util.StringUtil.isNotEmpty(entity.getDeadDate())){
            trdFinProductDealTotal.setMdate(DateTime.parse(entity.getDeadDate()).toDate());
        }
        else{
            trdFinProductDealTotal.setMdate(DateTime.parse(entity.getmDate()).toDate());
        }
        trdFinProductDealTotal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
        trdFinProductDealTotal.setShareAmt(entity.getTheoryPrin());
        //实际利息 = 到期结算金额-理论本金
        trdFinProductDealTotal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrin()));
//        利息总额
        trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
        trdFinProductDealTotal.setTradeMarket(entity.getTradeMarket());
        trdFinProductDealTotal.setCounterId(entity.getCounterId());
        trdFinProductDealTotal.setTrader(entity.getTrader());
        trdFinProductDealTotal.setCounterTrader(entity.getCounterTrader());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());

        //到期结算金额
        trdFinProductDealTotal.setFpriceAmt(entity.getFpriceAmt());
        //通道费
        if(com.joyintech.framework.util.StringUtil.isNotEmpty(entity.getChannelFee())){
            trdFinProductDealTotal.setFeeAmt(entity.getChannelFee());
            if(com.joyintech.framework.util.StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())){
                trdFinProductDealTotal.setFeeAmtPay(entity.getChannelFee());
            }else{
                trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee());
            }
        }
        // 通道交易新增 start
        trdFinProductDealTotal.setChlId(entity.getChlId());
        //期待值：要加上罚息金额
        if(entity.getPenaltyIntamt()!= null){
            trdFinProductDealTotal.setIntAmt(ArithmeticUtil.doAdd(entity.getPenaltyIntamt(),trdFinProductDealTotal.getIntAmt()));
        }
        if(entity.getActTradeFee()!= null){
            trdFinProductDealTotal.setFeeAmt(ArithmeticUtil.doAdd(trdFinProductDealTotal.getFeeAmt()==null?BigDecimal.ZERO:trdFinProductDealTotal.getFeeAmt(),entity.getActTradeFee()));
            trdFinProductDealTotal.setFeeAmtUnpay(
                    ArithmeticUtil.doAdd(trdFinProductDealTotal.getFeeAmtUnpay()==null?BigDecimal.ZERO:trdFinProductDealTotal.getFeeAmtUnpay(),entity.getActTradeFee()));
        }
        trdFinProductDealTotal.setFpriceAmt(entity.getTheoryAmt());
        trdFinProductDealTotal.setRemark(entity.getRemark());
        //汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        if (DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(entity.getFinprodType2())) {
            trdFinProductDealTotal.setTgBusiType(DictCodeListExt1.平安业务类型_正回购到期.getValue());
            trdFinProductDealTotal.setPs(DictCodeListExt3.交易方向_正回购.getValue());
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_正回购到期.getValue());
        } else if (DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(entity.getFinprodType2())) {
            trdFinProductDealTotal.setTgBusiType(DictCodeListExt1.平安业务类型_逆回购到期.getValue());
            trdFinProductDealTotal.setPs(DictCodeListExt3.交易方向_逆回购.getValue());
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        }
        paTrdFinProductDealTotal.setRate(ArithmeticUtil.divide(entity.getIntRate(), 100, 5));
        paTrdFinProductDealTotal.setTheoryAmt(entity.getTheoryAmt());
        paTrdFinProductDealTotal.setTheoryInt(entity.getTheoryInt());
        paTrdFinProductDealTotal.setTheoryPrin(entity.getTheoryPrin());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        //交易明细
        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        List<FinCashPlan> 现金流 = restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findFinCashInfo", entity.getFinprodId(),
                FinCashPlan.class);
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        现金流.forEach(finCashPlan -> {
            TrdProductDeal deal = new TrdProductDeal();
            deal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
            deal.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
            deal.setCounterId(entity.getCounterId());
            deal.setTrader(entity.getTrader());
            deal.setCounterTrader(entity.getCounterTrader());
            deal.setFinprodId(entity.getFinprodId());
            deal.setFinprodType2(entity.getFinprodType2());
            deal.setSecManageAcctId(entity.getSecManageAcctId());
            deal.setCcy(entity.getCcy());
            deal.setTradeDate(DateTime.parse(entity.getSettleDate()).toDate());
            //存入到期交付日
            if(com.joyintech.framework.util.StringUtil.isNotEmpty(entity.getMpayDate())){
                deal.setPayDate(DateTime.parse(entity.getMpayDate()).toDate());
            }
            deal.setVdate(DateTime.parse(entity.getvDate()).toDate());
            if(com.joyintech.framework.util.StringUtil.equals(DictCodeListExt3.交易方向_投资端_提前终止.getValue(),entity.getPs())){
                deal.setMdate(DateTime.parse(entity.getSettleDate()).toDate());
            }
            else{
                deal.setMdate(DateTime.parse(entity.getmDate()).toDate());
            }
            deal.setSettleDate(DateTime.parse(entity.getSettleDate()).toDate());
            deal.setShareAmt(entity.getTheoryPrin());
            deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            deal.setIsCancel(DictCodeList.是否_否.getValue());
            deal.setTradeMarket(entity.getTradeMarket());

            deal.setWorkflowId(entity.getWorkflowId());
            deal.setTotleTradeId(entity.getBusiCode());
            deal.setCashId(finCashPlan.getCashId());
            deal.setCancelRemark(entity.getRevokeReason());
            if (DictCodeList.现金流类型_到期还本.getValue().equals(finCashPlan.getCashType())) {
                deal.setPs(DictCodeList.交易方向_还本到账.getValue());
                deal.setStatus(DictCodeList.交易方向_到期.getValue());
                deal.setBranch(AutoPeriodCommonService.changePsToBranch(entity.getmDate(), deal.getPs()));
                deal.setPs(DictCodeList.交易方向_还本到账.getValue());
                deal.setTradeAmt(entity.getTheoryPrin());
                deal.setPrinAmt(entity.getTheoryPrin());
                deal.setFpriceAmt(entity.getTheoryPrin());
                deal.setIntAmt(BigDecimal.ZERO);
            }
            else if (DictCodeList.现金流类型_到期收息.getValue().equals(finCashPlan.getCashType())) {
                deal.setStatus(DictCodeList.交易方向_收息.getValue());
                deal.setPs(DictCodeList.交易方向_收息.getValue());
                deal.setBranch(AutoPeriodCommonService.changePsToBranch(entity.getmDate(), deal.getPs()));
                deal.setPs(DictCodeList.交易方向_收息到账.getValue());
                BigDecimal intAmt = ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrin());
                deal.setIntAmt(intAmt);
                deal.setFpriceAmt(intAmt);
                deal.setPrinAmt(BigDecimal.ZERO);
                deal.setTradeAmt(ArithmeticUtil.subtract(entity.getTradeAmt(),entity.getTheoryPrin()));
                if(com.joyintech.framework.util.StringUtil.isNotEmpty(entity.getChannelFee())){
                    deal.setFeeAmt(entity.getChannelFee());
                    if(com.joyintech.framework.util.StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())){
                        deal.setFeeAmtPay(entity.getChannelFee());
                    }else{
                        deal.setFeeAmtUnpay(entity.getChannelFee());
                    }
                }
                //期待值：要加上罚息金额
                if(entity.getPenaltyIntamt()!= null){
                    deal.setIntAmt(ArithmeticUtil.doAdd(entity.getPenaltyIntamt(),deal.getIntAmt()));
                    deal.setFpriceAmt(deal.getIntAmt());
                }
                if(entity.getActTradeFee()!= null){
                    deal.setFeeAmt(ArithmeticUtil.doAdd(deal.getFeeAmt()==null?BigDecimal.ZERO:deal.getFeeAmt(),entity.getActTradeFee()));
                    deal.setFeeAmtUnpay(
                            ArithmeticUtil.doAdd(deal.getFeeAmtUnpay()==null?BigDecimal.ZERO:deal.getFeeAmtUnpay(),entity.getActTradeFee()));
                }
                if(com.joyintech.framework.util.StringUtil.equals(DictCodeList.交易方向_到期续做.getValue(),entity.getPs())){
                    deal.setIntAmt(ArithmeticUtil.subtract(entity.getFpriceAmt(), entity.getTheoryPrinex()));
                }
            }
            deal.setChlId(entity.getChlId());
            // 通道交易新增 end
            deal.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
            trdProductDeals.add(deal);
        });
        //划款指令
        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = AutoPeriodRepoCommonService.getTrdProductDeal(entity);
        trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
        trdProductDeal.setTotleTradeId(entity.getBusiCode());
        trdProductDeal.setTradeAmt(entity.getTradeAmt());
        trdProductDeal.setSettleDate(DateUtil.parse(entity.getSettleDate()));
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDeals.add(trdProductDeal);
        //交易明细附属表
        List<TrdProductDealAdd> trdProductDealAdds = new ArrayList<>();
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
        trdProductDealAdd.setPortfolioId(entity.getPortfolioId());
        trdProductDealAdd.setEntrustId(entity.getPortfolioId());
        trdProductDealAdds.add(trdProductDealAdd);
        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(entity.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);
        //划款指令
        List<StlPayOrders> stlPayOrders = new ArrayList<>();
        List<WflStlPayOrders> wflStlPayOrders = pinganBatchCommonService.generateSettleOrder(parameterDto);
        if(!CollectionUtils.isEmpty(wflStlPayOrders)){
            for (WflStlPayOrders wflStlPayOrder : wflStlPayOrders) {
                StlPayOrders stlPayOrder = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrder, wflStlPayOrder);
                stlPayOrders.add(stlPayOrder);
            }
            trdBondDurDbInertDto.setStlPayOrdersList(stlPayOrders);
        }
        trdBondDurDbInertDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
        trdBondDurDbInertDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        trdBondDurDbInertDto.setTrdProductDealList(trdProductDeals);
        trdBondDurDbInertDto.setTrdProductDealAdds(trdProductDealAdds);
        return trdBondDurDbInertDto;
    }

    /**
     *  入库并处理头寸
     * @param trdBondDurDbInserts
     */
    public void  insertandposition(List<TrdBondDurDbInertDto> trdBondDurDbInserts){
        for(TrdBondDurDbInertDto trdBondDurDbInertDto : trdBondDurDbInserts){
            autoPeriodCommonService.insertCfetsDealData(trdBondDurDbInertDto);
            sendPositionMethod(trdBondDurDbInertDto);
        }
    }


    /**
     * 组装头寸
     * @param trdBondDurDbInertDto
     */
    private void sendPositionMethod(TrdBondDurDbInertDto trdBondDurDbInertDto) {
        //调用头寸
        Map<String,Object> req = new HashMap<>();
        List<Map<String,Object>> ptlCashList = new ArrayList<>();
        List<Map<String,Object>> ptlSecList = new ArrayList<>();
        TrdFinProductDealTotal trdFinProductDealTotal = trdBondDurDbInertDto.getTrdFinProductDealTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", trdBondDurDbInertDto.getTradeId());
        List<RepoInfo> repoInfoList = restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findRepoInfo", map,
                RepoInfo.class);
        for (TrdProductDeal dto : trdBondDurDbInertDto.getTrdProductDealList()) {
            //调用头寸
            Map<String, Object> ptlCash = new HashMap<>();
            ptlCash.put("portfolioId", trdFinProductDealTotal.getPortfolioId());
            ptlCash.put("tradeId", dto.getTradeId());
            ptlCash.put("etrId", dto.getEntrustId());
            ptlCash.put("cashAmount", dto.getTradeAmt());
            if (com.joyintech.framework.util.StringUtil.isNotEmpty(trdFinProductDealTotal.getPortfolioId())) {
                Map<String, Object> acctIdMap = new HashMap<String, Object>();
                acctIdMap.put("portfolioId", trdFinProductDealTotal.getPortfolioId());
                acctIdMap.put("acctType", BussinessDictCodeList.资金账户类型_托管账户.getValue());
                String cashAcctId = cfetsBondAutoDurDealGenerateMapper.qryPortfolioCashAcctId(acctIdMap);
                if (com.joyintech.framework.util.StringUtil.isNotEmpty(cashAcctId)) {
                    ptlCash.put("cashAcctId", cashAcctId);
                }
            }
            if (repoInfoList != null && repoInfoList.size() > 0) {
                for (RepoInfo repoInfo : repoInfoList) {
                    Map<String,Object> ptlSec = new HashMap<>();
                    ptlSec.put("portfolioId",trdFinProductDealTotal.getPortfolioId());
                    ptlSec.put("finprodId",repoInfo.getPledgeFinprodId());
                    if(StringUtil.isEmpty(trdFinProductDealTotal.getSecManageAcctId())){
                        ptlSec.put("secAcctId",cfetsBondAutoDurDealGenerateMapper.getSecAcctId(trdFinProductDealTotal.getPortfolioId(),repoInfo.getPledgeFinprodId(),trdFinProductDealTotal.getTradeMarket()));
                    }else{
                        ptlSec.put("secAcctId",dto.getSecManageAcctId());
                    }
                    ptlSec.put("tradeId",dto.getTradeId());
                    ptlSec.put("etrId",dto.getEntrustId());
                    ptlSec.put("secAmount",repoInfo.getPledgeNumber());
                    ptlSecList.add(ptlSec);
                }
            }
            ptlCashList.add(ptlCash);
        }
        req.put("interfaceType","REPO001");
        req.put("userId","system");
        req.put("totalTradeId",trdFinProductDealTotal.getTotleTradeId());
        req.put("flowStatus",DictCodeListExt3.流程节点_审批生效.getValue());
        req.put("modelType",DictCodeListExt1.子系统代码_交易.getValue());
        req.put("balanceCheck","N");
        req.put("busiType",DictCodeListExt3.头寸业务类型_质押式回购到期.getValue());
        req.put("systemAct",DictCodeListExt3.系统动作_新建.getValue());
        req.put("decisionAct",DictCodeListExt3.系统动作_新建.getValue());
        req.put("busiAct","22");
        if(StringUtil.equals(trdFinProductDealTotal.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())){
            req.put("ps","03");
        }else{
            req.put("ps","04");
        }
        req.put("tradePlace",BussinessDictCodeList.交易场所_银行间.getValue());
        req.put("cdate",trdFinProductDealTotal.getTradeDate());
        req.put("ccy",trdFinProductDealTotal.getCcy());
        req.put("repoPledgeModel",CacheUtil.getSysParam("repo_pledge_model"));
        req.put("ptlCashList",ptlCashList);
        req.put("ptlSecList",ptlSecList);
        PositionHttpRequest.send(req);
    }
}

