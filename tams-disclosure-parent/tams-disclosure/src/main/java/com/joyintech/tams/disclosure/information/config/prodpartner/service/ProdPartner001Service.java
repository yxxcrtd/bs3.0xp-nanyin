/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodpartner.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner001Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 合作机构信息维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdPartner001Service
 * @since 1.0
 */
@Service
public class ProdPartner001Service extends CommonExportService<ProdPartnerDto>
    implements BaseProcessService<ProdPartnerDto>{

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdPartner001Mapper prodPartner001Mapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * excel2003格式
     */
    public static final String EXCEL_XLS = "xls";

    /**
     * excel2007以上格式
     */
    public static final String EXCEL_XLSX = "xlsx";

    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";


    /**
     * 根据产品代码或产品名称模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        map.put("dataStatus",DictCodeListExt3.信披_数据状态_有效E.getValue());
        if(map.containsKey("initValue")){
            String str=map.get("initValue").toString();
            List<String> prodIdList=Arrays.asList(str.split(","));
            map.put("prodIdList",prodIdList);
        }

        return prodPartner001Mapper.queryProdIdInfo(map);
    }

    /**
     * 使用给定的检索分页数据。
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<ProdPartnerDto> selectForPage(Map<String, Object> pageParam){
        int page=1;
        int length=30;
        if(pageParam.get("page")instanceof Integer){
            page= (int) pageParam.get("page");
        }else{
            page = Integer.parseInt((String) pageParam.get("page"));
        }
        if(pageParam.get("page")instanceof Integer){
            page= (int) pageParam.get("length");
        }else{
            length = Integer.parseInt((String) pageParam.get("length"));
        }
        if(StringUtil.isNotEmpty(pageParam.get("prodId"))){
            pageParam.put("prodId",StringUtil.splitToList(null,pageParam.get("prodId").toString()));
        }else{
            pageParam.put("prodId",new ArrayList<>());
        }
        return super.queryForPage(page,length,() -> prodPartner001Mapper.select(pageParam));
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/prodpartner/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProdPartnerDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 选中导出
     * @param param
     * @return
     */
    public List<ProdPartnerDto> selectForExport(Map<String, Object> param) {
        List<ProdPartnerDto> investList = null;
        try {
            investList = (List<ProdPartnerDto>)param.get("investList");
            return prodPartner001Mapper.selectForExport(investList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<ProdPartnerDto>();
    }


    @Override
    public void doComplete(ProdPartnerDto prodPartner001Dto, OperationType operationType) {

    }

    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) throws FileNotFoundException, ParseException {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        //获取系统参数：单次导入的最大条数 todo:添加系统参数
        int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null?50000:Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
        //todo:数据量较大时，操作耗时时间过长
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName),
                null);
        List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
        //校验导入文件条数是否大于50000
        if(dataList.size()>maxCount){
            JoyinMessage ms = new JoyinMessage("导入数据超过"+maxCount+"条，可能导致系统较慢，是否确认导入？",
                    MessageType.确认消息);
            joyinMessages.add(ms);
        }

        List<ProdPartnerDto> entities = this.converToDto(dataList);
        List<ProdPartnerDto> data = this.selectProdPartnerByKey(entities);
        if (data != null && data.size() > 0) {
            Map<String, List<ProdPartnerDto>> map = entities.stream().collect(
                    JoyinCollectors.groupingBy(x ->
                            x.getProdId() + GlobalConstant.UNDER_LINE + DateUtil.format(x.getEffDate())
                                    + GlobalConstant.UNDER_LINE + DateUtil.format(x.getEffEndDate())
                    ));
            // 将序号放入list中
            List<String> excelNumList = new ArrayList<>();
            List<Integer> excelNumIntList = new ArrayList<>();
            for (ProdPartnerDto dto : data) {
                List<ProdPartnerDto> listTemp = map.get(dto.getProdId() + GlobalConstant.UNDER_LINE + DateUtil.format(dto.getEffDate())
                        + GlobalConstant.UNDER_LINE + DateUtil.format(dto.getEffEndDate()));
                excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelRowNum()));
            }
            // 将list进行排序，并转化为string
            Collections.sort(excelNumIntList);
            for (Integer rowNo : excelNumIntList) {
                if(!excelNumList.contains(String.valueOf(rowNo))){
                    excelNumList.add(String.valueOf(rowNo));
                }
            }
            wr.setMessage(
                    this.getMessage("REPORT_COMMON_FILE_C_0001",
                            StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                    MessageType.确认消息);
        } else {
            // 防止在进行导入前check时进行了两次的弹框
            wr.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
        }

        wr.setJoyinMessages(joyinMessages);
        return wr;
    }

    /**
     * excel导入功能
     *
     * @param fileName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String fileName) throws Exception {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        // 获取平台日期
        Date platDate = DateUtil.platDateFromCache()==null?new Date():DateUtil.platDateFromCache();
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName),
                null);
        List<List<String>> dataList = excelUtil.readToList(0, 0, 0);
        List<ExcelValidDto> excelValidDtolist=new ArrayList<>();

        if(dataList.size()<2){
            excelValidDtolist.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                      "文件内容为空,没有需要导入的数据！"));
            return excelValidDtolist;
        }
        if(null == dataList.get(0) || dataList.get(0).size() != 4){
            excelValidDtolist.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(1),
                    "导入文件模板不正确，表头应该为4列！"));
            return excelValidDtolist;
        }
        for (int i = 1; i < dataList.size(); i++) {
            // 验证消息
            List<String> message = new ArrayList<>();
            List<String> list = dataList.get(i);
            //字段非空检查
            if (!StringUtil.isNotEmpty(list.get(0))){
                message.add("产品主代码 不能为空！");
            }else{
                //判断产品主代码是否存在
                if(!checkProdIdExist(list.get(0).trim())){
                    message.add("产品主代码未在系统中维护！");
                }
            }
            if(!StringUtil.isNotEmpty(list.get(1))){
                message.add("合作机构 不能为空!");
            }
            if (!StringUtil.isNotEmpty(list.get(2))){
                message.add("生效日 不能为空！");
            }else{
                //判断日期格式是否正确
                if(!isDateStr(list.get(2).trim())){
                    message.add("生效日格式不正确，正确格式：yyyy/MM/dd！");
                }else{
                    //验证生效日是否大于等于平台日期
                    if (DateUtil.compareDate(platDate,DateUtil.parse(list.get(2).trim()))>0){
//                        excelValidDtolist.add(new ExcelValidDto(list.get(2), String.valueOf(i+1),
//                                "生效日要大于等于平台日期！"));
                    }
                }
            }
            if (!StringUtil.isNotEmpty(list.get(3))){
                if (DateUtil.compareDate(platDate,DateUtil.parse(list.get(3).trim()))>0){
                    message.add("生效截止日期 不能为空！");
                }
            }else{
                //判断日期格式是否正确
                if(!isDateStr(list.get(3).trim())){
                    message.add("生效截止日期格式不正确，正确格式：yyyy/MM/dd！");
                }else{
                    Date effEndDate=DateUtil.parse(list.get(3).trim());
                    //生效截止日期是否大于等于平台日期
                    if (DateUtil.compareDate(platDate,effEndDate)>0){
//                        excelValidDtolist.add(new ExcelValidDto(list.get(3), String.valueOf(i+1),
//                                "生效截止日期要大于等于平台日期！"));
                    }
                    if(DateUtil.isDateStr(list.get(2).trim())){
                        Date effDate=DateUtil.parse(list.get(2).trim());
                        if (DateUtil.compareDate(effDate,effEndDate)>0) {
                            message.add("生效截止日期要大于等于生效日！");
                        }
                        //生效日、生效截止日格式都正确，通过产品主代码查询系统中该产品已有的生效区间，校验生效区间是否有重叠
                        if(StringUtil.isNotEmpty(list.get(0))){
                            List<ProdPartnerDto> dtos = prodPartner001Mapper.selectPartnerByProdId(list.get(0).trim());
                            for (ProdPartnerDto dto : dtos) {
                                if(DateUtil.compareDate(effDate,dto.getEffDate())==0&&DateUtil.compareDate(effEndDate,dto.getEffEndDate())==0){
                                    break;
                                }
                                if(!((DateUtil.compareDate(effDate,dto.getEffDate())<0 && DateUtil.compareDate(effEndDate,dto.getEffDate())<0)
                                        || (DateUtil.compareDate(effDate,dto.getEffEndDate())>0 && DateUtil.compareDate(effEndDate,dto.getEffEndDate())>0))){
                                    excelValidDtolist.add(new ExcelValidDto(list.get(0), String.valueOf(i+1),
                                            "生效区间重叠！<br/> 系统中已存在的生效区间:"+DateUtil.format(dto.getEffDate(),"yyyyMMdd")
                                                    +"-"+DateUtil.format(dto.getEffEndDate(),"yyyyMMdd")));
                                    message.add("生效区间重叠！<br/> 系统中已存在的生效区间:"+DateUtil.format(dto.getEffDate(),"yyyyMMdd")
                                            +"-"+DateUtil.format(dto.getEffEndDate(),"yyyyMMdd"));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(message)) {
                excelValidDtolist.add(new ExcelValidDto(list.get(0)
                        + GlobalConstant.UNDER_LINE + list.get(2)
                        + GlobalConstant.UNDER_LINE + list.get(3),
                        String.valueOf(i + 1), StringUtil.joinString(message, "<br>")));
            }
        }
        if(StringUtil.isEmptyList(excelValidDtolist)){
            //所有数据通过校验后，转换成bean,按产品分组，校验导入文件中同一产品的生效区间是否有重叠
            List<ProdPartnerDto> partnerDtos = this.converToBean(dataList);
            if(partnerDtos!=null && partnerDtos.size()>=2){
                Map<String, List<ProdPartnerDto>> map = partnerDtos.stream().collect(Collectors.groupingBy(ProdPartnerDto::getProdId));
                for (String key : map.keySet()) {
                    List<ProdPartnerDto> prodPartnerDtoList = map.get(key);
                    if(prodPartnerDtoList.size()>1){
                        loop:for(int m=0;m<prodPartnerDtoList.size()-1;m++){
                            ProdPartnerDto dto1 = prodPartnerDtoList.get(m);
                            for(int n=m+1;n<prodPartnerDtoList.size();n++){
                                ProdPartnerDto dto2 = prodPartnerDtoList.get(n);
                                if(!((DateUtil.compareDate(dto2.getEffDate(),dto1.getEffDate())<0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffDate())<0)
                                        || (DateUtil.compareDate(dto2.getEffDate(),dto1.getEffEndDate())>0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffEndDate())>0))){
                                    excelValidDtolist.add(new ExcelValidDto(key, dto1.getExcelRowNum(),
                                            "文件中同一产品生效区间有重叠：第"+dto1.getExcelRowNum()+"行和第"+dto2.getExcelRowNum()+"行"));
                                    break loop;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (excelValidDtolist.isEmpty()) {
            List<ProdPartnerDto> equityList = converToBean(dataList);
            this.insertBtachEquityInfoByPart(equityList);
        }

        return excelValidDtolist;
    }

    /**
     * 查询产品主代码是否在系统中维护
     * @param prodId
     * @return
     */
    private boolean checkProdIdExist(String prodId) {
        return prodPartner001Mapper.selectByProdId(prodId)>0;
    }

    /**
     * 根据产品代码查询该产品的所有合作机构信息
     * @param prodId
     * @return
     */
    private boolean selectPartnerByProdId(String prodId) {
        List<ProdPartnerDto> prodPartnerDtoList=prodPartner001Mapper.selectPartnerByProdId(prodId);
        if(StringUtil.isEmptyList(prodPartnerDtoList)){
            return false;
        }
        return true;
    }

    /**
     * list转换为Bean
     *
     * @param dataList excel解析后的list
     * @return List<mstMarketCalendarInfo001Dto>
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<ProdPartnerDto> converToBean(List<List<String>> dataList)
            throws ParseException {
        // 返回的list定义
        List<ProdPartnerDto> returnInfoList = new ArrayList<ProdPartnerDto>();
        ProdPartnerDto prodPartnerDto = null;
        for (int i = 1; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            prodPartnerDto = new ProdPartnerDto();
            prodPartnerDto.setProdPartenerId(IDGenerator.generate(36));
            prodPartnerDto.setProdId(list.get(0).trim());
            prodPartnerDto.setPartnerName(list.get(1).trim());
            prodPartnerDto.setEffDate(DateUtil.parse(list.get(2).trim()));
            prodPartnerDto.setEffEndDate(DateUtil.parse(list.get(3).trim()));
            prodPartnerDto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            prodPartnerDto.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
            prodPartnerDto.setCreateDept(CommonUtil.getDeptCode());
            prodPartnerDto.setCreateUser(CommonUtil.getUserName());
            prodPartnerDto.setUpdateUser(CommonUtil.getUserName());
            prodPartnerDto.setExcelRowNum(String.valueOf(i+1));
            returnInfoList.add(prodPartnerDto);
        }
        return returnInfoList;
    }

    private List<ProdPartnerDto> converToDto(List<List<String>> dataList)
            throws ParseException {
        // 返回的list定义
        List<ProdPartnerDto> returnInfoList = new ArrayList<ProdPartnerDto>();
        ProdPartnerDto prodPartnerDto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            prodPartnerDto = new ProdPartnerDto();
            prodPartnerDto.setProdId(list.get(0).trim());
            prodPartnerDto.setEffDate(DateUtil.parse(list.get(2).trim()));
            prodPartnerDto.setEffEndDate(DateUtil.parse(list.get(3).trim()));
            prodPartnerDto.setExcelRowNum(String.valueOf(i+2));
            returnInfoList.add(prodPartnerDto);
        }
        return returnInfoList;
    }


    /**
     * 分批次执行insertBtachEquityInfo
     *
     * @param dataList 数据
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfoByPart(List<ProdPartnerDto> dataList) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }
        else {

            int maxDataCountOneTime = 20;
            int dataCount = dataList.size();
            if (dataCount <= maxDataCountOneTime) {
                insertBtachEquityInfo(dataList);
            }
            else {
                List<ProdPartnerDto> newDataList = new ArrayList<ProdPartnerDto>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    insertBtachEquityInfo(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    insertBtachEquityInfo(newDataList);
                }
            }
        }
    }

    /**
     * 分批次执行updateBtachEquityInfo
     *
     * @param dataList 数据
     * @see
     * @since 1.0
     */
    /*private void updateBtachEquityInfoByPart(List<ProdPartnerDto> dataList) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }

        int maxDataCountOneTime = 20;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            updateBtachEquityInfo(dataList);
        }
        else {

            List<ProdPartnerDto> newDataList = new ArrayList<ProdPartnerDto>();
            newDataList.addAll(dataList);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                updateBtachEquityInfo(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                updateBtachEquityInfo(newDataList);
            }
        }
    }*/


    /**
     * 插入数据 <功能详细描述>
     *
     * @param list 参数
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfo(List<ProdPartnerDto> list) {
        prodPartner001Mapper.insertBtachEquityInfo(list);
    }

    public List<ProdPartnerDto> findAll(Map<String, Object> parameters) {
        if(StringUtil.isNotEmpty(parameters.get("prodId"))){
            parameters.put("prodId",StringUtil.splitToList(null,parameters.get("prodId").toString()));
        }else{
            parameters.put("prodId",new ArrayList<>());
        }
        return prodPartner001Mapper.select(parameters);
    }

    /**
     * 分批查询
     * @param dataList
     * @return
     */
    private List<ProdPartnerDto> selectProdPartnerByKey(List<ProdPartnerDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return prodPartner001Mapper.selectProdPartnerList(dataList);
        }
        // 导入大于1000时，返回值
        List<ProdPartnerDto> equtiyList = new ArrayList<>();

        List<ProdPartnerDto> newDataList = new ArrayList<>();

        // 分批处理
        int part = dataCount / maxDataCountOneTime;
        List<ProdPartnerDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = prodPartner001Mapper.selectProdPartnerList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = prodPartner001Mapper.selectProdPartnerList(newDataList);
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

}
