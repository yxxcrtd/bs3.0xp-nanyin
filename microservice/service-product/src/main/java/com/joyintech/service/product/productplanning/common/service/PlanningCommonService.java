package com.joyintech.service.product.productplanning.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.prdrisklevel.dto.PrdRiskLevel;
import com.joyintech.service.product.productplanning.common.dao.PlanningCommonMapper;
import com.joyintech.service.product.productplanning.common.dto.BondRepaymentAmountDto;
import com.joyintech.service.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.service.product.productplanning.common.dto.PlanningCommonDto;
import com.joyintech.service.product.productplanning.common.dto.TacticsOptimizeDto;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
/**
 * 产品规划共通
 */
@Service("backPlanningCommonService")
@Transactional(rollbackFor = Exception.class)
public class PlanningCommonService extends BaseService<PrdRiskLevel> {

    @Autowired
    private PlanningCommonMapper planningCommonMapper;

    /**
     * 持仓查询
     * @param params :cdate 参数.基准日
     */
    public List<HoldPositionDto> queryHoldPosition(Map<String,Object> params){
        return planningCommonMapper.queryHoldPosition(params);
    }

    /**
     * 资产分类查询
     */
    public List<AssetClass> queryAssetType(){
        return planningCommonMapper.selectAllAssetClass();
    }

    /**
     * 战术配置优化查询
     *
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    public List<TacticsOptimizeDto> queryTacticsOptimize(Map<String,Object> params){
        return planningCommonMapper.queryTacticsOptimize(params);
    }

    /**
     * 产品规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    public List<PrdPlanInfo> queryProductPlan(Map<String,Object> params){
        return planningCommonMapper.queryProductPlan(params);
    }

    /**
     * 资产规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     */
    public List<AssetPlanInfo> quertAssetPlan(Map<String,Object> params){
        return planningCommonMapper.quertAssetPlan(params);
    }

    /**
     * 规划策略查询
     */
    public List<PtlInvStrategy> queryPlanStrategy(){
        Map<String,Object> params = new HashMap<>();
        params.put("strategy_class",DictCodeListExt3.策略分类_规划策略.getValue());
        params.put("use_status",DictCodeListExt1.使用状态_启用.getValue());
        return planningCommonMapper.queryPlanStrategy(params);
    }

    /**
     * 债券还本到期金额
     * @param paramMap
     * @return
     */
    public List<BondRepaymentAmountDto> getBondRepaymentAmount(Map<String, Object> paramMap){
        paramMap.put("POSITION_TYPE",DictCodeList.持仓类型_实际.getValue());
        paramMap.put("middle",BussinessDictCodeList.现金流类型_中间还本.getValue());
        paramMap.put("end",BussinessDictCodeList.现金流类型_到期还本.getValue());
        paramMap.put("prodType",DictCodeListExt1.金融产品类型_债券.getValue());
        paramMap.put("NO",DictCodeList.是否_否.getValue());
        paramMap.put("YES",DictCodeList.是否_是.getValue());
        paramMap.put("SH",DictCodeListExt1.行权方式_WIND_赎回.getValue());
        paramMap.put("HS",DictCodeListExt1.行权方式_WIND_回售.getValue());
        return planningCommonMapper.getBondRepaymentAmount(paramMap);
    }

    /**
     * 资产还本到期金额
     * @param map
     * @return
     */
    public List<AssetConfigMonDetail> getCurEndAmt(Map<String, Object> map){
        map.put("holdingType",DictCodeList.持仓类型_实际.getValue());
        return  planningCommonMapper.getCurEndAmt(map);
    }


    public Page<PlanningCommonDto> queryForPagePlanningCommon(int page, int length, Map<String, Object> paramMap) {
    	
    	paramMap.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()); // 数据字典.金融产品类型（投管）.理财产品
    	paramMap.put("monetaryTransaction", DictCodeList.业务类型_货币型TA交易.getValue()); // 数据字典.业务类型.货币型TA交易
    	paramMap.put("netWorthTransaction", DictCodeList.业务类型_净值型TA交易.getValue()); // 数据字典.业务类型.净值型TA交易
    	paramMap.put("expectedReturnTrading", DictCodeList.业务类型_预期收益型TA交易.getValue()); // 数据字典.业务类型.预期收益型TA交易
    	paramMap.put("negate", DictCodeListExt1.是否_否.getValue());
    	paramMap.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue()); // 投资状态
    	paramMap.put("strongShareIncrease", DictCodeList.交易方向_份额强增.getValue()); // 份额增强
    	paramMap.put("productSubscription", DictCodeList.交易方向_产品认购.getValue()); // 产品认购
    	paramMap.put("productPurchase", DictCodeList.交易方向_产品申购.getValue()); // 产品申购
    	paramMap.put("productDividendsInto", DictCodeList.交易方向_产品红利转投.getValue()); // 产品红利转投
    	paramMap.put("productRedemption", DictCodeList.交易方向_产品赎回.getValue()); // 产品赎回
    	paramMap.put("earlyRedemptionOfProducts", DictCodeList.交易方向_产品提前赎回.getValue()); // 产品提前赎回
    	paramMap.put("redemptionOfProductsInDefault", DictCodeList.交易方向_产品违约赎回.getValue()); // 产品违约赎回
    	paramMap.put("productMaturity", DictCodeList.交易方向_产品到期.getValue()); // 产品到期
    	paramMap.put("earlyTermination", DictCodeList.交易方向_提前终止.getValue()); // 提前终止
//    	paramMap.put("productReturnInTheMiddle", DictCodeList.交易方向_产品中间还本.getValue()); // 产品中间还本
    	paramMap.put("shareStrongBy", DictCodeList.交易方向_份额强减.getValue()); // 份额强减
    	
        return this.queryForPageForSelectReturn(page, length, ()-> planningCommonMapper.planningCommonQuery(paramMap));
    }
    /**
     * 存续产品系列规划信息查询
     * @param paramMap
     * @return
     */
    public List<PrdPlanDetail> getPrdPlanDetail(Map<String, Object> paramMap){
        List<PrdPlanDetail> prdPlanDetail = planningCommonMapper.getPrdPlanDetail(paramMap);
        return  prdPlanDetail;
    }
    
    /**
     * 投资策略查询
     * @param paramMap
     * @return
     */
    public List<SelectOptionDto> getPtlInvStrategyList(Map<String, Object> paramMap){
    	paramMap.put("strategy_class",DictCodeListExt3.策略分类_规划策略.getValue());
    	paramMap.put("use_status",DictCodeListExt1.使用状态_启用.getValue());
        List<SelectOptionDto> ptlInvStrategyList = planningCommonMapper.getPtlInvStrategyList(paramMap);
        for (SelectOptionDto selectOptionDto : ptlInvStrategyList) {
            selectOptionDto.setText(selectOptionDto.getText()+"("+selectOptionDto.getId()+")");
        }
        return ptlInvStrategyList;
    }
}
