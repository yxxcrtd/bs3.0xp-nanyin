/*
 * 文件名：DisRepCashYieldFileInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月8日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */

package com.joyintech.tams.svreport.interf.dis.prodcashyield.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.dis.common.DisFileInterfCommonService;

/**
 * 产品费率数据，外部数据文件接口入口定义类
 * 
 * @author ZhangYangFei
 * @version 1.0 2019年5月8日
 * @see DisRepCashYieldFileInterfService
 * @since 1.0
 */
@Service
public class DisRepCashYieldFileInterfService extends AbstractFileImpService {

    @Autowired
    private DisRepCashYieldService disRepCashYieldService;

    @Autowired
    private DisFileInterfCommonService disFileInterfCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoByInterf(String currentDate) {
        String successFlag = disFileInterfCommonService.readFileAndImportDb(currentDate,
            disRepCashYieldService, InterfDefine.产品费率数据, DictCodeListExt2.上游接口文件类型_产品费率信息);
        return successFlag;
    }
}
