/*
 * 文件名：TrdPlanCcyInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.planccy.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTrade001Service
 * @since 1.0
 */
@Service
public class TrdPlanCcyInvestTrade001Service extends CommonExportService<TrdPlanCcyInvestTradeDto> implements BaseProcessService<TrdPlanCcyInvestTradeDto> {

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
    private TrdPlanCcyInvestTrade003Service trdPlanCcyInvestTrade003Service;
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
    public void doComplete(TrdPlanCcyInvestTradeDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        List<BaseTradeProductDealDto> dtoList = restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getFinDto?tradId="+
            entity.getTradeId(),BaseTradeProductDealDto.class);
        
        //删除授信占用
        if(StringUtil.equals(entity.getPs(), DictCodeListExt2.交易方向_认购.getValue()) 
        		|| StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购.getValue())) {
            if (null != entity.getTradeId() && !"".equals(entity.getTradeId())){
                riskCreditListCommonValidatorService.deleteCredit(entity.getTradeId());
            }
        }
        if(StringUtil.equals(DictCodeList.交易方向_赎回确认.getValue(), entity.getPs())) {
            if (null != entity.getTradeId() && !"".equals(entity.getTradeId())){
            	List<String> tradeids = riskCreditListCommonValidatorService.selectTradeidByBTtradid(entity.getTradeId());
            	if (StringUtil.isNotEmpty(tradeids)) {
                	for(String tradeid : tradeids) {
                        riskCreditListCommonValidatorService.deleteCredit(tradeid);
                	}
            	}
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
                  bttob = trdPlanCcyInvestTrade003Service.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
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
        //        委托代码不为空处理委托代码
        finProductTradeDto.getBaseTradeProductDealDtos().forEach(item->{
            String entrustId = StringUtil.isNotEmpty(entity.getEntrustId())?entity.getEntrustId():item.getTrdProductDealAdd()!=null?item.getTrdProductDealAdd().getEntrustId():null;
            if(StringUtil.isEmpty(item.getEntrustId())&&StringUtil.isNotEmpty(entrustId)){
                item.setEntrustId(entrustId);
            }
        });

        // 撤销时，子组合需要调估值共通
        if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                finProductTrade.setActPortfolioId(entity.getActPortfolioId());
            });
        }
        baseTradeProductDealCommService.execute(finProductTradeDto);
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doComplete",
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
        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
            baseTradeProductDealCommService.insertPosition(finProductTradeDto);
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
        }

        // 调用投管接口 add by renping 20190205 start
        if (!(StringUtil.equals(DictCodeList.交易方向_申购.getValue(), entity.getPs())
            || StringUtil.equals(DictCodeList.交易方向_赎回.getValue(), entity.getPs())
            || StringUtil.equals(DictCodeList.交易方向_赎回到账.getValue(), entity.getPs()))) {
            InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
            investInterfCommDto.setPageId("TrdPlanCcyInvestTrade002");
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
                    DictCodeList.共通业务类型_计划类资产_货币型_交易.getValue());
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
    public void revokeAccountDoComplet(TrdPlanCcyInvestTradeDto entity){
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
            List<BaseTradeProductDealDto> dtoList = restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/getFinDto?tradId="+
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
            //        委托代码不为空处理委托代码
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(item->{
                String entrustId = StringUtil.isNotEmpty(entity.getEntrustId())?entity.getEntrustId():item.getTrdProductDealAdd()!=null?item.getTrdProductDealAdd().getEntrustId():null;
                if(StringUtil.isEmpty(item.getEntrustId())&&StringUtil.isNotEmpty(entrustId)){
                    item.setEntrustId(entrustId);
                }
            });

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
    public void doInsert(TrdPlanCcyInvestTradeDto entity, OperationType type){
            entity.setOperateType(type.toString());
            if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_赎回确认.getValue())){
                Map<String, Object> accountTradId = new HashMap<>();
                Map<String, Object> param = new HashMap<>();
                param.put("tradeId", entity.getTradeId());
                param.put("valid", DictCodeListExt1.有效状态_有效.getValue());
                accountTradId = getAccountId(param);
                if(accountTradId == null){
                    accountTradId = selectAccountTrdIdinwfl(entity.getTradeId());
                }
                if(accountTradId!=null){
//                List<BussTrdTransferOrderBase> bttob = new ArrayList<>();
                if(StringUtil.equals((String)accountTradId.get("WORKFLOWID"), "0")){
//                    bttob = TrdPlanCcyInvestTrade003Service.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                }else{
//                    bttob = bussSettleOrderCommService.selectOrdersByBizCode((String)accountTradId.get("TRADID"));
                    TrdPlanCcyInvestTradeDto repAccount = new TrdPlanCcyInvestTradeDto();
                    repAccount.setTradeId((String)accountTradId.get("TRADID"));
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
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doInsert",
                entity, Integer.class);
    }
    
    /**
     * 撤销流程作废
     */
    @Override
    public void doDelete(String workflowId) {
        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/doDelete?workflowId="+
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
    public void revokeAccount(TrdPlanCcyInvestTradeDto entity){
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/revokeAccount",
            entity, void.class);
    }
    
    public boolean selectConfirmInfo(String bTradeId){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectConfirmInfo?bTradeId="+
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
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selRedemption?tradeId="+
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
    public boolean revokeCheck(TrdPlanCcyInvestTradeDto dto){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/revokeCheck",
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
        Map<String, Object> accountTradId = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectAccountTrdId",
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
        Map<String, Object> accountTradId = restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectAccountTrdIdinwfl?tradId="+
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
    public Page<TrdPlanCcyInvestTradeDto> findPage(Map<String, Object> pageParam) {
        pageParam.put("loginUser", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/findPage",
            pageParam,TrdPlanCcyInvestTradeDto.class);
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
        	exportParam.config("/com/joyintech/tams/invest/trade/planccy/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
        }else {
        	exportParam.config("/com/joyintech/tams/invest/trade/planccy/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
        }
        
    }
    @Override
    public Page<TrdPlanCcyInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
    
	public List<TrdPlanCcyInvestTradeDto> selectAllTrade(String tradeId) {
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put("tradeId", tradeId);
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectAllTrade",
	            pageParam,TrdPlanCcyInvestTradeDto.class);
	}

	public boolean isCheckConfirm(String tradeId) {
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put("tradeId", tradeId);
		return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/isCheckConfirm",
	            pageParam,boolean.class);
	}
	
	public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(Map<String, Object> pageParam) {
		setParam(pageParam);
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdPlanCcyInvestTrade/selectTrdTransfenpeiList",
	            pageParam,BussTrdTransfenpeiDto.class);
	}
	
	public void setParam(Map<String, Object> param){
		String tradeDateStr = (String)param.get("tradeDate");
        Date tradeDate = DateUtil.parse(tradeDateStr);

        String dateId = (String)param.get("calendarId");
        String finprodId = (String)param.get("finprodId");
        if (StringUtil.isBlank(dateId) && StringUtil.isNotBlank(finprodId)) {
            dateId = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class,
                param.get("finprodId").toString(), FinProduct.class).getCalendarId();
        }
        List<String> holidayList = dateCalService.getDealCalendarHoliday(tradeDate, DateUtil.addDay(tradeDate, 15), dateId);
        param.put("tradeDateNext", DateUtil.format(dateCalService.calculationTradingDay(tradeDate, 1, holidayList, "1")));
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
