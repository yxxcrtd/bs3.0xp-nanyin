package com.joyintech.tams.settle.batch.dealrevoke.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.EtrCashPosition;
import com.joyintech.tams.common.base.entity.EtrSecPosition;
import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.service.StlDataDealProcessCommonService;
import com.joyintech.tams.settle.batch.dealrevoke.dao.InvestDealRevokeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 何阳阳
 * @date 2020/8/3
 * @see InvestDealRevokeService
 * @since 1.0
 */
@Service
@Lazy
public class InvestDealRevokeService {

    /**
     * 是否单体服务
     */
    @Value("${application.micorservice.contextPath.single:false}")
    private boolean isSingle;

    @Autowired(required = false)
    private JoyinRestTemplate restTemplate;

    @Autowired(required = false)
    @Qualifier("simpleRestTemplate")
    protected JoyinRestTemplate simpleRestTemplate;

    @Value("${application.micorservice.contextPath.tamsServer:http://tams-server}")
    private String tamsSerever;

    @Autowired
    private InvestDealRevokeMapper investDealRevokeMapper;

    @Autowired
    private StlDataDealProcessCommonService stlDataDealProcessCommonService;


    private Map<String, Object> createCommonParam(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = new HashMap<>();
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("dealDate", tradeDate);
        param.put("dealDateStr", DateUtil.format(tradeDate));
        param.put("portfolioIdList", portfolioIdList);
        return param;
    }

