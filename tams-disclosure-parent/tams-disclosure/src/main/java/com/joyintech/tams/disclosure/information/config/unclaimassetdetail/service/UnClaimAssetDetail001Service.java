package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao.UnClaimAssetDetailMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UnClaimAssetDetail001Service extends CommonExportService<UnClaimAssetDetailDto>
         implements BaseProcessService<UnClaimAssetDetailDto>{

    @Autowired
    private UnClaimAssetDetailMapper unClaimAssetDetailMapper;

    @Autowired
    private UnClaimAssetDetail002Service unClaimAssetDetail002Service;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    @Autowired
    private SqlSessionTemplate template;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * excel2003格式
     */
    public static final String EXCEL_XLS = "xls";

    /**
     * excel2007以上格式
     */
    public static final String EXCEL_XLSX = "xlsx";
    /**
     *
     * 页面查询按钮方法
     * @param
     * @return
     */
    public Page<UnClaimAssetDetailDto> selectForPage(Map<String, Object> pageParam) {


        if (StringUtil.isNotEmpty(pageParam.get("prodId"))) {
            pageParam.put("prodId", StringUtil.splitToList(null, pageParam.get("prodId").toString()));
        }else{
            pageParam.remove("prodId");
        }

        Page<UnClaimAssetDetailDto> page = super.queryForPage(Integer.parseInt(pageParam.get("page").toString()), Integer.parseInt(pageParam.get("length").toString()), ()
                -> unClaimAssetDetailMapper.selectForPage(pageParam));
        return page;
    }




    /**
     *
     * 产品代码和产品名称联想文本框
     * @param map
     * @return
     */
    public List<SelectOptionDto> getProdInfo(Map<String,Object> map){
        return unClaimAssetDetailMapper.getProdInfo(map);
    }


    @Override
    public void doComplete(UnClaimAssetDetailDto dto, OperationType operationType) {
        dto.setEffEndDateO(dto.getEffEndDate());
        dto.setEffDateO(dto.getEffDate());
        unClaimAssetDetailMapper.deleteOnReal(dto);

        Map<String, Object> map = new HashMap<>();
        map.put("prodId", dto.getProdId());
        map.put("effDate",dto.getEffDateStr());
        map.put("effEndDate", dto.getEffEndDateStr());
        map.put("dataStatus", DictCodeListExt1.维护数据状态_有效.getValue());
        Integer num = unClaimAssetDetailMapper.selectCount(map);

        for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()){
            String code = IDGenerator.generate(36);
            if (unclaimDto.getProdId()==null){
                unclaimDto.setProdId(dto.getProdId());
            }
            num+=1;
            unclaimDto.setDataStatus("E");
            unclaimDto.setNostdAssetId(code);
            unclaimDto.setEffDate(dto.getEffDate());
            unclaimDto.setEffEndDate(dto.getEffEndDate());
            unclaimDto.setProjectNo(String.valueOf(num));
            unClaimAssetDetailMapper.insert(unclaimDto);
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/unclaimassetdetail/config/export_unclaimassetdetail.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<UnClaimAssetDetailDto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
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
        if (null == tempList.get(0) || tempList.get(0).size() != 9) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 9), MessageType.错误消息);
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
//            List<UnClaimAssetDetailDto> entities = this.convertToBean(dataList);
//            List<UnClaimAssetDetailDto> data = this.selectUnClaimAssetByKey(entities);
//            if (data != null && data.size() > 0) {
//                Map<String, List<UnClaimAssetDetailDto>> map = entities.stream().collect(
//                        JoyinCollectors.groupingBy(x ->
//                                x.getProdId()
//                                        + GlobalConstant.UNDER_LINE + x.getEffDateStr()
//                                        + GlobalConstant.UNDER_LINE + x.getEffEndDateStr()
//                        ));
//                // 将序号放入list中
//                List<String> excelNumList = new ArrayList<>();
//                List<Integer> excelNumIntList = new ArrayList<>();
//                for (UnClaimAssetDetailDto dto : data) {
//                    List<UnClaimAssetDetailDto> listTemp = map.get(dto.getProdId()
//                            + GlobalConstant.UNDER_LINE + dto.getEffDateStr()
//                            + GlobalConstant.UNDER_LINE + dto.getEffEndDateStr());
//                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
//                }
//                // 将list进行排序，并转化为string
//                Collections.sort(excelNumIntList);
//                for (Integer rowNo : excelNumIntList) {
//                    excelNumList.add(String.valueOf(rowNo));
//                }
//                webResult.setMessage(
//                        this.getMessage("REPORT_COMMON_FILE_C_0001",
//                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
//                        MessageType.确认消息);
//            } else {
//                // 防止在进行导入前check时进行了两次的弹框
//                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
//            }
            webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
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
    private List<UnClaimAssetDetailDto> convertToBean(List<List<String>> dataList) {
        List<UnClaimAssetDetailDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            UnClaimAssetDetailDto entity = new UnClaimAssetDetailDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(7))) {
                entity.setEffDateStr(list.get(7));
            } else {
                entity.setEffDateStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(8))) {
                entity.setEffEndDateStr(list.get(8));
            } else {
                entity.setEffEndDateStr(" ");
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
    private List<UnClaimAssetDetailDto> selectUnClaimAssetByKey(List<UnClaimAssetDetailDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return unClaimAssetDetailMapper.selectUnClaimAssetList(dataList);
        }
        // 导入大于1000时，返回值
        List<UnClaimAssetDetailDto> equtiyList = new ArrayList<>();

        List<UnClaimAssetDetailDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<UnClaimAssetDetailDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = unClaimAssetDetailMapper.selectUnClaimAssetList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = unClaimAssetDetailMapper.selectUnClaimAssetList(newDataList);
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
            List<UnClaimAssetDetailDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                try {
                    Map<String,List<UnClaimAssetDetailDto>>tt=entities.stream().collect(
                            JoyinCollectors.groupingBy(x -> x.getProdId() + GlobalConstant.UNDER_LINE + x.getEffDateStr() + GlobalConstant.UNDER_LINE + x.getEffEndDateStr()));
                    UnClaimAssetDetailMapper mapper=session.getMapper(UnClaimAssetDetailMapper.class);
                    for(String key:tt.keySet()){
                        List<UnClaimAssetDetailDto> entityList=tt.get(key);

                        Map<String, Object> map = new HashMap<>();
                        map.put("prodId", entityList.get(0).getProdId());
                        map.put("effDate", entityList.get(0).getEffDateStr());
                        map.put("effEndDate", entityList.get(0).getEffEndDateStr());
                        map.put("dataStatus", DictCodeListExt1.维护数据状态_待处理.getValue());
                        AtomicInteger num = new AtomicInteger(mapper.selectCount(map));
                        entityList.forEach(item -> {
                            num.addAndGet(1);
                            item.setUpdateTime(DateUtil.now());
                            item.setUpdateUser(CommonUtil.getUserName());
                            item.setNostdAssetId(IDGenerator.generate(36));
                            item.setDataStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
                            item.setProjectNo(String.valueOf(num));
                            mapper.insert(item);
                        });
                        session.commit();
                    }

                }catch (Exception e) {
                    session.rollback();
                } finally {
                    session.close();
                }


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
                                                 List<UnClaimAssetDetailDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList =  unClaimAssetDetailMapper.getProdId(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 9) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 9)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            } else if (!findList.contains(rowData.get(0))){
                message.add("产品主代码在产品基本信息表中没有维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "融资顾客"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "项目名称"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "剩余融资期限"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(4))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "到期收益分配"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "交易结构"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "风险状况"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(7))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "生效日"));
            }else if(!isDateStr(rowData.get(7))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "生效日"));
            } else if (rowData.get(7).compareTo(DateUtil.format(DateUtil.platDateFromCache())) < 0){
                //message.add("生效日要大于等于平台日。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(8))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "生效截止日期"));
            } else if (!isDateStr(rowData.get(8))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "生效截止日期"));
            } else if (rowData.get(8).compareTo(DateUtil.format(DateUtil.platDateFromCache())) < 0){
                //message.add("生效截止日期要大于等于平台日。");
            } else if (isDateStr(rowData.get(7)) && rowData.get(8).compareTo(rowData.get(7)) < 0){
                message.add("生效截止日期要大于等于生效日。");
            }
            if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(7)
                    + GlobalConstant.UNDER_LINE + rowData.get(8))) {
                //message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(7)
                    + GlobalConstant.UNDER_LINE + rowData.get(8), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(7)
                        + GlobalConstant.UNDER_LINE + rowData.get(8),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private UnClaimAssetDetailDto convertToEntity(List<String> rowData) {
        UnClaimAssetDetailDto unClaimAssetDetailDto = new UnClaimAssetDetailDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            unClaimAssetDetailDto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            unClaimAssetDetailDto.setFinancier(rowData.get(1));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            unClaimAssetDetailDto.setProjectName(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            unClaimAssetDetailDto.setRemFinancingPeriod(rowData.get(3));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            unClaimAssetDetailDto.setIncomeDistribute(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            unClaimAssetDetailDto.setTrdConstruct(rowData.get(5));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            unClaimAssetDetailDto.setRiskInfo(rowData.get(6));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            unClaimAssetDetailDto.setEffDate(DateUtil.parse(rowData.get(7)));
        }
        if (StringUtil.isNotEmpty(rowData.get(8))) {
            unClaimAssetDetailDto.setEffEndDate(DateUtil.parse(rowData.get(8)));
        }
        return unClaimAssetDetailDto;
    }

    public boolean isInWorkflow(UnClaimAssetDetailDto dto){
        try {
            ProcessMark processMark = new ProcessMark();
            processMark.setPageId("UnClaimAssetDetail002");
            processMark.setBusinessDataPk1(dto.getProdId());
            processMark.setBusinessDataPk2(dto.getEffDateStr());
            processMark.setBusinessDataPk3(dto.getEffEndDateStr());
            List<String> resultList = WorkflowContext.getProcessMarkService().getBusinessDataInFlow(
                    processMark);
            if (!StringUtil.isEmptyList(resultList)) {
                return true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException("处理失败", e);
        }
        return false;
    }
}
