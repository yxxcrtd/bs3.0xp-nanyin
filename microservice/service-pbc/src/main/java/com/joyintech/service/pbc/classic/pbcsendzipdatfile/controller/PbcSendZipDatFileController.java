package com.joyintech.service.pbc.classic.pbcsendzipdatfile.controller;


import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.PbcSendZipDatFileService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Page<PbcMonthReport> searchPage(@RequestBody Map<String, Object> param) {
		Page<PbcMonthReport> page = pbcSendZipDatFileService.searchPage(param);
		return page;
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
	@PostMapping("searchPageBysubmitDate")
    public PbcMonthReport searchPageBysubmitDate(@RequestBody Map<String, Object> param) {
        return pbcSendZipDatFileService.searchPageBysubmitDate(param);
    }

	
}
