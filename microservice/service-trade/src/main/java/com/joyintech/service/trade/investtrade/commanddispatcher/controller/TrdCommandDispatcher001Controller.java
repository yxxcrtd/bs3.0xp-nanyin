/*
 * 文件名：TrdCommandDispatcher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commanddispatcher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.trade.investtrade.commanddispatcher.dto.PaEtrAutoDispenseTimeDto;
import com.joyintech.service.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;
import com.joyintech.service.trade.investtrade.commanddispatcher.service.TrdCommandDispatcher001Service;
import com.joyintech.tams.common.base.entity.PaEtrAutoDispenseTime;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * 指令分发页面
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月25日
 * @see TrdCommandDispatcher001Controller
 * @since 1.0
 */
@RestController("TrdCommandDispatcher001Ctr_dtx")
@RequestMapping("/TrdCommandDispatcher001")
public class TrdCommandDispatcher001Controller{

	/**
     * 指令分发微服务service注入
     */
	@Autowired
	private TrdCommandDispatcher001Service trdCommandDispatcher001Service;
    @Autowired
    private IPlatformDate iPlatformDate;
	
	
	 /**
     * 查询 <功能详细描述>
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
	@PostMapping("findPage/{page}/{length}")
	public Page<TrdCommandDispatcher001Dto> selectAllCommonChar(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
	    Page<TrdCommandDispatcher001Dto> pageLimit =trdCommandDispatcher001Service.selectForPage(page, length, param);
	    //将交易员和投资经理转为真实姓名
        List<TrdCommandDispatcher001Dto> list=pageLimit.getRoot();
        if(!list.isEmpty()){
          for(TrdCommandDispatcher001Dto dto:list){
              if(StringUtil.isNotEmpty(dto.getTrader())){
            	  String[] traders = dto.getTrader().split(",");
            	  StringBuffer buffer = new StringBuffer();
            	  StringBuffer userName = new StringBuffer();
            	  for (String trader : traders) {
            	      userName.append(trader + ",");
                      User user=trdCommandDispatcher001Service.getRealNameByUserName(trader);
                      if(StringUtil.isNotEmpty(user)){
                    	  buffer.append(user.getRealName() + ",");
                      }
            	  }
            	  if(buffer.toString().length() !=0){
            	      dto.setTrader(buffer.toString().substring(0, buffer.toString().length() - 1));
            	  }
            	  if(userName.toString().length() !=0){
            	      dto.setUserName(userName.toString().substring(0,userName.toString().length() - 1));
            	  }
              }
              if(StringUtil.isNotEmpty(dto.getInvManagerId())){
                  User user1=trdCommandDispatcher001Service.getRealNameByUserName(dto.getInvManagerId());
                  if(StringUtil.isNotEmpty(user1)){
                      dto.setInvManagerId(user1.getRealName()); 
                  }
              }
              if(StringUtil.isNotEmpty(dto.getBusiPs())){
                  //委托指令附属表中取出回购/拆借方向
                  if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
                         || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2()) ){
                      dto.setPs(dto.getBusiPs());
                  }
              }
              if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt2.平安资产类型_一级市场债.getValue())){
                  //债券一级投标交易方向的查询结果显示为空；
                  dto.setPs(null);
                  TrdCommandDispatcher001Dto  dto2=trdCommandDispatcher001Service.getEtrBidInfo(dto.getEntrustId());
                  if(StringUtil.isNotEmpty(dto2)){
                      //列表项中委托额度后增加字段 投标标位
                      dto.setBidPrice(dto2.getBidPrice());
                      // 委托额度显示的是投标委托的投标券面总额汇总；
                      dto.setEntrustAmt(dto2.getBidPrinAmt());
                      //设置投标日期
                      dto.setBussBidDate(DateUtil.format(dto2.getBidDate()));
                  }
              }
          }
        }    
        return pageLimit;
	}
	/**
	 * 委托代码联想文本框
	 * @param param
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/getEntIds")
    public List<SuggestDto> getEntrustId(@RequestBody Map<String,Object> param) throws Exception {
        return trdCommandDispatcher001Service.getEntrustId(param);
    }
    /**
     * 组合代码联想文本框
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getPortfolioIds")
    public List<SuggestDto> getPortfolioIds(@RequestBody Map<String,Object> param) throws Exception {
    	return trdCommandDispatcher001Service.getPortfolioIds(param);
    }
    /**
     * 市场代码联想文本框
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping("/getFinprodIds")
    public List<SuggestDto> getFinprodIds(String finprodId) throws Exception {
    	return trdCommandDispatcher001Service.getFinprodIds(finprodId);
    }
    /**
     * 投资经理联想框
     * <功能详细描述>
     * @param manager
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getManagers")
    public List<SelectOptionDto> getManagers(String manager) {
        return trdCommandDispatcher001Service.getManager(manager);
    }
    
    /**
     * 获取radio交易员组的查询方法
     * @param param param
     * @return List
     * @see
     * @since 1.0
     */
    @PostMapping("/getRadios")
    public List<TrdCommandDispatcher001Dto> getRadios(@RequestBody Map<String, Object> param) throws Exception{
        return trdCommandDispatcher001Service.getRadios(param);
    }
    /**
     * 获取联想文本框交易员的查询方法
     * @param param param
     * @return List
     * @see
     * @since 1.0
     */
    @PostMapping("/getUsers")
    public List<SelectOptionDto> getUsers(@RequestBody Map<String,Object> param) throws Exception{
        return trdCommandDispatcher001Service.getUsers(param);
    }
    
