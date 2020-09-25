package com.joyintech.service.disclosure.information.manage.estimatereveal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;
import com.joyintech.service.disclosure.information.manage.estimatereveal.service.EstimateReveal001Service;
import com.joyintech.service.disclosure.information.manage.workmonitor.service.ReportWorkMonitor001Service;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftppath.service.FtpPathService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;

@RestController(value="serviceEstimateReveal001Controller")
@RequestMapping("/EstimateReveal001")
public class EstimateReveal001Controller extends BaseController {
	
	@Autowired
    private ReportWorkMonitor001Service reportWorkMonitor001Service;
	
	@Autowired
    private EstimateReveal001Service estimateReveal001Service;
	
	@Autowired
    private FtpPathService ftpPathService;
	
	@Autowired
	private GetPrdInfoSelectService getPrdInfoSelectService;
	
	
	/**
	 * 分页查询产品下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPrdInfoSelect")
	@ResponseBody
    public Page<SelectOptionDto> getPrdInfoSelect()
        throws Exception {
		Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPrdInfoSelect(RequestUtil.getPageParam());
        return prdInfoSelect;
    }
	
	/**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
	@PostMapping("findPage/{page}/{length}")
    public Page<EstimateReveal001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
    	Page<EstimateReveal001Dto> pageList = estimateReveal001Service.findPage(page, length, param);
        return pageList;
    }
    @PostMapping("getJobidList")
    public List<SelectOptionDto> getJobidList(@RequestBody Map<String,Object> map) {
//         Map<String,Object> map=new HashMap<String,Object>();
         return reportWorkMonitor001Service.getJobidList(map);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export() throws Exception {
//		String datas = this.getParam("datas");
//		List<EstimateReveal001Dto> dtoList;
//		if (datas == null) {
//			dtoList = this.findPage().getRoot();
//		}
//		else {
//			dtoList = JsonUtils.json2List(datas, EstimateReveal001Dto.class);
//		}
//        File outFile = estimateReveal001Service.makeFile("/com/joyintech/tams/disclosure/information/manage/estimatereveal/conf/export_estimateReveal001.xml",dtoList);
//        return ExportUtil.export(
//                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
//    }
}
