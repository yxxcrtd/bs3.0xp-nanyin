/*
 * 文件名：InvestorsHoldInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.interf.cnbond.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.cnbond.dao.InvestorsHoldInfo001InterfMapper;
import com.joyintech.tams.report.interf.cnbond.dto.InvestorsHoldInfo001InterfDto;
import com.joyintech.tams.report.interf.cnbond.dto.InvestorsHoldInfo001InterfFileReadParaDto;
import com.joyintech.tams.report.interf.cnbond.dto.InvestorsHoldInfoRegist001InterfDto;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tools.collectors.JoyinCollectors;
/**
 * 投资者持有信息业务层service 〈功能详细描述〉
 *
 * @author zhujiabin
 * @version 1.0 2018年10月22日
 * @see InvestorsHoldInfo001InterfService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsHoldInfo001InterfService{
    
    @Autowired
    InvestorsHoldInfo001InterfMapper investorsHoldInfo001InterfMapper;
    
    
    @Autowired
    CnBondInterfService cnBondInterfService;
    /**
     * sql最大一次最大删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    public  String reportSelectOrDeleteCount;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * MessageSource
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * 白名单验证
     */
    public static final String PATTERN_ALL = "^[\\u4e00-\\u9fef-\\u0020-\\u0026-\\u0028-\\u007B-\\u007D-\\u007E-\\u2000-\\u2040-\\u2160-\\u216B-\\u3000-\\u301F-\\u3400-\\u4DB5-\\uE815-\\uE86F-\\uF900-\\uFAD9-\\uFF00-\\uFF65-\\uFFE0-\\uFFEF-\\u00B7]{0,}$";
    /**
     * 接口报送
     */
    private static final String SEND_STATUS_02 = DictCodeListExt2.报送状态_接口报送.getValue();
    /**
     * 手工处理
     */
    private static final String SEND_STATUS_03 = DictCodeListExt2.报送状态_手工处理.getValue();
    
    /**
     * 未报送
     */
    private static final String SEND_STATUS_01 = DictCodeListExt2.报送状态_未报送.getValue();
    
    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public  String reportInsertLongCount;
    /**
     * 根据最新导入的有效的产品登记编码数据，联动处理持有者信息待处理数据
     *
     * @param prdresistCodeList
     *            最新导入的有效的产品登记编码数据
     * @see
     * @since 1.0
     */
    public void refreshStatusAData(List<ChbPrdresistCode> prdresistCodeList) {
        if (StringUtil.isEmptyList(prdresistCodeList)) {
            return;
        }

        for (ChbPrdresistCode chbPrdresistCode : prdresistCodeList) {
            // 根据产品代码和机构代码，查询持有者信息有效数据
            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("prodId", chbPrdresistCode.getProdid());
            paramMap1.put("orgCode", chbPrdresistCode.getOrgCode());
            paramMap1.put("regCode", chbPrdresistCode.getRegCode());
            paramMap1.put("newProdId", chbPrdresistCode.getNewProdId());
            //修改重新更新有效的产品登记编码代码 begin 
//            List<InvestorsHoldInfo001InterfDto> statusAInvestholdInfoList1 = investorsHoldInfo001InterfMapper
//                    .selectStatusEInvestholdInfoData(paramMap1);
//            if (!StringUtil.isEmptyList(statusAInvestholdInfoList1)
//                    && "01".equals(SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_REP_IS_COVER))) {
//                // 将产品登记编码设值并转化为List<Map<String, String>>
//                for (ChbInvestholdInfo chbInvestholdInfo : statusAInvestholdInfoList1) {
//                    chbInvestholdInfo.setPrdRegistCode(chbPrdresistCode.getRegCode());
//                }
//
//                // 入库处理
//                this.refreshStatusEDataToDb(statusAInvestholdInfoList1);
//                this.refreshStatusEDataToDb1(statusAInvestholdInfoList1);
//            }
            if ("01".equals(SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_REP_IS_COVER))) {
            	investorsHoldInfo001InterfMapper.updateStatusEInvestholdInfoData(paramMap1);
            	investorsHoldInfo001InterfMapper.updateStatusEInvestholdRegistData(paramMap1);
            }
            //修改重新更新有效的产品登记编码代码 end
            // 根据产品代码和机构代码，查询持有者信息待处理数据（待处理，且产品登记编码为空）
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("prodId", chbPrdresistCode.getProdid());
            paramMap.put("orgCode", chbPrdresistCode.getOrgCode());
            paramMap.put("isCover", SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_REP_IS_COVER));
            List<ChbInvestholdInfo> statusAInvestholdInfoList = investorsHoldInfo001InterfMapper
                    .selectStatusAInvestholdInfoData(paramMap);

            if (!StringUtil.isEmptyList(statusAInvestholdInfoList)) {
                // 将产品登记编码设值并转化为List<Map<String, String>>
                List<Map<String, String>> dataMapList = new ArrayList<Map<String, String>>();
                for (ChbInvestholdInfo chbInvestholdInfo : statusAInvestholdInfoList) {
                    chbInvestholdInfo.setPrdRegistCode(chbPrdresistCode.getRegCode());
                    //add by liubin start
                    chbInvestholdInfo.setProdid(chbPrdresistCode.getNewProdId());
                    //add by liubin end
                    dataMapList.add(this.dtoToMap(chbInvestholdInfo));
                }

                List<InvestorsHoldInfo001InterfDto> listE = new ArrayList<>();
                List<InvestorsHoldInfo001InterfDto> listA = new ArrayList<>();

                // 数据检查
                checkDataInfo(dataMapList, false, listE, listA, null, null);

                // 入库处理
                this.refreshStatusADataToDb(listE, listA);
            }
        }
    }
    /**
     * 根据最新导入的有效的产品登记编码数据，联动处理持有信息有效的数据 入库处理
     *
     * @param listE
     *            校验通过数据
     * @see
     * @since 1.0
     */
