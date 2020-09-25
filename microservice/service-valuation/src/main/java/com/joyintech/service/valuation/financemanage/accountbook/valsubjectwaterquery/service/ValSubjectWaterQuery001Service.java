/*
 * 文件名：ValSubjectWaterQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dao.ValSubjectWaterQuery001Mapper;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年7月10日
 * @see ValSubjectWaterQuery001Service
 * @since 1.0
 */
@Service(value="serviceValSubjectWaterQuery001Service")
public class ValSubjectWaterQuery001Service extends CommonExportService<ValSubjectWaterQuery001Dto> {

    @Autowired
    private ValSubjectWaterQuery001Mapper valSubjectWaterQuery001Mapper;

    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public Page<SelectOptionDto> getSubjectNo(int page,int length,Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page,length,()->valSubjectWaterQuery001Mapper.getSubjectNo(param));
    }

    /**
     * 查询所有账套 <br/>
     * 用于所属账套控件初期化
     * 
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findBooksets() {
        return valSubjectWaterQuery001Mapper.findBooksets();
    }

    /**
     * 格式化创建SelectOptionDto对象 <功能详细描述>
     * 
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    /**
     * 科目流水查询
     * 
     * @param vouchNum 凭证编号
     * @param bookDate 入账日期
     * @return 科目流水List
     * @see
     * @since 1.0
     */
    public List<ValSubjectWaterQuery001Dto> getSubjectWater(ValSubjectWaterQuery001SearchDto dto)
        throws Exception {
        // 账套类别
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
        return valSubjectWaterQuery001Mapper.getSubjectWater(dto);
    }

    /**
     * 根据条件获取表格信息
     * @param length 
     * @param page 
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ValSubjectWaterQuery001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {
        ValSubjectWaterQuery001SearchDto dto = new ValSubjectWaterQuery001SearchDto();
        // 账套代码
        // String booksetId = (String)params.get("booksetId");
        // dto.setBooksetId(booksetId);
        String booksetId = (String)params.get("booksetId");
        String[] booksetIdArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetIdArr = booksetId.split(",");
        }
        dto.setBooksetIdArr(booksetIdArr);
        // 业务类别
        String comTableId = (String)params.get("comTableId");
        String[] comTableIdArr = null;
        if (StringUtil.isNotEmpty(comTableId)) {
            comTableIdArr = comTableId.split(",");
        }
        dto.setComTableIdArr(comTableIdArr);
        // 资产/负债代码
        String busiId = (String)params.get("busiId");
        dto.setBusiId(busiId);
        // 币种
        String ccy = (String)params.get("ccy");
        dto.setCcy(ccy);
        // 科目号
        String subjectNo = (String)params.get("subjectNo");
        String[] subjectNoArr = null;
        if (StringUtil.isNotEmpty(subjectNo)) {
            subjectNoArr = subjectNo.split(",");
        }
        dto.setSubjectNoArr(subjectNoArr);
        // 获取记账日期
        String bookDateBegin = (String)params.get("bookDateBegin");
        String bookDateEnd = (String)params.get("bookDateEnd");
        if (StringUtil.isNotEmpty(bookDateBegin)) {
            dto.setBookDateBegin(DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(bookDateEnd)) {
            dto.setBookDateEnd(DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
        }
        // 账套类别
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
        // 没有选择展示维度
        return super.queryForPage(page, length, () -> valSubjectWaterQuery001Mapper.queryPageList(dto));
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void buildExportParam(ExportParam exportParam) {
        Map<String, Object> params = exportParam.getSelectParam();
        String pageIds = (String)params.get("pageIds");
        if(StringUtil.isNotEmpty(pageIds)){
            List<String> pageNames =  Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            params.replace("pageIds", pageIds, pageNames);
        }
        else{
        	params.replace("pageIds", pageIds, null);
        }
        
        
        ValSubjectWaterQuery001SearchDto dto = new ValSubjectWaterQuery001SearchDto();
        // 账套代码
        // String booksetId = (String)params.get("booksetId");
        // dto.setBooksetId(booksetId);
        String booksetId = (String)params.get("booksetId");
        String[] booksetIdArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetIdArr = booksetId.split(",");
        }
        dto.setBooksetIdArr(booksetIdArr);
        // 业务类别
        String comTableId = (String)params.get("comTableId");
        String[] comTableIdArr = null;
        if (StringUtil.isNotEmpty(comTableId)) {
            comTableIdArr = comTableId.split(",");
        }
        dto.setComTableIdArr(comTableIdArr);
        // 资产/负债代码
        String busiId = (String)params.get("busiId");
        dto.setBusiId(busiId);
        // 币种
        String ccy = (String)params.get("ccy");
        dto.setCcy(ccy);
        // 科目号
        String subjectNo = (String)params.get("subjectNo");
        String[] subjectNoArr = null;
        if (StringUtil.isNotEmpty(subjectNo)) {
            subjectNoArr = subjectNo.split(",");
        }
        dto.setSubjectNoArr(subjectNoArr);
        // 获取记账日期
        String bookDateBegin = (String)params.get("bookDateBegin");
        String bookDateEnd = (String)params.get("bookDateEnd");
        if (StringUtil.isNotEmpty(bookDateBegin)) {
            try {
				dto.setBookDateBegin(DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (StringUtil.isNotEmpty(bookDateEnd)) {
            try {
				dto.setBookDateEnd(DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        // 账套类别
        dto.setBooksetType(DictCodeList.账套类别_理财账套.getValue());
        // 没有选择展示维度
        exportParam.config("/com/joyintech/tams/valuation/financemanage/accountbook/valsubjectwaterquery/config/export_subjectwaterquery.xml").dataSource(DataSourceType.MASERT).select(() -> valSubjectWaterQuery001Mapper.queryPageList(dto));
    }
    
}
