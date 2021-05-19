package com.joyintech.tams.svreport.pbc.classic.prdassetdebt.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.util.JsonUtils;
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
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubMService;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService001;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.service.PbcMonthReport001Service;

/**
 * 
 * <p>
 * 人行报送信息登记-资产负债信息Controller
 * </p>
 * 
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("PbcPrdAssetDebtSub")
@RequestMapping("/pbc/prdassetdebt/PbcPrdAssetDebtSub")
public class PbcPrdAssetDebtSub001Controller extends BaseController {
	/**
	 * 资产负债信息service
	 */
	@Autowired
	private PbcPrdAssetDebtSubService001 pbcPrdAssetDebtSubService001;
	  /**
     * service
     */
    @Autowired
    private PbcMonthReport001Service pbcMonthReport001Service;
    
    /**
     * 微服务service
     */
    @Autowired
    private PbcPrdAssetDebtSubMService pbcPrdAssetDebtSubMService;

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
		super.setSelectOptionToModel(model, DictCode.报送数据状态, DictCode.报送状态, DictCode.人行报送反馈状态, DictCode.人行产品类型,
				DictCode.人行产品运作模式);
		return super.init(model);
	}

	/**
	 * 资产负债信息查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<PbcMonthReport> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();

		Page<PbcMonthReport> page = pbcPrdAssetDebtSubMService.searchPage(searchParam);
		;
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
		return pbcPrdAssetDebtSubService001.export(this.getPageId(), RequestUtil.getPageParam());
	}

	/**
	 * 手工处理
	 * 
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String,Object> map) {
	    pbcPrdAssetDebtSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理
	 * 
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = pbcPrdAssetDebtSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 查询列表
	 *
	 * @return DtPage
	 */
	@ResponseBody
	@RequestMapping(value = "preView")
	public String preView(HttpServletRequest request, HttpServletResponse response) {
		String htmlExcel = pbcMonthReport001Service.priview(this.getPageId(), RequestUtil.getPageParam());
		if ("fail".equals(htmlExcel)) {
			return "fail";
		}
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
			getLogger().error(e.getMessage(), e);
			return "fail";
		}
		return "success";
	}
	/**
	 * 
	* 数据检查.
	* @author yuluan
	* @time 2019年9月4日 下午4:23:31
	* @return
	 */
	@ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return pbcPrdAssetDebtSubMService.reportCheck(RequestUtil.getPageParam());
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
		return pbcPrdAssetDebtSubService001.download(this.getPageId(), RequestUtil.getPageParam(),datas);
	}
	/**
	 * 查询列表
	 *
	 * @return DtPage
	 */
	@ResponseBody
	@RequestMapping(value = "searchNoDxAndXt")
	public List<PbcMonthReport> searchNoDxAndXt(@RequestBody Map<String, Object> params)throws IOException  {
		List<PbcMonthReport> list= pbcPrdAssetDebtSubService001.searchNoDxAndXt(params);
		return list;
	}

}
