package com.joyintech.tams.valuation.assets.posit.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 存款类交易
 * 
 * @author HuangJian
 * @version 1.0 2018年10月30日
 * @see ValDespositAssetQuery002Service
 * @since 1.0
 */
@Service

public class ValDespositAssetQuery002Service extends CommonExportService<ValDespositAssetQuery002Dto> implements 
        BaseProcessService<ValDespositAssetQuery002Dto> {



    /**
     * valDespositAssetQuery002Mapper
     */
//    @Autowired
//    private ValDespositAssetQuery002Mapper valDespositAssetQuery002Mapper;


    /**
     * 根据查询finprodId单个详细信息
     * 
     * @param map Map<String, Object>
     * @return ValDespositAssetQuery002Dto
     * @see
     * @since 1.0
     */
    public ValDespositAssetQuery002Dto fingByfinprodId(Map<String, Object> map) {
    	
    	ValDespositAssetQuery002Dto valDespositAssetQuery002Dto =restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery002/fingByfinprodId",
    			map,ValDespositAssetQuery002Dto.class);
    	return valDespositAssetQuery002Dto;
//        return valDespositAssetQuery002Mapper.selectByfinprodId(map);
    }


    /**
     * 利率表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    public List<DateTableDto> getTable1(String finprodId) {
    	return restTemplate.getForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery002/getTable1?finprodId="+finprodId,
                DateTableDto.class);
    	
    	
//        return valDespositAssetQuery002Mapper.getTable1(finprodId);
    }

    /**
     * 付息计划表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    /** modify by dongjun on 20181203 start**/
    public List<DateTableDto> getTable2(Map<String, Object> map) {
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery002/getTable2",
                map,DateTableDto.class);
    	
//        return valDespositAssetQuery002Mapper.getTable2(map);
    }

    /**
     * 还本计划表初始化
     * 
     * @param finprodId finprodId
     * @return List<DateTableDto>
     * @see
     * @since 1.0
     */
    public List<DateTableDto> getTable3(Map<String, Object> map) {
    	
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery002/getTable3",
                map,DateTableDto.class);
//        return valDespositAssetQuery002Mapper.getTable3(map);
    }
    /** modify by dongjun on 20181203 end**/
    
 
    
    /**
     * 表格新建
     * 
     * @param table2 List<DateTableDto>
     * @param dto DateTableDto
     * @return DateTableDto
     * @see
     * @since 1.0
     */
    public DateTableDto setDateTableDto(List<DateTableDto> table2,
                                        ValDespositAssetQuery002Dto dto) {
        DateTableDto table = new DateTableDto();
        return table;

    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * 
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++ ) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            }
            else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValDespositAssetQuery002Dto entity, OperationType type) {
        // 删除
        if (OperationType.DELETE.equals(type)) {
//            valDespositAssetQuery001Service.delete(entity.getFinprodId());
            restTemplate.getForObject(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery001/delete?finprodId="+entity.getFinprodId(),
                     Integer.class);
        }
        else {
//            this.doCommit(entity);
        	restTemplate.postForObject(
    	            ServiceApplication.VALUATION_SERVICE_NAME + "/ValDespositAssetQuery002/doCommit", entity,
    	            Integer.class);
        }
    }

    /**
     * 获取交易日历下拉框
     * @param map map
     * @return ValRepurchaseAssetQuery001Dto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCalendarInfoList() {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValRepurchaseAssetQuery002/getCalendarInfoList",
                null, SelectOptionDto.class);
    }
}