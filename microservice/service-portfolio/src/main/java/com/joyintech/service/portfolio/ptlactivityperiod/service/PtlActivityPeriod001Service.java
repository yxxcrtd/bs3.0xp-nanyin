/*
 * 文件名：PtlActivityPeriod001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlactivityperiod.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlactivityperiod.dao.PtlActivityPeriod001Mapper;
import com.joyintech.service.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.component.base.group.impl.BasePtlCashPositionInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.*;

/**
 * 活期结息
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Service
 * @since 1.0
 */
@Service("backPtlActivityPeriod001Service")
public class PtlActivityPeriod001Service extends BaseService<PtlActivityPeriod001Dto>{
    /**
     * 注入活期结息Mapper
     */
    @Autowired
    private PtlActivityPeriod001Mapper ptlActivityPeriod001Mapper;
    
    /**
     * 注入业务主键生成工具类
     */
    @Autowired(required =false)
    public BaseGetBusinessId baseGetBussinessId;
    

    /**
     * 分页查询
     * @param page 页码
     * @param length 每页的长度
     * @param map 请求参数
     * @return 分页产讯结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlActivityPeriod001Dto> selectForPage(int page,int length,Map<String,Object> map) throws Exception{
        map.put("BUSI_TYPE", DictCodeList.共通业务类型_活期结息.getValue());
        map.put("TRADE_FLOW_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        map.put("TRADE_FLOW_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
        map.put("IS_CANCEL_N", DictCodeList.是否_否.getValue());
        Page<PtlActivityPeriod001Dto> pages = super.queryForPage(page, length,() -> ptlActivityPeriod001Mapper.selectForPage(map));
        return pages;
    }
    /**
     * 下拉框实体类列表
     * @param map 下拉框实体类列表
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String,Object> map,int page, int length){
       map.put("PORT_TYPE", DictCodeListExt1.组合类型_投资类组合.getValue());
        Page<SelectOptionDto> dto =  this.queryForPageForSelectReturn(page,length,()->ptlActivityPeriod001Mapper.getPortfolioId(map));
        return dto;
    }
    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCounterAcct(Map<String,Object> map,int page, int length){
        return this.queryForPageForSelectReturn(page,length,()->ptlActivityPeriod001Mapper.getCounterAcct(map));
    }
    /**
     * 获取组合代码
     * @param map 联想条件
     * @return 联想实体类列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioIdSug(Map<String,Object> map){
        map.put("PORT_TYPE", DictCodeListExt1.组合类型_投资类组合.getValue());
        List<SuggestDto> dto = ptlActivityPeriod001Mapper.getPortfolioIdSug(map);
        return dto;
    }
    /**
     * 获取交易编码
     * @param map 模糊查询参数
     * @return 联想文本框列表
     * @see
     * @since 1.0
     */
    
    public List<SuggestDto> getBusiNo(Map<String,Object> map){
        map.put("busiType", DictCodeList.共通业务类型_活期结息.getValue());
        map.put("yesOrNo_Y", DictCodeList.是否_是.getValue());
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        return ptlActivityPeriod001Mapper.getBusiNo(map);
    }
    
