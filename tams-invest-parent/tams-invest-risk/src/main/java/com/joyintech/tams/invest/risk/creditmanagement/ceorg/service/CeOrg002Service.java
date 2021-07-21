package com.joyintech.tams.invest.risk.creditmanagement.ceorg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CeOrg002Service
 * @since 1.0
 */
@Service
public class CeOrg002Service extends CommonExportService<CreditLinesOrgDto>
		implements BaseProcessService<CreditLinesOrgDto> {

	private String quote_rank = "01";
	
	@Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

	 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(CreditLinesOrgDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	            // modify by yushilong 新建画面入库逻辑变更 20200702 start
				entity.setSeqno(getQuotePrimaryKey());
	            if(StringUtil.isNotEmpty(entity.getCreditLabelItem())){
	        		for(CreditConfInfoDto dto:entity.getCreditLabelItem()){
	        			dto.setSeqno(getQuotePrimaryKey());
	        		}
	        		
	        	}
				// modify by yushilong 新建画面入库逻辑变更 20200702 end
	        }
	        else {
	            action = "update";
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/" + action, entity,
	            Integer.class);

	}
	
	private String getQuotePrimaryKey(){
		if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
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
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getAllCeOrgQuote?rank=" + quote_rank,
				TreeNode.class);
		profitList = restTemplate.getForList(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getParentCeOrgQuote?rank=" + quote_rank,
				TreeNode.class);

		result.addAll(profitList);
		result.addAll(treeProList);
		return result;

	}
	
	public Page<CreditConfInfoDto> getTreeCeOrgQuote(Map<String, Object> pageParam) {
		pageParam.put("rank", quote_rank);
		return restTemplate.postForPage(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getTreeCeOrgQuote",pageParam,
				CreditConfInfoDto.class);
	}
	
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
    public CreditLinesOrgDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/queryCeOrgBySeqno",
            map, CreditLinesOrgDto.class);
    }
	
    /**
     * 新建场合时查询时间区间是否重复
     * @param
     */
    public int searchRepeatByVmDate(String seqno,String customerId,Date vdate,String creditMstId){
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("customerId", customerId);
         map.put("seqno", seqno);
         map.put("vdate", DateUtil.format(vdate,"yyyy-MM-dd"));
         map.put("creditMstId",creditMstId);
    	 return restTemplate.postForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/searchRepeatByVmDate",map,
    	             Integer.class);
    }
    
    /**
     * 新建场合时查询时间区间是否重复
     * @param
     */
    public int searchRepeatByVmDateArea(String seqno,String customerId,Date vdate,Date mdate){
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("customerId", customerId);
         map.put("seqno", seqno);
         map.put("vdate", DateUtil.format(vdate));
         map.put("mdate", DateUtil.format(mdate));
    	 return restTemplate.postForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/searchRepeatByVmDateArea",map,
    	             Integer.class);
    }
    /**
     * 新建或修改场合时查询批复号是否重复
     * @param pflsh
     */
    public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId){
    	 return restTemplate.getForObject(
    	            ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/searchRepeatByPflsh?pflsh="+pflsh+"&seqno="+seqno+"&creditMstId="+creditMstId,
    	             Integer.class);
    }


    //如果当前数据额度已被占用则不允许操作
	public boolean searchIsUse(String seqno, String customerId, Date vdate, Date mdate, String pflsh) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", customerId);
		map.put("pflsh", pflsh);
		map.put("seqno", seqno);
		map.put("vdate", DateUtil.format(vdate));
		map.put("mdate", DateUtil.format(mdate));
		return restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/searchIsUse",map,
				Boolean.class);
	}
	
	public String getOrgInfo(String customerId){
		return restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getOrgInfo",customerId,
				String.class);
	}
	
	
    public Page<CreditConfInfoDto> getCreditConfig(Map<String, Object> pageParam)
            throws Exception {
			return restTemplate.postForPage(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/getCreditConfig",
				pageParam, CreditConfInfoDto.class);
        }
	// add by yushilong 查询是否重复校验 20200629 start
	/**
	 * 新建或修改场合时查询数据是否重复
	 * @param map
	 */
	public int searchRepeat(Map<String, Object> map){
		return restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/CeOrg002/searchRepeat",map,
				Integer.class);
	}
	// add by yushilong 查询是否重复校验 20200629 end
}
