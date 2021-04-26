package com.joyintech.tams.svreport.chbreport.hxprofit.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.entity.HxProfit;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportFileDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.hxprofit.dto.HXProfitQuery001Dto;
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
 * @see HXProfitQueryMService
 * @since 1.0
 */
@Service
public class HXProfitQueryMService extends CommonExportService<ChbreportPrdInfoDto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(HXProfitQueryMService.class);

    /**
     * 华夏利润表分页查询
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> param) throws Exception {
        return restTemplate.postForPage(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/HXProfitQuery/findPage", param,
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
        String fileName = "华夏利润表 " + reportDate + ".xls";
        String tempName = "HXProfit.xls";
        String tplFlodPath = "hxprofit/HXProfit.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ChbreportExportFileDto exportFileDto = new ChbreportExportFileDto();
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);

        // 设置 Excel 元素
        HXProfitQuery001Dto entity = getProfitData(reportDate);
        Map<String, Object> map = new HashMap<>(4);
        map.put("preCol", reportDate.substring(4, 6) + "月累计金额");
        map.put("reportDate", reportDate);
        map.put("entity", entity);

        InputStream stream = null;
        try {
            //针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            //文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            //文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }

            ExcelTools.writeExcel(stream, tempName, map, outPath);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return exportFileDto;
    }

    /**
     * 获取导出数据
     *
     * @param
     * @param
     * @return
     */
    private HXProfitQuery001Dto getProfitData(String reportDate) {
        List<HxProfit> bqList = getHxProfitList(reportDate, "1");
        List<HxProfit> syList = getHxProfitList(reportDate, "2");
        List<HxProfit> bnList = getHxProfitList(reportDate, "3");

        HXProfitQuery001Dto profitDto = new HXProfitQuery001Dto();
        if (!bqList.isEmpty() && bqList.size() > 0) {
            profitDto.setBq(bqList.get(0));
            profitDto.setBusiIncBQ(getBusiInc(profitDto.getBq()));
            profitDto.setBusiPayBQ(getBusiInc(profitDto.getBq()));
        }
        if (!syList.isEmpty() && syList.size() > 0) {
            profitDto.setSy(syList.get(0));
            profitDto.setBusiIncSY(getBusiInc(profitDto.getSy()));
            profitDto.setBusiPaySY(getBusiInc(profitDto.getSy()));
        }
        if (!bnList.isEmpty() && bnList.size() > 0) {
            profitDto.setBn(bnList.get(0));
            profitDto.setBusiIncBN(getBusiInc(profitDto.getBn()));
            profitDto.setBusiPayBN(getBusiInc(profitDto.getBn()));
        }
        return profitDto;
    }

    /**
     * 根据入参查询利润表数据
     */
    private List<HxProfit> getHxProfitList(String reportDate, String flag) {
        Map<String, Object> paraMap = new HashMap<>(4);
        paraMap.put("reportMon", reportDate);
        paraMap.put("flag", flag);
        return restTemplate.postForList(
                SvrServiceApplication.CHBREPORT_SERVICE_NAME + "/HXProfitQuery/selectHXProfitList", paraMap,
                HxProfit.class);
    }

    /**
     * 计算营业收入总值
     */
    private BigDecimal getBusiInc(HxProfit entity) {
        BigDecimal busiInc = new BigDecimal(0);
        busiInc = busiInc.add(entity.getLixiInc())
                .add(entity.getInvestLixiInc())
                .add(entity.getFinaLixiInc());
        return busiInc;
    }

    /**
     * 计算营业支出总值
     */
    private BigDecimal getBusiPay(HxProfit entity) {
        BigDecimal busiPay = new BigDecimal(0);
        busiPay = busiPay.add(entity.getProdPay())
                .add(entity.getBusiFee())
                .add(entity.getAssetImp());
        return busiPay;
    }
}
