/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dao.SvrMstExchangeQuotationInfoMapper;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dao.SvrMstExchargeQuoteMapper;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;

/**
 * 汇率行情service 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see SvrMstExchangeQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMstExchangeQuotationInfoService extends CommonExportService<SvrMstExchangeQuotationInfoDto> {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
     * 汇率行情mapper
     */
    @Autowired
    private SvrMstExchangeQuotationInfoMapper mstExchangeQuotationInfoMapper;

    /**
     * 汇率行情自动生成mapper
     */
    @Autowired
    private SvrMstExchargeQuoteMapper mstExchargeQuoteMapper;

    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<SvrMstExchangeQuotationInfoDto> selectForPage(Map<String, Object> pageParam){
//        return simpleRestTemplate.postForPage(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectForPage",
//            pageParam, MstExchangeQuotationInfoDto.class);
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            Page<SvrMstExchangeQuotationInfoDto> page = queryForPage(pagenum,legth,()->mstExchangeQuotationInfoMapper.select(pageParam));
            return page;
        } else {
            Page<SvrMstExchangeQuotationInfoDto> page = queryForPage(SvrMstExchangeQuotationInfoMapper.class,"select",pageParam);
            return page;
        }
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SvrMstExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
//        return simpleRestTemplate.postForList(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstExchangeQuotationInfo/selectAllList",
//            pageParam, MstExchangeQuotationInfoDto.class);
    	return mstExchangeQuotationInfoMapper.select(pageParam);
    }

    
    public void doComplete(SvrMstExchangeQuotationInfoDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstExchargeQuoteMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {
//             设置基本币种，单位币种等
            String exchargeCode = entity.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                entity.setBasisCyy(splitToList.get(0));
                entity.setQuotCyy(splitToList.get(1));
                entity.setQuotUnit((long)1);
            }
            mstExchargeQuoteMapper.insert(entity);
        }
        else {
            mstExchargeQuoteMapper.updateByPrimaryKeySelective(entity);
        }
    }

    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<SvrMstExchangeQuotationInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "tradeDate")) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

	public List<SvrMstExchangeQuotationInfoDto> selectForExport(Map<String, Object> param) {
    	String keyList = param.get("keyList").toString();
        List<SvrMstExchangeQuotationInfoDto> investList = null;
        try {
            investList = JsonUtils.json2List(keyList,
            SvrMstExchangeQuotationInfoDto.class);
            return mstExchangeQuotationInfoMapper.selectForExport(investList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return new ArrayList<SvrMstExchangeQuotationInfoDto>();
    }
}
