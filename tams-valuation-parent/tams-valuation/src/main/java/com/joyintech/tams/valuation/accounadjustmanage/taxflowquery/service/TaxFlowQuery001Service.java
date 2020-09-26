/*
 * 文件名：TaxFlowQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dao.TaxFlowQuery001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.taxflowquery.dto.TaxFlowQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
/**
 * 计税流水表查询service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年9月14日
 * @see TaxFlowQuery001Service
 * @since 1.0
 */
@Service
public class TaxFlowQuery001Service  extends CommonExportService<TaxFlowQuery001Dto>{
	
//	@Autowired
//	private TaxFlowQuery001Mapper taxFlowQuery001Mapper;

	public Page<TaxFlowQuery001Dto> selectForPage(Map<String, Object> params)
			 throws Exception {
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/TaxFlowQuery001/findPage", params, TaxFlowQuery001Dto.class);
//		 return super.queryForPage(() -> taxFlowQuery001Mapper.queryList(dto));
	}
	
	/**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
        
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/TaxFlowQuery001/getBooksetId", SelectOptionDto.class);
//    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
//    	List<ValAssetbook001Dto> listBookset = taxFlowQuery001Mapper.getBooksetId();
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
//    	dto.setObject(DateUtil.format(new Date(), "yyyy-MM-dd"));
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
	 public List<TaxFlowQuery001Dto> findPageout(TaxFlowQuery001Dto dto) throws Exception {
	     return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/TaxFlowQuery001/findPageout",dto, TaxFlowQuery001Dto.class);
//	        return  taxFlowQuery001Mapper.queryList(dto);
	    }

}
