/*
 * 文件名：PtlFundAllocationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlfundallocation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlfundallocation.dao.PtlFundAllocationMapper;
import com.joyintech.service.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资金划拨管理
 *
 * @author fanshuanghu
 * @version 1.0 2019年1月29日
 * @see PtlFundAllocationService
 * @since 1.0
 */
@Service
public class PtlFundAllocationService extends BaseService<PtlFundAllocationDto> {
    /**
     * 注入资金划拨mapper
     */
    @Autowired
    private PtlFundAllocationMapper ptlFundAllocationMapper;
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
     * 注入组合现金流水mapper
     */
    @Autowired
    private MapperDelegateService bussCommonMapperDelegateService;
    /**
     * 注入组合交易mapper
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    /**
     * 注入组合现金余额mapper
     */
    @Autowired
    private PtlCashPositionMapper ptlCashPositionMapper;
    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 注入划款指令Mapper
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    /**
     * 主键生成类
     */
    @Autowired
    private BaseGetBussinessId baseGetBussinessId;
    /**
     * 委托共同Service
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    //调用投管接口 add cxq 20200210
    @Autowired
    private InvestInterfCommService investInterfCommService;
    @Autowired(required = false)
    private FinProductMapper finProductMapper;
    /**
     * 获取账号
     *
     * @param map 请求参数
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAcct(Map<String, Object> map) {
        return ptlFundAllocationMapper.getAcct(map);
    }

    /**
     * 查询组合（资金划拨管理）
     *
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioList(Map<String, Object> map) {
        map.put("HK01", DictCodeList.交易方向_银证转账.getValue());
        map.put("HK02", DictCodeList.交易方向_证银转账.getValue());
        return ptlFundAllocationMapper.getPortfolioList(map);
    }

    /**
     * 查询组合
     *
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> map) {
        return ptlFundAllocationMapper.getPortfolioId(map);
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
        if (map.get("payType") != null && StringUtil.equals(map.get("payType"), DictCodeListExt1.指令类型_银证转账_银转证.getValue())) {
            map.put("acctType", DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//            map.put("acctType1", DictCodeListExt1.资金账户类型_结算备付金账户.getValue());
        } else if (map.get("payType") == null || StringUtil.isEmpty((String) map.get("payType")) ||
                map.get("portfolioId") == null || StringUtil.isEmpty((String) map.get("portfolioId"))) {
            return null;
        } else {
            map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        }

//        // 资金的划拨管理，银证转账的收款方账户，应该关联系统中的证券托管户配置的交易结算户；证银转账，付款方账户关联系统中证券托管户配置的交易结算户
//        if (map.get("payType") != null && StringUtil.equals(map.get("payType"), DictCodeListExt1.指令类型_银证转账_银转证.getValue())) {
//            return ptlFundAllocationMapper.getPtlSecTrdAcctNo(map);
//        }

        return ptlFundAllocationMapper.getCounterAcctNo(map);
    }

    /**
     * 查询付款账号
     *
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getMyAcctNo(Map<String, Object> map) {
        if (map.get("payType") != null && StringUtil.equals(map.get("payType"), DictCodeListExt1.指令类型_银证转账_银转证.getValue())) {
            map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
//            map.put("acctType1", DictCodeListExt1.资金账户类型_结算备付金账户.getValue());
        } else if (map.get("payType") == null || StringUtil.isEmpty((String) map.get("payType")) ||
                map.get("portfolioId") == null || StringUtil.isEmpty((String) map.get("portfolioId"))) {
            return null;
        } else {
            map.put("acctType", DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        }

//        // 资金的划拨管理，银证转账的收款方账户，应该关联系统中的证券托管户配置的交易结算户；证银转账，付款方账户关联系统中证券托管户配置的交易结算户
//        if (map.get("payType") != null && StringUtil.equals(map.get("payType"), DictCodeListExt1.指令类型_银证转账_证转银.getValue())) {
//            return ptlFundAllocationMapper.getPtlSecTrdAcctNo(map);
//        }

        return ptlFundAllocationMapper.getMyAcctNo(map);
    }

    /**
     * 获取现金余额
     *
     * @param map 请求参数
     * @return 现金余额
     * @see
     * @since 1.0
     */
    public BigDecimal getAmount(Map<String, Object> map) {
        PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        BigDecimal amount = null;
        try {
            ptlCashPositionFlowDto = (PtlCashPositionFlowDto) JsonUtils.map2entity(map, PtlCashPositionFlowDto.class);
            amount = basePtlCashPositionInfoService.queryUsableCashPosition(ptlCashPositionFlowDto);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    /**
     * 画面查询方法
     *
     * @param map    请求参数
     * @param page   页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<PtlFundAllocationDto> findPage(int page, int length, Map<String, Object> map) throws Exception {
//        return super.queryForPage(page, length, ()->ptlFundAllocationMapper.findPage(map));
        Page<PtlFundAllocationDto> pageData = super.queryForPage(page, length, () -> ptlFundAllocationMapper.findPage(map));
        if (pageData != null && !StringUtil.isEmptyList(pageData.getRoot())) {
            for (PtlFundAllocationDto dataDto : pageData.getRoot()) {
                if (StringUtil.equals(DictCodeListExt1.交易状态_初始.getValue(), dataDto.getOrderStatus())) {
                    dataDto.setOrderStatus(DictCodeListExt1.画面数据状态_有效.getValue());
                } else if (StringUtil.equals(DictCodeListExt1.交易状态_撤销.getValue(), dataDto.getOrderStatus())) {
                    dataDto.setOrderStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
                }
                if (StringUtil.equals(dataDto.getPs(), DictCodeListExt1.收支方向_收入.getValue())) {
                    PtlFundAllocationDto dataDtotemp = new PtlFundAllocationDto();
                    dataDtotemp.setCounterAcctNo(dataDto.getMyAcctNo());
                    dataDtotemp.setMyAcctNo(dataDto.getCounterAcctNo());
                    dataDto.setMyAcctNo(dataDtotemp.getMyAcctNo());
                    dataDto.setCounterAcctNo(dataDtotemp.getCounterAcctNo());
                }
            }
        }
        return pageData;
    }

    /**
     * 新建资金划拨
     *
     * @param entity 资金划拨实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public int insertData(PtlFundAllocationDto entity) {
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
        List<PtlTrade> ptlTradeList = new ArrayList<>();
        ptlTradeMapper.insert(entity.getPtlTrade());
        ptlTradeList.add(entity.getPtlTrade());
        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
        	PtlTrade ptlTrade = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade, entity.getPtlTrade());
        	ptlTrade.setPortfolioId(entity.getActPortfolioId());
            ptlTradeMapper.insert(ptlTrade);
            ptlTradeList.add(ptlTrade);
        }
        /**
         * 插入划款指令表
         */
        if (entity.getStlPayOrdersList() != null) {
            for (StlPayOrders stlPayOrders : entity.getStlPayOrdersList()) {
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }
        }

        deal持仓(entity, false);

        // 调用估值接口 start
        if (!StringUtil.isEmptyList(entity.getStlPayOrdersList())) {
            for (StlPayOrders stlPayOrders : entity.getStlPayOrdersList()) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getActPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setProdVdate(null);
                inputDto.setStlPayOrders(stlPayOrders);
                inputDto.setTrdProductDeal(entity.getTrdProductDeal());
                inputDto.setPtlTradeList(ptlTradeList);
                AllTradeCommonUtil.stlOrdersBokTradeBuild(inputDto);
            }
            
        }
        // 调用估值接口 end
		// 调用投管接口 add by chenxiaoqiu 20190210 sta
		if (!StringUtil.isEmptyList(entity.getStlPayOrdersList())) {
			for (StlPayOrders stlPayOrders : entity.getStlPayOrdersList()) {
				InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
				investInterfCommDto.setPageId("PtlFundAllocation002");
				investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
				investInterfCommDto.setTrdProductDeal(entity.getTrdProductDeal());
				investInterfCommDto.setTrdProductDealAdd(entity.getTrdProductDealAdd());
				investInterfCommDto.setStlPayOrders(stlPayOrders);
				investInterfCommService.doInvestInterf(investInterfCommDto);
				// 调用投管接口 add by chenxiaoqiu 20190210 end
			}
		}
        return 1;
    }

