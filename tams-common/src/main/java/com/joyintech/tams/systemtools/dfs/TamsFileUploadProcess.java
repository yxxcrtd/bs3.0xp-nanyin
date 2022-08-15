package com.joyintech.tams.systemtools.dfs;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.systemtools.dao.JoyinFSMapper;
import com.joyintech.tools.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * ClassName TamsFileUploadProcess
 * Description tams文件上传处理过程处理
 *
 * @Author ZY22350
 * @Date 2022/8/12 10:15
 * @Version 1.0.0
 **/
@Component
@Primary
public class TamsFileUploadProcess implements FileAttachmentProcess {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    /**
     * 文件服务模板
     */
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * joyinFSMapper
     */
    @Autowired
    private JoyinFSMapper joyinFSMapper;

    @Override
    @DataSource(DataSourceType.OTHER)
    public UploadFile fetch(String fileNo) {
        // 查询附件
        SysUploadFileInfo file = null;
        file = joyinFSMapper.selectByPrimaryKey(fileNo);
        if (file != null) {
            UploadFile uploadFile = new UploadFile(null, fileNo, new SafelyFile(file.getPath()));
            uploadFile.setFileName(file.getFileName());
            uploadFile = joyinDFSTemplate.fetch(file);
            return uploadFile;
        }
        return null;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public void upload(UploadFile uploadFile) {
        SysUploadFileInfo file = new SysUploadFileInfo();
        file.setSeqNo(uploadFile.getFileNo());
        file.setFileName(FileNameUtil.formatFileName(uploadFile.getTargetFile().getName()));
        file.setPath(uploadFile.getTargetFile().getPath());
        joyinDFSTemplate.upload(file);
        joyinFSMapper.insert(file);
//        File f = new SafelyFile(file.getPath());
//        uploadFile.setTargetFile(f);
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public int remove(String fileNo) {
        UploadFile file = this.fetch(fileNo);
        if (null != file) {
            String filePath = file.getTargetFile().getPath();
            if(filePath.toLowerCase(Locale.CHINA).startsWith("http:")||filePath.toLowerCase(Locale.CHINA).startsWith("https:")) {

            }else{
                FileUtil.deleteFile(file.getTargetFile().getPath());
            }
            return joyinFSMapper.deleteByPrimaryKey(fileNo);
        }
        return 0;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    public int removeByBusinessId(String businessId) {
        return joyinFSMapper.removeByBusinessCode(businessId);
    }

    @Override
    public int updateBussinessNo(List<String> fileNoList, String bussinessNo) {
        int result = 0;
        if (null != fileNoList && fileNoList.size() > 0) {
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setBusinessId(bussinessNo);
            for (String fileNo : fileNoList) {
                if (StringUtil.isNotEmpty(fileNo)) {
                    file.setSeqNo(fileNo);
                    result += joyinFSMapper.updateByPrimaryKeySelective(file);
                }
            }
        }
        return result;
    }

    @Override
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UploadFile> fetchByBusinessCode(String businessCode) {
        if (StringUtil.isNotEmpty(businessCode)) {
            List<SysUploadFileInfo> files = joyinFSMapper.getUploadInfo(businessCode);
            List<UploadFile> uploadFiles = new ArrayList<>(files.size());
            files.forEach(file -> {
                UploadFile f = new UploadFile(null, file.getSeqNo(), new SafelyFile(file.getPath()));
                f.setFileName(file.getFileName());
                joyinDFSTemplate.fetch(file);
                uploadFiles.add(f);
            });
            return uploadFiles;
        }
        else {
            return new ArrayList<UploadFile>();
        }
    }
}
