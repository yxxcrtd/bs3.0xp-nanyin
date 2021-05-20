/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dao.SvrMstExchangeQuotationInfoMapper;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dao.SvrMstExchargeQuoteMapper;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;

/**
 * 汇率行情service 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see SvrMstExchangeQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMstExchangeQuotationInfoService extends CommonExportService<SvrMstExchangeQuotationInfoDto> {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
     * 汇率行情mapper
     */
    @Autowired
    private SvrMstExchangeQuotationInfoMapper mstExchangeQuotationInfoMapper;

    /**
     * 汇率行情自动生成mapper
     */
    @Autowired
    private SvrMstExchargeQuoteMapper mstExchargeQuoteMapper;

    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstExchangeQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
//        return simpleRestTemplate.postForPage(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectForPage",
//            pageParam, MstExchangeQuotationInfoDto.class);
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            Page<SvrMstExchangeQuotationInfoDto> page = queryForPage(pagenum,legth,()->mstExchangeQuotationInfoMapper.select(pageParam));
            return page;
        } else {
            Page<SvrMstExchangeQuotationInfoDto> page = queryForPage(SvrMstExchangeQuotationInfoMapper.class,"select",pageParam);
            return page;
        }
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SvrMstExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
//        return simpleRestTemplate.postForList(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectAllList",
//            pageParam, MstExchangeQuotationInfoDto.class);
    	return mstExchangeQuotationInfoMapper.select(pageParam);
    }

    
    public void doComplete(SvrMstExchangeQuotationInfoDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstExchargeQuoteMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {
//             设置基本币种，单位币种等
            String exchargeCode = entity.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                entity.setBasisCyy(splitToList.get(0));
                entity.setQuotCyy(splitToList.get(1));
                entity.setDatasourceType(DictCodeListExt2.数据来源_系统新建.getValue());
                entity.setQuotUnit((long)1);
            }
            mstExchargeQuoteMapper.insert(entity);
        }
        else {
            mstExchargeQuoteMapper.updateByPrimaryKeySelective(entity);
        }
    }

    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<SvrMstExchangeQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
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

	public List<SvrMstExchangeQuotationInfoDto> selectForExport(Map<String, Object> param) {
    	String keyList = param.get("keyList").toString();
        List<SvrMstExchangeQuotationInfoDto> investList = null;
        try {
            investList = JsonUtils.json2List(keyList,
            SvrMstExchangeQuotationInfoDto.class);
            return mstExchangeQuotationInfoMapper.selectForExport(investList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<SvrMstExchangeQuotationInfoDto>();
    }

    /**
     * 导入文件之前检查文件 导入的excel是否有重复数据
     *
     * param fileName 导入的文件名名
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
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
            webResult.setData(NO_INFO_SHOWOUT);
        }else{
            webResult.setErrorList(errorList);
        }
        return webResult;
    }

    /**
     * 导入文件
     * <p>
     * 解析excel文件并进行验证
     * </p>
     *

     * @return 导入结果
     */
    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("货币对");
            headList.add("交易日期");
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
     * @param userName 用戶名
     * @param deptCode 机构id
     * @return 验证信息
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<MstExchargeQuote> entities,String userName,String deptCode) {

        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date nowDate = new Date();
        for (int i = 0; i < dataList.size(); i++ ) {
            //是否正常标志
            boolean flag = true;
            //数据来源
            String dataSource = DictCodeListExt2.数据来源_EXCEL导入.getValue();
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
            //中间价
            if(list.get(2) == null || list.get(2).trim().equals("")){
                flag = false;
                message.add("中间价不能为空");
            }else{
                if(!checkFigure(list.get(2))){
                    flag = false;
                    message.add("中间价不是数字");
                }
            }
            //报价单位
            if(list.get(3) == null || list.get(3).trim().equals("")){
                flag = false;
                message.add("报价单位不能为空");
            }else{
                if(!checkLongFigure(list.get(3))){
                    flag = false;
                    message.add("报价单位不是长整型数字");
                }
            }
            //主键校验
            if(flag){
                param.put("finprodId",list.get(0));
                param.put("tradeDate", DateUtil.parse(list.get(1)));
                param.put("dataSource", dataSource);
                String  result = mstExchangeQuotationInfoMapper.getRegistExcelList(param);
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
                //中间价
                entity.setMidPrice(new BigDecimal(list.get(2)));
                //报价单位
                entity.setQuotCyy("USD");
                //基础币种
                entity.setBasisCyy("CNY");
                //报价币种
                entity.setQuotUnit(Long.parseLong(list.get(3)));
                entity.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);
                entity.setCreateTime(nowDate);
                entity.setUpdateUser(userName);
                entity.setUpdateUser(userName);
                entity.setDatasourceType(dataSource);
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

}
