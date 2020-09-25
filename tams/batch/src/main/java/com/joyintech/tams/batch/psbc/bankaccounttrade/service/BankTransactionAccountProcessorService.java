/*
 * 文件名：BankAccountTradeProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.bankaccounttrade.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
 
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeReaderDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeWriterDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BokVouchDetailDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BokVouchInfoDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType; 
import com.joyintech.psbc.constant.DictCode;
import com.joyintech.psbc.constant.DictCodeBankBaseSubjectNatrue;
import com.joyintech.psbc.constant.DictCodeList; 
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.batch.psbc.bankaccounttrade.dao.BankTransactionAccountMapper;
import com.joyintech.tams.common.base.entity.BokBankSceConfig;
import com.joyintech.tams.common.base.entity.BokSubject;


/**
 * 
 * 银行账交易账务批处理-processor
 * 
 * @author wangyang1
 * @version 1.0 2019年8月30日
 * @see BankTransactionAccountProcessorService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankTransactionAccountProcessorService {

	/**
	 * 账务类共通方法service
	 */
	@Autowired
	private AccountingCommonService accountingCommonService;

	 
	@Autowired
    private BankTransactionAccountMapper bankAccountTradeMapper;
	
	/**
	 * DBUtil
	 */
	@Autowired
	private DBUtil dBUtil;

	public BankAccountTradeWriterDto execute(Map<String, Object> param1) {
		// 获取reader返回值
		BankAccountTradeReaderDto acctEntity = (BankAccountTradeReaderDto) param1.get(GlobalConstant.READER_RESULT);
		// 定义返回值
		BankAccountTradeWriterDto returnResult = new BankAccountTradeWriterDto();
		if (returnResult.getListBokVouchInfoDto() == null) {
			returnResult.setListBokVouchInfoDto(new ArrayList<BokVouchInfoDto>());
		}
		// 1. 调用共通【银行账场景配置】
		List<BokBankSceConfig> listBokBankSceConfig = accountingCommonService.getBokBankSceConfig(
				acctEntity.getBooksetId(), DictCodeList.计提区分_交易.getValue(), DictCodeList.是否_是.getValue());
		// 调用共通【科目查询】
		Map<String, Object> map = new HashMap<>();
		map.put("booksetId", acctEntity.getBooksetId());
		List<BokSubject> listBokSubject = accountingCommonService.getSubjectInfo(map);
		// 循环银行账场景配置列表
		for (BokBankSceConfig bokBankSceConfig : listBokBankSceConfig) {
			// DTO.交易方向 ！= 银行账场景配置列表.交易方向
			if (!acctEntity.getPs().equals(bokBankSceConfig.getPs())) {
				continue;
			}
			// （解决现金分红是FeeType是空，导致不出账）
			if (StringUtils.isNotEmpty(bokBankSceConfig.getFeeType()) && !acctEntity.getFinprodType().equals(bokBankSceConfig.getFeeType())) {
				continue;
			}
			// 变量.银行账场景代码 = 银行账场景配置列表.场景代码
			String tableId = bokBankSceConfig.getTableId();
			// 变量.金融产品代码 = DTO.金融产品代码
			String finprodId = acctEntity.getFinprodId();
			// 变量.理财产品代码 = DTO.理财产品代码
			String proId = acctEntity.getProdId();
			// 变量.币种 = DTO.币种
			String ccy = acctEntity.getCcy();
			// 变量.分支序号 = DTO.分支序号
			Long branch = acctEntity.getBranch();

			// list<DTO<凭证信息，凭证子表<凭证明细list>>>
			List<BokVouchInfoDto> listBokVouchInfoDto = new ArrayList<>();
			// 凭证主表DTO
			BokVouchInfoDto dto = new BokVouchInfoDto();
			// 凭证主表DTO.凭证编号 = 自动生成
			dto.setVouchNum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
			// 凭证主表DTO.业务凭证编号 = 自动生成
			dto.setBusVouchnum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
			// 凭证主表DTO.账套代码 = DTO.账套代码
			dto.setBooksetId(acctEntity.getBooksetId());
			// 凭证主表DTO.合并场景代码 = 变量.银行账场景代码
			dto.setComTableId(tableId);
			// 凭证主表DTO.入账日期 = DTO.入账日
			dto.setBookDate(acctEntity.getBookDate());
			// 凭证主表DTO.凭证说明=变量.银行账场景代码对应的数据字典名称
			dto.setVouchRemark(SysDataUtil.getNameFromDataDict(DictCode.银行账场景代码, tableId));
			// 凭证主表DTO.交易编号=DTO.交易编号
			dto.setTradeId(acctEntity.getTradeId());
			//日终
			dto.setBookType("01");
			//正常交易
			dto.setOffsetFlag("01"); 
//			// 凭证子表<凭证明细list>
//			List<BokVouchDetailDto> listBokVouchDetail = new ArrayList<>();
			// 凭证明细
			BokVouchDetailDto bokVouchDetail = new BokVouchDetailDto();
			// 凭证明细.凭证子表编号 = 自动生成
			bokVouchDetail.setVouchSubnum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));

			// if 变量.银行账场景代码 = “产品固定管理费","产品浮动管理费"
			if (DictCodeList.银行账场景代码_产品固定管理费支付.getValue().equals(tableId)
					|| DictCodeList.银行账场景代码_产品浮动管理费支付.getValue().equals(tableId)) {
				// 循环科目列表
			    // if 变量.银行账场景代码 = “产品固定管理费支付”
                if (DictCodeList.银行账场景代码_产品固定管理费支付.getValue().equals(tableId)) {
                    // 循环科目列表
                    for (BokSubject bokSubject2 : listBokSubject) {
                        // if 科目列表.科目性质 = “应收固定管理费”
                        if (DictCodeBankBaseSubjectNatrue.应收固定管理费.getValue()
                                .equals(bokSubject2.getBaseSubjectNature())) {
                            // 凭证明细.科目号 = 科目列表.科目号
                            bokVouchDetail.setSubjectNo(bokSubject2.getSubjectNo());
                            break;
                        }
                    }
                    // 变量.银行账场景代码 = “产品浮动管理费支付”
                } else if (DictCodeList.银行账场景代码_产品浮动管理费支付.getValue().equals(tableId)) {
                    // 循环科目列表
                    for (BokSubject bokSubject3 : listBokSubject) {
                        // 科目列表.科目性质 ！= “应收浮动管理费”
                        if (!DictCodeBankBaseSubjectNatrue.应收浮动管理费.getValue()
                                .equals(bokSubject3.getBaseSubjectNature())) {
                            // 凭证明细.科目号 = 科目列表.科目号
                            continue;
                        }
                        bokVouchDetail.setSubjectNo(bokSubject3.getSubjectNo());
                        break;
                    }
                    // 变量.银行账场景代码 = “产品外包服务费支付”
                }
				// 凭证明细.发生日期 = DTO.发生日
				bokVouchDetail.setHappenDate(acctEntity.getHappenDate());
				// 凭证明细.场景代码 = 变量.银行账场景代码
				bokVouchDetail.setTableId(tableId);
				// 凭证明细.借贷方向 = ‘贷方’
				bokVouchDetail.setCdFlag(DictCodeList.借贷方向_贷.getValue()); 
				bokVouchDetail.setBookType("01");
				// 应收费用 = 费用头寸
				// if DTO.交易状态 = “初始”
			    BigDecimal feeAmt = BigDecimal.ZERO;
//				if(DictCodeList.是否_否.getValue().equals(acctEntity.getIsClean())){
				    feeAmt = acctEntity.getTradeAmt();
//				}else{
//				    feeAmt = this.queryFeeAmtByFeeType(acctEntity.getFinprodType(), acctEntity.getBookDate(), finprodId);
//				}
			    if (DictCodeListExt1.交易状态_初始.getValue().equals(acctEntity.getTradeStatus())) {
			        // 凭证明细.发生额 = 费用头寸.应付金额
			        bokVouchDetail.setHappenAmt(feeAmt==null?BigDecimal.ZERO:feeAmt);
			    }else{
			        // 凭证明细.发生额 = 费用头寸.应付金额
                    bokVouchDetail.setHappenAmt(feeAmt==null?BigDecimal.ZERO:ArithmeticUtil.multiply(feeAmt, new BigDecimal(-1)));
			    } 
			    bokVouchDetail.setbHappenAmt(bokVouchDetail.getHappenAmt());
				// 凭证明细.币种=变量.币种
				bokVouchDetail.setCcy(ccy);
				bokVouchDetail.setbCcy(ccy);
				// 凭证明细.金融产品代码=变量.金融产品代码
				bokVouchDetail.setFinprodId(finprodId);
				// 凭证明细.分支序号=变量.分支序号
				bokVouchDetail.setBranch(branch);
				// 凭证明细.理财产品代码=变量.理财产品代码
				bokVouchDetail.setProId(proId);
				// 凭证明细.凭证编号 = 凭证主表DTO.凭证编号
				bokVouchDetail.setVouchNum(dto.getVouchNum());
				// 凭证明细.业务明细代码 = 金融产品代码
				bokVouchDetail.setBusiId(acctEntity.getFinprodId());
				bokVouchDetail.setTradeId(acctEntity.getTradeId());
				// 凭证主表DTO.凭证字表.add(凭证明细)
				dto.getListBokVouchDetail().add(bokVouchDetail); 
				// (活期存款) 凭证明细copy（）；
				BokVouchDetailDto bokVouchDetail2 = new BokVouchDetailDto();
				BeanUtils.copyProperties(bokVouchDetail, bokVouchDetail2);
				// 凭证明细.凭证子表编号 = 自动生成
				bokVouchDetail2.setVouchSubnum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
				//交易发生额  采用理财帐的活期存款数据
				BigDecimal subjectBalance = acctEntity.getTradeAmt();
				// if DTO.交易状态 = “初始”
                if (DictCodeListExt1.交易状态_初始.getValue().equals(acctEntity.getTradeStatus())) {
                    bokVouchDetail2.setHappenAmt(subjectBalance==null?BigDecimal.ZERO:subjectBalance);
                }else{
                    bokVouchDetail2.setHappenAmt(subjectBalance==null?BigDecimal.ZERO:ArithmeticUtil.multiply(subjectBalance,new BigDecimal(-1)));
                }
                bokVouchDetail2.setbHappenAmt(bokVouchDetail2.getHappenAmt());
				for (BokSubject bokSubject : listBokSubject) {
                    // 科目列表.科目性质 ！= “活期存款”
                    if (!DictCodeBankBaseSubjectNatrue.活期存款.getValue()
                            .equals(bokSubject.getBaseSubjectNature())) {
                        // 凭证明细.科目号 = 科目列表.科目号
                        continue;
                    }
                    bokVouchDetail2.setSubjectNo(bokSubject.getSubjectNo());
                    break;
                }
				// 凭证明细.借贷方向 = ‘借方’
                bokVouchDetail2.setCdFlag(DictCodeList.借贷方向_借.getValue());
                // 凭证主表DTO.凭证字表.add(凭证明细)
                dto.getListBokVouchDetail().add(bokVouchDetail2);
				BokVouchDetailDto bokVouchDetail3 = new BokVouchDetailDto();
                BeanUtils.copyProperties(bokVouchDetail, bokVouchDetail3);
                // 凭证明细.凭证子表编号 = 自动生成
                bokVouchDetail3.setVouchSubnum(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型)); 
				// if 变量.银行账场景代码 = “产品固定管理费支付”
				if (DictCodeList.银行账场景代码_产品固定管理费支付.getValue().equals(tableId)) {
					// 循环科目列表
					for (BokSubject bokSubject2 : listBokSubject) {
						// if 科目列表.科目性质 = “固定管理费收入”
						if (DictCodeBankBaseSubjectNatrue.固定管理费收入.getValue()
								.equals(bokSubject2.getBaseSubjectNature())) {
							// 凭证明细.科目号 = 科目列表.科目号
							bokVouchDetail3.setSubjectNo(bokSubject2.getSubjectNo());
							break;
						}
					}
					// 变量.银行账场景代码 = “产品浮动管理费支付”
				} else if (DictCodeList.银行账场景代码_产品浮动管理费支付.getValue().equals(tableId)) {
					// 循环科目列表
					for (BokSubject bokSubject3 : listBokSubject) {
						// 科目列表.科目性质 ！= “浮动管理费收入”
						if (!DictCodeBankBaseSubjectNatrue.浮动管理费收入.getValue()
								.equals(bokSubject3.getBaseSubjectNature())) {
							// 凭证明细.科目号 = 科目列表.科目号
							continue;
						}
						bokVouchDetail3.setSubjectNo(bokSubject3.getSubjectNo());
						break;
					}
					// 变量.银行账场景代码 = “产品外包服务费支付”
				}
				//应收管理费 减去  实际支付管理费 
				BigDecimal happenAmt3 = ArithmeticUtil.doSub(feeAmt==null?BigDecimal.ZERO:feeAmt,subjectBalance); 
				if (happenAmt3.compareTo(BigDecimal.ZERO)==1) {
				    // 凭证明细.借贷方向 = ‘借方’
	                bokVouchDetail3.setCdFlag(DictCodeList.借贷方向_借.getValue()); 
                }else{
                    // 凭证明细.借贷方向 = ‘贷方’
                    bokVouchDetail3.setCdFlag(DictCodeList.借贷方向_贷.getValue());
                    happenAmt3 =ArithmeticUtil.multiply(happenAmt3, new BigDecimal(-1));
                }
				// if DTO.交易状态 = “初始”
                if (DictCodeListExt1.交易状态_初始.getValue().equals(acctEntity.getTradeStatus())) {
                    bokVouchDetail3.setHappenAmt(happenAmt3);
                }else{
                    bokVouchDetail3.setHappenAmt(ArithmeticUtil.multiply(happenAmt3,new BigDecimal(-1)));
                } 
                bokVouchDetail3.setbHappenAmt(bokVouchDetail3.getHappenAmt());
				// 凭证主表DTO.凭证字表.add(凭证明细)
				dto.getListBokVouchDetail().add(bokVouchDetail3);
				// 待出账list.add（凭证主表DTO）
				listBokVouchInfoDto.add(dto); 
			}
			returnResult.getListBokVouchInfoDto().addAll(listBokVouchInfoDto);
		}

		return returnResult;
	}
  
    /**
     * 查询应付费用 
     * @param feeType  费用类型
     * @param cdate    入账日期  
     * @param finprodId  产品代码
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal queryFeeAmtByFeeType(String feeType, Date cdate, String finprodId) {
        Map<String, Object> param = new HashMap<>();
        param.put("feeType", feeType); 
        param.put("cdate", DateUtil.format(DateUtil.addDay(cdate, -1), DateUtil.DEFAULT_DATE_FORMAT_EN));
        param.put("finprodId", finprodId);
        return bankAccountTradeMapper.queryFeeAmtByFeeType(param);
    }
}
