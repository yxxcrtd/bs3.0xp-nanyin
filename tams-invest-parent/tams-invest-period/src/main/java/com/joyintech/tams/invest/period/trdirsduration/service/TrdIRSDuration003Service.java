package com.joyintech.tams.invest.period.trdirsduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.irs.AutoPeriodIRSCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdirsduration.dto.TrdIRSDuration003ProcessDto;
import com.joyintech.tams.valuation.api.constant.BussinessJixuConstant;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.CommentFunction;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.setWorkflowId;

/**
 * 利率互换提前终止
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.service
 * @Description: 利率互换存续期管理-查询
 * @date Date : 2019年08月10日
 */
@Service
public class TrdIRSDuration003Service extends BaseService<TrdIRSDuration003ProcessDto> implements BaseProcessService<TrdIRSDuration003ProcessDto> {

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    /**
     * AutoPeriodIRSCommonService
     */
    @Autowired
    private AutoPeriodIRSCommonService autoPeriodIRSCommonService;

    @Autowired
    private PeriodCommonService periodCommonService;

    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     * 估值接口
     */
    @Autowired
    private ValuationInterfService valuationInterfService;

    /**
     * 查看/确认画面初始数据查询
     * 
     * @param pageParam 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public TrdIRSDuration003ProcessDto findEffectiveData(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        if (pageParam.get("statusValid") != null) {
            pageParam.put("statusValid",
                StringUtil.splitToList(",", (String)pageParam.get("statusValid")));
        }
        else {
            pageParam.put("statusValid", new ArrayList<>());
        }
        // 组合代码
        if (pageParam.get("portfolioId") != null) {
            pageParam.put("portfolioId", StringUtil.splitToList(GlobalConstant.COMMA,
                (String)pageParam.get("portfolioId")));
        }
        return restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/findEffectiveData",
            pageParam, TrdIRSDuration003ProcessDto.class);
    }

    /**
     * 根据金融产品代码查询利率互换交易信息
     * 
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public TrdIRSDuration003ProcessDto findIrsDealInfo(String tradeId, Date platDate) {
        if (StringUtil.isEmpty(tradeId)) {
            return new TrdIRSDuration003ProcessDto();
        }
        TrdIRSDuration003ProcessDto dealDto = restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdIRSTrade/getTrdIRSTradeDto/" + tradeId
                                                                         + "/"
                                                                         + CacheUtil.getSysParam(
                                                                             BussinessConstant.NOBRANCHNOVALUE),
            null, TrdIRSDuration003ProcessDto.class);
        if (dealDto != null) {
            if (dealDto.getTrdInvestPersonInfo() != null) {
                dealDto.setCounterId(dealDto.getTrdInvestPersonInfo().getCounterId());
            }
            dealDto.setFinprodId(tradeId);
            PtlSecPositionFlowDto dto = new PtlSecPositionFlowDto();
            dto.setPortfolioId(dealDto.getPortfolioId());
            dto.setFinprodId(tradeId);
            dto.setBranch(Long.valueOf(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
            dto.setCdate(platDate);
            BigDecimal position = restTemplate.postForObject(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/getAssetAvailablePosition",
                dto, BigDecimal.class);
            dealDto.setRestPrin(ArithmeticUtil.divide(position, new BigDecimal(10000), 4));
        }
        else {
            return new TrdIRSDuration003ProcessDto();
        }
        return dealDto;
    }

    /**
     * 工作流完成回调方法
     *
     * @param entity 业务实体
     * @param operationType 操作类型
     */
    @Override
    public void doComplete(TrdIRSDuration003ProcessDto entity, OperationType operationType) {
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        if (OperationType.REVOKE == operationType) {
            execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销);
            entity.setOperateType(BussinessConstant.REVOKE);
        }
        else {
            execute交易共通(entity, DictCodeListExt1.交易流程状态_有效);
        }
        // 子组合转母组合
        if (doPortfolioIdChange(entity)) {
            if (OperationType.REVOKE == operationType) {
                execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销);
            } else {
                execute交易共通(entity, DictCodeListExt1.交易流程状态_有效);
            }
        }
        // 新建交易撤销数据 或 插入Entity到对应的业务DB中, 并删除流程表中的数据
        restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/doComplete", entity,
            String.class);
        // jixu接口 start
        TrdCommDto trdCommDto = new TrdCommDto();
        if (OperationType.REVOKE == operationType) {
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_DELETE);;
        }
        else if (OperationType.NEW == operationType) {
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_ADD);
        }
        else {
            trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_UPDATE);
        }
        BaseTotalTradeInfoDto baseTotalTradeInfoDto = entity.getBaseTotalTradeInfoDto();
        trdCommDto.setHeader(BussinessJixuConstant.消息头_利率互换付息交易);
        trdCommDto.setTrdFinProductDealTotal(baseTotalTradeInfoDto.getTrdFinProductDealTotal());
        trdCommDto.setTrdProductDeal(baseTotalTradeInfoDto.getBaseTradeInfoDtoList().get(0).getTrdProductDeal());
        trdCommDto.setPtlTradeList(baseTotalTradeInfoDto.getBaseTradeInfoDtoList().get(0).getPtlTrade());
        List<StlPayOrders> stlPayOrdersList = new ArrayList<>();
        baseTotalTradeInfoDto.getBaseTradeInfoDtoList().forEach(baseDto->{
            stlPayOrdersList.add(baseDto.getStlPayOrders());
        });
        trdCommDto.setStlPayOrdersList(stlPayOrdersList);
        valuationInterfService.runValInvest2Jixu(trdCommDto);
        // jixu接口 end
    }

    /**
     * 子组合转为母组合
     * 
     * @param entity 参数
     * @see
     * @since 1.0
     */
    private boolean doPortfolioIdChange(TrdIRSDuration003ProcessDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = entity.getBaseTotalTradeInfoDto().getTrdFinProductDealTotal();
        String portfolioId = AllTradeCommonUtil.exchangePortfolio(trdFinProductDealTotal.getPortfolioId());
        if(StringUtil.isEmpty(portfolioId) || StringUtil.equals(portfolioId, trdFinProductDealTotal.getPortfolioId())){
            return false;
        }
        trdFinProductDealTotal.setPortfolioId(portfolioId);
        entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseDto -> {
            baseDto.getTrdProductDealAdd().setPortfolioId(AllTradeCommonUtil.exchangePortfolio(
                baseDto.getTrdProductDealAdd().getPortfolioId()));
            PtlTrade ptlTrade = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade, baseDto.getPtlTrade().get(0));
            if (!StringUtil.equals(ptlTrade.getPortfolioId(),
                AllTradeCommonUtil.exchangePortfolio(ptlTrade.getPortfolioId()))) {
                ptlTrade.setPortfolioId(
                    AllTradeCommonUtil.exchangePortfolio(ptlTrade.getPortfolioId()));
                baseDto.getPtlTrade().add(ptlTrade);
            }
        });
        return true;
    }

    @Override
    public void doInsert(TrdIRSDuration003ProcessDto entity, OperationType type) {
        entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        entity.setCreateDept(SecurityUtil.getLoginUser().getDeptCode());
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            setWorkflowId(entity.getBaseTotalTradeInfoDto(), entity.getWorkflowId());
        }
        saveDataWhenStartWorkflow(entity, type);
    }

    @Override
    public void doUpdate(TrdIRSDuration003ProcessDto entity) {
//        baseTradeProductDealCommServiceImpl.delete(entity.getWorkflowId());
        // 2.0改造
        entity.setBaseTotalTradeInfoDto(genFinProductTradeDto2(entity));
        ProcessMark newEntity;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(
                TrdIRSDuration003ProcessDto.class, true);
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
        saveDataWhenStartWorkflow(entity, OperationType.MODIFY);
    }

    private void saveDataWhenStartWorkflow(TrdIRSDuration003ProcessDto entity,
                                           OperationType type) {
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())
            && type != OperationType.REVOKE) {
            // 插入Entity到对应的业务流程DB中
            if (entity.getNeedSkipInsertWfl() == null || !entity.getNeedSkipInsertWfl()) {
                restTemplate.postForObject(
                    ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/doInsert",
                    entity, String.class);
            }
            if (type != OperationType.REVOKE) {
                execute交易共通(entity, DictCodeListExt1.交易流程状态_流程中);
            }
        }
        else if (type == OperationType.REVOKE) {
            restTemplate.postForObject(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/doRevoke", entity,
                String.class);
        }
    }

    /**
     * 批量调用共通 <功能详细描述>
     * 
     * @param entity 业务实体
     * @param 交易流程状态
     * @see
     * @since 1.0
     */
    private void execute交易共通(TrdIRSDuration003ProcessDto entity, DictCodeListExt1 交易流程状态) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());

        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(
                baseTradeInfoDto -> {
                    TrdProductDeal deal = baseTradeInfoDto.getTrdProductDeal();
                    if (deal != null) {
                        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
                        baseTradeProductDealDto.setTrdProductDeal(deal);
                        baseTradeProductDealDto.setTrdProductDealAdd(
                            baseTradeInfoDto.getTrdProductDealAdd());
                        StlPayOrdersDto orders = new StlPayOrdersDto();
                        BeanUtils.copyProperties(baseTradeInfoDto.getStlPayOrders(), orders);
                        baseTradeProductDealDto.setStlPayOrdersList(
                            Arrays.asList(new StlPayOrdersDto[] {orders}));
                        baseTradeProductDealDtos.add(baseTradeProductDealDto);
                        getInterfInputDto(entity, 交易流程状态, baseTradeProductDealDto);
                    }
                });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        else {
            // 查询交易相关表数据，用于撤销
            List<BaseTradeProductDealDto> baseTradeProductDealDtos1 = periodCommonService.findEffectiveDeal(
                entity.getBusiCode(), 交易流程状态);
            for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtos1) {
                TrdProductDeal deal1 = baseTradeProductDealDto.getTrdProductDeal();
                deal1.setTradeId(deal1.getTradeId() + 'R');
                deal1.setCreateUser(tradeDto.getCreateUser());
                deal1.setCreateTime(tradeDto.getCreateTime());
                deal1.setCreateDept(tradeDto.getCreateDept());
                deal1.setUpdateTime(tradeDto.getUpdateTime());
                deal1.setUpdateUser(tradeDto.getUpdateUser());
                getInterfInputDto(entity, 交易流程状态, baseTradeProductDealDto);
            }
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos1);
        }
        baseTradeProductDealCommServiceImpl.execute(tradeDto);
    }

    public void getInterfInputDto(TrdIRSDuration003ProcessDto entity, DictCodeListExt1 交易流程状态,
                                  BaseTradeProductDealDto baseTradeProductDealDto) {
        if (baseTradeProductDealDto.getTrdProductDealAdd() == null
                || StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())
                || AllTradeCommonUtil.isNotActPortfolio(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())) {
            return;
        }
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("TrdIRSDuration002");
        if (DictCodeListExt1.交易流程状态_撤销 == 交易流程状态) {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        }
        else {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
        }
        investInterfCommDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        if (StringUtil.isNotEmpty(entity.getBeforeEndPs())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt2.平安业务类型_利率互换提前终止.getValue());;
        }
        else if (entity.getMdate() != null
                && ((entity.getFixMdate() != null
                && entity.getMdate().equals(entity.getFixMdate()))
                || (entity.getFloatMdate() != null
                && entity.getMdate().equals(entity.getFloatMdate())))) {
            paTrdProductDeal.setPaBussType(DictCodeListExt2.平安业务类型_利率互换到期.getValue());
        }
        else if (entity.getMdate() != null
                && (entity.getFixMdate() != null || entity.getFloatMdate() != null)) {
            // 中间收付息时，根据收付息类型到接口判断交易类型
            paTrdProductDeal.setPaBussType(entity.getIrsPsIntType());
        }
        // 名义本金没地方存，设置到理论金额中
        paTrdProductDeal.setTheoryAmt(entity.getPrin());
        // 名义币种没地方存，设置到分支中，要是以后改变类型，换个属性存，记得要改SomdIrsDurService类
        paTrdProductDeal.setBranch(entity.getCcy());
        investInterfCommDto.setPaTrdProductDeal(paTrdProductDeal);
        if (baseTradeProductDealDto.getStlPayOrdersList() != null && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
            investInterfCommDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList().get(0));
        }
        investInterfCommService.doInvestInterf(investInterfCommDto);
    }

    /**
     * 2.0划款指令复制 <功能详细描述>
     * 
     * @param stlPayOrdersList 划款指令
     * @param entity 业务实体
     * @return 转换的划款指令
     * @see
     * @since 1.0
     */
    private StlPayOrders gen划款指令流程2(List<BussTrdTransferOrderDto> stlPayOrdersList,
                                    TrdProductDeal deal, TrdIRSDuration003ProcessDto entity) {
        StlPayOrders stlPayOrders = null;
        for (BussTrdTransferOrderDto dto : stlPayOrdersList) {
            if ((StringUtil.equals(deal.getPs(), DictCodeList.交易方向_收取.getValue())
                 && StringUtil.equals(dto.getPs(), DictCodeList.收付方向_收入.getValue()))
                || (StringUtil.equals(deal.getPs(), DictCodeList.交易方向_支付.getValue())
                    && StringUtil.equals(dto.getPs(), DictCodeListExt1.收付方向_支出.getValue()))) {
                stlPayOrders = dto;
                stlPayOrders.setOrderStatus(DictCodeList.清算操作类型_新建.getValue());
                stlPayOrders.setTradeId(deal.getTradeId());
                stlPayOrders.setStatus(DictCodeList.数据状态_新建.getValue());
                stlPayOrders.setWorkflowId(entity.getWorkflowId());
            }
        }
        return stlPayOrders;
    }

    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommServiceImpl.delete(workflowId);
        TrdIRSDuration003ProcessDto entity = getBussinessEntityByWorkflowId(workflowId);
        TrdCommonDeleteDto trdCommonDeleteDto = new TrdCommonDeleteDto();
        trdCommonDeleteDto.setWorkflowId(workflowId);
        restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdIRSDuration/doDelete", entity,
            String.class);
    }

    /**
     * 生成业务说明
     * 
     * @return 生成业务说明方法
     * @see
     * @since 1.0
     */
    public CommentFunction<TrdIRSDuration003ProcessDto, String> 生成业务说明() {
        return entity -> WorkFlowUtil.concatComment("交易编号：" + entity.getBusiCode() + "，交易日期："
                                                    + DateUtil.format(entity.getBeforeEndDate()));
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
    public BaseTotalTradeInfoDto genFinProductTradeDto2(TrdIRSDuration003ProcessDto entity) {

        BaseTotalTradeInfoDto result = autoPeriodIRSCommonService.genFinProductTradeDtoBeforeEnd(entity);

        // 处理划款指令
        List<BaseTradeInfoDto> baseTradeInfoDtos = result.getBaseTradeInfoDtoList();
        if (!StringUtil.isEmptyList(baseTradeInfoDtos)) {
            List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
                entity.getBussTrdTransferOrder());
            for (BaseTradeInfoDto baseTradeInfoDto : baseTradeInfoDtos) {
                baseTradeInfoDto.setStlPayOrders(
                    gen划款指令流程2(stlPayOrdersList, baseTradeInfoDto.getTrdProductDeal(), entity));
            }
        }
        return result;
    }
}
