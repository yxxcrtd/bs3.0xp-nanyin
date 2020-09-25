/*
 * 文件名：TrdFundDurationDealEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：丁志鹏
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdfundobversion.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfundobversion.dto.TrdFundObversionDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理entity所用service 〈功能详细描述〉
 * 
 * @author 王学磊
 * @version 1.0 2019年11月1日
 * @see TrdFundObversionDealEntity
 * @since 1.0
 */
@Service
public class TrdFundObversionDealEntity extends BaseService<TrdFundObversionDto> {
    /**
     * 共通mapper处理
     */
    /**
     * 共通交易
     */
    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;
    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;
    @Autowired
    private FinProductMapper finProductMapper;


    /**
     * 处理在代办中提交及保存的操作
     *  @param entity 业务实体
     * @param type   操作类型
     * @param 交易流程状态
     */
    public void saveDataWhenStartWorkflow(TrdFundObversionDto entity, OperationType type, String 交易流程状态) {
    	if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            // 插入Entity到对应的业务流程DB中
            if (entity.getNeedSkipInsertWfl() == null || !entity.getNeedSkipInsertWfl()) {
                entity.getBaseTotalTradeInfoDto().setBack(entity.isBack());
                restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/doInsert",
                    entity, String.class);
            }
            if (type != OperationType.REVOKE) {
                if (!DictCodeListExt1.交易流程状态_流程中.getValue().equals(交易流程状态)) {
                    execute交易共通(entity, DictCodeListExt1.交易流程状态_有效);
                }
                else {
                    execute交易共通(entity, DictCodeListExt1.交易流程状态_流程中);
                }
            }
        }
        else if (type == OperationType.REVOKE) {
            restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundObversion/doRevoke", entity,
                String.class);
        }
    }

    /**
     * 调用共通
     * @param entity 实例
     * @param 交易流程状态 交易状态
     */
    public void execute交易共通(TrdFundObversionDto entity, DictCodeListExt1 交易流程状态) {
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
                getCommonExcuteDtoByTrade(baseTradeInfoDto,baseTradeProductDealDtos);
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            baseTradeProductDealCommServiceImpl.execute(tradeDto);
            execute交易共通子组合(entity,交易流程状态, false);
        } else {
            Map<String,Object>map = new HashMap<>();
            map.put("statusValid", 交易流程状态.getValue());
            map.put("statusValid03", DictCodeListExt1.交易流程状态_撤销.getValue());
            map.put("bizCode", entity.getpTradeId());
            map.put("YON_Y", BussinessDictCodeList.是否_是.getValue());
            List<BaseTradeProductDealDto> baseTradeProductDealDtos1 = restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundObversion/findEffectiveDeal",
                    map, BaseTradeProductDealDto.class);
            for (BaseTradeProductDealDto baseTradeProductDealDto : baseTradeProductDealDtos1) {
                TrdProductDeal deal1 = baseTradeProductDealDto.getTrdProductDeal();
                deal1.setTradeId(deal1.getTradeId() + 'R');
                deal1.setCreateUser(tradeDto.getCreateUser());
                deal1.setCreateTime(tradeDto.getCreateTime());
                deal1.setCreateDept(tradeDto.getCreateDept());
                deal1.setUpdateTime(tradeDto.getUpdateTime());
                deal1.setUpdateUser(tradeDto.getUpdateUser());
            }
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos1);
            baseTradeProductDealCommServiceImpl.execute(tradeDto);
            execute交易共通子组合(entity,交易流程状态, false);
        }
    }
    /**
     * 根据交易dto组装共同dto
     * @param baseTradeInfoDto 交易dto
     * @param baseTradeProductDealDtos 共同dto list
     */
    private void getCommonExcuteDtoByTrade(BaseTradeInfoDto baseTradeInfoDto , List<BaseTradeProductDealDto> baseTradeProductDealDtos) {
        TrdProductDeal deal = baseTradeInfoDto.getTrdProductDeal();
        if (deal == null) {
            deal = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
            TrdProductDeal dealExe = new TrdProductDeal();
            BeanUtils.copyProperties(deal, dealExe);
            setDeal(baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe);
        } else {
            TrdProductDeal dealExe = new TrdProductDeal();
            BeanUtils.copyProperties(deal, dealExe);
            setDeal(baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getTrdProductDealAdd(), dealExe);

            if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                TrdProductDeal deal1 = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                TrdProductDeal dealExe1 = new TrdProductDeal();
                BeanUtils.copyProperties(deal1, dealExe1);
                setDeal(baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe1);
            }
        }
    }
    /**
     *
     */
