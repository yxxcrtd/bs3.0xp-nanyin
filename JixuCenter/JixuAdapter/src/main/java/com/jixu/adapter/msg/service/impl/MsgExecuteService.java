package com.jixu.adapter.msg.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.types.OSType;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.FileReader;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.valid.ValidatorUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.dao.intf.UsersDao;
import com.jixu.framework.msg.BaseMsgDto;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgError;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.framework.msg.MsgService;
import com.jixu.framework.msg.ParseMessage;
import com.joyintech.workflow.engine.constant.OperationType;

/** 
 * @ClassName 		MsgExecuteService
 * @author  		Pan Wei 
 * @Date 			2017年5月29日 下午1:04:49 	
 * @Description		[]  
 */

public abstract class MsgExecuteService<T extends BaseMsgDto> implements MsgService<T>{
	
	protected static final Logger logger = LoggerFactory.getLogger(MsgExecuteService.class);
	
	@Resource(name="parseMsg")
	private ParseMessage parseMsg;
	
	@Autowired
	protected HolidayLSDao holidayLSDao;
	
	@Resource(name="msgAssemble")
	protected MsgAssemble msgAssemble;
	
	@Autowired
	protected InterfaceAdapterDao interfaceSourceDao;

	@Autowired
	private UsersDao usersDao;
	
	@Resource(name="configAdptService")
	protected ConfigAdptService configService;
	
	protected String add="ADD";
	protected String update="UPDATE";
	protected String delete="DELETE";
	
	public abstract String messageForm();
	
