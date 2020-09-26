/*
 * 文件名：AccountDetailsQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.bankaccount.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.bankaccount.dao.AccountDetailsQuery001Mapper;
import com.joyintech.tams.valuation.bankaccount.dto.AccountDetailsQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 账务明细查询-service
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月1日
 * @see AccountDetailsQuery001Service
 * @since 1.0
 */
@Service
public class AccountDetailsQuery001Service extends CommonExportService<AccountDetailsQuery001Dto> {

    @Autowired
    private AccountDetailsQuery001Mapper accountDetailsQuery001Mapper;

    /**
     * 账务明细查询
     * 
     * @param params
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public Page<AccountDetailsQuery001Dto> findAccountDetailsPage(Map<String, Object> params)
        throws Exception {
//        String subjectName = (String)params.get("subjectName");
//        // 筛选条件-科目名称list
//        String[] subjectNameStr = this.String2Arr(subjectName);
//
//        String busiType = (String)params.get("busiType");
//        // 筛选条件-业务类别list
//        String[] busiTypeStr = this.String2Arr(busiType);
//
//        params.put("subjectNameStr", subjectNameStr);
//        params.put("busiTypeStr", busiTypeStr);
//        Page<AccountDetailsQuery001Dto> pages = super.queryForPage(
//            () -> accountDetailsQuery001Mapper.findAccountDetailsList(params));
//
//        List<AccountDetailsQuery001Dto> list = pages.getRoot();
//
//        for (AccountDetailsQuery001Dto dto : list) {
//            // 当前业务类别code
//            // 定义：变量-业务类别code = dto.业务类别code
//            String busiTypeCode = dto.getBusiTypeCode();
//            // 当期科目名称code
//            // 定义：变量-科目名称code = dto.科目名称code
//            String subjectNameCode = dto.getSubjectNameCode();
//            // 当前日期
//            // 定义：变量-发生日 = dto.发生日
//            String happenDate = dto.getHappenDate();
//            // 科目号list
//            // 定义：科目号list
//            String[] subjectNoStr;
//            // 业务类别
//            String type01 = DictCodeListExt1.业务类别_投资收益分配.getValue();
//            String type02 = DictCodeListExt1.业务类别_兑付客户收益.getValue();
//            String type03 = DictCodeListExt1.业务类别_兑付管理费.getValue();
//            // 科目名称
//            String sjt01 = DictCodeListExt1.科目名称_已实现未分配损益.getValue();
//            String sjt02 = DictCodeListExt1.科目名称_应付客户收益.getValue();
//            String sjt03 = DictCodeListExt1.科目名称_管理费收入.getValue();
//            String sjt04 = DictCodeListExt1.科目名称_存放托管行资金.getValue();
//            String sjt05 = DictCodeListExt1.科目名称_存放中央款项.getValue();
//
//            if (busiTypeCode.equals(type01) && subjectNameCode.equals(sjt01)) {
//                // 1.1 投资收益分配-已实现未分配损益(借方)
//                subjectNoStr = new String[] {"22320101", "22060101", "22060201"};
//            }
//            else if (busiTypeCode.equals(type01) && subjectNameCode.equals(sjt02)) {
//                // 1.2 投资收益分配-应付客户收益(贷方)
//                subjectNoStr = new String[] {"22320101"};
//            }
//            else if (busiTypeCode.equals(type01) && subjectNameCode.equals(sjt03)) {
//                // 1.3 投资收益分配-管理费收入(贷方)
//                subjectNoStr = new String[] {"22060101", "22060201"};
//            }
//            else if (busiTypeCode.equals(type02) && subjectNameCode.equals(sjt02)) {
//                // 2.1 兑付客户收益-应付客户收益(借方)
//                subjectNoStr = new String[] {"22320101"};
//            }
//            else if (busiTypeCode.equals(type02) && subjectNameCode.equals(sjt04)) {
//                // 2.2 兑付客户收益-存放托管行资金(贷方)
//                subjectNoStr = new String[] {"22320101"};
//            }
//            else if (busiTypeCode.equals(type03) && subjectNameCode.equals(sjt05)) {
//                // 3.1 兑付管理费-存放中央款项(借方)
//                subjectNoStr = new String[] {"22060101","22060201"};
//            }
//            else if (busiTypeCode.equals(type03) && subjectNameCode.equals(sjt04)) {
//                // 3.2 兑付管理费-存放托管行资金(贷方)
//                subjectNoStr = new String[] {"22060101","22060201"};
//            }
//            else {
//                continue;
//            }
//            Map<String, Object> map = new HashMap<>();
//            map.put("subjectNoStr", subjectNoStr);
//            map.put("happenDate", happenDate);
//            AccountDetailsQuery001Dto accountDetailsQuery001Dto = accountDetailsQuery001Mapper.getSubjectAmt(
//                map);
//            // dto.余额方向 = 返回值.余额方向
//            dto.setBalFlag(accountDetailsQuery001Dto.getBalFlagCode());
//            dto.setBalFlagCode(accountDetailsQuery001Dto.getBalFlagCode());
//            // dto.原币余额 = 返回值.原币余额
//            dto.setoAmt(accountDetailsQuery001Dto.getoAmt());
//        }
//
//        pages.setRoot(list);
    	// 微服务改造
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValBankAccountQuery001/findAccountDetailsPage",
                params, AccountDetailsQuery001Dto.class);

    }

    /**
     * 字符串转数组
     * 
     * @param valueString
     * @return
     * @see
     * @since 1.0
     */
    private String[] String2Arr(String valueString) {
        // 定义返回数组
        String[] returnArr = null;

        // 字符串转数组
        if (valueString != null && valueString.trim().length() > 0) {
            returnArr = valueString.trim().split(",");
        }

        return returnArr;
    }
}
