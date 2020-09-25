/*
 * 文件名：ResetBaseRuleInfoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdfutureopenplangen.writer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinOpenPlanMapper;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlanKey;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.FinOpenPlanDto;

/**
 * 对应 写入处理
 * 
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SusPrdFutureOpenPlanGenWriter extends BaseWriter<NeedToWriteEntity> {

	@Autowired
	private FinOpenPlanMapper finOpenPlanMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void write(List<? extends NeedToWriteEntity> items) throws Exception {

		List<FinOpenPlanDto> finOpenPlanDtoList = (List<FinOpenPlanDto>) items;
		
		List sqls = new ArrayList();

		if (!StringUtil.isEmptyList(finOpenPlanDtoList)) {
			for (FinOpenPlanDto finOpenPlanDto : finOpenPlanDtoList) {

				if (!StringUtil.isEmptyList(finOpenPlanDto.getFinOpenPlanList())) {

					for (FinOpenPlan finOpenPlan : finOpenPlanDto.getFinOpenPlanList()) {

						// 查询无重复
						FinOpenPlanKey key = new FinOpenPlanKey();
						key.setCashId(finOpenPlan.getCashId());
						key.setOpenType(finOpenPlan.getOpenType());
						key.setOpenDateEnd(finOpenPlan.getOpenDateEnd());
						FinOpenPlan fop = finOpenPlanMapper.selectByPrimaryKey(key);
						
//						if(StringUtils.isEmpty(fop)) {
//							finOpenPlanMapper.insert(finOpenPlan); // 如果为空再插入操作
//						}
//						CASH_ID, OPEN_TYPE, OPEN_DATE_END
						
						try {
							finOpenPlanMapper.insert(finOpenPlan); // 如果为空再插入操作
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							String sql = "select * from FIN_OPEN_PLAN where CASH_ID = '" + finOpenPlan.getCashId() + "' and OPEN_TYPE = '" + finOpenPlan.getOpenType() + "' and OPEN_DATE_END = to_date('" + new SimpleDateFormat("yyyy-MM-dd").format(finOpenPlan.getOpenDateEnd()) + "','yyyy-MM-dd');";
							sqls.add(sql);
						}
						
					}
				}
			}

		}
		System.out.println(sqls);
	}

}
