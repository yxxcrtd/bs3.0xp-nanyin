package com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.controller;

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
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubMService;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService001;
/**
 * 
 * <p>人行报送信息登记-产品起始募集信息Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("PbcPrdInitRaiseSub")
@RequestMapping("/pbc/prdinitraisesub/PbcPrdInitRaiseSub")
public class PbcPrdInitRaiseSubController extends BaseController {
	/**
	 * 产品资金募集信息service
	 */
	@Autowired
	private PbcPrdInitRaiseSubService001 pbcPrdRaiseSubService001;

	/**
	 * 微服务service
	 */
	@Autowired
	private PbcPrdInitRaiseSubMService bcPrdInitRaiseSubMService;
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
    	super.setSelectOptionToModel(model,	DictCode.报送数据状态,DictCode.报送状态,DictCode.人行报送反馈状态,DictCode.人行产品类型,DictCode.人行产品运行方式);
        return super.init(model);
    }
    
    /**
     * 产品资金募集信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<PbcInitProdRaisingSub> searchPage(){
    	Map<String,Object> searchParam = RequestUtil.getPageParam();
    	
    	Page<PbcInitProdRaisingSub> page = bcPrdInitRaiseSubMService.searchPage(searchParam);;
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
	    bcPrdInitRaiseSubMService.updateSumStaus(map);
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
		String result = bcPrdInitRaiseSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	
    

}
