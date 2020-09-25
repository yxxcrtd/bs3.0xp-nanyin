/*
 * 文件名：InterfConstant.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.constant;

/**
 * 接口用常量
 *
 * @author lihuadong
 * @version 1.0 2017年2月3日
 * @see InterfConstant
 * @since 1.0
 */
public class InterfConstant {

    /**
     * 常数 0
     */
    public static final int ZERO = 0;

    /**
     * 字符串 0
     */
    public static final String STRING_ZERO = "0";

    /**
     * .dat 常量
     */
    public static final String FILE_DAT = ".dat";

    /**
     * file
     */
    public static final String FILE = "文件";

    /**
     * _文件名分割
     */
    public static final String FILE_UNDERLINE = "_";

    /**
     * -
     */
    public static final String FILE_MIDLINE = "-";

    /**
     * .ok 常量
     */
    public static final String FILE_OK = ".ok";

    /**
     * 托管文件初始生成文件束
     */
    public static final String FILE_001 = "001";

    /**
     * 文件登录DB条数
     */
    public static final int DB_AMOUNT = 100;

    /**
     * 文件开头start
     */
    public static final String FILE_START = "START";

    /**
     * 文件结尾END
     */
    public static final String FILE_END = "END";

    /**
     * 接口更新业务表更新用户
     */
    public static final String UPDATE_USER = "interf";

    /**
     * socket 通信异常 返回值 PARAMETER_ERROR
     */
    public static final String SOCKET_PARAMETER_ERROR = "PARAMETER_ERROR";

    /**
     * socket 通信异常 返回值 CONNECTION_ERROR
     */
    public static final String SOCKET_CONNECTION_ERROR = "CONNECTION_ERROR";

    /**
     * socket 通信异常 返回值 TIMEOUT_ERROR
     */
    public static final String SOCKET_TIMEOUT_ERROR = "TIMEOUT_ERROR";

    /**
     * socket 通信异常 返回值 COMMUNICATION_ERROR
     */
    public static final String SOCKET_COMMUNICATION_ERROR = "COMMUNICATION_ERROR";

    /**
     * socket 连接次数
     */
    public static final Integer SOCKET_CONNECT_CNT = 3;

    /**
     * socket 连接次数
     */
    public static final Integer FTP_CONNECT_CNT = 3;

    /**
     * socket 连接次数
     */
    public static final String FTP_DEFAULT_ENCODE = "iso-8859-1";

    /**
     * [X03]分隔符
     */
    public static final String SPLIT_SIGN_X03 = new String(new byte[] {3});

    /**
     * 左尖括号
     */
    public static final String LEFT_BRACKET = "<";

    /**
     * 右尖括号
     */
    public static final String RIGHT_BRACKET = ">";

    /**
     * 斜线
     */
    public static final String VIRGULE = "/";

    /**
     * 单引号
     */
    public static final String SINGLE_QUOTES = "'";

    /**
     * INSERT_INTO
     */
    public static final String INSERT_INTO = "INSERT INTO ";

    /**
     * CREATEDATE
     */
    public static final String CREATEDATE = "CREATEDATE";

    /**
     * LSTMNTDATE
     */
    public static final String LSTMNTDATE = "lasttime";

    /**
     * CURRENT_DATE
     */
    public static final String CURRENT_DATE = "CURRENT TIMESTAMP";

    /**
     * VALUES
     */
    public static final String VALUES = "VALUES";

    /**
     * sql 长度上限
     */
    public static final Integer SQL_MAX_LENGTH = 1500;

    /**
     * 一次事务提交sql数
     */
    public static final Integer SQL_COMMIT_CNT = 1000;

    /**
     * GET
     */
    public static final String GET = "get";

    /**
     * SET
     */
    public static final String SET = "set";

    /**
     * 文件夹路径分隔符
     */
    public static final String FILE_SEPARATOR = "file.separator";

    /**
     * 文件操作 返回值 FOLDER_NOT_FOLDER_MSG
     */
    public static final String FOLDER_NOT_FOLDER_MSG = "指定的文件夹类型不是文件夹";

    /**
     * 文件操作 返回值 FILE_NOT_FILE_MSG
     */
    public static final String FILE_NOT_FILE_MSG = "指定的文件类型不是文件";

    /**
     * 文件操作 返回值 FILE_NOT_EXISTED_MSG
     */
    public static final String FILE_NOT_EXISTED_MSG = "指定的文件不存在";

    /**
     * 文件操作 返回值 FOLDER_CREATE_FAILURE_MSG
     */
    public static final String FOLDER_CREATE_FAILURE_MSG = "创建文件夹失败";

