/*
 * 文件名：TrdDepoDurationDealEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trddepoduration.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.depo.AutoPeriodDepoCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2018年11月1日
 * @see TrdDepoDurationDealEntity
 * @since 1.0
 */
@Service
public class TrdDepoDurationDealEntity extends BaseService<TrdDepoDuration001Dto> {
    @Autowired
    private InvestInterfCommService investInterfCommService;

    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    @Autowired
    private TrdDepoDuration001Service trdDepoDuration001Service;
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * AutoPeriodDepoCommonService
     */
    @Autowired
    private AutoPeriodDepoCommonService autoPeriodDepoCommonService;

    public List<RepTrade> gen交易报送信息(TrdDepoDuration001Dto entity) {
        return null;
    }

    public List<PtlTrade> gen组合交易(TrdDepoDuration001Dto entity,
                                  List<WflTrdProductDeal> wflTrdProductDeals) {
        List<PtlTrade> result = new ArrayList<>();
        wflTrdProductDeals.forEach(wflTrdProductDeal -> {
            PtlTrade ptlTrade2 = new PtlTrade();
            ptlTrade2.setPortfolioId(entity.getPortfolioId());
            ptlTrade2.setBusiNo(wflTrdProductDeal.getTradeId());
            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade2.setBusiId(entity.getFinprodId());
            ptlTrade2.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
            ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
            ptlTrade2.setSettleDate(new DateTime(entity.getTradeDate()).toDate());
            result.add(ptlTrade2);
        });
        return result;
    }

    /**
     * 保存及启动工作流
     * <功能详细描述>
     *
     * @param entity 业务entity
     * @param type   操作类型
     * @see
     * @since 1.0
     */
    public void saveDataWhenStartWorkflow(TrdDepoDuration001Dto entity, OperationType type) {
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            // 插入Entity到对应的业务流程DB中
            if (entity.getNeedSkipInsertWfl() == null || !entity.getNeedSkipInsertWfl()) {
                restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/doInsert",
                        entity, String.class);
            }
            if (type != OperationType.REVOKE) {
                execute交易共通(entity, DictCodeListExt1.交易流程状态_流程中);
            }
        } else if (type == OperationType.REVOKE) {
            restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/doRevoke", entity,
                    String.class);
        }
    }

    /**
     * 交易共通
     * <功能详细描述>
     *
     * @param entity 业务entity
     * @param 交易流程状态 流程状态
     * @see
     * @since 1.0
     */
    public void execute交易共通(TrdDepoDuration001Dto entity, DictCodeListExt1 交易流程状态) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());

        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
                TrdProductDeal deal = baseTradeInfoDto.getTrdProductDeal();
                if (deal == null) {
                    deal = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                    TrdProductDeal dealExe = new TrdProductDeal();
                    BeanUtils.copyProperties(deal, dealExe);
                    dealExe.setTradeId(entity.getTotleTradeId());
                    //BS1.9
                    if (baseTradeInfoDto.getTrdFinProductDealTotal() != null) {
                        dealExe.setPs(baseTradeInfoDto.getTrdFinProductDealTotal().getPs());
                    }
                    setDeal(entity, 交易流程状态, baseTradeProductDealDtos, baseTradeInfoDto, baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe);
                } else {
                    TrdProductDeal dealExe = new TrdProductDeal();
                    BeanUtils.copyProperties(deal, dealExe);
                    dealExe.setTradeId(entity.getTotleTradeId());
                    //BS1.9
                    if (baseTradeInfoDto.getBaseTrade() == null || baseTradeInfoDto.getBaseTrade().getTrdProductDeal() == null) {
                        if (baseTradeInfoDto.getTrdFinProductDealTotal() != null) {
                            dealExe.setPs(baseTradeInfoDto.getTrdFinProductDealTotal().getPs());
                        }
                        setDeal(entity, 交易流程状态, baseTradeProductDealDtos, baseTradeInfoDto, baseTradeInfoDto.getTrdProductDealAdd(), dealExe);
                    }

                    if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                        TrdProductDeal deal1 = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                        TrdProductDeal dealExe1 = new TrdProductDeal();
                        BeanUtils.copyProperties(deal1, dealExe1);
                        dealExe1.setTradeId(entity.getTotleTradeId());
                        if (baseTradeInfoDto.getTrdFinProductDealTotal() != null) {
                            dealExe1.setPs(baseTradeInfoDto.getTrdFinProductDealTotal().getPs());
                        }
                        setDeal(entity, 交易流程状态, baseTradeProductDealDtos, baseTradeInfoDto, baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe1);
                    }
                }
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        } else {
            boolean isTqzq = false;
            if (CollectionUtils.isEmpty(entity.getFinAssetIntInfoList())) {
                isTqzq = true;
            }
            List<BaseTradeProductDealDto> baseTradeProductDealDtos1 = periodCommonService.findEffectiveDeal(entity.getpTradeId(), 交易流程状态, isTqzq);
            for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtos1) {
                TrdProductDeal deal1 = baseTradeProductDealDto.getTrdProductDeal();
                if(StringUtil.isNotEmpty( deal1.getFinprodId())){
                	Map<String, Object> pageParam = new HashMap<>();
                	pageParam.put("finprodId",  deal1.getFinprodId());
                	List<SelectOptionDto> selectOptionDto = restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getChannelIdAndCollectTypeForAsset",
                			pageParam , SelectOptionDto.class);
                	if(!StringUtil.isEmptyList(selectOptionDto)){
                		//设置通道id  撤销通道持仓
                		deal1.setChlId(selectOptionDto.get(0).getId());
                	}
                }