    private void deal持仓(PtlFundAllocationDto entity, boolean 是否撤销) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", entity.getOrderId());
        paramMap.put("holdingType03", DictCodeList.持仓类型_可用.getValue());
        List<PtlCashPositionFlow> ptlCashPositionFlowList = ptlFundAllocationMapper.selectPtlCashPositionFlowList(paramMap);
        PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
        List<PtlCashPositionFlowDto> ptlCash = new ArrayList<>();
        ptlHoldDto.setPtlCashPositionFlows(ptlCash);
        if (!StringUtil.isEmptyList(ptlCashPositionFlowList)) {
            for (PtlCashPositionFlow ptlCashPositionFlow : ptlCashPositionFlowList) {
                PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto, ptlCashPositionFlow);
                if (是否撤销) {
                    basePtlCashPositionInfoService.deleteCashPosition(ptlCashPositionFlowDto);
                    ptlCashPositionFlowDto.setOrderId(ptlCashPositionFlowDto.getOrderId() + "R");
                }
                ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
//                增加委托指令流水逻辑
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlowDto);
                ptlCash.add(ptlCashPositionFlowDto);
                PtlCashPositionFlowDto ptlCashPositionFlowDto2 = new PtlCashPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlowDto2, ptlCashPositionFlowDto);
                BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
                bussinessCodeParameter.setSeqNo("9999");
                if (是否撤销) {
                    ptlCashPositionFlowDto2.setAcctId(entity.getMyAcctNo());
                } else {
                    ptlCashPositionFlowDto2.setAcctId(entity.getCounterAcctNo());
                }
                ptlCashPositionFlowDto2.setAmount(ptlCashPositionFlowDto2.getAmount().negate());
                String seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter) : IDGenerator.generate();
                ptlCashPositionFlowDto2.setSeqno(seqno);
                ptlCashPositionFlowDto2.setHodingType(DictCodeList.持仓类型_实际.getValue());
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlowDto2);
//                增加委托指令流水逻辑
                ptlCash.add(ptlCashPositionFlowDto2);
            }
        }
        //增加委托指令流水逻辑
        if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, false);
        }
    }

    /**
     * 撤销资金划拨
     *
     * @param entity 资金划拨实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    public int revokeData(PtlFundAllocationDto entity) {
        /**
         * 撤销金融产品交易表数据
         */
        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(entity.getTradeId());
        TrdProductDeal updateTrdProductDeal = trdProductDeal;
        updateTrdProductDeal.setIsCancel(DictCodeList.是否_是.getValue());
        trdProductDealMapper.updateByPrimaryKey(updateTrdProductDeal);
        trdProductDeal.setpTradeId(entity.getTradeId());
