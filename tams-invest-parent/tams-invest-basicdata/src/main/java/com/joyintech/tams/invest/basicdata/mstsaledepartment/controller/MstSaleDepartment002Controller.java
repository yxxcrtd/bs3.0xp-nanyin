package com.joyintech.tams.invest.basicdata.mstsaledepartment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.service.MstSaleDepartmentService;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstCustomerDto;

@Controller
@PageId("MstSaleDepartment002")
@RequestMapping("/invest/basicdata/mstsaledepartment/MstSaleDepartment002")
public class MstSaleDepartment002Controller extends BaseController {
	/**
	 * 营业部管理service注入
	 */
	@Autowired
	private MstSaleDepartmentService mstSaleDepartmentService;

	/**
	 * 新建页面初始化
	 */

	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, new DictCode[] {DictCode.申报单位,DictCode.特殊处理业务,DictCode.柜台接口类型,DictCode.是或否});
//		List<SelectOptionDto> brokerIds = new ArrayList<SelectOptionDto>();
//		try {
//			brokerIds = getMstCustomerInfo();
//			model.addAttribute("brokerIds", JsonUtils.objectArray2Json(brokerIds.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		List<SelectOptionDto> SZSeatNo = new ArrayList<SelectOptionDto>();
//		 Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			//上交所席位号
//			map.put("tradeMarket", DictCodeListExt1.交易场所_上海证券交易所.getValue());
//			SZSeatNo = getSeatInfoByCustomer(map);
//			model.addAttribute("SHSeatNo", JsonUtils.objectArray2Json(SZSeatNo.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		List<SelectOptionDto> SHSeatNo = new ArrayList<SelectOptionDto>();
//		try {
//			//深交所席位号
//			map.put("tradeMarket", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
//			SHSeatNo = getSeatInfoByCustomer(map);
//			model.addAttribute("SZSeatNo", JsonUtils.objectArray2Json(SHSeatNo.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		List<SelectOptionDto> ZJSeatNo = new ArrayList<SelectOptionDto>();
//		try {
//			//中金所席位号
//			map.put("tradeMarket", DictCodeListExt1.交易场所_中国金融期货交易所.getValue());
//			ZJSeatNo = getSeatInfoByCustomer(map);
//			model.addAttribute("ZJSeatNo", JsonUtils.objectArray2Json(ZJSeatNo.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		List<SelectOptionDto> SQSeatNo = new ArrayList<SelectOptionDto>();
//		try {
//			//上期所席位号
//			map.put("tradeMarket", DictCodeListExt1.交易场所_上海期货交易所.getValue());
//			SQSeatNo = getSeatInfoByCustomer(map);
//			model.addAttribute("SQSeatNo", JsonUtils.objectArray2Json(SQSeatNo.toArray()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String saledepid = getParam("saledepid");
		// 根据主键获取业务实体
		if (StringUtils.isNotEmpty(saledepid)) {
			MstSaleDepartmentDto dto = new MstSaleDepartmentDto();
			dto.setSaledepid(saledepid);
			MstSaleDepartmentDto entity = mstSaleDepartmentService.selectDetail(dto);
			model.addAttribute("entity", entity);
			model.addAttribute("SHSeatNo", entity.getShseatno());
			model.addAttribute("SZSeatNo", entity.getSzseatno());
			model.addAttribute("ZJSeatNo", entity.getZjseatno());
		}

		return super.init(model);
	}
	
	
	/**
	 * 券商
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getMstCustomerInfo")
    public List<SelectOptionDto> getMstCustomerInfo()
            throws Exception {
            Map<String, Object> map = new HashMap<String, Object>();
            List<SelectOptionDto> temp = mstSaleDepartmentService.getMstCustomerInfo(RequestUtil.getPageParam());
            return temp;
        }
    
    /**
     * 上海/深圳交易所席位号
     * @param map
     * @return
     * @throws Exception
     */
	@ResponseBody
	@RequestMapping("getSeatInfoByCustomer")
    public List<SelectOptionDto> getSeatInfoByCustomer(Map<String, Object> map)
            throws Exception {
            String brokerId = super.getRequest().getParameter("brokerid");
            String tradeMarket = super.getRequest().getParameter("tradeMarket");
            if(StringUtils.isNotEmpty(brokerId)){
                map.put("brokerId", brokerId);	
            }
            if(StringUtils.isNotEmpty(tradeMarket)){
                map.put("tradeMarket", tradeMarket);	
            }
            List<SelectOptionDto> temp = mstSaleDepartmentService.getSeatInfoByCustomer(map);
            return temp;
        }
	
	/**
	 * 保存
	 * 
	 * @param dto
	 *            dto
	 * @param br
	 *            br
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("save")
	public boolean save(@RequestBody MstSaleDepartmentDto dto) throws Exception {
		return mstSaleDepartmentService.save(dto);
	}

	/**
	 * 保存按钮点击时的check
	 *
	 * @param dto
	 *            dto
	 * @param result
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) MstSaleDepartmentDto dto,
			BindingResult result) {
		if (result.getAllErrors().isEmpty()) {
			AjaxResult ar = commonCheck(result, dto.getOperateType());
			return ar;
		}
		return commonCheck(result, dto.getOperateType());
	}
	
	/**
	 * 根据选择的券商信息过滤交易场所
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping("getTradeMarketBybrokerId")
    public Map<String, Object> getTradeMarketBybrokerId()
            throws Exception {
			Map<String, Object> map = new HashMap<>();
			MstCustomerDto customerDto = new MstCustomerDto();
			List<MstCustomerDto> customerDtos = new ArrayList<>();
			Map<String, Object> param = RequestUtil.getParameters(getRequest());
		    if(StringUtils.isNotEmpty((String) param.get("customerId")) ){
		    customerDtos =  mstSaleDepartmentService.getTradeMarketBybrokerId(param);
		    if(null != customerDtos && customerDtos.size() > 0 ){
		    	customerDto = customerDtos.get(0);
		    	//券商
		    	if(null != customerDto.getIsBroker() && customerDto.getIsBroker().equals("Y")){
		    		map.put("isBroker", "Y");
		    	}else{
		    		map.put("isBroker", "N");
		    	}
		    	}
		    }
		    return map;
        }
}
