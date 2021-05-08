package com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PrdBalance;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tools.util.ExcelTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see FinAssetLiabilityStatementQueryMService
 * @since 1.0
 */
@Service
public class FinAssetLiabilityStatementQueryMService extends CommonExportService<FinAssetLiabilityStatementQuery001Dto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(FinAssetLiabilityStatementQueryMService.class);

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
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/ChbreportCommon/getPrdBaseinfo", param,
                SelectOptionDto.class);
    }

    /**
     * 理财产品资产负债表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/findPage", param,
                ChbreportPrdInfoDto.class);
    }

    /**
     * 导出理财产品资产负债表
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
        // 获取资产List和负债及所有者权益List
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("prodId", prodId);
        param.put("reportDate", reportDate);

        List<PrdBalance> finAssetLiabilityList = restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/FinAssetLiabilityStatementQuery/selectFinAssetLiabilityList", param,
                PrdBalance.class);
        List<FinAssetLiabilityStatementQuery001Dto> mergeList = getMergerList(finAssetLiabilityList);

        // 解决报表数据为空导出模板公式的问题
        if (mergeList.isEmpty()) {
            FinAssetLiabilityStatementQuery001Dto emptyDto = new FinAssetLiabilityStatementQuery001Dto();
            emptyDto.setInitialBalance(null);
            emptyDto.setInitialBalance1(null);
            emptyDto.setEndingBalance(null);
            emptyDto.setEndingBalance1(null);
            mergeList.add(emptyDto);
        }

        // 设置文件信息
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = prodId + "_资产负债表_" + reportDate + ".xls";
        String outPath = exportFilePath + File.separator + fileName;
        String exceltemplate = "finassetliabilitystatement/FinAssetLiabilityStatement";

        ChbreportExportFileDto exportFileDto = new ChbreportExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        Map<String, Object> map = new HashMap<>();
        map.put("title", String.format("%s年%s月_资产负债表_%s_月报", reportDate.substring(0, 4), reportDate.substring(5, 7), prodName));
        //map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        map.put("reportDate", DateUtil.format(DateUtil.platDateFromCache()));
        map.put("org", "华夏银行");
        map.put("ccy", finAssetLiabilityList.get(0).getCcy());
        map.put("prodName", prodName);
        map.put("year", reportDate.substring(0, 4));
        map.put("mergeList", mergeList);
        TisUtil.createFile(map, exceltemplate, fileName);

        return exportFileDto;
    }

    /**
     * 获取合并资产负债所有者权益
     *
     * @param finAssetLiabilityList
     * @return
     */
    private List<FinAssetLiabilityStatementQuery001Dto> getMergerList(List<PrdBalance> finAssetLiabilityList) {
        List<PrdBalance> assetList = finAssetLiabilityList.stream().filter(dto -> {
            return StringUtil.isNotEmpty(dto.getSubjectName());
        }).collect(Collectors.toList());

        List<PrdBalance> liabilitiesAndOwnerEquityList = finAssetLiabilityList.stream().filter(dto -> {
            return StringUtil.isNotEmpty(dto.getLiaSubjectName());
        }).collect(Collectors.toList());

        List<PrdBalance> maxList = new ArrayList<>();
        List<PrdBalance> minList = new ArrayList<>();
        // 资产标识
        boolean flag = true;

        if (assetList.size() >= liabilitiesAndOwnerEquityList.size()) {
            maxList.addAll(assetList);
            minList.addAll(liabilitiesAndOwnerEquityList);
            flag = true;
        } else {
            maxList.addAll(liabilitiesAndOwnerEquityList);
            minList.addAll(assetList);
            flag = false;
        }

        List<FinAssetLiabilityStatementQuery001Dto> mergerList = new ArrayList<>();
        PrdBalance dto = null;
        FinAssetLiabilityStatementQuery001Dto mergerDto = null;
        for (int i = 0; i < maxList.size(); i++) {
            mergerDto = new FinAssetLiabilityStatementQuery001Dto();
            if (i < minList.size()) {
                dto = minList.get(i);
            } else {
                dto = null;
            }
            if (flag) {
                mergerDto.setItemName(maxList.get(i).getSubjectName());
                mergerDto.setInitialBalance(maxList.get(i).getBeginBal());
                mergerDto.setEndingBalance(maxList.get(i).getEndBal());
                if (Objects.nonNull(dto) && StringUtil.isNotEmpty(dto.getLiaSubjectName())) {
                    mergerDto.setItemName1(dto.getLiaSubjectName());
                    mergerDto.setEndingBalance1(dto.getLiaEndBal());
                    mergerDto.setInitialBalance1(dto.getLiaBeginBal());
                }
            } else {
                mergerDto.setItemName1(maxList.get(i).getLiaSubjectName());
                mergerDto.setEndingBalance1(maxList.get(i).getLiaEndBal());
                mergerDto.setInitialBalance1(maxList.get(i).getLiaBeginBal());
                if (Objects.nonNull(dto) && StringUtil.isNotEmpty(dto.getSubjectName())) {
                    mergerDto.setItemName(dto.getSubjectName());
                    mergerDto.setInitialBalance(dto.getBeginBal());
                    mergerDto.setEndingBalance(dto.getEndBal());
                }
            }

            if (StringUtil.isEmpty(mergerDto.getItemName())) {
                mergerDto.setItemName(null);
                mergerDto.setInitialBalance(null);
                mergerDto.setEndingBalance(null);
            }
            if (StringUtil.isEmpty(mergerDto.getItemName1())) {
                mergerDto.setItemName1(null);
                mergerDto.setInitialBalance1(null);
                mergerDto.setEndingBalance1(null);
            }

            mergerList.add(mergerDto);
        }
        return mergerList;
    }
}
