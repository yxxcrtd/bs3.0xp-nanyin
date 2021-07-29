package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.service;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFileMService
 * @since 1.0
 */
@Service
public class PbcSendZipDatFileMService extends BaseService<PbcSendZipDatFile>{

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcSendZipDatFileMService.class);

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<PbcSendZipDatFile> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/findPage",param,
				PbcSendZipDatFile.class);
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public PbcSendZipDatFile searchPageBysubmitDate(Map<String, Object> param) {
        return restTemplate.postForObject(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/searchPageBysubmitDate",param,
				PbcSendZipDatFile.class);
    }

	/**
	 *
	 * 业务导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AjaxResult checkBaseInfoSubDat(String fileName) {
		Map<String,Object> param = new HashMap<>();
		param.put("dataList", ExcelUtil.getDataList1(fileName));
		return restTemplate.postForObject(
				SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubDat/checkBaseInfoSubDat?fileName="+fileName,param,
				AjaxResult.class);

	}


	/**
	 * 导入数据文件
	 *
	 * @param fileName 导入的文件名
	 * @return 导入结果，包含验证内容，如果验证失败，返回message
	 */
	public List<ExcelValidDto> importBaseInfoSubDat(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataList", ExcelUtil.getDataList1(fileName));
		param.put("sheetName", ExcelUtil.getDataList1(fileName));
		return restTemplate.postForList(
				SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubDat/importBaseInfoSubDat",
				param,
				ExcelValidDto.class);
	}


}
