package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.controller;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocate001Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange001Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service.PtlSubFundsAllocate001Service;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service.PtlSubFundsMange001Service;

/**
 * 子组合资金管理
 */
@Controller
@PageId("PtlSubFundsMange001")
@RequestMapping("/invest/portfolio/ptlsubfundsmange/PtlSubFundsMange001")
public class PtlSubFundsMange001Controller extends BaseController {


	@Autowired
	private  PtlSubFundsMange001Service  ptlSubFundsMange001Service;
	
	@Autowired
	private PtlSubFundsAllocate001Service ptlSubFundsAllocate001Service;
	/**
     * 重写init方法，加载页面
     * {@inheritDoc}
     */
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
	   
        //分配状态
        this.setSelectOptionToModel(model, DictCode.分配状态);
		return super.init(model);
    }
	
	/**
	 * 子组合间资金调配-组合代码联想框
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getPortfolioId")
	public Page<SelectOptionDto> getPortfolioId() throws Exception {
		return ptlSubFundsAllocate001Service.getPortfolioId(RequestUtil.getPageParam());
	}

	/**
	 * 子组合间资金调配-子组合代码联想框
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getSubPortfolioId")
	public Page<SelectOptionDto> getSubPortfolioId() throws Exception {
		return ptlSubFundsAllocate001Service.getSubPortfolioId(RequestUtil.getPageParam());
	}

	/**
	 * 子组合间资金调配-分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findFundsAllocatePage")
	public Page<PtlSubFundsAllocate001Dto> findFundsAllocatePage() throws Exception {
		return ptlSubFundsAllocate001Service.findFundsAllocatePage(RequestUtil.getPageParam());
	}
	
	/**
	 * 子组合间资金调配-导出
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("exportPtlSubFundsAllocate")
	public ResponseEntity<byte[]> exportPtlSubFundsAllocate() throws Exception {
		String datas = this.getParam("datas");
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<PtlSubFundsAllocate001Dto> listdto;
		if (datas == null) {
			listdto = ptlSubFundsAllocate001Service.selectFundsAllocateList(paraMap);
		} else {
			listdto = JsonUtils.json2List(datas, PtlSubFundsAllocate001Dto.class);
		}
		File outFile = ptlSubFundsAllocate001Service
				.makeFile("/com/joyintech/tams/invest/portfolio/ptlsubfundsmange/conf/export_PtlSubFundsAllocate.xml", listdto);
		return ExportUtil.export("子组合资金调配" + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

  
    
    /**
     * 产品名称联想下拉框
     * 
     * @return 产品名称列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdNameInfo")
    public List<SelectOptionDto> queryProdNameInfo() {
        return ptlSubFundsMange001Service.queryProdNameInfo(RequestUtil.getPageParam());
  
    }
    
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo() {
        return ptlSubFundsMange001Service.queryProdIdInfo(RequestUtil.getPageParam());
  
    }
    
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findIncomeStatisticsPage")
    @ResponseBody
    public List<PtlSubFundsMange001Dto> findIncomeStatisticsPage()
        throws Exception {
        return ptlSubFundsMange001Service.findPage(RequestUtil.getPageParam());
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
    	Map<String, Object> paraMap = RequestUtil.getPageParam();
		String datas = this.getParam("datas");		
		List<PtlSubFundsMange001Dto> listdto = null;
				if (datas == null) {
			listdto = ptlSubFundsMange001Service.findPageList(paraMap);
			
		} else {
			listdto = JsonUtils.json2List(datas, PtlSubFundsMange001Dto.class);
		}
				File outFile = ptlSubFundsMange001Service.makeFile(
						"/com/joyintech/tams/invest/portfolio/ptlsubfundsmange/conf/export_conf.xml",
						listdto);
				return ExportUtil.export("产品交易资金分配" + SystemGlobalConstant.OPEN_PARENTHESIS
						+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
						+ FileNameUtil.getFileExtName(outFile), outFile, true);
    }
}
