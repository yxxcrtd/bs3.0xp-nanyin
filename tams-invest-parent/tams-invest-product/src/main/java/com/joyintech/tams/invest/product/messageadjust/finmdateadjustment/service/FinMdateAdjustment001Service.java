/*
 * 文件名：FinMdateAdjustment001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustment001Dto;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;

/**
 * 到期日调整信息
 * 
 * @author zhangliang
 * @version 1.0 2018年11月2日
 * @see FinMdateAdjustment001Service
 * @since 1.0
 */
@Service
public class FinMdateAdjustment001Service extends CommonExportService<FinMdateAdjustment001Dto> {

    /**
     * 查询到期日调整列表数据
     * 
     * @param map 查询参数
     * @return 到期日调整列表数据
     * @see
     * @since 1.0
     */
    public Page<FinMdateAdjustment001Dto> selectForPage(Map<String, Object> map) {
        if (map.get("finprodId") != null){
            map.put("finprodId", StringUtil.splitToList(null, map.get("finprodId").toString()));
        }
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("TZLX_TQZZ", DictCodeListExt2.调整类型_提前终止.getValue());
        map.put("TZLX_CPZQ", DictCodeListExt2.调整类型_产品展期.getValue());
        map.put("TZLX_DQRTZ", DictCodeListExt2.调整类型_到期日调整.getValue());
        
        Page<FinMdateAdjustment001Dto> page = restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinMdateAdjustment/queryAdjustMdateList", map,
                FinMdateAdjustment001Dto.class);
//        List<FinMdateAdjustment001Dto> list = page.getRoot();
//        for (FinMdateAdjustment001Dto dto : list) {
//            //调整类型为提前终止
//            if(StringUtil.equals(dto.getAdjustType(), DictCodeListExt2.调整类型_提前终止.getValue())){
//                dto.setBdate(dto.getMdate());
//                dto.setMdate(null);
//            }
//            //调整类型为产品展期
//            else if(StringUtil.equals(dto.getAdjustType(), DictCodeListExt2.调整类型_产品展期 .getValue())){
//                dto.setRdate(dto.getMdate());
//                dto.setMdate(null);
//            }
//            else if(StringUtil.equals(dto.getAdjustType(), DictCodeListExt2.调整类型_到期日调整 .getValue())){
//            	dto.setMdate(dto.getMdate());
//            }else{
//            	dto.setMdate(null);
//            }
//        }
//        page.setRoot(list);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId("FinMdateAdjustment001");
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto); 
         String value1 = isEleShow.get("FinMdateAdjustment001_sendStatus");
         String value2 = isEleShow.get("send_remark_table");
        if(StringUtil.equals(value1, DictCodeList.是否_是.getValue())&&StringUtil.equals(value2, DictCodeList.是否_是.getValue())) {
        	exportParam.config(
                    "/com/joyintech/tams/invest/product/messageadjust/finmdateadjustment/conf/export_conf.xml").dataSource(
                        DataSourceType.MASERT);
        }else if(StringUtil.equals(value1, DictCodeList.是否_否.getValue())&&StringUtil.equals(value2, DictCodeList.是否_否.getValue()) ) {
        	exportParam.config(
                    "/com/joyintech/tams/invest/product/messageadjust/finmdateadjustment/conf/export_conf_N.xml").dataSource(
                        DataSourceType.MASERT);
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinMdateAdjustment001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 查询产品的最大调整批次
     * @param finprodId
     * @return
     */
	public int getMaxAdjustNum(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinMdateAdjustment/getMaxAdjustNum?finprodId="
                + finprodId,
                Integer.class);
	}

	/**
	 * 查询是否是存续产品
	 * @return存续产品
	 */
	public FinMdateAdjustment001Dto queryProductByProdId(String finprodId) {
		return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinMdateAdjustment/queryProductByProdId?finprodId="
                + finprodId,
                FinMdateAdjustment001Dto.class);
	}
	
    @SuppressWarnings("unchecked")
    public Map<String,String> sendMdateAdjustmentSale(List<String> finProdIds) {
        return restTemplate.postForObject(
                ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendMdateAdjustmentSale", finProdIds,
                Map.class);
    }

}
