/*
 * 文件名：BatchCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.sztinterf.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;
import com.joyintech.tams.valuation.common.sztinterf.constant.SztValConstant;
import com.joyintech.tams.valuation.common.sztinterf.dao.SztInterfCommonMapper;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztSubjectBalanceDto;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztSubjectInfoDto;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztValuationDto;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztXmlMessageDto;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 *  深圳通公共方法类
 * @author yanya
 * @version 1.0 2019年12月11日
 * @see SztInterfCommonService
 * @since 1.0
 */
@Service
public class SztInterfCommonService {

    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(SztInterfCommonService.class);

    @Autowired
    private SztInterfCommonMapper sztInterfCommonMapper;

    /**
     * 生成业务流水号
     * @return
     */
    public String getSerialNo() {
        String flowSerialNo = SztValConstant.深圳通接口业务流水号;
        //数据库当前流水号
        String dbSerialNo = sztInterfCommonMapper.getSerialNo();
        //获取服务器当前时间，格式：yyyyMMdd
        String date = DateUtil.format(new Date(), SztValConstant.深圳通接口业务流水号时间格式化格式);
        int dbSerialNoLength = dbSerialNo.length();
        int flowSerialNoLength  = flowSerialNo.length();
        //变量.流水号 = 变量.时间 + 变量.流水号.subString(0,变量.流水号.length()-变量.序列号.length()) + 变量.序列号
        if(dbSerialNoLength < flowSerialNoLength) {
            return date + SztValConstant.深圳通接口业务流水号.substring(0, flowSerialNoLength - dbSerialNoLength) + dbSerialNo;
        }
        else {
            return date + dbSerialNo;
        }
    }

    /**
     * 向Element里添加字符串,且判断是否为空
     * @param e
     * @param text
     */
    public void addTextToElement(Element e, String text) {
        if(StringUtils.isNotEmpty(text)){
            addStrToElement(e, text);
        }
    }

    /**
     * 向Element里添加字符串,且判断是否为空
     * @param e
     * @param text
     */
    public void addBigDecimalToElement(Element e, BigDecimal val) {
        if(val != null){
            addStrToElement(e, val.toString());
        }
    }

    /**
     * 向Element里添加字符串
     * @param e
     * @param text
     */
    public void addStrToElement(Element e, String text) {
        e.addText(text);
    }

    /**
     * 深圳通科目信息表报文生成
     * @param prodId
     * @param cDate
     * @param detailFlag
     * @return
     */
    public SztXmlMessageDto createSubjectInfoDocument(String prodId, String cDate, String detailFlag) {
        //定义返回报文实体
        SztXmlMessageDto sztXmlMessageDto = new SztXmlMessageDto();
        //构建客户信息查询参数map
        Map<String, Object> params = new HashMap<>();
        params.put(DictCodeSztList.深圳通接口查询参数字段_产品代码.getValue(), prodId);
        //param.put(DictCodeSztList.深圳通接口查询参数字段_发送日期.getValue(), cDate);
        params.put(DictCodeSztList.深圳通接口查询参数字段_是否发送四级科目.getValue(), detailFlag);
        params.put(DictCodeSztList.深圳通接口查询参数字段_接口类型.getValue(), DictCodeListExt1.深圳通接口类型_科目信息表.getValue());

        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型_资产类.getValue(), DictCodeList.科目类型_资产类.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型_负债类.getValue(), DictCodeList.科目类型_负债类.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型_资产负债共同类.getValue(), DictCodeList.科目类型_资产负债共同类.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型_所有者权益类.getValue(), DictCodeList.科目类型_所有者权益类.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型_损益类.getValue(), DictCodeList.科目类型_损益类.getValue());

