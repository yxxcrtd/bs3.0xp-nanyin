package com.joyintech.tams.invest.batch.extrdautosettle.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.RtgsStatusRet;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RtgsStatusRetService extends BaseService {

    /**
     * 取手工交收结果
     * @return
     */
    public  List<RtgsStatusRet> getRtgsStatusRetList(Set<String> dealNoSet){
        List<String> dealNoList = new ArrayList<>(dealNoSet);
        List<RtgsStatusRet> res = restTemplate.postForList(ServiceApplication.EXCHANGE_SERVICE_NAME + "/order/rtgs/status",
                dealNoList, RtgsStatusRet.class);
        return res;
    }
}
