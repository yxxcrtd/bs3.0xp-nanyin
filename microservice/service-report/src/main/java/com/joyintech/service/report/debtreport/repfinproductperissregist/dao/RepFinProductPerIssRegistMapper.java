/*
 * 文件名：RepFinProductPerIssRegistMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperissregist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductperissregist.dto.RepFinProductPerIssRegistDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月20日
 * @see RepFinProductPerIssRegistMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductPerIssRegistMapper {

    /**
     * 产品名称的联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepFinprodName(Map<String, Object> param);

    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepFinProductPerIssRegistDto> select(Map<String, Object> pageParam);

    /**
     * 导出
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<RepFinProductPerIssRegistDto> selectForExport(Map<String, Object> param);

}
