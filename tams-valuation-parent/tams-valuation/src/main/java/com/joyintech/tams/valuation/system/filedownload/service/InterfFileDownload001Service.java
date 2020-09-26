package com.joyintech.tams.valuation.system.filedownload.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.service.send.dao.PinAnSendNetValueInterfMapper;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValRecvServerInfoEntity;
import com.joyintech.tams.valuation.common.interf.base.util.FtpUtil;
import com.joyintech.tams.valuation.common.interf.base.util.SearchFilePath;
import com.joyintech.tams.valuation.common.interf.base.util.SftpUtil;

/**
 * 系统文件上传service
 * 
 * @author Administrator
 *
 */
@Service
public class InterfFileDownload001Service {
	@Autowired
	PinAnSendNetValueInterfMapper pinAnSendNetValueInterfMapper;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(InterfFileDownload001Service.class);
	/**
     * 设置下载响应头
     *
     * @param response
     * @return 
     * @author 
     */

    public HttpServletResponse setDownloadResponse(HttpServletResponse response, String downloadName) {
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/zip");// 定义输出类型
        response.setHeader("Content-Disposition", "attachment;fileName*=UTF-8''"+ downloadName);
        return response;
    }
    /**
     * 导出估值表excel文件
     * 
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public  ResponseEntity<byte[]> exportFile(Map<String, Object> params) throws Exception {
        ValRecvServerInfoEntity entity=new ValRecvServerInfoEntity();
        entity.setRsStatus("R");
        entity.setSystemID("ZG");
        ValRecvServerInfoEntity info=pinAnSendNetValueInterfMapper.querySftpServer(entity);
        if (DictCodeInterfList.接口服务器类型_FTP.getValue().equals(info.getServerType())) {
            this.ftpService(info, params);
        } else if (DictCodeInterfList.接口服务器类型_SFTP.getValue().equals(info.getServerType())) {
            this.sftpService(info, params);
        } else if (DictCodeInterfList.接口服务器类型_SFTP_IDENTITY.getValue().equals(info.getServerType())) {
            this.sftpServiceDoRecvId(info, params);
        }else if(DictCodeInterfList.接口服务器类型_LOCALHOST.getValue().equals(info.getServerType())){
            this.localService(info, params);
        }

        String executeDate=DateUtil.formatDate(String.valueOf(params.get("executeDate")));
        //先清理本地路径文件
        String localDir = info.getLocalPath() + File.separator + info.getSystemID()+ File.separator+ DateUtil.formatDate(executeDate);
        //-- 将多文件循环写入压缩包
        return this.zipFileDownLoad(loadfiles(localDir),localDir,executeDate);
       
    }
    /**
     *罗列文件夹下面的文件
     * 
     * @param entity
     * @throws Exception
     */
    private  List<String> loadfiles(String localDir){
        List<String> filePathsTem = new ArrayList<String>(); 
        File inputFile = new File(localDir);  //根据文件路径创建文件  
        if(inputFile.exists()) { //判断文件是否存在  
            if (inputFile.isDirectory()){//判断是否属于文件，还是文件夹  
                File[] files = inputFile.listFiles();  
                for (File fileTem:files) {  
                    filePathsTem.add(fileTem.toString());
                }  
            }
        }
        return filePathsTem;
        
    }
    /**
     * 通过ftp密码模式下载文件
     * 
     * @param entity
     * @throws Exception
     */
    public void ftpService(ValRecvServerInfoEntity entity,Map<String, Object> params)throws Exception {
        String executeDate=String.valueOf(params.get("executeDate"));
        //先清理本地路径文件
        String localDir = entity.getLocalPath() + File.separator + entity.getSystemID()+ File.separator+ DateUtil.formatDate(executeDate);
        File localDirFile = new File(localDir);
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        
        FtpUtil ftpUtil = new FtpUtil();
        ftpUtil.login(entity.getUserName(), entity.getPassword(), entity.getServerIp(),entity.getServerPort());
  
        //远程路径
        String remotePath = entity.getRemotePath() + "/" + DateUtil.formatDate(executeDate);
        //全部下载全部下载
        List<String> fileNames = ftpUtil.listAllFileName(remotePath);
        fileNames.forEach(fileName->{
            String remoteFile = remotePath + "/" + fileName;
            ftpUtil.getFile(remoteFile, localDir);
        });
        ftpUtil.close();
    }

