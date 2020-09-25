package com.joyintech.tams.invest.entrust.etrbondfirstlbidding.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.service.EtrBondFirstBiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 债券一级投标委托
 *
 * @author gejiaao
 * @version 1.0 2019年7月4日
 * @see EtrBondFirstLBidding001Controller
 * @since 1.0
 */

@Controller
@PageId("EtrBondFirstLBidding001")
@RequestMapping("/invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding001")
public class EtrBondFirstLBidding001Controller extends BaseController {
    
    /**
     * 债券一级投标委托处理详细
     */
    @Autowired
    private EtrBondFirstBiddingService service;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, BussinessDictCode.中标状态);
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
    public Page<EtrBondFirstLBiddingDto> selectForPage(){
        return service.selectForPage(RequestUtil.getPageParam());
    }
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getentrustId")
    public List<SuggestDto> getentrustId(String param)
        throws Exception {
        return service.getentrustId(param);
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
    @RequestMapping("/getfinprodId")
    public List<SuggestDto> getfinprodId(String param)
        throws Exception {
        return service.getfinprodId(param);
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
        ValidateGroupDelete.class}) EtrBondFirstLBiddingDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrBondFirstLBiddingDto dto) {
        List<EtrBondFirstLBiddingDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrBondFirstLBiddingDto entity : dtolist){
                service.recallDelegate(entity);
            }
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrBondFirstLBiddingDto dto) {
        List<EtrBondFirstLBiddingDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrBondFirstLBiddingDto entity : dtolist){
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
