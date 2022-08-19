package com.joyintech.tams.disclosure.information.config.profailureinfo.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
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
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dao.ProFailureInfoMapper;
import com.joyintech.tams.disclosure.information.config.profailureinfo.dto.ProFailureInfo001Dto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProFailureInfo001Service extends CommonExportService<ProFailureInfo001Dto> implements BaseProcessService<ProFailureInfo001Dto> {

    @Autowired
    private ProFailureInfoMapper proFailureInfoMapper;

    @Autowired
    private ProElementAdjMapper proElementAdjMapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;
    public Page<ProFailureInfo001Dto> selectForPage(Map<String, Object> param) {
        String prodId = (String)param.get("prodRegistNo");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        Page<ProFailureInfo001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> proFailureInfoMapper.select(param));
        return result;
    }
    @Override
    public void doComplete(ProFailureInfo001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            proFailureInfoMapper.deleteByKey(dto.getProdRegistNo());
        }
        else if (StringUtil.equals(type, "NEW")) {
            ProFailureInfo001Dto dto1 = proFailureInfoMapper.isRepeated2(dto);
            if (dto1!=null){
                if( StringUtil.equals(dto1.getDataStatus(),"D")){
                    dto.setDataStatus("E");
                    proFailureInfoMapper.updateByKey(dto);
                }
            }else {
            ProFailureInfo001Dto proFailureInfo001Dto = new ProFailureInfo001Dto();
            BeanUtil.copyPropertiesIgnoreNull(proFailureInfo001Dto,dto);
            proFailureInfo001Dto.setDataStatus("E");
            proFailureInfoMapper.insert(proFailureInfo001Dto);
            }
        }
        else {
            dto.setDataStatus("E");
            proFailureInfoMapper.updateByKey(dto);
        }

    }



    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/profailureinfo/config/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<ProFailureInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }

    public ProFailureInfo001Dto selectByPrimaryKey(String prodRegistNo) {
        return proFailureInfoMapper.selectByPrimaryKey(prodRegistNo);
    }




    public List<SelectOptionDto> getRegistNo(Map<String, Object> map) {
        return proFailureInfoMapper.getRegistNo(map);
    }

    public String selectByProdRegistNo(String prodRegistNo) {
        return proFailureInfoMapper.selectByProdRegistNo(prodRegistNo);
    }

//    public Page<SelectOptionDto> getProdId(Map map) {
//        Map<String, Object> pageParam = RequestUtil.getPageParam();
//        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getProdId(map));
//    }

    public List<SelectOptionDto> getProdId(Map<String, Object> map) {
        return proFailureInfoMapper.getProdId(map);
    }


    public Page<SelectOptionDto> getRegistNoList(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getRegistNoList(map));

    }


    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->proFailureInfoMapper.getProdIdlist(map));
    }
    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        List<String> findList =  proElementAdjMapper.getRegistNo(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
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
        } else {
            if (null == objects.get(1) || objects.get(1).size() < 3) {//NYLCXP-599
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要3列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            }else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);


                    if (!StringUtil.isNotEmpty(rowData.get(0).trim())){
                        errorMsg.append("产品登记编码 必填！");
                    }
                    else if(!findList.contains(rowData.get(0).trim())){
                        errorMsg.append("产品登记编码不存在！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1).trim())){
                        errorMsg.append("不成立原因 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))){
                        errorMsg.append("报告发布日期 必填！");
                    }else if(!this.isDateStr(rowData.get(2))){
                        errorMsg.append("报告发布日期 格式不正确！");
                    }
                    if (StringUtil.isNotEmpty(errorMsg.toString())){
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第"+(i+1)+"行数据："+errorMsg.toString());
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())){
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //产品登记编码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(1), list2.get(1)))) {
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
        ProFailureInfo001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProFailureInfo001Dto> selectedItem = dto.getSelectedItem();
        List<ProFailureInfo001Dto>  existData = new ArrayList<>();
        for (ProFailureInfo001Dto temp:selectedItem) {
            if (this.isRepeated(temp).size()>0){
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


    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        String tempList = (String) param.get("tempList");
        ProFailureInfo001Dto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProFailureInfo001Dto> selectedItem = dto.getSelectedItem();
        List<ProFailureInfo001Dto>  existData = new ArrayList<>();
        List<ProFailureInfo001Dto>  newData = new ArrayList<>();

        for (ProFailureInfo001Dto temp:selectedItem) {
            //数据已存在 【产品登记编码 相同】，做更新操作。
            if (this.isRepeated(temp).size()>0){
                existData.add(temp);
            }else {
                temp.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                temp.setUpdateTime(sysDate);
                temp.setUpdateUser(CommonUtil.getUserName());
                newData.add(temp);
            }
        }
        //更新操作
        for (ProFailureInfo001Dto existDataDto : existData) {
            this.doComplete(existDataDto,OperationType.MODIFY);
        }
        //插入操作
        for (ProFailureInfo001Dto newDataDto : newData
        ) {
            this.doComplete(newDataDto,OperationType.NEW);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public ProFailureInfo001Dto getValNetAssetQuotationInfoListData(String tempList) {
        ProFailureInfo001Dto dto = new ProFailureInfo001Dto();
        // 产品不成立信息列表
        List<ProFailureInfo001Dto> ProFailureInfo001DtoList = new ArrayList<>();
        dto.setSelectedItem(ProFailureInfo001DtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            ProFailureInfo001Dto ProFailureInfo001Dto = new ProFailureInfo001Dto();
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                ProFailureInfo001Dto.setProdRegistNo(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                ProFailureInfo001Dto.setNocreateReason(rowData.get(1).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                ProFailureInfo001Dto.setReportIssueDate(DateUtil.parse(rowData.get(2).toString()));
            }
            ProFailureInfo001DtoList.add(ProFailureInfo001Dto);
        }
        dto.setSelectedItem(ProFailureInfo001DtoList);
        return dto;
    }


    public List<SelectOptionDto> getRePord(Map<String, Object> pageParam) {
        return proFailureInfoMapper.getRePord(pageParam);
    }

    public int hasRepeatedData(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.hasRepeatedData(dto);
    }

    public List<ProFailureInfo001Dto> isRepeated(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.isRepeated(dto);
    }

    public ProFailureInfo001Dto isRepeated2(ProFailureInfo001Dto dto) {
        return proFailureInfoMapper.isRepeated2(dto);
    }
}
