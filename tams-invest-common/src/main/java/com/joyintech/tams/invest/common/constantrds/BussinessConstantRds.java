/*
 * 文件名：BussinessConstantRds.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.constantrds;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author 裴家俊
 * @version 1.0 2019年8月21日
 * @see BussinessConstantRds
 * @since 1.0
 */
public interface BussinessConstantRds {
	
	/**
     * 风险事件买入有指标
     */
    String event_buy_calc = "event_buy_calc";
    /**
     * 风险事件买入无指标
     */
    String event_buy_nocalc = "event_buy_nocalc";
    /**
     * 风险事件卖出有指标
     */
    String event_sell_calc = "event_sell_calc";
    /**
     * 风险事件卖出无指标
     */
    String event_sell_nocalc = "event_sell_nocalc";

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

    /**
     * 发行机构代码
     */
    String ISSUE_PARTY_ID = "issue_party_id";

    /**
     * 发行机构名称
     */
  //  String ISSUE_PARTY_NAME = "issue_party_name";

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

    /**
     * 是否需要上级审批（是否已超授权）
     */
    String 是否需要上级审批 = "needNextCheck";

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
     * 文件分割符
     */
    byte B_TERM = 0x03;

    /**
     * 文件下载路径
     */
    String RSK_LOCAL_PATH = "rskLocalPath";

    /**
     * 文件路径
     */
    String RSK_REMOTE_PATH = "rskRemotePath";

    /**
     * 文件下载服务器主机
     */
    String RSK_FTP_HOST = "rskFtpHost";

    /**
     * 文件下载服务器端口
     */
    String RSK_FTP_PORT = "rskFtpPort";

    /**
     * 文件编码格式
     */
    String RSK_FTP_ENCODE = "rskFtpEncode";

    /**
     * 文件下载服务器登录用户
     */
    String RSK_FTP_USER = "rskFtpUser";

    /**
     * 文件下载服务器登录密码
     */
    String RSK_FTP_PASS = "rskFtpPass";

    /**
     * 本地生成DAT文件路径
     */
    String RSK_LOCAL_DAT_URL = "rskLocalDatUrl";

    /**
     * 文件通知接口路径
     */
    String RSK_FILE_NOTICE_URL = "fileNoticeUrl";

    /**
     * 文件通知接口路径
     */
    String RSK_SYSTEM_CODE = "system_code";

    /**
     * 文件通知接口ID
     */
    String RSK_FILE_NOTICE_ID = "fileNoticeInterId";

    /**
     * 文件通知接口超时时间
     */
    String RSK_FILE_NOTICE_TIMEOUT = "fileNoticeTimeOut";

    /**
     * 报文类型
     */
    String STDMSGTYPE = "stdmsgtype";

    /**
     * 多页查询标识
     */
    String STD400PGQF = "std400pgqf";

    /**
     * 业务归属系统代码
     */
    String STD400BSYS = "std400bsys";

    /**
     * 交易码
     */
    String STDPROCODE = "stdprocode";

    /**
     * 发起方系统代码
     */
    String STD400AQID = "std400aqid";

    /**
     * 发起方流水号
     */
    String STDTERMTRC = "stdtermtrc";

    /**
     * 发起方系统日期
     */
    String STD400RBDT = "std400rbdt";

    /**
     * 发起方系统时间戳
     */
    String STD400RSTS = "std400rsts";

    /**
     * 发起方系统时间
     */
    String STD400RTIM = "std400rtim";

    /**
     * 报文长度
     */
    String STD400IMTL = "std400imtl";

    /**
     * 委托交易编号
     */
    String ENTRUSTCODE = "entrustCode";

    /**
     * 审批结果信息
     */
    String APPROVEMSG = "approveMsg";

    /**
     * 流程状态
     */
    String FLOWSTATUS = "flowStatus";

    /**
     * 接口编号
     */
    String INTERFCODE = "interfCode";
    
    /**
     * 交易系统返回报文字段
     */
    String EVENT_ID = "event_id";

    /**
     * 交易系统返回报文字段
     */
    String STATUS = "status";

    /**
     * 返回码成功
     */
    String AAAAAAA = "AAAAAAA";

    /**
     * 返回码
     */
    String STD400MGID = "std400mgid";


    /**
     * 主动提醒文件下载服务器主机
     */
    String REMIND_FTP_HOST = "remindFtpHost";

    /**
     * 主动提醒文件下载服务器端口
     */
    String REMIND_FTP_PORT = "remindFtpPort";

    /**
     * 主动提醒文件编码格式
     */
    String REMIND_FTP_ENCODE = "remindFtpEncode";

    /**
     * 主动提醒文件下载服务器登录用户
     */
    String REMIND_FTP_USER = "remindFtpUser";

    /**
     * 主动提醒文件下载服务器登录密码
     */
    String REMIND_FTP_PASS = "remindFtpPass";

    /**
     * 组合数据权限
     */
    String PTL_AUTHORITY_SWITCH = "ptlAuthoritySwitch";

    /**
     * 刷新批平台日路径
     */
    String REFRESH_BATCH_PLAT_DATE = "refreshBatchPlatDate";

    /**
     * 刷新接口平台日路径
     */
    String REFRESH_INTERF_PLAT_DATE = "refreshInterfPlatDate";

    /**
     * 刷新接口缓存路径
     */
    String REFRESH_INTERF_CACHE = "refreshInterfCache";

    /**
     * 接口加载文件内容路径
     */
    String INTERF_FILE_LOAD = "interfFileLoad";
    /**
     * 返回信息
     */
    public static final String std400msgt="std400msgt";

    /**
     * 返回状态
     */
    public static final String stdrtninfo="stdrtninfo";

    /**
     * 金融产品代码
     */
    public static final String finprodId="finprodId";

    /**
     * 金融产品分类_投管
     */
    public static final String finprodType2="finprodType2";

    /**
     * 交易方向
     */
    public static final String ps="ps";

    /**
     * 币种
     */
    public static final String CCY="ccy";

    /**
     * 异常画面前缀
     */
    public static final String MSG="MSG:";

    /**
     * 异常状态
     */
    public static final String E="E";
    /**
     * 自定义特征值
     */
    public static final String CUSTOM="custom";
    
    
    /**
     * 自定义特征值
     */
    public static final String IS_REPEATED_REIDSBATCH ="is_repeated_reidsbatch";
    
}
