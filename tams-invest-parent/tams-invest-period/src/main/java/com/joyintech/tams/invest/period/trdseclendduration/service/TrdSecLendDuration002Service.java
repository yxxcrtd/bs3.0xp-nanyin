package com.joyintech.tams.invest.period.trdseclendduration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.seclend.AutoPeriodSeclendCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdseclendduration.dto.TrdSecLendDuration002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author : 裴家俊
 * @version V1.0
 * @Project: tams-invest-parent
 * @Description: 债券借贷存续期管理-查询
 * @date 2019年07月1日
 */
@Service
public class TrdSecLendDuration002Service extends BaseService<TrdSecLendDuration002Dto> implements BaseProcessService<TrdSecLendDuration002Dto> {

    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflTrdDealTotalAddMapper wflTrdDealTotalAddMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     * AutoPeriodRepoCommonService
     */
    @Autowired
    private AutoPeriodSeclendCommonService autoPeriodSeclendCommonService;

    public TrdSecLendDuration002Dto findEffectiveData(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/findEffectiveData",
            pageParam, TrdSecLendDuration002Dto.class);
    }

    @Override
    public void doComplete(TrdSecLendDuration002Dto entity, OperationType operationType) {
        FinProductTradeDto tradeDto = genFinProductTradeDto(entity, operationType);
        tradeDto.setNeedSum(true);
        if (OperationType.REVOKE == operationType) {
            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
            entity.setOperateType(BussinessConstant.REVOKE);
        }
        else {
            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        }

        tradeDto.getBaseTradeProductDealDtos().forEach(item->{
            if (OperationType.REVOKE == operationType) {
                getInterfInputDto(item, DictCodeListExt1.交易流程状态_撤销);
            }
            else {
                getInterfInputDto(item, DictCodeListExt1.交易流程状态_有效);
            }
            // add by jiangpeng on 20210421 start
            // ZGBSS-1148 【债券借贷存续期】：到期交易中有“是否现金交割”的功能没有按照预期实现。
            if (StringUtil.isNotEmpty(entity.getIsCashPay())){
                item.setIsCashPay(entity.getIsCashPay());
            }
            item.setIsSLDeal(DictCodeList.是否_是.getValue());
            //add by jiangpeng on 20210421 end
        });

        baseTradeProductDealCommServiceImpl.execute(tradeDto);

        if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
            tradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 ->
                    {
                        baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId());
                        if (OperationType.REVOKE == operationType) {
                            getInterfInputDto(baseTradeProductDealDto1, DictCodeListExt1.交易流程状态_撤销);
                        }
                        else {
                            getInterfInputDto(baseTradeProductDealDto1, DictCodeListExt1.交易流程状态_有效);
                        }
                    }
                    );
            baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
            tradeDto.getBaseTradeProductDealDtos().forEach(
                    baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
        }

        // 写入质押品的组合持仓流水
        this.ptlPredgeSecPositionFlowLogic(entity, OperationType.REVOKE == operationType);

        // add by jiangpeng on 20210416 start
        // ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融出到期交易产生的错误
        if(OperationType.REVOKE == operationType) {
            // modify by jiangpeng on 20210419 start
            //ZGBSS-1150 【债券借贷存续期】：融出到期交易和撤销融出到期交易产生的错误
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), entity.getFinprodType2())) {
                // 写入融入券指令持仓流水
                this.etrBlendSecPositionFlowLogic(entity, true);
            }
            // modify by jiangpeng on 20210419 end
            // 写入融入券组合持仓流水
            this.ptlBlendSecPositionFlowLogic(entity, true);
            // add by jiangpeng on 20210416 end
        }else{
            // modify by jiangpeng on 20210419 start
            //ZGBSS-1150 【债券借贷存续期】：融出到期交易和撤销融出到期交易产生的错误
            if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), entity.getFinprodType2())
                    && StringUtil.equals(DictCodeList.是否_否.getValue(), entity.getIsCashPay())) {
                // 写入融入券组合持仓流水(借贷融入/借贷融出->融入/融出)
                this.ptlBlendSecPositionFlowLogicEffect(entity);
            }else {
                // 写入融入券组合持仓流水
                this.ptlBlendSecPositionFlowLogic(entity, false);
            }
            // modify by jiangpeng on 20210419 end
            // add by jiangpeng on 20210421 start
            // ZGBSS-1148 【债券借贷存续期】：到期交易中有“是否现金交割”的功能没有按照预期实现。
            if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), entity.getFinprodType2())
                    && StringUtil.equals(DictCodeList.是否_是.getValue(), entity.getIsCashPay())){
                // 写入融入券指令持仓流水
                this.etrBlendSecPositionFlowLogic(entity, false);
            }
            // add by jiangpeng on 20210421 end
        }

        // 新建交易撤销数据 或 插入Entity到对应的业务DB中, 并删除流程表中的数据
        entity.setIssuePartyId(CacheUtil.getSysParam("issue_party_id"));//为了登陆交易报送表中登记产品机构用，取系统参数
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/doComplete", entity, String.class);
    }


    private void getInterfInputDto(BaseTradeProductDealDto baseTradeProductDealDto,
                                   DictCodeListExt1 交易流程状态) {
        if (baseTradeProductDealDto.getTrdProductDealAdd() == null
                || StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())
                || AllTradeCommonUtil.isNotActPortfolio(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())) {
            return;
        }
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("TrdSecLendDuration002");
        investInterfCommDto.setTradeStatus(交易流程状态.getValue());
        baseTradeProductDealDto.getTrdProductDeal().setPs(DictCodeList.交易方向_到期.getValue());
        investInterfCommDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
        if (baseTradeProductDealDto.getStlPayOrdersList() != null
                && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
            investInterfCommDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList().get(0));
        }
        investInterfCommService.doInvestInterf(investInterfCommDto);
    }

    /**
     * 为调用共通组件组装的entity
     *
     * @param entity
     * @return
     */
    public static TrdProductDeal getTrdProductDeal(TrdSecLendDuration002Dto entity) {
        return AutoPeriodSeclendCommonService.getTrdProductDeal(entity);
    }

    @Override
    public void doInsert(TrdSecLendDuration002Dto entity, OperationType type) {
        saveDataWhenStartWorkflow(entity, type);
    }

    private void saveDataWhenStartWorkflow(TrdSecLendDuration002Dto entity, OperationType type) {
        if (type == OperationType.REVOKE || "submit".equals(entity.getOperateType())) {
            // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
            WflTrdFinProductDealTotal trdFinProductDealTotal = entity.getTrdFinProductDealTotal();
            trdFinProductDealTotal.setPortfolioId(entity.getActPortfolioId());
            // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
            List<WflStlPayOrders> stlPayOrdersDtos = entity.getStlPayOrdersDtos();

            // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
            WflPaTrdFinDealTotal wflPaTrdFinDealTotal = entity.getWflPaTrdFinDealTotal();

            WflTrdDealTotalAdd wflTrdDealTotalAdd = entity.getWflTrdDealTotalAdd();

            // 金融产品交易Entity -> 金融产品交易业务流程表
            List<WflTrdProductDeal> wflTrdProductDeals = entity.getWflTrdProductDeals();

            if (type == OperationType.REVOKE) {
                stlPayOrdersDtos.forEach(wflStlPayOrders -> {
                    wflStlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                    wflStlPayOrders.setTradeId(wflStlPayOrders.getTradeId() + "R");
                });
                trdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId() + 'R');
                wflPaTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId() + 'R');
                trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
                wflTrdProductDeals.forEach(wflTrdProductDeal -> {
                    wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
                    wflTrdProductDeal.setTradeId(wflTrdProductDeal.getTradeId() + 'R');
                });
            }
            wflTrdProductDeals.forEach(wflTrdProductDeal -> {
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDealMapper.insertSelective(wflTrdProductDeal);
            });

            wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
            wflPaTrdFinDealTotalMapper.insertSelective(wflPaTrdFinDealTotal);

            wflTrdDealTotalAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflTrdDealTotalAdd.setWorkflowId(entity.getWorkflowId());
            wflTrdDealTotalAddMapper.insertSelective(wflTrdDealTotalAdd);

            trdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
            wflTrdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
            stlPayOrdersDtos.forEach(wflStlPayOrders -> {
                wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
                wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
            });

            if (type != OperationType.REVOKE) {
                // 调用业务组件.【金融产品交易共通.交易产品接口】
                FinProductTradeDto tradeDto = genFinProductTradeDto(entity, OperationType.NEW);
                tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                baseTradeProductDealCommServiceImpl.execute(tradeDto);
                if (StringUtil.isNotEmpty(entity.getActPortfolioId()) && !StringUtil.equals(entity.getPortfolioId(), entity.getActPortfolioId())) {
                    tradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getActPortfolioId()));
                    baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
                    tradeDto.getBaseTradeProductDealDtos().forEach(
                            baseTradeProductDealDto1 -> baseTradeProductDealDto1.getTrdProductDealAdd().setPortfolioId(entity.getPortfolioId()));
                }
                // add by jiangpeng on 20210416 start
                // ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
                // modify by jiangpeng on 20210419 start
                //ZGBSS-1150 【债券借贷存续期】：融出到期交易和撤销融入到期交易产生的错误
                if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), entity.getFinprodType2())
                        && StringUtil.equals(DictCodeList.是否_否, entity.getIsCashPay())) {
                    // modify by jiangpeng on 20210419 end
                    // 写入融入券组合持仓流水
                    this.ptlBlendSecPositionFlowLogic(entity, false);
                    // 写入融入券指令持仓流水
                    this.etrBlendSecPositionFlowLogic(entity, false);
                }
                // add by jiangpeng on 20210416 end
            }
        }
    }

    private WflTrdDealTotalAdd gen汇总附属业务流程(TrdSecLendDuration002Dto entity, WflTrdFinProductDealTotal trdFinProductDealTotal) {
        return autoPeriodSeclendCommonService.gen汇总附属业务流程(entity, trdFinProductDealTotal);
    }

    @Override
    public void doUpdate(TrdSecLendDuration002Dto entity) {
        baseTradeProductDealCommServiceImpl.delete(entity.getWorkflowId());
        prepareEntity(entity);
        saveDataWhenStartWorkflow(entity, OperationType.NEW);
    }

    private List<WflTrdProductDeal> gen交易业务流程(TrdSecLendDuration002Dto entity) {
        return autoPeriodSeclendCommonService.gen交易业务流程(entity);
    }

    private WflPaTrdFinDealTotal gen汇总扩展业务流程(TrdSecLendDuration002Dto entity, WflTrdFinProductDealTotal trdFinProductDealTotal) {
        return AutoPeriodSeclendCommonService.gen汇总扩展业务流程(entity, trdFinProductDealTotal);
    }

    private List<WflStlPayOrders> gen划款指令流程(TrdSecLendDuration002Dto entity, OperationType type) {
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(entity.getBussTrdTransferOrder());
        List<WflStlPayOrders> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                WflStlPayOrders dto = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                dto.setTradeId(entity.getBusiCode());
                dto.setWorkflowId(entity.getWorkflowId());
                dto.setStatus(DictCodeList.数据状态_新建中.getValue());
                dto.setCreateDept(entity.getCreateDept());
                dto.setUpdateTime(entity.getUpdateTime());
                dto.setCreateUser(entity.getCreateUser());
                dto.setCreateTime(entity.getCreateTime());
                dto.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
                if (type == OperationType.REVOKE) {
                    dto.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
                    dto.setTradeId(dto.getTradeId() + "R");
                }
                else {
                    dto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                }
                stlPayOrdersDtos.add(dto);
            });
        }
        return stlPayOrdersDtos;
    }

    private static WflTrdFinProductDealTotal gen汇总流程表(TrdSecLendDuration002Dto entity) {
        return AutoPeriodSeclendCommonService.gen汇总流程表(entity, DictCodeListExt2.共通业务类型_债券借贷存续期.getValue());
    }

    @Autowired
    private PeriodCommonService periodCommonService;

    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommServiceImpl.delete(workflowId);
        TrdSecLendDuration002Dto entity = getBussinessEntityByWorkflowId(workflowId);
        TrdCommonDeleteDto trdCommonDeleteDto = new TrdCommonDeleteDto();
        trdCommonDeleteDto.setWorkflowId(workflowId);
        if (DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue().equals(entity.getFinprodType2())) {
            trdCommonDeleteDto.setWflTrdFinProductDealTotal(gen汇总流程表(entity));
        }

        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/doDelete", trdCommonDeleteDto,
            String.class);
    }

    public Page<RepoInfo> findRepoInfo() {
        return new Page<>(restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdSecLendDuration/findRepoInfo",
            RequestUtil.getPageParam(), RepoInfo.class));
    }

    /**
     * 写入融入券的组合持仓流水
     * @param input    画面参数Dto
     * @param isNegate 是否冲销
     */
    private boolean ptlBlendSecPositionFlowLogic(TrdSecLendDuration002Dto input, boolean isNegate) {
        List<PtlSecPositionFlowDto> lstPtlSecPositionFlowDto = new ArrayList<>();
        // 冲销时（撤销）
        // 根据交易编号检索组合持仓流水（持仓类型为实际）
        List<PtlSecPositionFlow> lstReal = this.selectPtlSecPositionFlow(isNegate ? input.getBusiCode() : input.getFinprodId(), false);
        if (!StringUtil.isEmptyList(lstReal)) {
            PtlSecPositionFlowDto resultDto;
            for (PtlSecPositionFlow ptlSecPositionFlow : lstReal) {
                resultDto = new PtlSecPositionFlowDto();
                // 组合代码
                resultDto.setPortfolioId(ptlSecPositionFlow.getPortfolioId());
                // 证券管理账户/通道代码
                resultDto.setSecAcctId(ptlSecPositionFlow.getSecAcctId());
                // 金融产品代码
                resultDto.setFinprodId(ptlSecPositionFlow.getFinprodId());
                // 分支序号
                resultDto.setBranch(ptlSecPositionFlow.getBranch());
                // add by jiangpeng on 20210416 start
                // ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
                // 持仓类型
                if (isNegate || StringUtil.equals(DictCodeList.是否_是.getValue(), input.getIsCashPay())) {
                    resultDto.setHodingType(ptlSecPositionFlow.getHodingType());
                } else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), input.getFinprodType2())) {
                    resultDto.setHodingType(DictCodeListExt1.持仓类型_借贷融入.getValue());
                } else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue(), input.getFinprodType2())) {
                    // modify by jiangpeng on 20210419 ZGBSS-1150 【债券借贷存续期】：融出到期交易和撤销融出到期交易产生的错误
//                    resultDto.setHodingType(DictCodeListExt1.持仓类型_借贷融出.getValue());
                    resultDto.setHodingType(ptlSecPositionFlow.getHodingType());
                }
                // add by jiangpeng on 20210416 end
                // 日期
                resultDto.setCdate(input.getMdate());
                // 数量
                resultDto.setAmount(ptlSecPositionFlow.getAmount().negate());
                // 币种
                resultDto.setCcy(ptlSecPositionFlow.getCcy());
                // 交易编号
                resultDto.setTradeId(input.getBusiCode());
                // 投资目的
                resultDto.setInvAim(ptlSecPositionFlow.getInvAim());

                lstPtlSecPositionFlowDto.add(resultDto);
            }
        }
        if (StringUtil.isEmptyList(lstPtlSecPositionFlowDto)) {
            return true;
        } else {
            PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
            ptlPositionHoldDto.setPtlSecPositionFlows(lstPtlSecPositionFlowDto);
            return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                ptlPositionHoldDto, Boolean.class);
        }
    }

    /**
     * 写入融入券的组合持仓流水(持仓类型为借贷融入/借贷融出)
     *  ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
     * @author jiangpeng
     * @param input    画面参数Dto
     */
    private boolean ptlBlendSecPositionFlowLogicEffect(TrdSecLendDuration002Dto input) {
        List<PtlSecPositionFlowDto> lstPtlSecPositionFlowDto = new ArrayList<>();
        // 冲销时（撤销）
        // 根据交易编号检索组合持仓流水（持仓类型为借贷融入/借贷融出）
        List<PtlSecPositionFlow> lstReal = this.selectPtlSecPositionFlowForSL(input.getBusiCode());
        if (!StringUtil.isEmptyList(lstReal)) {
            PtlSecPositionFlowDto resultDto;
            for (PtlSecPositionFlow ptlSecPositionFlow : lstReal) {
                resultDto = new PtlSecPositionFlowDto();
                // 组合代码
                resultDto.setPortfolioId(ptlSecPositionFlow.getPortfolioId());
                // 证券管理账户/通道代码
                resultDto.setSecAcctId(ptlSecPositionFlow.getSecAcctId());
                // 金融产品代码
                resultDto.setFinprodId(ptlSecPositionFlow.getFinprodId());
                // 分支序号
                resultDto.setBranch(ptlSecPositionFlow.getBranch());
                // 持仓类型
                if (StringUtil.equals(DictCodeListExt1.持仓类型_借贷融入.getValue(), ptlSecPositionFlow.getHodingType())) {
                    resultDto.setHodingType(DictCodeListExt1.持仓类型_融入.getValue());
                } else if (StringUtil.equals(DictCodeListExt1.持仓类型_借贷融出.getValue(), ptlSecPositionFlow.getHodingType())) {
                    resultDto.setHodingType(DictCodeListExt1.持仓类型_融出.getValue());
                }
                // 日期
                resultDto.setCdate(input.getMdate());
                // 数量
                resultDto.setAmount(ptlSecPositionFlow.getAmount());
                // 币种
                resultDto.setCcy(ptlSecPositionFlow.getCcy());
                // 交易编号
                resultDto.setTradeId(input.getBusiCode());
                // 投资目的
                resultDto.setInvAim(ptlSecPositionFlow.getInvAim());
                // 流水号
//                resultDto.setSeqno(EtrCommonCashPositionDealService.getSeqNo());
                lstPtlSecPositionFlowDto.add(resultDto);
                // 流程中的持仓冲销掉
                PtlSecPositionFlowDto ptlSecPositionFlowDtoTemp = new PtlSecPositionFlowDto();
                BeanUtil.copyPropertiesIgnoreNull(ptlSecPositionFlowDtoTemp,ptlSecPositionFlow);
                // 数量取反
                ptlSecPositionFlowDtoTemp.setAmount(ptlSecPositionFlowDtoTemp.getAmount().negate());
                // 流水号
//                ptlSecPositionFlowDtoTemp.setSeqno(EtrCommonCashPositionDealService.getSeqNo());
                lstPtlSecPositionFlowDto.add(ptlSecPositionFlowDtoTemp);
            }
        }
        if (StringUtil.isEmptyList(lstPtlSecPositionFlowDto)) {
            return true;
        } else {
            PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
            ptlPositionHoldDto.setPtlSecPositionFlows(lstPtlSecPositionFlowDto);
            return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                    ptlPositionHoldDto, Boolean.class);
        }
    }

    /**
     * 写入融入券的指令持仓流水
     *  ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
     * @author jiangpeng
     *@param input    画面参数Dto
     * @param isNegate 是否冲销
     */
    private void etrBlendSecPositionFlowLogic(TrdSecLendDuration002Dto input, boolean isNegate) {
        List<EtrSecPositionFlowDto> lstEtrSecPositionFlowDto = new ArrayList<>();
        // 冲销时（撤销）
        // 根据交易编号检索指令持仓流水（持仓类型为E01）
        List<EtrSecPositionFlow> lstReal = this.selectEtrSecPositionFlow(isNegate ? input.getBusiCode() : input.getFinprodId(), input.getPledgeFinprodId(), false);
        if (!StringUtil.isEmptyList(lstReal)) {
            EtrSecPositionFlowDto resultDto;
            for (EtrSecPositionFlow etrSecPositionFlow : lstReal) {
                resultDto = new EtrSecPositionFlowDto();
                // 组合代码
                resultDto.setPortfolioId(etrSecPositionFlow.getPortfolioId());
                // 证券管理账户/通道代码
                resultDto.setSecAcctId(etrSecPositionFlow.getSecAcctId());
                // 金融产品代码
                resultDto.setFinprodId(etrSecPositionFlow.getFinprodId());
                // 分支序号
                resultDto.setBranch(etrSecPositionFlow.getBranch());
                // 持仓类型
                resultDto.setHodingType(etrSecPositionFlow.getHodingType());
                // 日期
                resultDto.setCdate(input.getMdate());
                // 数量
                resultDto.setAmount(etrSecPositionFlow.getAmount().negate());
                // 币种
                resultDto.setCcy(etrSecPositionFlow.getCcy());
                // 交易编号
                resultDto.setTradeId(input.getBusiCode());
                // 投资目的
                resultDto.setInvAim(etrSecPositionFlow.getInvAim());
                // 流水号
                resultDto.setSeqno(EtrCommonCashPositionDealService.getSeqNo());

                lstEtrSecPositionFlowDto.add(resultDto);
            }
        }
        if (!StringUtil.isEmptyList(lstEtrSecPositionFlowDto)) {
            EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
            etrPositionHoldDto.setPtlSecPositionFlows(lstEtrSecPositionFlowDto);
            etrPositionHoldDto.setPtlCashPositionFlows(new ArrayList<>());
            EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);//流水入库
        }
    }

    /**
     * 写入质押品的组合持仓流水
     * @param input    画面参数Dto
     * @param isNegate 是否冲销
     */
    private boolean ptlPredgeSecPositionFlowLogic(TrdSecLendDuration002Dto input, boolean isNegate) {
        List<PtlSecPositionFlowDto> lstPtlSecPositionFlowDto = new ArrayList<>();
        // isNegate is true冲销时（撤销）
        // 根据交易编号检索组合持仓流水（持仓类型为实际）
        List<PtlSecPositionFlow> lstReal = this.selectPtlSecPositionFlow(isNegate?input.getBusiCode():input.getFinprodId(),true);
        if (!StringUtil.isEmptyList(lstReal)) {
            PtlSecPositionFlowDto resultDto;
            for (PtlSecPositionFlow ptlSecPositionFlow : lstReal) {
                resultDto = new PtlSecPositionFlowDto();
                // 组合代码
                resultDto.setPortfolioId(ptlSecPositionFlow.getPortfolioId());
                // 证券管理账户/通道代码
                resultDto.setSecAcctId(ptlSecPositionFlow.getSecAcctId());
                // 金融产品代码
                resultDto.setFinprodId(ptlSecPositionFlow.getFinprodId());
                // 分支序号
                resultDto.setBranch(ptlSecPositionFlow.getBranch());
                // 持仓类型
                resultDto.setHodingType(DictCodeList.持仓类型_质押.getValue());
                // 日期
                resultDto.setCdate(input.getMdate());
                // 数量
                resultDto.setAmount(ptlSecPositionFlow.getAmount().negate());
                // 币种
                resultDto.setCcy(ptlSecPositionFlow.getCcy());
                // 交易编号
                resultDto.setTradeId(input.getBusiCode());
                // 投资目的
                resultDto.setInvAim(ptlSecPositionFlow.getInvAim());

                lstPtlSecPositionFlowDto.add(resultDto);

            }
        }
        if (StringUtil.isEmptyList(lstPtlSecPositionFlowDto)) {
            return true;
        } else {
            PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
            ptlPositionHoldDto.setPtlSecPositionFlows(lstPtlSecPositionFlowDto);
            return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                ptlPositionHoldDto, Boolean.class);
        }
    }
    private FinProductTradeDto genFinProductTradeDto(TrdSecLendDuration002Dto trdBondDuration001Dto, OperationType operationType) {
        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();

        List<WflTrdProductDeal> wflTrdProductDeals = trdBondDuration001Dto.getWflTrdProductDeals();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            TrdProductDeal deal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(deal, wflTrdProductDeal);

            BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            baseTradeProductDealDtos.add(baseTradeProductDealDto);
            if (operationType == OperationType.REVOKE) {
                deal.setTradeId(deal.getTradeId() + "R");
            }
            TrdProductDeal pTrdProductDeal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealMapper.class,
                    deal.getFinprodId(), TrdProductDeal.class);
            if (pTrdProductDeal != null) {
                deal.setShareAmt(pTrdProductDeal.getShareAmt());
                // 名义本金
                deal.setPrinAmt(deal.getShareAmt());
            }
            // 交割金额 = 结算金额（费用）+名义本金