    /**
     * 文件操作 返回值 UNSUPPORTEDENCODINGEXCEPTION_MSG
     */
    public static final String UNSUPPORTEDENCODINGE_MSG = "UnsupportedEncodingException happened";

    /**
     * 文件操作 返回值 FILENOTFOUNDEXCEPTION_MSG
     */
    public static final String FILENOTFOUND_MSG = "FileNotFoundException happened";

    /**
     * 文件操作 返回值 IOEXCEPTION_MSG
     */
    public static final String IOEXCEPTION_MSG = "IOException happened";

    /**
     * 文件操作 返回值 DELETE_FILE_FAILURE_MSG
     */
    public static final String DELETE_FILE_FAILURE_MSG = "DELETE FILE FAILURE";

    /**
     * 系统参数键值_核算机构号
     */
    public static final String KEY_HS_FORGNO = "HS_FORGNO";

    /**
     * 系统参数键值_对账结果文件目录
     */
    public static final String KEY_EC_RESULT_FILEPATH = "EC_RESULT_FILEPATH";

    /**
     * 系统参数键值_对账结果文件备份目录
     */
    public static final String KEY_EC_RESULT_BACK_PATH = "EC_RESULT_BACK_PATH";

    /**
     * 系统参数键值_电子对账FTP_IP
     */
    public static final String KEY_EC_FTP_IP = "EC_FTP_IP";

    /**
     * 系统参数键值_电子对账FTP_PORT
     */
    public static final String KEY_EC_FTP_PROT = "EC_FTP_PROT";

    /**
     * 系统参数键值_电子对账FTP_ENCODE
     */
    public static final String KEY_EC_FTP_ENCODE = "EC_FTP_ENCODE";

    /**
     * 系统参数键值_电子对账FTP_USER
     */
    public static final String KEY_EC_FTP_USER = "EC_FTP_USER";

    /**
     * 系统参数键值_电子对账FTP_PASSWORD
     */
    public static final String KEY_EC_FTP_PASSWORD = "EC_FTP_PASSWORD";

    /**
     * 系统参数键值_电子对账FTP_FILEPATH
     */
    public static final String KEY_EC_FTP_FILEPATH = "EC_FTP_FILEPATH";

    /**
     * 系统参数键值_电子对账本地文件路径
     */
    public static final String KEY_EC_LOCAL_FILEPATH = "EC_LOCAL_FILEPATH";

    /**
     * 系统参数键值_电子对账文件编码
     */
    public static final String KEY_EC_FILE_ENCODE = "EC_FILE_ENCODE";

    /**
     * 系统参数键值_托管核算FTP_IP
     */
    public static final String KEY_TRUST_FTP_IP = "TRUST_FTP_IP";

    /**
     * 系统参数键值_托管核算FTP_PORT
     */
    public static final String KEY_TRUST_FTP_PROT = "TRUST_FTP_PROT";

    /**
     * 系统参数键值_托管核算FTP_ENCODE
     */
    public static final String KEY_TRUST_FTP_ENCODE = "TRUST_FTP_ENCODE";

    /**
     * 系统参数键值_托管核算FTP_USER
     */
    public static final String KEY_TRUST_FTP_USER = "TRUST_FTP_USER";

    /**
     * 系统参数键值_托管核算FTP_PASSWORD
     */
    public static final String KEY_TRUST_FTP_PASSWORD = "TRUST_FTP_PASSWORD";

    /**
     * 系统参数键值_托管核算FTP_FILEPATH
     */
    public static final String KEY_TRUST_FTP_FILEPATH = "TRUST_FTP_FILEPATH";

    /**
     * 系统参数键值_托管核算本地文件路径
     */
    public static final String KEY_TRUST_LOCAL_FILEPATH = "TRUST_LOCAL_FILEPATH";

    /**
     * 系统参数键值_托管核算文件编码
     */
    public static final String KEY_TRUST_FILE_ENCODE = "TRUST_FILE_ENCODE";

    /**
     * 系统参数键值_托管管信FTP_IP
     */
    public static final String KEY_GX_FTP_IP = "GX_FTP_IP";

    /**
     * 系统参数键值_托管管信FTP_PORT
     */
    public static final String KEY_GX_FTP_PROT = "GX_FTP_PROT";

    /**
     * 系统参数键值_托管管信FTP_ENCODE
     */
    public static final String KEY_GX_FTP_ENCODE = "GX_FTP_ENCODE";

    /**
     * 系统参数键值_托管管信FTP_USER
     */
    public static final String KEY_GX_FTP_USER = "GX_FTP_USER";

    /**
     * 系统参数键值_托管管信FTP_PASSWORD
     */
    public static final String KEY_GX_FTP_PASSWORD = "GX_FTP_PASSWORD";