//                设置汇总交易编号 保持一致
                deal1.setTradeId(entity.getTotleTradeId());
                deal1.setShareAmt(deal1.getPrinAmt());
                deal1.setCreateUser(tradeDto.getCreateUser());
                deal1.setCreateTime(tradeDto.getCreateTime());
                deal1.setCreateDept(tradeDto.getCreateDept());
                deal1.setUpdateTime(tradeDto.getUpdateTime());
                deal1.setUpdateUser(tradeDto.getUpdateUser());
                if (baseTradeProductDealDto.getStlPayOrdersList() != null && baseTradeProductDealDto.getStlPayOrdersList().size() > 0) {
                    baseTradeProductDealDto.getStlPayOrdersList().forEach(item -> {
                        item.setTradeId(entity.getTotleTradeId());
                    });
                }
                getInterfInputDto(entity, baseTradeProductDealDto, DictCodeListExt1.交易流程状态_撤销);
            }
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos1);
        }
        baseTradeProductDealCommServiceImpl.execute(tradeDto);
//        调用子组合共通
        execute交易共通子组合(entity, 交易流程状态, entity.getpTradeId());
    }

    private void execute交易共通子组合(TrdDepoDuration001Dto entity, DictCodeListExt1 交易流程状态, String tradeId) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
//                    子组合调用
                if (baseTradeInfoDto.getBaseTradeInfoDtoList() != null) {
                    baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(x -> {
                        List<BaseTradeProductDealDto> temp = new ArrayList<>();
                        List<TrdProductDeal> deals = periodCommonService.getCommonExcuteDtoByTrade(x, temp);
                        temp.forEach(dealDto -> {
                            if (DictCodeList.交易方向_还本.getValue().equals(dealDto.getTrdProductDeal().getPs())
                                    || DictCodeList.交易方向_到期.getValue().equals(dealDto.getTrdProductDeal().getPs())) {
                                dealDto.setStlPayOrdersList(null);
                            } else if (DictCodeList.交易方向_收息.getValue().equals(dealDto.getTrdProductDeal().getPs())) {
                                dealDto.getTrdProductDeal().setPs(DictCodeList.交易方向_到期.getValue());
                            }
                            dealDto.getTrdProductDeal().setTradeId(entity.getTotleTradeId());
//                        利息时 持仓金额应该不入
                            dealDto.getTrdProductDeal().setShareAmt(dealDto.getTrdProductDeal().getPrinAmt() == null ? BigDecimal.ZERO : dealDto.getTrdProductDeal().getPrinAmt());
                        });
                        baseTradeProductDealDtos.addAll(temp);
                        temp.clear();
                    });
                }
            });
            // 调用投管接口 add by qcx 20190206 start
            baseTradeProductDealDtos.forEach(baseTradeProductDealDto -> {
                getInterfInputDto(entity, baseTradeProductDealDto, DictCodeListExt1.画面数据状态_新建);
            });
            // 调用投管接口 add by qcx 20190206 end
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("statusValid", 交易流程状态.getValue());
            map.put("statusValid03", DictCodeListExt1.交易流程状态_撤销.getValue());
            map.put("bizCode", tradeId);
            if (CollectionUtils.isEmpty(entity.getFinAssetIntInfoList())) {
                map.put("isTqzq", BussinessDictCodeList.是否_是.getValue());
            }
            baseTradeProductDealDtos.addAll(periodCommonService.getChiledTradeProductDealInfo(map));
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            baseTradeProductDealDtos.forEach(temp -> {
                if (temp.getStlPayOrdersList() != null && temp.getStlPayOrdersList().size() > 0) {
                    temp.getStlPayOrdersList().forEach(item -> {
                        item.setTradeId(temp.getTrdProductDeal().getTradeId());
                    });
                }
                temp.getTrdProductDeal().setTradeId(tradeId+'R');
            });
        }
        // 调用投管接口 add by qcx 20190206 start
        baseTradeProductDealDtos.forEach(baseTradeProductDealDto -> {
            getInterfInputDto(entity, baseTradeProductDealDto, DictCodeListExt1.交易流程状态_撤销);
        });
        // 调用投管接口 add by qcx 20190206 end
        baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
    }

    private void getInterfInputDto(TrdDepoDuration001Dto entity, BaseTradeProductDealDto baseTradeProductDealDto,
                                   DictCodeListExt1 交易流程状态) {
        if (baseTradeProductDealDto.getTrdProductDealAdd() == null
                || StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())
                || AllTradeCommonUtil.isNotActPortfolio(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())) {
            return;
        }

        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("TrdDepoDuration002");
        investInterfCommDto.setTradeStatus(交易流程状态.getValue());
        investInterfCommDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        if (StringUtil.equals(entity.getOpType(), "draw")) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_存款提前支取.getValue());
        }
        else {
            paTrdProductDeal.setPaBussType(entity.getPingAnCodeSin());
        }
        investInterfCommDto.setPaTrdProductDeal(paTrdProductDeal);
        if (baseTradeProductDealDto.getStlPayOrdersList() != null
                && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
            investInterfCommDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList().get(0));
        }
        investInterfCommService.doInvestInterf(investInterfCommDto);
    }

    /**
     * 组装共通的交易entity
     * <功能详细描述>
     *
     *
     * @param entity
     * @param 交易流程状态
     * @param baseTradeProductDealDtos 交易dto
     * @param baseTradeInfoDto         交易dto
     * @param add                      扩展
     * @param dealExe                  交易dto
     * @see
     * @since 1.0
     */
    private void setDeal(TrdDepoDuration001Dto entity, DictCodeListExt1 交易流程状态, List<BaseTradeProductDealDto> baseTradeProductDealDtos, BaseTradeInfoDto baseTradeInfoDto, TrdProductDealAdd add, TrdProductDeal dealExe) {
        dealExe.setShareAmt(dealExe.getPrinAmt() == null ? BigDecimal.ZERO : dealExe.getPrinAmt()); // 调共通时，持仓改变的是还本金额，共通取得是shareAmt字段
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(dealExe);
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        StlPayOrdersDto orders = new StlPayOrdersDto();
        if(null!=baseTradeInfoDto.getStlPayOrders()){
            BeanUtils.copyProperties(baseTradeInfoDto.getStlPayOrders(), orders);
            baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(new StlPayOrdersDto[]{orders}));
        }
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        getInterfInputDto(entity, baseTradeProductDealDto, DictCodeListExt1.交易流程状态_撤销);
    }
