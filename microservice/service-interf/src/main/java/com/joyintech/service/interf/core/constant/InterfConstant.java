/*
 * 文件名：InterConstant.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import com.cs.esb.util.ESBDict;

/**
 * 常量类
 * 
 * @author zhangrui1
 * @version 1.0 2018年9月3日
 * @see InterfConstant
 * @since 1.0
 */
public class InterfConstant extends ESBDict {

    /**
     * KEY:返回状态-成功
     */
    public static final String ESB_RET_STATUS_S = "S";

    /**
     * KEY:返回状态-成功
     */
    public static final String ESB_RET_STATUS_F = "F";

    /**
     * KEY:返回值代码-000000，成功
     */
    public static final String ESB_RET_CODE_000000 = "000000";

    /**
     * KEY:返回值代码-111111，失败
     */
    public static final String ESB_RET_CODE_111111 = "111111";

    /**
     * 错误码
     */
    public static final String ESB_RET_CODE_0099000 = "0099000";

    /**
     * KEY:上行接口标志。
     */
    public static final String FLG_SEND = "send";

    /**
     * KEY:下行接口标志
     */
    public static final String FLG_RECEIVE = "receive";

    /**
     * KEY:撤销标识-已撤销
     */
    public static final String REVFLAG_Y = "Y";

    /**
     * KEY:撤销标识-未撤销
     */
    public static final String REVFLAG_N = "N";

    /**
     * KEY:发送状态-未发送
     */
    public static final String SENDFLAG_U = "U";

    /**
     * KEY:发送状态-发送成功
     */
    public static final String SENDFLAG_S = "S";

    /**
     * KEY:发送状态-发送失败
     */
    public static final String SENDFLAG_F = "F";

    /**
     * KEY:发送状态-待发送
     */
    public static final String SENDFLAG_I = "I";

    /**
     * KEY:LOG序列号
     */
    public static final String INTERF_LOG_SEQNO = "INTERF_LOG_SEQNO";

    /**
     * KEY:交易序列号
     */
    public static final String INTERF_BIZNO = "INTERF_BIZNO";

    /**
     * KEY:邮件序列号
     */
    public static final String INTERF_EMAIL_SEQNO = "INTERF_EMAIL_SEQNO";

    /**
     * KEY:发送机构ID
     */
    public static final String INF_BRANCH_ID = "INF_BRANCH_ID";

    /**
     * KEY:系统编号
     */
    public static final String INF_CONSUMER_ID = "INF_CONSUMER_ID";

    /**
     * KEY:消费者ID
     */
    public static final String INF_USER_ID = "INF_USER_ID";

    /**
     * KEY:时间格式 hhmmssms
     */
    public static final String FORMAT_HHMMSSMS = "hhmmssms";

    /**
     * KEY:线程池大小
     */
    public static final String INF_CORE_POOL_SIZE = "INF_CORE_POOL_SIZE";

    /**
     * KEY:最大线程数
     */
    public static final String INF_MAXIMUM_POOL_SIZE = "INF_MAXIMUM_POOL_SIZE";

    /**
     * KEY:线程执行时间
     */
    public static final String INF_KEEP_ALIVE_TIME = "INF_KEEP_ALIVE_TIME";

    /**
     * KEY:时间单位
     */
    public static final String INF_UNIT = "INF_UNIT";

    /**
     * BFTS系统标志
     */
    public static final String INF_BFTS = "INF_BFTS";

    /**
     * 产品说明书上传
     */
    public static final String INF_BFTS_PRODINS = "INF_BFTS_PRODINS";

    /**
     * ODS系统标志
     */
    public static final String INF_ODS = "INF_ODS";

    /**
     * KEY:系统IP
     */
    public static final String KEY_SERVER = "SERVER";

    /**
     * KEY:系统端口
     */
    public static final String KEY_PORT = "PORT";

    /**
     * KEY:系统访问用户名
     */
    public static final String KEY_USERNAME = "USERNAME";

    /**
     * KEY:系统访问密码
     */
    public static final String KEY_PASS = "PASSWORD";

    /**
     * KEY:系统下载的本地地址
     */
    public static final String KEY_LOCALPATH = "LOCALPATH";

    /**
     * KEY:系统上传的地址
     */
    public static final String KEY_PATH = "PATH";

    /**
     * 文件轮询默认间隔时间：5min
     */
    public static final long DEFAULT_INTERVAL = 5 * 60 * 1000L;

    /**
     * KEY:金融产品代码KEY
     */
    public static final String PROD_INFO = "PROD_INFO";

    /**
     * KEY:处理识别号KEY
     */
    public static final String UUID = "UUID";

    /**
     * KEY:平台日期KEY
     */
    public static final String SYS_DATE = "SYS_DATE";

    /**
     * 下划线
     */
    public static final String UNDERLINE = "_";

    /**
     * Key:调用模块
     */
    public static final String CALL_MODEL = "caller";

    /**
     * KEY:esb请求数据
     */
    public static final String ESB_DATA = "esbData";

    /**
     * KEY:画面ID
     */
    public static final String PAGE_ID = "pageId";

    /**
     * KEY:产品列表
     */
    public static final String DATA_LIST = "dataList";

    /**
     * 现金流一级类型
     */
    public static final String CASHTYPE1 = "cashType1";

    /**
     * 现金流二级类型
     */
    public static final String CASHTYPE2 = "cashType2";

    /**
     * KEY:平台时间
     */
    public static final String CDATE = "cDate";

    /**
     * KEY:数据状态
     */
    public static final String EFFECTFLAG = "EFFECTFLAG";

    /**
     * KEY:经办人
     */
    public static final String AGENTCY = "INF_WFL_AGENCY";

    /**
     * KEY:审批人
     */
    public static final String CHECK1 = "INF_WFL_CHECK1";

    /**
     * KEY:复核人
     */
    public static final String CHECK2 = "INF_WFL_CHECK2";

    /**
     * 服务代码：理财产品信息维护
     */
    public static final String SERVER_PROD_INFO = "09002000013";

    /**
     * 服务代码：理财产品信息查询1
     */
    public static final String SERVER_PROD_INFO1 = "09003000014";

    /**
     * 服务代码：理财产品交易明细查询1
     */
    public static final String SERVER_TRADE_SEARCH = "09003000016";

    /**
     * 服务代码：查询理财产品信息
     */
    public static final String SERVER_PROD_SEARCH = "09003000013";

    /**
     * ESB流水号
     */
    public static final String ESB_SEQ_NO = "ESB_SEQ_NO";

    /**
     * 文件路径KEY
     */
    public static final String KEY_FILE_PATH = "FILE_PATH";
}
