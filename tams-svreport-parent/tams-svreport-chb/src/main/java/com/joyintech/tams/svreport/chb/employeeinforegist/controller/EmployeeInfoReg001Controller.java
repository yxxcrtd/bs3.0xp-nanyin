package com.joyintech.tams.svreport.chb.employeeinforegist.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.component.svrreport.chb.dto.ChbEmployeeInfoDto;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chb.employeeinforegist.dto.EmployeeInfoReg001Dto;
import com.joyintech.tams.svreport.chb.employeeinforegist.service.EmployeeInfoRegMService;
import com.joyintech.tams.svreport.chbsd.employeeinfo.service.SdEmployInfoBatchService;
import com.joyintech.tools.collectors.JoyinCollectors;


/**
 * 〈功能详细描述〉
 * @author lingxq
 * @version 1.0 2020年11月3日
 * @see EmployeeInfoReg001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrEmployeeInfoReg001")
@RequestMapping("/chb/employeeinforegist/EmployeeInfoReg001")
public class EmployeeInfoReg001Controller extends BaseController {
	@Autowired
	private EmployeeInfoRegMService employeeInfoRegMService;
	@Autowired
	private SdEmployInfoBatchService sdEmployInfoBatchService;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
     * 页面初始化
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
        super.setSelectOptionToModel(model, 
        		DictCode.报送状态,
        		DictCode.报送反馈状态,
        		DictCode.报送数据状态,
        		DictCode.从业人员类型);
        return super.init(model);
    }
    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("findPage")
   public Page<EmployeeInfoReg001Dto> selectAllData() {
       Page<EmployeeInfoReg001Dto> page = employeeInfoRegMService.selectForPage(RequestUtil.getPageParam());
       return page;
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
	public AjaxResult doManual(@RequestBody Map<String, Object> map) {
		employeeInfoRegMService.updateSumStaus(map);
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
		String result = employeeInfoRegMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
	/**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("export")
   public ResponseEntity<byte[]> export() {
	   	Map<String, Object> pageParam = RequestUtil.getPageParam();
	   	String zipFileName = "从业人员登记_"+ DateUtil.getSysDate() +".zip";
	    String exportFilePath = SvrDataUtil.getSysParam( GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
	    // 如果生成excel文件个数大于1，生成压缩文件
	    File outFile =  new File(exportFilePath + File.separator + zipFileName);
	    //需要导出的excel文件集合
	    List<File> sourceFiles = new ArrayList<File>();
	    //导出的数据
	    List<EmployeeInfoReg001Dto> dtoList = null;
	   	if(pageParam.containsKey("keyList")){
	   		dtoList = employeeInfoRegMService.selectForExport(RequestUtil.getParameters(getRequest()));
	   	}
	   	else{
	   		Page<EmployeeInfoReg001Dto> page = employeeInfoRegMService.selectForPage(RequestUtil.getParameters(getRequest()));
	   		if(null != page){
	   			dtoList = page.getRoot();
	   		}
	   	}
	   	//根据从业人员类型导出不同模板
	   	createFile(sourceFiles,dtoList);
	    // 导出路径不存在则重新生成
	    if (!outFile.getParentFile().exists()) {
	        outFile.getParentFile().mkdirs();
	    }
	    try {
	        ZipFileUtil.compressZip(outFile, true, sourceFiles.toArray(new File[] {}));
	    }
	    catch (Exception e) {
	        // 压缩文件失败后，删除所有临时文件
	        for (File file : sourceFiles) {
	            if (file.exists()) {
	                file.delete();
	            }
	        }
	        logger.error(e.getMessage(),e);
	    }
	    //删掉xls文件
	    for(File single : sourceFiles){
	    	single.delete();
	    }
	    return ExportUtil.export(zipFileName, outFile, true);
   }

   /**
    * 根据数据生成excel文件
    * @param sourceFiles
    * @param dtoList
    */
	private void createFile(List<File> sourceFiles, List<EmployeeInfoReg001Dto> dtoList) {
		//根据从业人员类型分组
		Map<String, List<EmployeeInfoReg001Dto>> infoMap = dtoList.stream().collect(JoyinCollectors.groupingBy(
				x -> x.getEmployeeType()));
		//发行机构名称
		String bankNm = CacheUtil.getSysParam("BANK_NAME");
		
		for(Entry<String, List<EmployeeInfoReg001Dto>> map : infoMap.entrySet()){
			
			Map<String, Object> dataParam = new HashMap<String, Object>();
			// 发行机构
            dataParam.put("issuePartyName", bankNm);
			// 发行机构
            dataParam.put("issuePartyId", map.getValue().get(0).getIssuePartyId());
            // 删除类型
            dataParam.put("employeeType", map.getValue().get(0).getEmployeeType());
            // 明细数据
            dataParam.put("detail", map.getValue());
			File file = TisUtil.createFile(dataParam, "EmployeeInfoReg001", "从业人员信息登记_(" + map.getValue().get(0).getEmployeeType() + ").xls");
			sourceFiles.add(file);
		}
	}
	
	/**
    * 报送检查
    * @param map 查询条件
    * @return 检查结果
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("reportCheck")
   public Map<String,Object> reportCheck() {
       return employeeInfoRegMService.checkData(RequestUtil.getPageParam());
   }
   
	/**
	 * 中债直连报送
	 *
	 * @param map
	 *            传入需要报送的主键
	 * @return 反馈信息
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/submitChb", method = RequestMethod.POST)
	public AjaxResult doSubmit(@RequestBody Map<String, Object> param) throws Exception {

		List<EmployeeInfoReg001Dto> list = new ArrayList<EmployeeInfoReg001Dto>();
		// 选择行数据时
		if (param.containsKey("keyList")) {
			String keyList = param.get("keyList").toString();
			try {
				list = JsonUtils.json2List(keyList,
						EmployeeInfoReg001Dto.class);
			}
			catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}else{
			Page<EmployeeInfoReg001Dto> page = employeeInfoRegMService.selectForPage(RequestUtil.getPageParam());
			if(page!=null&&page.getRoot()!=null&&page.getRoot().size()>0){
				list = page.getRoot();
			}
			//查询数据
		}
		
		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbEmployeeInfoDto> dataList = new ArrayList<ChbEmployeeInfoDto>();
		for(EmployeeInfoReg001Dto sdDto:list){
			ChbEmployeeInfoDto target = new ChbEmployeeInfoDto();
			BeanUtil.copyPropertiesIgnoreNull(target, sdDto);
			dataList.add(target);
		}

		sdEmployInfoBatchService.sendBatchCommon(dataList);

		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0018"));
	}
}