    /**
     * 系统参数键值_托管管信FTP_FILEPATH
     */
    public static final String KEY_GX_FTP_FILEPATH = "GX_FTP_FILEPATH";

    /**
     * 系统参数键值_托管管信本地文件路径
     */
    public static final String KEY_GX_LOCAL_FILEPATH = "GX_LOCAL_FILEPATH";

    /**
     * 系统参数键值_托管管信文件编码
     */
    public static final String KEY_GX_FILE_ENCODE = "GX_FILE_ENCODE";

    /**
     * 系统参数键值_XFUND拆借FTP_IP
     */
    public static final String KEY_XF_LEND_FTP_IP = "XF_LEND_FTP_IP";

    /**
     * 系统参数键值_XFUND拆借FTP_PORT
     */
    public static final String KEY_XF_LEND_FTP_PROT = "XF_LEND_FTP_PROT";

    /**
     * 系统参数键值_XFUND拆借FTP_ENCODE
     */
    public static final String KEY_XF_LEND_FTP_ENCODE = "XF_LEND_FTP_ENCODE";

    /**
     * 系统参数键值_XFUND拆借FTP_USER
     */
    public static final String KEY_XF_LEND_FTP_USER = "XF_LEND_FTP_USER";

    /**
     * 系统参数键值_XFUND拆借FTP_PASSWORD
     */
    public static final String KEY_XF_LEND_FTP_PASSWORD = "XF_LEND_FTP_PASSWORD";

    /**
     * 系统参数键值_XFUND拆借FTP_FILEPATH
     */
    public static final String KEY_XF_LEND_FTP_FILEPATH = "XF_LEND_FTP_FILEPATH";

    /**
     * 系统参数键值_XFUND拆借本地文件路径
     */
    public static final String KEY_XF_LEND_LOCAL_FILEPATH = "XF_LEND_LOCAL_FILEPATH";

    /**
     * 系统参数键值_XFUND拆借文件编码
     */
    public static final String KEY_XF_LEND_FILE_ENCODE = "XF_LEND_FILE_ENCODE";

    /**
     * 系统参数键值_理财 实时文件路径
     */
    public static final String KEY_SALE_REALFILEPATH = "sale_realfilePath";

    /**
     * 系统参数键值_理财 非实时文件路径
     */
    public static final String KEY_SALE_UNREALFILEPATH = "sale_unrealfilePath";

    /**
     * 系统参数键值_理财encode 编码方式
     */
    public static final String KEY_SALE_ENCODE = "sale_encode";

    /**
     * 系统参数键值_理财directoryPath 移动目标目录
     */
    public static final String KEY_SALE_DIRECTORYPATH = "sale_directoryPath";

    /**
     * 系统参数键值_本币-文件路径
     */
    public static final String KEY_BB_FILEPATH = "BB_FILEPATH";

    /**
     * 系统参数键值_本币-编码方式
     */
    public static final String KEY_BB_FILE_ENCODE = "BB_FILE_ENCODE";

    /**
     * 系统参数键值_本币-移动的目录
     */
    public static final String KEY_BB_FILE_DIRECTORY = "BB_FILE_DIRECTORY";

    /**
     * 系统参数键值_本币-交易日历
     */
    public static final String KEY_BB_DEAL_CAL_ID = "BB_DEAL_CAL_ID";

    /**
     * 系统参数键值_资讯文件路径
     */
    public static final String KEY_INFO_FILEPATH = "info_filePath";

    /**
     * 系统参数键值_资讯文件编码方式
     */
    public static final String KEY_INFO_ENCODE = "info_encode";

    /**
     * 系统参数键值_XFunds拆借交易交易员
     */
    public static final String KEY_XFUNDS_TRADERID = "XFUNDS_TRADERID";

    /**
     * 系统参数键值_XFunds拆借交易交易对手
     */
    public static final String KEY_XFUNDS_COUNTERID = "XFUNDS_COUNTERID";

    /**
     * 系统参数键值_XFUNDS外汇文件路径
     */
    public static final String KEY_XFUNDS_SOPT_FILEPATH = "xfunds_sopt_filepath";

    /**
     * 系统参数键值_XFUNDS外汇文件编码方式
     */
    public static final String KEY_XFUNDS_SOPT_ENCODE = "xfunds_sopt_encode";

    /**
     * 系统参数键值_XFUNDS外汇文件移动目标目录
     */
    public static final String KEY_XFUNDS_SOPT_DIRECTORYPATH = "xfunds_sopt_directorypath";

    /**
     * 系统参数键值_ESB URL
     */
    public static final String KEY_ESB_URL = "ESB_URL";

    /**
     * 系统参数键值_ESB ENCODE
     */
    public static final String KEY_ESB_ENCODE = "ESB_ENCODE";

