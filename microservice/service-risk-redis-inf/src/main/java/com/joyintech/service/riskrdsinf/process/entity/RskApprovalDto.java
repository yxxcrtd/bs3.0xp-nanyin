/*
 * 文件名：RskApprovalDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.process.entity;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 风险审批Dto
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月23日
 * @see RskApprovalDto
 * @since 1.0
 */
public interface RskApprovalDto extends ITableRow, ProcessEntity {

    /**
     * 取得 画面模式
     * 
     * @return 画面模式
     * @see
     * @since 1.0
     */
    String getPageModel();

    /**
     * 设置 画面模式
     * 
     * @param pageModel 画面模式
     * @see
     * @since 1.0
     */
    void setPageModel(String pageModel);

    /**
     * 取得 画面操作类型
     * 
     * @return 画面操作类型
     * @see
     * @since 1.0
     */
    String getOperateType();

    /**
     * 设置 画面操作类型
     * 
     * @param operateType 画面操作类型
     * @see
     * @since 1.0
     */
    void setOperateType(String operateType);

    /**
     * 取得 试算结论
     * 
     * @return 试算结论
     * @see
     * @since 1.0
     */
    String getConclusion();

    /**
     * 设置 试算结论
     * 
     * @param conclusion 试算结论
     * @see
     * @since 1.0
     */
    void setConclusion(String conclusion);

    /**
     * 取得 画面元素ID
     * 
     * @return 画面元素ID
     * @see
     * @since 1.0
     */
    String getElementId();

    /**
     * 设置 画面元素ID
     * 
     * @param elementId 画面元素ID
     * @see
     * @since 1.0
     */
    void setElementId(String elementId);

    /**
     * 取得 画面ID
     * 
     * @return 画面ID
     * @see
     * @since 1.0
     */
    String getPageId();

    /**
     * 设置 画面ID
     * 
     * @param pageId 画面ID
     * @see
     * @since 1.0
     */
    void setPageId(String pageId);

    /**
     * 系统标识表明由哪个系统发起的流程
     * 
     * @return 系统标识
     * @see
     * @since 1.0
     */
    String getSysCode();

    /**
     * 设置系统标识
     * 
     * @param sysCode 系统标识
     * @see
     * @since 1.0
     */
    void setSysCode(String sysCode);
}
