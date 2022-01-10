package com.joyintech.tams.svreport.interf.chb.mst.mstholiday.service;

import com.joyintech.tams.common.BaseInterfService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.mst.mstholiday.dao.SvrMstHolidayInterfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMstHolidayInterfService extends BaseInterfService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrMstHolidayInterfMapper svrMstHolidayInterfMapper;

    @Override
    public List<MstHoliday> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {

        Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.节假日数据.getInterId());

        List<MstHoliday> list =svrMstHolidayInterfMapper.select(lastUpdatetime);

        for(MstHoliday dto : list){
            lastUpdatetime = dto.getUpdateTime().after(lastUpdatetime) ? dto.getUpdateTime()
                    : lastUpdatetime;
        }
        if (lastUpdatetime != null) {
            ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.节假日数据.getInterId(), lastUpdatetime);
        }
        return list;
    }

    @Override
    public Class getEntityClass() {
        return MstHoliday.class;
    }

    @Override
    public String getFileName() {
        return InterfDefine.节假日数据.getCode();
    }

}
