/*
 * 文件名：AccountCurrentRate002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dao.AccountCurrentRate002Mapper;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.tams.common.base.dao.FinCashCalcRuleMapper;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinRateInfoMapper;
//import com.joyintech.tams.common.base.dao.FinCashIntInfoMapper;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.FinRateInfoKey;
//import com.joyintech.tams.common.base.entity.FinCashIntInfo;
//import com.joyintech.tams.common.base.entity.FinCashIntInfoKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年11月13日
 * @see AccountCurrentRate002Service
 * @since 1.0
 */
@Service(value="serviceAccountCurrentRate002Service")
@Transactional(rollbackFor = Exception.class)
public class AccountCurrentRate002Service extends CommonExportService<AccountCurrentRate002Dto> implements 
    BaseProcessService<AccountCurrentRate002Dto> {

    /**
     * accountCurrentRate002Mapper
     */
    @Autowired
    private AccountCurrentRate002Mapper accountCurrentRate002Mapper;

    /**
     * interfCommonService
     */
    @Autowired
    private InterfCommonService interfCommonService;

//    /**
//     * finCashIntInfoMapper
//     */
//    @Autowired
//    private FinCashIntInfoMapper finCashIntInfoMapper;
    
    @Autowired
    private FinRateInfoMapper finRateInfoMapper;

    /**
     * finCashCalcRuleMapper
     */
    @Autowired
    private FinCashCalcRuleMapper finCashCalcRuleMapper;

    /**
     * finCashInfoMapper
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 帐号下拉框初始化
     * 
     * @param map Map
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctNo(Map<String, Object> map) {
        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        List<AccountCurrentRate002Dto> list = accountCurrentRate002Mapper.getAccNo(map);
        list.forEach(item -> {
            listSelectDto.add(createSelect(item.getAccountId(), item.getAccountName()));
        });
        return listSelectDto;
    }
    
// TODO 一体化问题8：表删除（行情信息（MST_RATE_INFO）） 修改：基准利率从数据字典取
//    /**
//     * 基准利率初始化
//     * 
//     * @return List<SelectOptionDto>
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getBasicRate() {
//        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//        List<AccountCurrentRate002Dto> list = accountCurrentRate002Mapper.getBasicRate();
//        list.forEach(item -> {
//            listSelectDto.add(createSelect(item.getIntRateId(), item.getRateName()));
//        });
//        return listSelectDto;
//    }

    /**
     * 格式化创建SelectOptionDto对象
     * 
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    // /**
    // * 联动出账户类型
    // *
    // * @param dto AccountCurrentRate002Dto
    // * @return String
    // * @see
    // * @since 1.0
    // */
    // public String searchType(AccountCurrentRate002Dto dto) {
    // AccountCurrentRate002Dto AccountCurrentRate002Dto = accountCurrentRate002Mapper.searchType(
    // dto);
    // return AccountCurrentRate002Dto.getAccountType();
    // }

    /**
     * 修改双击查看页面初始化数据
     * 
     * @param map Map<String, Object>
     * @return AccountCurrentRate002Dto
     * @see
     * @since 1.0
     */
    public AccountCurrentRate002Dto getfind002Dto(Map<String, Object> map) {
        return accountCurrentRate002Mapper.getfind002Dto(map);
    }

    /**
     * 提交
     * 
     * @param dto AccountCurrentRate002Dto
     * @return int
     * @see
     * @since 1.0
     */
    public int doCommit(AccountCurrentRateDto dto) {
        int count = 0;
        // 现金流代码不为空 修改
        if (StringUtil.isNotEmpty(dto.getCashId())) {
            // 日期不等判断
            if (!dto.getEffDate().equals(dto.getEffDate1())) {

                Map<String, Object> map = new HashMap<>();
                map.put("cashId", dto.getCashId());
                map.put("effDate", dto.getEffDate());
                // 判断在流程中时又没有新建的数据入库
                if (accountCurrentRate002Mapper.checkAccNo(map)) {
//                      TODO  保存在流程中时 并且一批流程执行完 该条流程数据主键已经在数据库中 
//                      TODO  反馈前台修改失败 
                }
                // 不存在可以入库
                else {
                    // FinCashIntInfoKey key1 = new FinCashIntInfoKey();
                	FinRateInfoKey key1 = new FinRateInfoKey();
                    key1.setCashId(dto.getCashId());
                    key1.setEffDate(DateUtil.parse(dto.getEffDate1()));
                    FinCashCalcRuleKey key2 = new FinCashCalcRuleKey();
                    key2.setCashId(dto.getCashId());
                    key2.setEffDate(DateUtil.parse(dto.getEffDate1()));
                    // count += finCashIntInfoMapper.deleteByPrimaryKey(key1);
                    count += finRateInfoMapper.deleteByPrimaryKey(key1);
                    count += finCashCalcRuleMapper.deleteByPrimaryKey(key2);
                    String cashId = InterfHelper.getMd5CashId_(dto.getAcctNo(),
                        DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
                    if (!accountCurrentRate002Mapper.checkCashId(cashId)) {
                        finCashInfoMapper.insertSelective(this.getFinCashInfo(dto, cashId));

                    }
                    // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
                    // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
                    // count += finCashIntInfoMapper.insertSelective(finCashIntInfo);
                    FinRateInfo finRateInfo = this.getFinRateInfo(dto);
                    FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finRateInfo);
                    count += finRateInfoMapper.insertSelective(finRateInfo);
                    count += finCashCalcRuleMapper.insertSelective(finCashCalcRule);
                }

            }
            // 相等直接操作
            else {

                // FinCashIntInfoKey key1 = new FinCashIntInfoKey();
            	FinRateInfoKey key1 = new FinRateInfoKey();
                key1.setCashId(dto.getCashId());
                key1.setEffDate(DateUtil.parse(dto.getEffDate1()));
                FinCashCalcRuleKey key2 = new FinCashCalcRuleKey();
                key2.setCashId(dto.getCashId());
                key2.setEffDate(DateUtil.parse(dto.getEffDate1()));
                // count += finCashIntInfoMapper.deleteByPrimaryKey(key1);
                count += finRateInfoMapper.deleteByPrimaryKey(key1);
                count += finCashCalcRuleMapper.deleteByPrimaryKey(key2);
                String cashId = InterfHelper.getMd5CashId_(dto.getAcctNo(),
                    DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
                if (!accountCurrentRate002Mapper.checkCashId(cashId)) {
                    finCashInfoMapper.insertSelective(this.getFinCashInfo(dto, cashId));

                }
                // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
                //FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
                //count += finCashIntInfoMapper.insertSelective(finCashIntInfo);
                FinRateInfo finRateInfo = this.getFinRateInfo(dto);
                FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finRateInfo);
                count += finRateInfoMapper.insertSelective(finRateInfo);
                count += finCashCalcRuleMapper.insertSelective(finCashCalcRule);
            }
        }
        // 现就刘代码为空 新建
        else {
            String cashId = InterfHelper.getMd5CashId_(dto.getAcctNo(),
                DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue());
            if (!accountCurrentRate002Mapper.checkCashId(cashId)) {
                finCashInfoMapper.insertSelective(this.getFinCashInfo(dto, cashId));

            }
            // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
            // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
            // count += finCashIntInfoMapper.insertSelective(finCashIntInfo);
            FinRateInfo finRateInfo = this.getFinRateInfo(dto);
            FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finRateInfo);
            count += finRateInfoMapper.insertSelective(finRateInfo);
            count += finCashCalcRuleMapper.insertSelective(finCashCalcRule);
        }

        return count;
    }

