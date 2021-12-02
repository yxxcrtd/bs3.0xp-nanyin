package com.joyintech.service.entrust.common.controller;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.service.EntrustBankCommonService;
import com.joyintech.service.entrust.common.service.EntrustCommonService;
import com.joyintech.system.base.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("EntrustCommonCtr_dtx")
@RequestMapping("/EntrustCommon")
public class EntrustCommonController {

	@Autowired
	private EntrustCommonService entrustCommonService;

	/**
	 * 查询是否sppi测试
	 * @param pageParam
	 * @return
	 */
	@PostMapping("assetIsTestBySppi")
	public String assetIsTestBySppi(@RequestBody Map<String, Object> pageParam) {
		return entrustCommonService.assetIsTestBySppi(pageParam);
	}

	@PostMapping("getAllUserList")
	public List<SysUser> getAllUserList(@RequestBody Map<String, Object> map)
	{
		return entrustCommonService.getAllUserList(map);
	}

	@PostMapping("getZyqk")
	public String getZyqk(@RequestBody String param) {
		return entrustCommonService.getZyqk(param);
	}
}
