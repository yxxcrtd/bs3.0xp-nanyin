/*
 * 文件名：ResetBaseRuleInfoReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdfutureopenplangen.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.service.SusPrdFutureOpenPlanGenReaderService;

/**
 * 对应 读取数据
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SusPrdFutureOpenPlanGenReader extends BaseReader<SusPrdFutureOpenPlanGenDto>{

    @Autowired
    private SusPrdFutureOpenPlanGenReaderService readerService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<SusPrdFutureOpenPlanGenDto> createList() {
        return readerService.execute(this.getParams());	
    }

}
