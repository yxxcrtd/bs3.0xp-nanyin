package com.joyintech.tams.invest.batch.autocoupon.dao;

import com.joyintech.tams.invest.batch.autocoupon.dto.AutoCouponErrLog;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author zhangnian
 * @ClassName AutoCouponMapper
 * @description TODO
 * @date 2020/10/14 0014 10:51
 **/
@Repository
public  interface AutoCouponMapper {

    //接口表查询债券信息
    List<String> queryInterfPayhsrcsecinfo(Date nowSysDate);

    List<String> queryInterfCFuturesDescription();

    //接口表查询基金信息
    List<String> queryInterfFundinfo(Date nowSysDate);


    //记录自动加券异常的信息
    int insertAutoCouponErrLog(AutoCouponErrLog autoCouponErrLog);
} 

