package com.joyintech.tams.systemtools.dfs;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.common.oss.service.impl.MinioFileRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * ClassName OSSFileSystemMethod
 * Description OSS文件对象存储
 *
 * @Author ZY22350
 * @Date 2022/8/11 20:14
 * @Version 1.0.0
 **/
public class OSSFileSystemMethod implements JoyinFileSystemMethod{

    private static final Logger logger = LoggerFactory.getLogger(OSSFileSystemMethod.class);

    private static final String HTTP_STR = "http:";

    private static final String HTTPS_STR = "https:";

    private FileRemoteService minioFileRemoteService = SpringContextUtil.getBean(MinioFileRemoteService.class);
    @Override
    public void upload(SysUploadFileInfo uploadFile) {
        String filePath = uploadFile.getPath();
        String fileName = uploadFile.getFileName();
        //进行文本路径转化
        String newfilePath = filePath.replace(":\\","://");
        newfilePath = newfilePath.replace("\\","/");
        String basePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(StringUtil.isNotEmpty(basePath)){
            newfilePath = newfilePath.replace(basePath,"");
        }
        if(filePath.toLowerCase(Locale.CHINA).startsWith(HTTP_STR)||filePath.toLowerCase(Locale.CHINA).startsWith(HTTPS_STR)){

        }else{
            File f = new File(filePath);
            FileInputStream fis = null;
            try{
                fis = new FileInputStream(f);
                String remoteFilePath = minioFileRemoteService.uploadFile(newfilePath,fis);
                uploadFile.setPath(remoteFilePath);
            }catch(Exception e){
                logger.error("上传文件发生异常",e);
                throw new RuntimeException("上传文件异常",e);
            }finally{
                if(fis != null){
                    try{
                        fis.close();
                    }catch(IOException e){
                        logger.error("关闭文件流失败",e);
                    }
                }
            }
        }

    }

    @Override
    public UploadFile fecth(SysUploadFileInfo file) {
    	try {
	        String filePath = file.getPath();
	        if(filePath != null){
	            File f = null;
	            if(filePath.toLowerCase(Locale.CHINA).startsWith(HTTP_STR)||filePath.toLowerCase(Locale.CHINA).startsWith(HTTPS_STR)) {
	                String localFilePath = minioFileRemoteService.getSimpleFilePathName(filePath);
	                String basePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
	                if(localFilePath.startsWith("/")||basePath.endsWith("/") || basePath.endsWith("\\")|| localFilePath.startsWith("\\")){
	                    localFilePath = basePath+localFilePath;
	                }else{
	                    localFilePath = basePath + File.separator+localFilePath;
	                }
	                int begin = Math.max(localFilePath.lastIndexOf("/"), localFilePath.lastIndexOf("\\"));
	                File fileDir = new SafelyFile(localFilePath.substring(0,begin));
	                if(!fileDir.exists()){
	                    boolean ret = fileDir.mkdirs();
						if ( ret==false ) {
							throw new IOException("创建文件夹失败.");
						}
	                }
	                f = new SafelyFile(localFilePath);
	
	//                if(!f.exists()){
	                createLocalFile(f,filePath);
	//                }
	
	            }else{
	                f = new SafelyFile(filePath);
	                if(!f.exists()){
	                    int begin = Math.max(filePath.lastIndexOf("/"), filePath.lastIndexOf("\\"));
	                    File fileDir = new SafelyFile(filePath.substring(0,begin));
	                    if(!fileDir.exists()){
	                        boolean ret = fileDir.mkdirs();
	    					if ( ret==false ) {
	    						throw new IOException("创建文件夹失败.");
	    					}
	                    }
	                    String localFilePath = minioFileRemoteService.getSimpleFilePathName(filePath);
	                    String basePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
	                    localFilePath = localFilePath.replaceAll(basePath,"");
	                    String remoteFilePathStr = minioFileRemoteService.getRemoteFilePathName(localFilePath);
	                    createLocalFile(f,remoteFilePathStr);
	                }
	            }
	            UploadFile upfile = new UploadFile(null,file.getSeqNo(),f);
	            upfile.setFileName(file.getFileName());
	            return upfile;
	        }
	        return null;
    	}catch (Exception e) {
    		logger.error("上传文件失败",e);
    		return null;
    	}
    }
    /**
     * @Author ZY22350
     * @Description //下载本地文件处理
     * @Date 17:16 2022/8/12
     * @Param [f, filePath]
     * @return void
     **/
    private void createLocalFile(File f,String filePath){
        byte[] bytes = minioFileRemoteService.downloadFile(filePath);
        if(bytes!=null) {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(f);
                fos.write(bytes);
                fos.flush();
            } catch (Exception e) {
                logger.error("写入本地文件异常",e);
                throw new RuntimeException(e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        logger.error("关闭流异常", e);
                    }
                }
            }
        }

    }
}


