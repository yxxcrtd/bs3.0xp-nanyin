package com.joyintech.tams.valuation.system.filedownload.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValRecvServerInfoEntity;
import com.joyintech.tams.valuation.system.filedownload.service.InterfFileDownload001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 系统文件上传
 * 
 * @author Administrator
 *
 */
@Controller
@PageId("InterfFileDownload001")
@RequestMapping("/valuation/system/filedownload/InterfFileDownload001")
public class InterfFileDownload001Controller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private InterfFileDownload001Service interfFileDownload001Service;

	@Autowired
	private BaseCommonService baseCommonService;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String sysDate = baseCommonService.getSysDateString();
		if (sysDate == null || "".equals(sysDate)) {
			sysDate = DateUtil.format(new Date());
		}
		model.addAttribute("sysDate", sysDate);
		return super.init(model);
	}

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValRecvServerInfoEntity dto, BindingResult result) {
        AjaxResult ajaxResult = new AjaxResult();
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        
        return ajaxResult;
    }
    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception{
        return interfFileDownload001Service.exportFile(RequestUtil.getParameters(getRequest()));
        
    }
}
