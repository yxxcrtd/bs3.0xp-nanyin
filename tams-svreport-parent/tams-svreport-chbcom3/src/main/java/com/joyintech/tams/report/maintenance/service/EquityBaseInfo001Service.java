/*
 * 文件名：EquityBaseInfo001Service.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.maintenance.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbEquperiodHisMapper;
import com.joyintech.tams.common.base.dao.ChbEquperiodMapper;
import com.joyintech.tams.common.base.entity.ChbEquperiod;
import com.joyintech.tams.common.base.entity.ChbEquperiodHis;
import com.joyintech.tams.common.base.entity.ChbEquperiodKey;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.maintenance.dao.EquityBaseInfo001Mapper;
import com.joyintech.tams.report.maintenance.dto.EquiteBaseInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.EquityInfoCheckUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * @author 何阳阳
 * @version 1.0 2017年9月14日
 * @see EquityBaseInfo001Service
 * @since 1.0
 */
@Service
public class EquityBaseInfo001Service extends CommonExportService<EquiteBaseInfo001Dto> {

    /**
     * 验证登记银行编码
     */
    public static final String PATTERN_REGIST_BANK_CODE = "^C[0-9]{5}$";

    /**
     * EquityBaseInfo001Mapper
     */
    @Autowired
    private EquityBaseInfo001Mapper equityBaseInfo001Mapper;

    /**
     * ChbEquperiodMapper
     */
    @Autowired
    private ChbEquperiodMapper chbEquperiodMapper;

    /**
     * ChbEquperiodMapper
     */
    @Autowired
    private ChbEquperiodHisMapper chbEquperiodHisMapper;
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(EquityBaseInfo001Service.class);


    /**
     * 查询权益基期信息列表
     *
     * @param param 查询参数
     * @return 权益基期信息列表
     */
    public Page<EquiteBaseInfo001Dto> searchForPage(Map<String, Object> param) {
        buildSearchParam(param);
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<EquiteBaseInfo001Dto> page = super.queryForPage(
        		pagenum,legth,()->equityBaseInfo001Mapper.queryForPage(param));
        format(page.getRoot());
        return page;
    }

    /**
     * 查询所有内容，不分页
     * <p>
     * 导出功能需要导出查询条件的所有内容
     * </p>
     *
     * @param param 画面查询参数
     * @return 查询结果列表
     */
    public List<EquiteBaseInfo001Dto> queryReportList(Map<String, Object> param) {
        String keys = (String)param.get("keys");
        param.put("keys", StringUtil.splitToList(GlobalConstant.COMMA, keys));
        buildSearchParam(param);
        List<EquiteBaseInfo001Dto> dataList = equityBaseInfo001Mapper.queryForPage(param);
        format(dataList);
        return dataList;
    }

