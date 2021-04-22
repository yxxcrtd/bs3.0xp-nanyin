/*
 * 文件名：TrdCommandDispatcher001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.investtrade.commanddispatcher.service;

import java.util.*;
import java.util.stream.Collectors;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.tams.common.base.dao.AutoDispenseRightMapper;
import com.joyintech.tams.common.base.entity.AutoDispenseRight;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher004Dto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.entity.PaEtrAutoDispenseTime;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.investtrade.commanddispatcher.dto.TrdCommandDispatcher001Dto;

/**
 * 指令分发service
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年9月12日
 * @see TrdCommandDispatcher001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdCommandDispatcher001Service extends CommonExportService<TrdCommandDispatcher001Dto> {
	@Autowired
	private AutoDispenseRightMapper autoDispenseRightMapper;
	/**
	 * 查询 
	 * @param pageParam
	 * @return
	 */
	public Page<TrdCommandDispatcher001Dto> selectForPage(Map<String, Object> pageParam){
		pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
		pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
		pageParam.put("entrustUntread", DictCodeList.委托状态_已完成.getValue());
		pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
		pageParam.put("entrustReturn", DictCodeList.委托状态_已退回.getValue());
		String finprodType2Old = (String)pageParam.get("finprodType2");
		String entrustStatus = (String)pageParam.get("entrustStatus");
		if(StringUtil.isNotEmpty(pageParam.get("entrustStatus"))){
			List<String> entrustStatusList = StringUtil.splitToList(",",(String)pageParam.get("entrustStatus"));
			pageParam.put("entrustStatusList", entrustStatusList);
		}
		String assetType1 = (String)pageParam.get("assetType1");
		pageParam.put("assetType1", StringUtil.splitToList(GlobalConstant.COMMA, assetType1));
//		List<String> list1=new ArrayList<>();
//        List<String> list2=new ArrayList<>();
//        List<String> list3=new ArrayList<>();
		if (StringUtil.isNotEmpty(finprodType2Old) || StringUtil.isNotEmpty(pageParam.get("ps"))) {
		    List<String> finprodType2ListOld = StringUtil.splitToList(GlobalConstant.COMMA, finprodType2Old);
           /* for(String finprodType2 : finprodType2ListOld){
//              //为不同资产类型赋查询条件
                if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), finprodType2)){
                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                    list2.add(DictCodeList.收益类型_收益型.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(), finprodType2)){
                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                    list2.add(DictCodeList.收益类型_净值型.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(), finprodType2)){
                    list3.add(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                    list2.add(DictCodeList.收益类型_货币型.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_存款.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
                }
                else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_拆入.getValue());
                    list1.add(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
                }
                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
                }
                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
                }
                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
                }
                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_债券借贷.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue());
                    list1.add(DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue());
                }
                else if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
                }else if (StringUtil.equals(DictCodeListExt2.平安资产类型_股票.getValue(), finprodType2)){
                    list1.add(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
                }
            }*/
            //交易方向买入卖出
//            if( finprodType2ListOld.isEmpty() ){
//                if( StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_买入.getValue())
//                        ||StringUtil.equals((String)pageParam.get("ps") , DictCodeList.交易方向_卖出.getValue()) ){
//                	finprodType2ListOld.add(DictCodeList.平安资产类型_存款.getValue());
//                	finprodType2ListOld.add(DictCodeList.平安资产类型_债券.getValue());
//                	finprodType2ListOld.add(DictCodeList.平安资产类型_项目类资产.getValue());
//                }
//            }//finprodType2不为空时
//            else{
//                boolean flag=false;
//                for(String finprodType2 : finprodType2ListOld){
//                    if(StringUtil.equals( DictCodeList.平安资产类型_质押式回购.getValue(), finprodType2 )
//                        || StringUtil.equals( DictCodeList.平安资产类型_拆借.getValue(), finprodType2 ) ){
//                        flag=true;
//                    }
//                }
//                if(flag){
////                    pageParam.put("busiPs", (String)pageParam.get("ps"));
//                }
//            }
//          pageParam.put("profitType", list2);
          pageParam.put("finprodType2List", finprodType2ListOld);
//          pageParam.put("list3", list3);
        //交易方向为利率互换 转换
          if(StringUtil.isNotEmpty((String)pageParam.get("ps"))){
        	  if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
          		pageParam.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
          		pageParam.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
              }
              if( StringUtil.equals((String)pageParam.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
              	pageParam.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
              	pageParam.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
              }
              pageParam.put("PS_T01", DictCodeList.交易方向_买入.getValue());
          }
        }
		pageParam.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        pageParam.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
        pageParam.put("ASSET_TYPE_07", DictCodeList.平安资产类型_拆借.getValue());
        pageParam.put("ASSET_TYPE_08", DictCodeList.平安资产类型_质押式回购.getValue());
        pageParam.put("ASSET_TYPE_10", DictCodeList.平安资产类型_买断式回购.getValue());
        pageParam.put("ASSET_TYPE_12", DictCodeListExt2.平安资产类型_债券借贷.getValue());
        pageParam.put("ASSET_TYPE_16", DictCodeListExt2.平安资产类型_利率互换.getValue());
        pageParam.put("BACK_LEND_DIRECTION", DictCode.回购拆借方向.getValue());
        pageParam.put("IRS_TYPE", DictCode.互换方式.getValue());
        pageParam.put("PS", DictCode.交易方向.getValue());
		//未完成状态的对应
		if(StringUtil.isNotEmpty(entrustStatus) ){
		    if(StringUtil.equals("99", entrustStatus)){
		        pageParam.put("entrustStatus1", DictCodeList.委托状态_已接收.getValue());
		        pageParam.put("entrustStatus2", DictCodeList.委托状态_待接收.getValue());
		    }
		}
		//根据组合权限表添加检索权限
		if(StringUtil.isNotEmpty(SecurityUtil.getLoginUser())) {
		    pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
		    return restTemplate.postForPage(
		           ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandDispatcher001/findPage", pageParam,
		           TrdCommandDispatcher001Dto.class);
		} 
		else {
		    return new Page<TrdCommandDispatcher001Dto>();
		}

	}
	
	/**
	 * 委托代码的联想文本框的查询方法
	 * @param pageParam
	 * @return
	 */
    public List<SuggestDto> getEntrustId(Map<String, Object> pageParam) {
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustUntread", DictCodeList.委托状态_已退回.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
    	return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getEntIds",pageParam,SuggestDto.class);
    }
	
    /**
     *  组合代码的联想文本框的查询方法
     * @param portfolioId
     * @return
     */
	public List<SuggestDto> getPortfolioIds(String portfolioId) {
	    User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("param", portfolioId);
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getPortfolioIds", map ,SuggestDto.class);		
	}
	/**
	 * 投资经理联想框
	 * <功能详细描述>
	 * @param manager
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> getManagers(String manager) {
        return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdCommandDispatcher001/getManagers?manager=" +StringUtil.defaultString(manager),SelectOptionDto.class);       
    }
	
	
	/**
     * 资产代码的联想文本框的查询方法
     * @return List
     * @see
     * @since 1.0
     */
	public List<SuggestDto> getFinprodIds(String finprodId) {
		return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getFinprodIds?finprodId=" +finprodId,SuggestDto.class);				
	}
    
	/**
    * 导出 
    */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		// TODO Auto-generated method stub
		exportParam.config(
				"/com/joyintech/tams/invest/trade/investtrade/commanddispatcher/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
	}
	/**
	 * 
	 */
	@Override
	public Page<TrdCommandDispatcher001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		return selectForPage(selectParam);
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("entrustAmt", propName) || StringUtil.equals("finishAmt", propName)) {
            cell.setCellValue(value.toString());
        }
    }

	/**
	 * 获取radio交易员组的查询方法
	 * @return
	 */
	public List<TrdCommandDispatcher001Dto> getRadios() {
		Map<String, Object> pageParam=new HashMap<>();
		List<String> list=new ArrayList<>();
		list.add(DictCodeList.角色岗位_交易处长.getValue());
		list.add(DictCodeList.角色岗位_交易员.getValue());
		list.add(DictCodeList.角色岗位_交易员_资本境内_岗.getValue());
		list.add(DictCodeList.角色岗位_交易员_资本境外_岗.getValue());
		pageParam.put("rolePosList", list);
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getRadios",pageParam,TrdCommandDispatcher001Dto.class);
	}
	
	/**
	 * 获取联想文本框交易员的查询方法
	 * @param pageParam
	 * @return
	 */
	public List<SelectOptionDto> getUsers(Map<String, Object> pageParam) {
		String dealers = (String)pageParam.get("search");
		List<String> list=new ArrayList<>();
		list.add(DictCodeList.角色岗位_交易处长.getValue());
		list.add(DictCodeList.角色岗位_交易员.getValue());
		list.add(DictCodeList.角色岗位_交易员_资本境内_岗.getValue());
		list.add(DictCodeList.角色岗位_交易员_资本境外_岗.getValue());
		pageParam.put("rolePosList", list);
		pageParam.put("dealers", dealers);
		return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getUsers",pageParam,SelectOptionDto.class);
	}
	
	
	/**
	 * 自动分发的保存
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int addAutoTime(TrdCommandDispatcher001Dto dto) throws Exception{
		PaEtrAutoDispenseTime record=new PaEtrAutoDispenseTime();
		record.setVdate(dto.getVdate());
		record.setMdate(dto.getMdate());
		return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/addAutoTime",record,Integer.class);
	}
	
	
	/**
	 * 得到自动分发的日期
	 * @return
	 */
	public List<TrdCommandDispatcher001Dto> selectEtrAutoTime(){
		return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/getAutoTime",TrdCommandDispatcher001Dto.class);
	}
	
	/**
     * 删除自动分发的日期
     * @return
     */
    public int deleteEtrAutoTime(){
        return restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdCommandDispatcher001/deleteAutoTime",Integer.class);
    }
    
    /**
     * 插入自动分发的日期
     * @return
     */
    public int insertEtrAutoTime(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdCommandDispatcher001/insertAutoTime",map,Integer.class);
    }
	
	/**
	 * 委托组件的数据校验
	 * @return
	 */
	public List<ValidMessage> distributeCheck(TrdCommandDispatcher001Dto dto){
		return  restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    			+ "/TrdCommandDispatcher001/distributeCheck",dto,ValidMessage.class);
	}
	
	
	/**
	 * 判断用户是否有相应资产类型的权限
	 */
	public List<ValidMessage> getAssetTypeUser(Map<String, Object> pageParam){
	    List<ValidMessage> errorList=new ArrayList<ValidMessage>();
//		String otherFinprodType2 = (String)pageParam.get("finprodType2");
//		String userName = (String)pageParam.get("userName");
//		List<String> otherFinprodTypeList=StringUtil.splitToList("otherFinprodType2", otherFinprodType2);
//		//循环删除重复元素
//		for(int i  =   0 ; i < otherFinprodTypeList.size() - 1 ; i ++ )  {       
//			for(int j = otherFinprodTypeList.size() - 1 ; j > i; j -- )  {       
//				if(otherFinprodTypeList.get(j).equals(otherFinprodTypeList.get(i)))  {       
//					otherFinprodTypeList.remove(j);       
//				}        
//			}        
//		}    
//		List<String> userNamesFor=StringUtil.splitToList(GlobalConstant.COMMA, userName);
//		List<String> userNames=StringUtil.splitToList(GlobalConstant.COMMA, userName);
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("finprodType2List", otherFinprodTypeList);
//		List<TrdCommandDispatcher001Dto> dealers=restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
//    			+ "/TrdCommandDispatcher001/getAssetTypeUser",param,TrdCommandDispatcher001Dto.class);
//		
//		
//		//将根据资产类型查询到的用户和分发员比较判断
//		for(int i=0;i<userNamesFor.size();i++){
//			for(TrdCommandDispatcher001Dto dealer:dealers){	
//				if(dealer.getDealers().equals(userNamesFor.get(i).toString())){
//					if(dealer.getCount()>=otherFinprodTypeList.size()){
//						userNames.remove(userNames.get(i).toString());
//					}
//				}
//			}
//		}
//		//判断所选分发员是否都有权限
//		if(!userNames.isEmpty()){
//			StringBuffer sb = new StringBuffer();
//	        for(int i = 0; i < userNames.size(); i++) {
//	            if (sb.length() > 0) {
//	                sb.append(",");
//	            }
//	            sb.append(userNames.get(i));
//	        }
//	        errorList.add(new ValidMessage(getMessage("TRD_W_0008",sb.toString())));
//		}
		
		return errorList;
	}
	
	/**
	 * 删除和保存委托指令分发

	 */
	public void delAndAddEtrDistribute(TrdCommandDispatcher001Dto dto){
	    dto.setUpdateUser(LoginUserUtil.getUserName());
	    dto.setCreateUser(LoginUserUtil.getUserName());
		restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
	    	+ "/TrdCommandDispatcher001/delAndAddEtrDistribute", dto,Integer.class);
	}
	/**
	 * 自动分发的保存时check
	 * <功能详细描述>
	 * @param vdate
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<ValidMessage> addAutoTimeCheck(Date vdate){
	    List<ValidMessage> errorList=new ArrayList<ValidMessage>();
	    Date currentTime = DateUtil.platDateFromCache();
	    if(vdate.compareTo(currentTime) <0){
	        errorList.add(new ValidMessage("",getMessage("TRD_W_0006")));
	    }
	    return errorList;
	}
	/**
	 * 自动分发的保存操作
	 * <功能详细描述>
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void addAutoEtrDistribute(TrdCommandDispatcher001Dto dto){
		restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdCommandDispatcher001/addAutoEtrDistribute",dto,Integer.class);
	}
	/**
	 * 手工和自动分发的check
	 * <功能详细描述>
	 * @param value
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AjaxResult validEtr(TrdCommandDispatcher001Dto value) {
	    AjaxResult result=new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        TrdCommandDispatcher001Dto trdCommandDispatcher001Dto=(TrdCommandDispatcher001Dto)value;
        String entrustIds = trdCommandDispatcher001Dto.getEntrustId();
        if(StringUtil.isNotEmpty(entrustIds)){
        	Map<String, Object> pageParam = new HashMap<String, Object>();
        	pageParam.put("entrustIds", StringUtil.splitToList(GlobalConstant.COMMA, entrustIds));
        	List<TrdCommandDispatcher001Dto> list = restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
    				+ "/TrdCommandDispatcher001/getEntrustStatus",pageParam,TrdCommandDispatcher001Dto.class);
        	for(TrdCommandDispatcher001Dto dto:list){
        		String entrustStatu = dto.getEntrustStatus();
        		if(StringUtil.equals(entrustStatu, DictCodeList.委托状态_已接收.getValue()) || 						StringUtil.equals(entrustStatu,DictCodeList.委托状态_已关闭.getValue())
                        || StringUtil.equals(entrustStatu, DictCodeList.委托状态_已完成.getValue())){
                    errorList.add(new ValidMessage("",getMessage("TRD_W_0001",SysDataUtil.getNameFromDataDict(DictCode.委托状态, 								entrustStatu),"分发")));
                }
        	}
        }
        /*String entrustStatus=trdCommandDispatcher001Dto.getEntrustStatus();
        if(StringUtil.isNotEmpty(entrustStatus)){
            List<String> entrustStatusList=StringUtil.splitToList(GlobalConstant.COMMA, entrustStatus);
            for(String entrustStatu:entrustStatusList){
                if(StringUtil.equals(entrustStatu, DictCodeList.委托状态_已接收.getValue()) || StringUtil.equals(entrustStatu, DictCodeList.委托状态_已关闭.getValue())
                        || StringUtil.equals(entrustStatu, DictCodeList.委托状态_已完成.getValue())){
                    errorList.add(new ValidMessage("",getMessage("TRD_W_0001",SysDataUtil.getNameFromDataDict(DictCode.委托状态, entrustStatu),"分发")));
                }
            }
        }*/
        result.setErrorList(errorList);
        return result;
    }
	/**
	 * 根据委托代码查询一投标债的交易人员的信息
	 * @param
	 * @return
	 */
	public List<TrdCommandDispatcher001Dto> getTraders(TrdCommandDispatcher001Dto dto) {
		return	restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdCommandDispatcher001/getTraders",dto,TrdCommandDispatcher001Dto.class);

	}

	public List<TrdCommandDispatcher001Dto> selectEtrAutoRuleAndDetail(){
		return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdCommandDispatcher001/selectEtrAutoRuleAndDetail",TrdCommandDispatcher001Dto.class);
	}

	/**
	 * 查询交易员指令分发权限配置列表
	 * @param
	 * @return
	 */

	public List<TrdCommandDispatcher001Dto> getTraderAuthList(Map<String, Object> pageParam){
		return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdCommandDispatcher001/getTraderAuthList",TrdCommandDispatcher001Dto.class);
	}


	/**
	 * 指令分发权限分配check
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String authConfigCheck(List<TrdCommandDispatcher004Dto> dtoList){
        String returnMsg="";
		List<String> selectList = new ArrayList<String>();//存入所有交易员已选择的投资品种类
		List<String> allList = new ArrayList<String>();//存入所有的投资品种类
		if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
		{
			dtoList.forEach(item->{

				if(StringUtil.isNotEmpty(item.getFinprodType()))
				{
					List<String> itemList= Arrays.asList(item.getFinprodType().split(GlobalConstant.COMMA));
					itemList.forEach(ii->
					{
						if(!selectList.contains(ii))
						{
							selectList.add(ii);
						}
					});
				}
			});
		}
		List<? extends DataDictItem> dataDictItemlist = SysDataUtil.getDataDictItem(DictCode.平安资产类型.getValue());
		if(!StringUtil.isEmptyList(dataDictItemlist) && dataDictItemlist.size()>0)
		{
			dataDictItemlist.forEach(data->
					{
						allList.add(data.getItemCode());
					}
			);
		}
		//获取未被选择的投资品，然后分配到所有的交易员上面
		List<String> noSelectList = allList.stream().filter(item -> !selectList.contains(item)).collect(Collectors.toList());
         StringBuilder stringBuilder=new StringBuilder();
		if(!StringUtil.isEmptyList(noSelectList) &&noSelectList.size()>0){
			if(!StringUtil.isEmptyList(noSelectList)) {
				noSelectList.forEach(entity ->
				{
					stringBuilder.append(SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,entity)+"、");
				});
			}
		}
		if(StringUtil.isNotEmpty(stringBuilder.toString())) {
			returnMsg="[" + stringBuilder.toString().substring(0,stringBuilder.toString().length()-1) + "]品种权限未选，将默认所有交易员都有此权限";
		}
		return returnMsg;
	}
	/**
	 * 指令分发权限分配保存
	 * @return
	 * @see
	 * @since 1.0
	 */
	public boolean saveTraderAuthList(List<TrdCommandDispatcher004Dto> dtoList) {

		List<String> selectList = new ArrayList<String>();//存入所有交易员已选择的投资品种类
		List<String> allList = new ArrayList<String>();//存入所有的投资品种类
		if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
		{
			dtoList.forEach(item->{

				if(StringUtil.isNotEmpty(item.getFinprodType()))
				{
					List<String> itemList= Arrays.asList(item.getFinprodType().split(GlobalConstant.COMMA));
					itemList.forEach(ii->
					{
						if(!selectList.contains(ii))
						{
							selectList.add(ii);
						}
					});
				}
			});
		}
		List<? extends DataDictItem> dataDictItemlist = SysDataUtil.getDataDictItem(DictCode.平安资产类型.getValue());
		if(!StringUtil.isEmptyList(dataDictItemlist) && dataDictItemlist.size()>0)
		{
			dataDictItemlist.forEach(data->
					{
						allList.add(data.getItemCode());

					}
			);
		}
		//获取未被选择的投资品，然后分配到所有的交易员上面
		List<String> noSelectList = allList.stream().filter(item -> !selectList.contains(item)).collect(Collectors.toList());

		//新增交易员分配的权限【先删除后添加】
		restTemplate.getForObject(ServiceApplication.TRADE_SERVICE_NAME
				+ "/TrdCommandDispatcher001/deleteTraderAuthList",Integer.class);

		if(!StringUtil.isEmptyList(dtoList)&&dtoList.size()>0)
		{
			dtoList.forEach(item->{

				//添加原来选择的
				if(StringUtil.isNotEmpty(item.getFinprodType()))
				{
					List<String> itemList= Arrays.asList(item.getFinprodType().split(GlobalConstant.COMMA));
					itemList.forEach(ii->
					{
						AutoDispenseRight  autoDispenseRight=new AutoDispenseRight();
						autoDispenseRight.setUserId(item.getUserName());
						autoDispenseRight.setAssetType(ii);
						autoDispenseRightMapper.insert(autoDispenseRight);
					});
				}
               // 添加未分配的默认项
				if(!StringUtil.isEmptyList(noSelectList))
				{
					noSelectList.forEach(entity->
					{
						AutoDispenseRight  autoDispenseRightEntity=new AutoDispenseRight();
						autoDispenseRightEntity.setUserId(item.getUserName());
						autoDispenseRightEntity.setAssetType(entity);
						autoDispenseRightMapper.insert(autoDispenseRightEntity);
					});
				}

				/*//如果当前用户什么权限也没选择并且默认项列表也为空，默认给添加一条债券权限

				if(StringUtil.isEmpty(item.getFinprodType()) &&(StringUtil.isEmptyList(noSelectList)))
				{
					AutoDispenseRight  autoDispenseRightEntity=new AutoDispenseRight();
					autoDispenseRightEntity.setUserId(item.getUserName());
					autoDispenseRightEntity.setAssetType(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
					autoDispenseRightMapper.insert(autoDispenseRightEntity);
				}*/
			});
		}
		return  true;
	}
}
