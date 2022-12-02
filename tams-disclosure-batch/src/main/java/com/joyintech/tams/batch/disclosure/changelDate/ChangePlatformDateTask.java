package com.joyintech.tams.batch.disclosure.changelDate;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.system.cache.CacheRefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * 平台日切换批处理
 *
 * @author 刘二通
 * @version 1.0 2018年11月23日
 * @see ChangePlatformDateTask
 * @since 1.0
 */
@Service("changePlatformDateTask")
@Scope("prototype")
public class ChangePlatformDateTask extends BatchBaseService {
    /**
     * PlatformDate
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;
    private CacheRefreshService cacheRefreshService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
            throws BatchException {

        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);

        try {
            // 当前平台日
            Date platformDate = platformDateService.getSysDate();
            // 切日后平台日
            Date newPlatformDate = DateUtil.addDay(platformDate, 1);
            // 平台日切换
            platformDateService.updateSysDate(newPlatformDate);

            result.setStatus(BatchStatus.COMPLETED);

            this.cacheRefreshService.updatePlatDate((Set)null);

        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        return result;

    }



}
