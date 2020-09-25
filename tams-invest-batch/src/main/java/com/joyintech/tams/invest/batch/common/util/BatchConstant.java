/*
 * 文件名：BatchConstant.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.common.util;


/**
 * BatchConstant
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BatchConstant
 * @since 1.0
 */
public interface BatchConstant {

    /**
     * READER_RESULT
     */
    String READER_RESULT = "READER_RESULT";

    /**
     * BATCH_INFO
     */
    String BATCH_INFO = "BATCH_INFO";

    /**
     * PLATE_DATE
     */
    String PLATE_DATE = "PLATE_DATE";

    //-----add by 裴家俊 20200218 风险
    /**
     * KEY:通知类型
     */
    String KEY_CALLTYPE = "callType";

    /**
     * KEY:处理状态
     */
    String KEY_DEALSTATUS = "dealStatus";

    /**
     * KEY:业务编号
     */
    String KEY_INTERFID = "interfId";

    /**
     * KEY:文件名
     */
    String KEY_FILENAME = "fileName";

    /**
     * KEY:文件名前缀
     */
    String KEY_FILECODE = "fileCode";

    /**
     * KEY:文件批次号
     */
    String KEY_FILENO = "fileNo";

    /**
     * KEY:文件路径
     */
    String KEY_FILEPATH = "filePath";

    /**
     * KEY:产品列表
     */
    String KEY_PRODID_LIST = "prodIdList";
}
