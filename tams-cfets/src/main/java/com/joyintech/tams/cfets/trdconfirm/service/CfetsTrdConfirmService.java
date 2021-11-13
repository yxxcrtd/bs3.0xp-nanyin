/*
 * 文件名：CfetsTrdConfirmService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：sjj
 * 修改时间：2021年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.trdconfirm.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.cfets.trdconfirm.dao.CfetsTrdConfirmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 银行间交易成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmService
 * @since 1.0
 */
@Service("CfetsTrdConfirmService")
public class CfetsTrdConfirmService extends BaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsTrdConfirmService.class);

    @Autowired
    private CfetsTrdConfirmMapper cfetsTrdConfirmMapper;


}
