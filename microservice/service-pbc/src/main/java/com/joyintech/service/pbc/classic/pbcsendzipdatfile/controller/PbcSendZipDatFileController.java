package com.joyintech.service.pbc.classic.pbcsendzipdatfile.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.PbcSendZipDatFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFileController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcSendZipDatFile")
public class PbcSendZipDatFileController {
    /**
     * service
     */
    @Autowired
    private PbcSendZipDatFileService pbcSendZipDatFileService;
 
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<PbcSendZipDatFile> searchPage(@RequestBody Map<String, Object> param) {
		Page<PbcSendZipDatFile> page = pbcSendZipDatFileService.searchPage(param);
		return page;
	}

	/**
	 * 创建压缩文件
	 * @param param 页面参数
	 * @return 压缩文件之地
	 */
	@PostMapping("createZipFile")
	public AjaxResult createZipFile(@RequestBody Map<String, Object> param) {
		return pbcSendZipDatFileService.createZipFile(param);
	}


	
}
