/*
 * 文件名：mstMarketCalendarInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dao.ChbCalendarInfoMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dto.ChbCalendarInfo001Dto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dto.ChbCalendarInfoDto;
import com.joyintech.tams.svreport.chb.common.dto.TabColumnsDto;
import com.joyintech.tams.svreport.chb.common.service.CheckExcelImportService;
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
import java.util.regex.Pattern;

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

    public static String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/calendarinfo/conf/export_conf.xml";


    /**
     * 表名
     */
    public static String TABLE_NAME = "MST_HOLIDAY";

    /**
     * 数字正则表达式
     */
    public static String NUMBER_REGEX = "-?[0-9]+(\\.[0-9]+)?";

    @Resource
    private ChbCalendarInfoMapper chbCalendarInfoMapper;
    @Resource
    private CheckExcelImportService checkExcelImportService;

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
        return super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), () -> chbCalendarInfoMapper.selectInfo(param));
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
        if (entity.getYorn() == 0) {
            // 工作日设节假日
            entity.setYorn(1);
            chbCalendarInfoMapper.sethday(entity);
        } else if (entity.getYorn() == 1) {
            // 节假日设工作日
            entity.setYorn(0);
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
        List<ValidMessage> errorList = new LinkedList<>();

        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        //判断excel表中日期和市场日历类型是否冲突
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = i + 1; j < dataList.size(); j++) {
                if (StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
                        StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1))) {
                    ValidMessage validMessage = new ValidMessage("第" + (i + 2) + "日期、市场日历类型与" + "第" + (j + 2) + "日期、市场日历类型相同");
                    errorList.add(validMessage);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(errorList)) {
            webResult.setErrorList(errorList);
            return webResult;
        }

        //基础校验数据库字段属性
        Map<String, TabColumnsDto> map = checkExcelImportService.getTabColumns(TABLE_NAME);
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            //校验日期
            this.checkTabColumns(map.get("CDATE"), list.get(0), i, "日期", errorList);
            //校验市场日历类型
            this.checkTabColumns(map.get("CALENDAR_ID"), list.get(1), i, "市场日历类型", errorList);
            //校验是否节假日
            this.checkTabColumns(map.get("IS_HOLIDAY"), list.get(2), i, "是否节假日", errorList);
        }
        if (CollectionUtils.isNotEmpty(errorList)) {
            webResult.setErrorList(errorList);
            return webResult;
        }


        webResult.setData("noInfoShowOut");

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
        List<ExcelValidDto> checkResult = new LinkedList<>();
        // 检查文件名是否包含节假日信息导入
        if (!fileName.contains("节假日信息导入")) {
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY, getMessage("BUSS_COM_E_0002", "市场日历信息导入")));
            return checkResult;
        }

        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        if (CollectionUtils.isEmpty(dataList)) {
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY, "数据不能为空"));
            return checkResult;
        }
        // 增强check 将excel表格中的数据转化为dto
        List<ChbCalendarInfo001Dto> list = this.checkDataConvertToBean(dataList, checkResult);
        List<ExcelValidDto> excelValidList = checkExcelImportService.getExcelValidList(checkResult);
        this.dealAfterCheck(list);
        return excelValidList;
    }


    /**
     * 校验列
     */
    private void checkTabColumns(TabColumnsDto dto, String value, int i, String columns, List<ValidMessage> errorList) {
        //如果字段允许为空，值为空直接返回
        if (Objects.equals("Y", dto.getNullAble()) && StringUtil.isBlank(value)) {
            return;
        }
        if (StringUtil.isEmpty(value)) {
            errorList.add(new ValidMessage("第" + (i + 2) + "行" + columns + "字段在数据库中不能为空"));
        }
        //校验字段长度
        if (dto.getLength() != 0 && dto.getLength() < value.length()) {
            errorList.add(new ValidMessage("第" + (i + 2) + "行" + columns + "字段长度不能超过" + dto.getLength()));
        }
        //日期类型校验
        if (Objects.equals("DATE", dto.getDataType())) {
            if (!this.isValidDate(value)) {
                errorList.add(new ValidMessage("第" + (i + 2) + "行" + columns + "不是日期格式"));
            }
        }
        //数字类型校验
        if (Objects.equals("NUMBER", dto.getDataType())) {
            if (!this.isNumeric(value.split(" ")[0])) {
                errorList.add(new ValidMessage("第" + (i + 2) + "行" + columns + "不是数字格式"));
            }
        }
    }

    /**
     * 判断一个字符串是否可以转成日期
     */
    public boolean isValidDate(String date) {
        boolean convertSuccess = true;
        String newDate = date.replaceAll("/", "-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(newDate);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 匹配是否为数字
     *
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
            return false;
        }
        // matcher是全匹配
        return pattern.matcher(bigStr).matches();
    }


    /**
     * 增强check list转换为Bean
     *
     * @param dataList 导入数据
     * @return 返回检查结果
     */
    private List<ChbCalendarInfo001Dto> checkDataConvertToBean(List<List<String>> dataList, List<ExcelValidDto> checkResultAll) {


        List<ChbCalendarInfo001Dto> returnInfoList = new LinkedList<>();
        ChbCalendarInfo001Dto chbCalendarInfo001Dto;
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            List<ExcelValidDto> checkResult = new LinkedList<>();
            //校验数据是否为必填
            if (StringUtil.isEmpty(list.get(0))) {
                checkResult.add(new ExcelValidDto("日期", String.valueOf(i + 2), "日期为空"));
            }
            if (StringUtil.isEmpty(list.get(1))) {
                checkResult.add(new ExcelValidDto("市场日历类型", String.valueOf(i + 2), "市场日历类型为空"));
            }
            if (StringUtil.isEmpty(list.get(2))) {
                checkResult.add(new ExcelValidDto("是否节假日", String.valueOf(i + 2), "是否节假日为空"));
            }
            // 检查表内数据每行是否合法
            if (CollectionUtils.isNotEmpty(checkExcelCow(i, dataList.get(i)))) {
                checkResult.addAll(checkExcelCow(i, dataList.get(i)));
            }
            //list转换为Bean
            chbCalendarInfo001Dto = new ChbCalendarInfo001Dto();
            chbCalendarInfo001Dto.setCdate(DateUtil.parse(list.get(0)));
            chbCalendarInfo001Dto.setCalendarId(list.get(1).split(" ")[0]);
            chbCalendarInfo001Dto.setIsHoliday(StringUtil.isNotBlank(list.get(2)) ? Long.parseLong(list.get(2).split(" ")[0]) : null);
            chbCalendarInfo001Dto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            if (CollectionUtils.isEmpty(checkResult)) {
                chbCalendarInfo001Dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            } else {
                chbCalendarInfo001Dto.setStatus(DictCodeListExt2.数据状态_待处理.getValue());
                chbCalendarInfo001Dto.setErrMsg(checkExcelImportService.getCheckErrMsg(checkResult));
                checkResultAll.addAll(checkResult);
            }

            returnInfoList.add(chbCalendarInfo001Dto);
        }
        return returnInfoList;
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
     * check后的保存 <功能详细描述>
     *
     * @param equityList 需要保存的数据
     */
    public void dealAfterCheck(List<ChbCalendarInfo001Dto> equityList) {
        List<ChbCalendarInfo001Dto> listN = new LinkedList<>();
        List<ChbCalendarInfo001Dto> listA = new LinkedList<>();
        for (ChbCalendarInfo001Dto chbCalendarInfo001Dto : equityList) {
            ChbCalendarInfo001Dto equityInfoCheck = getMstMarketCalendarInfo001DtoByKey(chbCalendarInfo001Dto);
            if (equityInfoCheck == null) {
                listN.add(chbCalendarInfo001Dto);
            } else {
                listA.add(chbCalendarInfo001Dto);
            }

        }
        if (CollectionUtils.isNotEmpty(listN)) {

            this.insertBtachEquityInfoByPart(listN);
        }
        if (CollectionUtils.isNotEmpty(listA)) {
            this.updateBtachEquityInfoByPart(listA);
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
     * 插入数据 <功能详细描述>
     */
    private void insertBtachEquityInfo(List<ChbCalendarInfo001Dto> list) {
        chbCalendarInfoMapper.insertBtachEquityInfo(list);
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
     * 分批次执行insertBtachEquityInfo
     *
     * @param dataList 数据
     */
    private void insertBtachEquityInfoByPart(List<ChbCalendarInfo001Dto> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return;
        }
        int maxDataCountOneTime = 20;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            insertBtachEquityInfo(dataList);
        } else {
            List<ChbCalendarInfo001Dto> newDataList = new ArrayList<>(dataList);

            // 分批处理
            // 分批数
            int part = dataCount / maxDataCountOneTime;
            for (int i = 0; i < part; i++) {
                insertBtachEquityInfo(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                insertBtachEquityInfo(newDataList);
            }
        }
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
