package com.joyintech.tams.disclosure.interf.interf.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {

    private static ChannelSftp sftp = new ChannelSftp();

    private static Session sshSession;

    public static final String ENCODE = System.getProperty("file.encoding");

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    /**
     * 连接sftp服务器
     * 
     * @param host 主机
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static void SFTPconnect(String user, String ip, int prot, String password)
        throws JSchException {
        // 接口 SFTP 服务配置
        JSch jsch = new JSch();
        sshSession = jsch.getSession(user, ip, prot);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp)channel;
    }

    /**
     * sftp将本地文件上传到远程服务器
     * 
     * @param local 本地文件路径
     * @param remote 远程文件路径
     */
    public static void putFile(String local, String remote) {
        try {
            sftp.put(local, remote);
            logger.info("文件上传成功");
        }
        catch (SftpException e) {
        	logger.error("error:",e);
            logger.info("文件上传失败");
        }
    }

    public static void rename(String oldpath, String newpath, String fileName) {
        try {
            try {
                sftp.cd(newpath);
            }
            catch (SftpException sException) {
                if (ChannelSftp.SSH_FX_NO_SUCH_FILE == sException.id) {
                    sftp.mkdir(newpath);
                }
            }
            sftp.rename(oldpath, newpath + File.separatorChar + fileName);
            logger.info("文件重命名成功");
        }
        catch (SftpException e) {
        	logger.error("error:",e);
            logger.info("文件重命名失败");
        }
    }

    /**
     * 列出目录下的文件
     * 
     * @param directory 要列出的目录 /opt/wind/20141215/SRC_BONDENCASHYIELD20141215.txt
     * @return
     * @throws SftpException
     */
    @SuppressWarnings("unchecked")
    public static Vector<String> getSFtpFile(String pathname)
        throws SftpException {
        Vector<String> vector = null;
        try {
            vector = sftp.ls(pathname);
        }
        catch (SftpException e) {
            logger.error("未找到文件:" + pathname);
        }
        return vector;
    }

    /**
     * 得到 SFTP 指定文件名 的 输入流 包装成 缓冲字符流
     * 
     * @param nowStr 当前时间
     * @param fileName
     * @return
     * @throws IOException
     * @throws SftpException
     */
    public static BufferedReader getSFtpBufferedReader(String path, String fileName) {

        // 文件路径
        // String remoteFilePath = ApplicationConfigInterf.windConfig.getFileDir() + nowStr + "/" +
        // fileName;
        String remoteFilePath = path + fileName;
        // log.info("下载文件==========:" + remoteFilePath );
        // SftpUtil.downloadSftp(path, fileName, "/tmp");
        // 输入流
        InputStream is;
        BufferedReader br;
        try {
            is = sftp.get(remoteFilePath);
            logger.info("输入流为:" + is);
            // 包装输入流
            InputStreamReader inr;
            try {
                inr = new InputStreamReader(is, ENCODE);
                br = new BufferedReader(inr);
                logger.info("获取流成功:" + br);
                // inr.close();
                return br;
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
            	logger.error("error:",e);
                logger.info("获取流不成功！");
            }
        }
        catch (SftpException e) {
            // TODO Auto-generated catch block
        	logger.error("error:",e);
            logger.info("数据文件：" + fileName + "不存在！");
        }
        return null;
    }

    public static void deleteSFTPFile(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        }
        catch (SftpException e) {

        }
    }

    /**
     * 关闭ftp
     * 
     * @throws IOException
     */
    public static void SFTPDisConnect() {
        if (sftp.isConnected()) {
            sftp.disconnect();
            logger.info("成功关闭连接SFTP！");
        }
        if (sshSession != null) {
            sshSession.disconnect();
        }
    }

    /**
     * 下载文件
     * 
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public static void downloadSftp(String directory, String downloadFile, String saveFile) {
        try {
            sftp.cd(directory);
            // File file=new File(saveFile);
            sftp.get(downloadFile, saveFile);
        }
        catch (Exception e) {
        	logger.error("error:",e);
        }
    }

    /**
     * 得到指定目录下的文件名
     * 
     * @param filename
     * @return
     * @throws SftpException
     * @author zhutao
     * @date 2016-05-30
     */
    public static List<String> getSftpFileName(String filename)
        throws SftpException {
        List<String> list = new ArrayList<String>();
        Vector<ChannelSftp.LsEntry> v = null;
        try {
            v = sftp.ls(filename);
            for (int i = 0; i < v.size(); i++ ) {
                ChannelSftp.LsEntry aa = v.elementAt(i);
                list.add(aa.getFilename());
            }
        }
        catch (SftpException e) {
            // log.error("未找到文件:"+pathname);
        }
        return list;
    }

    /**
     * @param 到指定的路径下面创建文件夹
     * @param dirName 指定要创建的文件夹
     * @param path 指定路径
     */
    public static void createMkdir(String path, String dirName) {
        try {
            sftp.cd(path);
            sftp.mkdir(dirName);
        }
        catch (SftpException e) {
            // logger.error("error:",e);
            System.out.println("未找到路劲");
        }
    }

    /**
     * 通过打开指定的文件夹路径的方式，看文件是否存在
     * 
     * @param dirName
     * @return
     */
    public static boolean dirIsHave(String dirName) {
        try {
            sftp.cd(dirName);
            return true;
        }
        catch (SftpException e) {
            // logger.error("error:",e);
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @throws Exception
     */
    public static void delete(String directory, String deleteFile)
        throws Exception {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        }
        catch (SftpException e) {
            System.out.println(e);
        }
    }
}
