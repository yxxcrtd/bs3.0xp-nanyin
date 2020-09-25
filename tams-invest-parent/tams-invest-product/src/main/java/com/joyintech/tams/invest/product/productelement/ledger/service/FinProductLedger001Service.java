/*
 * 文件名：FinProductLedger001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.ledger.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.SysDataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.ledger.dto.FinProductLedger001Dto;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductLedger001Service
 * @since 1.0
 */
@Service
public class FinProductLedger001Service extends CommonExportService<FinProductLedger001Dto> {
    /**
     * {@link MstWorkflowCommonService}
     */
	@Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 查询排期所有数据
     * 
     * @param map 参数map
     * @return 排期所有数据
     * @see
     * @since 1.0
     */
    public Page<FinProductLedger001Dto> selectForPage(Map<String, Object> map) {
        if (StringUtil.isNotEmpty(map.get("prodId"))) {
            map.put("prodId", StringUtil.splitToList(null, map.get("prodId").toString()));
        }
        if(StringUtil.isNotEmpty(map.get("issueStatus"))){
            map.put("issueStatus", StringUtil.splitToList(null, map.get("issueStatus").toString()));
        }
        if(StringUtil.isNotEmpty(map.get("durationStatus"))){
            map.put("durationStatus", StringUtil.splitToList(null, map.get("durationStatus").toString()));
        }
        if(StringUtil.isNotEmpty(map.get("prodManager"))){
            map.put("prodManager", StringUtil.splitToList(null, map.get("prodManager").toString()));
        } else {
            map.put("prodManager",null);
        }
        if(StringUtil.isNotEmpty(map.get("targetCustomType"))){
            List<String> invList = StringUtil.splitToList(null, map.get("targetCustomType").toString());
            map.put("investorType", invList);
            for (int i = 0; i < invList.size(); i++) {
                map.put("investorType" + (i + 1), invList.get(i));
            }
        }
        if(StringUtil.isNotEmpty(map.get("riskLevel"))){
            map.put("riskLevel", StringUtil.splitToList(null, map.get("riskLevel").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("profitFlag"))) {
            map.put("profitFlag", StringUtil.splitToList(null, map.get("profitFlag").toString()));
        }
        if(StringUtil.isNotEmpty(map.get("operationType"))){
            map.put("operationType", StringUtil.splitToList(null, map.get("operationType").toString()));
        }
        if (StringUtil.isNotEmpty(map.get("issueType"))) {
            map.put("issueType", StringUtil.splitToList(null, map.get("issueType").toString()));
        }        
        if (StringUtil.isNotEmpty(map.get("groupId"))) {
            map.put("groupId", StringUtil.splitToList(null, map.get("groupId").toString()));
        }
        if(StringUtil.isNotEmpty(map.get("investNature"))){
            map.put("investNature", StringUtil.splitToList(null, map.get("investNature").toString()));
        }
		if(StringUtil.isNotEmpty(map.get("prodRegistCode"))){
            map.put("prodRegistCode", StringUtil.splitToList(null, map.get("prodRegistCode").toString()));
        }
        Date queryDate = iPlatformDate.getSysDate();
        if(StringUtil.isNotEmpty(map.get("queryDate")))
        	queryDate = DateUtil.parse((String)map.get("queryDate"));
        map.put("cashType", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("queryDateStart", DateUtil.format(DateUtil.getFirstDay(queryDate)));
        map.put("queryDateEnd", DateUtil.format(DateUtil.getLastDay(queryDate)));
        map.put("profitFlag", map.get("profitFlag"));
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        Page<FinProductLedger001Dto> pageDto = restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductLedger/selectForLedger", map,
                FinProductLedger001Dto.class);
        List<FinProductLedger001Dto> ldto = pageDto.getRoot();
        
        List<FinProductLedger001Dto> rlist = new ArrayList<FinProductLedger001Dto>();
        for (FinProductLedger001Dto mdto : ldto) {
    		long remainDays = DateUtil.getBetweenDayNumber(queryDate,mdto.getMdate());
    		mdto.setRemainDays(remainDays);
    		if(mdto.getProdUnitValue()!=null && BigDecimal.ZERO.compareTo(mdto.getProdUnitValue())!=0
    				&&mdto.getSubDealNum()!=null && BigDecimal.ZERO.compareTo(mdto.getSubDealNum())!=0) {
        		BigDecimal prodAssetAmt = ArithmeticUtil.multiply(mdto.getSubDealNum(), mdto.getProdUnitValue());
    			mdto.setProdUnitValue(ArithmeticUtil.round(mdto.getProdUnitValue(),2,null));
        		mdto.setProdAssetAmt(prodAssetAmt);
			}
			else {
				mdto.setProdUnitValue(null);
				mdto.setProdAssetAmt(null);
			}
        	
            //销售渠道
            List<String> slist = new ArrayList<String>();
            List<String> saleChannelList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getSaleChannel());
            for (int i = 0; i < saleChannelList.size(); i++ ) {
                if (StringUtil.isNotEmpty(saleChannelList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.销售渠道, saleChannelList.get(i));
                    slist.add(str);
                }
            }
            String saleChannel = StringUtil.joinString(slist, GlobalConstant.COMMA);
            mdto.setSaleChannel(saleChannel);
            
            //销售部门
            List<String> slist1 = new ArrayList<String>();
            List<String> saleDepartmentList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getSaleDepartment());
            for (int i = 0; i < saleDepartmentList.size(); i++ ) {
                if (StringUtil.isNotEmpty(saleDepartmentList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartmentList.get(i));
                    slist1.add(str);
                }
            }
            String saleDepartment = StringUtil.joinString(slist1, GlobalConstant.COMMA);
            mdto.setSaleDepartment(saleDepartment);

            //目标客户类型
            List<String> slist2 = new ArrayList<String>();
            List<String> repInvestorTypeList = StringUtil.splitToList(GlobalConstant.COMMA,
                    mdto.getRepInvestorType());
            for (int i = 0; i < repInvestorTypeList.size(); i++ ) {
                if (StringUtil.isNotEmpty(repInvestorTypeList.get(i))) {
                    String str = SysDataUtil.getNameFromDataDict(DictCode.目标客户类型, repInvestorTypeList.get(i));
                    slist2.add(str);
                }
            }
            String repInvestorType = StringUtil.joinString(slist2, GlobalConstant.COMMA);
            mdto.setRepInvestorType(repInvestorType);
            mdto.setRateType(SysDataUtil.getNameFromDataDict(DictCode.利率类型,mdto.getRateType()));
            
            rlist.add(mdto);
        }
        pageDto.setRoot(rlist);
        return pageDto;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/product/productelement/ledger/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductLedger001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdIdInfo", map,
            SelectOptionDto.class);
    }
  
}
