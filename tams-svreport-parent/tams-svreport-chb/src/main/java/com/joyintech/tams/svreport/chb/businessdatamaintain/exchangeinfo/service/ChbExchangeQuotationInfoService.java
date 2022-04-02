/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.MstExchargeQuoteMapper;
import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.dao.ChbExchangeQuotationInfoMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.exchangeinfo.dto.ChbExchangeQuotationInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 汇率行情service 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ChbExchangeQuotationInfoService
 * @since 1.0
 */

@Service
public class ChbExchangeQuotationInfoService extends CommonExportService<ChbExchangeQuotationInfoDto> implements BaseProcessService<ChbExchangeQuotationInfoDto>{

    private static Logger logger = LoggerFactory.getLogger(ChbExchangeQuotationInfoService.class);

    public static String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/exchangeinfo/conf/export_conf.xml";
    @Resource
    private ChbExchangeQuotationInfoMapper chbExchangeQuotationInfoMapper;
    @Resource
    private MstExchargeQuoteMapper mstExchargeQuoteMapper;

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
    public Page<ChbExchangeQuotationInfoDto> selectForPage(Map<String, Object> param){
        return super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()),() -> chbExchangeQuotationInfoMapper.select(param));
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<ChbExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return chbExchangeQuotationInfoMapper.select(pageParam);
    }



    public AjaxResult save(ChbExchangeQuotationInfoDto dto){
        dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        //把货币对分割得到基础币种和报价币种
        String[] currency = dto.getExchargeCode().split("/");
        //基础币种
        dto.setBasisCyy(currency[0]);
        //报价币种
        dto.setQuotCyy(currency[1]);
        List<ChbExchangeQuotationInfoDto> list = new ArrayList<>(1);
        list.add(dto);
        try {
            Integer count = chbExchangeQuotationInfoMapper.getMstExchargeQuoteByKey(dto);
            if (count<1) {
                chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(list);
            }else {
                chbExchangeQuotationInfoMapper.updateExchargeQuoteInfo(list);
            }
        } catch (Exception e) {
            logger.error("操作失败：{}",e.getMessage());
            return new AjaxResult("操作失败");
        }
        return new AjaxResult("操作成功");
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
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        //判断excel表中货币对和交易日期、数据来源是否冲突
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(0), dataList.get(j).get(0)) &&
                        StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行货币对、交易日期、数据来源与"+"第"+(j+2)+"行货币对、交易日期、数据来源相同");
                    errorList.add(validMessage);
                }
            }
        }


        if(errorList.size() == 0){
            webResult.setData(NO_INFO_SHOW_OUT);
        }else{
            webResult.setErrorList(errorList);
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


        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        List<List<String>> tempList = ExcelUtil.getTempList1(fileName);
        String userName=CommonUtil.getUserName();
        String deptCode=CommonUtil.getDeptCode();
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("货币对");
            headList.add("交易日期");
            headList.add("数据来源");
            headList.add("开盘价");
            headList.add("收盘价");
            headList.add("最高价");
            headList.add("最低价");
            headList.add("中间价");
            headList.add("报价单位");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<MstExchargeQuote> entities = new ArrayList<>(dataList.size());
                List<ExcelValidDto> checkResult= convertToEntity(dataList, entities,userName,deptCode);
                if(checkResult.isEmpty()) {
                    for(MstExchargeQuote dto : entities){
                        mstExchargeQuoteMapper.insert(dto);
                    }
                }
                return checkResult;
            }
        }else{
            List<ExcelValidDto> checkResult = new ArrayList<>();
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            checkResult.add(excelValidDto);
            return checkResult;
        }
        return null;
    }

    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为ProRegistCodeManage001Dto  FinProductValuation对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @param entities 转化后的列表
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstExchargeQuote> entities,String userName,String deptCode) {

        List<ExcelValidDto> checkResult = new ArrayList<>();
        //数据来源枚举值：BAK:行内、CALYPSO：calypso、CBC:中债登、CFETS:外汇交易中心、CSD:中证登、EXP：专家法、PBOC:央行、SAFE：外管局、SCH：上清所。
        String[] dataSourceStr = {"行内","calypso","中债登","外汇交易中心","中证登","专家法","央行","外管局","上清所"};
        List dataSourceList = Arrays.asList(dataSourceStr);
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        for (int i = 0; i < dataList.size(); i++ ) {
            //是否正常标志
            boolean flag = true;
            //数据来源
            String dataSource = "";
            List<String> list = dataList.get(i);

            Map<String,Object> param = new HashMap<String,Object>();
            // 验证消息
            List<String> message = new ArrayList<>();

            //货币对
            if(list.get(0) == null || list.get(0).trim().equals("")){
                flag = false;
                message.add("货币对不能为空");
            }
            //交易日期
            if(list.get(1) == null || list.get(1).trim().equals("")){
                flag = false;
                message.add("交易日期不能为空");
            }else{
                if (!checkDate(list.get(1))) {
                    flag = false;
                    message.add("交易日期格式不正确");
                }
            }
            //数据来源
            if(list.get(2) == null || list.get(2).trim().equals("")){
                flag = false;
                message.add("数据来源不能为空");
            }else{
                if(!dataSourceList.contains(list.get(2))){
                    flag = false;
                    message.add("数据来源不在枚举值之中");
                }else{
                    if(list.get(2).equals("行内")){
                        dataSource = "BAK";
                    }else if(list.get(2).equals("calypso")){
                        dataSource = "CALYPSO";
                    }else if(list.get(2).equals("中债登")){
                        dataSource = "CBC";
                    }else if(list.get(2).equals("外汇交易中心")){
                        dataSource = "CFETS";
                    }else if(list.get(2).equals("中证登")){
                        dataSource = "CSD";
                    }else if(list.get(2).equals("专家法")){
                        dataSource = "EXP";
                    }else if(list.get(2).equals("央行")){
                        dataSource = "PBOC";
                    }else if(list.get(2).equals("外管局")){
                        dataSource = "SAFE";
                    }else if(list.get(2).equals("上清所")){
                        dataSource = "SCH";
                    }
                }
            }
            //开盘价
            if(list.get(3) == null || list.get(3).trim().equals("")){
                flag = false;
                message.add("开盘价不能为空");
            }else{
                if(!checkFigure(list.get(3))){
                    flag = false;
                    message.add("开盘价不是数字");
                }
            }
            //收盘价
            if(list.get(4) == null || list.get(4).trim().equals("")){
                flag = false;
                message.add("收盘价不能为空");
            }else{
                if(!checkFigure(list.get(4))){
                    flag = false;
                    message.add("收盘价不是数字");
                }
            }
            //最高价
            if(list.get(5) == null || list.get(5).trim().equals("")){
                flag = false;
                message.add("最高价不能为空");
            }else{
                if(!checkFigure(list.get(5))){
                    flag = false;
                    message.add("最高价不是数字");
                }
            }
            //最低价
            if(list.get(6) == null || list.get(6).trim().equals("")){
                flag = false;
                message.add("最低价不能为空");
            }else{
                if(!checkFigure(list.get(6))){
                    flag = false;
                    message.add("最低价不是数字");
                }
            }
            //中间价
            if(list.get(7) == null || list.get(7).trim().equals("")){
                flag = false;
                message.add("中间价不能为空");
            }else{
                if(!checkFigure(list.get(7))){
                    flag = false;
                    message.add("中间价不是数字");
                }
            }
            //报价单位
            if(list.get(8) == null || list.get(8).trim().equals("")){
                flag = false;
                message.add("报价单位不能为空");
            }else{
                if(!checkLongFigure(list.get(8))){
                    flag = false;
                    message.add("报价单位不是长整型数字");
                }
            }
            //主键校验
            if(flag){
                param.put("finprodId",list.get(0));
                param.put("tradeDate", DateUtil.parse(list.get(1)));
                param.put("dataSource", dataSource);
                String  result = chbExchangeQuotationInfoMapper.getRegistExcelList(param);
                if(StringUtils.isNotEmpty(result)){
                    flag = false;
                    message.add("存在和货币对、交易日、数据来源相同的数据");
                }
            }

            if(flag){
                MstExchargeQuote entity = new MstExchargeQuote();
                //货币对
                entity.setExchargeCode(list.get(0));
                //交易日期
                entity.setTradeDate(DateUtil.parse(list.get(1)));
                //数据来源
                entity.setDatasourceType(dataSource);
                //开盘价
                entity.setOpenPrice(new BigDecimal(list.get(3)));
                //收盘价
                entity.setClosePrice(new BigDecimal(list.get(4)));
                //最高价
                entity.setHighPrice(new BigDecimal(list.get(5)));
                //最低价
                entity.setLowPrice(new BigDecimal(list.get(6)));
                //中间价
                entity.setMidPrice(new BigDecimal(list.get(7)));
                //报价单位
                entity.setQuotCyy("USD");
                //基础币种
                entity.setBasisCyy("CNY");
                //报价币种
                entity.setQuotUnit(Long.parseLong(list.get(8)));
                entity.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);
                entity.setCreateTime(nowDate);
                entity.setUpdateUser(userName);
                entity.setUpdateTime(nowDate);
                entities.add(entity);
            }

            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(0), String.valueOf(i+2),
                        StringUtil.joinString(message, GlobalConstant.SEMICOLON)));
            }
        }
        return checkResult;
    }

    /**
     * 日期格式校验
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkDate(String date) {
        boolean flag = true;
        if(!DateUtil.isDateStr(date, DateUtil.DEFAULT_OTHER_FORMAT,
                DateUtil.DEFAULT_ANOTHER_FORMAT,DateUtil.DEFAULT_DATE_FORMAT_EN)){
            flag = false;
        }else{
            date = date.replace("-", "").replace("/", "");
            //年
            int year = Integer.valueOf(date.substring(0, 4));
            //月
            int month = Integer.valueOf(date.substring(4, 6));
            //日
            int day = Integer.valueOf(date.substring(6));
            if(month < 1 || month > 12){
                flag = false;
            }else{
                if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if(day < 1 || day > 31){
                        flag = false;
                    }
                }else if(month == 4 || month == 6 || month == 9 || month == 11){
                    if(day < 1 || day > 30){
                        flag = false;
                    }
                }else if(month == 2){
                    if((year % 4 == 0 && year % 100 != 0) ||year % 400 == 0){
                        if(day < 1 || day > 29){
                            flag = false;
                        }
                    }else{
                        if(day < 1 || day > 28){
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 判断是否为数字
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkFigure(String figure) {
        try{
            new BigDecimal(figure);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 判断是否为长整型数字
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkLongFigure(String longFigure) {
        try{
            Long.parseLong(longFigure);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void doComplete(ChbExchangeQuotationInfoDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        if (StringUtil.equals(dto.getOperateType(), "DELETE")) {
            mstExchargeQuoteMapper.deleteByPrimaryKey(dto);
        }
        else if (StringUtil.equals(dto.getOperateType(), "NEW")) {
            List<ChbExchangeQuotationInfoDto> list = new ArrayList<>(1);
            //设置基本币种，单位币种等
            String exchargeCode = dto.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                dto.setBasisCyy(splitToList.get(0));
                dto.setQuotCyy(splitToList.get(1));
            }
            dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            list.add(dto);
            chbExchangeQuotationInfoMapper.insertExchargeQuoteInfo(list);
        }
        else {
            mstExchargeQuoteMapper.updateByPrimaryKeySelective(dto);
        }
    }
}
