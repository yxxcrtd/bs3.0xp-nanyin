package com.joyintech.service.bankuplink.basic.errorcodeinfocompare.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuIntfErrMsgDto;
import com.joyintech.service.bankuplink.basic.errorcodeinfocompare.dto.ErrorCodeInfoCompareDto;
import com.joyintech.service.bankuplink.basic.errorcodeinfocompare.service.ErrorCodeInfoCompareService;

@RestController("backErrorCodeInfoCompareController")
@RequestMapping("/ErrorCodeInfoCompare")
public class ErrorCodeInfoCompareController {
	
	/**
	 * 错误码信息查询Service
	 */
	@Autowired
    private ErrorCodeInfoCompareService errorCodeInfoCompareService;
	
	/**
	 * 错误码信息查询 查询
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@PostMapping("queryForList")
    public List<ErrorCodeInfoCompareDto> queryForList(@RequestBody Map<String, Object> param)
    		throws Exception {
        return errorCodeInfoCompareService.queryForList(param);
    }
	
	/**
	 * 错误码信息查询 双击查看
	 * @param errCode
	 * @return
	 */
	@RequestMapping("selectByErrCode")
    public ErrorCodeInfoCompareDto selectByErrCode(String errCode){
        return errorCodeInfoCompareService.selectByErrCode(errCode);
    }
	
	/**
	 * 错误码信息查询  分页查询
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/selectForPage/{page}/{length}")
	public Page<IbuIntfErrMsgDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
	throws Exception {
	return errorCodeInfoCompareService.selectForPage(page, length, param);
	}

}
