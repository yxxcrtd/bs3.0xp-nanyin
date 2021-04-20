package com.joyintech.tams.invest.common.cstp.service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.dto.cstp.EtrEntrustExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 债券
 * @auther zhangnian
 * @date 2021/3/25
 */
@Service
public class CstpCommonMatchBondImplService{

    private static final String  CFTES = "CFTES";

    @Autowired
    private CstpCommonService cstpCommonService;

    @Autowired
    private CstpCommonMatchService cstpCommonMatchService;
    public Map<String,Object> dealMatchEntrust(Map<String,Object> map) {
        String quoteIdFlag = (String)map.get("quoteIdFlag");
        if(StringUtil.equals(quoteIdFlag,"01")){
            //按比例分配
            dealMatchEntrustByProportion(map,x->{
                cstpCommonMatchService.saveMatchInfo(x);
            });
        }
        else{
            //按规则分配
            dealMatchEntrustByRule(map,x->{
                cstpCommonMatchService.saveMatchInfo(x);
            });
        }
        return  map;
    }

    /**
     * 按比例分配
     * @param map
     * @return
     */
    private void dealMatchEntrustByProportion(Map<String,Object> map,Consumer<Map<String,Object>> func){
        String isDzh = (String)map.get("isDzh");
        if(StringUtil.equals(isDzh,"Y")){
            matchForDzh(map);
        }else{
            matchForNotDzh(map);
        }
        func.accept(map);
    }

