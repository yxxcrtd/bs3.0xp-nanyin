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

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage001Mapper;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    ProdInfoService prodInfoService;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Service.class);


    public List<DisProdPlanManage001Dto> searchReportsNew(Map<String, Object> params) {
        List<DisProdPlanManage001Dto> result = disProdPlanManage001Mapper.searchReports(params);
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(result)) {
            result.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态ED, item.getDataStatus()));
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
    public Page<DisProdPlanManage001Dto> findByPage(Map<String, Object> params) {
        String dataStatus = (String) params.get("dataStatus");
        String[] dataStatusArr = null;
        if (StringUtil.isNotEmpty(dataStatus)) {
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);
        String prodId = (String) params.get("prodId");
        String[] prodIdArr = null;
        if (StringUtil.isNotEmpty(prodId)) {
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        Page<DisProdPlanManage001Dto> pages = super.queryForPageForSelectReturn(
                Integer.parseInt(params.get("page").toString()),
                Integer.parseInt(params.get("length").toString())
                , () -> disProdPlanManage001Mapper.searchReports(params));
        List<DisProdPlanManage001Dto> list = pages.getRoot();
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态ED, item.getDataStatus()));
            });
            pages.setRoot(list);
        }
        return pages;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/disprodplanmanage/export_disprodplanmanage001.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<DisProdPlanManage001Dto> selectPageData(Map<String, Object> selectParam) {
        /*queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> hugeInfoProMapper.select(selectParam));*/
        return this.findByPage(selectParam);
    }

    /**
     * 根据产品代码 查询有效的 数据
     *
     * @param prodId
     * @return
     */
    public DisProdPlanManage002Dto selectByProdId(String prodId) {

        return disProdPlanManage001Mapper.selectByProdId(prodId, DictCodeListExt3.信披_数据状态_有效E.getValue());
    }
    public AjaxResult checkImportData(String fileName) {
        AjaxResult webResult = new AjaxResult();
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> tempList = ExcelUtil.getTempList(fileName);
        if (StringUtil.isEmptyList(tempList) || CollectionUtils.isEmpty(tempList)) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        if (null == tempList.get(0) || tempList.get(0).size() != 7) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 7), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<DisProdPlanManage002Dto> entities = this.convertToBean(dataList);
            List<DisProdPlanManage002Dto> data = this.selectProdPlanByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<DisProdPlanManage002Dto>> planMap = entities.stream().collect(
                        JoyinCollectors.groupingBy(
                                x -> x.getProdId() + GlobalConstant.UNDER_LINE + x.getOpenEndDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (DisProdPlanManage002Dto plandto : data) {
                    List<DisProdPlanManage002Dto> listTemp = planMap.get(plandto.getProdId() + GlobalConstant.UNDER_LINE + plandto.getOpenEndDateStr());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;
    }

    /**
     * excel数据转为java对象
     * @return
     */
    private List<DisProdPlanManage002Dto> convertToBean(List<List<String>> dataList) {
        List<DisProdPlanManage002Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            DisProdPlanManage002Dto entity = new DisProdPlanManage002Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setOpenEndDateStr(list.get(2));
            } else {
                entity.setOpenEndDateStr(" ");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }

    /**
     * 分批查询
     * @param dataList
     * @return
     */
    private List<DisProdPlanManage002Dto> selectProdPlanByKey(List<DisProdPlanManage002Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return disProdPlanManage001Mapper.selectProdPlanList(dataList);
        }
        // 导入大于1000时，返回值
        List<DisProdPlanManage002Dto> equtiyList = new ArrayList<>();

        List<DisProdPlanManage002Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<DisProdPlanManage002Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = disProdPlanManage001Mapper.selectProdPlanList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = disProdPlanManage001Mapper.selectProdPlanList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    private List<DisProdPlanManage002Dto> isRepeated(DisProdPlanManage002Dto dto) {
        return disProdPlanManage001Mapper.isRepeated(dto);
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

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<DisProdPlanManage002Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    List<DisProdPlanManage002Dto> plandto = this.isRepeated(item);
                    item.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    item.setUpdateUser(CommonUtil.getUserName());
                    if (plandto.size() > 0) {
                        disProdPlanManage002Service.update(item);
                    } else {
                        item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                        disProdPlanManage002Service.insert(item);
                    }
                });
            }
            return checkResult;
        } else {
            List<ExcelValidDto> checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }
    }

    private List<ExcelValidDto> convertToEntitys(List<List<String>> dataList,
                                                 List<DisProdPlanManage002Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> prodplanMap = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 7) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 7)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            ProdInfoDto prodInfo = new ProdInfoDto();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            } else {
                if (!this.getProdId(rowData.get(0))) {
                    message.add("产品主代码不存在。");
                }else{
                    prodInfo = prodInfoService.selectByProdStatus(rowData.get(0));
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "开放期起始日"));
            } else if (!isDateStr(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "开放期起始日"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "开放期结束日"));
            } else if (!isDateStr(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "开放期结束日"));
            }
            if(prodInfo != null && isDateStr(rowData.get(1))
                    && DateUtil.compareDate(rowData.get(1), prodInfo.getSetDateStr()) <= 0){
                message.add("开放期起始日不能小于等于产品成立日期。");
            }
            if(isDateStr(rowData.get(1)) && isDateStr(rowData.get(2))
                    &&  DateUtil.compareDate(rowData.get(2), rowData.get(1)) < 0){
                message.add("开放期结束日不能小于开放期起始日。");
            }
            if (StringUtil.isNotEmpty(rowData.get(3)) && !isDateStr(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "产品冷静期"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "运作起始日"));
            } else if (!isDateStr(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "运作起始日"));
            }
            if(isDateStr(rowData.get(4)) && isDateStr(rowData.get(1))
                    && DateUtil.compareDate(rowData.get(4), rowData.get(1)) < 0){
                message.add("运作起始日不能小于开放期起始日。");
            }
            if (StringUtil.isNotEmpty(rowData.get(5)) && !isDateStr(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "自动赎回日"));
            }
            if(isDateStr(rowData.get(4)) && isDateStr(rowData.get(5))
                    && DateUtil.compareDate(rowData.get(4), rowData.get(5)) >= 0){
                message.add("自动赎回日不能小于运作起始日。");
            }
            if (StringUtil.isNotEmpty(rowData.get(6)) && !StringUtils.isNumeric(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "客户实际持有天数"));
            }

            if (prodInfo != null && StringUtils.isNotBlank(prodInfo.getIsPeriod())
                    && StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), prodInfo.getIsPeriod())) {
                if (StringUtils.isBlank(rowData.get(5))) {
                    message.add("客户周期型产品，自动赎回日必填。");
                } else if (isDateStr(rowData.get(5))
                        && isDateStr(rowData.get(4))
                        && DateUtil.compareDate(rowData.get(5), rowData.get(4)) < 0) {
                    message.add("自动赎回日不能小于运作起始日。");
                }
                if (StringUtil.isEmpty(rowData.get(6))) {
                    message.add("客户周期型产品,客户实际持有天数必填。");
                }
            }

            if (prodInfo != null &&  StringUtils.isNotBlank(prodInfo.getRaiseType())
                    && StringUtils.equals(DictCodeListExt5.信披募集方式_私募.getValue(), prodInfo.getRaiseType())) {
                if (StringUtils.isBlank(rowData.get(3))) {
                    message.add("募集方式为私募,产品冷静期必填。");
                }
                // 开放结束日＜产品冷静期＜运作起始日
                if (isDateStr(rowData.get(3)) && isDateStr(rowData.get(2))
                        && DateUtil.compareDate(rowData.get(3), rowData.get(2)) <= 0) {
                    message.add("产品冷静期必须大于开放结束日！");
                }
                if (isDateStr(rowData.get(4)) && isDateStr(rowData.get(3))
                        && DateUtil.compareDate(rowData.get(4), rowData.get(3)) <= 0) {
                    message.add("产品冷静期必须小于运作起始日！");
                }
            }
            if (prodplanMap.containsKey(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            prodplanMap.put(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0) + GlobalConstant.UNDER_LINE + rowData.get(2),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private DisProdPlanManage002Dto convertToEntity(List<String> list) {
        DisProdPlanManage002Dto disProdPlanManage002Dto = new DisProdPlanManage002Dto();
        if (StringUtil.isNotEmpty(list.get(0))) {
            disProdPlanManage002Dto.setProdId(list.get(0));
        }
        if (StringUtil.isNotEmpty(list.get(1))) {
            disProdPlanManage002Dto.setOpenStartDate(DateUtil.parse(list.get(1)));
        }
        if (StringUtil.isNotEmpty(list.get(2))) {
            disProdPlanManage002Dto.setOpenEndDate(DateUtil.parse(list.get(2)));
        }
        if (StringUtil.isNotEmpty(list.get(3))) {
            disProdPlanManage002Dto.setCollingoffPeriod(DateUtil.parse(list.get(3)));
        }
        if (StringUtil.isNotEmpty(list.get(4))) {
            disProdPlanManage002Dto.setOperationStartDate(DateUtil.parse(list.get(4)));
        }
        if (StringUtil.isNotEmpty(list.get(5))) {
            disProdPlanManage002Dto.setAutoRedeemDate(DateUtil.parse(list.get(5)));
        }
        if (StringUtil.isNotEmpty(list.get(6))) {
            disProdPlanManage002Dto.setHoldDays((short) Integer.parseInt(list.get(6)));
        }
        return disProdPlanManage002Dto;
    }

    public Boolean getProdId(String prodId) {
        String r = disProdPlanManage001Mapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }
}
