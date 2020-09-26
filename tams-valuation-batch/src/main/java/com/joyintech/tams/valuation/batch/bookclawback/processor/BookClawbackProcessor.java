package com.joyintech.tams.valuation.batch.bookclawback.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.bookclawback.dto.BookClawbackResult;
import com.joyintech.tams.valuation.batch.bookclawback.service.BookClawbackService;
import com.joyintech.tams.valuation.batch.common.accounting.entity.StayDrawSubprdEntity;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


@Service
@Scope("prototype")
public class BookClawbackProcessor extends BaseProcessor<NeedOperateEntity, BookClawbackResult> {

	@Autowired
	private BookClawbackService bookClawbackService;

	@Override
	public BookClawbackResult process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		StayDrawSubprdEntity acctEntity = (StayDrawSubprdEntity) item;

		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());
		// 将返回值塞入参数中
		thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

		// 调用service方法进行逻辑逻辑
		BookClawbackResult result = bookClawbackService.execute(thisParams);

		// 当为null时候，给予初始化
		if (result == null) {
			result = new BookClawbackResult();
		}

		result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

		return result;
	}

}