    /**
     * 得到所有有前台传过来的资产类型的所有用户
     */
    @PostMapping("/getAssetTypeUser")
    public List<TrdCommandDispatcher001Dto> getAssetTypeUser(@RequestBody Map<String, Object> param){
        return trdCommandDispatcher001Service.getAssetTypeUser(param);
    }
    
    /**
     * 删除并保存委托分发指令
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delAndAddEtrDistribute")
    public void delAndAddEtrDistribute(@RequestBody TrdCommandDispatcher001Dto dto){
        trdCommandDispatcher001Service.delAndAddEtrDistribute(dto);
    }
    /**
     * 对前台传入数据进行验证
     * @param dto 实体类
     * @return AjaxResult
     */
    @PostMapping("/distributeCheck")
    public List<ValidMessage> distributeCheck(@RequestBody TrdCommandDispatcher001Dto dto) {
        return trdCommandDispatcher001Service.distributeCheck(dto);
    }
    /**
     * 添加委托自动分发时间
     * @param record
     * @return
     * @throws Exception 
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/addAutoTime")
    public int insertAutoTime(@RequestBody PaEtrAutoDispenseTime record) throws Exception{
         return trdCommandDispatcher001Service.addAutoTime(record);
    }
    /**
     * 得到自动分发时间
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/getAutoTime")
    public List<TrdCommandDispatcher001Dto> getAutoTime(){
        return trdCommandDispatcher001Service.selectEtrAutoTime();
    }
    /**
     * 添加委托分发
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/addAutoEtrDistribute")
    public void addAutoEtrDistribute(@RequestBody TrdCommandDispatcher001Dto dto){
        trdCommandDispatcher001Service.addEtrDistribute(dto);
    }
    
    /**
     * 删除自动分发的日期
     * @return
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/deleteAutoTime")
    public int deleteEtrAutoTime(){
        try {
            trdCommandDispatcher001Service.deleteEtrAutoTime();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * 插入自动分发的日期
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertAutoTime")
    public int insertEtrAutoTime(@RequestBody Map<String, Object> map){
        try {
            PaEtrAutoDispenseTimeDto dto = new PaEtrAutoDispenseTimeDto();
            dto.setVdate(DateUtil.parse((String)map.get("vdate")));
            dto.setMdate(DateUtil.parse((String)map.get("mdate")));
            dto.setDistributeRule(map.get("distributeRule").toString());
            dto.setDistributeParameter(map.get("distributeParameter").toString());
            trdCommandDispatcher001Service.insertEtrAutoTime(dto);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     *
     * 根据投标日查询一投标债的交易人员的信息
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/getTraders")
    public List<TrdCommandDispatcher001Dto> getTraders(@RequestBody TrdCommandDispatcher001Dto dto){
        return trdCommandDispatcher001Service.getTraders(dto);
    }

    @GetMapping("/selectEtrAutoRuleAndDetail")
    public  List<TrdCommandDispatcher001Dto> selectEtrAutoRuleAndDetail(){
        return trdCommandDispatcher001Service.selectEtrAutoRuleAndDetail(iPlatformDate.getSysDate());
    }

    /**
     * 查询交易员指令分发权限配置列表
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/getTraderAuthList")
    public List<TrdCommandDispatcher001Dto> getTraderAuthList()
    {
        return trdCommandDispatcher001Service.getTraderAuthList();
    }

    /**
     * 删除交易员指令分发的权限
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/deleteTraderAuthList")
    public int deleteTraderAuthList()
    {
        return trdCommandDispatcher001Service.deleteTraderAuthList();
    }

    /**
     * 根据委托代码查询委托状态
     * add by hpp 20200710
     * @param map
     * @return
     */
    @PostMapping("/getEntrustStatus")
    public List<TrdCommandDispatcher001Dto> getEntrustStatus(@RequestBody Map<String, Object> map){
    	return trdCommandDispatcher001Service.getEntrustStatus(map);
    }
    
}
