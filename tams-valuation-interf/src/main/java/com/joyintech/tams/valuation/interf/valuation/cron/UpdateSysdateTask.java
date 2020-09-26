/*
 * 文件名：updateSysdateTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.service.BaseCommonService;



/**
 * 接受任務接口
 * 
 * @author chengzhong
 * @version 1.0 2018年6月20日
 * @see updateSysdateTask
 * @since 1.0
 */
@Service("updateSysdateTask")
public class UpdateSysdateTask {


    @Autowired
    private BaseCommonService baseCommonService;

    public void execute() throws Exception{
        baseCommonService.updateSysdate();
    }
}
