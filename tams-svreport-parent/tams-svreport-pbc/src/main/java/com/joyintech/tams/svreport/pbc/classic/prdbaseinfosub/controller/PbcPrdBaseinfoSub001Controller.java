package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubMService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService001;

/**
 * 
 * <p>人行报送信息登记-产品基本信息信息Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("PbcPrdBaseinfoSub")
@RequestMapping("/pbc/prdbaseinfosub/PbcPrdBaseinfoSub")
public class PbcPrdBaseinfoSub001Controller extends BaseController {
	/**
	 * 产品基本信息信息service
	 */
	@Autowired
	private PbcPrdBaseinfoSubService001 pbcPrdRaiseSubService001;
	
	/**
	 * 微服务service
	 */
	@Autowired
	private PbcPrdBaseinfoSubMService pbcPrdBaseinfoSubMService;

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
     * 产品基本信息信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<PbcPrdBaseinfoSub> searchPage(){
    	Map<String,Object> searchParam = RequestUtil.getPageParam();
    	
    	Page<PbcPrdBaseinfoSub> page = pbcPrdBaseinfoSubMService.searchPage(searchParam);;
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
		return pbcPrdRaiseSubService001.export(this.getPageId(), RequestUtil.getPageParam());
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
		return pbcPrdRaiseSubService001.download(this.getPageId(), RequestUtil.getPageParam(),datas);
	}

}
