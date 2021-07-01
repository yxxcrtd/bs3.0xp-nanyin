/*
 * 文件名：PtlDvpFundAllocationService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2019年3月29日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlchlfundallocation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlchlfundallocation.dao.PtlChlFundAllocationMapper;
import com.joyintech.service.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 通道资金划拨Service
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlChlFundAllocationService
 * @since 1.0
 */
@Service
public class PtlChlFundAllocationService extends BaseService<PtlChlFundAllocationDto> {

    /**
     * PtlChlFundAllocationMapper
     */
    @Autowired
    private PtlChlFundAllocationMapper ptlChlFundAllocationMapper;

    /**
     * 注入金融产品mapper
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    /**
     * 注入金融产品mapper
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    /**
     * PaTrdProductDealMapper
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    
    /**
     * 现金流水DAO
     */
    @Autowired
    private EtrCashPositionFlowMapper etrCashPositionFlowMapper;

    /**
     * 现金DAO
     */
    @Autowired
    private EtrCashPositionMapper etrCashPositionMapper;

    /**
     * 注入组合交易mapper
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    /**
     * 注入划款指令Mapper
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private PtlCashPositionFlowMapper ptlCashPositionFlowMapper;

    /**
     * 组合现金流水
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 委托共同Service
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    @Autowired
    private InvestInterfCommService investInterfCommService;
    @Autowired(required = false)
    private FinProductMapper finProductMapper;
    /**
     * 画面查询方法
     * 
     * @param map 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<PtlChlFundAllocationDto> findPage(int page, int length, Map<String, Object> map)
        throws Exception {

        // 组合代码
        if (map.get("portfolioId") != null && StringUtil.isNotEmpty((String)map.get("portfolioId"))) {
            map.replace("portfolioId", Arrays.asList(map.get("portfolioId").toString().split(",")));
        }
        else {
            map.replace("portfolioId", null);
        }

        // 划款类型→交易方向
        String payType = (String)map.get("payType");
        List<String> psList = new ArrayList<String>();
        if (StringUtil.isBlank(payType)) {
            String[] psArr = {DictCodeList.交易方向_通道划款.getValue(), DictCodeList.交易方向_通道回款.getValue()};
            psList = Arrays.asList(psArr);
        }
        else {
            psList.add(payType);
        }
        map.put("psList", psList);

        // 有效状态转交易状态
        String dataStatus = (String)map.get("dataStatus");
        List<String> tradeStatusList = new ArrayList<String>();
        if (StringUtil.equals(DictCodeListExt1.画面数据状态_有效.getValue(), dataStatus)) {
            tradeStatusList.add(DictCodeListExt1.交易状态_初始.getValue());
        }
        else if (StringUtil.equals(DictCodeListExt1.画面数据状态_撤销.getValue(), dataStatus)) {
            tradeStatusList.add(DictCodeListExt1.交易状态_撤销.getValue());
        }
        else {
            tradeStatusList.add(DictCodeListExt1.交易状态_初始.getValue());
            tradeStatusList.add(DictCodeListExt1.交易状态_撤销.getValue());
        }
        map.put("tradeStatusList", tradeStatusList);

        // 转账日期
        if (map.get("dataDateStart") == null || StringUtil.isEmpty((String)map.get("dataDateStart"))) {
            map.replace("dataDateStart", null);
        }

        map.put("yesNo_no", DictCodeList.是否_否.getValue());
        map.put("IE_FLAG_02", DictCodeListExt1.收支方向_支出.getValue());

        Page<PtlChlFundAllocationDto> pageData = super.queryForPage(page, length, () -> ptlChlFundAllocationMapper.findPage(map));
        if (pageData != null && !StringUtil.isEmptyList(pageData.getRoot())) {
            for (PtlChlFundAllocationDto dataDto : pageData.getRoot()) {
                if (StringUtil.equals(DictCodeListExt1.交易状态_初始.getValue(), dataDto.getOrderStatus())) {
                    dataDto.setOrderStatus(DictCodeListExt1.画面数据状态_有效.getValue());
                }
                else if (StringUtil.equals(DictCodeListExt1.交易状态_撤销.getValue(), dataDto.getOrderStatus())) {
                    dataDto.setOrderStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
                }
            }
        }
        return pageData;
    }

    /**
     * 查询收款账号
     * 
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterAcctNo(Map<String, Object> map) {
        String payType = (String)map.get("payType");
        String portfolioId = (String)map.get("portfolioId");
        String chlId = (String)map.get("chlId");

        if (StringUtil.isBlank(payType) || StringUtil.isBlank(portfolioId) || StringUtil.isBlank(chlId)) {
            return null;
        }

        // 获取组合托管户账户
        if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), payType)) {
            map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
            return ptlChlFundAllocationMapper.getPtlTghCounterAcctNo(map);
        }

        // 获取通道账户
        return ptlChlFundAllocationMapper.getChlAcctNo(map);
    }

    /**
     * 查询付款账号
     * 
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getMyAcctNo(Map<String, Object> map) {
        String payType = (String)map.get("payType");
        String portfolioId = (String)map.get("portfolioId");
        String chlId = (String)map.get("chlId");

        if (StringUtil.isBlank(payType) || StringUtil.isBlank(portfolioId) || StringUtil.isBlank(chlId)) {
            return null;
        }

        // 获取组合托管户账户
        if (StringUtil.equals(DictCodeList.交易方向_通道划款.getValue(), payType)) {
            map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
            return ptlChlFundAllocationMapper.getPtlTghCounterAcctNo(map);
        }

        // 获取通道账户
        return ptlChlFundAllocationMapper.getChlAcctNo(map);
    }

    /**
     * 查询交易编号(根据指令编号)
     * 
     * @param map 请求参数
     * @return 交易编号
     * @see
     * @since 1.0
     */
    public String getTradeId(Map<String, Object> map) {
        return ptlChlFundAllocationMapper.getTradeId(map);
    }

