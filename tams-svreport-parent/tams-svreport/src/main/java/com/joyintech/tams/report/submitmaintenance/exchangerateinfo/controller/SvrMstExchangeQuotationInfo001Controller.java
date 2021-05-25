
package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.controller;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service.SvrMstExchangeQuotationInfoMService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 汇率行情 
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see SvrMstExchangeQuotationInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrMstExchangeQuotationInfo001")
@RequestMapping("/submitmaintenance/exchangerateinfo/SvrMstExchangeQuotationInfo001")
public class SvrMstExchangeQuotationInfo001Controller extends BaseController {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 汇率行情service
     */
    @Autowired
    private SvrMstExchangeQuotationInfoMService mstExchangeQuotationInfoMService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.货币对);
        return super.init(model);
    }
    /**
     * 
     * 汇率行情查询
     * <功能详细描述>
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SvrMstExchangeQuotationInfoDto> selectAll()
        throws Exception {
        Page<SvrMstExchangeQuotationInfoDto> page = mstExchangeQuotationInfoMService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) SvrMstExchangeQuotationInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * 工作流发起删除操作
     * <功能详细描述>
     * @param dto 删除数据
     * @param br 报错信息
     * @return 删除结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(){
    	String msg = this.getMessage("REPORT_COMMON_I_0007");
    	String keyStr = super.getParam("deleteKeys");
    	if (StringUtil.isNotEmpty(keyStr)) {
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, keyStr);
            keys.forEach(key -> {
                List<String> keyList = StringUtil.splitToList("|",key);
                SvrMstExchangeQuotationInfoDto dto = new SvrMstExchangeQuotationInfoDto();
                dto.setExchargeCode(keyList.get(0));
                dto.setTradeDate(DateUtil.parse(keyList.get(1)));
                mstExchangeQuotationInfoMService.doComplete(dto,OperationType.DELETE);
            });
        }
        return new AjaxResult(msg);
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
        return mstExchangeQuotationInfoMService.checkConfirmMsgForImportData(fileName);
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
        return this.mstExchangeQuotationInfoMService.importData(fileName);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	if(param.containsKey("keyList")){
        	String keyList = param.get("keyList").toString();
			try {
				List<SvrMstExchangeQuotationInfoDto> investList = JsonUtils.json2List(keyList,SvrMstExchangeQuotationInfoDto.class);
				param.put("investList", investList);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			List<SvrMstExchangeQuotationInfoDto> dtoList = mstExchangeQuotationInfoMService.selectForExport(param);
			File outFile = mstExchangeQuotationInfoMService.makeFile(
					"/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf.xml", dtoList);
		         return ExportUtil.export(
		             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
		                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
		                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
		             outFile, true);
        }else{
        	return mstExchangeQuotationInfoMService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
        }
    }
}
