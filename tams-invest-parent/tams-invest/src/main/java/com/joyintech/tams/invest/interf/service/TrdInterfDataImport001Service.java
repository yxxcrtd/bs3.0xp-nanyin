package com.joyintech.tams.invest.interf.service;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.interf.dao.TrdInterfDataImport001Mapper;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001Dto;
import com.joyintech.tams.invest.interf.dto.TrdInterfDataImport001LogDto;
import com.joyintech.tams.invest.interf.dto.ValInterflogExt;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TrdInterfDataImport001Service extends CommonExportService<TrdInterfDataImport001Dto>{

	private static final String INTERF_PLACE_URL = "url_interf_place";

	@Autowired
	private TrdInterfDataImport001Mapper valPublicDataImport001Mapper;
	

	
	public List<ValInterflogExt> getValInterflogDetail(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getValInterflogDetail(params);
		return restTemplate.postForList(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport003/searchList",
                params, ValInterflogExt.class);
	}
	
	public List<TrdInterfDataImport001LogDto> getInterActionLogDetail(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getInterActionLogDetail(params);
		return restTemplate.postForList(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport002/searchList",
                params, TrdInterfDataImport001LogDto.class);
	}
	
	
	
	/**
	 * 获取任务组
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getInterGroupCode(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getInterGroupCode(params);
		return restTemplate.postForList(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport001/getInterGroupCode",
                params, SelectOptionDto.class);
	}
	
	/**
	 * 获取界面日志信息
	 * @param params
	 * @return
	 */
	public List<TrdInterfDataImport001LogDto> getInterActionLog(Map<String, Object> params){
//		return valPublicDataImport001Mapper.getInterActionLog(params);
		return restTemplate.postForList(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport001/findLogPage",
                params, TrdInterfDataImport001LogDto.class);
	}
	
	/**
	 * 手工停止
	 * @param params
	 * @return
	 */
	public int stopInterLog(Map<String, Object> params){
//		return valPublicDataImport001Mapper.stopInterLog(params);
		return restTemplate.postForObject(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport001/stopInterf",
                params, int.class);
	}
	
	
	/**
	 * 检查当前是否有正在运行中的批处理
	 * @param params
	 * @return
	 */
	public int checkInterLog(Map<String, Object> params){
//		return valPublicDataImport001Mapper.checkInterLog(params);
		return restTemplate.postForObject(
                ServiceApplication.INTERF_PLACE_SERVICE_NAME + "/ValPublicDataImport001/runAs",
                params, int.class);
	}
	
	/**
     * 封装参数
     * 
     * @param
     * @param startDate 开始日
     * @param endDate 结束日
     * @return urlParams
     * @see
     * @since 1.0
     */
    public Map<String, String> getParm(String datas, String startDate,
                                       String endDate, String booksetId,String valModel) {
        Map<String, String> urlParams = new HashMap<>();

        String date = "";
        if(datas.length()>0){
        	date=datas.substring(0, datas.length()-1);
        }
        
        urlParams.put(InterfCommonConstant.接口_运行接口名称, date);
        urlParams.put(InterfCommonConstant.接口_对接系统ID, DictCodeInterfList.接口对接系统ID_资管.getValue());
        urlParams.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_接收.getValue());
        urlParams.put(InterfCommonConstant.接口_运行接口账套ID, booksetId);//modify by dongjun on 20190104
        urlParams.put(InterfCommonConstant.接口模式, valModel);//modify by dongjun on 20190104
        urlParams.put("INSTITUTION_CODE", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));//modify by liupengfei on 20200706
        if (DateUtil.isSameDate(DateUtil.parse(startDate), DateUtil.parse(endDate))) {
            urlParams.put(InterfCommonConstant.接口_接口时间, startDate);
        }
        else {
            urlParams.put(InterfCommonConstant.接口_开始日, startDate);
            urlParams.put(InterfCommonConstant.接口_结束日, endDate);
        }
        return urlParams;
    }

	/**
	 * 封装参数
	 * add by wujie on 20190521
	 */
	public Map<String, String> getParm(String datas, String startDate,
									   String endDate, String booksetId,String valModel,
									   String batchBooksetType,
									   String booksetIds) {

		Map<String, String> urlParams = this.getParm(datas, startDate, endDate, booksetId, valModel);
		urlParams.put(InterfCommonConstant.接口_界面账套选择类型, batchBooksetType);
		urlParams.put(InterfCommonConstant.接口_界面选择账套或账套组, booksetIds);
		return urlParams;
	}

	public Page<SelectOptionDto> getFinprodId (Map<String, Object> map) {
		map.put("userId", SecurityUtil.getLoginUser().getUserName());
		return restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdIdInfo", map,
				SelectOptionDto.class);
	}


	@SuppressWarnings("rawtypes")
	public List<TreeNode> getTreeNodeList(Map<String,Object> params){
		AjaxResult result = restTemplate.postForObject(getUrl(), params, AjaxResult.class);
		List<Map> list = (List<Map>)result.getData();
		List<TreeNode> res = new ArrayList<>();
		list.forEach((en) ->{
			TreeNode node = new TreeNode();
			node.setId((String)en.get("id"));
			node.setpId((String)en.get("pid"));
			node.setName((String)en.get("name"));
			if(StringUtil.equals((String)en.get("type"),"1")) {
				node.setParent(true);
			}
			res.add(node);
		});

		return res;
	}

	public void runAs(Map<String, Object> map) {
		AjaxResult result = restTemplate.postForObject("http://192.168.90.208:1666/bridge/datarouter/http/json/callTrigger", map, AjaxResult.class);
	}

	private String getUrl() {
		return CacheUtil.getSysParam(INTERF_PLACE_URL);
	}


}