    /**
     * 系统参数键值_核心记账文件路径
     */
    public static final String KEY_CORE_KEEP_PATH = "CORE_KEEP_PATH";

    /**
     * 系统参数键值_核心记账FTP文件路径
     */
    public static final String KEY_CORE_KEEP_FTP_PATH = "CORE_KEEP_FTP_PATH";

    /**
     * 系统参数键值_核心记账返回文件ftp路径
     */
    public static final String KEY_CORE_KEEP_FTP_RESULT_PATH = "CORE_KEEP_FTP_RESULT_PATH";

    /**
     * 系统参数键值_核心记账文件名
     */
    public static final String KEY_CORE_KEEP_FILE = "CORE_KEEP_FILE_NAME";

    /**
     * 系统参数键值_核心记账出返回文件名
     */
    public static final String KEY_CORE_KEEP_RESULT_FILE = "CORE_KEEP_RESULT_FILE_NAME";

    /**
     * 系统参数键值_核心框息文件名
     */
    public static final String KEY_CORE_KX_FILE = "CORE_KX_FILE_NAME";

    /**
     * 系统参数键值_核心记账出文件编码格式
     */
    public static final String KEY_CORE_KEEP_ENCODE = "CORE_KEEP_FILE_ENCODE";

    /**
     * 系统参数键值_核心记账FTP编码格式
     */
    public static final String KEY_CORE_KEEP_FTP_ENCODE = "CORE_KEEP_FTP_ENCODE";

    /**
     * 系统参数键值_核心记账FTP用户名
     */
    public static final String KEY_CORE_KEEP_FTP_USER = "CORE_KEEP_FTP_USER";

    /**
     * 系统参数键值_核心记账FTP密码
     */
    public static final String KEY_CORE_KEEP_FTP_PASSWORD = "CORE_KEEP_FTP_PASSWORD";

    /**
     * 系统参数键值_核心记账FTP地址
     */
    public static final String KEY_CORE_KEEP_FTP_IP = "CORE_KEEP_FTP_IP";

    /**
     * 系统参数键值_核心记账FTP端口
     */
    public static final String KEY_CORE_KEEP_FTP_PORT = "CORE_KEEP_FTP_PORT";

    /**
     * 系统参数键值_资管系统标识(托管清算发起方ID)
     */
    public static final String KEY_TAMS = "TAMS";

    /**
     * 系统参数键值_银行号(ESB接口用)
     */
    public static final String KEY_ESB_STDBANKNO = "stdbankno";

    /**
     * 系统参数键值_资管机构号(ESB接口用)
     */
    public static final String KEY_ESB_STDTRANINS = "stdtranins";

    /**
     * 系统参数键值_资管客户号
     */
    public static final String KEY_HX_CUNO = "hx_cuno";

    /**
     * 系统参数键值_金融市场部机构号(拆借交易接口用)
     */
    public static final String KEY_LEND_JRSC_ORG_NO = "jrscOrgNo";

    /**
     * 系统参数键值_用户名(ESB接口用)
     */
    public static final String KEY_ESB_STD400USNO = "std400usno";

    /**
     * 系统参数键值_TIMS交易员
     */
    public static final String KEY_TIMS_TRADER = "tims_trader";

    /**
     * 系统参数键值_TIMS投资组合
     */
    public static final String KEY_TIMS_GROUPID = "tims_groupid";

    /**
     * 系统参数键值_TIMS客户号
     */
    public static final String KEY_TIMS_CUSTOMER = "tims_customer";

    /**
     * 系统参数键值_TIMS交易对手
     */
    public static final String KEY_TIMS_COUNTER_ID = "tims_counter_id";

    /**
     * 系统参数键值_核心拆入交易 产品编码
     */
    public static final String KEY_LEND_IN_PRDID = "lendInPrdid";

    /**
     * 系统参数键值_核心拆出交易 产品编码
     */
    public static final String KEY_LEND_OUT_PRDID = "lendOutPrdid";

    /**
     * 系统参数键值_核心拆借 担险交易 产品编码
     */
    public static final String KEY_LEND_DANXAIN_PRDID = "lendDanXianPrdid";

    /**
     * 系统参数键值_接口 9992000 交易码
     */
    public static final String KEY_9992000_TRADEID = "9992000TRADEID";

    /**
     * 系统参数键值_核心记账 联机服务号
     */
    public static final String KEY_CORE_SERVICEID = "CORE_SERVICEID";

    /**
     * 系统参数键值_核心记账 前置机名称
     */
    public static final String KEY_CORE_QIANZHIJI = "CORE_QIANZHIJI";

