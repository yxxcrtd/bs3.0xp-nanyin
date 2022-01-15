/*
 * 文件名：BondAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.cfetsbond.service;

import com.joyintech.framework.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * processor相关业务处理
 *
 * @author zznn
 * @version 1.0 2022年01月11日
 * @see CfetsAutoDurDealProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class CfetsAutoDurDealProcessorService extends BaseService {

    @Autowired
    private CfetsBondAutoDurDealGenerateProcessorService cfetsBondAutoDurDealGenerateProcessorService;

    @Autowired
    private CfetsRepoAutoDurDealGenerateProcessorService cfetsRepoAutoDurDealGenerateProcessorService;

    /**
     * process 数据处理
     *
     * @param batchDate 传递参数
     * @param productIdList 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public void execute(Date batchDate, List<String> productIdList) {
        cfetsBondAutoDurDealGenerateProcessorService.execute(batchDate);
        cfetsRepoAutoDurDealGenerateProcessorService.execute(batchDate);
    }

}
