/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage001Mapper;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 记账凭证查询service
 *
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage001Service extends CommonExportService<DisProdPlanManage001Dto> {

    @Autowired
    private DisProdPlanManage001Mapper disProdPlanManage001Mapper;

    @Autowired
    DisProdPlanManage002Service disProdPlanManage002Service;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Service.class);


    public List<DisProdPlanManage001Dto> searchReportsNew(Map<String,Object> params){
        List<DisProdPlanManage001Dto> result = disProdPlanManage001Mapper.searchReports(params);
        // 遍历构造数据
        if(CollectionUtils.isNotEmpty(result)){
            result.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
            });
        }
        return result;
    }



    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param params
     * @return
     */
    public Page<DisProdPlanManage001Dto> findByPage(Map<String, Object>  params)
    {
        String dataStatus = (String)params.get("dataStatus");
        String[] dataStatusArr = null;
        if(StringUtil.isNotEmpty(dataStatus)){
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);
        String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if(StringUtil.isNotEmpty(prodId)){
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        Page<DisProdPlanManage001Dto> pages = super.queryForPageForSelectReturn(
                Integer.parseInt((String) params.get("page")),
                Integer.parseInt((String) params.get("length"))
                , () -> disProdPlanManage001Mapper.searchReports(params));
        List<DisProdPlanManage001Dto> list = pages.getRoot();
        // 遍历构造数据
        if(CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
            });
            pages.setRoot(list);
        }
        return pages;
    }



    /**
     * 根据产品代码 查询有效的 数据
     * @param prodId
     * @return
     */
    public DisProdPlanManage002Dto selectByProdId(String prodId) {

        return disProdPlanManage001Mapper.selectByProdId(prodId, DictCodeListExt3.信披_数据状态_有效.getValue());
    }

    /**
     * 导入check
     *
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 50000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "单次最多导入50000条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        String totalMsg = "";
        List<List<String>> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 7) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要7列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            } else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);

                    if (!StringUtil.isNotEmpty(rowData.get(0))) {
                        errorMsg.append("产品主代码 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))) {
                        errorMsg.append("开放期起始日 必填！");
                    }else if (!this.isDateStr(rowData.get(2))) {
                        errorMsg.append("开放期起始日 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))) {
                        errorMsg.append("开放期结束日 必填！");
                    } else if (!this.isDateStr(rowData.get(2))) {
                        errorMsg.append("开放期结束日 格式不正确！");
                    }
                    if (StringUtil.isNotEmpty(rowData.get(3)) && !this.isDateStr(rowData.get(3))) {
                        errorMsg.append("产品冷静期 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))) {
                        errorMsg.append("运作起始日 必填！");
                    } else if (!this.isDateStr(rowData.get(4))) {
                        errorMsg.append("运作起始日 格式不正确！");
                    }
                    if (StringUtil.isNotEmpty(rowData.get(5)) && !this.isDateStr(rowData.get(5))) {
                        errorMsg.append("自动赎回日 格式不正确！");
                    }
                    if (StringUtil.isNotEmpty(rowData.get(6)) && !StringUtils.isNumeric(rowData.get(6))) {
                        errorMsg.append("客户实际持有天数 必须为数字！");
                    }
                    if (StringUtil.isNotEmpty(errorMsg.toString())) {
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第" + (i + 1) + "行数据：" + errorMsg.toString());
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //子产品代码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat) {
                        repeatNum++;
                    } else {
                        jsonArrays.add(list1);
                    }
                }
            }
        }
        if (repeatNum > 0) {
            totalMsg += "导入文件存在" + String.valueOf(repeatNum) + "条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }


    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        DisProdPlanManage002Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<DisProdPlanManage002Dto> selectedItem = dto.getSelectedItem();
        List<DisProdPlanManage002Dto> existData = new ArrayList<>();
        List<DisProdPlanManage002Dto> newData = new ArrayList<>();
        for (DisProdPlanManage002Dto temp : selectedItem) {
            //数据已存在 【产品编码 相同】，做更新操作。
            if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))) {
                existData.add(temp);
            } else {
                temp.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
                temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                newData.add(temp);
            }
        }
        //更新操作
        for (DisProdPlanManage002Dto existDataDto : existData) {
            // this.doComplete(existDataDto, OperationType.MODIFY);
            disProdPlanManage002Service.update(existDataDto);
        }
        //插入操作
        for (DisProdPlanManage002Dto newDataDto : newData
        ) {
           // this.doComplete(newDataDto, OperationType.NEW);
            disProdPlanManage002Service.insert(newDataDto);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public DisProdPlanManage002Dto getValNetAssetQuotationInfoListData(String tempList) {
        DisProdPlanManage002Dto dto = new DisProdPlanManage002Dto();
        // 行情信息列表
        List<DisProdPlanManage002Dto> disProdPlanManage002DtoList = new ArrayList<>();
        dto.setSelectedItem(disProdPlanManage002DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            DisProdPlanManage002Dto disProdPlanManage002Dto = new DisProdPlanManage002Dto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                disProdPlanManage002Dto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                disProdPlanManage002Dto.setOpenStartDate(DateUtil.parse(rowData.get(1).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                disProdPlanManage002Dto.setOpenEndDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                disProdPlanManage002Dto.setCollingoffPeriod(DateUtil.parse(rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                disProdPlanManage002Dto.setOperationStartDate(DateUtil.parse(rowData.get(4).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                disProdPlanManage002Dto.setAutoRedeemDate(DateUtil.parse(rowData.get(5).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                disProdPlanManage002Dto.setHoldDays((short)Integer.parseInt(rowData.get(6).toString()));
            }
            disProdPlanManage002DtoList.add(disProdPlanManage002Dto);
        }
        dto.setSelectedItem(disProdPlanManage002DtoList);
        return dto;
    }

}
