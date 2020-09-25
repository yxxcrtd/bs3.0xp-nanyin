package com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.controller;

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
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMain001Dto;
import com.joyintech.tams.invest.product.nolimit.nonstProUplimitMain.service.NonstProUplimitMain001Service;

/**
 * @program: NonstProUplimitMain001Controller
 * @description: 非标比例上限维护
 * @author: cxq
 * @create: 2019-12-18
 **/
@Controller
@PageId("NonstProUplimitMain001")
@RequestMapping("/invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain001")
public class NonstProUplimitMain001Controller extends BaseController {
	@Autowired
	NonstProUplimitMain001Service nonstProUplimitMain001Service;
	//初始化页面
	@Override
	public String init(Model model) {
		return super.init(model);
	}

	/**
	 * 产品系列下拉框查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	@RequestMapping("selectPrdFamily")
	@ResponseBody
	public Page<SelectOptionDto> selectPrdFamily() throws Exception {
		return nonstProUplimitMain001Service.selectPrdFamily(RequestUtil.getPageParam());

	}
	/**
	 * 	画面查询方法
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectForPage")
	@ResponseBody
	public Page<NonstProUplimitMain001Dto> selectForPage() throws Exception{
		return nonstProUplimitMain001Service.selectForPage(RequestUtil.getPageParam());
		
	}
	 /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return nonstProUplimitMain001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

}
