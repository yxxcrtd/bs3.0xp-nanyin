package com.joyintech.tams.svreport.interf.dis.institutiondata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.dis.common.DisFileInterfCommonService;


/**
 * 关联机构数据，外部数据文件接口入口定义类
 * 
 * @author ChuFengJie
 * @version 1.0 2019年5月8日
 * @see DisRepCashYieldFileInterfService
 * @since 1.0
 */
@Service
public class DisAssociatedInstitutionsFileInterfService extends AbstractFileImpService {
    
    @Autowired
    private DisAssociatedInstitutionsService disAssociatedInstitutionsService;
    
    @Autowired
    private DisFileInterfCommonService disFileInterfCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoByInterf(String currentDate) {
        String successFlag = disFileInterfCommonService.readFileAndImportDb(currentDate, disAssociatedInstitutionsService, 
        		InterfDefine.关联机构信息, DictCodeListExt2.上游接口文件类型_关联机构信息);
        return successFlag;
    }
}

