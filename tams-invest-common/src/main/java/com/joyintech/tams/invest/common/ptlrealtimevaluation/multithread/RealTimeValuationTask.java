package com.joyintech.tams.invest.common.ptlrealtimevaluation.multithread;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import com.joyintech.tams.common.base.entity.BokTradeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.assetvaluation.BaseAssetValuationService;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.tradevaluation.FeeTradeValuationService;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.tradevaluation.ProdTradeValuationService;

/**
 * @program: tams-disclosure-busicommon
 * @description: 多线程任务
 * @author: 强成西
 * @create: 2020-04-16 19:43
 **/
public class RealTimeValuationTask  implements Callable<String> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

// TODO 属性   T+N资产变化

// TODO 属性  T+N现金变化

    // TODO 属性 出参.List<组合资产估值扩展Entity>
    private String 估值类型;
    private String 投资目的;
    private MockAssetInfoDto 资产基本信息;
    private MockPtlPortfolioDto 组合基本信息dto;
	private List<MockTrdProductDealDto> 负债端交易列表;
    private List<MockTrdProductDealDto> 费用交易列表;
	private List<MockTrdProductDealDto> 负债端T_N交易列表;
    private ConcurrentHashMap<MockSingleAssetChangingKey, Map<String,MockSingleAssetChanging>> t_N资产变化;
    private ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化;
    private	Map<String, MockSingleAssetChanging> t_N单资产变化;
    private String 测算开始日;
    private String 测算结束日;
    private String 基准日期;
    private List<MockMstExchargeQuoteDto> 汇率行情列表;
    private List<MockPtlFeePositionDto> 组合费用头寸扩展列表;
    private List<MockPtlPortfolioValuationDto> 处理后组合估值列表;
    private MockPtlSecValuationDto 组合资产估值;
    private List<MockTrdProductDealDto> 资产端交易列表;
    private List<MockTrdProductDealDto> 资产端t_N交易列表; 
    private MockTrdProductDealDto 划款类交易;
    private List<MockPtlSecValuationDto> 处理后组合资产估值拓展;
    private List<BokTradeOrder> 交易排序规则;
    private Map<String, LiabilitiesChanging> t_N负债变化;
    private MockPtlPortfolioValuationDto 组合估值;
    private List<MockPtlFeePositionDto> 处理后组合费用头寸拓展;
    
    
    public RealTimeValuationTask(String 估值类型,String 投资目的,MockAssetInfoDto 资产基本信息, MockPtlPortfolioDto 组合基本信息dto,
    		MockPtlSecValuationDto 组合资产估值,
    		List<MockTrdProductDealDto> 资产端交易列表,
			List<MockTrdProductDealDto> 资产端t_N交易列表,
			Map<String, MockSingleAssetChanging> t_N单资产变化,
			String 测算开始日, String 测算结束日, String 基准日期,
			List<MockMstExchargeQuoteDto> 汇率行情列表,
			List<MockPtlSecValuationDto> 处理后组合资产估值拓展,
			ConcurrentHashMap<MockSingleAssetChangingKey, Map<String,MockSingleAssetChanging>> t_N资产变化,
			ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化,
            List<BokTradeOrder> 交易排序规则) {
    	this.估值类型 = 估值类型;
		this.投资目的 = 投资目的;
    	this.资产基本信息 = 资产基本信息;
        this.组合基本信息dto = 组合基本信息dto;
        this.组合资产估值 = 组合资产估值;
        this.资产端交易列表 = 资产端交易列表;
        this.资产端t_N交易列表 = 资产端t_N交易列表;
        this.t_N单资产变化 = t_N单资产变化;
        this.测算开始日 = 测算开始日;
        this.测算结束日 = 测算结束日;
        this.基准日期 = 基准日期;
        this.汇率行情列表 = 汇率行情列表;
        this.处理后组合资产估值拓展 = 处理后组合资产估值拓展;
        this.t_N资产变化 = t_N资产变化;
        this.t_N现金变化 = t_N现金变化;
        this.交易排序规则 = 交易排序规则;
    }
    
    
    public RealTimeValuationTask(String 估值类型,ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化, MockTrdProductDealDto 划款类交易, String 基准日期) {
		// TODO Auto-generated constructor stub
    	this.估值类型 = 估值类型;
    	this.t_N现金变化 = t_N现金变化;
    	this.划款类交易 = 划款类交易;
    	this.基准日期 = 基准日期;
	}
    
    public RealTimeValuationTask(String 估值类型,MockPtlPortfolioValuationDto 组合估值,
    		MockPtlPortfolioDto 组合基本信息dto, List<MockTrdProductDealDto> 负债端交易列表, List<MockTrdProductDealDto>负债端T_N交易列表,
    		Map<String, LiabilitiesChanging> t_N负债变化, String 测算开始日, String 测算结束日, String 基准日期,
			ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化,List<MockPtlPortfolioValuationDto> 处理后组合估值列表) {
    	this.估值类型 = 估值类型;
    	this.组合估值 = 组合估值;
    	this.组合基本信息dto = 组合基本信息dto;
    	this.负债端交易列表 = 负债端交易列表;
    	this.负债端T_N交易列表 = 负债端T_N交易列表;
    	this.t_N负债变化 = t_N负债变化;
    	this.测算开始日 = 测算开始日;
    	this.测算结束日 = 测算结束日;
    	this.基准日期 = 基准日期;
    	this.t_N现金变化 = t_N现金变化;
    	this.处理后组合估值列表 = 处理后组合估值列表;
    }

    
    public RealTimeValuationTask(String 估值类型,List<MockPtlFeePositionDto> 组合费用头寸扩展列表,MockPtlPortfolioDto 组合基本信息dto,
    		List<MockTrdProductDealDto> 费用交易列表,String 测算开始日, String 测算结束日, String 基准日期,
    		List<MockMstExchargeQuoteDto> 汇率行情列表,ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化,
    		List<MockPtlFeePositionDto> 处理后组合费用头寸拓展) {
    	this.估值类型 = 估值类型;
    	this.组合费用头寸扩展列表 = 组合费用头寸扩展列表;
    	this.组合基本信息dto = 组合基本信息dto;
    	this.费用交易列表 = 费用交易列表;
    	this.测算开始日 = 测算开始日;
    	this.测算结束日 = 测算结束日;
    	this.基准日期 = 基准日期;
    	this.汇率行情列表 = 汇率行情列表;
    	this.t_N现金变化 = t_N现金变化;
    	this.处理后组合费用头寸拓展 = 处理后组合费用头寸拓展;
    }

	@Override
    public String call() throws Exception {
    	// 调用资产估值
    	if(StringUtil.equals(估值类型, RealTimeValCommonUtil.估值类型_资产估值)) {
        	BaseAssetValuationService.execute资产估值(投资目的, 资产基本信息, 组合基本信息dto, 组合资产估值, 资产端交易列表, 
        			资产端t_N交易列表, t_N单资产变化,
        			测算开始日, 测算结束日, 基准日期, 汇率行情列表,处理后组合资产估值拓展, t_N资产变化,t_N现金变化,
                    交易排序规则);
    	}else if(StringUtil.equals(估值类型, RealTimeValCommonUtil.估值类型_划款交易)) {
    		BaseAssetValuationService.execute划款交易(t_N现金变化,划款类交易,基准日期);
    	}else if(StringUtil.equals(估值类型, RealTimeValCommonUtil.估值类型_产品存续期交易)) {
    		MockCalcAssetValuationResult 产品存续期交易result = ProdTradeValuationService.excuteTrade(组合估值, 组合基本信息dto, 负债端交易列表, 负债端T_N交易列表,
    				t_N负债变化, 测算开始日, 测算结束日, 基准日期);
    		t_N负债变化 = 产品存续期交易result.getLiabilitiesChanging();
    		处理后组合估值列表.addAll(产品存续期交易result.getMockPtlPortfolioValuationDtoList());
    		RealTimeValCommonUtil.现金变化合并处理T_N(t_N现金变化, 产品存续期交易result.getCashChanging());
    	}else if(StringUtil.equals(估值类型, RealTimeValCommonUtil.估值类型_费用交易)) {
			MockCalcAssetValuationResult 费用交易result = FeeTradeValuationService.excute(组合费用头寸扩展列表, 组合基本信息dto, 费用交易列表,
					测算开始日,测算结束日, 基准日期, 组合基本信息dto.getCcy(), 汇率行情列表);
			处理后组合费用头寸拓展.addAll(费用交易result.getMockPtlFeePositionDtoList());
			RealTimeValCommonUtil.现金变化合并处理T_N(t_N现金变化, 费用交易result.getCashChanging());
    	}
        return null;
    }
}
