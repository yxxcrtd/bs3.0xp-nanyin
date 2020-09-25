package com.joyintech.tams.invest.entrust.etrinstruction.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrinstruction.dto.EtrInstructionDto;
import com.joyintech.tams.invest.entrust.etrinstruction.service.EtrInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PageId("EtrInstruction001")
@RequestMapping("/invest/entrust/etrinstruction/EtrInstruction001")
public class EtrInstruction001Controller extends BaseController {

    @Autowired EtrInstructionService instructionService;
    @Override public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.币种, DictCode.计划类资产一级分类,DictCode.计划类资产二级分类,DictCode.委托状态
            });
        return super.init(model);
    }



    /**
     *
     * 资产代码联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SuggestDto> getFinprodMarketIdList() {
        String finprodId = super.getRequest().getParameter("param");
        return instructionService.getFinprodMarketId(finprodId);
    }

    /**
     *
     * 融资人/管理人联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getRepFinancierList")
    public List<SuggestDto> getRepFinancierList() {
        String repFinancier = super.getRequest().getParameter("param");
        return instructionService.getRepFinancierList(repFinancier);
    }

    /**
     * 编辑前的校验
     * @param dto
     * @param editResult
     * @return
     */
    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(@RequestBody  @Validated EtrInstructionDto dto, BindingResult editResult) {
        AjaxResult wr = new AjaxResult();
        return wr;
    }

    /**
     *
     * 删除、撤销、关闭委托check
     * <功能详细描述>
     * @param dto etrInstructionDto
     * @param result BindingResult
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) EtrInstructionDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }


    /**
     * 撤回委托
     * <功能详细描述>
     * @param dto etrInstructionDto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody  EtrInstructionDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List< EtrInstructionDto> list = dto.getSelectedItem();
        for( EtrInstructionDto etrInstructionDto:list){
            if(instructionService.recallDelegate(etrInstructionDto.getEntrustId())){
                ajaxResult.setMessage(this.getMessage("ETR_EI_006"));
            }else{
                errorList.add(new ValidMessage(getMessage("委托代码:"+etrInstructionDto.getPortfolioId())+"委托撤销失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }
    /**
     * 关闭委托
     *
     * @param dto etrInstructionDto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrInstructionDto dto) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        List<EtrInstructionDto> list = dto.getSelectedItem();
        for(EtrInstructionDto   etrInstructionDto:list){
            if(instructionService.closeDelegate(etrInstructionDto.getEntrustId(), etrInstructionDto.getCloseWay())){
                ajaxResult.setMessage(this.getMessage("ETR_EI_007"));
            }else{
                errorList.add(new ValidMessage(getMessage("组合代码:"+etrInstructionDto.getPortfolioId())+"委托关闭失败"));
                ajaxResult.setErrorList(errorList);
            }
        }
        return ajaxResult;
    }


    /**
     *
     * 立项指令分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrInstructionDto> selectForPage(){
        Page<EtrInstructionDto> page = instructionService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 文件导出 <功能详细描述>
     *
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return instructionService.export(this.getPageId(),param);
    }

}
