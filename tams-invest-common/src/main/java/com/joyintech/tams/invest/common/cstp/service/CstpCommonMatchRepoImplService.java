package com.joyintech.tams.invest.common.cstp.service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.common.base.entity.TrdPledgeMatchInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.cstp.dao.CstpCommonMatchMapper;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import com.joyintech.tams.invest.common.dto.cstp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 回购
 * @auther zhangnian
 * @date 2021/3/25
 */
@Service
public class CstpCommonMatchRepoImplService{

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String  CFTES = "CFTES";

    @Autowired
    private CstpCommonMatchMapper cstpCommonMatchMapper;

    @Autowired
    private CstpCommonMatchService cstpCommonMatchService;

    @Autowired
    private CstpCommonService cstpCommonService;


    public Map<String,Object> dealMatchEntrust(Map<String,Object> map) {
        String quoteIdFlag = (String)map.get("quoteIdFlag");
        if(StringUtil.equals(quoteIdFlag,"01")){
            //按比例分配
            dealMatchEntrustByProportion(map,x ->{
                cstpCommonMatchService.saveMatchInfo(x);
            });
        }
        else{
            //按规则分配
            dealMatchEntrustByRule(map,x->{
                cstpCommonMatchService.saveMatchInfo(x);
            });
        }
        return map;
    }

    /**
     * 按比例分配
     * @param map
     * @param func
     */
    private void dealMatchEntrustByProportion(Map<String,Object> map, Consumer<Map<String,Object>>func){
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
     * @param func
     */
    private void dealMatchEntrustByRule(Map<String,Object> map,Consumer<Map<String,Object>> func){
        InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo) map.get("interfOpicsTradRepo");
        String ps = interfOpicsTradRepo.getPs();
        if(StringUtil.equals(ps,DictCodeList.回购拆借方向_正回购.getValue())){
            dealMatchEntrustByRuleZhg(map);
        }else if(StringUtil.equals(ps,DictCodeList.回购拆借方向_逆回购.getValue())){
            dealMatchEntrustByRuleNhg(map);
        }
        func.accept(map);
    }