//        子组合调用共同
    public void execute交易共通子组合(TrdFundObversionDto entity, DictCodeListExt1 交易流程状态, boolean 折算交易){
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());

        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        // 调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
//                    子组合调用
                baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(x->{
                    getCommonExcuteDtoByTrade(x,baseTradeProductDealDtos);
                });
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        else {
            Map<String,Object> map = new HashMap<>();
            map.put("statusValid", 交易流程状态.getValue());
            map.put("statusValid03", DictCodeListExt1.交易流程状态_撤销.getValue());
            map.put("isFund", Boolean.TRUE);
            if (折算交易) {
                map.put("bTrade", entity.getpTradeId());
            }
            else {
                map.put("bizCode", entity.getpTradeId());
            }
            List<BaseTradeInfoDto> baseTradeInfoDtoList = entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();//获得母组合
            for (BaseTradeInfoDto baseDto:baseTradeInfoDtoList) {
                List<BaseTradeInfoDto> baseTradeInfoDtoList1 = baseDto.getBaseTradeInfoDtoList();//子组合
                for (BaseTradeInfoDto baseTradeInfoDto:baseTradeInfoDtoList1) {
                    BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
                    BeanUtil.copyPropertiesIgnoreNull(baseTradeProductDealDto, baseTradeInfoDto);
                    baseTradeProductDealDtos.add(baseTradeProductDealDto);
                }
            }
            baseTradeProductDealDtos.addAll(baseTradeProductDealDtos);
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
    }
    private void setDeal(List<BaseTradeProductDealDto> baseTradeProductDealDtos, BaseTradeInfoDto baseTradeInfoDto, TrdProductDealAdd add, TrdProductDeal dealExe) {
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(dealExe);
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
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
    public BaseTotalTradeInfoDto genBaseTotalTradeInfoDto(TrdFundObversionDto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);
        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展业务流程(entity, trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        TrdDealTotalAdd trdDealTotalAdd = gen交易汇总附属(entity);
        result.setTrdDealTotalAdd(trdDealTotalAdd);
        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
//        根据利息明细处理汇总及交易dto
        List<FinAssetIntInfo> 利息明细 = entity.getFinAssetIntInfoList();
        利息明细.forEach(finAssetIntInfo -> {
            BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
            //获取母组合的折算前和折算后的差额
            BigDecimal subtract = ArithmeticUtil.subtract(finAssetIntInfo.getAconShareAmt(), finAssetIntInfo.getBconShareAmt());
            TrdProductDeal deal = 生成交易(entity, finAssetIntInfo,subtract);
            baseTradeInfoDto.setTrdProductDeal(deal);
            TrdProductDealAdd dealAdd = 生成交易附属(entity, finAssetIntInfo, deal);
            baseTradeInfoDto.setTrdProductDealAdd(dealAdd);
            PaTrdProductDeal paDeal = 生成交易扩展(entity, finAssetIntInfo, deal);
            baseTradeInfoDto.setPaTrdProductDeal(paDeal);
            baseTradeInfoDto.setPtlTrade(生成组合交易(entity, finAssetIntInfo, deal));
            //            生成划款指令
//            StlPayOrders stl = periodCommonService.gen划款指令流程(stlPayOrdersList, baseTradeInfoDto.getTrdProductDealAdd(), entity.getWorkflowId());
//            baseTradeInfoDto.setStlPayOrders(stl);
            //            生成子组合利息明细共同方法
            Map<String,Object> subMap = new HashMap<>();
            subMap.put("portfolioId",finAssetIntInfo.getPortfolioId());
//            持仓时间
            subMap.put("dealDate", DateUtil.format(entity.getConversionTime()));
//            是否提前
            subMap.put("isAdvance", "N");
//            资产代码
            subMap.put("finprodMarketId",entity.getFinprodMarketId());
//            自定义不累加
            Map<String, BigDecimal> defaultMap = new HashMap<>();
            defaultMap.put("unitCprice",finAssetIntInfo.getUnitCprice());
            subMap.put("defaultMap", JsonUtils.object2Json(defaultMap));
////            汇总交易编号
//            subMap.put("totleTradeId",entity.getTotleTradeId());
            Map<String, BigDecimal> preMap = new HashMap<>();
            preMap.put("qty",finAssetIntInfo.getQty());
            preMap.put("registShare",finAssetIntInfo.getRegistShare());
            preMap.put("tradeAmt",finAssetIntInfo.getTradeAmt());
            preMap.put("shareAmt",subtract);
//            划款指令结算金额
//            if(stl != null){
//                preMap.put("settleAmt",stl.getSettleAmt());
//            }
            subMap.put("preMap", JsonUtils.object2Json(preMap));
            subMap.put(GlobalConstant.WORKFLOW_ID,entity.getWorkflowId());
            List<FinAssetIntInfo> 子组合利息明细 = autoPeriodCommonService.distributeDataByPercent(subMap);
            List<BaseTradeInfoDto> subBaseTradeInfoDtoList = new ArrayList<>();
//            子组合交易没有处理前置交易编号如果需要再说
//            TODO
            子组合利息明细.forEach(subFinAssetIntInfo -> {
                BaseTradeInfoDto subBaseTradeInfoDto = new BaseTradeInfoDto();
                BigDecimal subtractSub = ArithmeticUtil.doAdd(subFinAssetIntInfo.getAmount(), subFinAssetIntInfo.getShareAmt());
//                        设置组合编号
                subFinAssetIntInfo.setPortfolioId(subFinAssetIntInfo.getSubPortfolioId());
                TrdProductDeal subDeal = 生成交易(entity, subFinAssetIntInfo,subFinAssetIntInfo.getShareAmt());
                subBaseTradeInfoDto.setTrdProductDeal(subDeal);
                TrdProductDealAdd subDealAdd = 生成交易附属(entity, subFinAssetIntInfo, subDeal);
                subBaseTradeInfoDto.setTrdProductDealAdd(subDealAdd);
                PaTrdProductDeal subPaDeal = 生成交易扩展子组合(entity, subFinAssetIntInfo, subDeal,subtractSub);
                subBaseTradeInfoDto.setPaTrdProductDeal(subPaDeal);
                subBaseTradeInfoDto.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal));
                subBaseTradeInfoDtoList.add(subBaseTradeInfoDto);
            });
//            设置子组合交易
            baseTradeInfoDto.setBaseTradeInfoDtoList(subBaseTradeInfoDtoList);
            baseTradeInfoDtoList.add(baseTradeInfoDto);
        });
        return result;
    }

    /**
	 * 生成组合交易
	 * <功能详细描述>
	 * @param entity 业务实体
	 * @param finAssetIntInfo 利息明细
	 * @param deal 交易
	 * @return 生成结果
	 * @see
	 * @since 1.0
	 */
	private List<PtlTrade> 生成组合交易(TrdFundObversionDto entity, FinAssetIntInfo finAssetIntInfo, TrdProductDeal deal) {
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade =new PtlTrade();
		ptlTrade.setPortfolioId(finAssetIntInfo.getPortfolioId());
		ptlTrade.setBusiNo(deal.getTradeId());
		ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
		ptlTrade.setBusiId(entity.getFinprodId());
		ptlTrade.setBusiType(DictCodeList.共通业务类型_基金类资产存续期.getValue());
		ptlTrade.setSecManageAcctId(finAssetIntInfo.getSecAcctId());
		ptlTrade.setSettleDate(deal.getTradeDate());
		ptlTrades.add(ptlTrade);
		return ptlTrades;
	}

	/**
     * 生成交易扩展 <功能详细描述>
     * 
     * @param entity          业务实体
     * @param finAssetIntInfo 利息明细
     * @param deal            交易dto
     * @return 生成结果
     * @see
     * @since 1.0
     */
    private PaTrdProductDeal 生成交易扩展(TrdFundObversionDto entity, FinAssetIntInfo finAssetIntInfo,
            TrdProductDeal deal) {
        PaTrdProductDeal padeal = new PaTrdProductDeal();
        padeal.setTradeId(deal.getTradeId());
        if(StringUtils.equals(entity.getConversionType(),DictCodeListExt1.折算类型_货币基金份额折算.getValue())){
            padeal.setPaBussType("132");
        }else {
            padeal.setPaBussType("131");
        }
//        padeal.padeal(DictCodeList.交易方向_份额折算.getValue());
        padeal.setStatus(entity.getStatus());
        padeal.setWorkflowId(entity.getWorkflowId());
        //折算前份额
        padeal.setBconShareAmt(finAssetIntInfo.getBconShareAmt());
        //折算后份额
        padeal.setAconShareAmt(finAssetIntInfo.getAconShareAmt());
        return padeal;
    }

    private PaTrdProductDeal 生成交易扩展子组合(TrdFundObversionDto entity, FinAssetIntInfo finAssetIntInfo,
                                    TrdProductDeal deal,BigDecimal acon) {
        PaTrdProductDeal padeal = new PaTrdProductDeal();
        padeal.setTradeId(deal.getTradeId());
        if(StringUtils.equals(entity.getConversionType(),DictCodeListExt1.折算类型_货币基金份额折算.getValue())){
            padeal.setPaBussType("132");
        }else {
            padeal.setPaBussType("131");
        }
//        padeal.padeal(DictCodeList.交易方向_份额折算.getValue());
        padeal.setStatus(entity.getStatus());
        padeal.setWorkflowId(entity.getWorkflowId());
        //折算前份额
        padeal.setBconShareAmt(finAssetIntInfo.getAmount());
        //折算后份额
        padeal.setAconShareAmt(acon);
        return padeal;
    }

    /**
     * 生成交易附属 <功能详细描述>
     * 
     * @param entity          业务entity
     * @param finAssetIntInfo 利息明细
     * @param deal            生成的交易
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd 生成交易附属(TrdFundObversionDto entity, FinAssetIntInfo finAssetIntInfo,
            TrdProductDeal deal) {
    	TrdProductDealAdd dealAdd = new TrdProductDealAdd();
      dealAdd.setTradeId(deal.getTradeId());
      dealAdd.setPortfolioId(finAssetIntInfo.getPortfolioId());
//      理财产品代码 金融产品代码
//      dealAdd.pr(entity.getFinprodId());
      dealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
      dealAdd.setStatus(entity.getStatus());
      dealAdd.setWorkflowId(entity.getWorkflowId());
        return dealAdd;
    }

    /**
     * 
     * 生成交易 <功能详细描述>
     * 
     * @param entity          业务entity
     * @param finAssetIntInfo 利率信息
     * @return 交易dto
     * @see
     * @since 1.0
     */
    private TrdProductDeal 生成交易(TrdFundObversionDto entity, FinAssetIntInfo finAssetIntInfo,BigDecimal shareAmt) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTotleTradeId(entity.getTotleTradeId());
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_基金存续期折算));
        // 交易编号
        trdProductDeal.setTradeId(autoPeriodCommonService
                .buildBussinessCode(codeParameter));
        trdProductDeal.setBusiType(DictCodeListExt2.共通业务类型_基金存续期折算.getValue());
