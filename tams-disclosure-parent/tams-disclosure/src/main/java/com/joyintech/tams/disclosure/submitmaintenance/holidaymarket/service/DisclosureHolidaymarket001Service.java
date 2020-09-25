/*
 * 文件名：Holidaymarket001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.submitmaintenance.holidaymarket.service;

import java.awt.Color;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbMktHolidayMapper;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.disclosure.busicommon.disclosure.system.regtimepoints.dto.RegTimePoints001Dto;
import com.joyintech.tams.disclosure.common.ReadConfigurationUtil;
import com.joyintech.tams.disclosure.common.TxtParseUtil;
import com.joyintech.tams.disclosure.common.XmlParseUtil;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.common.common.util.DateCalcUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.common.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.disclosure.information.config.reportpage.controller.ReportPageManage002Controller;
import com.joyintech.tams.disclosure.submitmaintenance.holidaymarket.dao.DisclosureHolidaymarket001Mapper;
import com.joyintech.tams.disclosure.submitmaintenance.holidaymarket.dto.DisclosureHolidaymarket001Dto;


/**
 * 节假日信息维护Service
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月25日
 * @see DisclosureHolidaymarket001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisclosureHolidaymarket001Service extends CommonExportService<DisclosureHolidaymarket001Dto> {

    /**
     * Holidaymarket001Mapper
     */
    @Autowired
    private DisclosureHolidaymarket001Mapper holidaymarket001Mapper;

    /**
     * CHB_MKT_HOLIDAY_NAME
     */
    private static final String MKT_HOLIDAY_NAME = "MKT_HOLIDAY";
    private Logger logger = LoggerFactory.getLogger(ReportPageManage002Controller.class);
    /**
     * Holidaymarket001Mapper
     */
    @Autowired
    private ChbMktHolidayMapper chbMktHolidayMapper;

    /**
     * 上游接口文件编码格式
     */
    @Value("${application.reportInterfFileEncoding}")
    public String reportInterfFileEncoding;
    /**
     * 上游接口解析分割符
     */
    @Value("${application.reportInterfSplitCharacter}")
    private String reportInterfSplitCharacter;

    /**
     * 查询节假日信息
     * @param param 查询参数
     * @return 权益基期信息列表
     * @throws Exception 异常
     */
    public Page<DisclosureHolidaymarket001Dto> searchForPage(Map<String, Object> param)
        throws Exception {
//        Page<DisclosureHolidaymarket001Dto> pageList = super.queryForPage(DisclosureHolidaymarket001Mapper.class, "queryForPage", param);
//        return pageList;
    	
    	 return restTemplate.postForPage(
                 ServiceApplication.VALUATION_SERVICE_NAME + "/DisclosureHolidaymarket001/findPage",
                 param, DisclosureHolidaymarket001Dto.class); 
    }

    public Map<String,Object> setDateByType(Map<String,Object> param){
        List<Date> checkDateList = new ArrayList<Date>();
        checkDateList = getCheckDateList(param.get("start").toString(),param.get("end").toString());
        param.put("checkDateList", checkDateList);
        param.put("status","E");
        if("holid".equals(param.get("type").toString())){
            param.put("isHoliday", 0);
        }else if("work".equals(param.get("type").toString())){
            param.put("isHoliday", 1);
        }
//        int result = holidaymarket001Mapper.setDateByType(param);
        int result = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisclosureHolidaymarket001/setDateByType",
                param, Integer.class); 
        
        
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", result);
        resultMap.put("successDate", checkDateList);
        return resultMap;
    }

    public List<DisclosureHolidaymarket001Dto> initHolid(Map<String,Object> param){
    	
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisclosureHolidaymarket001/initHolid",
                param, DisclosureHolidaymarket001Dto.class); 
//        return holidaymarket001Mapper.initHolid(param);
    }

    public static void main(String[] args) {


    }

    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     * @throws Exception 异常
     */
    public List<ExcelValidDto> importData(String fileName)
        throws Exception {
    	
    	
    	return restTemplate.getForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisclosureHolidaymarket001/importData?fileName="+fileName,
                ExcelValidDto.class); 
