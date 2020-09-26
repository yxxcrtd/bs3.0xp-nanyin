package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.controller;

import java.io.*;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.service.ExecuteService;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.common.utils.SftpTools;

/**
 * 估值表、余额表对账，文件下载controller
 *
 * @author yangjian
 * @version 1.0 2018年11月19日
 * @see FileDownloadController
 * @since 1.0
 */
@Controller("ValFileDownloadController") // 2.1.2.9 同名 com.joyintech.framework.controller.FileDownloadController
@RequestMapping("/fileProcess")
public class FileDownloadController extends BaseController {

    /**
     * 文件夹分隔符
     */
    private static final String SEPARATOR_NOW = "/";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * executeService
     */
    @Autowired
    private ExecuteService executeService;

    /**
     * 文件下载
     *
     * @param response 请求参数
     * @throws IOException 异常
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public void downloadFile(HttpServletResponse response)
        throws IOException {
        Map<String, Object> paramMap = RequestUtil.getPageParam();
        String path = (String)paramMap.get("path");
        File file = new File(path);
        String filename = file.getName();
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
            // 去掉filename的时间戳 add by 何阳阳 2017/01/04 start
            int index = filename.lastIndexOf("_");
            String[] names = this.splitTwo(filename, ".");
            if (index > 0 && names.length == 2) {
                // if(filename.substring(index + 1).length() == 18 + names[1].length()) {
                filename = filename.substring(0, index) + "." + names[1];
                // }
            }
            // add by 何阳阳 2017/01/04 end
            response.addHeader("Content-Disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (toClient != null) {
                toClient.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

    }

    /**
     * 文件下载 - 【针对非本方估值表余额表文件，即他方文件和对比文件】
     *
     * @param response 请求参数
     * @throws IOException 异常
     */
    @RequestMapping(value = "/download2")
    @ResponseBody
    public void downloadFile2(HttpServletResponse response)
        throws IOException {
        Map<String, Object> paramMap = RequestUtil.getPageParam();

        // 获取文件路径
        String path = (String)paramMap.get("path");

        String fileNameFromPath = path.substring(path.lastIndexOf("/") + 1);

        // 从ftp中拿取文件到服务器
        SftpTools sftpTools = new SftpTools();
        String remoteFile = path;// ftp存放路径（包含文件名称）
        String localdir =
            CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE") + SEPARATOR_NOW + "COMPAREDOWNLOAD";// 从ftp拿到该文件，放再服务器存放路径。

        try {
            String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");// 主机IP
            Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));// 主机端口
            String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");// 主机用户名
            String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");// 主机密码或秘钥地址
            String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式

