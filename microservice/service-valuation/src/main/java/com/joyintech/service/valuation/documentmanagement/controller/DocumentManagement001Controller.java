/*
 * 文件名：ValDocumentManagement001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.documentmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.documentmanagement.dto.DocumentManagement001Dto;
import com.joyintech.service.valuation.documentmanagement.service.DocumentManagement001Service;
/**
 * 文档列表controller
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月24日
 * @see DocumentManagement001Controller
 * @since 1.0
 */
@RestController(value="serviceDocumentManagement001Controller")
@RequestMapping("/DocumentManagement001")
public class DocumentManagement001Controller extends BaseController {
	
	
   
	@Autowired
	private  DocumentManagement001Service DocumentManagement001Service;
	
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception
     */
	@PostMapping("findPage/{page}/{length}")
	public Page<DocumentManagement001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return DocumentManagement001Service.findPage(page, length, param);
	}
	
	
	/**
	 * 导出功能 <功能详细描述>
	 * 
	 * @param paraMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("/findPageout")
	public List<DocumentManagement001Dto> findPageout(@RequestBody Map<String, Object> params) throws Exception {
		return DocumentManagement001Service.findPageout(params);
	}
	
	
    
	/**
	 * 文件下载
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestBody Map<String, Object> params)
            throws Exception { 
           return DocumentManagement001Service.downloadFile(params);
    }
}
