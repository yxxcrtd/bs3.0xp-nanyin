/*
 * 文件名：PtlFundAllocation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlfundallocation.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PtlCashPositionFlowMapper;
import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资金划拨Service
 * @author fanshuanghu
 * @version 1.0 2019年1月29日
 * @see PtlFundAllocation001Service
 * @since 1.0
 */
@Service("PtlFundAllocation001Service")
@Transactional(rollbackFor=Exception.class)
public class PtlFundAllocation001Service extends CommonExportService<PtlFundAllocationDto>
    implements BaseProcessService<PtlFundAllocationDto>{
    /**
     * 业务主键生成类
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBussinessId;
    /**
     * 基础Mapper代理Service
     */
    @Autowired(required=false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * 查询组合
     * @return 组合集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioList(Map<String,Object> map){
        User user = SecurityUtil.getLoginUser();
        map.put("userId", user.getUserName());
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/getPortfolioList", map, SelectOptionDto.class);
    }

    /**
     * 获取账号
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAcct(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/getAcct", map, SuggestDto.class);
    }

    /**
     * 分页查询
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlFundAllocationDto> findPage(Map<String,Object> map){
        if(map.get("portfolioId") != null && StringUtil.isNotEmpty((String)map.get("portfolioId"))){
            map.replace("portfolioId", Arrays.asList(map.get("portfolioId").toString().split(",")));
        }else {
            map.replace("portfolioId", null);
        }
        if(map.get("dataDateStart") == null || StringUtil.isEmpty((String)map.get("dataDateStart"))){
            map.replace("dataDateStart", null);
        }
//      String[] psArr = {DictCodeListExt1.指令类型_银证转账_证转银.getValue(),DictCodeListExt1.指令类型_银证转账_银转证.getValue()};
        String[] psArr = {DictCodeList.交易方向_银证转账.getValue(),
            DictCodeList.交易方向_证银转账.getValue()};
        List<String> ps = Arrays.asList(psArr);
        map.put("ps", ps);

        // 划款类型转交易方向
        String payType = (String)map.get("payType");
        if (StringUtil.equals(DictCodeListExt1.指令类型_银证转账_银转证.getValue(), payType)) {
            payType = DictCodeList.交易方向_银证转账.getValue();
        }
        else if (StringUtil.equals(DictCodeListExt1.指令类型_银证转账_证转银.getValue(), payType)) {
            payType = DictCodeList.交易方向_证银转账.getValue();
        }
        map.put("payType", payType);

        map.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());

        map.put("userId", LoginUserUtil.getUserName());

        // 有效状态转交易状态
        String dataStatus = (String) map.get("dataStatus");
        if (StringUtil.equals(DictCodeListExt1.画面数据状态_有效.getValue(), dataStatus)) {
            map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        }
        else if (StringUtil.equals(DictCodeListExt1.画面数据状态_撤销.getValue(), dataStatus)) {
            map.put("tradeStatus", DictCodeListExt1.交易状态_撤销.getValue());
        }

        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/findPage", map, PtlFundAllocationDto.class);
    }

    public PtlFundAllocationDto setEntity(PtlFundAllocationDto entity, OperationType type){

        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(entity.getPortfolioId());
        ptlTrade.setBusiNo(entity.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_结算指令表.getValue());
        entity.setPtlTrade(ptlTrade);

        PtlCashPositionFlow ptlCashPositionFlow = new PtlCashPositionFlow();
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setSeqNo("9999");
        String seqno = baseGetBussinessId !=null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate();
        ptlCashPositionFlow.setSeqno(seqno);
        ptlCashPositionFlow.setPortfolioId(entity.getPortfolioId());
        ptlCashPositionFlow.setAcctId(entity.getMyAcctNo());
        ptlCashPositionFlow.setHodingType(DictCodeList.持仓类型_可用.getValue());
        ptlCashPositionFlow.setAmount(entity.getSettleAmt());
//        if (DictCodeListExt1.收支方向_支出.getValue().equals(entity.getPs())) {
        ptlCashPositionFlow.setAmount(ArithmeticUtil.multiply(entity.getSettleAmt(),new BigDecimal("-1")));
//        }
        if (OperationType.REVOKE == type || OperationType.DELETE == type) {
            ptlCashPositionFlow.setAmount(ArithmeticUtil.multiply(ptlCashPositionFlow.getAmount(),new BigDecimal("-1")));
        }
        ptlCashPositionFlow.setOrderId(entity.getOrderId());
        ptlCashPositionFlow.setCdate(entity.getSettleDate());
        ptlCashPositionFlow.setCcy(entity.getCcy());
        entity.setPtlCashPositionFlow(ptlCashPositionFlow);


        PtlCashPosition ptlCashPosition = new PtlCashPosition();
        ptlCashPosition.setPortfolioId(entity.getPortfolioId());
        ptlCashPosition.setAcctId(entity.getMyAcctNo());
        ptlCashPosition.setCcy(entity.getCcy());
        ptlCashPosition.setCdate(entity.getSettleDate());
        ptlCashPosition.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlCashPosition.setAmount(ArithmeticUtil.multiply(entity.getSettleAmt(),new BigDecimal("-1")));
        entity.setPtlCashPosition(ptlCashPosition);

        entity.setTradeId(entity.getTradeId());

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlFundAllocationDto entity, OperationType type) {
        // TODO Auto-generated method stub
        this.setEntity(entity, type);
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/doComplete", entity, Integer.class);
    }

    @Override
    public void doDelete(String workflowId) {
        PtlFundAllocationDto entity = this.getBussinessEntityByWorkflowId(workflowId);
        if (StringUtil.isNotEmpty(entity.getOrderId())) {
            restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/doDelete", entity.getOrderId(), Integer.class);
        }
    }

    @Override
    public void doInsert(PtlFundAllocationDto entity, OperationType type) {
        entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        this.setEntity(entity, type);
        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/doInsert", entity, String.class);
    }

    @Override
    public void doUpdate(PtlFundAllocationDto entity){
        if(bussCommonMapperDelegateService != null){
            restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlFundAllocation/doDelete", entity.getOrderId(), Integer.class);
            doInsert(entity, OperationType.NEW);
        }
    }


    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlfundallocation/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlFundAllocationDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 设置单元格值
     * <p>
     * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
     * </p>
     *
     * @param beanName sheet对应的JavaBean
     * @param cell 单元格
     * @param propName 属性名
     * @param value 单元格的值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if("settleDate".equals(propName)){
            cell.setCellValue(DateUtil.format((Date)value, "yyyy-MM-dd"));
        }
    }
}
