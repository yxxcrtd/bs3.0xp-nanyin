/*
 * 文件名：PtlActivityPeriod002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.RepTradeMapper;
import com.joyintech.tams.common.base.dao.StlCapacctInfoMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.PtlTradeKey;
import com.joyintech.tams.common.base.entity.RepTrade;
import com.joyintech.tams.common.base.entity.RepTradeKey;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfoKey;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderResultDto;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.component.base.group.impl.BasePtlCashPositionInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 活期结息新建
 * @author fanshuanghu
 * @version 1.0 2018年9月26日
 * @see PtlActivityPeriod002Service
 * @since 1.0
 */
@Service
public class PtlActivityPeriod002Service extends CommonExportService<PtlActivityPeriod001Dto> 
implements BaseProcessService<PtlActivityPeriod001Dto>{
    /**
     * 注入主键生成工具类
     */
    @Autowired(required = false)
    public BaseGetBusinessId baseGetBussinessId;
    
    /**
     * 货款指令Mapper
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    
    /**
     * 金融产品交易汇总Mapper
     */
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    
    /**
     * 金融产品交易Mapper
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    /**
     * 金融产品交易Mapper
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;
    /**
     * 金融产品交易扩展Mapper
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    /**
     * 组合交易Mapper
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    /**
     * 中债报送信息
     */
    @Autowired
    private RepTradeMapper repTradeMapper;
    /**
     * 现金流更新Service
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoServiceImpl BasePtlCashPositionInfoServiceImpl;
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    /**
     * 平台日期获取
     */
    @Autowired(required=false)
    private PlatformDateImpl platformDateImpl;
    /**
     * 委托指令流水共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    
    @Autowired
    private StlCapacctInfoMapper stlCapacctInfoMapper;
    
    /**
     * 查询明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlActivityPeriod001Dto> findDetailsPage(Map<String,Object> map) {
        String busiAcctNo = (String)map.get("busiAcctNo");
        if(StringUtil.isNotEmpty(busiAcctNo)) {
            List<String> busiAcctNoList = StringUtil.splitToList(",",busiAcctNo);
            map.put("busiAcctNoList", busiAcctNoList);
        }
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/findDetailsPage", map, PtlActivityPeriod001Dto.class); 
    }
    
    public  List<PtlActivityPeriod001Dto> findChildrenPortfolioDetails(Map<String,Object> map) {
        List<PtlActivityPeriod001Dto> list = restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME +
                                                "/ptlactivityperiod/findChildrenPortfolioDetails", map, PtlActivityPeriod001Dto.class); 
        return list;
    }
    
    public List<PtlActivityPeriod001Dto> findDetailsByBusiNo(Map<String,Object> map) {
    	String busiAcctNo = (String)map.get("busiAcctNo");
    	if(!StringUtil.isNotEmpty(busiAcctNo)) {
    		return new ArrayList<PtlActivityPeriod001Dto>();
    	}
    	else {
            List<String> busiAcctNoList = StringUtil.splitToList(",",busiAcctNo);
            map.put("busiAcctNoList", busiAcctNoList);
        }
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/findDetailsByBusiNo", map, PtlActivityPeriod001Dto.class); 
    }
    
    public List<BussTrdTransferOrderDto> selectBussTrdTransferOrder(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/selectBussTrdTransferOrder", map, BussTrdTransferOrderDto.class); 
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlActivityPeriod001Dto dto, OperationType type) {
            dto.setOperateType(type.toString());
            //每一条组合信息对应他的划款指令
            for(PtlActivityPeriod001Dto period001Dto:dto.getDetailsList()) {
                for(BussTrdTransferOrderDto orderDto:dto.getBussTrdTransferOrderList()) {
                    if(StringUtil.equals(period001Dto.getAcctNo(), orderDto.getRecAcctNo())
                        && StringUtil.equals(period001Dto.getPortfolioId(),orderDto.getPortfolioId())) {
                        period001Dto.setBussTrdTransferOrder(orderDto);
                    }
                }
            }

            if("NEW".equals(dto.getOperateType())){
                
                //汇总交易
                TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
                BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, dto);
                trdFinProductDealTotal.setTotleTradeId(dto.getBusiNo());
                trdFinProductDealTotal.setFinprodId(dto.getAcctNo());
                trdFinProductDealTotal.setTradeDate(dto.getSettleDate());
//                trdFinProductDealTotal.setVdate(dto.getVdate());//需求确认过不需要上次收息日，暂时没存该字段
                trdFinProductDealTotal.setMdate(dto.getMdate());
                trdFinProductDealTotal.setPs(DictCodeList.交易方向_收息.getValue());
                trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
                trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_资金账户.getValue());
                trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_活期结息.getValue());
                trdFinProductDealTotal.setBranch(0L);
                trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
                trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
                
                
                if(!StringUtil.isEmptyList(dto.getDetailsList())) {
                    for(PtlActivityPeriod001Dto period001Dto:dto.getDetailsList()) {
                    	if(StringUtil.isNotEmpty(period001Dto.getActualTradeAmt()) 
                    			&& period001Dto.getActualTradeAmt().compareTo(BigDecimal.ZERO) > 0) {
                            period001Dto.setSettleDate(dto.getSettleDate());
                            //交易表  
                            TrdProductDeal trdProductDeal = genTrdProductDeal(dto,period001Dto);
                            
                            trdProductDealMapper.insertSelective(trdProductDeal);
                            // 一体化对应etliu end
                            
                            TrdProductDealAdd add = this.genTrdProductDealAdd(period001Dto, trdProductDeal);
                            trdProductDealAddMapper.insertSelective(add);
                            
                            PaTrdProductDeal paDeal = new PaTrdProductDeal();
                            paDeal.setPaBussType(DictCodeListExt1.平安业务类型_活期结息.getValue());
                            paDeal.setTradeId(trdProductDeal.getTradeId());
                            paTrdProductDealMapper.insertSelective(paDeal);
                            
                            PtlTrade ptlTrade = this.genPtlTrade(period001Dto, trdProductDeal);
                            ptlTradeMapper.insertSelective(ptlTrade);
                            

                            //划款指令
                            StlPayOrders stlPayOrders = new StlPayOrders();
                            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, period001Dto.getBussTrdTransferOrder());
                            stlPayOrders.setWorkflowId(dto.getWorkflowId());
                            stlPayOrders.setTradeId(trdProductDeal.getTradeId());
                            stlPayOrders.setMyAcctNo(period001Dto.getBussTrdTransferOrder().getRecAcctNo());
                            stlPayOrders.setCounterAcctNo(period001Dto.getBussTrdTransferOrder().getPayAcctNo());
                            stlPayOrdersMapper.insert(stlPayOrders);
                            
                            //组合现金流水
                            PtlCashPositionFlowDto ptlCashPositionFlowDto = this.genPtlCashPositionFlowDto(period001Dto,period001Dto.getBussTrdTransferOrder().getOrderId(),false);
                            BasePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlowDto);
                            
                            //更新指令流水
                            List<PtlCashPositionFlowDto> ptlCash = new ArrayList<>();
                            ptlCash.add(ptlCashPositionFlowDto);
                            //委托指令流水共通调用
                            //增加委托指令流水逻辑
                            if(baseEtrCashPositionCommService!=null){
                                PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
                                ptlHoldDto.setPtlCashPositionFlows(ptlCash);
                                baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
                            }
                            
                            //子组合
                            if(StringUtil.equals(period001Dto.getIsParentPortfolio(), DictCodeList.是否_是.getValue())) {
                                if(!StringUtil.isEmptyList(period001Dto.getChildrenDetailsList())) {
                                    for(PtlActivityPeriod001Dto childrenDetailsDto:period001Dto.getChildrenDetailsList()) {
                                        childrenDetailsDto.setSettleDate(dto.getSettleDate());
                                        
                                        TrdProductDeal childrenTrdProductDeal = this.genTrdProductDeal(dto, childrenDetailsDto);
                                        trdProductDealMapper.insertSelective(childrenTrdProductDeal);
                                        
                                        TrdProductDealAdd childrenAdd = this.genTrdProductDealAdd(childrenDetailsDto, childrenTrdProductDeal);
                                        trdProductDealAddMapper.insertSelective(childrenAdd);
                                        
                                        PaTrdProductDeal childrenPaDeal = new PaTrdProductDeal();
                                        childrenPaDeal.setPaBussType(DictCodeListExt1.平安业务类型_活期结息.getValue());
                                        childrenPaDeal.setTradeId(childrenTrdProductDeal.getTradeId());
                                        paTrdProductDealMapper.insertSelective(childrenPaDeal);
                                        
//                                        //划款指令
//                                        StlPayOrders childrenStlPayOrders = new StlPayOrders();
//                                        BeanUtil.copyPropertiesIgnoreNull(childrenStlPayOrders, period001Dto.getBussTrdTransferOrder());
//                                        String orderId;
//                                        if(baseGetBussinessId != null){
//                                            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
//                                            bussinessCodeParameter.setSeqNo("9998");
//                                            orderId = baseGetBussinessId.buildBussinessCode(bussinessCodeParameter);
//                                        }
//                                        else {
//                                            orderId = IDGenerator.generate();
//                                        }
//                                        childrenStlPayOrders.setOrderId(orderId);
//                                        childrenStlPayOrders.setWorkflowId(dto.getWorkflowId());
//                                        childrenStlPayOrders.setTradeId(childrenTrdProductDeal.getTradeId());
//                                        childrenStlPayOrders.setSettleAmt(childrenDetailsDto.getTradeAmt());
//                                        childrenStlPayOrders.setMyAcctNo(period001Dto.getBussTrdTransferOrder().getRecAcctNo());
//                                        childrenStlPayOrders.setCounterAcctNo(period001Dto.getBussTrdTransferOrder().getPayAcctNo());
//                                        stlPayOrdersMapper.insert(childrenStlPayOrders);
                                        
                                        PtlTrade childrenPtlTrade = this.genPtlTrade(childrenDetailsDto, childrenTrdProductDeal);
                                        ptlTradeMapper.insertSelective(childrenPtlTrade);
                                        
                                        //组合现金流水
                                        childrenDetailsDto.setCcy(period001Dto.getCcy());
                                        PtlCashPositionFlowDto childrenPtlCashPositionFlowDto = this.genPtlCashPositionFlowDto(childrenDetailsDto,period001Dto.getBussTrdTransferOrder().getOrderId(),false);
                                        BasePtlCashPositionInfoServiceImpl.insertCashPositionFlow(childrenPtlCashPositionFlowDto);
                                        
                                        //更新指令流水
                                        List<PtlCashPositionFlowDto> childrenPtlCash = new ArrayList<>();
                                        childrenPtlCash.add(childrenPtlCashPositionFlowDto);
                                        //委托指令流水共通调用
                                        //增加委托指令流水逻辑
                                        if(baseEtrCashPositionCommService!=null){
                                            PtlPositionHoldDto childrenPtlHoldDto = new PtlPositionHoldDto();
                                            childrenPtlHoldDto.setPtlCashPositionFlows(childrenPtlCash);
                                            baseEtrCashPositionCommService.portfolioEtrCashPosition(childrenPtlHoldDto, true);
                                        }
                                        
                                        Map<String,Object> map = new HashMap<>();
                                        map.put("portfolioId", childrenDetailsDto.getPortfolioId());
                                        List<String> registCodeList = restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME +
                                            "/ptlactivityperiod/getProdRegistCode", map, String.class); 
                                        if(registCodeList != null && registCodeList.size() > 0){
                                            String registCode = registCodeList.get(0);
                                            RepTrade repTrade = new RepTrade();
                                            repTrade.setRepFinprodRegId(registCode);
                                            repTrade.setRepBankTradeCode(childrenTrdProductDeal.getTradeId());
                                            repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_其他收入.getValue());
                                            Map<String,Object> acctMap = new HashMap<>();
                                            acctMap.put("portfolioId",childrenDetailsDto.getPortfolioId());
                                            acctMap.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
                                            acctMap.put("ccy", childrenDetailsDto.getCcy());
                                            String acctNo = restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME +
                                                "/ptlactivityperiod/getRepAcctNo", map, String.class);
                                            repTrade.setRepCount(childrenDetailsDto.getTradeAmt());
                                            repTrade.setRepOccurMoney(childrenDetailsDto.getTradeAmt());
                                            repTrade.setRepTradeComponent(acctNo);
                                            repTrade.setRepTradeDate(childrenDetailsDto.getSettleDate());
                                            if(platformDateImpl != null){
                                                repTrade.setRepStatusDate(platformDateImpl.getSysDate());
                                            }
                                            repTradeMapper.insertSelective(repTrade);
                                        }
                                        
                                        // 一体化对应etliu start
                                        // 所有交易生效后调用的共通方法
                                        AllTradeCommonInputDto childrenInputDto = new AllTradeCommonInputDto();
                                        childrenInputDto.setProdid(childrenDetailsDto.getPortfolioId());
                                        childrenInputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                                        childrenInputDto.setTrdProductDeal(childrenTrdProductDeal);
                                        childrenInputDto.setPtlTradeList(Arrays.asList(childrenPtlTrade));
//                                        childrenInputDto.setStlPayOrders(childrenStlPayOrders);
                                        AllTradeCommonUtil.handleAfterTradeComplete(childrenInputDto);
                                    }
                                }
                            } 
                            
                            

                            Map<String,Object> map = new HashMap<>();
                            map.put("portfolioId", period001Dto.getPortfolioId());
                            List<String> registCodeList = restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME +
                                "/ptlactivityperiod/getProdRegistCode", map, String.class); 
                            if(registCodeList != null && registCodeList.size() > 0){
                                String registCode = registCodeList.get(0);
                                RepTrade repTrade = new RepTrade();
                                repTrade.setRepFinprodRegId(registCode);
                                repTrade.setRepBankTradeCode(trdProductDeal.getTradeId());
                                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_其他收入.getValue());
                                Map<String,Object> acctMap = new HashMap<>();
                                acctMap.put("portfolioId",period001Dto.getPortfolioId());
                                acctMap.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
                                acctMap.put("ccy", period001Dto.getCcy());
                                String acctNo =  restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME +
                                    "/ptlactivityperiod/getRepAcctNo", map, String.class);
                                repTrade.setRepCount(period001Dto.getTradeAmt());
                                repTrade.setRepOccurMoney(period001Dto.getTradeAmt());
                                repTrade.setRepTradeComponent(acctNo);
                                repTrade.setRepTradeDate(period001Dto.getSettleDate());
                                if(platformDateImpl != null){
                                    repTrade.setRepStatusDate(platformDateImpl.getSysDate());
                                }
                                repTradeMapper.insertSelective(repTrade);
                            }
                            
                            // 一体化对应etliu start
                            // 所有交易生效后调用的共通方法
                            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                            inputDto.setProdid(period001Dto.getPortfolioId());
                            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                            inputDto.setTrdProductDeal(trdProductDeal);
                            inputDto.setPtlTradeList(Arrays.asList(ptlTrade));
                            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                            // 一体化对应etliu end
                    	}
                    }
                }
            }
           
            else{
                //汇总交易表
                TrdFinProductDealTotal trdFinProductDealTotal = trdFinProductDealTotalMapper.selectByPrimaryKey(dto.getBusiNo());
                if(trdFinProductDealTotal != null) {
                    trdFinProductDealTotal.setpTradeId(trdFinProductDealTotal.getTotleTradeId());
                    trdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                    trdFinProductDealTotal.setWorkflowId(dto.getWorkflowId());
                    trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                    trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
                    
                    TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
                    trdFinProductDealTotalRevoke.setTotleTradeId(dto.getBusiNo());
                    trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
                    trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);
                }
                
                List<TrdProductDeal> trdProductDealList = restTemplate.getForList(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryTrdProductDealByTotleTradeId?tradeId=" 
                                + dto.getBusiNo(), TrdProductDeal.class);
                if(!StringUtil.isEmptyList(trdProductDealList)) {
                    for(TrdProductDeal trdProductDeal:trdProductDealList) {
                        
                        //划款指令
                        List<StlPayOrders> listStlPayOrders = restTemplate.getForList(
                            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryStlPayOrdersByTradeId?tradeId="
                                                                                      + trdProductDeal.getTradeId(),
                            StlPayOrders.class);
                        listStlPayOrders.forEach(stlPayOrdersDto -> {
                            stlPayOrdersDto.setpTradeId(stlPayOrdersDto.getTradeId());
                            stlPayOrdersDto.setOrderId(
                                StringUtil.defaultString(stlPayOrdersDto.getOrderId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                            stlPayOrdersDto.setTradeId(
                                StringUtil.defaultString(stlPayOrdersDto.getTradeId()) + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                            stlPayOrdersDto.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                            stlPayOrdersMapper.insertSelective(stlPayOrdersDto);
                        });
                        
                        //交易附属表
                        TrdProductDealAdd trdProductDealAdd = trdProductDealAddMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                        trdProductDealAdd.setTradeId(trdProductDealAdd.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                        trdProductDealAddMapper.insertSelective(trdProductDealAdd);
                        
                        //交易扩展表
                        PaTrdProductDeal paTrdProductDeal = paTrdProductDealMapper.selectByPrimaryKey(trdProductDeal.getTradeId());
                        paTrdProductDeal.setTradeId(paTrdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                        paTrdProductDealMapper.insertSelective(paTrdProductDeal);
                        
                        //组合交易
                        PtlTradeKey ptlTradeKey = new PtlTradeKey();
                        ptlTradeKey.setBusiNo(trdProductDeal.getTradeId());
                        ptlTradeKey.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
                        ptlTradeKey.setPortfolioId(trdProductDealAdd.getPortfolioId());
                        PtlTrade ptlTrade = ptlTradeMapper.selectByPrimaryKey(ptlTradeKey);
                        ptlTrade.setBusiNo(ptlTrade.getBusiNo() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                        ptlTradeMapper.insertSelective(ptlTrade);
                        
                        //交易表
                        trdProductDeal.setpTradeId(trdProductDeal.getTradeId());
                        trdProductDeal.setTotleTradeId(trdProductDeal.getTotleTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                        trdProductDeal.setTradeId(trdProductDeal.getTradeId() + BussinessConstant.TRADE_REVOKE_TRADE_ID_SPLIT_STR);
                        trdProductDeal.setWorkflowId(dto.getWorkflowId());
                        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                        trdProductDeal.setCancelRemark(dto.getCancelRemark());
                        trdProductDealMapper.insertSelective(trdProductDeal);
                        
                        TrdProductDeal trdProductDealRevoke = new TrdProductDeal();
                        trdProductDealRevoke.setTradeId(trdProductDeal.getpTradeId());
                        trdProductDealRevoke.setIsCancel(DictCodeList.是否_是.getValue());
                        trdProductDealMapper.updateByPrimaryKeySelective(trdProductDealRevoke);
                        
                        
                        RepTradeKey repTradeKey = new RepTradeKey();
                        repTradeKey.setRepBankTradeCode(trdProductDeal.getTradeId());
                        repTradeKey.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_其他收入.getValue());
                        RepTrade repTrade =repTradeMapper.selectByPrimaryKey(repTradeKey);
                        if(repTrade != null){
                            repTradeMapper.deleteByPrimaryKey(repTradeKey);
                        }
                        
                        
                        // 一体化对应etliu start
                        // 所有交易生效后调用的共通方法
                        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                        inputDto.setProdid(dto.getPortfolioId());
                        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                        inputDto.setTrdProductDeal(trdProductDeal);
                        inputDto.setPtlTradeList(Arrays.asList(ptlTrade));
                        AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                        // 一体化对应etliu end
                    }
                }
                

                
                if(!StringUtil.isEmptyList(dto.getDetailsList())) {
                    for(PtlActivityPeriod001Dto period001Dto:dto.getDetailsList()) {
                        period001Dto.setSettleDate(dto.getSettleDate());
                        PtlCashPositionFlowDto ptlCashPositionFlowDto = this.genPtlCashPositionFlowDto(period001Dto,period001Dto.getBussTrdTransferOrder().getOrderId(), true); 
                        BasePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlowDto);
                        List<PtlCashPositionFlowDto> ptlCash = new ArrayList<>();
                        ptlCash.add(ptlCashPositionFlowDto);
                        //委托指令流水共通调用
                        //增加委托指令流水逻辑
                        if(baseEtrCashPositionCommService!=null){
                            PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
                            ptlHoldDto.setPtlCashPositionFlows(ptlCash);
                            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
                        }
                        
                        if(StringUtil.equals(period001Dto.getIsParentPortfolio(), DictCodeList.是否_是.getValue())) {
                            if(!StringUtil.isEmptyList(period001Dto.getChildrenDetailsList())) {
                                for(PtlActivityPeriod001Dto childrenDetailsDto:period001Dto.getChildrenDetailsList()) {
                                    childrenDetailsDto.setSettleDate(dto.getSettleDate());
                                    
                                    //组合现金流水
                                    childrenDetailsDto.setCcy(period001Dto.getCcy());
                                    PtlCashPositionFlowDto childrenPtlCashPositionFlowDto = this.genPtlCashPositionFlowDto(childrenDetailsDto,period001Dto.getBussTrdTransferOrder().getOrderId(),true);
                                    BasePtlCashPositionInfoServiceImpl.insertCashPositionFlow(childrenPtlCashPositionFlowDto);
                                    
                                    //更新指令流水
                                    List<PtlCashPositionFlowDto> childrenPtlCash = new ArrayList<>();
                                    childrenPtlCash.add(childrenPtlCashPositionFlowDto);
                                    //委托指令流水共通调用
                                    //增加委托指令流水逻辑
                                    if(baseEtrCashPositionCommService!=null){
                                        PtlPositionHoldDto childrenPtlHoldDto = new PtlPositionHoldDto();
                                        childrenPtlHoldDto.setPtlCashPositionFlows(childrenPtlCash);
                                        baseEtrCashPositionCommService.portfolioEtrCashPosition(childrenPtlHoldDto, true);
                                    }
                                }
                            }
                        }
                    }
                }
       
            }
        
    }

    /**
     * 获取组合名称
     * @param map 组合代码
     * @return 组合名称
     * @see
     * @since 1.0
     */
    public String getportFolioName(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getportFolioName",map, String.class);
    }
    /**
     * 获取划款指令信息（未调用）
     * @param map 查询参数
     * @return 账号
     * @see
     * @since 1.0
     */
    public String getAcctNo(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getAcctNo",map, String.class);
    }
    /**
     * 获取账号名称（未调用）
     * @param map 请求参数
     * @return 账号名称
     * @see
     * @since 1.0
     */
    public String getAcctName(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getAcctName",map, String.class);
    }
    /**
     * 获取上次付息日
     * @param map 请求参数（组合、账号）
     * @return 上次付息日
     * @see
     * @since 1.0
     */
    public String getVDate(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getVDate",map, String.class);
    }
    /**
     * 获取支付日
     * @return 支付日
     * @see
     * @since 1.0
     */
    public String getInitDate(){
        return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getInitDate", String.class);
    }
    
    /**
     * 获取对手方账户列表
     * @param map 请求参数
     * @return 对手方账户列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterAcct(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getCounterAcct",map, SelectOptionDto.class);
    }
    
    /**
     * 划款金额默认为理论计提金额
     * @return 理论计提金额
     * @see
     * @since 1.0
     */
    public BigDecimal initTradeAmt(Map<String,Object> paramMap) {
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/initTradeAmt", paramMap, BigDecimal.class);
    }
    
    /**
     * 获取开户机构信息
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBankDep(Map<String,Object> paramMap){
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlactivityperiod/getBankDep",paramMap, SelectOptionDto.class);
    }
    
    
    /**
     * 组装TrdProductDeal
     * <一句话功能简述>
     * <功能详细描述>
     * @param parentDto
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal genTrdProductDeal(PtlActivityPeriod001Dto parentDto,PtlActivityPeriod001Dto dto){
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter(); 
        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_活期结息);
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, dto);
       
        trdProductDeal.setTradeId(baseGetBussinessId !=null ? 
            baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
        trdProductDeal.setFinprodId(dto.getAcctNo());
        trdProductDeal.setTradeDate(parentDto.getSettleDate());
//        trdProductDeal.setVdate(dto.getVdate());和需求确认目前不需要上次收息日，故先不存。
        trdProductDeal.setMdate(parentDto.getMdate());
        trdProductDeal.setTotleTradeId(parentDto.getBusiNo());
        trdProductDeal.setTradeAmt(dto.getActualTradeAmt());
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_活期结息.getValue());
        trdProductDeal.setPs(DictCodeList.交易方向_收息.getValue());
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_资金账户.getValue());
        trdProductDeal.setBranch(0L);
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        return trdProductDeal;
    }
    
    /**
     * 组装交易附属表
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param trdProductDeal
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd genTrdProductDealAdd(PtlActivityPeriod001Dto dto,TrdProductDeal trdProductDeal) {
        TrdProductDealAdd add = new TrdProductDealAdd();
        add.setPortfolioId(dto.getPortfolioId());
        add.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        add.setTradeId(trdProductDeal.getTradeId());
        return add;
    }
    
    private PtlTrade genPtlTrade(PtlActivityPeriod001Dto dto,TrdProductDeal trdProductDeal) {
        PtlTrade ptlTrade = new PtlTrade();
        BeanUtil.copyPropertiesIgnoreNull(ptlTrade, dto);
        ptlTrade.setBusiId(dto.getAcctNo());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_活期结息.getValue());
        ptlTrade.setBusiNo(trdProductDeal.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        return ptlTrade;
    }
        
    /**
     * 组装组合现金流水
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param isRevoke
     * @return
     * @see
     * @since 1.0
     */
    private PtlCashPositionFlowDto genPtlCashPositionFlowDto(PtlActivityPeriod001Dto dto,String orderId,boolean isRevoke){
        PtlCashPositionFlowDto ptlCashPositionFlowDto =new PtlCashPositionFlowDto();
        ptlCashPositionFlowDto.setPortfolioId(dto.getPortfolioId());
        ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlCashPositionFlowDto.setCcy(dto.getCcy());
        ptlCashPositionFlowDto.setAcctId(dto.getAcctNo());
        ptlCashPositionFlowDto.setOrderId(orderId);
        ptlCashPositionFlowDto.setCdate(dto.getSettleDate());
        ptlCashPositionFlowDto.setCcy(dto.getCcy());
        if(isRevoke) {
            ptlCashPositionFlowDto.setAmount(dto.getActualTradeAmt().negate());
        } else {
            ptlCashPositionFlowDto.setAmount(dto.getActualTradeAmt());
        }
        if(StringUtil.equals(dto.getIsParentPortfolio(), DictCodeList.是否_是.getValue())) {
            ptlCashPositionFlowDto.setSplitType(DictCodeListExt3.拆分标识_已拆分.getValue());
        }
        return ptlCashPositionFlowDto;
    }
    
    /**
     * 
     * 自定义生成付款指令
     * <功能详细描述>
     * @param bussSettleOrderParameterDtos
     * @return
     * @see
     * @since 1.0
     */
    public List<BussTrdTransferOrderDto> generateOrder(PtlActivityPeriod001Dto dto) {
        List<BussTrdTransferOrderDto> list = new ArrayList<BussTrdTransferOrderDto>();
        for(PtlActivityPeriod001Dto ptlActivityPeriod001Dto :dto.getDetailsList()) {
            String orderId;
            if(baseGetBussinessId != null){
                BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
                bussinessCodeParameter.setSeqNo("9998");
                orderId = baseGetBussinessId.buildBussinessCode(bussinessCodeParameter);
            }
            else {
                orderId = IDGenerator.generate();
            }
            BussTrdTransferOrderDto bussTrdTransferOrderDto = new BussTrdTransferOrderDto();
            bussTrdTransferOrderDto.setOrderId(orderId);
            bussTrdTransferOrderDto.setPayType(DictCodeListExt1.平安划款类型_托管划款.getValue());
            bussTrdTransferOrderDto.setSettleDate(dto.getSettleDate());
            bussTrdTransferOrderDto.setPs(DictCodeListExt1.收支方向_收入.getValue());
            bussTrdTransferOrderDto.setSettleAmt(ptlActivityPeriod001Dto.getActualTradeAmt());
            bussTrdTransferOrderDto.setCcy(ptlActivityPeriod001Dto.getCcy());
            bussTrdTransferOrderDto.setSettleStatus(DictCodeList.结算状态_已结算.getValue());
            bussTrdTransferOrderDto.setIsNeedSend(DictCodeList.是否_否.getValue());
            bussTrdTransferOrderDto.setIsTotalTrade(DictCodeList.是否_否.getValue());
            bussTrdTransferOrderDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_资金账户.getValue());
            bussTrdTransferOrderDto.setTradeId(ptlActivityPeriod001Dto.getBusiNo());
            bussTrdTransferOrderDto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue()); 
            
            bussTrdTransferOrderDto.setRecAcctNo(ptlActivityPeriod001Dto.getAcctNo());
            bussTrdTransferOrderDto.setRecAcctName(ptlActivityPeriod001Dto.getAcctName());
            
            StlCapacctInfo stlCapacctInfo = bussTrdTransferOrderService.queryStlCapacctInfoByBusiAcctNo(ptlActivityPeriod001Dto.getCcy(), ptlActivityPeriod001Dto.getAcctNo());
            bussTrdTransferOrderDto.setRecAcctPoint(stlCapacctInfo.getLargePayno());
            bussTrdTransferOrderDto.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源, stlCapacctInfo.getInOutBank()));
            
            
            bussTrdTransferOrderDto.setPortfolioId(ptlActivityPeriod001Dto.getPortfolioId());
            bussTrdTransferOrderDto.setPortfolioName(ptlActivityPeriod001Dto.getPortfolioName());
            
            list.add(bussTrdTransferOrderDto);
        }

        return list;
        
    }
    
}
