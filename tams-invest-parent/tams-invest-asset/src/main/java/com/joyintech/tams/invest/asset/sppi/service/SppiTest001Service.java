/*
 * 文件名：SppiTest001Service
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020-10-12
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.asset.sppi.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.asset.sppi.dto.SppiImportDto;
import com.joyintech.tams.invest.asset.sppi.dto.SppiTest001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SPPI测试查询服务
 *
 * @author Jiaoxujin
 * @version 1.0 2020-10-12
 */
@Service
public class SppiTest001Service extends CommonExportService<SppiTest001Dto> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    /**
     * 分页查询SPPI测试结果列表
     * @param pageParam Map&lt;String, Object&gt;
     * @return Page&lt;SppiTest001Dto&gt;
     */
    public Page<SppiTest001Dto> findPage(@RequestBody Map<String, Object> pageParam) {
        Page<SppiTest001Dto> pageData = restTemplate.postForPage(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest001/findPage", pageParam,
                SppiTest001Dto.class);
        return pageData;
    }

    /**
     * 查询金融产品有效交易数
     * @param finprodId
     * @return int
     */
    public int queryValidTradeCount(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME +
                "/SppiTest002/queryValidTradeCount?finprodId=" + finprodId, Integer.class);
    }

    /**
     * 查询适用于SPPI测试的资产标的列表<br />
     * 仅能查询出债券类资产、项目类资产、计划类利率型资产
     * @param paramMap Map&lt;String, Object&gt;
     * @return List&lt;SelectOptionDto&gt;
     */
    public List<SelectOptionDto> querySppiAssetList(@RequestBody Map<String, Object> paramMap) {
        return restTemplate.postForList(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest001/querySppiAssetList", paramMap,
                SelectOptionDto.class);
    }

    /**
     * 导出重写方法
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/asset/sppi/conf/SppiTest001_config.xml");
    }

    /**
     *
     * 导出重写方法
     */
    @Override
    public Page<SppiTest001Dto> selectPageData(Map<String, Object> selectParam) {
        Page<SppiTest001Dto> pageData = new Page<>();
        String selectedRows = MapUtils.getString(selectParam, "selectedRows");

        // 判断是导出选中项，还是导出全部
        if (StringUtils.isNotBlank(selectedRows)) {
            try {
                String pageSize = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_LENGTH), "20");
                List<SppiTest001Dto> dataList = JsonUtils.json2List(selectedRows, SppiTest001Dto.class);
                pageData.setRoot(dataList);
                pageData.setRecords(dataList.size());
                pageData.setTotal((int) Math.ceil(dataList.size() * 1.0d / Integer.parseInt(pageSize)));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        else {
            // 如果是导出全部
            //selectParam.put(GlobalConstant.PARAM_PAGE, 1);
            //selectParam.put(GlobalConstant.PARAM_LENGTH, Integer.MAX_VALUE);
            pageData = findPage(selectParam);
        }

        return pageData;
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
    	Map<String,Object> param = new HashMap<String,Object>();
    	List<List<String>> dataList = ExcelUtil.getDataList(fileName);
    	//String orgCode = CommonUtil.getOrgCode();
    	//String deptCode = CommonUtil.getDeptCode();
    	AjaxResult result = new AjaxResult();
        if (!StringUtil.isEmptyList(dataList)) {
            if (CollectionUtils.isNotEmpty(dataList)) {
                List<SppiImportDto> entities = convertToBean(dataList);
                List<SppiImportDto> data = new ArrayList<SppiImportDto>();
                if(entities.size()>1000){
                	List<SppiImportDto> newDataList = new ArrayList<SppiImportDto>();
                	newDataList.addAll(entities);
                	int part = entities.size() / 1000; // 分批数
                    for (int i = 0; i < part; i++ ) {
                    	data.addAll(getBondId(newDataList.subList(0, 1000)));
                        newDataList.subList(0, 1000).clear();
                    }
                    // 最后剩下的数据
                    if (CollectionUtils.isNotEmpty(newDataList)) {
                    	data.addAll(getBondId(newDataList));
                    }
                }
                else{
                    data = getBondId(entities);
                }
                if (data != null && data.size() > 0) {
                    Map<String, List<SppiImportDto>> equityInfo001Map = entities.stream().collect(
                        JoyinCollectors.groupingBy(
                            x -> x.getBondId()));
                    // 将序号放入list中
                    List<String> excelNumList = new ArrayList<String>();
                    List<Integer> excelNumIntList = new ArrayList<Integer>();
                    for (SppiImportDto string : data) {
                        List<SppiImportDto> listTemp = equityInfo001Map.get(string.getBondId());
//                        excelNumList.add(listTemp.get(0).getExcelNum());
                        excelNumIntList.add(Integer.valueOf(listTemp.get(0).getExcelNum()));
                    }
                    // 将list进行排序，并转化为string
                    Collections.sort(excelNumIntList);
                    for (Integer rowNo : excelNumIntList) {
                        excelNumList.add(String.valueOf(rowNo));
                    }
                    result.setMessage(
                        this.getMessage("SPPI_BONID_EQU",
                            StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
                        MessageType.确认消息);
                }
                else {
                    // 防止在进行导入前check时进行了两次的弹框
                    result.setData(NO_INFO_SHOWOUT);
                }
            }
            else{
             // 防止在进行导入前check时进行了两次的弹框
             result.setData(NO_INFO_SHOWOUT);
            }
        }else {
            result.setMessage("文件内容无有效数据！",
                    MessageType.错误消息);
        }
        return result;
    }
    
    /**
     * excel数据转为java对象
     * <p> 将excel导入的数据，按行转为ProRegistCodeManage001Dto对象，同时验证各字段值</p>
     *
     * @param dataList excel导入的数据
     * @return 返回list
     */
    private List<SppiImportDto> convertToBean(List<List<String>> dataList) {
        List<SppiImportDto> entities = new ArrayList<SppiImportDto>();
        for (int i = 0; i < dataList.size(); i++ ) {
            List<String> list = dataList.get(i);
            SppiImportDto entity = new SppiImportDto();
            entity.setBondId(list.get(0));
            entity.setBondAbbr(list.get(1));
            entity.setIsSppiPass(list.get(2));
            entity.setReason(list.get(3));
            entity.setReason(list.get(3));
            entity.setBidPriceCon(list.get(4));
            entity.setCostValue(list.get(5));
            entity.setOtherCondition(list.get(6));
            entity.setExcelNum(String.valueOf(i+2));
            entities.add(entity);
        }
        return entities;
    }
    
    
    /**
     * 查询债券ID是否重复
     * @param list
     * @return
     */
    public List<SppiImportDto> getBondId(List<SppiImportDto> list){
    	return restTemplate.postForList(
                ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest001/getBondId", list,
                SppiImportDto.class);
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
     	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("dataList", ExcelUtil.getDataList(fileName));
    	param.put("tempList", ExcelUtil.getTempList(fileName));
    	param.put("orgCode", CommonUtil.getOrgCode());
    	param.put("deptCode", CommonUtil.getDeptCode());
    	param.put("bankCode", CommonUtil.getUserBankCode());
    	return restTemplate.postForList(
    			ServiceApplication.ASSET_SERVICE_NAME + "/SppiTest001/importData", 
	       		param,
	       		ExcelValidDto.class);
    }
}
