package com.joyintech.tams.disclosure.common.common.util;

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
     * 颜色mid
     */
    public static final String SYS_PARA_COLOR_CONFIG_MID = "COLOR_CONFIG_mid";

    /**
     * 颜色top
     */
    public static final String SYS_PARA_COLOR_CONFIG_TOP = "COLOR_CONFIG_top";

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
    public static final String defaultPhone = "11111111111";
    /**
     * 无
     */
    public static final String 无="无";
    /**
     * 默认电话
     */
    public static final String defaultTel="11111111";
    /**
     * 默认邮箱
     */
    public static final String defaultEmail="11@11.com";

    /**
     * 上游接口解析读取文件处理中，是否需要.ok传输完成标志文件 1：需要
     */
    public static String REPORT_TINTERF_ISNEEDOKFILE_YES = "1";

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
     * 报表服务器地址
     */
    public static final String REPORT_SERVER_URL = "REPORT_SERVER_URL";
    
    /**
     * 报表服务器地址
     */
    public static final String REPORT_BATCH_SERVER_URL = "REPORT_BATCH_SERVER_URL";
    /**
     * 报表服务器地址
     */
    public static final String REPORT_SERVER_PATH = "REPORT_SERVER_PATH";
    /**
     * 报表服务器用户名
     */
    public static final String REPORT_USERNAME = "REPORT_USERNAME";
    /**
     * 报表服务器密码
     */
    public static final String REPORT_PASSWORD = "REPORT_PASSWORD";
    /**
     * 数据适配器服务器地址
     */
    public static final String DATAADAPTER_SERVER_URL = "DATAADAPTER_SERVER_URL";
    /**
     * 数据适配器服务器地址
     */
    public static final String DATAADAPTER_SERVER_PATH = "DATAADAPTER_SERVER_PATH";
    /**
     * 数据适配器服务器用户名
     */
    public static final String DATAADAPTER_USERNAME = "DATAADAPTER_USERNAME";
    /**
     * 数据适配器服务器密码
     */
    public static final String DATAADAPTER_PASSWORD = "DATAADAPTER_PASSWORD";
    /**
     * 批量导出文件层级
     */
    public static final String REPORT_BATCH_EXPORT_PATH = "REPORT_BATCH_EXPORT_PATH";
    /**
     * 斜线 
     */
    public static final String OBLIQUE_LINE = "/";
    /**
     * 问号
     */
    public static final String QUESTION_MARK = "?";
    /**
     * 报表服务器用户名参数
     */
    public static final String J_USERNAME = "j_username=";
    /**
     * 报表服务器密码参数
     */
    public static final String J_PASSWORD = "j_password=";
    /**
     * 希望下载名称
     */
    public static final String HOPE_DOWNLOAD_NAME = "report";
    /**
     * 百分号
     */
    public static final String PERCENT_SIGN = "%";
    /**
     * 单引号
     */
    public static final String SIGN_MARK = "'";

    /**
     * 报表默认文件名
     */
    public static final String REPORT_DEFAULT_FILENAME="REPORT_DEFAULT_FILENAME";
    /**
     * 报表默认输出路径
     */
    public static final String REPORT_DOWNLOAD_PATH="REPORT_DOWNLOAD_PATH";
    
    /**
     * 预计生成天数
     */
    public static final String PRE_GEN_DATE = "PRE_GEN_DATE";
    /**
     * 
     */
    public static final String REP_READER_RESULT = "READER_RESULT";
    /**
     * 作业刷新执行时间
     */
    public static final String REP_REFRESH_JOBRULE = "REP_REFRESH_JOBRULE";
    /**
     * 作业刷新执行时间
     */
    public static final String REP_DATA_SOURCE_ID = "REP_DATA_SOURCE_ID";
    /**
     * 拼接url中固定部分
     */
    public static final String MID_URL = "/rest_v2/reports";
    /**
     * 节假日是否披露
     */
    public static final String HOLIDAY_IS_WORK = "HOLIDAY_IS_WORK";
    
    
    /**
     * 跑批计算日期.默认为-1
     */
    public static final String REP_BATCH_DAY = "REP_BATCH_DAY";
    /**
     * 适配器获取数据方式
     */
    public static final String ADAPTER_SOURCE_TYPE = "ADAPTER_SOURCE_TYPE";
    /**
     * 报表模板上传路径
     */
	public static final String TEMPLATE_FILE_PATH = "TEMPLATE_FILE_PATH";
    /**
     * 报表模板临时路径
     */
	public static final String TEMPLATE_TMEP_PATH = "TEMPLATE_TMEP_PATH";	
	/**
	 /**
    * 鎶ヨ〃妯℃澘涓婁紶璺緞
    */
	public static final String TEMPLATE_DELETE_TYPE = "TEMPLATE_DELETE_TYPE";
	/**
	 * 报表下载压缩包是否需要多级目录
	 */
	public static final String IS_NEED_SECOND = "is_need_second";   
	/**
	 * 导出压缩包是否需要系统文件
	 */
	public static final String IS_NEED_SYSFILE = "is_need_sysfile";	
	/**
	* 净值型产品表二
     */
    public static final String pAjZ_TWO = "pAjZ_TWO";
    /**
     * 净值型产品表三
     */
    public static final String pAjZ_THREE = "pAjZ_THREE";
    /**
     * 货币型产品表二
     */
    public static final String pAzb_TWO = "pAzb_TWO";
    /**
     * 货币型产品表三
     */
    public static final String pAzb_THREE = "pAzb_THREE";
   
    /**
     * BOOKSETS
     */
    public static final String BOOKSETS = "BOOKSETS";
    
    /**
     * 批量上传文档解压在服务器上的报表路径
     */
    public static final String DECOMPRESS_FILEPATH = "DECOMPRESS_FILEPATH";
    
    /**
     * 定义reader返回值key
     */
    public static final String READER_RESULT = "READER_RESULT";
    /**
     * 批处理数据来源
     */
    public static final String DIS_DATA_SOURCE = "DIS_DATA_SOURCE";
}