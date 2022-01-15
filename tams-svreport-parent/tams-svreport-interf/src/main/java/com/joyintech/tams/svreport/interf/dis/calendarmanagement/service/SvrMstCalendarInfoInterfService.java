package com.joyintech.tams.svreport.interf.dis.calendarmanagement.service;

import com.joyintech.tams.common.BaseInterfService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.dis.calendarmanagement.dao.SvrMstCalendarInfoInterfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMstCalendarInfoInterfService extends BaseInterfService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrMstCalendarInfoInterfMapper svrMstCalendarInfoInterfMapper;

    @Override
    public List<MstCalendarInfo> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {

        Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.日历管理信息数据.getInterId());

        List<MstCalendarInfo> list =svrMstCalendarInfoInterfMapper.select(lastUpdatetime);

        for(MstCalendarInfo dto : list){
            lastUpdatetime = dto.getUpdateTime().after(lastUpdatetime) ? dto.getUpdateTime()
                    : lastUpdatetime;
        }
        if (lastUpdatetime != null) {
            ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.日历管理信息数据.getInterId(), lastUpdatetime);
        }
        return list;
    }

    @Override
    public Class getEntityClass() {
        return MstCalendarInfo.class;
    }

    @Override
    public String getFileName() {
        return InterfDefine.日历管理信息数据.getCode();
    }

}
