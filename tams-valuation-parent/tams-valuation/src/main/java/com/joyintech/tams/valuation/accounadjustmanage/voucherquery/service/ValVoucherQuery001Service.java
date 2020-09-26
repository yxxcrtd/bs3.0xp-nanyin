/*
 * 文件名：ValVoucherQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.voucherquery.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQueryPrintDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 记账凭证查询service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValVoucherQuery001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValVoucherQuery001Service extends CommonExportService<ValVoucherQuery001Dto> {
	
    /**
     * ValVoucherQuery001Mapper
     */
//    @Autowired
//	private ValVoucherQuery001Mapper mapper;
	
	/**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/getBooksetId", SelectOptionDto.class);
        
//    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
//    	List<ValAssetbook001Dto> listBookset = mapper.getBooksetId();
//    	listBookset.forEach(item -> {
//    	    listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
//    	});
//        return listSelectDto;
    }

    /**
     * 业务类别下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBusiClass() {
        
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/getBusiClass", SelectOptionDto.class);
        
//    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
//    	List<ValTablelist001Dto> listTable = mapper.getBusiClass();
//    	listTable.forEach(item -> {
//            listSelectDto.add(createSelect(item.getTableId(), item.getTableName()));
//        });
//        return listSelectDto;
    }
    
    /**
     * 格式化创建SelectOptionDto对象
     * <功能详细描述>
     * @param selectId 下拉Id值
     * @param text	下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text){
    	SelectOptionDto dto=new SelectOptionDto();
    	dto.setId(selectId);
    	dto.setText(text);
    	return dto;
    }

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValVoucherQuery001Dto> findPage(ValVoucherQuery001SearchDto dto) throws Exception {
//        ValVoucherQuery001SearchDto dto = (ValVoucherQuery001SearchDto)JsonUtils.map2entity(params, ValVoucherQuery001SearchDto.class);
//
//        String tableId = (String) params.get("tableId");
//        String[] tableIds = null;
//        if(StringUtil.isNotEmpty(tableId)){
//            tableIds = tableId.split(",");
//        }
//        
//        String booksetId = (String) params.get("booksetId");
//        String[] booksetIdArr = null;
//        if(StringUtil.isNotEmpty(booksetId)){
//        	booksetIdArr = booksetId.split(",");
//        }
//        
//        dto.setBooksetIdArr(booksetIdArr);
//        dto.setTableIds(tableIds);
        Map<String , ValVoucherQuery001SearchDto> param = new HashMap<String, ValVoucherQuery001SearchDto>();
        param.put("dto", dto);
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/findPage", param, ValVoucherQuery001Dto.class);
//        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
//    	return super.queryForPage(()-> mapper.queryPageList(dto));
    }
    
    /**
     * 
     * 根据条件查询全部信息
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<ValVoucherQuery001Dto> selectAll(ValVoucherQuery001SearchDto dto){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/selectAll", dto, ValVoucherQuery001Dto.class); 
//        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
//    	return mapper.queryPageList(dto);
    }

    /**
     * 凭证打印
     * @param param
     * @return
     */
    public ValVoucherQueryPrintDto printVoucher(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/findPrintInfo",
            param, ValVoucherQueryPrintDto.class);
    }
}
