/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.manage.workrule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.manage.workrule.dao.ReportRule002Mapper;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule001Dto;
import com.joyintech.service.disclosure.information.manage.workrule.dto.ReportRule002Dto;
import com.joyintech.tams.common.base.dao.RepRuleMapper;
import com.joyintech.tams.common.base.entity.RepRule;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;

/**
 * 
 * 报表规则查询Service
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceReportRule002Service")
public class ReportRule002Service extends CommonExportService<ReportRule001Dto> {

	/**
	 */
	@Autowired
	private ReportRule002Mapper reportRule002Mapper;
	@Autowired
	private RepRuleMapper repRuleMapper;

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(ReportRule002Service.class);

	/**
	 * 查询报表名称
	 * 
	 * @return
	 */
	public List<SelectOptionDto> getReportInfo() {
		List<RepRule> reportInfoList = reportRule002Mapper.getReportInfo();
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
		for (RepRule reportInfo : reportInfoList) {
			SelectOptionDto dto = new SelectOptionDto();
			dto.setId(reportInfo.getRepId());
			dto.setText(reportInfo.getRepName());
			listSelectDto.add(dto);
		}
		return listSelectDto;
	}

	/**
	 * 获取披露渠道
	 * 
	 * @return
	 */
//	public List<SelectOptionDto> getRevealChannel() {
//		List<? extends DataDictItem> channelList = SysDataUtil.getDataDictItem("REVEAL_CHANNEL");
//		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//		for (DataDictItem channel : channelList) {
//			SelectOptionDto dto = new SelectOptionDto();
//			dto.setId(channel.getItemCode());
//			dto.setText(channel.getItemValue());
//			listSelectDto.add(dto);
//		}
//		return listSelectDto;
//	}

	/**
	 * 入库
	 * 
	 * @param param
	 * @return
	 */
	public List<ValidMessage> insert(ReportRule002Dto dto) {
		dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
		List<ValidMessage> checkList = checkInfo(dto);
		if (StringUtil.isEmptyList(checkList)) {
			dto.setRevealTime(dto.getRevealTime() + ":00");
			repRuleMapper.insert(dto);
			return null;
		}
		return checkList;
	}

	/**
	 * 非空check
	 * 
	 * @param dto
	 * @return
	 */
	private List<ValidMessage> checkInfo(ReportRule002Dto dto) {
		int repType =reportRule002Mapper.selectRepType(dto.getRepId());
		List<ValidMessage> checkList = new ArrayList<ValidMessage>();
		if(StringUtil.equals(dto.getDataType(), DictCodeListExt2.报告数据类型_时点报表.getValue())&&repType>1){
			checkList.add(new ValidMessage("报表数据类型不正确，该报表数据类型只能为区间报表"));
		}
		if(StringUtil.equals(dto.getDataType(), DictCodeListExt2.报告数据类型_区间报表.getValue())&&repType<2){
			checkList.add(new ValidMessage("报表数据类型不正确，该报表数据类型只能为时点报表"));
		}
		if (StringUtil.isEmpty(dto.getRuleName())) {
			checkList.add(new ValidMessage("规则名称不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRepName())) {
			checkList.add(new ValidMessage("报表名称不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRcfType())) {
			checkList.add(new ValidMessage("披露频率类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getBasisDateType())) {
			checkList.add(new ValidMessage("基准日类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getMakeType())) {
			checkList.add(new ValidMessage("生成时点类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getMakeValue())) {
			checkList.add(new ValidMessage("生成时点值不能为空"));
		}
		if (StringUtil.isEmpty(dto.getMakeDateType())) {
			checkList.add(new ValidMessage("生成时点日期类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRevealType())) {
			checkList.add(new ValidMessage("披露时点类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRevealValue())) {
			checkList.add(new ValidMessage("披露时点不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRevealDateType())) {
			checkList.add(new ValidMessage("披露时点日期类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getRevealTime())) {
			checkList.add(new ValidMessage("披露时点时间不能为空"));
		}
		if (StringUtil.isEmpty(dto.getDataType())) {
			checkList.add(new ValidMessage("报表数据类型不能为空"));
		}
		if (StringUtil.isEmpty(dto.getDataRange())) {
			checkList.add(new ValidMessage("取数范围不能为空"));
		}
		if (StringUtil.equals(dto.getDataType(), "02")
				&& (StringUtil.equals(dto.getDataRange(), "03") || StringUtil.equals(dto.getDataRange(), "04"))) {
			if (StringUtil.isEmpty(dto.getDataEnd())) {
				checkList.add(new ValidMessage("取数结束日不能为空"));
			}
			if (StringUtil.isEmpty(dto.getDataBegin())) {
				checkList.add(new ValidMessage("取数开始日不能为空"));
			}
		}
		if (StringUtil.isEmpty(dto.getFileOutType())) {
			checkList.add(new ValidMessage("默认输出格式不能为空"));
		}
		if (StringUtil.isEmpty(dto.getIsConfirm())) {
			checkList.add(new ValidMessage("确认方式不能为空"));
		}
		if (StringUtil.isEmpty(dto.getIsReveal())) {
			checkList.add(new ValidMessage("披露方式不能为空"));
		}
		if (dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
			if (reportRule002Mapper.getRuleName(dto.getRuleName()) != 0) {
				checkList.add(new ValidMessage("报表规则名称已存在"));
			}
		}
		if (dto.getPageModel().equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
			RepRule ruleDto = repRuleMapper.selectByPrimaryKey(dto.getId());
			if (!StringUtil.equals(dto.getRuleName(), ruleDto.getRuleName())
					&& reportRule002Mapper.getRuleName(dto.getRuleName()) != 0) {
				checkList.add(new ValidMessage("报表规则名称已存在"));
			}
		}
		return checkList;
	}

	/**
	 * 更新
	 * 
	 * @param dto
	 * @return
	 */
	public List<ValidMessage> update(ReportRule002Dto dto) {
		List<ValidMessage> checkList = new ArrayList<ValidMessage>();
		if (null == repRuleMapper.selectByPrimaryKey(dto.getId())) {
			checkList.add(new ValidMessage("报表规则不存在"));
			return checkList;
		}
		checkList = checkInfo(dto);
		if (StringUtil.isEmptyList(checkList)) {
			if(!isTime(dto.getRevealTime()))
			{
				dto.setRevealTime(dto.getRevealTime() + ":00");
			}
			repRuleMapper.updateByPrimaryKey(dto);
			return null;
		}
		return checkList;
	}
	/**
	 * 初始化编辑页面信息
	 * 
	 * @param ruleId
	 * @return
	 */
	public ReportRule002Dto ReportRule002Dto(String ruleId) {
		ReportRule002Dto dto = null;
		RepRule repRule = repRuleMapper.selectByPrimaryKey(ruleId);
		dto = JsonUtils.json2Object(JSONObject.toJSONString(repRule), ReportRule002Dto.class);
		return dto;
	}

	public int delete(String id) {
		return repRuleMapper.deleteByPrimaryKey(id);

	}

	public int selectById(String id) {
		if (null == repRuleMapper.selectByPrimaryKey(id)) {
			return 0;
		}
		return 1;
	}
	public static boolean isTime(String time){
		  Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		  return p.matcher(time).matches();
		}

}
