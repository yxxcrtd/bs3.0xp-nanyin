/*
 * 文件名：AccountsReceivableInfoQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.dto.AccountsReceivableInfoQuery001Dto;
import com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.service.AccountsReceivableInfoQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 应收款项信息查询
 * 
 * @author zhangzhao
 * @version 1.0 2018年10月9日
 * @see AccountsReceivableInfoQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("AccountsReceivableInfoQuery001")
@RequestMapping("/valuation/trademanage/accountsreceivableinfoquery/AccountsReceivableInfoQuery001")
public class AccountsReceivableInfoQuery001Controller extends BaseController {

	@Autowired
	private AccountsReceivableInfoQuery001Service accountsReceivableInfoQuery001Service;
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
	@Autowired
    private BaseCommonService baseCommonService;
	/**
	 *  页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		 String sysDate=baseCommonService.getSysDateString();
	        if(sysDate==null||"".equals(sysDate)){
	            sysDate=DateUtil.format(new Date());
	        }
	        model.addAttribute("sysDate", sysDate);
		this.setSelectOptionToModel(model, DictCode.应收款项类别);
		return super.init(model);
	}

	@RequestMapping("findPage")
	@ResponseBody
	public Page<AccountsReceivableInfoQuery001Dto> findPage() throws Exception {
		Map<String,Object> reqMap = RequestUtil.getPageParam();
		return accountsReceivableInfoQuery001Service.findByPage(reqMap);
	}
	
	/**
     * 账套代码下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
        return valAccountHandle001Service.getBookSetId2(RequestUtil.getPageParam()); 
    }

    /**
     * 标的资产下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBdFinprodId")
    @ResponseBody
    public List<SelectOptionDto> getBdFinprodId() throws Exception{
    	Map<String, Object> param = RequestUtil.getPageParam();
        return accountsReceivableInfoQuery001Service.getBdFinprodId(param); 
    }

	/*
	 * 导出信息excel
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		 String datas = this.getParam("datas");
		 Map<String, Object> paraMap = RequestUtil.getPageParam();
         List<AccountsReceivableInfoQuery001Dto> listdto;
         if (datas == null) {
        	 listdto = (List<AccountsReceivableInfoQuery001Dto>)accountsReceivableInfoQuery001Service.findPageout(paraMap);
         }
         else {
        	 listdto = JsonUtils.json2List(datas, AccountsReceivableInfoQuery001Dto.class);
         }
         File outFile = accountsReceivableInfoQuery001Service.makeFile(
  				"/com/joyintech/tams/valuation/trademanage/accountsreceivableinfoquery/conf/export_accountsreceivableinfoquery001.xml",listdto);
         return ExportUtil.export(
             this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                  + DateUtil.format(new Date())
                                  + SystemGlobalConstant.CLOSE_PARENTHESIS
                                  + SystemGlobalConstant.POINT
                                  + FileNameUtil.getFileExtName(outFile),
             outFile, true);
	}

}
