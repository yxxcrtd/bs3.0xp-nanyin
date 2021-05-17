/*
 * 文件名：DocumentManagement002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.documentmanagement.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.json.JsonSanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.listener.FileUploadListener;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.documentmanagement.service.DocumentManagement002Service;

/**
 * 文档列表新建 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年10月25日
 * @see DocumentManagement002Controller
 * @since 1.0
 */
@Controller("valDocumentManagement002")
@PageId("DocumentManagement002")
@RequestMapping("/valuation/documentmanagement/DocumentManagement002")
public class DocumentManagement002Controller extends BaseController {

	@Autowired
	private DocumentManagement002Service documentManagement002Service;
	
	@Autowired
	private ProgressBarService progressBarService;

	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.文件类型);
		return super.init(model);
	}

	/**
	 * 文件上传
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param model
	 *            ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public AjaxResult uploadFile(HttpServletRequest request,HttpServletResponse response, ModelMap model)
			throws Exception {
		 AjaxResult res = new AjaxResult();
		 String fileType = request.getParameter("fileType");
		 if(StringUtil.isNotEmpty(fileType)){			 
				 int num = documentManagement002Service.insertFileTransferInfo(request);
		         if(num>0){
		             res = new AjaxResult(getMessage("VAL_EXCELIMP_E_0020"), MessageType.提示消息);
		         }else {
		             String msg = "上传信息入库失败";
		             res = new AjaxResult(msg, MessageType.错误消息);
		         } 
		 }else {
			    String msg = "文档类型不能为空,上传失败！！！";
				res =  new AjaxResult(msg, MessageType.错误消息);
				progressBarService.updatePercent("DocumentManagement002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
				return res;
		 }
			
			return res;
	}
	
	
	 /**
     * 文件上传
     * 
     * @param request 请求
     * @param response 响应
     * @param model ModelAndView
     * @return 保存文件地址
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "fileUpload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model)
        throws Exception {
        // 画面ID
        String pageId = StringUtil.trim(request.getParameter(GlobalConstant.PAGE_ID));
        // 初始化进度条
        progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, 0D, true);
        String uploadFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        File fileMik = new File(uploadFilePath);
        if (!fileMik.exists()) {
            fileMik.mkdirs();
        }
        // 返回结果
        List<UploadFile> result = new ArrayList<>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = StringUtil.trim(file.getOriginalFilename());
                    // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                    if (StringUtil.isNotEmpty(myFileName)) {
                        // 定义上传路径
                        String prexFileName = FileNameUtil.getBaseName(myFileName);
                        String path = FileNameUtil.createUploadFilePath(prexFileName)
                                      + GlobalConstant.UNDER_LINE + FileNameUtil.createFileName(
                                          FileNameUtil.getExtension(myFileName));
                        File localFile = new File(path);
                        File dir = localFile.getParentFile();
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        // file.transferTo(localFile);
                        // FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(localFile));
                        //  copyFile(file.getInputStream(), new FileOutputStream(localFile), file.getSize(), pageId);
                        
                        UploadFile fileInfo = new UploadFile(myFileName, IDGenerator.generate(),
                            localFile);
                        excuteListener(fileInfo);
                        fileInfo.setFileName(localFile.getPath());
                        result.add(fileInfo);
                    }
                }
            }
        }
//        progressBarService.updatePercent(pageId, ProgressBarService.FILE_UPLOAD_ID, 0.5D, true);
        return JsonSanitizer.sanitize(JsonUtils.object2Json(result));
    }
	    
	    /**
	     * 执行监听器
	     * 
	     * @param fileInfo 上传文件信息
	     */
	    private void excuteListener(UploadFile fileInfo) {
	        // 当前操作画面ID
	        Map<String, FileUploadListener> listeners = SpringContextUtil.getBeans(
	            FileUploadListener.class);
	        if (null != listeners) {
	            listeners.forEach((key, listener) -> {
	                listener.uploaded(fileInfo);
	            });
	        }
	    }
}
