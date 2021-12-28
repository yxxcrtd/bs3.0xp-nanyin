package com.joyintech.tams.svreport.interf.chb.bokbalance.service;

import com.joyintech.tams.common.BaseInterfService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.bokbalance.dao.BokBalanceInterfMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BokBalanceInterfServiceBak extends BaseInterfService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BokBalanceInterfMapper bokBalanceInterfMapper;

    @Override
    public List<BokBalance> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {

        Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.科目余额数据.getInterId());

        List<BokBalance> list =bokBalanceInterfMapper.select(lastUpdatetime);

        for(BokBalance dto : list){
            lastUpdatetime = dto.getUpdateTime().after(lastUpdatetime) ? dto.getUpdateTime()
                    : lastUpdatetime;
        }
        if (lastUpdatetime != null) {
            ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.科目余额数据.getInterId(), lastUpdatetime);
        }
        return list;
    }

    @Override
    public Class getEntityClass() {
        return BokBalance.class;
    }

    @Override
    public String getFileName() {
        return InterfDefine.科目余额数据.getCode();
    }

}