    /**
     * 按规则分配
     * @param map
     * @return
     */
    private void dealMatchEntrustByRule(Map<String,Object> map, Consumer<Map<String,Object>> func){
        InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal) map.get("interfTradSecdeal");
        String ps = interfTradSecdeal.getPs();
        if(StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())){
            dealMatchEntrustByRuleMr(map);
        }else if(StringUtil.equals(ps,DictCodeList.交易方向_卖出.getValue())){
            dealMatchEntrustByRuleMc(map);
        }
        func.accept(map);
    }


    /**
     * 买入
     * @param map
     */
    private void dealMatchEntrustByRuleMr(Map<String,Object> map){
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal) map.get("interfTradSecdeal");
        List<EtrEntrustExt>  etrEntrustExts = (List<EtrEntrustExt>)map.get("etrEntrustExts");
        String execId = (String)map.get("execId");
        String matchType = (String)map.get("matchType");
        BigDecimal prinAmt = ArithmeticUtil.divide(interfTradSecdeal.getPrinamt(),new BigDecimal(100),2);
        for(EtrEntrustExt item : etrEntrustExts){
            TrdMatchInfo  trdMatchInfo = new TrdMatchInfo();
            BigDecimal avaNum = item.getAvailableNum();
            //成交质押券已全部分配
            if(prinAmt.compareTo(BigDecimal.ZERO)<=0){
                break;
            }
            if(avaNum.compareTo(BigDecimal.ZERO)<=0){
                continue;
            }
            //扣减持仓
            BigDecimal subSec = BigDecimal.ZERO;
            if(prinAmt.compareTo(avaNum)>=0){
                subSec = avaNum;
            }else{
                subSec = prinAmt;
            }
            //该委托额度 递归减少
            item.setAvailableNum(ArithmeticUtil.subtract(avaNum,subSec));
            prinAmt = ArithmeticUtil.subtract(prinAmt,subSec);
            trdMatchInfo.setCstpTradeId(execId);
            trdMatchInfo.setEntrustId(item.getEntrustId());
            trdMatchInfo.setTradeDate(interfTradSecdeal.getDealdate());
            trdMatchInfo.setMatchType(matchType);
            trdMatchInfo.setfSysId(CFTES);
            trdMatchInfo.setMatchNum(subSec);
            trdMatchInfos.add(trdMatchInfo);
        }
        map.put("trdMatchInfos",trdMatchInfos);
    }

    /**
     * 卖出
     * @param map
     */
    private void dealMatchEntrustByRuleMc(Map<String,Object> map){
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal) map.get("interfTradSecdeal");
        List<EtrEntrustExt>  etrEntrustExts = (List<EtrEntrustExt>)map.get("etrEntrustExts");
        String execId = (String)map.get("execId");
        String matchType = (String)map.get("matchType");
        String finprodId = "F01_"+interfTradSecdeal.getSecid()+".IB";
        BigDecimal prinAmt = ArithmeticUtil.divide(interfTradSecdeal.getPrinamt(),new BigDecimal(100),2);
        //组合下持仓信息
        Map<String,Object> secByPtlMap = new HashMap<>();
        BigDecimal positionGroupByPtl = BigDecimal.ZERO;
        for(EtrEntrustExt item : etrEntrustExts){
            TrdMatchInfo  trdMatchInfo = new TrdMatchInfo();
            //成交质押券已全部分配
            if(prinAmt.compareTo(BigDecimal.ZERO)==0){
                break;
            }
            BigDecimal avaNum = item.getAvailableNum();
            //扣减持仓
            BigDecimal subSec = BigDecimal.ZERO;
            Map<String,Object> avaMap = new HashMap<>();
            avaMap.put("portfolioId",item.getPortfolioId());
            avaMap.put("ccy",item.getCcy());
            String key = item.getPortfolioId()+finprodId;
            if(secByPtlMap.containsKey(key)){
                positionGroupByPtl = (BigDecimal) secByPtlMap.get(key);
                if(positionGroupByPtl.compareTo(BigDecimal.ZERO)<=0){continue;}
            }else{
                avaMap.put("tradeDate", DateUtil.format(interfTradSecdeal.getDealdate()));
                avaMap.put("finprodId",finprodId);
                avaMap = cstpCommonMatchService.getAvailableCash(avaMap);
                //BigDecimal position = new BigDecimal((String)avaMap.get("position"));
                BigDecimal position = new BigDecimal("18000000");
                if(null!=position&&position.compareTo(BigDecimal.ZERO)>0){
                    positionGroupByPtl = position;
                    secByPtlMap.put(key,position);
                }else{
                    continue;
                }
            }
            subSec = positionGroupByPtl;
            //定义一个临时比较对象
            BigDecimal tempSec = positionGroupByPtl.compareTo(avaNum)>=0?avaNum:positionGroupByPtl;
            if(prinAmt.compareTo(tempSec)>=0){
                subSec = tempSec;
            }else{
                subSec = prinAmt;
            }
            //该委托额度 递归减少
            item.setAvailableNum(ArithmeticUtil.subtract(avaNum,subSec));
            prinAmt = ArithmeticUtil.subtract(prinAmt,subSec);
            positionGroupByPtl = ArithmeticUtil.subtract(positionGroupByPtl,subSec);
            secByPtlMap.put(key,positionGroupByPtl);
            trdMatchInfo.setCstpTradeId(execId);
            trdMatchInfo.setEntrustId(item.getEntrustId());
            trdMatchInfo.setTradeDate(interfTradSecdeal.getDealdate());
            trdMatchInfo.setMatchType(matchType);
            trdMatchInfo.setfSysId(CFTES);
            trdMatchInfo.setMatchNum(subSec);
            trdMatchInfos.add(trdMatchInfo);
        }
        map.put("trdMatchInfos",trdMatchInfos);
    }

    /**
     * 有报价多组合匹配
     * @param map
     */
    private void matchForDzh(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal) map.get("interfTradSecdeal");
        String execId = (String)map.get("execId");
        String quoteId = (String)map.get("quoteId");
        String matchType = (String)map.get("matchType");
        List<PtlAllocationDto> ptlAllocations = cstpCommonService.selectEtrReByQuoteId(quoteId);
        Map<String,Object> mapFpriceAmt= new HashMap<>();
        BigDecimal  fpriceAmtTotal = null!=interfTradSecdeal.getPrinamt()?interfTradSecdeal.getPrinamt():BigDecimal.ZERO;
        mapFpriceAmt.put("fpriceAmt",fpriceAmtTotal);
        mapFpriceAmt.put("shareAmt",fpriceAmtTotal);
        ptlAllocations = cstpCommonService.组合分配拆分(ptlAllocations,mapFpriceAmt);
        for(PtlAllocationDto ptlAllocationDto:ptlAllocations){
            TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
            trdMatchInfo.setCstpTradeId(execId);
            trdMatchInfo.setEntrustId(ptlAllocationDto.getEntrustId());
            trdMatchInfo.setMatchNum(ArithmeticUtil.divide(null!=ptlAllocationDto.getShareAmt()?ptlAllocationDto.getShareAmt():ptlAllocationDto.getFpriceAmt(),new BigDecimal("100"),2));
            trdMatchInfo.setMatchType(matchType);
            trdMatchInfo.setTradeDate(interfTradSecdeal.getDealdate());
            trdMatchInfo.setfSysId(CFTES);
            trdMatchInfos.add(trdMatchInfo);
        }
        map.put("trdMatchInfos",trdMatchInfos);
    }

    /**
     * 有报价单组合匹配
     * @param map
     */
    private void matchForNotDzh(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        InterfTradSecdeal interfTradSecdeal = (InterfTradSecdeal) map.get("interfTradSecdeal");
        String execId = (String)map.get("execId");
        String matchType = (String)map.get("matchType");
        String entrustId = (String)map.get("entrustId");
        TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
        trdMatchInfo.setCstpTradeId(execId);
        trdMatchInfo.setEntrustId(entrustId);
        trdMatchInfo.setMatchNum(ArithmeticUtil.divide(interfTradSecdeal.getPrinamt(),new BigDecimal("100"),2));
        trdMatchInfo.setMatchType(matchType);
        trdMatchInfo.setTradeDate(interfTradSecdeal.getDealdate());
        trdMatchInfo.setfSysId(CFTES);
        trdMatchInfos.add(trdMatchInfo);
        map.put("trdMatchInfos",trdMatchInfos);
    }
}

