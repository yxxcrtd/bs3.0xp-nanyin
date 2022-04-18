/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dao.ChbExchangeQuotationInfoMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dto.ChbExchangeQuotationInfoDto;

import com.joyintech.tams.svreport.chb.common.service.CheckExcelImportService;
import com.joyintech.tams.svreport.chb.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 汇率行情
 *
 * @author zhoubin
 * @version 1.0 2022年03月30日
 * @see ChbExchangeQuotationInfoService
 * @since 1.0
 */

@Service
public class ChbExchangeQuotationInfoService extends CommonExportService<ChbExchangeQuotationInfoDto> implements BaseProcessService<ChbExchangeQuotationInfoDto> {

    private final Logger logger = LoggerFactory.getLogger(ChbExchangeQuotationInfoService.class);

    public static final String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbexchangeinfo/conf/export_conf.xml";
    /**
     * 表名
     */
    public static final String TABLE_NAME = "MST_EXCHARGE_QUOTE";
    /**
     * 接口类型
     */
    public static final String CHECK_FLAG = "D";

    /**
     * 删除
     */
    public static final String DELETE = "DELETE";
    /**
     * 新建
     */
    public static final String NEW = "NEW";

    /**
     * 分页大小
     */
    public static final int PAGE_SIZE = 500;


    @Resource
    private ChbExchangeQuotationInfoMapper chbExchangeQuotationInfoMapper;
    @Resource
    private BaseCommonCheckService baseCommonCheckService;