	public String msgFields(){
		String path = MsgExecuteService.class.getResource("/").getPath();
		String msgForm = null;
		if (OSType.detctOS() == OSType.WIN){
			msgForm = path.replace("WEB-INF/classes/", "data" + messageForm() + ".json").replaceFirst("/", "");
		}
		else{
			msgForm = path.replace("WEB-INF/classes/", "data" + messageForm() + ".json");
		}
		
		if(messageForm()==null){
			return null;
		}
		
		HashMap<String, Object> formMap = (HashMap<String, Object>)JsonUtil.stringToMap(FileReader.ReadFile(msgForm));
		if (formMap.isEmpty()){
			return null;
		}
		else{
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> flist = (List<HashMap<String, Object>>)formMap.get("forms");
			Iterator<HashMap<String, Object>> iter = flist.iterator();
			String str = "";
			while(iter.hasNext()){
				HashMap<String, Object> hm=iter.next();
				if(hm.containsKey("cat")){
					str = str + hm.get("cat")+",";
				}else if(hm.containsKey("label")){
					str = str + iter.next().get("label")+",";
				}else{
					continue;
				}
			}
			str=str.substring(0, str.length()-1);
			return str;
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void executeMessage(String msg, MsgContainer msgcontainer) {
		parseMsg.convertMessage(msg, msgcontainer);
		if(msgcontainer.isCorrectFlag()){
			String header=msgcontainer.getHeader();
			String template = MsgFactory.getMsgFieldsTemplateHM().get(header);
			if(template==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error10,msgcontainer.getHeader()));
			}else{
				int sizeNum = template.split(",").length;
				List<String> fields=msgcontainer.getFields();
				if((fields.size()==2 && fields.get(0).equals("DELETE")) || fields.size()==sizeNum || MsgTemplate.getSubListMsgHM().containsKey(msgcontainer.getHeader())){
					try{
						executeMsg(msgcontainer);
					} catch (NumberFormatException ne) {
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg(MsgError.error4);
						return;
					} catch (MsgFieldLackException me) {
						msgcontainer.setCorrectFlag(false);
						msgcontainer.setErrorMsg(MsgError.error6);
						return;
					}
				}else{
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error3);
					return;
				}
			}
		}
	}
	
	/**
	 * 执行消息
	 * @param msgcontainer 传入消息，容器里的hashmap放入map的消息
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	protected abstract void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException;
	
	/**
	 * post交易
	 * @param msgcontainer
	 * @throws NumberFormatException
	 * @throws MsgFieldLackException
	 */
	private void executeMsg(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		execute(msgcontainer);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void executeMessage(MsgContainer msgcontainer) {
		executeMessageReal(msgcontainer);
	}

	@Override
	public void executeMessageNoTx(MsgContainer msgcontainer) {
		executeMessageReal(msgcontainer);
	}

	private void executeMessageReal(MsgContainer msgcontainer) {
		try {
			HashMap<String, Object> msgHM=msgcontainer.getFieldsHM();
			String header=getFieldValue(msgHM, MsgFields.header);
			
			int msglogid=msgcontainer.getObjID();
			
			if(msgcontainer.isVerifyFlag()){
				try{
					executeMsg(msgcontainer);
				} catch (NumberFormatException ne) {
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error4);
					return;
				} catch (MsgFieldLackException me) {
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.error6);
					return;
				}
				MsgLog msglog=usersDao.loadEntity(MsgLog.class,msglogid);
				msglog.setStatus(msgcontainer.isCorrectFlag());
				msglog.setErrorinfo(msgcontainer.getErrorMsg());
				usersDao.updateEntity(msglog);
			}else{
				//把消息保存入msglog，状态是未执行
				MsgLog errormsg=new MsgLog();
				Object tradeDate=msgHM.get(MsgFields.tradeDate);
				
				int operationStep=0;
				
				errormsg.setMsg(JSON.toJSONString(msgHM));
				errormsg.setErrorinfo(msgcontainer.getErrorMsg());
				errormsg.setOperationTime(new Date());
				errormsg.setStatus(false);//状态:未执行
				errormsg.setSource(msgcontainer.getSource());
				errormsg.setOperationType(operationStep);
				if(tradeDate!=null){
					tradeDate=tradeDate.toString().replaceAll("-", "");
					errormsg.setLedgerDate(DateUtil.convertStr2Date(tradeDate.toString()));
				}
				errormsg.setIngore(false);
				
				
				if(msgHM.containsKey(MsgFields.portnum)){
					String portNumStr=getFieldValue(msgHM, MsgFields.portnum);
					if(portNumStr!=null){
						errormsg.setPortfolio(portNumStr);
					}
				}
				
				usersDao.saveEntity(errormsg);
				try{
					executeMsg(msgcontainer);
			    } catch (NumberFormatException ne) {
			    	msgcontainer.setCorrectFlag(false);
			    	msgcontainer.setErrorMsg(MsgError.error4);
			    	return;
			    } catch (MsgFieldLackException me) {
			    	msgcontainer.setCorrectFlag(false);
			    	msgcontainer.setErrorMsg(MsgError.error6);
			    	return;
			    }
				
			   //把所有的交易消息,记录到消息日志表里,以供workflow获取记录数量
				errormsg.setStatus(msgcontainer.isCorrectFlag());
			    errormsg.setErrorinfo(msgcontainer.getErrorMsg());
			    usersDao.updateEntity(errormsg);
			}
		} catch (MsgFieldLackException mle) {
			logger.error("Error", mle);
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(mle.getMessage());
		}
	}
	
	
	
	protected void genMsgContainer(String header, String fields, MsgContainer msgcontainer){
		msgAssemble.genMsgContainer(header,fields, msgcontainer);
	}
	
	protected String getFieldValue(HashMap<String, Object> fieldsHM, String key) throws MsgFieldLackException{
		String value=fieldsHM.get(key).toString().trim();
		if(value==null){
			throw new MsgFieldLackException("该消息关键字段没有值提供: "+key);
		}else{
			return value;
		}
	}
	
	protected void completeEntity(T dto) {};
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void doComplete(T entity, OperationType type) {
		completeEntity(entity);
	}
	
	/**
	 * 检查时间格式
	 * @param msgcontainer
	 * @param header
	 * @param dateStr
	 * @return
	 */
	protected Date checkDateFormat(MsgContainer msgcontainer, String header, String dateStr){
		Date pdate=DateUtil.convertStr2Date(dateStr);
		if(dateStr.length()!=8 || pdate==null){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error5,header,dateStr));
		}
		return pdate;
	}
	
	
	
	/**
	 * 获取数据源
	 * @param sourceID
	 * @param msgcontainer
	 * @return
	 */
	protected InterfaceSource getInterfaceSource(int sourceID, MsgContainer msgcontainer){
		InterfaceSource source = interfaceSourceDao.loadSource(sourceID);
		if (source == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setSource(source);
			msgcontainer.setEntityObj(source);
		}
		return source;
	}
	
	/**
	 * 返回是否通过前端校验
	 * @param container
	 * @param dto
	 */
	protected void validateDto(MsgContainer container, BaseMsgDto dto){
		Map<String, StringBuffer> resultmap=ValidatorUtil.validate(dto);
		//获取前端的错误信息返回到前端页面
		if(resultmap!=null && resultmap.size()>0){
			StringBuffer sb=new StringBuffer();
			for(Map.Entry<String, StringBuffer> entry : resultmap.entrySet()){
				sb.append(" && ");
				sb.append(entry.getValue());
			}
			
			String message=sb.toString();
			message=message.replaceFirst(" && ", "");
			
			container.setCorrectFlag(false);
			container.setErrorMsg(message);
		}else {
			container.setEntityObj(dto);
		}
	}
	
	@Override
	public void doUpdate(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doUpdate(entity);
	}
	
	@Override
	public void doDelete(String workflowId) {
		// TODO Auto-generated method stub
		MsgService.super.doDelete(workflowId);
	}
	
	@Override
	public void doInsert(T entity, OperationType type) {
		// TODO Auto-generated method stub
		MsgService.super.doInsert(entity, type);
	}
	
	@Override
	public void doBack(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doBack(entity);
	}

	@Override
	public void doRevoke(T entity) {
		// TODO Auto-generated method stub
		MsgService.super.doRevoke(entity);
	}
	
	@Override
	public String generateTitle(T entity, OperationType type) {
		return MsgService.super.generateTitle(entity, type);
	}
}
