/*
 * 文件名：ValOptInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valoptinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valoptinfo.dao.ValOptInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductTypeKey;
import com.joyintech.tams.common.base.entity.FinTradeProductAddKey;
import com.joyintech.tams.common.base.entity.FinTradeProductKey;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 场外期权标的Service
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOptInfo001Service
 * @since 1.0
 */
@Service(value="serviceValOptInfo001Service")
public class ValOptInfo001Service extends CommonExportService<ValOptInfo001Dto> implements BaseProcessService<ValOptInfo001Dto>{
	
    /**
     * ValOptInfo001Mapper
     */
    @Autowired
    private ValOptInfo001Mapper mapper;
    
    /**
     * FinTradeProductMapper
     */
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    
    /**
     * FinTradeProductAddMapper
     */
    @Autowired
    private FinTradeProductAddMapper finTradeProductAddMapper;
    
    /**
     * FinProductTypeMapper
     */
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValOptInfo001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
    	params.put("FINPROD_TYPE_TG_F35", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        return super.queryForPage(page,length,()->mapper.findPage(params));
    }
    
    /**
     * 获取信息
     * 
     * @param params 筛选条件
     * @return 信息
     * @throws Exception 
     */
    public List<ValOptInfo001Dto> findByTargetId(Map<String, Object> params) throws Exception {
    	params.put("FINPROD_TYPE_TG_F35", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        return mapper.findPage(params);
    }
	
    /**
     * 交易编号联想框
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> findFinprodId (Map<String,Object> map) {
    	map.put("FINPROD_TYPE_TG_F35", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        return mapper.findFinprodId(map);
    }
    
	@Override
	public void doComplete(ValOptInfo001Dto entity, OperationType type) {
		if(OperationType.DELETE.equals(type)) {
			FinTradeProductKey finTradeProductKey = new FinTradeProductKey();
			finTradeProductKey.setFinprodId(entity.getFinprodId());
			finTradeProductKey.setBranch(InterfCommonConstant.分支序号_默认);
			finTradeProductMapper.deleteByPrimaryKey(finTradeProductKey);
			
			FinTradeProductAddKey finTradeProductAddKey = new FinTradeProductAddKey();
			finTradeProductAddKey.setFinprodId(entity.getFinprodId());
			finTradeProductAddKey.setBranch(InterfCommonConstant.分支序号_默认);
			finTradeProductAddMapper.deleteByPrimaryKey(finTradeProductAddKey);
			
			FinProductTypeKey finProductTypeKey = new FinProductTypeKey();
			finProductTypeKey.setFinprodId(entity.getFinprodId());
			finProductTypeKey.setBranch(InterfCommonConstant.分支序号_默认);
			finProductTypeMapper.deleteByPrimaryKey(finProductTypeKey);
//		}else if(OperationType.MODIFY.equals(type)){
//			mapper.update(entity);
		}
	}

}
