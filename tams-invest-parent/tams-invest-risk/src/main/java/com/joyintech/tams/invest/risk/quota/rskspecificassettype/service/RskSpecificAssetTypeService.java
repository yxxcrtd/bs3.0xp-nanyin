package com.joyintech.tams.invest.risk.quota.rskspecificassettype.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import com.joyintech.tams.invest.risk.quota.rskspecificassettype.dto.RskSpecificAssetTypeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * 特定资产类型设置
 *
 * @author zhangnian
 * @see RskSpecificAssetTypeService
 * @since 1.0
 */
@Service
public class RskSpecificAssetTypeService extends CommonExportService<RskSpecificAssetTypeDto>
		implements BaseProcessService<RskSpecificAssetTypeDto> {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(RskSpecificAssetTypeService.class);

	@Autowired
	private RiskRefreshRedisService riskRefreshRedisService;

	  /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RskSpecificAssetTypeDto> selectForPage(Map<String, Object> map){
		if (StringUtil.isNotEmpty(map.get("type1"))) {
			map.put("type1", StringUtil.splitToList(null, map.get("type1").toString()));
		}else{
			map.remove("type1");
		}
		if (StringUtil.isNotEmpty(map.get("type2"))) {
			map.put("type2", StringUtil.splitToList(null, map.get("type2").toString()));
		}else{
			map.remove("type2");
		}
		if (StringUtil.isNotEmpty(map.get("type3"))) {
			map.put("type3", StringUtil.splitToList(null, map.get("type3").toString()));
		}else{
			map.remove("type3");
		}
		if (StringUtil.isNotEmpty(map.get("type4"))) {
			map.put("type4", StringUtil.splitToList(null, map.get("type4").toString()));
		}else{
			map.remove("type4");
		}
        Page<RskSpecificAssetTypeDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_SERVICE_NAME + "/RskSpecificAssetType/findPage",
                map, RskSpecificAssetTypeDto.class);
        return page;
    }

	@Override
	public void doComplete(RskSpecificAssetTypeDto entity, OperationType type) {
		String action = StringUtil.EMPTY;
		Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
		entity.setBranch(branch);
		if (OperationType.DELETE.equals(type)) {
			action = "delete";
		}
		else if (OperationType.NEW.equals(type)) {
			action = "insert";
		}else{
			action = "update";
		}
		restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME + "/RskSpecificAssetType/" + action, entity,
				Integer.class);
		if (CommonUtil.isType(CacheUtil.getSysParam(RiskUtil.PAGE_REDIS_FLAG), BussinessDictCodeListRds.是否_是)) {
				try {
					logger.info("===========特定资产类型更新资产redis开始=============");
					ImplParaRds implParaRds=new ImplParaRds();
					implParaRds.setCommonBusinessType(BussinessDictCodeListRds.共同业务类型_特定资产类型设置.getValue());
					riskRefreshRedisService.refreshParamRedis(implParaRds);
					logger.info("===========特定资产类型更新资产redis结束=============");
				}catch (Exception e){
					//异常无需抛出，在内部处理掉
					logger.error("特定资产类型更新资产redis异常",e);
				}

		}
	}


	/**
	 * 导出
	 */
	@Override
	public void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/risk/quota/rskspecificassettype/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
	}
	
	@Override
	public Page<RskSpecificAssetTypeDto> selectPageData(Map<String, Object> selectParam) {
		return selectForPage(selectParam);
		/* Page<RskSpecificAssetTypeDto> page = restTemplate.postForPage(
	                ServiceApplication.RISK_SERVICE_NAME + "/RskSpecificAssetType/findPage",
	                selectParam, RskSpecificAssetTypeDto.class);
		 
	        return page;*/
	}

	public RskSpecificAssetTypeDto getBasicInfo(String specificId){
		return restTemplate.getForObject(ServiceApplication.RISK_SERVICE_NAME
						+ "/RskSpecificAssetType/getBasicInfo?specificId="
						+ specificId,
				RskSpecificAssetTypeDto.class);
	}

	public Page<SelectOptionDto> getAssetIdOption(Map<String, Object> map) {
		return restTemplate.postForPage(
				ServiceApplication.RISK_SERVICE_NAME + "/RskSpecificAssetType/getAssetIdOption",
				map, SelectOptionDto.class);
	}

	public String getUUid() {
		UUID uuid = UUID.randomUUID();
		String id = (uuid.toString()).replace("-", "").substring(0, 32);
		return id;
	}

	public int  getRepeatCount(Map<String,Object> param){
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/RskSpecificAssetType/getRepeatCount", param,
                Integer.class);
    }
}
