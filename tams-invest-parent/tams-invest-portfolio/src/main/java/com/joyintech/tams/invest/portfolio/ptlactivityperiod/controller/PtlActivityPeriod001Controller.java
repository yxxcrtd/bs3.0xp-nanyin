/*
 * 文件名：PtlActivityPeriod001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlactivityperiod.controller;

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
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.service.PtlActivityPeriod001Service;
import com.joyintech.tams.invest.portfolio.ptlactivityperiod.service.PtlActivityPeriod002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 活期结息查询 
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlActivityPeriod001")
@RequestMapping("/invest/portfolio/ptlactivityperiod/PtlActivityPeriod001")
public class PtlActivityPeriod001Controller extends BaseController{
    /**
     * 注入火气结息001Service
     */
    @Autowired
    private PtlActivityPeriod001Service ptlActivityPeriod001Service;
    
    /**
     * 注入活期结息Service
     */
    @Autowired
    public PtlActivityPeriod002Service ptlActivityPeriod002Service;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.资金账户类型,DictCode.交易流程状态);
        return super.init(model);
    }
   /**
   * 分页查询
   * @return Page<PtlActivityPeriod001Dto>泛型的page实体类
   * @throws Exception 异常
   * @see
   * @since 1.0
   */
  @ResponseBody
  @RequestMapping("findPage")
  public Page<PtlActivityPeriod001Dto> findPage()
      throws Exception {
      return ptlActivityPeriod001Service.selectForPage(RequestUtil.getParameters(getRequest()));
  }
  /**
   * 获取账号联想文本框
   * @return 账号集合
   * @see
   * @since 1.0
   */
  @ResponseBody
  @RequestMapping("getBusiNo")
  public List<SuggestDto> getBusiNo(){
      Map<String,Object> map = RequestUtil.getPageParam();
      return ptlActivityPeriod001Service.getBusiNo(map);
  }
  /**
   * 组合代码下拉框
   * @return 请求参数
   * @see
   * @since 1.0
   */
  @ResponseBody
  @RequestMapping("getPortfolioId")
  public Page<SelectOptionDto> getPortfolioId(){
      Map<String,Object> map = RequestUtil.getPageParam();
      if(map.get("initValue") == null || StringUtil.equals((String)map.get("iniValue"), "")){
          map.put("userId",SecurityUtil.getLoginUser().getUserName());
      }
      return ptlActivityPeriod001Service.getPortfolioId(map);
  }
  /**
   * 组合代码联想文本框
   * @return 请求参数
   * @see
   * @since 1.0
   */
  @ResponseBody
  @RequestMapping("getPortfolioIdSug")
  public List<SuggestDto> getPortfolidIdSug(){
      Map<String,Object> map = RequestUtil.getPageParam();
      if(map.get("initValue") == null || StringUtil.isEmpty((String)map.get("initValue"))){
          map.put("userId",SecurityUtil.getLoginUser().getUserName());
      }
      return ptlActivityPeriod001Service.getPortfolioIdSug(map);
  }
  
  /**
   * 获取账号
   * @return 下拉框实体类列表
   * @see
   * @since 1.0
   */
  @ResponseBody
  @RequestMapping("getBusiAcctNo")
  public Page<SelectOptionDto> getBusiAcctNo(){
      Map<String,Object> map = RequestUtil.getPageParam();
      Page<SelectOptionDto> list =  ptlActivityPeriod001Service.getBusiAcctNo(map);
      return list;
  }
  
  /**
   * 删除按钮点击时的check
   * @param dto 验证的实体类
   * @param result 验证的结果
   * @return 错误信息
   */
  @ResponseBody
  @RequestMapping("check")
  public AjaxResult check(@RequestBody @Validated({
      ValidateGroupDelete.class}) PtlActivityPeriod001Dto dto, BindingResult result) {
      return commonCheck(result, dto.getOperateType());
  }
  /**
   * 删除操作
   * @param dto 验证的实体类
   * @param br 错误结果
   * @return 错误信息
   * @throws Exception 异常
   */
  @ResponseBody
  @RequestMapping("delete")
  public AjaxResult delete(@RequestBody @Validated({
      ValidateGroupDelete.class}) PtlActivityPeriod001Dto dto, BindingResult br)
      throws Exception {
      if (br.hasErrors()) {
          return new AjaxResult(br);
      }
      ProcessParameter<PtlActivityPeriod001Dto> processParameter = new ProcessParameter<PtlActivityPeriod001Dto>();
      WorkFlowParameter<PtlActivityPeriod001Dto> params = new WorkFlowParameter<PtlActivityPeriod001Dto>();
      params.setPageId("PtlActivityPeriod002");
      params.setElementId(dto.getElementId());
      params.setBusinessCode(item -> item.getBusiNo());
      params.setBusinessType(DictCodeList.共通业务类型_活期结息.getValue());
   // 设置流程业务说明, 账户名称 +"(" + 账户名称 + ")"+"-"+"账号"+"（"+"账号"+")"+"-"+"币种"+"("+"币种"+")"
      params.setComment(
          entity -> this.getMessage("PtlActivityPeriod001.acctName1") + "("
                    + entity.getAcctName() + ")" + "-"
                    + this.getMessage("PtlActivityPeriod001.acctNo") + "("
                    + entity.getBusiAcctNo() + ")" + "-"
                    + this.getMessage("PtlActivityPeriod001.ccy") + "("
                    + entity.getCcyStr() + ")" );
      processParameter.setWorkFlowParameter(params);
      String msg = ptlActivityPeriod002Service.start(processParameter,dto);
      return new AjaxResult(msg);
  }
  
  /**
   * 导出信息excel
   * @return excel
   * @throws Exception 异常
   * @see
   * @since 1.0
   */

  @RequestMapping("export")
  public ResponseEntity<byte[]> export()
      throws Exception {
      return ptlActivityPeriod001Service.export(this.getPageId(),
          RequestUtil.getParameters(getRequest()));

  }
}
