/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productdata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.productdata.dao.ValProductAssetsQuery001Mapper;
import com.joyintech.service.valuation.basedata.productdata.dto.ValProductAssetsQuery001Dto;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceValProductAssetsQuery001Service")
public class ValProductAssetsQuery001Service extends CommonExportService<ValProductAssetsQuery001Dto> {
	
    /**
     * ValFinprodTradeQuery001Mapper
     */
    @Autowired
    private ValProductAssetsQuery001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValProductAssetsQuery001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
        return super.queryForPage(page,length,()-> this.queryPageList(params));
    }
    
    public List<ValProductAssetsQuery001Dto> queryPageList(Map<String, Object> params) {
      //获取交易方向
        String finprodType = (String)params.get("finprodType");
        //交易方向多选，转为数组
        String[] finprodTypeArr = null;
        if(StringUtil.isNotEmpty(finprodType)){
            finprodTypeArr = finprodType.split(",");
        }
        params.put("finprodTypeArr", finprodTypeArr);
        //add by liupengfei for 多级法人改造 S
        if(MultilevelDataAuthorityUtil.isEnabled()){
            params.put("institutionCodeList", LoginUserUtil.getTreeInstCode());
        }
        //add by liupengfei for 多级法人改造 E
        return mapper.queryPageList(params);
    }
    
}
