/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.config.ShiroProperties;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.poireport.dto.DisReportType;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.poireport.service.ReportFileService;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepEdit001Service;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.WebOfficeObject;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import com.joyintech.tams.utils.poi.PoiUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepView001Contoller
 * @since 1.0
 */

@Controller
@PageId("RepView001")
@RequestMapping("/disclosure/information/reveal/detailshow/RepView001")
public class RepView001Contoller extends BaseController {
	
	@Autowired
	private ReportInfoMapper reportInfoMapper;
	
    @Autowired
    private PoiReportService poiReportService;
    
    @Autowired
    ReportDealService reportDealService;
    
    @Autowired
	@Qualifier("masterDataSource") DataSource master;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;

    @Autowired
    private ReportFileService reportFileService;

    @Autowired
    private RepEdit001Service repEdit001Service;

//    private String editFilename = "";
//    private String saveFilename = "";
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(RepView001Contoller.class);
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    @Override
    public String init(Model model) {
    	Map<String,Object> params = RequestUtil.getPageParam();
    	//repDetailsShow001Service.collateParams(params);

    	String templateName = params.get("repName")==null?"":params.get("repName").toString();
    	Date reportDate = (Date)params.get("reportDate"); 
    	if ( reportDate ==null )
    		reportDate = params.get("enddate")==null?commonPlatformDateService.getSysDate():DateUtil.parse(params.get("enddate").toString());
    	String portnum ="";
    	if ( params.get("pprodid")!=null )
    		 portnum = params.get("prodid")==null?params.get("pprodid").toString():params.get("prodid").toString();
    	String repId = params.get("repid")==null?"":params.get("repid").toString();
    	String webRoot = params.get("webRoot")==null?"":params.get("webRoot").toString();
    	
    	
        model.addAttribute("endDate", reportDate);
        model.addAttribute("repName", templateName); 
        model.addAttribute("portnum", portnum);
        model.addAttribute("reportId", repId);
        model.addAttribute("webRoot", webRoot);

//        String editFilename = reportFileService.getTempOSSReportFileName(repId, reportDate, params, ReportFileService.FILE_TYPE_DOCX) ;
//        model.addAttribute("fileName", editFilename);
//		logger.debug("==================报表下载编辑文件："+editFilename);
//    	//saveFilename = editFilename;
//        model.addAttribute("shiroCookie", SpringContextUtil.getBean(ShiroProperties.class).getSessionId());
        return super.init(model);
    }

    @RequestMapping(value="/viewReport", method = RequestMethod.POST)
    public void viewReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
        ByteArrayInputStream bis = null;
        String imagePath = "";
    	try {
        	Map<String,Object> params = RequestUtil.getPageParam();
        	//String reportFile = params.get("reportFile").toString();
        	String reportFile = "E:/poi/aa.docx";
	        byte[] bytes = poiReportService.getFileByteArray(reportFile);

	        if(bytes != null) {
                bis = new ByteArrayInputStream(bytes);
                //String rptType = params.get("uri").toString();
                String rptType = "JDBG_ZSX";
                if (rptType.equals(DisReportType.JDBG_ZSX) || rptType.equals(DisReportType.JDBG_FZSX)
        				|| rptType.equals(DisReportType.BNDBG_ZSX) || rptType.equals(DisReportType.BNDBG_FZSX)
        				|| rptType.equals(DisReportType.NDBG_ZSX) || rptType.equals(DisReportType.NDBG_FZSX) 
        				) { // 定期公告-季度/半年度/年度报告指数型和非指数型
                	PoiUtils.docxToHtml(bis,response.getOutputStream(),imagePath,"报告期（");
                } else {
                	PoiUtils.docxToHtml(bis,response.getOutputStream(),imagePath);
                }
            }
        }catch (Exception e) {
        	logger.error("预览文件错误",e);
        } finally {
        	bis.close();
        }
    }
    
 
}
