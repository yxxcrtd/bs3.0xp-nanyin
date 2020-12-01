/*
 * 文件名：TrdPlanNetInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.plannet.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月2日
 * @see TrdPlanNetInvestTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdPlanNetInvestTrade001Service extends CommonExportService<TrdPlanNetInvestTradeDto> implements BaseProcessService<TrdPlanNetInvestTradeDto>{

    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    /**
     * 交易产品接口
     */
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;
    
    @Autowired
    private TrdPlanNetInvestTrade003Service trdPlanNetInvestTrade003Service;
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    
    @Autowired(required = false)
    private DateCalService dateCalService;

    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    // 调用投管接口 add by renping 20190205 start
    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;
    // 调用投管接口 add by renping 20190205 end
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;
    /**
     * 撤销审批完成操作
     */
    @Override
    public void doComplete(TrdPlanNetInvestTradeDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        List<BaseTradeProductDealDto> dtoList = restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getFinDto?tradId="+
            entity.getTradeId(),BaseTradeProductDealDto.class);
        
        
        //删除授信占用
        if(StringUtil.equals(DictCodeList.交易方向_申购.getValue(), entity.getPs()) || 
        		StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), entity.getPs())) {
            if (null != entity.getTradeId() && !"".equals(entity.getTradeId())){
                riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
            }
        }
        
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        if(dtoList != null && dtoList.size()>0){
            BaseTradeProductDealDto btpddto = dtoList.get(0);
            btpddto.getTrdProductDeal().setIsCancel(DictCodeListExt1.交易状态_撤销.getValue());
            btpddto.getTrdProductDeal().setCancelRemark(entity.getCancelRemark());
            //获取划款指令
            if(StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(), entity.getPs())){
                List<BussTrdTransferOrderBase> bttob = bussSettleOrderCommService.selectOrdersByBizCode(entity.getbTradeId());
                if(bttob!=null && bttob.size()>0){
                    List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                    BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                    bttodList.add(bttod);
                    List<StlPayOrdersDto> spod = new ArrayList<>();
                    spod.add(bussTrdTransferOrderService.save(bttodList).get(0));
                    btpddto.setStlPayOrdersList(spod);
                }
            }else if(StringUtil.equals(DictCodeList.交易方向_赎回确认.getValue(), entity.getPs())){
                Map<String, Object> accountTradId = new HashMap<>();
                Map<String, Object> param = new HashMap<>();
                param.put("tradeId", entity.getTradeId());
                param.put("valid", DictCodeListExt1.有效状态_有效.getValue());
                accountTradId = getAccountId(param);
                if(accountTradId == null){
                    accountTradId = selectAccountTrdIdinwfl(entity.getTradeId());
                }
              List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
              if(accountTradId !=null){
              if(StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "0")){
                  bttob = trdPlanNetInvestTrade003Service.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
              }else{
                  bttob = bussSettleOrderCommService.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
              }
              if(bttob!=null && bttob.size()>0){
                  List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                  BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                  BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                  bttodList.add(bttod);
                  List<StlPayOrdersDto> spod = new ArrayList<>();
                  spod.add(bussTrdTransferOrderService.save(bttodList).get(0));
                  btpddto.setStlPayOrdersList(spod);
              }
              }
            }
            dtoList = new ArrayList<>();
            dtoList.add(btpddto);
        }
        finProductTradeDto.setBaseTradeProductDealDtos(dtoList);
        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        //basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getTradeId()+"R");

        // 撤销时，子组合需要调估值共通
        if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                finProductTrade.setActPortfolioId(entity.getActPortfolioId());
            });
        }
        baseTradeProductDealCommService.execute(finProductTradeDto);

        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doComplete",
            entity,Integer.class);
        if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
            Map<String, Object> param = new HashMap<>();
            param.put("tradeId", entity.getTradeId());
            param.put("valid", DictCodeListExt1.有效状态_有效.getValue());
            Map<String, Object> resultMap = getAccountId(param);
            if(resultMap != null){
                revokeAccountDoComplet(entity);
            }
        }
        // 一体化对应etliu start
        // 所有交易生效后调用的共通方法
        for (BaseTradeProductDealDto baseDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(entity.getPortfolioId());
            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
            inputDto.setTrdProductDeal(baseDto.getTrdProductDeal());
            if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                inputDto.setActPortfolioId(entity.getActPortfolioId());
            }
            inputDto.setPtlTradeList(entity.getPtlTrade());
            if (baseDto.getStlPayOrdersList() != null && !baseDto.getStlPayOrdersList().isEmpty()) {
                inputDto.setStlPayOrders(baseDto.getStlPayOrdersList().get(0));
            }
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
        }
        // 一体化对应etliu end

        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
            baseTradeProductDealCommService.insertPosition(finProductTradeDto);
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
        }

        // 调用投管接口 add by renping 20190205 start
        if (!(StringUtil.equals(DictCodeList.交易方向_赎回.getValue(), entity.getPs())
            || StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), entity.getPs()))) {
            InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
            investInterfCommDto.setPageId("TrdPlanNetInvestTrade002");
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
            investInterfCommDto.setPortfolioId(entity.getActPortfolioId());
            if (!StringUtil.isEmptyList(finProductTradeDto.getBaseTradeProductDealDtos())) {
                investInterfCommDto.setTrdProductDeal(
                    finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal());
                investInterfCommDto.setTrdProductDealAdd(
                    finProductTradeDto.getBaseTradeProductDealDtos().get(
                        0).getTrdProductDealAdd());
                if (!StringUtil.isEmptyList(finProductTradeDto.getBaseTradeProductDealDtos().get(
                    0).getStlPayOrdersList())) {
                    investInterfCommDto.setStlPayOrders(
                        finProductTradeDto.getBaseTradeProductDealDtos().get(
                            0).getStlPayOrdersList().get(0));
                }
                investInterfCommDto.setOtherTypeForConvert(
                    DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
                investInterfCommService.doInvestInterf(investInterfCommDto);
            }
        }
        // 调用投管接口 add by renping 20190205 end
    }
    /**
     * 
     * 交易到账撤销流程生效
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    public void revokeAccountDoComplet(TrdPlanNetInvestTradeDto entity){
        Map<String, Object> accountTradId = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("tradeId", entity.getTradeId());
        param.put("valid", DictCodeListExt1.有效状态_有效.getValue());
        accountTradId = getAccountId(param);
        if(accountTradId == null){
            accountTradId = selectAccountTrdIdinwfl(entity.getTradeId());
        }
        if(accountTradId!=null){
        if(StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "1")){
            List<BaseTradeProductDealDto> dtoList = restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/getFinDto?tradId="+
                (String)accountTradId.get("TRADID"),BaseTradeProductDealDto.class);
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            if(dtoList != null && dtoList.size()>0){
                BaseTradeProductDealDto btpddto = dtoList.get(0);
                btpddto.getTrdProductDeal().setIsCancel(DictCodeListExt1.交易状态_撤销.getValue());
                btpddto.getTrdProductDeal().setCancelRemark(entity.getCancelRemark());
                List<BussTrdTransferOrderBase> bttob = bussSettleOrderCommService.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                if(bttob!=null && bttob.size()>0){
                    List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                    BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                    BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                    bttodList.add(bttod);
                    List<StlPayOrdersDto> spod = new ArrayList<>();
                    spod.add(bussTrdTransferOrderService.save(bttodList).get(0));
                    btpddto.setStlPayOrdersList(spod);
                }
                dtoList = new ArrayList<>();
                dtoList.add(btpddto);
            }
            finProductTradeDto.setBaseTradeProductDealDtos(dtoList);
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());

            // 撤销时，子组合需要调估值共通
            if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                    finProductTrade.setActPortfolioId(entity.getActPortfolioId());
                });
            }
            baseTradeProductDealCommService.execute(finProductTradeDto);

            if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
                baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                        baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
            }
        }
        }
    }
    /**
     * 
     * 撤销流程启动时操作
     */
    @Override
    public void doInsert(TrdPlanNetInvestTradeDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        if (StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())) {
            Map<String, Object> accountTradId = new HashMap<>();
            Map<String, Object> param = new HashMap<>();
            param.put("tradeId", entity.getTradeId());
            param.put("valid", DictCodeListExt1.有效状态_有效.getValue());
            accountTradId = getAccountId(param);
            if (accountTradId == null) {
                accountTradId = selectAccountTrdIdinwfl(entity.getTradeId());
            }
            if (accountTradId != null) {
//                List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                if (StringUtil.equals((String) accountTradId.get("WORKFLOWID"), "0")) {
//                    bttob = trdPlanNetInvestTrade003Service.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                } else {
//                    bttob = bussSettleOrderCommService.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                    TrdPlanNetInvestTradeDto repAccount = new TrdPlanNetInvestTradeDto();
                    repAccount.setTradeId((String) accountTradId.get("TRADID"));
                    repAccount.setWorkflowId(entity.getWorkflowId());
                    repAccount.setCancelRemark(entity.getCancelRemark());
//                    if (bttob != null && bttob.size() > 0) {
//                        List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
//                        BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
//                        BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
//                        bttodList.add(bttod);
//                        repAccount.setBussTrdTransferOrder(bussTrdTransferOrderService.save(bttodList));
//                    }
                    revokeAccount(repAccount);
                }
            }
        }
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doInsert",
                entity, Integer.class);
    }
    
    /**
     * 撤销流程作废
     */
    @Override
    public void doDelete(String workflowId) {
        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/doDelete?workflowId="+
            workflowId, Integer.class);
    }
    /**
     * 
     * 撤销已生效交易到账
     * <功能详细描述>
     * @param tradId
     * @see
     * @since 1.0
     */
    public void revokeAccount(TrdPlanNetInvestTradeDto entity){
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/revokeAccount",
            entity, void.class);
    }
    
    public boolean selectConfirmInfo(String bTradeId){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selectConfirmInfo?bTradeId="+
            bTradeId, boolean.class);
    }
    
    /**
     * 
     * 查询到账交易是否托管匹配
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public Integer selRedemption(String tradeId){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selRedemption?tradeId="+
            tradeId,Integer.class);
    }
    /**
     * 
     * 检验撤回数据是否有后继交易
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public boolean revokeCheck(TrdPlanNetInvestTradeDto dto){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/revokeCheck",
            dto,boolean.class);
    }
    /**
     * 
     * 获取交易到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getAccountId(Map<String, Object> param){
        Map<String, Object> accountTradId = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selectAccountTrdId",
            param, Map.class);
        return accountTradId;
    }
    /**
     * 
     * 获取交易到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> selectAccountTrdIdinwfl(String tradId){
        Map<String, Object> accountTradId = restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selectAccountTrdIdinwfl?tradId="+
            tradId, Map.class);
        return accountTradId;
    }
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdPlanNetInvestTradeDto> findPage(Map<String, Object> pageParam) {
        pageParam.put("loginUser", SecurityUtil.getLoginUser().getUserName());
        String[] portfolioId = null;
        if(pageParam.get("portfolioId") != null && !(pageParam.get("portfolioId").toString().isEmpty())){
            String portfolioIdTemp = (String)pageParam.get("portfolioId");
            portfolioId = portfolioIdTemp.split(",");
            pageParam.replace("portfolioId", Arrays.asList(portfolioId));
        }else{
        	pageParam.replace("portfolioId", portfolioId);
        }
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/findPage",
            pageParam,TrdPlanNetInvestTradeDto.class);
    }
    
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	String isShowType = (String) exportParam.getSelectParam().get("isShowType");
        if(!StringUtil.isNotEmpty(isShowType) || StringUtil.equals(isShowType, "01")) {
        	exportParam.config("/com/joyintech/tams/invest/trade/plannet/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
        }else {
        	exportParam.config("/com/joyintech/tams/invest/trade/plannet/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
        }
        
       
    }
    @Override
    public Page<TrdPlanNetInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
    
    public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(Map<String, Object> pageParam) {
    	String tradeDateStr = (String)pageParam.get("tradeDate");
        Date tradeDate = DateUtil.parse(tradeDateStr);

        String dateId = (String)pageParam.get("calendarId");
        String finprodId = (String)pageParam.get("finprodId");
        if (StringUtil.isBlank(dateId) && StringUtil.isNotBlank(finprodId)) {
            dateId = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class,
            		pageParam.get("finprodId").toString(), FinProduct.class).getCalendarId();
        }
        List<String> holidayList = dateCalService.getDealCalendarHoliday(tradeDate, DateUtil.addDay(tradeDate, 15), dateId);
        pageParam.put("tradeDateNext", DateUtil.format(dateCalService.calculationTradingDay(tradeDate, 1, holidayList, "1")));
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanNetInvestTrade/selectTrdTransfenpeiList",
	            pageParam,BussTrdTransfenpeiDto.class);
	}

    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        TrdPlanNetInvestTradeDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            BigDecimal amt = dto.getPrinAmt();
            if (amt == null || amt.compareTo(BigDecimal.ZERO) <= 0) {
                amt = dto.getShareAmt();
            }
            try {
//              return commonBusinessService.是否需要上级审批(dto.getCounterId(), dto.getPrinAmt(), LoginUserUtil.getUserName(),
//                      DictCodeList.平安资产类型_计划类资产_净值型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
//                      // 资产类型改为授权类型 add by renping 2019-08-06  start
//                      , dto.getFinprodId()
//                      // 资产类型改为授权类型 add by renping 2019-08-06  end
//                      );
                Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(),
                        //金额不存在的时候取份额 wangguiqi 20200416
                        dto.getPrinAmt() != null ? dto.getPrinAmt():dto.getShareAmt(), LoginUserUtil.getUserName(),
                        DictCodeList.平安资产类型_计划类资产_净值型.getValue(), dto.getType6(), dto.getTradeMarket(), dto.getPs()
                        , dto.getFinprodId() ,entity.getWorkflowId());
                params.put("riskTrialRetDto", dto.getRiskTrialRetDto());
                return commonBusinessService.是否需要上级审批(params);
            }
            catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        }
        else {
            return null;
        }
//        return null;
    }

    /**
     * 获取委托代码
     * @param map 请求参数
     * @return 联想文本框实体类
     * @since 1.0
     */
    public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
        return restTemplate.postForList(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
                SuggestDto.class);
    }
}
