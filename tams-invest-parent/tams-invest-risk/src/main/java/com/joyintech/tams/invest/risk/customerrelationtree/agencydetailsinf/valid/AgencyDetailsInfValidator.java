/*
 * 文件名：RskAuthorityManageValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.dto.SeniorExecutiveInfDto;
import com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.service.AgencyDetailsInf002Service;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzehui
 * @version 1.0 2018年11月30日
 * @see AgencyDetailsInfValidator
 * @since 1.0
 */
@Component
public class AgencyDetailsInfValidator implements BeanValidator {

	@Autowired
	private AgencyDetailsInf002Service agencyDetailsInf002Service;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidMessage> valid(Object value) {
		SeniorExecutiveInfDto dto = (SeniorExecutiveInfDto) value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();
		if (StringUtil.equals(dto.getCorClass(), "1") && StringUtil.equals(dto.getOperateType(), "new")) {
			// 添加 check验证
			List<SeniorExecutiveInfDto> list = dto.getSelectedItem();
			if (list.size() > 0 && list != null) {
				for (SeniorExecutiveInfDto e : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orgNo", e.getOrgNo());
					map.put("executiveName", e.getExecutiveName());
					map.put("title", e.getTitle());
					List<SeniorExecutiveInfDto> lst = new ArrayList<SeniorExecutiveInfDto>();
					try {
						lst = agencyDetailsInf002Service.getCorrelationInf(map);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (lst != null && lst.size() > 0) {
						SeniorExecutiveInfDto entity = lst.get(0);
						resultList.add(new ValidMessage(this.getMessage("RSK_W_0070", entity.getExecutiveName(), entity.getTitle())));
					}
				}
			}
		} else if (StringUtil.equals(dto.getCorClass(), "0") && StringUtil.equals(dto.getOperateType(), "new")) {
			List<SeniorExecutiveInfDto> lst = new ArrayList<SeniorExecutiveInfDto>();
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("corClass", dto.getCorClass());
				map.put("orgNo", dto.getOrgNo());
				map.put("corOrgid", dto.getCorOrgid());
				map.put("corOrgtype", dto.getCorOrgtype());
				lst = agencyDetailsInf002Service.getCorrelationInf(map);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (lst != null && lst.size() > 0) {
				resultList.add(new ValidMessage(this.getMessage("RSK_W_0071")));
			}
		}
		return resultList;
	}

}
