package com.joyintech.tams.svreport.chb.monthreport.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.service.ChbMonthReport001Service;
import com.joyintech.tams.svreport.chb.monthreport.service.ChbMonthReportMService;


/**
 * 中债G06报表报送登记Controller
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年05月08日
 * @see ChbG06MonthReport001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrG06MonthReport001")
@RequestMapping("/chb/monthreport/ChbG06MonthReport001")
public class ChbG06MonthReport001Controller extends BaseController{
    /**
     * service
     */
    @Autowired
    private ChbMonthReport001Service chbMonthReport001Service;
    @Autowired
    private ChbMonthReportMService chbMonthReportMService;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
 
	
    /**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		super.setSelectOptionToModel(model, DictCode.报送状态, DictCode.报送数据状态, DictCode.报送反馈状态);
		return super.init(model);
	}

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ChbMonthReport> searchPage() {
		Page<ChbMonthReport> page = chbMonthReportMService
				.searchPage(RequestUtil.getPageParam());
		return page;
	}

	
	/**
	 *
	 * 业务画面导出事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() {
		return chbMonthReport001Service.export(this.getPageId(), RequestUtil.getPageParam());
	}
	
	/**
	 * 手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("doManual")
	public AjaxResult doUpdate(@RequestBody Map<String, Object> map) {
		chbMonthReportMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
		chbMonthReportMService.cancelMannul(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping(value = "preView")
    public void preView(HttpServletRequest request, HttpServletResponse response) {
        String htmlExcel = chbMonthReport001Service.priview(this.getPageId(), RequestUtil.getPageParam());
        response.reset();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        InputStream res = new ByteArrayInputStream(htmlExcel.getBytes(Charset.forName("UTF-8")));
        byte[] buf = new byte[1024 * 10];
        int len = 0;
        OutputStream out;
        try {
            out = response.getOutputStream();
            while ((len = res.read(buf)) != -1)
                out.write(buf, 0, len);
            out.flush();
            res.close();
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return;
        }
    }

}
