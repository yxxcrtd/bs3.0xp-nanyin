package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.EtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.poc.directtransaction.uptrinqbuyback.dto.UpTrInqBuyBack001Dto;
import com.joyintech.tams.poc.directtransaction.uptrinqbuyback.service.UpTrInqBuyBack002Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 回购交易新增报价
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see UpTrInqBuyBack002Controller
 * @since 1.0
 */
@PageId("UpTrInqBuyBack002")
@RequestMapping("/invest/trade/uptrinqbuyback/UpTrInqBuyBack002")
@Controller
public class UpTrInqBuyBack002Controller extends BaseController{
	
    @Autowired
    private UpTrInqBuyBack002Service upTrInqBuyBack002Service;
    
    @Autowired
	private EtrEntrustMapper etrEntrustMapper;
	
	@Autowired
	private EtrEntrustAddMapper etrEntrustAddMapper;
	
	@Autowired
	private PtlPortfolioMapper ptlPortfolioMapper;
	
	@Autowired
	private FinProductMapper finProductMapper;
	
	@Override
    public String init(Model model) {

		this.setSelectOptionToModel(model, BussinessDictCode.清算速度);
		this.setSelectOptionToModel(model, BussinessDictCode.币种);
		  //新增界面，带出数据
		  UpTrInqBuyBack001Dto upTrInqBuyBack001Dto = new UpTrInqBuyBack001Dto();
		  if(StringUtil.isNotEmpty(this.getParam("entrustId"))){
			 //获取委托表中数据
			  EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(this.getParam("entrustId"));
			  if(etrEntrust != null){
				  BeanUtil.copyPropertiesIgnoreNull(upTrInqBuyBack001Dto, etrEntrust);
				  if(upTrInqBuyBack001Dto.getEntrustNum() != null){
					  upTrInqBuyBack001Dto.setEntrustNum(ArithmeticUtil.multiply(upTrInqBuyBack001Dto.getEntrustNum(), new BigDecimal(100)));
				  }
				  if(upTrInqBuyBack001Dto.getPrice() != null){
					  upTrInqBuyBack001Dto.setPrice(ArithmeticUtil.multiply(upTrInqBuyBack001Dto.getPrice(), new BigDecimal(100)));
				  }
			  }
			  //获取委托附属表数据
			  EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(this.getParam("entrustId"));
			  if(etrEntrustAdd != null){
				  BeanUtil.copyPropertiesIgnoreNull(upTrInqBuyBack001Dto, etrEntrustAdd);
			  }
			  //获取组合信息
			  PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(upTrInqBuyBack001Dto.getPortfolioId());
			  if(ptlPortfolio != null){
				  upTrInqBuyBack001Dto.setPortfolioName(ptlPortfolio.getPortfolioName());
			  }
			  
			//获取交易方向
				String ps = "";
				if (StringUtil.equals(upTrInqBuyBack001Dto.getPs(), BussinessDictCodeList.回购拆借方向_正回购.getValue())){
					ps = "psBuy";
				}
				if (StringUtil.equals(upTrInqBuyBack001Dto.getPs(), BussinessDictCodeList.回购拆借方向_逆回购.getValue())){
					ps = "psSell";
				}
				upTrInqBuyBack001Dto.setPs(ps);    //交易方向
		  }
		  
		  model.addAttribute(GlobalConstant.ENTITY, upTrInqBuyBack001Dto);
          return super.init(model);
    }

	

	/**
     * 
     * 查询对手方交易员
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectCounterId")
    @ResponseBody
    public List<SelectOptionDto> selectCounterId(){
        return upTrInqBuyBack002Service.selectCounterId(RequestUtil.getPageParam());
    }
    /**
     * 
     * 查询组合代码下拉框
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId")
    @ResponseBody
    public List<SelectOptionDto> getPortfolioId(){
        return upTrInqBuyBack002Service.getPortfolioId(RequestUtil.getPageParam());
    }
    /**
     * 
     * 查询托管账户信息
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAcctNo")
    @ResponseBody
    public List<SelectOptionDto> getAcctNo(){
        return upTrInqBuyBack002Service.getAcctNo(RequestUtil.getPageParam());
    }
    /**
     * 
     * 资金账户信息
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/getTrdAcctId")
    @ResponseBody
    public List<SelectOptionDto> getTrdAcctId(){
        return upTrInqBuyBack002Service.getTrdAcctId(RequestUtil.getPageParam());
    }
}
