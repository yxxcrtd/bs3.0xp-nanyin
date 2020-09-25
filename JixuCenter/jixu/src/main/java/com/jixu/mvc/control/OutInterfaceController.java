package com.jixu.mvc.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.util.DateUtil;
import com.jixu.mvc.control.model.PortParams;
import com.jixu.mvc.control.service.OutInterfaceService;

@RestController  
public class OutInterfaceController extends ExceptionControl{
	
	@Autowired
	private OutInterfaceService outInterfaceService;
	
	/**
	 * 获取该投资组合的最新单位净值
	 * @param portnav
	 * @return
	 */
	@PostMapping(value="/outInterface/getLatestNav")
	@ResponseBody
	public double getLatestNav(@RequestBody PortParams params) {
		Date navdate=DateUtil.convertStr2Date2(params.getNavDate());
		double nav= outInterfaceService.getLatestNav(params.getPortnum(), navdate);
		if(nav==0)
			nav=1;
		return nav;
	}
	
	/**
	 * 通过投资组合编号获取代码
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getPortnumByCode")
	@ResponseBody
	public String getPortnumByCode(@RequestBody PortParams params) {
		return outInterfaceService.getPortnumByCode(params.getPortcode());
	}
	
	/**
	 * 通过投资组合编号获取代码
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getBkAccIDByExtacct")
	@ResponseBody
	public int getBkAccIDByExtacct(@RequestBody PortParams params) {
		return outInterfaceService.getBkAccIDByExtacct(params.getExtacctcode());
	}
	
	/**
	 * 通过银行账户查找证券账户
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getSecAcctByBkAcct")
	@ResponseBody
	public String getSecAcctByBkAcct(@RequestBody PortParams params) {
		return outInterfaceService.getSecAcctByBkAcct(params.getBkAcctID());
	}
	
	/**
	 * 通过科目名找科目号
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getCoaacctByPortnumAcctName")
	@ResponseBody
	public String getCoaacctByPortnumAcctName(@RequestBody PortParams params) {
		return outInterfaceService.getCoaacctByPortnumAcctName(params.getPortnum(), params.getAcctName());
	}
	
	/**
	 * 通过证券名找cusip
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getCusipByName")
	@ResponseBody
	public String getCusipByName(@RequestBody PortParams params) {
		return outInterfaceService.getCusipByName(params.getAcctName());
	}
	
	/**
	 * 通过证券代码名找cusip
	 * @param portcode
	 * @return
	 */
	@PostMapping(value="/outInterface/getCusipBySecid")
	@ResponseBody
	public String getCusipBySecid(@RequestBody PortParams params) {
		return outInterfaceService.getCusipBySecid(params.getAcctName());
	}
}
