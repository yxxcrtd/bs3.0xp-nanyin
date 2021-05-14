/*
 * 文件名：PayTheVatQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.invest.product.paythevatquery.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.invest.product.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.invest.product.paythevatquery.service.PayTheVatQuery001Service;
import com.joyintech.tams.invest.product.paythevatquery.service.PayTheVatQuery002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * 纳税增值税新建
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Controller
 * @since 1.0
 */
@Controller("finproductPayTheVatQuery002Controller")
@PageId("FinproductPayTheVatQuery002")
@RequestMapping("/invest/product/paythevatquery/PayTheVatQuery002")
public class PayTheVatQuery002Controller extends BaseController{
    
    @Autowired
    private PayTheVatQuery002Service payTheVatQuery002Service;
    
    @Autowired
    private PayTheVatQuery001Service payTheVatQuery001Service;
    
    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

	@Autowired
	private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向,DictCode.币种,BussinessDictCode.是否,DictCode.增值税品种);
        String workflowId = getParam("workflowId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            PayTheVatQuery002Dto dto = payTheVatQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", dto);
			model.addAttribute("payType",dto.getPayType());

		} else {
        	PayTheVatQuery002Dto dto = new PayTheVatQuery002Dto();
        	dto.setPayTaxVDate(DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_DATE_FORMAT_EN));
        	model.addAttribute("entity", dto);
			model.addAttribute("payType",dto.getPayType());

		}
        model.addAttribute("TAX_CLASS_01", DictCodeListExt3.增值税品种_产品增值税.getValue());
        model.addAttribute("TAX_CLASS_02", DictCodeListExt3.增值税品种_通道增值税.getValue());
        model.addAttribute("PAY_MODE_02", DictCodeListExt2.划款方式_一次性划款.getValue());
        return super.init(model);
    }
    /**
     * 查询
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("search")
    @ResponseBody
    public Page<PayTheVatQuery002Dto> search(Map<String, Object> params) throws Exception {
        return payTheVatQuery002Service.search(RequestUtil.getPageParam());
    }
    
    /**
     * 查询账套/通道的check 
     * @param dto
     * @return
     */
    @RequestMapping(value = "checkBokDate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult checkBokDate(@RequestBody PayTheVatQuery002Dto dto){
    	Map<String, Object> param = new HashMap<>();
	    String msg1 = "";
	    if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_产品增值税.getValue())) {
	        param.put("payment1", dto.getPayment1());
	        if(StringUtil.isNotEmpty(dto.getPortfolioId())){
	            param.put("portfolioId", dto.getPortfolioId());
	        }
	        List<PayTheVatQuery002Dto> listdto = payTheVatQuery002Service.checkdate(param);  
	      
	        if(!CollectionUtils.isEmpty(listdto) ){
	            for(PayTheVatQuery002Dto m :listdto){
	                msg1 +="," + m.getBooksetName();
	            }
	        }
			//modify by jyl 20200113 软控制改为硬控制
	        if(StringUtils.isNotEmpty(msg1)){
	            return new AjaxResult("存在账套日-1小于缴税截止日的账套"+msg1,MessageType.错误消息);
	        }
	    } else if(StringUtil.equals(dto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())){
	        param.put("channelId", dto.getChannelId());
	        Date lastSettleDate = payTheVatQuery002Service.getLastChannelSettleDate(param);
	        String lastSettleDateStr = DateUtil.format(lastSettleDate, DateUtil.DEFAULT_DATE_FORMAT_EN);
	        if(DateUtil.compareDate(dto.getPayTaxVDate(), lastSettleDateStr)<0) {
	            return new AjaxResult("纳税日期必须大于等于"+lastSettleDateStr+"日期",MessageType.错误消息); 
	        }
	    }

        return new AjaxResult(); 

    }
    /**
     * 校验缴税/退税日期
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "checkdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult checkdate(@RequestBody PayTheVatQuery002Dto dto)  {
    	String msg = StringUtils.EMPTY;
    	String msg1 = StringUtils.EMPTY;
    	String msg2 = StringUtils.EMPTY;
    	for(PayTheVatQuery002TableDto tableDto : dto.getListpayTheVatQuery002TableDto()){
    	    String booksetId = tableDto.getFinprodId();
//    	    String payment2 = dto.getPayment2();
    	    String payment1 = dto.getPayment1();
//    	    Map<String, Object> Param = new HashMap<>();
//    	    Param.put("booksetId", booksetId);
//    	    Param.put("payment2", payment2);
//    	    List<PayTheVatQuery002Dto> listdto = payTheVatQuery002Service.checkdate(Param);       
//    	    if(!CollectionUtils.isEmpty(listdto) ){         
//    	    	msg1 += tableDto.getPortfolioName();
//    	    }
//    	    Map<String, Object> Param1 = new HashMap<>();
//    	    Param1.put("booksetId", booksetId);
//    	    Param1.put("payment1", payment1);
//    	    List<PayTheVatQuery002Dto> listdto1 = payTheVatQuery002Service.checkdate(Param1);       
//    	    if(!CollectionUtils.isEmpty(listdto1) ){         
//    	    	msg2 += tableDto.getPortfolioName();
//    	    }
    		PtlCashPositionFlowDto querydto = new PtlCashPositionFlowDto();
    		querydto.setPortfolioId(tableDto.getPortfolioId());
    		querydto.setHodingType(BussinessDictCodeList.持仓类型_可用.getValue());
    		querydto.setCdate(DateUtil.parse(dto.getPayment2()));
    		querydto.setCcy(dto.getCcy());
    		querydto.setAcctType(StringUtil.splitToList(GlobalConstant.COMMA, BussinessDictCodeList.资金账户类型_托管账户.getValue()+","+BussinessDictCodeList.资金账户类型_交易所资金账户.getValue()));

//			BigDecimal cash = basePtlCashPositionInfoService.queryUsableCashPosition(querydto);
//			if (StringUtil.isNotEmpty(cash) && cash.compareTo(tableDto.getTaxTotal()) < 0) {
//				msg += tableDto.getPortfolioName();
//			}
    	}
    	if(StringUtils.isNotEmpty(msg2)){ 
    		msg2 += "账套日-1小于缴税截止日!";
    	}
//    	if(StringUtils.isNotEmpty(msg1)){ 
//    		msg1 += "缴税/纳税日期不能小于产品的账套日期!";
//    	}
        if(StringUtils.isNotEmpty(msg)){  
        	msg += "产品或组合剩余现金不足!";
        }
        if(StringUtils.isNotEmpty(msg2+msg1+msg)){
        	return new AjaxResult(msg2+msg1+msg,MessageType.错误消息);
       }else {
            return new AjaxResult(); 
        }
        
    }
    
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody  PayTheVatQuery002Dto dto, BindingResult br) throws Exception {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
		if (WorkFlowUtil.isNotFromToDoList()) {
			String ms = "";
			// modify by jiangpeng on 20210514 start ZGBSSE-388
			for (PayTheVatQuery002TableDto mo : dto.getListpayTheVatQuery002TableDto()) {
				WflValidInputParameter param = new WflValidInputParameter();
				param.setBusinessDataPk2(mo.getFinprodId());
				param.setPageId("FinproductPayTheVatQuery002");
				// 查询该产品流程中的增值税信息 查询出的结果产品id已经与校验数据产品id相同
				List<ProcessMark> processMarkList = mstWorkflowCommonService.getDataStatusFromProcessMarkList(param);
				for (ProcessMark processMark : processMarkList) {
					PayTheVatQuery002Dto payTheVatQuery002Dto = JsonUtils.OBJECT_MAPPER.readValue(processMark.getBusinessData(), PayTheVatQuery002Dto.class);
					if (!StringUtil.isNotEmpty(payTheVatQuery002Dto)
							|| !StringUtil.equals(payTheVatQuery002Dto.getTaxClass(), mo.getTaxClass())) {
						continue;
					}
					// 增值税品种为产品增值税时,则判定为存在流程中数据
					if (StringUtil.equals(mo.getTaxClass(), DictCodeListExt3.增值税品种_产品增值税.getValue())) {
						ms = this.getMessage("COMMON_E_0021", payTheVatQuery002Dto.getTotleTradeId(), mo.getFinprodId());
						return new AjaxResult(ms, MessageType.错误消息);
					}// 增值税品种为通道增值税时,判断通道户是否一致
					else if (StringUtil.equals(payTheVatQuery002Dto.getChannelId(), mo.getChannelId())) {
						ms = this.getMessage("COMMON_E_0021", payTheVatQuery002Dto.getTotleTradeId(), mo.getFinprodId());
						return new AjaxResult(ms, MessageType.错误消息);
					}
				}
			}
			// modify by jiangpeng on 20210514 end ZGBSSE-388
		}
   	 	AjaxResult ajaxResult= this.commonCheck(br, dto.getOperateType());
        if(ajaxResult.getErrorList() != null && ajaxResult.getErrorList().size()>0 ){
        	return  ajaxResult;
        }
       
        AjaxResult result = new AjaxResult();
        String str = "";
        String btnName = "";
    	if(!StringUtil.isEmptyList(dto.getBussTrdTransferOrder()) && dto.getBussTrdTransferOrder().size()>0){
	       	if(!StringUtil.isNotEmpty(dto.getBussTrdTransferOrder().get(0).getRecAcctNo())){
	       		str = "收款账户未填,";
	       	}
    	}
    	
    	if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,dto.getOperateType())) {
			btnName = "请确认是否保存?";
		}
		else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,dto.getOperateType())) {
			btnName = "请确认是否提交?";
		}
   	 	result.setData(this.getMessage(str+btnName));
   	 	return result;
    }
    
    /**
     * 
     * 新建页面获取组合下拉框选项
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectPortfolioId")
    @ResponseBody
    public Page<SelectOptionDto> selectPortfolioId(){
        Map<String,Object> pageParam = RequestUtil.getPageParam();
        String taxClass = (String)pageParam.get("taxClass");
        String channelId = (String)pageParam.get("channelId");
        String payTaxMDate = (String)pageParam.get("payTaxMDate");
        if(StringUtil.equals(taxClass, DictCodeListExt3.增值税品种_产品增值税.getValue())){
            return payTheVatQuery001Service.selectPortfolioId(pageParam);  
        } else if(StringUtil.equals(taxClass, DictCodeListExt3.增值税品种_通道增值税.getValue())
            && StringUtil.isNotEmpty(channelId) && StringUtil.isNotEmpty(payTaxMDate)){
            return payTheVatQuery002Service.selectPortfolioIdByChannel(pageParam);
        }  else {
            return new Page<SelectOptionDto>();
        }
    }

    
    
    @RequestMapping(value = "docommit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult docommit(@RequestBody PayTheVatQuery002Dto dto,
                               BindingResult result) throws Exception {
    	String msg = getMessage("COMMON_I_0001");
    	for(PayTheVatQuery002TableDto tabledto : dto.getListpayTheVatQuery002TableDto()){
    		PayTheVatQuery002Dto payTheVatQuery002Dto = new PayTheVatQuery002Dto();
    		BeanUtil.copyProperties(payTheVatQuery002Dto, dto);
    		// 启动流程
    		ProcessParameter<PayTheVatQuery002Dto> proParam = new ProcessParameter<>();
    		WorkFlowParameter<PayTheVatQuery002Dto> params = new WorkFlowParameter<>();
    		String busiCode = interfCommonService.getTradeid(dto.getTaxidenType(), dto.getCreateTime(), "A");
    		if (StringUtil.isEmpty(payTheVatQuery002Dto.getTotleTradeId())) {
    			payTheVatQuery002Dto.setTotleTradeId(busiCode);
    		}
    		payTheVatQuery002Dto.setFinprodId(tabledto.getFinprodId());
    		payTheVatQuery002Dto.setTradeAmt(tabledto.getTaxTotal());
    		payTheVatQuery002Dto.setPortfolioId(tabledto.getPortfolioId());
    		//指令过滤
    		BussTrdTransferOrderDto bussTrdTransferOrderDtos = null;
    		for(BussTrdTransferOrderDto m : payTheVatQuery002Dto.getBussTrdTransferOrder()){
    			if(StringUtil.equals(m.getPortfolioId(), tabledto.getPortfolioId())){
    				bussTrdTransferOrderDtos = m;
    			}
    		}
    		payTheVatQuery002Dto.setBussTrdTransferOrder(new ArrayList<>());
    		payTheVatQuery002Dto.getBussTrdTransferOrder().add(bussTrdTransferOrderDtos);
    		// 业务代码（交易编号
    		params.setBusinessCode(busiCode);
    		// 业务类型
    		params.setBusinessType(DictCodeList.共通业务类型_缴纳增值税.getValue());
    		// 页面id
    		params.setPageId(this.getPageId());
    		// 元素id
    		params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
    		
    		// 设置流程业务说明, entity即传入的dto
    		if(StringUtil.equals(tabledto.getTaxClass(), DictCodeListExt3.增值税品种_通道增值税.getValue())) {
                params.setComment(entity -> WorkFlowUtil.concatComment(tabledto.getChannelName()+"("+tabledto.getChannelId() +")" , "交易金额("+tabledto.getTaxTotal()+")" , "交易日期(" + payTheVatQuery002Dto.getPayment1()+")",
                    "交易编号("+payTheVatQuery002Dto.getTotleTradeId()+")"));
    		} else {
    	       params.setComment(entity -> WorkFlowUtil.concatComment(tabledto.getFinprodAbbr()+"("+tabledto.getProdId() +")" , "交易金额("+tabledto.getTaxTotal()+")" , "交易日期(" + payTheVatQuery002Dto.getPayment1()+")",
                       "交易编号("+payTheVatQuery002Dto.getTotleTradeId()+")"));
    		}
    		

    		// 塞入流程参数
    		proParam.setWorkFlowParameter(params);
    		payTheVatQuery002Dto.setListpayTheVatQuery002TableDto(new ArrayList<PayTheVatQuery002TableDto>());
    		payTheVatQuery002Dto.getListpayTheVatQuery002TableDto().add(tabledto);
    		Date payment1 =  DateUtil.parse(payTheVatQuery002Dto.getPayment1());
    		String year = DateUtil.getYearStr(payTheVatQuery002Dto.getPayment1());
    		String season = DateUtil.getSeason(payment1)+"";
    		String remark = year+"年"+season+"季度销项税金结转金额:"+tabledto.getTaxTotal();
    		payTheVatQuery002Dto.setRemark(remark);
    		try {
    			msg = payTheVatQuery002Service.start(proParam, payTheVatQuery002Dto);
    		}
    		catch (Exception e) {
    			getLogger().error("启动流程失败：", e);
    			return new AjaxResult(msg);
    		}
    	}
    		
        return new AjaxResult(msg);
    }
    
    /**
     * 列表删除-不带流程
     * 
     * @return int
     * @throws IOException 转换
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody PayTheVatQuery002Dto dto) {
        String msg = StringUtils.EMPTY;
        String booksetId = dto.getBooksetId();
        int row = payTheVatQuery002Service.delete(booksetId);
        if (row > 0) {
            msg = "删除成功";
            return new AjaxResult(msg);
        }
            msg = "删除失败";
            return new AjaxResult(msg, MessageType.错误消息);
       
     }
    
    /**
     * 查询币种
     */
    @ResponseBody
    @RequestMapping("searchCurrency")
    public List<String> searchCurrency(Map<String, Object> params) throws Exception {
    	
    	params.put("finprodMarketId", getParam("finprodMarketId"));
    	
        return payTheVatQuery002Service.searchCurrency(params);
    }
    
}












