/*
 * 文件名：PtlAccountCurrentInterest001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlaccountcurrentinterest.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinRateInfoMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.FinRateInfoKey;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dao.PtlAccountCurrentInterest001Mapper;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto.MarkRate;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 账户活期利率
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Service
 * @since 1.0
 */
@Service("backPtlAccountCurrentInterest001Service")
public class PtlAccountCurrentInterest001Service extends BaseService<PtlAccountCurrentInterest001Dto>{
    /**
     * 注入账户活期利率Mapper
     */
    @Autowired
    private PtlAccountCurrentInterest001Mapper PtlAccountCurrentInterest001Mapper;
    /**
     * 现金流信息表Mapper
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    /**
     * 利率信息表Mapper
     */
    @Autowired
    private FinRateInfoMapper finRateInfoMapper;
    /**
     * 现金流计算规则表Mapper
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;
    /**
     * 获取账号
     * @param param 模糊查询参数
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiAcctNo(int page, int length, Map<String,Object> map){
       /*if("null".equals( map.get("param")) || StringUtil.isBlank( String(map.get("param")))){
            param = "";
        }*/       
        return this.queryForPageForSelectReturn(page, length,()->PtlAccountCurrentInterest001Mapper.getBusiAcctNo(map));
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param length 长度
     * @param map 查询参数
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlAccountCurrentInterest001Dto> selectForPage(int page,int length,Map<String,Object> map) throws Exception{
        return super.queryForPage(page, length, () -> PtlAccountCurrentInterest001Mapper.selectForPage(map));
    }
    /**
     * 获取浮动利率（复合基准的数据）
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getcurrentRate(){
        return PtlAccountCurrentInterest001Mapper.getcurrentRate();
    }
    /**
     * 删除数据
     * @param dto 账户实体类
     * @see
     * @since 1.0
     */
    public void delete(PtlAccountCurrentInterest001Dto dto){
        FinCashCalcRuleKey finCashCalcRuleKey = new FinCashCalcRuleKey();
        finCashCalcRuleKey.setCashId(dto.getCashId());
        finCashCalcRuleKey.setEffDate(dto.getEffDate());
        finCashCalcRuleMapper.deleteByPrimaryKey(finCashCalcRuleKey);
        Map<String,Object> map = new HashMap<>();
        map.put("cashId", dto.getCashId());
        if(PtlAccountCurrentInterest001Mapper.getRateCount(map) <= 1){
        finCashInfoMapper.deleteByPrimaryKey(dto.getCashId());
        }
        FinRateInfoKey finRateInfoKey = new FinRateInfoKey();
        finRateInfoKey.setCashId(dto.getCashId());
        finRateInfoKey.setEffDate(dto.getEffDate());
        finRateInfoMapper.deleteByPrimaryKey(finRateInfoKey);
//        PtlAccountCurrentInterest001Mapper.deleteFinCashCalcRule(dto);
//        PtlAccountCurrentInterest001Mapper.deleteFinCashInfo(dto);
//        PtlAccountCurrentInterest001Mapper.deleteFinRateInfo(dto);
    }
    /**
     * 更新数据
     * @param dto 账户实体类
     * @see
     * @since 1.0
     */
    public void update(PtlAccountCurrentInterest001Dto dto){
        if(dto.getSpreadRate() == null){
            dto.setSpreadRate(new BigDecimal("0"));
        }
        if(dto.getCoefficient() == null){
            dto.setCoefficient(new BigDecimal("1"));
        }
        if(DictCodeList.利率类型_浮动利率.getValue().equals(dto.getRateType())){
            dto.setYield(getIndexRate(dto).multiply(dto.getCoefficient()).add(dto.getSpreadRate().divide(new BigDecimal("100"))));
            dto.setSpreadRate(dto.getSpreadRate().divide(new BigDecimal("100")));
            dto.setCurrentRate(getIndexRate(dto));
        }
        else{
            dto.setYield(dto.getRate().divide(new BigDecimal("100")));
            dto.setRate(dto.getRate().divide(new BigDecimal("100")));
        }
        FinCashInfo finCashInfo = new FinCashInfo();
        finCashInfo.setCcy(dto.getCcy());
        finCashInfo.setFinprodId(dto.getFinprodId());
        finCashInfoMapper.updateByPrimaryKey(finCashInfo);
//        PtlAccountCurrentInterest001Mapper.updateFinCashInfo(dto);
        PtlAccountCurrentInterest001Mapper.updateFinCashCalcRule(dto);
        PtlAccountCurrentInterest001Mapper.updateFinRateInfo(dto);
    }
    /**
     * 插入数据
     * @param dto 账号活期利率实体类
     * @see
     * @since 1.0
     */
    public void insert(PtlAccountCurrentInterest001Dto dto){
        if(dto.getSpreadRate() == null){
            dto.setSpreadRate(new BigDecimal("0"));
        }
        if(dto.getCoefficient() == null){
            dto.setCoefficient(new BigDecimal("1"));
        }
        if(DictCodeList.利率类型_浮动利率.getValue().equals(dto.getRateType()) || DictCodeList.利率类型_结构性.getValue().equals(dto.getRateType())){
            dto.setYield(getIndexRate(dto).multiply(dto.getCoefficient()).add(dto.getSpreadRate().divide(new BigDecimal("100"))));
            dto.setSpreadRate(dto.getSpreadRate().divide(new BigDecimal("100")));
            dto.setCurrentRate(getIndexRate(dto));
        }
        else{
            dto.setYield(dto.getRate().divide(new BigDecimal("100")));
        }

        FinCashInfo finCashInfo = new FinCashInfo();
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        finCashInfo.setBranch(Long.valueOf(dto.getBranch()));
        FinCashInfo oldCashId = finCashInfoMapper.selectByPrimaryKey(finCashInfo.getCashId());
        if(oldCashId == null){
        finCashInfoMapper.insertSelective(finCashInfo);
        }

        FinRateInfo finRateInfo = new FinRateInfo();
        BeanUtil.copyPropertiesIgnoreNull(finRateInfo, dto);
        finRateInfo.setIntType(dto.getRateType());
        finRateInfo.setBenchmarkId(dto.getIndexId());
        finRateInfo.setBenchmarkType(dto.getIndexSource());
        if(StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_浮动利率.getValue()) || StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_结构性.getValue()) ){
            finRateInfo.setRate(dto.getCurrentRate());
            //sjj add 20190422 利率重置批處理需求修改 start
            finRateInfo.setResetType(DictCodeListExt2.重置类型_随调.getValue());
            //sjj add 20190422 利率重置批處理需求修改 end
        }
        else {
        	finRateInfo.setRate(dto.getRate().divide(new BigDecimal("100")));
        }
        finRateInfo.setBranch(Long.valueOf(dto.getBranch()));
        finRateInfoMapper.insertSelective(finRateInfo);

        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, dto);
        finCashCalcRule.setCalcType(dto.getRateType());
        finCashCalcRule.setBranch(Long.valueOf(dto.getBranch()));
        finCashCalcRuleMapper.insertSelective(finCashCalcRule);

