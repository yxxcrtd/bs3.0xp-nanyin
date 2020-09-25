package com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.service.PtlSingleIssuerPositionAnalysis001Service;

/**
 * 单一发行人持仓分析
 */
@Controller
@PageId("PtlSingleIssuerPositionAnalysis001")
@RequestMapping("/invest/portfolio/ptlsingleissuerpositionanalysis/PtlSingleIssuerPositionAnalysis001")
public class PtlSingleIssuerPositionAnalysis001Controller extends BaseController {

	@Autowired
	private PtlSingleIssuerPositionAnalysis001Service ptlSingleIssuerPositionAnalysis001Service;
	/**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
	    Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate); 
        model.addAttribute("sysDate", morenDate);
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
		return super.init(model);
    }

    /**
	 * 分页查询发行人下拉框
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPublisher")
	@ResponseBody
	public Page<SelectOptionDto> getPublisher()
        throws Exception {
		Page<SelectOptionDto> publisherSelect = ptlSingleIssuerPositionAnalysis001Service.getPublisher(RequestUtil.getPageParam());
        return publisherSelect;
    }


	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlSingleIssuerPositionAnalysis001Dto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<PtlSingleIssuerPositionAnalysis001Dto> page = ptlSingleIssuerPositionAnalysis001Service.selectForPage(map);
        return page;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlSingleIssuerPositionAnalysis001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
