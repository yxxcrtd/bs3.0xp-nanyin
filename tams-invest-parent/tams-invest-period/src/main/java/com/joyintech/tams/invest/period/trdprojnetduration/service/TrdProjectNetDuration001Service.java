package com.joyintech.tams.invest.period.trdprojnetduration.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdprojnetduration.dto.TrdProjectNetDurationDto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.setWorkflowId;

@Service
public class TrdProjectNetDuration001Service extends CommonExportService<TrdProjectNetDurationDto> implements BaseProcessService<TrdProjectNetDurationDto> {
    /**
     * 业务编号
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private TrdProjectNetDurationDealEntity dealEntityService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    @Autowired
    private PeriodCommonService periodCommonService;

    /**
     * 划款指令共通
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    // 调用投管接口 add by renping 20190330 start
    /**
     * 投管接口共通
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;
    // 调用投管接口 add by renping 20190330 end

    /**
     * 获取资产代码 <功能详细描述>
     *
     * @return 资产代码
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        params.put("profitType", DictCodeList.收益类型_净值型.getValue());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/getFinprodMarketId", params,
                SelectOptionDto.class);
    }

    /**
     * 工作流doInsert
     *
     * @param entity 业务entity
     * @param type 操作类型
     */
    @Override
    public void doInsert(TrdProjectNetDurationDto entity, OperationType type) {
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            setWorkflowId(entity.getBaseTotalTradeInfoDto(), entity.getWorkflowId());
        }
        dealEntityService.saveDataWhenStartWorkflow(entity, type, false);
    }

    /**
     * 工作流doUpdate
     *
     * @param entity entity 业务entity
     */
    @Override
    public void doUpdate(TrdProjectNetDurationDto entity) {
        entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
        setWorkflowId(entity.getBaseTotalTradeInfoDto(), entity.getWorkflowId());
        ProcessMark newEntity = null;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdProjectNetDurationDto.class, true);
            // 工作流ID
            newEntity.setWorkflowId(entity.getWorkflowId());
            newEntity.setStatus(entity.getStatus());
            newEntity.setBusinessData(JsonUtils.object2Json(entity));
            // 更新业务表数据
            // 操作留痕表
            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        dealEntityService.saveDataWhenStartWorkflow(entity, OperationType.NEW, false);
    }

    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdProjectNetDurationDto entity, OperationType type) {
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        entity.setRepIssuePartyId(CacheUtil.getSysParam("issue_party_id"));
        // FinProductTradeDto tradeDto = dealEntityService.genFinProductTradeDto(entity);
        // if (OperationType.REVOKE == type) {
        // tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        // entity.setOperateType(BussinessConstant.REVOKE);
        // }
        // else {
        // tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        // }
        // tradeDto.setNeedSum(true);
        // dealEntityService.generateOrder(entity);
        // baseTradeProductDealCommServiceImpl.execute(tradeDto);
        // 调用投管接口 add by renping 20200330 start
        // 撤销时需要查询后续交易用来发送接口
        List<BaseTradeProductDealDto> baseTradeProductDealDtoList = null;
        // 调用投管接口 add by renping 20200330 end
        if (OperationType.REVOKE == type) {
            dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销, false);
            entity.setOperateType(BussinessConstant.REVOKE);
//            // 调用投管接口 add by renping 20200330 start
//            // 撤销时需要查询后续交易用来发送接口
//            baseTradeProductDealDtoList = periodCommonService.findEffectiveDealByBTradeId(
//                    entity.getTotleTradeId().substring(0, entity.getTotleTradeId().length() - 1));
//            // 调用投管接口 add by renping 20200330 end
        }
        else {
            dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_有效, false);
        }
        // 插入Entity对应的业务流程DB中, 并删除流程表中的数据
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/doComplete", entity,
                String.class);

        // 调用投管接口 add by renping 20200330 start
        DictCodeListExt1 interfStatus = DictCodeListExt1.交易状态_初始;
        if (OperationType.REVOKE == type) {
            interfStatus = DictCodeListExt1.交易状态_撤销;
        }
//        investInterfCommService.doSendTradeToInterf(entity.getTotleTradeId(),DictCodeListExt3.共通业务类型_权益类资产存续期.getValue());

