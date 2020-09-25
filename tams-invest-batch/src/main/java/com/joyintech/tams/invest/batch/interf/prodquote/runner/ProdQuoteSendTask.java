/*
 * 文件名：ProdQuoteSendTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.prodquote.runner;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.batch.interf.runner.LoadProdDataTask;
import com.joyintech.tams.invest.batch.interf.service.ProdListService;
import com.joyintech.tams.invest.batch.interf.service.ProdListService.DoType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 报送单位净值_万分收益定时任务
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see ProdQuoteSendTask
 * @since 1.0
 */
@Service("prodQuoteSendTask")
@Scope("prototype")
public class ProdQuoteSendTask extends LoadProdDataTask {

    /**
     * {@inheritDoc}
     */
    @Override
    public DoType getDoType() {
        return ProdListService.DoType.PROD_VNET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getDealMode() {
        List<String> dealMode = new ArrayList<>();
        dealMode.add(BussinessDictCodeList.处理模式_净值型.getValue());
        dealMode.add(BussinessDictCodeList.处理模式_货币型.getValue());
        return dealMode;
    }
}
