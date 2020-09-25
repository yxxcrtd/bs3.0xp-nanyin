/*
 * 文件名：JoyinFileSystemMethod.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2019年9月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.systemtools.dfs;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
  *  文件系统取文件相关方法
 * @author 王波
 * @version 1.0 2019年9月4日
 * @see JoyinFileSystemMethod
 * @since 1.0
 */
public interface JoyinFileSystemMethod {

    /**
     * 上传文件
     * @param uploadFile
     */
    public void upload(SysUploadFileInfo uploadFile);

    public UploadFile fecth(SysUploadFileInfo file);
}
