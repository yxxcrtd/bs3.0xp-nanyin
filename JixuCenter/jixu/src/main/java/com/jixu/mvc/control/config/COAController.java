package com.jixu.mvc.control.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.NetCategory;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ConverIDNum;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.model.GlCoaaMapModel;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.BokSubject;
import com.orm.obj.Coaa;
import com.orm.obj.CoaaGroup;
import com.orm.obj.Coaahdr;
import com.orm.obj.GlCoaaMap;

@Controller
public class COAController extends ExceptionControl{
    
    @Resource(name="configService")
    private ConfigService configService;
    
    @RequestMapping(value="/coa.do", method = RequestMethod.GET, name="管理-总账组-页面显示")
    public String info() {
        return "coa";
    }
    
    @RequestMapping(value="/config/coaList.do", method = RequestMethod.POST, name="管理-会计科目表-查询")
     public void coaList(HttpServletResponse response){
    	HashMap<String, String> result = configService.loadCoaahdr();
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/coaDetail.do", method = RequestMethod.POST, name="管理-会计科目表-详情")
     public void coaDetail(@RequestParam(value = "id", required = true) String coaID,
             			   HttpServletResponse response){
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "账号", "账户描述", "分类","结算账户");
        result.put("titles", titles);
        
        List<List<Object>> coaas=configService.loadCoaaDetailsAllByChartNum(coaID);
        
        result.put("data", coaas);
        //result.put("pages",8);
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/coaCreate.do", method = RequestMethod.POST, name="管理-会计科目表-创建")
     public void coaCreate(@RequestParam(value = "coaName", required = true) String coaName,
             			 HttpServletResponse response){
    	Coaahdr coaahdr=new Coaahdr();
    	coaahdr.setChartname(coaName);
    	configService.createCoaahdr(coaahdr);
        JsonUtil.writeObjToResponse(response, "" + coaahdr.getId());
    }
    
    @RequestMapping(value="/config/glCreate.do", method = RequestMethod.POST, name="管理-总账组-创建")
     public void glCreate(@RequestParam(value = "coaID", required = true) String coaID,
             			@RequestParam(value = "glName", required = true) String glName,
             			HttpServletResponse response){
        CoaaGroup coaGroup=new CoaaGroup();
        coaGroup.setGroupName(glName);
        configService.createCoaaGroup(coaGroup);
        ArrayList<Object> result = new ArrayList<Object>();
        result.add(coaID);
        result.add(coaGroup.getGroupNum());
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/glCopy.do", method = RequestMethod.POST, name="管理-总账组-复制")
    public void glCopy(@RequestParam(value = "coaID", required = true) String coaID,
    					@RequestParam(value = "copyGl", required = true) int copyGl,
            			@RequestParam(value = "coaName", required = true) String coaName,
            			HttpServletResponse response){
       //创建新的总账组名称
       CoaaGroup coaGroup=new CoaaGroup();
       coaGroup.setGroupName(coaName);
       configService.createCoaaGroup(coaGroup);
       //查询要复制的总账组下的总账栏
       List<GlCoaaMapModel> loadGlCoaaMapList=configService.loadGlCoaaMap(coaID, copyGl);
       List<GlCoaaMap> list=new ArrayList<GlCoaaMap>();
       for(GlCoaaMapModel glmap:loadGlCoaaMapList){
    	   //对查询到的总账栏循环遍历进行保存
    	   GlCoaaMap coamap=new GlCoaaMap();
       	   coamap.setChartNum(coaID);
       	   coamap.setGroupNum(coaGroup.getGroupNum());
       	   coamap.setColumnNum(glmap.getColumnNum());
       	   coamap.setAcctNum(glmap.getAcctNum());
       	   list.add(coamap);
       }
       configService.copyGLMap(list);
       ArrayList<Object> result = new ArrayList<Object>();
       result.add(coaID);//会计科目表
       result.add(coaGroup.getGroupNum());//新建的总账组
       JsonUtil.writeObjToResponse(response, result);
   }
    
    @RequestMapping(value="/config/acctList.do", method = RequestMethod.POST, name="管理-会计科目-查询")
     public void acctList(@RequestParam(value = "portID", required = true) String portID,
             			  HttpServletResponse response){
       
    	ArrayList<String> result = new ArrayList<String>();
        List<Coaa> coaaList=configService.loadCoaaDetailsByPortNum(portID);
        for(Coaa coa:coaaList){
	        result.add(coa.getAccntnum()+"|" + coa.getAccntname());
        }
        JsonUtil.writeObjToResponse(response, result);
    }
    
    //TODO: Need to update
    @RequestMapping(value="/config/acctList_new.do", method = RequestMethod.POST)
    public void acctList_new(@RequestParam(value = "portID", required = true) String portID,
            			  HttpServletResponse response){
      
       HashMap<String, String> result = new HashMap<String, String>();
       List<BokSubject> coaaList=configService.loadBokSubjectsByPortNum(portID);
       for(BokSubject coa:coaaList){
	        result.put(coa.getBokSubjectPK().getSubject_no()+"|" + coa.getSubject_name(), 
	        		coa.getBokSubjectPK().getSubject_no()+"|" + coa.getSubject_name());
       }
       JsonUtil.writeObjToResponse(response, result);
   }
        
    @RequestMapping(value="/config/acctRemove.do", method = RequestMethod.POST, name="管理-会计科目-删除")
     public void acctRemove(@RequestParam(value = "pid", required = true) String coaID,
             				@RequestParam(value = "id", required = true) String acctID,
             			    HttpServletResponse response){
        System.out.println("Remove acctid -" + acctID + " in COA " + coaID);
        JsonUtil.writeObjToResponse(response, "");
    }
    
    @RequestMapping(value="/config/acctUpdate.do", method = RequestMethod.POST, name="管理-会计科目-更新")
     public void acctUpdate(@RequestParam(value = "pid", required = true) String coaID,
             				@RequestParam(value = "id", required = true) String acctID,
             				@RequestParam(value = "params", required = true) String params,
             			    HttpServletResponse response){
    	
    	List<Object> param_list = JsonUtil.stringToList(params);
        int acctid=ConverIDNum.converIDNumByString(acctID);
        String acctnum=param_list.get(0).toString();
        String actname=param_list.get(1).toString();
        int acttype=Integer.valueOf(param_list.get(2).toString());
        String actclo=param_list.get(3).toString().split("\\|")[0];
        
        configService.updateCOADetail(acctid, acctnum, actname, acttype, actclo);
        JsonUtil.writeObjToResponse(response, "");
    }
    
    @RequestMapping(value="/config/acctCreate.do", method = RequestMethod.POST, name="管理-会计科目-创建")
     public void acctCreate(@RequestParam(value = "pid", required = true) String chartNum,
    		 			    @RequestParam(value = "params", required = true) String params,
             			    HttpServletResponse response){
    	List<Object> param_list = JsonUtil.stringToList(params);
    	ResultContainer container=configService.createCoa(chartNum, param_list);
      
    	if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
    }
    
    @RequestMapping(value="/config/acctListInCOA.do", method = RequestMethod.POST)
     public void acctListInCOA(@RequestParam(value = "coaID", required = true) String coaID,
             			  HttpServletResponse response){
        List<String> result = configService.loadCoaaDetailsByChartNum(coaID);
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/acctListInCOA_new.do", method = RequestMethod.POST)
    public void acctListInCOA_new(@RequestParam(value = "coaID", required = true) String coaID,
            			  HttpServletResponse response){
       HashMap<String, String> result = configService.getCoaaDetailsMapByCharNum(coaID);
       JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/glList.do", method = RequestMethod.POST, name="管理-总账组-查询")
     public void glList(HttpServletResponse response){
    	HashMap<Integer, String> result = configService.loadCoaaGroup();
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/glListInCOA.do", method = RequestMethod.POST)
     public void glListInCOA(@RequestParam(value = "coaID", required = true) String coaID,
             				 HttpServletResponse response){
    	 HashMap<Integer, String> result = configService.loadCoaaGroup();
         JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/glListInPort.do", method = RequestMethod.POST)
    public void glListInPort(@RequestParam(value = "portID", required = true) String portID,
            				 HttpServletResponse response){
	   	HashMap<Integer, String> result = configService.loadCoaaGroup();
        JsonUtil.writeObjToResponse(response, result);
   }
    
    /**
     * 显示所有的总账栏号
     * @param response
     */
    @RequestMapping(value="/config/glColList.do", method = RequestMethod.POST, name="管理-总账栏-查询")
     public void glColList(HttpServletResponse response){
    	HashMap<Integer, String> result = GLACCT.optionHM();
		JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/newGlColList.do", method = RequestMethod.POST, name="管理-总账栏-新查询")
    public void newGlColList(HttpServletResponse response){
    	HashMap<String, String> result=new HashMap<>();
    	HashMap<Integer, String> glAcctMap = GLACCT.optionHM();
    	glAcctMap.forEach((k,v)->result.put(v, v));
		JsonUtil.writeObjToResponse(response, result);
   }
    
    @RequestMapping(value="/config/glDetail.do", method = RequestMethod.POST)
     public void glDetail(@RequestParam(value = "coaID", required = true) String coaID,
    		 			   @RequestParam(value = "id", required = true) int glID, 
             			   HttpServletResponse response){
    	String chartnum=coaID;
        int glnum=glID;
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        
        List<String> titles = Arrays.asList("编号", "总账栏号|总账栏名称", "会计账户");
        result.put("titles", titles);
        
        List<Object> data = new ArrayList<Object>();
        List<GlCoaaMapModel> loadGlCoaaMapList=configService.loadGlCoaaMap(chartnum, glnum);
        for(GlCoaaMapModel glmap:loadGlCoaaMapList){
        List<Object> item = new ArrayList<Object>();
	        item.add(glmap.getId());
	        item.add(glmap.getColumnNum());
	        item.add(glmap.getAcctNum()+"|"+glmap.getAcctname());
	        data.add(item);
        }
        result.put("data", data);
            
        JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/newGlDetail.do", method = RequestMethod.POST)
    public void newGlDetail(@RequestParam(value = "coaID", required = true) String coaID,
   		 			   		@RequestParam(value = "id", required = true) String glID, 
   		 			   		HttpServletResponse response){
       int glnum=Integer.valueOf(glID);
       
       HashMap<String, Object> result = new HashMap<String, Object>();
       
       List<String> titles = Arrays.asList("编号", "总账栏号|总账栏名称", "会计账户");
       result.put("titles", titles);
       
       List<Object> data = new ArrayList<Object>();
       List<GlCoaaMapModel> loadGlCoaaMapList=configService.loadGlCoaaMap(coaID, glnum);
       HashMap<Integer, String> glAcctMap = GLACCT.optionHM();
       
       for(GlCoaaMapModel glmap:loadGlCoaaMapList){
       List<Object> item = new ArrayList<Object>();
	        item.add(glmap.getId());
	        item.add(glAcctMap.get(glmap.getColumnNum()));
	        item.add(glmap.getAcctNum()+"|"+glmap.getAcctname());
	        data.add(item);
       }
       result.put("data", data);
           
       JsonUtil.writeObjToResponse(response, result);
   }
    
    //NEW ADD
    @RequestMapping(value="/config/glColDetail.do", method = RequestMethod.POST)
    public void glColDetail(@RequestParam(value = "coaID", required = true) String coaID,
   		 			   		@RequestParam(value = "glID", required = true) int glID, 
   		 			   		@RequestParam(value = "glColID", required = true) int glColID,
   		 			   		HttpServletResponse response){
       int glnum=glID;
       
       HashMap<String, Object> result = new HashMap<String, Object>();
       
       List<GlCoaaMapModel> loadGlCoaaMapList=configService.loadGlCoaaMap(coaID, glnum);
       for(GlCoaaMapModel glmap:loadGlCoaaMapList){
    	   if (glColID == glmap.getId()){
    		   result.put("glCol_id",glmap.getId());
    		   result.put("glCol", glmap.getColumnNum());
    		   result.put("acct",glmap.getAcctNum()+"|"+glmap.getAcctname());
    		   break;
    	   }
       }   
       JsonUtil.writeObjToResponse(response, result);
   }
    
    @RequestMapping(value="/config/glColRemove.do", method = RequestMethod.POST, name="管理-总账栏-删除")
     public void glColRemove(@RequestParam(value = "pid", required = true) int glID,
             				@RequestParam(value = "id", required = true) int glColID,
             				@RequestParam(value = "coaID", required = true) String coaID,
             			    HttpServletResponse response){
    	configService.deleteGlCoaMap(glColID);
        JsonUtil.writeObjToResponse(response, "");
    }
    
    @RequestMapping(value="/config/glColUpdate.do", method = RequestMethod.POST, name="管理-总账栏-更新")
     public void glColUpdate(@RequestParam(value = "pid", required = true) int glID,
             				@RequestParam(value = "id", required = true) int glColID,
             				@RequestParam(value = "coaID", required = true) String coaID,
             				@RequestParam(value = "params", required = true) String params,
             			    HttpServletResponse response){
        List<Object> param_list = JsonUtil.stringToList(params);
        configService.updateGlCoaMap(glColID, param_list.get(1).toString().split("\\|")[0]);
        JsonUtil.writeObjToResponse(response, glColID);
    }
    
    @RequestMapping(value="/config/glColCreate.do", method = RequestMethod.POST, name="管理-总账栏-创建")
     public void glColCreate(@RequestParam(value = "pid", required = true) int glID,
    		 				 @RequestParam(value = "coaID", required = true) String coaID,
    		 				 @RequestParam(value = "params", required = true) String params,
    		 				 HttpServletResponse response){
    	List<Object> param_list = JsonUtil.stringToList(params);
    	GlCoaaMap coamap=new GlCoaaMap();
    	coamap.setChartNum(coaID);
    	coamap.setGroupNum(glID);
    	coamap.setColumnNum(Integer.valueOf(param_list.get(0).toString().split("\\|")[0]));
    	coamap.setAcctNum(param_list.get(1).toString().split("\\|")[0]);
    	configService.createGLMap(coamap);
        JsonUtil.writeObjToResponse(response, coamap.getId() + "");
    }
    
    //option-select-values acct_add_form.json--category
    //option-select-values coa_table_form.json--category
    @RequestMapping(value="/config/ListCOACategoryOption.do", method = RequestMethod.POST)
    public void listCOACategoryOption(HttpServletResponse response){
    	HashMap<Integer, String> result = NetCategory.getNetCategory();
    	JsonUtil.writeObjToResponse(response, result);
    }
    
    @RequestMapping(value="/config/autoMapGlGroupForJoyin.do", method = RequestMethod.POST)
    public void autoMapGlGroupForJoyin(String assetType, String curr, String curr2, HttpServletResponse response) {
    	int group=configService.autoMapGlGroupForJoyin(assetType, curr, curr2);
    	JsonUtil.writeObjToResponse(response, group);
    }
}