    /**
     * 通过sftp下载文件
     * 
     * @param entity
     * @param fileNamePart
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void sftpService(ValRecvServerInfoEntity entity,Map<String, Object> params)throws Exception {
        String executeDate=String.valueOf(params.get("executeDate"));
        // 本地路径
        String localDir = entity.getLocalPath() + File.separator + entity.getSystemID()+ File.separator+ DateUtil.formatDate(executeDate);
        File localDirFile = new File(localDir);
        // 远程路径
        String remotePath = entity.getRemotePath() + "/" + DateUtil.formatDate(executeDate);
        // 删除本地路径的原始文件
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        SftpUtil ftpUtil = new SftpUtil();
        try {
            ftpUtil.login(entity.getUserName(), entity.getPassword(), entity.getServerIp(),
                entity.getServerPort());
        }catch (Exception e) {
            throw new Exception("登录远程服务器失败！");
        }

        // 如果是空的列表，全部下载
        List<String> fileNames = ftpUtil.getSFtpFile(remotePath);
        if (fileNames.isEmpty()) {
            return;
        }
        fileNames.forEach(fileName -> {
            String remoteFile = remotePath + "/" + fileName;
            try {
                ftpUtil.getFile(remoteFile, localDir);
            }
            catch (Exception e) {
                logger.error("下载文件失败：" + fileName, e);
            }
        });
        ftpUtil.close();
    }

    /**
     * 通过sftp秘钥模式
     * 
     * @param entity
     * @param fileNamePart
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void sftpServiceDoRecvId(ValRecvServerInfoEntity entity,Map<String, Object> params)throws Exception {
        String executeDate=String.valueOf(params.get("executeDate"));
        // 本地路径
        String localDir = entity.getLocalPath() + File.separator + entity.getSystemID()+ File.separator+ DateUtil.formatDate(executeDate);
        File localDirFile = new File(localDir);
        // 远程路径
        String remotePath = entity.getRemotePath() + "/" + DateUtil.formatDate(executeDate);
        // 删除本地路径的原始文件
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        SftpUtil ftpUtil = new SftpUtil();
        try {
            ftpUtil.loginById(entity.getUserName(), entity.getPassword(), entity.getServerIp(),
                entity.getServerPort());
        }catch (Exception e) {
            throw new Exception("登录远程服务器失败！");
        }
        List<String> fileNames = ftpUtil.getSFtpFile(remotePath);
        if (fileNames.isEmpty()) {
            return;
        }
        fileNames.forEach(fileName -> {
            String remoteFile = remotePath + "/" + fileName;
            try {
                ftpUtil.getFile(remoteFile, localDir);
            }
            catch (Exception e) {
                logger.error("下载文件失败：" + fileName, e);
            }
        });
        ftpUtil.close();
    }
    /**
     * 本地文件接收
     * 
     * @param entity
     * @throws Exception
     */
    public void localService(ValRecvServerInfoEntity entity,Map<String, Object> params)throws Exception {
        String executeDate=String.valueOf(params.get("executeDate"));
        String localDir = entity.getLocalPath() + File.separator + entity.getSystemID()+ File.separator+ DateUtil.formatDate(executeDate);
        File localDirFile = new File(localDir);
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        // 远程路径
        String remotePath = entity.getRemotePath() + File.separator + DateUtil.formatDate(executeDate);
        // 如果是空的列表，全部下载
        List<File> files = SearchFilePath.searchAllFile(remotePath,false);
        files.forEach(file->{
            String remoteFile = remotePath + File.separator + file.getName();
            SearchFilePath.copyFile(remoteFile, localDir);
        });
    }


    private void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            boolean rec =myFilePath.delete(); // 删除空文件夹
            if(rec){
                logger.info(" 删除空文件夹成功");
            }
        }
        catch (Exception e) {
             logger.error("删除文件夹失败",e);
        }
    }

    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    private boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++ ) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                }
                else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile()) {
                   boolean rec =  temp.delete();
                   if(rec){
                       logger.info("空文件夹删除成功");
                   }
                }
                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                    delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                    flag = true;
                }
            }
        }
        return flag;
    }
    /**
     * 压缩文件
     * @param filePaths 需要压缩的文件列表
     * @param exportFilePath  压缩文件存放路径 
     * @param zipFileName  压缩文件名称
     */
    
    private ResponseEntity<byte[]> zipFileDownLoad(List<String> filePaths,String exportFilePath,String zipFileName)
        throws IOException {

        // 如果当前画面的模板不存在，则下载所有模板
        zipFileName =zipFileName+".zip";
        File zipFile = new File(exportFilePath + File.separator + zipFileName);
        if(zipFile.exists()){
            zipFile.delete();
        }
        FileOutputStream stream = null;
        ZipOutputStream zipOut = null;
        BufferedInputStream bis = null;
        //循环读取文件路径集合，获取每一个文件的路径  
        try {
            stream = new FileOutputStream(zipFile);
            zipOut =new ZipOutputStream(stream);
            for(String filePath : filePaths){  
                File inputFile = new File(filePath);  //根据文件路径创建文件  
                if(inputFile.exists()) { //判断文件是否存在  
                    if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹  
                        //创建输入流读取文件  
                        bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入zip内，即将文件进行打包  
                        zipOut.putNextEntry(new ZipEntry(inputFile.getName()));  

                        //写入文件的方法，同上                  
                        int size = 0;  
                        byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                        while ((size = bis.read(buffer)) > 0) {  
                            zipOut.write(buffer, 0, size);  
                        }  
                        //关闭输入输出流  
                        zipOut.closeEntry();  
                        bis.close();

                    }
                }  
            }  
            zipOut.flush();
        }
        catch (IOException e) {
            logger.error("", e);
        }
        finally {
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zipOut != null) {
                try {
                    zipOut.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ExportUtil.export(zipFileName, zipFile, true);
    } 
}
