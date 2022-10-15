/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service;

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
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dao.ProdBonusplanMapper;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 记账凭证查询service
 *
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager001Service extends CommonExportService<ProdBonusplanDto> /*implements BaseProcessService<ProdBonusplanDto>*/ {

    @Autowired
    private ProdBonusplanMapper prodBonusplanMapper;

    @Autowired
    private ProdBonusplanManager002Service prodBonusplanManager002Service;

    @Autowired
    private ProdBonusplanManager001Service prodBonusplanManager001Service;

    @Autowired
    private ProdInfoService prodInfoService ;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Service.class);

    private Map<String, List<String>> propertiesMap = new HashMap<>();


    public List<ProdBonusplanDto> searchReportsNew(Map<String, Object> params) {
        List<ProdBonusplanDto> result = prodBonusplanMapper.searchReports(params);
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(result)) {
            result.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态ED, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));
            });
        }
        return result;
    }

    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param pageParam
     * @return
     */
    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param params
     * @return
     */
    public Page<ProdBonusplanDto> findByPage(Map<String, Object> params) {
        String dataStatus = (String) params.get("dataStatus");
        String[] dataStatusArr = null;
        if (StringUtil.isNotEmpty(dataStatus)) {
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);

        String bonusMode = (String) params.get("bonusMode");
        String[] bonusModeArr = null;
        if (StringUtil.isNotEmpty(bonusMode)) {
            bonusModeArr = bonusMode.split(",");
        }
        params.put("bonusModeArr", bonusModeArr);

        String prodId = (String) params.get("prodId");
        String[] prodIdArr = null;
        if (StringUtil.isNotEmpty(prodId)) {
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);

        String prodId1 = (String) params.get("prodId1");
        String[] prodIdArr1 = null;
        if (StringUtil.isNotEmpty(prodId1)) {
            prodIdArr1 = prodId1.split(",");
        }
        params.put("prodIdArr1", prodIdArr1);

        Page<ProdBonusplanDto> pages = super.queryForPageForSelectReturn(
                Integer.parseInt( params.get("page").toString()),
                Integer.parseInt(params.get("length").toString()),
                () -> prodBonusplanMapper.searchReports(params));
        List<ProdBonusplanDto> list = pages.getRoot();
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态ED, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));

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
                "/com/joyintech/tams/disclosure/information/config/prodbonusplanmanage/export_prodbonusplanmanager001.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProdBonusplanDto> selectPageData(Map<String, Object> selectParam) {
        /*queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> hugeInfoProMapper.select(selectParam));*/
        return this.findByPage(selectParam);
    }

    /**
     * todo
     *
     * @param prodId
     * @return
     */
    public ProdBonusplanDto selectByProdId(String prodId) {
        return prodBonusplanMapper.selectByProdId(prodId, DictCodeListExt3.信披_数据状态_有效E.getValue());
    }
    public int selectByProdId2(String prodId) {
        return prodBonusplanMapper.selectByProdId2(prodId, DictCodeListExt3.信披_数据状态_有效E.getValue());
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
        if (null == tempList.get(0) || tempList.get(0).size() != 8) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 8), MessageType.错误消息);
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
            List<ProdBonusplanDto> entities = this.convertToBean(dataList);
            List<ProdBonusplanDto> data = this.selectBonusPlanByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<ProdBonusplanDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getBonusMode()
                                        + GlobalConstant.UNDER_LINE + x.getSettDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (ProdBonusplanDto dto : data) {
                    List<ProdBonusplanDto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getBonusMode()
                            + GlobalConstant.UNDER_LINE + dto.getSettDateStr());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001_001",
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
    private List<ProdBonusplanDto> convertToBean(List<List<String>> dataList) {
        List<ProdBonusplanDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            ProdBonusplanDto entity = new ProdBonusplanDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1)) && StringUtil.equals(list.get(1),"1-份额分红后净值为1")) {
                entity.setBonusMode(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue());
            } else if (StringUtil.isNotEmpty(list.get(1)) && StringUtil.equals(list.get(1),"2-到期兑付"))
            {
                entity.setBonusMode(DictCodeListExt3.信披_分红方式_到期兑付.getValue());
            } else {
                entity.setBonusMode(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setSettDateStr(list.get(2));
            } else {
                entity.setSettDateStr(" ");
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
    private List<ProdBonusplanDto> selectBonusPlanByKey(List<ProdBonusplanDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return prodBonusplanMapper.selectBonusPlanList(dataList);
        }
        // 导入大于1000时，返回值
        List<ProdBonusplanDto> equtiyList = new ArrayList<>();

        List<ProdBonusplanDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<ProdBonusplanDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = prodBonusplanMapper.selectBonusPlanList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = prodBonusplanMapper.selectBonusPlanList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public static boolean isDateStr(String str) {
        boolean result=true;
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
        try {
            format.setLenient(false);
            format.parse(str);
            result=true;

        } catch (Exception var3) {
            result=false;
        }
        String yearStr=str.split("/")[0];
        if(yearStr.startsWith("0")||yearStr.length()!=4){
            result=false;
        }
        return result;
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<ProdBonusplanDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    ProdBonusplanDto serchdto  = new ProdBonusplanDto();
                    serchdto.setProdId(item.getProdId());
                    serchdto.setBonusPlanId(item.getBonusMode());
                    serchdto.setSettDateStr(item.getSettDateStr());
                    ProdBonusplanDto dto = prodBonusplanManager002Service.selectByCondition(serchdto);
                    if(StringUtil.isNotEmpty(dto) && StringUtil.equals(dto.getDataStatus(), DictCodeListExt1.维护数据状态_有效.getValue())) {
                        item.setBonusPlanId(dto.getBonusPlanId());
                        prodBonusplanManager002Service.update(item);
                    }else {
                        item.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
                        item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                        prodBonusplanMapper.deleteDisProdBonusPlan(serchdto);
                        prodBonusplanManager002Service.insert(item);
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
                                                 List<ProdBonusplanDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 8) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 8)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else {
                //不存在 校验
                ProdInfoDto prodInfo = prodInfoService.selectByProdStatus(rowData.get(0));
                if(prodInfo == null){
                    message.add("子产品代码不存在。");
                }
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "分红方式"));
            } else if (!StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(), rowData.get(1))
                    && !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(), rowData.get(1))
                    && !StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(), rowData.get(1))
                    && !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(), rowData.get(1))
                    && !StringUtils.equals("1-份额分红后净值为1", rowData.get(1))
                    && !StringUtils.equals("2-到期兑付", rowData.get(1))
            ) {
                message.add("分红方式值不正确。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "收益分配基准日"));
            } else if (!isDateStr(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "收益分配基准日"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "现金红利发放日"));
            } else if (!isDateStr(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "现金红利发放日"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业绩报酬"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "超额业绩报酬收取比例"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业绩比较基准"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(7))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "业绩比较基准比例"));
            }
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                int num = prodBonusplanManager001Service.selectByProdId2(rowData.get(0));
                if (StringUtils.equals("2-到期兑付", rowData.get(1))
                        && num > 0) {
                    message.add("当分红方式选择到期兑付时，一个子产品代码只能有一条数据。");
                }
            }
            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(2),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private ProdBonusplanDto convertToEntity(List<String> rowData) {
        ProdBonusplanDto prodBonusplanDto = new ProdBonusplanDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            prodBonusplanDto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            if (StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(), rowData.get(1).toString())
                    || StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(), rowData.get(1).toString())
                    || StringUtils.equals("1-份额分红后净值为1", rowData.get(1).toString())
            ) {
                prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue());
            } else if (StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(), rowData.get(1).toString())
                    || StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(), rowData.get(1).toString())
                    || StringUtils.equals("2-到期兑付", rowData.get(1).toString())
            ) {
                prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_到期兑付.getValue());
            }
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            prodBonusplanDto.setSettDate(DateUtil.parse(rowData.get(2)));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            prodBonusplanDto.setPaymentDate(DateUtil.parse(rowData.get(3)));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            prodBonusplanDto.setAchievement(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            prodBonusplanDto.setExcessAchievementProp(new BigDecimal(rowData.get(5)));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            prodBonusplanDto.setAchievementBasis(rowData.get(6));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            prodBonusplanDto.setAchievementBasisProp(new BigDecimal(rowData.get(7)));
        }
        return prodBonusplanDto;
    }
}
