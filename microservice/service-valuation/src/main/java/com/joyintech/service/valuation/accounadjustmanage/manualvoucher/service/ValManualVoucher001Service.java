/*
 * 文件名：ValManualVoucher001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dao.ValManualVoucher001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001SearchDto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.tams.common.base.dao.BokBookdetailCurrentMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 手工凭证查询service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher001Service
 * @since 2.0
 */
@Service(value="serviceValManualVoucher001Service")
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucher001Service extends CommonExportService<ValManualVoucher001Dto> implements BaseProcessService<ValManualVoucher001Dto> {
	
    /**
     * ValManualVoucher001Mapper
     */
    @Autowired
	private ValManualVoucher001Mapper mapper;
    
    @Autowired
    private BokBookdetailCurrentMapper bokBookdetailCurrentMapper;
    
    @Autowired
    private ValManualVoucher003Service valManualVoucher003Service;
	
	/**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
    	List<ValAssetbook001Dto> listBookset = mapper.getBooksetId();
    	listBookset.forEach(item -> {
    	    listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
    	});
        return listSelectDto;
    }

    /**
     * 业务类别下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBusiClass() {
    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
    	List<ValTablelist001Dto> listTable = mapper.getBusiClass();
    	listTable.forEach(item -> {
            listSelectDto.add(createSelect(item.getTableId(), item.getTableName()));
        });
        return listSelectDto;
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
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValManualVoucher001Dto> findPage(int page,int length,ValManualVoucher001SearchDto dto) throws Exception {
    	String booksetId = dto.getBooksetId();
        String[] bookSetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
        	bookSetIdArr = booksetId.split(",");
        	dto.setBooksetIdArr(bookSetIdArr);
        }
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
    	return super.queryForPage(page,length,()-> mapper.queryPageList(dto));
    }
    
    /**
     * 
     * 根据条件查询全部信息
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<ValManualVoucher001Dto> selectAll(ValManualVoucher001SearchDto dto){
        String booksetId = dto.getBooksetId();
        String[] bookSetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
            bookSetIdArr = booksetId.split(",");
            dto.setBooksetIdArr(bookSetIdArr);
        }
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
    	return mapper.queryPageList(dto);
    }
    
    public int deleteList(List<ValManualVoucher001Dto> listDto){
        int count = 0;
        for(ValManualVoucher001Dto item : listDto){
            count += bokBookdetailCurrentMapper.deleteByPrimaryKey(item.getSeqno());
        }
        return count;
    }

    /**
     * 流程审核完成
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValManualVoucher001Dto entity, OperationType type) {
        // 删除操作
        if(OperationType.DELETE.equals(type)) {
            valManualVoucher003Service.deleteVouch(entity);
        }
    }
}