        List<String> subjectList = Arrays.asList(DictCodeList.科目类型_资产类.getValue(), DictCodeList.科目类型_负债类.getValue(),
                DictCodeList.科目类型_资产负债共同类.getValue(), DictCodeList.科目类型_所有者权益类.getValue(),
                DictCodeList.科目类型_损益类.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_科目类型LIST.getValue(), subjectList);
        //查询待发送的客户信息数据：
        //定义 变量.科目信息表modelList = 上述返回结果
        List<SztSubjectInfoDto> list  = sztInterfCommonMapper.getSubjetInfo(params);
        //String serialNo = 调用共通【生成业务流水号】
        String serialNo = getSerialNo();
        Document document = DocumentHelper.createDocument();
        // 指定编码方式
        document.setXMLEncoding(SztValConstant.深圳通_接口文件GBK编码);
        //文件类型fileType --1031
        String fileType = SztValConstant.深圳通_科目信息文件类型;
        //组合外部代码
        String fundId = prodId;
        //报表类型fileType -- 01
        String reportType = SztValConstant.深圳通_报表类型;
        //开始时间
        String beginDate = cDate;
        //结束时间
        String endDate = "";
        //公司代码 -- 0133
        String deptCode = SztValConstant.深圳通_公司代码;
        //证书代码
        String certId = "";

        //根据【科目信息表报文结构sheet】生成报文信息
        //生成报文体IN
        Element in = document.addElement(DictCodeSztList.深圳通接口报文公共接口字段_报文头.getValue());
        //报文公共部分
        createSztCommonDocument(in, fileType, fundId, reportType, beginDate, endDate,
                deptCode, certId, serialNo);
        list.forEach(item -> {
            Element Record = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_数据行.getValue());
            //科目代码
            Element FAcctCode = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_科目代码.getValue());
            addTextToElement(FAcctCode, item.getfAcctCode());
            //科目名称
            Element FAcctName = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_科目名称.getValue());
            addTextToElement(FAcctName, item.getfAcctName());
            //科目级别
            Element FAcctLevel = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_科目级别.getValue());
            addTextToElement(FAcctLevel, item.getfAcctLevel());
            //上级科目代码
            Element FAcctParent = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_上级科目代码.getValue());
            addTextToElement(FAcctParent, item.getfAcctParent());
            //是否明细科目
            Element FAcctDetail = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_是否明细科目.getValue());
            addTextToElement(FAcctDetail, item.getfAcctDetail());
            //科目类别
            Element FAcctClass = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_科目类别.getValue());
            addTextToElement(FAcctClass, item.getfAcctClass());
            //余额方向
            Element FBalDC = Record.addElement(DictCodeSztList.深圳通接口科目信息报文字段_余额方向.getValue());
            addTextToElement(FBalDC, item.getFbaldc());
        });

        sztXmlMessageDto.setResp(getResp(document));
        sztXmlMessageDto.setSendNum(list.size());
        sztXmlMessageDto.setSerialNo(serialNo);
        return sztXmlMessageDto;
    }

    /**
     * 深圳通估值表报文生成
     * @param prodId
     * @param cDate
     * @return
     */
    public SztXmlMessageDto createBookValuationDocument(String prodId, String cDate) {
        //定义返回报文实体
        SztXmlMessageDto sztXmlMessageDto = new SztXmlMessageDto();
        //构建估值查询参数map
        Map<String, Object> params = new HashMap<>();
        params.put(DictCodeSztList.深圳通接口查询参数字段_产品代码.getValue(), prodId);
        params.put(DictCodeSztList.深圳通接口查询参数字段_发送日期.getValue(), cDate);
        params.put(DictCodeSztList.深圳通接口查询参数字段_金融产品类型.getValue(), DictCodeListExt1.金融产品类型_理财产品.getValue());
        params.put(DictCodeSztList.深圳通接口查询参数字段_行情标志.getValue(), SztValConstant.深圳通_行情标志_收市价);
        //查询待发送估值表数据
        List<SztValuationDto> list = sztInterfCommonMapper.getBookValuation(params);
        //定义 变量.估值表modelList = 上述返回结果
        //String serialNo = 调用共通【生成业务流水号】
        String serialNo = getSerialNo();
        Document document = DocumentHelper.createDocument();
        // 指定编码方式
        document.setXMLEncoding(SztValConstant.深圳通_接口文件GBK编码);
        //文件类型fileType --1011
        String fileType = SztValConstant.深圳通_估值文件类型;
        //组合外部代码
        String fundId = prodId;
        //报表类型fileType -- 01
        String reportType = SztValConstant.深圳通_报表类型;
        //开始时间
        String beginDate = cDate;
        //结束时间
        String endDate = "";
        //公司代码 -- 0133
        String deptCode = SztValConstant.深圳通_公司代码;
        //证书代码
        String certId = "";
        //根据【估值表报文结构sheet】生成报文信息
        //生成报文体IN
        Element in = document.addElement(DictCodeSztList.深圳通接口报文公共接口字段_报文头.getValue());
        //报文公共部分
        createSztCommonDocument(in, fileType, fundId, reportType, beginDate, endDate,
                deptCode, certId, serialNo);
        list.forEach(item -> {
            Element Record = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_数据行.getValue());
            //FKmbm	科目编码
            Element FKmbm = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_科目编码.getValue());
            addTextToElement(FKmbm, item.getfKmbm());
            //FKmmc	科目名称
            Element FKmmc = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_科目名称.getValue());
            addTextToElement(FKmmc, item.getfKmmc());
            //FHqjg	行情价格
            Element FHqjg = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_行情价格.getValue());
