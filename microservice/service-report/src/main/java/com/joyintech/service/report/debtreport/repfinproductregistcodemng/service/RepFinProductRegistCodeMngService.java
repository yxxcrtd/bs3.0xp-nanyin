/*
 * 文件名：RepFinProductRegistCodeMngService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductregistcodemng.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.debtreport.repfinproductregistcodemng.dao.RepFinProductRegistCodeMngMapper;
import com.joyintech.service.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngService
 * @since 1.0
 */
@Service
public class RepFinProductRegistCodeMngService extends BaseService<RepFinProductRegistCodeMngDto>{
    
    /**
     * 自定义mapper
     */
    @Autowired
    private RepFinProductRegistCodeMngMapper repFinProductRegistCodeMngMapper;
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param){
        return repFinProductRegistCodeMngMapper.getFinprodMarketId(param);
    }
    
    /**
     * 
     * 产品登记编码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPordRegistCode(Map<String, Object> param){
        return repFinProductRegistCodeMngMapper.getPordRegistCode(param);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductRegistCodeMngDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        String pordRegistCode = (String)pageParam.get("pordRegistCode");
        if(StringUtil.isNotEmpty(pordRegistCode)){
            pageParam.put("pordRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA,pordRegistCode));
        }
        pageParam.put("startVdate", DateUtil.parse((String)pageParam.get("startVdate")));
        pageParam.put("endVdate", DateUtil.parse((String)pageParam.get("endVdate")));
        pageParam.put("startMdate", DateUtil.parse((String)pageParam.get("startMdate")));
        pageParam.put("endMdate", DateUtil.parse((String)pageParam.get("endMdate")));
        return super.queryForPage(page, length, () -> repFinProductRegistCodeMngMapper.selectForPage(pageParam));
    }
    
    /**
     * 
     * 产品代码在理财产品排期表里面不存在
     * <功能详细描述>
     * @param finprodMarketId String
     * @return int
     * @see
     * @since 1.0
     */
    public int check1(String finprodMarketId){
        return repFinProductRegistCodeMngMapper.check1(finprodMarketId);
    }
    
    /**
     * 
     * 产品登记编码在理财产品排期表里面已存在
     * <功能详细描述>
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    public int check2(String prodRegistCode){
        return repFinProductRegistCodeMngMapper.check2(prodRegistCode);
    }
    
    /**
     * 
     * 产品代码在理财产品排期表的产品登记编码和该行的产品登记编码不一样 
     * <功能详细描述>
     * @param finprodMarketId String
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    public int check3(String finprodMarketId, String prodRegistCode){
        return repFinProductRegistCodeMngMapper.check3(finprodMarketId, prodRegistCode);
    }
    
    /**
     * 
     * 更新理财产品排期
     * <功能详细描述>
     * @param paPrdIssueSchedule PaPrdIssueSchedule
     * @return int
     * @see
     * @since 1.0
     */
    public int updatePaPrdIssueSchedule(List<PaPrdIssueSchedule> paPrdIssueSchedule){
    	for(PaPrdIssueSchedule dto:paPrdIssueSchedule){
    		repFinProductRegistCodeMngMapper.updatePaPrdIssueSchedule(dto);
    	}
        return 0;
    }
    
    /**
     * 
     * 更新产品申报报送信息
     * <功能详细描述>
     * @param repProdDeclare RepProdDeclare
     * @return int
     * @see
     * @since 1.0
     */
    public int updateRepProdDeclare(List<RepProdDeclare> repProdDeclare){
    	for(RepProdDeclare dto:repProdDeclare){
    		repFinProductRegistCodeMngMapper.updateRepProdDeclare(dto);
    	}
        return 0;
    }
    
    /**
     * 
     * 更新产品发行报送信息
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
     */
    public int updateRepProdIssue(List<RepProdIssue> repProdIssue){
    	for(RepProdIssue dto:repProdIssue){
    		repFinProductRegistCodeMngMapper.updateRepProdIssue(dto);
    	}
        return 0;
    }

}
