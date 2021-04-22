/*
 * 文件名：EntrustCompleteService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskinfrds.util.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.invest.common.component.base.entrust.dto.BaseEtrFinishAmtDto;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 委托完成释放剩余额度Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月27日
 * @see EntrustCompleteService
 * @since 1.0
 */
@Service
public class EntrustCompleteService extends BaseService<EtrEntrust> {

    public static final BigDecimal NUM_100 = new BigDecimal("100.00");

    /**
     * 投资委托信息Service
     */
    @Autowired
    private BaseEtrInvestInfoServiceImpl etrInfoService;

    /**
     * 获取RestTemplate
     * 
     * @return RestTemplate
     */
    public JoyinRestTemplate getJoyinRestTemplate() {
        return restTemplate;
    }

    /**
     * 取得指令完成状况
     * 
     * @param etrList 指令代码
     * @return 指令完成状况
     */
    public Map<String, Boolean> getEtrCompletedMap(List<String> etrList) {
        // 取得委托完成状况查询
        List<BaseEtrFinishAmtDto> finishList = etrInfoService.queryDelegateStatus(etrList);
        // 判断委托代码的完成额度是否大于等于100
        Map<String, Boolean> entrustList = new HashMap<>();
        for (BaseEtrFinishAmtDto dto : finishList) {
            entrustList.put(dto.getEntrustId(), dto.getSumFinishPercent().compareTo(NUM_100) >= 0);
        }
        return entrustList;
    }
}
