package com.joyintech.tams.invest.period.trdbondduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.bond.AutoPeriodBondCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
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
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdBondDuration002Service extends BaseService<TrdBondDuration002ProcessDto> implements BaseProcessService<TrdBondDuration002ProcessDto> {

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    /**
     * AutoPeriodBondCommonService
     */
    @Autowired
    private AutoPeriodBondCommonService autoPeriodBondCommonService;

    @Autowired
    private InvestInterfCommService investInterfCommService;

    public TrdBondDuration002ProcessDto findEffectiveData(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/findEffectiveData",
            pageParam, TrdBondDuration002ProcessDto.class);
    }

    @Override
    public void doComplete(TrdBondDuration002ProcessDto entity, OperationType operationType) {
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//        FinProductTradeDto tradeDto = genFinProductTradeDto(entity);
//        if (OperationType.REVOKE == operationType) {
//            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
//            tradeDto.getBaseTradeProductDealDtos().forEach(baseTradeProductDealDto -> {
//                baseTradeProductDealDto.getTrdProductDeal().setTradeId(
//                    baseTradeProductDealDto.getTrdProductDeal().getTradeId() + 'R');
//            });
//            entity.setOperateType(BussinessConstant.REVOKE);
//        }
//        else {
//            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
//        }
//        tradeDto.setNeedSum(true);
//        baseTradeProductDealCommServiceImpl.execute(tradeDto);
//        // 插入Entity对应的业务流程DB中, 并删除流程表中的数据
//        entity.setIssuePartyId(CacheUtil.getSysParam("issue_party_id"));
//        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/doComplete", entity, String.class);
//        // 一体化对应etliu start
//        if (OperationType.REVOKE != operationType) {
//            // 所有交易生效后调用的共通方法
////        	TODO 需要改成现在的逻辑
//            for (WflTrdProductDeal wflTrdProductDeal : entity.getTrdBondDurDbInertDto().getWflTrdProductDealList()) {
//                TrdProductDeal trdProductDeal = new TrdProductDeal();
//                BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, wflTrdProductDeal);
//
//                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
//                inputDto.setProdid(entity.getPortfolioId());
//                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
//                inputDto.setTrdProductDeal(trdProductDeal);
//                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
//    }
//        }
//        // 一体化对应etliu end
        if (OperationType.REVOKE == operationType) {
            execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销);
            entity.setOperateType(BussinessConstant.REVOKE);
        }
        else {
            execute交易共通(entity, DictCodeListExt1.交易流程状态_有效);
        }
        entity.setIssuePartyId(CacheUtil.getSysParam("issue_party_id"));
        // 新建交易撤销数据 或 插入Entity到对应的业务DB中, 并删除流程表中的数据
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/doComplete", entity,
            String.class);
    }

    /*public void genDataForInsertDto(TrdBondDuration002ProcessDto entity) {
        autoPeriodBondCommonService.genBondDataForInsertDto(entity);

        // // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        // WflTrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);

        // // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        // WflPaTrdFinDealTotal wflPaTrdFinDealTotal = gen汇总扩展业务流程(entity, trdFinProductDealTotal);
        //
        // // 金融产品交易Entity -> 金融产品交易业务流程表
        // List<WflTrdProductDeal> wflTrdProductDeals = gen交易业务流程(entity);
        //
        // List<PtlTrade> ptlTrades = gen组合交易(entity, wflTrdProductDeals);

        // TrdBondDurDbInertDto trdBondDurDbInertDto = new TrdBondDurDbInertDto();
        TrdBondDurDbInertDto trdBondDurDbInertDto = entity.getTrdBondDurDbInertDto();
        // trdBondDurDbInertDto.setWflTrdFinProductDealTotal(trdFinProductDealTotal);
        trdBondDurDbInertDto.setWflStlPayOrdersList(stlPayOrdersDtos);
        // trdBondDurDbInertDto.setWflPaTrdFinDealTotal(wflPaTrdFinDealTotal);
        // trdBondDurDbInertDto.setWflTrdProductDealList(wflTrdProductDeals);
        // trdBondDurDbInertDto.setPtlTrades(ptlTrades);
        entity.setTrdBondDurDbInertDto(trdBondDurDbInertDto);
    }*/

    // private List<PtlTrade> gen组合交易(TrdBondDuration002ProcessDto entity, List<WflTrdProductDeal> wflTrdProductDeals) {
    // List<PtlTrade> result = new ArrayList<>();
    // wflTrdProductDeals.forEach(wflTrdProductDeal -> {
    // PtlTrade ptlTrade2 = new PtlTrade();
    // ptlTrade2.setPortfolioId(entity.getPortfolioId());
    // ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
    // ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
    // ptlTrade2.setBusiId(entity.getFinprodId());
    // ptlTrade2.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
    // ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
    // ptlTrade2.setSettleDate(DateTime.parse(entity.getTradeDate()).toDate());
    // result.add(ptlTrade2);
    // });
    // return result;
    // }

    /*private FinProductTradeDto genFinProductTradeDto(TrdBondDuration002ProcessDto trdBondDuration001Dto) {
        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        List<TrdProductDeal> trdProductDeal = genTrdProductDealForCashFlow(trdBondDuration001Dto);
        trdProductDeal.forEach(deal -> {
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            TrdProductDealAdd add = new TrdProductDealAdd();
            add.setPortfolioId(trdBondDuration001Dto.getPortfolioId());

            baseTradeProductDealDto.setTrdProductDealAdd(add);
            baseTradeProductDealDto.setTrdProductDeal(deal);
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        });

        // 划款指令表Entity
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
            trdBondDuration001Dto.getBussTrdTransferOrder());
        List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrdersDto dto = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                stlPayOrdersDtos.add(dto);
            });
        }
        baseTradeProductDealDtos.get(0).setStlPayOrdersList(stlPayOrdersDtos);
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }

    private List<TrdProductDeal> genTrdProductDealForCashFlow(TrdBondDuration002ProcessDto entity) {
        return AutoPeriodBondCommonService.genTrdProductDealForCashFlow(entity);
    }*/

    /**
     * 为调用共通组件组装的entity
     *
     * @param entity
     * @return
     */
    public static TrdProductDeal getTrdProductDeal(TrdBondDuration002ProcessDto entity) {
        // TrdProductDeal trdProductDeal = new TrdProductDeal();
        // trdProductDeal.setTradeId(entity.getBusiCode());
        // trdProductDeal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
        // trdProductDeal.setFinprodId(entity.getFinprodId());
        // // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        // trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        // trdProductDeal.setSecManageAcctId(entity.getSecManageAcctId());
        // trdProductDeal.setCcy(entity.getCcy());
        // String ps = change现金流类型To交易方向(entity.getCashType());
        // trdProductDeal.setPs(ps);
        // trdProductDeal.setTradeDate(DateTime.parse(entity.getTradeDate()).toDate());
        // trdProductDeal.setVdate(DateTime.parse(entity.getvDate()).toDate());
        // trdProductDeal.setMdate(DateTime.parse(entity.getmDate()).toDate());
        // trdProductDeal.setSettleDate(DateTime.parse(entity.getTradeDate()).toDate());
        // trdProductDeal.setShareAmt(entity.getQty());
        //
        // trdProductDeal.setPrinAmt(ArithmeticUtil.subtract(entity.getTradeAmt(), entity.getIntAmt()));
        // trdProductDeal.setIntAmt(entity.getIntAmt());
        // trdProductDeal.setTradeAmt(entity.getTradeAmt());
        // trdProductDeal.setTradeMarket(entity.getTradeMarket());
        // return trdProductDeal;

        return AutoPeriodBondCommonService.getTrdProductDeal(entity);
    }

    @Override
    public void doInsert(TrdBondDuration002ProcessDto entity, OperationType type) {
        entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        entity.setCreateDept(SecurityUtil.getLoginUser().getDeptCode());
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
        	setWorkflowId(entity.getBaseTotalTradeInfoDto(), entity.getWorkflowId());
        }
