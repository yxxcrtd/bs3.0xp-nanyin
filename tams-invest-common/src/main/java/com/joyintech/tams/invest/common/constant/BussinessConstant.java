/*
 * 文件名：BussinessConstant.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.constant;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年8月21日
 * @see BussinessConstant
 * @since 1.0
 */
public interface BussinessConstant {

    /**
     * 风险试算CHECK开关
     */
    String RISK_MATH_SWITCH = "risk_math_switch";
    /**
     * 委托授权额度check开关
     */
    String ETRLIMIT_SWITCH = "etrlimit_switch";
    /**
     * 配置天数
     */
    String CONFIGURATION_DAYS = "configuration_days";

    /**
     * 产品首次付息日最多允许推迟年数
     */
    String MAX_AFTER_NOW_YEAR = "max_pay_interest_year";

    /**
     * 节假日获取延后天数（画面用）
     */
    String HOLIDAY_POSTPONE_DAYS = "holiday_postpone_days";

    /**
     * 分支序号
     */
    String NOBRANCHNOVALUE = "noBranchNoValue";

    /**
     * 产品托管户(虚拟).账户代码
     */
    String PROD_TRU_ACCT_NO_KEY = "prod_tru_acct_no";

    /**
     * 产品托管户(虚拟).账户名称
     */
    String PROD_TRU_ACCT_NAME_KEY = "prod_tru_acct_name";

    /**
     * 特拆账户.账户代码
     */
    String LENDING_ACCT_NO_KEY = "lending_acct_no";

    /**
     * 特拆账户.账户名称
     */
    String LENDING_ACCT_NAME_KEY = "lending_acct_name";

    /**
     * 清算户（虚拟）.账户代码
     */
    String SETTLE_ACCT_NO_KEY = "settle_acct_no";

    /**
     * 清算户（虚拟）.账户名称
     */
    String SETTLE_ACCT_NAME_KEY = "settle_acct_name";

    // 场内基金改造 add by renping 20191117 start
    /**
     * 交易对手ID（上海证券交易所）
     */
    String COUNTER_PARTY_ID_SHANGHAI = "JYDSJYS000000001";

    /**
     * 交易对手ID（深圳证券交易所）
     */
    String COUNTER_PARTY_ID_SHENZHEN = "JYDSJYS000000002";
    // 场内基金改造 add by renping 20191117 end

    // add by wangguoxian 外汇即远掉期集中净额清算用集中账户 20190805 start
    /**
     * 集中净额清算户.账户代码
     */
    String CENTRAL_NET_LIQUIDATION_ACCOUNT_NO_KEY = "CENTRAL_NET_LIQUIDATION_ACCOUNT_NO_";

    /**
     * 集中净额清算户.账户代码
     */
    String CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME_KEY = "CENTRAL_NET_LIQUIDATION_ACCOUNT_NAME_";
    // add by wangguoxian 外汇即远掉期集中净额清算用集中账户 20190805 end

    // add by renping 利率互换划款指令用行内统一账户 20190826 start
    /**
     * 利率互换行内统一账户.账户代码
     */
    String IRS_ACCOUNT_NO_KEY = "IRS_ACCOUNT_NO";

    /**
     * 利率互换行内统一账户.账户名称
     */
    String IRS_ACCOUNT_NAME_KEY = "IRS_ACCOUNT_NAME";
    // add by renping 利率互换划款指令用行内统一账户 20190826 end

    /**
     * 发行机构代码
     */
    String ISSUE_PARTY_ID = "issue_party_id";

    /**
     * 发行机构名称
     */
   // String ISSUE_PARTY_NAME = "issue_party_name";

    /**
     * 报送信息
     */
    String REPORT_INFORMATION = "report_information";

    /**
     * 平台日计算相加的天数
     */
    String PLUS_DAYS = "PLUS_DAYS";

    /**
     * 撤销
     */
    String REVOKE = "revoke";

