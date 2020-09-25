/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workrule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage001Dto;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule001Dto;


/**
 * 
 * 作业规则查询
 * @author leipeng
 * @version 1.0 2018年11月29日
 * @see ReportRule001Mapper
 * @since 1.0
 */
@Repository(value="serviceReportRule001Mapper")
public interface ReportRule001Mapper {
    
    /**
     * 分页查询
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ReportRule001Dto> queryPageList(Map<String, Object> params);


}
