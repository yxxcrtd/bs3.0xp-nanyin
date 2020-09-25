package com.joyintech.tams.invest.product.messageadjust.finproductquotation.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品报价管理查询service.
 * @author wangkang
 * date:2018 10 9
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FinProductQuotation001Service extends CommonExportService<FinProductQuotation001Dto> implements BaseProcessService<FinProductQuotation001Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private PlatformDate platformDate;

    /**
     * 根据产品代码模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map) {
        if(!StringUtil.isNotEmpty(map.get("initValue"))){
            map.put("userId", SecurityUtil.getLoginUser().getUserName());
            map.put("platDate", DateUtil.format(platformDate.getSysDate()));
        }
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/queryProdIdInfoIssueSuc", map,
            SelectOptionDto.class);
    }

    /**
	 * 查询
	 * <功能详细描述>
	 * @param pageParam Map<String, Object>
	 * @return Page<FinProductQuotation001Dto>
	 * @see
	 * @since 1.0
	 */
    public Page<FinProductQuotation001Dto> selectForPage(Map<String, Object> pageParam)	{
		//得到产品代码
        if(StringUtil.isNotEmpty(pageParam.get("finprodId"))){
            pageParam.put("finprodIdList", StringUtil.splitToList(GlobalConstant.COMMA,pageParam.get("finprodId").toString()));
        }
      //得到产品代码
        if(StringUtil.isNotEmpty(pageParam.get("prodType"))){
            pageParam.put("prodTypeList", StringUtil.splitToList(GlobalConstant.COMMA,pageParam.get("prodType").toString()));
        }
        pageParam.put("proEarnings", DictCodeList.收益类型_收益型.getValue());
        pageParam.put("financingProduct", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("interest", DictCodeListExt1.现金流一级类型_利息.getValue());
        pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
	            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/selectForPage", pageParam,
	            FinProductQuotation001Dto.class);
    }

	/**
	 * 产品代码联想框
	 * <功能详细描述>
	 * @param finprodId
	 * @return
	 * @see
	 * @since 1.0
	 */
    public List<SelectOptionDto> getFinprodIds(String finprodId) {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductQuotation001/getFinprodId",param,SelectOptionDto.class);
    }
    
    
    /**
     * 产品款型联想框
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getProdType(String prodType) {
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("finprodId", prodType);
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinProductQuotation001/getProdType",param,SelectOptionDto.class);
    }

	/**
	 * 导出
	 * {@inheritDoc}
	 */
    @Override
	protected void buildExportParam(ExportParam exportParam) {
		// TODO Auto-generated method stub
        exportParam.config(
				"/com/joyintech/tams/invest/product/messageadjust/finproductquotation/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
	/**
	 *
	 * {@inheritDoc}
	 */
    @Override
	public Page<FinProductQuotation001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
        return selectForPage(selectParam);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("releaseDate", propName)||StringUtil.equals("effDate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
	/**
	 *
	 * {@inheritDoc}
	 */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void doComplete(FinProductQuotation001Dto entity, OperationType type) {
		// TODO Auto-generated method stub
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "doDelete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else if (OperationType.MODIFY.equals(type)) {
            action = "update";
        }
        restTemplate.postForObject(
	            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductQuotation001/" + action, entity,
	            Integer.class);
    }

    @SuppressWarnings("unchecked")
	public Map<String, String> sendProductQuotationToSale(List<Map<String,String>> listMap) {
        return restTemplate.postForObject(
            ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendProductQuotationToSale", listMap,
            Map.class);
    }

}
