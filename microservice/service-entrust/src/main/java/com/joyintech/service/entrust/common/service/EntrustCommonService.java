/*
 * 文件名：EntrustCommonPersonSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.common.dao.EntrustBankCommonMapper;
import com.joyintech.service.entrust.common.dao.EntrustCommonMapper;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.EtrFinishStatusKey;
import com.joyintech.tams.common.base.entity.EtrQuoteFinishStatus;
import com.joyintech.tams.invest.common.component.base.entrust.impl.dao.BaseEtrInvestInfoMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投资委托人员信息下拉框查询
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月9日
 * @see EntrustCommonService
 * @since 1.0
 */
@Service
public class EntrustCommonService extends BaseService<ITableRow> {


	@Autowired
	private EntrustCommonMapper entrustCommonMapper;

	public String assetIsTestBySppi(Map<String, Object> map) {
		return entrustCommonMapper.assetIsTestBySppi(map);
	}

	public List<SysUser> getAllUserList(Map<String, Object> map) {
		return entrustCommonMapper.getAllUserList();
	}

	public String getZyqk(String param) {
		return entrustCommonMapper.getZyqk(param);
	}
}
