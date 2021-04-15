/*
 * 文件名：MstCustomerNatureService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcustomernature.service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.basicdata.mstcustomernature.dto.MstCustomerNatureDto;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 客服性质管理业务类 
 *〈一句话功能简述〉 
 *〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstCustomerNatureService
 * @since 1.0
 */
@Service
public class MstCustomerNatureService extends CommonExportService<MstCustomerNatureDto> implements BaseProcessService<MstCustomerNatureDto> {

    /**
     * doComplete{@inheritDoc}
     */
    @Override
    public void doComplete(MstCustomerNatureDto entity, OperationType type) {
//        Map<String,Object>map=new HashMap<>();
//        map.put("entity", entity);
//        map.put("type", type);
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/doComplete/" + type,
            entity, Integer.class);
    }

    /**
     * 客户名称的联想文本框的查询方法
     * @param param map
     * @return List
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCustomerName(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/getCustomerName",
            param, SuggestDto.class);
    }

    /**
     * 客户的联想文本框的查询方法
     * @param param map
     * @return List
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCustomerId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/getCustomerId",
            param, SuggestDto.class);
    }

    /**
     * 客户名称的联想文本框的查询方法
     * @param param map
     * @return List
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getName(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/getName",
            param, SuggestDto.class);
    }

    /**
     * 客服性质配置查询按钮的查询方法 <一句话功能简述> <功能详细描述>
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstCustomerNatureDto> searchMstCustomerNature(Map<String, Object> pageParam)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/searchMstCustomerNature",
            pageParam, MstCustomerNatureDto.class);
    }

    /**
     * 客服性质管理查询按钮的查询方法
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstCustomerNatureDto> selectCustomerInfo(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/selectCustomerInfo",
            pageParam, MstCustomerNatureDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstcustomernature/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstCustomerNatureDto> selectPageData(Map<String, Object> selectParam) {
        return selectCustomerInfo(selectParam);
    }

    /**
     * 通过主键的查询方法
     * @param param map
     * @return Page
     * @see
     * @since 1.0
     */
    public MstCustomerNatureDto selectByPrimaryKey(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/selectByPrimaryKey",
            param, MstCustomerNatureDto.class);
    }

    /**
     * check是否持有金融牌照
     * @param customerId
     * @return 
     * @see
     * @since 1.0
     */
	public String checkIsFinLicenceStr(String customerId) {
		  return restTemplate.postForObject(
		            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/checkIsFinLicenceStr",
		            customerId, String.class);
	}
	 /**
     * check同一个客户是否已有在流程中的客户性质
     * @param param customerId
     * @return 
     * @see
     * @since 1.0
     */
	public int checkwflstatus(Map<String, Object> param) {
		return restTemplate.postForObject(
	            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCustomerNature/checkwflstatus",
	            param, Integer.class);
	}
	
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<MstTradeOpponentDto> selectCounterIdByCustomer(Map<String, Object> param) {
		return restTemplate.postForList(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/selectCounterIdByCustomer", param, MstTradeOpponentDto.class);
	}
}
