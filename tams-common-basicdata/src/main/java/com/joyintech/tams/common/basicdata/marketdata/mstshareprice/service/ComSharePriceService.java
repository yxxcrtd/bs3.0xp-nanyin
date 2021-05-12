/*
 * 文件名：MstSharePriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstshareprice.service;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 股票行情Service
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePriceService
 * @since 1.0
 */
@Service
public class ComSharePriceService extends CommonExportService<ComSharePriceDto> implements BaseProcessService<ComSharePriceDto>{

    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<ComSharePriceDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.CommonBasicDataService + "/ComSharePrice/selectForPage",
            pageParam, ComSharePriceDto.class);
    }
    
    /**
     * 获取下拉选资产代码
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.CommonBasicDataService
                                       + "/ComSharePrice/getFinprodIdForAdd",map,SelectOptionDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/common/basicdata/marketdata/mstshareprice/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 
     * 导出数据查询
     */
    @Override
    public Page<ComSharePriceDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(ComSharePriceDto entity, OperationType type) {
        restTemplate.postForObject(ServiceApplication.CommonBasicDataService+"/ComSharePrice/doComplete/" + type, entity, Integer.class);
    }

    /**
     * 通过id获取最近指定日期的股票行情信息   
     * @param pageParam
     * @return
     */
	public ComSharePriceDto getLatelyShareById(Map<String, Object> pageParam) {
		return restTemplate.postForObject(ServiceApplication.CommonBasicDataService
                + "/ComSharePrice/getLatelyShareById",pageParam, ComSharePriceDto.class);
}

	/**
	 * 获取所有查询结果list
	 * @param pageParam
	 * @return
	 */
	public List<ComSharePriceDto> selectAllList(Map<String, Object> pageParam) {
		return restTemplate.postForList(ServiceApplication.CommonBasicDataService
                + "/ComSharePrice/selectAllList",pageParam, ComSharePriceDto.class);
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
            if (flieName.indexOf("股票行情信息导入") != -1) {
                List<List<String>> dataList = dataAnalysis(flieName);
                if (null != dataList && dataList.size() > 0) {
                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = checkExcelData(dataList);
//                    if (checkResult.isEmpty()) {
//                        List<ComSharePriceDto> equityList = converToBean(dataList);
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
        List<ComSharePriceDto> equityList = converToBean(dataList,null);
        ComSharePriceDto ComSharePriceDto = null;
        List<Integer> duplicateDataList=new ArrayList<Integer>();
        if (equityList.size() >= 1) {
            for (int i = 0; i < equityList.size(); i++ ) {
                ComSharePriceDto = equityList.get(i);
                // 检查表内数据每行是否合法
                if (checkExcelCow(i, dataList.get(i)) != null) {
                    checkResult.addAll(checkExcelCow(i, dataList.get(i)));
                }
                if(ComSharePriceDto.getSharesId() == null
                		|| ComSharePriceDto.getDealDate() == null
                		|| duplicateDataList.contains(i)){
                	continue;
                }
                // 检查表内数据是否有主键重复
                if (equityList.size() >= 2) {
                    for (int k = i + 1; k < equityList.size(); k++ ) {
                        if(equityList.get(k).getSharesId() == null
                        		|| equityList.get(k).getDealDate() == null){
                        	continue;
                        }
                        if (equityList.get(k).getSharesId().equals(ComSharePriceDto.getSharesId())
                            && equityList.get(k).getDealDate().equals(
                                ComSharePriceDto.getDealDate())) {
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
    public List<ComSharePriceDto> converToBean(List<List<String>> dataList,List<Integer> checkResult)
        throws ParseException {
        // 返回的list定义
        List<ComSharePriceDto> returnInfoList = new ArrayList<ComSharePriceDto>();
        ComSharePriceDto ComSharePriceDto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
        	if(null !=checkResult && checkResult.contains(i)){
        		continue;
        	}
            List<String> list = dataList.get(i);
            ComSharePriceDto = new ComSharePriceDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                ComSharePriceDto.setSharesId(list.get(0).trim());
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                ComSharePriceDto.setFinprodAbbr(list.get(1).trim());
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                ComSharePriceDto.setsShares(new BigDecimal(list.get(2).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                ComSharePriceDto.setcShares(new BigDecimal(list.get(3).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                ComSharePriceDto.setDealDate(DateUtil.parse(list.get(4).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                ComSharePriceDto.setPreClosePrice(new BigDecimal(list.get(5).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(6))) {
                ComSharePriceDto.setOpenPrice(new BigDecimal(list.get(6).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(7))) {
                ComSharePriceDto.setHighPrice(new BigDecimal(list.get(7).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(8))) {
                ComSharePriceDto.setLowPrice(new BigDecimal(list.get(8).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(9))) {
                ComSharePriceDto.setClosePrice(new BigDecimal(list.get(9).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(10))) {
                ComSharePriceDto.setChange(new BigDecimal(list.get(10).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(11))) {
                ComSharePriceDto.setChangePercent(new BigDecimal(list.get(11).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(12))) {
                ComSharePriceDto.setDealValume(new BigDecimal(list.get(12).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(13))) {
                ComSharePriceDto.setDealAmount(new BigDecimal(list.get(13).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(14))) {
                ComSharePriceDto.setAvgPrice(new BigDecimal(list.get(14).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(15))) {
                ComSharePriceDto.setPreClosePriceAdj(new BigDecimal(list.get(15).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(16))) {
                ComSharePriceDto.setOpenPriceAdj(new BigDecimal(list.get(16).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(17))) {
                ComSharePriceDto.setHighPriceAdj(new BigDecimal(list.get(17).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(18))) {
                ComSharePriceDto.setLowPriceAdj(new BigDecimal(list.get(18).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(19))) {
                ComSharePriceDto.setClosePriceAdj(new BigDecimal(list.get(19).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(20))) {
                ComSharePriceDto.setFactorAdj(new BigDecimal(list.get(20).trim()));
            }
            if (StringUtil.isNotEmpty(list.get(21))) {
                ComSharePriceDto.setTradeStatus(list.get(21).trim().split("：")[0].trim());
            }
            // Date date = new Date();
            ComSharePriceDto.setCreateUser(CommonUtil.getUserName());
            ComSharePriceDto.setCreateDept(CommonUtil.getDeptCode());
            // ComSharePriceDto.setCreateTime(date);
            ComSharePriceDto.setUpdateUser(CommonUtil.getUserName());
            // ComSharePriceDto.setUpdateTime(date);
            returnInfoList.add(ComSharePriceDto);
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
        // 列数为22
        if (null == list || list.size() != 22) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i), "数据列数错误");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(0))) {
            // 验证导入资产代码是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "资产代码为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(1))) {
            // 验证导入资产名称是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "资产名称为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(2))) {
            // 验证导入总股本是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "总股本为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(3))) {
            // 验证导入流通股本是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "流通股本为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(4))) {
            // 验证导入交易日期是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "交易日期为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(5))) {
            // 验证导入昨收盘价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "昨收盘价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(6))) {
            // 验证导入开盘价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "开盘价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(7))) {
            // 验证导入最高价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "最高价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(8))) {
            // 验证导入最低价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "最低价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(9))) {
            // 验证导入收盘价是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "收盘价为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(12))) {
            // 验证导入成交量是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "成交量为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(13))) {
            // 验证导入成交金额是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "成交金额为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(21))) {
            // 验证导入交易状态是否为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "交易状态为空");
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
    public void dealAfterCheck(List<ComSharePriceDto> equityList) {
        restTemplate.postForObject(
            ServiceApplication.CommonBasicDataService + "/ComSharePrice/mergeInfoList",
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
    	List<ComSharePriceDto> equityList = converToBean(dataList,null);
    	List<Integer> duplicateData=new ArrayList<Integer>();
      for (int i = 0; i < dataList.size(); i++ ) {
      	ComSharePriceDto ComSharePriceDto = equityList.get(i);
      	if(ComSharePriceDto.getSharesId() == null
        		|| ComSharePriceDto.getDealDate() == null
        		|| duplicateData.contains(i)){
      		continue;
        }
          // 检查表内数据是否有主键重复
          for (int k = i + 1; k < dataList.size(); k++ ) {
        	  if(equityList.get(k).getSharesId() == null
              		|| equityList.get(k).getDealDate() == null){
        		  continue;
              }
              if (equityList.get(k).getSharesId().equals(
              		ComSharePriceDto.getSharesId())
                      && equityList.get(k).getDealDate().equals(ComSharePriceDto.getDealDate())) {
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
