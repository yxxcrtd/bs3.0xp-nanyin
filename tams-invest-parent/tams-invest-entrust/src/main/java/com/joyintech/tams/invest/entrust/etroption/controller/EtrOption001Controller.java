/*
 * 文件名：EtrOption001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etroption.controller;

import java.util.List;

//import com.joyintech.tams.invest.entrust.etroption.service.EtrOptionService;
import com.joyintech.tams.invest.entrust.etroption.dto.EtrOptionDto;
import com.joyintech.tams.invest.entrust.etroption.service.EtrOptionService;
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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.service.EtrLendSpecInfoService;

 /**
  * 〈一句话功能简述〉
  * 〈功能详细描述〉
  * @author likai
  * @version 1.0 2019年12月20日
  * @see EtrOption001Controller
  * @since 1.0
  */
@Controller
@PageId("EtrOption001")
@RequestMapping("/invest/entrust/etroption/EtrOption001")
public class EtrOption001Controller extends BaseController {
    
    /**
     * 期权委托service
     */
    @Autowired
    private EtrOptionService etrOptionService;

    /**
     * 初始化页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.结构类型);
        this.setSelectOptionToModel(model, DictCode.行权方式);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        return super.init(model);
    }
    
    /**
     * 
     *期权委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrOptionDto> selectForPage(){
        Page<EtrOptionDto> page = etrOptionService.selectForPage(
            RequestUtil.getPageParam());
        return page;
    }

    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getentrustId")
    public List<SuggestDto> getentrustId(String param) {
        return etrOptionService.getentrustId(param);
    }

     /**
      * 新建页面合约标的联想下拉框 <功能详细描述>
      *
      * @param param
      * @return
      * @see
      * @since 1.0
      */
     @ResponseBody
     @RequestMapping("getOptionTargetId")
     public List<SuggestDto> getOptionTargetId(String param) {
         return etrOptionService.getOptionTargetId(param);
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
        ValidateGroupDelete.class}) EtrOptionDto dto, BindingResult result) {
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
    public AjaxResult recallDelegate(@RequestBody EtrOptionDto dto) {
        List<EtrOptionDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrOptionDto entity : dtolist){
                etrOptionService.recallDelegate(entity);
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
    public AjaxResult closeDelegate(@RequestBody EtrOptionDto dto) {
        List<EtrOptionDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrOptionDto entity : dtolist){
                etrOptionService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
    }

//    /**
//     *
//     * 删除投资委托
//     * <功能详细描述>
//     * @param entrustId
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("delete")
//    public AjaxResult deleteDelegate(@RequestBody EtrLendSpecInfoDto dto) {
//        List<EtrLendSpecInfoDto> list = dto.getSelectedItem();
//        if(list!=null && list.size()>0){
//            for(EtrLendSpecInfoDto entity : list){
//                String entrustId = entity.getEntrustId();
//                etrLendSpecInfoService.deleteDelegate(entrustId);
//            }
//            return new AjaxResult("删除成功");
//        }
//        return new AjaxResult("删除失败");
//    }
//
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
        return etrOptionService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
