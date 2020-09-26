/*
 * 文件名：ReportRegistBaseService.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债监管报送平台
 * 修改人：魏玉强
 * 修改时间：2019年0月8日
 * 修改内容：新建
 * 系统名称：ReportRegistBaseService
 */

package com.joyintech.tams.batch.report.autosendcb.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.exception.BusinessException;

/**
 * 功能:中债报送系统报表登记批处理统一调度入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月8日
 * @修改记录：
 */
@Service
public abstract class SvrCBBaseService extends AutoSendCBBaseService{
    
    /**
     * 功能:中债报送系统报表登记批统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param executeDate 执行日期
     * @param prodList 产品list
     * @param assetList 资产list
     * @throws BusinessException 业务异常
     * void
     */
    public abstract void execute(Date executeDate,List<String> prodList,List<String> assetList,Map<String,List<String>> param) throws BusinessException;
}