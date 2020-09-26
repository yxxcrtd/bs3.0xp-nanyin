/*
 * 文件名：ValCurrentDepositInterest001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.dao.ValCurrentDepositInterest001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.valcurrentdepositinterest.dto.ValCurrentDepositInterest001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tools.util.ExcelTools;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * @author zhangzhao
 * @version 1.0 2019年1月30日
 * @see ValCurrentDepositInterest001Service
 * @since 1.0
 */
@Service
public class ValCurrentDepositInterest001Service extends CommonExportService<ValCurrentDepositInterest001Dto> {

//    @Autowired
//    private ValCurrentDepositInterest001Mapper valCurrentDepositInterest001Mapper;

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";


    /**
     * 分析日期
     * 
     * @param pageParam 查询条件
     * @param  
     * @return
     * @throws ParseException
     */
    public Map<String, Object> analyseDate(Map<String, Object> pageParam)
        throws ParseException {
    	String year = (String)pageParam.get("year");
        String quarter = (String)pageParam.get("quarter");
        

            if (DictCodeListExt1.季度_第一季度.getValue().equals(quarter)) {
            	
            	int	yearnew = Integer.parseInt(year) - 1 ;
            pageParam.put("queryStart", yearnew + "-12-21");
            pageParam.put("queryEnd", year + "-03-21");
            }
            else if (DictCodeListExt1.季度_第二季度.getValue().equals(quarter)) {
            	pageParam.put("queryStart", year + "-03-21" );
                pageParam.put("queryEnd", year + "-06-21");
            }
            else if (DictCodeListExt1.季度_第三季度.getValue().equals(quarter)) {
            	pageParam.put("queryStart", year + "-06-21" );
                pageParam.put("queryEnd", year + "-09-21");
            }
            else {
            	pageParam.put("queryStart", year + "-09-21" );
                pageParam.put("queryEnd", year + "-12-21");
            }
            return pageParam;
    }

    /**
	 * 查询数据
	 * <功能详细描述>
	 * @param params
	 * @return
     * @throws ParseException 
	 * @see
	 * @since 1.0
	 */
	public List<ValCurrentDepositInterest001Dto> selectList(Map<String, Object> pageParam)  throws Exception { 
	    return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValCurrentDepositInterest001/selectList", pageParam, ValCurrentDepositInterest001Dto.class);
	    
//		String queryStart = (String)pageParam.get("queryStart"); 
//		String queryEnd = (String)pageParam.get("queryEnd");
//		pageParam.put("queryStart", queryStart);
//		pageParam.put("queryEnd", queryEnd);
//		return valCurrentDepositInterest001Mapper.selectAll(pageParam);
	}

    /**
     * 导出信息
     * 
     * @param params
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params)
        throws Exception {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

    /**
     * 导出信息
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private ExportFileDto createExcel(Map<String, Object> params)
        throws Exception {
        // 报表日期
    	analyseDate(params);
        // 导出数据集
        List<ValCurrentDepositInterest001Dto> dtoList = selectList(params);
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        //画面.年+"年第"+画面.季度+"季度活期存款利息一览表"
        String fileName = params.get("year").toString() + "年第" + params.get("quarter").toString() + "季度活期存款利息一览表"+ ".xls";
        String tempName = "ValCurrentDepositInterest001.xls";
        String tplFlodPath = "accounadjustmanage/valcurrentdepositinterest/ValCurrentDepositInterest001.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year", params.get("year").toString());
        map.put("quarter", params.get("quarter").toString());
        map.put("dtoList", dtoList);
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {

            // 针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            // 文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            // 文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }

            ExcelTools.writeExcel(stream, tempName, map, outPath);
        }
        catch (IOException e) {
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                }
            }
        }
        return exportFileDto;
    }


}
