package com.joyintech.tams.invest.interf.service;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001LogDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TrdInterfDataImport001Service extends CommonExportService<TrdInterfDataImport001LogDto>{

	private static final String INTERF_PLACE_URL = "url_interf_place";



	public List<TrdInterfDataImport001LogDto> getInterActionLogDetail(Map<String,Object> params){
		//TODO
		return null;
	}



	/**
	 * 获取任务组
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getInterGroupCode(Map<String,Object> params){
		//TODO
		return null;
	}

	/**
	 * 获取界面日志信息
	 * @param params
	 * @return
	 */
	public List<TrdInterfDataImport001LogDto> getInterActionLog(Map<String, Object> params){

		List<TrdInterfDataImport001LogDto>  list = new ArrayList<TrdInterfDataImport001LogDto>();
//		AjaxResult result = restTemplate.postForObject(getUrl("03"), params, AjaxResult.class);
//		if(StringUtil.isNotEmpty(result)&&StringUtil.isNotEmpty(result.getData())) {
//			Map<String, Object> map = (Map) result.getData();
//			list.add(BeanUtil.mapToObject(map, TrdInterfDataImport001LogDto.class));
//		}
		AjaxResult result = restTemplate.postForObject(getUrl("04"), params, AjaxResult.class);
		if(StringUtil.isNotEmpty(result)&&StringUtil.isNotEmpty(result.getData())) {
			List<Map> resultData = (List<Map>)result.getData();
			resultData.forEach((map)->{
				TrdInterfDataImport001LogDto dto = new TrdInterfDataImport001LogDto();
				dto = BeanUtil.mapToObject(map, TrdInterfDataImport001LogDto.class);
				dto.setBusidate((String)params.get("queryDate"));
				dto.setOptdate((String)params.get("predate"));
				list.add(dto);
			});
		}
		return list;
	}

	/**
	 * 手工停止
	 * @param params
	 * @return
	 */
	public int stopInterLog(Map<String, Object> params){
		//TODO
		return 0;
	}


	/**
	 * 检查当前是否有正在运行中的批处理
	 * @param params
	 * @return
	 */
	public int checkInterLog(Map<String, Object> params){
		//TODO
		return 0;
	}




	public Page<SelectOptionDto> getFinprodId (Map<String, Object> map) {
		map.put("userId", SecurityUtil.getLoginUser().getUserName());
		return restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdIdInfo", map,
				SelectOptionDto.class);
	}


	@SuppressWarnings("rawtypes")
	public List<TreeNode> getTreeNodeList(Map<String,Object> params){
		AjaxResult result = restTemplate.postForObject(getUrl("01"), params, AjaxResult.class);
		List<TreeNode> res = new ArrayList<>();
		if(StringUtil.isNotEmpty(result)&&StringUtil.isNotEmpty(result.getData())) {
			List<Map> list = (List<Map>) result.getData();
			list.forEach((en) -> {
				TreeNode node = new TreeNode();
				node.setId((String) en.get("id"));
				node.setpId((String) en.get("pid"));
				node.setName((String) en.get("name"));
				if (StringUtil.equals((String) en.get("type"), "1")) {
					node.setParent(true);
				}
				res.add(node);
			});
		}

		return res;
	}

	public AjaxResult runAs(Map<String, Object> map) {

		AjaxResult result = restTemplate.postForObject(getUrl("02"), map, AjaxResult.class);

		return 	result;
	}

	private String getUrl(String infType) {
		String url = CacheUtil.getSysParam(INTERF_PLACE_URL);
		if(StringUtil.equals(infType,"01")) {
			//查询文件接口
			url = url+"/listRouterByGroupAndTrigger";
		}else if(StringUtil.equals(infType,"02")) {
			//执行接口
			url = url+"/callTrigger";
		}else if(StringUtil.equals(infType,"03")) {
			//日志接口POST
			url = url+"/getTriggerExecuteInfo";
		}else if(StringUtil.equals(infType,"04")) {
			//明细日志接口
			url = url+"/listTriggerExecuteRouterInfo";
		}
		return url;
	}

}
