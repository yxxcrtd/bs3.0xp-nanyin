package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service.ValNetValueConfirm003Service;
import com.joyintech.system.excelimp.dto.ExcelImpFileInfoDto;
import com.joyintech.system.excelimp.dto.ExcelImpResultDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 附件上传
 * 
 * @author Administrator
 *
 */
//@RestController(value="serviceValNetValueConfirm003Contoller")
@RestController("valNetValueConfirm003Ctl_dtx")
@RequestMapping("/ValNetValueConfirm003")
public class ValNetValueConfirm003Contoller extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ValNetValueConfirm003Service valNetValueConfirm003Service;

	@Autowired
	private BaseCommonService baseCommonService;

	/**
	 * 重写init方法，加载页面 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
	    //产品代码
	    String finprodId = this.getParam("finprodId");
	    model.addAttribute("finprodId", finprodId);
	    //产品简称
	    String finprodAbbr = this.getParam("finprodAbbr");
        model.addAttribute("finprodAbbr", finprodAbbr);
        //估值表余额表标识
	    String valBalFlagCode = this.getParam("valBalFlagCode");
	    if(valBalFlagCode!=null){
	        if(valBalFlagCode.equals(DictCodeList.文件类型_估值表.getValue())){
	            model.addAttribute("valBalFlag", DictCodeList.文件类型_估值表.getCodeName());
	        }else {
	            model.addAttribute("valBalFlag", DictCodeList.文件类型_余额表.getCodeName());
	        }
	    }
	    //估值日期
	    String valDate = this.getParam("valDate");
        model.addAttribute("valDate", valDate);
		String sysDate = baseCommonService.getSysDateString();
		if (sysDate == null || "".equals(sysDate)) {
			sysDate = DateUtil.format(new Date());
		}
		model.addAttribute("sysDate", sysDate);
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
    @PostMapping("/findPage/{page}/{length}")
    public Page<BokFileTransfer003Dto> findPage(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valNetValueConfirm003Service.findPage(page,length,map);
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
    @Transactional
    @DTXTransaction
    @PostMapping("/uploadFile")
	public int insertFileTransferInfo(@RequestBody Map<String, Object> params) throws Exception {
		 return valNetValueConfirm003Service.insertFileTransferInfo(params);
        
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
    @Transactional
    @DTXTransaction
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
//    @RequestMapping(value = "/downloadFile")
//    @ResponseBody
//    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request)
//            throws Exception { 
//           return valNetValueConfirm003Service.downloadFile(request);
//    }
    
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
    @Transactional
    @DTXTransaction
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public void deleteFile(HttpServletRequest request)
            throws Exception { 
           valNetValueConfirm003Service.deleteFile(request);
    }
    
    
    @PostMapping("/downloadFile")
    public BokFileTransfer003Dto downloadFile(@RequestBody String fileId) throws Exception{
    	return valNetValueConfirm003Service.downloadFile(fileId);
    }
    
}