    /**
     * 获取账号下拉框
     * @param map 请求参数
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiAcctNo(Map<String,Object> map,int page,int length){
        return this.queryForPageForSelectReturn(page,length,()->ptlActivityPeriod001Mapper.getBusiAcctNo(map));
    }
    
    public Page<SelectOptionDto> getBankDep(Map<String,Object> map,int page,int length){
        return this.queryForPageForSelectReturn(page,length,()->ptlActivityPeriod001Mapper.getBankDep(map)); 
    }
    /**
     * 获取组合名称
     * @param map 请求参数
     * @return 组合名称
     * @see
     * @since 1.0
     */
    public String getportFolioName(Map<String,Object> map){
        return ptlActivityPeriod001Mapper.getportFolioName(map);
    }
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号
     * @see
     * @since 1.0
     */
    public String getAcctNo(Map<String,Object> map){
        return ptlActivityPeriod001Mapper.getAcctNo(map);
    }
    /**
     * 获取账号名称
     * @param map 请求参数
     * @return 账号名称
     * @see
     * @since 1.0
     */
    public String getAcctName(Map<String,Object> map){
        return ptlActivityPeriod001Mapper.getAcctName(map);
    }
    /**
     * 获取上次收息日
     * @param map 账号
     * @return 日期字符串
     * @see
     * @since 1.0
     */
    public String getVDate(Map<String,Object> map){
        map.put("TRADE_STATUS",DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_OR_NO_N",DictCodeList.是否_否.getValue());
        return ptlActivityPeriod001Mapper.getVDate(map);
    }
    
    /**
     * 获取划款信息
     * @param tradeId 交易编号
     * @return 划款实体类
     * @see
     * @since 1.0
     */
    public List<BussTrdTransferOrderDto> selectBussTrdTransferOrder(Map<String,Object> map){
        return ptlActivityPeriod001Mapper.selectBussTrdTransferOrder(map);
    }
    
    /**
     * 划款金额默认为理论计提金额
     * @param paramMap 请求参数集合
     * @return 理论计提金额
     * @see
     * @since 1.0
     */
    public BigDecimal initTradeAmt(Map<String,Object> paramMap) {
        String portfolioId = (String) paramMap.get("portfolioId");
        String busiAcctNo = (String) paramMap.get("busiAcctNo");
        String mdate = (String) paramMap.get("mdate");
        
        if (StringUtil.isBlank(portfolioId)
            || StringUtil.isBlank(busiAcctNo)
            || StringUtil.isBlank(mdate)) {
            return null;
        }
        
        Date lastDay = DateUtil.addDay(DateUtil.parse(mdate), -1);
        Map<String, Object> selectParamMap = new HashMap<String, Object>();
        selectParamMap.put("portfolioId", portfolioId);
        selectParamMap.put("busiAcctNo", busiAcctNo);
        selectParamMap.put("lastDay", lastDay);
        selectParamMap.put("mdate", mdate);
        selectParamMap.put("BUSI_TYPE", DictCodeList.共通业务类型_活期结息.getValue());
        selectParamMap.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        //增加查询参数 是否撤销
        selectParamMap.put("IS_CANCEL_N", DictCodeList.是否_否.getValue());
        BigDecimal initTradeAmt = ptlActivityPeriod001Mapper.initTradeAmt(selectParamMap) ;
        return initTradeAmt == null ? BigDecimal.ZERO:initTradeAmt;
    }
    
   /**
    * 查询明细信息
    * <一句话功能简述>
    * <功能详细描述>
    * @param map
    * @return
    * @see
    * @since 1.0
    */
    public List<PtlActivityPeriod001Dto> findDetailsPage(Map<String,Object> map) {

        map.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("BUSI_TYPE_PTL03", DictCodeList.共通业务类型_活期结息.getValue());
        map.put("FINPROD_TYPE2_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("FINPROD_TYPE2_F21", DictCodeListExt1.金融产品类型_投管用_资金账户.getValue());
        return ptlActivityPeriod001Mapper.findDetailsPage(map); 
 
    }
    
    public List<PtlActivityPeriod001Dto> findDetailsByBusiNo(Map<String,Object> map) {
        
        map.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("BUSI_TYPE_PTL03", DictCodeList.共通业务类型_活期结息.getValue());
        map.put("FINPROD_TYPE2_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("FINPROD_TYPE2_F21", DictCodeListExt1.金融产品类型_投管用_资金账户.getValue());
        List<PtlActivityPeriod001Dto> list = ptlActivityPeriod001Mapper.findDetailsByBusiNo(map);
        
        return list;
    }
    
    /**
     * 子组合明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlActivityPeriod001Dto> findChildrenPortfolioDetails(Map<String,Object> map) {
        return ptlActivityPeriod001Mapper.findChildrenPortfolioDetails(map);
        
    }
    
    /**
     * 获取报送登记编号
     * @param map 组合代码（市场代码）
     * @return 登记编号
     * @see
     * @since 1.0
     */
    public List<String> getProdRegistCode(Map<String,Object> map) {
        return ptlActivityPeriod001Mapper.getProdRegistCode(map);
    }
    
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号
     * @see
     * @since 1.0
     */
    public String getRepAcctNo(Map<String,Object> map) {
        return ptlActivityPeriod001Mapper.getRepAcctNo(map);
    }
}
