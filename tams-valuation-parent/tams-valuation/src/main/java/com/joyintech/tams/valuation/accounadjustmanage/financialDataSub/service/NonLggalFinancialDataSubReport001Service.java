/*
 * 文件名：NonLggalFinancialDataSubReport001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanwgei1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.dao.NonLggalFinancialDataSubReport001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.financialDataSub.dto.NonLggalFinancialDataSubReport001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
/**
 * 非法人财务数据报送service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangwei1
 * @version 1.0 2018年11月20日
 * @see NonLggalFinancialDataSubReport001Service
 * @since 1.0
 */
@Service
public class NonLggalFinancialDataSubReport001Service  extends CommonExportService<NonLggalFinancialDataSubReport001Dto>{
	
	@Autowired
	private NonLggalFinancialDataSubReport001Mapper NonLggalFinancialDataSubReport001Mapper;

	public Page<NonLggalFinancialDataSubReport001Dto> selectForPage(Map<String, Object> params)
			 throws Exception {
	     return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/NonLggalFinancialDataSubReport001/findPage", params, NonLggalFinancialDataSubReport001Dto.class);
//		 return super.queryForPage(() -> NonLggalFinancialDataSubReport001Mapper.queryList(dto));
	}
	
	/**
     * 产品下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/NonLggalFinancialDataSubReport001/getBooksetId", SelectOptionDto.class);
//    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
//    	List<ValAssetbook001Dto> listBookset = NonLggalFinancialDataSubReport001Mapper.getBooksetId();
//    	listBookset.forEach(item -> {
//    	    listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
//    	});
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
	 * 导出功能
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @throws Exception
	 */
	 public List<NonLggalFinancialDataSubReport001Dto> findPageout(NonLggalFinancialDataSubReport001Dto dto) throws Exception {
	     return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/NonLggalFinancialDataSubReport001/findPageout", dto, NonLggalFinancialDataSubReport001Dto.class);
//	        return  NonLggalFinancialDataSubReport001Mapper.queryList(dto);
	    }

}
