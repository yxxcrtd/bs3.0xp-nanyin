package com.joyintech.tams.business.common.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.tams.business.common.entity.JoyinDataDictEntity;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common/joyinbusinessCommon")
@Api(tags = "公共方法")
public class JoyinbusinessCommonController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(JoyinbusinessCommonController.class);
	

	@Autowired
	private HttpServletRequest request;

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
	
}