//        trdProductDeal.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        trdProductDeal.setTradeId(entity.getTradeId() + "R");
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDealMapper.insert(trdProductDeal);
        /**
         * 撤销组合交易表数据
         */
        PtlTrade ptlTrade = ptlTradeMapper.selectByPrimaryKey(entity.getPtlTrade());
        ptlTrade.setBusiNo(entity.getTradeId() + "R");
        ptlTradeMapper.insert(ptlTrade);
        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
            entity.getPtlTrade().setPortfolioId(entity.getActPortfolioId());
            ptlTrade = ptlTradeMapper.selectByPrimaryKey(entity.getPtlTrade());
            if (ptlTrade != null) {
                ptlTrade.setBusiNo(entity.getTradeId() + "R");
                ptlTradeMapper.insert(ptlTrade);
            }
        }

        // 金融产品交易扩展表
        PaTrdProductDeal paTrdProductDeal = paTrdProductDealMapper.selectByPrimaryKey(entity.getTradeId());
        if (paTrdProductDeal != null) {
            paTrdProductDeal.setTradeId(entity.getTradeId() + "R");
            paTrdProductDealMapper.insert(paTrdProductDeal);
        }

//        /**
//         * 撤销组合现金余额数据（收款账户即对手方账户）
//         */
//        PtlCashPosition cashPosition = ptlCashPositionMapper.selectByPrimaryKey(entity.getPtlCashPosition());
//        if(cashPosition != null){
//            if(cashPosition.getAmount() != null){
//                entity.getPtlCashPosition().setAmount(ArithmeticUtil.subtract(cashPosition.getAmount(), entity.getSettleAmt()));
//                ptlCashPositionMapper.updateByPrimaryKey(entity.getPtlCashPosition());
//            }
//        }
//        else{
//            ptlCashPositionMapper.insert(entity.getPtlCashPosition());
//        }
//        /**
//         * 撤销组合现金余额数据（付款账户即本方账户）
//         */
//        entity.getPtlCashPosition().setAcctId(entity.getMyAcctNo());
//        PtlCashPosition cashPositionMyAccount = ptlCashPositionMapper.selectByPrimaryKey(entity.getPtlCashPosition());
//        if(cashPositionMyAccount != null){
//            if(cashPositionMyAccount.getAmount() != null){
//                entity.getPtlCashPosition().setAmount(ArithmeticUtil.doAdd(cashPosition.getAmount(), entity.getSettleAmt()));
//                ptlCashPositionMapper.updateByPrimaryKey(entity.getPtlCashPosition());
//            }
//        }
//        else{
//            ptlCashPositionMapper.insert(entity.getPtlCashPosition());
//        }

        /**
         * 划款指令表
         */
        Map<String, Object> map = new HashMap<>();
        map.put("tradeId", entity.getTradeId());
        String orderId = ptlFundAllocationMapper.getPayOrderId(map);

        // 撤销划款指令表
        StlPayOrders tempStlPayOrders = stlPayOrdersMapper.selectByPrimaryKey(orderId);
        //stlPayOrdersMapper.deleteByPrimaryKey(orderId);
        
        String tradeIdNew = entity.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR;
        String orderIdNew = orderId + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR;

        tempStlPayOrders.setOrderId(orderIdNew);
        tempStlPayOrders.setpTradeId(orderId);
        tempStlPayOrders.setTradeId(tradeIdNew);
        tempStlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
        stlPayOrdersMapper.insert(tempStlPayOrders);

        // 撤销组合现金余额数据 收付款账户
        // 根据指令编号查询之前的组合现金流水记录，插入相反的流水记录
        deal持仓(entity, true);

