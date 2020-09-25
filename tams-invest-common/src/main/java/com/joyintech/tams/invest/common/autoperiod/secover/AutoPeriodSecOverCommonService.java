/*
 * 文件名：PeriodCommonUtil.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月13日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.secover;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.secover.dao.AutoPeriodSecOverCommonMapper;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrade;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 债券资产存续期共通Service 批处理自动存续期和存续期画面共用逻辑
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see AutoPeriodSecOverCommonService
 * @since 1.0
 */
@Service
public class AutoPeriodSecOverCommonService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;
    
    @Autowired
    private AutoPeriodSecOverCommonMapper autoPeriodSecOverCommonMapper;
    
    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;

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
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdSecOverdueDurationDto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表2(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);
        // 金融产品交易汇总附属Entity -> 金融产品交易汇总附属业务流程表
        TrdDealTotalAdd trdDealTotalAdd = gen汇总附属业务流程2(entity, trdFinProductDealTotal);
        result.setTrdDealTotalAdd(trdDealTotalAdd);
        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展业务流程2(entity, trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);

        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        //       划款指令
        List<BussTrdTransferOrderDto> stlPayOrdersList = autoPeriodCommonService.save(entity.getBussTrdTransferOrder());

        // 根据利息明细处理汇总及交易dto
        List<FinAssetIntInfo> 利息明细 = entity.getFinAssetIntInfoList();
        利息明细.forEach(finAssetIntInfo -> {
            BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
            baseTradeInfoDtoList.add(baseTradeInfoDto);
            gen利息明细汇总表附属表和扩展表(finAssetIntInfo, entity, baseTradeInfoDto);
            //                      生成划款指令
            BigDecimal fPriceAmt=baseTradeInfoDto.getTrdFinProductDealTotal().getFpriceAmt();
            baseTradeInfoDto.getTrdFinProductDealTotal().setFpriceAmt(baseTradeInfoDto.getTrdFinProductDealTotal().getTradeAmt());
            StlPayOrders stl = autoPeriodCommonService.gen划款指令流程(stlPayOrdersList, baseTradeInfoDto.getTrdFinProductDealTotal(), entity.getWorkflowId());
            baseTradeInfoDto.getTrdFinProductDealTotal().setFpriceAmt(fPriceAmt);
            baseTradeInfoDto.setStlPayOrders(stl);
            
            //            生成子组合利息明细共同方法
            Map<String,Object> subMap = new HashMap<>();
            subMap.put("portfolioId",finAssetIntInfo.getPortfolioId());
//            持仓时间
            subMap.put("bjBranch", entity.getBjBranch());
//            资产代码
            subMap.put("liBranch", entity.getLiBranch());
//          证券/通道
            subMap.put("secManageAcctId", entity.getSecAcctId());
//            金融产品代码
            subMap.put("finprodId",entity.getFinprodId());
            Map<String, BigDecimal> preMap = new HashMap<>();
            preMap.put("prinAmt",finAssetIntInfo.getPrinAmt());//实付本金
            preMap.put("intAmt",finAssetIntInfo.getIntAmt());//实收利息
            preMap.put("penaltyIntamt",finAssetIntInfo.getPenaltyIntamt());//实收罚息
            preMap.put("tradeAmt",finAssetIntInfo.getTradeAmt());//实收本息总额
            
            if(finAssetIntInfo.getChannelFee()!=null){
            	preMap.put("channelFee",finAssetIntInfo.getChannelFee());
            }
//          划款指令结算金额
            if(stl != null){
                preMap.put("settleAmt",stl.getSettleAmt());
            }
            subMap.put("preMap", JsonUtils.object2Json(preMap));
            subMap.put("invAim", finAssetIntInfo.getInvAim());
            
            //获取子组合明细信息
            List<FinAssetIntInfo> 子组合利息明细 =  new ArrayList<>();
            //页面存在子组合信息 使用页面子组合信息
            if(!StringUtil.isEmptyList(entity.getChildPortfolioInfo())){
            	String  portfolioIdInvAim= finAssetIntInfo.getPortfolioId()+finAssetIntInfo.getInvAim();
            	for(FinAssetIntInfo fin:entity.getChildPortfolioInfo()){
            		String subportfolioIdInvAim = fin.getPortfolioId()+fin.getInvAim();
            		if(StringUtil.equals(portfolioIdInvAim, subportfolioIdInvAim)){
            			子组合利息明细.add(fin);
            		}
            	}
            	//当前组合和投资目的，页面没有子组合信息，生成该子组合信息
            	if(StringUtil.isEmptyList(子组合利息明细)){
                	子组合利息明细 = this.distributeDataByPercent(preMap,subMap,子组合利息明细);
                }
            }else{
            	子组合利息明细 = this.distributeDataByPercent(preMap,subMap,子组合利息明细);
            }
            
//          子组合交易
            List<BaseTradeInfoDto> subBaseTradeInfoList = new ArrayList<>();
            baseTradeInfoDto.setBaseTradeInfoDtoList(subBaseTradeInfoList);
            String totalTradeId=baseTradeInfoDto.getTrdFinProductDealTotal().getTotleTradeId();
            baseTradeInfoDto =this.gen(finAssetIntInfo, entity, trdFinProductDealTotal,baseTradeInfoDto,totalTradeId);
            
            子组合利息明细.forEach(subFinAssetIntInfo -> {
            	BaseTradeInfoDto subBaseTradeInfoDto =  new BaseTradeInfoDto();
            	StlPayOrders subStl = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(subStl,stl);
                if(StringUtil.isNotEmpty(subStl.getSettleAmt())&&subStl.getSettleAmt().compareTo(BigDecimal.ZERO)!=0){
                	subStl.setSettleAmt(subFinAssetIntInfo.getSettleAmt());
                }else{
                	subStl.setSettleAmt(subFinAssetIntInfo.getTradeAmt());
                }
                subStl.setPortfolioId(subFinAssetIntInfo.getSubPortfolioId());
                
                //设置划款指令
                subBaseTradeInfoDto.setStlPayOrders(subStl);
            	subBaseTradeInfoDto =this.gen(subFinAssetIntInfo, entity, trdFinProductDealTotal,subBaseTradeInfoDto,totalTradeId);
            	subBaseTradeInfoList.add(subBaseTradeInfoDto);
            });
        });
        return result;
    }

	/**
     * 查询子组合利息明细，计算其占比
     * @param preMap 待分配的金额 key值必须与dto字段名一致
     * @param subMap
     * @return
     */
    public List<FinAssetIntInfo> distributeDataByPercent(Map<String,BigDecimal> preMap,Map<String, Object> subMap, List<FinAssetIntInfo> portfolioListInfo) {
        //如果没有提供子组合持仓信息则查询子组合持仓信息
        if(portfolioListInfo==null || portfolioListInfo.size()==0){
        	subMap.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        	subMap.put("RECE_TYPE_01", DictCodeListExt3.应收款类型_逾期未收本金.getValue());
        	subMap.put("RECE_TYPE_02", DictCodeListExt3.应收款类型_逾期未收利息.getValue());
        	subMap.put("RECE_TYPE_03", DictCodeListExt3.应收款类型_还本未收利息.getValue());
//            处理日期
        	subMap.put("Cdate",platformDateImpl.getSysDate());
        	subMap.put("BUSI_TYPE_37", DictCodeList.业务类型_逾期交易.getValue());
        	subMap.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        	subMap.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        	subMap.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        	subMap.put("INPUT_TYPE_04", DictCodeListExt1.录入方式_批处理生成.getValue());
            portfolioListInfo =autoPeriodSecOverCommonMapper.selectNewInterestDetails(subMap);
        }else{
        	return portfolioListInfo;
        }
        //初始化设置
    	portfolioListInfo.forEach(finAssetIntInfo->{
//    		理论本金罚息  本金总罚息*(未支付本金 - 当日本金)/本金总持仓  - 实付本金罚息 保留2位小数
    		finAssetIntInfo.setCapitalPenalty(ArithmeticUtil.subtract(ArithmeticUtil.divide(ArithmeticUtil.multiply(finAssetIntInfo.getCapitalPenaltyTotle(),ArithmeticUtil.subtract(finAssetIntInfo.getPrinAmtTheory(),finAssetIntInfo.getEarlyPrinAmtTheory())),finAssetIntInfo.getCapitalHoldPosition(),2), finAssetIntInfo.getActPenaltyIntBj()));
    		//理论利息罚息  利息总罚息*(收息未支付利息-当日利息)/利息总持仓 - 实付利息罚息  保留2位小数
    		finAssetIntInfo.setInterestPenalty(ArithmeticUtil.subtract(ArithmeticUtil.divide(ArithmeticUtil.multiply(finAssetIntInfo.getInterestPenaltyTotle(),ArithmeticUtil.subtract(finAssetIntInfo.getChargeUnpayInterest(),finAssetIntInfo.getEarlyChargeUnpayInterest())),finAssetIntInfo.getInterestHoldPosition(),2), finAssetIntInfo.getActPenaltyIntLx()));
    		//理论还本未支付利息  还本未支付利息总罚息*(还本未支付利息-当日还本未收利息)/还本未付息总持仓  -实付还本未付息罚息 保留2位小数
    		finAssetIntInfo.setRepaymentUnpayHoldPenalty(ArithmeticUtil.subtract(ArithmeticUtil.divide(ArithmeticUtil.multiply(finAssetIntInfo.getRepaymentUnpayHoldPenaltyTotle(),ArithmeticUtil.subtract(finAssetIntInfo.getRepaymentUnpayInterest(), finAssetIntInfo.getEarlyRepaymentUnpayInterest())),finAssetIntInfo.getRepaymentUnpayHoldPosition(),2), finAssetIntInfo.getActPenaltyIntHbWfx()));

    		//理论总罚息
    		finAssetIntInfo.setTheoryPenalty(ArithmeticUtil.doAdd(finAssetIntInfo.getCapitalPenalty(),finAssetIntInfo.getInterestPenalty(),finAssetIntInfo.getRepaymentUnpayHoldPenalty()));
    		//理论利息  收息未支付利息+还本未支付利息
    		finAssetIntInfo.setTheoryInt(ArithmeticUtil.doAdd(finAssetIntInfo.getRepaymentUnpayInterest(),finAssetIntInfo.getChargeUnpayInterest()));
    		//理论本息和
    		finAssetIntInfo.setTheoryAmt(ArithmeticUtil.doAdd(finAssetIntInfo.getPrinAmtTheory(),finAssetIntInfo.getTheoryInt(),finAssetIntInfo.getTheoryPenalty()));
    		//設置母組合唯一標識
    		finAssetIntInfo.setPortfolioIdInvAim(subMap.get("portfolioId")+finAssetIntInfo.getInvAim());

        });
        
		// 根据占比处理数值
		for (String key : preMap.keySet()) {
			//计算子组合各自信息持仓
	        if(StringUtil.equals(key, "prinAmt")){
	        	//本金
	        	portfolioListInfo.forEach(portfolio->{
	            	portfolio.setAmount(portfolio.getPrinAmtTheory());
	            });
	        }else if(StringUtil.equals(key, "intAmt")){
	        	//利息
	        	portfolioListInfo.forEach(portfolio->{
	            	portfolio.setAmount(portfolio.getTheoryInt());
	            });
	        }else if(StringUtil.equals(key, "penaltyIntamt")){
	        	//罚息
	        	portfolioListInfo.forEach(portfolio->{
	            	portfolio.setAmount(portfolio.getTheoryPenalty());
	            });
	        }else{
	        	//理论本息和
	        	portfolioListInfo.forEach(portfolio->{
	            	portfolio.setAmount(portfolio.getTheoryAmt());
	            });
	        }
//	      按照持仓排序
	        portfolioListInfo.sort( Comparator.comparing(FinAssetIntInfo::getAmount));//折算后金额排序
//	        总持仓
	        BigDecimal sumAmount = BigDecimal.ZERO;
	        for(FinAssetIntInfo item:portfolioListInfo){
	            sumAmount = ArithmeticUtil.doAdd(sumAmount,item.getAmount());
	        }
//	        初始化已分配
	        BigDecimal sumMap = BigDecimal.ZERO;
	        Class clzz = FinAssetIntInfo.class;
	        for (int i = 0; i < portfolioListInfo.size(); i++) {
	            FinAssetIntInfo temp = portfolioListInfo.get(i);
	            try{
//	              	最后一个
	                if (i == portfolioListInfo.size() - 1) {
                        Field fieldT = clzz.getDeclaredField(key);
                        fieldT.setAccessible(true);
                        fieldT.set(temp,ArithmeticUtil.subtract(preMap.get(key),sumMap));
	                }
//	        	      非最后一个
	                else{
	                  	//计算占比
	                    BigDecimal prop  = ArithmeticUtil.divide(temp.getAmount(),sumAmount,13, RoundingMode.HALF_UP);
                        Field fieldT = clzz.getDeclaredField(key);
                        fieldT.setAccessible(true);
	                    //计算这次需要的数值
                        BigDecimal thisNum = ArithmeticUtil.multiply( preMap.get(key),prop).setScale(2, RoundingMode.HALF_UP);
	        			//处理已分配的钱
                        sumMap=ArithmeticUtil.doAdd(sumMap,thisNum);
	                   	//把数据存入dto
                        fieldT.set(temp,thisNum);
	                }
	            }
	            catch (Exception e){
	                e.printStackTrace();
	            }
	        }
		}
        
        return portfolioListInfo;
    }
    
    /**
     * 构造对象
     * @param subMap
     * @return
     */
    private BaseTradeInfoDto gen(FinAssetIntInfo finAssetIntInfo,TrdSecOverdueDurationDto dto,TrdFinProductDealTotal trdFinProductDealTotal,BaseTradeInfoDto baseTradeInfoDto,String totalTradeId) {
		//还本不带息利息明细
    	List<BaseTradeInfoDto> repaymentUnpayInterestBaseTradeInfoDtoList = new ArrayList<>();
    	//罚息利息明细
    	List<BaseTradeInfoDto> penaltyIntBaseTradeInfoDtoList = new ArrayList<>();
    	baseTradeInfoDto.setPenaltyIntBaseTradeInfoDtoList(penaltyIntBaseTradeInfoDtoList);
    	baseTradeInfoDto.setRepaymentUnpayInterestBaseTradeInfoDtoList(repaymentUnpayInterestBaseTradeInfoDtoList);
    	
    	String bjCashId = null;//本金cashid
    	
    	//通道费使用该交易表对象，无通道费时，不处理该对象
    	TrdProductDeal deal = null;
    	
    	//本金罚息总额
    	BigDecimal  penaltyPrinAmt = BigDecimal.ZERO;
    	//实付本金存在
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getPrinAmt())
    			&&finAssetIntInfo.getPrinAmt().compareTo(BigDecimal.ZERO)>0){
    		//交易表信息
    		TrdProductDeal trdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_还本到账.getValue(),null,null,false,0L,bjCashId,totalTradeId,null,null); 
			//交易附属
			TrdProductDealAdd trdProductDealAdd=this.gen交易附属(finAssetIntInfo, trdProductDeal, dto);
			//组合交易表信息
			List<PtlTrade> ptlTrades = this.gen组合交易(dto,trdProductDeal,finAssetIntInfo);
			//本金数据
			BaseTrade baseTrade = new BaseTrade();
			baseTrade.setTrdProductDeal(trdProductDeal);
			baseTrade.setTrdProductDealAdd(trdProductDealAdd);
			baseTrade.setPtlTrade(ptlTrades);
			//应收款持仓
			baseTrade.setRecType(DictCodeListExt3.应收款类型_逾期未收本金.getValue());//应收款持仓类型
			baseTrade.setPayDate(finAssetIntInfo.getPayDate());//计划支付日
			baseTradeInfoDto.setBaseTrade(baseTrade);
			//组合现金流水
			
			//指令流水
		}
		//金融产品交易及相关-本金罚息  列表.实收罚息> 0 且 列表隐藏.理论本金罚息 >0
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getPenaltyIntamt())
    			&&finAssetIntInfo.getPenaltyIntamt().compareTo(BigDecimal.ZERO)>0
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getCapitalPenalty())
    			&&finAssetIntInfo.getCapitalPenalty().compareTo(BigDecimal.ZERO)>0){
    		//交易表信息
    		TrdProductDeal penaltyTrdProductDeal =  new TrdProductDeal();
			//利息总额   列表.实收罚息 * 列表隐藏.理论本金罚息 /（列表隐藏.理论利息罚息 + 列表隐藏.理论本金罚息 + 列表隐藏.理论还本未付利息罚息）
    		penaltyTrdProductDeal.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(finAssetIntInfo.getPenaltyIntamt(),finAssetIntInfo.getCapitalPenalty()), 
    				ArithmeticUtil.doAdd(finAssetIntInfo.getCapitalPenalty(),finAssetIntInfo.getInterestPenalty(),finAssetIntInfo.getRepaymentUnpayHoldPenalty()), 2));
    		//全价总额  列表.实收罚息 * 列表隐藏.理论本金罚息 /（列表隐藏.理论利息罚息 + 列表隐藏.理论本金罚息 + 列表隐藏.理论还本未付利息罚息）
    		penaltyTrdProductDeal.setFpriceAmt(penaltyTrdProductDeal.getIntAmt());
    		//交易金额  列表.实收罚息 * 列表隐藏.理论本金罚息 /（列表隐藏.理论利息罚息 + 列表隐藏.理论本金罚息 + 列表隐藏.理论还本未付利息罚息）
    		penaltyTrdProductDeal.setTradeAmt(penaltyTrdProductDeal.getIntAmt());
    		//本金罚息信息
    		penaltyPrinAmt = penaltyTrdProductDeal.getIntAmt();
    		//分支序号  传入.本金分支序号
    		penaltyTrdProductDeal.setBranch(dto.getBjBranch());
    		//交易表信息
    		TrdProductDeal penaltytrdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),null,penaltyTrdProductDeal,true,dto.getBjBranch(),bjCashId,totalTradeId,null,null);
			//交易附属
			TrdProductDealAdd penaltyTrdProductDealAdd=this.gen交易附属(finAssetIntInfo, penaltytrdProductDeal, dto);
			//组合交易表信息
			List<PtlTrade> penaltyPtlTrades = this.gen组合交易(dto,penaltytrdProductDeal,finAssetIntInfo);
			//组装加入罚息信息
			BaseTradeInfoDto baseTradeInfoDto2 = new BaseTradeInfoDto();
			baseTradeInfoDto2.setTrdProductDeal(penaltyTrdProductDeal);
			baseTradeInfoDto2.setTrdProductDealAdd(penaltyTrdProductDealAdd);
			baseTradeInfoDto2.setPtlTrade(penaltyPtlTrades);
			penaltyIntBaseTradeInfoDtoList.add(baseTradeInfoDto2);
			
			//指向通道费对象
			deal=penaltytrdProductDeal;
    	}
    	//还本不带息总利息额
    	BigDecimal  intAmt = BigDecimal.ZERO;
    	//还本不带息罚息总额
    	BigDecimal  penaltyIntAmt = BigDecimal.ZERO;
    	
    	//还本不带息信息列表
    	List<TrdSecOverdueDurationDto>  repaymentUnpayInterestList = null;
    	
    	//列表.实收利息>0时录入且列表隐藏.还本未支付利息>0时
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getIntAmt())
    			&&finAssetIntInfo.getIntAmt().compareTo(BigDecimal.ZERO)>0
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getRepaymentUnpayInterest())
    			&&finAssetIntInfo.getRepaymentUnpayInterest().compareTo(BigDecimal.ZERO)>0){
    		Map<String, Object> paramMap = new HashMap<>();
    		paramMap.put("libranch", dto.getLiBranch());
    		paramMap.put("finprodId", dto.getFinprodId());
    		paramMap.put("portfolioId", finAssetIntInfo.getPortfolioId());
    		paramMap.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
    		paramMap.put("Cdate", platformDateImpl.getSysDate());
    		paramMap.put("invAim", finAssetIntInfo.getInvAim());
    		paramMap.put("secAcctId", dto.getSecAcctId());
    		repaymentUnpayInterestList = autoPeriodSecOverCommonMapper.selectRepaymentUnpayInterest(paramMap);
    		//还本未付利息不为空
    		if(!StringUtil.isEmptyList(repaymentUnpayInterestList)){
    			for(TrdSecOverdueDurationDto trdSecOverdueDurationDto:repaymentUnpayInterestList){
        			TrdProductDeal trdProductDeal =  new TrdProductDeal();
        			//利息总额 列表.实收利息 * 上述查询数量/(列表隐藏.收息未支付利息 + 列表隐藏.还本未支付利息)
            		trdProductDeal.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(trdSecOverdueDurationDto.getAmount(),finAssetIntInfo.getIntAmt()), 
        					ArithmeticUtil.doAdd(finAssetIntInfo.getChargeUnpayInterest(),finAssetIntInfo.getRepaymentUnpayInterest()), 2));
            		//全价总额  列表.实收利息 * 上述查询数量/(列表隐藏.收息未支付利息 + 列表隐藏.还本未支付利息)
            		trdProductDeal.setFpriceAmt(trdProductDeal.getIntAmt());
            		//交易金额  列表.实收利息 * 上述查询数量/(列表隐藏.收息未支付利息 + 列表隐藏.还本未支付利息)
            		trdProductDeal.setTradeAmt(trdProductDeal.getIntAmt());
            		//累加上述利息
            		intAmt = ArithmeticUtil.doAdd(trdProductDeal.getIntAmt(),intAmt);
            		//分支序号  上述查询到的分支序号
            		trdProductDeal.setBranch(trdSecOverdueDurationDto.getBranch());
            		//交易表信息
            		trdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),trdSecOverdueDurationDto.getCashId(),trdProductDeal,false,trdSecOverdueDurationDto.getBranch(),bjCashId,totalTradeId,trdSecOverdueDurationDto.getVdate(),trdSecOverdueDurationDto.getMdate());
            		//交易附属
        			TrdProductDealAdd trdProductDealAdd=this.gen交易附属(finAssetIntInfo, trdProductDeal, dto);
        			//组合交易表信息
        			List<PtlTrade> ptlTrades = this.gen组合交易(dto,trdProductDeal,finAssetIntInfo);
        			//组装加入还本不带息信息
        			BaseTradeInfoDto baseTradeInfoDto2 = new BaseTradeInfoDto();
    				baseTradeInfoDto2.setTrdProductDeal(trdProductDeal);
    				baseTradeInfoDto2.setTrdProductDealAdd(trdProductDealAdd);
    				baseTradeInfoDto2.setPtlTrade(ptlTrades);
    				//应收款持仓
    				baseTradeInfoDto2.setRecType(trdSecOverdueDurationDto.getRecType());//应收款持仓类型
    				baseTradeInfoDto2.setPayDate(finAssetIntInfo.getPayDate());//计划支付日
    				//画面是否结清为是时， 结清应收款持仓，否则不需要存入此字段
    				if(StringUtil.equals(dto.getIsClean(), DictCodeList.是否_是.getValue())){
    					baseTradeInfoDto2.setAmount(trdSecOverdueDurationDto.getAmount());
    				}
    				repaymentUnpayInterestBaseTradeInfoDtoList.add(baseTradeInfoDto2);
        		}
    		}
    		
    	}
    	
    	//金融产品交易及相关-还本未付息利息罚息  列表.实收罚息> 0 且 列表隐藏.理论还本未付利息罚息 >0  时录入
		if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getPenaltyIntamt())
    			&&finAssetIntInfo.getPenaltyIntamt().compareTo(BigDecimal.ZERO)>0
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getRepaymentUnpayHoldPenalty())
    			&&finAssetIntInfo.getRepaymentUnpayHoldPenalty().compareTo(BigDecimal.ZERO)>0){
	    	//还本未付利息不为空
			if(!StringUtil.isEmptyList(repaymentUnpayInterestList)){
				for(TrdSecOverdueDurationDto trdSecOverdueDurationDto:repaymentUnpayInterestList){
		    		TrdProductDeal penaltyTrdProductDeal =  new TrdProductDeal();
					//利息总额 列表.实收罚息 * 列表隐藏.理论还本未付利息罚息*(查询.数量/还本未付息总持仓) /（列表隐藏.理论利息罚息 + 列表隐藏.理论本金罚息 + 列表隐藏.理论还本未付利息罚息）
		    		penaltyTrdProductDeal.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(finAssetIntInfo.getPenaltyIntamt(),finAssetIntInfo.getRepaymentUnpayHoldPenalty(),trdSecOverdueDurationDto.getAmount()), 
		    				ArithmeticUtil.multiply(finAssetIntInfo.getRepaymentUnpayHoldPosition(),ArithmeticUtil.doAdd(finAssetIntInfo.getCapitalPenalty(),finAssetIntInfo.getInterestPenalty(),finAssetIntInfo.getRepaymentUnpayHoldPenalty())), 2));
		    		//全价总额  列表.实收利息 * 上述查询数量/(列表隐藏.收息未支付利息 + 列表隐藏.还本未支付利息)
		    		penaltyTrdProductDeal.setFpriceAmt(penaltyTrdProductDeal.getIntAmt());
		    		//交易金额  列表.实收利息 * 上述查询数量/(列表隐藏.收息未支付利息 + 列表隐藏.还本未支付利息)
		    		penaltyTrdProductDeal.setTradeAmt(penaltyTrdProductDeal.getIntAmt());
		    		//累加上述利息
		    		penaltyIntAmt = ArithmeticUtil.doAdd(penaltyTrdProductDeal.getIntAmt(),penaltyIntAmt);
		    		//分支序号  上述查询到的分支序号
		    		penaltyTrdProductDeal.setBranch(trdSecOverdueDurationDto.getBranch());
		    		//交易表信息
		    		penaltyTrdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),null,penaltyTrdProductDeal,true,trdSecOverdueDurationDto.getBranch(),bjCashId,totalTradeId,trdSecOverdueDurationDto.getVdate(),trdSecOverdueDurationDto.getMdate());
		    		//交易附属
					TrdProductDealAdd penaltyTrdProductDealAdd=this.gen交易附属(finAssetIntInfo, penaltyTrdProductDeal, dto);
					//组合交易表信息
					List<PtlTrade> penaltyPtlTrades = this.gen组合交易(dto,penaltyTrdProductDeal,finAssetIntInfo);
					//组装加入罚息信息
					BaseTradeInfoDto baseTradeInfoDto3 = new BaseTradeInfoDto();
					baseTradeInfoDto3.setTrdProductDeal(penaltyTrdProductDeal);
					baseTradeInfoDto3.setTrdProductDealAdd(penaltyTrdProductDealAdd);
					baseTradeInfoDto3.setPtlTrade(penaltyPtlTrades);
					penaltyIntBaseTradeInfoDtoList.add(baseTradeInfoDto3);
			    	
				}
			}
		}
		
    	// 金融产品交易及相关-利息   列表.实收利息>0时录入
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getIntAmt())
    			&&finAssetIntInfo.getIntAmt().compareTo(BigDecimal.ZERO)>0){
    		//交易表信息
    		TrdProductDeal trdProductDeal =  new TrdProductDeal();
			//利息总额 列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		trdProductDeal.setIntAmt(ArithmeticUtil.subtract(finAssetIntInfo.getIntAmt(), intAmt));
    		//全价总额  列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		trdProductDeal.setFpriceAmt(trdProductDeal.getIntAmt());
    		//交易金额  列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		trdProductDeal.setTradeAmt(trdProductDeal.getIntAmt());
    		//分支序号  利息分支序号
    		trdProductDeal.setBranch(dto.getLiBranch());
    		//交易表信息
    		trdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),null,trdProductDeal,false,0L,bjCashId,totalTradeId,null,null);
			//交易附属
    		TrdProductDealAdd trdProductDealAdd=this.gen交易附属(finAssetIntInfo, trdProductDeal, dto);
			//组合交易表信息
    		List<PtlTrade> ptlTrades = this.gen组合交易(dto,trdProductDeal,finAssetIntInfo);
    		//组装加入利息信息
    		baseTradeInfoDto.setTrdProductDeal(trdProductDeal);
    		baseTradeInfoDto.setTrdProductDealAdd(trdProductDealAdd);
    		baseTradeInfoDto.setPtlTrade(ptlTrades);
    		//应收款持仓
    		baseTradeInfoDto.setRecType(DictCodeListExt3.应收款类型_逾期未收利息.getValue());//应收款持仓类型
    		baseTradeInfoDto.setPayDate(finAssetIntInfo.getPayDate());//计划支付日
    		//画面是否结清为是时， 结清应收款持仓，否则不需要存入此字段
			if(StringUtil.equals(dto.getIsClean(), DictCodeList.是否_是.getValue())){
				baseTradeInfoDto.setAmount(finAssetIntInfo.getChargeUnpayInterest());
			}
    	}
		//金融产品交易及相关-利息罚息   列表.实收罚息> 0 且 列表隐藏.理论利息罚息 >0 时录入
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getPenaltyIntamt())
    			&&finAssetIntInfo.getPenaltyIntamt().compareTo(BigDecimal.ZERO)>0
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getInterestPenalty())
    			&&finAssetIntInfo.getInterestPenalty().compareTo(BigDecimal.ZERO)>0){
    		//交易表信息
    		TrdProductDeal penaltyTrdProductDeal =  new TrdProductDeal();
			//利息总额 列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		penaltyTrdProductDeal.setIntAmt(ArithmeticUtil.subtract(finAssetIntInfo.getPenaltyIntamt(), ArithmeticUtil.doAdd(penaltyPrinAmt,penaltyIntAmt)));
    		//全价总额  列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		penaltyTrdProductDeal.setFpriceAmt(penaltyTrdProductDeal.getIntAmt());
    		//交易金额  列表.实收利息 - 上述还本未付息利息中利息总额合计值
    		penaltyTrdProductDeal.setTradeAmt(penaltyTrdProductDeal.getIntAmt());
    		//分支序号  利息分支序号
    		penaltyTrdProductDeal.setBranch(dto.getLiBranch());
    		//交易表信息
    		penaltyTrdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),null,penaltyTrdProductDeal,true,dto.getLiBranch(),bjCashId,totalTradeId,null,null);
			//交易附属
    		TrdProductDealAdd penaltyTrdProductDealAdd=this.gen交易附属(finAssetIntInfo, penaltyTrdProductDeal, dto);
			//组合交易表信息
    		List<PtlTrade> penaltyPtlTrades = this.gen组合交易(dto,penaltyTrdProductDeal,finAssetIntInfo);
    		//组装加入罚息信息
			BaseTradeInfoDto baseTradeInfoDto3 = new BaseTradeInfoDto();
			baseTradeInfoDto3.setTrdProductDeal(penaltyTrdProductDeal);
			baseTradeInfoDto3.setTrdProductDealAdd(penaltyTrdProductDealAdd);
			baseTradeInfoDto3.setPtlTrade(penaltyPtlTrades);
			penaltyIntBaseTradeInfoDtoList.add(baseTradeInfoDto3);
			
			//指向通道费对象
			deal=penaltyTrdProductDeal;
    	}
    	
    	//实收罚息存在  但理论罚息不存在  有利息 算利息罚息 无利息算本金罚息
    	if(StringUtil.isNotEmpty(finAssetIntInfo)
    			&&StringUtil.isNotEmpty(finAssetIntInfo.getPenaltyIntamt())
    			&&finAssetIntInfo.getPenaltyIntamt().compareTo(BigDecimal.ZERO)>0
    			&&(!StringUtil.isNotEmpty(finAssetIntInfo.getTheoryPenalty())
    					||finAssetIntInfo.getTheoryPenalty().compareTo(BigDecimal.ZERO)==0)){
    		//交易表信息
    		TrdProductDeal penaltyTrdProductDeal =  new TrdProductDeal();
			//利息总额 列表.实收实收罚息
    		penaltyTrdProductDeal.setIntAmt(finAssetIntInfo.getPenaltyIntamt());
    		//全价总额  列表.实收实收罚息
    		penaltyTrdProductDeal.setFpriceAmt(penaltyTrdProductDeal.getIntAmt());
    		//交易金额  列表.实收实收罚息
    		penaltyTrdProductDeal.setTradeAmt(penaltyTrdProductDeal.getIntAmt());
    		if(StringUtil.isNotEmpty(dto.getLiBranch())){
    			//分支序号  利息分支序号
        		penaltyTrdProductDeal.setBranch(dto.getLiBranch());
    		}else{
        		//分支序号  传入.本金分支序号
        		penaltyTrdProductDeal.setBranch(dto.getBjBranch());
    		}
    		//交易表信息
    		penaltyTrdProductDeal = this.gen交易(finAssetIntInfo,trdFinProductDealTotal ,dto,DictCodeList.交易方向_收息到账.getValue(),null,penaltyTrdProductDeal,true,dto.getLiBranch(),bjCashId,totalTradeId,null,null);
			//交易附属
    		TrdProductDealAdd penaltyTrdProductDealAdd=this.gen交易附属(finAssetIntInfo, penaltyTrdProductDeal, dto);
			//组合交易表信息
    		List<PtlTrade> penaltyPtlTrades = this.gen组合交易(dto,penaltyTrdProductDeal,finAssetIntInfo);
    		//组装加入罚息信息
			BaseTradeInfoDto baseTradeInfoDto3 = new BaseTradeInfoDto();
			baseTradeInfoDto3.setTrdProductDeal(penaltyTrdProductDeal);
			baseTradeInfoDto3.setTrdProductDealAdd(penaltyTrdProductDealAdd);
			baseTradeInfoDto3.setPtlTrade(penaltyPtlTrades);
			penaltyIntBaseTradeInfoDtoList.add(baseTradeInfoDto3);
			
			//指向通道费对象
			deal=penaltyTrdProductDeal;
    	}
    	//通道费存在的时，按照利息，利息罚息，本金罚息，本金的顺序挂入通道费。
    	if(StringUtil.isNotEmpty(finAssetIntInfo.getChannelFee())&&finAssetIntInfo.getChannelFee().compareTo(BigDecimal.ZERO)>0){
    		//利息存在
    		if(StringUtil.isNotEmpty(baseTradeInfoDto.getTrdProductDeal())){
    			deal=baseTradeInfoDto.getTrdProductDeal();
    		}
    		//通道费对象为空  即仅存在本金  存入本金
    		if(!StringUtil.isNotEmpty(deal)){
    			deal = baseTradeInfoDto.getBaseTrade().getTrdProductDeal();
    		}
    		//塞入费用明细信息
    		baseTradeInfoDto.setTrdTradeFeeDetail(getFeeDetailList(deal,finAssetIntInfo,dto));
    	}
    	return baseTradeInfoDto;
	}
    
    /**
     * 
     * @param finAssetIntInfo 利息明细信息
     * @param trdFinProductDealTotal 总的汇总表信息
     * @param dto  页面对象
     * @param 交易方向
     * @param cashId 
     * @param trdProductDeal 
     * @param isPenalty 是否罚息
     * @param branch 分支序号
     * @param bjCashId 本金对应的cashId
     * @param totalTradeId 组合层totalTradeId
     * @param vdate 起息日
     * @param mdate 到期日
     * @return
     */
    private TrdProductDeal gen交易(FinAssetIntInfo finAssetIntInfo,TrdFinProductDealTotal trdFinProductDealTotal,
			TrdSecOverdueDurationDto dto,String 交易方向,String cashId,TrdProductDeal trdProductDeal,boolean isPenalty ,long branch,String bjCashId,String totalTradeId,Date vdate,Date mdate) {
    	BussinessCodeParameter parameter = new BussinessCodeParameter();
    	
    	parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        if(!StringUtil.isNotEmpty(trdProductDeal)){
        	trdProductDeal = new TrdProductDeal();
        }
      //罚息信息
		if (isPenalty){
			// 到期日 画面.资金到账日
			trdProductDeal.setMdate(dto.getTradeDate());
		}else{
			//传入 起息日 使用该起息日 否则使用画面端
	        if(StringUtil.isNotEmpty(vdate)){
	        	trdProductDeal.setVdate(vdate);
	        }else{
	        	trdProductDeal.setVdate(dto.getVdate());
	        }
	        //传入 到期日 使用该到期日 否则使用画面端
	        if(StringUtil.isNotEmpty(vdate)){
	        	trdProductDeal.setMdate(vdate);
	        }else{
	        	trdProductDeal.setMdate(dto.getMdate());
	        }
		}
        //交易编号自动生成
        trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
        //cashid
        String cashType=null;
		// 业务类型 。同各类资产的存续期交易
		if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
			trdProductDeal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
			// 同原存续期
			trdProductDeal.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
		} else if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
			trdProductDeal.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
		} else if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
			trdProductDeal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
		} else if (StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
			trdProductDeal.setBusiType(DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue());
		}
        //投管业务类型   数据字段。投管业务类型。已到期资产存续期
		trdProductDeal.setTgBusiType(DictCodeListExt3.平安业务类型_已到期资产存续期.getValue());
        
        if(StringUtil.isEmpty(cashId)){
        	Map<String, Object> paramMap = new HashMap<>();
    		paramMap.put("branch", branch);
//    		paramMap.put("mdate", dto.getMdate());
    		paramMap.put("finprodId", dto.getFinprodId());
    		List<String> cashTypeList = new ArrayList<String>();
    		//罚息信息
    		if (isPenalty){
    			cashTypeList.add(DictCodeListExt3.现金流类型_罚息收息.getValue());
    			//存入busiType aop会使用该busiType
    			trdProductDeal.setBusiType(DictCodeList.业务类型_逾期交易.getValue());
    		}else if(StringUtil.equals(交易方向, DictCodeList.交易方向_收息到账.getValue())){
    			cashTypeList.add(DictCodeList.现金流类型_中间收息.getValue());
        		cashTypeList.add(DictCodeList.现金流类型_到期收息.getValue());
    		}else if(StringUtil.equals(交易方向, DictCodeList.交易方向_还本到账.getValue())){
    			cashTypeList.add(DictCodeList.现金流类型_中间还本.getValue());
        		cashTypeList.add(DictCodeList.现金流类型_到期还本.getValue());
    		}
    		paramMap.put("cashTypeList", cashTypeList);
    		List<TrdSecOverdueDurationDto> resultmap  = autoPeriodSecOverCommonMapper.selectCashId(paramMap);
    		if(!StringUtil.isEmptyList(resultmap)){
    			cashId = resultmap.get(0).getCashId();
        		cashType = resultmap.get(0).getCashType();
        		if(StringUtil.equals(交易方向, DictCodeList.交易方向_还本到账.getValue())){
        			bjCashId= cashId;
        		}
    		}else{
    			cashId =bjCashId;
    		}
    		
        }
        trdProductDeal.setCashId(cashId);
		
		// status债券，项目类，计划类,存款 存入此字段，调用持仓流水，入库交易方向为收息到账，还本到账
		if (StringUtil.equals(交易方向, DictCodeList.交易方向_收息到账.getValue())) {
			trdProductDeal.setStatus(DictCodeList.交易方向_收息.getValue());
		} else if (StringUtil.isNotEmpty(cashType)) {
			String ps = AutoPeriodCommonService.change现金流类型To交易方向(cashType);
			trdProductDeal.setStatus(ps);
			//交易份额  列表实收本金
			trdProductDeal.setShareAmt(finAssetIntInfo.getPrinAmt());
			//交易本金  列表实收本金
			trdProductDeal.setPrinAmt(finAssetIntInfo.getPrinAmt());
			//全价总额  列表实收本金
			trdProductDeal.setFpriceAmt(finAssetIntInfo.getPrinAmt());
			//交易金额  列表实收本金
			trdProductDeal.setTradeAmt(finAssetIntInfo.getPrinAmt());
		}
		trdProductDeal.setPs(交易方向);
		
		//金融产品代码
		trdProductDeal.setFinprodId(dto.getFinprodId());
		//金融产品类型（投管分类） 根据传入字段分类8设置，数据字典.金融产品类
		trdProductDeal.setFinprodType2(dto.getFinprodType2());
		//本金分支序号  传入.本金分支序号
		if(!StringUtil.isNotEmpty(trdProductDeal.getBranch())){
			trdProductDeal.setBranch(dto.getBjBranch());
		}
		//交易日  画面。资金到账日
		trdProductDeal.setTradeDate(dto.getTradeDate());
		//交割日  画面。资金到账日
		trdProductDeal.setSettleDate(dto.getTradeDate());
		//交易币种  
		trdProductDeal.setCcy(dto.getCcy());
		
		//投资目的 列表.投资目的
		trdProductDeal.setInvAim(finAssetIntInfo.getInvAim());
		// 交易状态  数据字典缓存.交易状态.初始
		trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
		// 是否结清  画面.是否结清
		trdProductDeal.setIsClean(dto.getIsClean());
		//汇总交易编号  组合层.金融产品交易汇总.汇总交易编号
		trdProductDeal.setTotleTradeId(totalTradeId);
		//通道代码  画面.证券账户/通道中 存在于证券账户表中时，则登录空，否则 登录画面.证券账户/通道
		//证券管理户代码   画面.证券账户/通道中 存在于证券账户表中时，则登录画面.证券账户/通道，否则空
		if (!dto.isChannel()) {
			trdProductDeal.setSecManageAcctId(dto.getSecAcctId());
		} else {
			trdProductDeal.setChlId("999999".equals(dto.getSecAcctId()) ? null : dto.getSecAcctId());
		}
		//是否外部交易    是
		trdProductDeal.setIsOutTrade(DictCodeList.是否_是.getValue());
		//交易场所  
		trdProductDeal.setTradeMarket( dto.getTradeMarket());
		return trdProductDeal;
    }
    
    /**
     * 
     * @param finAssetIntInfo
     * @param trdProductDeal
     * @param trdSecOverdueDurationDto
     * @return
     */
    private TrdProductDealAdd gen交易附属(FinAssetIntInfo finAssetIntInfo,
			TrdProductDeal trdProductDeal,TrdSecOverdueDurationDto trdSecOverdueDurationDto) {
    	TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
    	// 交易编号  交易表。交易编号
    	trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
    	//生成方式 数据字典缓存.生成方式.画面录入
    	trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
    	//投资组合代码   列表。组合代码
    	trdProductDealAdd.setPortfolioId(finAssetIntInfo.getPortfolioId());
        trdProductDealAdd.setStatus(DictCodeList.数据状态_新建.getValue());
         return trdProductDealAdd;
	}
    
    private List<PtlTrade> gen组合交易(TrdSecOverdueDurationDto entity, TrdProductDeal deal, FinAssetIntInfo finAssetIntInfo) {
        List<PtlTrade> result = new ArrayList<>();
        PtlTrade ptlTrade2 = new PtlTrade();
        ptlTrade2.setPortfolioId(finAssetIntInfo.getPortfolioId());
        ptlTrade2.setBusiNo(deal.getTradeId());
//        // 添加投资目的
//        if(StringUtil.equals(finAssetIntInfo.getInvAim(),"999999")){
//            ptlTrade2.setInvAim(DictCodeListExt1.投资目的_卖空.getValue());
//        }
//        else{
//            ptlTrade2.setInvAim(finAssetIntInfo.getInvAim());
//        }
        ptlTrade2.setInvAim(finAssetIntInfo.getInvAim());
        ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade2.setBusiId(entity.getFinprodId());
        ptlTrade2.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
        ptlTrade2.setSecManageAcctId(entity.getSecManageAcctId());
        ptlTrade2.setSettleDate(entity.getTradeDate());
        result.add(ptlTrade2);
        return result;
    }
    

	private TrdDealTotalAdd gen汇总附属业务流程2(TrdSecOverdueDurationDto entity,
			TrdFinProductDealTotal trdFinProductDealTotal) {
    	TrdDealTotalAdd trdDealTotalAdd = new TrdDealTotalAdd();
    	//罚息金额 = 画面.实收罚息
    	trdDealTotalAdd.setPenaltyIntamt(entity.getPenaltyIntamt());
    	trdDealTotalAdd.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
    	trdDealTotalAdd.setWorkflowId(entity.getWorkflowId());
         return trdDealTotalAdd;
	}

    /**
     * 2.0改造生成汇总交易entity <功能详细描述>
     * 
     * @param entity 源entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    public TrdFinProductDealTotal gen汇总流程表2(TrdSecOverdueDurationDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
		// 业务类型 。同各类资产的存续期交易
		if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue());
		}

        //投管业务类型   数据字段。投管业务类型。已到期资产存续期
        trdFinProductDealTotal.setTgBusiType(DictCodeListExt3.平安业务类型_已到期资产存续期.getValue());
		//通道费不为空时，设置通道费信息
		if(entity.getChannelFee()!=null){
        	trdFinProductDealTotal.setFeeAmt(entity.getChannelFee());	
        	if(StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
        		if(StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
            		trdFinProductDealTotal.setFeeAmtPay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
            	}else{
            		trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
            	}
        	}else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
        		 if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
        			 if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
        				 trdFinProductDealTotal.setFeeAmtPay(entity.getChannelFee());
        			 }
        			 else {
        				 trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee());
        			 }
        		 }else{
        			 trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee());
        		 }
        	}else{
        		trdFinProductDealTotal.setFeeAmtUnpay(entity.getChannelFee()==null?BigDecimal.ZERO:entity.getChannelFee());
        	}
        }
		//金融产品代码
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        //金融产品类型（投管分类）   根据传入字段分类8设置，数据字典.金融产品类型(投管用).债券/存款/计划类/项目类资产
        trdFinProductDealTotal.setFinprodType2(entity.getFinprodType2());
        if (!entity.isChannel()) {
            trdFinProductDealTotal.setSecManageAcctId(entity.getSecAcctId());
        }
        else {
            trdFinProductDealTotal.setChlId("999999".equals(entity.getSecAcctId()) ? null : entity.getSecAcctId());
        }
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(AutoPeriodCommonService.change现金流类型To交易方向(entity.getCashType()));
     // 分支序号 传入.本金分支序号 != null then 传入.本金分支序号 else 传入.利息分支序号
 		if (StringUtil.isNotEmpty(entity.getBjBranch())) {
 			trdFinProductDealTotal.setBranch(entity.getBjBranch());
 		} else {
 			trdFinProductDealTotal.setBranch(entity.getLiBranch());
 		}
        trdFinProductDealTotal.setTradeDate(entity.getTradeDate());
        trdFinProductDealTotal.setVdate(entity.getVdate());
        trdFinProductDealTotal.setMdate(entity.getMdate());
        trdFinProductDealTotal.setSettleDate(entity.getTradeDate());
        //画面.实收本金
        trdFinProductDealTotal.setShareAmt(entity.getPrinAmt());
        //画面.实收本金
        trdFinProductDealTotal.setPrinAmt(entity.getPrinAmt());
        //画面.实收利息
        trdFinProductDealTotal.setIntAmt(entity.getIntAmt());
        //画面.实收本息总额
        trdFinProductDealTotal.setTradeAmt(entity.getTradeAmt());
        //画面.实收本金+画面.实收利息+实收罚息
        trdFinProductDealTotal.setFpriceAmt(ArithmeticUtil.doAdd(entity.getIntAmt(),entity.getPrinAmt(),entity.getPenaltyIntamt()));
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        //是否外部交易 是
        trdFinProductDealTotal.setIsOutTrade(DictCodeList.是否_是.getValue());
        //是否逾期交易  是
        trdFinProductDealTotal.setIsOverdueDeal(DictCodeList.是否_是.getValue());
		// 是否结清 画面.是否结清
		trdFinProductDealTotal.setIsClean(entity.getIsClean());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        //多级法人改造 add by caiqun 20200615
		if(MultilevelDataAuthorityUtil.isEnabled()){
			// 机构代码
			trdFinProductDealTotal.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser()));
		}
		trdFinProductDealTotal.setInstitutionCode(CommonUtil.getOrgCode()); // 机构代码
		//多级法人改造 add by caiqun 20200615
        return trdFinProductDealTotal;
    }

    /**
     * 2.0改造生成汇总扩展交易entity <功能详细描述>
     * 
     * @param entity 业务entity
     * @param trdFinProductDealTotal 汇总entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal gen汇总扩展业务流程2(TrdSecOverdueDurationDto entity, TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal wflPaTrdFinDealTotal = new PaTrdFinProductDealTotal();
        //汇总交易编号 =金融产品交易汇总.汇总交易编号
        wflPaTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
//        wflPaTrdFinDealTotal.setUnitInt(entity.getUnitIntAmt());
//        wflPaTrdFinDealTotal.setUnitPrin(entity.getIntPrinAmt());
//        wflPaTrdFinDealTotal.setPrin(entity.getPrinAmt());
//        wflPaTrdFinDealTotal.setRate(ArithmeticUtil.divide(entity.getYield(), 100, 5));
//        wflPaTrdFinDealTotal.setPaBussType(entity.getPaBussType());
//        wflPaTrdFinDealTotal.setTheoryAmt(entity.getFpriceAmt());
//        wflPaTrdFinDealTotal.setTheoryPayDate(DateUtil.parse(entity.getPayDate()));
        //理论应计利息 = 画面.未支付利息
        wflPaTrdFinDealTotal.setTheoryInt(entity.getTheoryInt());
        //理论还本金额= 画面.未支付本金
        wflPaTrdFinDealTotal.setTheoryPrin(entity.getTheoryPrin());
        //理论罚息金额 = 画面.理论罚息总额
        wflPaTrdFinDealTotal.setTheoryPenaltyInt(entity.getTheoryPenaltyInt());;
        //平安业务类型 = 数据字典.投管业务类型.已到期资产存续期
        wflPaTrdFinDealTotal.setPaBussType(DictCodeListExt3.平安业务类型_已到期资产存续期.getValue());
        wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
        return wflPaTrdFinDealTotal;
    }

    /**
     * 2.0根据利息明细生成汇总交易及扩展 <功能详细描述>
     * 
     * @param finAssetIntInfo 利息明细
     * @param entity entity
     * @param baseTradeInfoDto 交易dto
     * @see
     * @since 1.0
     */
    private void gen利息明细汇总表附属表和扩展表(FinAssetIntInfo finAssetIntInfo, TrdSecOverdueDurationDto entity, BaseTradeInfoDto baseTradeInfoDto) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(
            autoPeriodCommonService.buildBussinessCode(new BussinessCodeParameter(DictCodeList.共通业务类型_资产存续期交易)));
		// 业务类型 。同各类资产的存续期交易
        if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_债券类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_存款类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_项目类资产存续期.getValue());
		} else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())) {
			trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_计划类资产_利率型_存续期.getValue());
		}
		//通道费不为空时，设置通道费信息
		if(finAssetIntInfo.getChannelFee()!=null){
        	trdFinProductDealTotal.setFeeAmt(finAssetIntInfo.getChannelFee());	
        	if(StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
        		if(StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
            		trdFinProductDealTotal.setFeeAmtPay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
            	}else{
            		trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
            	}
        	}else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
        		 if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
        			 if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
        				 trdFinProductDealTotal.setFeeAmtPay(finAssetIntInfo.getChannelFee());
        			 }
        			 else {
        				 trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee());
        			 }
        		 }else{
        			 trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee());
        		 }
        	}else{
        		trdFinProductDealTotal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
        	}
        }
        //金融产品代码
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        //金融产品类型（投管分类）   根据传入字段分类8设置，数据字典.金融产品类型(投管用).债券/存款/计划类/项目类资产
        trdFinProductDealTotal.setFinprodType2(entity.getFinprodType2());
		// 分支序号 传入.本金分支序号 != null then 传入.本金分支序号 else 传入.利息分支序号
		if (StringUtil.isNotEmpty(entity.getBjBranch())) {
			trdFinProductDealTotal.setBranch(entity.getBjBranch());
		} else {
			trdFinProductDealTotal.setBranch(entity.getLiBranch());
		}
		//交易日期   画面资金到账日
		trdFinProductDealTotal.setTradeDate(entity.getTradeDate());
		//起息日 画面计息开始日
		trdFinProductDealTotal.setVdate(entity.getVdate());
		//到期日  画面计息结束日
		trdFinProductDealTotal.setMdate(entity.getMdate());
		//交割日 画面资金到账日
		trdFinProductDealTotal.setSettleDate(entity.getTradeDate());
		//币种    隐藏字段币种
		trdFinProductDealTotal.setCcy(entity.getCcy());
		//交易份额  列表实收本金
		trdFinProductDealTotal.setShareAmt(finAssetIntInfo.getPrinAmt());
		//交易本金 列表实收本金
		trdFinProductDealTotal.setPrinAmt(finAssetIntInfo.getPrinAmt());
		//利息总额 列表实收利息
		trdFinProductDealTotal.setIntAmt(finAssetIntInfo.getIntAmt());
		//全价总额  列表实收本金+ 画面实收利息+实收罚息
		trdFinProductDealTotal.setFpriceAmt(ArithmeticUtil.doAdd(finAssetIntInfo.getPrinAmt(),finAssetIntInfo.getIntAmt(),finAssetIntInfo.getPenaltyIntamt()));
		//交易金额  画面实收本息总额
		trdFinProductDealTotal.setTradeAmt(finAssetIntInfo.getTradeAmt());
		//交易方向
		trdFinProductDealTotal.setPs(AutoPeriodCommonService.change现金流类型To交易方向(entity.getCashType()));
		// 投资目的
        trdFinProductDealTotal.setInvAim(finAssetIntInfo.getInvAim());
        // 交易状态  数据字典缓存.交易状态.初始
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        
        //是否外部交易 是
        trdFinProductDealTotal.setIsOutTrade(DictCodeList.是否_是.getValue());
        //是否逾期交易  是
        trdFinProductDealTotal.setIsOverdueDeal(DictCodeList.是否_是.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
		// 是否结清 画面.是否结清
        trdFinProductDealTotal.setIsClean(entity.getIsClean());
        //投资组合代码 列表.组合代码
        trdFinProductDealTotal.setPortfolioId(finAssetIntInfo.getPortfolioId());
        //投管业务类型   数据字段。投管业务类型。已到期资产存续期
        trdFinProductDealTotal.setTgBusiType(DictCodeListExt3.平安业务类型_已到期资产存续期.getValue());
        //上级汇总交易编号  最外层.金融产品交易汇总.汇总交易编号
        trdFinProductDealTotal.setpTotleTradeId(entity.getBusiCode());
        if (!entity.isChannel()) {
            trdFinProductDealTotal.setSecManageAcctId(entity.getSecAcctId());
        }
        else {
            trdFinProductDealTotal.setChlId("999999".equals(entity.getSecAcctId()) ? null : entity.getSecAcctId());
        }
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
		//多级法人改造 add by caiqun 20200708
		trdFinProductDealTotal.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser())); // 机构代码
		//多级法人改造 add by caiqun 20200708
        baseTradeInfoDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        //理论应计利息 = 画面.未支付利息
        paTrdFinProductDealTotal.setTheoryInt(finAssetIntInfo.getTheoryInt());
        //理论还本金额= 画面.未支付本金
        paTrdFinProductDealTotal.setTheoryPrin(finAssetIntInfo.getPrinAmtTheory());
        //理论罚息金额 = 画面.理论罚息总额
        paTrdFinProductDealTotal.setTheoryPenaltyInt(finAssetIntInfo.getTheoryPenalty());;
        //平安业务类型 = 数据字典.投管业务类型.已到期资产存续期
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt3.平安业务类型_已到期资产存续期.getValue());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        paTrdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        baseTradeInfoDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        
    	TrdDealTotalAdd trdDealTotalAdd = new TrdDealTotalAdd();
    	//罚息金额 = 画面.实收罚息
    	trdDealTotalAdd.setPenaltyIntamt(finAssetIntInfo.getPenaltyIntamt());
    	trdDealTotalAdd.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
    	trdDealTotalAdd.setWorkflowId(entity.getWorkflowId());
    	baseTradeInfoDto.setTrdDealTotalAdd(trdDealTotalAdd);
    }



    /**
     * add by wangpeng 生成通道费明细  修改交易表交易金额
     */
    private List<TrdTradeFeeDetail>  getFeeDetailList(TrdProductDeal deal,FinAssetIntInfo finAssetIntInfo,TrdSecOverdueDurationDto entity){
    	deal.setFeeAmt(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
    	TrdTradeFeeDetail detail=new TrdTradeFeeDetail();
    	List<TrdTradeFeeDetail> detailList=new ArrayList<TrdTradeFeeDetail>();
    	detail.setTradeId(deal.getTradeId());
    	detail.setFeeAmt(finAssetIntInfo.getChannelFee());
    	detail.setFeeType(DictCodeListExt1.一次性费用类型_通道费.getValue());
    	detail.setCcy(deal.getCcy());
//    	if(entity.isChannel()&&StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
//    		detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
//    		deal.setFeeAmtPay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//    	}else{
//    		detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
//    		deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
//    	}
    	detail.setIsPayWithTrade(DictCodeList.是否_否.getValue());
    	if(StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
    		if(StringUtil.equals(entity.getCollectType(),DictCodeListExt2.收取方式_回款前扣除.getValue())){
    			deal.setFeeAmtPay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
    			detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
        	}else{
        		deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
        	}
    	}else if (StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue())) {
    		 if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
    			 if (StringUtil.equals(DictCodeListExt2.收取方式_回款前扣除.getValue(), entity.getCollectType())) {
    				 deal.setFeeAmtPay(finAssetIntInfo.getChannelFee());
    				 detail.setIsPayWithTrade(DictCodeList.是否_是.getValue());
    			 }
    			 else {
    				 deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee());
    			 }
    		 }else{
    			 deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee());
    		 }
    	}else{
    		deal.setFeeAmtUnpay(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee());
    	}
    	//随交易支付
    	if(StringUtil.equals(detail.getIsPayWithTrade(), DictCodeList.是否_是.getValue())){
    		deal.setTradeAmt(deal.getTradeAmt().subtract(finAssetIntInfo.getChannelFee()==null?BigDecimal.ZERO:finAssetIntInfo.getChannelFee()));
    	}
    	detail.setFeeId(DictCodeListExt1.一次性费用类型_通道费.getValue());
    	detail.setChlFinprodId(entity.getSecManageAcctId());
    	detail.setCreateUser(deal.getCreateUser());
    	detail.setCreateTime(deal.getCreateTime());
    	detail.setUpdateUser(deal.getUpdateUser());
    	detail.setUpdateTime(deal.getUpdateTime());
    	detailList.add(detail);
    	return detailList;
    }
}