//        for (BaseTradeInfoDto baseTradeInfoDto : entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList()) {
//            List<BaseTradeInfoDto> subBaseTradeInfoDtoList = baseTradeInfoDto.getBaseTradeInfoDtoList();
//            if (StringUtil.isEmptyList(subBaseTradeInfoDtoList)) {
//                getInterfInputDto(entity, baseTradeInfoDto, interfStatus);
//            }
//            else {
//                for (BaseTradeInfoDto subBaseTradeInfoDto : subBaseTradeInfoDtoList) {
//                    getInterfInputDto(entity, subBaseTradeInfoDto, interfStatus);
//                }
//            }
//        }
//        // 有后续交易撤销时
//        if (OperationType.REVOKE == type) {
//            if (!StringUtil.isEmptyList(baseTradeProductDealDtoList)) {
//                for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtoList) {
//                    investInterfCommService.doSendTradeToInterf(baseTradeProductDealDto.getTrdProductDeal().getTotleTradeId()+"R",DictCodeListExt3.共通业务类型_权益类资产存续期.getValue());
//                }
//            }
//        }
//        // 调用投管接口 add by renping 20200330 end

        // 除权登记生成总集复核数据
        if (OperationType.REVOKE != type && StringUtil.equals(entity.getType(), "02")) {
            // 设置工作流代码
            entity.setWorkflowId(null);
            if (StringUtil.equals(entity.getBonusMode(), DictCodeList.分红方式_现金分红.getValue())) {
                // 现金分红
                entity.setType("03");
                //update by zhangnian 20200720 start
                //分红到账
                doCompleteDividendsReceived(entity);
                //update by zhangnian 20200720 end
                // getWorkFlowPatameter(entity);
                //entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
                //dealEntityService.saveDataWhenStartWorkflow(entity, OperationType.NEW, true);
            }
            else {
                // 红利再投
                entity.setType("04");
                entity.setElementId("TrdProjectNetDuration002_btn_save");
                BigDecimal unit = periodCommonService.getLatestValuation(entity.getFinprodId(),
                        DateUtil.format(entity.getExRightsDate()));
                entity.setQty(ArithmeticUtil.divide(entity.getTradeAmt(), unit == null ? BigDecimal.ONE : unit, 4));
                WorkFlowParameter<TrdProjectNetDurationDto> param = getWorkFlowPatameter(entity);
                periodCommonService.autoDealQtyForSubmit(entity.getFinAssetIntInfoList(), entity.getRegistShare(),
                        entity.getQty());
                entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
                try {
                    // 启动流程
                    WorkFlowUtil.startProcess(param,"","", entity.getCreateUser(),OperationType.NEW,false,entity);
                    // 设置为提交 插入流程表
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 调用投管接口 add by renping 20190330 start
    /**
     * 存续期调用接口
     *
     * @param entity 画面DTO
     * @param baseTradeInfoDto 参数
     * @param 交易状态 交易状态
     * @see
     * @since 1.0
     */
    private void getInterfInputDto(TrdProjectNetDurationDto entity, BaseTradeInfoDto baseTradeInfoDto,
                                   DictCodeListExt1 交易状态) {
        if (baseTradeInfoDto.getTrdProductDeal() != null) {
            InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
            investInterfCommDto.setPageId("TrdProjectNetDuration002");
            investInterfCommDto.setTradeStatus(交易状态.getValue());
            investInterfCommDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
            investInterfCommDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
            investInterfCommDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
            PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
            // 计划类净值型分红除权
            if (StringUtil.equals(entity.getType(), "02")) {
                //paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_计划类资产_净值型_分红除权.getValue());
            }
            // 计划类净值型现金分红
            else if (StringUtil.equals(entity.getType(), "03")) {
                paTrdProductDeal.setPaBussType(DictCodeListExt3.平安业务类型_权益类资产_现金分红.getValue());
            }
            // 计划类净值型再投
            /*else if (StringUtil.equals(entity.getType(), "04")) {
                paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_计划类资产_净值型_红利再投.getValue());
            }*/
            investInterfCommDto.setPaTrdProductDeal(paTrdProductDeal);
            investInterfCommDto.setOtherTypeForConvert(
                    DictCodeListExt3.共通业务类型_权益类资产存续期.getValue());
            TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
            trdFinProductDealTotal.setTotleTradeId(
                    baseTradeInfoDto.getTrdProductDeal().getTotleTradeId());
            trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
            investInterfCommDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
            investInterfCommService.doInvestInterf(investInterfCommDto);
        }
    }
    // 调用投管接口 add by renping 20200330 end

    /**
     * 设置流程所需parameter
     *
     * @param dto 流程dto
     * @return 构建的dto
     */
    public WorkFlowParameter<TrdProjectNetDurationDto> getWorkFlowPatameter(TrdProjectNetDurationDto dto) {
        WorkFlowParameter<TrdProjectNetDurationDto> parameter = new WorkFlowParameter<>();
        parameter.setPageId("TrdProjectNetDuration002");
        parameter.setElementId(dto.getElementId());
        parameter.setServiceName(WorkFlowUtil.getServiceName(this.getClass()));
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        // 为非货币现金分红及非货币红利再投时 设置前置交易编号
        if (StringUtil.equals(dto.getType(), "03") || StringUtil.equals(dto.getType(), "04")) {
            dto.setbTradeId(dto.getTotleTradeId());
        }
        dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        parameter.setBusinessType(DictCodeListExt3.共通业务类型_权益类资产存续期.getValue());
        parameter.setBusinessCode(dto.getTotleTradeId());
        // 设置流程业务说明, entity即传入的dto
        parameter.setComment(entity -> WorkFlowUtil.concatComment(this.getMessage("TrdDepoDuration001.finprodName"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName() + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdPlanNetDuration001.assetType"),
                GlobalConstant.OPEN_PARENTHESIS + DictCodeListExt3.平安资产类型_权益类资产.getCodeName() + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("TrdPlanNetDuration001.tradeId"),
                GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS));
        return parameter;
    }

    /**
     * 获取组合代码 组合代码获取功能
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        if (map.get("initValue") == null) {
            map.put("userId", LoginUserUtil.getUserName());
        }
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/getPortfolioId", map,
                SelectOptionDto.class);
    }

    /**
     * 画面查询 <功能详细描述>
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdProjectNetDurationDto> findPage(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/findPage", pageParam,
                TrdProjectNetDurationDto.class);
    }

    /**
     * @Description: 构建导出参数
     * @param: [selectParam 参数]
     * @return:
     * @author: 丁志鹏
     * @Date: 2018/11/23
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdprojnetduration/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdProjectNetDurationDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 资产代码联动查询 <功能详细描述>
     *
     * @param pageParam 画面的条件
     * @return 画面查询结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> finprodMarketIdLink(Map<String, Object> pageParam) {
        pageParam.put("FINPRODTYPE2_F06", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/finprodMarketIdLink",
                pageParam, Map.class);
    }

    /**
     * 组合代码联动查询 <功能详细描述>
     *
     * @param pageParam 画面的条件
     * @return 画面查询结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> portfolioIdLink(Map<String, Object> pageParam) {
        pageParam.put("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/portfolioIdLink",
                pageParam, Map.class);
    }

    /**
     * 登记份额查询 <功能详细描述>
     *
     * @param pageParam 画面的条件
     * @return 登记份额
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> calRegistShare(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/calRegistShare",
                pageParam, Map.class);
    }

    /**
     * 获取有效或者撤销的数据
     *
     * @param param
     * @return
     */
    public TrdProjectNetDurationDto findEffectiveData(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/findEffectiveData", param,
                TrdProjectNetDurationDto.class);
    }

    /**
     * 获取现金分红流程中
     *
     * @param param
     * @return
     */
    public TrdProjectNetDurationDto findCashDividend(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/findCashDividend", param,
                TrdProjectNetDurationDto.class);
    }

    /**
     * 统计是否有后续交易
     *
     * @param map 参数
     * @return 统计数
     */
    public int countFollowDeal(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/countFollowDeal", map,
                Integer.class);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"registDate") || StringUtil.equals(propName,"exRightsDate")
                || StringUtil.equals(propName,"arriveDate") || StringUtil.equals(propName,"reInvesDate")){
            if(value instanceof Date){
                cell.setCellValue(DateUtil.format((Date)value));
            }else if (value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }

    /**
     * 获取利息明细信息 <功能详细描述>
     *
     * @param pageParam 界面参数
     * @return 查询到的结果
     * @see
     * @since 1.0
     */
    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        List<FinAssetIntInfo> finAssetIntInfos = restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/findAssetIntInfo", pageParam, FinAssetIntInfo.class);
        return finAssetIntInfos;
    }

    /**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * 汇总费用明细 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 费用明细 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdProjectNetDurationDto entity) {
        BaseTotalTradeInfoDto result = dealEntityService.genBaseTotalTradeInfoDto(entity);
        return result;
    }

    /**
     * 根据交易编号删除流程表中数据 <功能详细描述>
     *
     * @param tradeId 交易代码
     * @see
     * @since 1.0
     */
    public int deleteWflByTradeId(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundDuration/deleteWflByTradeId", tradeId,
                Integer.class);
    }

    @Override
    public void doBack(TrdProjectNetDurationDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTotleTradeId());
    }

    @Override
    public void doRevoke(TrdProjectNetDurationDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTotleTradeId());
    }

    /**
     * 分红到账
     * @param entity
     */
    public void doCompleteDividendsReceived(TrdProjectNetDurationDto entity){
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        // 为非货币现金分红及非货币红利再投时 设置前置交易编号
        if (StringUtil.equals(entity.getType(), "03") || StringUtil.equals(entity.getType(), "04")) {
            entity.setbTradeId(entity.getTotleTradeId());
        }
        entity.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
        doComplete(entity,OperationType.NEW);
    }

    public Page<SelectOptionDto> getBusiCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("FINPROD_TYPE_TGF01", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdProjectNetDuration/getBusiCode",
                param, SelectOptionDto.class);
    }
}
