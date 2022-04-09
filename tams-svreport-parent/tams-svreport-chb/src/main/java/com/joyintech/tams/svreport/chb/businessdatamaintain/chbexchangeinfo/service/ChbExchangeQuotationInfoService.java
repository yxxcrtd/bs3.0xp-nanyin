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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dao.ChbExchangeQuotationInfoMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dto.ChbExchangeQuotationInfoDto;
import com.joyintech.tams.svreport.chb.common.dto.TabColumnsDto;
import com.joyintech.tams.svreport.chb.common.service.CheckExcelImportService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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


    @Resource
    private ChbExchangeQuotationInfoMapper chbExchangeQuotationInfoMapper;
    @Resource
    private CheckExcelImportService checkExcelImportService;


    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOW_OUT = "noInfoShowOut";

    /**
     * 汇率行情分页查询 <功能详细描述>
     *
     * @param param 查询参数
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    public Page<ChbExchangeQuotationInfoDto> selectForPage(Map<String, Object> param) {
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
        List<ValidMessage> errorListAll = new LinkedList<>();
        // 检查文件名是否包含汇率行情信息
        if (!fileName.contains("汇率行情信息")) {
            throw new RuntimeException("模板名称错误");
        }

        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        List<Map<String, String>> mapList = new LinkedList<>();
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            Map<String, String> map = new LinkedHashMap<>(9);
            map.put("EXCHARGE_CODE", list.get(0));
            map.put("TRADE_DATE", list.get(1));
            map.put("OPEN_PRICE", list.get(2));
            map.put("CLOSE_PRICE", list.get(3));
            map.put("MID_PRICE", list.get(4));
            map.put("HIGH_PRICE", list.get(5));
            map.put("LOW_PRICE", list.get(6));
            map.put("QUOT_UNIT", list.get(7));
            map.put("DATASOURCE_TYPE", list.get(8));
            mapList.add(map);
        }

        //正常接入的条数
        int success = 0;
        //存在格式问题的条数
        int fail = 0;
        //未按照要求填写的条数
        int unSuccess = 0;

        errorListAll.add(new ValidMessage(success + "条数据无问题，可正常接入"));
        if (fail > 0) {
            errorListAll.add(new ValidMessage(fail + "条数据存在格式问题，系统无法接入"));
        }
        if (unSuccess > 0) {
            errorListAll.add(new ValidMessage(unSuccess + "条数据未按照要求填写，接入系统后为待处理状态"));
        }
        webResult.setErrorList(errorListAll);
        return webResult;
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {

        List<ExcelValidDto> checkResultAll = new LinkedList<>();
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        //判断excel表中货币对和交易日期、数据来源是否冲突
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = i + 1; j < dataList.size(); j++) {
                if (StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
                        StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))) {
                    checkResultAll.add(new ExcelValidDto("0未接入", String.valueOf(i + 2), "第" + (i + 2) + "行货币对、交易日期、数据来源与" + "第" + (j + 2) + "行货币对、交易日期、数据来源相同"));
                }
            }
        }



        List<ChbExchangeQuotationInfoDto> list = this.checkDataConvertToBean(null, 9, checkResultAll);
        List<ExcelValidDto> excelValidList = checkExcelImportService.getExcelValidList(checkResultAll);
        this.dealAfterCheck(list);
        return excelValidList;
    }

    /**
     * 增强check list转换为Bean
     *
     * @return 返回检查结果
     */
    private List<ChbExchangeQuotationInfoDto> checkDataConvertToBean(List<String> list, int i, List<ExcelValidDto> checkResultAll) {


        List<ChbExchangeQuotationInfoDto> returnList = new LinkedList<>();
        List<ExcelValidDto> checkResult = new LinkedList<>();
        //校验数据是否为必填
        if (StringUtil.isEmpty(list.get(0))) {
            checkResult.add(new ExcelValidDto("货币对", String.valueOf(i + 2), "货币对为空"));
        }
        if (StringUtil.isEmpty(list.get(1))) {
            checkResult.add(new ExcelValidDto("交易日期", String.valueOf(i + 2), "交易日期为空"));
        }
        if (StringUtil.isEmpty(list.get(2))) {
            checkResult.add(new ExcelValidDto("开盘价", String.valueOf(i + 2), "开盘价为空"));
        }
        if (StringUtil.isEmpty(list.get(3))) {
            checkResult.add(new ExcelValidDto("收盘价", String.valueOf(i + 2), "收盘价为空"));
        }
        if (StringUtil.isEmpty(list.get(4))) {
            checkResult.add(new ExcelValidDto("中间价", String.valueOf(i + 2), "中间价为空"));
        }
        if (StringUtil.isEmpty(list.get(5))) {
            checkResult.add(new ExcelValidDto("最高价", String.valueOf(i + 2), "最高价为空"));
        }
        if (StringUtil.isEmpty(list.get(6))) {
            checkResult.add(new ExcelValidDto("最低价", String.valueOf(i + 2), "最低价为空"));
        }
        if (StringUtil.isEmpty(list.get(7))) {
            checkResult.add(new ExcelValidDto("报价单位", String.valueOf(i + 2), "报价单位为空"));
        }
        if (StringUtil.isEmpty(list.get(8))) {
            checkResult.add(new ExcelValidDto("数据来源", String.valueOf(i + 2), "数据来源为空"));
        }
        //list转换为Bean
        ChbExchangeQuotationInfoDto dto = new ChbExchangeQuotationInfoDto();
        dto.setExchargeCode(list.get(0));
        dto.setTradeDate(DateUtil.parse(list.get(1)));
        dto.setDatasourceType(list.get(8).split(":")[0]);
        dto.setOpenPrice(StringUtil.isEmpty(list.get(2)) == true ? null : new BigDecimal(list.get(2)));
        dto.setClosePrice(StringUtil.isEmpty(list.get(3)) == true ? null : new BigDecimal(list.get(3)));
        dto.setMidPrice(StringUtil.isEmpty(list.get(4)) == true ? null : new BigDecimal(list.get(4)));
        dto.setHighPrice(StringUtil.isEmpty(list.get(5)) == true ? null : new BigDecimal(list.get(5)));
        dto.setLowPrice(StringUtil.isEmpty(list.get(6)) == true ? null : new BigDecimal(list.get(6)));
        dto.setQuotUnit(new BigDecimal(list.get(7)));
        dto.setBasisCyy(list.get(0).split("/")[0]);
        dto.setQuotCyy(list.get(0).split("/")[1]);
        if (CollectionUtils.isEmpty(checkResult)) {
            dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        } else {
            dto.setStatus(DictCodeListExt2.数据状态_待处理.getValue());
            dto.setErrMsg(checkExcelImportService.getCheckErrMsg(checkResult));
            checkResultAll.addAll(checkResult);
        }

        returnList.add(dto);
        return returnList;
    }

    /**
     * check后的保存 <功能详细描述>
     *
     * @param list 需要保存的数据
     */
    public void dealAfterCheck(List<ChbExchangeQuotationInfoDto> list) {
        List<ChbExchangeQuotationInfoDto> listN = new LinkedList<>();
        List<ChbExchangeQuotationInfoDto> listA = new LinkedList<>();
        for (ChbExchangeQuotationInfoDto dto : list) {
            Integer count = chbExchangeQuotationInfoMapper.getChbExchargeQuoteByKey(dto);
            if (count < 1) {
                listN.add(dto);
            } else {
                listA.add(dto);
            }

        }
        //新增
        if (CollectionUtils.isNotEmpty(listN)) {
            chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(listN);
        }
        //更新
        if (CollectionUtils.isNotEmpty(listA)) {
            chbExchangeQuotationInfoMapper.updateExchargeQuoteInfo(listA);
        }
    }


    @Override
    public void doComplete(ChbExchangeQuotationInfoDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        List<ChbExchangeQuotationInfoDto> list = new ArrayList<>(1);
        dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        if (StringUtil.equals(dto.getOperateType(), "DELETE")) {
            dto.setStatus(DictCodeListExt2.数据状态_删除.getValue());
            chbExchangeQuotationInfoMapper.updateStatus(dto);
        } else if (StringUtil.equals(dto.getOperateType(), "NEW")) {
            //设置基本币种，单位币种等
            String exchargeCode = dto.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                dto.setBasisCyy(splitToList.get(0));
                dto.setQuotCyy(splitToList.get(1));
            }
            list.add(dto);

            chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(list);


        } else {
            list.add(dto);
            chbExchangeQuotationInfoMapper.updateExchargeQuoteInfo(list);
        }
    }

    public Integer getChbExchargeQuoteByKey(ChbExchangeQuotationInfoDto dto) {
        return chbExchangeQuotationInfoMapper.getChbExchargeQuoteByKey(dto);
    }
}
