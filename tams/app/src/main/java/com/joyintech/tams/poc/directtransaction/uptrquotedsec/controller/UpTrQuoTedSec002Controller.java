package com.joyintech.tams.poc.directtransaction.uptrquotedsec.controller;

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
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.poc.directtransaction.uptrquotedsec.dto.UpTrQuoTedSec001Dto;
import com.joyintech.tams.poc.directtransaction.uptrquotedsec.service.UpTrQuoTedSec002Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 新增请求报价
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see
 * @since 1.0
 */
@PageId("UpTrQuoTedSec002")
@RequestMapping("/invest/trade/uptrquotedsec/UpTrQuoTedSec002")
@Controller
public class UpTrQuoTedSec002Controller extends BaseController{
	
	@Autowired
	private UpTrQuoTedSec002Service upTrQuoTedSec002Service;
	
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
		UpTrQuoTedSec001Dto UpTrQuoTedSec001Dto = new UpTrQuoTedSec001Dto();
		if (StringUtil.isNotEmpty(this.getParam("entrustId"))){
			// 新增界面，带出数据
			EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(this.getParam("entrustId"));
			if (etrEntrust != null){
				BeanUtil.copyPropertiesIgnoreNull(UpTrQuoTedSec001Dto, etrEntrust);
				if (UpTrQuoTedSec001Dto.getEntrustNum() != null){
					UpTrQuoTedSec001Dto.setEntrustNum(ArithmeticUtil.multiply(UpTrQuoTedSec001Dto.getEntrustNum(), new BigDecimal(100)));
				}
			}
			EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(this.getParam("entrustId"));
			if (etrEntrustAdd != null){
				BeanUtil.copyPropertiesIgnoreNull(UpTrQuoTedSec001Dto, etrEntrustAdd);
				if (UpTrQuoTedSec001Dto.getMatureYield() != null){
					UpTrQuoTedSec001Dto.setMatureYield(ArithmeticUtil.multiply(UpTrQuoTedSec001Dto.getMatureYield(), new BigDecimal(100)));
				}
				if (UpTrQuoTedSec001Dto.getExerYield() != null){
					UpTrQuoTedSec001Dto.setExerYield(ArithmeticUtil.multiply(UpTrQuoTedSec001Dto.getExerYield(), new BigDecimal(100)));
				}
			}
			PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(UpTrQuoTedSec001Dto.getPortfolioId());
			if (ptlPortfolio != null){
				UpTrQuoTedSec001Dto.setPortfolioName(ptlPortfolio.getPortfolioName());
			}
			FinProduct finProduct = finProductMapper.selectByPrimaryKey(UpTrQuoTedSec001Dto.getFinprodId());
			if (finProduct != null){
				UpTrQuoTedSec001Dto.setFinprodName(finProduct.getFinprodName());
				UpTrQuoTedSec001Dto.setFinprodMarketId(finProduct.getFinprodMarketId());
			}
			
			//获取交易方向
			String ps = "";
			if (StringUtil.equals(UpTrQuoTedSec001Dto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())){
				ps = "psBuy";
			}
			if (StringUtil.equals(UpTrQuoTedSec001Dto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue())
					|| StringUtil.equals(UpTrQuoTedSec001Dto.getPs(), BussinessDictCodeList.交易方向_行权.getValue())){
				ps = "psSell";
			}
			UpTrQuoTedSec001Dto.setPs(ps);    //交易方向
		}
		
		model.addAttribute(GlobalConstant.ENTITY, UpTrQuoTedSec001Dto);
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
	public List<SelectOptionDto> getPortfolioId() {
		Map<String, Object> map = RequestUtil.getPageParam();
		// map.put("userId",SecurityUtil.getLoginUser().getUserName());
		return upTrQuoTedSec002Service.getPortfolioId(map);
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
        return upTrQuoTedSec002Service.getAcctNo(RequestUtil.getPageParam());
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
        return upTrQuoTedSec002Service.getTrdAcctId(RequestUtil.getPageParam());
    }
    
    /**
	 * 获取新建界面的资产代码联想下拉框框
	 * 
	 * @param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("/getfinprodIdForAdd")
	public List<SelectOptionDto> getfinprodIdForAdd() {
		List<SelectOptionDto> temp = upTrQuoTedSec002Service.getfinprodIdForAdd(RequestUtil.getPageParam());
		return temp;
	}
	
	/**
     * 
     * 资产名称联动
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodName")
    public String getFinprodName(String finprodId){
        return upTrQuoTedSec002Service.getFinprodName(finprodId);
    }
}
