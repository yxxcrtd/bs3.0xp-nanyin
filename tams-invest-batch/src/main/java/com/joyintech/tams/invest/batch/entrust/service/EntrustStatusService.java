/*
 * 文件名：EntrustStatusService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.entrust.service;

import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.entrust.dao.EntrustStatusMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  更新关闭委托状态
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月15日
 * @see EntrustStatusService
 * @since 1.0
 */
@Service
public class EntrustStatusService {

    @Autowired
    private EntrustStatusMapper entrustStatusMapper;

    @Autowired
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

   /* @Autowired
    private PinganBatchPlatformDateImpl platformDateService;*/
    
    public void run(SysBatchExecInfo batchExecuteInfo){
        if(null!=batchExecuteInfo.getExecuteDate()){
            Map<String, String> params = new HashMap<>();
            String sysDate = DateUtil.format(batchExecuteInfo.getExecuteDate());
            params.put("sysDate", sysDate);
            List<String> entrustIdList = entrustStatusMapper.selectEntrustIds(params);
            entrustIdList.forEach(s -> baseEtrCashPositionCommService.entrustCloseExecute(s));
            entrustStatusMapper.updateDistributeStatus(params);//add by yangwenjun 20201211 更新委托指令分发表中的接收状态
            entrustStatusMapper.updateEntrustStatus(params);
        }
    }
}
