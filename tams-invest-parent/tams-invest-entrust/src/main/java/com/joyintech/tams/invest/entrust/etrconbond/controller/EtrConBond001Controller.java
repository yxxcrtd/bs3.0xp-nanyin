package com.joyintech.tams.invest.entrust.etrconbond.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.entrust.etrconbond.dto.EtrConBondDto;
import com.joyintech.tams.invest.entrust.etrconbond.service.EtrConBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 可转债委托
 *
 * @author gejiaao
 * @version 1.0 2019年10月23日
 * @see EtrConBond001Controller
 * @since 1.0
 */

@Controller
@PageId("EtrConBond001")
@RequestMapping("/invest/entrust/etrconbond/EtrConBond001")
public class EtrConBond001Controller extends BaseController {
    
    /**
     * 可转债委托处理详细
     */
    @Autowired
    private EtrConBondService service;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易场所, DictCode.委托状态,
                DictCode.申购方式, DictCode.申购类型);
        return super.init(model);
    }
    
    /**
     * 
     * 分页查询债券一级投标委托信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrConBondDto> selectForPage(){
        return service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodId")
    public Page<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> params)
        throws Exception {
        return service.getFinprodId(params);
    }
    /**
     * 按钮点击时的check
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
        ValidateGroupDelete.class}) EtrConBondDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 撤回委托 <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrConBondDto dto) {
        List<EtrConBondDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrConBondDto entity : dtolist){
                service.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
    }
    /**
     * 关闭委托
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrConBondDto dto) {
        List<EtrConBondDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrConBondDto entity : dtolist){
                service.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
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
        return service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

}
