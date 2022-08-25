package com.joyintech.tams.disclosure.information.config.proelementadj.service;

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
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProElementAdj001Service extends CommonExportService<ProElementAdj001Dto> implements BaseProcessService<ProElementAdj001Dto> {

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<ProElementAdj001Dto> selectForPage(Map<String, Object> param) {
        String prodRegistNo = (String)param.get("prodRegistNo");
        if(StringUtil.isNotEmpty(prodRegistNo)){
            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodRegistNo));
        }
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        String adjField = (String)param.get("adjField");
        if(StringUtil.isNotEmpty(adjField)){
            param.put("adjFieldList", StringUtil.splitToList(GlobalConstant.COMMA,adjField));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<ProElementAdj001Dto> result = queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), ()
                -> proElementAdjMapper.select(param));
        return result;
    }
    @Override
    public void doComplete(ProElementAdj001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            proElementAdjMapper.deleteByKey(dto.getProdAdjustId());
        }
        else if (StringUtil.equals(type, "NEW")) {
            ProElementAdj001Dto proElementAdj001Dto = new ProElementAdj001Dto();
            BeanUtil.copyPropertiesIgnoreNull(proElementAdj001Dto,dto);
            proElementAdj001Dto.setDataStatus("E");
            proElementAdjMapper.insert(proElementAdj001Dto);
        }
        else {
            dto.setDataStatus("E");
            dto.setUpdateUser(CommonUtil.getUserName());
            proElementAdjMapper.updateByKey(dto);
        }

    }



    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/proelementadj/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProElementAdj001Dto> selectPageData(Map<String, Object> selectParam) {
        return queryForPage(Integer.parseInt(selectParam.get("page").toString()),  Integer.parseInt(selectParam.get("length").toString()), ()
                -> proElementAdjMapper.select(selectParam));
    }

    public ProElementAdj001Dto selectByPrimaryKey(String prodRegistNo) {
        return proElementAdjMapper.selectByPrimaryKey(prodRegistNo);
    }




    public List<SelectOptionDto> getRegistNo(Map<String, Object> map) {
        return proElementAdjMapper.getRegistNo(map);
    }

    public String selectByProdRegistNo(Map map) {
        return proElementAdjMapper.selectByProdRegistNo(map);
    }

//    public Page<SelectOptionDto> getProdId(Map map) {
//        Map<String, Object> pageParam = RequestUtil.getPageParam();
//        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proElementAdjMapper.getProdId(map));
//    }

    public List<SelectOptionDto> getProdId(Map<String, Object> map) {
        return proElementAdjMapper.getProdId(map);
    }
    public Page<SelectOptionDto>  getAdjF(Map<String, Object> map) {
//        return proElementAdjMapper.getAdjF(map);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proElementAdjMapper.getAdjF(map));
    }

    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proElementAdjMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proElementAdjMapper.getProdIdlist(map));
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
        if (null == tempList.get(0) || tempList.get(0).size() != 13) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 13), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            if (dataList.size() > 50000) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            // 防止在进行导入前check时进行了两次的弹框
            webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;
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
            List<ProElementAdj001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    item.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                    item.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                    item.setUpdateTime(DateUtil.now());
                    item.setUpdateUser(CommonUtil.getUserName());
                    item.setProdAdjustId(IDGenerator.generate(36));
                    this.doComplete(item, OperationType.NEW);
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
                                                 List<ProElementAdj001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> registNoList =  this.getRegistNo(Maps.newHashMap())
                .stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        List<String> prodList =  this.getProdId(Maps.newHashMap())
                .stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 13) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 13)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品登记编码"));
            } else if(!registNoList.contains(rowData.get(0))){
                message.add("产品登记编码未在产品基本信息表中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "子产品代码"));
            } else if(!prodList.contains(rowData.get(1))){
                message.add("子产品代码未在产品基本信息表中维护。");
            }
            if (StringUtil.isNotEmpty(rowData.get(2)) && rowData.get(2).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整此产品要素原因"));
            }
            if (StringUtil.isNotEmpty(rowData.get(3)) && rowData.get(3).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "此产品要素调整描述文字1"));
            }
            if (StringUtil.isNotEmpty(rowData.get(4)) && rowData.get(4).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "此产品要素调整描述文字2"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(5))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "调整要素"));
            } else if (rowData.get(5).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整要素"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(6))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "调整前"));
            } else if (rowData.get(6).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整前"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(7))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "调整后"));
            } else if (rowData.get(7).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整后"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(8))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "生效时间"));
            } else if(!isDateStr(rowData.get(8))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "生效时间"));
            }
            if (StringUtil.isNotEmpty(rowData.get(9)) && rowData.get(9).length() > 500) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "对此要素调整(原因)"));
            }
            if (StringUtil.isNotEmpty(rowData.get(10)) && rowData.get(10).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整此要素（补充说明文1）"));
            }
            if (StringUtil.isNotEmpty(rowData.get(11)) && rowData.get(11).length() > 500){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "调整此要素（补充说明文2）"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(12))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "报告发布日期"));
            }else if(!isDateStr(rowData.get(12))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "报告发布日期"));
            }
            if (map.containsKey(rowData.get(1) + GlobalConstant.UNDER_LINE + rowData.get(5))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(1) + GlobalConstant.UNDER_LINE + rowData.get(5), rowData);
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(5),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private ProElementAdj001Dto convertToEntity(List<String> rowData) {
        ProElementAdj001Dto proElementAdj001Dto = new ProElementAdj001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            proElementAdj001Dto.setProdRegistNo(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            proElementAdj001Dto.setProdId(rowData.get(1));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            proElementAdj001Dto.setAdjReason(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            proElementAdj001Dto.setProdAdjRemark(rowData.get(3));
        }
        if (StringUtil.isNotEmpty(rowData.get(4))) {
            proElementAdj001Dto.setProdAdjRemark2(rowData.get(4));
        }
        if (StringUtil.isNotEmpty(rowData.get(5))) {
            proElementAdj001Dto.setAdjField(rowData.get(5));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))) {
            proElementAdj001Dto.setPreAdjust(rowData.get(6));
        }
        if (StringUtil.isNotEmpty(rowData.get(7))) {
            proElementAdj001Dto.setAfterAdjust(rowData.get(7));
        }
        if (StringUtil.isNotEmpty(rowData.get(8))) {
            proElementAdj001Dto.setEffDate(DateUtil.parse(rowData.get(8)));
        }
        if (StringUtil.isNotEmpty(rowData.get(9))) {
            proElementAdj001Dto.setAdjReason2(rowData.get(9));
        }
        if (StringUtil.isNotEmpty(rowData.get(10))) {
            proElementAdj001Dto.setFieldAdjRemark(rowData.get(10));
        }
        if (StringUtil.isNotEmpty(rowData.get(11))) {
            proElementAdj001Dto.setFieldAdjRemark2(rowData.get(11));
        }
        if (StringUtil.isNotEmpty(rowData.get(12))) {
            proElementAdj001Dto.setRptIssueDate(DateUtil.parse(rowData.get(12)));
        }
        return proElementAdj001Dto;
    }
}
