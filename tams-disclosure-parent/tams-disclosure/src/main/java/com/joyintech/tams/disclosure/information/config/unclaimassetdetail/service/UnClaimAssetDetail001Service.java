package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao.UnClaimAssetDetailMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UnClaimAssetDetail001Service extends CommonExportService<UnClaimAssetDetailDto>
         implements BaseProcessService<UnClaimAssetDetailDto>{

    @Autowired
    private UnClaimAssetDetailMapper unClaimAssetDetailMapper;

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

        Page<UnClaimAssetDetailDto> page = super.queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
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

    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) throws FileNotFoundException, ParseException {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        //获取系统参数：单次导入的最大条数 todo:添加系统参数
        int maxCount = CacheUtil.getSysParam("SEND_CB_MAX_DATA_COUNT")==null?50000:Integer.parseInt(CacheUtil.getSysParam("SEND_CB_MAX_DATA_COUNT"));
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
        // 获取平台日期
        Date platDate = DateUtil.platDateFromCache()==null?new Date():DateUtil.platDateFromCache();
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName),
                null);
        List<List<String>> dataList = excelUtil.readToList(0, 0, 0);
        List<ExcelValidDto> excelValidDtolist=new ArrayList<>();
        if(dataList.size()<1){
            excelValidDtolist.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    "导入文件为空！"));
            return excelValidDtolist;
        }
        if(null == dataList.get(0) || dataList.get(0).size() != 9){
            excelValidDtolist.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(1),
                    "导入文件模板不正确，表头应该为9列！"));
            return excelValidDtolist;
        }
        for (int i = 1; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            //字段非空检查
            if (!StringUtil.isNotEmpty(list.get(0))){
                excelValidDtolist.add(new ExcelValidDto(list.get(0), String.valueOf(i+1),
                        "产品主代码 不能为空！"));
            }else{
                //判断产品主代码是否存在
                if(!checkProdIdExist(list.get(0).trim())){
                    excelValidDtolist.add(new ExcelValidDto(list.get(0), String.valueOf(i+1),
                            "产品主代码未在系统中维护！"));
                }
            }
            if(!StringUtil.isNotEmpty(list.get(1))){
                excelValidDtolist.add(new ExcelValidDto(list.get(1), String.valueOf(i+1),
                        "融资客户 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(2))){
                excelValidDtolist.add(new ExcelValidDto(list.get(2), String.valueOf(i+1),
                        "项目名称 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(3))){
                excelValidDtolist.add(new ExcelValidDto(list.get(3), String.valueOf(i+1),
                        "剩余融资期限 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(4))){
                excelValidDtolist.add(new ExcelValidDto(list.get(4), String.valueOf(i+1),
                        "到期收益分配 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(5))){
                excelValidDtolist.add(new ExcelValidDto(list.get(5), String.valueOf(i+1),
                        "交易结构 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(6))){
                excelValidDtolist.add(new ExcelValidDto(list.get(6), String.valueOf(i+1),
                        "风险状况 不能为空！"));
            }
            if (!StringUtil.isNotEmpty(list.get(7))){
                excelValidDtolist.add(new ExcelValidDto(list.get(7), String.valueOf(i+1),
                        "生效日 不能为空！"));
            }else{
                //判断日期格式是否正确
                if(!DateUtil.isDateStr(list.get(7).trim())){
                    excelValidDtolist.add(new ExcelValidDto(list.get(7), String.valueOf(i+1),
                            "生效日格式不正确，正确格式：yyyy-MM-dd！"));
                }else{
                    //验证生效日是否大于等于平台日期
                    if (DateUtil.compareDate(platDate,DateUtil.parse(list.get(7).trim()))>0){
                        excelValidDtolist.add(new ExcelValidDto(list.get(7), String.valueOf(i+1),
                                "生效日要大于等于平台日期！"));
                    }
                }
            }
            if (!StringUtil.isNotEmpty(list.get(8))){
                if (DateUtil.compareDate(platDate,DateUtil.parse(list.get(8).trim()))>0){
                    excelValidDtolist.add(new ExcelValidDto(list.get(8), String.valueOf(i+1),
                            "生效截止日期 不能为空！"));
                }
            }else{
                //判断日期格式是否正确
                if(!DateUtil.isDateStr(list.get(8).trim())){
                    excelValidDtolist.add(new ExcelValidDto(list.get(8), String.valueOf(i+1),
                            "生效截止日期格式不正确，正确格式：yyyy-MM-dd！"));
                }else{
                    Date effEndDate=DateUtil.parse(list.get(8).trim());
                    //生效截止日期是否大于等于平台日期
                    if (DateUtil.compareDate(platDate,effEndDate)>0){
                        excelValidDtolist.add(new ExcelValidDto(list.get(8), String.valueOf(i+1),
                                "生效截止日期要大于等于平台日期！"));
                    }
                    if(DateUtil.isDateStr(list.get(7).trim())){
                        Date effDate=DateUtil.parse(list.get(7).trim());
                        if (DateUtil.compareDate(effDate,effEndDate)>0) {
                            excelValidDtolist.add(new ExcelValidDto(list.get(8), String.valueOf(i+1),
                                    "生效截止日期要大于等于生效日！"));
                        }
                        //生效日、生效截止日格式都正确，通过产品主代码查询系统中该产品已有的生效区间，校验生效区间是否有重叠
                        if(StringUtil.isNotEmpty(list.get(0))){
                            List<UnClaimAssetDetailDto> dtos = unClaimAssetDetailMapper.selectNostdByProdId(list.get(0).trim());
                            for (UnClaimAssetDetailDto dto : dtos) {
                                if(!((DateUtil.compareDate(effDate,dto.getEffDate())<0 && DateUtil.compareDate(effEndDate,dto.getEffDate())<0)
                                        || (DateUtil.compareDate(effDate,dto.getEffEndDate())>0 && DateUtil.compareDate(effEndDate,dto.getEffEndDate())>0))){
                                    excelValidDtolist.add(new ExcelValidDto(list.get(0), String.valueOf(i+1),
                                            "生效区间重叠！<br/> 系统中已存在的生效区间:"+DateUtil.format(dto.getEffDate(),"yyyyMMdd")
                                                    +"-"+DateUtil.format(dto.getEffEndDate(),"yyyyMMdd")));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(StringUtil.isEmptyList(excelValidDtolist)){
            //所有数据通过校验后，转换成bean,按产品分组，校验导入文件中同一产品的生效区间是否有重叠
            List<UnClaimAssetDetailDto> unClaimAssetDetailDtos = this.converToBean(dataList);
            if(unClaimAssetDetailDtos!=null && unClaimAssetDetailDtos.size()>=2){
                Map<String, List<UnClaimAssetDetailDto>> map = unClaimAssetDetailDtos.stream().collect(Collectors.groupingBy(UnClaimAssetDetailDto::getProdId));
                for (String key : map.keySet()) {
                    List<UnClaimAssetDetailDto> unClaimAssetDetailDtoList = map.get(key);
                    if(unClaimAssetDetailDtoList.size()>1){
                        for(int m=0;m<unClaimAssetDetailDtoList.size()-1;m++){
                            UnClaimAssetDetailDto dto1 = unClaimAssetDetailDtoList.get(m);
                            for(int n=m+1;n<unClaimAssetDetailDtoList.size();n++){
                                UnClaimAssetDetailDto dto2 = unClaimAssetDetailDtoList.get(n);
                                if(!((DateUtil.compareDate(dto2.getEffDate(),dto1.getEffDate())<0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffDate())<0)
                                        || (DateUtil.compareDate(dto2.getEffDate(),dto1.getEffEndDate())>0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffEndDate())>0))){
                                    excelValidDtolist.add(new ExcelValidDto(key, StringUtil.EMPTY,
                                            "文件中同一产品生效区间有重叠！"));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (excelValidDtolist.isEmpty()) {
            List<UnClaimAssetDetailDto> equityList = converToBean(dataList);
            this.insertBtachEquityInfoByPart(equityList);
        }

        return excelValidDtolist;
    }



    /**
     * 根据产品代码查询该产品的所有非标资产信息
     * @param prodId
     * @U
     */
    private boolean selectNostdByProdId(String prodId) {
        List<UnClaimAssetDetailDto> unClaimAssetDetailList=unClaimAssetDetailMapper.selectNostdByProdId(prodId);
        if(StringUtil.isEmptyList(unClaimAssetDetailList)){
            return false;
        }
        return true;
    }
    /**
     * 检查导入文件中的产品主代码是否存在
     * @param prodId
     * @return
     */
    private boolean checkProdIdExist(String prodId) {
        return unClaimAssetDetailMapper.selectByProdId(prodId)>0;
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
    private List<UnClaimAssetDetailDto> converToBean(List<List<String>> dataList)
            throws ParseException {
        // 返回的list定义
        List<UnClaimAssetDetailDto> returnInfoList = new ArrayList<UnClaimAssetDetailDto>();
        UnClaimAssetDetailDto dto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            dto = new UnClaimAssetDetailDto();
            dto.setNostdAssetId(IDGenerator.generate(36));
            dto.setProdId(list.get(0).trim());
            dto.setFinancier(list.get(1).trim());
            dto.setProjectName(list.get(2).trim());
            dto.setRemFinancingPeriod(list.get(3).trim());
            dto.setIncomeDistribute(list.get(4).trim());
            dto.setTrdConstruct(list.get(5).trim());
            dto.setRiskInfo(list.get(6).trim());
            dto.setEffDate(DateUtil.parse(list.get(7).trim()));
            dto.setEffEndDate(DateUtil.parse(list.get(8).trim()));
            dto.setCreateDept(CommonUtil.getDeptCode());
            dto.setCreateUser(CommonUtil.getUserName());
            dto.setUpdateUser(CommonUtil.getUserName());
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
            dto.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());

            returnInfoList.add(dto);
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
    private void insertBtachEquityInfoByPart(List<UnClaimAssetDetailDto> dataList) {
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
                List<UnClaimAssetDetailDto> newDataList = new ArrayList<UnClaimAssetDetailDto>();
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
     * 插入数据 <功能详细描述>
     *
     * @param list 参数
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfo(List<UnClaimAssetDetailDto> list) {
        unClaimAssetDetailMapper.insertBtachEquityInfo(list);
    }






}
