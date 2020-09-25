package com.jixu.post.service;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.TranBasic;

public interface PostFeeService {

	public void postFee(TranBasic tranAdvice, ResultContainer container);

	public void reverseFee(TranBasic tranAdvice, ResultContainer container);
}
