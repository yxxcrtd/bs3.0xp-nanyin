/*
 * 文件名：TrdCommandDispatcher002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.investtrade.commanddispatcher.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;
import com.joyintech.tams.invest.trade.investtrade.commanddispatcher.service.TrdCommandDispatcher001Service;

/**
 * 指令分发页面-手工分发
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月14日
 * @see TrdCommandDispatcher002Controller
 * @since 1.0
 */
@Controller
@PageId("TrdCommandDispatcher002")
@RequestMapping("/invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher002")
public class TrdCommandDispatcher002Controller extends BaseController{
	/**
     * 指令分发service注入
     */
	@Autowired
	private TrdCommandDispatcher001Service trdCommandDispatcher001Service;
	
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		String entrustIds=getParam("entrustIds");
		String finprodMarketIds=getParam("finprodMarketIds");
		String finprodType2s=getParam("finprodType2s");
		String userNames=getParam("userNames");
		List<TrdCommandDispatcher001Dto> radios=trdCommandDispatcher001Service.getRadios();
		//根据资产类型和资产代码查询交易员组
		String bussBidDate=getParam("bussBidDate");
		model.addAttribute("bussBidDate", bussBidDate);
		model.addAttribute("finprodMarketIds", finprodMarketIds);
		model.addAttribute("Radios", radios);
		model.addAttribute("finprodType2s", finprodType2s);
		model.addAttribute("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
		model.addAttribute("entrustIds", entrustIds);
		model.addAttribute("userNames", userNames);
        // add by dubaojun 2020-06-29 上海农商行项目新增是否进入审批流程 start
        String isApproval = getParam("isApproval");
        model.addAttribute("isApproval", isApproval);
        // add by dubaojun 2020-06-29 上海农商行项目新增是否进入审批流程 end
		return super.init(model);
	}

	
	/**
     * 获取联想文本框交易员的查询方法
     * @param param param
     * @return List
     * @see
     * @since 1.0
     */
	@ResponseBody
	@RequestMapping("getUsers")
	public List<SelectOptionDto> getUsers() throws Exception{
		Map<String,Object> param = RequestUtil.getParameters(getRequest());
//		String entrustId = super.getRequest().getParameter("entrustId");
//		String finprodType2s = super.getRequest().getParameter("finprodType2s");
		return trdCommandDispatcher001Service.getUsers(param);
	}
	
	/**
     * 对前台传入数据进行验证
     * @param dto 实体类
     * @param result 结果集
     * @param model
     * @return AjaxResult
     */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody TrdCommandDispatcher001Dto dto) {
		Map<String, Object> param=new HashMap<String, Object>();
		AjaxResult result=new AjaxResult();
//		List<ValidMessage> errorList=trdCommandDispatcher001Service.distributeCheck(dto);
		List<ValidMessage> errorList=new ArrayList<>();
		//同一债券同一投标日，若已有分发记录，再次对同一标的同一投标日的委托做分发时，限制只能分发给同一交易员。否则系统给出提示信息并限制操作的check
		List<String>  FinprodTypeList= StringUtil.splitToList(GlobalConstant.COMMA,dto.getFinprodType2());
		List<String>  bussBidDateList=StringUtil.splitToList(GlobalConstant.COMMA, dto.getBussBidDate());
		List<String>  finprodMarketIdsList=StringUtil.splitToList(GlobalConstant.COMMA, dto.getFinprodMarketId());
		if(!FinprodTypeList.isEmpty()&&!bussBidDateList.isEmpty()){
			for (int i = 0; i < FinprodTypeList.size(); i++)  {
				if(StringUtil.equals(FinprodTypeList.get(i), DictCodeListExt2.平安资产类型_一级市场债.getValue())&&!StringUtil.equals(bussBidDateList.get(i).trim(),"undefined")){
					dto.setBussBidDate(bussBidDateList.get(i));
					dto.setFinprodMarketId(finprodMarketIdsList.get(i));
					List<TrdCommandDispatcher001Dto> listTraders=trdCommandDispatcher001Service.getTraders(dto);
					if(!listTraders.isEmpty()){
						for (TrdCommandDispatcher001Dto list2 : listTraders) {
							if(!StringUtil.equals(list2.getTrader(),dto.getDealers())&&StringUtil.isNotEmpty(list2)){
								errorList.add(new ValidMessage(this.getMessage("TRD_I_0004")+list2.getTrader()));
								result.setErrorList(errorList);
								return result;
							}
						}
					}
				}
			}
		}
		//end
		//验证成功后的判断相应权限
//		if(errorList.size() == 0){
			param.put("userName", dto.getDealers());
			param.put("finprodType2", dto.getFinprodType2());
//			List<ValidMessage> errorList2=trdCommandDispatcher001Service.getAssetTypeUser(param);
//			result.setErrorList(errorList2);
//		}else{
//			result.setErrorList(errorList);
//		}
//		if(result.getErrorList().size() == 0){
		    dto.setUpdateUser(LoginUserUtil.getUserName());
		    dto.setCreateTime(DateUtil.platDateFromCache());
		    trdCommandDispatcher001Service.delAndAddEtrDistribute(dto);
//		}
		return result;
    }
	/**
	 * check后的委托指令分发
	 * <功能详细描述>
	 * @param dto
	 * @see
	 * @since 1.0
	 */
	/*@RequestMapping("delAndAddEtr")
	public int delAndAddEtrDistribute(@RequestBody TrdCommandDispatcher001Dto dto){
	    return trdCommandDispatcher001Service.delAndAddEtrDistribute(dto);
	}*/
	
	
	 
}
