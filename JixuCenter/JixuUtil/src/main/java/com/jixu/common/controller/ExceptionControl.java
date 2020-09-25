package com.jixu.common.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Pages;
import com.jixu.framework.msg.MsgFactory;

/**
 * @ClassName ExceptionController
 * @author Pan Wei
 * @Date 2017年10月18日 下午3:55:31
 * @Description []
 */

public class ExceptionControl {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControl.class);

	@ExceptionHandler
	public void expCon(Exception ex) {
		logger.error("Error", ex);
	}
	
	/**
	 * 执行消息，传递给MsgFactory执行
	 * @param request	httprequest，获取执行用户id
	 * @param jsonMsg	执行的json消息
	 * @return	消息执行结果，错误信息
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeMsg(HttpServletRequest request, String jsonMsg){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(userID);
		msgcontainer.setJsonMsg(jsonMsg);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * 执行消息，传递给MsgFactory执行
	 * @param userID	用户id
	 * @param jsonMsg	执行的json消息
	 * @return	消息执行结果，错误信息
	 */
	@SuppressWarnings("unchecked")
	public MsgContainer executeMsg(int userID, String jsonMsg){
		HashMap<String, Object> fieldsMap=JSONObject.parseObject(jsonMsg, HashMap.class);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(userID);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	public MsgContainer executeNewMsg(HttpServletRequest request,HashMap<String, Object> fieldsMap){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setFieldsHM(fieldsMap);
		msgcontainer.setUserID(userID);
		msgcontainer=MsgFactory.executeMsgBean(msgcontainer);
		return msgcontainer;
	}
	
	/**
	 * 统一处理Controller的返回界面信息
	 * @param container	信息容器，包含正确/错误标志位，错误信息
	 * @return	返回界面的信息
	 */
	public Object controllerReturn(MsgContainer container){
		if(container.isCorrectFlag()){
			return container.getObjID();
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			return result;
		}
	}
	
	/**
	 * 统一处理Controller的返回界面信息。通过reponse返回值
	 * @param response	httpresponse
	 * @param container 信息容器，包含正确/错误标志位，错误信息
	 */
	public void controllerReturn(HttpServletResponse response, MsgContainer container){
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
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
	
	/**
	 * 配合马总最新界面 统一处理Controller的返回界面信息。通过reponse返回值
	 * @param response	httpresponse
	 * @param container 信息容器，包含正确/错误标志位，错误信息
	 */
	public void adapterControllerReturn(HttpServletResponse response, MsgContainer container,HashMap<String,Object> result){
		HashMap<String, Object> status = new HashMap<String, Object>();
		if(container.isCorrectFlag()){
			status.put("success", 1);
		}else{
			status.put("success", 0);
		}
		status.put("message", container.getErrorMsg());
		status.put("code", container.getErrorCode());
		result.put("status", status);
	}
	
	//jixu新界面消息返回格式
	public void controllerReturnNew(HttpServletResponse response, ResultContainer container){
		HashMap<String, Object> status = new HashMap<String, Object>();
		HashMap<String,Object> result=new HashMap<String, Object>();
		if(container.isCorrectFlag()){
			status.put("success", 1);
		}else{
			status.put("success", 0);
		}
		status.put("message", container.getErrorMsg());
		status.put("code", container.getErrorCode());
		if(container.getObjID()!=0) {
			status.put("id", container.getObjID());
		}else {
			status.put("id", container.getEntityID());
		}
		result.put("status", status);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 返回界面是否执行正确
	 * @param response
	 * @param correctFlag
	 * @param errorMsg
	 */
	public void controllerReturn(HttpServletResponse response, boolean correctFlag, String errorMsg){
		if(correctFlag){
			JsonUtil.writeObjToResponse(response, "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", 0);
			issue.put("level", "fail");
			issue.put("detail", errorMsg);
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	/**
	 * 统一处理Controller的返回界面信息。通过reponse返回值
	 * @param response	httpresponse
	 * @param container 信息容器，包含正确/错误标志位，错误信息
	 */
	public void controllerReturn(HttpServletResponse response, ResultContainer container){
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
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
	
	//返回错误页面的方法
	/*public String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);

		// 根据不同错误转向不同页面
		if (ex instanceof BusinessException) {
			return "error-business";
		} else if (ex instanceof ParameterException) {
			return "error-parameter";
		} else {
			return "error";
		}
	}*/

	
	/*<error-page>  
	<exception-type>java.lang.Throwable</exception-type>  
	    <location>/500.jsp</location>  
	</error-page>  
	<error-page>  
	    <error-code>500</error-code>  
	    <location>/500.jsp</location>  
	</error-page>  
	<error-page>  
	    <error-code>404</error-code>  
	    <location>/404.jsp</location>  
	</error-page>  
	*/
	
	/**
	 * 根据前端查询是否需要导出  设置每页条数或导出总数
	 * @param export2excel 判断是否导出成excel
	 * @param limits 每页显示条数或导出总数
	 */
	protected int pageLimits(String export2excel,int limits){
		if(export2excel!=null && export2excel.equals("1")){
			limits=100000;
		}
		return limits;
	}
	
	/**
	 * 统一处理界面查询结果或导出成excel
	 * @param export2excel 判断是否导出成excel
	 * @param result 结果集
	 * @param response 前台交互
	 */
	protected void returnControllerList(String export2excel,HashMap<String, Object> result,HttpServletResponse response){
		//当export2excel=1时   导出成excel
		if(export2excel!=null && export2excel.equals("1")){
			DataReadResult dataSet=new DataReadResult();
			dataSet.setDataHeader((List<String>) result.get("titles"));
			dataSet.setDataResult((List<List<Object>>) result.get("data"));
			downLoadFile(response, dataSet);
		}else{
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	/**
	 * 通过页面下载输出结果
	 * @param response
	 * @param dataSet
	 */
	protected void downLoadFile(HttpServletResponse response, DataReadResult dataSet) {
		OutputStream out=null;
		try {
			response.reset();
			response.setContentType("application/binary;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=111.xlsx");
			out=response.getOutputStream();
		}catch(IOException ie) {
			logger.error("Error", ie);
			return;
		}
		
		Workbook workBook = new XSSFWorkbook();
		try {
			List<List<Object>> fieldsDataSet = dataSet.getDataResult();

			if (fieldsDataSet != null && !fieldsDataSet.isEmpty()) {
				Sheet sheet= workBook.createSheet();
				CellStyle headStyle = workBook.createCellStyle();
				headStyle.setAlignment(HorizontalAlignment.CENTER);
				//写入文件头header
				Row row = sheet.createRow(0);
				// CellStyle cellStyle = createHeadCellStyle(workBook);
				List<String> headerList = dataSet.getDataHeader();
				for (int i = 0; i < headerList.size(); i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(headerList.get(i));
					cell.setCellStyle(headStyle);;
				}
				
				int rownum=1;
				CellStyle bodyStyle = workBook.createCellStyle();
				bodyStyle.setAlignment(HorizontalAlignment.CENTER);
				for (List<Object> fieldsList : fieldsDataSet) {
					Row row1 = sheet.createRow(rownum);
					for (int i = 0; i < fieldsList.size(); i++) {
						Cell cell = row1.createCell(i);
						cell.setCellValue(fieldsList.get(i).toString());
						cell.setCellStyle(bodyStyle);
					}
					rownum++;
				}
			}
			workBook.write(out);
		} catch (IOException e) {
			logger.error("Error", e);
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if(workBook!=null){
					workBook.close();
				}
			} catch (IOException e) {
				logger.error("Error", e);
			}
		}
	}
}
