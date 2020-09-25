/*
 * 文件名：LoadProdDataTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.interf.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.invest.interf.constant.InterfServiceCaller;
import com.joyintech.tams.invest.interf.dto.BatchResultDto;
import com.joyintech.tams.invest.interf.service.InterfComponentService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.interf.service.ProdListService;
import com.joyintech.tams.invest.batch.interf.service.ProdListService.DoType;


/**
 * 产品端batch共同类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see LoadProdDataTask
 * @since 1.0
 */
public abstract class LoadProdDataTask extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询产品数据Service
     */
    @Autowired
    private ProdListService prodService;

    /**
     * 接口组件服务
     */
    @Autowired
    private InterfComponentService interfComponentService;

    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecution execute()
        throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        try {
            // 支持页面配置的产品组，但必须符合任务需要的产品
            List<String> prodList = StringUtil.isEmptyList(
                getProductIdList()) ? prodService.createList(getDoType(),
                    getDealMode()) : getProductIdList();
            // 接口参数-任务信息
            BatchResultDto dto = new BatchResultDto();
            dto.setBatchId(this.getBatchTask().getTaskId());
            dto.setBatchName(this.getBatchTask().getBeanName());
            dto.setProdList(prodList);
            // batch执行
            interfComponentService.sendBatchRequest(InterfServiceCaller.PROD_BATCH.code(), dto);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    /**
     * 取得产品模块
     * 
     * @return 产品模块
     * @see
     * @since 1.0
     */
    public abstract DoType getDoType();

    /**
     * 取得处理模式列表
     * 
     * @return 处理模式列表
     * @see
     * @since 1.0
     */
    public abstract List<String> getDealMode();
}
