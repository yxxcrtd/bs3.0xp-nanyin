package com.joyintech.tams.disclosure.information.config.repRelDeal.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dao.RepRelDeal001Mapper;
import com.joyintech.tams.disclosure.information.config.repRelDeal.dto.RepRelDealDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RepRelDealService extends CommonExportService<RepRelDealDto> implements BaseProcessService<RepRelDealDto> {
    @Autowired
    private RepRelDeal001Mapper repRelDeal001Mapper;
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
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->repRelDeal001Mapper.getProdInsId(map));
    }
    public List<SelectOptionDto> queryProdIdInfo2(Map<String, Object> map) {
        return repRelDeal001Mapper.getProdInsId(map);
    }

    public Page<RepRelDealDto> selectInfo(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepRelDealDto> result = queryForPage(Integer.parseInt((String) param.get("page")), Integer.parseInt((String) param.get("length")), ()
                -> repRelDeal001Mapper.selectInfo(param));
        return result;
    }

    public Page<RepRelDealDto> selectForExport(Map<String, Object> param) {
        param.put("prodList", StringUtil.isNotEmpty(param.get("prodId"))?(param.get("prodId").toString().split(",")):null);
        Page<RepRelDealDto> result = queryForPage( ()
                -> repRelDeal001Mapper.selectInfo(param));
        return result;
    }

    /**
     * 编辑查看显示查询
     *
     * @return
     */
    public RepRelDealDto selectData(Map<String, Object> param) {
        return repRelDeal001Mapper.selectData(param);
    }

    @Override
    public void doComplete(RepRelDealDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if(StringUtil.isEmpty(entity.getCreateDept())){
            entity.setCreateDept("");
        }
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setStatus("E");
            repRelDeal001Mapper.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            entity.setStatus("E");
            repRelDeal001Mapper.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            repRelDeal001Mapper.delete(entity);
        }
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/repRelDeal/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<RepRelDealDto> selectPageData(Map<String, Object> selectParam) {
        return selectForExport(selectParam);
    }

    public List<RepRelDealDto> selectExist(RepRelDealDto dto) {
        List<RepRelDealDto> result = repRelDeal001Mapper.selectExist(dto);
        return result;
    }

    public RepRelDealDto selectData2(Map<String, Object> param) {
        return repRelDeal001Mapper.selectData2(param);
    }

    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 50000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "单次最多导入50000条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        String totalMsg = "";
        List<List<String>> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }else {
            if (null == objects.get(0) || objects.get(0).size() != 7) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要7列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            } else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);
                    if (!StringUtil.isNotEmpty(rowData.get(0).trim())){
                        errorMsg.append("产品主代码 必填！");
                    }else if(!this.selectPProdId(rowData.get(0).trim())){
                        errorMsg.append("产品主代码未在系统中维护！");
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(1))){
                        errorMsg.append("期间投资关联机构发行证券交易金额 必填！");
                    }else if(!this.isBigDecimal(rowData.get(1))){
                        errorMsg.append("期间投资关联机构发行证券交易金额 格式不正确！");
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("期间投资关联机构承销证券交易金额 必填！");
                    }else if(!this.isBigDecimal(rowData.get(2))){
                        errorMsg.append("期间投资关联机构承销证券交易金额 格式不正确！");
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(3))){
                        errorMsg.append("期间关联方作为交易对手的投融资业务的交易金额 必填！");
                    }else if(!this.isBigDecimal(rowData.get(3))){
                        errorMsg.append("期间关联方作为交易对手的投融资业务的交易金额 格式不正确！");
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(4))){
                        errorMsg.append("关联方提供服务的交易金额 必填！");
                    }
                    Date platDate = DateUtil.platDateFromCache();
                    if (!StringUtil.isNotEmpty(rowData.get(5))){
                        errorMsg.append("生效日 必填！");
                    }else if(!this.isDateStr(rowData.get(5).trim())){
                        errorMsg.append("生效日 格式不正确！");
                    }else if (DateUtil.compareDate(DateUtil.parse(rowData.get(5).toString().trim()), platDate) < 0) {
                        errorMsg.append("生效日不能小于平台日！");
                    }

                    if (!StringUtil.isNotEmpty(rowData.get(6))){
                        errorMsg.append("生效截止日期 必填！");
                    }else if(!this.isDateStr(rowData.get(6).trim())){
                        errorMsg.append("生效截止日期 格式不正确！");
                    }else if (DateUtil.compareDate(DateUtil.parse(rowData.get(6).toString().trim()), platDate) < 0) {
                        errorMsg.append("生效截止日期不能小于平台日！");
                    }

                    if(StringUtil.isNotEmpty(rowData.get(5)) && StringUtil.isNotEmpty(rowData.get(6))){
                        if(this.isDateStr(rowData.get(5)) && this.isDateStr(rowData.get(6))){
                            Date effdate=DateUtil.parse(rowData.get(5).toString().trim());
                            Date effdelidate=DateUtil.parse(rowData.get(6).toString().trim());
                            if (DateUtil.compareDate(effdelidate,effdate) < 0){
                                errorMsg.append("生效截止日期不能小于生效日！");
                            }
                        }
                    }

                    if(StringUtil.isNotEmpty(rowData.get(0)) && StringUtil.isNotEmpty(rowData.get(5)) && StringUtil.isNotEmpty(rowData.get(6))){
                        Date effdate=DateUtil.parse(rowData.get(5).toString().trim());
                        Date effdelidate=DateUtil.parse(rowData.get(6).toString().trim());
                        RepRelDealDto map = new RepRelDealDto();
                        map.setProdId(rowData.get(0).trim());
                        map.setEffdate(effdate);
                        map.setEffdelidate(effdelidate);
                        List<RepRelDealDto> dtos = repRelDeal001Mapper.selectExistInfo(map);
                        for (RepRelDealDto dto : dtos) {
                            if(!((DateUtil.compareDate(effdate,dto.getEffdate())<0 && DateUtil.compareDate(effdelidate,dto.getEffdate())<0)
                                    || (DateUtil.compareDate(effdate,dto.getEffdelidate())>0 && DateUtil.compareDate(effdelidate,dto.getEffdelidate())>0)
                                    || (DateUtil.compareDate(effdate,dto.getEffdate())==0 && DateUtil.compareDate(effdelidate,dto.getEffdelidate())==0)
                            )){
                                errorMsg.append("生效区间重叠！ 系统中已存在的生效区间:"+DateUtil.format(dto.getEffdate(),"yyyy-MM-dd")
                                        +"-"+DateUtil.format(dto.getEffdelidate(),"yyyy-MM-dd"));
                                break;
                            }
                        }
                    }
                    if (StringUtil.isNotEmpty(errorMsg.toString())){
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第"+(i+1)+"行数据："+errorMsg.toString());
                    }
                }
                if(!StringUtil.isNotEmpty(returnerrorMsg.toString())){
                    //所有数据通过校验后，转换成bean,按产品分组，校验导入文件中同一产品的生效区间是否有重叠
                    List<RepRelDealDto> repRelDealDto = this.converToBean(objects);
                    if(repRelDealDto!=null && repRelDealDto.size()>=2){
                        Map<String, List<RepRelDealDto>> map = repRelDealDto.stream().collect(Collectors.groupingBy(RepRelDealDto::getProdId));
                        StringBuilder errorMsg = new StringBuilder("");
                        for (String key : map.keySet()) {
                            List<RepRelDealDto> repRelDealDtoList = map.get(key);
                            if(repRelDealDtoList.size()>1){
                                loop:for(int m=0;m<repRelDealDtoList.size()-1;m++){
                                    RepRelDealDto dto1 = repRelDealDtoList.get(m);
                                    for(int n=m+1;n<repRelDealDtoList.size();n++){
                                        RepRelDealDto dto2 = repRelDealDtoList.get(n);
                                        if(!((DateUtil.compareDate(dto2.getEffdate(),dto1.getEffdate())<0 && DateUtil.compareDate(dto2.getEffdelidate(),dto1.getEffdate())<0)
                                                || (DateUtil.compareDate(dto2.getEffdate(),dto1.getEffdelidate())>0 && DateUtil.compareDate(dto2.getEffdelidate(),dto1.getEffdelidate())>0)
                                                || (DateUtil.compareDate(dto2.getEffdate(),dto1.getEffdate()) == 0 && DateUtil.compareDate(dto2.getEffdelidate(),dto1.getEffdelidate())==0))){
                                            errorMsg.append("文件中同一产品生效区间有重叠：第"+dto1.getExcelRowNum()+"行和第"+dto2.getExcelRowNum()+"行");
                                            errorMsg.append("<br />");
                                            break loop;
                                        }
                                    }
                                }

                            }
                        }
                        if (StringUtil.isNotEmpty(errorMsg.toString())){
                            returnerrorMsg.append(errorMsg.toString());
                        }
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())){
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))
                                &&(StringUtil.equals(list1.get(5), list2.get(5)))
                                &&(StringUtil.equals(list1.get(6), list2.get(6)))) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat){
                        repeatNum++;
                    }else {
                        jsonArrays.add(list1);
                    }
                }

            }
        }
        if (repeatNum > 0){
            totalMsg += "导入文件存在"+String.valueOf(repeatNum)+"条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }

        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        String tempList = (String) resultMap.get("correctData");
        RepRelDealDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepRelDealDto> selectedItem = dto.getSelectedItem();
        List<RepRelDealDto>  existData = new ArrayList<>();
        for (RepRelDealDto temp:selectedItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId",temp.getProdId());
            map.put("effdate",temp.getEffdate());
            map.put("effdelidate",temp.getEffdelidate());
            if (StringUtil.isNotEmpty(this.selectData2(map))){
                existData.add(temp);
            }
        }
        if(existData.size() > 0){
            resultMap.put("confirmMsg2", "有重复信息，是否覆盖？");
        }
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    public Boolean selectPProdId(String prodId) {
        String r = repRelDeal001Mapper.selectPProdId(prodId);
        if(StringUtil.isNotEmpty(r)){
            return true;
        }else{
            return false;
        }
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
    public static boolean isBigDecimal(String str) {
        Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher m = NUMERIC_PATTERN.matcher(str);
        if (m.find()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * list转换为Bean
     *
     * @param dataList excel解析后的list
     * @return List<RepRelDealDto>
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<RepRelDealDto> converToBean(List<List<String>> dataList) {
        // 返回的list定义
        List<RepRelDealDto> repRelDealList = new ArrayList<RepRelDealDto>();
        RepRelDealDto repRelDealDto = null;
        for (int i = 1; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            repRelDealDto = new RepRelDealDto();
            repRelDealDto.setUuid(IDGenerator.generate(36));
            repRelDealDto.setProdId(list.get(0).trim());
            repRelDealDto.setBankTransAmt(new BigDecimal(list.get(1).trim()));
            repRelDealDto.setOrgTransAmt(new BigDecimal(list.get(2).trim()));
            repRelDealDto.setInvestTransAmt(new BigDecimal(list.get(3).trim()));
            repRelDealDto.setRelateamount(list.get(4).trim());
            repRelDealDto.setEffdate(DateUtil.parse(list.get(5).trim()));
            repRelDealDto.setEffdelidate(DateUtil.parse(list.get(6).trim()));
            repRelDealDto.setCreateDept(CommonUtil.getDeptCode());
            repRelDealDto.setCreateUser(CommonUtil.getUserName());
            repRelDealDto.setUpdateUser(CommonUtil.getUserName());
            repRelDealDto.setExcelRowNum(String.valueOf(i+1));
            repRelDealList.add(repRelDealDto);
        }
        return repRelDealList;
    }

    public RepRelDealDto getValNetAssetQuotationInfoListData(String tempList) {
        RepRelDealDto dto = new RepRelDealDto();
        List<RepRelDealDto> repRelDealDtoList = new ArrayList<>();
        dto.setSelectedItem(repRelDealDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            RepRelDealDto repRelDealDto = new RepRelDealDto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                repRelDealDto.setProdId(rowData.get(0).toString().trim());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                repRelDealDto.setBankTransAmt(new BigDecimal(rowData.get(1).toString().trim()));
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                repRelDealDto.setOrgTransAmt(new BigDecimal(rowData.get(2).toString().trim()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                repRelDealDto.setInvestTransAmt(new BigDecimal(rowData.get(3).toString().trim()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                repRelDealDto.setRelateamount(rowData.get(4).toString().trim());
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                repRelDealDto.setEffdate(DateUtil.parse(rowData.get(5).toString().trim()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                repRelDealDto.setEffdelidate(DateUtil.parse(rowData.get(6).toString().trim()));
            }
            repRelDealDto.setStatus("E");
            repRelDealDto.setUuid(IDGenerator.generate(36));
            repRelDealDtoList.add(repRelDealDto);
        }
        dto.setSelectedItem(repRelDealDtoList);
        return dto;
    }


    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        RepRelDealDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<RepRelDealDto> selectedItem = dto.getSelectedItem();
        for (RepRelDealDto temp:selectedItem) {
            repRelDeal001Mapper.importInfo(temp);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }
}
