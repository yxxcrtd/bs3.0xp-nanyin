package com.joyintech.tams.valuation.trademanage.accountcurrentrate.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate001Dto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.service.AccountCurrentRate001Service;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.service.AccountCurrentRateService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 账户活期利率查询-controller
 * 
 * @author huwei
 * @version 1.0 2018年10月11日
 * @see AccountCurrentRate001Controller
 * @since 1.0
 */
@Controller
@PageId("AccountCurrentRate001")
@RequestMapping("/valuation/trademanage/accountcurrentrate/AccountCurrentRate001")
public class AccountCurrentRate001Controller extends BaseController {

    /**
     * accountCurrentRate001Service
     */
    @Autowired
    private AccountCurrentRate001Service accountCurrentRate001Service;

    /**
     * accountCurrentRateService
     */
    @Autowired
    private AccountCurrentRateService accountCurrentRateService;

    /**
     * 加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.资金账户类型);// 资金账户类型
        this.setSelectOptionToModel(model, DictCode.利率类型);
        /**
         * accountType A01:托管户 A02:通道账户 A03:待清算款 A04:券商保证金 A05:资金结算账户
         */
        return super.init(model);
    }

    /**
     * 查询按钮的查询方法
     * 
     * @return Page<AccountCurrentRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<AccountCurrentRate001Dto> findPage()
        throws Exception {
        return accountCurrentRate001Service.queryAccountCurrentRateList(
            RequestUtil.getPageParam());
    }

    /**
     * 下拉框获取：账户
     * 
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getAccount")
    @ResponseBody
    public List<SuggestDto> getAccount()
        throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        return accountCurrentRate001Service.getAccount(map);
    }

    /**
     * 导出信息excel
     * 
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<AccountCurrentRate001Dto> listdto;
        if (datas == null) {
            listdto = accountCurrentRate001Service.findAll(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, AccountCurrentRate001Dto.class);
        }
        File outFile = accountCurrentRate001Service.makeFile(
            "/com/joyintech/tams/valuation/trademanage/accountcurrentrate/conf/export_accountcurrentrate001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 删除前判断
     * 
     * @param dto AccountCurrentRate001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult check(@Valid @RequestBody AccountCurrentRate001Dto dto, BindingResult result)
        throws Exception {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult();
    }

    /**
     * 删除
     * 
     * @param dto AccountCurrentRate001Dto
     * @param result BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteList")
    public AjaxResult deleteList(@RequestBody AccountCurrentRate001Dto dto, BindingResult result)
        throws Exception {
        String msg = getMessage("COMMON_I_0001");
        List<AccountCurrentRate001Dto> listDto = dto.getListAccountCurrentRate001Dto();

        for (AccountCurrentRate001Dto item : listDto) {
            ProcessParameter<AccountCurrentRateDto> proParam = new ProcessParameter<>();
            WorkFlowParameter<AccountCurrentRateDto> params = new WorkFlowParameter<>();

            AccountCurrentRateDto dto1 = accountCurrentRate001Service.getAccountCurrentRateDto(
                item);

            // 业务代码
            params.setBusinessCode(dto1.getAcctNo() + dto1.getEffDate());
            // 业务类型
            params.setBusinessType(DictCodeList.共通业务类型_账户活期利率.getValue());
            params.setPageId("AccountCurrentRate002");
            // 元素id
            params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
            // 设置流程业务说明, entity即传入的dto
            params.setComment(entity -> WorkFlowUtil.concatComment("活期利率删除：", dto1.getAcctNo(), //
                dto1.getEffDate()));
            // 塞入流程参数
            proParam.setWorkFlowParameter(params);

            try {
                msg = accountCurrentRateService.start(proParam, dto1);
            }
            catch (Exception e) {
                getLogger().error("启动流程失败：", e);
                return new AjaxResult(e.getMessage());
            }

        }

        return new AjaxResult(msg);
    }

    /**
     * 删除
     * 
     * @param dto AccountCurrentRate001Dto
     * @return AjaxResult
     * @throws IOException IOException
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody AccountCurrentRate001Dto dto)
        throws IOException {

        AccountCurrentRateDto dto1 = accountCurrentRate001Service.getAccountCurrentRateDto(dto);
        ProcessParameter<AccountCurrentRateDto> proParam = new ProcessParameter<>();
        WorkFlowParameter<AccountCurrentRateDto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto1.getAcctNo() + dto1.getEffDate());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_账户活期利率.getValue());
        params.setPageId("AccountCurrentRate002");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("活期利率删除：", dto1.getAcctNo(), //
            dto1.getEffDate()));
        // 塞入流程参数
        proParam.setWorkFlowParameter(params);
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = accountCurrentRateService.start(proParam, dto1);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }

}
