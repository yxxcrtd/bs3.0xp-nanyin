/*
 * 文件名：EquityInfoRegist002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：导出删除模板共通页面
 * 修改人：Administrator
 * 修改时间：2019年5月30日
 * 修改内容：新建
 * 系统名称：中债报送监管系统
 */

package com.joyintech.tams.svreport.chb.common.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.Page;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dao.EquityBusiInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * 导出删除模板共通逻辑
 *
 * @author 魏玉强
 * @version 1.7.0 2019年5月30日
 * @see EquityExportDeleteExcelConfirmMService
 * @since 1.0
 */
@Service
public class EquityExportDeleteExcelConfirmMService extends BaseService {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EquityInfoRegist001Mapper equityInfoRegist001Mapper;

    @Autowired
    private EquityBusiInfoRegist001Mapper equityBusiInfoRegist001Mapper;

    @Autowired
    private InvestorsHoldInfoRegist001Mapper investorsHoldInfoRegist001Mapper;

    public ResponseEntity<byte[]> export(Map<String, Object> param) {
        // 是否是勾选的
        boolean exKeys = param.containsKey("expKeys");
        // 原页面id
        String pageId = (String) param.get("orgPageId");
        String exportKeys = "";
        // 是否手工处理
        String doMannulFlag = String.valueOf(param.get("doMannulFlag"));
        // 删除原因
        String deleteReason = (String) param.get("remark");
        // 删除信息为空则直接返回空数据
        Map<String, Object> dataParam = new HashMap<String, Object>();
        // 导出Excel模板名称
        String excelTemplate = "";
        File outFile = null;
        if ("EquityInfoRegist001".equals(pageId)) {
            List<EquityInfoRegist001Dto> list = new ArrayList<>();
            if (exKeys) {
                // 勾选数据
                exportKeys = (String) param.get("expKeys");
                List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
                List<EquityInfoRegist001Dto> equiytRegistList = new ArrayList<EquityInfoRegist001Dto>();
                for (String string : keys) {
                    EquityInfoRegist001Dto equityInfoRegist001Dto = new EquityInfoRegist001Dto();
                    String[] key = StringUtil.split(string, "|");
                    equityInfoRegist001Dto.setBankCode(key[0]);
                    equityInfoRegist001Dto.setIndentifyMark(key[1]);
                    equityInfoRegist001Dto.setSubmitStatus(key[2]);
                    equiytRegistList.add(equityInfoRegist001Dto);
                }
                list = equityInfoRegist001Mapper.queryExportListByKeys(equiytRegistList);
            } else {
                // 无勾选数据，根据条件查询数据
                dealEquityInfoRegistSearchParam(param);
                list = equityInfoRegist001Mapper.queryForEquityInfoRegistPage(param);
            }
            // 遍历增加删除原因信息
            list.stream().forEach(item -> item.setDeleteReason(deleteReason));

            // 明细数据
            if (CollectionUtils.isNotEmpty(list)) {
                dataParam.put("detail", list);
            }
            // 导出Excel模板名称
            excelTemplate = "EquityInfoRegistDel";

            // 是否更新报送状态为手工报送
            if (DictCodeList.是否_中债_是.getValue().equals(doMannulFlag)) {
                List<String> updateKeys = list.stream().map(
                        item -> item.getBankCode()+"|"+item.getIndentifyMark()+"|"+item.getSubmitStatus()+"|"+item.getSumitSeq()).collect(Collectors.toList());
                Map<String,Object> map = new HashMap<>();
                map.put("updateKeys", StringUtils.join(updateKeys,","));
                dealDeleteEquityInfoRegistDataSendStatus(map);
            }

        } else if ("SvrEquityBusiInfoRegist001".equals(pageId)) {
            List<EquityBusiInfoRegist001Dto> list = new ArrayList<>();
            if (exKeys) {
                // 勾选数据
                exportKeys = (String) param.get("expKeys");
                List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
                List<EquityBusiInfoRegist001Dto> equityBusiInfoRegistList = new ArrayList<EquityBusiInfoRegist001Dto>();
                for (String string : keys) {
                    EquityBusiInfoRegist001Dto equityBusiInfoRegist001Dto = new EquityBusiInfoRegist001Dto();
                    String[] key = StringUtil.split(string, "|");
                    equityBusiInfoRegist001Dto.setBusiSeqno(key[0]);
                    equityBusiInfoRegist001Dto.setRegCode(key[1]);
                    equityBusiInfoRegist001Dto.setRegistBankCode(key[2]);
                    equityBusiInfoRegist001Dto.setIndentifyMark(key[3]);
                    equityBusiInfoRegist001Dto.setSubmitStatus(key[4]);
                    equityBusiInfoRegistList.add(equityBusiInfoRegist001Dto);
                }
                list = equityBusiInfoRegist001Mapper.selectAllDataByKey(equityBusiInfoRegistList);

            } else {
                // 无勾选数据，根据条件查询
                String paramKeys = (String)param.get("paramKeys") ;
                Map<String,Object> searchParamMap = JsonUtils.stringToMap(paramKeys);
                dealEquityBusiInfoRegistSearchParam(searchParamMap);
                list = equityBusiInfoRegist001Mapper.selectAllData(searchParamMap);
            }

            // 遍历构造数据
            list.stream().forEach(item -> {
                item.setDeleteReason(deleteReason);
                item.setSubmitStatusName(com.joyintech.tams.report.common.util.SysDataUtil.getNameFromDataDict(DictCode.报送状态, item.getSubmitStatus()));

                item.setBusiType(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.业务种类, item.getBusiType()));
                item.setFundAccPlace(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.销售区域_中债, item.getFundAccPlace()));
                item.setStatusName(com.joyintech.tams.report.common.util.SysDataUtil.getNameFromDataDict(DictCode.报送数据状态, item.getStatus()));
                item.setChannel(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.投资者明细渠道,item.getChannel()));
                item.setIsConsign(
                        com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, item.getIsConsign()));
                item.setAgencyFsa(com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.监管机构,item.getAgencyFsa()));
                item.setBusiFsa(SysDataUtil.getZZDictCodeAndName(DictCode.监管机构2,item.getBusiFsa()));
                item.setEquityConfDateStr(DateUtil.format(item.getEquityConfDate(), "yyyyMMdd"));
            });

            // 明细数据
            if (CollectionUtils.isNotEmpty(list)) {
                dataParam.put("detail", list);
            }

            // 导出Excel模板名称
            excelTemplate = "EquityBusiInfoRegistDel";

            // 是否更新报送状态为手工报送
            if (DictCodeList.是否_中债_是.getValue().equals(doMannulFlag)) {
                Map<String,Object> map = new HashMap<>();
                map.put("keyList",JSON.toJSONString(list));
                dealDeleteEquityBusiInfoRegistDataSendStatus(map);
            }

        } else if ("InvestorsHoldInfoRegist001".equals(pageId)) {
            List<InvestorsHoldInfoRegist001Dto> list = new ArrayList<>();
            if (exKeys) {
                // 勾选数据
                String keyList = param.get("keyList").toString();
                try {
                    list = JsonUtils.json2List(keyList, InvestorsHoldInfoRegist001Dto.class);
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            } else {
                // 无勾选数据，根据条件查询
                dealInvestorsHoldInfoRegistSearchParam(param);
                list = investorsHoldInfoRegist001Mapper.selectAllData(param);
            }
            list.stream().forEach(item -> {
                item.setDeleteReason(deleteReason);
                item.setSendStatusCode(item.getSendStatus());
                item.setStatusCode(item.getStatus());
                item.setCcyCode(item.getCcy());
                item.setStatus(SysDataUtil.getNameFromDataDict(DictCode.报送数据状态, item.getStatus()));
                item.setSendStatus(SysDataUtil.getNameFromDataDict(DictCode.报送状态, item.getSendStatus()));
            });

            // 明细数据
            if (CollectionUtils.isNotEmpty(list)) {
                dataParam.put("detail", list);
            }

            // 导出Excel模板名称
            excelTemplate = "InvestorsHoldInfoRegistdel";

            // 是否更新报送状态为手工报送
            if (DictCodeList.是否_中债_是.getValue().equals(doMannulFlag)) {
                Map<String,Object> map = new HashMap<>();
                map.put("keyList",JSON.toJSONString(list));
                dealDeleteInvestorsHoldInfoRegistDataSendStatus(map);
            }
        }
        return exportCommon(pageId, excelTemplate, dataParam);
    }

    public  ResponseEntity<byte[]> exportCommon(String pageId,String excelTemplate,Map<String,Object> dataParam){
        File outFile = TisUtil.createFile(dataParam, excelTemplate, pageId);
        Page page = CacheUtil.getPage(pageId);
        // 导出文件名
        String exportFileName = pageId;
        if (null != page) {
            exportFileName = SpringContextUtil.getTextValue(pageId) + GlobalConstant.SPLIT
                    + "删除数据" + GlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                    + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT + GlobalConstant.FILE_POSTFIX_XLS;
        }
        return ExportUtil.export(exportFileName, outFile, true);
    }

    /**
     * 身份信息手工处理
     */
    public void dealDeleteEquityInfoRegistDataSendStatus(Map<String,Object> map){
        restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityInfoRegist/doUpdate", map,
                Integer.class);

    }

    /**
     * 明细信息手工处理
     */
    public void dealDeleteEquityBusiInfoRegistDataSendStatus(Map<String,Object> map){
        restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/EquityBusiInfoRegist/updateSumStaus",map,
                boolean.class);
    }

    /**
     * 持有信息手工处理
     */
    public void dealDeleteInvestorsHoldInfoRegistDataSendStatus(Map<String,Object> map){
        restTemplate.postForObject(
                SvrServiceApplication.CHB3_SERVICE_NAME + "/InvestorsHoldInfoRegist/doUpdate", map,
                Integer.class);
    }

    /**
     * 构造身份查询参数
     *
     * @param param
     */
    public void dealEquityInfoRegistSearchParam(Map<String, Object> param){
        String paramKeys = (String)param.get("paramKeys") ;
        Map<String,Object> searchParamMap = JsonUtils.stringToMap(paramKeys);
        String orgCode = CommonUtil.getOrgCode();
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        param.put("orgCodeList", orgCodeList);
        String personalIdentifyType = (String)searchParamMap.get("personalIdentifyType");
        String institutionIdentifyType = (String)searchParamMap.get("institutionIdentifyType");
        String status = (String)searchParamMap.get("status");
        String submitStatus = (String)searchParamMap.get("submitStatus");
        String submitFeedbackStatus = (String)searchParamMap.get("submitFeedbackStatus");
        param.put("personalIdentifyTypeList", StringUtil.splitToList(GlobalConstant.COMMA, personalIdentifyType));
        param.put("institutionIdentifyTypeList", StringUtil.splitToList(GlobalConstant.COMMA, institutionIdentifyType));
        param.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
        param.put("submitStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitStatus));
        param.put("submitFeedbackStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
        param.put("submitDeadLineStart", DateCalcUtil.getEndOfDay((String)searchParamMap.get("submitDeadLineStart")));
        param.put("submitDeadLineEnd", DateCalcUtil.getEndOfDay((String)searchParamMap.get("submitDeadLineEnd")));
    }

    /**
     * 构造明细查询参数
     *
     * @param pageParam
     */
    public void dealEquityBusiInfoRegistSearchParam(Map<String, Object> pageParam){
        // 数据状态
        String status = (String)pageParam.get("status");
        // 报送状态
        String sendStatus = (String)pageParam.get("sendStatus");
        // 报送状态
        String orgCode = (String)pageParam.get("orgCode");
        // 反馈状态
        String submitFeedbackStatus = (String)pageParam.get("submitFeedbackStatus");
        // 产品
        String prdCode = (String)pageParam.get("prdCode");
        // 产品登记编码
        String prdRegistCode = (String)pageParam.get("prdRegistCode");
        // 发送状态
        String submitSendStatus = (String)pageParam.get("submitSendStatus");
        String busiTypes = (String)pageParam.get("busiTypes");
        // 渠道
        String channel = (String)pageParam.get("channel");

        // 将数据状态当做多选下拉框处理
        if (StringUtil.isNotBlank(status)) {
            pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
        }
        // 将报送状态当做多选下拉框处理
        if (StringUtil.isNotBlank(sendStatus)) {
            pageParam.put("sendStatusList",
                    StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
        }
        // 将反馈状态当做多选下拉框处理
        if (StringUtil.isNotBlank(submitFeedbackStatus)) {
            pageParam.put("submitFeedbackStatusList",
                    StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
        }
        // 将机构代码当做多选下拉框处理
        if (StringUtil.isNotBlank(orgCode)) {
            pageParam.put("orgCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, orgCode));
        }
        if (StringUtil.isNotBlank(busiTypes)) {
            pageParam.put("busiTypeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, busiTypes));
        }
        // 将产品当做多选下拉框处理
        if (StringUtil.isNotBlank(prdCode)) {
            pageParam.put("prdCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, prdCode));
        }
        // 将产品登记编码当做多选下拉框处理
        if (StringUtil.isNotBlank(prdRegistCode)) {
            pageParam.put("prdRegistCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
        }
        // 将发送状态当做多选下拉框处理
        if (StringUtil.isNotBlank(submitSendStatus)) {
            pageParam.put("submitSendStatusList",
                    StringUtil.splitToList(GlobalConstant.COMMA, submitSendStatus));
        }

        // 将渠道当做多选下拉框处理
        if (StringUtil.isNotBlank(channel)) {
            pageParam.put("channelList",
                    StringUtil.splitToList(GlobalConstant.COMMA, channel));
        }
    }

    /**
     * 构造持有查询参数
     * @param pageParam
     */
    public void dealInvestorsHoldInfoRegistSearchParam(Map<String, Object> pageParam) {
        // 查询参数条件
        // 数据状态
        String status = (String) pageParam.get("status");
        // 报送状态
        String sendStatus = (String) pageParam.get("sendStatus");
        // 报送状态
        String orgCode = (String) pageParam.get("orgCode");
        // 反馈状态
        String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");
        // 产品登记编码
        String prdRegistCode = (String) pageParam.get("prdRegistCode");
        // 将数据状态当做多选下拉框处理
        if (StringUtil.isNotBlank(status)) {
            pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
        }
        // 将报送状态当做多选下拉框处理
        if (StringUtil.isNotBlank(sendStatus)) {
            pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
        }
        // 将反馈状态当做多选下拉框处理
        if (StringUtil.isNotBlank(submitFeedbackStatus)) {
            pageParam.put("submitFeedbackStatusList",
                    StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
        }
        // 将机构代码当做多选下拉框处理
        if (StringUtil.isNotBlank(orgCode)) {
            pageParam.put("orgCodeList", StringUtil.splitToList(GlobalConstant.COMMA, orgCode));
        }
        // 将产品登记编码当做多选下拉框处理
        if (StringUtil.isNotBlank(prdRegistCode)) {
            pageParam.put("prdRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prdRegistCode));
        }
    }
}