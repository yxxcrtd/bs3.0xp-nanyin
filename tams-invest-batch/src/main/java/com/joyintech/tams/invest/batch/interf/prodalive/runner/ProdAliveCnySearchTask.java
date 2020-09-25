/*
 * 文件名：ProdAliveCnySearchTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.prodalive.runner;

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
 * 货币型存续期定时批
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see ProdAliveCnySearchTask
 * @since 1.0
 */
@Service("prodAliveCnySearchTask")
@Scope("prototype")
public class ProdAliveCnySearchTask extends LoadProdDataTask {

    /**
     * {@inheritDoc}
     */
    @Override
    public DoType getDoType() {
        // 任务类型 产品到期
        return ProdListService.DoType.PROD_ALIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getDealMode() {
        // 产品处理模式 普通N天
        List<String> dealMode = new ArrayList<>();
        dealMode.add(BussinessDictCodeList.处理模式_货币型.getValue());
        return dealMode;
    }
}
