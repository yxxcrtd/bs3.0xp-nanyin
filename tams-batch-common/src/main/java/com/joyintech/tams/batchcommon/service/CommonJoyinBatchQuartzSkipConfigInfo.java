package com.joyintech.tams.batchcommon.service;

import com.joyintech.batch.core.JoyinBatchQuartzSkipConfigInfo;
import com.joyintech.tams.batchcommon.dao.BatchMstHolidayMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wj
 * @version 1.0.0
 * @ClassName CommonJoyinBatchQuartzSkipConfigInfo.java
 * @Description 跳过节假日
 * @createTime 2021年06月22日 10:19:00
 */
@Service
public class CommonJoyinBatchQuartzSkipConfigInfo implements JoyinBatchQuartzSkipConfigInfo {


    @Autowired
    private BatchMstHolidayMapper batchMstHolidayMapper;

    public boolean isSkip(String marketCalendarType, Date batchDate) {

        String isHoliday = batchMstHolidayMapper.getIsHoliday(batchDate, marketCalendarType);
        if(StringUtils.equals("0",isHoliday)){
            return false;
        }
        return true;
    }
}