//            if(item.getfHqjg() != null){
//                addTextToElement(FHqjg, item.getfHqjg().toString());
//            }
            addBigDecimalToElement(FHqjg, item.getfHqjg());
            //FHqbz	行情标志
            Element FHqbz = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_行情标志.getValue());
            addTextToElement(FHqbz, item.getfHqbz());
            //FZqsl	证券数量
            Element FZqsl = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_证券数量.getValue());
//            if(item.getfZqsl() != null) {
//                addTextToElement(FZqsl, item.getfZqsl().toString());
//            }
            addBigDecimalToElement(FZqsl, item.getfZqsl());
            //FZqcb	成本
            Element FZqcb = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_成本.getValue());
//            if(item.getfZqcb() != null) {
//                addTextToElement(FZqcb, item.getfZqcb().toString());
//            }
            addBigDecimalToElement(FZqcb, item.getfZqcb());
            //FZqsz	市值（总市值）
            Element FZqsz = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_市值总市值.getValue());
//            if(item.getfZqsz() != null) {
//                addTextToElement(FZqsz, item.getfZqsz().toString());
//            }
            addBigDecimalToElement(FZqsz, item.getfZqsz());
            //FGz_zz	估值增值
            Element fGzZz = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_估值增值.getValue());
//            if(item.getfGzZz() != null) {
//                addTextToElement(fGzZz, item.getfGzZz().toString());
//            }
            addBigDecimalToElement(fGzZz, item.getfGzZz());
            //FCb_Jz_bl	成本占净值比例
            Element fCbJzBl = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_成本占净值比例.getValue());
//            if(item.getfCbJzBl() != null) {
//                addTextToElement(fCbJzBl, item.getfCbJzBl().toString());
//            }
            addBigDecimalToElement(fCbJzBl, item.getfCbJzBl());
            //FSz_Jz_bl	市值占净值比例
            Element fSzJzBl = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_市值占净值比例.getValue());
