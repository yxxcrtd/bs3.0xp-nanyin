package com.joyintech.tams.svreport.chb.finassetliabilitystatement.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dao.FinAssetLiabilityStatementQuery001Mapper;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery004Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private FinAssetLiabilityStatementQuery001Mapper finAssetLiabilityStatementQuery001Mapper;

    /**
     * 查询产品代码和产品名称
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPrdBaseinfo(Map<String, Object> map) {
        return finAssetLiabilityStatementQuery001Mapper.selectAllPrdBaseInfo(map);
    }

    /**
     * 根据条件获取表格信息
     *
     * @param pageParam 筛选条件
     * @return 表格信息
     * @see
     * @since 1.0
     */
    public Page<FinAssetLiabilityStatementQuery001Dto> selectForPage(Map<String, Object> pageParam) throws Exception {
        // 查询参数条件
        // 将产品代码当做多选下拉框处理
        String prodId = (String) pageParam.get("prodId");
        if (StringUtil.isNotBlank(prodId)) {
            pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodId));
        }
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            Page<FinAssetLiabilityStatementQuery001Dto> page = super.queryForPage(
                    pagenum, length, () -> finAssetLiabilityStatementQuery001Mapper.selectAllData(pageParam));
            return page;
        } else {
            Page<FinAssetLiabilityStatementQuery001Dto> page = queryForPage(FinAssetLiabilityStatementQuery001Mapper.class, "selectAllData", pageParam);
            return page;

        }
    }

    /**
     * 查询导出数据-资产
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<FinAssetLiabilityStatementQuery004Dto> selectAssetList(Map<String, Object> pageParam) throws Exception {

        pageParam.put("type", "01");
        return finAssetLiabilityStatementQuery001Mapper.selectBalanceData(pageParam);
    }

    /**
     * 查询导出数据-负债，所有者权益
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<FinAssetLiabilityStatementQuery004Dto> selectLiabilitiesAndOwnerEquityList(Map<String, Object> pageParam) throws Exception {
        pageParam.put("type", "02,03");

        return finAssetLiabilityStatementQuery001Mapper.selectBalanceData(pageParam);
    }

    /**
     * 查询表头数据
     *
     * @param pageParam 筛选条件
     * @return dto
     * @see
     * @since 1.0
     */
    public List<FinAssetLiabilityStatementQuery004Dto> selectTitle(Map<String, Object> pageParam) throws Exception {
        return finAssetLiabilityStatementQuery001Mapper.selectTitle(pageParam);
    }

//    /**
//     * 联动：报表期限类型 - 季度 半年
//     *
//     * @return
//     * @throws Exception
//     */
//    public List<SelectOptionDto> getTradeMarket(Map<String, Object> param) {
//        return simpleRestTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/getTradeMarket", param, SelectOptionDto.class);
//    }
//
//    /**
//     * 通用联动查询
//     *
//     * @param param : dict_code_parent,dict_code_child,item_code_parent
//     * @return
//     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @DataSource(DataSourceType.OTHER)
//    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
//        return cashFlowsStatement001Mapper.getLinkSelectOption(param);
//    }
//
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param params
//     * @return
//     * @throws IOException
//     * @throws ParseException
//     * @see
//     * @since 1.0
//     */
//    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params) throws IOException, ParseException {
//        ExportFileDto exportFileDto = createExcel(params);
//        File file = new File(exportFileDto.getFilePath());
//        return ExportUtil.export(exportFileDto.getFileName(), file, true);
//
//    }
//
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param params
//     * @return
//     * @throws ParseException
//     * @see
//     * @since 1.0
//     */
//    private ExportFileDto createExcel(Map<String, Object> params) throws ParseException {
//        // 报表日期
//        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
//        // 导出数据集
//        List<CashFlowsStatement001Dto> dtoList = selectList(params);
//        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
//        String fileName = params.get("booksetId").toString() + "_" + reportDate + "_现金流量表" + ".xls";
//        String tempName = "CashFlowsStatement.xls";
//        String tplFlodPath = "accounadjustmanage/cashflowsstatement/CashFlowsStatement.xls";
//        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
//        String outPath = exportFilePath + File.separator + fileName;
//
//        ExportFileDto exportFileDto = new ExportFileDto();
//        InputStream stream = null;
//        Map<String, Object> map = new HashMap<>();
//        map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
//        map.put("dtoList", dtoList);
//        map.put("reportDate", reportDate);
//        map.put("reportTermType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, params.get("reportTermType").toString()));
//        exportFileDto.setFileName(fileName);
//        exportFileDto.setFilePath(outPath);
//        try {
//
//            //针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
//            File templeteFile = new File(tplPath);
//            //文件存在情况,直接该路径获取文件流
//            if (templeteFile.exists()) {
//                stream = new FileInputStream(templeteFile);
//            }
//            //文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
//            else {
//                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
//            }
//
//            ExcelTools.writeExcel(stream, tempName, map, outPath, new ExcelCellValueFunction() {
//
//                @Override
//                public void execute(String tplKey, Object value, Cell cell, Row row, Sheet sheet, Workbook workbook) {
//
//                    CellStyle newStyle = workbook.createCellStyle();
//                    newStyle.cloneStyleFrom(cell.getCellStyle());
//                    Font font = workbook.createFont();
//                    // 部分标题加粗
//                    if (StringUtil.startsWith(tplKey, "dtoList")
//                        && tplKey.contains("project")
//                        && String.valueOf(value).endsWith("活动产生的现金流量")) {
//                        font.setBold(true);
//                        newStyle.setFont(font);
//                        cell.setCellStyle(newStyle);
//                    }
//                }
//            });
//        } catch (IOException e) {
//        	logger.error("",e);
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (IOException e) {
//                	logger.error("",e);
//                }
//            }
//        }
//        return exportFileDto;
//    }
//
//
//
//
//
//    /**
//     * 查询现金流量表数据list
//     *
//     * @param pageParam
//     * @return
//     * @throws ParseException
//     * @see
//     * @since 1.0
//     */
//    public List<CashFlowsStatement001Dto> selectListp(Map<String, Object> pageParam, CashFlowsStatement001Dto dto) {
//
//        pageParam.put("CashFlowsStatement001Dto",dto);
//
//        return simpleRestTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/selectListp",
//                pageParam, CashFlowsStatement001Dto.class);
//    }
//
//    /**
//     * 获取默认日期信息
//     *
//     * @param pageParam
//     * @return CashFlowsStatement001Dto
//     */
//    public CashFlowsStatement001Dto getDateInfo(Map<String, Object> pageParam) {
//        return simpleRestTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
//                                          + "/CashFlowsStatement001/getDateInfo",
//            pageParam, CashFlowsStatement001Dto.class);
//    }

}