    /**
     * 产品新建时未选组合的场景默认生成的组合经办用户Id
     */
    String PORTFOLIO_HANDLE_USERID = "portfolio_handle_userid";

    /**
     * 流程相关
     */
    String SUBMIT = "submit";
    String 交易经办 = "TRADE_OPERATOR";
    String 交易复核 = "TRADE_CHECKER";
    String 总集经办 = "BACK_OPERATOR";
    String 总集复核 = "BACK_CHECKER";
    String XN_STANDARD_BOND_PRODID = "XN_PRODID999";
    /**
     * 是否需要上级审批（是否已超授权）
     */
    String 是否需要上级审批 = "needNextCheck";
    
    /**
     * 指令交易录单是否需要上级审批
     */
    String 指令交易录单是否需要上级审批 = "approverFlage";
    /**
     * 投资交易撤销，撤销交易编号后缀
     */
    String TRADE_REVOKE_TRADE_ID_SPLIT_STR = "R";
    String 存款类资产存放同业保本系列的单据业务说明_KEY = "PRINT_DEPO_D02_BUSI_COMMENT";
    String 存款类资产同业借款交易条款_KEY = "PRINT_DEPO_D01_BUSI_COMMENT";
    // 一体化对应etliu
    /**
     * 默认支付频率
     */
    String DEFAULT_PAY_CYCLE = "1";
    /**
     * 交易日历：中国法定节假日
     */
    String CALENDARID_CHINA_HOLIDAYS = "01";
    /**
     * 授信模式
     */
    String CEMODEL = "CE_MODEL";
    //sjj add 20180131 授信相关开关 start
    /**
     *是否占用授信开关
     */
    String ISCREDIT = "ISCREDIT";
    /**
     *授信使用条件开关
     */
    String CREDIT_CONDITION_SHOW = "CREDIT_CONDITION_SHOW";
    /**
     *是否需要监控授信开关
     */
    String ISNEEDCONTROLCREDIT = "ISNEEDCONTROLCREDIT";
  //sjj add 20180131 授信相关开关 end

    // 2.0自动生成反向交易 bug修改 start
    /**
     * 是否自动生成反向交易
     */
    String IS_AUTO_GENERATE_NEGATE_DEAL = "is_auto_generate_negate_deal";
    // 2.0自动生成反向交易 bug修改 end
    
    /**
     * 系统参数code，是否需要走超授权流程标识，value是数据字典 01: 是、02: 否
     */
    String AUTHORIZE_LIMIT = "AUTHORIZE_LIMIT";
    /**
     * 过滤器路径
     */
    String VALUATION_FILTER_URL = "com.joyintech.tams.invest.risk.valtblimport.filtertools.";

    /**
     * 过滤器合并列
     */
    String VALUATION_FILTER_MERGECOL = "MergeColsFilter";
    
    /**
     * 操作类型 - 新增
     */
    String OPERATE_TYPE_ADD = "ADD";
    
    /**
     * 操作类型 - 修改
     */
    String OPERATE_TYPE_UPDATE = "UPDATE";
    
    /**
     * 操作类型 - 删除
     */
    String OPERATE_TYPE_DELETE = "DELETE";
    
    /**
     * 期次管理方式 01:显性、02:隐性
     */
    String PERIODIC_MANAGE_INFORMATION = "PERIODIC_MANAGE_INFORMATION";
    /**
     * 产品信息与申报信息是否合并 Y 合并  N 分开
     */
    String PRODUCTT_IS_MERGER = "PRODUCTT_IS_MERGER";
    /**
     * 可转债委托check头寸软校验开关 01：软校验 02：强控
     */
    String CHECK_CASH_SWITCH = "check_cash_switch";

    /**
     * 是否有截标 01：有；02：无
     */
    String IS_CLOSE_BID = "is_close_bid";
    /**
     * 调整中签量 N：不调整；具体数字：代表调整量
     */
    String ADJUST_WINNING_AMOUNT = "adjust_winning_amount";
    /**
     * 是否显示日期值  Y：是  N：否
     */
    String IsDisplayDate = "IsDisplayDate";