    /**
     * 新建资金划拨
     * 
     * @param entity 资金划拨实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public int insertData(PtlChlFundAllocationDto entity) {
        /**
         * 插入金融产品交易表
         */
        trdProductDealMapper.insert(entity.getTrdProductDeal());

        // 交易附属表
        trdProductDealAddMapper.insert(entity.getTrdProductDealAdd());

        // 金融产品交易扩展表
        paTrdProductDealMapper.insert(entity.getPaTrdProductDeal());

        /**
         * 插入组合交易表
         */
        if (entity.getPtlTrade() != null) {
            entity.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insert(ptlTrade));
        }

        /**
         * 插入DVP划款及DVP指令交易匹配表
         */
        if (!StringUtil.isEmptyList(entity.getStlPayOrdersList())) {
            for (StlPayOrders stlPayOrders : entity.getStlPayOrdersList()) {
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }
        }

        PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
        ptlHoldDto.setPtlCashPositionFlows(new ArrayList<>());

        /**
         * 付款账户处理（新建付款信息或修改付款账户金额）
         */
        // 调用组合可用现金和实际现金查询更新组件，将组合现金流水改为实际，并处理组合现金表数据
        if (entity.getPtlCashPositionFlow() != null) {
            entity.getPtlCashPositionFlow().forEach(ptlCashPositionFlow -> {
                PtlCashPositionFlowDto ptlCashPositionFlowDto_FuKuan = new PtlCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto_FuKuan, ptlCashPositionFlow);
                ptlCashPositionFlowDto_FuKuan.setHodingType(DictCodeList.持仓类型_实际.getValue());
                // 通道代码
                if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), entity.getPayType())) {
                    ptlCashPositionFlowDto_FuKuan.setChlFinprodId(entity.getChlId());
                }
                else {
                    ptlCashPositionFlowDto_FuKuan.setChlFinprodId(null);
                }
                ptlCashPositionFlowDto_FuKuan.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlowDto_FuKuan);

                ptlHoldDto.getPtlCashPositionFlows().add(ptlCashPositionFlowDto_FuKuan);
                /**
                 * 收款账户处理（新建付款信息或修改付款账户金额）
                 */
                String accountId_ShouKuan = entity.getCounterAcctNo();
                if (StringUtil.isNotBlank(accountId_ShouKuan)) {
                    PtlCashPositionFlowDto ptlCashPositionFlowDto_FuKuan1 = new PtlCashPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto_FuKuan1, ptlCashPositionFlowDto_FuKuan);
                    ptlCashPositionFlowDto_FuKuan1.setAcctId(accountId_ShouKuan);
                    ptlCashPositionFlowDto_FuKuan1.setAmount(ptlCashPositionFlowDto_FuKuan1.getAmount().negate());
                    ptlCashPositionFlowDto_FuKuan1.setHodingType(DictCodeList.持仓类型_实际.getValue());
                    // 通道代码
                    if (StringUtil.equals(DictCodeList.交易方向_通道划款.getValue(), entity.getPayType())) {
                        ptlCashPositionFlowDto_FuKuan1.setChlFinprodId(entity.getChlId());
                    }
                    else {
                        ptlCashPositionFlowDto_FuKuan1.setChlFinprodId(null);
                    }
                    ptlCashPositionFlowDto_FuKuan1.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());
                    basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlowDto_FuKuan1);
                    ptlHoldDto.getPtlCashPositionFlows().add(ptlCashPositionFlowDto_FuKuan1);
                }
            });
        }

        // 增加委托指令流水逻辑
       /* if (baseEtrCashPositionCommService != null) {
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, false);
        }*/

        // 调用估值接口 start
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setTrdProductDeal(entity.getTrdProductDeal());
        inputDto.setProdid(entity.getPortfolioId());
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        inputDto.setPtlTradeList(entity.getPtlTrade());
        AllTradeCommonUtil.getChannelProdDeal(inputDto);
        // 调用估值接口 end
        //调用投管接口 add by chenxiaoqiu 20190210 end
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("PtlFundAllocation002");
        investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
        investInterfCommDto.setTrdProductDeal(entity.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
        investInterfCommDto.setStlPayOrders(inputDto.getStlPayOrders());
        investInterfCommDto.setFinProduct( 
            finProductMapper.selectByPrimaryKey(entity.getActPortfolioId()));
        investInterfCommService.doInvestInterf(investInterfCommDto);
        // 调用投管接口 add by chenxiaoqiu 20190210 end

        return 1;
    }

    /**
     * 撤销资金划拨
     * 
     * @param entity 资金划拨实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    public int revokeData(PtlChlFundAllocationDto entity) {
        String tradeId = entity.getTradeId();
        String orderId = entity.getOrderId();
        String revokeReason = entity.getRevokeReason();
        String tradeIdNew = tradeId + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR;
        String orderIdNew = orderId + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR;

        // 撤销金融产品交易表数据
        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);
        trdProductDeal.setIsCancel(DictCodeList.是否_是.getValue());
        trdProductDealMapper.updateByPrimaryKey(trdProductDeal);

        TrdProductDeal trdProductDealNew = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDealNew, trdProductDeal);
        trdProductDealNew.setpTradeId(tradeId);
        trdProductDealNew.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        trdProductDealNew.setTradeId(tradeIdNew);
        trdProductDealNew.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDealNew.setCancelRemark(revokeReason);
        trdProductDealMapper.insert(trdProductDealNew);

        // 交易附属表
        TrdProductDealAdd trdProductDealAdd = trdProductDealAddMapper.selectByPrimaryKey(tradeId);
        if (trdProductDealAdd != null) {
            trdProductDealAdd.setTradeId(tradeIdNew);
            trdProductDealAddMapper.insert(trdProductDealAdd);
        }

        // 金融产品交易扩展表
        PaTrdProductDeal paTrdProductDeal = paTrdProductDealMapper.selectByPrimaryKey(tradeId);
        if (paTrdProductDeal != null) {
            paTrdProductDeal.setTradeId(tradeIdNew);
            paTrdProductDealMapper.insert(paTrdProductDeal);
        }

        // 撤销组合交易表数据
        PtlTradeKey ptlTradeKey = new PtlTradeKey();
        ptlTradeKey.setPortfolioId(trdProductDealAdd.getPortfolioId());
        ptlTradeKey.setBusiNo(tradeId);
        ptlTradeKey.setBusiTable(DictCodeList.业务表_结算指令表.getValue());

        PtlTrade ptlTrade = ptlTradeMapper.selectByPrimaryKey(ptlTradeKey);
        if (ptlTrade != null) {
            ptlTrade.setBusiNo(tradeIdNew);
            ptlTradeMapper.insert(ptlTrade);
        }

        // if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
        // ptlTradeKey.setPortfolioId(entity.getActPortfolioId());
        // ptlTradeKey.setBusiNo(tradeId);
        // ptlTrade = ptlTradeMapper.selectByPrimaryKey(ptlTradeKey);
        // if (ptlTrade != null) {
        // ptlTrade.setBusiNo(tradeIdNew);
        // ptlTradeMapper.insert(ptlTrade);
        // }
        // }

        // 撤销划款指令表
        StlPayOrders stlPayOrders = stlPayOrdersMapper.selectByPrimaryKey(orderId);
        stlPayOrdersMapper.deleteByPrimaryKey(orderId);

        stlPayOrders.setOrderId(orderIdNew);
        stlPayOrders.setpTradeId(orderId);
        stlPayOrders.setTradeId(tradeIdNew);
        stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
        stlPayOrdersMapper.insert(stlPayOrders);

        PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
        ptlHoldDto.setPtlCashPositionFlows(new ArrayList<>());
        // 撤销组合现金余额数据 收付款账户
        // 根据指令编号查询之前的组合现金流水记录，插入相反的流水记录
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId);
        List<PtlCashPositionFlow> ptlCashPositionFlowList = ptlChlFundAllocationMapper.selectPtlCashPositionFlowList(paramMap);
        if (!StringUtil.isEmptyList(ptlCashPositionFlowList)) {
            for (PtlCashPositionFlow ptlCashPositionFlow : ptlCashPositionFlowList) {
                PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto, ptlCashPositionFlow);
                ptlCashPositionFlowDto.setAmount(ptlCashPositionFlow.getAmount().negate());
                ptlCashPositionFlowDto.setOrderId(orderIdNew);
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlowDto);
                ptlHoldDto.getPtlCashPositionFlows().add(ptlCashPositionFlowDto);
            }
        }

        // 增加委托指令流水逻辑
        if (baseEtrCashPositionCommService != null) {
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
        }

        // 调用估值接口 start
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setTrdProductDeal(trdProductDealNew);
        inputDto.setProdid(entity.getPortfolioId());
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        inputDto.setPtlTradeList(entity.getPtlTrade());
        AllTradeCommonUtil.getChannelProdDeal(inputDto);
        // 调用估值接口 end
    	// 调用投管接口 add by chenxiaoqiu 20190205 sta
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("PtlFundAllocation002");
        investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        investInterfCommDto.setTrdProductDeal(trdProductDeal);
        investInterfCommService.doInvestInterf(investInterfCommDto);
        // 调用投管接口 add by chenxiaoqiu 20190205 end
        return 1;
    }

    public String doInsert(PtlChlFundAllocationDto entity) {
        entity.getPtlCashPositionFlow().forEach(ptlCashPositionFlow -> {
            if (StringUtil.isNotBlank(ptlCashPositionFlow.getAcctId())) {
                ptlCashPositionFlowMapper.insert(ptlCashPositionFlow);
            }
        });
        
        PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
        ptlHoldDto.setPtlCashPositionFlows(new ArrayList<>());           
        if (entity.getPtlCashPositionFlow() != null) {
            entity.getPtlCashPositionFlow().forEach(ptlCashPositionFlow -> {
                PtlCashPositionFlowDto ptlCashPositionFlowDto_FuKuan = new PtlCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto_FuKuan, ptlCashPositionFlow);
                ptlCashPositionFlowDto_FuKuan.setHodingType(DictCodeList.持仓类型_实际.getValue());
                // 通道代码
                if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), entity.getPayType())) {
                    ptlCashPositionFlowDto_FuKuan.setChlFinprodId(entity.getChlId());
                }
                else {
                    ptlCashPositionFlowDto_FuKuan.setChlFinprodId(null);
                }
                ptlCashPositionFlowDto_FuKuan.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());              
                ptlHoldDto.getPtlCashPositionFlows().add(ptlCashPositionFlowDto_FuKuan);
                /**
                 * 收款账户处理（新建付款信息或修改付款账户金额）
                 */
                String accountId_ShouKuan = entity.getCounterAcctNo();
                if (StringUtil.isNotBlank(accountId_ShouKuan)) {
                    PtlCashPositionFlowDto ptlCashPositionFlowDto_FuKuan1 = new PtlCashPositionFlowDto();
                    BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto_FuKuan1, ptlCashPositionFlowDto_FuKuan);
                    ptlCashPositionFlowDto_FuKuan1.setAcctId(accountId_ShouKuan);
                    ptlCashPositionFlowDto_FuKuan1.setAmount(ptlCashPositionFlowDto_FuKuan1.getAmount().negate());
                    ptlCashPositionFlowDto_FuKuan1.setHodingType(DictCodeList.持仓类型_实际.getValue());
                    // 通道代码
                    if (StringUtil.equals(DictCodeList.交易方向_通道划款.getValue(), entity.getPayType())) {
                        ptlCashPositionFlowDto_FuKuan1.setChlFinprodId(entity.getChlId());
                    }
                    else {
                        ptlCashPositionFlowDto_FuKuan1.setChlFinprodId(null);
                    }
                    ptlCashPositionFlowDto_FuKuan1.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());                
                    ptlHoldDto.getPtlCashPositionFlows().add(ptlCashPositionFlowDto_FuKuan1);
                }
            });
        }

        // 增加委托指令流水逻辑
        if (baseEtrCashPositionCommService != null) {
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, false);
        }
        return "Success";
    }
    
    public String doBack(PtlChlFundAllocationDto entity) {
    	 etrCashPositionFlowMapper.deleteFlowByOrderId(entity.getOrderId());
    	 /**
          * 指令现金流水
          */
         List<EtrCashPosition> etrCash = new ArrayList<>();
         EtrCashPosition tempPosition = new EtrCashPosition();            
         tempPosition.setPortfolioId(entity.getPortfolioId());
         tempPosition.setAcctId(entity.getMyAcctNo());
         tempPosition.setAmount(entity.getSettleAmt());   
         tempPosition.setCcy(entity.getCcy());
         tempPosition.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
         tempPosition.setCdate(entity.getSettleDate());
         EtrCashPositionKey etrCashPositionKey = new EtrCashPosition(); 
         BeanUtil.copyPropertiesIgnoreNull(etrCashPositionKey, tempPosition);
         EtrCashPosition EtrCashPositionKey = etrCashPositionMapper.selectByPrimaryKey(etrCashPositionKey);
         tempPosition.setAmount(ArithmeticUtil.doAdd(tempPosition.getAmount(), EtrCashPositionKey.getAmount()));
         etrCash.add(tempPosition); 
         EtrCashPosition tempPosition1 = new EtrCashPosition();     
         tempPosition1.setPortfolioId(entity.getPortfolioId());
         tempPosition1.setAcctId(entity.getCounterAcctNo());
         tempPosition1.setAmount(entity.getSettleAmt().negate());   
         tempPosition1.setCcy(entity.getCcy());
         tempPosition1.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
         tempPosition1.setCdate(entity.getSettleDate());
         EtrCashPositionKey etrCashPositionKey1 = new EtrCashPosition(); 
         BeanUtil.copyPropertiesIgnoreNull(etrCashPositionKey1, tempPosition1);
         EtrCashPosition EtrCashPositionKey1 = etrCashPositionMapper.selectByPrimaryKey(etrCashPositionKey1);
         tempPosition1.setAmount(ArithmeticUtil.doAdd(tempPosition1.getAmount(), EtrCashPositionKey1.getAmount()));
         etrCash.add(tempPosition1); 
         for (EtrCashPosition etrCashPosition : etrCash) {
        	 etrCashPositionMapper.updateByPrimaryKeySelective(etrCashPosition);
         }
         return "Success";
    }
    
}
