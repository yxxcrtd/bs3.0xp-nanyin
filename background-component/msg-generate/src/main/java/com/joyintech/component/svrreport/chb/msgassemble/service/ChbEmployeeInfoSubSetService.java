package com.joyintech.component.svrreport.chb.msgassemble.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.contant.DictCode;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.contant.DictCodeListExt;
import com.joyintech.component.svrreport.chb.dto.ChbBondSubmitPara;
import com.joyintech.component.svrreport.chb.dto.ChbEmployeeInfoDto;
import com.joyintech.component.svrreport.chb.dto.ChbMsgBuildInfoDto;
import com.joyintech.component.svrreport.chb.dto.CnBondInterfItemDto;

/**
 * 从业人员登记报文生成
 * @description 
 * @author 赵永生
 * @version 1.0 2020年10月17日
 * @see ChbEmployeeInfoSubSetService
 * @since 1.0
 */
@Service
public class ChbEmployeeInfoSubSetService {

	private static Logger logger = LoggerFactory.getLogger(ChbEmployeeInfoSubSetService.class);

	@Autowired
	private ChbMsgSetCommonService chbMsgSetCommonService;

	/**
	 * 设置消息
	 * 
	 * @param datas
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ChbMsgBuildInfoDto setMsg(List<ChbEmployeeInfoDto> datas)  throws Exception {
		logger.debug("开始组装从业人员登记报送报文");
		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		ChbBondSubmitPara chbBondSubmitPara = generateBondMessage(datas);
		//拼接报文
		ChbMsgBuildInfoDto chbMsgBuildInfoDto = chbMsgSetCommonService.buildMsg(chbBondSubmitPara);
		chbMsgBuildInfoDto.setChbBondSubmitPara(chbBondSubmitPara);
		logger.debug("结束组装从业人员登记报送报文");

		return chbMsgBuildInfoDto;
	}
	
	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private ChbBondSubmitPara generateBondMessage(List<ChbEmployeeInfoDto> datas) {
		if(StringUtil.isEmptyList(datas)){
			return new ChbBondSubmitPara();
		}
		ChbBondSubmitPara res = new ChbBondSubmitPara();
		
		List<Map<String, CnBondInterfItemDto>> infos = new ArrayList<>();
		
		List<String> registSeq = new ArrayList<String>();
		Map<String,String> registSeqKeyMap = new HashMap<String,String>();
		datas.forEach(item -> {
			Map<String, CnBondInterfItemDto> cnBondItemMap = new LinkedHashMap<String, CnBondInterfItemDto>();
			String currRegistSeq = "";
			// modify by zhangliang 20210130 重新发送不需要重生生成流水号 start
			if (StringUtil.isNotEmpty(item.getSumitSeq())){
				registSeq.add(item.getSumitSeq());
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(item.getSumitSeq(), item.getEmployeeType() + ',' + item.getIdentifyNumber());	
				//add by wudebao 20210424 在para里面保存唯一标识符 end	
			}
			else{
				currRegistSeq = Sid.nextShort(20);
				item.setSumitSeq(currRegistSeq);
				registSeq.add(currRegistSeq);
				//add by wudebao 20210424 在para里面设置唯一标识符 start
				registSeqKeyMap.put(currRegistSeq, item.getEmployeeType() + ',' + item.getIdentifyNumber());	
				//add by wudebao 20210424 在para里面设置唯一标识符 end	
			}
//            if(DictCodeList.报送反馈状态_发送失败.getValue().equals(item.getSubmitFeedbackStatus())
//                    ||DictCodeList.报送反馈状态_中债退回.getValue().equals(item.getSubmitFeedbackStatus())){
//                currRegistSeq = Sid.nextShort(20);
//                registSeq.add(currRegistSeq);
//            }
//            else if(StringUtil.isEmpty(item.getSumitSeq())){
//                currRegistSeq = Sid.nextShort(20);
//                registSeq.add(currRegistSeq);
//            }
//            else{
//                registSeq.add(item.getSumitSeq());
//            }
			generateNodes(cnBondItemMap, item);
			infos.add(cnBondItemMap);
		});
		
		res.setDealType(DictCodeList.报表名称_从业人员登记.getValue());
		res.setSysDate(DateUtil.now());
	    //res.setEquityConfDate(equityConfDate);
		res.setRegistBankCode(datas.get(0).getIssuePartyId());
	//	List<String> registSeq = new ArrayList<String>();
		
		res.setRegistSeq(registSeq);
		res.setInfos(infos);
		//add by wudebao 20210424 在para里面设置唯一标识符唯一标识符 start		
		res.setRegistSeqKeyMap(registSeqKeyMap);		
		//add by wudebao 20210424 在para里面保存唯一标识符 end	

		return res;
	}
	/**
	 * 生成标准接口的数据结构
	 * 
	 * @param datas 业务数据
	 * @return 标准接口的数据结构
	 */
	private void generateNodes(Map<String, CnBondInterfItemDto> cnBondItemMap1, ChbEmployeeInfoDto sub) {
	    //发行机构代码
		if(StringUtil.isNotEmpty(sub.getIssuePartyId())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getIssuePartyId());
	    cnBondItemMap1.put("IssuerIdentificationCode", cnBondItem1);
		}
	    //从业人员类型
		if(StringUtil.isNotEmpty(sub.getEmployeeType())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getEmployeeType());
	    cnBondItemMap1.put("Profession", cnBondItem1);
		}
	    //从业人员姓名
		if(StringUtil.isNotEmpty(sub.getEmployeeName())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getEmployeeName());
	    cnBondItemMap1.put("Name", cnBondItem1);
		}
	    //性别
		if(StringUtil.isNotEmpty(sub.getEmployeeSex())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getEmployeeSex());
	    cnBondItemMap1.put("Gender", cnBondItem1);
		}
	    //身份证号
		if(StringUtil.isNotEmpty(sub.getIdentifyNumber())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getIdentifyNumber());
	    cnBondItemMap1.put("PractitionerIdNumber", cnBondItem1);
		}
	    //所属总行或分支行
		if(StringUtil.isNotEmpty(sub.getBelongsBank())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getBelongsBank());
	    cnBondItemMap1.put("HeadquartersBranchOfficePleaseSelect", cnBondItem1);
		}
	    //所属区域
		if(StringUtil.isNotEmpty(sub.getBelongsPlace())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getBelongsPlace());
	    cnBondItemMap1.put("Region", cnBondItem1);
		}
	    //具体单位名称
		if(StringUtil.isNotEmpty(sub.getCompanyName())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getCompanyName());
	    cnBondItemMap1.put("FirmName", cnBondItem1);
		}
	    //所属部门
		if(StringUtil.isNotEmpty(sub.getBelongsDept())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getBelongsDept());
	    cnBondItemMap1.put("Department", cnBondItem1);
		}
	    //职位
		if(StringUtil.isNotEmpty(sub.getPosition())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getPosition());
	    cnBondItemMap1.put("Position", cnBondItem1);
		}
	    //学历
		if(StringUtil.isNotEmpty(sub.getEducation())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getEducation());
	    cnBondItemMap1.put("Education", cnBondItem1);
		}
	    //学位
		if(StringUtil.isNotEmpty(sub.getAcademicDegree())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getAcademicDegree());
	    cnBondItemMap1.put("Degree", cnBondItem1);
		}
	    //首次参加工作时间
		if(StringUtil.isNotEmpty(sub.getFirsttime2work())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getFirsttime2work());
	    cnBondItemMap1.put("CareerStartDate", cnBondItem1);
		}
	    //首次从事理财业务时间
		if(StringUtil.isNotEmpty(sub.getFirsttime2finwork())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getFirsttime2finwork());
	    cnBondItemMap1.put("StartDateOfWorkingExperienceInWealthManagement", cnBondItem1);
		}
	    //专业技术职称
		if(StringUtil.isNotEmpty(sub.getProfessionalTitle())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getProfessionalTitle());
	    cnBondItemMap1.put("LevelofProfessionalQualifications", cnBondItem1);
		}
	    //理财专业证书
	    if(StringUtil.isNotEmpty(sub.getFinProfessionalCrtf())){
	    CnBondInterfItemDto cnBondItem15 = new CnBondInterfItemDto();
	    List<Map<String, CnBondInterfItemDto>> mapList1 = new ArrayList<Map<String, CnBondInterfItemDto>>();
	    String finProfessionalCrtf = sub.getFinProfessionalCrtf();
	    String [] fpc = finProfessionalCrtf.split(ChbConstant.COMMA);
	    for(String str : fpc){
		    Map<String, CnBondInterfItemDto> cnBondItemMapSon =  new LinkedHashMap<String, CnBondInterfItemDto>();//子节点map
		    CnBondInterfItemDto cnBondItem15_1 = new CnBondInterfItemDto();//第一个子节点
		    cnBondItem15_1.setValue(str);
		    cnBondItemMapSon.put("WealthManagementCertification",cnBondItem15_1);
		    mapList1.add(cnBondItemMapSon);
	    }
	    cnBondItem15.setMaps(mapList1);//第一个父子节点组合结束
	    cnBondItemMap1.put("WealthManagementCertificationTuple", cnBondItem15);
	    }
	    //理财登记培训证书编号
		if(StringUtil.isNotEmpty(sub.getFinTrainingNo())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getFinTrainingNo());
	    cnBondItemMap1.put("WealthManagementRegistrationTrainingCertificateNumber", cnBondItem1);
		}
	    //所获奖励
		if(StringUtil.isNotEmpty(sub.getAwards())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getAwards());
	    cnBondItemMap1.put("RewardsInWealthManagement", cnBondItem1);
		}
	    //办公电话
		if(StringUtil.isNotEmpty(sub.getOfficeTel())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getOfficeTel());
	    cnBondItemMap1.put("WorkPhoneNumberForPractitioner", cnBondItem1);
		}
	    //移动电话
		if(StringUtil.isNotEmpty(sub.getMobileTel())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getMobileTel());
	    cnBondItemMap1.put("MobilePhoneNumberForPractitioner", cnBondItem1);
		}
	    //从业人员电子邮箱
		if(StringUtil.isNotEmpty(sub.getMail())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getMail());
	    cnBondItemMap1.put("Email", cnBondItem1);
		}
	    //登记业务分类
	    if(StringUtil.isNotEmpty(sub.getRegBusiType())){
	    CnBondInterfItemDto cnBondItem15 = new CnBondInterfItemDto();
	    List<Map<String, CnBondInterfItemDto>> mapList1 = new ArrayList<Map<String, CnBondInterfItemDto>>();
	    String regBusiType = sub.getRegBusiType();
	    String [] rpt = regBusiType.split(ChbConstant.COMMA);
	    for(String str : rpt){
		    Map<String, CnBondInterfItemDto> cnBondItemMapSon =  new LinkedHashMap<String, CnBondInterfItemDto>();//子节点map
		    CnBondInterfItemDto cnBondItem15_1 = new CnBondInterfItemDto();//第一个子节点
		    cnBondItem15_1.setValue(str);
		    cnBondItemMapSon.put("RegistrationClassification",cnBondItem15_1);
		    mapList1.add(cnBondItemMapSon);
	    }
	    cnBondItem15.setMaps(mapList1);//第一个父子节点组合结束
	    cnBondItemMap1.put("RegistrationClassificationTuple", cnBondItem15);
	    }
	    //登记人员类别
	    if(StringUtil.isNotEmpty(sub.getRegEmployeeType())){
	    CnBondInterfItemDto cnBondItem15 = new CnBondInterfItemDto();
	    List<Map<String, CnBondInterfItemDto>> mapList1 = new ArrayList<Map<String, CnBondInterfItemDto>>();
	    String regEmployeeType = sub.getRegEmployeeType();
	    String [] ret = regEmployeeType.split(ChbConstant.COMMA);
	    for(String str : ret){
		    Map<String, CnBondInterfItemDto> cnBondItemMapSon =  new LinkedHashMap<String, CnBondInterfItemDto>();//子节点map
		    CnBondInterfItemDto cnBondItem15_1 = new CnBondInterfItemDto();//第一个子节点
		    cnBondItem15_1.setValue(str);
		    cnBondItemMapSon.put("TypeOfRegistrar",cnBondItem15_1);
		    mapList1.add(cnBondItemMapSon);
	    }
	    cnBondItem15.setMaps(mapList1);//第一个父子节点组合结束
	    cnBondItemMap1.put("TypeOfRegistrarTuple", cnBondItem15);
	    }
	    //从业人员登记备注
		if(StringUtil.isNotEmpty(sub.getRemark())){
	    CnBondInterfItemDto cnBondItem1 = new CnBondInterfItemDto();
	    cnBondItem1.setValue(sub.getRemark());
	    cnBondItemMap1.put("PractitionerRegistrationDetails", cnBondItem1);
		}
		//登记流水号
		if(StringUtil.isNotEmpty(sub.getSumitSeq())){
	    CnBondInterfItemDto cnBondItem19 = new CnBondInterfItemDto();
	    cnBondItem19.setValue(sub.getSumitSeq());
	    cnBondItemMap1.put("RegistrationSerialNumber", cnBondItem19);
		}
	}
}
