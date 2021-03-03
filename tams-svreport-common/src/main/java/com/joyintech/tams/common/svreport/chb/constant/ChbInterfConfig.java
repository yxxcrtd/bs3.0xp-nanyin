package com.joyintech.tams.common.svreport.chb.constant;

public enum  ChbInterfConfig {
	底层持仓信息接口("CHB_BOT_AST_POS"),
	
	产品开放计划信息接口("CHB_PRD_OPENSCH"),
	
	产品报价变动信息接口("CHB_PRD_PERIODYIELD"),
	
	产品费用交易信息接口("CHB_PRD_FEE_DEAL"),
	
	产品存续交易信息接口("CHB_PRD_DEAL"),
	
	组合资产估值信息接口("CHB_PTL_VALUTION"),
	
	中债基础数据信息接口("CHB_BASEINFO"),
	
	中债资产要素信息接口("CHB_ASSET_INFO"),
	
	中债资产交易信息接口("CHB_INVEST_TRADE"),
	
	中债债券评级信息接口("CHB_SEC_RATING"),
	
	中债交易对手信息接口("CHB_COUNTER_PARTY"),
	
	产品要素信息接口("CHB_PRD_BASEINFO"),

	资产基础指标信息接口("CHB_ASSET_INDEX"),

	产品申报信息接口("CHB_PRD_DECLARE"),
	
	周期型产品余额接口("CHB_CPROD_BALANCE"),
	
	母子资产基础指标数据("CHB_CASSET_INDEX"),
	
	产品费用头寸接口("CHB_PRDFEE_BASIS_DATA"),
	
	产品分红交易接口("CHB_PRD_BONUS_DEAL"),

	产品利率基本信息接口("CHB_PRD_RATE"),
	资产利率基本信息接口("CHB_ASSET_RATE"),
	产品汇总交易接口("CHB_PRD_DEAL_TOTAL")
	;
	
	
    /**
     * 序列名
     */
    private String interfId;


    /**
     * @param sequenceName 序列名
     */
    ChbInterfConfig(String interfId) {
        this.interfId = interfId;
    }

    public String getInterId() {
        return this.interfId;
    }

}
