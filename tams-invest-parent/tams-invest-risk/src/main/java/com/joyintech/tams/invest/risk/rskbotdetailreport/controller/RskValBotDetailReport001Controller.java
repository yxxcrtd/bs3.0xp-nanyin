package com.joyintech.tams.invest.risk.rskbotdetailreport.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.rskbotdetailreport.service.RskValBotDetailReport001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 底层穿透明细表
 * @author lingxq
 * @see RskValBotDetailReport001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValBotDetailReport001")
@RequestMapping("/invest/risk/rskbotdetailreport/RskValBotDetailReport001")
public class RskValBotDetailReport001Controller extends BaseController{
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	  /**
     * service
     */
    @Autowired
    private RskValBotDetailReport001Service rskValBotDetailReport001Service;
    /**
  	 * {@inheritDoc}
  	 */
  	@Override
  	public String init(Model model) {
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
		return rskValBotDetailReport001Service.export(this.getPageId(), RequestUtil.getPageParam(), "valDate");
	}
}