    /**
     * 查询汇总交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTotalTradeIdForBond(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        //param.put("busiType", DictCodeList.共通业务类型_债券类资产交易.getValue());
        List<String> busiTypeList = new ArrayList<String>();
        busiTypeList.add(DictCodeList.共通业务类型_债券类资产交易.getValue());
        busiTypeList.add(DictCodeList.共通业务类型_债券类资产存续期.getValue());
        busiTypeList.add(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        // GZYHTZJY-1142: 对债券债转股交易进行撤销 fuyongsheng add start
        busiTypeList.add(DictCodeListExt2.共通业务类型_可转债转股交易.getValue());
        // GZYHTZJY-1142: 对债券债转股交易进行撤销 fuyongsheng add end
        param.put("busiTypeList", busiTypeList);
        //增加协议回购中途换券撤销、竞价回购的入库、出库
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        param.put("psList", Arrays.asList(/*DictCodeList.交易方向_买入.getValue()
                ,*/DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),/*DictCodeList.交易方向_申购确认.getValue()
                ,DictCodeList.交易方向_申购.getValue(),*/DictCodeListExt3.交易方向_债转股.getValue(),DictCodeListExt3.交易方向_配债.getValue()
                // ZGZGST-4219: 对债券存续期交易进行撤销 fuyongsheng add start
                ,DictCodeList.交易方向_收息到账.getValue(),DictCodeList.交易方向_还本到账.getValue()
                // ZGZGST-4219: 对债券存续期交易进行撤销 fuyongsheng add end
                // GZYHTZJY-1142: 对债券债转股交易进行撤销 fuyongsheng add start
                ,DictCodeListExt3.交易方向_债转股.getValue()
                // GZYHTZJY-1142: 对债券债转股交易进行撤销 fuyongsheng add end
        ));
        param.put("isBond", DictCodeList.是否_是.getValue());
        param.put("PS_01", DictCodeList.交易方向_买入.getValue());
        param.put("SHARES_TRADE_MARKET_01", DictCodeListExt1.交易市场_一级市场.getValue());
        param.put("psApplyList", Arrays.asList(DictCodeList.交易方向_申购.getValue(),DictCodeList.交易方向_申购确认.getValue()));
        param.put("PURCH_MODLE_02", DictCodeListExt3.申购方式_网下申购.getValue());
        List<String> totalTradeIdTgMs = investDealRevokeMapper.selectTotalTradeIdTgM(param);


        param.put("psList",  Arrays.asList(DictCodeList.交易方向_上市流通.getValue()));
        param.put("busiType", DictCodeList.共通业务类型_债券类资产交易.getValue());
        String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SHJYS_CALID);
        Date shNextTradeDate = stlDataDealProcessCommonService.getNextWorkDay(tradeDate,calendarId);
        param.put("dealDate", shNextTradeDate);
        param.put("dealDateStr", DateUtil.format(shNextTradeDate));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue()));
        // 上交所上市流通
        List<String> shltTotalTradeIdTg03Ms = investDealRevokeMapper.selectSsltTotalTradeIdTgM(param);
        calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SZJYS_CALID);
        Date szNextTradeDate = stlDataDealProcessCommonService.getNextWorkDay(tradeDate,calendarId);
        param.put("dealDate", szNextTradeDate);
        param.put("dealDateStr", DateUtil.format(szNextTradeDate));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        // 深交所上市流通
        List<String> shltTotalTradeIdTg04Ms = investDealRevokeMapper.selectSsltTotalTradeIdTgM(param);
        totalTradeIdTgMs.addAll(shltTotalTradeIdTg03Ms);
        totalTradeIdTgMs.addAll(shltTotalTradeIdTg04Ms);
        return totalTradeIdTgMs;
    }

    /**
     * 查询交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTradeIdForRepo(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        param.put("finprodType2List", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()
                ,DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        param.put("psList",Arrays.asList(DictCodeList.交易方向_买入.getValue()));
        return investDealRevokeMapper.selectTradeIdTgM(param);
    }

    /**
     * 查询汇总交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTotalTradeIdForRepo(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
//        param.put("busiType", DictCodeList.共通业务类型_质押式回购存续期.getValue());
        param.put("psDurList", Arrays.asList(DictCodeList.交易方向_到期续做.getValue(), DictCodeList.交易方向_到期.getValue()
                , DictCodeListExt3.交易方向_投资端_提前终止.getValue(),DictCodeListExt3.交易方向_中途换券.getValue()));
        param.put("tgBusiTypeList",Arrays.asList(DictCodeListExt3.平安业务类型_债券质押式逆回购.getValue(),DictCodeListExt3.平安业务类型_债券质押式正回购.getValue()));
        param.put("isRepoFund",DictCodeList.是否_是.getValue());
        return investDealRevokeMapper.selectTotalTradeIdTgM(param);
    }

    /**
     * 查询交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTradeIdForShares(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        param.put("finprodType2List", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_股票.getValue()));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
         //AND A.交易方向 IN (交易方向_买入，交易方向_卖出，交易方向_申购，交易方向_申购确认，交易方向_上市流通）
        param.put("psSharsList",Arrays.asList(DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_申购.getValue(),
                DictCodeList.交易方向_申购确认.getValue()));
        param.put("psSharsTwoList",Arrays.asList(DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_申购.getValue(),
                DictCodeList.交易方向_申购确认.getValue()));
        return investDealRevokeMapper.selectTradeIdTgM(param);
    }

    /**
     * 查询汇总交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTotalTradeIdForShares(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        param.put("busiType", DictCodeListExt2.共通业务类型_股票分红交易.getValue());
        /*param.put("psList", Arrays.asList(DictCodeList.股票分红类型_分红送转.getValue()));*/
        param.put("psList", Arrays.asList(DictCodeList.交易方向_现金分红.getValue(),DictCodeList.交易方向_红利再投.getValue()));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        List<String> totalTradeIdTgMs = investDealRevokeMapper.selectTotalTradeIdTgM(param);


        param.put("psList",  Arrays.asList(DictCodeList.交易方向_上市流通.getValue()));
        param.put("busiType", DictCodeListExt2.共通业务类型_股票投资交易.getValue());
        String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SHJYS_CALID);
        Date shNextTradeDate = stlDataDealProcessCommonService.getNextWorkDay(tradeDate,calendarId);
        param.put("dealDate", shNextTradeDate);
        param.put("dealDateStr", DateUtil.format(shNextTradeDate));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue()));
        // 上交所上市流通
        List<String> shltTotalTradeIdTg03Ms = investDealRevokeMapper.selectSsltTotalTradeIdTgM(param);
        calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SZJYS_CALID);
        Date szNextTradeDate = stlDataDealProcessCommonService.getNextWorkDay(tradeDate,calendarId);
        param.put("dealDate", szNextTradeDate);
        param.put("dealDateStr", DateUtil.format(szNextTradeDate));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        // 深交所上市流通
        List<String> shltTotalTradeIdTg04Ms = investDealRevokeMapper.selectSsltTotalTradeIdTgM(param);
        totalTradeIdTgMs.addAll(shltTotalTradeIdTg03Ms);
        totalTradeIdTgMs.addAll(shltTotalTradeIdTg04Ms);
        return totalTradeIdTgMs;
    }

    /**
     * 查询交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTradeIdForFund(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        param.put("finprodType2List", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_基金.getValue()));
        param.put("psList", Arrays.asList(DictCodeList.交易方向_买入.getValue(),DictCodeList.交易方向_卖出.getValue()));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        return investDealRevokeMapper.selectTradeIdTgM(param);
    }

    /**
     * 查询汇总交易编号
     * @param tradeDate 交易日
     * @param portfolioIdList 组合代码列表
     * @return 汇总交易编号列表
     */
    public List<String> selectTotalTradeIdForFund(Date tradeDate, List<String> portfolioIdList) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        param.put("busiType", DictCodeList.共通业务类型_基金类资产存续期.getValue());
        param.put("psList", Arrays.asList(DictCodeList.交易方向_除权登记.getValue(),DictCodeList.交易方向_现金分红.getValue(),DictCodeList.交易方向_红利再投.getValue()));
        param.put("tradeMarketList",Arrays.asList(DictCodeListExt1.交易场所_上海证券交易所.getValue(),DictCodeListExt1.交易场所_深圳证券交易所.getValue()));
        param.put("isRepoFund", DictCodeList.是否_是.getValue());
        return investDealRevokeMapper.selectTotalTradeIdTgM(param);
    }

    /**
     * 删除日间成交数据形成的头寸流水信息
     */
    public void deletePositionFlow(Date tradeDate, List<String> portfolioIdList, String busiType,List<String> orderIds) {
        Map<String, Object> param = createCommonParam(tradeDate, portfolioIdList);
        List<String> finprodType2List = new ArrayList<String>();
        
        if (StringUtil.equals(DictCodeListExt1.证券品种_债券.getValue(), busiType)) {
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        }
        if (StringUtil.equals(DictCodeListExt1.证券品种_基金.getValue(), busiType)) {
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        }
        if (StringUtil.equals(DictCodeListExt1.证券品种_股票.getValue(), busiType)) {
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        }
        if (StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), busiType)) {
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        	finprodType2List.add(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        }
        param.put("finprodType2List", finprodType2List);
        //删除前,查出流水，以便更新余额
        param.put("trdIdList", orderIds);
        List<PtlCashPosition> ptlCashPositions = investDealRevokeMapper.listPtlCashPositionFlow(param);
        List<PtlSecPosition> ptlSecPositions = investDealRevokeMapper.listPtlSecPositionFlow(param);
        List<EtrCashPosition> etrCashPositions = investDealRevokeMapper.listEtrCashPositionFlow(param);
        List<EtrSecPosition> etrSecPositions = investDealRevokeMapper.listEtrSecPositionFlow(param);
        investDealRevokeMapper.deletePtlCashPositionFlow(param);
        investDealRevokeMapper.deletePtlSecPositionFlow(param);
        investDealRevokeMapper.deleteEtrCashPositionFlow(param);
        investDealRevokeMapper.deleteEtrSecPositionFlow(param);
        // add 日间指令流水删除 20210817 start
        investDealRevokeMapper.deleteEtrCashPositionFlowByTradeId(param);
        // add end
        // 删除日间委托完成度
        investDealRevokeMapper.deleteEtrFinishStatus(param);
        //更新余额
        ptlCashPositions.stream().forEach(item ->{
             if(null!=item.getAmount()){
                 item.setAmount(item.getAmount().negate());
                 investDealRevokeMapper.updatePtlCashPosition(item);
             }
        });
        ptlSecPositions.stream().forEach(item ->{
            if(null!=item.getAmount()){
                item.setAmount(item.getAmount().negate());
                investDealRevokeMapper.updatePtlSecPosition(item);
            }
        });
        etrCashPositions.stream().forEach(item ->{
            if(null!=item.getAmount()){
                item.setAmount(item.getAmount().negate());
                investDealRevokeMapper.updateEtrCashPosition(item);
            }
        });
        etrSecPositions.stream().forEach(item ->{
            if(null!=item.getAmount()){
                item.setAmount(item.getAmount().negate());
                investDealRevokeMapper.updateEtrSecPosition(item);
            }
        });
    }

    public void updateEtrStatusByTrdId(List<String> tradeIdList, List<String> totalTradeIdList) {
        Map<String, Object> param = new HashMap<>();
        param.put("ENTRUST_STATUS_03",DictCodeList.委托状态_已接收.getValue());
        if (!StringUtil.isEmptyList(tradeIdList)){
            param.put("trdIdList", tradeIdList);
            investDealRevokeMapper.updateEtrStatusByTrdId(param);
        }
        if (!StringUtil.isEmptyList(totalTradeIdList)){
            param.put("trdIdList", totalTradeIdList);
            investDealRevokeMapper.updateEtrStatusByTrdId(param);
        }


    }
    
    /**
     * 删除日终交易占用的委托完成度信息
     * 
     * @param tradeIdList 交易明细
     * @param totalTradeIdList 汇总交易明细
     * @see
     * @since 1.0
     */
    public void deleteEtrFinishStatusByTrdId(List<String> tradeIdList, List<String> totalTradeIdList){
    	Map<String, Object> param = new HashMap<>();
    	if (!StringUtil.isEmptyList(tradeIdList)){
    		param.put("trdIdList", tradeIdList);
    		investDealRevokeMapper.deleteEtrFinishStatusByTrdId(param);
    	}
    	if (!StringUtil.isEmptyList(totalTradeIdList)){
    		param.put("trdIdList", totalTradeIdList);
    		investDealRevokeMapper.deleteEtrFinishStatusByTrdId(param);
    	}
    }
    
    /**
     * 根据交易编号删除日间指令现金流水
     * 
     * @param tradeIdList 交易明细
     * @param totalTradeIdList 汇总交易明细
     * @see
     * @since 1.0
     */
    public void deleteEtrCashPositionFlowByTrdId(List<String> tradeIdList, List<String> totalTradeIdList){
    	Map<String, Object> param = new HashMap<>();
    	if (!StringUtil.isEmptyList(tradeIdList)){
    		param.put("trdIdList", tradeIdList);
    		investDealRevokeMapper.deleteEtrCashPositionFlowByTrdId(param);
    	}
    	if (!StringUtil.isEmptyList(totalTradeIdList)){
    		param.put("trdIdList", totalTradeIdList);
    		investDealRevokeMapper.deleteEtrCashPositionFlowByTrdId(param);
    	}
    }
    

    /**
     * 删除日间成交数据形成的头寸流水信息
     */
    public void revokeDeal(List<String> tradeIdList) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("tradeIdList", tradeIdList);
        param.put("updateUser", GlobalConstant.SYSTEM_USER);
        param.put("revokeReason", "数据清算批处理");
        // 调用债券交易撤销逻辑
        if(isSingle) {
            simpleRestTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
        }
        else {
            restTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
        }

    }

    /**
     * 删除日间成交数据形成的头寸流水信息
     */
    public void revokeDealTotal(List<String> totalTradeIdList) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("totalTradeIdList", totalTradeIdList);
        param.put("updateUser", GlobalConstant.SYSTEM_USER);
        param.put("revokeReason", "数据清算批处理");
        // 调用债券交易撤销逻辑
        if(isSingle) {
            simpleRestTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
        }
        else {
            restTemplate.postForObject(tamsSerever + "/invest/trade/api/revoke", param, String.class);
        }

    }

    public Map<String,String> getTradeIdPsMap(List<String> tradeIdList) {
        Map<String,Object> param = new HashMap<>();
        param.put("tradeIdList",tradeIdList);
        param.put("isTotal","02");
        if (!CollectionUtils.isEmpty(tradeIdList)){
            return getTradeIdPsMap(param);
        }
        return null;
    }

    public Map<String,String> getTotalTradeIdPsMap(List<String> totleTradeIdList) {
        Map<String,Object> param = new HashMap<>();
        param.put("totleTradeIdList",totleTradeIdList);
        param.put("isTotal","01");
        if (!CollectionUtils.isEmpty(totleTradeIdList)){
            return getTradeIdPsMap(param);
        }
        return null;

    }

    public Map<String,String> getTradeIdPsMap(Map<String,Object> param) {
        List<Map<String, String>> list = investDealRevokeMapper.selectPsById(param);
        return getStringStringMap(param, list);
    }

    public Map<String,String> getRepoTradeIdPsMap(List<String> totleTradeIdList) {
        Map<String,Object> param = new HashMap<>();
        param.put("isTotal","01");
        param.put("totleTradeIdList",totleTradeIdList);
        if (!CollectionUtils.isEmpty(totleTradeIdList)) {
            List<Map<String, String>> list = investDealRevokeMapper.selectRepoPsById(param);
            return getStringStringMap(param, list);
        }
        return null;
    }

    private Map<String, String> getStringStringMap(Map<String, Object> param, List<Map<String, String>> list) {
        Map<String,String> map = new HashMap<>();
        if (!StringUtil.isEmptyList(list)){
            list.forEach(item->{
               if (StringUtil.isNotEmpty(param.get("isTotal"))){
                  if( StringUtil.equals(param.get("isTotal"),"01")) {
                      String totalkey = null;
                      String psvalue = null;
                      for (Map.Entry<String,String> i:item.entrySet()) {
                         if ("TOTALKEY".equalsIgnoreCase(i.getKey())) {
                            totalkey = i.getValue();
                         }else if ("PSVALUE".equalsIgnoreCase(i.getKey())) {
                            psvalue = i.getValue();
                         }
                         if (totalkey != null) {
                             map.put(totalkey, psvalue);
                         }
                       }
                   }else if( StringUtil.equals(param.get("isTotal"),"02")){
                       String tradekey = null;
                       String psvalue = null;
                       for (Map.Entry<String,String> i:item.entrySet()) {
                         if ("TRADEKEY".equalsIgnoreCase(i.getKey())) {
                             tradekey = i.getValue();
                         }else if ("PSVALUE".equalsIgnoreCase(i.getKey())) {
                              psvalue = i.getValue();
                          }
                           if (tradekey != null) {
                               map.put(tradekey, psvalue);
                           }
                        }
                    }
                 }
            });
        }
        return map;
    }

    public List<String> searchTradeIdsByTotalTradeIds(List<String> totalTradeIds){
       return investDealRevokeMapper.searchTradeIdsByTotalTradeIds(totalTradeIds);
    }

    public void deleteSsltSecPositionFlow(List<String> totalTradeIds){
        investDealRevokeMapper.deleteSsltSecPositionFlow(totalTradeIds);
    }

    public void deleteSsltEtrSecPositionFlow(List<String> totalTradeIds){
        investDealRevokeMapper.deleteSsltEtrSecPositionFlow(totalTradeIds);
    }
}
