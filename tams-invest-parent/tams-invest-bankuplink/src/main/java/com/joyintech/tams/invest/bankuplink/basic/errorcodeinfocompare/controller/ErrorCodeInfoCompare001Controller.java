package com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto.ErrorCodeInfoCompare001Dto;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto.IbuIntfErrMsgDto;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.service.ErrorCodeInfoCompare001Service;
/**
 * 错误码信息查询 Controller
 */
@Controller
@PageId("ErrorCodeInfoCompare001")
@RequestMapping("/invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare001")
public class ErrorCodeInfoCompare001Controller extends BaseController{
	/**
	 * 错误码信息查询 Service
	 */
	@Autowired
	ErrorCodeInfoCompare001Service errorCodeInfoCompare001Service;
	/**
	 * 初始化 init()
	 */
	@Override
	public String init(Model model){
		
		return super.init(model);
	}
	
	/**
	 * 错误码信息查询 查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryForList")
    @ResponseBody
    public List<ErrorCodeInfoCompare001Dto> queryForList() throws Exception {
        return errorCodeInfoCompare001Service.queryForList(RequestUtil.getPageParam());
    }
	
	/**
	 * 错误码信息查询 导出
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        return errorCodeInfoCompare001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }
    
	@ResponseBody
	@RequestMapping("findPage")
	public Page<IbuIntfErrMsgDto> selectForPage() throws Exception {

		Page<IbuIntfErrMsgDto> page = errorCodeInfoCompare001Service.selectForPage(RequestUtil.getPageParam());
		return page;
	}

}
