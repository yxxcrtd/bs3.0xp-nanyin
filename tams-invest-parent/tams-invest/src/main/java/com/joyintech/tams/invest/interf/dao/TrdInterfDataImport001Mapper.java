/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.invest.interf.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001Dto;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001LogDto;
import com.joyintech.tams.invest.interf.dto.ValInterflogExt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 接口任务表
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see TrdInterfDataImport001Mapper
 * @since 1.0
 */
@Repository
public interface TrdInterfDataImport001Mapper {
    
   /**
    * 获取树表数据
    * @param params
    * @return
    */
    List<TrdInterfDataImport001Dto> getInterGroup(Map<String, Object> params);
    
    
    /**
     * 获取树表结构数据
     * @param params
     * @return
     */
    List<SelectOptionDto> getInterGroupCode(Map<String, Object> params);
    
    
    
    
    /**
     * 检查当前日志是否正在进行中
     * @param params
     * @return
     */
    int checkInterLog(Map<String, Object> params);
    
    
    /**
     * 获取界面任务组所需日志信息
     * @param params
     * @return
     */
    List<TrdInterfDataImport001LogDto> getInterActionLog(Map<String, Object> params);
    
    
    /**
     * 获取任务层详情
     * @param params
     * @return
     */
    List<TrdInterfDataImport001LogDto> getInterActionLogDetail(Map<String, Object> params);
    
    /**
     * 获取任务日志详情
     * @param params
     * @return
     */
    List<ValInterflogExt> getValInterflogDetail(Map<String, Object> params);

    
    /**
     * 停止批处理日志
     * @return
     */
    int stopInterLog(Map<String, Object> params);
    
}
