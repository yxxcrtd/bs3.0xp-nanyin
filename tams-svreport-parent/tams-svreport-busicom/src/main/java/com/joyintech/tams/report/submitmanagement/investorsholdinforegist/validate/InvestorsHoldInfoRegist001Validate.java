/*
 * 文件名：InvestorsHoldInfoRegist001Validate.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月10日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.submitmanagement.investorsholdinforegist.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.EquityInfoCheckUtil;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 投资者持有信息登记校验
 * @author zuoyinyi
 * @version 1.0 2018年5月10日
 * @see InvestorsHoldInfoRegist001Validate
 * @since 1.0
 */
@Component
public class InvestorsHoldInfoRegist001Validate {
    /**
     * 投资者持有信息登记mapper
     */
    @Autowired
    private InvestorsHoldInfoRegist001Mapper investorsHoldInfoRegist001Mapper;

    /**
     * MessageSource
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * 不含有汉子验证
     */
    public static final String PATTERN_IS_CHINA = "^[^\u4e00-\u9fa5]{0,}$";

    /**
     * 验证登记银行编码
     */
    public static final String PATTERN_REGIST_BANK_CODE = "^[0-9a-zA-Z]{6}$";
    
    /**
     * 数据校验
     * @param investorsHoldInfoRegistList
     * @param string
     * @return
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> checkData(List<InvestorsHoldInfoRegist001Dto> list,
                                         String flag) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        //获取投资者信息报送表中的数据
        Map<String, String> indentifyMarkMap = investorsHoldInfoRegist001Mapper.getAllRegistBankCodeSet().stream().collect(JoyinCollectors.toMap(x->x.get("INDENTIFY_MARK"), x->x.get("ORG_CODE")));
        //获取所有的产品登记编码
        Map<String,String> prdRegistMap = investorsHoldInfoRegist001Mapper.getAllRegistProdCodeSet().stream().collect(JoyinCollectors.toMap(x->x.get("REG_CODE"), x->x.get("PRODID")));
        for (int i = 0; i < list.size(); i++ ) {
            // 验证消息
            List<String> message = new ArrayList<>();
            // 识别标识验证
            message = checkIndentifyMark(message, list.get(i),indentifyMarkMap);
            // 登记银行编码
            message = checkBankCode(message, list.get(i).getRegistBankCode());
            //产品登记编码校验
            if(StringUtil.isEmpty(list.get(i).getPrdRegistCode())){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            }
            else if(!prdRegistMap.containsKey(list.get(i).getPrdRegistCode())){
                message.add("产品登记编码在系统中不存在");
            }
            //持有日期校验
            String holdDate = DateUtil.format(list.get(i).getHoldDate());
            if(StringUtil.isEmpty(holdDate)){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "持有日期"));
            }
            else if (!DateUtil.isDateStr(holdDate, DateUtil.DEFAULT_OTHER_FORMAT,
                DateUtil.DEFAULT_ANOTHER_FORMAT, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "持有日期"));
            }
            //币种校验
            if(StringUtil.isEmpty(list.get(i).getCcy())){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "币种"));
            }
            Map<String, String> dictCodeMap = SysDataUtil.getSysDictDataMap(DictCode.币种_中债);
            if (!dictCodeMap.containsKey(list.get(i).getCcy())) {
                message.add(getMessage("CHBCONNECT_E_0002", "币种"));
            }
            //机构代码
            if(StringUtil.isEmpty(list.get(i).getOrgCode())){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "机构代码"));
            }
            //持有份额
            BigDecimal qty = list.get(i).getQty();
            String qtyInfo = checkMoney(qty,"持有份额",18,5,true);
            if (StringUtil.isNotEmpty(qtyInfo)) {
                message.add(qtyInfo);
            }

            //持有金额
            BigDecimal amount = list.get(i).getAmount();
            String amountInfo = checkMoney(amount,"持有金额",15,2,true);
            if (StringUtil.isNotEmpty(amountInfo)) {
                message.add(amountInfo);
            }
            //折算人民币金额（元）
            BigDecimal cnyAmt = list.get(i).getCnyAmt();
            String cnyAmtInfo = checkMoney(cnyAmt,"折算人民币金额（元）",15,2,true);
            if (StringUtil.isNotEmpty(cnyAmtInfo)) {
                message.add(cnyAmtInfo);
            }
            //元币种为人民币时，持有金额和折算金额必须相等校验
            if(StringUtil.equals(list.get(i).getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())){
                if(cnyAmt!=null && cnyAmt.compareTo(amount)!=0){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0025"));
                }
            }
            String validKey = "产品登记编码 "+list.get(i).getPrdRegistCode()+GlobalConstant.COMMA
                +"识别标识 "+list.get(i).getIndentifyMark()+GlobalConstant.COMMA
                +"持有日期 "+DateUtil.format(list.get(i).getHoldDate(),"yyyy-MM-dd")+GlobalConstant.COMMA
                +"币种  "+list.get(i).getCcy()+GlobalConstant.COMMA
                +"登记银行代码  "+list.get(i).getRegistBankCode();
            if (!message.isEmpty()) {
                ExcelValidDto excelValidDto = new ExcelValidDto(validKey, String.valueOf(i),StringUtil.joinString(message, GlobalConstant.COMMA));
                excelValidDto.setDataTime(list.get(i).getUpdateTime());
                excelValidDto.setIsLogicCheck(list.get(i).getIsLogicCheck());
                excelValidDto.setOrgCode(list.get(i).getOrgCode());
                excelValidDto.setCreateDept(list.get(i).getCreateDept());
                checkResult.add(excelValidDto);
                continue;
            }
            else{
                ExcelValidDto excelValidDto = new ExcelValidDto(validKey,StringUtil.EMPTY,StringUtil.EMPTY);
                excelValidDto.setDataTime(list.get(i).getUpdateTime());
                excelValidDto.setOrgCode(list.get(i).getOrgCode());
                excelValidDto.setCreateDept(list.get(i).getCreateDept());
                checkResult.add(excelValidDto);
            }
        }
        return checkResult;
    }

    /**
     * 投资者识别标识验证
     *
     * @param message 检查消息
     * @param indentifyMark 投资者识别标识
     * @param indentifyMarkMap
     * @return message 检查消息
     * @see
     * @since 1.0
     */
    private List<String> checkIndentifyMark(List<String> message, InvestorsHoldInfoRegist001Dto dto, Map<String, String> indentifyMarkMap) {
        if(StringUtil.isEmpty(dto.getIndentifyMark())){
            message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "识别标识"));
        }
        else if (EquityInfoCheckUtil.findPatern(PATTERN_IS_CHINA, dto.getIndentifyMark())) {
            message.add(this.getMessage("CHB_EQUITYINFO_E_012", "识别标识"));
        }
        else if (EquityInfoCheckUtil.isExceedLength(dto.getIndentifyMark(), 30)) {
            message.add(this.getMessage("CHB_EQUITYINFO_E_010", "识别标识", "30"));
        }
        else {
            if(!indentifyMarkMap.containsKey(dto.getIndentifyMark())){
                message.add(this.getMessage("INVESTREGIST_001"));
                //标记逻辑校验
                dto.setIsLogicCheck(DictCodeList.是否_中债_是.getValue());
            }
        }
        return message;
    }

    /**
     * 登记银行编码验证
     *
     * @param message 检查消息
     * @param bankCode 登记银行编码
     * @return message 检查消息
     * @see
     * @since 1.0
     */
    private List<String> checkBankCode(List<String> message, String bankCode) {
        if (EquityInfoCheckUtil.isEmpty(bankCode)) {
            message.add(this.getMessage("CHB_EQUITYINFO_E_001", "登记银行代码"));
        }
        else if (bankCode.length() != 6) {
            message.add(this.getMessage("CHB_EQUITYINFO_E_002", "登记银行代码", "6"));
        }
        else if (EquityInfoCheckUtil.findPatern(PATTERN_REGIST_BANK_CODE, bankCode)) {
            message.add(this.getMessage("CHB_EQUITYINFO_E_003", "登记银行代码"));
        }
        return message;
    }

    /**
     * 获取message
     *
     * @param code message code
     * @param args 参数
     * @return message
     * @see
     * @since 1.0
     */
    protected String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * 判断代码是否合法，返回错误信息
     *
     * @param code 代码
     * @param intlen 长度
     * @param declen 长度
     * @param cloumnTitle 列名
     * @param isRequired 是否必须
     * @return 错误信息
     */
    private String checkMoney(BigDecimal code, String cloumnTitle, int intlen, int declen,
                              boolean isRequired) {
        String message = null;
        // 必须验证
        if (code==null) {
            if (isRequired) {
                message = this.getMessage("REPORT_COMMON_E_0001", cloumnTitle);
                return message;
            }
            else{
                return null;
            }
        }
        // 属性验证
        if (!CommonUtil.isFloatNumeric(code.toString())) {
            message = this.getMessage("REPORT_COMMON_E_0003", cloumnTitle);
        }
        else {
            if (code.compareTo(BigDecimal.ZERO) < 0) {
                message = this.getMessage("REPORT_COMMON_E_0023", cloumnTitle, "0");
            }

            // 长度验证
            if ((code.precision() - code.scale()) > intlen || code.scale() > declen) {
                message = this.getMessage("REPORT_ASSETBOND_E_001", cloumnTitle, intlen, declen);
            }
        }
        return message;
    }
}
