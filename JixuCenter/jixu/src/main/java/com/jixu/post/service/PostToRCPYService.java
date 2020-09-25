package com.jixu.post.service;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.TranBasic;

public interface PostToRCPYService {

	public void postRcpy(TranBasic tranAdvice, ResultContainer container);

	public void deleteRcpy(TranBasic tranAdvice, ResultContainer container);
}
