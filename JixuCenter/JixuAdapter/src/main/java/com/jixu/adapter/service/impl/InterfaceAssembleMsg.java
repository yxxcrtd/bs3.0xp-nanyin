package com.jixu.adapter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.InterfaceFieldsService;
import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.ResultContainer;

/** 
 * @ClassName 		InterfaceAssembleMsg
 * @author  		Pan Wei 
 * @Date 			2016年12月6日 下午4:48:57 	
 * @Description		[]  
 */

@Service("interfaceAssembleMsg")
public class InterfaceAssembleMsg extends InterfaceFieldsService{
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceAssembleMsg.class);
	
	@Autowired
	private InterfaceColConfigDao interfaceColConfigDao;
	
	/**
	 * 根据接口配置,读取源文件后,自动生成msg
	 * @param source
	 */
	public ResultContainer genMsg(InterfaceSource source, List<List<Object>> dataContainer, HashMap<String, Object> initGlobalValHM){
		ResultContainer container=new ResultContainer();
		
		int filterNum=0;
		
		List<InterfaceColConfig> colcfgList1=interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(), new int[]{InterfaceConfig.baseType});
		List<InterfaceColConfig> colcfgList2=interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(), 
				new int[]{InterfaceConfig.advanceType,InterfaceConfig.globalType,InterfaceConfig.groupType,InterfaceConfig.groupAdvanceType});
		List<InterfaceColConfig> colcfgList3=interfaceColConfigDao.loadCfgOrderByTypeAndID(source.getSourceID(), new int[]{InterfaceConfig.filterType});
		List<InterfaceColConfig> colcfgList=new ArrayList<InterfaceColConfig>();
		colcfgList.addAll(colcfgList1);
		colcfgList.addAll(colcfgList2);
		colcfgList.addAll(colcfgList3);
		
		Set<InterfaceMsgColMap> msgcolMapSet=source.getMsgColMap();
		if(msgcolMapSet==null){
			return container;
		}
		
		AdditionFunc func=null;
		Object field="";
		boolean filterFlag=true;

		List<HashMap<String, Object>> sourceList=new ArrayList<HashMap<String,Object>>();
		logger.debug("Start Basic................................................................................");
		
		for(List<Object> fieldsList:dataContainer){
			HashMap<String, Object> colcfgMap=new HashMap<String, Object>();
			for(InterfaceColConfig colcfg:colcfgList){
				func=null;
				if(colcfg.getFuncClass()!=null){
					//获取接口类
					func=ineterfaceBeanService.getAdditionFuncBean(colcfg.getFuncClass());
				}
				
				if(colcfg.getColType()==InterfaceConfig.baseType){
					if(fieldsList.size()<colcfg.getColNO()){
						//如果列的数量小于输入位数,填入空值
						colcfgMap.put(colcfg.getColname(), "");
						continue;
					}
					
					//获取基础列
					field=fieldsList.get(colcfg.getColNO()-1);
					
					if(colcfg.getColStartNO()>=0){
						try{
							byte[] byteFields=field.toString().getBytes(FileEncodeType.optionHM.get(source.getEnCodeType()));
							field=new String(byteFields, colcfg.getColStartNO(), colcfg.getColEndNo()-colcfg.getColStartNO(), FileEncodeType.optionHM.get(source.getEnCodeType()));
						}catch(Exception ue){
							logger.error("Error", ue);
							container.setCorrectFlag(false);
							StringBuilder builder=new StringBuilder();
							builder.append("Error: ");
							builder.append("\r\n");
							builder.append("Column: ");
							builder.append(colcfg.getColname());
							builder.append("\r\n");
							builder.append("Column StartNum: ");
							builder.append(colcfg.getColStartNO());
							builder.append("\r\n");
							builder.append("Column EndNum: ");
							builder.append(colcfg.getColEndNo());
							builder.append("\r\n");
							builder.append("Source Fields: ");
							builder.append(field);
							builder.append("\r\n");
							builder.append(ue.getMessage());
							container.setErrorMsg(builder.toString());
							return container;
						}
						
					}
					//核对基础列是否符合需求
					if(func==null || func.validateFiled(field)){
						colcfgMap.put(colcfg.getColname(), field);
					}else{
						//container.setCorrectFlag(false);
						StringBuilder builder=new StringBuilder();
						builder.append("Error: ");
						builder.append("\r\n");
						builder.append("Column Num: ");
						builder.append(colcfg.getColNO());
						builder.append("\r\n");
						builder.append("Column Value: ");
						builder.append(field);
						builder.append("\r\n");
						builder.append("Func name: ");
						builder.append(colcfg.getFuncClass());
						builder.append("\r\n");
						builder.append(func.fundUsefulDesc());
						container.setCorrectFlag(false);
						container.setErrorMsg(builder.toString());
						//return container;
						filterFlag=false;
						break;
					}
				}
			}
			
			if(filterFlag){
				for(InterfaceColConfig colcfg:colcfgList){
					if(colcfg.getFilterFuncClass()!=null){
						func=ineterfaceBeanService.getAdditionFuncBean(colcfg.getFilterFuncClass());
						if(func!=null){
							try{
								//筛选功能
								if(colcfgMap.get(colcfg.getColname())!=null){
									if(!func.filterField(colcfgMap.get(colcfg.getColname()), colcfg.getFilterFieldValue())){
										filterFlag=false;
										break;
									}
								}
							}catch(Exception e){
								logger.error("Error", e);
								container.setCorrectFlag(false);
								StringBuilder builder=new StringBuilder();
								builder.append("FuncName: ");
								builder.append(func.getFuncName());
								builder.append("\r\n");
								builder.append("ColumnName: ");
								builder.append(colcfg.getColname() );
								builder.append("\r\n");
								builder.append("ColumnName Value: ");
								builder.append(colcfgMap.get(colcfg.getColname()));
								builder.append("\r\n");
								builder.append("Column Value: ");
								builder.append(colcfg.getFilterFieldValue());
								container.setErrorMsg(builder.toString() + "		" + e.toString());
								return container;
							}
						}
					}
				}
			}
			
			logger.debug("Basic Column: " + colcfgMap);
			
			if(filterFlag){
				sourceList.add(colcfgMap);
			}else {
				filterNum++;
			}
			filterFlag=true;
		}
			
		
		List<HashMap<String, Object>> newSourceList=new ArrayList<HashMap<String,Object>>();
		try{
			newSourceList=genAdvanceMsg(sourceList, colcfgList, initGlobalValHM);
			filterNum = filterNum + (sourceList.size()-newSourceList.size());
		}catch(Exception e){
			logger.error("Error", e);
			AdditionFunc addExe=exception.getExceptionClass(e);
			container.setCorrectFlag(false);
			if(addExe==null){
				container.setErrorMsg(e.toString());
			}else{
				StringBuilder sb=new StringBuilder();
				sb.append(e.toString());
				sb.append("		\r\n");
				sb.append(addExe.getFuncName());
				sb.append(":	");
				sb.append(addExe.fundUsefulDesc());
				container.setErrorMsg(sb.toString());
			}
			return container;
		}
		
		boolean dbinsertFlag=false;
		String targeTtablename=source.getTargeDBTable();
		if(targeTtablename!=null && !targeTtablename.trim().equals("")) 
			dbinsertFlag=true;
		
		if(dbinsertFlag) {
			List<HashMap<String, Object>> dataList=new ArrayList<HashMap<String,Object>>();
			for(HashMap<String, Object> newSourceHM:newSourceList){
				Iterator<InterfaceMsgColMap> msgcolIter=msgcolMapSet.iterator();
				HashMap<String, Object> preDataMap=new HashMap<String, Object>();
				while(msgcolIter.hasNext()){
					InterfaceMsgColMap msgcolmap=msgcolIter.next();
					//创建msg,用计算出来的字段,替换msg里面的字段
					//msg=msg.replace(InterfaceConfig.fieldStart+msgcolmap.getMsgCol()+InterfaceConfig.fieldEnd, newSourceHM.get(msgcolmap.getColConfig().getColname()));
					preDataMap.put(msgcolmap.getMsgCol(), newSourceHM.get(msgcolmap.getColConfig().getColname()));
				}
				dataList.add(preDataMap);
				
				//debug,打印msg信息
				//logger.info(msg);
			}
			container.setEntityObj(dataList);
		}else {
			List<String> msgList=new ArrayList<String>();
			String msg="";
			for(HashMap<String, Object> newSourceHM:newSourceList){
				msg=source.getMsg();
				Iterator<InterfaceMsgColMap> msgcolIter=msgcolMapSet.iterator();
				
				while(msgcolIter.hasNext()){
					InterfaceMsgColMap msgcolmap=msgcolIter.next();
					//创建msg,用计算出来的字段,替换msg里面的字段
					msg=msg.replace(InterfaceConfig.fieldStart+msgcolmap.getMsgCol()+InterfaceConfig.fieldEnd, newSourceHM.get(msgcolmap.getColConfig().getColname()).toString());
				}
				
				msgList.add(msg);
				
				//debug,打印msg信息
				logger.info(msg);
			}
			container.setEntityObj(msgList);
		}
		
		container.setObjID(filterNum);
		return container;
	}
}
