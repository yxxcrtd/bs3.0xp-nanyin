/*
 * 文件名：BookDetailCompareIntoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchchilddispatch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dao.BatchChildDispatchMapper;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoTask;

/**
 * 账务明细比较合并批处理 - reader相关业务处理
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BatchChildDispatchReaderMastService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchChildDispatchReaderMastService {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private BatchChildDispatchMapper batchChildDispatchMapper;

    
    /**
     * 取得批处理优先信息
     * @param param2
     * @return
     * @see
     * @since 1.0
     */
     public List<TodoTask> queryTodoTasksEx() {
         return batchChildDispatchMapper.queryTodoTasksEx();
    }


}