//        if(StringUtil.isNotEmpty(orderId)){
//            StlPayOrders stlPayOrders = stlPayOrdersMapper.selectByPrimaryKey(orderId);
//            if(stlPayOrders != null){
//                stlPayOrders.setTradeId(stlPayOrders.getTradeId() + "R");
//                BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter(); 
//                bussinessCodeParameter.setSeqNo("9999");
//                String seqno = baseGetBussinessId !=null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate();
//                stlPayOrders.setOrderId(seqno);
//                stlPayOrdersMapper.insertSelective(stlPayOrders);
//            }
//        }
//        
//        Map<String,Object> maps = new HashMap<>();
//        maps.put("tradeId", orderId);
//        maps.put("hodeType", DictCodeList.持仓类型_可用.getValue());
//        String seq = ptlFundAllocationMapper.selectSeq(maps);
//        PtlCashPositionFlow ptlCashPositionFlowRevoke = ptlCashPositionFlowMapper.selectByPrimaryKey(seq);
//        ptlCashPositionFlowRevoke.setHodingType(DictCodeList.持仓类型_实际.getValue());
//        ptlCashPositionFlowMapper.updateByPrimaryKey(ptlCashPositionFlowRevoke);

        // 调用估值接口 start
        if (!StringUtil.isEmptyList(entity.getStlPayOrdersList())) {
            for (StlPayOrders stlPayOrders : entity.getStlPayOrdersList()) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getPtlTrade().getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setProdVdate(null);
                inputDto.setStlPayOrders(stlPayOrders);
                inputDto.setTrdProductDeal(entity.getTrdProductDeal());
                AllTradeCommonUtil.stlOrdersBokTradeBuild(inputDto);
            }
        }
    	// 调用投管接口 add by chenxiaoqiu 20190205 sta
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("PtlFundAllocation002");
        investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        investInterfCommDto.setTrdProductDeal(trdProductDeal);
        investInterfCommService.doInvestInterf(investInterfCommDto);
        // 调用投管接口 add by chenxiaoqiu 20190205 end
        // 调用估值接口 end
        return 1;
    }

    /**
     * 删除现金流水表的流水
     *
     * @param orderId 划款指令编号
     * @return
     */
    public int doDelete(String orderId) {
        return ptlFundAllocationMapper.deleteFlowByOrderId(orderId, DictCodeList.持仓类型_可用.getValue());
    }

    public String doInsert(PtlFundAllocationDto entity) {
        if (entity.getPtlCashPositionFlow() != null) {
            PtlCashPositionFlow flow = entity.getPtlCashPositionFlow();
            flow.setAcctId(entity.getCounterAcctNo());
            flow.setAmount(flow.getAmount().negate());
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setSeqNo("9999");
            String seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter) : IDGenerator.generate();
            flow.setSeqno(seqno);
            bussCommonMapperDelegateService.insert(PtlCashPositionFlowMapper.class, flow);

            if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
                PtlCashPositionFlow flow1 = new PtlCashPositionFlow();
                BeanUtil.copyPropertiesIgnoreNull(flow1, entity.getPtlCashPositionFlow());
                flow1.setPortfolioId(entity.getActPortfolioId());
                seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter) : IDGenerator.generate();
                flow1.setSeqno(seqno);
                bussCommonMapperDelegateService.insert(PtlCashPositionFlowMapper.class, flow1);
            }
        }
        return "Success";
    }

    public String doInsertAction(PtlFundAllocationDto entity) {
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setSeqNo("9999");

        bussCommonMapperDelegateService.insert(PtlCashPositionFlowMapper.class, entity.getPtlCashPositionFlow());
        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
            PtlCashPositionFlow flow1 = new PtlCashPositionFlow();
            BeanUtil.copyPropertiesIgnoreNull(flow1, entity.getPtlCashPositionFlow());
            flow1.setPortfolioId(entity.getActPortfolioId());
            String seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter) : IDGenerator.generate();
            flow1.setSeqno(seqno);
            bussCommonMapperDelegateService.insert(PtlCashPositionFlowMapper.class, flow1);
        }
        return "Success";
    }
}
