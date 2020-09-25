package com.joyintech.tams.invest.product.resultscovering.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.resultscovering.dto.FinResultsCovering001Dto;

@Service
public class FinResultsCovering001Service extends CommonExportService<FinResultsCovering001Dto> {
	public Page<FinResultsCovering001Dto> selectForPage(Map<String, Object> map) {
        if (StringUtil.isNotEmpty(map.get("prodId"))) {
            map.put("prodId", StringUtil.splitToList(null, map.get("prodId").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("layFinprodId"))) {
            map.put("prodStyle", StringUtil.splitToList(null, map.get("layFinprodId").toString()));
        }
        map.put("COMMON_BUSINESS_TYPE_01", BussinessDictCodeList.共同业务类型_金融产品.getValue());
        map.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        map.put("CASH_TYPE_2_C11", DictCodeListExt1.现金流二级类型_回补.getValue());
        map.put("BUSI_TYPE_31", DictCodeList.业务类型_产品回补交易.getValue());
        map.put("PS_C16", DictCodeList.交易方向_回补.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinResultsCovering/selectForPage", map,
            FinResultsCovering001Dto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	//修改bug 业绩回补导出 报错 add by wangcheng 20191105 start
        exportParam.config(
            "/com/joyintech/tams/invest/product/resultscovering/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinResultsCovering001Dto> selectPageData(Map<String, Object> selectParam) {
    	try {
			return selectForPage(selectParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public List<FinResultsCovering001Dto> findPtradeid(String tradeId){
    	  return restTemplate.postForList(
    	            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinResultsCovering/findPtradeid", tradeId,
    	            FinResultsCovering001Dto.class);
    }
    
    /**
     * 产品代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> param)
        throws Exception {
        // 收益型产品 下拉选项过滤净值型产品 lijun add SHBS-518
        // param.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            param.put("userName", user.getUserName());
        }
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinResultsCovering/queryProdIdInfo", param,
            SelectOptionDto.class);
    }
    
    /**
     * 产品款型下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdStyleInfo(Map<String, Object> param) {
        String prodId = (String)param.get("prodId");
        if(!StringUtil.isBlank(prodId)){
            List<String> finprodIdList =  StringUtil.splitToList(",", prodId);
            param.put("finprodIdList", finprodIdList);
            param.put("style", "01");
            return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getProdStyle", param,
            SelectOptionDto.class); 
        } 
        else {
            return null;
        }

    }

}
