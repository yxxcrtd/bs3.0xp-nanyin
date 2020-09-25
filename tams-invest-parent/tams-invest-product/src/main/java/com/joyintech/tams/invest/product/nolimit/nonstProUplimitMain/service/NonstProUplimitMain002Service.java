package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * @program: NonstProUplimitMain002Service
 * @description: 非标比例上限维护
 * @author: cxq
 * @create: 2019-12-28
 **/
@Service
public class NonstProUplimitMain002Service extends BaseService<NonstProUplimitMain001Dto> implements BaseProcessService<NonstProUplimitMain001Dto>{
	//走流程方法流程
	@Override
	public void doComplete(NonstProUplimitMain001Dto entity, OperationType type) {
		if (type.name()=="NEW"){
	         this.insert(entity);
	     }else if (type.name()=="MODIFY"){
	    	 //由于是列表数据 存在tablelist里面 所以需要遍历
	    	 List<NonstProUplimitMain001Dto> tableList = entity.getTableList();
	    	 for (NonstProUplimitMain001Dto dto : tableList) {
	    		 entity.setNonStandardLimit(dto.getNonStandardLimit());
	    		 entity.setPrdPlanSerisId(dto.getPrdPlanSerisId());
	    		 entity.setPrdPlanSerisName(dto.getPrdPlanSerisName());
			}
	        this.update(entity);
	     }else {
	         restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/delete",entity,Integer.class);
	     }
	}
	//插入方法
	public Integer insert(NonstProUplimitMain001Dto entity) {
		return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/insert",entity,Integer.class);
	}
	//更新方法
	public Integer update(NonstProUplimitMain001Dto entity) {
		return  restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/update",entity,Integer.class);
	}
    /**
	 * 	根据系列id 查name
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */	
	public Page<NonstProUplimitMain001Dto> queryPrdPlanSerName(Map<String, Object> pageParam) {
		return restTemplate.postForPage(
	            ServiceApplication.PRODUCT_SERVICE_NAME + "/NonstProUplimitMain/queryPrdPlanSerName", pageParam,
	            NonstProUplimitMain001Dto.class);
	}
	
}
