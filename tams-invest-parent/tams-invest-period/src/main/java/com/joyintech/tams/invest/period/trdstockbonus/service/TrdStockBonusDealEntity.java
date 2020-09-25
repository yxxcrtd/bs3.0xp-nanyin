/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author panbin
 * @version 2019年7月1日
 * @see TrdStockBonusDealEntity
 */
@Service
public class TrdStockBonusDealEntity extends BaseService<TrdStockBonus002Dto> {

	@Autowired
    private AutoPeriodCommonService autoPeriodCommonService;
	
	/**
     * 共通交易
     */
    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    @Autowired
    private InvestInterfCommService investInterfCommService;
    /**
     * 划款指令共通
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    @Autowired
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
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
	public BaseTotalTradeInfoDto genBaseTotalTradeInfoDto(TrdStockBonus002Dto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        //分红送转生成三条交易，其中现金分红需要划款指令
      /*  if(!StringUtil.equals(entity.getPs(), "01")) {
            result.setNeedStlPayOrders(false);
        }*/
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);
        // 金融产品交易汇总扩展Entity  -> 金融产品交易汇总扩展业务流程表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展业务流程(entity, trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票分红交易));
        //根据分红明细处理汇总及交易dto
        List<FinAssetIntInfo> dividDetailList = entity.getDividDetailInfoList();
        if(!StringUtil.isEmptyList(dividDetailList)){
            AtomicInteger i = new AtomicInteger(1);
            dividDetailList.forEach(dividDetailInfo -> {
                BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
                baseTradeInfoDtoList.add(baseTradeInfoDto);
                TrdProductDeal deal = 生成交易(entity, dividDetailInfo,"03");
                baseTradeInfoDto.setTrdProductDeal(deal);
                TrdProductDealAdd dealAdd = 生成交易附属(entity, dividDetailInfo, deal);
                baseTradeInfoDto.setTrdProductDealAdd(dealAdd);
                baseTradeInfoDto.setPtlTrade(生成组合交易(entity, dividDetailInfo, deal));
                StlPayOrders stlPayOrders = new StlPayOrders();
                //现金分红
                if(StringUtil.equals(entity.getPs(), "01")){
                    BaseTradeInfoDto baseTradeInfoDto1 = new BaseTradeInfoDto();
                    baseTradeInfoDtoList.add(baseTradeInfoDto1);
                    TrdProductDeal deal1 = 生成交易(entity, dividDetailInfo,"01");
                    deal1.setbTradeId(deal.getTradeId());
                    baseTradeInfoDto1.setTrdProductDeal(deal1);
                    TrdProductDealAdd dealAdd1 = 生成交易附属(entity, dividDetailInfo, deal1);
                    baseTradeInfoDto1.setTrdProductDealAdd(dealAdd1);
                    PaTrdProductDeal paDeal1 = 生成交易扩展(entity, dividDetailInfo, deal1);
                    baseTradeInfoDto1.setPaTrdProductDeal(paDeal1);
                    baseTradeInfoDto1.setPtlTrade(生成组合交易(entity, dividDetailInfo, deal1));
                    stlPayOrders =  gen划款指令流程(entity,deal1,dealAdd1);
                    stlPayOrders.setOrderId(stlPayOrders.getOrderId() + i.toString());
                    i.getAndIncrement();
                    baseTradeInfoDto1.setStlPayOrders(stlPayOrders);
                }
                //送转
                else if(StringUtil.equals(entity.getPs(), "02")){
                    BaseTradeInfoDto baseTradeInfoDto2 = new BaseTradeInfoDto();
                    baseTradeInfoDtoList.add(baseTradeInfoDto2);
                    TrdProductDeal deal2 = 生成交易(entity, dividDetailInfo,"02");
                    deal2.setbTradeId(deal.getTradeId());
                    baseTradeInfoDto2.setTrdProductDeal(deal2);
                    TrdProductDealAdd dealAdd2 = 生成交易附属(entity, dividDetailInfo, deal2);
                    baseTradeInfoDto2.setTrdProductDealAdd(dealAdd2);
                    PaTrdProductDeal paDeal2 = 生成交易扩展(entity, dividDetailInfo, deal2);
                    baseTradeInfoDto2.setPaTrdProductDeal(paDeal2);
                    baseTradeInfoDto2.setPtlTrade(生成组合交易(entity, dividDetailInfo, deal2));
                }else{
                    //分红送转同时生成3条交易信息
                    //现金分红
                    BaseTradeInfoDto baseTradeInfoDto1 = new BaseTradeInfoDto();
                    baseTradeInfoDtoList.add(baseTradeInfoDto1);
                    TrdProductDeal deal1 = 生成交易(entity, dividDetailInfo,"01");
                    deal1.setbTradeId(deal.getTradeId());
                    baseTradeInfoDto1.setTrdProductDeal(deal1);
                    TrdProductDealAdd dealAdd1 = 生成交易附属(entity, dividDetailInfo, deal1);
                    baseTradeInfoDto1.setTrdProductDealAdd(dealAdd1);
                    PaTrdProductDeal paDeal1 = 生成交易扩展(entity, dividDetailInfo, deal1);
                    baseTradeInfoDto1.setPaTrdProductDeal(paDeal1);
                    baseTradeInfoDto1.setPtlTrade(生成组合交易(entity, dividDetailInfo, deal1));
                    stlPayOrders =  gen划款指令流程(entity,deal1,dealAdd1);
                    stlPayOrders.setOrderId(stlPayOrders.getOrderId() + i.toString());
                    i.getAndIncrement();
                    baseTradeInfoDto1.setStlPayOrders(stlPayOrders);

                    //送转
                    BaseTradeInfoDto baseTradeInfoDto2 = new BaseTradeInfoDto();
                    baseTradeInfoDtoList.add(baseTradeInfoDto2);
                    TrdProductDeal deal2 = 生成交易(entity, dividDetailInfo,"02");
                    deal2.setbTradeId(deal.getTradeId());
                    baseTradeInfoDto2.setTrdProductDeal(deal2);
                    TrdProductDealAdd dealAdd2 = 生成交易附属(entity, dividDetailInfo, deal2);
                    baseTradeInfoDto2.setTrdProductDealAdd(dealAdd2);
                    PaTrdProductDeal paDeal2 = 生成交易扩展(entity, dividDetailInfo, deal2);
                    baseTradeInfoDto2.setPaTrdProductDeal(paDeal2);
                    baseTradeInfoDto2.setPtlTrade(生成组合交易(entity, dividDetailInfo, deal2));
                }
//                获取子组合交易
                //            生成子组合利息明细共同方法
                Map<String,Object> subMap = new HashMap<>();
                subMap.put("portfolioId",dividDetailInfo.getPortfolioId());
//            持仓时间
                subMap.put("dealDate", DateUtil.format(entity.getRegDate()));
//            资产代码
                subMap.put("finprodMarketId",entity.getFinprodMarketId());
//                证券账户
                subMap.put("chlId",dividDetailInfo.getSecAcctId());
//            是否提前
                subMap.put("isAdvance", "N");
                /*Map<String, String> defaultMap = new HashMap<>();
                defaultMap.put("secAcctId",dividDetailInfo.getSecAcctId());
                subMap.put("defaultMap", JsonUtils.object2Json(defaultMap)); */
////            汇总交易编号
                Map<String, BigDecimal> preMap = new HashMap<>();
                preMap.put("regDateAmt",dividDetailInfo.getRegDateAmt());
                preMap.put("fpriceAmt",dividDetailInfo.getFpriceAmt());
                preMap.put("shareAmt",dividDetailInfo.getShareAmt());
                StlPayOrders pStlPayOrders = new StlPayOrders();
                if(stlPayOrders != null){
                    BeanUtil.copyPropertiesIgnoreNull(pStlPayOrders,stlPayOrders);
                    preMap.put("settleAmt",pStlPayOrders.getSettleAmt());
                }
                subMap.put("preMap", JsonUtils.object2Json(preMap));
                subMap.put(GlobalConstant.WORKFLOW_ID,entity.getWorkflowId());
                List<BaseTradeInfoDto> subBaseTradeInfoDtoList = new ArrayList<>();
                baseTradeInfoDto.setBaseTradeInfoDtoList(subBaseTradeInfoDtoList);
                List<FinAssetIntInfo> 子组合利息明细 = autoPeriodCommonService.distributeDataByPercent(subMap);
                子组合利息明细.forEach(subFinAssetIntInfo->{
                    subFinAssetIntInfo.setPortfolioId(subFinAssetIntInfo.getSubPortfolioId());
                    BaseTradeInfoDto subBaseTradeInfoDto = new BaseTradeInfoDto();
                    subBaseTradeInfoDtoList.add(subBaseTradeInfoDto);
                    TrdProductDeal subDeal = 生成交易(entity, subFinAssetIntInfo,"03");
                    subBaseTradeInfoDto.setTrdProductDeal(subDeal);
                    TrdProductDealAdd subDealAdd = 生成交易附属(entity, subFinAssetIntInfo, subDeal);
                    subBaseTradeInfoDto.setTrdProductDealAdd(subDealAdd);
                    subBaseTradeInfoDto.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal));
                    StlPayOrders subStlPayOrders =  new StlPayOrders();
                    BeanUtil.copyPropertiesIgnoreNull(subStlPayOrders,pStlPayOrders);
                    subStlPayOrders.setSettleAmt(subFinAssetIntInfo.getSettleAmt());
                    subStlPayOrders.setPortfolioId(subFinAssetIntInfo.getPortfolioId());
                    //现金分红
                    if(StringUtil.equals(entity.getPs(), "01")){
                        BaseTradeInfoDto subBaseTradeInfoDto1 = new BaseTradeInfoDto();
                        subBaseTradeInfoDtoList.add(subBaseTradeInfoDto1);
                        TrdProductDeal subDeal1 = 生成交易(entity, subFinAssetIntInfo,"01");
//                        设置前置交易编号
                        subDeal1.setbTradeId(subDeal.getTradeId());
                        subBaseTradeInfoDto1.setTrdProductDeal(subDeal1);
                        TrdProductDealAdd subDealAdd1 = 生成交易附属(entity, subFinAssetIntInfo, subDeal1);
                        subBaseTradeInfoDto1.setTrdProductDealAdd(subDealAdd1);
                        PaTrdProductDeal subPaDeal1 = 生成交易扩展(entity, subFinAssetIntInfo, subDeal1);
                        subBaseTradeInfoDto1.setPaTrdProductDeal(subPaDeal1);
                        subBaseTradeInfoDto1.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal1));
                        subBaseTradeInfoDto1.setStlPayOrders(subStlPayOrders);
                    }
                    //送转
                    else if(StringUtil.equals(entity.getPs(), "02")){
                        BaseTradeInfoDto subBaseTradeInfoDto2 = new BaseTradeInfoDto();
                        subBaseTradeInfoDtoList.add(subBaseTradeInfoDto2);
                        TrdProductDeal subDeal2 = 生成交易(entity, subFinAssetIntInfo,"02");
                        subDeal2.setbTradeId(subDeal.getTradeId());
                        subBaseTradeInfoDto2.setTrdProductDeal(subDeal2);
                        TrdProductDealAdd subDealAdd2 = 生成交易附属(entity, subFinAssetIntInfo, subDeal2);
                        subBaseTradeInfoDto2.setTrdProductDealAdd(subDealAdd2);
                        PaTrdProductDeal subPaDeal2 = 生成交易扩展(entity, subFinAssetIntInfo, subDeal2);
                        subBaseTradeInfoDto2.setPaTrdProductDeal(subPaDeal2);
                        subBaseTradeInfoDto2.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal2));
                    }else{
                        //分红送转同时生成3条交易信息
                        //现金分红
                        BaseTradeInfoDto subBaseTradeInfoDto1 = new BaseTradeInfoDto();
                        subBaseTradeInfoDtoList.add(subBaseTradeInfoDto1);
                        TrdProductDeal subDeal1 = 生成交易(entity, subFinAssetIntInfo,"01");
                        subDeal1.setbTradeId(subDeal.getTradeId());
                        subBaseTradeInfoDto1.setTrdProductDeal(subDeal1);
                        TrdProductDealAdd subDealAdd1 = 生成交易附属(entity, subFinAssetIntInfo, subDeal1);
                        subBaseTradeInfoDto1.setTrdProductDealAdd(subDealAdd1);
                        PaTrdProductDeal subPaDeal1 = 生成交易扩展(entity, subFinAssetIntInfo, subDeal1);
                        subBaseTradeInfoDto1.setPaTrdProductDeal(subPaDeal1);
                        subBaseTradeInfoDto1.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal1));
                        subBaseTradeInfoDto1.setStlPayOrders(subStlPayOrders);

                        //送转
                        BaseTradeInfoDto subBaseTradeInfoDto2 = new BaseTradeInfoDto();
                        subBaseTradeInfoDtoList.add(subBaseTradeInfoDto2);
                        TrdProductDeal subDeal2 = 生成交易(entity, subFinAssetIntInfo,"02");
                        subDeal2.setbTradeId(subDeal.getTradeId());
                        subBaseTradeInfoDto2.setTrdProductDeal(subDeal2);
                        TrdProductDealAdd subDealAdd2 = 生成交易附属(entity, subFinAssetIntInfo, subDeal2);
                        subBaseTradeInfoDto2.setTrdProductDealAdd(subDealAdd2);
                        PaTrdProductDeal subPaDeal2 = 生成交易扩展(entity, subFinAssetIntInfo, subDeal2);
                        subBaseTradeInfoDto2.setPaTrdProductDeal(subPaDeal2);
                        subBaseTradeInfoDto2.setPtlTrade(生成组合交易(entity, subFinAssetIntInfo, subDeal2));
                    }
                });
            });
        }

        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);
        return result;
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
    private TrdFinProductDealTotal gen汇总流程表(TrdStockBonus002Dto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTotleTradeId());
