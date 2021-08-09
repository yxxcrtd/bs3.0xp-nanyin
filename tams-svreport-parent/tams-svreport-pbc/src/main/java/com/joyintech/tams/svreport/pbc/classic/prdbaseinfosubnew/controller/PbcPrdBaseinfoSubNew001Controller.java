package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.service.PbcPrdBaseinfoSubNewMService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.service.PbcPrdBaseinfoSubNew001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;

/**
 * 
 * <p>人行报送信息登记-产品基本信息信息Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("PbcPrdBaseinfoSubNew001")
@RequestMapping("/pbc/prdbaseinfosubnew/PbcPrdBaseinfoSubNew001")
public class PbcPrdBaseinfoSubNew001Controller extends BaseController {

	/**
	 * 产品基本信息信息service
	 */
	@Autowired
	private PbcPrdBaseinfoSubNew001Service pbcPrdRaiseSub001Service;
	
	/**
	 * 微服务service
	 */
	@Autowired
	private PbcPrdBaseinfoSubNewMService pbcPrdBaseinfoSubMService;

	/**
	 * 页面初始化
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
    	super.setSelectOptionToModel(model,	DictCode.报送数据状态,DictCode.报送状态,DictCode.人行报送反馈状态,DictCode.人行产品类型,DictCode.人行产品运作模式);
        return super.init(model);
    }


	/**
	 * 生成产品代码列表
	 * @return 产品代码下拉框初始值
	 */
	@ResponseBody
	@RequestMapping("/getPbcProdId")
	public List<SelectOptionDto> getPbcProdId() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return pbcPrdBaseinfoSubMService.getPbcProdId(param);
	}
    
    /**
     * 产品基本信息信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<PbcPrdBaseinfoSubNew> searchPage(){
    	Map<String,Object> searchParam = RequestUtil.getPageParam();
    	
    	Page<PbcPrdBaseinfoSubNew> page = pbcPrdBaseinfoSubMService.searchPage(searchParam);;
		return page;
    	
    }
    /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return pbcPrdBaseinfoSubMService.reportCheck(RequestUtil.getPageParam());
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
		return pbcPrdRaiseSub001Service.export(this.getPageId(), RequestUtil.getPageParam());
	}
	
	/**
	 * 手工处理
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String,Object> map) {
	    pbcPrdBaseinfoSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	/**
	 * 取消手工处理
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = pbcPrdBaseinfoSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
	
	/**
	 * 	导出人行Excel
	 * 
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("download")
	public ResponseEntity<byte[]> download() throws IOException {

		String datas = this.getParam("datas");
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<PbcPrdBaseinfoSubNew> dtoList;

		if(null==datas){
			dtoList = pbcPrdRaiseSub001Service.searchByCondition(paraMap);
		}else{
			dtoList = JsonUtils.json2List(datas, PbcPrdBaseinfoSubNew.class);
		}

		File outFile = pbcPrdRaiseSub001Service.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/prdbaseinfosubnew/conf/export_confNew.xml",
				dtoList);
		return ExportUtil.export(
				"产品终止信息报送内容_"
						+ DateUtil.format(new Date())
						+ SystemGlobalConstant.POINT
						+ ".xls",
				outFile, true);
	}

}
