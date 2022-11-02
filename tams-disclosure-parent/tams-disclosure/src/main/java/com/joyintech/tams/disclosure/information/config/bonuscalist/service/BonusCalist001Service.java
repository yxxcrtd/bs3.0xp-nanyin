package com.joyintech.tams.disclosure.information.config.bonuscalist.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.BonusReportMapper;
import com.joyintech.tams.disclosure.busicommon.rptdata.dto.BonusReportDto;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.BonusReportDataService;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dao.BonusCalist001Mapper;
import com.joyintech.tams.disclosure.information.config.bonuscalist.dto.BonusCalist001Dto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BonusCalist001Service extends CommonExportService<BonusCalist001Dto> implements BaseProcessService<BonusCalist001Dto> {

    @Autowired
    private BonusCalist001Mapper bonusCalist001Mapper;

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;

    @Autowired
    private BonusReportMapper bonusReportMapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BonusReportDataService.class);

    public Page<BonusCalist001Dto> selectForPage(Map<String, Object> param) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Page<BonusCalist001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> bonusCalist001Mapper.select(param));
        return result;
    }

    @Override
    public void doComplete(BonusCalist001Dto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            bonusCalist001Mapper.deleteByKey(dto.getBonusListId());
        }
        else if (StringUtil.equals(type, "NEW")) {
            BonusCalist001Dto BonusCalist001Dto = new BonusCalist001Dto();
            BeanUtil.copyPropertiesIgnoreNull(BonusCalist001Dto,dto);
            BonusCalist001Dto.setDataStatus("E");
            bonusCalist001Mapper.insert(BonusCalist001Dto);
        }
        else {
            dto.setDataStatus("E");
            bonusCalist001Mapper.updateByKey(dto);
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/bonuscalist/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<BonusCalist001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }
    //根据主键查找数据
    public BonusCalist001Dto selectByPrimaryKey(String bonusListId) {
        return bonusCalist001Mapper.selectByPrimaryKey(bonusListId);
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
        if (null == tempList.get(0) || tempList.get(0).size() != 15) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 15), MessageType.错误消息);
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
            List<BonusCalist001Dto> entities = this.convertToBean(dataList);
            List<BonusCalist001Dto> data = this.selectBonusInfoByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<BonusCalist001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getProfitBaseDate()

                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (BonusCalist001Dto dto : data) {
                    List<BonusCalist001Dto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getProfitBaseDate());
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
    private List<BonusCalist001Dto> convertToBean(List<List<String>> dataList) {
        List<BonusCalist001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            BonusCalist001Dto entity = new BonusCalist001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                entity.setProfitBaseDateStr(list.get(5));
            } else {
                entity.setProfitBaseDateStr("");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }
    //校验新建重复数据
    public List<BonusCalist001Dto> selectBonusInfoByKey(List<BonusCalist001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return bonusCalist001Mapper.selectBonusCaList(dataList);
        }
        // 导入大于1000时，返回值
        List<BonusCalist001Dto> equtiyList = new ArrayList<>();

        List<BonusCalist001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<BonusCalist001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = bonusCalist001Mapper.selectBonusCaList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = bonusCalist001Mapper.selectBonusCaList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public BonusCalist001Dto selectByBusPK(Map map) {
        return bonusCalist001Mapper.selectByBusPK(map);
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
            List<BonusCalist001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    //根据 子产品代码 查找 产品登记编码
                    String re = bonusCalist001Mapper.findRe(item.getProdId());
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    map.put("profitBaseDate", item.getProfitBaseDate());
                    item.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    item.setUpdateTime(DateUtil.now());
                    item.setUpdateUser(CommonUtil.getUserName());
                    item.setProdRegistNo(re);
                    BonusCalist001Dto dto = this.selectByBusPK(map);
                    if (dto != null ) {
                        bonusCalist001Mapper.updateByBusKey(item);
                    } else {
                        item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                        item.setBonusListId(IDGenerator.generate(36));
                        this.doComplete(item, OperationType.NEW);
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
                                                 List<BonusCalist001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> message = new ArrayList<>();
        List<String> registNoList =  proElementAdjMapper.getRegistNo(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        List<String> prodList =  proElementAdjMapper.getProdId(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 15) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 15)));
                break;
            }
            // 验证消息

            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if (!prodList.contains(rowData.get(0).trim())){
                message.add("产品代码未在产品基本信息表中维护。");
            }
            //净值 份额非必填 不需要做非空校验
