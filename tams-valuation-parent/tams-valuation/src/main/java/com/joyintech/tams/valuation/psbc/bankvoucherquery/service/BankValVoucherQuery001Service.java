/*
 * 文件名：ValVoucherQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.psbc.bankvoucherquery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.service.ValVoucherQuery001Service;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.psbc.bankvoucherquery.dao.BankValVoucherQuery001Mapper;
import com.joyintech.tams.valuation.psbc.bankvoucherquery.dto.BankValVoucherQuery001Dto;
import com.joyintech.tams.valuation.psbc.bankvoucherquery.dto.BankValVoucherQuery001SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BankValVoucherQuery001Service extends CommonExportService<BankValVoucherQuery001Dto> {
	
    /**
     * ValVoucherQuery001Mapper
     */
    @Autowired
	private BankValVoucherQuery001Mapper mapper;
	
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {  
      List<SelectOptionDto> listSelectDto = mapper.getBooksetId(); 
        return listSelectDto;
    }


    
	/**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getTableId() { 
    	List<SelectOptionDto> listSelectDto=mapper.getTableId(); 
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
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValVoucherQuery001/getBusiClass", SelectOptionDto.class);
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
    public Page<BankValVoucherQuery001Dto> findPage(BankValVoucherQuery001SearchDto dto) throws Exception {
        Map<String , BankValVoucherQuery001SearchDto> param = new HashMap<String, BankValVoucherQuery001SearchDto>();
        param.put("dto", dto); 
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
        dto.setBooksetType2(DictCodeList.账套类别_银行账套.getValue());
        Page<BankValVoucherQuery001Dto> page = super.queryForPage(()-> mapper.queryPageList(dto));
        for (BankValVoucherQuery001Dto valdto : page.getRoot()) {
            valdto.setTaskStatus(SysDataUtil.getNameFromDataDict(DictCode.发送核心状态, valdto.getTaskStatus()));
        }
        return page;
    }
    
    /**
     * 
     * 根据条件查询全部信息
     * @param params 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<BankValVoucherQuery001Dto> selectAll(BankValVoucherQuery001SearchDto dto){
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
        dto.setBooksetType2(DictCodeList.账套类别_银行账套.getValue());
        List<BankValVoucherQuery001Dto> list = mapper.queryPageList(dto);
        for (BankValVoucherQuery001Dto valdto : list) {
            valdto.setTaskStatus(SysDataUtil.getNameFromDataDict(DictCode.发送核心状态, valdto.getTaskStatus()));
        }
        return mapper.queryPageList(dto);
    }
}