//    /**
//     * 现金流利率信息表设值
//     * 
//     * @param dto AccountCurrentRateDto
//     * @return FinCashIntInfo
//     * @see
//     * @since 1.0
//     */
//    public FinCashIntInfo getFinCashIntInfo(AccountCurrentRateDto dto) {
//        FinCashIntInfo finCashIntInfo = new FinCashIntInfo();
//        finCashIntInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAcctNo(),
//            DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue()));
//        finCashIntInfo.setEffDate(DateUtil.parse(dto.getEffDate()));
//        finCashIntInfo.setIntType(dto.getIntType());
//        finCashIntInfo.setBasis(dto.getBasis());
//        finCashIntInfo.setFinprodId(dto.getAcctNo());
//        finCashIntInfo.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
//        finCashIntInfo.setBranch(InterfCommonConstant.分支序号_默认);
//        // 浮动利率
//        if (DictCodeList.利率类型_浮动利率.getValue().equals(dto.getIntType())) {
//            finCashIntInfo.setResetType(DictCodeListExt1.重置类型_随调.getValue());
//            finCashIntInfo.setPreObsNum(BigDecimal.ZERO);
//            finCashIntInfo.setBenchmarkId(dto.getBasicRate());
//            finCashIntInfo.setBenchmarkType(DictCodeListExt1.基准类型_2_利率.getValue());
//            finCashIntInfo.setBasicRate(null);
//
//        }
//        // 固定利率
//        else if (DictCodeList.利率类型_固定利率.getValue().equals(dto.getIntType())) {
//
//            finCashIntInfo.setBasicRate(ArithmeticUtil.divide(dto.getRate(), new BigDecimal(100)));
//            finCashIntInfo.setResetType(null);
//            finCashIntInfo.setPreObsNum(null);
//            finCashIntInfo.setBenchmarkId(null);
//            finCashIntInfo.setBenchmarkType(null);
//        }
//        return finCashIntInfo;
//    }
    
    /**
     * 现金流利率信息表设值
     * 
     * @param dto AccountCurrentRateDto
     * @return FinRateInfo
     * @see
     * @since 1.0
     */
    public FinRateInfo getFinRateInfo(AccountCurrentRateDto dto) {
    	FinRateInfo finRateInfo = new FinRateInfo();
        finRateInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAcctNo(),
            DictCodeListExt1.金融产品类型_资金账户.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        finRateInfo.setEffDate(DateUtil.parse(dto.getEffDate()));
        finRateInfo.setIntType(dto.getIntType());
        finRateInfo.setBasis(dto.getBasis());
        finRateInfo.setFinprodId(dto.getAcctNo());
        finRateInfo.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
        finRateInfo.setBranch(InterfCommonConstant.分支序号_默认);
        // 浮动利率
        if (DictCodeList.利率类型_浮动利率.getValue().equals(dto.getIntType())) {
            finRateInfo.setResetType(DictCodeListExt1.重置类型_随调.getValue());
            finRateInfo.setObserveBefDay(BigDecimal.ZERO);
            finRateInfo.setBenchmarkId(dto.getBasicRate());
            finRateInfo.setBenchmarkType(DictCodeListExt1.基准类型_2_利率.getValue());
            finRateInfo.setRate(null);

        }
        // 固定利率
        else if (DictCodeList.利率类型_固定利率.getValue().equals(dto.getIntType())) {

            finRateInfo.setRate(ArithmeticUtil.divide(dto.getRate(), new BigDecimal(100), 2));
            finRateInfo.setResetType(null);
            finRateInfo.setObserveBefDay(BigDecimal.ZERO);
            finRateInfo.setBenchmarkId(null);
            finRateInfo.setBenchmarkType(null);
        }
        return finRateInfo;
    }

    /**
     * 现金流计算规则表设值
     * 
     * @param dto FinCashIntInfo
     * @return FinCashCalcRule
     * @see
     * @since 1.0
     */