    /**
     * 系统参数键值_上传前端文件目录
     */
    public static final String KEY_CORE_UPLOAD_PATH = "CORE_UPLOAD";

    /**
     * 系统参数键值_下传前端文件目录
     */
    public static final String KEY_CORE_DOWNLOAD_PATH = "CORE_DOWNLOAD";

    /**
     * 系统参数键值_批量拆借最大异常处理次数
     */
    public static final String KEY_PEND_QUERY_MAX_COUNT = "pendQueryMaxCount";

    /**
     * 系统参数键值_接口 9992005 交易码
     */
    public static final String KEY_9992005_TRADEID = "9992005TRADEID";

    /**
     * 系统参数键值_接口 9992008 交易码
     */
    public static final String KEY_9992008_TRADEID = "9992008TRADEID";

    /**
     * 系统参数键值_接口 2209018 交易码
     */
    public static final String KEY_2209018_TRADEID = "2209018TRADEID";

    /**
     * 系统参数键值_接口 1002055 交易码
     */
    public static final String KEY_1002055_TRADEID = "1002055TRADEID";

    /**
     * 系统参数键值_接口 0200926 交易码
     */
    public static final String KEY_0200926_TRADEID = "0200926TRADEID";

    /**
     * 系统参数键值_接口 3405520 交易码
     */
    public static final String KEY_3405520_TRADEID = "3405520TRADEID";

    /**
     * 系统参数键值_资讯移动目标目录
     */
    public static final String KEY_INFO_DIRECTORYPATH = "indfo_directoryPath";

    /**
     * 系统参数键值_接口任务状态(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_TASK_STATUS = "INTERF_TASK_STATUS";

    /**
     * 系统参数键值_理财(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_LC_TASK_STATUS = "INTERF_LC_TASK_STATUS";

    /**
     * 系统参数键值_资讯(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_ZX_TASK_STATUS = "INTERF_ZX_TASK_STATUS";

    /**
     * 系统参数键值_ODS(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_ODS_TASK_STATUS = "INTERF_ODS_TASK_STATUS";

    /**
     * 系统参数键值_XFUND(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_XF_TASK_STATUS = "INTERF_XF_TASK_STATUS";

    /**
     * 系统参数键值_托管(ON:开启；OFF：关闭)
     */
    public static final String KEY_INTERF_TG_TASK_STATUS = "INTERF_TG_TASK_STATUS";

    /**
     * 缺少参数 页面传入接口参数异常
     */
    public static final String CORE_LACK_PARAMETER = "缺少参数！";

    /**
     * 接口返回参数异常
     */
    public static final String INTERF_RETURN_PARA_ERR = "接口返回参数异常！";

    /**
     * 没有输入拆入到期拆出到期类型！
     */
    public static final String ERRMESSAGE3 = "没有输入拆入到期拆出到期类型！";

    /**
     * ERR_MESSAGE_STATUS_10000 系统参数不合法
     */
    public static final String ERR_MESSAGE_STATUS_10000 = "系统参数不合法！";

    /**
     * ERR_MESSAGE_STATUS_10000 没有网络
     */
    public static final String ERR_MESSAGE_STATUS_10001 = "网络异常！";

    /**
     * ERR_MESSAGE_STATUS_10000 链接超时
     */
    public static final String ERR_MESSAGE_STATUS_10002 = "链接超时！";

    /**
     * ERR_MESSAGE_STATUS_10000 通信异常
     */
    public static final String ERR_MESSAGE_STATUS_10003 = "通信异常！";

    /**
     * ERR_MESSAGE_STATUS_10000 通信异常
     */
    public static final String ERR_MESSAGE_RETURN = "核心接口返回报文转换失败！";

    /**
     * 数据完整性错误
     */
    public static final String MEESSAGE_ERR_DATA = "数据完整性错误！";

    /**
     * 传入值为空
     */
    public static final String MEESSAGE_ERR_EMPTY = "传入值为空";

    /**
     * 数据导出失败
     */
    public static final String MEESSAGE_ERR_EXPORT = "数据导出失败";

    /**
     * 生成文件成功
     */
    public static final String MEESSAGE_ERR_SUCCESS = "生成文件成功";

    /**
     * FTP上传失败
     */
    public static final String MEESSAGE_FTP_FAILED = "FTP上传失败";

    /**
     * FTP上传失败
     */
    public static final String MEESSAGE_FTP_LOGIN_FAILED = "FTP登陆失败";

    /**
     * FTP上传成功
     */
    public static final String MEESSAGE_FTP_SUCCESS = "FTP上传成功";

    /**
     * 文件处理状态 文件处理成功
     */
    public static final String MESSAGE_HINT00 = "文件处理成功";

