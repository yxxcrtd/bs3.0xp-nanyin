/*
 * 文件名：TrdCommandDispatcher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commandreceive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.trade.investtrade.commandreceive.dao.TrdCommandReceive001Mapper;
import com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;
import com.joyintech.tams.common.base.dao.EtrDistributeMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrDistributeKey;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;


/**
 * 指令接收service
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年09月26日 
 * @see TrdCommandReceive001Service
 * @since 1.0
 */
@Service("backTrdCommandReceive001Service")
public class TrdCommandReceive001Service extends BaseService<TrdCommandReceive001Dto>  {
	
	/**
     * 手动书写mapper
     */
	@Autowired
	private TrdCommandReceive001Mapper trdCommandReceive001Mapper;
	/**
	 * 委托指令分发mapper
	 */
	@Autowired
	private EtrDistributeMapper etrDistributeMapper;
	/**
     * 投资委托委托组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoServiceImpl etrInvesInfo;
    /**
     * 标的类金融产品mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;
	
	/**
     * 已接收查询 <功能详细描述>
     * 
     * @param pageParam 查询信息
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
	public Page<TrdCommandReceive001Dto>  selectForPage(int page, int length,Map<String, Object> pageParam)
			throws Exception{
        Page<TrdCommandReceive001Dto> pageLimit =  super.queryForPage(page, length, () -> trdCommandReceive001Mapper.selectForPage(pageParam));
//        List<TrdCommandReceive001Dto> list=pageLimit.getRoot();
//        if(!list.isEmpty()){
//            //投管用字典转为平安字典
//            finprodTypeTg2PingAn(list);
//        }
        return pageLimit;
	}

    /**
	 * 未接收查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<TrdCommandReceive001Dto> otherSelectForPage(int page, int length,Map<String, Object> pageParam)
			throws Exception {
	    Page<TrdCommandReceive001Dto> pageLimit = super.queryForPage(page, length, () -> trdCommandReceive001Mapper.otherSelectForPage(pageParam));
//		List<TrdCommandReceive001Dto> list=pageLimit.getRoot();
//        if(!list.isEmpty()){
//            //投管用字典转为平安字典
//            finprodTypeTg2PingAn(list);
//        }
        return pageLimit;
	}
	
	/**
     * 指令已接受接收查询数目
     * @param map
     * @return
     */
    public List<TrdCommandReceive001Dto> SelectList(Map<String, Object> map){
        List<TrdCommandReceive001Dto> trdCommandReceive001Dtos = trdCommandReceive001Mapper.selectForPage(map);
        return trdCommandReceive001Dtos;
    }

    /**
     * 指令未接收查询数目
     * @param map
     * @return
     */
    public List<TrdCommandReceive001Dto> otherSelectList(Map<String, Object> map){
        List<TrdCommandReceive001Dto> trdCommandReceive001Dtos = new ArrayList<>();
        boolean isReceive = (Boolean)map.get("isReceive");
        if(isReceive){
            trdCommandReceive001Dtos =  trdCommandReceive001Mapper.selectForPage(map);
        }else{
            trdCommandReceive001Dtos = trdCommandReceive001Mapper.otherSelectForPage(map);
        }
        return trdCommandReceive001Dtos;
    }
	
	/**
	 * 市场代码联想框查询
	 * @param finprodMarketId
	 * @return
	 */
	public List<SuggestDto> getFinprodMarketIds(String finprodMarketId){
		List<SuggestDto> list = trdCommandReceive001Mapper.getFinprodMarketIds(finprodMarketId);
		List<SuggestDto> list1 = trdCommandReceive001Mapper.getCommonFinprodMarketIds(finprodMarketId);
		if(list1!=null && list1.size()>0) {
			for(SuggestDto dto : list1) {
				list.add(dto);
			}
		}
		return list;
	}
	
	/**
	 * 根据委托代码查询投资委托
	 * @param param
	 * @return
	 */
	public List<TrdCommandReceive001Dto> selectEtrInvest(String entrustId){
	    List<TrdCommandReceive001Dto> list = trdCommandReceive001Mapper.selectEtrInvest(entrustId);
	    if(!list.isEmpty()){
	      //投管用字典转为平安字典
            finprodTypeTg2PingAn(list);
        }
		return list;
	}
	
