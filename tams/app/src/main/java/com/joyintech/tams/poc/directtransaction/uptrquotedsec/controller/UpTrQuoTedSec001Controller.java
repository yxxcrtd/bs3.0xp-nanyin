package com.joyintech.tams.poc.directtransaction.uptrquotedsec.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.poc.directtransaction.uptrquotedsec.service.UpTrQuoTedSec001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 请求报价查询
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see
 * @since 1.0
 */
@PageId("UpTrQuoTedSec001")
@RequestMapping("/invest/trade/uptrquotedsec/UpTrQuoTedSec001")
@Controller
public class UpTrQuoTedSec001Controller extends BaseController{
	@Autowired
	private UpTrQuoTedSec001Service upTrQuoTedSec001Service;
	
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, BussinessDictCode.交易方向);
        return super.init(model);
    }
	
	/**
     * 组合名称的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
	  @ResponseBody
	  @RequestMapping("/getPortfolioId")
	  public List<SelectOptionDto> getPortfolioId(){
	      Map<String,Object> map = RequestUtil.getPageParam();
	//      map.put("userId",SecurityUtil.getLoginUser().getUserName());
	      return upTrQuoTedSec001Service.getPortfolioId(map);
	  }
	
	  
	  /**
	     * 分页查询 资产代码
	     *
	     * @return
	     */
	    @ResponseBody
	    @RequestMapping("/getFinprodMarketId")
	    public List<SelectOptionDto> getFinprodMarketId() {
	    	Map<String,Object> map = RequestUtil.getPageParam();
	        return upTrQuoTedSec001Service.getFinprodMarketId(map);
	    }
	  /**
	     * 
	     * 交易对手
	     * <功能详细描述>
	     * @return
	     * @see
	     * @since 1.0
	     */
	    @ResponseBody
	    @RequestMapping("getCounterId")
	    public List<SelectOptionDto> getCounterId(){
	        return upTrQuoTedSec001Service.getCounterId(RequestUtil.getPageParam());
	    }
}