    /**
     * 文件处理状态 未轮询到文件名
     */
    public static final String MESSAGE_HINT1 = "未轮询到文件名";

    /**
     * 文件处理状态 轮询到的文件名不是以.ok结尾
     */
    public static final String MESSAGE_HINT2 = "轮询到的文件名不是以.ok结尾";

    /**
     * 文件处理状态 轮询到文件的源文件不存在
     */
    public static final String MESSAGE_HINT3 = "轮询到文件对应的源文件是空";

    /**
     * 文件处理状态 文件目录下未找到源文件名
     */
    public static final String MESSAGE_HINT4 = "文件目录下未找到源文件名";

    /**
     * 文件处理状态 文件目录下未找到.ok文件名
     */
    public static final String MESSAGE_HINT5 = "文件目录下的.ok文件已经被删除了";

    /**
     * 文件处理状态 删除.ok文件失败
     */
    public static final String MESSAGE_HINT6 = "删除.ok文件失败";

    /**
     * 文件处理状态 读取源文件为空
     */
    public static final String MESSAGE_HINT7 = "读取的文件文件为空";

    /**
     * 文件处理状态 SQL文生成失败
     */
    public static final String MESSAGE_HINT8 = "SQL文生成失败";

    /**
     * 文件处理状态 登录DB操作失败
     */
    public static final String MESSAGE_HINT9 = "登录DB操作失败";

    /**
     * 文件处理状态 移动文件到指定备份目录错误
     */
    public static final String MESSAGE_HINT10 = "虽然读取成功，但是移动文件到指定备份目录时发生错误";

    /**
     * message11 未找到文件对应的文件类型
     */
    public static final String MESSAGE_HINT11 = "未找到文件对应的文件类型";

    /**
     * 文件处理状态 将[X03]分隔符的字符串转换成Entity失败
     */
    public static final String MESSAGE_HINT12 = "将[X03]分隔符的字符串转换成Entity失败";

    /**
     * 文件处理状态 读取源文件为空
     */
    public static final String MESSAGE_HINT13 = "读取文件失败";

    /**
     * 文件处理状态 将Entity转换成[X03]分隔符的字符串失败
     */
    public static final String MESSAGE_HINT14 = "将Entity转换成[X03]分隔符的字符串失败";

    /**
     * 上传文件重复
     */
    public static final String MESSAGE_HINT15 = "上传文件重复";

    /**
     * error
     */
    public static final String FOLDER_ERROR = "error";

    /**
     * entity转x03分隔符失败
     */
    public static final String MEESSAGE_ERR_X03 = "传入值为空";

    // TODO 给托管发核心汇率的接口暂时保留
    // /**
    // * 发送折算汇率信息至托管 ENCODE(数据字典配置)
    // */
    // public static final String UPCERA_ENCODE = "UPCERA_ENCODE";
    //
    // /**
    // * 发送折算汇率信息至托管 ip(数据字典配置)
    // */
    // public static final String UPCERA_IP = "UPCERA_IP";
    //
    // /**
    // * 发送折算汇率信息至托管 端口号(数据字典配置)
    // */
    // public static final String UPCERA_PORT = "UPCERA_PORT";
    //
    // /**
    // * 发送折算汇率信息至托管 username(数据字典配置)
    // */
    // public static final String UPCERA_USERNAME = "UPCERA_USERNAME";
    //
    // /**
    // * 发送折算汇率信息至托管 password(数据字典配置)
    // */
    // public static final String UPCERA_PASSWORD = "UPCERA_PASSWORD";
    //
    // /**
    // * 发送折算汇率信息至托管 FTP文件路径(数据字典配置)
    // */
    // public static final String UPCERA_FTPPATH = "UPCERA_FTPPATH";

    /**
     * XFUNDS外汇即期交易插入业务表失败！
     */
    public static final String MSG_XF_INSERT_FXSPOT = "外汇即期交易数据插入业务表失败！";

    /**
     * XFUNDS外汇远期交易插入业务表失败！
     */
    public static final String MSG_XF_INSERT_FXFORWARD = "外汇远期交易数据插入业务表失败！";

    /**
     * XFUNDS外汇掉期交易插入业务表失败！
     */
    public static final String MSG_XF_INSERT_FXSWAP = "外汇掉期交易数据插入业务表失败！";

    /**
     * 法定存贷款利率 表名
     */
    public static final String INTERF_INFO_CBONDBENCHMARK = "INTERF_INFO_CBONDBENCHMARK";

    /**
     * 中国Wind债券分类板块 表名
     */
    public static final String INTERF_INFO_BONDCLASSIFY = "INTERF_INFO_BONDCLASSIFY";

    /**
     * 中国债券基本资料 表名
     */
    public static final String INTERF_INFO_BONDINFO = "INTERF_INFO_BONDINFO";