	/**
	 * 根据委托代码查出委托指令分发
	 * @param entrustIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<EtrDistribute> getDistributeTrader(Map<String,Object> param){
		List<EtrDistribute> list=new ArrayList<EtrDistribute>();
		EtrDistributeKey etrKey=new EtrDistributeKey();
		etrKey.setTrader((String) param.get("trader"));
		List<String> entrustIds=(List<String>)param.get("entrustIds");
		for(String entrustId:entrustIds){
			etrKey.setEntrustId(entrustId);
			list.add(etrDistributeMapper.selectByPrimaryKey(etrKey));
		}
		return list;
	}
   /**
    * 接收委托
    * <功能详细描述>
    * @param list
    * @return
    * @see
    * @since 1.0
    */
    public List<ValidMessage> receiveEntrust(Map<String,Object> param){
        List<EtrDistribute> list=getDistributeTrader(param);
        List<ValidMessage> errorList=new ArrayList<ValidMessage>();
        List<String> traderList=new ArrayList<String>();
        for(EtrDistribute etrDis:list){
            errorList.addAll(etrInvesInfo.checkDelegateOperation(etrDis.getEntrustId(), DictCodeListExt1.委托操作类型_接收.getValue(), traderList, DictCodeListExt1.委托关闭方式_人工.getValue()));
            etrDis.setUpdateUser(etrDis.getTrader());
        }
        //无错误时，进行接收操作
        if(errorList.size() == 0){
            etrInvesInfo.receptionDelegate(list);
        }
        return errorList;
    }
    /**
     * 退回委托
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> returnEntrust(Map<String,Object> param){
        List<EtrDistribute> list=getDistributeTrader(param);
        List<ValidMessage> errorList=new ArrayList<ValidMessage>();
        List<String> traderList=new ArrayList<String>();
        //委托组件退回校验
        for(EtrDistribute etrDis:list){
            errorList.addAll(etrInvesInfo.checkDelegateOperation(etrDis.getEntrustId(), DictCodeListExt1.委托操作类型_退回.getValue(), traderList, DictCodeListExt1.委托关闭方式_人工.getValue()));
            etrDis.setUpdateUser(etrDis.getTrader());
        }
        param.get("entrustStatus");
        //委托状态校验
//        for(String entrustSta:entrustStatus){
//            if(StringUtil.equals(DictCodeList.委托状态_已关闭.getValue(), entrustSta)){
//                errorList.add(new ValidMessage("",getMessage("TRD_W_0001",entrustSta,"拒绝") ));  
//            }
//        }
        //无错误时，进行退回操作
        if(errorList.size() == 0){
            etrInvesInfo.returnDelegate(list,param.get("entrustBackReason").toString());
        }
        return errorList;
    }
	
    /**
     * 将用户名转为真实姓名
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public User getRealNameByUserName(String userName){
        return trdCommandReceive001Mapper.getRealNameByUserName(userName);
    }
    /**
     * 将投管用字典转为平安字典
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdCommandReceive001Dto> finprodTypeTg2PingAn(List<TrdCommandReceive001Dto> list){
        for(TrdCommandReceive001Dto dto:list){
            String finType2=dto.getFinprodType2();
            if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_债券.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_基金.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_存款.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
                    || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_拆借.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                    || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_质押式回购.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                    || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_买断式回购.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
                dto.setFinprodType2(DictCodeList.平安资产类型_项目类资产.getValue());
            }
            else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
                FinProduct finProduct=finProductMapper.selectByPrimaryKey(dto.getFinprodId());
                if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_净值型.getValue())){
                    dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_净值型.getValue());
                }
                else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())){
                    dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
                }
                else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_货币型.getValue())){
                    dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_货币型.getValue());
                }
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_即期.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇即期.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇远期.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_掉期.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_利率互换.getValue());
            }
            else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
                dto.setFinprodType2(DictCodeListExt2.平安资产类型_股票.getValue());
            }
        }
        return list;
    }

    /**
     * 根据委托代码查出一级投标委托的信息
     * @param entrustIds
     * @return
     */
    public TrdCommandReceive001Dto getEtrBidInfo(String entrustId){
        return trdCommandReceive001Mapper.getEtrBidInfo(entrustId);
    }
    /** @MethodName: 投资指令和预录入
    * @Description: TODO
    * @Param: [page, length, pageParam]
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto>
    * @Author: wangxuelei
    * @Date: 2020/1/9 0009
    **/
    public Page<TrdCommandReceive001Dto> selectForPageFromCstp(int page, int length,Map<String, Object> pageParam)
            throws Exception {
        Page<TrdCommandReceive001Dto> pageLimit = super.queryForPage(page, length, () -> trdCommandReceive001Mapper.selectForPageFromCstp(pageParam));
        finprodType2PinAn(pageLimit.getRoot());
        return pageLimit;
    }
    
	/**
	 * 将金融产品类型转为平安数据字典
	 * <功能详细描述>
	 * @param list
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void finprodType2PinAn(List<TrdCommandReceive001Dto> list){
	    if(!list.isEmpty()){
            for(TrdCommandReceive001Dto dto : list){
            	if (dto.getTradeType().equals("01")) {
	                String finType2=dto.getFinprodType2();
	                if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_债券.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_基金.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_存款.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_拆借.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_质押式回购.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_买断式回购.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
	                    dto.setFinprodType2(DictCodeList.平安资产类型_项目类资产.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
	                    FinProduct finProduct=finProductMapper.selectByPrimaryKey(dto.getFinprodId());
	                    if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_净值型.getValue())){
	                        dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_净值型.getValue());
	                    }
	                    else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())){
	                        dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
	                    }else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_货币型.getValue())){
	                        dto.setFinprodType2(DictCodeList.平安资产类型_计划类资产_货币型.getValue());
	                    }
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_即期.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇即期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇远期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_掉期.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_债券借贷.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_债券借贷.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_利率互换.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_股票.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue())) {
	                    dto.setFinprodType2(DictCodeListExt2.平安资产类型_一级市场债.getValue());
	                }
            	}
            }    
        }
	}
}