    /**
     * 可转债交易check头寸开关 01：开 02：关
     */
    String TRD_CHECK_CASH_SWITCH = "TRD_CHECK_CASH_SWITCH";

    /**
     * 委托指令组合的可用持仓不足时配置的提醒或禁止 01：提醒 02：禁止
     */
    String ETR_CHECK_POS_SWITCH = "etr_check_pos_switch";

    /**
     * 委托指令组合的可用现金不足时配置的提醒或禁止 01：提醒 02：禁止
     */
    String ETR_CHECK_CASH_SWITCH = "etr_check_cash_switch";
	/**
     * 是否显示划款指令 01：是 02：否
     */
    String GEN_PAY_ORDERS_AUTO = "GEN_PAY_ORDERS_AUTO";
    /**
     * 是否显示划款指令 01：是 02：否
     */
    String ASSET_INFO_CHB_HIDE = "ASSET_INFO_CHB_HIDE";
    
    /**
     * 存款是否使用借款利率
     */
    String IS_CAL_LOADRATE="IS_CAL_LOADRATE";

    /**
     * 系统参数code，投管接口是否打开，value是数据字典 01: 是、02: 否
     */
    String IS_INVEST_INTERF_OPEN = "IS_INVEST_INTERF_OPEN";

    /**
     * 系统参数code，是否自动生成全部的赎回到账交易，value是数据字典 01: 是、02: 否
     */
    String IS_AUTO_REDEM_ACCOUNT_OPEN = "IS_AUTO_REDEM_ACCOUNT_OPEN";
    
    /**
     * 费用是否平均分配 add by lwj 20200323
     */
    String IS_AVERAGE_DISTRIBUTION_OF_EXPENSES = "IS_AVERAGE_DISTRIBUTION_OF_EXPENSES";
    
    /**
     *  add by wj 20200506 工作流优化 存款类交易  不入业务表的开关  02：不入业务表
     */
    String WFL_AUTO = "wfl_auto";
    /**
     * 提示信息换行
     */
    String MSG_SPLIT = "<br />";
    /**
     * 委托指令交易时间端配置的开关 Y：开 N：关
     */
    String ETR_TRADE_TIME_SWITCH = "etr_trade_time_switch";
    
    /**
     * 银行帐账套前缀
     */
    String BOK_BANK_PREFIX = "BANK_";

    /**
     * 公墓基金存续期处理逻辑 01：旧逻辑 02：新逻辑
     */
    String FUND_DEAL_TYPE = "FUND_DEAL_TYPE";

    /**
     * 系统参数code，场内交易指令画面行情区域自动刷新时间（毫秒）
     */
    String ETREXCHANGE_HQAREA_REFRESH_PERIOD = "etrExchange_HqArea_refresh_period";

    /**
     * 系统参数code，场内交易指令画面持仓列表自动刷新时间（毫秒）
     */
    String ETREXCHANGE_POSTABLE_REFRESH_PERIOD = "etrExchange_posTable_refresh_period";

    /**
     * 系统参数code，场内交易指令画面指令列表自动刷新时间（毫秒）
     */
    String ETREXCHANGE_ENTRUSTTABLE_REFRESH_PERIOD = "etrExchange_entrustTable_refresh_period";

    /**
     * 交易所协议回购指令画面非公开行情区域自动刷新时间（毫秒）
     */
    String UNOPENQUOTA_REFRESH_PERIOD = "etrAgreRepTrade_UnOpenQuota_refresh_period";
    /**
     * 交易所协议回购指令画面持仓列表自动刷新时间（毫秒）
     */
    String POSTABLE_REFRESH_PERIOD = "etrAgreRepTrade_posTable_refresh_period";
    /**
     * 交易所协议回购指令画面在途回购自动刷新时间（毫秒）
     */
    String ONWAYREP_REFRESH_PERIOD = "etrAgreRepTrade_onWayRep_refresh_period";
}
