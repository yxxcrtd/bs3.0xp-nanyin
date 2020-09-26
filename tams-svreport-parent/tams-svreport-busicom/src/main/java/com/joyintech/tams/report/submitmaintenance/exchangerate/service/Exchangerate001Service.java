/*
 * 文件名：EquityBaseInfo001Service.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.submitmaintenance.exchangerate.service;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.ReadConfigurationUtil;
import com.joyintech.tams.report.common.TxtParseUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.common.base.dao.ChbMstExchangeInfoMapper;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.common.base.entity.ChbMstExchangeInfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dao.Exchangerate001Mapper;
import com.joyintech.tams.report.submitmaintenance.exchangerate.dto.Exchangerate001Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 *
 * @author zyf
 * @version 1.0 2017年9月18日
 * @see Exchangerate001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Exchangerate001Service extends CommonExportService<Exchangerate001Dto> {

    /**
     * 日志
     */
    private  Logger logger = LoggerFactory.getLogger(Exchangerate001Service.class);

    /**
     * EquityBusiDetails001Mapper
     */
    @Autowired
    private Exchangerate001Mapper exchangerate001Mapper;

    /**
     * ChbMstExchangeInfoMapper
     */
    @Autowired
    private ChbMstExchangeInfoMapper chbMstExchangeInfoMapper;

    /**
     * CHB_MST_EXCHANGE_INFO_NAME
     */
    private static final String MST_EXCHANGE_INFO_NAME = "MST_EXCHANGE_INFO";

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
     * 查询汇率行情维护信息列表
     * @param param 查询参数
     * @return 权益基期信息列表
     */
    public Page<Exchangerate001Dto> searchForPage(Map<String, Object> param) {
        //变更查询条件为多选，查询日期为日期区间
        //币种
        String ccyOriginal = (String)param.get("ccyOriginal");
        param.put("ccyOriginalList", StringUtil.splitToList(GlobalConstant.COMMA, ccyOriginal));
        //折算币种
        String ccyConvert = (String)param.get("ccyConvert");
        param.put("ccyConvertList", StringUtil.splitToList(GlobalConstant.COMMA, ccyConvert));
        Page<Exchangerate001Dto> pageList = super.queryForPage(Exchangerate001Mapper.class, "queryForPage", param);
        for (Exchangerate001Dto exchangerate001Dto : pageList.getRoot()) {
            exchangerate001Dto.setStatusName(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, exchangerate001Dto.getStatus()));
            exchangerate001Dto.setCcyOriginalName(SysDataUtil.getNameFromDataDict(DictCode.币种_中债, exchangerate001Dto.getCcyOriginal()));
            exchangerate001Dto.setCcyConvertName(SysDataUtil.getNameFromDataDict(DictCode.币种_中债, exchangerate001Dto.getCcyConvert()));
        }
        return pageList;
    }


    /**
     * 查询所有内容，不分页
     * <p>导出功能需要导出查询条件的所有内容</p>
     * @param param 画面查询参数
     * @return 查询结果列表
     */
    public List<Exchangerate001Dto> queryReportList(Map<String, Object> param) {
        String keys = (String)param.get("keys");
        param.put("keys", StringUtil.splitToList(GlobalConstant.COMMA, keys));
        //币种
        String ccyOriginal = (String)param.get("ccyOriginal");
        param.put("ccyOriginalList", StringUtil.splitToList(GlobalConstant.COMMA, ccyOriginal));
        //折算币种
        String ccyConvert = (String)param.get("ccyConvert");
        param.put("ccyConvertList", StringUtil.splitToList(GlobalConstant.COMMA, ccyConvert));
        List<Exchangerate001Dto> pageList = exchangerate001Mapper.queryForPage(param);
        for (Exchangerate001Dto exchangerate001Dto : pageList) {
            exchangerate001Dto.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, exchangerate001Dto.getStatus()));
            exchangerate001Dto.setCcyOriginal(SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, exchangerate001Dto.getCcyOriginal()));
            exchangerate001Dto.setCcyConvert(SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, exchangerate001Dto.getCcyConvert()));
        }
        return pageList;
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
                List<String> keyList = StringUtil.splitToList("|",key);
                Exchangerate001Dto exchangerate001Dto = new Exchangerate001Dto();
                //Exchangerate001Dto.setStatus(DictCodeList.数据状态_删除.getValue());
                exchangerate001Dto.setCcyOriginal(keyList.get(0));
                exchangerate001Dto.setCcyConvert(keyList.get(1));
                exchangerate001Dto.setPublishDate(DateUtil.parse(keyList.get(2)));
                exchangerate001Mapper.deleteData(exchangerate001Dto);
                //exchangerate001Mapper.updateByPrimaryKeySelective(Exchangerate001Dto);
            });
        }
    }

    /**
     * 导入文件之前检查文件中的数据在数据库中是否已经存在
     * 如果已经存在，需要提示用户进行确认是否覆盖数据库
     *
     * @param fileName 导入的文件名名
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
        AjaxResult webResult = new AjaxResult();

        if (StringUtil.isNotEmpty(fileName)) {
            List<List<String>> dataList = null;
            try {
                dataList = ExcelUtil.readToList(
                    FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 0);
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(),e);
            }
            if (null != dataList) {
                List<Exchangerate001Dto> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities);
                if(checkResult.isEmpty() && CollectionUtils.isNotEmpty(entities)) {
                    int dataCount = this.exchangerate001Mapper.selectDataCount(entities);
                    if (dataCount > 0) {
                        webResult.setMessage(this.getMessage("REPORT_ASSET_C_0003"), MessageType.确认消息);
                    }
                    else{
                        webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
                    }
                }
                else {
                    webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
                }
            }
            else{
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        }

        return webResult;
    }

    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *
     * @param fileName 导入的文件名名
     * @return 导入结果
     */
    public List<ExcelValidDto> importData(String fileName) {
        if (StringUtil.isNotEmpty(fileName)) {
            List<String> headList = new ArrayList<String>();
            headList.add("原币种");
            headList.add("折算币种");
            headList.add("公布日期");
            headList.add("汇率");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(fileName, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            List<List<String>> dataList = null;
            try {
                dataList = ExcelUtil.readToList(
                    FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 0);
            } catch (FileNotFoundException e1) {
                logger.error(e1.getMessage(),e1);
            }
            if (null != dataList) {
                List<Exchangerate001Dto> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult = convertToEntity(dataList, entities);
                if(checkResult.isEmpty()) {
                    entities.forEach(item -> {
                        item.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());

                        try {
                            exchangerate001Mapper.insertSelective(item);
                        }
                        catch (Exception e) {
                            // 对于数据库中已经存在的数据，更新覆盖
                            ChbMstExchangeInfo chbMstExchangeInfo = new ChbMstExchangeInfo();
                            chbMstExchangeInfo.setCcyOriginal(item.getCcyOriginal());
                            chbMstExchangeInfo.setCcyConvert(item.getCcyConvert());
                            chbMstExchangeInfo.setPublishDate(item.getPublishDate());
                            chbMstExchangeInfo.setExchangePrice(item.getExchangePrice());
                            chbMstExchangeInfo.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                            chbMstExchangeInfoMapper.updateByPrimaryKey(chbMstExchangeInfo);
                        }
                    });
                }
                return checkResult;
            }
        }
        return null;
    }

    /**
     * 从接口中获取产品交易信息1.调用共通方法解析传入的接口文件 2.遍历解析后的数据，逐条插入验证（暂定） 3.验证中债需要验证的模块，通过则赋有效状态；不通过，则显示待处理状态
     * 4.验证通过后，判断是否新增数据 4。1 若为新增数据，则直接insert 4.2 若非新增数据，则需读取是否覆盖开关 4.2.1 若开关打开，只更新数据配置项中的字段 4.2.2
     * 若开关关闭，且数据库中为接口维护，则全量更新；若数据库中为手动维护，则不更新
     *
     * @param in 传入输入流
     * @param type 数据来源
     * @param tmpPath 临时路径
     * @param suffix 文件后缀名
     * @param monitorInfoDto 读取上游文件 监控信息dto
     * @return string 处理成功失败
     * @see
     * @since 1.0
     */
    public String getExchangerateByInterf(InputStream in, String type, String tmpPath, String suffix, ChbFileReadMonitorInfo monitorInfoDto){
        // 判断是xml文件 还是txt文件
        List<Map<String, String>> dealList = null;
        if ("xml".equals(suffix)) {
            try {
                dealList = XmlParseUtil.parse(in);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        }
        else if ("txt".equals(suffix)) {
            try {
                dealList = TxtParseUtil.parse(in, reportInterfFileEncoding, reportInterfSplitCharacter);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        }
        // 从配置文件中读取需要覆盖的配置项 01覆盖02不覆盖
        String[] result = ReadConfigurationUtil.readPropertiesByGetResourceAsStream(
            MST_EXCHANGE_INFO_NAME + "." + type).split(GlobalConstant.COMMA);
        // 解析成功或失败的标识
        boolean success = true;
        if (dealList != null) {
            monitorInfoDto.setFileDataCount(BigDecimal.valueOf(dealList.size()));
            if (dealList.size() <= 1000) {
                success = excuteValueInfoByStep(dealList, type, result, null);
            }
            else {
                // 创建一个线程池
                ExecutorService service = new JoyinThreadPoolExecutor(20);
                List<Callable<Boolean>> tasks = new ArrayList<>();
                if (null != dealList) {
                    int start = 0;
                    int end = 0;
                    int size = dealList.size();
                    int max = (int)Math.ceil((double)size / 1000);
                    for (int i = 0; i < max; i++ ) {
                        start = end;
                        end = Math.min(size, 1000 * (i + 1));
                        List<Map<String, String>> subList = dealList.subList(start, end);
                        tasks.add(new Callable<Boolean>() {

                            @Override
                            public Boolean call() {
                                return excuteValueInfoByStep(subList, type, result, tmpPath);
                            }
                        });
                    }
                }
                // 为线程池添加任务
                List<Future<Boolean>> taskResult;
                try {
                    taskResult = service.invokeAll(tasks);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(),e);
                    return "fail";
                }
                for (Future<Boolean> res : taskResult) {
                    try {
                        success = success && res.get();
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(),e);
                        return "fail";
                    }
                }
                // 任务结束，停止线程池的所有线程
                service.shutdown();
            }
        }
        // 数据解析完成，并导入数据库成功后，将ftp文件修改名称
        if (success) {
            return "success";
        }
        return "fail";
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dealList 数据list
     * @param type 数据来源
     * @param result 参数
     * @param tmpPath 生成的临时文件存放路径
     * @return 是否成功
     * @see
     * @since 1.0
     */
    public boolean excuteValueInfoByStep(List<Map<String, String>> dealList, String type,
                                        String[] result, String tmpPath) {

        // 新增数据list
        List<Exchangerate001Dto> listA = new ArrayList<Exchangerate001Dto>();
        List<Exchangerate001Dto> listE = new ArrayList<Exchangerate001Dto>();

        List<ExcelValidDto> resultInfo = checkBaseInfo(dealList,listE,listA);
        //更新或插入数据
        this.updateAndInsertAllList(listE,listA);
        if(StringUtil.isEmptyList(resultInfo)){
            return true;
        }
        return false;
    }

    /**
     * 对数据的基础性校验并且放在对应的容器里面
     * <功能详细描述>
     * @param infoList 待检验的容器
     * @param listE 成功的数据
     * @param listA 失败的数据
     * @return 校验结果
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkBaseInfo(List<Map<String, String>> infoList,
                                              List<Exchangerate001Dto> listE,
                                              List<Exchangerate001Dto> listA
                                              ) {
      //存储了根据产品id作区分的分类数据
        //用来判断数据的容器
        Map<String ,List<Exchangerate001Dto>>box=new HashMap<String ,List<Exchangerate001Dto>>();
        List<ExcelValidDto> reslutList=new ArrayList<ExcelValidDto>();
        for(Map<String, String> map : infoList){
            //是否保含必要信息
            if(StringUtil.isNotEmpty(map.get("CYY_ORIGINAL"))
                && StringUtil.isNotEmpty(map.get("CYY_CONVERT"))
                && StringUtil.isNotEmpty(map.get("PUBLISH_DATE"))){
                Exchangerate001Dto dto = new Exchangerate001Dto();
             // 验证消息
                List<String> message = new ArrayList<>();
                //以下为一条数据的基本辨识条件意思是数据所必须的情况否则不考虑
                //设置原币种
                dto.setCcyOriginal(map.get("CYY_ORIGINAL"));
                // 折算币种
                dto.setCcyConvert(map.get("CYY_CONVERT"));
                // 公布日期
                dto.setPublishDate(DateUtil.parse(map.get("PUBLISH_DATE")));
                // 汇率
                if (StringUtil.isEmpty(map.get("EXCHANGE_PRICE"))) {
                    message.add(this.getMessage("REPORT_COMMON_E_0057", "汇率"));
                }
                dto.setExchangePrice(new BigDecimal(map.get("EXCHANGE_PRICE")));
                // 经办人
                dto.setAgent(map.get("AGENT"));
                List<Exchangerate001Dto> entities = new ArrayList<Exchangerate001Dto>();
                entities.add(dto);
                if (CollectionUtils.isNotEmpty(message)) {
                    dto.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
                    listA.add(dto);
                    reslutList.add(new ExcelValidDto(dto.getCcyOriginal() + GlobalConstant.COLON + dto.getCcyConvert(),
                        "", StringUtil.joinString(message, "<br>")));
                }
                else {
                    dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    listE.add(dto);
                }
            }
        }
        return null;
    }

    /**
     * 删除与即将导入的数据相关的待处理数据
     * <功能详细描述>
     * @param listE 有效的list
     * @param listA 待处理的list
     * @see
     * @since 1.0
     */
    private void updateAndInsertAllList(List<Exchangerate001Dto> listE,
                             List<Exchangerate001Dto> listA) {
        //生成需要删除的数据的条件
        //所有的数据
        List<Exchangerate001Dto> listAll= new ArrayList<Exchangerate001Dto>();
        //将所有的数据放入到listAll中
        listAll.addAll(listA);
        listAll.addAll(listE);
        for (Exchangerate001Dto item : listAll) {
            try {
                exchangerate001Mapper.insertSelective(item);
            }
            catch (Exception e) {
                // 对于数据库中已经存在的数据，更新覆盖
                ChbMstExchangeInfo chbMstExchangeInfo = new ChbMstExchangeInfo();
                chbMstExchangeInfo.setCcyOriginal(item.getCcyOriginal());
                chbMstExchangeInfo.setCcyConvert(item.getCcyConvert());
                chbMstExchangeInfo.setPublishDate(item.getPublishDate());
                chbMstExchangeInfo.setExchangePrice(item.getExchangePrice());
                chbMstExchangeInfo.setStatus(item.getStatus());
                chbMstExchangeInfoMapper.updateByPrimaryKey(chbMstExchangeInfo);
            }
        }
    }

    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为Exchangerate001Dto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,
                                                List<Exchangerate001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            if (null == list || list.size() != 4) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1),
                    this.getMessage("REPORT_COMMON_FILE_E_0001", "导入行内容")));
                continue;
            }
            Exchangerate001Dto entity = new Exchangerate001Dto();
            // 验证消息
            List<String> message = new ArrayList<>();
            String ccyOriginal = SysDataUtil.getZZDictCodeFromName(DictCode.币种_中债, list.get(0));
            String ccyConvert = SysDataUtil.getZZDictCodeFromName(DictCode.币种_中债, list.get(1));
            //数据库币种缓存
            Map<String, String> dictCodeMap = SysDataUtil.getSysDictDataMap(DictCode.币种_中债);
            //判断折算币种是否与原币种相同
            if(StringUtil.equals(ccyOriginal, ccyConvert)){
                message.add(getMessage("CHB_INVESTHOLD_CHECKCCY"));
            }
            // 原币种
            if (StringUtil.isEmpty(ccyOriginal)) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "原币种"));
            }
            else if (!dictCodeMap.containsKey(ccyOriginal)) {
                    message.add(getMessage("CHBCONNECT_E_0002", "原币种"));
                }
            else{
                entity.setCcyOriginal(ccyOriginal);
            }

            // 折算币种
            if (StringUtil.isEmpty(ccyConvert)) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "折算币种"));
            }
            else if(!dictCodeMap.containsKey(ccyConvert)) {
                    message.add(getMessage("CHBCONNECT_E_0002", "折算币种"));
                }
            else{
                entity.setCcyConvert(ccyConvert);
            }
            // 公布日期
            if (StringUtil.isEmpty(list.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "公布日期"));
            }
            else if (!DateUtil.isDateStr(list.get(2), DateUtil.DEFAULT_OTHER_FORMAT,
                DateUtil.DEFAULT_ANOTHER_FORMAT,DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "公布日期"));
            }
            else {
                String temp = list.get(2).replaceAll("\\/", "").replaceAll("\\-", "");
                String pattern = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?" +
                        "((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|" +
                        "(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" +
                        "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?" +
                        "((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +
                        "[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))" +
                        "(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
                if (!Pattern.compile(pattern).matcher(temp).matches()) {
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "公布日期"));
                }
                else{
                    entity.setPublishDate(DateUtil.parse(list.get(2)));
                }
            }
            // 汇率
            if (StringUtil.isEmpty(list.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_E_0057", "汇率"));
            }
            // 汇率属性验证
            else if (!CommonUtil.isFloatNumeric(list.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_E_0003", "汇率"));
            }
            else {
                BigDecimal num = new BigDecimal(list.get(3));
                if (num.compareTo(BigDecimal.ZERO) <= 0) {
                    message.add(this.getMessage("REPORT_COMMON_E_0059", "汇率", "0"));
                }
                else if(num.scale()>6||(num.precision()-num.scale())>13){
                	message.add(this.getMessage("REPORT_ASSETBOND_E_001","汇率","13","6"));
                }
                else {
                    entity.setExchangePrice(new BigDecimal(list.get(3)));
                }
            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+2),
                    StringUtil.joinString(message, "<br>")));
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
		if (StringUtil.startsWith(propName, "exchangePrice")) {
			BigDecimal cellValue = (BigDecimal)value;
            cell.setCellValue(cellValue.setScale(6).toString());
		}
		if (StringUtil.startsWith(propName, "publishDate")) {
			cell.setCellValue(DateUtil.format((Date)value));
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
}
