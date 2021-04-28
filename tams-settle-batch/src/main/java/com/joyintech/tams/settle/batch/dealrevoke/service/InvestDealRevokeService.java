package com.joyintech.tams.settle.batch.dealrevoke.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.settle.batch.dealrevoke.dao.InvestDealRevokeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
        busiTypeList.add(DictCodeListExt2.共通业务类型_可转债申购交易.getValue());
        param.put("busiTypeList", busiTypeList);
        param.put("psList", Arrays.asList(DictCodeList.交易方向_买入.getValue()
                ,DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_行权_回售.getValue(),DictCodeList.交易方向_申购确认.getValue()
                ,DictCodeList.交易方向_申购.getValue(),DictCodeListExt3.交易方向_债转股.getValue(),DictCodeListExt3.交易方向_配债.getValue()));
        return investDealRevokeMapper.selectTotalTradeIdTgM(param);
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
        param.put("busiType", DictCodeList.共通业务类型_质押式回购存续期.getValue());
        param.put("psList", Arrays.asList(DictCodeList.交易方向_到期续做.getValue(), DictCodeList.交易方向_到期.getValue()
                , DictCodeListExt3.交易方向_投资端_提前终止.getValue()));
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
        param.put("psList", Arrays.asList(DictCodeList.股票分红类型_分红送转.getValue()));
        return investDealRevokeMapper.selectTotalTradeIdTgM(param);
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
        return investDealRevokeMapper.selectTotalTradeIdTgM(param);
    }

    /**
     * 删除日间成交数据形成的头寸流水信息
     */
    public void deletePositionFlow(Date tradeDate, List<String> portfolioIdList, String busiType) {
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
        investDealRevokeMapper.deletePtlCashPositionFlow(param);
        investDealRevokeMapper.deletePtlSecPositionFlow(param);
        investDealRevokeMapper.deleteEtrCashPositionFlow(param);
        investDealRevokeMapper.deleteEtrSecPositionFlow(param);
        // 删除日间委托完成度
        investDealRevokeMapper.deleteEtrFinishStatus(param);
        
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
}
