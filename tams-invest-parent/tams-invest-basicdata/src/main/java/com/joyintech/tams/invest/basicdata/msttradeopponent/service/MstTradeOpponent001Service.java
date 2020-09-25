/*
 * 文件名：MstTradeOpponent001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.msttradeopponent.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.basicdata.msttradeopponent.dto.MstTradeOpponentDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 交易对手管理Service
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see MstTradeOpponent001Service
 * @since 1.0
 */
@Service
public class MstTradeOpponent001Service extends CommonExportService<MstTradeOpponentDto> implements BaseProcessService<MstTradeOpponentDto>{
    
    /**
     * 基础Mapper代理Service
     */
    /**
     * 获取交易对手类型
     * @param map 查询条件
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterType(Map<String, Object> map){
//        return mstTradeOpponent001Mapper.queryCounterType(map);
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCounterType",
            map, SelectOptionDto.class);
    }
    /**
     * 分页查询
     * @param pageParam 分页查询条件
     * @return page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstTradeOpponentDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/selectForPage",
            pageParam, MstTradeOpponentDto.class);
    }
    /**
     * 获取账号信息
     * @param param 联想文字
     * @see
     * @since 1.0
     * @return 账号实体类
     */
    public List<SelectOptionDto> getAccount(Map<String,Object> param){
        List<SelectOptionDto> selDto = /*mstTradeOpponent001Mapper.getAccount();*/
            restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getAccount",param, SelectOptionDto.class);
//        for(SelectOptionDto dto : selDto){
//            dto.setId(dto.getId()+"_"+dto.getObject());
//        }
        return selDto;
    }
    /**
     * 获取费用品种
     * @param map 请求集合
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFeeId(Map<String,Object> map){
        map.put("applyType", DictCodeList.关联范围_通道.getValue());
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getFeeId",map, SelectOptionDto.class);
    }
    /**
     * 获取账号类型
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAccountType(){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getAccountType", SelectOptionDto.class);
    }
    /**
     * 获取账号类型信息
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAccountTypeInfo(){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getAccountTypeInfo", SelectOptionDto.class);
    }
    /**
     * 获取所属总行下拉框
     * @param param 请求参数
     * @return 所属总行下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getOfficeId(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getOfficeId?param="+param, SelectOptionDto.class);
    }
    
    /**
     * 获取市場機構
     * @param param 请求参数
     * @return 市場機構下拉框集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getMarketId(Map<String, Object> map){
        return restTemplate.postForPage(ServiceApplication.BASICDATA_SERVICE_NAME + 
        		"/MstTradeOpponent/getMarketId",map, SelectOptionDto.class);
    }
    /**
     * 获取管理人
     * @param param 请求参数
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCustomerId(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCustomerId?param="+param, SelectOptionDto.class);
    }
    /**
     * 获取管理人
     * @param param 请求参数
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCustomerIdMarket(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCustomerIdMarket?param="+param, SelectOptionDto.class);
    }
    /**
     * 获取交易对手联想文本框
     * @param param 请求参数
     * @return 交易对手联想文本框集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCounterName(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCounterName?param="+param, SuggestDto.class);
    }
    /**
     * 交易对手下拉框集合
     * @param param 请求参数
     * @return 交易对手下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterId(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCounterId?param="+param, SelectOptionDto.class);
    }
    /**
     * 获取托管人
     * @param param 请求参数
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCustomerId1(String param){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCustomerId1?param="+param, SelectOptionDto.class);
    }
    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/msttradeopponent/conf/export_conf.xml").dataSource(
                    DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstTradeOpponentDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 工作流
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MstTradeOpponentDto entity, OperationType type) {
//        if (OperationType.DELETE.equals(type)) {
//            mstTradeOpponent001Mapper.deleteCounterAcct(dto);
//            mstTradeOpponent001Mapper.deleteMstCounterParty(dto);
//            if("03".equals(dto.getCounterType())){
//                mstTradeOpponent001Mapper.deleteFinCashCalcRule(dto);
//                mstTradeOpponent001Mapper.deleteFinCashInfo(dto);
//                mstTradeOpponent001Mapper.deleteFinCashPlan(dto);
//                mstTradeOpponent001Mapper.deleteFinProduct(dto);
//                mstTradeOpponent001Mapper.deleteFinProductType(dto);
//                mstTradeOpponent001Mapper.deleteRepAssetMngPlan(dto);
//            }
//            
//        }
//        String id = IDGenerator.generate();
//        entity.setFinprodId(id);
        entity.setLinkId(entity.getCustomerId());
        if (DictCodeListExt1.交易对手类型_通道计划.getValue().equals(entity.getCounterType())) {
            entity.setLinkId(entity.getFinprodId());
        }
        entity.setBranch(Long.valueOf(CacheUtil.getSysParam("noBranchNoValue")));
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/doComplete001", entity, Integer.class);
    }
    /**
     * 统计
     * <功能详细描述>
     * @param counterId id
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int selectCounter(String counterId) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/selectCounter", 
            counterId,Integer.class);
    }
    /**
     * 统计金融产品
     * <功能详细描述>
     * @param dto 参数
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int selectFinProduct(MstTradeOpponentDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/selectFinProduct", 
            dto,Integer.class);
    }
    /**
     * 验证交易对手账号是否唯一
     *  @param counterId 账号代码
     * @return 数量
     * @see
     * @since 1.0
     */
    public int checkCounterId(String counterId){
        return restTemplate.getForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/checkCounterId?counterId="+counterId, 
            Integer.class);
    }
    /**
     * 检查交易对手名字是否重复
     */
    public int checkcounterName(String counterId,String counterName){
        return restTemplate.getForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/checkcounterName?counterId="+counterId+"&counterName="+counterName,
            Integer.class);
    }
    
    /**
     * 验证交易对手关联代码是否唯一
     * @param linkId 账号名称
     * @return 数量
     * @see
     * @since 1.0
     */
    public int checkCounterName(String counterName){
        return restTemplate.getForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/checkCounterName1?counterName="+counterName,
            Integer.class);
    }

    /**
     * {@linkplain}
     */
    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 获取费用信息
     * @param feeId 费用代码 
     * @return 费用信息
     * @see
     * @since 1.0
     */
    public Map<String,Object> getBasis(String feeId){
        Map<String,Object> map = new HashMap<>();
        if(bussCommonMapperDelegateService != null){
            MstFeeinfo mstFeeinfo = bussCommonMapperDelegateService.selectBySinglePrimaryKey(MstFeeinfoMapper.class, feeId, MstFeeinfo.class);
            if(mstFeeinfo == null){
                mstFeeinfo = restTemplate.getForObject(
                    ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getFeeInfo?feeId="+feeId, 
                    MstFeeinfo.class);
            }
            if(mstFeeinfo != null){
                map.put("basis", mstFeeinfo.getBasis());
                map.put("basisStr", SysDataUtil.getNameFromDataDict(DictCode.计息基础,mstFeeinfo.getBasis()));
                map.put("basicBill", mstFeeinfo.getBasicBill());
                map.put("basicBillStr", SysDataUtil.getNameFromDataDict(DictCode.计费基数_费用品种,mstFeeinfo.getBasicBill()));
                map.put("feeType", mstFeeinfo.getFeeType());
                map.put("feeTypeStr", SysDataUtil.getNameFromDataDict(DictCode.费用品种类型,mstFeeinfo.getFeeType()));
            }
        }
        return map;
    }
    
    /**
     * 获取内部交易对手信息
     * @param map 请求参数组合代码
     * @return 清算账户表实体类
     * @see
     * @since 1.0
     */
    public List<StlCapacctInfo> getCountInfo(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstTradeOpponent/getCountInfo",map,
            StlCapacctInfo.class);
    }
}
