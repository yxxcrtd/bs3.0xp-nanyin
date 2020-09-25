/*
 * 文件名：BokBalanceStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.bokbalancestatementquery.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.balancetablegeneration.service.BalTableGenerate001Service;
import com.joyintech.service.valuation.accounadjustmanage.bokbalancestatementquery.dao.BokBalanceStatementQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
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
@Service(value="serviceBokBalanceStatementQuery001Service")
public class BokBalanceStatementQuery001Service extends CommonExportService<BokBalanceStatementQuery001Dto> {

    /**
     * 余额查询mapper
     */
    @Autowired
    private BokBalanceStatementQuery001Mapper bokBalanceStatementQuery001Mapper;

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

    public Page<BokBalanceStatementQuery001Dto> selectForPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> bokBalanceStatementQuery001Mapper.selectAll(pageParam));
    }

    public List<BokBalanceStatementQuery001Dto> selectAllList(Map<String, Object> pageParam) {
        List<BokBalanceStatementQuery001Dto> list = bokBalanceStatementQuery001Mapper.selectAll(
            pageParam);
        return list;
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
     * 通过账套获取对应资产名称和资产代码
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BokBookset getFinProductByBookset(BokBalanceStatementQuery001Dto dto) {
        return bokBooksetMapper.selectByPrimaryKey(dto.getBooksetId());

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
         return bokBooksetMapper.selectByPrimaryKey(booksetId);
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
