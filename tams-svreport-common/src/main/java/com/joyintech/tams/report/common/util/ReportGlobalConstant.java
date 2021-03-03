package com.joyintech.tams.report.common.util;

/**
 * 中债子系统用GlobalConstant
 *
 * @author 刘二通
 * @version 1.0 2018年5月10日
 * @see ReportGlobalConstant
 * @since 1.0
 */
public class ReportGlobalConstant {

//    /**
//     * 共通Property属性--DB类型
//     */
//    public static final String COM_PROP_DATABASE_TYPE = "DATABASE_TYPE";

    /**
     * 中债报送单文件最大数据件数
     */
    public static final String SYS_PARA_SENDCB_MAX_COUNT = "SEND_CB_MAX_DATA_COUNT";

    /**
     * 持有信息每批次处理数据条数
     */
    public static final String SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME = "SEND_CB_DATA_COUNT_PER_TIME";

    /**
     * 报送检查出现警告信息时是否继续报送（01：是，02：否）
     */
    public static final String SYS_PARA_IS_SEND_WHEN_WARN = "IS_SEND_WHEN_WARN";

    /**
     * 中债子系统接口是否导入开关
     */
    public static final String DICT_INTERF_IMPORT_SWITCH = "INTERF_IMPORT_SWITCH";

    /**
     * FTP类型（01：FTP，02：SFTP，03：本地文件）
     */
    public static final String SYS_PARA_FTP_TYPE = "FTP_TYPE";

    /**
     * 销售系统接口FTP地址
     */
    public static final String SYS_PARA_SALES_FTP_IP = "SALES_FTP_IP";

    /**
     * 销售系统接口FTP端口
     */
    public static final String SYS_PARA_SALES_FTP_PROT = "SALES_FTP_PROT";

    /**
     * 销售系统接口FTP编码
     */
    public static final String SYS_PARA_SALES_FTP_ENCODE = "SALES_FTP_ENCODE";

    /**
     * 销售系统接口FTP用户名
     */
    public static final String SYS_PARA_SALES_FTP_USER = "SALES_FTP_USER";

    /**
     * 销售系统接口FTP密码
     */
    public static final String SYS_PARA_SALES_FTP_PASSWORD = "SALES_FTP_PASSWORD";

    /**
     * 销售系统接口文件编码
     */
    public static final String SYS_PARA_SALES_FILE_ENCODE = "SALES_FILE_ENCODE";

    /**
     * 持有信息数据来源：01：业务明细、02：持有信息
     */
    public static final String SYS_PARA_HOLD_INFO_SOURCE = "HOLD_INFO_SOURCE";

    /**
     * 是否过滤部门权限  01 过滤   02 不过滤
     */
    public static final String SYS_PARA_IS_DEPTCODE_CONTROL = "IS_DEPTCODE_CONTROL";
    /**
     * 颜色mid_list
     */
    public static final String SYS_PARA_COLOR_CONFIG_MID_LIST = "COLOR_CONFIG_mid_list";

    /**
     * 颜色top_list
     */
    public static final String SYS_PARA_COLOR_CONFIG_TOP_LIST = "COLOR_CONFIG_top_list";
    
    /**
     * 人行颜色mid_list
     */
    public static final String SYS_PARA_COLOR_CONFIG_MID_LIST_PBC = "COLOR_CONFIG_MID_LIST_PBC";

    /**
     * 人行颜色top_list
     */
    public static final String SYS_PARA_COLOR_CONFIG_TOP_LIST_PBC = "COLOR_CONFIG_TOP_LIST_PBC";

    /**
     * db2数据库服务器FTP地址
     */
    public static final String SYS_PARA_REP_DB2_SERVER_FTP_IP = "REP_DB2_SERVER_FTP_IP";

    /**
     * db2数据库服务器FTP用户名
     */
    public static final String SYS_PARA_REP_DB2_SERVER_FTP_USER = "REP_DB2_SERVER_FTP_USER";

    /**
     * db2数据库服务器FTP密码
     */
    public static final String SYS_PARA_REP_DB2_SERVER_FTP_PASSWORD = "REP_DB2_SERVER_FTP_PASSWORD";

    /**
     * db2数据库服务器FTP密码
     */
    public static final String SYS_PARA_REP_DB2_SERVER_FTP_PROT = "REP_DB2_SERVER_FTP_PROT";

    /**
     * db2数据库服务器FTP文件路径
     */
    public static final String SYS_PARA_REP_DB2_SERVER_FTP_FILEPATH = "REP_DB2_SERVER_FTP_FILEPATH";

    /**
     * 报送中债出现超时时是否抛异常回滚（01:是,02:否）
     */
    public static final String SYS_PARA_REP_IS_EXCEPTION_WHEN_SENDCB_TIMEOUT = "REP_IS_EXCEPTION_WHEN_SENDCB_TIMEOUT";

    /**
     * 调用中债API获取反馈消息开始时间
     */
    public static final String SYS_PARA_REP_CB_GETMESSAGE_STARTTIME = "REP_CB_GETMESSAGE_STARTTIME";

    /**
     * 调用中债API获取反馈消息结束时间
     */
    public static final String SYS_PARA_REP_CB_GETMESSAGE_ENDTIME = "REP_CB_GETMESSAGE_ENDTIME";

    /**
     * 中债报送自检规则数据缓存key
     */
    public static final String SUBCHECK_BASICRULE_CACHE_KEY = "subCheckBasicRuleData";

