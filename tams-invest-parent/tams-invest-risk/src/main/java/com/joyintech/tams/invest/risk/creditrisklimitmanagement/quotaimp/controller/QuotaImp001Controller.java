package com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.controller;

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
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.quotaimp.service.QuotaImpService;

/**
 * 额度导入 
 * 〈功能详细描述〉
 * @author wangjie1
 * @version 1.0 2020年2月28日
 * @see QuotaImp001Controller
 * @since 1.0
 */
@Controller
@PageId("QuotaImp001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/quotaimp/QuotaImp001")
public class QuotaImp001Controller extends BaseController {
	
    @Autowired
    private QuotaImpService quotaImpService;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.额度导入类型,
            DictCode.白名单状态
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
    public Page<QuotaImpDto> selectAll()
        throws Exception {
        return quotaImpService.selectForPage(
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
        return quotaImpService.checkConfirmMsgForImportData(fileName);
    }
    
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("/importFile")
    public List<ExcelValidDto> importData(String fileName,String impType) {
        return this.quotaImpService.importData(fileName,impType);
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
        return quotaImpService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
