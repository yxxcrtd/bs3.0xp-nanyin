package com.joyintech.tams.disclosure.information.config.repInvestAccount.service;

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
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dao.RepInvestAccount001Mapper;
import com.joyintech.tams.disclosure.information.config.repInvestAccount.dto.RepInvestAccountDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RepInvestAccountService extends CommonExportService<RepInvestAccountDto> implements BaseProcessService<RepInvestAccountDto> {

    @Autowired
    private RepInvestAccount001Mapper repInvestAccountMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @param
     *
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
//        return repInvestAccountMapper.getProdInsId(map);
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        if(map.containsKey("initValue")){
            String str=map.get("initValue").toString();
            List<String> prodIdList=Arrays.asList(str.split(","));
            map.put("prodIdList",prodIdList);
        }
        Integer page=1;
        if(StringUtil.isNotEmpty(pageParam.get("page"))){
            page=Integer.parseInt((String) pageParam.get("page"));
        }
        Integer length=100;
        if(StringUtil.isNotEmpty(pageParam.get("length"))){
            length=Integer.parseInt((String) pageParam.get("length"));
        }
        Page<SelectOptionDto> result = queryForPageForSelectReturn(page, length, ()
                -> repInvestAccountMapper.getProdInsId(map));
        return result;
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repInvestAccountMapper.getProdInsId2(map);
    }
    /**
     * 投资账户信息查询 <功能详细描述>
     *
     * @param param 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<RepInvestAccountDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepInvestAccountDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repInvestAccountMapper.selectInfo(param));
        return result;
    }

    /**
     * 投资账户数据操作 <功能详细描述>
     *
     * @param entity 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Override
    public void doComplete(RepInvestAccountDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
//        entity.setUpdateUser(CommonUtil.getUserName());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repInvestAccountMapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repInvestAccountMapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            entity.setUpdateUser(LoginUserUtil.getUserName());
            repInvestAccountMapper.delete(entity);
        }
    }

    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepInvestAccountDto selectData(Map<String, Object> param) {
        return repInvestAccountMapper.selectData(param);
    }

    public Map<String,String> selectProdName(String prodId) {
        Map<String, String> map = repInvestAccountMapper.selectProdName(prodId);
        if (map==null){
            map = new HashMap<>();
            map.put("PROD_NAME", "");
        }
        return map;
    }

    public RepInvestAccountDto selectByProdID(RepInvestAccountDto dto) {
        return repInvestAccountMapper.selectByProdID(dto);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repInvestAccount/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepInvestAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }

    public Page<RepInvestAccountDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepInvestAccountDto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()),
                () -> repInvestAccountMapper.selectInfo(param));
        return result;
    }

    public AjaxResult checkImportData(String fileName) {
        AjaxResult webResult = new AjaxResult();
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> tempList = ExcelUtil.getTempList(fileName);
        if (StringUtil.isEmptyList(tempList) || CollectionUtils.isEmpty(tempList)) {  //空文件
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        if (null == tempList.get(0) || tempList.get(0).size() != 4) {  //格式不正确
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0030", 4), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = tempList.subList(1, tempList.size());
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {  //导入的最大数不能超过5000
            int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null
                    ? 50000 : Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
            if (dataList.size() > maxCount) {
                webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0028"), MessageType.错误消息);
                return webResult;
            }
            List<RepInvestAccountDto> entities = this.convertToBean(dataList);
            List<RepInvestAccountDto> data = this.selectInvestAccountByKey(entities);
            if (data != null && data.size() > 0) {  
            	//对导入的数据与数据库已有的数据进行重复校验，校验规则：有相同产品id的数据判断为重复
                Map<String, List<RepInvestAccountDto>> map = entities.stream().collect(
                        JoyinCollectors.groupingBy(RepInvestAccountDto::getProdId));
                // 将序号放入list中
                List<String> excelNumList = new ArrayList<>();
                List<Integer> excelNumIntList = new ArrayList<>();
                for (RepInvestAccountDto dto : data) {
                    List<RepInvestAccountDto> listTemp = map.get(dto.getProdId());
                    excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                }
                // 将list进行排序，并转化为string
                Collections.sort(excelNumIntList);
                for (Integer rowNo : excelNumIntList) {
                    excelNumList.add(String.valueOf(rowNo));
                }
                //数据重复，覆盖确认？
                webResult.setMessage(
                        this.getMessage("REPORT_COMMON_FILE_C_0001",
                                StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } else {  //文件中没有需要导入的数据
            webResult.setMessage(this.getMessage("REPORT_COMMON_FILE_E_0016"), MessageType.错误消息);
            return webResult;
        }
        return webResult;
    }

    /**
     * excel数据转为java对象
     * @return
     */
    private List<RepInvestAccountDto> convertToBean(List<List<String>> dataList) {
        List<RepInvestAccountDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list =  dataList.get(i);
            RepInvestAccountDto entity = new RepInvestAccountDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                entity.setProdId(list.get(0));
            } else {
                entity.setProdId(" ");
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
    private List<RepInvestAccountDto> selectInvestAccountByKey(List<RepInvestAccountDto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return repInvestAccountMapper.selectInvestAccountList(dataList);
        }
        // 导入大于1000时，返回值
        List<RepInvestAccountDto> equtiyList = new ArrayList<>();

        List<RepInvestAccountDto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<RepInvestAccountDto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = repInvestAccountMapper.selectInvestAccountList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = repInvestAccountMapper.selectInvestAccountList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }

    public Boolean getProdId(String prodId) {
        String r = repInvestAccountMapper.getProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
    }

    public List<ExcelValidDto> importData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        if (!StringUtil.isEmptyList(dataList) && CollectionUtils.isNotEmpty(dataList)) {
            List<RepInvestAccountDto> entities = new ArrayList<>(dataList.size());
            List<ExcelValidDto> checkResult = convertToEntitys(dataList, entities);
            if (checkResult.isEmpty()) {
                entities.forEach(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prodId", item.getProdId());
                    if (StringUtil.isNotEmpty(this.selectData(map))) {
                        this.doComplete(item, OperationType.MODIFY);
                    } else {
                        this.doComplete(item, OperationType.NEW);
                    }
                });
            }
            return checkResult;
        } else {  //文件中无数据
            List<ExcelValidDto> checkResult = new ArrayList<>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    this.getMessage("REPORT_COMMON_FILE_E_0016")));
            return checkResult;
        }
    }

    private List<ExcelValidDto> convertToEntitys(List<List<String>> dataList,
                                                 List<RepInvestAccountDto> entities) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> rowData = dataList.get(i);
            if (null == rowData || rowData.size() != 4) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        this.getMessage("REPORT_COMMON_FILE_E_0030", 4)));
                break;
            }
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!StringUtil.isNotEmpty(rowData.get(0))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "产品主代码"));
            } else if (!this.getProdId(rowData.get(0))) {
                message.add("产品主代码未在系统中维护。");
            }
            if (!StringUtil.isNotEmpty(rowData.get(1))){
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "账号"));
            } else if (rowData.get(1).length() > 20) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0005", "账号"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(2))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "账户名称"));
            }
            if (!StringUtil.isNotEmpty(rowData.get(3))) {
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0001", "开户单位"));
            }
            if (StringUtil.isNotEmpty(rowData.get(0)) && map.containsKey(rowData.get(0))) {  //导入的文件中存在重复数据
                message.add(this.getMessage("REPORT_COMMON_FILE_E_0027"));
            }
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                map.put(rowData.get(0), rowData);
            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(rowData.get(0),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(this.convertToEntity(rowData));
            }
        }
        return checkResult;
    }

    private RepInvestAccountDto convertToEntity(List<String> rowData) {
        RepInvestAccountDto repInvestAccountDto = new RepInvestAccountDto();
        if (StringUtil.isNotEmpty(rowData.get(0))) {
            repInvestAccountDto.setProdId(rowData.get(0));
        }
        if (StringUtil.isNotEmpty(rowData.get(1))) {
            repInvestAccountDto.setAccountNum(rowData.get(1));
        }
        if (StringUtil.isNotEmpty(rowData.get(2))) {
            repInvestAccountDto.setAccountName(rowData.get(2));
        }
        if (StringUtil.isNotEmpty(rowData.get(3))) {
            repInvestAccountDto.setAccountOpener(rowData.get(3));
        }
        repInvestAccountDto.setAccountType("A01");
        repInvestAccountDto.setStatus("E");
        return repInvestAccountDto;
    }

    public RepInvestAccountDto checkExistData(RepInvestAccountDto dto) {
        return repInvestAccountMapper.checkExistData(dto);
    }

    public List<SelectOptionDto> queryAccountNum(Map<String, Object> map) {
         return repInvestAccountMapper.queryAccountNum(map);
    }
}
