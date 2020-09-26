/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.service;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetExtKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.HandleReturnDto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ValTableGenerate001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ValTableGenerate001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValTableGenerate001Service extends CommonExportService<ValTableGenerate001Dto> {

    /**
     * ValTableGenerate001Mapper
     */
//    @Autowired
//    private ValTableGenerate001Mapper mapper;

    /**
     * ValTableGenerate001Mapper
     */
//    @Autowired
//    private ValBalanceStatementQuery001Mapper valBalanceStatementQuery001Mapper;
//
//    @Autowired
//    private BokValbalTableStatusMapper bokValbalTableStatusMapper;
//
//    @Autowired
//    private BokValbalTableLogMapper bokValbalTableLogMapper;
//    
//    @Autowired
//    private BokBooksetExtMapper bokBooksetExtMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValTableGenerate001Service.class);

//    /**
//     * 所属账套下拉框获取
//     *
//     * @return 返回的数据
//     * @exception Exception 异常
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getBooksetId() {
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        List<ValAssetbook001Dto> listBookset = mapper.getBooksetId();
//        listBookset.forEach(item -> {
//            listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
//        });
//        return listSelectDto;
//    }

//    /**
//     * 业务类别下拉框获取
//     *
//     * @return 返回的数据
//     * @exception Exception 异常
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getBusiClass() {
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        List<ValTablelist001Dto> listTable = mapper.getBusiClass();
//        listTable.forEach(item -> {
//            listSelectDto.add(createSelect(item.getTableId(), item.getTableName()));
//        });
//        return listSelectDto;
//    }

    /**
     * 格式化创建SelectOptionDto对象 <功能详细描述>
     * 
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ValTableGenerate001Dto> findPage(Map<String, Object> params)
        throws Exception {
        
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/findPage", params, ValTableGenerate001Dto.class);
        
//        // 获取交易类型
//        String prodType = (String)params.get("prodTypes");
//        // 交易方向多选，转为数组
//        String[] prodTypeArr = null;
//        if (StringUtil.isNotEmpty(prodType)) {
//            prodTypeArr = prodType.split(",");
//        }
//
//        // 获取交易代码
//        String prodId = (String)params.get("prodIds");
//        // 交易方向多选，转为数组
//        String[] prodIdArr = null;
//        if (StringUtil.isNotEmpty(prodId)) {
//            prodIdArr = prodId.split(",");
//        }
//
//        // 获取生成状态
//        String buildStatus = (String)params.get("buildStatuses");
//        // 交易方向多选，转为数组
//        String[] buildStatusArr = null;
//        if (StringUtil.isNotEmpty(buildStatus)) {
//            buildStatusArr = buildStatus.split(",");
//        }
//        ValTableGenerate001SearchDto dto = (ValTableGenerate001SearchDto)JsonUtils.map2entity(
//            params, ValTableGenerate001SearchDto.class);
//        dto.setProdIds(prodIdArr);
//        dto.setProdTypes(prodTypeArr);
//        dto.setBuildStatuses(buildStatusArr);
//        return super.queryForPage(() -> mapper.queryPageList(dto));
    }

    /**
     * 根据条件查询全部信息
     * 
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    // public List<ValVoucherQuery001Dto> selectAll(ValVoucherQuery001SearchDto
    // dto){
    // return mapper.queryPageList(dto);
    // }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/getFinprodId", map, SelectOptionDto.class);
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        List<ValProductInfoQuery001Dto> listBookset = mapper.getFinprodId(map);
//        listBookset.forEach(item -> {
//            SelectOptionDto dto = new SelectOptionDto();
//            dto.setId(item.getFinprodId());
//            dto.setText(item.getFinprodAbbr());
//            listSelectDto.add(dto);
//        });
//        return listSelectDto;
    }
    
//    /**
//     * 查询产品下拉框
//     * 
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getBooksetList() {
//        return mapper.getBooksetList(DictCodeList.账套类别_理财账套.getValue());
//    }

    /**
     * 手动生成更新估值余额状态表
     * 
     * @return
     * @see
     * @since 1.0
     */
    public HandleReturnDto handle(String data) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/handle", data,HandleReturnDto.class);
//        boolean flag = false;
//        int n = 0;
//        int m = 0;
//        // 删除估值表余额表状态表数据
//        mapper.deleteValbalTableStatus(dto);
//        // 删除估值表日志表数据
//        mapper.deleteValbalTableLog(dto);
//        // 从估值表数据抽取数据生成估值表余额表状态表数据
//        List<BokValbalTableStatus> blist = mapper.queryValbalTableStatus(dto);
//        for (BokValbalTableStatus bdto : blist) {
//            // 对账状态--未对账
//            bdto.setCheckStatus(DictCodeList.对账状态_未对账.getValue());
//            // 文件类型
//            bdto.setValBalFlag(DictCodeList.文件类型_估值表.getValue());
//            bdto.setCreateTime(new Date());
//            bdto.setCreateUser("admin");
//            bdto.setUpdateTime(new Date());
//            bdto.setUpdateUser("admin");
//            // 入库
//            int result1 = bokValbalTableStatusMapper.insert(bdto);
//            if (result1 > 0) {
//                n++ ;
//            }
//
//            BokValbalTableLog bldto = new BokValbalTableLog();
//            // 产品代码
//            bldto.setFinprodId(bdto.getFinprodId());
//            // 估值表
//            bldto.setBuildStatus(DictCodeList.生成状态_生成成功.getValue());
//            // 文件类型
//            bldto.setValBalFlag(DictCodeList.文件类型_估值表.getValue());
//            bldto.setValDate(bdto.getValDate());
//            bldto.setCreateTime(new Date());
//            bldto.setCreateUser("admin");
//            bldto.setUpdateTime(new Date());
//            bldto.setUpdateUser("admin");
//            int result2 = bokValbalTableLogMapper.insert(bldto);
//            if (result2 > 0) {
//                m++ ;
//            }
//        }
//
//        if (n == m) {
//            flag = true;
//        }
//        return flag;
    }

//    /**
//     * <一句话功能简述> <功能详细描述>
//     * 
//     * @param map
//     * @see
//     * @since 1.0
//     */
//    public int handleByHandCheck(ValTableGenerate001SearchDto dto) {
//        int count = 0;
//        count = mapper.queryValbalTableStatusCount(dto);
//        return count;
//    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @see
     * @since 1.0
     */
    public ValAssetbook001Dto queryBooksetId(String prodid) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/queryBooksetId", prodid, ValAssetbook001Dto.class);
//        ValAssetbook001Dto booksetid = mapper.queryBooksetId(prodid);
//        return booksetid;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param map
     * @see
     * @since 1.0
     */
    public int queryBooksetIdCount(ValTableGenerate001SearchDto dto) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/queryBooksetIdCount", dto, Integer.class);
//        return mapper.queryBooksetIdCount(dto);
    }
    

    /**
     * 估值表生成 - 手动生成 - 执行前数据检查
     * @param data
     * @return
     * @see
     * @since 1.0
     */
    public HandleReturnDto checkAccountsForHandle(String data) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValTableGenerate001/checkAccountsForHandle", data, HandleReturnDto.class);
        
    }

    /**
     * <一句话功能简述> 导出估值表
     * 
     * @param map
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<ValValuationStatementQuery001Dto> list)
        throws IOException {
        List<ExportFileDto> listFileDto = new ArrayList<ExportFileDto>();
        for (ValValuationStatementQuery001Dto dto : list) {
            // 根据产品代码查询账套id
            ValAssetbook001Dto bookdto = queryBooksetId(dto.getFinprodId());
            listFileDto.add(this.createExcel(bookdto.getBooksetId(), dto.getValDate(),
                bookdto.getBooksetFullname()));
        }

        if (!CollectionUtils.isEmpty(listFileDto)) {
            // 超过一条时，用zip打包下载
            if (listFileDto.size() > 1) {
                String exportFilePath = CacheUtil.getSysParam(
                    GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
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
                        }
                        catch (Exception e) {
                            logger.error("", e);
                        }
                        finally {
                            if (input != null) {
                                input.close();
                            }
                        }
                    }
                    zipOut.flush();
                }
                catch (Exception e) {
                    logger.error("", e);
                }
                finally {
                    if (zipOut != null) {
                        zipOut.close();
                    }
                    if (stream != null) {
                        stream.close();
                    }
                }
                return ExportUtil.export(zipFileName, zipFile, true);
            }
            // 一条时单个文件导出
            else {
                File file = new File(listFileDto.get(0).getFilePath());
                return ExportUtil.export(listFileDto.get(0).getFileName(), file, true);
            }
        }
        else {
            return null;
        }
    }

    public ExportFileDto createExcel(String booksetId, String valDate, String booksetFullName)
        throws IOException {
        ExportFileDto exportFileDto = new ExportFileDto();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("booksetId", booksetId);
        param.put("valDate", valDate);
        List<ValBalanceStatementQuery001Dto> listAll = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+
            "/ValTableGenerate001/selectBalanceAll", param, ValBalanceStatementQuery001Dto.class);
        
        List<ValBalanceStatementQuery001Dto> listSum = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+
            "/ValTableGenerate001/selectBalanceSum", param, ValBalanceStatementQuery001Dto.class);
        
        // 获取单位净值
        Optional<ValBalanceStatementQuery001Dto> value = listSum.stream().filter(
            item -> DictCodeList.估值表类型_产品单位净值.getValue().equals(item.getDetailDist())).findFirst();
        ValBalanceStatementQuery001Dto unitPrice = null;
        if (value.isPresent()) {
            unitPrice = value.get();
        }
        String fileName = booksetId + "（" + booksetFullName + "）" + "估值表" + valDate + ".xls";
        // 无论是净值型还是货币型，查询结果都是listSum，故不用区分
        String tempName = "ProdValuation.xls";
        
        // 获取账套配置表对应的模板
        BokBooksetExtKey key = new BokBooksetExtKey();
        key.setBooksetId(booksetId);
        key.setParamCode(DictCode.估值表模板.getValue());
        BokBooksetExt bokBooksetExt =restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+
            "/ValTableGenerate001/selectByPrimaryKey", key, BokBooksetExt.class);
     // 若账套配置表中估值表模板不为空，则根据当前配置区分模板选择
        if (StringUtil.isNotEmpty(bokBooksetExt)) {
            if (DictCodeListExt1.估值表模板_理财产品模板_系统推荐.getValue().equals(bokBooksetExt.getParamValue())) {
                // 杭州估值表模板
                tempName = GlobalConstant.PROD_TMP_NAME;
            }
            else{
                // 平安估值表模板
                tempName = GlobalConstant.FUND_TMP_NAME;
            }
        }
        else{
            // 估值模板选择
            String hzSchemeSwitch = CacheUtil.getSysParam("SCHEME_MODE");
            if (DictCodeList.估值模板_理财产品模板.getValue().equals(hzSchemeSwitch)) {
                // 杭州估值表模板 
                tempName = GlobalConstant.PROD_TMP_NAME;
            }
            else{
                // 平安估值表模板
                tempName = GlobalConstant.FUND_TMP_NAME;
            }
        }
        String outPath = CacheUtil.getSysParam("upload_file_path") + File.separator
                         + fileName;
        /*String tplPath = CacheUtil.getSysParam("BATCH_TPL_OUT") + File.separator
                         + tempName;*/