//        PtlAccountCurrentInterest001Mapper.insertFinCashInfo(dto);
//        PtlAccountCurrentInterest001Mapper.insertFinRateInfo(dto);
//        PtlAccountCurrentInterest001Mapper.insertFinCashCalcRule(dto);
    }
    /**
     * 获取初始化实体类
     * @param map 查询参数
     * @return 账号活期利率实体类
     * @see
     * @since 1.0
     */
    public PtlAccountCurrentInterest001Dto getInitInfo(Map<String,Object> map){
        map.put("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        return PtlAccountCurrentInterest001Mapper.getInitInfo(map);
    }
    /**
     * 获取最后的生效日期
     * @param acctNo 网点账号
     * @return 最近交易日
     * @see
     * @since 1.0
     */
    public Date getLastEffDate(String acctNo){
        return PtlAccountCurrentInterest001Mapper.getLastEffDate(acctNo);
    }
    /**
     * 计算浮动利率的值
     * @param dto 账户活期利率实体类
     * @return 浮动利率
     * @see
     * @since 1.0
     */
    public BigDecimal getIndexRate(PtlAccountCurrentInterest001Dto dto){
        BigDecimal realIntRate = new BigDecimal("0");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("indexId", dto.getIndexId());
        param.put("effDate", DateUtil.format(dto.getEffDate()));
        param.put("ccy", dto.getCcy());
      //基准来源 = 利率行情时
        //从利率行情表中取得基准利率
        if(DictCodeListExt1.基准类型_2_利率.getValue().equals(dto.getIndexSource())){
            realIntRate = realIntRate.add(PtlAccountCurrentInterest001Mapper.getIndexIntRate(param)
                    != null ? PtlAccountCurrentInterest001Mapper.getIndexIntRate(param) : new BigDecimal("0"));
        }
        else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(dto.getIndexSource())){
            realIntRate = realIntRate.add(PtlAccountCurrentInterest001Mapper.getInterRate(param)
                    != null ? PtlAccountCurrentInterest001Mapper.getInterRate(param) : new BigDecimal("0"));
        }
        else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(dto.getIndexSource())){
            realIntRate = realIntRate.add(PtlAccountCurrentInterest001Mapper.getIntRate(param)
                    != null ? PtlAccountCurrentInterest001Mapper.getIntRate(param) : new BigDecimal("0"));
        }
        //基准类型 = 复合基准时
        //查询出该基准代码所对应的所有行情信息
        else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(dto.getIndexSource())){
            //查出出该基准代码所对应的所有行情信息
            List<MarkRate> markRateList = PtlAccountCurrentInterest001Mapper.getBenchmarkInfo(param);
            if(markRateList != null && markRateList.size()>0){
                for(MarkRate entity:markRateList){
                    param.put("indexId",entity.getComponentId());
                    if(DictCodeListExt1.基准类型_2_利率.getValue().equals(entity.getComponentType())){
                        realIntRate = PtlAccountCurrentInterest001Mapper.getIndexIntRate(param)
                                != null ? realIntRate.add(PtlAccountCurrentInterest001Mapper.getIndexIntRate(param)).multiply(entity.getWeight())
                                : realIntRate;
                    }
                    else if(DictCodeListExt1.基准类型_2_指数.getValue().equals(entity.getComponentType())){
                        realIntRate = PtlAccountCurrentInterest001Mapper.getIntRate(param)
                                != null ? realIntRate.add(PtlAccountCurrentInterest001Mapper.getIntRate(param).multiply(entity.getWeight()))
                                : realIntRate;
                    }
                    else if(DictCodeListExt1.基准类型_2_收益率曲线.getValue().equals(entity.getComponentType())){
                        realIntRate = PtlAccountCurrentInterest001Mapper.getInterRate(param)
                                != null ? realIntRate.add(PtlAccountCurrentInterest001Mapper.getInterRate(param).multiply(entity.getWeight()))
                                : realIntRate;
                    }
                }
            }
        }
        return realIntRate;
    }
    /**
     *
     * 统计存在的信息
     * <功能详细描述>
     * @param dto 待校验dto
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int countExistsData(PtlAccountCurrentInterest001Dto dto) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("cashId", dto.getCashId());
        map.put("ccy", dto.getCcy());
        map.put("effDate", dto.getEffDate());
        map.put("busiAcctNo", dto.getBusiAcctNo());
        map.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        return PtlAccountCurrentInterest001Mapper.countExistsData(map);
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param length 长度
     * @param map 查询参数
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlAccountCurrentInterest001Dto> ratePage(int page,int length,Map<String,Object> map) throws Exception{
        return super.queryForPage(page, length, () -> PtlAccountCurrentInterest001Mapper.ratePage(map));
    }

    /**
     * 根据金融产品代码查询生效日最新的活期利率信息
     * @param map 查询参数
     * @return 账号活期利率实体类
     * @see
     * @since 1.0
     */
    public PtlAccountCurrentInterest001Dto getInitInfoLastFinprodId(Map<String,Object> map){
        return PtlAccountCurrentInterest001Mapper.getInitInfoLastFinprodId(map);
    }
}