//      交易编号
        trdFinProductDealTotal.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
//      金融产品代码
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        trdFinProductDealTotal.setRemark(entity.getRemark());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 金融产品类型
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        //trdFinProductDealTotal.setBranch();
        //币种
        trdFinProductDealTotal.setCcy(entity.getCcy());

        trdFinProductDealTotal.setPs(entity.getPs());
        //交易金额 分红金额
        trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
        //证券管理户
//        trdFinProductDealTotal.setSecManageAcctId(entity.getSecManageAcctId());
        // 单位净值 单位分红净值 每股送转
        if(StringUtil.isNotEmpty(entity.getUnitCprice())){
            trdFinProductDealTotal.setUnitCprice(entity.getUnitCprice());
        }else{
            if (StringUtil.isNotEmpty(entity.getShareAmt())){
                trdFinProductDealTotal.setUnitCprice(entity.getShareAmt().divide(entity.getRegDateAmt(),8, RoundingMode.HALF_UP));
            }else{
                trdFinProductDealTotal.setUnitCprice(new BigDecimal(0));
            }
        }
        //单位利息 每股派息
        if(StringUtil.isNotEmpty(entity.getUnitInt())){
            trdFinProductDealTotal.setUnitInt(entity.getUnitInt());
        }else {
            if (StringUtil.isNotEmpty(entity.getFpriceAmt())){
                trdFinProductDealTotal.setUnitInt(entity.getFpriceAmt().divide(entity.getRegDateAmt(),8, RoundingMode.HALF_UP));
            }else {
                trdFinProductDealTotal.setUnitInt(new BigDecimal(0));
            }
        }
        //交易份额
        trdFinProductDealTotal.setShareAmt(entity.getShareAmt());
        trdFinProductDealTotal.setFpriceAmt(entity.getFpriceAmt());
        trdFinProductDealTotal.setbTradeId(entity.getbTradeId());
