/*
 * 文件名：CheckReport001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月18日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */

package com.joyintech.tams.report.submitmanagement.checkreport.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.ChbReportCheck;

/**
 * 报送前检查
 * 
 * @author zuoyinyi
 * @version 1.0 2017年10月18日
 * @see CheckReport001Dto
 * @since 1.0
 */
public class CheckReport001Dto extends ChbReportCheck implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1460507654984427202L;

    /**
     * 错误信息项
     */
    private String errorInfo;
    
    /**
     * 警告信息项
     */
    private String warnInfo;

    /**
     * 获取 errorInfo
     * 
     * @return errorInfo.
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * 设置 errorInfo
     * 
     * @param errorInfo errorInfo
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    
    /**
     * 获取 warnInfo
     * @return warnInfo.
     */
    public String getWarnInfo() {
        return warnInfo;
    }

    
    /**
     * 设置 warnInfo
     * @param warnInfo warnInfo
     */
    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }
}
