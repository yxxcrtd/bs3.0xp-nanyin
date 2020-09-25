/*
 * 文件名：ValBalanceStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dao.ValBalanceStatementQuery001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto.ValBalanceStatementQuery001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.service.valuation.accounadjustmanage.valuationtablegeneration.service.ValTableGenerate001Service;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 估值余额表查询Service
 * 
 * @author 杨智
 * @version 1.0 2018年7月25日
 * @see ValBalanceStatementQuery001Service
 * @since 1.0
 */
@Service(value="serviceValBalanceStatementQuery001Service")
public class ValBalanceStatementQuery001Service extends CommonExportService<ValBalanceStatementQuery001Dto> {

    /**
     * 估值表查询mapper
     */
    @Autowired
    private ValBalanceStatementQuery001Mapper valBalanceStatementQuery001Mapper;

    /**
     * 账套查询mapper
     */
    @Autowired
    private BokBooksetMapper bokBooksetMapper;

    /**
     * 标的类金融产品查询Mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    @Autowired
    private ValTableGenerate001Service valTableGenerate001Service;

    private Predicate<ValBalanceStatementQuery001Dto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            String subjectNo = (String)pageParam.get("subjectNo");
            if (StringUtil.isNotEmpty(subjectNo)) {
                if (!StringUtil.equals(item.getSubjectNo(), subjectNo)) {
                    return false;
                }
            }
            return true;
        });
    }

    public Page<ValBalanceStatementQuery001Dto> selectForPage(Map<String, Object> pageParam) throws Exception {
        String parentId = (String)pageParam.get("parentId");
        
        return super.queryForTree(() -> valBalanceStatementQuery001Mapper.selectAll(pageParam),
            ValBalanceStatementQuery001Dto::getSubjectNo,
            ValBalanceStatementQuery001Dto::getParentId,
            ValBalanceStatementQuery001Dto::getSubjectName, getFilter(pageParam),Integer.MAX_VALUE,parentId.equals("root")?null:parentId);

       // return super.queryForPage(() -> valBalanceStatementQuery001Mapper.selectAll(pageParam));

    }

    public Page<ValBalanceStatementQuery001Dto> selectSum(Map<String, Object> pageParam) {
        return new Page<ValBalanceStatementQuery001Dto>(
            valBalanceStatementQuery001Mapper.selectSum(pageParam));
    }

    public List<ValBalanceStatementQuery001Dto> selectAllList(Map<String, Object> pageParam) {
        List<ValBalanceStatementQuery001Dto> list = valBalanceStatementQuery001Mapper.selectAll(
            pageParam);
        list.addAll(valBalanceStatementQuery001Mapper.selectSum(pageParam));
        return list;
    }

    public FinProduct getFinProductByBookset(String booksetId) {
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
//        FinProductKey key = new FinProductKey();
        String finprodId="";
        if(StringUtil.isNotEmpty(bokBookset)){
            // 账套的核算主体代码关联金融产品标的表的金融产品代码
        	finprodId=bokBookset.getAcctSubjectId();
        }
        // 金融产品类型=理财产品
//        key.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finProductMapper.selectByPrimaryKey(finprodId);
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
        String valDate = (String)params.get("valDate");
        BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetId);
        // 获取导出文件
        ExportFileDto exportFileDto = valTableGenerate001Service.createExcel(
            booksetId, valDate, bokBookset.getBooksetFullname());
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

}
