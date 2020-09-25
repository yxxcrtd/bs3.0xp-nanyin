/*
 * 文件名：ValSubjectBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.dao.ValSubjectBalanceQuery001Mapper;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBookset;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceValSubjectBalanceQuery001Service")
public class ValSubjectBalanceQuery001Service extends CommonExportService<ValSubjectBalanceQuery001Dto> {
	
    /**
     * ValFinprodTradeQuery001Mapper
     */
    @Autowired
    private ValSubjectBalanceQuery001Mapper mapper;
    /**
     * 根据条件获取表格信息
     * @param length 
     * @param page 
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValSubjectBalanceQuery001Dto> findPage(int page, int length, Map<String, Object> params) throws Exception {
        
    	
    	String booksetId = (String) params.get("booksetId");
        String[] booksetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
        	booksetIdArr = booksetId.split(",");
        }
        params.put("booksetIdArr", booksetIdArr);
    	
        //获取交易方向
        String bookSetType = (String)params.get("bookSetType");
        String subjectLevel = (String)params.get("subjectLevel");
        String ccy = (String)params.get("ccy");
        
        // GZHSXT-1608 delete by QuJianZhong 20181214 start
        // 显示科目
        // String showSubject = (String)params.get("showSubject");
        // GZHSXT-1608 delete by QuJianZhong 20181214 end
        
        //展示维度
        String issueType = (String)params.get("issueType");
        //交易方向多选，转为数组
        String[] bookSetTypes = null;
        //套账类别
        if(StringUtil.isNotEmpty(bookSetType)){
            bookSetTypes=bookSetType.split(",");
        }
        if("全部".equals(subjectLevel)||"".equals(subjectLevel)){
            params.put("subjectLevel", "");
        }
        //交易方向多选，转为数组
        String[] ccyArr = null;
        if(StringUtil.isNotEmpty(ccy)){
            ccyArr=ccy.split(",");
        }
        params.put("ccyArr", ccyArr);
        params.put("bookSetTypes", bookSetTypes);
        
        // GZHSXT-1608 delete by QuJianZhong 20181214 start
        // params.put("showSubject", showSubject);
        // GZHSXT-1608 delete by QuJianZhong 20181214 end
        
        if(issueType!=null){
            if(issueType.equals("01")){
            	
            	//多级法人改造  start sadd by hxy  20200616
            	if(MultilevelDataAuthorityUtil.isEnabled()) {
            		 List<String> institutionlist=LoginUserUtil.getTreeInstCode();
            		 params.put("institutionlist", institutionlist);
            	}
            	//多级法人改造  end sadd by hxy  20200616
                //合并展示
                return super.queryForPage(page,length,()-> mapper.queryPageListTotal(params));
            }else {
                //逐账套展示
                return super.queryForPage(page,length,()-> mapper.queryPageList(params));
            }
        }else {
            //没有选择展示维度
            return super.queryForPage(page,length,()-> mapper.queryPageList(params));
        }
    }
    
    public List<ValSubjectBalanceQuery001Dto> getAllList(Map<String, Object> params){
        String booksetId = (String) params.get("booksetId");
        String[] booksetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
            booksetIdArr = booksetId.split(",");
        }
        params.put("booksetIdArr", booksetIdArr);
        
        //获取交易方向
        String bookSetType = (String)params.get("bookSetType");
        String subjectLevel = (String)params.get("subjectLevel");
        String ccy = (String)params.get("ccy");
        
        // GZHSXT-1608 delete by QuJianZhong 20181214 start
        // 显示科目
        // String showSubject = (String)params.get("showSubject");
        // GZHSXT-1608 delete by QuJianZhong 20181214 end
        
        //展示维度
        String issueType = (String)params.get("issueType");
        //交易方向多选，转为数组
        String[] bookSetTypes = null;
        //套账类别
        if(StringUtil.isNotEmpty(bookSetType)){
            bookSetTypes=bookSetType.split(",");
        }
        if("全部".equals(subjectLevel)||"".equals(subjectLevel)){
            params.put("subjectLevel", "");
        }
        //交易方向多选，转为数组
        String[] ccyArr = null;
        if(StringUtil.isNotEmpty(ccy)){
            ccyArr=ccy.split(",");
        }
        params.put("ccyArr", ccyArr);
        params.put("bookSetTypes", bookSetTypes);
        
        // GZHSXT-1608 delete by QuJianZhong 20181214 start
        // params.put("showSubject", showSubject);
        // GZHSXT-1608 delete by QuJianZhong 20181214 end
        
        if(issueType!=null){
            if(issueType.equals("01")){
                //合并展示
                return mapper.queryPageListTotal(params);
            }else {
                //逐账套展示
                return mapper.queryPageList(params);
            }
        }else {
            //没有选择展示维度
            return mapper.queryPageList(params);
        }
//        return  mapper.queryPageList(params);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param hashMap
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryValBokSubject(int page,int length,Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page,length,()->mapper.queryValBokSubject(param));
    }
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetId() {
        List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
        List<BokBookset> listBookset = mapper.queryBookSet();
        listBookset.forEach(item -> {
            SelectOptionDto dto=new SelectOptionDto();
            dto.setId(item.getBooksetId());
            dto.setText(item.getBooksetName());
            listSelectDto.add(dto);
        });
        return listSelectDto;
    }

}
