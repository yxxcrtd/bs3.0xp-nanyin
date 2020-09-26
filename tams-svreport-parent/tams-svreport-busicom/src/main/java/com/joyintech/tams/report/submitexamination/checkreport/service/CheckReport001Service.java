/*
 * 文件名：CheckReport001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年10月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitexamination.checkreport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbReportCheck;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.constant.DictCode;

/**
 * 报送前检查service
 * 
 * @author dingzhipeng
 * @version 1.0 2017年10月11日
 * @see CheckReport001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CheckReport001Service extends BaseService<CheckReport001Dto> {

    /**
     * 报送检查mapper
     */
    @Autowired
    private CheckReport001Mapper checkReport001Mapper;

    /**
     * 查询产品名称
     * 
     * @param pageParam 参数
     * @return 结果list
     * @see
     * @since 1.0
     */
    public List<SuggestDto> queryPrdNameList(Map<String,Object> paramMap) {
    	String prodId=paramMap.get("prodid")==null?null:paramMap.get("prodid").toString();
    	if(paramMap.get("type").equals("N")){
    		return checkReport001Mapper.queryPrdNameList(prodId);
    	}else{
    		return checkReport001Mapper.queryPrdNameListOld(prodId);
    	}
    }

    /**
     * 查询产品名称
     * 
     * @param pageParam 参数
     * @return 结果list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryPrdNames(Map<String, Object> map) {
        return checkReport001Mapper.queryPrdNames(map);
    }
    
    /**
     * 查询结果列表
     * 
     * @param pageParam 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    public Page<CheckReport001Dto> searchTable(Map<String, Object> pageParam){
        // 报表名称
        pageParam.put("repCodeList",
            StringUtil.splitToList(GlobalConstant.COMMA, (String)pageParam.get("repCode")));
        // 机构代码
        pageParam.put("orgCodeList",
            StringUtil.splitToList(GlobalConstant.COMMA, (String)pageParam.get("orgCode")));
        int page = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<CheckReport001Dto> pageList = 
        		queryForPage(page,legth,()->checkReport001Mapper.searchTable(pageParam));
           
        pageList.getRoot().forEach(dto -> {
            dto.setCheckResult(
                SysDataUtil.getNameFromDataDict(DictCode.检查结果, dto.getCheckResult()));
        });
        return pageList;
    }
}