//        trdFinProductDealTotal.setPrinAmt(entity.getRegDateAmt());
        trdFinProductDealTotal.setTradeDate(entity.getTradeDate());
        //日期与交易日一致
        trdFinProductDealTotal.setSettleDate(trdFinProductDealTotal.getTradeDate());
        //利息总额 分红金额
        trdFinProductDealTotal.setIntAmt(entity.getTradeAmt());
        trdFinProductDealTotal.setChlId(entity.getChlId());
        // 多级法人改造 add by caiqun 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()){
            // 机构代码
            trdFinProductDealTotal.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser()));
        }
        // 多级法人改造 add by caiqun 20200615
        return trdFinProductDealTotal;
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
    private PaTrdFinProductDealTotal gen汇总扩展业务流程(TrdStockBonus002Dto entity,
                                                 TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal paTrdTotal = new PaTrdFinProductDealTotal();
        paTrdTotal.setTotleTradeId(entity.getTotleTradeId());

        // 现金分红
        if (StringUtil.equals(entity.getPs(), "01")) {
            paTrdTotal.setPaBussType(DictCodeListExt2.平安业务类型_现金分红.getValue());
            paTrdTotal.setRegDate(entity.getRegDate());
        }

        //红利再投
        else if (StringUtil.equals(entity.getPs(), "02")) {
            paTrdTotal.setPaBussType(DictCodeListExt2.平安业务类型_送转.getValue());
            paTrdTotal.setRegDate(entity.getRegDate());
        }
        //分红除权
        else if (StringUtil.equals(entity.getPs(), "03")) {
            paTrdTotal.setPaBussType(DictCodeListExt2.平安业务类型_分红送转.getValue());
            paTrdTotal.setRegDate(entity.getRegDate());
            paTrdTotal.setBonusCfmDate(entity.getSettleDate());
        }

//        分红类型
        paTrdTotal.setPayType(entity.getPs());
        paTrdTotal.setWorkflowId(entity.getWorkflowId());
        return paTrdTotal;
    }
    
    /**
     * 
     * 生成交易 <功能详细描述>
     * 
     * @param entity          业务entity
     * @param dividDetailInfo 分红明细信息
     * @param type			  分红类型（存在一条明细同时生成多条交易信息，所以单独传参用于判断）
     * @return 交易dto
     * @see
     * @since 1.0
     */
    private TrdProductDeal 生成交易(TrdStockBonus002Dto entity, FinAssetIntInfo dividDetailInfo,String type) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTotleTradeId(entity.getTotleTradeId());
        // 交易编号
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessExtType(DictCodeListExt2.fromDictCodeList(DictCodeListExt2.共通业务类型_股票分红交易));
        trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
        trdProductDeal.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
        //金融产品代码
        trdProductDeal.setFinprodId(entity.getFinprodId());
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        trdProductDeal.setRemark(entity.getRemark());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDeal.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        //金融产品类型
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        //币种
        trdProductDeal.setCcy(entity.getCcy());
        //现金分红
        if(StringUtil.equals(type, "01")){
        	trdProductDeal.setPs(DictCodeList.交易方向_现金分红.getValue());
        	//交易日保存派息日
            trdProductDeal.setTradeDate(entity.getSettleDate());
        	//交割日保存派息日
            trdProductDeal.setSettleDate(entity.getSettleDate());
            //交易金额保存分红金额
            trdProductDeal.setTradeAmt(dividDetailInfo.getFpriceAmt());
            //每股派息
            trdProductDeal.setUnitInt(entity.getUnitInt());
        }
        //送转
        else if(StringUtil.equals(type, "02")){
        	trdProductDeal.setPs(DictCodeList.交易方向_红利再投.getValue());
        	//交易日保存派息日
            trdProductDeal.setTradeDate(entity.getSettleDate());
            //交割日保存红股上市日
            trdProductDeal.setSettleDate(entity.getRedStockDate());
            //每股送转
            trdProductDeal.setUnitCprice(entity.getUnitCprice());
        }else{
        	trdProductDeal.setPs(DictCodeList.交易方向_除权登记.getValue());
        	 //每股送转
            trdProductDeal.setUnitCprice(entity.getUnitCprice());
            //每股派息
            trdProductDeal.setUnitInt(entity.getUnitInt());

            //交割日保存派息日
            trdProductDeal.setSettleDate(entity.getSettleDate());
            //交易日保存除权除息日
            trdProductDeal.setTradeDate(entity.getTradeDate());
        }
        //保存为画面分红股本
        trdProductDeal.setShareAmt(dividDetailInfo.getShareAmt());
        //保存为画面分红金额
        trdProductDeal.setFpriceAmt(dividDetailInfo.getFpriceAmt());
        trdProductDeal.setPrinAmt(dividDetailInfo.getPrinAmt());
        trdProductDeal.setSecManageAcctId(dividDetailInfo.getSecAcctId());
