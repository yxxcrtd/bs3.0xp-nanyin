/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.service.ComFundMarketInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangqidong
 * @version 1.0 2018年7月17日
 * @see ComFundMarketInfo001Controller
 * @since 1.0
 */
@Controller
@PageId("ComFundMarketInfo001")
@RequestMapping("/common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo001")
public class ComFundMarketInfo001Controller extends BaseController {

    /**
     * valFundMarketInfo001Service
     */
    @Autowired
    private ComFundMarketInfo001Service comFundMarketInfo001Service;

    /**
     * 重写init方法 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.基金类型);
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ComFundMarketInfo001dto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 加载表格
     * <功能详细描述>
     * 
     * @return List<ValFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ComFundMarketInfo001dto> tableLoad()
        throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
        return comFundMarketInfo001Service.findPage(params);
    }

    /**
     * 导出数据 <一句话功能简述> <功能详细描述>
     * 
     * @return List<ValFundMarketInfo001dto>
     * @throws Exception Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {

        String datas = this.getParam("datas");
        Map<String, Object> params = RequestUtil.getPageParam();
        List<ComFundMarketInfo001dto> listdto;

        if (datas == null) {
            listdto = comFundMarketInfo001Service.tableLoad(params);
        }
        else {
            listdto = JsonUtils.json2List(datas, ComFundMarketInfo001dto.class);
        }

        File outFile = comFundMarketInfo001Service.makeFile(
                "/com/joyintech/tams/common/basicdata/marketdata/fundmarketinfo/config/export_conf.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(DateUtil.platDateFromCache())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型 <一句话功能简述> <功能详细描述>
     * 
     * @return 日期区间
     * @throws ParseException ParseException
     */
    public Map<String, Object> getRequestMap()
        throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 估值日期区间
        String startDate = this.getParam("startDate");
        String endDate = this.getParam("endDate");
        if (StringUtil.isNotEmpty(startDate)) {
            paraMap.put("startDate", DateUtil.parseDate(startDate, "yyyy-MM-dd"));
        }
        if (StringUtil.isNotEmpty(endDate)) {
            paraMap.put("endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
        }

        return paraMap;
    }

    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
            ValidateGroupDelete.class}) ComFundMarketInfo001dto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ComFundMarketInfo001dto> processParameter = new ProcessParameter<ComFundMarketInfo001dto>();
        WorkFlowParameter<ComFundMarketInfo001dto> params = new WorkFlowParameter<ComFundMarketInfo001dto>();
        params.setPageId("ValFundMarketInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_每日基金行情信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：债券代码 + "(" + 债券代码 + ")" + "-" + "估值日" + "-" + "估值日"
        params.setComment(entity ->"基金代码(" + entity.getFundCode() + ")" + "-" + "估值日期(" + entity.getValueDate()+ ")"
                + "-" + "基金类型(" + entity.getFundTypeFmt() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = comFundMarketInfo001Service.start(processParameter,
                dto.getSelectedItem().toArray(
                        new ComFundMarketInfo001dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }
    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping("checkImportData")
    public AjaxResult checkImportData(String fileName) {
        return comFundMarketInfo001Service.checkImportData(fileName);
    }

    /**
     *
     * @param param
     * @return
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping("importFile")
    public AjaxResult importFile(@RequestBody Map<String, Object> param) throws IOException {
        return comFundMarketInfo001Service.importFile(param);
    }
}
