/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workmonitor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor001Dto;
import com.joyintech.service.disclosure.information.manage.workmonitor.dto.ReportWorkMonitorNew001Dto;



/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValNetValueConfirm001Mapper
 * @since 1.0
 */
@Repository(value="serviceReportWorkMonitor001Mapper")
public interface ReportWorkMonitor001Mapper {
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ReportWorkMonitor001Dto> queryPageList(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int updateConfirmStatusById(ReportWorkMonitor001Dto dto);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ReportWorkMonitor001Dto> selectJobidList(Map<String, Object> map);

    List<ReportWorkMonitorNew001Dto> queryPageListNew(Map<String, Object> params);

}
