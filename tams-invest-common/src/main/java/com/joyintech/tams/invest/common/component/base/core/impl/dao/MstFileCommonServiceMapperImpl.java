/*
 * 文件名：WorkFlowCommonServiceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.component.base.core.impl.dao;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.valuation.common.dto.WflValidInputParameter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件信息共通Service
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年8月26日
 * @see MstFileCommonServiceMapperImpl
 * @since 1.0
 */
@Repository
public interface MstFileCommonServiceMapperImpl {
    /**
     * 
     * 根据查询参数查询留痕表数据
     * <功能详细描述>
     * @param wflValidInputParameter 函数输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<String> getBusinessData(WflValidInputParameter wflValidInputParameter);

    /**
     * 更新数据入信息表
     * @param fileInfo 文件信息
     * @see
     * @since 1.0
     */
    void updateFileBusinessId(MstFileOutputParameter fileInfo);

    /**
     * 获取文件信息
     * <功能详细描述>
     * @param mstFileInputParameter 输入参数
     * @return 文件列表
     * @see
     * @since 1.0
     */
    List<SysUploadFileInfo> getUploadInfo(MstFileInputParameter mstFileInputParameter);
}