//        File tplFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "tpl/tplPath");
//        String tplPath = tplFile + File.separator + tempName;

        InputStream stream = null;
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("prodTitle", String.format("%s（%s）估值表", booksetId, booksetFullName));
        paraMap.put("dateStr", valDate);
        paraMap.put("listAll", listAll);
        paraMap.put("listSum", listSum);
        if (unitPrice != null) {
            paraMap.put("unitPrice", unitPrice.getSubjectName());
        }
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {
            stream = this.getClass().getResourceAsStream("/tpl/tplPath/"+ tempName);
            ExcelTools.writeExcel(stream, tempName, paraMap, outPath,
                new ExcelCellValueFunction() {
                    CellStyle newStyle = null;
                    Font font = null;
                    // 一级科目号对应一行加粗
                    @Override
                    public void execute(String tplKey, Object value, Cell cell, Row row,
                                        Sheet sheet, Workbook workbook) {
                    	if(null == newStyle) {
                    		newStyle = workbook.createCellStyle();
                    	}
                    	if(null == font) {
                    		 font = workbook.createFont();
                             font.setBold(true);
                    	}
//                        newStyle.cloneStyleFrom(cell.getCellStyle());
                        // 列名对应科目号，且为4位，则为1级科目
                        if (StringUtil.startsWith(tplKey, "listAll")
                            && tplKey.contains("subjectNo")
                            && String.valueOf(value).length() == 4) {
                        	newStyle.setFont(font);
                            // row.setRowStyle()无效，所以循环对整行加粗
//                            for (int i = 1; i < row.getPhysicalNumberOfCells(); i++ ) {
//                                row.getCell(i).setCellStyle(newStyle);
//                            }
                        	cell.setCellStyle(newStyle);
                        }

                    }
                });
        }
        catch (Exception e) {
            logger.error("", e);
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return exportFileDto;
    }
}
