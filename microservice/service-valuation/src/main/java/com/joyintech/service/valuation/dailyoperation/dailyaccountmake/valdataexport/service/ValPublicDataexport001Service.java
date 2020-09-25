package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataexport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataexport.dao.ValPublicDataexport001Mapper;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValInterflogExt;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValPublicDataImport001LogDto;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;

@Service(value="serviceValPublicDataexport001Service")
@Transactional(rollbackFor = Exception.class)
public class ValPublicDataexport001Service extends CommonExportService<ValPublicDataImport001Dto>{
	
	
	@Autowired
	private ValAccountHandle001Mapper valAccountHandle001Mapper;
	@Autowired
	private ValPublicDataexport001Mapper valPublicDataexport001Mapper;
	
	
	public List<ValPublicDataImport001Dto> getInterGroup(Map<String,Object> params){
		
		return valPublicDataexport001Mapper.getInterGroup(params);
	}
	
	public List<ValInterflogExt> getValInterflogDetail(Map<String,Object> params){
		
		return valPublicDataexport001Mapper.getValInterflogDetail(params);
	}
	
	public List<ValPublicDataImport001LogDto> getInterActionLogDetail(Map<String,Object> params){
		
		return valPublicDataexport001Mapper.getInterActionLogDetail(params);
	}
	
	
	
	/**
	 * 获取任务组
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getInterGroupCode(Map<String,Object> params){
		
		return valPublicDataexport001Mapper.getInterGroupCode(params);
	}
	
	/**
	 * 获取界面日志信息
	 * @param params
	 * @return
	 */
	public List<ValPublicDataImport001LogDto> getInterActionLog(Map<String, Object> params){
		return valPublicDataexport001Mapper.getInterActionLog(params);
	}
	
	/**
	 * 手工停止
	 * @param params
	 * @return
	 */
	public int stopInterLog(Map<String, Object> params){
		return valPublicDataexport001Mapper.stopInterLog(params);
	}
	
	
	/**
	 * 检查当前是否有正在运行中的批处理
	 * @param params
	 * @return
	 */
	public int checkInterLog(Map<String, Object> params){
		return valPublicDataexport001Mapper.checkInterLog(params);
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
                                       String endDate,String booksetId) {
        Map<String, String> urlParams = new HashMap<>();

        String date = "";
        if(datas.length()>0){
        	date=datas.substring(0, datas.length()-1);
        }
        urlParams.put(InterfCommonConstant.接口_运行接口账套ID, booksetId);
        urlParams.put(InterfCommonConstant.接口_运行接口名称, date);
        urlParams.put(InterfCommonConstant.接口_对接系统ID, DictCodeInterfList.接口对接系统ID_资管.getValue());
        urlParams.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_发送.getValue());
        if (DateUtil.isSameDate(DateUtil.parse(startDate), DateUtil.parse(endDate))) {
            urlParams.put(InterfCommonConstant.接口_接口时间, startDate);
        }
        else {
            urlParams.put(InterfCommonConstant.接口_开始日, startDate);
            urlParams.put(InterfCommonConstant.接口_结束日, endDate);
        }
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
    /**
     * 校验当前产品是否存在在途跑批业务
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public int checkExecute(Map<String, Object> map) {
        String booksetId = (String)map.get("booksetId");
        
        String[] booksetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
            booksetIdArr = booksetId.split(",");
        }
        if(booksetIdArr==null){
        	return -1;
        }
        map.put("booksetIdArr", booksetIdArr);
        return valAccountHandle001Mapper.checkExecute(map);
    }
}
