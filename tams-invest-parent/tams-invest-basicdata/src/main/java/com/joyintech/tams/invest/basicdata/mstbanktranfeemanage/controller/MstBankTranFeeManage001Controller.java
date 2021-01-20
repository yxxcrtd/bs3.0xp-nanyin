/*
 * 文件名：MstBankTranFeeManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.controller;

import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
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
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.service.MstBankTranFeeManageService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

import java.io.File;
import java.util.List;

/**
 * 银行间交易费用管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年3月21日
 * @see MstBankTranFeeManage001Controller
 * @since 1.0
 */
@Controller
@PageId("MstBankTranFeeManage001")
@RequestMapping("/invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage001")
public class MstBankTranFeeManage001Controller extends BaseController {
    /**
     * mstBankTranFeeManageService
     */
    @Autowired
    private MstBankTranFeeManageService mstBankTranFeeManageService;
    /**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 下拉框初始化
        setSelectOptionToModel(model, BussinessDictCode.费用类型);
        setSelectOptionToModel(model, BussinessDictCode.登记托管机构);
        return super.init(model);
    }
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstBankTranFeeManageDto> selectAll()
        throws Exception {
        return mstBankTranFeeManageService.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
        ValidateGroupDelete.class}) MstBankTranFeeManageDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstBankTranFeeManageDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstBankTranFeeManageDto> processParameter = new ProcessParameter<MstBankTranFeeManageDto>();
        WorkFlowParameter<MstBankTranFeeManageDto> params = new WorkFlowParameter<MstBankTranFeeManageDto>();
        params.setPageId("MstBankTranFeeManage002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getBatchSeq());
        params.setBusinessType(BussinessDictCodeList.共同业务类型_银行间交易费用设置.getValue());
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：流水号 + "(" + 流水号 + ")" + "-" + "费用类型" + "-" + "费用类型"
        params.setComment(entity -> "批次号" + "(" + entity.getBatchSeq() + ")" + "-" + "费用类型" + "("
                                    + entity.getFeeTypeStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstBankTranFeeManageService.start(processParameter,
            dto.getSelectedItem().toArray(new MstBankTranFeeManageDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
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
        String datas = this.getParam("datas");
        List<MstBankTranFeeManageDto> param1;
        if (datas == null) {
            return mstBankTranFeeManageService.export(this.getPageId(),RequestUtil.getPageParam());
        }
        param1 = JsonUtils.json2List(datas, MstBankTranFeeManageDto.class);
        File outFile =  mstBankTranFeeManageService.makeFile(
                "/com/joyintech/tams/invest/basicdata/mstbanktranfeemanage/conf/export_conf.xml",
                param1);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);

    }
}