    /**
     * 正回购
     * @param map
     * @return
     */
    private void dealMatchEntrustByRuleZhg(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        //匹配券面
        List<TrdPledgeMatchInfo> trdPledgeMatchInfos = new ArrayList<>();
        InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo) map.get("interfOpicsTradRepo");
        List<EtrEntrustExt>  etrEntrustExts = (List<EtrEntrustExt>)map.get("etrEntrustExts");
        String execId = (String)map.get("execId");
        String matchType = (String)map.get("matchType");
        String isktg = (String)map.get("isKtg");
        //该list  默认第一个为中债  第二个为上清  下行接口逗号隔开处理
        List<String> acctNoList = (List<String>)map.get("acctNoList");
        //组合下持仓信息
        Map<String,Object> secByPtlMap = new HashMap<>();
        //成交质押券信息
        List<InterfTradRepoasstDto> interfTradRepoasstDtos = cstpCommonMatchMapper.getInterfTradRepoasst(execId);
        //成交后折算后总金额
        BigDecimal repoAssetConversionDealAmtTotal = interfTradRepoasstDtos.stream().map(InterfTradRepoasstDto::getDealamt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal repoamt = interfOpicsTradRepo.getRepoamt();
        BigDecimal positionGroupByPtl = BigDecimal.ZERO;
        for(InterfTradRepoasstDto interfTradRepoasstDto : interfTradRepoasstDtos){
            //券面总额(百元)
            BigDecimal facetotalamt = ArithmeticUtil.divide(interfTradRepoasstDto.getFacetotalamt(),new BigDecimal(100),2);
            //匹配额度
            BigDecimal matchNum = BigDecimal.ZERO;BigDecimal matchNumCB = BigDecimal.ZERO;BigDecimal matchNumSC = BigDecimal.ZERO;
            //折算比例
            BigDecimal rate = interfTradRepoasstDto.getConvrate();
            //托管机构
            String registOrg = interfTradRepoasstDto.getRegistOrg();
            String secAcctId = 处理证券账户(isktg,registOrg,acctNoList);
            for(EtrEntrustExt item : etrEntrustExts){
                TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
                TrdPledgeMatchInfo trdPledgeMatchInfo = new TrdPledgeMatchInfo();
                //成交质押券已全部分配
                if(facetotalamt.compareTo(BigDecimal.ZERO)==0){
                    break;
                }
                BigDecimal avaNum = item.getAvailableNum();
                //扣减金额
                BigDecimal subAvaNum = BigDecimal.ZERO;
                //扣减持仓
                BigDecimal subSec = BigDecimal.ZERO;
                Map<String,Object> avaMap = new HashMap<>();
                avaMap.put("portfolioId",item.getPortfolioId());
                avaMap.put("ccy",item.getCcy());
                String key = item.getPortfolioId()+interfTradRepoasstDto.getFinprodId();
                if(secByPtlMap.containsKey(key)){
                    positionGroupByPtl = (BigDecimal) secByPtlMap.get(key);
                    if(positionGroupByPtl.compareTo(BigDecimal.ZERO)<=0){continue;}
                }else{
                    avaMap.put("tradeDate", DateUtil.format(interfOpicsTradRepo.getDealdate()));
                    avaMap.put("finprodId",interfTradRepoasstDto.getFinprodId());
                    avaMap.put("secAcctId",secAcctId);
                    avaMap = cstpCommonMatchService.getAvailableCash(avaMap);
                    BigDecimal position = new BigDecimal((String)avaMap.get("position"));
                    if(null!=position&&position.compareTo(BigDecimal.ZERO)>0){
                        positionGroupByPtl = position;
                        secByPtlMap.put(key,position);
                    }else{
                        continue;
                    }
                }
                subAvaNum = 券面折算本金(positionGroupByPtl,rate,facetotalamt,repoAssetConversionDealAmtTotal);
                subSec = positionGroupByPtl;
                //可用额度不够
                if(subAvaNum.compareTo(avaNum)>0){
                    //可用额度 折算券面
                    subSec = 本金折算券面(avaNum,rate,repoamt,repoAssetConversionDealAmtTotal);
                }
                if(subSec.compareTo(facetotalamt)>=0){
                    //折算后的大于成交单质押券券面
                    subSec = facetotalamt;
                }
                subAvaNum = 券面折算本金(subSec,rate,facetotalamt,repoAssetConversionDealAmtTotal);
                if(StringUtil.equals(isktg,"Y")){
                    if(StringUtil.equals(registOrg,DictCodeList.登记托管机构_中央国债登记结算有限责任公司.getValue())){
                        matchNumCB = ArithmeticUtil.doAdd(matchNumCB,subAvaNum);
                    }else{
                        matchNumSC = ArithmeticUtil.doAdd(matchNumSC,subAvaNum);
                    }
                    matchNum = ArithmeticUtil.doAdd(matchNumCB,matchNumSC);
                }else{
                    matchNum = ArithmeticUtil.doAdd(matchNum,subAvaNum);
                }
                //该委托额度 递归减少
                item.setAvailableNum(ArithmeticUtil.subtract(avaNum,subAvaNum));
                facetotalamt = ArithmeticUtil.subtract(facetotalamt,subSec);
                positionGroupByPtl = ArithmeticUtil.subtract(positionGroupByPtl,subSec);
                secByPtlMap.put(key,positionGroupByPtl);
                trdMatchInfo.setCstpTradeId(execId);
                trdMatchInfo.setEntrustId(item.getEntrustId());
                trdMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
                trdMatchInfo.setMatchType(matchType);
                trdMatchInfo.setfSysId(CFTES);
                trdMatchInfo.setMatchNumCb(matchNumCB);
                trdMatchInfo.setMatchNumSc(matchNumSC);
                trdMatchInfo.setMatchNum(matchNum);
                trdPledgeMatchInfo.setCstpTradeId(execId);
                trdPledgeMatchInfo.setEntrustId(item.getEntrustId());
                trdPledgeMatchInfo.setSecAcctId(secAcctId);
                trdPledgeMatchInfo.setPledgeFinprodId(interfTradRepoasstDto.getFinprodId());
                trdPledgeMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
                trdPledgeMatchInfo.setMatchNum(subSec);
                trdPledgeMatchInfo.setMatchType(matchType);
                trdPledgeMatchInfo.setfSysId(CFTES);
                trdMatchInfos.add(trdMatchInfo);
                trdPledgeMatchInfos.add(trdPledgeMatchInfo);
            }
        }
        //匹配额度实体  按着委托代码汇总
        trdMatchInfos = groupByEntrustId(trdMatchInfos);
        map.put("trdMatchInfos",trdMatchInfos);
        map.put("trdPledgeMatchInfos",trdPledgeMatchInfos);
    }

