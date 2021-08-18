package com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto.RepDealCounterWhiteListDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.repdealcounterwhitelist.service.RepDealCounterWhiteListService;

/**
 * 质押回购交易对手白名单维护 
 * 〈功能详细描述〉
 * @author wangjie1
 * @version 1.0 2020年2月25日
 * @see RepDealCounterWhiteList001Controller
 * @since 1.0
 */
@Controller
@PageId("RepDealCounterWhiteList001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/repdealcounterwhitelist/RepDealCounterWhiteList001")
public class RepDealCounterWhiteList001Controller extends BaseController{
	
    @Autowired
    private RepDealCounterWhiteListService repDealCounterWhiteListService;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.回购对手类型
           };
    
    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }
	
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepDealCounterWhiteListDto> selectAll()
        throws Exception {
        return repDealCounterWhiteListService.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return repDealCounterWhiteListService.checkConfirmMsgForImportData(fileName);
    }
    
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName) {
        return this.repDealCounterWhiteListService.importData(fileName);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return repDealCounterWhiteListService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
}
