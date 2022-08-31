/*
 * 文件名：ReportPageManage001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.service;

import com.google.json.JsonSanitizer;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.infoCompliance.dao.InfoComplianceMapper;
import com.joyintech.tams.disclosure.infoCompliance.dto.InfoComplianceCommentDto;
import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.tams.disclosure.information.config.reportpage.dao.ReportPageManage001Mapper;
import com.joyintech.tams.disclosure.information.config.reportpage.dao.ReportPageManage002Mapper;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportStatusDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.constant.ProcessType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.service.BusinessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage002Service
 * @since 1.0
 */
@Service
public class ReportPageManage002Service extends BaseService<ReportStatusDto> implements BaseProcessService<ReportStatusDto> {

	@Autowired
	private ReportPageManage001Mapper reportPageManage001Mapper;

	@Autowired
	private ReportPageManage002Mapper reportPageManage002Mapper;

	@Autowired
	private InfoComplianceMapper infoComplianceMapper;

	@Autowired
	private ReportPageManage001Service reportPageManage001Service;

	public List<SelectOptionDto> getReportInfo() {
		List<ReportPageManage001Dto> reportInfoList = reportPageManage002Mapper.getReportInfo();
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
		for (ReportPageManage001Dto reportInfo : reportInfoList) {
			SelectOptionDto dto = new SelectOptionDto();
			dto.setId(reportInfo.getRepId());
			dto.setText(reportInfo.getReportName());
			listSelectDto.add(dto);
		}
		return listSelectDto;
	}

	@Override
	public void doInsert(ReportStatusDto dto, OperationType type) {
		try{
			dto.setEffDate(DateUtil.parseDate(dto.getEffDateStr(),"yyyy-MM-dd HH:mm"));
		}catch (Exception e){
			e.printStackTrace();
		}
		//rep_status:01,启用审核中：02,启用审核通过;03,禁用审核中;04,禁用审核通过
		if("01".equals(dto.getWorkflowType())){
			//流程状态:启用审核中
			dto.setRepStatus("01");
			dto.setStartOff(dto.getWorkflowType());
			dto.setRepId(dto.getStartRepId());
			dto.setCreateUser(CommonUtil.getUserName());
			dto.setUpdateUser(CommonUtil.getUserName());
			reportPageManage002Mapper.saveReportStatus(dto);

			if(StringUtil.isNotEmpty(dto.getOffRepId())){
				ReportStatusDto offDto = new ReportStatusDto();
				//禁用dto
				BeanUtils.copyProperties(dto,offDto);
				//设置报表ID
				offDto.setRepId(dto.getOffRepId());
				//启用禁用标识:02禁用
				offDto.setStartOff("02");
				//流程状态:禁用审核中
				offDto.setRepStatus("03");
				offDto.setCreateUser(CommonUtil.getUserName());
				offDto.setUpdateUser(CommonUtil.getUserName());
				offDto.setSeqno(IDGenerator.generate());
				reportPageManage002Mapper.saveReportStatus(offDto);
			}
		}
		//发起的是禁用流程
		if("02".equals(dto.getWorkflowType())){
			//流程状态:禁用审核中
			dto.setRepStatus("03");
			dto.setStartOff(dto.getWorkflowType());
			dto.setRepId(dto.getOffRepId());
			dto.setCreateUser(CommonUtil.getUserName());
			dto.setUpdateUser(CommonUtil.getUserName());
			reportPageManage002Mapper.saveReportStatus(dto);
		}
		//保存附件信息
		if (CollectionUtils.isNotEmpty(dto.getUploadFileList())) {
			for (UploadFileDto file : dto.getUploadFileList()){
				UploadFileDto sysFile = new UploadFileDto();
				sysFile.setBusinessId(dto.getSeqno());
				sysFile.setSeqNo(file.getFileNo());
				sysFile.setFileType("01");
				reportPageManage002Mapper.updateUplodFile(sysFile);
			}
		}
	}

	/**
	 *  流程中保存按钮
	 * */
	@Override
	public void doUpdate(ReportStatusDto dto){
		dto.setRepStatus(null);
		dto.setUpdateUser(CommonUtil.getUserName());
		dto.setUpdateTime(DateUtil.now());
		reportPageManage002Mapper.updateReportStatus(dto);
		//保存审批意见
		if(StringUtil.isNotEmpty(dto.getComment())){
			String infoSeqno = dto.getSeqno();
			InfoComplianceCommentDto commentDto = new InfoComplianceCommentDto(IDGenerator.generate(),infoSeqno,dto.getComment());
			infoComplianceMapper.saveInfoComplianceComment(commentDto);
		}
	}

	/**
	 *  退回时,updateUser变更为退回人,updateTime更新到当前时间
	 **/
	@Override
	public void doBack(ReportStatusDto dto){
		dto.setRepStatus(null);
		dto.setUpdateUser(CommonUtil.getUserName());
		dto.setUpdateTime(DateUtil.now());
		reportPageManage002Mapper.updateReportStatus(dto);
	}

	//流程作废
	public void doDelete(String workflowId) {
		Map<String,Object> param = new HashMap<>();
		param.put("workflowId",workflowId);
		reportPageManage002Mapper.deleteReportStatus(param);
	}

