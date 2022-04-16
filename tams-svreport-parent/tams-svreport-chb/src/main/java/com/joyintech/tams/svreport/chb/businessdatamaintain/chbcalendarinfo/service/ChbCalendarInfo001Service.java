/*
 * 文件名：mstMarketCalendarInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.service;

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
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dao.ChbCalendarInfoMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dto.ChbCalendarInfo001Dto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbcalendarinfo.dto.ChbCalendarInfoDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbexchangeinfo.dto.ChbExchangeQuotationInfoDto;
import com.joyintech.tams.svreport.chb.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 市场日历信息service层 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年8月20日
 * @see ChbCalendarInfo001Service
 * @since 1.0
 */
@Service
public class ChbCalendarInfo001Service extends CommonExportService<ChbCalendarInfo001Dto> {

    public static String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbcalendarinfo/conf/export_conf.xml";


    /**
     * 表名
     */
    public static String TABLE_NAME = "MST_HOLIDAY";

    /**
     * 接口类型
     */
    public static final String CHECK_FLAG = "D";

    /**
     * 分页大小
     */
    public static final int PAGE_SIZE = 500;

    @Resource
    private ChbCalendarInfoMapper chbCalendarInfoMapper;
    @Resource
    private BaseCommonCheckService baseCommonCheckService;