//    private void refreshStatusEDataToDb(List<InvestorsHoldInfo001InterfDto> listE) {
//        // 待维护数据插入数据库
//        if (CollectionUtils.isEmpty(listE)) {
//            return;
//        }
//        // 修改产品登记编码更新持有信息维护表数据
//        if (!StringUtil.isEmptyList(listE)) {
//            if (StringUtil.isEmptyList(listE)) {
//                return;
//            }
//
//            int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
////          if (maxDataCountOneTime < 1) {
////              maxDataCountOneTime = 1000;
////          }
//            int dataCount = listE.size();
//            if (dataCount <= maxDataCountOneTime) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData2(listE, listE.get(0).getPrdRegistCode());
//                return;
//            }
//
//            List<InvestorsHoldInfo001InterfDto> newDataList = new ArrayList<InvestorsHoldInfo001InterfDto>();
//            newDataList.addAll(listE);
//
//            // 分批处理
//            int part = dataCount / maxDataCountOneTime; // 分批数
//            for (int i = 0; i < part; i++) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData2(newDataList.subList(0, maxDataCountOneTime),
//                        listE.get(0).getPrdRegistCode());
//                newDataList.subList(0, maxDataCountOneTime).clear();
//            }
//            // 最后剩下的数据
//            if (CollectionUtils.isNotEmpty(newDataList)) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData2(newDataList, listE.get(0).getPrdRegistCode());
//            }
//        }
//    }
    /**
     * 根据最新导入的有效的产品登记编码数据，联动处理持有信息登记表数据 入库处理
     *
     * @param listE
     *            校验通过数据
     * @see
     * @since 1.0
     */