//        20190818 共同调用不需要添加
//        trdProductDeal.setTradeAmt(ArithmeticUtil.doAdd(trdProductDeal.getTradeAmt(),trdProductDeal.getPrinAmt()));
            if (DictCodeList.交易方向_到期.getValue().equals(deal.getPs())) {
                List<WflStlPayOrders> stlPayOrdersList = trdBondDuration001Dto.getStlPayOrdersDtos();
                List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
                if (stlPayOrdersList != null) {
                    stlPayOrdersList.forEach(stlPayOrders -> {
                        StlPayOrdersDto dto = new StlPayOrdersDto();
                        BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                        if (operationType == OperationType.REVOKE) {
                            dto.setTradeId(dto.getTradeId() + "R");
                        }
                        stlPayOrdersDtos.add(dto);
                    });
                }
                baseTradeProductDealDto.setStlPayOrdersList(stlPayOrdersDtos);
            }

            TrdProductDealAdd add1 = new TrdProductDealAdd();
            add1.setPortfolioId(trdBondDuration001Dto.getPortfolioId());
            add1.setTradeId(deal.getTradeId());

            baseTradeProductDealDto.setSecLendFinprodId(trdBondDuration001Dto.getPledgeFinprodId());
            baseTradeProductDealDto.setTrdProductDealAdd(add1);
            baseTradeProductDealDto.setTrdProductDeal(deal);
        });
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为实际）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    public List<PtlSecPositionFlow> selectPtlSecPositionFlow(String bizCode,boolean plegFlag) {
        if(plegFlag) {
            return restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdRepoTrade/selectPtlSecPositionFlow/" + bizCode,
                null, PtlSecPositionFlow.class);
        }
        else{
            return restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdOutRightRepoTrade/selectPtlSecPositionFlow/" + bizCode,
                null, PtlSecPositionFlow.class);
        }
    }

    /**
     * 写入融入券的组合持仓流水(持仓类型为借贷融入/借贷融出)
     *  ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
     * @author jiangpeng
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    public List<PtlSecPositionFlow> selectPtlSecPositionFlowForSL(String bizCode) {
            return restTemplate.postForList(
                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdOutRightRepoTrade/selectPtlSecPositionFlowForSL/" + bizCode,
                    null, PtlSecPositionFlow.class);
    }

    /**
     * 根据交易编号检索指令持仓流水（持仓类型为E01）
     * ZGBSS-1149 【债券借贷存续期】：融入到期交易和撤销融入到期交易产生的错误
     * @author jiangpeng
     *
     * @param bizCode 交易编号
     * @param finprodId 资产代码
     * @return List<EtrSecPositionFlow>
     * @since 1.0
     */
    public List<EtrSecPositionFlow> selectEtrSecPositionFlow(String bizCode,String finprodId, boolean isDoComplete) {
        Map<String, Object> param = new HashMap<>();
        param.put("bizCode", bizCode);
        param.put("finprodId", finprodId);
        List<EtrSecPositionFlow> list = restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdRepoTrade/selectEtrSecPositionFlow/", param,
                EtrSecPositionFlow.class);
        return list;
    }

    public void prepareEntity(TrdSecLendDuration002Dto entity) {
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);
        trdFinProductDealTotal.setPortfolioId(entity.getActPortfolioId());
        entity.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity, OperationType.NEW);
        entity.setStlPayOrdersDtos(stlPayOrdersDtos);

        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = gen汇总扩展业务流程(entity, trdFinProductDealTotal);
        entity.setWflPaTrdFinDealTotal(wflPaTrdFinDealTotal);

        WflTrdDealTotalAdd wflTrdDealTotalAdd = gen汇总附属业务流程(entity, trdFinProductDealTotal);
        entity.setWflTrdDealTotalAdd(wflTrdDealTotalAdd);

        // 金融产品交易Entity -> 金融产品交易业务流程表
        List<WflTrdProductDeal> wflTrdProductDeals = gen交易业务流程(entity);
        entity.setWflTrdProductDeals(wflTrdProductDeals);
    }

    @Override
    public void doBack(TrdSecLendDuration002Dto entity) {
        AllTradeCommonUtil.deletePosition(entity.getCancelRemark(), entity.getBusiCode());
    }
}
