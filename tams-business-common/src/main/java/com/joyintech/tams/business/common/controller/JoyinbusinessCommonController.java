package com.joyintech.tams.business.common.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.business.common.dto.EntrustIdRequestDto;
import com.joyintech.tams.business.common.dto.SecManageAcctIdRequestDto;
import com.joyintech.tams.business.common.entity.JoyinDataDictEntity;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.service.JoyinbusinessCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/common/joyinbusinessCommon")
@Api(tags = "公共方法")
public class JoyinbusinessCommonController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoyinbusinessCommonController.class);
	

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private JoyinbusinessCommonService joyinbusinessCommonService;

	@PostMapping("/getSelectDataFromDataDic")
	@ApiOperation(value = "数据字典查询功能", notes = "数据字典查询功能")
	@ResponseBody
	public Message<SelectDto> getSelectDataFromDataDic(@RequestBody String  dc) {
		try {

			JoyinDataDictEntity dictCode=new JoyinDataDictEntity();
			dictCode.setValue(dc);
			SelectDto selectDto = this.getSelectDataFromDataDic(dictCode, null);
			return new Message<SelectDto>().success().setData(selectDto);
		} catch (Exception e) {
			logger.error("查询功能异常", e);
			return new Message<SelectDto>().fail().setMessage("查询异常");
		}
	}

	@PostMapping("/getSecManageAcctId")
	@ApiOperation(value = "证券账户列表查询", notes = "证券账户列表查询")
	@ResponseBody
	public Message<Page<SelectOptionDto>> getSecManageAcctId(@RequestBody SecManageAcctIdRequestDto secManageAcctIdRequestDto) {
		try {
			Page<SelectOptionDto> tempList = joyinbusinessCommonService.getSecManageAcctId(secManageAcctIdRequestDto);
			return new Message<Page<SelectOptionDto>>().success().setData(tempList);
		} catch (Exception e) {
			logger.error("查询功能异常", e);
			return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
		}
	}

	@PostMapping("/getEntrustId")
	@ApiOperation(value = "指令代码列表查询", notes = "指令代码列表查询")
	@ResponseBody
	public Message<Page<SelectOptionDto>> getEntrustId(@RequestBody EntrustIdRequestDto entrustIdRequestDto) {
		try {
			Page<SelectOptionDto> tempList = joyinbusinessCommonService.getEntrustId(entrustIdRequestDto);
			return new Message<Page<SelectOptionDto>>().success().setData(tempList);
		} catch (Exception e) {
			logger.error("查询功能异常", e);
			return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
		}
	}
}
