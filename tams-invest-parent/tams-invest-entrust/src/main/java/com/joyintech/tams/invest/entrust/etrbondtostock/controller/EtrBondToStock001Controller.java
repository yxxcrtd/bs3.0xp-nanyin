package com.joyintech.tams.invest.entrust.etrbondtostock.controller;

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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.tams.invest.entrust.etrbondtostock.service.EtrBondToStockService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author gaoepng
 * @version 1.0 2019年10月23日
 * @see EtrBondToStock001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrBondToStock001")
@RequestMapping("/invest/entrust/etrbondtostock/EtrBondToStock001")
public class EtrBondToStock001Controller extends BaseController{
	/**
     * 查询service
     */
    @Autowired
    private EtrBondToStockService service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.委托状态);
        setSelectOptionToModel(model, DictCode.可转换显示);
        setSelectOptionToModel(model, DictCode.正股价区间);
        //行情模式开关
        model.addAttribute("BTSRMS",CacheUtil.getSysParam("BOND_TO_STOCK_RATE_MODEL_SWITCH"));
        return super.init(model);
    }
    
    /**
     * 
     * 债转股委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrBondToStockDto> selectForPage(){
        Page<EtrBondToStockDto> page = service.selectForPage(
            RequestUtil.getPageParam());
        return page;
    } 
    
    /**
     * 
     * 持仓分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findOtherPage")
    public Page<EtrBondToStockDto> findOtherPage(){
        Page<EtrBondToStockDto> page = service.findOtherPage(
            RequestUtil.getPageParam());
        return page;
    }   
    
    /**
     * 
     * 获取转债代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodId")
    public Page<SelectOptionDto> getFinprodId(String param)
        throws Exception {
    	Page<SelectOptionDto> temp = service.getFinprodId(RequestUtil.getPageParam());
        return temp;
    }
    
    /**
     * 
     * 获取正股代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getShareId")
    public Page<SelectOptionDto> getShareId(String param)
            throws Exception {
        	Page<SelectOptionDto> temp = service.getShareId(RequestUtil.getPageParam());
            return temp;
        }
    
    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result EtrBondToStock
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) EtrBondToStockDto dto, BindingResult result) {
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
    public AjaxResult recallDelegate(@RequestBody EtrBondToStockDto dto) {
        List<EtrBondToStockDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrBondToStockDto entity : dtolist){
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
    public AjaxResult closeDelegate(@RequestBody EtrBondToStockDto dto) {
        List<EtrBondToStockDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && !dtolist.isEmpty()){
            for(EtrBondToStockDto entity : dtolist){
                service.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
    }
    
    
    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> searchParams = RequestUtil.getPageParam();
        String fileName = "债转股委托查询" + "(" + DateUtil.getCurrentDate() + ").xlsx";
        if (searchParams.get("isPosi") != null && Boolean.parseBoolean((String)searchParams.get("isPosi"))) {
            fileName = "持仓" + "(" + DateUtil.getCurrentDate() + ").xlsx";
        }
        return service.export(fileName, RequestUtil.getParameters(getRequest()));
    }
    
}
