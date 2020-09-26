package com.joyintech.tams.report.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.TemplateName;
import com.joyintech.tams.systemtools.util.CommonUtil;


/**
 * 文件上传共通Controller
 * 
 * @author 刘溶容
 * @version 1.0 2016年10月24日
 * @see ChbFileUploadController
 * @since 1.0
 */
@Controller
@RequestMapping("/ChbFileUpload")
public class ChbFileUploadController {    

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbFileUploadController.class);

    /**
     * 获取支持文件的类型
     * @return 支持文件类型
     */
    private String getExtFile() {
        return StringUtil.trim(StringUtil.defaultString(
            RequestUtil.getCurrentRequest().getParameter("exts"), "xls|xlsx"));
    }

    /**
     * 文件上传公共画面
     * 
     * @param model Model
     * @return 画面路径
     */
    @RequestMapping("init")
    private String init(Model model) {
        // 上传文件控件的类型
        String type = StringUtil.trim(StringUtil.defaultString(
            RequestUtil.getCurrentRequest().getParameter("type"), "drag"));
        // 支持文件类型
        String exts = getExtFile();
        String legend = StringUtil.EMPTY;
        boolean hideTpl = Boolean.valueOf(StringUtil.trim(StringUtil.defaultString(
            RequestUtil.getCurrentRequest().getParameter("hideTpl"), "false")));
        switch (type) {
            case "singleImage":
                legend = "普通图片上传";
                exts = "jpg|jpeg|png|gif|bmp";
                break;
            case "mutiImage":
                legend = "上传多张图片";
                exts = "jpg|jpeg|png|gif|bmp";
                break;
            case "allowFile":
                legend = "只允许上传指定类型的文件";
                break;
            case "allowSize":
                legend = "设定文件大小限制";
                break;
            case "drag":
                legend = "拖拽上传";
                break;
            case "list":
                legend = "多文件列表";
                break;
            default:
                break;
        }
        model.addAttribute("type", type);
        model.addAttribute("hideTpl", hideTpl);
        model.addAttribute("legend", legend);
        model.addAttribute("exts", exts);
        return "report/common/ChbFileUpload";
    }

    /**
     * 文件上传
     * 
     * @param request 请求
     * @param response 响应
     * @param model ModelAndView
     * @return 保存文件地址
     * @throws IOException 
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/uploadFile", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                                          ModelMap model) throws IOException {
        // String filePath = request.getParameter("path");
        String uploadFilePath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_UPLOAD_PATH);
        // filePath = uploadFilePath;
        // if (!StringUtils.isEmpty(filePath)) {
        // + File.separator + filePath;
        // }
        File fileMik = new File(uploadFilePath);
        if (!fileMik.exists()) {
            fileMik.mkdir();
        }
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            List<String> fileNames = new ArrayList<>();
            while (iter.hasNext()) {
                // 取得上传文件
//                MultipartFile file = multiRequest.getFile(iter.next());
                MultipartFile file = CommonUtil.getFileFromRequest(multiRequest, iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (!"".equals(myFileName.trim())) {
                        // 定义上传路径
                        String prexFileName = myFileName.substring(0,
                            myFileName.lastIndexOf(GlobalConstant.POINT));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String path = uploadFilePath + File.separator + prexFileName
                                      + GlobalConstant.UNDER_LINE + sdf.format(new Date())
                                      + GlobalConstant.POINT
                                      + myFileName.substring(
                                          myFileName.lastIndexOf(GlobalConstant.POINT) + 1,
                                          myFileName.length());
                        File localFile = new File(path);
                        file.transferTo(localFile);
                        fileNames.add(localFile.getName());
                    }
                }
            }
            Map<String, String> resultMap = new HashMap<String, String>();
            // resultMap.put("fileIds", ids.substring(1, ids.length() - 1));
            resultMap.put("fileName", StringUtil.joinString(fileNames, GlobalConstant.COMMA));
            return JsonUtils.object2Json(resultMap);
        }
        return null;
    }

    /**
     * 模板文件下载
     * 
     * @return 将系统所有模板文件进行打包下载
     */
    @RequestMapping(value = "/downloadTemplate")
    public ResponseEntity<byte[]> download() {
        // 获取“/WEB-INF/tpl”的绝对路径
        String appRealPath = RequestUtil.getRealPath("/WEB-INF/tpl");
        // 支持文件类型
        String exts = getExtFile();
        String pageId = RequestUtil.getCurrentRequest().getParameter(GlobalConstant.PAGE_ID);
        TemplateName tempName = getTemplateFileNameByPageId(pageId, exts);
        // 如果当前画面的模板存在，则下载对应模板
        if(null != tempName) {
            File tplFile = new File(appRealPath + File.separator + tempName.getFileName());
            String targetFileName = tempName.name() + GlobalConstant.POINT + FileNameUtil.getFileExtName(tempName.getFileName());
            return ExportUtil.export(targetFileName, tplFile, false);
        }
        // 如果当前画面的模板不存在，则下载所有模板
        String exportFilePath = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String zipFileName = FileNameUtil.createFileName("zip");
        File zipFile = new File(exportFilePath + File.separator + zipFileName);
        FileOutputStream fos=null;
        ZipOutputStream zipOut=null;
        InputStream input = null;
        try{
        fos=new FileOutputStream(zipFile);
        zipOut = new ZipOutputStream(fos);

        File tmpDir = new File(appRealPath);
        if (tmpDir.isDirectory()) {
            File[] files = tmpDir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                input = new FileInputStream(files[i]);
                zipOut.putNextEntry(
                    new ZipEntry(tmpDir.getName() + File.separator + getTemplateNameByFileName(files[i].getName())));
                int tmp = 0;
                while ((tmp = input.read()) != -1) {
                    zipOut.write(tmp);
                }
                input.close();
            }
        }
        zipOut.flush();
        fos.close();
        zipOut.close();
        }
        catch(IOException e){
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        finally{
         if(input!=null) {
             try {
                 input.close();
             }
             catch (IOException e) {
//                 e.printStackTrace();
                 logger.error(e.getMessage(),e);
             }
         }
         if(fos!=null)  {
             try {
             fos.close();
             }
             catch (IOException e) {
//                 e.printStackTrace();
                 logger.error(e.getMessage(),e);
             }
         } 
         if (zipOut!=null){
             try{zipOut.close();}
             catch (IOException e) {
//                 e.printStackTrace();
                 logger.error(e.getMessage(),e);
             }
         }
       }
        return ExportUtil.export(zipFileName, zipFile, true);
    }
    
    /**
     * 根据画面ID获取对应的模板名
     * @param pageId 画面ID
     * @param exts 模板后缀
     * @return TemplateName 模板文件名的枚举类
     */
    private TemplateName getTemplateFileNameByPageId(String pageId, String exts) {
        if(null != pageId) {
            List<String> extList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE, exts);
            TemplateName[] tempArray = TemplateName.values();
            for(TemplateName temp : tempArray) {
                if(StringUtil.equals(temp.getPageId(), pageId)) {
                    if(StringUtil.isEmpty(exts) || extList.contains(FileNameUtil.getFileExtName(temp.getFileName()))) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 根据画面ID获取对应的模板名
     * @param fileName 画面ID
     * @return String 模板名
     */
    private String getTemplateNameByFileName(String fileName) {
        TemplateName[] tempArray = TemplateName.values();
        for(TemplateName temp : tempArray) {
            if(StringUtil.equals(temp.getFileName(), fileName)) {
                return temp.name();
            }
        }
        return fileName;
    }
}
