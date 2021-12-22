package com.joyintech.tams.exchange.service.trade.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.exchange.common.BusinessResultException;
import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.investment.CapitalAccountBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.domain.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易测试接口")
public class TestController {
	
	@GetMapping("testList")
	@ApiOperation(value = "测试返回list", notes = "测试返回list")
    public List<CapitalAccountBean> testList() {
		List<CapitalAccountBean> capitalAccountBeanList = new ArrayList<CapitalAccountBean>();
		CapitalAccountBean c1 = new CapitalAccountBean();
		c1.setAccountName("accountName1");
		c1.setAcctNo("acctNo1");
		CapitalAccountBean c2 = new CapitalAccountBean();
		c2.setAccountName("accountName2");
		c2.setAcctNo("acctNo2");
		capitalAccountBeanList.add(c1);
		capitalAccountBeanList.add(c2);
        return capitalAccountBeanList;
    }
	
	@GetMapping("testMessage")
	@ApiOperation(value = "测试返回Message", notes = "测试返回Message")
    public Message<List<CapitalAccountBean>> testMessage() {
		List<CapitalAccountBean> capitalAccountBeanList = new ArrayList<CapitalAccountBean>();
		CapitalAccountBean c1 = new CapitalAccountBean();
		c1.setAccountName("accountName1");
		c1.setAcctNo("acctNo1");
		CapitalAccountBean c2 = new CapitalAccountBean();
		c2.setAccountName("accountName2");
		c2.setAcctNo("acctNo2");
		capitalAccountBeanList.add(c1);
		capitalAccountBeanList.add(c2);
        return new Message<List<CapitalAccountBean>>(capitalAccountBeanList);
    }
	
	@GetMapping("testException")
	@ApiOperation(value = "测试抛出Exception", notes = "测试抛出Exception")
    public Message<List<CapitalAccountBean>> testException() throws Exception {
		List<CapitalAccountBean> capitalAccountBeanList = new ArrayList<CapitalAccountBean>();
		CapitalAccountBean c1 = new CapitalAccountBean();
		c1.setAccountName("accountName1");
		c1.setAcctNo("acctNo1");
		CapitalAccountBean c2 = new CapitalAccountBean();
		c2.setAccountName("accountName2");
		c2.setAcctNo("acctNo2");
		capitalAccountBeanList.add(c1);
		capitalAccountBeanList.add(c2);
		if(1==1) {
			throw new Exception("testException");
		}
        return new Message<List<CapitalAccountBean>>(capitalAccountBeanList);
    }
	
	@GetMapping("testBusinessResultException")
	@ApiOperation(value = "测试抛出BusinessResultException", notes = "测试抛出BusinessResultException")
    public Message<List<CapitalAccountBean>> testBusinessResultException() {
		List<CapitalAccountBean> capitalAccountBeanList = new ArrayList<CapitalAccountBean>();
		CapitalAccountBean c1 = new CapitalAccountBean();
		c1.setAccountName("accountName1");
		c1.setAcctNo("acctNo1");
		CapitalAccountBean c2 = new CapitalAccountBean();
		c2.setAccountName("accountName2");
		c2.setAcctNo("acctNo2");
		capitalAccountBeanList.add(c1);
		capitalAccountBeanList.add(c2);
		if(1==1) {
			throw new BusinessResultException(ErrorCode.ACCOUNT_APPLY_NOT_UNIQUE);
		}
        return new Message<List<CapitalAccountBean>>(capitalAccountBeanList);
    }

}
