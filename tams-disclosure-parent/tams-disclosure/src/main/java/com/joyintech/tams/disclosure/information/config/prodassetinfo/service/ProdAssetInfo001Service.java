/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodassetinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dao.ProdAssetInfo001Mapper;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner001Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * 合作机构信息维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdAssetInfo001Service
 * @since 1.0
 */
@Service
public class ProdAssetInfo001Service extends CommonExportService<ProdAssetInfoDto>
    implements BaseProcessService<ProdAssetInfoDto>{

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdAssetInfo001Mapper prodAssetInfo001Mapper;


    /**
     * 根据产品代码或产品名称模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        //todo:现在的数据状态不统一
//        map.put("dataStatus",DictCodeListExt3.信披_数据状态_有效.getValue());
        return prodAssetInfo001Mapper.queryProdIdInfo(map);
    }

    /**
     * 使用给定的检索分页数据。
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<ProdAssetInfoDto> selectForPage(Map<String, Object> pageParam){
        //todo:前台查询的请求参数（String）和导出中的参数类型(int)不一致
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
        return super.queryForPage(page,length,() -> prodAssetInfo001Mapper.select(pageParam));
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/prodassetinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProdAssetInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 选中导出
     * @param param
     * @return
     */
    public List<ProdAssetInfoDto> selectForExport(Map<String, Object> param) {
        List<ProdAssetInfoDto> investList = null;
        try {
            investList = (List<ProdAssetInfoDto>)param.get("investList");
            return prodAssetInfo001Mapper.selectForExport(investList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<ProdAssetInfoDto>();
    }


    @Override
    public void doComplete(ProdAssetInfoDto prodPartner001Dto, OperationType operationType) {

    }


    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName)
            throws Exception {
        //文件检查结果
        List<ExcelValidDto> checkFileResult = new ArrayList<>();
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            // 检查文件名是否包含产品资产组合情况导入
            if (flieName.indexOf("产品资产组合情况导入") != -1) {
                ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName),
                        null);
                List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
                // 检查读出信息是否为空
                if (null != dataList && dataList.size() > 0) {
                    // 文件内容检查
                    List<ExcelValidDto> checkResult = new ArrayList<>();
                    List<ProdAssetInfoDto> entities = new ArrayList<>(dataList.size());
//                    checkResult = convertToEntity(dataList, entities);
                    if (checkResult.isEmpty()) {
                        dealAfterCheck(entities);
                    }
                    return checkResult;
                }else{
                    checkFileResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                            "文件内容为空,没有需要导入的数据！"));
                    return checkFileResult;
                }
            } else {
                checkFileResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                        getMessage("BUSS_COM_E_0002", "产品资产组合情况导入")));
                return checkFileResult;
            }
        }
        return null;
    }


    /**
     * 导入文件内容检查 <功能详细描述>
     *
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelData(List<List<String>> dataList)
            throws ParseException {
        List<ExcelValidDto> checkResult = new ArrayList<>();

        /*List<ExcelValidDto> checkResult = new ArrayList<>();
        List<ProdAssetInfoDto> equityList = converToBean(dataList);
        ProdAssetInfoDto partnerDto = null;
        // 检查表中是否有数据
        if (equityList.size() >= 1) {
            for (int i = 0; i < equityList.size(); i++ ) {
                partnerDto = equityList.get(i);
                // 检查表内数据每行是否合法
                if (checkExcelCow(i, dataList.get(i)) != null) {
                    checkResult.addAll(checkExcelCow(i, dataList.get(i)));
                }
                //检查表内的产品主代码在系统中是否存在
                if(StringUtil.isNotEmpty(partnerDto.getProdId())){
                    if(!checkProdIdExist(partnerDto.getProdId())){
                        checkResult.add(
                                new ExcelValidDto(dataList.get(i).get(0), String.valueOf(i+2),
                                        "产品主代码不存在！"));
                    }
                }
            }
        }
        // 检查表内数据同一个产品是否有生效区间重合的
        if (equityList.size() >= 2) {
            Map<String, List<ProdAssetInfoDto>> map = equityList.stream().collect(Collectors.groupingBy(ProdAssetInfoDto::getProdId));
            for (String key : map.keySet()) {
                List<ProdAssetInfoDto> prodPartnerDtoList = map.get(key);
                if(prodPartnerDtoList.size()>1){
                    for(int m=0;m<prodPartnerDtoList.size()-1;m++){
                        ProdAssetInfoDto dto1 = prodPartnerDtoList.get(m);
                        for(int n=m+1;n<prodPartnerDtoList.size();n++){
                            ProdAssetInfoDto dto2 = prodPartnerDtoList.get(n);
                            if(!((DateUtil.compareDate(dto2.getEffDate(),dto1.getEffDate())<0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffDate())<0)
                                    || (DateUtil.compareDate(dto2.getEffDate(),dto1.getEffEndDate())>0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffEndDate())>0))){
                                checkResult.add(new ExcelValidDto(key+"("+DateUtil.format(dto1.getEffDate())+"~"+DateUtil.format(dto1.getEffEndDate())+")",null,"生效区间范围有重叠"));
                                checkResult.add(new ExcelValidDto(key+"("+DateUtil.format(dto2.getEffDate())+"~"+DateUtil.format(dto2.getEffEndDate())+")",null,"生效区间范围有重叠"));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return checkResult;*/
        return checkResult;
    }

    /**
     * 检查导入文件中的产品主代码是否存在
     * @param prodId
     * @return
     */
    private boolean checkProdIdExist(String prodId) {
        return prodAssetInfo001Mapper.selectByProdId(prodId)>0;
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
    private List<ProdAssetInfoDto> converToBean(List<List<String>> dataList)
            throws ParseException {
        // 返回的list定义
        List<ProdAssetInfoDto> returnInfoList = new ArrayList<ProdAssetInfoDto>();
        ProdAssetInfoDto prodPartnerDto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            prodPartnerDto = new ProdAssetInfoDto();
            prodPartnerDto.setProdId(list.get(0));
            prodPartnerDto.setFixPreRatio(new BigDecimal(list.get(1)));
//            prodPartnerDto.setPartnerName(list.get(1));
            prodPartnerDto.setEffDate(DateUtil.parse(list.get(2)));
            prodPartnerDto.setEffEndDate(DateUtil.parse(list.get(3)));
            prodPartnerDto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            prodPartnerDto.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
            returnInfoList.add(prodPartnerDto);
        }
        return returnInfoList;
    }

    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i 第几行
     * @param list 每一列解析后的List<String>
     * @return 检查结构
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtolist = new ArrayList<ExcelValidDto>();
        // 模板中列数应为4
        if (null == list || list.size() != 4) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                    this.getMessage("MST_INFO_E_0001"));
            excelValidDtolist.add(dto);
            return excelValidDtolist;
        }
        //字段非空检查
        String[] elementStr={"主产品代码","合作机构","生效日","生效截止日期"};
        for (int j = 0; j < list.size(); j++) {
            if(list.get(j)==null || list.get(j)==""){
                ExcelValidDto dto = new ExcelValidDto(list.get(j), String.valueOf(i+2),
                        elementStr[j]+"不能为空！");
                excelValidDtolist.add(dto);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date effDate = null;//生效日
        Date effEndDate = null;//生效日截止日
        // 获取平台日期
        Date sysDate = DateUtil.platDateFromCache();
        // 使用日期格式化捕获异常来判断日期格式是否合法
        try {
            effDate = list==null?null:sdf.parse(list.get(2));
            if (DateUtil.compareDate(sysDate,effDate)>0) {
                // 验证生效日是否大于等于平台日期
                ExcelValidDto dto = new ExcelValidDto(list.get(2), String.valueOf(i+2),
                        this.getMessage("BUSS_COM_E_0004", "生效日", "平台日期"));
                excelValidDtolist.add(dto);
            }
        }
        catch (ParseException e) {
            ExcelValidDto dto = new ExcelValidDto(list.get(2), String.valueOf(i+2),
                    this.getMessage("MST_INFO_E_0001"));
            excelValidDtolist.add(dto);
        }
        try {
            effEndDate = list==null?null:sdf.parse(list.get(3));
            if (DateUtil.compareDate(sysDate,effEndDate)>0) {
                // 验证生效日截止日是否大于等于平台日期
                ExcelValidDto dto = new ExcelValidDto(list.get(3), String.valueOf(i+2),
                        this.getMessage("BUSS_COM_E_0004", "生效截止日期", "平台日期"));
                excelValidDtolist.add(dto);
            }
            //生效日截止日是否大于等于生效日
            if (DateUtil.compareDate(effDate,effEndDate)>0) {
                // 验证生效日截止日是否大于等于平台日期
                ExcelValidDto dto = new ExcelValidDto(list.get(3), String.valueOf(i+2),
                        this.getMessage("BUSS_COM_E_0004", "生效截止日期", "生效日"));
                excelValidDtolist.add(dto);
            }
        }
        catch (ParseException e) {
            ExcelValidDto dto = new ExcelValidDto(list.get(3), String.valueOf(i+2),
                    this.getMessage("MST_INFO_E_0001"));
            excelValidDtolist.add(dto);
        }
        return excelValidDtolist;
    }

    /**
     * check后的保存 <功能详细描述>
     *
     * @param equityList 需要保存的数据
     * @see
     * @since 1.0
     */
    public void dealAfterCheck(List<ProdAssetInfoDto> equityList) {
        List<ProdAssetInfoDto> listN = new ArrayList<ProdAssetInfoDto>();
        ProdAssetInfoDto partnerDto=new ProdAssetInfoDto();
        for (int i = 0; i < equityList.size(); i++ ) {
            partnerDto = equityList.get(i);
            listN.add(partnerDto);
        }
        if (listN != null && listN.size() > 0) {
            this.insertBtachEquityInfoByPart(listN);
        }
        /*List<ProdPartnerDto> listN = new ArrayList<ProdPartnerDto>();
        List<ProdPartnerDto> listA = new ArrayList<ProdPartnerDto>();
        for (int i = 0; i < equityList.size(); i++ ) {
            ProdPartnerDto partnerDto = equityList.get(i);
            if(this.checkProdIdExist(partnerDto.getProdId())){
                listA.add(partnerDto);
            }else{
                listN.add(partnerDto);
            }
        }
        if (listN != null && listN.size() > 0) {
            this.insertBtachEquityInfoByPart(listN);
        }
        if (listA != null && listA.size() > 0) {
            this.updateBtachEquityInfoByPart(listA);
        }*/
    }

    /**
     * 分批次执行insertBtachEquityInfo
     *
     * @param dataList 数据
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfoByPart(List<ProdAssetInfoDto> dataList) {
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
                List<ProdAssetInfoDto> newDataList = new ArrayList<ProdAssetInfoDto>();
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
    private void updateBtachEquityInfoByPart(List<ProdPartnerDto> dataList) {
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
    }


    /**
     * 插入数据 <功能详细描述>
     *
     * @param list 参数
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfo(List<ProdAssetInfoDto> list) {
        prodAssetInfo001Mapper.insertBtachEquityInfo(list);
    }

    /**
     * 更新数据 <功能详细描述>
     *
     * @param list 参数
     * @see
     * @since 1.0
     */
    private void updateBtachEquityInfo(List<ProdPartnerDto> list) {
        simpleRestTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ProdPartner001/updateBtachEquityInfo",
                list, Integer.class);
    }

    public List<ProdAssetInfoDto> findAll(Map<String, Object> parameters) {
        if(StringUtil.isNotEmpty(parameters.get("prodId"))){
            parameters.put("prodId",StringUtil.splitToList(null,parameters.get("prodId").toString()));
        }else{
            parameters.put("prodId",new ArrayList<>());
        }
        return prodAssetInfo001Mapper.select(parameters);
    }
}