    /**
     * 中债报送项目自检规则明细数据缓存key
     */
    public static final String SUBCHECK_ITEMRULE_CACHE_KEY = "subCheckItemRuleData";

    /**
     * 中债报送自检项目编号转换数据缓存key
     */
    public static final String SUBCHECK_ITEMCONV_CACHE_KEY = "subCheckItemConvertData";

    /**
     * 是否当日报送延迟接入数据
     */
    public static final String IS_JOIN_DELAYDATA_TODAY = "IS_JOIN_DELAYDATA_TODAY";

    /**
     * 投资者持有信息统计截止时点
     */
    public static final String INVESTORS_COUNT_END_TIME_POINT = "INVESTORS_COUNT_END_TIME_POINT";

    /**
     * &
     */
    public static final String AMPERS_AND = "&";

    /**
     * !
     */
    public static final String AMPERS_NOT = "!";

    /**
     * 等号
     */
    public static final String EQUALITY_SIGN = "=";

    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    public static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    /**
     * 更新
     */
    public static final String PAGE_UPDATE = "/update";

    public static final String SEARCH_PAGE = "searchPage";

    /**
     * 报送时刻配置
     */
    public static final String SUBMIT_TIME = "SUBMIT_TIME";

    /**
     * 默认电话
     */
    public static final String DEFAULTPHONE = "11111111111";
    /**
     * 无
     */
    public static final String NAUGHT="无";
    /**
     * 默认电话
     */
    public static final String DEFAULTTEL="11111111";
    /**
     * 默认邮箱
     */
    public static final String DEFAULTEMAIL="11@11.com";

    /**
     * 上游接口解析读取文件处理中，是否需要.ok传输完成标志文件 1：需要
     */
    public static String reportTinterfIsneedokfileYes = "01";

    /**
     * 修改产品登记编码是否覆盖
     */
    public static final String SYS_PARA_REP_IS_COVER = "REP_IS_COVER";
    /**
     * 未进行校验标志
     */
    public static final String IS_UN_SUBMIT_CHECK = "0";
    /**
     * 数据检查标志位
     */
    public static final String IS_NUMBER_FLAG = "1";
    /**
     * 竖线
     */
    public static final String VERTICAL_LINE = "|";
    /**
     *
     */
    public static final String MANAY_SPACE="    ";
    /**
     * 电话号码是否给予默认值
     */
    public static final String IS_DEFAUL_TELPHONE = "IS_DEFAUL_TELPHONE";
    /**
     * 是否当日报送延迟接入数据
     */
    public static final String IS_GENERATE_INVESTOR = "IS_GENERATE_INVESTOR";
    /**
     * 数据归档中数据保留天数
     */
    public static final String DATA_BACKUP_DAYS = "DATA_BACKUP_DAYS";
    /**
     * 数据归档中数据保留月份数
     */
    public static final String DATA_BACKUP_MONTHS = "DATA_BACKUP_MONTHS";
    /**
     * 投资者身份信息三要素合并方案 
     *  0 仅校验提醒
	 *  1  校验并保留最小的识别标识
	 *  2  校验并保留最大的识别标识
     */
    public static final String SVR_EQU_HOLD_MERGER = "SVR_EQU_HOLD_MERGER";

	 /** result*/
    public static final String RESULT = "RESULT";
    
    /** result_code */
    public static final String RESULT_CODE = "RESULT_CODE";
    
    /** 销售正确返回值 */
    public static final String CORRECT_CODE = "000000";
    
    /** 销售错误返回值 */
    public static final String ERROR_CODE = "111111";
    
    /** 销售返错误回值描述 */
    public static final String ERROR_INFO = "失败";
    
    /** 销售成功返回值描述  */
    public static final String CORRECT_INFO = "成功";
    
    public static final String XML_LIST = "List";
    public static final String XML_ROW = "Row";

	public static final String FILE_SUFFIX_RES = ".res";

	public static final String INTERF_SALE_SEND_PATH = "INTERF_SALE_SEND_PATH";

	public static final CharSequence SUCCESS_FILE_SUFFIX = ".DONE";

	public static final String ENCODE = "UTF-8";
	/**
     * 人行dat生成文件编码
     */
    public static final String SVR_DAT_FILE_ENCODE = "SVR_DAT_FILE_ENCODE";

    /**
     * 客户名称等汉字项目，做有限暴露方式脱敏处理，中债要求的长度标准
     *（发文称三个汉字以内要处理，跟中债确认的是四个汉字也要处理）
     */
    public static final String NAME_ITEM_CHINESE_TRAN_LENGTH = "NAME_ITEM_CHINESE_TRAN_LENGTH";
    /**
     * 共通数据检查：检查的对象类型（投资者明细信息）
     */
    public static final String COMMON_CHECK_DATA_TYPE_EQUITY_DETAILS = "CHB_EQUDET";
    /**
     * 颜色mid
     */
    public static final String SYS_PARA_COLOR_CONFIG_MID = "COLOR_CONFIG_mid";

    /**
     * 颜色top
     */
    public static final String SYS_PARA_COLOR_CONFIG_TOP = "COLOR_CONFIG_top";
    /**
     * 投资者份额检查不通过是否要报送增加系统参数
     */
    public static final String SYS_PARA_IS_SEND_EQUDET_WHEN_DEVIATION="IS_SEND_EQUDET_WHEN_DEVIATION";

    /**
     * 中债一二期直连报送每批次处理数据条数
     */
    public static final String SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST = "SEND_CB_DATA_COUNT_PER_TIME_FIRST";
}