//        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
//            if (entity.getTrdBondDurDbInertDto() != null
//                && entity.getTrdBondDurDbInertDto().getWflTrdFinProductDealTotal().getWorkflowId() == null) {
//                entity.getTrdBondDurDbInertDto().getWflTrdFinProductDealTotal().setWorkflowId(entity.getWorkflowId());
//                entity.getTrdBondDurDbInertDto().getWflStlPayOrdersList().forEach(baseTradeInfoDto -> {
//                    baseTradeInfoDto.setWorkflowId(entity.getWorkflowId());
//                });
//                entity.getTrdBondDurDbInertDto().getWflPaTrdFinDealTotal().setWorkflowId(entity.getWorkflowId());
//                entity.getTrdBondDurDbInertDto().getWflTrdProductDealList().forEach(baseTradeInfoDto -> {
//                    baseTradeInfoDto.setWorkflowId(entity.getWorkflowId());
//                });
//                if (entity.getTrdBondDurDbInertDto() != null) {
//                    entity.getTrdBondDurDbInertDto().getWflTrdProductDealAdds().forEach(add -> {
//                        add.setWorkflowId(entity.getWorkflowId());
//                    });
//                }
//            }
//        }
        saveDataWhenStartWorkflow(entity, type);
    }

    @Override
    public void doUpdate(TrdBondDuration002ProcessDto entity) {
//        baseTradeProductDealCommServiceImpl.delete(entity.getWorkflowId());
        //对预增的金额和指令可用现金流进行先删后插，防止提交时多次进入doUpdate方法导致数据多次插入
        String busiCode = entity.getBusiCode();
//        AllTradeCommonUtil.deletePositionReal(busiCode);
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), busiCode);

