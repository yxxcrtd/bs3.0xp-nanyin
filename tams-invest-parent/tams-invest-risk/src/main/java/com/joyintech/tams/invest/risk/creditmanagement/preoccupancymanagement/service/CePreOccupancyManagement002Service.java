/*
 * 文件名：CePreOccupancyManagement002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.CreditLinesPre;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.invest.risk.creditmanagement.preoccupancymanagement.dto.CePreOccupancyManagementDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author 裴家俊
 * @version 1.0 2019年1月8日
 * @see CePreOccupancyManagement002Service
 * @since 1.0
 */
@Service
public class CePreOccupancyManagement002Service extends CommonExportService<CePreOccupancyManagementDto> implements BaseProcessService<CePreOccupancyManagementDto> {

	private String quote_rank = "01";
	
	@Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CePreOccupancyManagementDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	            if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
	        		for(CreditLinesPre dto:entity.getCreditQuoteItem()){
	        			dto.setSeqno(getQuotePrimaryKey());
	        		}
	        	}
	        }
	        else {
	            action = "update";
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/" + action, entity,
	            Integer.class);

	}
	
	private String getQuotePrimaryKey(){
		if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_预占用管理);
            return baseGetBusinessId.buildBussinessCode(codeParameter);
        }
        else {
        	return IDGenerator.generate();
        }
	}
	
	@SuppressWarnings({ "rawtypes"})
	public List<TreeNode> getAllCeOrgQuote(String type,String indId) {

		List<TreeNode> profitList = new ArrayList<TreeNode>();
		List<TreeNode> result = new ArrayList<TreeNode>();
		List<TreeNode> treeProList = new ArrayList<TreeNode>();
		treeProList = restTemplate.getForList(
				ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/getAllCeOrgQuote?rank=" + quote_rank,
				TreeNode.class);
		profitList = restTemplate.getForList(
				ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/getParentCeOrgQuote?rank=" + quote_rank,
				TreeNode.class);

		result.addAll(profitList);
		result.addAll(treeProList);
		return result;

	}
	
	public Page<CreditMstInfoDto> getTreeCeOrgQuote(Map<String, Object> pageParam) {
		pageParam.put("rank", quote_rank);
		return restTemplate.postForPage(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getTreeCeOrgQuote",pageParam,
				CreditMstInfoDto.class);
	}
	
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
    public CePreOccupancyManagementDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/queryCeOrgBySeqno",
            map, CePreOccupancyManagementDto.class);
    }
	
    /**
     * 新建场合时查询时间区间是否重复
     * @param name
     */
    public int searchRepeatByVmDate(String seqno,String customerId,Date vdate){
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("customerId", customerId);
         map.put("seqno", seqno);
         map.put("vmdate", DateUtil.format(vdate));
    	 return restTemplate.postForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/searchRepeatByVmDate",map,
    	             Integer.class);
    }
    
    /**
     * 新建场合时查询时间区间是否重复
     * @param name
     */
    public int searchRepeatByVmDateArea(String seqno,String customerId,Date vdate,Date mdate){
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("customerId", customerId);
         map.put("seqno", seqno);
         map.put("vdate", DateUtil.format(vdate));
         map.put("mdate", DateUtil.format(mdate));
    	 return restTemplate.postForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/searchRepeatByVmDateArea",map,
    	             Integer.class);
    }
    
    /**
     * 新建或修改场合时查询批复号是否重复
     * @param pflsh
     */
    public int searchRepeatByPflsh(String pflsh,String seqno){
    	 return restTemplate.getForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CePreOccupancyManagement/searchRepeatByPflsh?pflsh="+pflsh+"&seqno="+seqno,
    	             Integer.class);
    }

}