//            if (!StringUtil.isNotEmpty(rowData.get(1))){
//                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "净值"));
//            }
//            if (!StringUtil.isNotEmpty(rowData.get(2))){
//                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "份额"));
//            }

            if (!StringUtil.isNotEmpty(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "单位净值"));
            }

//            if (!StringUtil.isNotEmpty(rowData.get(4))){
//                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "上一收益分配基准日"));
//            } else
            if (StringUtil.isNotEmpty(rowData.get(4))&&!isDateStr(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "上一收益分配基准日"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "本期收益分配基准日"));
            } else if (!isDateStr(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "本期收益分配基准日"));
            }

            if (isDateStr(rowData.get(5))&& isDateStr(rowData.get(4)) && DateUtil.compareDate(rowData.get(5),rowData.get(4))<=0){
                message.add("上一收益分配基准日应小于本期收益分配基准日!");
            }

//            if (!StringUtil.isNotEmpty(rowData.get(6))){
//                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "运行天数"));
//            }

            if (!StringUtil.isNotEmpty(rowData.get(7))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "可供分配利润"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(12))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "应分配分红金额"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(13))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "单位分红"));
            }

//            if (!StringUtil.isNotEmpty(rowData.get(14))){
//                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "收益率"));
//            }

            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(5), rowData);
            lengthCheck(rowData,message);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(5),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private BonusCalist001Dto convertToEntity(List<String> rowData) {
        BonusCalist001Dto BonusCalist001Dto = new BonusCalist001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            BonusCalist001Dto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            BonusCalist001Dto.setProdAssets(new BigDecimal(rowData.get(1).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            BonusCalist001Dto.setProdEndBalance(new BigDecimal(rowData.get(2).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            BonusCalist001Dto.setUnitNum(new BigDecimal(rowData.get(3).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            BonusCalist001Dto.setLastProfitBaseDate(DateUtil.parse(rowData.get(4).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            BonusCalist001Dto.setProfitBaseDate(DateUtil.parse(rowData.get(5).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            BonusCalist001Dto.setRunDays((short) Integer.parseInt(rowData.get(6)));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            BonusCalist001Dto.setAvaiProfit(new BigDecimal(rowData.get(7).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(8))) {
            BonusCalist001Dto.setPerBase(rowData.get(8).toString());
        }
        if (StringUtil.isNotEmpty(rowData.get(9))) {
            BonusCalist001Dto.setPerBaseProfit(new BigDecimal(rowData.get(9).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(10))) {
            BonusCalist001Dto.setExceed(new BigDecimal(rowData.get(10).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(11))) {
            BonusCalist001Dto.setPerFee(new BigDecimal(rowData.get(11).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(12))) {
            BonusCalist001Dto.setDisBonusMoney(new BigDecimal(rowData.get(12).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(13))) {
            BonusCalist001Dto.setUnitBonus(new BigDecimal(rowData.get(13).toString()));
        }
        if (StringUtil.isNotEmpty(rowData.get(14))) {
            BonusCalist001Dto.setYield(new BigDecimal(rowData.get(14).toString()));
        }
        return BonusCalist001Dto;
    }

    public List<SelectOptionDto> getProdId(Map<String, Object> map) {
        return bonusCalist001Mapper.getProdId(map);
    }

    public int updateStatus(BonusCalist001Dto bonusCalist001Dto) {
         return bonusCalist001Mapper.updateStatus(bonusCalist001Dto);
    }

    public int calculateData(BonusCalist001Dto dto) {
        List<String> prodIds = StringUtil.splitToList(GlobalConstant.COMMA, dto.getProdId());
        for (String prodId : prodIds){
            dto.setProdId(prodId);
            HashMap map = new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("reportDate",dto.getProfitBaseDate());
            //结果数据
//        List<Map<String, Object>> listResult = new ArrayList<>();
            List<BonusCalist001Dto> listResult = new ArrayList<>();
            //入库集合元素
            BonusCalist001Dto bonusCalist001Dto = new BonusCalist001Dto();
            List<BonusReportDto> listReport = bonusReportMapper.selectBonusReport(map);
            for (BonusReportDto report : listReport) {  //指定产品后，只有一条数据
                // 上一分配基准日
                if ("01".equals(report.getIssuingWay())) { // 开放式
                    DisProdBonusplan plan = bonusReportMapper.selectLastBonusPlan(map);
                    if (plan == null) { // 第一个计划
                        bonusCalist001Dto.setLastProfitBaseDate(report.geteDate());
                    } else {
                        bonusCalist001Dto.setLastProfitBaseDate(plan.getSettDate());
                    }
                } else {
                    bonusCalist001Dto.setLastProfitBaseDate(report.geteDate());
                }
                //uuid
                bonusCalist001Dto.setBonusListId(IDGenerator.generate(36));
                //产品代码
                bonusCalist001Dto.setProdId(dto.getProdId());
                //产品登记编码
                bonusCalist001Dto.setProdRegistNo(report.getProdRe());
                //产品资产净值
                bonusCalist001Dto.setProdAssets(report.getAssetsValue());
                //本次收益分配日
                bonusCalist001Dto.setProfitBaseDate(dto.getProfitBaseDate());
                // 产品名称
                bonusCalist001Dto.setProdName(report.getProdName());
                //期末余额
                bonusCalist001Dto.setProdEndBalance(report.getCapital());
                //单位净值
                bonusCalist001Dto.setUnitNum(bonusCalist001Dto.getProdAssets().divide(bonusCalist001Dto.getProdEndBalance(),4,BigDecimal.ROUND_DOWN));
                // 运行天数
                if (bonusCalist001Dto.getLastProfitBaseDate().compareTo(report.geteDate()) == 0) {
                    bonusCalist001Dto.setRunDays((short)(com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil.getBetweenDayNumber(bonusCalist001Dto.getLastProfitBaseDate(),report.getSettDate() ) + 1));
                } else {
                    bonusCalist001Dto.setRunDays((short)com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil.getBetweenDayNumber(bonusCalist001Dto.getLastProfitBaseDate(),report.getSettDate() ));
                }
                //数据状态
                bonusCalist001Dto.setDataStatus("A");
                //业绩比较基准
                bonusCalist001Dto.setPerBase(report.getAchievementBasis());
                try {
                    // 可供分配利润
                    bonusCalist001Dto.setAvaiProfit(report.getAssetsValue().subtract(report.getCapital()).setScale(2,BigDecimal.ROUND_HALF_UP) );

                    // 业绩比较基准收益
                    BigDecimal abProfit =
                            report.getCapital().multiply(report.getAchievementBasisProp())
                                    .multiply(new BigDecimal(365))
                                    .divide(new BigDecimal(bonusCalist001Dto.getRunDays()),20,BigDecimal.ROUND_DOWN);
                    bonusCalist001Dto.setPerBaseProfit(
                            report.getCapital().multiply(report.getAchievementBasisProp())
                                    .multiply(new BigDecimal(bonusCalist001Dto.getRunDays()))
                                    .divide(new BigDecimal(365),2,BigDecimal.ROUND_DOWN) );

                    // 超出部分
//                BigDecimal esProfit = report.getDistributableProfit().subtract(abProfit);
                    bonusCalist001Dto.setExceed(bonusCalist001Dto.getAvaiProfit().subtract(bonusCalist001Dto.getPerBaseProfit()));

                    // 业绩报酬
//                BigDecimal floatFee = esProfit.multiply(report.getExcessAchievementProp());
                    if (report.getFloatFee()!=null && StringUtil.equals(report.getFloatFee().toString(),"无")){
                        bonusCalist001Dto.setPerFee(new BigDecimal("0.00"));
                    }else
                        bonusCalist001Dto.setPerFee(bonusCalist001Dto.getExceed().multiply(report.getExcessAchievementProp()).setScale(2, BigDecimal.ROUND_DOWN));

                    // 应分配利润
                    bonusCalist001Dto.setDisBonusMoney(bonusCalist001Dto.getAvaiProfit().subtract(bonusCalist001Dto.getPerFee()).setScale(2,BigDecimal.ROUND_DOWN));

                    // 单位分红
                    bonusCalist001Dto.setUnitBonus(
                            bonusCalist001Dto.getDisBonusMoney().divide(report.getCapital(), 4, BigDecimal.ROUND_DOWN));

                    // 年化收益率
//                BigDecimal ddProfit = bonusCalist001Dto.getDisBonusMoney().divide(bonusCalist001Dto.getProdEndBalance()).setScale(4,BigDecimal.ROUND_DOWN);
                    BigDecimal ddProfit = ArithmeticUtil.divide(bonusCalist001Dto.getDisBonusMoney(),bonusCalist001Dto.getProdEndBalance(),8);
                    bonusCalist001Dto.setYield(
                            ddProfit.multiply(new BigDecimal(365))
                                    .divide(new BigDecimal(bonusCalist001Dto.getRunDays()), 4, BigDecimal.ROUND_DOWN));
                } catch(Exception e) {
                    logger.error("获取分红计算清单异常。",e);
                }
//            listResult.add(com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils.bean2Map(report));
            }
            //计算出的数据 业务主键和已有删除数据业务主键重复则新增一条待处理数据，否则更新
//            BonusCalist001Dto dto1 = bonusCalist001Mapper.isRepeated(bonusCalist001Dto);
            List<String> statuses = bonusCalist001Mapper.isRepeated(bonusCalist001Dto);
            if (statuses.contains("D")){
                 bonusCalist001Mapper.insert(bonusCalist001Dto);
            }else{
                bonusCalist001Mapper.deleteByBusKey(bonusCalist001Dto);
                bonusCalist001Mapper.insert(bonusCalist001Dto);
            }

        }
         return 1;
        }

    private void lengthCheck(List<String> rowData,List<String> message) {
        if (StringUtil.isNotBlank(rowData.get(1))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(1)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "产品资产净值", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(2))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(2)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "产品期末余额(份额)", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(3))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(3)).setScale(4,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 10) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "单位净值", String.valueOf(10)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(6))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(6));
            if (bigDecimal.precision() > 4) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "运行天数", String.valueOf(4)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(7))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(7)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "可供分配利润", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(9))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(9)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "业绩比较基准收益", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(10))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(10)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "超出部分", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(11))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(11)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "业绩报酬", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(12))) {
            BigDecimal bigDecimal=new BigDecimal(rowData.get(12)).setScale(2,BigDecimal.ROUND_HALF_UP);;
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "应分配分红金额", String.valueOf(17)));
            }
        }
        if (StringUtil.isNotBlank(rowData.get(13))) {
            BigDecimal bigDecimal = new BigDecimal(rowData.get(13)).setScale(2, BigDecimal.ROUND_HALF_UP);
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "单位分红", String.valueOf(17)));
            }
        }

        if (StringUtil.isNotBlank(rowData.get(14))) {
            BigDecimal bigDecimal = new BigDecimal(rowData.get(14)).setScale(2, BigDecimal.ROUND_HALF_UP);
            if (bigDecimal.precision() > 17) {
                message.add(this.getMessage("REPORT_COMMON_E_0002_1", "收益率", String.valueOf(17)));
            }
        }
    }


}
