/*
 * 文件名：TrdConBond001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondRevokeDto;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class TrdConBond001Service extends CommonExportService<TrdConBondDto> implements BaseProcessService<TrdConBondDto> {

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
    
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    
    
    @Autowired(required = false)
    private TrdConBond003Service trdConBond003Service;
    
    
    @Autowired(required = false)
    private TrdConBond002Service trdConBond002Service;
    
    /**
     * 投资委托信息Service
     */
    @Autowired
    private BaseEtrInvestInfoServiceImpl etrInvestInfoServiceImpl;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdConBondDto> findPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }

        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        Page<TrdConBondDto> pageDto =restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/findPage",
                pageParam,TrdConBondDto.class);
        List<TrdConBondDto> listDto = pageDto.getRoot();
        for (TrdConBondDto tfitDto : listDto) {
            if (StringUtil.equals(tfitDto.getPs(), DictCodeList.交易方向_申购.getValue())
                    && StringUtil.equals(tfitDto.getValidStatus(), DictCodeListExt1.交易流程状态_有效.getValue())) {
            	WflValidInputParameter parameter = new WflValidInputParameter();
        		parameter.setPageId("TrdConBond003");
        		parameter.setBusinessDataPk2(tfitDto.getBusiCode());
        		if (mstWorkflowCommonService.isInWflProcess(parameter)) {
        			tfitDto.setFlg("02");
        		}
            }
        }
		
        
        return pageDto;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/conbond/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdConBondDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
    
    public List<StlPayOrders> getStlPayOrders(String busiCode) {
    	Map<String,Object>map = new HashMap<>();
        map.put("busiCode", busiCode);
    	return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/getStlPayOrders",
                map, StlPayOrders.class);
    }

    /**
     * 撤销审批完成操作
     */
    @Override
    public void doComplete(TrdConBondDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        
        
        Map<String,Object>map = new HashMap<>();
        map.put("busiCode", entity.getBusiCode());
        List<StlPayOrders> result = restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/getStlPayOrders",
                map, StlPayOrders.class);
        StlPayOrdersDto stlPayOrders = null;
        if (!StringUtil.isEmptyList(result)) {
            stlPayOrders = new StlPayOrdersDto();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders,
            		result.get(0));
        }
        //交易方向为申购确认
        if(StringUtil.equals(entity.getPs(), DictCodeList.交易方向_申购确认.getValue())){
        	TrdConBond003Dto dto = new TrdConBond003Dto();
        	BeanUtil.copyPropertiesIgnoreNull(dto, entity);
        	finProductTradeDto= trdConBond003Service.genFinProductTradeDto(dto, type, stlPayOrders);
        }else{
            BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            trdConBond002Service.prepareEntity(entity);
            stlPayOrders = new StlPayOrdersDto();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders,
                    entity.getBussTrdTransferOrder().get(0));
            baseTradeProductDealDto.setTrdProductDeal(entity.getTrdProductDeal()); // 金融产品交易
            baseTradeProductDealDto.setTrdProductDealAdd(entity.getTrdProductDealAdd()); // 金融产品附属
            baseTradeProductDealDto.setTrdFinProductDealTotal(entity.getTrdFinProductDealTotal()); // 汇总交易表 更新委托时获取真实交易方向
            // 划款指令
            if (stlPayOrders != null) {
                baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(stlPayOrders));
            }
            finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));
            //        委托代码不为空处理委托代码
            if(StringUtil.isNotEmpty(entity.getEntrustId())){
                baseTradeProductDealDto.setEntrustId(entity.getEntrustId());
                etrInvestInfoServiceImpl.updateDelegateStatus(entity.getEntrustId(),
                        DictCodeList.委托状态_已接收.getValue());
            }
        }
        finProductTradeDto.setNeedSum(true);
        finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        //basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getBusiCode()+"R");

        if (entity.getActPortfolioId() != null && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(finProductTrade -> {
                finProductTrade.setActPortfolioId(entity.getActPortfolioId());
            });
        }
        baseTradeProductDealCommService.execute(finProductTradeDto);
        entity.setUpdateUser(finProductTradeDto.getUpdateUser());
        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !entity.getActPortfolioId().equals(entity.getPortfolioId())) {
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
            baseTradeProductDealCommService.insertPosition(finProductTradeDto);
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
        }
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/doComplete",
                entity,Integer.class);
    }
    /**
     * 
     * 撤销流程启动时操作
     */
    @Override
    public void doInsert(TrdConBondDto entity, OperationType type){
        entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/doInsert",
            entity, Integer.class);
    }
    
    /**
     * 撤销流程作废
     */
    @Override
    public void doDelete(String workflowId) {
        restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/doDelete?workflowId="+
            workflowId, Integer.class);
    }
    
    /**
     * 
     * 检验撤回数据是否有后继交易
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public boolean revokeCheck(TrdConBondRevokeDto dto){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/revokeCheck",
            dto,boolean.class);
    }
    
    /**
     * 
     * 检验撤回数据是否有撤销流程中数据
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public boolean revokeCheckWfl(TrdConBondRevokeDto dto){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdConBond/revokeCheckWfl",
            dto,boolean.class);
    }
}
