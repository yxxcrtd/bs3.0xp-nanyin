package com.joyintech.tams.valuation.system.batchstart.controller;

import java.util.Map;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.batch.batchprocess.dto.SysBatchGrpDto;
import com.joyintech.system.batch.batchprocess.dto.SysBatchPrdGrpDto001;
import com.joyintech.system.batch.batchprocess.service.SettingService;
import com.joyintech.system.batch.batchprocess.service.SysBatchProcess003Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.system.batchstart.dto.Product;
import com.joyintech.tams.valuation.system.batchstart.service.ValBatchProcess003Service;
import com.joyintech.tams.valuation.system.batchstart.service.ValBatchStart001Service;
import com.joyintech.tams.valuation.system.batchstart.service.ValBatchStart002Service;

@Controller
@PageId("ValBatchStart001")
@RequestMapping("/valuation/system/batchstart/ValBatchStart001")
public class ValBatchStart001Controller extends BaseController {

    // @Autowired(required = false)
    // private ProductService productService;

    @Autowired
    private ValBatchStart001Service sysBatchStart001Service;

    @Autowired
    private ValBatchStart002Service sysBatchStart002Service;

    @Autowired
    private SysBatchProcess003Service sysBatchProcess003Service;
    
    @Autowired
    private ValBatchProcess003Service valBatchProcess003Service;

    @Autowired
    private SettingService settingService;

    @Autowired
    private ValAccountHandle001Service valAccountHandle001Service;

    @Override
    public String init(Model model) {
        // 是否显示查看数据字典明细按钮
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
        return super.init(model);
    }

    @RequestMapping(value = "executeBatch")
    @ResponseBody
    public AjaxResult executeBatch(String selectedBatchGroup, String selectedProductGroup, String startDate, String endDate,
                                   String batchGroupType, String prodGroupType) {

        String errormsg = sysBatchStart001Service.checkPrebatchResult();
        if(StringUtil.isNotEmpty(errormsg)){
            AjaxResult ajax = new AjaxResult();
            ajax.setMessage(errormsg, MessageType.错误消息);
            return ajax;
        }

        sysBatchStart001Service.executeBatch();
        return new AjaxResult("批处理启动成功，可通过监控页面查看");
    }

    @RequestMapping(value = "openBatchGroupSelect")
    public String openBatchGroupSelect(String batchGroupType, Model model) {
        model.addAttribute("batchGroupType", batchGroupType);
        return "valuation/system/batchstart/batchGroupSelect";
    }

    @RequestMapping(value = "openProdGroupSelect")
    public String openProdGroupSelect(String prodGroupType, Model model) {
        model.addAttribute("prodGroupType", prodGroupType);
        return "valuation/system/batchstart/prodGroupSelect";
    }

    @RequestMapping(value = "findAllTask")
    @ResponseBody
    public Page<SysBatchTask> findAllTask()
        throws Exception {
        return sysBatchStart001Service.findAllTask(RequestUtil.getPageParam());
    }

    @RequestMapping(value = "findAllProd")
    @ResponseBody
    public Page<Product> findAllProd()
        throws Exception {
        /**
         * modify by wujie on 20190328 start return sysBatchStart002Service.findAllProd(RequestUtil.getPageParam());
         **/
        Map<String, Object> paramsMap = RequestUtil.getPageParam();
        paramsMap.put("acctSubjectTypeNo", DictCodeListExt1.金融产品类型_通道.getValue());
        return sysBatchStart002Service.findAllProd(paramsMap);
        /** modify by wujie on 20190328 end **/

    }

    @RequestMapping({"findAllPrdGroup"})
    @ResponseBody
    public Page<SysBatchPrdGrpDto001> findAllPrdGroup()
        throws Exception {
        return this.valBatchProcess003Service.findAllPrdGroup(RequestUtil.getPageParam());
    }

    @RequestMapping(value = "findAllBatch")
    @ResponseBody
    public Page<SysBatchGrpDto> findAllBatch()
        throws Exception {
        return settingService.findAllBatch(RequestUtil.getPageParam());
    }

    @RequestMapping("checkExecute")
    @ResponseBody
    public int checkExecute() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.checkExecute(map);
    }
}