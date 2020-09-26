package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dao.ValPublicDataImport001Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValInterflogExt;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValPublicDataImport001Service extends CommonExportService<ValPublicDataImport001Dto>{
	
	@Autowired
	private ValPublicDataImport001Mapper valPublicDataImport001Mapper;
	
	
	public List<ValPublicDataImport001Dto> getInterGroup(Map<String,Object> params){
		
		return valPublicDataImport001Mapper.getInterGroup(params);
	}
	
	public List<ValInterflogExt> getValInterflogDetail(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getValInterflogDetail(params);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport003/searchList",
                params, ValInterflogExt.class);
	}
	
	public List<ValPublicDataImport001LogDto> getInterActionLogDetail(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getInterActionLogDetail(params);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport002/searchList",
                params, ValPublicDataImport001LogDto.class);
	}
	
	
	
	/**
	 * 获取任务组
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getInterGroupCode(Map<String,Object> params){
		
//		return valPublicDataImport001Mapper.getInterGroupCode(params);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport001/getInterGroupCode",
                params, SelectOptionDto.class);
	}
	
	/**
	 * 获取界面日志信息
	 * @param params
	 * @return
	 */
	public List<ValPublicDataImport001LogDto> getInterActionLog(Map<String, Object> params){
//		return valPublicDataImport001Mapper.getInterActionLog(params);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport001/findLogPage",
                params, ValPublicDataImport001LogDto.class);
	}
	
	/**
	 * 手工停止
	 * @param params
	 * @return
	 */
	public int stopInterLog(Map<String, Object> params){
//		return valPublicDataImport001Mapper.stopInterLog(params);
		return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport001/stopInterf",
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValPublicDataImport001/runAs",
                params, int.class);
	}
	
	/**
     * 封装参数
     * 
     * @param listdto 接口任务
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
    
    public List<TreeNode> getTreeNodeList(List<ValPublicDataImport001Dto> list,int flag){
    	List<TreeNode> treeList = new ArrayList<TreeNode>();
    	List<TreeNode> menuList = new ArrayList<TreeNode>();

    	String p_flag = Integer.toString(flag);
    	Map<String,String> menuMap = new HashMap<String,String>();
    	Map<String,String> menuP = new HashMap<String,String>();

    	for (ValPublicDataImport001Dto dto : list) {
			TreeNode<ValPublicDataImport001Dto> node = new TreeNode<ValPublicDataImport001Dto>();
			node.setId(dto.getTmpEName());
			node.setName(dto.getTmpCName());
			node.setpId(dto.getTaskGroupEName());
			treeList.add(node);
			menuMap.put(dto.getTaskGroupEName(), dto.getTaskGroupCName());
			menuP.put(dto.getTaskGroupEName(), dto.getGroupEName());
			if(StringUtil.isNotEmpty(dto.getGroupEName())){
				menuMap.put(dto.getGroupEName(), dto.getGroupCName());
			}

		}
    	
    	for (Map.Entry<String, String> entity : menuMap.entrySet()) {
    		TreeNode<ValPublicDataImport001Dto> node = new TreeNode<ValPublicDataImport001Dto>();
			node.setId(entity.getKey());
			node.setName(entity.getValue());
			if(null!=menuP.get(entity.getKey())){
				node.setpId(menuP.get(entity.getKey()));
			}
    		menuList.add(node);
		}

    	treeList.addAll(menuList);
    	return treeList;
    }

}
