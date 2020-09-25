package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.valid;

import java.util.*;
import java.util.stream.Collectors;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividSpecialAssetDto;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskIndividSpecialStageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto.RskSupervisionIndividuationDto;
@Component
public class RskSupervisionIndividuation002Validator implements BeanValidator {

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
	@Override
	public List<ValidMessage> valid(Object value) {
		ArrayList<ValidMessage> errorList = new ArrayList<>();
		RskSupervisionIndividuationDto dto = (RskSupervisionIndividuationDto)value;
		WflValidInputParameter params = new WflValidInputParameter();
        params.setBusinessDataPk1(dto.getPrtBagId());
        params.setBusinessDataPk2(dto.getIndId());
        params.setPageId("RskSupervisionPortfPoint002");
        //查询该指标是否有修改或删除流程流程
        if(null != mstWorkflowCommonService && 
            mstWorkflowCommonService.isInWflProcess(params)){
        	errorList.add(
                new ValidMessage(this.getMessage("BUSS_COM_E_0023")));
        }
        
        // add by wangshuang 判断是否存在日期重叠 2019-12-04 start
		//-----update by peijiajun 2019-12-09 更改对象类型的引用方式-------------
		List<RskIndividSpecialAssetDto> specialAssetList=new ArrayList<>();
		List<RskIndividSpecialStageDto> specialStageList=new ArrayList<>();
		if(StringUtil.isNotEmpty(dto.getSpecialAssetList()))
			specialAssetList.addAll(dto.getSpecialAssetList());
		if(StringUtil.isNotEmpty(dto.getSpecialStageList()))
			specialStageList.addAll(dto.getSpecialStageList());
		//-----update by peijiajun 2019-12-09 更改对象类型的引用方式-------------
        //判断是否存在阈值重叠,下一条的开始时间和结束时间都不能在存在的开始|结束时间段里
		if(isConflictDate(specialStageList,BussinessDictCodeListRds.阶段类型_特殊阶段.getValue())){
			errorList.add(new ValidMessage(this.getMessage("RSKRDS_W_0076")));
		}
		if(isConflictDate(specialAssetList,BussinessDictCodeListRds.阶段类型_特殊资产阶段.getValue())){
			errorList.add(new ValidMessage(this.getMessage("RSKRDS_W_0077")));
		}
		// add by wangshuang 判断是否存在日期重叠 2019-12-04 end
        return errorList;
	}
	
	/**
	 * add by wangshuang 判断阈值设置日期是否存在重叠冲突 2019-12-04
	 * @param specialList 
	 * @return true 存在重叠 false 不存在重叠
	 */
	private boolean isConflictDate(List<? extends RskIndividSpecialStage> specialList,String type){
		if (!StringUtil.isEmptyList(specialList)) {
			if(StringUtil.equals(type, BussinessDictCodeListRds.阶段类型_特殊阶段.getValue())) {
				Date startDate = specialList.get(0).getStartDate();
				Date endDate = specialList.get(0).getEndDate();
				specialList.remove(0);
				for (RskIndividSpecialStage specialDto : specialList) {
					if (!(DateUtil.compareDate(specialDto.getStartDate(), endDate) > 0
							|| DateUtil.compareDate(specialDto.getEndDate(), startDate) < 0)) {
						return true;
					}
				}
			}
			else{
				Map<String, List<RskIndividSpecialStage>> indSpecialListMap = specialList.stream().collect(
						Collectors.groupingBy(RskIndividSpecialStage::getFinprodId));
				for(String key :indSpecialListMap.keySet()){
					List<RskIndividSpecialStage> subSpecialList=indSpecialListMap.get(key);
					Date startDate = subSpecialList.get(0).getStartDate();
					Date endDate = subSpecialList.get(0).getEndDate();
					subSpecialList.remove(0);
					for (RskIndividSpecialStage specialDto : subSpecialList) {
						if (!(DateUtil.compareDate(specialDto.getStartDate(), endDate) > 0
								|| DateUtil.compareDate(specialDto.getEndDate(), startDate) < 0)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
