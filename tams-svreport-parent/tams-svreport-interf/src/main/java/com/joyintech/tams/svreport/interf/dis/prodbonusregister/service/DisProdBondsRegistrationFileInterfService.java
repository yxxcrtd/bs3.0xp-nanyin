package com.joyintech.tams.svreport.interf.dis.prodbonusregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.dis.common.DisFileInterfCommonService;


/**
 * 产品分红登记，外部数据文件接口入口定义类
 * 
 * @author ChuFengjie
 * @version 1.0 2019年5月8日
 * @see DisRepCashYieldFileInterfService
 * @since 1.0
 */
@Service
public class DisProdBondsRegistrationFileInterfService extends AbstractFileImpService {
    
    @Autowired
    private DisProdBondsRegistrationService disProdBondsRegistrationService;
    
    @Autowired
    private DisFileInterfCommonService disFileInterfCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoByInterf(String currentDate) {
        String successFlag = disFileInterfCommonService.readFileAndImportDb(currentDate, disProdBondsRegistrationService, 
        		InterfDefine.产品分红登记, DictCodeListExt2.上游接口文件类型_产品分红登记信息);
        return successFlag;
    }
}

