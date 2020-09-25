/*
 * 文件名：BankCalProvisionReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.bankcalbook.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.psbc.bankcalbook.dao.BokBankPositionMapper;
import com.joyintech.tams.batch.psbc.bankcalbook.dto.BokBankPositionDto;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.batch.finproduct.service.AccountingCommonBatchService;

/**
 * 银行账计提账务处理 ReaderService
 * 
 * @author wangyang1
 * @version 1.0 Apr 10, 2019
 * @see BokBankPositionService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BokBankPositionService {
  
    @Autowired
    private BokBankPositionMapper bokBankPositionMapper;
    
    @Autowired
    private AccountingCommonBatchService accountingCommonBatchService;

    /**
     * 银行账计提余额  - 银行账计提账务处理
     * @param finprodId 金融产品代码
     * @param branch 分支序号
     * @param booksetId 账套代码
     * @param tableId 账套代码
     * @param vouchNum 入账日
     * @param subjectNo 科目号
     * @return 银行账计提余额
     */
    public List<BokBankPositionDto> findBokBankPosition(String finprodId,Date balanceDate, String subjectNo){
        Map<String, Object> param = new HashMap<>();
        param.put("finprodId", finprodId);
        param.put("balanceDate", balanceDate);
        param.put("subjectNo", subjectNo);
        return bokBankPositionMapper.selectBokBankPosition(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void updateByPrimaryKey(BokBankPositionDto dto){
        bokBankPositionMapper.updateByPrimaryKey(dto);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listdto
     * @see
     * @since 1.0
     */
    public void insertBokBankPosition(List<BokBankPositionDto> listdto){
        bokBankPositionMapper.insertBokBankPosition(listdto);
    }
    
    
    public void insertSelective(BokBankPositionDto dto){
        bokBankPositionMapper.insertSelective(dto);
    }
    
    /**
     * 银行账科目余额汇总处理
     * 
     * @param balance 余额明细
     * @param subjectNo  科目号
     * @param subjectLevel 科目等级
     * @param balanceMap 科目余额map
     * @param subjectMap 科目map
     * @param isleaf 是否叶子节点：Y/N
     * @see
     * @since 1.0
     */
    public void collectBokBankPosition(BokBankPositionDto position, String subjectNo, Long subjectLevel,Map<String, BokSubject> subjectMap,
                                      Map<String, BokBankPositionDto> positionMap, String isleaf) {
        // 定义 变量.科目信息
        BokSubject subject = subjectMap.get(subjectNo);
        String fSubjectId = subject.getFsubjectId(); 
        String key = position.getFinprodId()+"@"+subjectNo;
        // if（科目余额MAP.get(变量.KEY) == null）
        if (positionMap.get(key) == null) {
            // 变量.本科目余额明细
            BokBankPositionDto dto = new BokBankPositionDto();
            dto.setFinprodId(position.getFinprodId());
            dto.setBalanceDate(position.getBalanceDate());
            dto.setBooksetId(position.getBooksetId());
            dto.setSubjectNo(subjectNo);
            dto.setFsubjectNo(fSubjectId);
            dto.setSubjectLevel(subjectLevel);
            dto.setBalFlag(position.getBalFlag());
            dto.setoCcy(position.getoCcy());
            dto.setoAmt(position.getoAmt());
            dto.setbCcy(position.getbCcy());
            dto.setbAmt(position.getbAmt());
            dto.setIsLeaf(isleaf);
            dto.setNumAmt(position.getNumAmt()); 
            // 存入map
            positionMap.put(key, dto);
        }
        else {
            BokBankPositionDto dto = positionMap.get(key);
            // 调用共通方法获取科目余额、方向、数量--原币
            Map<String, Object> obalanceDeal = accountingCommonBatchService.balanceDirectDealAccrualAddBalance(
                    subject.getBalFlag(), dto.getBalFlag(), dto.getoAmt(), dto.getNumAmt(),
                    position.getBalFlag(), position.getoAmt(), position.getNumAmt());
            // 调用共通方法获取科目余额、方向、数量--本位币
            Map<String, Object> balanceDeal = accountingCommonBatchService.balanceDirectDealAccrualAddBalance(
                subject.getBalFlag(), dto.getBalFlag(), dto.getbAmt(), dto.getNumAmt(),
                position.getBalFlag(), position.getbAmt(), position.getNumAmt());
            dto.setbAmt(new BigDecimal(balanceDeal.get("balance").toString()));
            // 设值科目余额
            dto.setoAmt(new BigDecimal(obalanceDeal.get("balance").toString()));
            // 设值余额数量
            dto.setNumAmt(new BigDecimal(obalanceDeal.get("num").toString()));
            // 设值余额方向
            dto.setBalFlag(obalanceDeal.get("balDirect").toString()); 
            dto.setBalanceDate(position.getBalanceDate());
            // 更新map
            positionMap.put(key, dto);
        }
        // 上级科目不为空，回调
        if (StringUtil.isNotEmpty(fSubjectId)) {
            BokSubject fsubject = subjectMap.get(fSubjectId);
            collectBokBankPosition(position,fSubjectId, fsubject.getSubjectLevel(),subjectMap,positionMap,
                    DictCodeList.是否_否.getValue());
        }
    }
}