//        2.0改造
        entity.setBaseTotalTradeInfoDto(genFinProductTradeDto2(entity));
//        genDataForInsertDto(entity);
        ProcessMark newEntity;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdBondDuration002ProcessDto.class, true);
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
        if (!StringUtil.isNotEmpty(entity.getRevokeReason())){
            saveDataWhenStartWorkflow(entity, OperationType.MODIFY);
        }
    }

    private void saveDataWhenStartWorkflow(TrdBondDuration002ProcessDto entity, OperationType type) {
//        if (type != OperationType.REVOKE && BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
//            // 插入Entity对应的业务流程DB中
//            if (entity.getNeedSkipInsertWfl() == null || !entity.getNeedSkipInsertWfl()) {
//                bussCommonMapperDelegateService.insertAutoClassifyBatch(
//                    entity.getTrdBondDurDbInertDto().getWflTrdProductDealList(),
//                    entity.getTrdBondDurDbInertDto().getWflPaTrdFinDealTotal(),
//                    entity.getTrdBondDurDbInertDto().getWflTrdFinProductDealTotal(),
//                    entity.getTrdBondDurDbInertDto().getWflStlPayOrdersList(),
//                    entity.getTrdBondDurDbInertDto().getWflTrdProductDealAdds());
//            }
//            // 调用业务组件.【金融产品交易共通.交易产品接口】
//            FinProductTradeDto tradeDto = genFinProductTradeDto(entity);
//            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
//            baseTradeProductDealCommServiceImpl.execute(tradeDto);
//        }
//        else if (type == OperationType.REVOKE) {
//            restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/doRevoke", entity,
//                String.class);
//        }
    	if (BussinessConstant.SUBMIT.equals(entity.getOperateType()) && type != OperationType.REVOKE) {
            // 插入Entity到对应的业务流程DB中
            if (entity.getNeedSkipInsertWfl() == null || !entity.getNeedSkipInsertWfl()) {
                restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/doInsert",
                    entity, String.class);
            }
            if (type != OperationType.REVOKE) {
                execute交易共通(entity, DictCodeListExt1.交易流程状态_流程中);
            }
        }
        else if (type == OperationType.REVOKE) {
            restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/doRevoke", entity,
                String.class);
        }
    }
    /**
     * 
     * 批量调用共通
     * <功能详细描述>
     * @param entity 业务实体
     * @param 交易流程状态
     * @see
     * @since 1.0
     */
    private void execute交易共通(TrdBondDuration002ProcessDto entity, DictCodeListExt1 交易流程状态) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());

        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
                TrdProductDeal deal = baseTradeInfoDto.getTrdProductDeal();
                if (deal == null) { // 付息还本不同频的还本
                    deal = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                    TrdProductDeal dealExe = new TrdProductDeal();
                    BeanUtils.copyProperties(deal, dealExe);
                    setDeal(baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe,
                            entity, 交易流程状态);
				} else { // 纯付息或中间还本到期还本带付息
					if (baseTradeInfoDto.getBaseTrade() == null
							|| baseTradeInfoDto.getBaseTrade().getTrdProductDeal() == null) { // 纯付息
						TrdProductDeal dealExe = new TrdProductDeal();
						BeanUtils.copyProperties(deal, dealExe);
						setDeal(baseTradeProductDealDtos, baseTradeInfoDto, baseTradeInfoDto.getTrdProductDealAdd(),
								dealExe, entity, 交易流程状态);
					}
					if (baseTradeInfoDto.getBaseTrade() != null
							&& baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) { // 还本到期带付息的还本交易
						TrdProductDeal deal1 = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
						TrdProductDeal dealExe1 = new TrdProductDeal();
						BeanUtils.copyProperties(deal1, dealExe1);
						setDeal(baseTradeProductDealDtos, baseTradeInfoDto,
								baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe1, entity, 交易流程状态);
					}
				}
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        } else {
            List<BaseTradeProductDealDto> baseTradeProductDealDtos1 = periodCommonService.findEffectiveDeal(entity.getBusiCode(),交易流程状态);
            for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtos1) {
                TrdProductDeal deal1 = baseTradeProductDealDto.getTrdProductDeal();
                deal1.setTradeId(entity.getBusiCode() + 'R');
                deal1.setCreateUser(tradeDto.getCreateUser());
                deal1.setCreateTime(tradeDto.getCreateTime());
                deal1.setCreateDept(tradeDto.getCreateDept());
                deal1.setUpdateTime(tradeDto.getUpdateTime());
                deal1.setUpdateUser(tradeDto.getUpdateUser());
                // 调用投管接口 add by qcx 20190205 start
                getInterfInputDto(entity, 交易流程状态, baseTradeProductDealDto, baseTradeProductDealDto.getStlPayOrdersList().get(0));
                // 调用投管接口 add by qcx 20190205 end
            }
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos1);
        }
        baseTradeProductDealCommServiceImpl.execute(tradeDto);
        execute交易共通子组合(entity,交易流程状态);
    }

    private void execute交易共通子组合(TrdBondDuration002ProcessDto entity, DictCodeListExt1 交易流程状态) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            List<BaseTradeInfoDto> baseTradeInfoDtoList = entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
            for (BaseTradeInfoDto baseTradeInfoDto : baseTradeInfoDtoList) {
                for (BaseTradeInfoDto x : baseTradeInfoDto.getBaseTradeInfoDtoList()) {
                    List<BaseTradeProductDealDto> temp = new ArrayList<>();
                    List<TrdProductDeal> deals = periodCommonService.getCommonExcuteDtoByTrade(x, temp);
                    for (BaseTradeProductDealDto dealDto : temp) {
                        if (交易流程状态 != DictCodeListExt1.交易流程状态_流程中) {
                            // 调用投管接口 add by qcx 20190205 start
                            getInterfInputDto(entity, 交易流程状态, dealDto, dealDto.getStlPayOrdersList().get(0));
                            // 调用投管接口 add by qcx 20190205 end
                        }
                        if (DictCodeList.交易方向_还本.getValue().equals(dealDto.getTrdProductDeal().getPs())
                                || DictCodeList.交易方向_到期.getValue().equals(dealDto.getTrdProductDeal().getPs())) {
                            dealDto.setStlPayOrdersList(null);
                        }
                    }
                    baseTradeProductDealDtos.addAll(temp);
                    temp.clear();
                }
            }
            baseTradeProductDealDtos.forEach(baseTradeProductDealDto -> {
                        baseTradeProductDealDto.getTrdProductDeal().setPs(baseTradeProductDealDto.getTrdProductDeal().getStatus());
                        baseTradeProductDealDto.getTrdProductDeal().setTradeId(entity.getBusiCode());
                    }
            );
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        else {
            Map<String,Object>map = new HashMap<>();
            map.put("statusValid", 交易流程状态.getValue());
            map.put("statusValid03", DictCodeListExt1.交易流程状态_撤销.getValue());
            map.put("bizCode", entity.getBusiCode());
            baseTradeProductDealDtos.addAll(periodCommonService.getChiledTradeProductDealInfo(map));
            for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtos) {
                baseTradeProductDealDto.getTrdProductDeal().setTradeId(entity.getBusiCode() + "R");
                // 调用投管接口 add by qcx 20190205 start
                getInterfInputDto(entity, 交易流程状态, baseTradeProductDealDto, baseTradeProductDealDto.getStlPayOrdersList().get(0));
                // 调用投管接口 add by qcx 20190205 end
            }
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
    }

    private void getInterfInputDto(TrdBondDuration002ProcessDto entity, DictCodeListExt1 交易流程状态, 
                                   BaseTradeProductDealDto baseTradeProductDealDto, StlPayOrders order) {
        if (baseTradeProductDealDto.getTrdProductDealAdd() == null
            || StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())
            || AllTradeCommonUtil.isNotActPortfolio(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())) {
            return;
        }
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("TrdBondDuration002");
        if (DictCodeListExt1.交易流程状态_撤销 == 交易流程状态) {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        }
        else {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
        }
        investInterfCommDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setPaBussType(entity.getPaBussType());
        investInterfCommDto.setPaTrdProductDeal(paTrdProductDeal);
        investInterfCommDto.setStlPayOrders(order);
        investInterfCommService.doInvestInterf(investInterfCommDto);
    }

    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseTradeProductDealDtos
     * @param baseTradeInfoDto
     * @param add
     * @param dealExe
     * @param entity
     * @param 交易流程状态
     * @see
     * @since 1.0
     */
    private void setDeal(List<BaseTradeProductDealDto> baseTradeProductDealDtos, BaseTradeInfoDto baseTradeInfoDto, TrdProductDealAdd add, TrdProductDeal dealExe, TrdBondDuration002ProcessDto entity, DictCodeListExt1 交易流程状态) {
        dealExe.setPs(dealExe.getStatus());
        /*if (!DictCodeList.交易方向_收息.getValue().equals(dealExe.getStatus())) {
            dealExe.setShareAmt(ArithmeticUtil.divied(entity.getQty(), new BigDecimal(100), 4)); // 只有到期时，才影响债券的持仓，并且是券面总额
        }*/
        if (entity.isChannel()) {
            dealExe.setChlId(entity.getSecManageAcctId());
            dealExe.setSecManageAcctId(null);
        }
    	dealExe.setFpriceAmt(dealExe.getTradeAmt());
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        dealExe.setTradeId(entity.getBusiCode());
        baseTradeProductDealDto.setTrdProductDeal(dealExe);
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        StlPayOrdersDto orders = new StlPayOrdersDto();
        BeanUtils.copyProperties(baseTradeInfoDto.getStlPayOrders(), orders);
        baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(new StlPayOrdersDto[]{orders}));
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        // 调用投管接口 add by qcx 20190205 start
        getInterfInputDto(entity, 交易流程状态, baseTradeProductDealDto, orders);
        // 调用投管接口 add by qcx 20190205 end
    }
    // private List<WflTrdProductDeal> gen交易业务流程(TrdBondDuration002ProcessDto entity) {
    // List<String> cashIds = StringUtil.splitToList(",", entity.getCashId());
    // List<String> cashTypes = StringUtil.splitToList(",", entity.getCashType());
    // List<WflTrdProductDeal> wflTrdProductDeals = new ArrayList<>();
    // for (int i = 0; i < cashIds.size(); i++ ) {
    // String ps = change现金流类型To交易方向(cashTypes.get(i));
    // BussinessCodeParameter parameter = new BussinessCodeParameter();
    // parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
    // WflTrdProductDeal deal = new WflTrdProductDeal();
    // if (entity.getTrdBondDurDbInertDto() != null && entity.getTrdBondDurDbInertDto().getWflTrdProductDealList() != null) {
    // entity.getTrdBondDurDbInertDto().getWflTrdProductDealList().forEach(wflTrdProductDeal -> {
    // if (wflTrdProductDeal.getPs().equals(ps)) {
    // deal.setTradeId(wflTrdProductDeal.getTradeId());
    // return;
    // }
    // });
    // }
    // else {
    // deal.setTradeId(baseGetBusinessIdImpl.buildBussinessCode(parameter));
    // }
    // deal.setBranch(changePsToBranch(entity.getmDate(), ps));
    // deal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
    // deal.setTotleTradeId(entity.getBusiCode());
    // deal.setFinprodId(entity.getFinprodId());
    // deal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
    // // deal.setFinprodType(); TODO 估值核算 金融产品类型
    // deal.setSecManageAcctId(entity.getSecManageAcctId());
    // deal.setCcy(entity.getCcy());
    // deal.setPs(ps);
    // deal.setTradeDate(DateTime.parse(entity.getTradeDate()).toDate());
    // deal.setVdate(DateTime.parse(entity.getvDate()).toDate());
    // deal.setMdate(DateTime.parse(entity.getmDate()).toDate());
    // deal.setSettleDate(deal.getTradeDate());
    // deal.setShareAmt(entity.getQty());
    // deal.setIntAmt(entity.getIntAmt());
    // deal.setTradeAmt(entity.getTradeAmt());
    // deal.setPrinAmt(ArithmeticUtil.subtract(deal.getTradeAmt(), deal.getIntAmt()));
    // deal.setTradeMarket(entity.getTradeMarket());
    // deal.setCashId(cashIds.get(i));
    // deal.setIsCancel(DictCodeList.是否_否.getValue());
    // deal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
    // deal.setWorkflowId(entity.getWorkflowId());
    // deal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
    // wflTrdProductDeals.add(deal);
    // }
    // return wflTrdProductDeals;
    // }

    // private WflPaTrdFinDealTotal gen汇总扩展业务流程(TrdBondDuration002ProcessDto entity,
    // WflTrdFinProductDealTotal trdFinProductDealTotal) {
    // WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
    // wflPaTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
    // wflPaTrdFinDealTotal.setUnitInt(entity.getUnitIntAmt());
    // wflPaTrdFinDealTotal.setUnitPrin(entity.getIntPrinAmt());
    // wflPaTrdFinDealTotal.setPrin(entity.getPrinAmt());
    // wflPaTrdFinDealTotal.setRate(entity.getYield());
    // wflPaTrdFinDealTotal.setPaBussType(entity.getPaBussType());
    // wflPaTrdFinDealTotal.setTheoryAmt(entity.getFpriceAmt());
    // wflPaTrdFinDealTotal.setTheoryPayDate(DateTime.parse(entity.getPayDate()).toDate());
    // wflPaTrdFinDealTotal.setTheoryInt(entity.getIntAmtTheory());
    // wflPaTrdFinDealTotal.setTheoryPrin(entity.getPrinAmtTheory());
    // wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
    // wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
    // return wflPaTrdFinDealTotal;
    // }
    /**
     * 
     * 2.0划款指令复制
     * <功能详细描述>
     * @param total 汇总交易 
     * @param stlPayOrdersList 划款指令 
     * @param entity 业务实体
     * @return 转换的划款指令
     * @see
     * @since 1.0
     */
    private StlPayOrders gen划款指令流程2(List<BussTrdTransferOrderDto> stlPayOrdersList, TrdFinProductDealTotal total, TrdBondDuration002ProcessDto entity) {
    	StlPayOrders stlPayOrders = null;
        for (BussTrdTransferOrderDto dto : stlPayOrdersList) {
            if (dto.getPortfolioId().equals(total.getPortfolioId()) && ((StringUtil.isEmpty(dto.getInvAim()) && StringUtil.isEmpty(total.getInvAim()))
                    || (dto.getInvAim() != null && dto.getInvAim().equals(total.getInvAim())))) {
                stlPayOrders = dto;
                stlPayOrders.setOrderStatus(DictCodeList.清算操作类型_新建.getValue());
                stlPayOrders.setTradeId(total.getTotleTradeId());
                stlPayOrders.setStatus(DictCodeList.数据状态_新建.getValue());
                stlPayOrders.setWorkflowId(entity.getWorkflowId());
            }
        }
        return stlPayOrders;
    }

    /*private List<WflStlPayOrders> gen划款指令流程(TrdBondDuration002ProcessDto entity) {
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(entity.getBussTrdTransferOrder());
        List<WflStlPayOrders> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                WflStlPayOrders dto = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                dto.setTradeId(entity.getBusiCode());
                dto.setPortfolioId(entity.getPortfolioId());
                dto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
                dto.setWorkflowId(entity.getWorkflowId());
                dto.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
                stlPayOrdersDtos.add(dto);
            });
        }
        return stlPayOrdersDtos;
    }*/

    // private WflTrdFinProductDealTotal gen汇总流程表(TrdBondDuration002ProcessDto entity) {
    // WflTrdFinProductDealTotal trdFinProductDealTotal = new WflTrdFinProductDealTotal();
    // trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
    // trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
    // trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
    // // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
    // trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
    // trdFinProductDealTotal.setSecManageAcctId(entity.getSecManageAcctId());
    // trdFinProductDealTotal.setCcy(entity.getCcy());
    // trdFinProductDealTotal.setPs(change现金流类型To交易方向(entity.getCashType()));
    // trdFinProductDealTotal.setBranch(changePsToBranch(entity.getmDate(), trdFinProductDealTotal.getPs()));
    // trdFinProductDealTotal.setTradeDate(DateTime.parse(entity.getTradeDate()).toDate());
    // trdFinProductDealTotal.setVdate(DateTime.parse(entity.getvDate()).toDate());
    // trdFinProductDealTotal.setMdate(DateTime.parse(entity.getmDate()).toDate());
    // trdFinProductDealTotal.setSettleDate(DateTime.parse(entity.getTradeDate()).toDate());
    // trdFinProductDealTotal.setShareAmt(entity.getQty());
    // trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
    // trdFinProductDealTotal.setPrinAmt(ArithmeticUtil.subtract(entity.getTradeAmt(), entity.getIntAmt()));
    // trdFinProductDealTotal.setIntAmt(entity.getIntAmt());
    // trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
    // trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
    // trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
    // trdFinProductDealTotal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
    // trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
    // trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
    // return trdFinProductDealTotal;
    // }

    @Autowired
    private PeriodCommonService periodCommonService;

    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommServiceImpl.delete(workflowId);
    }

    public CommentFunction<TrdBondDuration002ProcessDto, String> 生成业务说明() {
        return entity -> WorkFlowUtil.concatComment(
                this.getMessage("TrdBondDuration001.finprodName") + GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName()
                        + GlobalConstant.CLOSE_PARENTHESIS,
                "资产类型" + GlobalConstant.OPEN_PARENTHESIS + entity.getPaBussTypeString() + GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage(
                        "TrdBondDuration001.busiCode") + GlobalConstant.OPEN_PARENTHESIS + entity.getBusiCode() + GlobalConstant.CLOSE_PARENTHESIS);
    }

    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        List<FinAssetIntInfo> finAssetIntInfos = restTemplate.postForList(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/findAssetIntInfo", pageParam,
                FinAssetIntInfo.class);
