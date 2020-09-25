package com.joyintech.tams.invest.common.threadservice;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-24 17:23
 **/
@Service
public class JasperUtil {

    @Async
    public void jasperExecute(List<TrdProductDeal> tradeList, JoyinRestTemplate restTemplate) {
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            //生成DVP划款指令附件:调用自定义接口项目生成Jasper报表 add by caoxue 2019-08-14 start
            tradeList.forEach(trade -> {
                restTemplate.postForObject(
                        ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/connectJasparAndDownload",
                        trade, String.class);
            });
            //生成DVP划款指令附件:调用自定义接口项目生成Jasper报表 add by caoxue 2019-08-14 end
        }
    }
}