    /**
     * 市场日历信息查询
     *
     * @param param 查询参数
     * @return 查询结果
     */
    public Page<ChbCalendarInfo001Dto> selectInfo(Map<String, Object> param) {
        if (Objects.equals(param.get("ishDay"), DictCodeList.是否_是.getValue())) {
            param.put("ishDay", 1);
        } else if (StringUtil.equals(param.get("ishDay"), DictCodeList.是否_否.getValue())) {
            param.put("ishDay", 0);
        }
        String calendarType = (String) param.get("calendarType");
        if (StringUtil.isNotEmpty(calendarType)) {
            param.put("calendarTypeList", StringUtil.split(calendarType, GlobalConstant.COMMA));
        }
        Page<ChbCalendarInfo001Dto> pageList = super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), () -> chbCalendarInfoMapper.selectInfo(param));
        return pageList;
    }

    /**
     * 市场日历类型下拉框查询
     *
     * @param map 参数
     * @return 返回结果
     */
    public List<ChbCalendarInfoDto> selectCalendarInfo(Map<String, Object> map) {
        map.put("no", DictCodeList.是否_否.getValue());
        return chbCalendarInfoMapper.selectCalendarInfo(map);
    }

    /**
     * 修改日历状态 <功能详细描述>
     *
     * @param entity 实体类
     */
    public void doComplete(ChbCalendarInfo001Dto entity) {
        if (entity.getIsHoliday() == 0) {
            // 工作日设节假日
            entity.setIsHoliday(1L);
            chbCalendarInfoMapper.sethday(entity);
        } else if (entity.getIsHoliday() == 1) {
            // 节假日设工作日
            entity.setIsHoliday(0L);
            chbCalendarInfoMapper.setwday(entity);
        }
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {

        AjaxResult webResult = new AjaxResult();
        // 检查文件名是否包含汇率行情信息
        if (!fileName.contains("节假日信息")) {
            webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        List<List<String>> tempList = ExcelUtil.getTempList1(fileName);
        List<String> headList = new ArrayList<>(3);
        headList.add("日期");
        headList.add("市场日历类型");
        headList.add("是否节假日");
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
        long countA = mapList.stream().filter(ele -> Objects.equals("A", ele.get("STATUS"))).count();
        //问题数据
        int countR = excelValidList.size();
        webResult.setMessage(this.getMessage("ChbImportMessage", count - countA, countR, countA), MessageType.确认消息);
        //按500条分页
        List<List<Map<String, String>>> partitionList = Lists.partition(mapList, PAGE_SIZE);
        //数据重复的个数
        int duplicateCount = 0;
        for (List<Map<String, String>> list:partitionList) {
            int i = chbCalendarInfoMapper.selectMstHoliday(list);
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
     * excel导入功能
     *
     * @param fileName 文件名称
     * @return 检验list
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String fileName) throws FileNotFoundException, ParseException {
        //根据文件名获取文件数据
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);

        //List<List<String>>转成List<Map<String, String>>
        List<Map<String, String>> mapList = this.convertMapList(dataList);

        //check包含DB和业务CHECK
        List<ExcelValidDto> excelValidList = baseCommonCheckService.checkDataAll(CHECK_FLAG, mapList, TABLE_NAME, fileName, TABLE_NAME, TABLE_NAME, null, null);

        List<ChbCalendarInfo001Dto> dtoList = this.convertToBean(mapList);
        //按500条分页
        List<List<ChbCalendarInfo001Dto>> partitionList = Lists.partition(dtoList, PAGE_SIZE);
        //批量入库
        for (List<ChbCalendarInfo001Dto> list:partitionList) {
            chbCalendarInfoMapper.insertBatchMstHoliday(list);
        }
        return excelValidList.stream().filter(e -> Objects.equals("YW",e.getValidKey())).collect(Collectors.toList());
    }


    /**
     * 增强check list转换为Bean
     *
     * @return 返回检查结果
     */
    private List<ChbCalendarInfo001Dto> convertToBean(List<Map<String, String>> mapList) {


        List<ChbCalendarInfo001Dto> returnList = new LinkedList<>();
        ChbCalendarInfo001Dto dto = null;
        for (Map<String, String> map : mapList) {
            dto = new ChbCalendarInfo001Dto();

            if (map.containsKey("CALENDAR_ID")) {
                dto.setCalendarId(map.get("CALENDAR_ID"));
            }
            if (map.containsKey("CDATE")) {
                dto.setCdate(DateUtil.parse(map.get("CDATE").replaceAll("/","-")));
            }
            if (map.containsKey("IS_HOLIDAY")) {
                dto.setIsHoliday(StringUtils.isNotBlank(map.get("IS_HOLIDAY"))?Long.parseLong(map.get("IS_HOLIDAY")):null);
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
            Map<String, String> map = new LinkedHashMap<>(3);
            map.put("CDATE", list.get(0));
            map.put("CALENDAR_ID", StringUtil.isNotBlank(list.get(1))?list.get(1).split(" ")[0]:"");
            map.put("IS_HOLIDAY", StringUtil.isNotBlank(list.get(2)) ? list.get(2).split(" ")[0] :"");
            mapList.add(map);
        }
        return mapList;

    }



    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(CONFIG_FILE).dataSource(DataSourceType.MASERT);
    }

    /**
     * 给单元格设定值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "cdate")) {
            cell.setCellValue(DateUtil.format((Date) value));
        }
    }


    /**
     * 根据key获取dto <功能详细描述>
     *
     * @param dto key
     * @return 数据
     */
    private ChbCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(ChbCalendarInfo001Dto dto) {
        return chbCalendarInfoMapper.getMstMarketCalendarInfo001DtoByKey(dto);
    }


    /**
     * 更新数据
     *
     * @param list 参数
     */
    private void updateBtachEquityInfo(List<ChbCalendarInfo001Dto> list) {
        chbCalendarInfoMapper.updateBtachEquityInfo(list);
    }


    /**
     * 分批次执行updateBtachEquityInfo
     *
     * @param dataList 数据
     */
    private void updateBtachEquityInfoByPart(List<ChbCalendarInfo001Dto> dataList) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }

        int maxDataCountOneTime = 20;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            updateBtachEquityInfo(dataList);
        } else {

            List<ChbCalendarInfo001Dto> newDataList = new ArrayList<>(dataList);

            // 分批处理
            // 分批数
            int part = dataCount / maxDataCountOneTime;
            for (int i = 0; i < part; i++) {
                updateBtachEquityInfo(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                updateBtachEquityInfo(newDataList);
            }
        }
    }

    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i    第几行
     * @param list 每一列解析后的List<String>
     * @return 检查结构
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtoList = new LinkedList<>();
        // 删除数据列 SPV 登记编码
        // 列数变为3
        if (null == list || list.size() != 3) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i), this.getMessage("MST_INFO_E_0001"));
            excelValidDtoList.add(dto);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        // 使用日期格式化捕获异常来判断日期格式是否合法
        String newDate = list.get(0).replaceAll("/", "-");
        try {

            date = list == null ? null : sdf.parse(newDate);
            // 获取平台日期
            Date sysDate = DateUtil.platDateFromCache();
            if (date != null && date.before(sysDate)) {
                // 验证导入数据日期是否小于平台日期
                ExcelValidDto dto = new ExcelValidDto(newDate, String.valueOf(i), this.getMessage("BUSS_COM_E_0003", "平台日期", "导入数据的日期"));
                excelValidDtoList.add(dto);
            }
        } catch (ParseException e) {
            ExcelValidDto dto = new ExcelValidDto(newDate, String.valueOf(i), this.getMessage("MST_INFO_E_0001"));
            excelValidDtoList.add(dto);
        }
        return excelValidDtoList;
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    public Page<ChbCalendarInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectInfo(selectParam);
    }

}
