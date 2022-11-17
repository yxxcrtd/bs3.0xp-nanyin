package com.joyintech.tams.disclosure.information.config.investmentInfo.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;
import com.joyintech.tams.disclosure.information.config.accountamount.dto.AccountAmount001Dto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dao.InvestmentInfo001Mapper;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class InvestmentInfo001Service extends CommonExportService<InvestmentInfo001Dto> implements BaseProcessService<InvestmentInfo001Dto> {

    @Autowired
    private InvestmentInfo001Mapper investmentInfo001Mapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<InvestmentInfo001Dto> selectForPage(Map<String, Object> param) {
        String booksetId = (String)param.get("booksetId");
        if(StringUtil.isNotEmpty(booksetId)){
            param.put("booksetIdList", StringUtil.splitToList(GlobalConstant.COMMA,booksetId));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<InvestmentInfo001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> investmentInfo001Mapper.select(param));
        return result;
    }

    //flag 判断是否导入  false否  true是
    @Override
    public void doComplete(InvestmentInfo001Dto dto, OperationType operationType) {

        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (Objects.nonNull(dto.getFlag())&&!dto.getFlag()){
            dto.setProportionShare(dto.getProportionShare().divide(new BigDecimal("100")).setScale(4,BigDecimal.ROUND_HALF_UP));
        }
        Map<String,Object> map =new HashMap();
        if (StringUtil.equals(type, "DELETE")) {
            map.put("id",dto.getId());
            map.put("booksetId",dto.getBooksetId());
            map.put("indexType",dto.getIndexType());
            map.put("indexYear",dto.getIndexYear());
            map.put("tzRank",dto.getTzRank());
            map.put("tzType",dto.getTzType());
            InvestmentInfo001Dto dto1 = investmentInfo001Mapper.selectByPrimaryKey(map);
            dto1.setUpdateUser(LoginUserUtil.getUserName());
            dto1.setStatus("D");
            investmentInfo001Mapper.insertHis(dto1);
            investmentInfo001Mapper.deleteByKey(dto);
        }
        else if (StringUtil.equals(type, "NEW")) {
            map.put("booksetId",dto.getBooksetId());
            map.put("indexType",dto.getIndexType());
            map.put("indexYear",dto.getIndexYear());
            map.put("tzRank",dto.getTzRank());
            map.put("tzType",dto.getTzType());
            if (StringUtil.isNotEmpty(dto.getId())){
                map.put("id",dto.getId());
                InvestmentInfo001Dto dto1 = investmentInfo001Mapper.selectByPrimaryKey(map);
                if (dto1!=null && !StringUtil.equals(dto1.getStatus(),"D")){
                    dto.setStatus("E");
                    dto.setUpdateUser(CommonUtil.getUserName());
                    investmentInfo001Mapper.updateByKey(dto);
                }
            } else {
                InvestmentInfo001Dto investmentInfo001Dto = new InvestmentInfo001Dto();
                BeanUtil.copyPropertiesIgnoreNull(investmentInfo001Dto, dto);
                investmentInfo001Dto.setId(UUID.randomUUID().toString());
                investmentInfo001Dto.setStatus("E");
                investmentInfo001Dto.setUpdateUser(CommonUtil.getUserName());
                investmentInfo001Mapper.insert(investmentInfo001Dto);
            }
        }
        else {
            dto.setStatus("E");
            dto.setUpdateUser(CommonUtil.getUserName());
            investmentInfo001Mapper.updateByKey(dto);

        }
    }

    public InvestmentInfo001Dto selectByPrimaryKey(Map map) {
        return investmentInfo001Mapper.selectByPrimaryKey(map);
    }

    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return investmentInfo001Mapper.findProdId(map);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/investmentInfo/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<InvestmentInfo001Dto> selectPageData(Map<String, Object> selectParam) {
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
        if (null == tempList.get(0) || tempList.get(0).size() != 7) {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 5), MessageType.错误消息);
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
            List<InvestmentInfo001Dto> entities = null;
            try {
                entities = this.convertToBean(dataList);
            } catch (Exception e) {
                webResult.setMessage(e.getMessage(), MessageType.错误消息);
                return webResult;
            }
            // 防止在进行导入前check时进行了两次的弹框
            webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            /*List<InvestmentInfo001Dto> data = this.selectBalanceByKey(entities);
            if (data != null && data.size() > 0) {
                Map<String, List<AccountAmount001Dto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(x ->
                                x.getBooksetId()
                                        + GlobalConstant.UNDER_LINE + x.getDateStartStr()
                                        + GlobalConstant.UNDER_LINE + x.getDateEndStr()
                                        + GlobalConstant.UNDER_LINE + x.getSubjectNo()
                        ));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (AccountAmount001Dto dto : data) {
                    List<AccountAmount001Dto> listTemp = map.get(dto.getBooksetId()
                            + GlobalConstant.UNDER_LINE + dto.getDateStartStr()
                            + GlobalConstant.UNDER_LINE + dto.getDateEndStr()
                            + GlobalConstant.UNDER_LINE + dto.getSubjectNo());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_1111_1",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.错误消息);
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }*/
        } else {
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;

    }

    private List<InvestmentInfo001Dto> convertToBean(List<List<String>> dataList) {
        List<InvestmentInfo001Dto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            int i1 = i+2;
            List<String> list =  dataList.get(i);
            InvestmentInfo001Dto entity = new InvestmentInfo001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setIndexYear(list.get(0).trim());
            } else {
                throw new BusinessException("第"+i1+"行年度不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                //半年报年报
                if(DictCodeListExt3.前十大投资者信息指标类型_半年报.getCodeName().equals(list.get(1).trim())){
                    entity.setIndexType("01");
                }else if (DictCodeListExt3.前十大投资者信息指标类型_年报.getCodeName().equals(list.get(1).trim())){
                    entity.setIndexType("02");
                }else{
                    throw new BusinessException("第"+i1+"行数据类型必须是年报跟非年报！");
                }
            } else {
                throw new BusinessException("第"+i1+"行数据类型不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                entity.setTzRank(list.get(2).trim());
            } else {
                throw new BusinessException("第"+i1+"行排名不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                entity.setBooksetId(list.get(3).trim());
            } else {
                throw new BusinessException("第"+i1+"行产品主代码不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                entity.setTzType(list.get(4).trim());
            } else {
                throw new BusinessException("第"+i1+"行类别不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                BigDecimal bigDecimal = new BigDecimal("0");
                try {
                    bigDecimal = new BigDecimal(list.get(5));
                } catch (Exception e) {
                    throw new BusinessException("第"+i1+"行请检查持有份额类型!");
                }
                entity.setHoldShare(bigDecimal);
            } else {
                throw new BusinessException("第"+i1+"行持有份额不能为空!");
            }
            if (StringUtil.isNotEmpty(list.get(6))) {
                BigDecimal bigDecimal = new BigDecimal("0");
                try {
                    bigDecimal = new BigDecimal(list.get(6));
                } catch (Exception e) {
                    throw new BusinessException("第"+i1+"行请检查占总份额比例类型!");
                }
                entity.setProportionShare(bigDecimal);
            } else {
                throw new BusinessException("第"+i1+"行持有份额不能为空!");
            }
            entities.add(entity);
        }
        return entities;
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<InvestmentInfo001Dto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    item.setFlag(true);
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
                                                 List<InvestmentInfo001Dto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> findList =  investmentInfo001Mapper.getProdId1(Maps.newHashMap()).stream().map(SelectOptionDto::getId).collect(Collectors.toList());
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!findList.contains(rowData.get(3).trim())) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0033"));
            }
            Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
            if (rowData.get(5).indexOf(".")>0){
                Matcher isNum = pattern.matcher(rowData.get(5));
                if (!isNum.matches()){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0036"));
                }
                if (rowData.get(5).substring(rowData.get(5).indexOf(".")+1).length()>2){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0034"));
                }
            }
            if (rowData.get(6).indexOf(".")>0){
                if (rowData.get(6).substring(rowData.get(6).indexOf(".")+1).length()>4){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0035"));
                }
            }

            /*if (map.containsKey(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            map.put(rowData.get(0)
                    + GlobalConstant.UNDER_LINE + rowData.get(1)
                    + GlobalConstant.UNDER_LINE + rowData.get(2)
                    + GlobalConstant.UNDER_LINE + rowData.get(3), rowData);*/
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0)
                        + GlobalConstant.UNDER_LINE + rowData.get(1)
                        + GlobalConstant.UNDER_LINE + rowData.get(2)
                        + GlobalConstant.UNDER_LINE + rowData.get(3),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            entities.add(this.convertToEntity(rowData));
        }
        return checkResult;
    }

    private InvestmentInfo001Dto convertToEntity(List<String> rowData) {
        InvestmentInfo001Dto investmentInfo001Dto = new InvestmentInfo001Dto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            investmentInfo001Dto.setIndexYear(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            if (DictCodeListExt3.前十大投资者信息指标类型_半年报.getCodeName().equals(rowData.get(1))){
                investmentInfo001Dto.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_半年报.getValue());
            }
            if (DictCodeListExt3.前十大投资者信息指标类型_年报.getCodeName().equals(rowData.get(1))){
                investmentInfo001Dto.setIndexType(DictCodeListExt3.前十大投资者信息指标类型_年报.getValue());
            }
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            investmentInfo001Dto.setTzRank(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            investmentInfo001Dto.setBooksetId(rowData.get(3));
        }

        if (StringUtil.isNotEmpty(rowData.get(4))) {
            if (DictCodeListExt3.前十大投资者信息类别_个人.getCodeName().equals(rowData.get(4))){
                investmentInfo001Dto.setTzType(DictCodeListExt3.前十大投资者信息类别_个人.getValue());
            }
            if (DictCodeListExt3.前十大投资者信息类别_机构.getCodeName().equals(rowData.get(4))){
                investmentInfo001Dto.setTzType(DictCodeListExt3.前十大投资者信息类别_机构.getValue());
            }
        }
        if (StringUtil.isNotEmpty(rowData.get(5))){
            investmentInfo001Dto.setHoldShare(new BigDecimal(rowData.get(5)));
        }
        if (StringUtil.isNotEmpty(rowData.get(6))){
            investmentInfo001Dto.setProportionShare(new BigDecimal(rowData.get(6)));
        }
        return investmentInfo001Dto;
    }

}