    /**
     * 汇率行情分页查询
     *
     * @param param 查询参数
     * @return 返回查询结果
     */
    public Page<ChbExchangeQuotationInfoDto> selectForPage(Map<String, Object> param) {
        String exchargeCode = (String) param.get("exchargeCode");
        if (StringUtil.isNotEmpty(exchargeCode)) {
            param.put("exchargeCodeList", StringUtil.splitToList(GlobalConstant.COMMA, exchargeCode));
        }
        return super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), () -> chbExchangeQuotationInfoMapper.select(param));
    }

    /**
     * 查询所有数据验证删除是否存在
     *
     * @param pageParam 查询参数
     * @return 查询结果
     */
    public List<ChbExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return chbExchangeQuotationInfoMapper.select(pageParam);
    }


    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(CONFIG_FILE).dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<ChbExchangeQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "tradeDate")) {
            cell.setCellValue(DateUtil.format((Date) value));
        }
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {

        logger.info("===========导入校验===========");
        AjaxResult webResult = new AjaxResult();
        // 检查文件名是否包含汇率行情信息
        if (!fileName.contains("汇率行情信息")) {
            webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        List<List<String>> tempList = ExcelUtil.getTempList1(fileName);
        List<String> headList = new ArrayList<>(9);
        headList.add("货币对");
        headList.add("交易日期");
        headList.add("开盘价（元）");
        headList.add("收盘价（元）");
        headList.add("中间价（元）");
        headList.add("最高价（元）");
        headList.add("最低价（元）");
        headList.add("报价单位");
        headList.add("数据来源");
        //校验文件中列是否一致
        List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
        if (!StringUtil.isEmptyList(tempCheckResult)) {
            webResult.setMessage(this.getMessage("ImportFileTemplate"), MessageType.错误消息);
            return webResult;
        }

        //List<List<String>>转成List<Map<String, String>>
        List<Map<String, String>> mapList = this.convertMapList(dataList);
        //check包含DB和业务CHECK
        List<ExcelValidDto> excelValidList = baseCommonCheckService.checkDataAll(CHECK_FLAG, mapList, TABLE_NAME, fileName, TABLE_NAME, TABLE_NAME, null, null);
        //总数据
        int count = mapList.size();
        //待处理数据
        long countA = mapList.stream().filter(e -> Objects.equals("A", e.get("STATUS"))).count();
        //无法接入系统的数据
        long countR = excelValidList.stream().filter(e -> Objects.equals("0未导入", e.getValidKey())).count();
        webResult.setMessage(this.getMessage("ChbImportMessage", count - countA, countR, countA), MessageType.确认消息);
        //按500条分页
        List<List<Map<String, String>>> partitionList = Lists.partition(mapList, PAGE_SIZE);
        //数据重复的个数
        int duplicateCount = 0;
         for (List<Map<String, String>> list:partitionList) {
             int i = chbExchangeQuotationInfoMapper.selectExchargeQuoteCount(list);
             if (i>0){
                 duplicateCount+=i;
             }
         }
         //存在重复数据提示错误
         if (duplicateCount>0) {
             List<JoyinMessage> joyinMessages = new ArrayList<>(1);
             JoyinMessage ms = new JoyinMessage(this.getMessage("ChbImportDuplicateMessage",duplicateCount), MessageType.确认消息);
             joyinMessages.add(ms);
             webResult.setJoyinMessages(joyinMessages);
         }
        return webResult;
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        //根据文件名获取文件数据
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);

        //List<List<String>>转成List<Map<String, String>>
        List<Map<String, String>> mapList = this.convertMapList(dataList);

        //check包含DB和业务CHECK
        List<ExcelValidDto> excelValidList = baseCommonCheckService.checkDataAll(CHECK_FLAG, mapList, TABLE_NAME, fileName, TABLE_NAME, TABLE_NAME, null, null);

        List<ChbExchangeQuotationInfoDto> dtoList = this.convertToBean(mapList);
        //按500条分页
        List<List<ChbExchangeQuotationInfoDto>> partitionList = Lists.partition(dtoList, PAGE_SIZE);
        //批量入库
        for (List<ChbExchangeQuotationInfoDto> list:partitionList) {
            chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(list);
        }
        return excelValidList;
    }

    /**
     * 增强check list转换为Bean
     *
     * @return 返回检查结果
     */
    private List<ChbExchangeQuotationInfoDto> convertToBean(List<Map<String, String>> mapList) {


        List<ChbExchangeQuotationInfoDto> returnList = new LinkedList<>();
        ChbExchangeQuotationInfoDto dto = null;
        for (Map<String, String> map : mapList) {
            dto = new ChbExchangeQuotationInfoDto();
            if (map.containsKey("EXCHARGE_CODE")) {
                String exchargeCode = map.get("EXCHARGE_CODE");
                dto.setExchargeCode(exchargeCode);
                dto.setBasisCyy(exchargeCode.split("/")[0]);
                dto.setQuotCyy(exchargeCode.split("/")[1]);
            }
            if (map.containsKey("TRADE_DATE")) {
                dto.setTradeDate(DateUtil.parse(map.get("TRADE_DATE")));
            }
            if (map.containsKey("DATASOURCE_TYPE")) {
                dto.setDatasourceType(map.get("DATASOURCE_TYPE"));
            }
            if (map.containsKey("OPEN_PRICE")) {
                String openPrice = map.get("OPEN_PRICE");
                dto.setOpenPrice(StringUtil.isBlank(openPrice) ? null : new BigDecimal(openPrice));
            }
            if (map.containsKey("CLOSE_PRICE")) {
                String closePrice = map.get("CLOSE_PRICE");
                dto.setClosePrice(StringUtil.isBlank(closePrice) ? null: new BigDecimal(closePrice));
            }
            if (map.containsKey("MID_PRICE")) {
                String midPrice = map.get("MID_PRICE");
                dto.setMidPrice(StringUtil.isBlank(midPrice)? null : new BigDecimal(midPrice));
            }
            if (map.containsKey("HIGH_PRICE")) {
                String highPrice = map.get("HIGH_PRICE");
                dto.setHighPrice(StringUtil.isBlank(highPrice)? null : new BigDecimal(highPrice));
            }
            if (map.containsKey("LOW_PRICE")) {
                String lowPrice = map.get("LOW_PRICE");
                dto.setLowPrice(StringUtil.isBlank(lowPrice)? null : new BigDecimal(lowPrice));
            }
            if (map.containsKey("QUOT_UNIT")) {
                dto.setQuotUnit(Long.parseLong(map.get("QUOT_UNIT")));
            }
            if (Objects.equals("A", map.get("STATUS"))) {
                dto.setStatus(DictCodeListExt2.数据状态_待处理.getValue());
                dto.setErrMsg(map.get("ERRMSG"));
            } else {
                dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            }
            dto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            returnList.add(dto);
        }
        return returnList;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doComplete(ChbExchangeQuotationInfoDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        List<ChbExchangeQuotationInfoDto> list = new ArrayList<>(1);
        dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        if (StringUtil.equals(dto.getOperateType(), DELETE)) {
            List<ChbExchangeQuotationInfoDto> selectedItem = dto.getSelectedItem();
            selectedItem.forEach(e -> {
                e.setStatus(DictCodeListExt2.数据状态_删除.getValue());
            });
            chbExchangeQuotationInfoMapper.updateExchargeQuoteInfo(selectedItem);
        } else if (StringUtil.equals(dto.getOperateType(), NEW)) {
            //删除状态是已删除的数据
            chbExchangeQuotationInfoMapper.deleteChbExchargeQuoteByKey(dto.getExchargeCode(), dto.getTradeDate(), dto.getDatasourceType(), DictCodeListExt2.数据状态_删除.getValue());
            //设置基本币种，单位币种等
            String exchargeCode = dto.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                dto.setBasisCyy(splitToList.get(0));
                dto.setQuotCyy(splitToList.get(1));
            }
            dto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            list.add(dto);

            chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(list);


        } else {
            list.add(dto);
            chbExchangeQuotationInfoMapper.updateExchargeQuoteInfo(list);
        }
    }


    public Integer getChbExchargeQuoteByKey(ChbExchangeQuotationInfoDto dto) {
        dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        return chbExchangeQuotationInfoMapper.getChbExchargeQuoteByKey(dto);
    }


    /**
     * List<List<String>>转成List<Map<String, String>>
     *
     * @param dataList
     * @return
     */
    private List<Map<String, String>> convertMapList(List<List<String>> dataList) {

        List<Map<String, String>> mapList = new LinkedList<>();
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            Map<String, String> map = new LinkedHashMap<>(9);
            map.put("EXCHARGE_CODE", list.get(0));
            map.put("TRADE_DATE", StringUtils.isNotBlank(list.get(1))?list.get(1).replaceAll("/","-"):null);
            map.put("OPEN_PRICE", list.get(2));
            map.put("CLOSE_PRICE", list.get(3));
            map.put("MID_PRICE", list.get(4));
            map.put("HIGH_PRICE", list.get(5));
            map.put("LOW_PRICE", list.get(6));
            map.put("QUOT_UNIT", list.get(7));
            map.put("DATASOURCE_TYPE", StringUtils.isNotBlank(list.get(8))?list.get(8).split(":")[0]:null);
            mapList.add(map);
        }

        return mapList;

    }
}
