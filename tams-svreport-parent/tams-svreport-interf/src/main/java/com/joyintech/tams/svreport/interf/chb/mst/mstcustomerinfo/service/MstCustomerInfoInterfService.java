package com.joyintech.tams.svreport.interf.chb.mst.mstcustomerinfo.service;

import com.joyintech.tams.common.BaseInterfService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.mst.mstcustomerinfo.dao.MstCustomerInfoInterfMapper;
import com.joyintech.tams.svreport.interf.chb.mst.mstcustomerinfo.dto.MstCustomerInfoInterfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MstCustomerInfoInterfService extends BaseInterfService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MstCustomerInfoInterfMapper mstCustomerInfoInterfMapper;

    @Override
    public List<MstCustomerInfoInterfDto> getBusEntity(List<String> prodIdlist, Date date, String institutionCode, String source) {

        Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.市场机构信息数据.getInterId());

        List<MstCustomerInfoInterfDto> list =mstCustomerInfoInterfMapper.select(lastUpdatetime);

        for(MstCustomerInfoInterfDto dto : list){
            lastUpdatetime = dto.getUpdateTime().after(lastUpdatetime) ? dto.getUpdateTime()
                    : lastUpdatetime;
        }
        if (lastUpdatetime != null) {
            ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.市场机构信息数据.getInterId(), lastUpdatetime);
        }
        return list;
    }

    @Override
    public Class getEntityClass() {
        return MstCustomerInfoInterfDto.class;
    }

    @Override
    public String getFileName() {
        return InterfDefine.市场机构数据.getCode();
    }

}