//    public void saveDataWhenStartWorkflow(TrdDepoDuration001Dto entity, OperationType type) {
//        if ("submit".equals(entity.getOperateType())||type.equals(OperationType.REVOKE)) {
//            // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
//            WflTrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);
//
//            // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
//            List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);
//
//            // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
//            WflPaTrdFinDealTotal wflPaTrdFinDealTotal = gen汇总扩展业务流程(entity,
//                trdFinProductDealTotal);
//
//            // 金融产品交易Entity -> 金融产品交易业务流程表
//            List<WflTrdProductDeal> wflTrdProductDeals = gen交易业务流程(entity);
//
//            // 插入Entity对应的业务流程DB中
//            bussCommonMapperDelegateService.insertAutoClassifyBatch(wflTrdProductDeals,
//                wflPaTrdFinDealTotal, trdFinProductDealTotal,stlPayOrdersDtos);
//            // 调用业务组件.【金融产品交易共通.交易产品接口】
//            FinProductTradeDto tradeDto = genFinProductTradeDto(entity);
//            tradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
//            baseTradeProductDealCommServiceImpl.execute(tradeDto);
//        }
//    }

    /**
     * 金融产品交易
     * <功能详细描述>
     *
     * @param trdBondDuration001Dto
     * @return
     * @see
     * @since 1.0
     */
    public FinProductTradeDto genFinProductTradeDto(TrdDepoDuration001Dto trdBondDuration001Dto) {
        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        TrdProductDeal trdProductDeal = getTrdProductDeal(trdBondDuration001Dto);

        // 划款指令表Entity
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
                periodCommonService.transformOrderDto(trdBondDuration001Dto.getBussTrdTransferOrder()));
        List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrdersDto dto = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                stlPayOrdersDtos.add(dto);
            });
        }
        TrdProductDealAdd add = new TrdProductDealAdd();
        add.setPortfolioId(trdBondDuration001Dto.getPortfolioId());
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
        baseTradeProductDealDto.setStlPayOrdersList(stlPayOrdersDtos);
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }

    /**
     * 为调用共通组件组装的entity
     *
     * @param entity
     * @return
     */
    public static TrdProductDeal getTrdProductDeal(TrdDepoDuration001Dto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(entity.getBusiCode());
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
        trdProductDeal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_到期.getValue());
        trdProductDeal.setTradeDate(entity.getTradeDate());
        trdProductDeal.setVdate(entity.getVdateProd());
        trdProductDeal.setMdate(entity.getMdateProd());
        trdProductDeal.setSettleDate(entity.getTradeDate());
        trdProductDeal.setShareAmt(ArithmeticUtil.subtract(entity.getTradeAmt(), entity.getIntAmt()));
        trdProductDeal.setPrinAmt(ArithmeticUtil.subtract(entity.getTradeAmt(), entity.getIntAmt()));
        trdProductDeal.setIntAmt(entity.getIntAmt());
        trdProductDeal.setTradeAmt(entity.getTradeAmt());
        trdProductDeal.setTradeMarket(entity.getTradeMarket());