//      金融产品代码
        if(StringUtil.isNoneBlank(entity.getFinprodId())){
            trdProductDeal.setFinprodId(entity.getFinprodId());
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(entity.getFinprodId());
            if(finProduct!=null){
                trdProductDeal.setTradeMarket(finProduct.getTradeMarket());//设置交易场所
            }
        }
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        trdProductDeal.setRemark(entity.getRemark());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
//      金融产品类型
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        trdProductDeal.setTradeDate(entity.getConversionTime());
//        BigDecimal subtract = ArithmeticUtil.subtract(finAssetIntInfo.getAconShareAmt(), finAssetIntInfo.getBconShareAmt());
        if(shareAmt.compareTo(BigDecimal.ZERO)==1){////大于
            trdProductDeal.setPs(DictCodeList.交易方向_份额强增.getValue());
        }else if(shareAmt.compareTo(BigDecimal.ZERO)==-1){//小于
            trdProductDeal.setPs(DictCodeList.交易方向_份额强减.getValue());
        }
        trdProductDeal.setShareAmt(shareAmt.abs());//取绝对值
//      币种
        trdProductDeal.setCcy(entity.getCcy());
//
//    日期与交易日一致
        trdProductDeal.setSettleDate(trdProductDeal.getTradeDate());
        trdProductDeal.setSecManageAcctId(finAssetIntInfo.getSecAcctId());

        // 通道交易 新增 强成西 2019/03/26 start
        trdProductDeal.setChlId("999999".equals(finAssetIntInfo.getChIld()) ? null : finAssetIntInfo.getChIld());
        // 通道交易 新增 强成西 2019/03/26 end
