package com.joyintech.tams.systemtools.dfs;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * @description: 基于本地的的NAS文件共享系统
 * @author: 王波
 * @date : 2019/9/5 9:30
 */
public class NASFileSystemMethod implements JoyinFileSystemMethod {
    @Override
    public void upload(SysUploadFileInfo uploadFile) {
        // 不做任何处理
    }

    @Override
    public UploadFile fecth(SysUploadFileInfo file) {
        return null;
    }
}
