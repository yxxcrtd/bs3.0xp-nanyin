package com.joyintech.tams.invest.product.nolimit.maxissuablesize.controller;

import java.util.List;

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
import com.joyintech.tams.invest.product.nolimit.maxissuablesize.dto.MaxIssuableSize001Dto;
import com.joyintech.tams.invest.product.nolimit.maxissuablesize.service.MaxIssuableSize001Service;
/**
 * 产品端最大可发行规模查询controller
 * 〈功能详细描述〉
 * @author qufangxu
 * @version 1.0 2020年1月1日
 * @see MaxIssuableSize001Controller
 * @since 1.0
 */
@Controller
@PageId("MaxIssuableSize001")
@RequestMapping("/invest/product/nolimit/maxissuablesize/MaxIssuableSize001")
public class MaxIssuableSize001Controller extends BaseController{
	/**
	 * 产品端最大可发行规模查询service
	 */
	@Autowired
    private MaxIssuableSize001Service maxIssuableSize001Service ;
	
	/**
	 * 页面初始化
	 */
	@Override
	public String init(Model model){
		return super.init(model);
	}
	
	/**
	 * 最大可发行规模查询 分页查询
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
    @ResponseBody
    @RequestMapping("selectForPage")
    public Page<MaxIssuableSize001Dto> selectAll() throws Exception {
        Page<MaxIssuableSize001Dto> page = maxIssuableSize001Service.selectForPage(RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 产品规划系列联想框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
	@ResponseBody
	@RequestMapping("queryPrdPlanSerisIdInfo")
	public List<SelectOptionDto> queryPrdPlanSerisIdInfo() {
		return maxIssuableSize001Service.queryPrdPlanSerisIdInfo(RequestUtil.getPageParam());
	}
	
	/**
	 * 最大可发行规模查询 导出
	 * <功能详细描述>
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return maxIssuableSize001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
    }
    
}