    /**
     * 中国债券主体 表名
     */
    public static final String INTERF_INFO_BONDISSUER = "INTERF_INFO_BONDISSUER";

    /**
     * 中国债券主体信用评级 表名
     */
    public static final String INTERF_INFO_BONDISSUERRATING = "INTERF_INFO_BONDISSUERRATING";

    /**
     * 中国债券信用评级 表名
     */
    public static final String INTERF_INFO_BONDRATING = "INTERF_INFO_BONDRATING";

    /**
     * 中国债券证监会行业分类 表名
     */
    public static final String INTERF_INFO_BONDSECCLASS = "INTERF_INFO_BONDSECINDUSTRIESC";

    /**
     * 中国债券现金流 表名
     */
    public static final String INTERF_INFO_CASHFLOW = "INTERF_INFO_CASHFLOW";

    /**
     * 中债登估值 表名
     */
    public static final String INTERF_INFO_INDEPT = "INTERF_INFO_INDEPT";

    /**
     * Libor行情 表名
     */
    public static final String INTERF_INFO_LIBORPRICES = "INTERF_INFO_LIBORPRICES";

    /**
     * Shibor行情 表名
     */
    public static final String INTERF_INFO_SHIBORPRICES = "INTERF_INFO_SHIBORPRICES";

    /**
     * 中国债券中介机构 表名
     */
    public static final String INTERF_INFO_CBONDAGENCY = "INTERF_INFO_CBONDAGENCY";

    /**
     * 中国债券赎回条款执行说明 表名
     */
    public static final String INTERF_INFO_CBONDCALL = "INTERF_INFO_CBONDCALL";

    /**
     * 中国债券浮息债基础利率 表名
     */
    public static final String INTERF_INFO_CBONDFLOATINGRATE = "INTERF_INFO_CBONDFLOATINGRATE";

    /**
     * 中债登收益率曲线 表名
     */
    public static final String INTERF_INFO_CBONDCURVECNBD = "INTERF_INFO_CBONDCURVECNBD";

    /**
     * 中国债券回售条款执行说明 表名
     */
    public static final String INTERF_INFO_CBONDPUT = "INTERF_INFO_CBONDPUT";

    /**
     * 中债债券分类板块 表名
     */
    public static final String INTERF_INFO_CBONDINDUSTRYCNBD = "INTERF_INFO_CBONDINDUSTRYCNBD";

    /**
     * 基金价格历史 表名
     */
    public static final String INTERF_INFO_DAILYPRICE = "INTERF_INFO_DAILYPRICE";

    /**
     * Wind兼容代码 表名
     */
    public static final String INTERF_INFO_WINDCUSTOMCODE = "INTERF_INFO_WINDCUSTOMCODE";

    /**
     * 基金基本信息（万得） 表名
     */
    public static final String INTERF_INFO_FUNDBASICINFO = "INTERF_INFO_FUNDBASICINFO";

    /**
     * 折算汇率信息 表名
     */
    public static final String INTERF_INFO_UPCERA = "INTERF_INFO_UPCERA";

    /**
     * 中国债券预发行资料 表名
     */
    public static final String INTERF_INFO_CBONDPRERELEASE = "INTERF_INFO_CBONDPRERELEASE";

    /**
     * 中国债券市场交易日 表名
     */
    public static final String INTERF_INFO_CBONDCALENDAR = "INTERF_INFO_CBONDCALENDAR";

    /**
     * 中国债券付息和兑付 表名
     */
    public static final String INTERF_INFO_CBONDPAYMENT = "INTERF_INFO_CBONDPAYMENT";

    /**
     * 中国共同基金基本资料 表名
     */
    public static final String INTERF_INFO_MUTUALFUND = "INTERF_INFO_MUTUALFUND";

    /**
     * 中国封闭式基金日行情 表名
     */
    public static final String INTERF_INFO_CLOSEDFUND = "INTERF_INFO_CLOSEDFUND";

    /**
     * 接口枚举转换 表名
     */
    public static final String INTERF_ENUM_CONVERT = "INTERF_ENUM_CONVERT";

    /**
     * 中国共同基金分红 表名
     */
    public static final String INTERF_INFO_CHINAMFDIVIDEND = "INTERF_INFO_CHINAMFDIVIDEND";

    /**
     * 中国共同基金申购赎回天数 表名
     */
    public static final String INTERF_INFO_CFUNDPCHREDM = "INTERF_INFO_CFUNDPCHREDM";

    /**
     * 中国货币市场基金份额结转方式 表名
     */
    public static final String INTERF_INFO_CMONEYMARKET = "INTERF_INFO_CMONEYMARKET";

