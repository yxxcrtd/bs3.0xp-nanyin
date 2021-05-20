package com.joyintech.tams.svreport.chbreport.prdprofit.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.PrdProfit;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportConstant;
import com.joyintech.tams.svreport.chbreport.prdprofit.dto.PrdProfitQuery001Dto;
import com.joyintech.tools.util.ExcelTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see PrdProfitQueryMService
 * @since 1.0
 */
@Service
public class PrdProfitQueryMService extends CommonExportService<ChbreportPrdInfoDto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(PrdProfitQueryMService.class);

    /**
     * 理财产品利润表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB_SERVICE_NAME + "/PrdProfitQuery/findPage", param,
                ChbreportPrdInfoDto.class);
    }

    /**
     * 导出理财产品利润表
     *
     * @param list
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<ChbreportExportDto> list) throws Exception {
        List<ChbreportExportFileDto> listFileDto = new ArrayList<ChbreportExportFileDto>();
        for (ChbreportExportDto dto : list) {
            listFileDto.add(this.createExcel(dto.getProdId(), dto.getProdName(), dto.getReportDate()));
        }

        if (!CollectionUtils.isEmpty(listFileDto)) {
            // 一条时单个文件导出
            if (listFileDto.size() == 1) {
                File file = new File(listFileDto.get(0).getFilePath());
                return ExportUtil.export(listFileDto.get(0).getFileName(), file, true);
            }
            // 超过一条时，用zip打包下载

            String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = FileNameUtil.createFileName("zip");
            File zipFile = new File(exportFilePath + File.separator + zipFileName);
            FileOutputStream stream = null;
            ZipOutputStream zipOut = null;
            try {
                stream = new FileOutputStream(zipFile);
                zipOut = new ZipOutputStream(stream);
                InputStream input = null;
                for (ChbreportExportFileDto item : listFileDto) {
                    try {
                        File file = new File(item.getFilePath());
                        input = new FileInputStream(file);
                        zipOut.putNextEntry(new ZipEntry(file.getName()));
                        int tmp = 0;
                        while ((tmp = input.read()) != -1) {
                            zipOut.write(tmp);
                        }
                        // 打包后删除生成的源文件
                        boolean rec = file.delete();
                        if (rec) {
                            logger.info("处理成功");
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    } finally {
                        if (input != null) {
                            input.close();
                        }
                    }
                }
                zipOut.flush();
            } catch (Exception e) {
                logger.error("", e);
            } finally {
                if (zipOut != null) {
                    zipOut.close();
                }
                if (stream != null) {
                    stream.close();
                }
            }
            return ExportUtil.export(zipFileName, zipFile, true);
        } else {
            return null;
        }
    }

    private ChbreportExportFileDto createExcel(String prodId, String prodName, String reportDate)
            throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(4);
        param.put("prodId", prodId);
        param.put("reportDate", reportDate);

        List<PrdProfit> prdProfitList = restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/PrdProfitQuery/selectPrdProfitList", param,
                PrdProfit.class);
        List<PrdProfitQuery001Dto> dtoList = new ArrayList<>();//getMergerList(prdProfitList);
        prdProfitList.forEach(prdProfit -> {
            PrdProfitQuery001Dto dto = new PrdProfitQuery001Dto(prdProfit.getSubjectName(), prdProfit.getCurrentAmt(), prdProfit.getCurrentYearAccumuAmt());
            dtoList.add(dto);
        });

        // 解决报表数据为空导出模板公式的问题
        if (dtoList.isEmpty()) {
            PrdProfitQuery001Dto emptyDto = new PrdProfitQuery001Dto();
            emptyDto.setThisTermAmt(null);
            emptyDto.setLastTermAmt(null);
            dtoList.add(emptyDto);
        }

        // 设置文件信息
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = prodId + "_利润表_" + reportDate + ".xls";
        String outPath = exportFilePath + File.separator + fileName;
        String exceltemplate = "prdprofit/PrdProfit";

        ChbreportExportFileDto exportFileDto = new ChbreportExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        Map<String, Object> map = new HashMap<>();
        map.put("title", String.format("%s年%s月_利润表_%s_月报", reportDate.substring(0, 4), reportDate.substring(5, 7), prodName));

        map.put("org",CacheUtil.getSysParam(ChbInvestReportConstant.财务报表_编制单位));
        map.put("reportDate", DateUtil.format(DateUtil.platDateFromCache()));
        map.put("prodName", prodName);
        map.put("year", reportDate.substring(0, 4));
        map.put("dtoList", dtoList);
        TisUtil.createFile(map, exceltemplate, fileName);

        return exportFileDto;
    }
}
