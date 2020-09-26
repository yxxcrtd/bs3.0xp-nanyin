package com.joyintech.tams.valuation.report.common.base.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.valuation.report.common.base.dao.DaoFromJarMapper;


/**
 * 从jar包中迁移出方法，避免升级版本【该service只可以调用base库数据，因为改方法数据源指向的是base库】
 * @author Administrator
 *
 */
@Component
public class ServiceFromJar {
	
    /**
     * SysUploadFileInfoMapper
     */
    @Autowired
    private DaoFromJarMapper daoFromJarMapper;
	
	/**
     * 获取附件信息
     * 源于jar[com.joyintech.framework.listener.FileAttachmentProcess]中
     * @param fileNo 文件编号
     * @return 附件信息
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public UploadFile fetch(String fileNo) {
    	SysUploadFileInfo file = daoFromJarMapper.selectByPrimaryKey(fileNo);
        if(null != file) {
            UploadFile uploadFile = new UploadFile(null, fileNo, new File(file.getPath()));
            uploadFile.setFileName(file.getFileName());
            return uploadFile;
        }
        return null;
    }
    
    
    /**
     * 文件上传
     * 源于jar[com.joyintech.framework.listener.FileAttachmentProcess]中
     * @param uploadFile 附件信息
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void upload(UploadFile fileInfo) {
        SysUploadFileInfo file = new SysUploadFileInfo();
        file.setSeqNo(fileInfo.getFileNo());
        file.setFileName(FileNameUtil.formatFileName(fileInfo.getTargetFile().getName()));
        file.setPath(fileInfo.getTargetFile().getPath());
        daoFromJarMapper.insert(file);
    }

}
