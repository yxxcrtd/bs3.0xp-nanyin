package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dao.AssetInvestmentDetailMapper;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto.AssetInvestmentDetail001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class AssetInvestmentDetail001Service extends CommonExportService<AssetInvestmentDetail001Dto> implements BaseProcessService<AssetInvestmentDetail001Dto> {

    @Autowired
    private AssetInvestmentDetailMapper assetInvestmentDetailMapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<AssetInvestmentDetail001Dto> selectForPage(Map<String, Object> param) {
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Page<AssetInvestmentDetail001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> assetInvestmentDetailMapper.select(param));
        return result;
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
        if (null == tempList.get(0) || tempList.get(0).size() != 6) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 6), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT") == null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<AssetInvestmentDetail001Dto> entities = this.convertToBean(dataList);
            List<AssetInvestmentDetail001Dto> data = this.selectBalanceByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<AssetInvestmentDetail001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getProdId()
                                        + GlobalConstant.UNDER_LINE + x.getAssetCode()
                                        + GlobalConstant.UNDER_LINE + x.getPubDateStr()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (AssetInvestmentDetail001Dto dto : data) {
                    List<AssetInvestmentDetail001Dto> listTemp = map.get(dto.getProdId()
                            + GlobalConstant.UNDER_LINE + dto.getAssetCode()
                            + GlobalConstant.UNDER_LINE + dto.getPubDateStr());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_1111_2",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.错误消息);
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

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<AssetInvestmentDetail001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId",item.getProdId());
                    map.put("pubDate",item.getPubDateStr());
                    map.put("assetCode",item.getAssetCode());
                    AssetInvestmentDetail001Dto dto = this.selectByPrimaryKey(map);
                    if (null !=dto && !StringUtil.equals(dto.getStatus(),"D")){
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
                        this.doComplete(item,OperationType.NEW);
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



    @Override
    public void doComplete(AssetInvestmentDetail001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            HashMap map =new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("pubDate",dto.getPubDateStr());
            map.put("assetCode",dto.getAssetCode());
            AssetInvestmentDetail001Dto dto1 = assetInvestmentDetailMapper.selectByPrimaryKey(map);
            dto1.setUpdateUser(LoginUserUtil.getUserName());
            dto1.setStatus("D");
            assetInvestmentDetailMapper.updateByKey(dto1);
        }
        else if (StringUtil.equals(type, "NEW")) {
            HashMap map =new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("pubDate",dto.getPubDateStr());
            map.put("assetCode",dto.getAssetCode());
            AssetInvestmentDetail001Dto dto1 = assetInvestmentDetailMapper.selectByPrimaryKey(map);
            if (dto1!=null && !StringUtil.equals(dto1.getStatus(),"D")){
                dto.setStatus("E");
                dto.setErrormsg("");
                dto.setUpdateUser(CommonUtil.getUserName());
                assetInvestmentDetailMapper.updateByKey(dto);
            }
            else {
                AssetInvestmentDetail001Dto assetInvestmentDetail001Dto = new AssetInvestmentDetail001Dto();
                BeanUtil.copyPropertiesIgnoreNull(assetInvestmentDetail001Dto, dto);
                assetInvestmentDetail001Dto.setStatus("E");
                assetInvestmentDetail001Dto.setUpdateUser(CommonUtil.getUserName());
                assetInvestmentDetailMapper.insert(assetInvestmentDetail001Dto);
            }
        }
        else {
            dto.setStatus("E");
            dto.setErrormsg("");
            dto.setUpdateUser(CommonUtil.getUserName());
            assetInvestmentDetailMapper.updateByKey(dto);
        }

    }




    @Override
    public Page<AssetInvestmentDetail001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }



    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return assetInvestmentDetailMapper.findProdId(map);
    }



    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->assetInvestmentDetailMapper.getProdIdlist(map));
    }




    public AssetInvestmentDetail001Dto selectByPrimaryKey(Map map) {
        return assetInvestmentDetailMapper.selectByPrimaryKey(map);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/assetinvestmentdetail/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * excel数据转为java对象
     * @return
     */
    private List<AssetInvestmentDetail001Dto> convertToBean(List<List<String>> dataList) {
        List<AssetInvestmentDetail001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            AssetInvestmentDetail001Dto entity = new AssetInvestmentDetail001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                entity.setPubDateStr(list.get(1));
            } else {
                entity.setPubDateStr(" ");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setAssetCode(list.get(2));
            } else {
                entity.setAssetCode(" ");
            }
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }

    /**
     * 分批查询
     * @param
     * @return
     */
    private List<AssetInvestmentDetail001Dto> selectBalanceByKey(List<AssetInvestmentDetail001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return assetInvestmentDetailMapper.selectBalanceList(dataList);
        }
        // 导入大于1000时，返回值
        List<AssetInvestmentDetail001Dto> equtiyList = new ArrayList<>();

        List<AssetInvestmentDetail001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<AssetInvestmentDetail001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = assetInvestmentDetailMapper.selectBalanceList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = assetInvestmentDetailMapper.selectBalanceList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    private List<ExcelValidDto> convertToEntitys(List<List<String>> dataList,
                                                 List<AssetInvestmentDetail001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList =  assetInvestmentDetailMapper.getProdId1(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 6) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 6)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            }else if (!findList.contains(rowData.get(0).trim())) {
                message.add("产品主代码在产品基本信息表中没有维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "日期"));
            } else if (!isDateStr(rowData.get(1))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0032", "日期"));
            }

            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "资产代码"));
            }


            if (!isBigDecimal(rowData.get(4))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "公允价值（元）"));
            }

            if (!isBigDecimal(rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0012", "占产品资产比例（%）"));
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

    private AssetInvestmentDetail001Dto convertToEntity(List<String> rowData) {
        AssetInvestmentDetail001Dto assetInvestmentDetail001Dto = new AssetInvestmentDetail001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            assetInvestmentDetail001Dto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            assetInvestmentDetail001Dto.setPubDate(DateUtil.parse(rowData.get(1)));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            assetInvestmentDetail001Dto.setAssetCode(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            assetInvestmentDetail001Dto.setAssetName(rowData.get(3));
        }

        if (StringUtil.isNotEmpty(rowData.get(4))) {
            assetInvestmentDetail001Dto.setFairValue(new BigDecimal(rowData.get(4)));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            assetInvestmentDetail001Dto.setPdDrop(new BigDecimal(rowData.get(5)));
        }
        return assetInvestmentDetail001Dto;
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

    public static boolean isBigDecimal(String str) {
        Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher m = NUMERIC_PATTERN.matcher(str);
        if (m.find()) {
            return true;
        }else{
            return false;
        }
    }
}