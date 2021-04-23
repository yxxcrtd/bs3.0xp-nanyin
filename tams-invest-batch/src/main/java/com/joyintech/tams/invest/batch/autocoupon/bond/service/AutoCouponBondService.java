package com.joyintech.tams.invest.batch.autocoupon.bond.service;

import com.joyintech.tams.invest.batch.autocoupon.com.ThreadPoolTool;
import com.joyintech.tams.invest.batch.autocoupon.dao.AutoCouponMapper;
import com.joyintech.tams.invest.batch.autocoupon.dto.AutoCouponErrLog;
import com.joyintech.tams.invest.common.autocoupon.service.CommonAutoCouponBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * @author zhangnian
 * @ClassName AutoCouponBondService
 * @description 自动加券:债券
 * @date 2020/10/14 0014 10:25
 **/
@Service
public class AutoCouponBondService {
    private static final Logger LOG = LoggerFactory.getLogger(AutoCouponBondService.class);
    //单线程数量
    private static final  int SINGLE_COUNT=2000;

    @Autowired
    private AutoCouponMapper autoCouponMapper;

    @Autowired
    private CommonAutoCouponBatchService commonAutoCouponBatchService;

    public List<Future> run(Date currentDate){
        List<String> listBond = autoCouponMapper.queryInterfPayhsrcsecinfo(currentDate);
        ThreadPoolTool<AutoCouponErrLog,String> tool = new ThreadPoolTool(SINGLE_COUNT,listBond);
        tool.setCallBack(new ThreadPoolTool.CallBack<AutoCouponErrLog,String>() {
            @Override
            public List<AutoCouponErrLog> method(List<String> list){
                List<AutoCouponErrLog> result = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i);
                    try {
                        commonAutoCouponBatchService.autoBondTradeCoupon(str);
                    }catch (Exception e){
                        AutoCouponErrLog autoCouponErrLog=new AutoCouponErrLog();
                        UUID uuid = UUID.randomUUID();
                        String id = (uuid.toString()).replace("-", "").substring(0, 32);
                        autoCouponErrLog.setSeqNo(id);
                        autoCouponErrLog.setFinprodId(str);
                        autoCouponErrLog.setExecTime(new Date());
                        autoCouponErrLog.setErrMessage(e.getMessage());
                        autoCouponErrLog.setCreateTime(new Date());
                        autoCouponErrLog.setUpdateTime(new Date());
                        autoCouponErrLog.setCreateUser("wind");
                        autoCouponErrLog.setUpdateUser("wind");
                        result.add(autoCouponErrLog);
                        continue;
                    }
                }
                return result;
            }
        });
        return tool.excute();
    }
} 

