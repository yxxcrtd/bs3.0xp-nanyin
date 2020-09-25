package com.jixu.dataInterface.service;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.SystemInitialization;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.SecAcctDao;

/**
 * @ClassName ValidateFunc
 * @author Pan Wei
 * @Date 2016年12月3日 下午3:41:02
 * @Description []
 */

public abstract class AdditionValFunc extends AdditionFunc {

	@Resource(name = "commfuncService")
	protected CommFuncService comService;

	@Autowired
	protected IssueInfoDao issueInfoDao;

	@Autowired
	protected BkCashAcctDao bkCashAcctDao;

	@Autowired
	protected PortfolioHdrDao portfolioHdrDao;

	@Autowired
	protected SecAcctDao secAcctDao;

	@Autowired
	protected BkInfoDao bkInfoDao;
}
