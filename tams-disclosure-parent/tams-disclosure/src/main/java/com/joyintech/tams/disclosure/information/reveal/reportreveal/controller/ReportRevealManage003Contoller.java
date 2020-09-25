package com.joyintech.tams.disclosure.information.reveal.reportreveal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.excelimp.dto.ExcelImpFileInfoDto;
import com.joyintech.system.excelimp.dto.ExcelImpResultDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreveal.service.ReportRevealManage003Service;

/**
 * 附件上传
 * 
 * @author Administrator
 *
 */
@Controller
@PageId("ReportRevealManage003")
@RequestMapping("/disclosure/information/reveal/reportreveal/ReportRevealManage003")
public class ReportRevealManage003Contoller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ReportRevealManage003Service reportRevealManage003Service;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		return super.init(model);
	}
	
	/**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<BokFileTransfer003Dto> findPage() throws Exception{
//        return valNetValueReveal003Service.findPage(RequestUtil.getPageParam());
        return null;
    }

	/**
	 * 文件上传
	 *
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param model
	 *            ModelAndView
	 * @return 保存文件信息
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
	    AjaxResult res = new AjaxResult();
		 int num = reportRevealManage003Service.updateRepRevealById(RequestUtil.getPageParam());
         if(num>0){
             res = new AjaxResult(getMessage("EXCELIMP_E_0020"), MessageType.提示消息);
         }else {
             String msg = "上传信息入库失败";
             res = new AjaxResult(msg, MessageType.错误消息);
         }
		return res;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 *            request
	 * @param impFileInfo
	 *            导入文件信息
	 * @return 画面结果
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
	public ExcelImpResultDto save(HttpServletRequest request, @RequestBody ExcelImpFileInfoDto impFileInfo)
			throws Exception {
		return null;
	}
	
	
	/**
     * 文件下载
     *
     * @param request
     *            请求
     * @return 保存文件信息
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request)
            throws Exception { 
           return null;
    }
    
    /**
     * 文件下载信息删除
     *
     * @param request
     *            请求
     * @return 保存文件信息
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public void deleteFile(HttpServletRequest request)
            throws Exception { 
    }
}
