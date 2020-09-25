package com.joyintech.tams.systemtools.dao;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 文件系统相关mapper
 *
 * @author: 王波
 * @date : 2019/9/4 13:52
 */
@Repository
public interface JoyinFSMapper {

    SysUploadFileInfo selectByPrimaryKey(String seqNo);

    int insert(SysUploadFileInfo record);

    int deleteByPrimaryKey(String seqNo);

    /**
     * 获取附件信息
     * @param businessCode 业务代码
     * @return List&lt;UploadFile&gt;
     */
    List<SysUploadFileInfo> getUploadInfo(@Param("businessCode") String businessCode);

    /**
     * 删除
     * @param businessCode 业务代码
     * @return 删除结果
     */
    int removeByBusinessCode(@Param("businessCode") String businessCode);

    int updateByPrimaryKeySelective(SysUploadFileInfo record);
}