//    public FinCashCalcRule getFinCashCalcRule(FinCashIntInfo dto) {
    public FinCashCalcRule getFinCashCalcRule(FinRateInfo dto) {
        FinCashCalcRule finCashCalcRule = interfCommonService.getFinCashCalcRule(dto);
        finCashCalcRule.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        finCashCalcRule.setCreateTime(new Date());
        finCashCalcRule.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        finCashCalcRule.setUpdateTime(new Date());
        return finCashCalcRule;
    }
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(AccountCurrentRate002Dto dto, OperationType type) {
        // //新建
        // if (OperationType.NEW.equals(type)) {
        // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
        // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
        // finCashIntInfoMapper.insertSelective(finCashIntInfo);
        // finCashCalcRuleMapper.insertSelective(finCashCalcRule);
        // }
        // //修改
        // else if (OperationType.MODIFY.equals(type)) {
        // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
        // finCashIntInfo.setCashId(dto.getCashId());
        // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
        // finCashCalcRule.setCreateUser(null);
        // finCashCalcRule.setCreateTime(null);
        // finCashIntInfoMapper.updateByPrimaryKeySelective(finCashIntInfo);
        // finCashCalcRuleMapper.updateByPrimaryKeySelective(finCashCalcRule);
        // }
        // int count = 0;
        // // 现金流代码不为空 修改
        // if (StringUtil.isNotEmpty(dto.getCashId())) {
        // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
        // finCashIntInfo.setCashId(dto.getCashId());
        // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
        // finCashCalcRule.setCreateUser(null);
        // finCashCalcRule.setCreateTime(null);
        // count += finCashIntInfoMapper.updateByPrimaryKeySelective(finCashIntInfo);
        // count += finCashCalcRuleMapper.updateByPrimaryKeySelective(finCashCalcRule);
        // }
        // // 现就刘代码为空 新建
        // else {
        // FinCashIntInfo finCashIntInfo = this.getFinCashIntInfo(dto);
        // FinCashCalcRule finCashCalcRule = this.getFinCashCalcRule(finCashIntInfo);
        // count += finCashIntInfoMapper.insertSelective(finCashIntInfo);
        // count += finCashCalcRuleMapper.insertSelective(finCashCalcRule);
        // }

    }

    /**
     * 联想框初始化
     * 
     * @param map Map<String, Object>
     * @param param String
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAcctNo1(Map<String, Object> map, String param) {

        return queryForSuggest(() -> {
            return accountCurrentRate002Mapper.getAcctNo1(map);
        }, null, null);
    }

    /**
     * 现金流信息表塞值
     * 
     * @param dto AccountCurrentRateDto
     * @param cashId cashId
     * @return FinCashInfo
     * @see
     * @since 1.0
     */
    public FinCashInfo getFinCashInfo(AccountCurrentRateDto dto, String cashId) {
        FinCashInfo finCashInfo = new FinCashInfo();
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        finCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        finCashInfo.setFinprodId(dto.getAcctNo());
        finCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_资金账户.getValue());
        finCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
        finCashInfo.setCreateUser(SecurityUtil.getLoginUser().getUserName());
        finCashInfo.setCreateTime(new Date());
        finCashInfo.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        finCashInfo.setUpdateTime(new Date());
        return finCashInfo;

    }

    /**
     * 转换公共DTO
     * 
     * @param dto AccountCurrentRate002Dto
     * @return AccountCurrentRateDto
     * @see
     * @since 1.0
     */
    public AccountCurrentRateDto getAccountCurrentRateDto(AccountCurrentRate002Dto dto) {
        AccountCurrentRateDto result = new AccountCurrentRateDto();

        result.setAcctNo(dto.getAccountId());
        if (StringUtil.isNotEmpty(dto.getCashId())) {
            result.setCashId(dto.getCashId());
        }
        result.setEffDate(dto.getEffDate());
        result.setEffDate1(dto.getEffDate1());
        result.setAcctType(dto.getAccountType());
        result.setBasicRate(dto.getBasicRate());
        result.setBasis(dto.getBasis());
        result.setIntType(dto.getIntType());
        result.setRate(dto.getRate());
        result.setIntRateStructure(dto.getIntRateStructure());
        result.setOperateType(dto.getOperateType());
        return result;

    }
}