//            if(item.getfSzJzBl() != null) {
//                addTextToElement(fSzJzBl, item.getfSzJzBl().toString());
//            }
            addBigDecimalToElement(fSzJzBl, item.getfSzJzBl());
            //FIsDetail	是否明细类数据
            Element FIsDetail = Record.addElement(DictCodeSztList.深圳通接口估值报文字段_是否明细类数据.getValue());
            addTextToElement(FIsDetail, item.getfIsDetail());
        });

        sztXmlMessageDto.setResp(getResp(document));
        sztXmlMessageDto.setSendNum(list.size());
        sztXmlMessageDto.setSerialNo(serialNo);
        return sztXmlMessageDto;
    }

    /**
     * 深圳通客户余额报文生成
     * @param prodId
     * @param cDate
     * @return
     * @throws Exception
     */
    public SztXmlMessageDto createSubjectBalalceDocument(String prodId, String cDate) {
        //定义返回报文实体
        SztXmlMessageDto sztXmlMessageDto = new SztXmlMessageDto();
        //构建估值查询参数map
        Map<String, Object> params = new HashMap<>();
        params.put(DictCodeSztList.深圳通接口查询参数字段_产品代码.getValue(), prodId);
        params.put(DictCodeSztList.深圳通接口查询参数字段_发送日期.getValue(), cDate);
        params.put(DictCodeSztList.深圳通接口查询参数字段_金融产品类型.getValue(), DictCodeListExt1.金融产品类型_理财产品.getValue());
        //查询待发送余额表数据
        List<SztSubjectBalanceDto> list = sztInterfCommonMapper.getSubjectBalalce(params);
        //定义 变量.科目余额表modelList = 上述返回结果
        //String serialNo = 调用共通【生成业务流水号】
        String serialNo = getSerialNo();
        Document document = DocumentHelper.createDocument();
        // 指定编码方式
        document.setXMLEncoding(SztValConstant.深圳通_接口文件GBK编码);
        //文件类型fileType --1001
        String fileType = SztValConstant.深圳通_科目余额文件类型;
        //组合外部代码
        String fundId = prodId;
        //报表类型fileType -- 01
        String reportType = SztValConstant.深圳通_报表类型;
        //开始时间
        String beginDate = cDate;
        //结束时间
        String endDate = "";
        //公司代码 -- 0133
        String deptCode = SztValConstant.深圳通_公司代码;
        //证书代码
        String certId = "";
        //根据【科目余额报文结构sheet】生成报文信息
        //生成报文体IN
        Element in = document.addElement(DictCodeSztList.深圳通接口报文公共接口字段_报文头.getValue());
        //报文公共部分
        createSztCommonDocument(in, fileType, fundId, reportType, beginDate, endDate,
                deptCode, certId, serialNo);
        list.forEach(item -> {
            Element Record = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_数据行.getValue());
            //FAcctCode	科目代码
            Element FAcctCode = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_科目代码.getValue());
            addTextToElement(FAcctCode, item.getfAcctCode());
            //FCurCode	币种代码
            Element FCurCode = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_币种代码.getValue());
            addTextToElement(FCurCode, item.getfCurCode());
            //FStartBal	期初余额(原币)
            Element FStartBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期初余额原币.getValue());
//            if(item.getfStartBal() != null) {
//                addTextToElement(FStartBal, item.getfStartBal().toString());
//            }
            addBigDecimalToElement(FStartBal, item.getfStartBal());
            //FDebit	本期借方发生额(原币)
            Element FDebit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期借方发生额原币.getValue());
//            if(item.getfDebit() != null) {
//                addTextToElement(FDebit, item.getfDebit().toString());
//            }
            addBigDecimalToElement(FDebit, item.getfDebit());
            //FCredit	本期贷方发生额(原币)
            Element FCredit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期贷方发生额原币.getValue());
//            if(item.getfCredit() != null) {
//                addTextToElement(FCredit, item.getfCredit().toString());
//            }
            addBigDecimalToElement(FCredit, item.getfCredit());
            //FEndBal	期末余额(原币)
            Element FEndBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期末余额原币.getValue());
//            if(item.getfEndBal() != null) {
//                addTextToElement(FEndBal, item.getfEndBal().toString());
//            }
            addBigDecimalToElement(FEndBal, item.getfEndBal());
            //FBStartBal	期初余额(本位币)
            Element FBStartBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期初余额本位币.getValue());
//            if(item.getfBStartBal() != null) {
//                addTextToElement(FBStartBal, item.getfBStartBal().toString());
//            }
            addBigDecimalToElement(FBStartBal, item.getfBStartBal());
            //FBDebit	本期借方发生额(本位币)
            Element FBDebit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期借方发生额本位币.getValue());
//            if(item.getfBDebit() != null) {
//                addTextToElement(FBDebit, item.getfBDebit().toString());
//            }
            addBigDecimalToElement(FBDebit, item.getfBDebit());
            //FBCredit	本期贷方发生额(本位币)
            Element FBCredit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期贷方发生额本位币.getValue());
//            if(item.getfBCredit() != null) {
//                addTextToElement(FBCredit, item.getfBCredit().toString());
//            }
            addBigDecimalToElement(FBCredit, item.getfBCredit());
            //FBEndBal	期末余额(本位币)
            Element FBEndBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期末余额本位币.getValue());
//            if(item.getfBEndBal() != null) {
//                addTextToElement(FBEndBal, item.getfBEndBal().toString());
//            }
            addBigDecimalToElement(FBEndBal, item.getfBEndBal());
            //FAStartBal	期初余额(数量)
            Element FAStartBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期初余额数量.getValue());
