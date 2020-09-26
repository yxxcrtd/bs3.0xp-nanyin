/*
 * 文件名：BatchChildDispatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoDeal;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.TodoTask;

/**
 * 子调度批处理 - 数据库数据获取
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchMapper
 * @since 1.0
 */
@Repository
public interface BatchChildDispatchMapper {

   /**
    * 删除批处理待处理任务表所有数据
    * 
    * @see
    * @since 1.0
    */
    void deleteAllOfTodo();

    /**
     * 取得所有倒起息交易数据
     * @param param
     * @return
     * @see
     * @since 1.0
     */
     List<TodoDeal> queryTodoDeal(Map<String, Object> param);
    
    /**
     * 取得批处理优先信息
     * @param param2
     * @return
     * @see
     * @since 1.0
     */
     public List<TodoTask> queryTodoTasks(Map<String, String> param2);
     
     
     /**
      * 取得批处理优先信息
      * @param param2
      * @return
      * @see
      * @since 1.0
      */
      public List<TodoTask> queryTodoTasksEx();
}