//        if (StringUtil.isNotEmpty(fileName)) {
//            List<List<String>> dataList = ExcelUtil.readToList(
//                FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 0);
//            if (null != dataList) {
//                List<DisclosureHolidaymarket001Dto> entities = new ArrayList<>(dataList.size());
//                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities);
//                if(checkResult.isEmpty()) {
//                    entities.forEach(item -> {
//                        item.setStatus(DisclosureDictCodeList.维护数据状态_有效.getValue());
//                        holidaymarket001Mapper.insertSelective(item);
//                    });
//                }
//                return checkResult;
//            }
//        }
//        return null;
    }

    /**
     * 根据日期控件选择的时间计算其中所有天
     * <一句话功能简述>
     * <功能详细描述>
     * @param start 开始时间
     * @param end 截止时间（截止时间当天不包含）
     * @return
     * @see
     * @since 1.0
     */
    private List<Date> getCheckDateList(String start,String end){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> checkDateList = new ArrayList<Date>();
        try {
            Date d = sf.parse(start);
            Date d1 = sf.parse(end);
            Date temp = null;
            long l = d1.getTime()-d.getTime();
            long ms = 1000;
            int days = (int) (l/(24*60*60*ms));
            for(int i=0;i<days;i++){
                temp = new Date(d.getTime()+(i*24*60*60*ms));
                checkDateList.add(temp);
            }
        } catch (ParseException e) {
        	logger.error("error:",e);
        }
        return checkDateList;
    }

    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为Holidaymarket001Dto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,
                                                List<DisclosureHolidaymarket001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            if (null == list || list.size() < 4) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                    this.getMessage("REPORT_COMMON_E_0057")));
                continue;
            }
            DisclosureHolidaymarket001Dto entity = new DisclosureHolidaymarket001Dto();
            // 验证消息
            List<String> message = new ArrayList<>();
            // 日期
            if (StringUtil.isEmpty(list.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "日期"));
            }
            else {
                entity.setBusiDate(new Date(list.get(0)));
            }
            // 交易市场
            if (StringUtil.isEmpty(list.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "交易市场"));
            }
            else {
                entity.setMarketCode(list.get(1));
            }
            // 是否节假日
            if (StringUtil.isEmpty(list.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "是否节假日"));
            }
            else {
                entity.setIsHoliday(list.get(2));
            }
            //部门代码
            if (StringUtil.isEmpty(list.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "部门代码"));
            }
            else {
                entity.setDeptCode(list.get(3));
            }


            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                    StringUtil.joinString(message, GlobalConstant.COMMA)));
            }
            else {
                entities.add(entity);
            }
        }
        return checkResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        // 测试列子 把可用数量扩大10倍 TODO
        if(StringUtil.equals(propName, "availableQuantity") && value instanceof BigDecimal) {
            BigDecimal cellValue = ArithmeticUtil.multiply((BigDecimal)value, BigDecimal.TEN);
            cell.setCellValue(cellValue.doubleValue());
        }
    }

    @Override
    public void setCellStyle(Workbook wb, CellStyle cellStyle) {
        // 调整背景色为白色
        if(cellStyle instanceof XSSFCellStyle) {
            XSSFColor color = new XSSFColor(Color.WHITE);
            cellStyle.setFillBackgroundColor(color.getIndex());
            cellStyle.setFillForegroundColor(color.getIndex());
        }
        else {
            cellStyle.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        }
        // 左对齐
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        // 不加粗
        Font font = wb.getFontAt(cellStyle.getFontIndex());
        font.setBold(false);
    }
    /**
     *
     * 获取年份下拉框的数据源
     * <功能详细描述>
     * @param num 前后多少年
     * @param year 现在的年份
     * @return 选择
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getYearSelect(int num,String year){
        List<SelectOptionDto> result= new ArrayList<SelectOptionDto>();
        if(num<=0){
            //初始化
            num=50;
        }
        int nowyear = Integer.parseInt(year);
        for(int start=nowyear-num,end=nowyear+num; start<end; start++){
            SelectOptionDto dto = new SelectOptionDto();
            dto.setId(String.valueOf(start));
            dto.setText(String.valueOf(start));
            result.add(dto);
        }
        return result;
    }
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param date                 传入日期
     * @param regTimePoints001Dto  报送时点配置
     * @param map                  返回参数
     * @param flag                  01 新建、02 修改 、03 删除
     * @return List<Date>           返回报送截止日
     * @see
     * @since 1.0
     * @author add by 汪敏
     */
