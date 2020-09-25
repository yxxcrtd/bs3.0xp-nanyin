/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.manage.workmonitor.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.information.manage.workmonitor.dto.ReportWorkMonitor002Dto;

/**
 * 
 * 作业监控详细
 * @author 郭忠宝
 * @version 1.0 2018年12月5日
 * @see ValNetValueConfirm002Mapper
 * @since 1.0
 */
public interface ReportWorkMonitor002Mapper {

	List<ReportWorkMonitor002Dto> findPage(Map<String, Object> params);
    
}
