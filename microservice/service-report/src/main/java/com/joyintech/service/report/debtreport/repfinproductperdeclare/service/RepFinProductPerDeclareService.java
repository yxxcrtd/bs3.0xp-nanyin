/*
 * 文件名：RepFinProductPerDeclareService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperdeclare.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.debtreport.repfinproductperdeclare.dao.RepFinProductPerDeclareMapper;
import com.joyintech.service.report.debtreport.repfinproductperdeclare.dto.RepFinProductPerDeclareDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 个人产品申报报送信息
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月21日
 * @see RepFinProductPerDeclareService
 * @since 1.0
 */
@Service("backRepFinProductPerDeclareService")
public class RepFinProductPerDeclareService extends BaseService<RepFinProductPerDeclareDto>{
    
    /**
     * 自定义mapper
     */
    @Autowired
    private RepFinProductPerDeclareMapper repFinProductPerDeclareMapper;
    
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
        param.put("SALETARGET_DECLARE_05", DictCodeList.目标客户类型_一般个人客户.getValue());
        param.put("SALETARGET_DECLARE_06", DictCodeList.目标客户类型_高资产净值客户.getValue());
        return repFinProductPerDeclareMapper.getFinprodMarketId(param);
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
    public Page<RepFinProductPerDeclareDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        String finprodId = (String)pageParam.get("finprodId");
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        /*String pordRegistCode = (String)pageParam.get("pordRegistCode");
        if(StringUtil.isNotEmpty(pordRegistCode)){
            pageParam.put("pordRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA,pordRegistCode));
        }*/
        pageParam.put("startRepStatusDate", DateUtil.parse((String)pageParam.get("startRepStatusDate")));
        pageParam.put("endRepStatusDate", DateUtil.parse((String)pageParam.get("endRepStatusDate")));
        pageParam.put("start", DateUtil.parse((String)pageParam.get("start")));
        pageParam.put("end", DateUtil.parse((String)pageParam.get("end")));
        pageParam.put("SALETARGET_DECLARE_05", DictCodeList.目标客户类型_一般个人客户.getValue());
        pageParam.put("SALETARGET_DECLARE_06", DictCodeList.目标客户类型_高资产净值客户.getValue());
        return super.queryForPage(page, length, () -> repFinProductPerDeclareMapper.selectForPage(pageParam));
    }
    
    /**
     * 
     * 页面导出
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductPerDeclareDto> selectForExport(int page, int length, Map<String, Object> pageParam)
        throws Exception {
    	 String repFinprodId = (String)pageParam.get("repFinprodId");
         if(StringUtil.isNotEmpty(repFinprodId)){
        	 pageParam.put("repFinprodId", StringUtil.splitToList(GlobalConstant.COMMA,repFinprodId));
         }else{
        	 pageParam.put("repFinprodId",null);
         }
         pageParam.put("SALETARGET_DECLARE_05", DictCodeList.目标客户类型_一般个人客户.getValue());
         pageParam.put("SALETARGET_DECLARE_06", DictCodeList.目标客户类型_高资产净值客户.getValue());
         return super.queryForPage(page, length, () -> repFinProductPerDeclareMapper.selectForExport(pageParam));
    }
    
    /**
     * 
     * 获取申报附件
     * <功能详细描述>
     * @param schemeId String
     * @return PrdDeclareSchemeDoc
     * @see
     * @since 1.0
     */
    public List<PrdDeclareSchemeDoc> getSeqno(String schemeId){
        return repFinProductPerDeclareMapper.getSeqno(schemeId);
    }
}