//        trdProductDeal.setChlId(entity.getChlId());
        trdProductDeal.setCcy(DictCodeList.币种_人民币_CNY.getValue());

        return trdProductDeal;
    }
    
    
    /**
     * 生成交易附属 <功能详细描述>
     * 
     * @param entity          业务entity
     * @param dividDetailInfo 利息明细
     * @param deal            生成的交易
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDealAdd 生成交易附属(TrdStockBonus002Dto entity, FinAssetIntInfo dividDetailInfo, TrdProductDeal deal) {
    	TrdProductDealAdd dealAdd = new TrdProductDealAdd();
        dealAdd.setTradeId(deal.getTradeId());
        dealAdd.setPortfolioId(dividDetailInfo.getPortfolioId());
        dealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        dealAdd.setStatus(entity.getStatus());
        dealAdd.setWorkflowId(entity.getWorkflowId());
        dealAdd.setRegDate(entity.getRegDate());
        //权益登记日份额
        dealAdd.setRegDateAmt(dividDetailInfo.getRegDateAmt());
        return dealAdd;
	}


    /**
     * 生成交易扩展 <功能详细描述>
     *
     * @param entity          业务实体
     * @param dividDetailInfo 分红明细
     * @param deal            交易dto
     * @return 生成结果
     * @see
     * @since 1.0
     */
    private PaTrdProductDeal 生成交易扩展(TrdStockBonus002Dto entity, FinAssetIntInfo dividDetailInfo,
                                    TrdProductDeal deal) {
        PaTrdProductDeal padeal = new PaTrdProductDeal();
        padeal.setTradeId(deal.getTradeId());
        //现金分红
        if (StringUtil.equals("01",deal.getPs())) {
            padeal.setPaBussType(DictCodeListExt2.平安业务类型_现金分红.getValue());
        }
        //送转
        else if (StringUtil.equals("02",deal.getPs())) {
            padeal.setPaBussType(DictCodeListExt2.平安业务类型_送转.getValue());
        }
        //分红送转
        else {
            padeal.setPaBussType(DictCodeListExt2.平安业务类型_分红送转.getValue());
        }
        padeal.setStatus(entity.getStatus());
        padeal.setWorkflowId(entity.getWorkflowId());
        return padeal;
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
	private List<PtlTrade>  生成组合交易(TrdStockBonus002Dto entity, FinAssetIntInfo finAssetIntInfo, TrdProductDeal deal) {
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade =new PtlTrade();
		ptlTrade.setPortfolioId(finAssetIntInfo.getPortfolioId());
		ptlTrade.setBusiNo(deal.getTradeId());
		ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
		ptlTrade.setBusiId(deal.getFinprodId());
		ptlTrade.setBusiType(DictCodeListExt2.共通业务类型_股票分红交易.getValue());
		ptlTrade.setSecManageAcctId(deal.getSecManageAcctId());
		ptlTrade.setSettleDate(deal.getTradeDate());
		ptlTrades.add(ptlTrade);
		return ptlTrades;
	}


    /**
     * 生成划款指令
     * <功能详细描述>
     * @param entity 股票分红
     * @param deal 交易
     * @param dealAdd 交易附属
     * @return 生成结果
     * @see
     * @since 1.0
     */
    private StlPayOrders gen划款指令流程(TrdStockBonus002Dto entity, TrdProductDeal deal, TrdProductDealAdd dealAdd) {
        BussSettleOrderParameterDto bsParameter = new BussSettleOrderParameterDto();
        bsParameter.setDeal(deal);
        bsParameter.setIsTotalTrade(DictCodeList.是否_是.getValue());
        bsParameter.setPortfolioId(dealAdd.getPortfolioId());
        BussTrdTransferOrderDto bussTrdTransferOrder = bussTrdTransferOrderImplService.generateOrder(bsParameter);
        StlPayOrders stlPayOrders = new StlPayOrders();
        BeanUtil.copyPropertiesIgnoreNull(stlPayOrders,bussTrdTransferOrder);
        //本方账户
        stlPayOrders.setOrderId("O" + entity.getTotleTradeId());
        stlPayOrders.setMyAcctNo(bussTrdTransferOrder.getRecAcctNo());
        ///对手方账户
        stlPayOrders.setCounterAcctNo(bussTrdTransferOrder.getPayAcctNo());
        stlPayOrders.setTradeId(entity.getTotleTradeId());
        stlPayOrders.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
        stlPayOrders.setWorkflowId(entity.getWorkflowId());
        return stlPayOrders;
    }
    public void execute交易共通(TrdStockBonus002Dto entity, DictCodeListExt1 交易流程状态) {
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
                    setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(),
                            dealExe, 交易流程状态);
                } else {
                    TrdProductDeal dealExe = new TrdProductDeal();
                    BeanUtils.copyProperties(deal, dealExe);
                    setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getTrdProductDealAdd(), dealExe, 交易流程状态);

                    if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                        TrdProductDeal deal1 = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                        TrdProductDeal dealExe1 = new TrdProductDeal();
                        BeanUtils.copyProperties(deal1, dealExe1);
                        setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe1, 交易流程状态);
                    }
                }
            });
            AtomicInteger i = new AtomicInteger(1);
            baseTradeProductDealDtos.forEach(dealDto -> {
                if (dealDto.getStlPayOrdersList() != null) {
                    dealDto.getStlPayOrdersList().forEach(stlPayOrdersDto -> {
                        stlPayOrdersDto.setOrderId("O" + entity.getTotleTradeId() + i.toString());
                        i.getAndAdd(1);
                    });
                }
                dealDto.getTrdProductDeal().setTradeId(entity.getTotleTradeId()); // 持仓表的交易编号记为汇总交易编号，撤销时，直接操作
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
            baseTradeProductDealCommServiceImpl.execute(tradeDto);
            execute交易共通子组合(entity,交易流程状态);
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("tradeId", entity.getpTradeId());
            // 只查询T13的子组合交易
            List<BaseTradeProductDealDto> 股票除权登记交易 =  restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdStockBonus/findEffectiveT13Deal",
                    param, BaseTradeProductDealDto.class);

            股票除权登记交易.forEach(dealDto -> {
                        if (DictCodeListExt1.交易流程状态_有效 == 交易流程状态) {
                            // 调用投管接口 add by qcx 20190206 start
                            getInterfInputDto(entity, dealDto, 交易流程状态);
                            // 调用投管接口 add by qcx 20190206 end
                        }
                    });
            AllTradeCommonUtil.revokePosition(entity.getpTradeId());
            if (baseEtrCashPositionCommService != null) {
                baseEtrCashPositionCommService.revokePosition(entity.getpTradeId());
            }
        }
    }

    private void getInterfInputDto(TrdStockBonus002Dto entity, BaseTradeProductDealDto baseTradeProductDealDto, DictCodeListExt1 交易流程状态) {
        if (DictCodeListExt1.交易流程状态_流程中 == 交易流程状态
                || baseTradeProductDealDto.getTrdProductDealAdd() == null
                || StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())
                || AllTradeCommonUtil.isNotActPortfolio(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId())) {
            return;
        }
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("TrdStockBonus002");
        if (DictCodeListExt1.交易流程状态_撤销 == 交易流程状态) {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_撤销.getValue());
        }
        else {
            investInterfCommDto.setTradeStatus(DictCodeListExt1.画面数据状态_新建.getValue());
        }
        
        investInterfCommDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
        investInterfCommDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
        // 送转没有划款指令
        if (baseTradeProductDealDto.getStlPayOrdersList() != null && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
            investInterfCommDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList().get(0));
        }
        investInterfCommService.doInvestInterf(investInterfCommDto);
    }

    /**
     *
     */
