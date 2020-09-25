package com.joyintech.tams.poc.directtransaction.uptrinqsec.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.joyintech.tams.poc.directtransaction.uptrinqsec.dto.UpTrInqSec001Dto;
import com.joyintech.tams.poc.directtransaction.uptrinqsec.service.UpTrInqSec002Service;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 现券买卖新增报价 〈功能详细描述〉
 * 
 * @author
 * @version 1.0 2018年12月18日
 * @see UpTrInqSec001Controller
 * @since 1.0
 */
@PageId("UpTrInqSec002")
@RequestMapping("/invest/trade/uptrinqsec/UpTrInqSec002")
@Controller
public class UpTrInqSec002Controller extends BaseController {

	@Autowired
	private UpTrInqSec002Service upTrInqSec002Service;
	
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
		model.addAttribute("T01", BussinessDictCodeList.交易方向_买入.getValue());
        model.addAttribute("T02", BussinessDictCodeList.交易方向_卖出.getValue());
		UpTrInqSec001Dto upTrInqSec001Dto = new UpTrInqSec001Dto();
		if (StringUtil.isNotEmpty(this.getParam("entrustId"))){
			// 新增界面，带出数据
			EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(this.getParam("entrustId"));
			if (etrEntrust != null){
				BeanUtil.copyPropertiesIgnoreNull(upTrInqSec001Dto, etrEntrust);
				if (upTrInqSec001Dto.getEntrustNum() != null){
					upTrInqSec001Dto.setEntrustNum(ArithmeticUtil.multiply(upTrInqSec001Dto.getEntrustNum(), new BigDecimal(100)));
				}
			}
			EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(this.getParam("entrustId"));
			if (etrEntrustAdd != null){
				BeanUtil.copyPropertiesIgnoreNull(upTrInqSec001Dto, etrEntrustAdd);
				if (upTrInqSec001Dto.getMatureYield() != null){
					upTrInqSec001Dto.setMatureYield(ArithmeticUtil.multiply(upTrInqSec001Dto.getMatureYield(), new BigDecimal(100)));
				}
				if (upTrInqSec001Dto.getExerYield() != null){
					upTrInqSec001Dto.setExerYield(ArithmeticUtil.multiply(upTrInqSec001Dto.getExerYield(), new BigDecimal(100)));
				}
			}
			PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(upTrInqSec001Dto.getPortfolioId());
			if (ptlPortfolio != null){
				upTrInqSec001Dto.setPortfolioName(ptlPortfolio.getPortfolioName());
			}
			FinProduct finProduct = finProductMapper.selectByPrimaryKey(upTrInqSec001Dto.getFinprodId());
			if (finProduct != null){
				upTrInqSec001Dto.setFinprodName(finProduct.getFinprodName());
				upTrInqSec001Dto.setFinprodMarketId(finProduct.getFinprodMarketId());
			}
			
			upTrInqSec001Dto.setUnitIntAmt(new BigDecimal("2.47"));   //应计利息
			upTrInqSec001Dto.setFullPrice(ArithmeticUtil.doAdd(upTrInqSec001Dto.getPrice(), upTrInqSec001Dto.getUnitIntAmt()));   //全价
			upTrInqSec001Dto.setTradeAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(upTrInqSec001Dto.getEntrustNum(), upTrInqSec001Dto.getFullPrice()), 100, 2));  //交易金额
			upTrInqSec001Dto.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(upTrInqSec001Dto.getEntrustNum(), upTrInqSec001Dto.getUnitIntAmt()), 100, 2));  //应计利息总额
			upTrInqSec001Dto.setSettleAmt(upTrInqSec001Dto.getTradeAmt());  //结算金额
			upTrInqSec001Dto.setCcy("人民币(CNY)");    //币种
			//获取交易方向
			String ps = "";
			if (StringUtil.equals(upTrInqSec001Dto.getPs(), BussinessDictCodeList.交易方向_买入.getValue())){
				ps = "psBuy";
			}
			if (StringUtil.equals(upTrInqSec001Dto.getPs(), BussinessDictCodeList.交易方向_卖出.getValue())
					|| StringUtil.equals(upTrInqSec001Dto.getPs(), BussinessDictCodeList.交易方向_行权.getValue())){
				ps = "psSell";
			}
			upTrInqSec001Dto.setPs(ps);    //交易方向
		}
		model.addAttribute(GlobalConstant.ENTITY, upTrInqSec001Dto);
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
		return upTrInqSec002Service.getPortfolioId(map);
	}

	/**
	 * 分页查询 资产代码
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFinprodMarketId")
	public List<SelectOptionDto> getFinprodMarketId() {
		Map<String, Object> map = RequestUtil.getPageParam();
		return upTrInqSec002Service.getFinprodMarketId(map);
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
	@RequestMapping("getfinprodIdForAdd")
	public List<SelectOptionDto> getfinprodIdForAdd() throws Exception {
		List<SelectOptionDto> temp = upTrInqSec002Service.getfinprodIdForAdd(RequestUtil.getPageParam());
		return temp;
	}

	/**
	 * 
	 * 交易对手 <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("getCounterId")
	public List<SelectOptionDto> getCounterId() {
		return upTrInqSec002Service.getCounterId(RequestUtil.getPageParam());
	}

	/**
	 * 
	 * 查询托管账户信息
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getAcctNo")
	@ResponseBody
	public List<SelectOptionDto> getAcctNo() {
		return upTrInqSec002Service.getAcctNo(RequestUtil.getPageParam());
	}

	/**
	 * 
	 * 资金账户信息
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getTrdAcctId")
	@ResponseBody
	public List<SelectOptionDto> getTrdAcctId() {
		return upTrInqSec002Service.getTrdAcctId(RequestUtil.getPageParam());
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
        return upTrInqSec002Service.getFinprodName(finprodId);
    }
}
