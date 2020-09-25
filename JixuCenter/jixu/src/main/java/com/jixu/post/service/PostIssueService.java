package com.jixu.post.service;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

public interface PostIssueService {

	public void postIssue(String tanum, ResultContainer container);
	
	public void postIssue(TranBasic tran, ResultContainer container);
	
	public TranSum calPosition(Tran tran, TranSum tSum);
	
	public void reverse(String tanum, ResultContainer container);
}
