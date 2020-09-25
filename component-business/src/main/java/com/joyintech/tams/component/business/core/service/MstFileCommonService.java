/*
 * 文件名：MstFileCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.core.service;

import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 附件信息共通接口类
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月27日
 * @see MstFileCommonService
 * @since 1.0
 */
public interface MstFileCommonService {
    
    /**
     * 
     * 上传附件
     * <功能详细描述>
     * @param mstFileInputParameter 函数输入参数
     * @return 文件列表
     * @see
     * @since 1.0
     */
     MstFileOutputParameter uploadFile(MstFileInputParameter mstFileInputParameter);
    
    /**
     * 
     * 获取附件(业务编码)
     * <功能详细描述>
     * @param bussinessId 业务编号
     * @return 文件列表
     * @see
     * @since 1.0
     */
     MstFileOutputParameter getFileInfoByBusiId(String bussinessId);
    
    /**
     * 
     * 获取附件(通过文件名)
     * <功能详细描述>
     * @param fileName 文件名
     * @return 文件信息
     * @see
     * @since 1.0
     */
     MstFileOutputParameter getFileInfoByFileName(String fileName);
}
