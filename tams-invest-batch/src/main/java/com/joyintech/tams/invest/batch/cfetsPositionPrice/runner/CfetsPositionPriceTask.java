/*
 * 文件名：CfetsSettleTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.cfetsPositionPrice.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.invest.batch.cfetsPositionPrice.service.CfetsPositionPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  业绩基准重置
 * 〈功能详细描述〉
 * @author sjj
 * @version 1.0 2021年11月30日
 * @see CfetsPositionPriceTask
 * @since 1.0
 */
@Service("cfetsPositionPriceTask")
@Scope("prototype")
public class CfetsPositionPriceTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;

    @Autowired
    private CfetsPositionPriceService cfetsPositionPriceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
            throws BatchException {

        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }

        // 自动批处理，取平台日期为跑批日，手动批处理，取画面选择日期为跑批日
        Date batchDate = batchExecuteInfo.getExecuteDate();
        if (StringUtil.equals(BatchDictCodeList.批处理启动模式_自动.getValue(),
                batchExecuteInfo.getStartMode())) {
            batchDate = platformDateService.getSysDate();
        }


        try {
            cfetsPositionPriceService.autoSettle(batchDate, this.getProductIdList());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
           logger.error(e.getMessage());
        }

        return result;
    }
}
