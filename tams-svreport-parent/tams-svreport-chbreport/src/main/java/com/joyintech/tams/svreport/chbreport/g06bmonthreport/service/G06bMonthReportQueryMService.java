package com.joyintech.tams.svreport.chbreport.g06bmonthreport.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportG06bDetail;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
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
 * @see G06bMonthReportQueryMService
 * @since 1.0
 */
@Service
public class G06bMonthReportQueryMService extends CommonExportService<ChbreportPrdInfoDto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(G06bMonthReportQueryMService.class);

    /**
     * 华夏利润表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/G06bMonthReportQuery/findPage", param,
                ChbreportPrdInfoDto.class);
    }

    /**
     * 导出华夏利润表
     *
     * @param list
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<ChbreportExportDto> list) throws Exception {
        List<ChbreportExportFileDto> listFileDto = new ArrayList<ChbreportExportFileDto>();
        for (ChbreportExportDto dto : list) {
            listFileDto.add(this.createExcel(dto.getReportDate()));
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

    private ChbreportExportFileDto createExcel(String reportDate) throws Exception {
        // 设置文件信息
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = "G06b_II银行理财子公司风险资本计算表_" + reportDate + ".xls";
        String outPath = exportFilePath + File.separator + fileName;
        String exceltemplate = "g06bmonthreport/G06bMonthReport";

        ChbreportExportFileDto exportFileDto = new ChbreportExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        // 设置 Excel 元素
        Map<String, Object> map = createData(reportDate);
        //map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        map.put("reportDate", reportDate);
        map.put("org", "华夏银行");
        TisUtil.createFile(map, exceltemplate, fileName);

        return exportFileDto;
    }

    /**
     * 获取导出数据
     *
     * @param
     * @param
     * @return
     */
    private Map<String, Object> createData(String reportDate) {
        Map<Object, Object> paraMap = new HashMap<Object, Object>(4);
        paraMap.put("reportDate", reportDate);
        List<ChbMonthReportG06bDetail> detailList = restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/G06bMonthReportQuery/selectG06bMonthReportList", paraMap,
                ChbMonthReportG06bDetail.class);

        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
//        String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
        Map<String, Object> data = new HashMap<String, Object>(64);
        for (ChbMonthReportG06bDetail detail : detailList) {
            data.put(detail.getPropertycode(), detail);
        }
        return data;

    }
}
