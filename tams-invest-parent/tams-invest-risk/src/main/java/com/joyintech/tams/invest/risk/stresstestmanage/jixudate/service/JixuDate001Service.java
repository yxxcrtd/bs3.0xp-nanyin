/*
 * 文件名：JixuDate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.jixudate.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.common.base.pojo.SceneBatchOperationPojo;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.jixudate.dto.JixuDate001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Service
 * @since 1.0
 */
@Service
public class JixuDate001Service extends CommonExportService<JixuDate001Dto>{
    
    /**
     * 更新jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @param newDate
     * @return
     * @see
     * @since 1.0
     */
    public Integer updateJixuDate(Date newDate) {
        
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/JixuDate001/updateJixuDate",
            newDate, Integer.class);
    }
    
    /**
     * 得到jixu日期
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Date getJixuDate() {
        
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/JixuDate001/getJixuDate",
            "", Date.class);
    }
    
}
