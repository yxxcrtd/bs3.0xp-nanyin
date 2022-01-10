package com.joyintech.tams.svreport.interf.dis.boksubject.service;

import com.joyintech.tams.common.BaseInterfService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.dis.boksubject.dao.BokSubjectInterfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BokSubjectInterfServiceNew extends BaseInterfService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BokSubjectInterfMapper bokSubjectInterfMapper;

    @Override
    public List<BokSubject> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {

        Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.科目数据.getInterId());

        List<BokSubject> list =bokSubjectInterfMapper.select(lastUpdatetime);

        for(BokSubject dto : list){
            lastUpdatetime = dto.getUpdateTime().after(lastUpdatetime) ? dto.getUpdateTime()
                    : lastUpdatetime;
        }
        if (lastUpdatetime != null) {
            ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.科目数据.getInterId(), lastUpdatetime);
        }
        return list;
    }

    @Override
    public Class getEntityClass() {
        return BokSubject.class;
    }

    @Override
    public String getFileName() {
        return InterfDefine.科目数据.getCode();
    }

}