//        if(StringUtils.equals(entity.getConversionType(),DictCodeListExt1.折算类型_货币基金份额折算.getValue())){
//            trdProductDeal.setTgBusiType("132");
//        }else {
//            trdProductDeal.setTgBusiType("131");
//        }
        return trdProductDeal;
    }

    /**
     * 
     * 2.0改造生成汇总扩展交易entity <功能详细描述>
     * 
     * @param entity                 业务entity
     * @param trdFinProductDealTotal 汇总entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal gen汇总扩展业务流程(TrdFundObversionDto entity,
            TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal paTrdTotal = new PaTrdFinProductDealTotal();
        paTrdTotal.setTotleTradeId(entity.getTotleTradeId());
        if(StringUtils.equals(entity.getConversionType(),DictCodeListExt1.折算类型_货币基金份额折算.getValue())){
            paTrdTotal.setPaBussType("132");
        }else {
            paTrdTotal.setPaBussType("131");
        }
//        paTrdTotal.setPaBussType(DictCodeList.交易方向_份额折算.getValue());
        paTrdTotal.setRegDate(entity.getConversionTime());
        paTrdTotal.setWorkflowId(entity.getWorkflowId());
        return paTrdTotal;
    }

    private TrdDealTotalAdd gen交易汇总附属(TrdFundObversionDto entity){
        TrdDealTotalAdd add = new TrdDealTotalAdd();
        add.setTotleTradeId(entity.getTotleTradeId());
        add.setConversionRat(entity.getConversionRat());
        add.setBconUnitCprice2(entity.getBconUnitCprice());
        add.setConversionType(entity.getConversionType());
        add.setWorkflowId(entity.getWorkflowId());
        add.setMpFinprodId(entity.getFinprodId());
        return add;
    }


    /**
     * 
     * 2.0改造生成汇总交易entity <功能详细描述>
     * 
     * @param entity 源entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    private TrdFinProductDealTotal gen汇总流程表(TrdFundObversionDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTotleTradeId());
        trdFinProductDealTotal.setbTradeId(entity.getTotleTradeId());
//      交易编号
        trdFinProductDealTotal.setBusiType(DictCodeListExt2.共通业务类型_基金存续期折算.getValue());
//      金融产品代码
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        trdFinProductDealTotal.setRemark(entity.getRemark());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
//    TODO
//    trdFinProductDealTotal.setFinprodType(finprodType);
        // 金融产品类型
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
//      币种
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(DictCodeListExt2.交易方向_份额折算.getValue());
        trdFinProductDealTotal.setTradeDate(entity.getConversionTime());
        trdFinProductDealTotal.setShareAmt(entity.getTotalAmount());//交易份额
        trdFinProductDealTotal.setUnitCprice(entity.getAfterUnitCprice());//折算后净价
        if(StringUtils.equals(entity.getConversionType(),DictCodeListExt1.折算类型_货币基金份额折算.getValue())){
            trdFinProductDealTotal.setTgBusiType("132");
        }else {
            trdFinProductDealTotal.setTgBusiType("131");
        }
//    日期与交易日一致
        trdFinProductDealTotal.setSettleDate(trdFinProductDealTotal.getTradeDate());
        // 通道交易 新增 强成西 2019/03/26 start
        trdFinProductDealTotal.setChlId("999999".equals(entity.getChlId()) ? null : entity.getChlId());
        // 通道交易 新增 强成西 2019/03/26 end
        // 多级法人改造 add by caiqun 20200622
        trdFinProductDealTotal.setInstitutionCode(CommonUtil.getOrgCode());
        // 多级法人改造 add by caiqun 20200622
        return trdFinProductDealTotal;
    }
}
