package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.MonitorDictCode;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto.TyLineMonitorDailyDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.util.Column;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.util.ExcelTool;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

@Service
public class TyLineMonitorDailyService extends BaseService<TyLineMonitorDailyDto>  {
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(TyLineMonitorDailyService.class);
	
    private static final String[] BASE_TITLE_HEADS = new String[] {"主体名称","失效日","期限"};
    
    private static final String[] AMT_TITLE_HEADS = new String[] {"总额","已用","剩余"};
	
	public void getBaseTitleHead(List<Map<String,String>> titleList){
		 //主体名称， 失效日，期限
		for(int i = 0 ; i < BASE_TITLE_HEADS.length ; i++){
			 Map<String,String> titleMap=new HashMap<String,String>();
			 titleMap.put("id",String.valueOf(i+1));
			 titleMap.put("pid","0");
			 titleMap.put("content",BASE_TITLE_HEADS[i]);
			 titleMap.put("fielName",Enum.valueOf(MonitorDictCode.class,BASE_TITLE_HEADS[i]).getValue());
			 titleList.add(titleMap);
		}
	}
	
	/**
	 * 额度标题
	 * @param titleList
	 * @param creditTypeList
	 */
	public void getAmtTitleHead(List<Map<String,String>> titleList,List<SysDataDict> creditTypeDictList){
		
		for(int i = 0 ; i < creditTypeDictList.size() ; i++){
			SysDataDict dictItem = creditTypeDictList.get(i);
			String pid = String.valueOf(i+4);
			//额度标题
		    Map<String,String> titleMap=new HashMap<String,String>();
			 titleMap.put("id",pid);
			 titleMap.put("pid","0");
			 titleMap.put("content",dictItem.getItemValue());
			 titleMap.put("fielName",dictItem.getItemCode());
			 titleList.add(titleMap);
			 
			//总额度下面的分项标题
			for(int j = 0 ; j < AMT_TITLE_HEADS.length ; j++){
				 String fileName = dictItem.getItemCode() +"_"+ Enum.valueOf(MonitorDictCode.class,AMT_TITLE_HEADS[j]).getValue();
				 Map<String,String> titleTempMap=new HashMap<String,String>();
				 titleTempMap.put("id",pid +"_"+ String.valueOf(j));
				 titleTempMap.put("pid",pid);
				 titleTempMap.put("content",AMT_TITLE_HEADS[j]);
				 titleTempMap.put("fielName",fileName);
				 titleList.add(titleTempMap);
			
			}
		}
		
	}
	
	public List<SysDataDict> convertToTitleList(Map<String,String> dictItemMap){
		
		List<SysDataDict> dataDictList = new ArrayList<SysDataDict>();
		SysDataDict dataDict = new SysDataDict();
		dataDict.setItemCode("TOTAL");
		dataDict.setItemValue("总额度");
		dataDictList.add(dataDict);
		for(String key : dictItemMap.keySet()){	
			SysDataDict dataDictTemp = new SysDataDict();
			dataDictTemp.setItemCode(key);
			dataDictTemp.setItemValue(dictItemMap.get(key));
			dataDictList.add(dataDictTemp);
		}
		
		return dataDictList;
	}
	
	
	@PostMapping("queryAbsLineMonitor")
    public File querylineMonitorDailyList(LineMonitorDailyDto queryDto) throws Exception{
		File outFile = null;
		List<Map<String,String>> titleList=new ArrayList<Map<String,String>>();
		//设置基础标题（只有一级的标题）
		getBaseTitleHead(titleList);
		 
		//额度标题
		Map<String,String> dictItemMap = SysDatadictUtil.getSysDictDataMap(DictCode.额度分类);
		
		List<SysDataDict> creditTypeDictList =  convertToTitleList(dictItemMap);
		getAmtTitleHead(titleList,creditTypeDictList);
		
		String title = "";
		for(SysDataDict sysDataDict : creditTypeDictList){
			title = title + "," +sysDataDict.getItemCode();
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("cdate", DateUtil.format(queryDto.getCdate()));
		//为了保持与页面端组装的excel表头一致，才传的数据
    	param.put("title", title);
		
		List<TyLineMonitorDailyDto> resultList = restTemplate.postForList(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/linemonitordaily/queryTyLineMonitor",
		            param, TyLineMonitorDailyDto.class);
		 
		//构建数据list
		//数据列数
		 int colNum = 3 + creditTypeDictList.size() * 3;
		 
		// 数据行数 (一定能被整除，否则数据异常)
		int rowNum = resultList.size()/3;
		 
	    // 单级的 行内数据
		List<Map<String,Object>> rowList=new ArrayList<>();
		for(int i = 0 ; i < rowNum ; i++ ){
			//每行开始的位置
			int rowStartPoint = i * colNum;
			
	        Map<String,Object> dataMap= new HashMap<String,Object>();
	        //基础表头信息
	        for(int j = 0 ; j < 3 ; j++){
	        	//添加基础信息
	        	 dataMap.put(Enum.valueOf(MonitorDictCode.class,BASE_TITLE_HEADS[i]).getValue(),
	        			 resultList.get(rowStartPoint).getCustomerName());	 
	        }
	        //动态表头
	        for(int k = 0 ; k < creditTypeDictList.size() * 3 ; k++){
	        	
	        	TyLineMonitorDailyDto dataDto = resultList.get(rowStartPoint + k);
	        	//获取当前的大标题数据字典
	        	SysDataDict dictItem = creditTypeDictList.get(k/3);
	        	//取模获得小标题指针
	        	int p = k%3;
	        	String fileName = dictItem.getItemCode() +"_"+ Enum.valueOf(MonitorDictCode.class,AMT_TITLE_HEADS[p]).getValue();
	        	//这里的判断，通过字典，忽略字段顺序，防止需求变更，修改这里的if判断语句
	        	if(AMT_TITLE_HEADS[p].equals(MonitorDictCode.总额.name())){
	        		dataMap.put(fileName, dataDto.getCreditAmt());
	        	}else if(AMT_TITLE_HEADS[p].equals(MonitorDictCode.已用.name())){
	        		dataMap.put(fileName, dataDto.getHapCreditAmt());
	        	}else if(AMT_TITLE_HEADS[p].equals(MonitorDictCode.剩余.name())){
	        		dataMap.put(fileName, dataDto.getRemainderAmt());
	        	}
	        	
	        }
	        rowList.add(dataMap);
		}
		 
		ExcelTool excelTool = new ExcelTool("Sheet1",20,20);
		List<Column>  titleData=excelTool.columnTransformer(titleList,"id","pid","content","fielName","0");
 		outFile = excelTool.markExcelFile(titleData,rowList,"xls",true,false);
		 
		return outFile;
    }
	

}
