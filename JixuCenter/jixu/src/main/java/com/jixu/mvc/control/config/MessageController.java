package com.jixu.mvc.control.config;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.msg.MsgFactory;


@Controller
public class MessageController extends ExceptionControl{
    
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
    @RequestMapping(value="/message.do", method = RequestMethod.GET, name="管理-消息执行-页面显示")
    public String message() {
        return "message";
    }
    
    @RequestMapping(value="/config/uploadExeMessage.do", method = RequestMethod.POST, name="管理-消息执行-消息上传并执行")
	 public void uploadExeMessage(MultipartHttpServletRequest request, HttpServletResponse response){
		BufferedReader br = null;
    	HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("消息编号","文件所在行", "消息内容","执行状态","执行详情");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
    	int userID=JixuUserUtil.getUserIDbyRequest(request);
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			try {
				DataInputStream in = new DataInputStream(request.getFile(itr.next()).getInputStream());
				br=new BufferedReader(new InputStreamReader(in,"gbk"));
				String line=null;
				int num=1;
				while((line=br.readLine())!=null){
					HashMap<String, Object> fieldsMap = JSONObject.parseObject(line, HashMap.class);
					MsgContainer container=new MsgContainer();
					container.setFieldsHM(fieldsMap);
					container.setUserID(userID);
					container = MsgFactory.executeMsgBean(container);
					List<String> item1 = new ArrayList<String>();
					item1.add(String.valueOf(num));
					item1.add(String.valueOf(num));
					item1.add(line);
					if(container.isCorrectFlag()){
						item1.add("成功");
						item1.add("-");
					}else{
						item1.add("失败");
						item1.add(container.getErrorMsg());
					}
					data.add(item1);
					num++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
}
