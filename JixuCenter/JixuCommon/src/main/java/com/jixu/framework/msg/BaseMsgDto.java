package com.jixu.framework.msg;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class BaseMsgDto extends BaseEntity implements ProcessEntity{
//public class BaseMsgDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String emptyAlert="请输入";

	//消息头
	@PrimaryKey(value=Field.PK_1)
	private String header;
	
	//消息操作 add,update, delete
	private String operation;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
