/*
 * 文件名：ValDocumentManagement001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.documentmanagement.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.documentmanagement.dto.DocumentManagement001Dto;
import com.joyintech.tams.valuation.documentmanagement.service.DocumentManagement001Service;
import com.joyintech.tams.constant.DictCode;
/**
 * 文档列表controller
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月24日
 * @see DocumentManagement001Controller
 * @since 1.0
 */
@Controller("valDocumentManagement001Controller")
@PageId("DocumentManagement001")
@RequestMapping("/valuation/documentmanagement/DocumentManagement001")
public class DocumentManagement001Controller extends BaseController {
	
	
   
	@Autowired
	private  DocumentManagement001Service DocumentManagement001Service;
	@Override
	public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.文件类型);
		return super.init(model);
	}
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
	@RequestMapping("findPage")
	@ResponseBody
	public Page<DocumentManagement001Dto> findPage() throws Exception {
		Map<String, Object> params = RequestUtil.getPageParam();
		return DocumentManagement001Service.findPage(params);
	}
	
	
	/**
	 * 导出
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()throws Exception {
            String datas = this.getParam("datas");
            Map<String, Object> paraMap = RequestUtil.getPageParam();
            List<DocumentManagement001Dto> listdto;
            if (datas == null) {
            listdto = (List<DocumentManagement001Dto>)DocumentManagement001Service.findPageout(paraMap);
            }
            else {
                listdto = JsonUtils.json2List(datas, DocumentManagement001Dto.class);
            }
            File outFile = DocumentManagement001Service.makeFile(
                "/com/joyintech/tams/valuation/documentmanagement/config/export_documentmanagement001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        }
    
	/**
	 * 文件下载
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request)
            throws Exception { 
           return DocumentManagement001Service.downloadFile(request);
    }
}