            sftpTools.login(username, password, ip, port, sftpLoginType);// sftp登录
            sftpTools.getFile(null, remoteFile, localdir);// 从ftp中拿到文件到服务器中
        }
        catch (Exception e) {
            logger.error("产品估值表余额表对账文件获取失败", e);
        }
        finally {
            try {
                sftpTools.close();// 关闭登录
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 获取存放在服务器中的路径
        path = localdir + SEPARATOR_NOW + fileNameFromPath;

        // 文件下载到客户端
        File file = new File(path);
        // 从ftp下载到服务器后，判断该文件是否存在
        if (file.isFile() && file.exists()) {
            // 获取文件名称
            String fileName = file.getName();
            int index = fileName.lastIndexOf(".");
            String[] names = this.splitTwo(fileName, ".");
            if (index > 0 && names.length == 2) {
                fileName = fileName.substring(0, index) + "." + names[1];
            }

            // 下载到客户端
            InputStream fis = null;
            byte[] buffer = null;
            OutputStream toClient = null;
            try {
                fis = new BufferedInputStream(new FileInputStream(path));
                buffer = new byte[fis.available()];
                fis.read(buffer);
                response.reset();

                response.addHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
                response.addHeader("Content-Length", "" + file.length());
                toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                if (buffer != null) {
                    toClient.write(buffer);
                    toClient.flush();
                }
            }
            catch (IOException e) {
                throw e;
            }
            finally {
                if (toClient != null) {
                    try {
                        toClient.close();
                    } catch (Exception e) {
                        logger.error("", e);
                    }

                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }
            }

            // 删除存放在服务器上的临时文件
            file.delete();
        }
    }

    /**
     * 文件下载 - 【针对本方估值表余额表下载】
     *
     * @param response 请求参数
     * @throws IOException 异常
     */
    @RequestMapping(value = "/download2ForMy")
    @ResponseBody
    public void download2ForMy(HttpServletResponse response)
        throws IOException {
        Map<String, Object> paramMap = RequestUtil.getPageParam();

        // 产品id
        String prodId = (String)paramMap.get("prodId");
        // 时间字符串
        String valueDate = (String)paramMap.get("valueDate");
        // 估值表余额表标识
        String valBalFlag = (String)paramMap.get("valBalFlag");

        //参数不为空
        if (StringUtil.isNotEmpty(prodId) && StringUtil.isNotEmpty(valueDate) && StringUtil.isNotEmpty(valBalFlag)) {
            // 根据产品代码查询账套id
            ValAssetbook001Dto bookdto = executeService.getBooksetInfo(prodId);

            //时间格式化
            Date valDatenow = executeService.StringToDate(valueDate);

            // 在服务器上生成估值表或余额表，并且返回文件生成信息（针对本方估值表余额表文件）
            ExportFileDto exportFileDto = executeService.getMyFileInfo(bookdto.getBooksetId(),
                DateUtil.format(valDatenow, "yyyy-MM-dd"), valBalFlag);

            // 文件服务器路径
            String path = executeService.replaceStringForPath(exportFileDto.getFilePath());
            // 文件下载到客户端
            File file = new File(path);
            // 从ftp下载到服务器后，判断该文件是否存在
            if (file.isFile() && file.exists()) {
                // 获取文件名称
                // String fileName = file.getName();
                String fileName = exportFileDto.getFileName();
                //                int index = fileName.lastIndexOf("_");
                //                String[] names = this.splitTwo(fileName, ".");
                //                if (index > 0 && names.length == 2) {
                //                    fileName = fileName.substring(0, index) + "." + names[1];
                //                }

                // 下载到客户端
                InputStream fis = null;
                OutputStream toClient = null;
                try {
                    fis = new BufferedInputStream(new FileInputStream(path));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    response.reset();

                    response.addHeader("Content-Disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
                    response.addHeader("Content-Length", "" + file.length());
                    toClient = new BufferedOutputStream(response.getOutputStream());
                    response.setContentType("application/octet-stream");
                    toClient.write(buffer);
                    toClient.flush();
                }
                catch (IOException e) {
                    throw e;
                }
                finally {
                    if (toClient != null) {
                        try {
                            toClient.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        }
                        catch (IOException e) {
                        	e.printStackTrace();
                        }
                    }
                }
                // 删除存放在服务器上的临时文件
                file.delete();
            }
        }
    }

    /**
     * 文件下载，若参数带文件名，则使用参数文件名，否则用下载文件原文件名
     *
     * @param response 请求参数
     * @throws IOException 异常
     */
    @RequestMapping(value = "/download1")
    @ResponseBody
    public void downloadFile1(HttpServletResponse response)
        throws IOException {
        Map<String, Object> paramMap = RequestUtil.getPageParam();
        String path = (String)paramMap.get("path");
        String fileName = (String)paramMap.get("fileName");
        File file = new File(path);

        InputStream fis = null;
        OutputStream toClient = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            if (StringUtil.isEmpty(fileName)) {
                fileName = file.getName();
                int index = fileName.lastIndexOf("_");
                String[] names = this.splitTwo(fileName, ".");
                if (index > 0 && names.length == 2) {
                    fileName = fileName.substring(0, index) + "." + names[1];
                }
            }
            response.addHeader("Content-Disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (toClient != null) {
                try {
                    toClient.close();
                }
                catch (IOException e) {
                	e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }

    }

    /**
     * 将字符串根据指定字符串分隔为两个字符串
     *
     * @param old     源字符串
     * @param splitor 分隔符
     * @return 新的字符串数组
     */
    public String[] splitTwo(String old, String splitor) {
        String[] result = new String[2];
        if (StringUtil.isNotEmpty(old)) {
            int index = old.indexOf(splitor);
            if (index > -1) {
                result[0] = old.substring(0, index);
                result[1] = old.substring(index + splitor.length());
            }
            else {
                result[0] = old;
                result[1] = "";
            }
        }
        return result;
    }

}