//        trdProductDeal.setCounterId(entity.getCounterId());
//        trdProductDeal.setTrader(entity.getTrader());
//        trdProductDeal.setCounterTrader(entity.getCounterTrader());
        trdProductDeal.setSecManageAcctId("999999");

        // 一体化对应etliu start
        trdProductDeal.setFpriceAmt(trdProductDeal.getTradeAmt());
        // 一体化对应etliu end

        return trdProductDeal;
    }

    public List<WflTrdProductDeal> gen交易业务流程(TrdDepoDuration001Dto entity) {
        return autoPeriodDepoCommonService.gen交易业务流程(entity);
    }

    /**
     * 汇总交易扩展
     * <功能详细描述>
     *
     * @param entity
     * @param trdFinProductDealTotal
     * @return
     * @see
     * @since 1.0
     */
    public WflPaTrdFinDealTotal gen汇总扩展业务流程(TrdDepoDuration001Dto entity,
                                            WflTrdFinProductDealTotal trdFinProductDealTotal) {
        return AutoPeriodDepoCommonService.gen汇总扩展业务流程(entity, trdFinProductDealTotal);
    }

    /**
     * 转换划款指令dto
     * <功能详细描述>
     *
     * @param stlPayOrdersList 划款指令
     * @param total            汇总交易
     * @param entity           业务entity
     * @return
     * @see
     * @since 1.0
     */
    public StlPayOrders gen划款指令流程(List<BussTrdTransferOrderDto> stlPayOrdersList, TrdFinProductDealTotal total, TrdDepoDuration001Dto entity) {
        StlPayOrders stlPayOrders = null;

        String portfolioId = "";
        String tradeId = "";
        if (total != null) {
            portfolioId = total.getPortfolioId();
            tradeId = total.getTotleTradeId();
        } else if (entity != null) {
            portfolioId = entity.getPortfolioId();
            tradeId = entity.getBusiCode();
        }

        for (BussTrdTransferOrderDto dto : stlPayOrdersList) {
            if (dto.getPortfolioId().equals(portfolioId)) {
                stlPayOrders = dto;
                stlPayOrders.setOrderStatus(DictCodeList.清算操作类型_新建.getValue());
                stlPayOrders.setTradeId(tradeId);
                stlPayOrders.setWorkflowId(entity==null?null:entity.getWorkflowId());
            }
        }
        return stlPayOrders;
    }
//    public List<WflStlPayOrders> gen划款指令流程(TrdDepoDuration001Dto entity) {
//        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
//            entity.getBussTrdTransferOrder());
//        List<WflStlPayOrders> stlPayOrdersDtos = new ArrayList<>();
//        if (stlPayOrdersList != null) {
//            stlPayOrdersList.forEach(stlPayOrders -> {
//                WflStlPayOrders dto = new WflStlPayOrders();
//                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
//                dto.setTradeId(entity.getBusiCode());
//                dto.setWorkflowId(entity.getWorkflowId());
//                dto.setWorkflowStatus(DictCodeList.数据状态_新建中.getValue());
//                dto.setCreateDept(entity.getCreateDept());
//                dto.setUpdateTime(entity.getUpdateTime());
//                dto.setCreateUser(entity.getCreateUser());
//                dto.setCreateTime(entity.getCreateTime());
////              撤销
//                if(StringUtil.equals(OperationType.REVOKE.toString(), entity.getMode())) {
//                    dto.setpTradeId(entity.getpTradeId());
//                    dto.setOrderStatus(DictCodeListExt1.指令状态_撤销.getValue());
//                }
//                stlPayOrdersDtos.add(dto);
//            });
//        }
//        return stlPayOrdersDtos;
//    }

    /**
     * 汇总业务流程
     * <功能详细描述>
     *
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public static WflTrdFinProductDealTotal gen汇总流程表(TrdDepoDuration001Dto entity) {
        return AutoPeriodDepoCommonService.gen汇总流程表(entity);
    }
}