	@Override
	public void doComplete(ReportStatusDto dto, OperationType operationType) {

		//启用流程
		if("01".equals(dto.getWorkflowType())){
			//流程状态:启用审核通过
			dto.setRepStatus("02");
			dto.setRepId(dto.getStartRepId());
			dto.setUpdateUser(CommonUtil.getUserName());
			dto.setUpdateTime(DateUtil.now());
			reportPageManage002Mapper.updateReportStatus(dto);
			if(StringUtil.isNotEmpty(dto.getOffRepId())){
				dto.setRepId(dto.getOffRepId());
				//流程状态:禁用审核通过
				dto.setRepStatus("04");
				reportPageManage002Mapper.updateReportStatus(dto);
			}
		}
		//禁用流程
		if("02".equals(dto.getWorkflowType())){
			dto.setRepId(dto.getOffRepId());
			dto.setUpdateUser(CommonUtil.getUserName());
			dto.setUpdateTime(DateUtil.now());
			//流程状态:禁用审核通过
			dto.setRepStatus("04");
			reportPageManage002Mapper.updateReportStatus(dto);
		}
	}

	public ReportStatusDto selectReportInfo(Map<String,String> map){
		return reportPageManage002Mapper.selectReportInfo(map);
	}

	/**
	 *  流程中需要增加批注,不能进行批量审批
	 **/
	@Override
	public String checkForSubmit(FlowEntity entity, boolean isMutiApproval)  {
		if (isMutiApproval) {
			return "模板启用管理不能批量审批";
		}
		try{
			if(ProcessType.SUBMIT.equals(entity.getProcessType()) || ProcessType.BACK.equals(entity.getProcessType())){
				FlowEntity flowEntity = WorkflowContext.getProcessService().getRuntimeEntity(entity.getProcessInstanceId());
				BusinessService<WorkflowEntity> businessService = WorkflowContext.getBusinessService(flowEntity.getBusinessService());
				Class<?> clz = WorkFlowUtil.getClassByBusinessServiceName(flowEntity.getBusinessService());
				ProcessMark businessEntity = WorkFlowUtil.getBusinessObjectFromRequest(clz, true);
				Object a = JsonUtils.OBJECT_MAPPER.readValue(JsonSanitizer.sanitize(businessEntity.getBusinessData()), GenericsUtils.getSuperClassGenricType(ProxyUtil.getTargetClass(businessService.getClass())));
				ReportStatusDto infoDto = (ReportStatusDto)a;

				String firstNodeId = infoComplianceMapper.getFirstNodeId(entity.getWorkflowId());
				if(!entity.getCurNodeId().equals(firstNodeId)){
					//非第一个节点提交,需要修订意见
					if(StringUtil.isEmpty(infoDto.getComment())){
						return "审批意见不能为空!";
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public int getInfoStatusAfterSysDate(ReportStatusDto dto){
		//查询是否存在生效日之后的流程
		return reportPageManage002Mapper.getInfoStatusAfterSysDate(dto);
	}

	public int getInfoStatusInFlow(ReportStatusDto dto){
		//查询是否存在生效日之后的流程
		return reportPageManage002Mapper.getInfoStatusInFlow(dto);
	}

	public int getInfoStatusBeforeEffdate(ReportStatusDto dto){
		//查询是否存在生效日之后的流程
		return reportPageManage002Mapper.getInfoStatusBeforeEffdate(dto);
	}

	/***
	 *  同步模板状态
	 */
	public void synchroRepInfoStatus(){

		Calendar calendar = Calendar.getInstance();
		Date sysDate = calendar.getTime();
		List<ReportStatusDto> reportStatusDtoList = reportPageManage002Mapper.getLastedInfoListStatus(sysDate);
		for(ReportStatusDto dto:reportStatusDtoList){
			RepInfo repInfo = new RepInfo();
			repInfo.setRepId(dto.getRepId());
			if("02".equals(dto.getRepStatus())){
				repInfo.setReportType("Y");
			}
			if("04".equals(dto.getRepStatus())){
				repInfo.setReportType("N");
			}
			//更新模板状态
			reportPageManage002Mapper.updateReportType(repInfo);
			//更新状态记录表,设置为已同步
			dto.setIsSynchro("Y");
			dto.setEffDate(sysDate);
			reportPageManage002Mapper.updateReportStatus(dto);
		}
	}

	public void checkStatus(ReportStatusDto dto){
		//根据启用模板的ID找到是否有同名的信披报表
		String startRepId=dto.getStartRepId();
		List<RepInfo>list= reportPageManage002Mapper.getExistInfo(startRepId);
		if(!CollectionUtils.isEmpty(list)){
			//List<String>repIdList=list.stream().map(RepInfo::getRepId).collect(Collectors.toList());
			for(RepInfo info:list){
				String repId=info.getRepId();
				List<ReportStatusDto>statusList=reportPageManage002Mapper.getList(repId);
				if(CollectionUtils.isEmpty(statusList)){
					Map<String, List<ReportStatusDto>> statusMap = statusList.stream().collect(Collectors.groupingBy(ReportStatusDto::getStartOff));
					ReportStatusDto start=statusMap.get("01").get(0);
//					if(!CollectionUtils.isEmpty(statusMap.get("01"))){
//						start=statusMap.get("01").get(0);
//					}
					if(CollectionUtils.isEmpty(statusMap.get("02"))){
						//没有禁止生效日，说明存在冲突

					}else{
						//如果有生效截止日，则新报表的生效日要大于原报表的禁用截止日
					}

				}
			}

		}

	}
}