//        子组合调用共同
    public void execute交易共通子组合(TrdStockBonus002Dto entity, DictCodeListExt1 交易流程状态){
        // 调用业务组件.【金融产品交易共通.交易产品接口】
        FinProductTradeDto tradeDto = new FinProductTradeDto();
        tradeDto.setNeedSum(true);
        tradeDto.setWorkflowStatus(交易流程状态.getValue());
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
//        调交易共通时，一个利息明细一条交易，但存表时，一个交易明细可能有两个交易（收息、还本）
        if (DictCodeListExt1.交易流程状态_撤销 != 交易流程状态) {
            entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList().forEach(baseTradeInfoDto -> {
                if(baseTradeInfoDto.getBaseTradeInfoDtoList() != null && baseTradeInfoDto.getBaseTradeInfoDtoList().size()>0){
                    baseTradeInfoDto.getBaseTradeInfoDtoList().forEach(item->{
                        getCommonExcuteDtoByTrade(entity, item, baseTradeProductDealDtos, 交易流程状态);
                    });
                }
            });
            AtomicInteger i = new AtomicInteger();
            baseTradeProductDealDtos.forEach(dealDto -> {
                if (dealDto.getStlPayOrdersList() != null) {
                    dealDto.getStlPayOrdersList().forEach(stlPayOrdersDto -> {
                        stlPayOrdersDto.setOrderId("O" + entity.getTotleTradeId() + i.toString());
                        i.getAndAdd(1);
                    });
                }
                dealDto.getTrdProductDeal().setTradeId(entity.getTotleTradeId()); // 持仓表的交易编号记为汇总交易编号，撤销时，直接操作
            });
            tradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        }
        baseTradeProductDealCommServiceImpl.insertPosition(tradeDto);
    }
