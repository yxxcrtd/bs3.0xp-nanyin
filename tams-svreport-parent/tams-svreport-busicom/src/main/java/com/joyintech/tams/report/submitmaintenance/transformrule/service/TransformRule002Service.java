package com.joyintech.tams.report.submitmaintenance.transformrule.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.service.EquityBusiDetails002Service;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo002Service;
import com.joyintech.tams.report.submitmaintenance.transformrule.dao.TransformRule001Mapper;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 变形转换规则 service
 *
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRule002Service
 * @since 1.0
 */
@Service("transformRule002Service")
@Transactional(rollbackFor = Exception.class)
public class TransformRule002Service extends BaseService<TransformRule001Dto> {

	/**
	 * TransformRule001Mapper
	 */
	@Autowired
	private TransformRule001Mapper transformRule001Mapper;
	
	@Autowired
	private EquityBusiDetails002Service equityBusiDetails002Service;
	
	@Autowired
	private EquityInfo002Service equityInfo002Service;
	
	

	public int checkData(String targetField) {
		int count  = transformRule001Mapper.check(targetField);
		return count;
	}

	public void doSave(TransformRule001Dto dto) {
//		dto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
//		dto.setIsModify(DictCodeList.是否_是.getValue());
//		dto.setCreateUser(SecurityUtil.getLoginUser().getUserName());
//		dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
//		dto.setCreateDept(CommonUtil.getDeptCode());
		transformRule001Mapper.insertTransField(dto);
	}
	
	
	public void doEditSave(TransformRule001Dto dto) {
//		dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
		transformRule001Mapper.doEditSave(dto);
	}

	/**
	 * 获取entity实例 <功能详细描述>
	 * 
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public TransformRule001Dto getEntity(Map<String, Object> map) {
		TransformRule001Dto dto =transformRule001Mapper.getDtoBySeqno(map.get("seqno").toString());
		return dto;
	}
	
	
	public TransformRule001Dto getDto(String seqno) {
		TransformRule001Dto dto =transformRule001Mapper.getDtoBySeqno(seqno);
		return dto;
	}
	
	public void excuteRefresh(){
		//刷新身份信息
		equityInfo002Service.excuteEquityInfoRefresh();
		//刷新明细信息
		equityBusiDetails002Service.doEquityBusiDetailsRefresh();
    }

	
	
}