//        String statusValid = (String)pageParam.get("statusValid");
        for (int i = 0; i < finAssetIntInfos.size(); i++ ) {
            finAssetIntInfos.get(i).setCalcRuleString(
                    SysDataUtil.getNameFromDataDict(DictCode.频率, finAssetIntInfos.get(i).getCalcRule()));
        }
//        if (DictCodeListExt1.画面数据状态_待处理.getValue().equals(statusValid)) {
//            // 按占比计算还本金额、理论本息、实收本息
//            BigDecimal sumAmount = BigDecimal.ZERO;
//            for (FinAssetIntInfo finAssetIntInfo : finAssetIntInfos) {
//                sumAmount = ArithmeticUtil.doAdd(sumAmount, finAssetIntInfo.getAmount());
//            }
////              券面总额
//            BigDecimal 券面总额 = (BigDecimal)pageParam.get("qty");
////              理论应计利息
//            BigDecimal 理论应计利息 = (BigDecimal)pageParam.get("intAmtTheory");
////              理论还本金额
//            BigDecimal 理论还本金额 = (BigDecimal)pageParam.get("prinAmtTheory");
////              实收利息总额
//            BigDecimal 实收利息总额 = (BigDecimal)pageParam.get("intAmt");
////              实收本息总额
//            BigDecimal 实收本息总额 = (BigDecimal)pageParam.get("fpriceAmt");
////              理论本息总额
//            BigDecimal 理论本息总额 = (BigDecimal)pageParam.get("theoryAmt");
//
//            BigDecimal sum券面总额 = BigDecimal.ZERO;
//            BigDecimal sum理论应计利息 = BigDecimal.ZERO;
//            BigDecimal sum理论还本金额 = BigDecimal.ZERO;
//            BigDecimal sum实收利息总额 = BigDecimal.ZERO;
//            BigDecimal sum实收本息总额 = BigDecimal.ZERO;
//            BigDecimal sum理论本息总额 = BigDecimal.ZERO;
//            for (int i = 0; i < finAssetIntInfos.size(); i++ ) {
//                if (i == finAssetIntInfos.size() - 1) {
//                    finAssetIntInfos.get(i).setQty(ArithmeticUtil.subtract(券面总额, sum券面总额));
//                    finAssetIntInfos.get(i).setIntAmtTheory(ArithmeticUtil.subtract(理论应计利息, sum理论应计利息));
//                    finAssetIntInfos.get(i).setPrinAmtTheory(ArithmeticUtil.subtract(理论还本金额, sum理论还本金额));
//                    finAssetIntInfos.get(i).setIntAmt(ArithmeticUtil.subtract(实收利息总额, sum实收利息总额));
//                    finAssetIntInfos.get(i).setFpriceAmt(ArithmeticUtil.subtract(实收本息总额, sum实收本息总额));
//                    finAssetIntInfos.get(i).setTheoryAmt(ArithmeticUtil.subtract(理论本息总额, sum理论本息总额));
//                }
//                else {
//                    BigDecimal prop = ArithmeticUtil.divide(finAssetIntInfos.get(i).getAmount(), sumAmount, 12,
//                            RoundingMode.HALF_UP);
//                    BigDecimal this券面总额 = ArithmeticUtil.round(ArithmeticUtil.multiply(券面总额, prop), 2, RoundingMode.HALF_UP);
//                    BigDecimal this理论应计利息 = ArithmeticUtil.round(ArithmeticUtil.multiply(理论应计利息, prop), 2, RoundingMode.HALF_UP);
//                    BigDecimal this理论还本金额 = ArithmeticUtil.round(ArithmeticUtil.multiply(理论还本金额, prop), 2, RoundingMode.HALF_UP);
//                    BigDecimal this实收利息总额 = ArithmeticUtil.round(ArithmeticUtil.multiply(实收利息总额, prop), 2, RoundingMode.HALF_UP);
//                    BigDecimal this实收本息总额 = ArithmeticUtil.round(ArithmeticUtil.multiply(实收本息总额, prop), 2, RoundingMode.HALF_UP);
//                    BigDecimal this理论本息总额 = ArithmeticUtil.round(ArithmeticUtil.multiply(理论本息总额, prop), 2, RoundingMode.HALF_UP);
//                    sum券面总额 = ArithmeticUtil.doAdd(sum券面总额, this券面总额);
//                    sum理论应计利息 = ArithmeticUtil.doAdd(sum理论应计利息, this理论应计利息);
//                    sum理论还本金额 = ArithmeticUtil.doAdd(sum理论还本金额, this理论还本金额);
//                    sum实收利息总额 = ArithmeticUtil.doAdd(sum实收利息总额, this实收利息总额);
//                    sum实收本息总额 = ArithmeticUtil.doAdd(sum实收本息总额, this实收本息总额);
//                    sum理论本息总额 = ArithmeticUtil.doAdd(sum理论本息总额, this理论本息总额);
//                    finAssetIntInfos.get(i).setQty(this券面总额);
//                    finAssetIntInfos.get(i).setIntAmtTheory(this理论应计利息);
//                    finAssetIntInfos.get(i).setPrinAmtTheory(this理论还本金额);
//                    finAssetIntInfos.get(i).setIntAmt(this实收利息总额);
//                    finAssetIntInfos.get(i).setFpriceAmt(this实收本息总额);
//                    finAssetIntInfos.get(i).setTheoryAmt(this理论本息总额);
//                }
//            }
//        }
        return finAssetIntInfos;
    }
    public Map<String, Object> genParams(TrdBondDuration002ProcessDto entity) {
        return new HashMap<String, Object>() {
            {
                //调整后的计算结束日
                put("mDate", entity.getmDate());
                put("vDate", entity.getvDate());
//                金融产品代码
                put("finprodId", entity.getFinprodId());
                put("statusValid", entity.getStatusValid());
                put("busiCode", entity.getBusiCode());
                put("yield", entity.getYield());
//              券面总额
//                put("qty", entity.getQty());
////              理论应计利息
//                put("intAmtTheory", entity.getIntAmtTheory());
////              理论还本金额
//                put("prinAmtTheory", entity.getPrinAmtTheory());
////              实收利息总额
//                put("intAmt", entity.getIntAmt());
////              理论本息总额 dto字段问题
//                put("theoryAmt", entity.getFpriceAmt());
////              实收本息总额 dto字段问题
//                put("fpriceAmt", entity.getTradeAmt());
//              证券托管户
                put("secAcctId", entity.getSecManageAcctId());
            }
        };
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
    public BaseTotalTradeInfoDto genFinProductTradeDto2(TrdBondDuration002ProcessDto entity) {
        return autoPeriodBondCommonService.genFinProductTradeDto(entity);
    }

    @Override
    public void doBack(TrdBondDuration002ProcessDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getBusiCode());
    }

    @Override
    public void doRevoke(TrdBondDuration002ProcessDto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getBusiCode());
    }
}