    /**
     * 逆回购  交易本金拆
     * @param map
     * @return
     */
    private void dealMatchEntrustByRuleNhg(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        //匹配券面
        List<TrdPledgeMatchInfo> trdPledgeMatchInfos = new ArrayList<>();
        InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo) map.get("interfOpicsTradRepo");
        List<EtrEntrustExt>  etrEntrustExts = (List<EtrEntrustExt>)map.get("etrEntrustExts");
        String execId = (String)map.get("execId");
        String matchType = (String)map.get("matchType");
        String isktg = (String)map.get("isKtg");
        //该list  默认第一个为中债  第二个为上清  下行接口逗号隔开处理
        List<String> acctNoList = (List<String>)map.get("acctNoList");
        List<InterfTradRepoasstDto> interfTradRepoasstDtos = cstpCommonMatchMapper.getInterfTradRepoasst(execId);
        //成交后折算后总金额
        BigDecimal repoAssetConversionDealAmtTotal = interfTradRepoasstDtos.stream().map(InterfTradRepoasstDto::getDealamt).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal repoamt = interfOpicsTradRepo.getRepoamt();
        for(InterfTradRepoasstDto interfTradRepoasstDto : interfTradRepoasstDtos){
            //交易金额
            BigDecimal dealamt = interfTradRepoasstDto.getDealamt();
            BigDecimal matchNum = BigDecimal.ZERO;BigDecimal matchNumCB = BigDecimal.ZERO;BigDecimal matchNumSC = BigDecimal.ZERO;
            //托管机构
            String registOrg = interfTradRepoasstDto.getRegistOrg();
            String secAcctId = 处理证券账户(isktg,registOrg,acctNoList);
            for(EtrEntrustExt item : etrEntrustExts){
                TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
                TrdPledgeMatchInfo trdPledgeMatchInfo = new TrdPledgeMatchInfo();
                BigDecimal subSec = BigDecimal.ZERO;
                BigDecimal avaNum = item.getAvailableNum();
                //全部分配完
                if(dealamt.compareTo(BigDecimal.ZERO)<=0){
                    break;
                }
                if(avaNum.compareTo(BigDecimal.ZERO)<=0){
                    continue;
                }
                if(avaNum.compareTo(dealamt)>=0){
                    avaNum = ArithmeticUtil.subtract(avaNum,dealamt);
                    dealamt = BigDecimal.ZERO;
                }else{
                    dealamt = ArithmeticUtil.subtract(dealamt,avaNum);
                    avaNum = BigDecimal.ZERO;
                }
                if(trdMatchInfo.getMatchNum().compareTo(BigDecimal.ZERO)>=0){
                    subSec = 本金折算券面(trdMatchInfo.getMatchNum(),interfTradRepoasstDto.getConvrate(),repoamt,repoAssetConversionDealAmtTotal);
                }
                //该委托额度 递归减少
                item.setAvailableNum(avaNum);
                matchNum = ArithmeticUtil.subtract(interfTradRepoasstDto.getDealamt(),dealamt);
                if(StringUtil.equals(isktg,"Y")){
                    //匹配额度
                    if(StringUtil.equals(registOrg,DictCodeList.登记托管机构_中央国债登记结算有限责任公司.getValue())){
                        matchNumCB = ArithmeticUtil.doAdd(matchNumCB,matchNum);
                    }else{
                        matchNumSC = ArithmeticUtil.doAdd(matchNumSC,matchNum);
                    }
                    matchNum = ArithmeticUtil.doAdd(matchNumCB,matchNumSC);
                }
                trdMatchInfo.setMatchNumCb(matchNumCB);
                trdMatchInfo.setMatchNumSc(matchNumSC);
                trdMatchInfo.setMatchNum(matchNum);
                trdMatchInfo.setCstpTradeId(execId);
                trdMatchInfo.setEntrustId(item.getEntrustId());
                trdMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
                trdMatchInfo.setMatchType(matchType);
                trdMatchInfo.setfSysId(CFTES);
                trdMatchInfos.add(trdMatchInfo);
                trdPledgeMatchInfo.setEntrustId(item.getEntrustId());
                trdPledgeMatchInfo.setSecAcctId(secAcctId);
                trdPledgeMatchInfo.setPledgeFinprodId(interfTradRepoasstDto.getFinprodId());
                trdPledgeMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
                trdPledgeMatchInfo.setMatchNum(subSec);
                trdPledgeMatchInfo.setMatchType(matchType);
                trdPledgeMatchInfo.setfSysId(CFTES);
                trdPledgeMatchInfos.add(trdPledgeMatchInfo);
            }
        }
        //匹配额度实体  按着委托代码汇总
        trdMatchInfos = groupByEntrustId(trdMatchInfos);
        map.put("trdMatchInfos",trdMatchInfos);
        map.put("trdPledgeMatchInfos",trdPledgeMatchInfos);
    }


    /**
     * 本金折算券面
     * @param initValue 券面
     * @param rate
     * @param dealNum
     * @param total
     * @return
     */
    private BigDecimal 券面折算本金(BigDecimal initValue,BigDecimal rate,BigDecimal dealNum,BigDecimal total){
       return ArithmeticUtil.divide(ArithmeticUtil.multiply(ArithmeticUtil.multiply(initValue,rate),ArithmeticUtil.multiply(dealNum,rate)),total,2);
    }

    /**
     * 券面折算比例
     * @param initValue 本金
     * @param rate
     * @param dealAmt
     * @param total
     * @return
     */
    private BigDecimal 本金折算券面(BigDecimal initValue,BigDecimal rate,BigDecimal dealAmt,BigDecimal total){
        return ArithmeticUtil.divide(ArithmeticUtil.multiply(initValue,total),ArithmeticUtil.multiply(dealAmt,rate),2);
    }

    private String  处理证券账户(String isKtg,String registOrg,List<String> acctNoList) {
        String secAcctId = StringUtil.EMPTY;
        if (!StringUtil.isEmptyList(acctNoList)) {
            if(StringUtil.equals(isKtg,"Y")){
                if (StringUtil.equals(registOrg, DictCodeList.登记托管机构_中央国债登记结算有限责任公司.getValue())) {
                    secAcctId = acctNoList.get(0);
                } else {
                    if(acctNoList.size()>1){
                        secAcctId = acctNoList.get(1);
                    }else{
                        log.warn("跨托管下行成交证券账户数目错误!");
                    }
                }
            }
            else{
                secAcctId = acctNoList.get(0);
            }
        }
        return secAcctId;
    }

    /**
     * 匹配额度  按着委托代码汇总
     * @param trdMatchInfos
     */
    private List<TrdMatchInfo> groupByEntrustId(List<TrdMatchInfo> trdMatchInfos){
        List<TrdMatchInfo> trdMatchInfosResult = new ArrayList<>();
        if(!StringUtil.isEmptyList(trdMatchInfos)){
            Map<String, List<TrdMatchInfo>> map = trdMatchInfos.stream().collect(Collectors.groupingBy(TrdMatchInfo::getEntrustId));
            for (Map.Entry<String, List<TrdMatchInfo>> entry : map.entrySet()) {
                TrdMatchInfo trdMatchInfoResult = new TrdMatchInfo();
                BigDecimal matchNum = BigDecimal.ZERO;BigDecimal matchNumCB = BigDecimal.ZERO;BigDecimal matchNumSC = BigDecimal.ZERO;
                for (TrdMatchInfo trdMatchInfo : entry.getValue()) {
                    matchNumCB = ArithmeticUtil.doAdd(matchNumCB,trdMatchInfo.getMatchNumCb());
                    matchNumSC = ArithmeticUtil.doAdd(matchNumSC,trdMatchInfo.getMatchNumSc());
                    matchNum = ArithmeticUtil.doAdd(matchNum,trdMatchInfo.getMatchNum());
                    trdMatchInfoResult.setCstpTradeId(trdMatchInfo.getCstpTradeId());
                    trdMatchInfoResult.setTradeDate(trdMatchInfo.getTradeDate());
                    trdMatchInfoResult.setMatchType(trdMatchInfo.getMatchType());
                }
                trdMatchInfoResult.setMatchNumCb(matchNumCB);
                trdMatchInfoResult.setMatchNumSc(matchNumSC);
                trdMatchInfoResult.setMatchNum(matchNum);
                trdMatchInfoResult.setEntrustId(entry.getKey());
                trdMatchInfoResult.setfSysId(CFTES);
                trdMatchInfosResult.add(trdMatchInfoResult);
            }
        }
        return trdMatchInfosResult;
    }

    /**
     * 有报价多组合匹配
     * @param map
     */
    private void matchForDzh(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        //匹配券面
        List<TrdPledgeMatchInfo> trdPledgeMatchInfos = new ArrayList<>();
        InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo) map.get("interfOpicsTradRepo");
        String execId = (String)map.get("execId");
        String quoteId = (String)map.get("quoteId");
        String isKtg = (String)map.get("isKtg");
        String matchType = (String)map.get("matchType");
        //该list  默认第一个为中债  第二个为上清  下行接口逗号隔开处理
        List<String> acctNoList = (List<String>)map.get("acctNoList");
        List<PtlAllocationDto> ptlAllocations = cstpCommonService.selectEtrReByQuoteId(quoteId);
        List<InterfTradRepoasstDto> interfTradRepoasstDtos = cstpCommonMatchMapper.getInterfTradRepoasst(execId);
        Map<String,Object> mapFpriceAmt= new HashMap<>();
        mapFpriceAmt.put("isKtg",isKtg);
        if(StringUtil.equals(isKtg,"Y")){
            mapFpriceAmt.put("cbfpriceAmt",null!=interfOpicsTradRepo.getCbFpriceAmt()?interfOpicsTradRepo.getCbFpriceAmt():BigDecimal.ZERO);
            mapFpriceAmt.put("scfpriceAmt",null!=interfOpicsTradRepo.getScFpriceAmt()?interfOpicsTradRepo.getScFpriceAmt():BigDecimal.ZERO);
        }else{
            BigDecimal  fpriceAmtTotal = null!=interfOpicsTradRepo.getRepoamt()?interfOpicsTradRepo.getRepoamt():BigDecimal.ZERO;
            mapFpriceAmt.put("fpriceAmt",fpriceAmtTotal);
            mapFpriceAmt.put("shareAmt",fpriceAmtTotal);
        }
        ptlAllocations = cstpCommonService.组合分配拆分(ptlAllocations,mapFpriceAmt);
        CfetsRepoDealQuotedPriceDto cfetsRepoDealQuotedPriceDto = new CfetsRepoDealQuotedPriceDto();
        cfetsRepoDealQuotedPriceDto.setPtlAllocationList(ptlAllocations);
        List<CfetsRepoPledgeBondDto> cfetsRepoPledgeBonds = new ArrayList<>();
        if (!StringUtil.isEmptyList(interfTradRepoasstDtos)) {
            interfTradRepoasstDtos.forEach(item -> {
                CfetsRepoPledgeBondDto cfetsRepoPledgeBondDto = new CfetsRepoPledgeBondDto();
                BeanUtil.copyPropertiesIgnoreNull(cfetsRepoPledgeBondDto, item);
                cfetsRepoPledgeBondDto.setFinprodId(item.getFinprodId());
                cfetsRepoPledgeBonds.add(cfetsRepoPledgeBondDto);
            });
            cfetsRepoDealQuotedPriceDto.setPledgeBondList(cfetsRepoPledgeBonds);
        }
        cfetsRepoDealQuotedPriceDto.setIsDzh("Y");
        cfetsRepoDealQuotedPriceDto.setPtlAllocationList(ptlAllocations);
        cfetsRepoDealQuotedPriceDto.setIsKtg(isKtg);
        cfetsRepoDealQuotedPriceDto.setPs(interfOpicsTradRepo.getPs());
        List<IbuQuoteEntCrepPledgeCommonDto> ibuQuoteEntCrepPledgeCommons= cstpCommonService.质押券拆分(cfetsRepoDealQuotedPriceDto);
        for(PtlAllocationDto ptlAllocationDto:ptlAllocations){
            TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
            trdMatchInfo.setCstpTradeId(execId);
            trdMatchInfo.setEntrustId(ptlAllocationDto.getEntrustId());
            trdMatchInfo.setMatchNum(ptlAllocationDto.getFpriceAmt());
            trdMatchInfo.setMatchNumCb(ptlAllocationDto.getCbFpriceAmt());
            trdMatchInfo.setMatchNumSc(ptlAllocationDto.getScFpriceAmt());
            trdMatchInfo.setMatchType(matchType);
            trdMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
            trdMatchInfo.setfSysId(CFTES);
            trdMatchInfos.add(trdMatchInfo);
        }
        for(IbuQuoteEntCrepPledgeCommonDto ibuQuoteEntCrepPledgeCommon:ibuQuoteEntCrepPledgeCommons){
            //托管机构
            String registOrg = ibuQuoteEntCrepPledgeCommon.getRepRegistOrg();
            String secAcctId = 处理证券账户(isKtg,registOrg,acctNoList);
            TrdPledgeMatchInfo trdPledgeMatchInfo = new TrdPledgeMatchInfo();
            trdPledgeMatchInfo.setCstpTradeId(execId);
            trdPledgeMatchInfo.setEntrustId(ibuQuoteEntCrepPledgeCommon.getEntrustId());
            trdPledgeMatchInfo.setSecAcctId(secAcctId);
            trdPledgeMatchInfo.setPledgeFinprodId(ibuQuoteEntCrepPledgeCommon.getPledgeFinprodId());
            trdPledgeMatchInfo.setMatchNum(ibuQuoteEntCrepPledgeCommon.getPledgeNumber());
            trdPledgeMatchInfo.setMatchType(matchType);
            trdPledgeMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
            trdPledgeMatchInfo.setfSysId(CFTES);
            trdPledgeMatchInfos.add(trdPledgeMatchInfo);
        }
        map.put("trdMatchInfos",trdMatchInfos);
        map.put("trdPledgeMatchInfos",trdPledgeMatchInfos);
    }

    /**
     * 有报价单组合匹配
     * @param map
     */
    private void matchForNotDzh(Map<String,Object> map){
        //匹配额度
        List<TrdMatchInfo> trdMatchInfos = new ArrayList<>();
        //匹配券面
        List<TrdPledgeMatchInfo> trdPledgeMatchInfos = new ArrayList<>();
        InterfOpicsTradRepo interfOpicsTradRepo = (InterfOpicsTradRepo) map.get("interfOpicsTradRepo");
        String execId = (String)map.get("execId");
        String isKtg = (String)map.get("isKtg");
        String matchType = (String)map.get("matchType");
        String entrustId = (String)map.get("entrustId");
        //该list  默认第一个为中债  第二个为上清  下行接口逗号隔开处理
        List<String> acctNoList = (List<String>)map.get("acctNoList");
        List<InterfTradRepoasstDto> interfTradRepoasstDtos = cstpCommonMatchMapper.getInterfTradRepoasst(execId);
        TrdMatchInfo trdMatchInfo = new TrdMatchInfo();
        trdMatchInfo.setCstpTradeId(execId);
        trdMatchInfo.setEntrustId(entrustId);
        trdMatchInfo.setMatchNum(interfOpicsTradRepo.getRepoamt());
        trdMatchInfo.setMatchNumCb(interfOpicsTradRepo.getCbFpriceAmt());
        trdMatchInfo.setMatchNumSc(interfOpicsTradRepo.getScFpriceAmt());
        trdMatchInfo.setMatchType(matchType);
        trdMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
        trdMatchInfo.setfSysId(CFTES);
        trdMatchInfos.add(trdMatchInfo);
        interfTradRepoasstDtos.forEach(item ->{
            TrdPledgeMatchInfo trdPledgeMatchInfo = new TrdPledgeMatchInfo();
            String registOrg = item.getRegistOrg();
            String secAcctId = 处理证券账户(isKtg,registOrg,acctNoList);
            trdPledgeMatchInfo.setCstpTradeId(execId);
            trdPledgeMatchInfo.setEntrustId(entrustId);
            trdPledgeMatchInfo.setSecAcctId(secAcctId);
            trdPledgeMatchInfo.setPledgeFinprodId(item.getFinprodId());
            trdPledgeMatchInfo.setMatchNum(item.getFacetotalamt());
            trdPledgeMatchInfo.setMatchType(matchType);
            trdPledgeMatchInfo.setTradeDate(interfOpicsTradRepo.getDealdate());
            trdPledgeMatchInfo.setfSysId(CFTES);
            trdPledgeMatchInfos.add(trdPledgeMatchInfo);
        });
        map.put("trdMatchInfos",trdMatchInfos);
        map.put("trdPledgeMatchInfos",trdPledgeMatchInfos);

    }
}

