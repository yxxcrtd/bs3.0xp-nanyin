/*
 * 文件名：BokBalanceStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.service.BalTableGenerate001Service;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dao.BokBalanceStatementQuery001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.BokBookset;

/**
 * 估值余额表查询Service
 * 
 * @author 杨智
 * @version 1.0 2018年7月25日
 * @see BokBalanceStatementQuery001Service
 * @since 1.0
 */
@Service
public class BokBalanceStatementQuery001Service extends CommonExportService<BokBalanceStatementQuery001Dto> {

//    /**
//     * 余额查询mapper
//     */
//    @Autowired
//    private BokBalanceStatementQuery001Mapper bokBalanceStatementQuery001Mapper;

    /**
     * 账套查询mapper
     */
    @Autowired
    private BokBooksetMapper bokBooksetMapper;
    
    /**
     * 账套查询mapper
     */
    @Autowired
    private BalTableGenerate001Service balTableGenerate001Service;

    /**
     * 标的类金融产品查询Mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;
    

    public Page<BokBalanceStatementQuery001Dto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/BokBalanceStatementQuery001/findPage", pageParam, BokBalanceStatementQuery001Dto.class);  
//        return super.queryForPage(() -> bokBalanceStatementQuery001Mapper.selectAll(pageParam));
    }

    public List<BokBalanceStatementQuery001Dto> selectAllList(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/BokBalanceStatementQuery001/selectAllList", param, BokBalanceStatementQuery001Dto.class);  
//        List<BokBalanceStatementQuery001Dto> list = bokBalanceStatementQuery001Mapper.selectAll(
//            pageParam);
//        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> pageParam = exportParam.getSelectParam();
        String pageIds = (String)pageParam.get("pageIds");
        if (StringUtil.isNotEmpty(pageIds)) {
            List<String> pageNames = Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            pageParam.replace("pageIds", pageIds, pageNames);
        }
        else {
            pageParam.replace("pageIds", pageIds, null);
        }
        exportParam.config(
            "/com/joyintech/tams/valuation/accounadjustmanage/bokbalancestatementquery/conf/export_conf.xml").select(
                () -> selectAllList(pageParam));
    }

    /**
     * 估值表账套日期
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public String checkBokkSetDate(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BokBalanceStatementQuery001/checkBokkSetDate", map, String.class);  
    }
    
    public List<SuggestDto> getBooksetId2(Map<String, Object> map) {
//    	Map<String, Object> map=new HashMap<String, Object>();
//    	map.put("param", param);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/BokBalanceStatementQuery001/getBooksetId2", map, SuggestDto.class);   
    }
    
    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BokBookset getFinProductByBookset(BokBalanceStatementQuery001Dto dto) {
        return this.getFinProductByBookset(dto.getBooksetId());

    }
    
    /**
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param String
     * @return
     * @see
     * @since 1.0
     */
    public BokBookset getFinProductByBookset(String booksetId) {  
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BokBalanceStatementQuery001/getFinProductByBookset", booksetId, BokBookset.class);   
//         return bokBooksetMapper.selectByPrimaryKey(booksetId);
    }
    
    
    /**
     * 导出估值表excel文件
     * 
     * @param dto
     * @return
     * @throws IOException 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params) throws IOException {
        String booksetId = (String)params.get("booksetId");
        String balDate = (String)params.get("balDate");
        BokBookset finprod = this.getFinProductByBookset(booksetId);
        // 获取导出文件
        ExportFileDto exportFileDto = balTableGenerate001Service.createExcel(
            booksetId, balDate,
            finprod.getBooksetId(), finprod.getBooksetName());
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }
    
    
}
