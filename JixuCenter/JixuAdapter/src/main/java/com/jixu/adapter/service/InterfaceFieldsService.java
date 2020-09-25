package com.jixu.adapter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.adapter.service.impl.InterfaceExcpetion;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceColConfig;

/** 
 * @ClassName 		InterfaceAdvanceFieldOp
 * @author  		Pan Wei 
 * @Date 			2017年5月3日 上午11:23:25 	
 * @Description		[]  
 */

@Service("interfaceFieldsService")
public class InterfaceFieldsService {
	
	@Resource(name="dataInterfaceService")
	protected DataInterfaceService ineterfaceBeanService;
	
	@Resource(name="interfaceAdapterDao")
	protected InterfaceAdapterDao interfaceSourceDao;
	
	@Resource(name="InterfaceException")
	protected InterfaceExcpetion exception;
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceFieldsService.class);
	
	protected List<HashMap<String, Object>> genAdvanceMsg(List<HashMap<String, Object>> sourceHMList, List<InterfaceColConfig> colcfgList, HashMap<String, Object> initGlobalValHM) throws Exception{
		logger.debug("Start Advance................................................................................");
		
		AdditionFunc func=null;
		InterfaceColConfig groupCfg=null;
		boolean filterFlag=false;
		//用来存放分组的配置
		List<InterfaceColConfig> groupCfgList=new ArrayList<InterfaceColConfig>();
		List<InterfaceColConfig> unGroupCfgList=new ArrayList<InterfaceColConfig>();
		
		List<HashMap<String, Object>> newSourceList=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> globalVars=initGlobalValHM;
		int groupnum=0;
		
		for(HashMap<String, Object> sourceHM:sourceHMList){
			if(globalVars.size()>0){
				//如果存在全局变量,设置到准备的值中
				globalVars.forEach((k,v)->sourceHM.put(k, v));
			}
			
			for(int i=0;i<colcfgList.size();i++){
				InterfaceColConfig colcfg=colcfgList.get(i);
				if(colcfg.getColType()==InterfaceConfig.advanceType || colcfg.getColType()==InterfaceConfig.globalType){
					func=null;
					if(colcfg.getFuncClass()!=null){
						//获取接口类
						func=ineterfaceBeanService.getAdditionFuncBean(colcfg.getFuncClass());
					}
					//获取增强列
					if(func!=null){
						if(logger.isDebugEnabled()){
							logger.debug("Advance Input: " +func.getFuncName()+": " + colcfg.getFuncParams() + ": " + sourceHM);
						}
						
						if(colcfg.getColType()==InterfaceConfig.advanceType){
							//调用各个自定义接口计算方法,返回结果值
							Object returnAdv=func.calFunc(colcfg.getFuncParams(), sourceHM);
							sourceHM.put(colcfg.getColname(), returnAdv);
							logger.debug("Advance Return: " + colcfg.getColname() + ": " + returnAdv);
						}else if(colcfg.getColType()==InterfaceConfig.globalType){
							//如果返回值不为null,把返回值设置到全局变量
							Object globalresult=func.calFunc(colcfg.getFuncParams(), sourceHM);
							if(globalresult!=null){
								globalVars.put(colcfg.getColname(), globalresult);
							}
						}
					}
				}else if(colcfg.getColType()==InterfaceConfig.groupType){
					logger.debug("Group Flag.........................................................................");
					if(groupCfg==null){
						groupCfg=colcfg;
						groupnum=i;
					}
					if(groupCfgList.size()==0){
						//如果有分组类型,把分组
						boolean groupInnerFlag=true;
						for(int j=i+1;j<colcfgList.size();j++){
							InterfaceColConfig groupColcfg=colcfgList.get(j);
							if(groupColcfg.getColType()==InterfaceConfig.groupAdvanceType && groupInnerFlag){
								groupCfgList.add(groupColcfg);
							}else{
								//如果接下去的配置出现了第一个非分组配置,接下去所有的配置放入非分组配置
								unGroupCfgList.add(groupColcfg);
								groupInnerFlag=false;
							}
						}
					}
				}
				
				if(groupCfg!=null && i>=groupnum-1){
					//如果有一个Group配置,后面的cfg配置不采用
					break;
				}
			}
			
			filterFlag=true;
			for(InterfaceColConfig colcfg:colcfgList){
				if(colcfg.getFilterFuncClass()!=null){
					func=ineterfaceBeanService.getAdditionFuncBean(colcfg.getFilterFuncClass());
					if(func!=null){
						//筛选功能
						if(!func.filterField(sourceHM.get(colcfg.getColname()), colcfg.getFilterFieldValue())){
							filterFlag=false;
							break;
						}
					}
				}
			}
			
			logger.debug("Advance Column: " + sourceHM);
			
			if(filterFlag){
				newSourceList.add(sourceHM);
			}
		}
		
		if(groupCfgList.size()>0){
			logger.debug("Start Group+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			AdditionFunc groupFunc=ineterfaceBeanService.getAdditionFuncBean(groupCfg.getFuncClass());
			List<HashMap<String, Object>> newGroupSourceList=groupFunc.genGroupNewSourceData(newSourceList, groupCfg.getFuncParams(), groupCfgList);
			newSourceList=genAdvanceMsg(newGroupSourceList,unGroupCfgList, initGlobalValHM);
			logger.debug("End Group-------------------------------------------------------------------------------");
		}
		
		return newSourceList;
	}
}