//    private void refreshStatusEDataToDb1(List<InvestorsHoldInfo001InterfDto> listE) {
//        // 待维护数据插入数据库
//        if (CollectionUtils.isEmpty(listE)) {
//            return;
//        }
//        // 修改产品登记编码更新持有信息维护表数据
//        if (!StringUtil.isEmptyList(listE)) {
//            if (StringUtil.isEmptyList(listE)) {
//                return;
//            }
//
//            int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
////          if (maxDataCountOneTime < 1) {
////              maxDataCountOneTime = 1000;
////          }
//            int dataCount = listE.size();
//            if (dataCount <= maxDataCountOneTime) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoSubData(listE, listE.get(0).getPrdRegistCode());
//                return;
//            }
//
//            List<InvestorsHoldInfo001InterfDto> newDataList = new ArrayList<InvestorsHoldInfo001InterfDto>();
//            newDataList.addAll(listE);
//
//            // 分批处理
//            int part = dataCount / maxDataCountOneTime; // 分批数
//            for (int i = 0; i < part; i++) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoSubData(newDataList.subList(0, maxDataCountOneTime),
//                        listE.get(0).getPrdRegistCode());
//                newDataList.subList(0, maxDataCountOneTime).clear();
//            }
//            // 最后剩下的数据
//            if (CollectionUtils.isNotEmpty(newDataList)) {
//                investorsHoldInfo001InterfMapper.updateChbInvestholdInfoSubData(newDataList, listE.get(0).getPrdRegistCode());
//            }
//        }
//    }
    /**
     * 配置项中非空处理
     * @param Count
     * @param flag
     * @return
     */
    public int parsToInt(String count,String flag){
     if(StringUtil.isNotEmpty(count)&&Integer.parseInt(count)<=0){
         if("1".equals(flag)){
                return 20;
         }
         else if("1".equals(flag)){
             return 500;
      }
         else {
                return 100;
         }
        }
        else{
            return Integer.parseInt(count);
        }
   }
    /**
     * dto转化为map
     *
     * @param chbInvestholdInfo
     *            dto
     * @return map
     * @see
     * @since 1.0
     */
    private Map<String, String> dtoToMap(ChbInvestholdInfo chbInvestholdInfo) {
        Map<String, String> dataMap = new HashMap<String, String>();
        if (chbInvestholdInfo == null) {
            return dataMap;
        }

        dataMap.put("REGIST_BANK_CODE", chbInvestholdInfo.getRegistBankCode());
        dataMap.put("INDENTIFY_MARK", chbInvestholdInfo.getIndentifyMark());
        dataMap.put("PRD_REGIST_CODE", chbInvestholdInfo.getPrdRegistCode());
        dataMap.put("HOLD_DATE", DateUtil.format(chbInvestholdInfo.getHoldDate()));
        dataMap.put("CCY", chbInvestholdInfo.getCcy());
        if (chbInvestholdInfo.getAmount() != null) {
            dataMap.put("AMOUNT", chbInvestholdInfo.getAmount().toString());
        }
        if (chbInvestholdInfo.getQty() != null) {
            dataMap.put("QTY", chbInvestholdInfo.getQty().toString());
        }
        if (chbInvestholdInfo.getCnyAmt() != null) {
            dataMap.put("CNY_AMT", chbInvestholdInfo.getCnyAmt().toString());
        }
        dataMap.put("ORG_CODE", chbInvestholdInfo.getOrgCode());
        // add by etliu 20181017 start
        dataMap.put("DEPT_CODE", chbInvestholdInfo.getDeptCode());
        // add by etliu 20181017 end
        dataMap.put("AGENT", chbInvestholdInfo.getAgent());
        dataMap.put("ACCOUNT", chbInvestholdInfo.getProdid());

        return dataMap;
    }
    /**
     * 检查数据是否合法 <功能详细描述>
     *
     * @param mapList
     *            待检查数据
     * @param isExcel
     *            区分数据源
     * @param listE
     *            用来存储数据（检查通过的容器）
     * @param listA
     *            用来存储数据（检查不通过待处理的容器）
     * @param prodRegInfoList
     *            用来存储数据（系统中不存在的产品登记编码信息）
     * @param paraDto
     *            paraDto
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> checkDataInfo(List<Map<String, String>> mapList, boolean isExcel,
            List<InvestorsHoldInfo001InterfDto> listE, List<InvestorsHoldInfo001InterfDto> listA,
            List<ChbPrdresistCode> prodRegInfoList, InvestorsHoldInfo001InterfFileReadParaDto paraDto) {

        logger.info("checkDataInfo处理开始 " + DateUtil.getCurrentDateTime());

        List<ExcelValidDto> result = new ArrayList<>();
        if (mapList.isEmpty()) {
            result.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
        } else {
            if (isExcel) {
                //判断导入的数据是否存在重复的主键
                for(int i = 0; i < mapList.size(); i++) {
                    Map<String, String> checkMapI = mapList.get(i);
                    if(StringUtil.isEmpty(checkMapI.get("REGIST_BANK_CODE"))||StringUtil.isEmpty(checkMapI.get("ACCOUNT"))
                        ||StringUtil.isEmpty(checkMapI.get("INDENTIFY_MARK"))||StringUtil.isEmpty(checkMapI.get("HOLD_DATE"))
                        ||StringUtil.isEmpty(checkMapI.get("CCY"))){
                        continue;
                    }
                    for(int j = i + 1; j< mapList.size(); j++) {
                        Map<String, String> checkMapJ = mapList.get(j);
                        if(checkMapI.get("REGIST_BANK_CODE").equals(checkMapJ.get("REGIST_BANK_CODE")) && checkMapI.get("ACCOUNT").equals(checkMapJ.get("ACCOUNT"))
                            && checkMapI.get("INDENTIFY_MARK").equals(checkMapJ.get("INDENTIFY_MARK"))
                            && checkMapI.get("HOLD_DATE").equals(checkMapJ.get("HOLD_DATE"))
                            && checkMapI.get("CCY").equals(checkMapJ.get("CCY"))) {
                            result.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(j + 2),
                                this.getMessage("REPORT_COMMON_FILE_E_0027", String.valueOf(i + 2)+","+String.valueOf(j + 2))));
                            return result;
                        }
                    }
                }
            }

            String bankCode;
            if (isExcel) {
                bankCode = CommonUtil.getUserBankCode();
            } else {
                bankCode = "";
            }

            boolean isNeed = true;

            // 登记银行编码和产品登记编码的数据，在上游接口的情况，预先查询好传进来，避免重复查询
            // //获取所有的登记银行编码
            // Map<String,String> registMap =
            // investorsHoldInfo001InterfMapper.getAllRegistBankCodeSet().stream().collect(JoyinCollectors.toMap(x->x.get("REGIST_BANK_CODE"),
            // x->x.get("ORG_CODE")));
            // //获取所有的产品登记编码
            // Map<String,String> prdRegistMap =
            // investorsHoldInfo001InterfMapper.getAllRegistProdCodeSet().stream().collect(JoyinCollectors.toMap(x->x.get("PRODID"),
            // x->x.get("REG_CODE")));
            // 获取所有的登记银行编码
            Map<String, String> registMap = new HashMap<String, String>();
            // 获取所有的产品登记编码
            Map<String, String> prdRegistMap = new HashMap<String, String>();
            if (paraDto != null && paraDto.getRegistMap() != null) {
                registMap = paraDto.getRegistMap();
            } else {
                registMap = investorsHoldInfo001InterfMapper.getAllRegistBankCodeSet().stream()
                        .collect(JoyinCollectors.toMap(x -> x.get("REGIST_BANK_CODE"), x -> x.get("ORG_CODE")));
            }
            if (paraDto != null && paraDto.getPrdRegistMap() != null) {
                prdRegistMap = paraDto.getPrdRegistMap();
            } else {
                prdRegistMap = investorsHoldInfo001InterfMapper.getAllRegistProdCodeSet().stream()
                        .collect(JoyinCollectors.toMap(x -> x.get("PRODID"), x -> x.get("REG_CODE")));
            }

            // //获取所有的有效的投资者
            // Map<String,String> investorMap =
            // investorsHoldInfo001InterfMapper.getAllInvestorSet().stream().collect(JoyinCollectors.toMap(x->x.get("REGIST_BANK_CODE")+GlobalConstant.VERTICAL_LINE+x.get("INDENTIFY_MARK"),
            // x->x.get("INDENTIFY_MARK")));
            // 汇率mapper
            Map<String, BigDecimal> rateMap = new HashMap<>();
            int rowId = 1;
            // add by wangmin 2018-07-11 start
            // prodRegInfoList若为null，下方synchronized（prodRegInfoList） 方法报错
            if (prodRegInfoList == null) {
                prodRegInfoList = new ArrayList<ChbPrdresistCode>();
            }
            // add by wangmin 2018-07-11 end
            // prodRegInfoList若为null，下方synchronized（prodRegInfoList） 方法报错
            for (Map<String, String> item : mapList) {
                List<String> message = new ArrayList<>();
                //String checkMess="";
                InvestorsHoldInfo001InterfDto dto = new InvestorsHoldInfo001InterfDto();
                String registBankCode = item.get("REGIST_BANK_CODE");
                // 登记银行代码校验
                if (StringUtil.isEmpty(registBankCode)) {
                    message.add(this.getMessage("REPORT_COMMON_E_0057", "登记银行代码"));
                    isNeed = false;
                }
                // excel中的登记银行代码必须与当前导入人的登记银行代码一致!
                if (isExcel) {
                    if (!StringUtil.equals(bankCode, registBankCode)) {
                        message.add("登记银行代码必须与当前用户的登记银行代码一致!");
                    }
                    // add by etliu 20180912 start
                    else if (findPatern(PATTERN_ALL, registBankCode)) {
                        message.add(this.getMessage("CHB_EQUITYINFO_E_035", "登记银行代码"));
                    }
                    // add by etliu 20180912 end
                    else {
                        dto.setRegistBankCode(registBankCode);
                        dto.setOrgCode(CommonUtil.getOrgCode());
                    }
                }
                // 上游接口 为空或不存在直接移除
                else {
                    if (!registMap.containsKey(registBankCode)) {
                        isNeed = false;
                        message.add("登记银行代码在机构管理中不存在！");
                    } else {
                        dto.setRegistBankCode(registBankCode);
                        if (item.containsKey("ORG_CODE") && StringUtil.isNotEmpty(item.get("ORG_CODE"))) {
                            dto.setOrgCode(item.get("ORG_CODE"));
                        } else {
                            // 未传机构代码 数据字段遗失不保存
                            isNeed = false;
                        }
                    }
                }

                // 识别标识校验
                String indentifyMark = item.get("INDENTIFY_MARK");
                if (StringUtil.isEmpty(indentifyMark)) {
                    isNeed = false;
                    message.add(this.getMessage("REPORT_COMMON_E_0057", "识别标识"));
                } else if (!Pattern.compile("[^\u4e00-\u9fa5]+").matcher(indentifyMark).matches()) {
                    isNeed = false;
                    message.add(this.getMessage("CHB_EQUITYINFO_E_012", "识别标识"));
//              } else if (INDENTIFY_MARK.length() > 30) {
                } else if (getByteLength(indentifyMark) > 30) {
                    isNeed = false;
                    message.add(this.getMessage("REPORT_COMMON_E_0002", "识别标识", 30));
                }
                // add by etliu 20180912 start
                else if (findPatern(PATTERN_ALL, indentifyMark)) {
                    message.add(this.getMessage("CHB_EQUITYINFO_E_035", "识别标识"));
                }
                // add by etliu 20180912 end
                else {
                    dto.setIndentifyMark(indentifyMark);
                }
                // //判断系统是否存在该投资者的信息
                // if(StringUtil.isNotEmpty(dto.getRegistBankCode())&&StringUtil.isNotEmpty(dto.getIndentifyMark())&&
                // !investorMap.containsKey(dto.getRegistBankCode()+GlobalConstant.VERTICAL_LINE+dto.getIndentifyMark())){
                // message.add(this.getMessage("REPORT_E_0015", "识别标识"));
                // }
                // 持有日期校验
                String holdDate = item.get("HOLD_DATE");
                //判断持有日期是否符合中债要求yyyy-MM-dd
//              try {
//                  SimpleDateFormat df = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT_EN);
//                  Date temp = df.parse(HOLD_DATE);
//              } catch (Exception e) {
//                  isNeed = false;
//                  message.add(this.getMessage("CHB_INVESTHOLD_HOLDDATE_CHECK"));
//              }


                Date currDate = DateUtil.now();//当前日期
                if (StringUtil.isEmpty(holdDate)) {
                    isNeed = false;
                    message.add(this.getMessage("REPORT_COMMON_E_0057", "持有日期"));
                } else if (holdDate.length() < 8) {
                    isNeed = false;
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "持有日期"));
                } else if (!DateUtil.isDateStr(holdDate, DateUtil.DEFAULT_OTHER_FORMAT,
                        DateUtil.DEFAULT_ANOTHER_FORMAT, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                    isNeed = false;
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "持有日期"));
                } else if (DateUtil.isDateStr(holdDate, DateUtil.DEFAULT_OTHER_FORMAT)
                        && !isDateE(holdDate, DateUtil.DEFAULT_OTHER_FORMAT)) {
                    isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
                } else if (DateUtil.isDateStr(holdDate, DateUtil.DEFAULT_ANOTHER_FORMAT)
                        && !isDateE(holdDate, DateUtil.DEFAULT_ANOTHER_FORMAT)) {
                    isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
                } else if (DateUtil.isDateStr(holdDate, DateUtil.DEFAULT_DATE_FORMAT_EN)
                        && !isDateE(holdDate, DateUtil.DEFAULT_DATE_FORMAT_EN)) {
                    isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
                }
                // add by etliu 20180912 start
                else if (findPatern(PATTERN_ALL, holdDate)) {
                    message.add(this.getMessage("CHB_EQUITYINFO_E_035", "持有日期"));
                }
                // add by etliu 20180912 end
                else if(DateUtil.compareDate(currDate, DateUtil.parse(holdDate))<0){
                    isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE_2"));
                }else {
                    dto.setHoldDate(DateUtil.parse(holdDate));
                }

                // 币种校验
                String ccy = item.get("CCY");
                if (StringUtil.isEmpty(ccy)) {
                    isNeed = false;
                    message.add(getMessage("REPORT_COMMON_E_0001", "币种"));
                } else {
                    Map<String, String> dictCodeMap = SysDataUtil.getSysDictDataMap(DictCode.币种_中债);
                    //直接判断
//                  if(!dictCodeMap.containsKey(CCY)){
//                      isNeed = false;
//                      message.add(getMessage("CHBCONNECT_E_0002", "币种"));
//                  }else{
//                      dto.setCcy(CCY);
//                  }
                    String[] codeList = StringUtil.split(ccy, StringUtil.SPACE);
                    if (!dictCodeMap.containsKey(codeList[0])) {
                        isNeed = false;
                        message.add(getMessage("CHBCONNECT_E_0002", "币种"));
                    }
                    // add by etliu 20180912 start
                    else if (findPatern(PATTERN_ALL, ccy)) {
                        message.add(this.getMessage("CHB_EQUITYINFO_E_035", "币种"));
                    }
                    // add by etliu 20180912 end
                    else {
                        dto.setCcy(codeList[0]);
                    }
                }
                // 持有份额
                String qty = item.get("QTY");
                String qtyInfo = checkMoney(qty, "持有份额", 13, 5, true);
                if (StringUtil.isNotEmpty(qtyInfo)) {
                    message.add(qtyInfo);
                } else {
                    dto.setQty(new BigDecimal(qty));
                }

                // 持有金额
                String amount = item.get("AMOUNT");
                String amountInfo = checkMoney(amount, "持有金额", 13, 2, true);
                if (StringUtil.isNotEmpty(amountInfo)) {
                    message.add(amountInfo);
                } else {
                    dto.setAmount(new BigDecimal(amount));
                }

                // 折算人民币金额（元）
                if (item.containsKey("CNY_AMT")) {
                    String cnyAmt = item.get("CNY_AMT");
                    String cnyAmtInfo = checkMoney(cnyAmt, "折算人民币金额（元）", 13, 2, false);
                    if (StringUtil.isNotEmpty(cnyAmtInfo)) {
                        message.add(cnyAmtInfo);
                    } else if (StringUtil.isNotEmpty(cnyAmt)) {
                        dto.setCnyAmt(new BigDecimal(cnyAmt));
                    }
                }
                //如果币种是人民币时，持有金额和折算人民币金额必须保持一致
                if (isExcel) {
                    try {
                        String[] codeList1 = StringUtil.split(ccy, StringUtil.SPACE);
                        BigDecimal amount1 = new BigDecimal(item.get("AMOUNT"));
                        BigDecimal cnyAmt1 = new BigDecimal(item.get("CNY_AMT"));
//                      if("CNY".equals(codeList1[0])){
                        if (StringUtil.equals(codeList1[0], DictCodeListExt1.币种_中债_人民币.getValue())) {
                            if(amount1 != null || cnyAmt1 != null){
                                if (cnyAmt1.compareTo(amount1) != 0) {
                                    message.add(getMessage("REPORT_COMMON_FILE_E_0025"));
                                }
                            }
                        }
                    } catch (Exception e) {
                    	logger.error(e.getMessage(), e);
                    }
                }
                // 部门代码
                if (item.containsKey("DEPT_CODE") || StringUtil.isNotEmpty(item.get("DEPT_CODE"))) {
                    dto.setDeptCode(item.get("DEPT_CODE"));
                }
                // 经办人
                if (item.containsKey("AGENT") || StringUtil.isNotEmpty(item.get("AGENT"))) {
                    dto.setAgent(item.get("AGENT"));
                }
                // 创建部门
                if (isExcel) {
                    String createDept = CommonUtil.getDeptCode() != null ? CommonUtil.getDeptCode()
                            : CommonUtil.getOrgCode();
                    dto.setCreateDept(createDept);
                    dto.setDeptCode(CommonUtil.getDeptCode());
                } else {
                    String createDept = StringUtil.isNotEmpty(item.get("DEPT_CODE")) ? item.get("DEPT_CODE")
                            : item.get("ORG_CODE");
                    dto.setCreateDept(createDept);
                }
                String account = item.get("ACCOUNT");

                //新增产品登记编码格式校验
                String regCode = item.get("REG_CODE");
                if (StringUtil.isNotEmpty(regCode) && !regCode.matches("^[0-9a-zA-Z]{8}[ABC]?[0-9]{6}")){
                	//  isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKREGCODE"));
                }
                // add by etliu 20180912 start
                else if (StringUtil.isNotEmpty(regCode) && findPatern(PATTERN_ALL, regCode)) {
                    message.add(this.getMessage("CHB_EQUITYINFO_E_035", "产品登记编码"));
                }
                // add by etliu 20180912 end

                // 产品编码校验
//              String ACCOUNT = item.get("ACCOUNT");

                // 新加产品代码字段
                dto.setProdid(account);

                if (StringUtil.isEmpty(account)) {
                    isNeed = false;
                    message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
                }
                else {
                    if (!prdRegistMap.containsKey(account)) {
                        // if(StringUtil.isNotEmpty(item.get("REG_CODE"))){
                        dto.setPrdRegistCode(item.get("REG_CODE"));
//                        boolean flag = true;
//
//                        // 多线程情况下需要加锁，否则报错concurrentmodificationexception
//                        synchronized (prodRegInfoList) {
//                            if (CollectionUtils.isNotEmpty(prodRegInfoList)) {
//                                for (ChbPrdresistCode chbPrdresistCode : prodRegInfoList) {
//                                    if (StringUtil.equals(ACCOUNT, chbPrdresistCode.getProdid())) {
//                                        flag = false;
//                                        break;
//                                    }
//                                }
//                            }
//                            if (flag) {
//                                // 产品登记编码信息
//                                ChbPrdresistCode prodRegInfoDto = new ChbPrdresistCode();
//                                prodRegInfoDto.setProdid(ACCOUNT);
//                                prodRegInfoDto.setRegCode(item.get("REG_CODE"));
//                                prodRegInfoDto.setOrgCode(item.get("ORG_CODE"));
//                                // prodRegInfoDto.setDeptCode(dto.getDeptCode());
//                                // prodRegInfoDto.setCreateDept(dto.getCreateDept());
//                                prodRegInfoDto.setDeptCode(null);
//                                prodRegInfoDto.setCreateDept(null);
//                                prodRegInfoList.add(prodRegInfoDto);
//                            }
//                        }
                        // }
                        // else{
                        // isNeed = false;
                        // message.add(this.getMessage("REPORT_E_0015", "产品登记编码"));
                        // }
                        if (!StringUtil.isNotEmpty(item.get("REG_CODE"))) {
                            message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
                        } else if (StringUtil.isNotEmpty(item.get("REG_CODE")) && item.get("REG_CODE").length() != 14
                                && item.get("REG_CODE").length() != 15) {
                            message.add("产品登记编码的长度为14或15位！");
                        }
                    } else {
                        if (StringUtil.isEmpty(prdRegistMap.get(account)) && StringUtil.isEmpty(item.get("REG_CODE"))) {
                            message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
                        } else if (StringUtil.isNotEmpty(item.get("REG_CODE")) && item.get("REG_CODE").length() != 14
                                && item.get("REG_CODE").length() != 15) {
                            message.add("产品登记编码的长度为14或15位！");
                        } else if (StringUtil.equals(prdRegistMap.get(account), item.get("REG_CODE"))
                                || StringUtil.isEmpty(item.get("REG_CODE"))) {
                            dto.setPrdRegistCode(prdRegistMap.get(account));
                        } else if (StringUtil.isEmpty(prdRegistMap.get(account))) {
                            dto.setPrdRegistCode(item.get("REG_CODE"));
                        } else {
                            dto.setPrdRegistCode(item.get("REG_CODE"));
                            message.add(this.getMessage("REPORT_REG_MISMACTH"));
                        }
                    }
                }

                dto.setRowId(String.valueOf(rowId));
                // 判断为空check校验通过
                if (message.isEmpty()) {
                    dto.setInterfCheckResult(" ");
                    // 折算人民币处理
                    if (dto.getCnyAmt() == null) {
                        if (StringUtil.equals(dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())) {
                            dto.setCnyAmt(dto.getAmount());
                        } else {
                            String key = DateUtil.format(dto.getHoldDate()) + dto.getCcy();
                            if (rateMap.containsKey(key)) {
                                dto.setCnyAmt(ArithmeticUtil.multiply(rateMap.get(key), dto.getAmount()));
                            } else {
                                Map<String, Object> temp = new HashMap<>();
                                // 原币种
                                temp.put("CCY_ORIGINAL", dto.getCcy());
                                // 持有日期
                                temp.put("HOLDDATE", dto.getHoldDate());
                                // 折算币种
                                temp.put("CCY_CONVERT", DictCodeListExt1.币种_中债_人民币.getValue());
                                // 汇率计算规则
                                temp.put("pricetype",SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE));
                                BigDecimal rate = investorsHoldInfo001InterfMapper.getRateNearByDate(temp);
                                if (rate != null) {
                                    dto.setCnyAmt(ArithmeticUtil.multiply(rate, dto.getAmount()));
                                    rateMap.put(key, rate);
                                } else {
                                    message.add("汇率不存在！");
                                }
                            }
                        }
                    }
                }
                //反馈状态是发送成功不能修改
                if (isExcel) {
                    try {
                        List<ChbInvestholdInfoSub> subListUp = new ArrayList<>();
                        ChbInvestholdInfoSub subDto = new ChbInvestholdInfoSub();
                        String[] codeList = StringUtil.split(ccy, StringUtil.SPACE);
                        subDto.setIndentifyMark(item.get("INDENTIFY_MARK"));
                        subDto.setProdid(item.get("ACCOUNT"));
                        subDto.setCcy(codeList[0]);
                        subDto.setHoldDate(DateUtil.parse(item.get("HOLD_DATE")));
                        subDto.setRegistBankCode(item.get("REGIST_BANK_CODE"));
                        subListUp.add(subDto);
                        List<InvestorsHoldInfoRegist001InterfDto> submitFeedbackStatusList = this
                                .getSubmitFeedbackStatusList(subListUp);
                        if (CollectionUtils.isNotEmpty(submitFeedbackStatusList)) {
                            for (InvestorsHoldInfoRegist001InterfDto action : submitFeedbackStatusList) {
                                 if ("01".equals(action.getSubmitFeedbackStatus())
                                        || "02".equals(action.getSubmitFeedbackStatus())
                                        || "05".equals(action.getSubmitFeedbackStatus())
                                        || StringUtil.isBlank(action.getSubmitFeedbackStatus())) {
                                    message.add("该数据存在中债报送流程中的数据，不允许修改，请稍后再试！");
                                 }
                            }
                        }
                    } catch (Exception e) {
                    	logger.error(e.getMessage(), e);
                    }
                }
                // 判断为空check校验通过
                if (message.isEmpty()) {
                    listE.add(dto);
                } else {
                    // 存在错误信息 判断业务主键是否完整
                    if (isNeed && !isExcel) {
                        try {
                            String checkMess = "";
                            for (String msg : message) {
                                checkMess += msg;
                            }
                            if (checkMess.length() > 200) {
                                checkMess = checkMess.substring(0, 200);
                            }
                            dto.setInterfCheckResult(checkMess);
                        }
                        catch (Exception e) {
                        	logger.error(e.getMessage(), e);
                        }

                        listA.add(dto);
                    }

                    // 打印检查MESSAGE
                    try {
//                        System.out.println("识别标识[" + INDENTIFY_MARK + "] 产品[" + ACCOUNT + "]检查错误消息如下：");
                        logger.info("识别标识[{}] 产品[{}]检查错误消息如下：",indentifyMark,account);
                        for (String msg : message) {
                            //checkMess+=msg;
//                            System.out.println(msg);
                            logger.info(msg);
                        }
                    } catch (Exception e) {
                    	logger.error(e.getMessage(), e);
                    }
                    // 生成错误信息提醒
                    result.add(new ExcelValidDto(indentifyMark, String.valueOf(rowId + 1),
                            StringUtil.joinString(message, "<br>")));
                }
                rowId++;
            }
        }

        logger.info("checkDataInfo处理结束 " + DateUtil.getCurrentDateTime());

        return result;
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
     * 校验输入内容是否满足正则表达式 <功能详细描述>
     *
     * @param partern 正则表达式
     * @param value 需要校验的字符串
     * @return 满足返回false,不满足返回true
     * @see
     * @since 1.0
     */
    public static boolean findPatern(String parternid, String value) {
        boolean flag = true;
        Pattern pattern = Pattern.compile(parternid);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            flag = false;
        }
        return flag;
    }
    /**
     * 获取字节数（GBK编码，一个汉字两个字节）
     *
     * @param str str
     * @return 字节数
     * @see
     * @since 1.0
     */
    public static int getByteLength(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return str.getBytes("GBK").length;
            }
            catch (UnsupportedEncodingException e) {
            }
        }
        return 0;
    }
    /**
     * 判断代码是否合法，返回错误信息
     *
     * @param code
     *            代码
     * @param intlen
     *            长度
     * @param declen
     *            长度
     * @param cloumnTitle
     *            列名
     * @param isRequired
     *            是否必须
     * @return 错误信息
     */
    private String checkMoney(String code, String cloumnTitle, int intlen, int declen, boolean isRequired) {
        String message = null;
        // 必须验证
        if (StringUtil.isEmpty(code)) {
            if (isRequired) {
                message = this.getMessage("REPORT_COMMON_E_0001", cloumnTitle);
                return message;
            } else {
                return null;
            }
        }
        // 属性验证
        if (!CommonUtil.isFloatNumeric(code)) {
            message = this.getMessage("REPORT_COMMON_E_0003", cloumnTitle);
        }
        // add by etliu 20180912 start
        else if (StringUtil.isNotEmpty(code)
                 && findPatern(PATTERN_ALL, code)) {
            message = this.getMessage("CHB_EQUITYINFO_E_035", cloumnTitle);
        }
        // add by etliu 20180912 end
        else {
            BigDecimal num = new BigDecimal(code);
            if (num.compareTo(BigDecimal.ZERO) <= 0) {
                message = this.getMessage("REPORT_COMMON_E_0024", cloumnTitle, "0");
            }

            // 长度验证
            if ((num.precision() - num.scale()) > intlen || num.scale() > declen) {
                message = this.getMessage("REPORT_ASSETBOND_E_001", cloumnTitle, intlen, declen);
            }
        }
        return message;
    }
    /**
     * 传入日期，日期是否符合日期规范
     *
     * @param date
     *            传入日期
     * @param pattern
     *            日期转换
     * @return
     */
    public static boolean isDateE(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * 持有信息修改之前查询反馈状态
     * <功能详细描述>
     * @param pageParam
     * @see
     * @since 1.0
     */
   public List<InvestorsHoldInfoRegist001InterfDto>  getSubmitFeedbackStatusList(List<ChbInvestholdInfoSub> subListUp){
       return investorsHoldInfo001InterfMapper.getSubmitFeedbackStatusList(subListUp);
   }
   /**
    * 根据最新导入的有效的产品登记编码数据，联动处理持有者信息待处理数据 入库处理
    *
    * @param listE
    *            校验通过数据
    * @param listA
    *            校验不通过数据
    * @see
    * @since 1.0
    */
   private void refreshStatusADataToDb(List<InvestorsHoldInfo001InterfDto> listE, List<InvestorsHoldInfo001InterfDto> listA) {
       // 待维护数据插入数据库
       if (CollectionUtils.isEmpty(listE) && CollectionUtils.isEmpty(listA)) {
           return;
       }

       if (!StringUtil.isEmptyList(listA)) {
           this.updateChbInvestholdInfoDataByPart(listA, listA.get(0).getPrdRegistCode(),listA.get(0).getProdid(),
                   DictCodeListExt1.维护数据状态_待处理.getValue());
       }

       if (!StringUtil.isEmptyList(listE)) {
           this.updateChbInvestholdInfoDataByPart(listE, listE.get(0).getPrdRegistCode(),listE.get(0).getProdid(),
                   DictCodeListExt1.维护数据状态_有效.getValue());

           // 处理登记数据
           // 查询导入文件中的数据在数据库中报送表已经存在的数据
           final Map<String, ChbInvestholdInfoSub> allSubHoldInfoDataMap = selectAllSubHoldInfoData(listE);
           // 插入操作
           List<ChbInvestholdInfoSub> subListIn = new ArrayList<>();
           // 更新操作
           List<ChbInvestholdInfoSub> subListUp = new ArrayList<>();

           for (InvestorsHoldInfo001InterfDto action : listE) {
               // 数据来源
               action.setDataSource(DictCodeListExt2.数据来源_系统新建.getValue());
               // 用来区分的key(登记表用)
               String dataKey = action.getRegistBankCode() + GlobalConstant.UNDER_LINE + action.getIndentifyMark()
                       + GlobalConstant.UNDER_LINE + action.getPrdRegistCode() + GlobalConstant.UNDER_LINE
                       + action.getPrdRegistCode() + GlobalConstant.UNDER_LINE + action.getCcy()
                       + GlobalConstant.UNDER_LINE + DateUtil.format(action.getHoldDate());

               ChbInvestholdInfoSub chbInvestholdInfoSub = new ChbInvestholdInfoSub();
               BeanUtil.copyPropertiesIgnoreNull(chbInvestholdInfoSub, action);
               action.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
               String subStatus = StringUtil.EMPTY;
               chbInvestholdInfoSub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
               chbInvestholdInfoSub.setStatusDate(new Date());
               if (allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_02)
                       || allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_03)) {
                   subStatus = DictCodeListExt2.报送数据状态_修改.getValue();
               } else {
                   subStatus = DictCodeListExt2.报送数据状态_新建.getValue();
               }
               // 数据状态
               chbInvestholdInfoSub.setStatus(subStatus);
               // 当前批次报送时点集合
               Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
               List<Date> dateList = null;
               // 报送时点通用方法
               dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(), subStatus,
                       dateMap, chbInvestholdInfoSub.getHoldDate());
               if (CollectionUtils.isNotEmpty(dateList)) {
                   chbInvestholdInfoSub.setChbSubmitDeadline(dateList.get(0));
                   chbInvestholdInfoSub.setSubmitDeadline(dateList.get(1));
               }
               if (allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_01)) {
                   chbInvestholdInfoSub.setRegistSeqno(allSubHoldInfoDataMap
                           .get(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_01).getRegistSeqno());
                   subListUp.add(chbInvestholdInfoSub);
               } else {
                   chbInvestholdInfoSub.setRegistSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
                   subListIn.add(chbInvestholdInfoSub);
               }
           }

           // 登记表入库
           if (subListIn != null && subListIn.size() > 0) {
               insertChbSubHoldInfoDataByPart(subListIn);
           }
           if (subListUp != null && subListUp.size() > 0) {
               insertChbSubHoldInfoDataByPart(subListUp);
           }
       }
   }
   /**
    * 更新投资者持有信息维护表的产品登记编码和数据状态字段
    *
    * @param dataList
    *            数据列表
    * @see
    * @since 1.0
    */
   private void updateChbInvestholdInfoDataByPart(List<InvestorsHoldInfo001InterfDto> dataList, String prdRegistCode,String prodid,
           String statusToUpdate) {
       if (StringUtil.isEmptyList(dataList)) {
           return;
       }

       int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
       if (maxDataCountOneTime < 1) {
           maxDataCountOneTime = 1000;
       }
       int dataCount = dataList.size();
       if (dataCount <= maxDataCountOneTime) {
           investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData(dataList, prdRegistCode, prodid,statusToUpdate);
           return;
       }

       List<InvestorsHoldInfo001InterfDto> newDataList = new ArrayList<InvestorsHoldInfo001InterfDto>();
       newDataList.addAll(dataList);

       // 分批处理
       int part = dataCount / maxDataCountOneTime; // 分批数
       for (int i = 0; i < part; i++) {
           investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData(newDataList.subList(0, maxDataCountOneTime),
                   prdRegistCode, prodid,statusToUpdate);
           newDataList.subList(0, maxDataCountOneTime).clear();
       }
       // 最后剩下的数据
       if (CollectionUtils.isNotEmpty(newDataList)) {
           investorsHoldInfo001InterfMapper.updateChbInvestholdInfoData(newDataList, prdRegistCode, prodid,statusToUpdate);
       }
   }
   /**
    * 查询维护表中存在的相关数据 <功能详细描述>
    *
    * @param listE
    *            数据集合
    * @return 匹配的数据
    * @see
    * @since 1.0
    */
   private Map<String, ChbInvestholdInfoSub> selectAllSubHoldInfoData(List<InvestorsHoldInfo001InterfDto> listE) {
       // 查询导入文件中的数据在数据库中已经存在的数据
       List<ChbInvestholdInfoSub> allDataList = new ArrayList<ChbInvestholdInfoSub>();

       int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
//     if (maxDataCountOneTime < 1) {
//         maxDataCountOneTime = 1000;
//     }
       int dataCount = listE.size();
       if (dataCount <= maxDataCountOneTime) {
           allDataList = investorsHoldInfo001InterfMapper.selectAllSubHoldInfoData(listE);
       } else {
           List<InvestorsHoldInfo001InterfDto> newKeyList = new ArrayList<InvestorsHoldInfo001InterfDto>();
           newKeyList.addAll(listE);

           // 分批处理
           int part = dataCount / maxDataCountOneTime; // 分批数
           for (int i = 0; i < part; i++) {
               allDataList.addAll(investorsHoldInfo001InterfMapper
                       .selectAllSubHoldInfoData(newKeyList.subList(0, maxDataCountOneTime)));
               newKeyList.subList(0, maxDataCountOneTime).clear();
           }
           // 最后剩下的数据
           if (CollectionUtils.isNotEmpty(newKeyList)) {
               allDataList.addAll(investorsHoldInfo001InterfMapper.selectAllSubHoldInfoData(newKeyList));
           }
       }

       Map<String, ChbInvestholdInfoSub> allHoldInfoDataMap = StringUtil.isEmptyList(allDataList)
               ? new HashMap<String, ChbInvestholdInfoSub>()
               : allDataList.stream()
                       .collect(JoyinCollectors.toMap(x -> x.getRegistBankCode() + GlobalConstant.UNDER_LINE
                               + x.getIndentifyMark() + GlobalConstant.UNDER_LINE + x.getPrdRegistCode()
                               + GlobalConstant.UNDER_LINE + x.getPrdRegistCode() + GlobalConstant.UNDER_LINE
                               + x.getCcy() + GlobalConstant.UNDER_LINE + DateUtil.format(x.getHoldDate())
                               + GlobalConstant.UNDER_LINE + x.getSendStatus(), x -> x));

       return allHoldInfoDataMap;
   }
   /**
    * 分批登记数据插入数据库 <功能详细描述>
    *
    * @param subList
    *            待处理数据
    * @see
    * @since 1.0
    */
   private void insertChbSubHoldInfoDataByPart(List<ChbInvestholdInfoSub> subList) {
        //如果反馈状态是通过，修改持有信息时，且数据没有任何变化，则登记表不生成未报送数据
        //如果反馈状态是通过，修改持有信息时，且数据没有任何变化，则登记表不生成未报送数据
       List<ChbInvestholdInfoSub> subListNew = new ArrayList<ChbInvestholdInfoSub>();
       for(ChbInvestholdInfoSub sub : subList){
           if("02".equals(sub.getStatus())){
               subListNew.add(sub);
           }
       }
       if (CollectionUtils.isNotEmpty(subListNew)) {
           List<InvestorsHoldInfoRegist001InterfDto> submitFeedbackStatusList = this.getSubmitFeedbackStatusList(subListNew);
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
           if (CollectionUtils.isNotEmpty(submitFeedbackStatusList)) {
               for (InvestorsHoldInfoRegist001InterfDto action : submitFeedbackStatusList) {
                   if ("03".equals(action.getSubmitFeedbackStatus())) {
                       if (CollectionUtils.isNotEmpty(subList)) {
                           Iterator<ChbInvestholdInfoSub> it = subList.iterator();
                           while (it.hasNext()) {
                               ChbInvestholdInfoSub dto = it.next();
                               if (dto.getRegistBankCode().equals(action.getRegistBankCode())
                                       && dto.getProdid().equals(action.getProdid())
                                       && dto.getCcy().equals(action.getCcy())
                                       && dto.getIndentifyMark().equals(action.getIndentifyMark())
                                       && sdf.format(dto.getHoldDate()).equals(sdf.format(action.getHoldDate()))
                                       && (dto.getAmount().compareTo(action.getAmount()) == 0)
                                       && (dto.getQty().compareTo(action.getQty()) == 0)
                                       && (dto.getCnyAmt().compareTo(action.getCnyAmt()) == 0)) {
                                   it.remove();
                               }
                           }
                       }
                   }
               }
           }
       }
       if (CollectionUtils.isNotEmpty(subList)) {
           int maxDataCountOneTime = this.parsToInt(reportInsertLongCount, "1");
           int dataCount = subList.size();
           if (dataCount <= maxDataCountOneTime) {
               investorsHoldInfo001InterfMapper.insertChbSubHoldInfoData(subList);
               return;
           }

           List<ChbInvestholdInfoSub> newDataList = new ArrayList<ChbInvestholdInfoSub>();
           newDataList.addAll(subList);

           // 分批处理
           int part = dataCount / maxDataCountOneTime; // 分批数
           for (int i = 0; i < part; i++) {
               investorsHoldInfo001InterfMapper.insertChbSubHoldInfoData(newDataList.subList(0, maxDataCountOneTime));
               newDataList.subList(0, maxDataCountOneTime).clear();
           }
           // 最后剩下的数据
           if (CollectionUtils.isNotEmpty(newDataList)) {
               investorsHoldInfo001InterfMapper.insertChbSubHoldInfoData(newDataList);
           }
       }
   }

}