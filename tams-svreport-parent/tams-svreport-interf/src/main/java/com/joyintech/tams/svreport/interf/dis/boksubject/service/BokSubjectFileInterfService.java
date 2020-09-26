/*
 * 文件名：DisRepProdInvestAdjustFileInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月8日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */

package com.joyintech.tams.svreport.interf.dis.boksubject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.dis.common.DisFileInterfCommonService;

/**
 * 科目数据接口入口定义类
 * 
 * @author ZhangYangFei
 * @version 1.0 2019年5月8日
 * @see BokSubjectFileInterfService
 * @since 1.0
 */
@Service
public class BokSubjectFileInterfService extends AbstractFileImpService {

    @Autowired
    private BokSubjectInterfService bokSubjectInterfService;

    @Autowired
    private DisFileInterfCommonService disFileInterfCommonService;

    /**
     * {@inheritDoc}
     */
    public String getInfoByInterf(String currentDate) {
        String successFlag = disFileInterfCommonService.readFileAndImportDb(currentDate,
        		bokSubjectInterfService, InterfDefine.科目数据, DictCodeListExt2.上游接口文件类型_科目数据信息);
        return successFlag;
    }

}
