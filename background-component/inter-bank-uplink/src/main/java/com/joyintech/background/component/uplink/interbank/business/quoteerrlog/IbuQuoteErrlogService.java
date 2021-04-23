/*
 * 文件名：IbuQuoteErrlogService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.quoteerrlog;

import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteErrlogDto;
import com.joyintech.background.component.uplink.interbank.business.quoteerrlog.impl.IbuQuoteErrlogServiceImpl;

/**
 * 报价错误日志服务
 * @author zhengjunping
 * @version 1.0 2020年5月23日
 * @see IbuQuoteErrlogServiceImpl
 * @since 1.0
 */
public interface IbuQuoteErrlogService {

    /**
     * 根据报价编号查询错误日志
     * @param quoteId String
     * @return IbuQuoteErrlogDto
     */
    IbuQuoteErrlogDto selectErrlogByQuoteId(String  quoteId);
    
    /**
     * 保存错误信息
     * @param ibuQuoteErrlogDto IbuQuoteErrlogDto
     * @return int
     */
    int insertIbuQuoteErrlog(IbuQuoteErrlogDto ibuQuoteErrlogDto);
    
    /**
     * 删除错误信息
     * @param quoteId String
     * @return int
     */
    int deleteIbuQuoteErrlog(String  quoteId);
    
    /**
     * 获取版本号
     * @return Long
     */
    Long getVersion();
}