//            if(item.getfAStartBal() != null) {
//                addTextToElement(FAStartBal, item.getfAStartBal().toString());
//            }
            addBigDecimalToElement(FAStartBal, item.getfAStartBal());
            //FADebit	本期借方发生额(数量)
            Element FADebit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期借方发生额数量.getValue());
//            if(item.getfADebit() != null) {
//                addTextToElement(FADebit, item.getfADebit().toString());
//            }
            addBigDecimalToElement(FADebit, item.getfADebit());
            //FACredit	本期贷方发生额(数量)
            Element FACredit = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_本期贷方发生额数量.getValue());
//            if(item.getfACredit() != null) {
//                addTextToElement(FACredit, item.getfACredit().toString());
//            }
            addBigDecimalToElement(FACredit, item.getfACredit());
            //FAEndBal	期末余额(数量)
            Element FAEndBal = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_期末余额数量.getValue());
//            if(item.getfAEndBal() != null) {
//                addTextToElement(FAEndBal, item.getfAEndBal().toString());
//            }
            addBigDecimalToElement(FAEndBal, item.getfAEndBal());
            //FIsDetail	是否最明细科目
            Element FIsDetail = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_是否最明细科目.getValue());
            addTextToElement(FIsDetail, item.getfIsDetail());
            //F_J_TOLTAL_AMOUNT	借方累计发生额
            Element fJToltalAmount = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_借方累计发生额.getValue());
//            if(item.getfJToltalAmount() != null) {
//                addTextToElement(fJToltalAmount, item.getfJToltalAmount().toString());
//            }
            addBigDecimalToElement(fJToltalAmount, item.getfJToltalAmount());
            //F_D_TOTAL_AMOUNT	贷方累计发生额
            Element fDTotalAmount = Record.addElement(DictCodeSztList.深圳通接口科目余额报文字段_贷方累计发生额.getValue());
//            if(item.getfDTotalAmount() != null) {
//                addTextToElement(fDTotalAmount, item.getfDTotalAmount().toString());
//            }
            addBigDecimalToElement(fDTotalAmount, item.getfDTotalAmount());

        });
        sztXmlMessageDto.setResp(getResp(document));
        sztXmlMessageDto.setSendNum(list.size());
        sztXmlMessageDto.setSerialNo(serialNo);
        return sztXmlMessageDto;
    }

    /**
     * 创建深圳通公共报文体
     * @param in Element
     * @param fileType 文件类型
     * @param fundId 组合外部代码
     * @param reportType 报表类型
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @param deptCode 司代码
     * @param certId 证书代码
     * @param serialNo 业务流水号
     *
     */
    public void createSztCommonDocument(Element in, String fileType, String fundId,
                                        String reportType, String beginDate, String endDate,
                                        String deptCode, String certId, String serialNo) {
        //文件类型
        Element FILE_TYPE = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_文件类型.getValue());
        addTextToElement(FILE_TYPE, fileType);

        //组合代码
        Element FUND_ID = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_组合代码.getValue());
        addTextToElement(FUND_ID, fundId);

        //报表类型
        Element REPORT_TYPE = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_报表类型.getValue());
        addTextToElement(REPORT_TYPE, reportType);

        //开始日期
        Element BEGIN_DATE = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_开始日期.getValue());
        addTextToElement(BEGIN_DATE, beginDate);

        //结束日期
        Element END_DATE = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_结束日期.getValue());
        addTextToElement(END_DATE, endDate);

        //公司代码
        Element DEPT_CODE = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_公司代码.getValue());
        addTextToElement(DEPT_CODE,deptCode);

        //证书代码
        Element CERT_ID = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_证书代码.getValue());
        addTextToElement(CERT_ID, certId);

        //业务流水号
        Element SERIAL_NO = in.addElement(DictCodeSztList.深圳通接口报文公共接口字段_业务流水号.getValue());
        addTextToElement(SERIAL_NO, serialNo);
    }

    /**
     * document转str
     * @param document
     * @return
     */
    public String getResp(Document document) {
        String resp = null;
        try {
            String xmlStr = document.asXML();
            resp = String.format(SztValConstant.深圳通_接口字符串格式化,
                    xmlStr.getBytes(SztValConstant.深圳通_接口文件UTF编码).length, xmlStr);
            return resp.substring(SztValConstant.深圳通_接口字符串格式化_进制);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("error:",e);
        }
        return resp;
    }
}
