/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 每日债券行情
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Service
 * @since 1.0
 */
@Service
public class ComBondMarketInfo001Service  extends CommonExportService<ComBondMarketInfo001Dto> implements BaseProcessService<ComBondMarketInfo001Dto> {

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<ComBondMarketInfo001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/findPage",
        		pageParam, ComBondMarketInfo001Dto.class);
    }

    /**
     * 资产代码的联想下拉框
     * 
     * @param param param
     * @return List
     * @throws Exception 异常
     */
    public List<SuggestDto> getSecIdOption(String param) {
        return restTemplate.getForList(ServiceApplication.CommonBasicDataService
                                       + "/ComBondMarketInfo001/getSecId?secId=" + param,
            SuggestDto.class);
    }

    /**
     * 查询债券简称
     * 
     * @return List
     */
    public Page<SelectOptionDto> getFinprodAbbrOption(Map<String,Object> param) throws Exception{
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/getFinprodAbbrOption",
        		param,SelectOptionDto.class);
    }

    /**
     * 删除
     * 
     * @param pageId 画面ID
     * @param dto ComBondMarketInfo001Dto
     * @return 删除结果
     */
    public String delete(String pageId, ComBondMarketInfo001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/{pageId}/delete",
            dto, String.class, pageId);
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(ComBondMarketInfo001Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // entity.setSystemCode("SYS");
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/" + action, entity,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
           "/com/joyintech/tams/common/basicdata/marketdata/bondmarketinfo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ComBondMarketInfo001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 获取债券简称
     * 
     * @param finprodAbbr 债券简称
     * @return List
     */
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr) {
        return restTemplate.getForList(ServiceApplication.CommonBasicDataService
                                       + "/ComBondMarketInfo001/getFinProdAbbr?finprodAbbr="
                                       + finprodAbbr,
            SuggestDto.class);
    }

    /**
     * @param secId secId
     * @return dto
     */
    public ComBondMarketInfo001Dto getBySecId(String secId) {
        return restTemplate.getForObject(ServiceApplication.CommonBasicDataService
                                         + "/ComBondMarketInfo001/getBySecId?secId=" + secId,
            ComBondMarketInfo001Dto.class);
    }

    /**
     * @param secId secId
     * @param valueDate valueDate
     * @param valueSource valueSource
     * @return dto
     */
    public ComBondMarketInfo001Dto selectByKey(String secId, String valueDate,String valueSource) {
        return restTemplate.getForObject(ServiceApplication.CommonBasicDataService
                                         + "/ComBondMarketInfo001/selectByKey?secId="
                                         + secId + "&valueDate=" + valueDate+ "&valueSource=" + valueSource,
            ComBondMarketInfo001Dto.class);
    }

    /**
     * 新建画面的资产代码查询
     * 
     * @param secId 债券代码
     * @return list
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecIdToNew(String secId) {
        return restTemplate.getForList(
            ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/getSecIdToNew?secId="
                                                           + secId,
                                                           SuggestDto.class);
    }

    public List<SelectOptionDto> getAssetCodeList() {
        return restTemplate.getForList(
            ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/getFinProdAbbr", 
            SelectOptionDto.class);
    }

    /**
     * 查询符合条件的所有数据
     * @param pageParam
     * @return
     */
    public List<ComBondMarketInfo001Dto> selectAllList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/selectAllList",
                pageParam, ComBondMarketInfo001Dto.class);
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
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            if (flieName.indexOf("债券估值行情导入") != -1) {
                List<List<String>> dataList = dataAnalysis(flieName);
                if (null != dataList && dataList.size() > 0) {
                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = checkExcelData(dataList);
//                    if (checkResult.isEmpty()) {
//                        List<ComBondMarketInfo001Dto> equityList = converToBean(dataList);
//                        // 保存，已存在则更新
//                        dealAfterCheck(equityList);
//                    }
                    return checkResult;
                }
            }
            else {
                List<ExcelValidDto> checkResult = new ArrayList<>();
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                		"文件名称错误，请下载模板后上传"));
                return checkResult;
            }
        }
        return null;
    }
    
    /**
     * excel导入数据整理
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<List<String>> dataAnalysis(String flieName)
        throws Exception {
    	String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.startsWith(flieName, prefix)
            && !StringUtil.startsWith(flieName, prefix.replace("/", File.separator))) {
     	   flieName = prefix + File.separator + flieName;
       }
         ExcelUtil excelUtil = ExcelUtil.build(flieName, null);
         List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
        return dataList;
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
        List<ComBondMarketInfo001Dto> equityList = converToBean(dataList,null);
        ComBondMarketInfo001Dto ComBondMarketInfo001Dto = null;
        List<Integer> duplicateDataList=new ArrayList<Integer>();
        if (equityList.size() >= 1) {
            for (int i = 0; i < equityList.size(); i++ ) {
                ComBondMarketInfo001Dto = equityList.get(i);
                // 检查表内数据每行是否合法
                if (checkExcelCow(i, dataList.get(i)) != null) {
                    checkResult.addAll(checkExcelCow(i, dataList.get(i)));
                }
                if(ComBondMarketInfo001Dto.getSecId() == null
                		|| ComBondMarketInfo001Dto.getValueDate() == null
                		|| ComBondMarketInfo001Dto.getValueSource() == null
                		|| duplicateDataList.contains(i)){
                	continue;
                }
                // 检查表内数据是否有主键重复
                if (equityList.size() >= 2) {
                    for (int k = i + 1; k < equityList.size(); k++ ) {
                        if(equityList.get(k).getSecId() == null
                        		|| equityList.get(k).getValueDate() == null
                        		|| equityList.get(k).getValueSource() == null){
                        	continue;
                        }
                        if (equityList.get(k).getSecId().equals(ComBondMarketInfo001Dto.getSecId())
                            && equityList.get(k).getValueDate().equals(
                                ComBondMarketInfo001Dto.getValueDate())
                            && equityList.get(k).getValueSource().equals(
                                ComBondMarketInfo001Dto.getValueSource())) {
                        	if(!duplicateDataList.contains(k)){
                        		duplicateDataList.add(k);
                        		checkResult.add(new ExcelValidDto(dataList.get(k).get(1),
                                        String.valueOf(k + 2), "表中数据重复"));
                        	}
                        }
                    }
                }
            }
        }
        return checkResult;
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
    public List<ComBondMarketInfo001Dto> converToBean(List<List<String>> dataList,List<Integer> checkResult)
        throws ParseException {
        // 返回的list定义
        List<ComBondMarketInfo001Dto> returnInfoList = new ArrayList<ComBondMarketInfo001Dto>();
        ComBondMarketInfo001Dto ComBondMarketInfo001Dto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
        	if(null !=checkResult && checkResult.contains(i)){
        		continue;
        	}
            List<String> list = dataList.get(i);
            ComBondMarketInfo001Dto = new ComBondMarketInfo001Dto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                ComBondMarketInfo001Dto.setSecId(list.get(0).trim());
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                ComBondMarketInfo001Dto.setFinprodAbbr(list.get(1).trim());
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                ComBondMarketInfo001Dto.setValueSource(list.get(2).trim().split("：")[0].trim());
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                ComBondMarketInfo001Dto.setValueDate(DateUtil.parse(list.get(3).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                ComBondMarketInfo001Dto.setLastPeriod(new BigDecimal(list.get(4).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                ComBondMarketInfo001Dto.setPrice(new BigDecimal(list.get(5).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(6))) {
                ComBondMarketInfo001Dto.setNetprice(new BigDecimal(list.get(6).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(7))) {
                ComBondMarketInfo001Dto.setmDuration(new BigDecimal(list.get(7).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(8))) {
                ComBondMarketInfo001Dto.setmConvexity(new BigDecimal(list.get(8).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(9))) {
                ComBondMarketInfo001Dto.setBpvalue(new BigDecimal(list.get(9).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(10))) {
                ComBondMarketInfo001Dto.setSduration(new BigDecimal(list.get(10).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(11))) {
                ComBondMarketInfo001Dto.setScnvxty(new BigDecimal(list.get(11).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(12))) {
                ComBondMarketInfo001Dto.setInterestDuration(new BigDecimal(list.get(12).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(13))) {
                ComBondMarketInfo001Dto.setInterestCnvxty(new BigDecimal(list.get(13).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(14))) {
                ComBondMarketInfo001Dto.setBpyield(
                    new BigDecimal(list.get(14).trim()).divide(new BigDecimal(100)));
            }
            // Date date = new Date();
            ComBondMarketInfo001Dto.setCreateUser(CommonUtil.getUserName());
            ComBondMarketInfo001Dto.setCreateDept(CommonUtil.getDeptCode());
            // ComBondMarketInfo001Dto.setCreateTime(date);
            ComBondMarketInfo001Dto.setUpdateUser(CommonUtil.getUserName());
            // ComBondMarketInfo001Dto.setUpdateTime(date);
            returnInfoList.add(ComBondMarketInfo001Dto);
        }
        return returnInfoList;
    }

    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i 第几行
     * @param list 每一列解析后的List
     * @return 检查结构
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtolist = new ArrayList<ExcelValidDto>();
        // 列数为15
        if (null == list || list.size() != 15) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i), "数据列数错误");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(0))) {
            // 验证导入债券代码是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "债券代码为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(2))) {
            // 验证导入估值来源是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "估值来源为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(3))) {
            // 验证导入估值日期是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "估值日期为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(5))) {
            // 验证导入市价全价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "市价全价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(6))) {
            // 验证导入市价净价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "市价净价为空");
            excelValidDtolist.add(dto);
        }
        return excelValidDtolist;
    }

    /**
     * check后的保存
     * 
     * @param equityList 需要保存的数据
     * @see
     * @since 1.0
     */
    public void dealAfterCheck(List<ComBondMarketInfo001Dto> equityList) {
        restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComBondMarketInfo001/mergeInfoList",
            equityList, Integer.class);
    }
    
    /**
     * 导入文件重复数据检查 <功能详细描述>
     * 
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    public List<Integer> duplicateCheck(List<List<String>> dataList)
        throws ParseException {
    	List<ComBondMarketInfo001Dto> equityList = converToBean(dataList,null);
    	List<Integer> duplicateData=new ArrayList<Integer>();
      for (int i = 0; i < dataList.size(); i++ ) {
      	ComBondMarketInfo001Dto valBondQuotationInfoDto = equityList.get(i);
      	if(valBondQuotationInfoDto.getSecId() == null
        		|| valBondQuotationInfoDto.getValueDate() == null
        		|| valBondQuotationInfoDto.getValueSource() == null
        		|| duplicateData.contains(i)){
      		continue;
        }
          // 检查表内数据是否有主键重复
          for (int k = i + 1; k < dataList.size(); k++ ) {
        	  if(equityList.get(k).getSecId() == null
              		|| equityList.get(k).getValueDate() == null
              		|| equityList.get(k).getValueSource() == null){
        		  continue;
              }
              if (equityList.get(k).getSecId().equals(
              		valBondQuotationInfoDto.getSecId())
                      && equityList.get(k).getValueSource().equals(valBondQuotationInfoDto.getValueSource())
                      && equityList.get(k).getValueDate().equals(valBondQuotationInfoDto.getValueDate())) {
              	if(!duplicateData.contains(k)){
              		duplicateData.add(k);
              	}
              }
          }
      }
      if(duplicateData.isEmpty()){
    	  duplicateData.add(-1);
      }
      return duplicateData;
    }
    
}
