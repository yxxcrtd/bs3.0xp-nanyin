package com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.HxBalance;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto.HXAssetLiabilityStatementQuery002Dto;
import com.joyintech.tools.util.ExcelTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see HXAssetLiabilityStatementQueryMService
 * @since 1.0
 */
@Service
public class HXAssetLiabilityStatementQueryMService extends CommonExportService<ChbreportPrdInfoDto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(HXAssetLiabilityStatementQueryMService.class);

    /**
     * 华夏资产负债表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/HXAssetLiabilityStatementQuery/findPage", param,
                ChbreportPrdInfoDto.class);
    }

    /**
     * 导出华夏资产负债表
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
        String fileName = "华夏理财资产负债表_" + reportDate + ".xls";
        String outPath = exportFilePath + File.separator + fileName;
        String exceltemplate = "hxassetliabilitystatement/HXAssetLiabilityStatement";

        ChbreportExportFileDto exportFileDto = new ChbreportExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        // 设置 Excel 元素
        HXAssetLiabilityStatementQuery002Dto dto = getBalanceData(reportDate);
        Map<String, Object> map = new HashMap<>(4);
        map.put("entity", dto);
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
    private HXAssetLiabilityStatementQuery002Dto getBalanceData(String reportDate) {
        Map<String, Object> paraMap = new HashMap<>(4);
        paraMap.put("reportMon", reportDate);
        paraMap.put("flag", "1");
        List<HxBalance> qcList = restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/HXAssetLiabilityStatementQuery/selectHXAssetLiabilityList", paraMap,
                HxBalance.class);
        paraMap.put("flag", "2");
        List<HxBalance> qmList = restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/HXAssetLiabilityStatementQuery/selectHXAssetLiabilityList", paraMap,
                HxBalance.class);

        HXAssetLiabilityStatementQuery002Dto balance = new HXAssetLiabilityStatementQuery002Dto();
        if (!qmList.isEmpty() && qmList.size() > 0) {
            balance.setQmBal(qmList.get(0));
            balance.setAssetTotalQM(getAssetTotal(balance.getQmBal()));
            balance.setLiabilityTotalQM(getLiabilityTotal(balance.getQmBal()));
            balance.setOwnerEquityTotalQM(balance.getQmBal().getUndisProfit());
            balance.setLiabilitiesAndOwnerEquityTotalQM(balance.getLiabilityTotalQM().add(balance.getOwnerEquityTotalQM()));
        }
        if (!qcList.isEmpty() && qcList.size() > 0) {
            balance.setQcBal(qcList.get(0));
            balance.setAssetTotalQC(getAssetTotal(balance.getQcBal()));
            balance.setLiabilityTotalQC(getLiabilityTotal(balance.getQcBal()));
            balance.setOwnerEquityTotalQC(balance.getQcBal().getUndisProfit());
            balance.setLiabilitiesAndOwnerEquityTotalQC(balance.getLiabilityTotalQC().add(balance.getOwnerEquityTotalQC()));
        }
        return balance;
    }

    /**
     * 计算资产总值
     *
     * @param bal
     * @return 资产总值
     */
    private BigDecimal getAssetTotal(HxBalance bal) {
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(bal.getDeposit())
                .add(bal.getCurrInvest())
                .add(bal.getCredInvest())
                .add(bal.getBondInvest())
                .add(bal.getFeibiaoInvest())
                .add(bal.getQuanyiInvest())
                .add(bal.getDerivaInvest())
                .add(bal.getOtherInvest())
                .add(bal.getInterAccr());
        return totalAmount;
    }

    /**
     * 计算负债总值
     *
     * @param
     * @param
     * @return
     */
    private BigDecimal getLiabilityTotal(HxBalance bal) {
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(bal.getInFund())
                .add(bal.getHuigouFund())
                .add(bal.getDerivaDebt())
                .add(bal.getAccrProfit())
                .add(bal.getCapitalShouyi())
                .add(bal.getCapitalJingzhi())
                .add(bal.getAccrIncome())
                .add(bal.getTaxPay())
                .add(bal.getYujiFuzhai())
                .add(bal.getOtherPay());
        return totalAmount;
    }
}
