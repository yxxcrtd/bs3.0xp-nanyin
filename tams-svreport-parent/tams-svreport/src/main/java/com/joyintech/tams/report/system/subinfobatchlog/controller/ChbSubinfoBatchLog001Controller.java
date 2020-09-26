package com.joyintech.tams.report.system.subinfobatchlog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.report.system.subinfobatchlog.servcie.ChbSubinfoBatchLog001Servcie;
import com.joyintech.tams.report.system.subinfobatchlog.service.ChbSubinfoBatchLogService;

/**
 * 中债报送信息登记生成日志Controller
 * 
 * @author zhangyf
 */
@Controller
@PageId("ChbSubinfoBatchLog001")
@RequestMapping("/svreport/system/subinfobatchlog/ChbSubinfoBatchLog001")
public class ChbSubinfoBatchLog001Controller extends BaseController {

	@Autowired
	private ChbSubinfoBatchLog001Servcie chbSubinfoBatchLog001Servcie;
	@Autowired
	private ChbSubinfoBatchLogService chbSubinfoBatchLogService;
	@Autowired
    private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;
	@Override
	public String init(Model model) {
//		List<SelectOptionDto> userList = chbSubinfoBatchLog001Servcie.getUserList();
		List<SelectOptionDto> userList = chbSubinfoBatchLogService.selectUserList();
		model.addAttribute("userList", JsonUtils.object2Json(userList.toArray()));
		super.setSelectOptionToModel(model, DictCode.报送文件类别, DictCode.报送处理状态);
		//业务确认日-市场节假日表中有值则取前一个工作日，无则取前一天
        String dateStr = null;
        Date ywDate = equityBusiInfoRegist001Service.getLastWeekDay(
        		CacheUtil.getSysParam(SVReportGlobalConstant.SVR_CALENDAR_ID),DateUtil.getCurrentDate());
        String operateDate = "";
        if(StringUtil.isNotEmpty(ywDate)){
        	dateStr = DateUtil.format(ywDate);
        	operateDate = DateUtil.format(DateUtil.addDay(ywDate, 1));
        }else{
        	dateStr = DateUtil.plusDays(DateUtil.getCurrentDate(), -1);
        	operateDate = DateUtil.getCurrentDate();
        }
		model.addAttribute("DATE_START",dateStr);
        model.addAttribute("DATE_END",dateStr);
        model.addAttribute("OPERATE_DATE_START",operateDate);
        model.addAttribute("OPERATE_DATE_END",operateDate);
		return super.init(model);
	}
	
	/**
    *
    * 查询自动批处理日志结果列表
    * @return 结果列表，分页显示
    */
   @ResponseBody
   @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
   public Page<ChbSubinfoBatchLog> queryBatchProResult() {
       Map<String,Object> map = RequestUtil.getPageParam();
//       return chbSubinfoBatchLog001Servcie.queryBatchResult(map);
       return chbSubinfoBatchLogService.queryBatchResult(map);
   }
   
   /**
    * 检查选择的压缩文件是否存在
    * @return 不存在的文件清单
    */
   @ResponseBody
   @RequestMapping("/checkFileExists")
   public AjaxResult checkFileExists() {
   	Map<String, Object> param = RequestUtil.getPageParam();
       return chbSubinfoBatchLog001Servcie.checkFileExists(param);
   }
   
   /**
    * 下载压缩文件
    * @return 压缩文件
    * @throws IOException 
    */
   @ResponseBody
   @RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {
		Map<String, Object> param = RequestUtil.getPageParam();
		ResponseEntity<byte[]> responseEntity = chbSubinfoBatchLog001Servcie.download(param);
		return responseEntity;
	}
}