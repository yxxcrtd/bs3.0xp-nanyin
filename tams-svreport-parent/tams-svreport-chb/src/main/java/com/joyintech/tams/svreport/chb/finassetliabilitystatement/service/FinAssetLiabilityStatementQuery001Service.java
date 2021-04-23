package com.joyintech.tams.svreport.chb.finassetliabilitystatement.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.*;
import com.joyintech.tools.util.ExcelTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see FinAssetLiabilityStatementQuery001Service
 * @since 1.0
 */
@Service
public class FinAssetLiabilityStatementQuery001Service extends CommonExportService<FinAssetLiabilityStatementQuery001Dto> {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(FinAssetLiabilityStatementQuery001Service.class);

    /**
     * 查询产品代码和产品名称
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPrdBaseinfo(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/selectAllList", param,
                SelectOptionDto.class);
    }

    /**
     * 理财产品资产负债表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<FinAssetLiabilityStatementQuery001Dto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHB_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/findPage", param,
                FinAssetLiabilityStatementQuery001Dto.class);
    }

    /**
     * 导出理财产品资产负债表
     *
     * @param list
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<FinAssetLiabilityStatementQuery002Dto> list) throws Exception {
        logger.info("export .....");
        List<ExportFileDto> listFileDto = new ArrayList<ExportFileDto>();
        for (FinAssetLiabilityStatementQuery002Dto dto : list) {
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
                for (ExportFileDto item : listFileDto) {
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

    private ExportFileDto createExcel(String prodId, String prodName, String reportDate)
            throws IOException {
        // 获取资产List和负债及所有者权益List
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("prodId", prodId);
        param.put("reportDate", reportDate);
        List<FinAssetLiabilityStatementQuery004Dto> assetList = restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/selectAssetList",
                param, FinAssetLiabilityStatementQuery004Dto.class);

        List<FinAssetLiabilityStatementQuery004Dto> liabilitiesAndOwnerEquityList = restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/selectLiabilitiesAndOwnerEquityList",
                param, FinAssetLiabilityStatementQuery004Dto.class);

        // 设置文件信息
        String fileName = prodId + " 资产负债表 " + reportDate + ".xls";
        String outPath = CacheUtil.getSysParam("upload_file_path") + File.separator + fileName;
        String tempName = "FinAssetLiabilityStatement.xls";

        ExportFileDto exportFileDto = new ExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        // 设置 Excel 元素
        FinAssetLiabilityStatementQuery004Dto titleDto = restTemplate.postForObject(
                SvrServiceApplication.CHB_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/selectTitle", param,
                FinAssetLiabilityStatementQuery004Dto.class);
        List<FinAssetLiabilityStatementQuery003Dto> mergerList = merger(assetList, liabilitiesAndOwnerEquityList);

        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("title", String.format("%s年%s月_资产负债表_%s_月报", reportDate.substring(0, 4), reportDate.charAt(6), prodName));
        paraMap.put("ccy", titleDto.getCcy());
        // paraMap.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        paraMap.put("prodName", prodName);
        paraMap.put("year", reportDate.substring(0, 4));
        paraMap.put("mergeList", mergerList);

        InputStream stream = null;
        try {
            stream = this.getClass().getResourceAsStream("/tpl/tplPath/" + tempName);
            ExcelTools.writeExcel(stream, tempName, paraMap, outPath);
        } catch (Exception e) {
            logger.error("export error: ", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("close file stream error: ", e);
                }
            }
        }
        return exportFileDto;
    }

    /**
     * 合并资产负债所有者权益
     *
     * @param assetList
     * @param liabilitiesAndOwnerEquityList
     * @return
     */
    private List<FinAssetLiabilityStatementQuery003Dto> merger(List<FinAssetLiabilityStatementQuery004Dto> assetList, List<FinAssetLiabilityStatementQuery004Dto> liabilitiesAndOwnerEquityList) {
        List<FinAssetLiabilityStatementQuery004Dto> maxList = new ArrayList<>();
        List<FinAssetLiabilityStatementQuery004Dto> minList = new ArrayList<>();
        // 资产标识
        boolean flag = true;

        if (assetList.size() >= liabilitiesAndOwnerEquityList.size()) {
            maxList.addAll(assetList);
            minList.addAll(liabilitiesAndOwnerEquityList);
            flag = true;
        } else {
            maxList.addAll(liabilitiesAndOwnerEquityList);
            minList.addAll(liabilitiesAndOwnerEquityList);
            flag = false;
        }

        List<FinAssetLiabilityStatementQuery003Dto> mergerList = new ArrayList<>();
        FinAssetLiabilityStatementQuery004Dto dto = null;
        FinAssetLiabilityStatementQuery003Dto mergerDto = null;
        for (int i = 0; i < maxList.size(); i++) {
            mergerDto = new FinAssetLiabilityStatementQuery003Dto();
            if (i < minList.size()) {
                dto = minList.get(i);
            } else {
                dto = null;
            }
            if (flag) {
                mergerDto.setAssetSubjectName(maxList.get(i).getSubjectName());
                mergerDto.setAssetEndingBalance(maxList.get(i).getEndingBalance());
                mergerDto.setAssetInitialBalance(maxList.get(i).getInitialBalance());
                if (Objects.nonNull(dto)) {
                    mergerDto.setLiabilitiesAndOwnerEquitySubjectName(dto.getSubjectName());
                    mergerDto.setLiabilitiesAndOwnerEquityEndingBalance(dto.getEndingBalance());
                    mergerDto.setLiabilitiesAndOwnerEquityInitialBalance(dto.getInitialBalance());
                }
            } else {
                mergerDto.setLiabilitiesAndOwnerEquitySubjectName(maxList.get(i).getSubjectName());
                mergerDto.setLiabilitiesAndOwnerEquityEndingBalance(maxList.get(i).getEndingBalance());
                mergerDto.setLiabilitiesAndOwnerEquityInitialBalance(maxList.get(i).getInitialBalance());
                if (Objects.nonNull(dto)) {
                    mergerDto.setAssetSubjectName(dto.getSubjectName());
                    mergerDto.setAssetEndingBalance(dto.getEndingBalance());
                    mergerDto.setAssetInitialBalance(dto.getInitialBalance());
                }
            }
            mergerList.add(mergerDto);
        }
        return mergerList;
    }
}
