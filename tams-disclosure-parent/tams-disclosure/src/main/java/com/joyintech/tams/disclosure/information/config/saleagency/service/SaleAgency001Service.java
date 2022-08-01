/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.saleagency.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dao.ProdAssetInfo001Mapper;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency001Mapper;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 合作机构信息维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency001Service
 * @since 1.0
 */
@Service
public class SaleAgency001Service extends CommonExportService<SaleAgencyDto>
    implements BaseProcessService<SaleAgencyDto>{

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleAgency001Mapper saleAgency001Mapper;


    /**
     * 根据产品代码或产品名称模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySaleAgency(Map<String, Object> map) {
        return saleAgency001Mapper.querySaleAgency(map);
    }

    /**
     * 使用给定的检索分页数据。
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<SaleAgencyDto> selectForPage(Map<String, Object> pageParam){
        //todo:前台查询的请求参数（String）和导出中的参数类型(int)不一致
        int page=1;
        int length=30;
        if(pageParam.get("page")instanceof Integer){
            page= (int) pageParam.get("page");
        }else{
            page = Integer.parseInt((String) pageParam.get("page"));
        }
        if(pageParam.get("page")instanceof Integer){
            page= (int) pageParam.get("length");
        }else{
            length = Integer.parseInt((String) pageParam.get("length"));
        }
        //代销机构
        if(StringUtil.isNotEmpty(pageParam.get("agencyId"))){
            pageParam.put("agencyId",StringUtil.splitToList(null,pageParam.get("agencyId").toString()));
        }else{
            pageParam.put("agencyId",new ArrayList<>());
        }
        //产品系类
        if(StringUtil.isNotEmpty(pageParam.get("prodSeries"))){
            pageParam.put("prodSeries",StringUtil.splitToList(null,pageParam.get("prodSeries").toString()));
        }else{
            pageParam.put("prodSeries",new ArrayList<>());
        }
        Page<SaleAgencyDto> pageDto = super.queryForPage(page, length, () -> saleAgency001Mapper.select(pageParam));
        List<SaleAgencyDto> saleAgencyDtos = pageDto.getRoot();
        for (SaleAgencyDto saleAgencyDto : saleAgencyDtos) {
            if(!StringUtil.isEmptyList(saleAgencyDto.getProdSaleAgencyDtoList())){
                String prodSeries=saleAgencyDto.getProdSaleAgencyDtoList().stream().map(ProdSaleAgencyDto::getProdSeries).distinct().collect(Collectors.joining(","));
                saleAgencyDto.setProdSeriesStr(prodSeries);
            }
        }
        pageDto.setRoot(saleAgencyDtos);
        return pageDto;

    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/disclosure/information/config/saleagency/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<SaleAgencyDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }


    @Override
    public void doComplete(SaleAgencyDto saleAgencyDto, OperationType operationType) {

    }

    /**
     * 根据代销机构id查询关联产品
     * @param sequenceNo
     * @return
     */
    public List<ProdSaleAgencyDto> selectProdByAgencyId(String sequenceNo) {
        return saleAgency001Mapper.selectProdByAgencyId(sequenceNo);
    }

    public List<SaleAgencyDto> selectForExport(Map<String, Object> param) {
        List<SaleAgencyDto> investList = null;
        try {
            investList = (List<SaleAgencyDto>)param.get("investList");
            List<String> agencyIds = investList.stream().map(SaleAgencyDto::getSequenceNo).collect(Collectors.toList());
            Map<String,Object> pageParam=new HashMap<>();
            pageParam.put("agencyId",agencyIds);
            List<SaleAgencyDto> saleAgencyDtos = saleAgency001Mapper.select(pageParam);
            for (SaleAgencyDto saleAgencyDto : saleAgencyDtos) {
                if(!StringUtil.isEmptyList(saleAgencyDto.getProdSaleAgencyDtoList())){
                    String prodSeries=saleAgencyDto.getProdSaleAgencyDtoList().stream().map(ProdSaleAgencyDto::getProdSeries).distinct().collect(Collectors.joining(","));
                    saleAgencyDto.setProdSeriesStr(prodSeries);
                }
            }
            return investList;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<SaleAgencyDto>();
    }

    /**
     * 查询系统中已经维护的产品系列
     * @param map
     * @return
     */
    public List<SelectOptionDto> queryProdSeries(Map<String, Object> map) {
        return saleAgency001Mapper.queryProdSeries(map);
    }
}
