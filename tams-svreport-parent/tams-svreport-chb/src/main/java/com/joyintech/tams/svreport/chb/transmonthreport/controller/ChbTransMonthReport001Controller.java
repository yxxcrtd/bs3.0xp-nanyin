package com.joyintech.tams.svreport.chb.transmonthreport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.transmonthreport.service.ChbTransMonthReport001Service;
import com.joyintech.tams.svreport.chb.transmonthreport.service.ChbTransMonthReportMService;

/**
 * 理财产品转型情况月度统计表
 * @author Administrator
 * @see ChbTransMonthReport001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrTransMonthReport001")
@RequestMapping("/chb/transmonthreport/ChbTransMonthReport001")
public class ChbTransMonthReport001Controller extends BaseController{
	  /**
     * service
     */
    @Autowired
    private ChbTransMonthReport001Service chbTransMonthReport001Service;
    
    @Autowired
    private ChbTransMonthReportMService chbTransMonthReportMService;
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
        // 是否显示查看数据字典明细按钮，多级法人改造 add by hxy 20200623
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
      
        return super.init(model);
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
		return chbTransMonthReport001Service.export(this.getPageId(), RequestUtil.getPageParam());
	}
    /**
    /**
    * 检索登录人下面所属机构
    *
    * @return 账套代码联想框内容
    * @throws Exception
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("/getChbTransOrg")
   public Page<SelectOptionDto> getChbTransOrg() throws Exception {
	HashMap<String, Object> pageParam = (HashMap<String, Object>) RequestUtil.getPageParam();
 	String search = null;
   	if (pageParam.containsKey("search")) {
   		search = String.valueOf(pageParam.get("search"));
   	} else {
   		pageParam.put("search", search);
   	}
	List<String> institutionlist=LoginUserUtil.getTreeInstCode();
	pageParam.put("institutionlist", institutionlist);
    return chbTransMonthReport001Service.getChbTransOrg(pageParam);
   }
}