//    public  List<Date> getChbSubmitDeadlineByMark(Date date,RegTimePoints001Dto regTimePoints001Dto,Map<String,List<Date>> map
//                                                  ,String flag){
//        String dates = DateUtil.format(date);
//        String key = dates+ "|" + regTimePoints001Dto.getReportCode() ;
//        //中债报送截止日
//        DisclosureHolidaymarket001Dto holidaymarket001DtoChb = new DisclosureHolidaymarket001Dto();
//        //行内报送截止日
//        DisclosureHolidaymarket001Dto holidaymarket001Dto = new DisclosureHolidaymarket001Dto();
//        if(DisclosureDictCodeList.报送数据状态_新建.getValue().equals(flag)){
//            if(DisclosureDictCodeList.报送时点类型_每周.getValue().equals(regTimePoints001Dto.getSubmitTimetype())){//中债报送截止日
////                holidaymarket001DtoChb = new Holidaymarket001Dto();
////                //行内报送截止日
////                holidaymarket001Dto = new Holidaymarket001Dto();
//                //传入日期为周几
//                int weekDay = DateUtil.getWeekDay(date);
//                //若中债配置的时间大于当前，则直接取差值;反之则取差值+7
//                if(regTimePoints001Dto.getSubmitPointBank().intValue()>=weekDay){
//                    holidaymarket001DtoChb.setBusiDate(DateUtil.addDay(date,regTimePoints001Dto.getSubmitPointBank().intValue()-weekDay ));
//                    holidaymarket001Dto.setBusiDate(DateUtil.addDay(date,regTimePoints001Dto.getSubmitPointBond().intValue()-weekDay ));
//                }
//                else{
//                    holidaymarket001DtoChb.setBusiDate(DateUtil.addDay(date,regTimePoints001Dto.getSubmitPointBank().intValue()+7-weekDay ));
//                    holidaymarket001Dto.setBusiDate(DateUtil.addDay(date,regTimePoints001Dto.getSubmitPointBond().intValue()+7-weekDay ));
//                }
//            }
//            else if(DisclosureDictCodeList.报送时点类型_每月.getValue().equals(regTimePoints001Dto.getSubmitTimetype())){
//
//            }
//            else{
//                String type;
//                if(DisclosureDictCodeList.推送方式_前.getValue().equals(regTimePoints001Dto.getSubmitRequire2())){
//                    type = "01";
//                }
//                else {
//                    type = "02";
//                }
//                //当中债配置时间为0时
//                if(regTimePoints001Dto.getSubmitPointBank().compareTo(BigDecimal.ZERO)==0){
//                    holidaymarket001DtoChb = holidaymarket001Mapper.getChbDeadlineZero(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),date,type);
//                    if(DisclosureDictCodeList.推送方式_后.getValue().equals(regTimePoints001Dto.getSubmitRequire2())&&
//                        regTimePoints001Dto.getSubmitPointBond().compareTo(BigDecimal.ZERO)<0){
//                        type = "01";
//                    }
//                    holidaymarket001Dto =  holidaymarket001Mapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
//                        dates, type, regTimePoints001Dto.getSubmitPointBond().intValue());
//                }
//                //当不为0时
//                else{
//                    holidaymarket001DtoChb =  holidaymarket001Mapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
//                        dates, type, regTimePoints001Dto.getSubmitPointBank().intValue());
//                    holidaymarket001Dto =  holidaymarket001Mapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
//                        dates, type, regTimePoints001Dto.getSubmitPointBond().intValue());
//                }
//
//            }
//            //中债报送截止日非空处理
//            holidaymarket001DtoChb = this.getNullDeadline(holidaymarket001DtoChb,date,regTimePoints001Dto.getSubmitPointBank().intValue(),
//                regTimePoints001Dto.getSubmitRequire2());
//            //行内报送截止日非空处理
//            holidaymarket001Dto = this.getNullDeadline(holidaymarket001Dto,date,regTimePoints001Dto.getSubmitPointBond().intValue(),
//                regTimePoints001Dto.getSubmitRequire2());
//        }
//        //修改与删除时中债报送截止日与行内报送截止日相同
//        else if(DisclosureDictCodeList.报送数据状态_修改.getValue().equals(flag)){
//            holidaymarket001DtoChb =  holidaymarket001Mapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
//                dates, "02", regTimePoints001Dto.getUpdateSubmitPoint().intValue());
//            //中债报送截止日非空处理
//            holidaymarket001DtoChb = this.getNullDeadline(holidaymarket001DtoChb,date,regTimePoints001Dto.getUpdateSubmitPoint().intValue(),
//                regTimePoints001Dto.getSubmitRequire2());
//            BeanUtil.copyPropertiesIgnoreNull(holidaymarket001Dto, holidaymarket001DtoChb);
//        }
//        else if(DisclosureDictCodeList.报送数据状态_删除.getValue().equals(flag)){
//            holidaymarket001DtoChb =  holidaymarket001Mapper.getChbDeadline(DisclosureDictCodeList.交易市场_中国法定节假日.getValue(),
//                dates, "02", regTimePoints001Dto.getDeleteSubmingPoint().intValue());
//            //中债报送截止日非空处理
//            holidaymarket001DtoChb = this.getNullDeadline(holidaymarket001DtoChb,date,regTimePoints001Dto.getDeleteSubmingPoint().intValue(),
//                regTimePoints001Dto.getSubmitRequire2());
//            BeanUtil.copyPropertiesIgnoreNull(holidaymarket001Dto, holidaymarket001DtoChb);
//        }
//        //处理报送截止日
//        List<Date> dateList = new ArrayList<Date>();
//        dateList.add(DateCalcUtil.getEndOfDay(holidaymarket001DtoChb.getBusiDate()));
//        //行内与中债报送截止日相差
//        dateList.add(DateCalcUtil.getEndOfDay(holidaymarket001Dto.getBusiDate()));
//        map.put(key, dateList);
//        return dateList;
//    }
//    /**
//     * 节假日非空判断
//     * @param holidaymarket001Dto  节假日
//     * @param date                 传入日期
//     * @param days                 改变天数
//     * @param flag                 前后
//     * @return holidaymarket001Dto  返回
//     * @see
//     * @since 1.0
//     */
//    public DisclosureHolidaymarket001Dto getNullDeadline(DisclosureHolidaymarket001Dto holidaymarket001Dto,Date date,int days,String flag){
//        if(flag.equals(DisclosureDictCodeList.推送方式_前.getValue())){
//            days = -1*days;
//        }
//        if(holidaymarket001Dto==null){
//            holidaymarket001Dto = new DisclosureHolidaymarket001Dto();
//            holidaymarket001Dto.setBusiDate(DateUtil.addDay(date, days));
//        }
//        return holidaymarket001Dto;
//    }
//
//    /**
//     * 从接口中获取产品交易信息1.调用共通方法解析传入的接口文件 2.遍历解析后的数据，逐条插入验证（暂定） 3.验证中债需要验证的模块，通过则赋有效状态；不通过，则显示待处理状态
//     * 4.验证通过后，判断是否新增数据 4。1 若为新增数据，则直接insert 4.2 若非新增数据，则需读取是否覆盖开关 4.2.1 若开关打开，只更新数据配置项中的字段 4.2.2
//     * 若开关关闭，且数据库中为接口维护，则全量更新；若数据库中为手动维护，则不更新
//     *
//     * @param in 传入输入流
//     * @param type 数据来源
//     * @param tmpPath 临时路径
//     * @param suffix 文件后缀名
//     * @param monitorInfoDto 读取上游文件监控信息dto
//     * @return string 处理成功失败
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    public String getHolidaymarketByInterf(InputStream in, String type, String tmpPath, String suffix, ChbFileReadMonitorInfo monitorInfoDto)
//        throws Exception {
//        // 判断是xml文件 还是txt文件
//        List<Map<String, String>> dealList = null;
//        if ("xml".equals(suffix)) {
//            dealList = XmlParseUtil.parse(in);
//        }
//        else if ("txt".equals(suffix)) {
//            dealList = TxtParseUtil.parse(in, reportInterfFileEncoding, reportInterfSplitCharacter);
//        }
//        // 从配置文件中读取需要覆盖的配置项 01覆盖02不覆盖
//        String[] result = ReadConfigurationUtil.readPropertiesByGetResourceAsStream(
//            MKT_HOLIDAY_NAME + "." + type).split(GlobalConstant.COMMA);
//        // 解析成功或失败的标识
//        boolean success = true;
//        if (dealList != null) {
//            monitorInfoDto.setFileDataCount(BigDecimal.valueOf(dealList.size()));
//            if (dealList.size() <= 1000) {
//                success = excuteValueInfoByStep(dealList, type, result, null);
//            }
//            else {
//                // 创建一个线程池
//                ExecutorService service = new JoyinThreadPoolExecutor(20);
//                List<Callable<Boolean>> tasks = new ArrayList<>();
//                if (null != dealList) {
//                    int start = 0;
//                    int end = 0;
//                    int size = dealList.size();
//                    int max = (int)Math.ceil((double)size / 1000);
//                    for (int i = 0; i < max; i++ ) {
//                        start = end;
//                        end = Math.min(size, 1000 * (i + 1));
//                        List<Map<String, String>> subList = dealList.subList(start, end);
//                        tasks.add(new Callable<Boolean>() {
//
//                            @Override
//                            public Boolean call()
//                                throws Exception {
//                                return excuteValueInfoByStep(subList, type, result, tmpPath);
//                            }
//                        });
//                    }
//                }
//                // 为线程池添加任务
//                List<Future<Boolean>> taskResult = service.invokeAll(tasks);
//                for (Future<Boolean> res : taskResult) {
//                    success = success && res.get();
//                }
//                // 任务结束，停止线程池的所有线程
//                service.shutdown();
//            }
//        }
//        // 数据解析完成，并导入数据库成功后，将ftp文件修改名称
//        if (success) {
//            return "success";
//        }
//        return "fail";
//    }
//
//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param dealList 数据list
//     * @param type 数据来源
//     * @param result 参数
//     * @param tmpPath 生成的临时文件存放路径
//     * @return 是否成功
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    public boolean excuteValueInfoByStep(List<Map<String, String>> dealList, String type,
//                                        String[] result, String tmpPath) throws Exception {
//
//        // 新增数据list
//        List<DisclosureHolidaymarket001Dto> listA = new ArrayList<DisclosureHolidaymarket001Dto>();
//        List<DisclosureHolidaymarket001Dto> listE = new ArrayList<DisclosureHolidaymarket001Dto>();
//
//        List<ExcelValidDto> resultInfo = checkBaseInfo(dealList,listE,listA);
//        //更新或插入数据
//        this.updateAndInsertAllList(listE);
//        if(StringUtil.isEmptyList(resultInfo)){
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 对数据的基础性校验并且放在对应的容器里面
//     * <功能详细描述>
//     * @param prodListMap 所有的产品的容器
//     * @param infoList 待检验的容器
//     * @param listE 成功的数据
//     * @param listA 失败的数据
//     * @return 校验结果
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//    private List<ExcelValidDto> checkBaseInfo(List<Map<String, String>> infoList,
//                                              List<DisclosureHolidaymarket001Dto> listE,
//                                              List<DisclosureHolidaymarket001Dto> listA
//                                              ) throws Exception {
//      //存储了根据产品id作区分的分类数据
//        //用来判断数据的容器
//        for(Map<String, String> map : infoList){
//            //是否保含必要信息
//            if(StringUtil.isNotEmpty(map.get("BUSI_DATE"))
//                && StringUtil.isNotEmpty(map.get("MARKET_CODE"))
//                && StringUtil.isNotEmpty(map.get("IS_HOLIDAY"))){
//                DisclosureHolidaymarket001Dto dto = new DisclosureHolidaymarket001Dto();
//                //以下为一条数据的基本辨识条件意思是数据所必须的情况否则不考虑
//                //设置交易市场
//                dto.setMarketCode(map.get("MARKET_CODE"));
//                // 是否节假日
//                dto.setIsHoliday(map.get("IS_HOLIDAY"));
//                // 公布日期
//                dto.setBusiDate(DateUtil.parse(map.get("BUSI_DATE")));
//                // 经办人
//                dto.setAgent(map.get("AGENT"));
//                dto.setStatus(DisclosureDictCodeList.维护数据状态_有效.getValue());
//                listE.add(dto);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 删除与即将导入的数据相关的待处理数据
//     * <功能详细描述>
//     * @param listE 有效的list
//     * @see
//     * @since 1.0
//     */
//    private void updateAndInsertAllList(List<DisclosureHolidaymarket001Dto> listE) {
//        for (DisclosureHolidaymarket001Dto item : listE) {
//            try {
//                holidaymarket001Mapper.insertSelective(item);
//            }
//            catch (Exception e) {
//                // 对于数据库中已经存在的数据，更新覆盖
//                ChbMktHoliday chbMktHoliday = new ChbMktHoliday();
//                chbMktHoliday.setAgent(item.getAgent());
//                chbMktHoliday.setBusiDate(item.getBusiDate());
//                chbMktHoliday.setMarketCode(item.getMarketCode());
//                chbMktHoliday.setIsHoliday(item.getIsHoliday());
//                chbMktHoliday.setStatus(item.getStatus());
//                chbMktHolidayMapper.updateByPrimaryKey(chbMktHoliday);
//            }
//        }
//    }
}