//    /**
//     * 子组合信息处理划款指令（撤销用）
//     * @param map 参数交易编号
//     * @return 组装结果
//     */
//    public  List<BaseTradeProductDealDto> getChiledTradeProductDealInfo(Map<String, Object> map) {
//        //        处理提前支取没有flag
//        if(!map.containsKey("isTqzq")){
//            map.put("isTqzq","");
//        }
//        map.put("YON_Y", BussinessDictCodeList.是否_是.getValue());
//        List<BaseTradeProductDealDto> tol = restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundDuration/findEffectiveDeal",
//                    map, BaseTradeProductDealDto.class);
//        List<BaseTradeProductDealDto> result = new ArrayList<>();
//        for(BaseTradeProductDealDto baseTradeProductDealDto:tol){
//            map.put("portfolioId",baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
//            List<BaseTradeProductDealDto> temp = restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundDuration/findEffectiveDeal",map, BaseTradeProductDealDto.class);
//            for(BaseTradeProductDealDto subTemp : temp){
//                periodCommonService.gen划款指令_BY_交易(subTemp,"REVOKE");
//            }
//            result.addAll(temp);
//        }
//        return result;
//    }
    /**
     * 根据交易dto组装共同dto
     * @param entity
     * @param baseTradeInfoDto 交易dto
     * @param baseTradeProductDealDtos 共同dto list
     * @param 交易流程状态
     */
    private void getCommonExcuteDtoByTrade(TrdStockBonus002Dto entity, BaseTradeInfoDto baseTradeInfoDto, List<BaseTradeProductDealDto> baseTradeProductDealDtos, DictCodeListExt1 交易流程状态) {
        TrdProductDeal deal = baseTradeInfoDto.getTrdProductDeal();
        if (deal == null) {
            deal = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
            TrdProductDeal dealExe = new TrdProductDeal();
            BeanUtils.copyProperties(deal, dealExe);
            setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe, 交易流程状态);
        } else {
            TrdProductDeal dealExe = new TrdProductDeal();
            BeanUtils.copyProperties(deal, dealExe);
            setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getTrdProductDealAdd(), dealExe, 交易流程状态);

            if (baseTradeInfoDto.getBaseTrade() != null && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                TrdProductDeal deal1 = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
                TrdProductDeal dealExe1 = new TrdProductDeal();
                BeanUtils.copyProperties(deal1, dealExe1);
                setDeal(entity, baseTradeProductDealDtos, baseTradeInfoDto,baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd(), dealExe1, 交易流程状态);
            }
        }
    }
    private void setDeal(TrdStockBonus002Dto entity, List<BaseTradeProductDealDto> baseTradeProductDealDtos, BaseTradeInfoDto baseTradeInfoDto, TrdProductDealAdd add, TrdProductDeal dealExe, DictCodeListExt1 交易流程状态) {
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(dealExe);
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        StlPayOrdersDto orders = new StlPayOrdersDto();
        if (baseTradeInfoDto.getStlPayOrders() != null) {
            BeanUtils.copyProperties(baseTradeInfoDto.getStlPayOrders(), orders);
            baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(new StlPayOrdersDto[]{orders}));
        }
        // 调用投管接口 add by qcx 20190206 start
        getInterfInputDto(entity, baseTradeProductDealDto, 交易流程状态);
        // 调用投管接口 add by qcx 20190206 end
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
    }
}
