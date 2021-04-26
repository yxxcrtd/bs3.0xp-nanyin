/*
 * 文件名：BankAccountTradeWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年4月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankaccounttrade.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.common.base.dao.BokVouchDetailMapper;
import com.joyintech.tams.common.base.dao.BokVouchMainMapper;
import com.joyintech.tams.common.base.entity.BokVouchDetail;
import com.joyintech.tams.common.base.entity.BokVouchMain;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BankAccountTradeWriterDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BokVouchDetailDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.dto.BokVouchInfoDto;
import com.joyintech.tams.valuation.batch.bankaccounttrade.writer.BankAccountTradeWriter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 银行账交易账务批处理-writer
 * 
 * @author niuqianyu
 * @version 1.0 2019年4月11日
 * @see BankAccountTradeWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankTransactionAccountWriter extends BaseWriter<NeedToWriteEntity> {

	@Autowired
	private BokVouchMainMapper bokVouchMainMapper;

	@Autowired
	private BokVouchDetailMapper bokVouchDetailMapper;
  
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {
		// 获取process返回组合的list值 - 记账凭证list
		@SuppressWarnings("unchecked")
		List<BankAccountTradeWriterDto> resultList = (List<BankAccountTradeWriterDto>) items;
		// 循环进行数据更新
		for (BankAccountTradeWriterDto dto : resultList) {

			// 获取list<DTO<凭证信息，凭证子表<凭证明细list>>>
			List<BokVouchInfoDto> listBokVouchInfoDto = dto.getListBokVouchInfoDto();

			for (BokVouchInfoDto bokVouchInfoDto : listBokVouchInfoDto) {

				if (bokVouchInfoDto != null) {
					BokVouchMain bokVouchMain = bokVouchMainMapper.selectByPrimaryKey(bokVouchInfoDto.getVouchNum());
					if (bokVouchMain != null) {
						bokVouchMainMapper.deleteByPrimaryKey(bokVouchInfoDto.getVouchNum());
					}
					bokVouchMainMapper.insert(bokVouchInfoDto);

					// 获取 凭证子表<凭证明细list>
					List<BokVouchDetailDto> listBokVouchDetail = bokVouchInfoDto.getListBokVouchDetail();
					if (!CollectionUtils.isEmpty(listBokVouchDetail)) {
						// 循环凭证明细list
						for (BokVouchDetailDto bokVouchDetailDto : listBokVouchDetail) {
							BokVouchDetail bokVouchDetail = bokVouchDetailMapper
									.selectByPrimaryKey(bokVouchDetailDto.getVouchSubnum());

							if (bokVouchDetail != null) {
								bokVouchDetailMapper.deleteByPrimaryKey(bokVouchDetailDto.getVouchSubnum());
							}  
                            if (ArithmeticUtil.equals(BigDecimal.ZERO, bokVouchDetailDto.getHappenAmt())) {
                                continue;
                            }
							bokVouchDetailMapper.insert(bokVouchDetailDto);
						}
					}
				}
			}

		}

	}

}
