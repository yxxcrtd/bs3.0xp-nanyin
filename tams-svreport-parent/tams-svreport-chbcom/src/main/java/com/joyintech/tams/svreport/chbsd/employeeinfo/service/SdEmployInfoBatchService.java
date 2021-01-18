package com.joyintech.tams.svreport.chbsd.employeeinfo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.component.svrreport.chb.dto.ChbEmployeeInfoDto;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.chbsd.employeeinfo.dao.SdEmployeeInfoSvrMapper;
import com.joyintech.tams.svreport.chbsd.employeeinfo.dto.SdEmployeeInfoSvrDto;

/**
 * 功能:从业人员信息登记报送批处理主service
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdEmployInfoBatchService {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SdEmployInfoBatchServiceOneService sdEmployInfoBatchServiceOneService;
	
//	@Autowired
//	private EmployeeInfoReg001Service employeeInfoReg001Service;
	
	@Autowired
	private SdEmployeeInfoSvrMapper sdEmployeeInfoSvrMapper;
	
	public void sendEmployeeInfoMsg(Date batchDate) throws Exception {
	
			logger.debug("从业人员信息登记未报送数据检索开始------" + df.format(new Date()));
			// 1、报送数据检索,取得 List
			List<SdEmployeeInfoSvrDto> listSub = sdEmployeeInfoSvrMapper.selcteSendingData();
			logger.debug("从业人员信息登记未报送数据检索结束------" + df.format(new Date()));

			if(StringUtil.isEmptyList(listSub)){
				logger.info("没有未报送数据，批处理结束");
				return;
			} 
			
			//1.1、数据字典项需要进行格式转化
			dealData(listSub);
			
			//此处check在info入sub表时已做，此处不再重复check
			// 调用check逻辑
//			logger.debug("从业人员信息登记数据检查开始------检查前数据个数：" + listSub.size());
//			List<CheckReport001Dto> resultlist = employeeInfoReg001Service.check(batchDate,null);
//			Map<String,CheckReport001Dto> checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1, key2)->key1));
//			for (int i = listSub.size() - 1; i >= 0; i--){
//				if (checkmap.containsKey(listSub.get(i).getSumitSeq())
//						&& (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getSumitSeq()).getErrorInfo())
//						|| (StringUtil.isNotEmpty(checkmap.get(listSub.get(i).getSumitSeq()).getWarnInfo())
//						&& !StringUtil.equals("01", CacheUtil.getSysParam("CHB_WARNING_CHECKRESULT_IS_SEND"))))){
//					listSub.remove(i);
//				}
//			}
//			logger.debug("从业人员信息登记数据检查结束------检查后数据个数：" + listSub.size());
//			
//			if(StringUtil.isEmptyList(listSub)){
//				logger.info("检查后的数据全部不合规，批处理结束");
//				return;
//			} 

			//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
			List<ChbEmployeeInfoDto> dataList = new ArrayList<ChbEmployeeInfoDto>();
			for(SdEmployeeInfoSvrDto sdDto : listSub){
				ChbEmployeeInfoDto target = new ChbEmployeeInfoDto();
				BeanUtils.copyProperties(target, sdDto);
				dataList.add(target);
			}
			
			//调用报送组件
			sendBatchCommon(dataList);

	}

	public void sendBatchCommon(List<ChbEmployeeInfoDto> dataList)
			throws Exception {
		//获取单批次发送条目数
		int dataCountPerTime = Integer.valueOf(
		        CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME_FIRST));
		//MsgSendService msgSend = new MsgSendServiceImpl();
		logger.debug("从业人员信息登记报文报送开始------" + df.format(new Date()));
		 // 处理批次
		int handleIndex = 1;
		int dataCount = dataList.size();
		// 报送数据条数
		List<ChbEmployeeInfoDto> sendResult = new ArrayList<ChbEmployeeInfoDto>();
		List<ChbEmployeeInfoDto> newDataList = new ArrayList<ChbEmployeeInfoDto>();
		newDataList.addAll(dataList);
		//分批处理数据报送
		if (dataCount <= dataCountPerTime) {
			sdEmployInfoBatchServiceOneService
					.handleDataPerTime(handleIndex, dataCountPerTime,dataList,sendResult);
		}else{
		 // 分批次数
		     int part = dataCount / dataCountPerTime;
		     for (int i = 0; i < part; i++ ) {
		    	 sdEmployInfoBatchServiceOneService
		    			 .handleDataPerTime(handleIndex, dataCountPerTime,newDataList.subList(0, dataCountPerTime),
		    					 sendResult);
		         newDataList.subList(0, dataCountPerTime).clear();
		    	 handleIndex ++;

		     }
		     // 最后剩下的数据
		     if (!StringUtil.isEmptyList(newDataList)) {
		    	 sdEmployInfoBatchServiceOneService
		    			 .handleDataPerTime(handleIndex, dataCountPerTime,newDataList,sendResult);
		    }
		}

		logger.debug("从业人员信息登记报文报送结束------" + df.format(new Date()));
		//4、日志输出
		logger.info("从业人员信息登记自动报送处理结束, 共检索出未报送数据 " + dataList.size() + " 条 "
				    +" 实际报送数据 " + sendResult.size() + "条");
	}

	private void dealData(List<SdEmployeeInfoSvrDto> listSub) {
		listSub.forEach(item -> {
			//从业人员类型
			if(item.getEmployeeType()!=null){
				item.setEmployeeType(item.getEmployeeType().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, item.getEmployeeType()));
			}
			//性别
			if(item.getEmployeeSex()!=null){
				item.setEmployeeSex(item.getEmployeeSex().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.性别_中债, item.getEmployeeSex()));
			}
			//所属总行或分支行
			if(item.getBelongsBank()!=null){
				item.setBelongsBank(item.getBelongsBank().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.所属总行或分支行, item.getBelongsBank()));
			}
			//所属区域
			if(item.getBelongsPlace()!=null){
				item.setBelongsPlace(item.getBelongsPlace().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.销售区域_中债, item.getBelongsPlace()));
			}
			//学历
			if(item.getEducation()!=null){
				item.setEducation(item.getEducation().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.学历, item.getEducation()));
			}
			//学位
			if(item.getAcademicDegree()!=null){
				item.setAcademicDegree(item.getAcademicDegree().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.学位, item.getAcademicDegree()));
			}
			//专业技术职称
			if(item.getProfessionalTitle()!=null){
				item.setProfessionalTitle(item.getProfessionalTitle().trim()+" "
						+SvrDataUtil.getNameFromDataDict(DictCode.专业技术职称, item.getProfessionalTitle()));
			}
			//理财专业证书
		    if(item.getFinProfessionalCrtf()!=null){
		    	List<String> crtfs=StringUtil.splitToList(GlobalConstant.COMMA, item.getFinProfessionalCrtf());
				item.setFinProfessionalCrtf("");
				for(String crtf : crtfs){
					item.setFinProfessionalCrtf(item.getFinProfessionalCrtf() + "," + crtf + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.理财专业证书, crtf));
				}
				item.setFinProfessionalCrtf(item.getFinProfessionalCrtf().substring(1));
		    }
			//登记业务分类
		    if(item.getRegBusiType()!=null){
		    	List<String> regBusiTypes=StringUtil.splitToList(GlobalConstant.COMMA, item.getRegBusiType());
				item.setRegBusiType("");
				for(String regBusiType : regBusiTypes){
					item.setRegBusiType(item.getRegBusiType() + "," + regBusiType + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.登记业务分类, regBusiType));
				}
				item.setRegBusiType(item.getRegBusiType().substring(1));
		    }
			//登记人员类别
		    if(item.getRegEmployeeType()!=null){
		    	List<String> regEmployeeTypes=StringUtil.splitToList(GlobalConstant.COMMA, item.getRegEmployeeType());
				item.setRegEmployeeType("");
				for(String regEmployeeType : regEmployeeTypes){
					item.setRegEmployeeType(item.getRegEmployeeType() + "," + regEmployeeType + " "
												+SvrDataUtil.getNameFromDataDict(DictCode.登记人员类别, regEmployeeType));
				}
				item.setRegEmployeeType(item.getRegEmployeeType().substring(1));
		    }

		});
	}
}
