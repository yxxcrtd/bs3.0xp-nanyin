/*
 * 文件名：BankBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.psbc.bankbalancequery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.psbc.bankbalancequery.dao.BankBalanceQuery001Mapper;
import com.joyintech.tams.valuation.psbc.bankbalancequery.dto.BankBalanceQuery001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 金融产品交易查询Service
 * @author wangyang1
 * @version 1.0 2019年8月23日
 * @see BankBalanceQuery001Service
 * @since 1.0
 */
@Service
public class BankBalanceQuery001Service extends CommonExportService<BankBalanceQuery001Dto> {
	
    /**
     * BankBalanceQuery001Mapper
     */
    @Autowired
    private BankBalanceQuery001Mapper mapper;
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<BankBalanceQuery001Dto> findPage(Map<String, Object> params) throws Exception {
        Integer page = Integer.valueOf((String) params.get("page"));
        Integer length = Integer.valueOf((String) params.get("length"));
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
        String finprodId = (String)params.get("finprodId"); 
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
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr=finprodId.split(",");
        }
        params.put("ccyArr", ccyArr);
        params.put("finprodIdArr", finprodIdArr);
        params.put("bookSetTypes", bookSetTypes);
        if(issueType!=null){
            if(issueType.equals("01")){
                //合并展示
                return super.queryForPage(page,length,()-> mapper.queryPageListTotal(params));
            }else {
                return super.queryForPage(page,length,()-> mapper.queryPageList(params));
            }
        }else {
                //没有选择展示维度
                return super.queryForPage(page,length,()-> mapper.queryPageList(params));
        }
    }
    
    public List<BankBalanceQuery001Dto> getAllList(Map<String, Object> params){
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
        String finprodId = (String)params.get("finprodId"); 
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
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr=finprodId.split(",");
        }
        params.put("ccyArr", ccyArr);
        params.put("finprodIdArr", finprodIdArr);
        params.put("bookSetTypes", bookSetTypes);
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
    }
    
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param hashMap
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryValBokSubject(Map<String, Object> param) { 
        List<SelectOptionDto> list = mapper.queryValBokSubject(param);
        Integer beginIndex = Integer.valueOf((String) param.get("page"));
        Integer length = Integer.valueOf((String) param.get("length"));
        Page<SelectOptionDto> page = new Page<SelectOptionDto>();
        page.setRoot(list);
        page.setPage(beginIndex);
        page.setRecords(list.size());
        return page; 
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> findFinprodId(Map<String, Object> param) { 
        List<SelectOptionDto> list = mapper.getFinprodId(param);
        Integer beginIndex = Integer.valueOf((String) param.get("page"));
        Integer length = Integer.valueOf((String) param.get("length"));
        Page<SelectOptionDto> page = new Page<SelectOptionDto>();
        page.setRoot(list);
        page.setPage(beginIndex);
        page.setRecords(list.size());
        return page; 
    }
    
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

}
