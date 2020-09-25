package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.FlagType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.MarketDataService;

@Controller
public class InterfaceController extends ExceptionControl{
	
	@Resource(name="marketDataService")
	private MarketDataService marketDataService;
	
	@Resource(name="configService")
	private ConfigService configService;
	
	@Resource(name="commfuncService")
	protected CommFuncService commService;
	
	@RequestMapping(value="/config/importConfigList.do", method = RequestMethod.POST)
	 public void importConfigList(String ports, int category, HttpServletResponse response){
		String[] portfolios=null;
		if(ports==null){
			portfolios=new String[0];
		}else{
			List<Object> ports_list = JsonUtil.stringToList(ports);
			portfolios=new String[ports_list.size()];
			for(int i=0;i<ports_list.size();i++){
				portfolios[i]=ports_list.get(i).toString();
			}
		}
		
		
		HashMap<Integer, String> result = marketDataService.loadSourceList(portfolios, category);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/interfaceTypes.do", method = RequestMethod.POST)
	public void interfaceTypes(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, InterfaceSourceType.interfaceImporttypeHM);
	}
	
	@RequestMapping(value="/interface.do", method = RequestMethod.GET, name="管理-接口配置-页面显示")
	public String interfacePage(){
		return "interface";
	}
	
	@RequestMapping(value="/interfacePage.do", method = RequestMethod.GET, name="管理-接口配置-详情页面显示")
	public String interfacePage(@RequestParam(value="id") int interfaceID, ModelMap model) {
		model.addAttribute("interfaceID", interfaceID);
		return "interfacePage";
	}
	
	@RequestMapping(value="/interface/interfaceList.do", method = RequestMethod.POST, name="管理-接口配置-查询")
	 public void interfaceListList(@RequestParam(value = "name", required = false) String name,
			 					  @RequestParam(value = "category", required = true) int category,
			 					  @RequestParam(value = "type", required = false) int type,
			 					  HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","接口名称", "接口描述", "接口类型", "证券类型", "传输格式", "是否支持多文件", "是否自动导入", "自动导入时间");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		List<InterfaceSource> sourceList=configService.loadInterfaceSourceList(category);
		
		for(InterfaceSource source:sourceList){
			List<Object> item = new ArrayList<Object>();
			item.add(source.getSourceID());
			item.add(source.getSourceName());
			item.add(source.getSourceDesc());
			item.add(InterfaceSourceType.interfaceImporttypeHM.get(source.getSourceType()));
			item.add("-");
			item.add(InterfaceConfig.fileTypeOption.get(source.getFileType()));
			item.add(FlagType.operationsHM.get(source.isFileMultiFlag()));
			item.add(FlagType.operationsHM.get(source.isAutoFlag()));
			item.add(source.getAutoTime());
			data.add(item);
		}
		
		result.put("data", data);
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
//	@RequestMapping(value="/config/importData.do", method = RequestMethod.POST, name="接口-导入数据")
//	 public void importData(@RequestParam(value = "id", required = true) int sourceID,
//			 				@RequestParam(value = "port", required = true) String portID,
//			 				@RequestParam(value = "workflowStep", required = true) int workflowStep,
//			 				@RequestParam(value = "date", required = true) String date,
//			 			    HttpServletRequest request, 
//			 			    HttpServletResponse response){
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		int userID=JixuUserUtil.getUserIDbyRequest(request);
//		
//		List<String> titles = Arrays.asList("状态","数量", "错误详情");		
//		result.put("titles", titles);
//		List<Object> data = new ArrayList<Object>();
//		
//		Date ledgerDate=DateUtil.convertStr2Date2(date);
//		ResultContainer container=dataServiceCenter.importData(userID, sourceID, ledgerDate, workflowStep);
//	
//		if(container.isCorrectFlag()){
//			List<MsgContainer> containerList=(List<MsgContainer>)container.getEntityObj();
//			int successnum=0;
//			for(MsgContainer msgcontainer:containerList){
//				if(msgcontainer.isCorrectFlag()){
//					successnum++;
//				}else{
//					List<Object> item = new ArrayList<Object>();
//					item.add("失败");
//					item.add(1);
//					item.add(msgcontainer.getErrorMsg());
//					data.add(item);
//				}
//			}
//			List<Object> item1 = new ArrayList<Object>();
//			item1.add("成功");
//			item1.add(successnum);
//			item1.add("");
//			data.add(0, item1);
//		
//			result.put("data", data);
//			result.put("resultFlag", container.isCorrectFlag());
//			JsonUtil.writeObjToResponse(response, result);
//		}else{
//			HashMap<String, Object> issue = new HashMap<String, Object>();
//			issue.put("level", "fail");
//			issue.put("detail", container.getErrorMsg());
//			result.put("issue", issue);
//			result.put("resultFlag", container.isCorrectFlag());
//			JsonUtil.writeObjToResponse(response, result);
//		}
//	}
}