    /**
     * 中国债券本金提前偿还明细 表名
     */
    public static final String INTERF_INFO_CBONDEREPAY = "INTERF_INFO_CBONDEREPAY";

    /**
     * 中国银行间债券回购及同业拆借日行情 表名
     */
    public static final String INTERF_INFO_CBONDREPOPRICE = "INTERF_INFO_CBONDREPOPRICE";

    /**
     * 中国央行回购交易 表名
     */
    public static final String INTERF_INFO_CBONDREPO = "INTERF_INFO_CBONDREPO";

    /**
     * 中国债券特殊条款 表名
     */
    public static final String INTERF_INFO_CBONDSC = "INTERF_INFO_CBONDSC";

    /**
     * 行业代码 表名
     */
    public static final String INTERF_INFO_ASHAREINDCODE = "INTERF_INFO_ASHAREINDCODE";

    /**
     * 分销系统SFTP_IP
     */
    public static final String FX_TRUST_SFTP_IP = "FX_SFTP_IP";

    /**
     * 分销系统SFTP_PROT
     */
    public static final String FX_TRUST_SFTP_PROT = "FX_SFTP_PROT";

    /**
     * 分销系统SFTP_ENCODE
     */
    public static final String FX_TRUST_SFTP_ENCODE = "FX_SFTP_ENCODE";

    /**
     * 分销系统SFTP_USER
     */
    public static final String FX_TRUST_SFTP_USER = "FX_SFTP_USER";

    /**
     * 分销系统SFTP_PASSWORD
     */
    public static final String FX_TRUST_SFTP_PASSWORD = "FX_SFTP_PASSWORD";

    /**
     * 分销系统上传SFTP_FILEPATH
     */
    public static final String FX_TRUST_SFTP_FILEPATH = "FX_SFTP_FILEPATH";

    /**
     * 分销系统上传SFTP_FILEPATH
     */
    public static final String FX_TRUST_SFTP_FANGAN = "FX_SFTP_FANGAN";

    /**
     * 分销系统 本地文件路径
     */
    public static final String FX_TRUST_LOCAL_FILEPATH = "FX_LOCAL_FILEPATH";

    /**
     * 分销系统下载SFTP_FILEPATH
     */
    public static final String FX_DLOW_SFTP_FILEPATH = "FX_SFTP_DLOWPATH";

    /**
     * 销售系统SFTP_IP
     */
    public static final String XS_TRUST_SFTP_IP = "XS_SFTP_IP";

    /**
     * 销售系统SFTP_PROT
     */
    public static final String XS_TRUST_SFTP_PROT = "XS_SFTP_PROT";

    /**
     * 销售系统SFTP_ENCODE
     */
    public static final String XS_TRUST_SFTP_ENCODE = "XS_SFTP_ENCODE";

    /**
     * 分销系统SFTP_USER
     */
    public static final String XS_TRUST_SFTP_USER = "XS_SFTP_USER";

    /**
     * 销售系统SFTP_PASSWORD
     */
    public static final String XS_TRUST_SFTP_PASSWORD = "XS_SFTP_PASSWORD";

    /**
     * 销售系统上传SFTP_FILEPATH
     */
    public static final String XS_TRUST_SFTP_FILEPATH = "XS_SFTP_FILEPATH";

    /**
     * 销售系统净值文件上传SFTP_FILEPATH
     */
    public static final String XS_TRUST_SFTP_JINGZHI = "XS_SFTP_FILEPATH_JINGZHI";

    /**
     * 分销系统净值文件上传SFTP_FILEPATH
     */
    public static final String FX_TRUST_SFTP_JINGZHI = "FX_SFTP_FILEPATH_JINGZHI";

    /**
     * 销售系统 本地文件路径
     */
    public static final String XS_TRUST_LOCAL_FILEPATH = "XS_LOCAL_FILEPATH";

    /**
     * 销售系统下载SFTP_FILEPATH
     */
    public static final String XS_DLOW_SFTP_FILEPATH = "XS_SFTP_DLOWPATH";

    /**
     * 系统参数键值_中债报送文件目录
     */
    public static final String KEY_CB_FILEPATH = "CB_FILEPATH";

    /**
     * 系统参数键值_文件锁
     */
    public static final String KEY_LOCK_FILEPATH = "LOCK_FILEPATH";

    /**
     * 系统参数键值_中债报送下载文件目录
     */
    public static final String KEY_CB_DOWNLOAD_FILEPATH = "CB_DOWNLOAD_FILEPATH";

    /**
     * 系统参数键值_中债上游接口文件锁
     */
    public static final String KEY_LOCK_CHB_FILEPATH = "LOCK_CHB_FILEPATH";
}