    /**
     * 删除数据
     *
     * @param keyStr 主键字符串
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public void doDelete(String keyStr) {
        if (StringUtil.isNotEmpty(keyStr)) {
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, keyStr);
            keys.forEach(key -> {
                String[] keyArray1 = StringUtil.split(key, "|");
//                String[] keyArray2 = StringUtil.split(keyArray1[1], "|");
//                String[] keyArray3 = StringUtil.split(keyArray2[1], "|");
                ChbEquperiod record = new ChbEquperiod();
                record.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
                record.setRegistBankCode(keyArray1[0]);
                record.setProdid(keyArray1[1]);
                record.setIndentifyMark(keyArray1[2]);
                record.setCcy(keyArray1[3]);
                chbEquperiodMapper.updateByPrimaryKeySelective(record);
            });
        }
    }

    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @param deptCode 部门代码
     * @param orgCode 机构代码
     * @return 导入结果
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(List<List<String>> dataList, String deptCode, String orgCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            if (CollectionUtils.isNotEmpty(dataList)) {
                List<EquiteBaseInfo001Dto> entities = new ArrayList<>(dataList.size());
                dataList.get(0).add(deptCode);
                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities);

                if (checkResult.isEmpty()) {
                    try {
                        entities.forEach(item -> {
                            ChbEquperiod record = new ChbEquperiod();
                            BeanUtil.copyPropertiesIgnoreNull(record, item);
                            record.setRegistBankCode(item.getBankCode());
                            record.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                            record.setProdid(item.getProdId());
                            record.setDeptCode(deptCode);
                            record.setOrgCode(orgCode);

                            // 设值createDept
                            if (StringUtil.isEmpty(deptCode)) {
                                record.setCreateDept(orgCode);
                            }
                            else {
                                record.setCreateDept(deptCode);
                            }

                            // 查询表中是否已存在删除的该数据
                            ChbEquperiodKey key = new ChbEquperiodKey();
                            key.setRegistBankCode(item.getBankCode());
                            key.setIndentifyMark(item.getIndentifyMark());
                            key.setProdid(item.getProdId());
                            key.setCcy(item.getCcy());
                            ChbEquperiod equperiod = chbEquperiodMapper.selectByPrimaryKey(key);
                            if (null != equperiod) {
                                if (StringUtil.equals(DictCodeListExt1.维护数据状态_删除.getValue(),
                                    equperiod.getStatus())) {
                                    // 将删除的数据插入履历表
                                    ChbEquperiodHis recordHis = new ChbEquperiodHis();
                                    recordHis.setRegistBankCode(item.getBankCode());
                                    recordHis.setProdid(item.getProdId());
                                    recordHis.setIndentifyMark(item.getIndentifyMark());
                                    recordHis.setCcy(item.getCcy());
                                    chbEquperiodHisMapper.insertHisByBasicInfo(recordHis);
                                }
                                // 将状态为删除状态的数据更新为有效状态的数据
                                chbEquperiodMapper.updateByPrimaryKeySelective(record);
                            }
                            else {
                                // 正常数据插入
                                chbEquperiodMapper.insertSelective(record);
                            }
                        });
                    }
                    catch (Exception e) {
//                        e.printStackTrace();
                        logger.error(e.getMessage(),e);
                        checkResult = new ArrayList<>();
                        checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                            this.getMessage("REPORT_COMMON_FILE_E_0016")));
                    }
                }
                return checkResult;
            }
        }
        return null;
    }

    /**
     * 处理查询参数中的条件
     * <p>
     * 将前台组装的字符串进行解析
     * </p>
     *
     * @param param 查询参数
     */
    private void buildSearchParam(Map<String, Object> param) {
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        if (StringUtil.isBlank(orgCode)) {
            orgCodeList = CommonUtil.getOrgCodeListForSearch();
        }
        param.put("orgCodeList", orgCodeList);
        String prdName = (String)param.get("prdName");
        String status = (String)param.get("status");
        param.put("prdCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prdName));
        param.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
    }

    /**
     * 格式数据字典
     * <p>
     * 将数据字典的值转化为名称，用于画面显示
     * </p>
     *
     * @param dataList 数据列表
     */
    private void format(List<EquiteBaseInfo001Dto> dataList) {
        if (null != dataList) {
            dataList.forEach(item -> {
                item.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, item.getStatus()));
                item.setCcy(SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, item.getCcy()));
            });
        }
    }

    /**
     * excel数据转为java对象
     * <p>
     * 将excel导入的数据，按行转为EquiteBaseInfo001Dto对象，同时验证各字段值
     * </p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,
                                                List<EquiteBaseInfo001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        if (StringUtil.isEmptyList(dataList)) {
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }

        try {
            String bankCode = CommonUtil.getUserBankCode();
            String deptCode = dataList.get(0).get(dataList.get(0).size() - 1);
            dataList.get(0).remove(dataList.get(0).size() - 1);
            for (int i = 0; i < dataList.size(); i++ ) {
                List<String> list = dataList.get(i);
                if (null == list || list.size() != 11) {
                    checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 1),
                        this.getMessage("REPORT_COMMON_FILE_E_0016")));
                    break;
                }
                EquiteBaseInfo001Dto entity = new EquiteBaseInfo001Dto();
                // 验证消息
                List<String> message = new ArrayList<>();
                // 登记银行编码
                int index = 0;
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "登记银行代码"));
                }
                else if (StringUtil.length(list.get(index)) != 6) {
                    // 登记银行编码必须为6位
                    message.add(this.getMessage("REPORT_COMMON_E_0055", "登记银行代码", 6));
                }
                else if (EquityInfoCheckUtil.findPatern(PATTERN_REGIST_BANK_CODE,
                    list.get(index))) {
                    // 登记银行编码格式验证
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0011", "登记银行代码"));
                }
                // excel中的登记银行代码必须与当前导入人的登记银行代码一致!
                else if (!StringUtil.equals(bankCode, list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_I_0019"));
                }
                else {
                    entity.setBankCode(list.get(index));
                }
                // 产品代码
                index++ ;
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品代码"));
                }
                else if (StringUtil.length(list.get(index)) > 20) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0004", "产品代码"));
                }
                else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("deptCode", deptCode);
                    map.put("prodId", list.get(index));
                    map.put("orgCode", CommonUtil.getOrgCode());
                    if (equityBaseInfo001Mapper.queryProdInfo(map) == 0) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0022"));
                    }
                    else {
                        entity.setProdId(list.get(index));
                    }
                }
                // 识别标志
                index++ ;
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "识别标志"));
                }
                else if (StringUtil.length(list.get(index)) > 30) {
                    // 识别标志长度不能大于30
                    message.add(this.getMessage("CHB_EQUITYINFO_E_010", "识别标志", 30));
                }
                else if (!CommonUtil.isEnglish(list.get(index))) {
                    // 识别标志不能输入汉字
                    message.add(this.getMessage("REPORT_COMMON_E_0004", "识别标志"));
                }
                // else if (equityBaseInfo001Mapper.queryIndentifyMark(list.get(index)) == 0) {
                // // 识别标志在投资者信息中不存在时
                // message.add(this.getMessage("REPORT_COMMON_FILE_E_0023", "识别标志", list.get(index)));
                // }
                else {
                    entity.setIndentifyMark(list.get(index));
                }
                // 币种
                index++ ;
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "币种"));
                }
                else {
                    entity.setCcy(
                        SysDataUtil.getZZDictCodeFromName(DictCode.币种_中债, list.get(index)));
                }
                index++;
                //金额
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "金额"));
                }
                else if (!CommonUtil.isFloatNumeric(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "金额"));
                }
                else {
                    // 必须大于0
                    BigDecimal num = new BigDecimal(list.get(index));
                    if (num.compareTo(BigDecimal.ZERO) <= 0) {
                        message.add(this.getMessage("REPORT_COMMON_E_0059", "金额", "0"));
                    }
                    // 长度验证
                    if (num.precision() > 13 || num.scale() > 2) {
                        message.add(this.getMessage("REPORT_ASSETBOND_E_001", "金额", 13, 2));
                    }
                    else {
                        entity.setAmount(new BigDecimal(list.get(index)));
                    }
                }
                // 总折算人民币金额（元）
                index++ ;
                if (StringUtil.isEmpty(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "总折算人民币金额"));
                }
                else if (!CommonUtil.isFloatNumeric(list.get(index))) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "总折算人民币金额"));
                }
                else {
                    // 必须大于0
                    BigDecimal num = new BigDecimal(list.get(index));
                    if (num.compareTo(BigDecimal.ZERO) <= 0) {
                        message.add(this.getMessage("REPORT_COMMON_E_0059", "总折算人民币金额", "0"));
                    }
                    // 长度验证
                    if (num.precision() > 13 || num.scale() > 2) {
                        message.add(this.getMessage("REPORT_ASSETBOND_E_001", "总折算人民币金额", 13, 2));
                    }
                    else {
                        entity.setConverCnyAmount(new BigDecimal(list.get(index)));
                    }
                }
                // 可用数量
                index++ ;
                String error1 = this.checkNumber(list.get(index), "可用数量");
                if (StringUtil.isNotEmpty(error1)) {
                    message.add(error1);
                }
                else {
                    entity.setAvailableQuantity(new BigDecimal(list.get(index)));
                }
                // 冻结数量
                index++ ;
                String error2 = this.checkNumber(list.get(index), "冻结数量");
                if (StringUtil.isNotEmpty(error2)) {
                    message.add(error2);
                }
                else {
                    entity.setFrozenQuantity(new BigDecimal(list.get(index)));
                }
                // 质押数量
                index++ ;
                String error3 = this.checkNumber(list.get(index), "质押数量");
                if (StringUtil.isNotEmpty(error3)) {
                    message.add(error3);
                }
                else {
                    entity.setPledgedQuantity(new BigDecimal(list.get(index)));
                }
                // 初值日期
                index++ ;
                if (StringUtil.isNotEmpty(list.get(index))) {
                    if (!DateUtil.isDateStr(list.get(index), DateUtil.DEFAULT_OTHER_FORMAT,
                        DateUtil.DEFAULT_ANOTHER_FORMAT, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "初值日期"));
                    }
                    else {
                        entity.setInitValDate(DateUtil.parse(list.get(index)));
                    }
                }
                // 初值登记日期
                index++ ;
                if (StringUtil.isNotEmpty(list.get(index))) {
                    if (!DateUtil.isDateStr(list.get(index), DateUtil.DEFAULT_OTHER_FORMAT,
                        DateUtil.DEFAULT_ANOTHER_FORMAT, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                        message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "初值登记日期"));
                    }
                    else {
                        entity.setInitValRegistDate(DateUtil.parse(list.get(index)));
                    }
                }
                if (CollectionUtils.isNotEmpty(message)) {
                    checkResult.add(new ExcelValidDto(list.get(1) + GlobalConstant.COLON + list.get(2),
                        String.valueOf(i + 1), StringUtil.joinString(message, "<br>")));
                }
                else {
                    entities.add(entity);
                }
            }
        }
        catch (Exception e) {
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
            checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                this.getMessage("REPORT_COMMON_FILE_E_0016")));
        }

        return checkResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        // 为了日期格式显示错误注释对日期的格式设置
        // if (StringUtil.equals(propName, "initValDate")
        // || StringUtil.equals(propName, "initValRegistDate")) {
        // if (value instanceof Date) {
        // cell.setCellValue(DateUtil.format((Date)value, DateUtil.DEFAULT_OTHER_FORMAT));
        // }
        // }
    }

    /**
     * 判断数量是否合法，返回错误信息
     *
     * @param number 数量
     * @param name 列名
     * @return 错误信息
     */
    private String checkNumber(String number, String name) {
        // 必须验证
        if (StringUtil.isEmpty(number)) {
            return this.getMessage("REPORT_COMMON_FILE_E_0001", name);
        }
        // 属性验证
        if (!CommonUtil.isFloatNumeric(number)) {
            return this.getMessage("REPORT_COMMON_FILE_E_0012", name);
        }
        BigDecimal num = new BigDecimal(number);
        // 长度验证
        if (num.precision() > 19 || num.scale() > 5) {
            return this.getMessage("REPORT_ASSETBOND_E_001", name, 19, 5);
        }
        // 不能小于0
        if ((new BigDecimal(number)).compareTo(BigDecimal.ZERO) < 0) {
            return this.getMessage("REPORT_COMMON_E_0023", name, 0);
        }

        return null;
    }

    /**
     * 导入文件之前检查文件中的数据在数据库中是否已经存在 如果已经存在，需要提示用户进行确认是否覆盖数据库
     *
     * @param fileName 导入的文件名名
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        AjaxResult webResult = new AjaxResult();
        if (!StringUtil.isEmptyList(dataList)) {
            if (CollectionUtils.isNotEmpty(dataList)) {
                List<EquiteBaseInfo001Dto> entities = convertToBean(dataList);
                List<EquiteBaseInfo001Dto> data = this.selectEquityBaseByKey(entities);
                if (data != null && data.size() > 0) {
                    Map<String, List<EquiteBaseInfo001Dto>> equityInfo001Map = entities.stream().collect(
                        JoyinCollectors.groupingBy(
                            x -> x.getBankCode() + GlobalConstant.UNDER_LINE + x.getProdId()
                                 + GlobalConstant.UNDER_LINE + x.getIndentifyMark()
                                 + GlobalConstant.UNDER_LINE + x.getCcy()));
                    // Collections.sort(data, Comparator.comparing(EquityInfo001Dto::getExcelNum));
                    // 将序号放入list中
                    List<String> excelNumList = new ArrayList<String>();
                    List<Integer> excelNumIntList = new ArrayList<Integer>();
                    for (EquiteBaseInfo001Dto equiteBaseInfo001Dto : data) {
                        List<EquiteBaseInfo001Dto> listTemp = equityInfo001Map.get(
                            equiteBaseInfo001Dto.getBankCode() + GlobalConstant.UNDER_LINE
                                                                                  + equiteBaseInfo001Dto.getProdId()
                                                                                  + GlobalConstant.UNDER_LINE
                                                                                  + equiteBaseInfo001Dto.getIndentifyMark()
                                                                                  + GlobalConstant.UNDER_LINE
                                                                                  + equiteBaseInfo001Dto.getCcy());
//                        excelNumList.add(listTemp.get(0).getExcelNum());
                        excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                    }
                    // 将list进行排序，并转化为string
//                    Collections.sort(excelNumList);
                    Collections.sort(excelNumIntList);
                    for (Integer rowNo : excelNumIntList) {
                        excelNumList.add(String.valueOf(rowNo));
                    }
                    // String messages = StringUtil.joinString(excelNumList, GlobalConstant.COMMA);
                    webResult.setMessage(
                        this.getMessage("CHB_ISCOVER_EQU",
                            StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
                }
                else {
                    // 防止在进行导入前check时进行了两次的弹框
                    webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
                }
            }
        }
        return webResult;
    }

    /**
     * 分配查询处理
     *
     * @param dataList 传入条件
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    private List<EquiteBaseInfo001Dto> selectEquityBaseByKey(List<EquiteBaseInfo001Dto> dataList) {

        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return equityBaseInfo001Mapper.getEquityBaseList(dataList);
        }
        // 导入大于1000时，返回值
        List<EquiteBaseInfo001Dto> equtiyList = new ArrayList<EquiteBaseInfo001Dto>();

        List<EquiteBaseInfo001Dto> newDataList = new ArrayList<EquiteBaseInfo001Dto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<EquiteBaseInfo001Dto> listTemp = null;
        for (int i = 0; i < part; i++ ) {
            listTemp = equityBaseInfo001Mapper.getEquityBaseList(
                newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = equityBaseInfo001Mapper.getEquityBaseList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    /**
     * excel数据转为java对象
     * <p>
     * 将excel导入的数据，按行转为EquiteBaseInfo001Dto对象，同时验证各字段值
     * </p>
     *
     * @param dataList excel导入的数据
     * @return 返回map形式
     */
    private List<EquiteBaseInfo001Dto> convertToBean(List<List<String>> dataList) {
        List<EquiteBaseInfo001Dto> entities = new ArrayList<EquiteBaseInfo001Dto>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            EquiteBaseInfo001Dto entity = new EquiteBaseInfo001Dto();
            entity.setBankCode(list.get(0));
            entity.setProdId(list.get(1));
            entity.setIndentifyMark(list.get(2));
            if(StringUtil.isNotEmpty(list.get(3))){
                entity.setCcy(EquityInfoCheckUtil.subDictcode(list.get(3), StringUtil.SPACE));
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }
}
