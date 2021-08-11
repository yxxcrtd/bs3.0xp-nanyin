package com.joyintech.tams.valuation.valmodel.modstockdivitrateconfig.dto;

import java.util.Date;
import java.util.List;

/**
 * 描述:MOD_ASEET_MAPPING表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-07-01
 */
public class ModAseetMappingDto {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 映射类型
     */
    private String mappingType;

    /**
     * 输入1
     */
    private String input1;

    /**
     * 输入2
     */
    private String input2;

    /**
     * 输入3
     */
    private String input3;

    /**
     * 输入4
     */
    private String input4;

    /**
     * 输入5
     */
    private String input5;

    /**
     * 输出1
     */
    private String output1;

    /**
     * 输出2
     */
    private String output2;

    /**
     * 输出3
     */
    private String output3;

    /**
     * 输出4
     */
    private String output4;

    /**
     * 输出5
     */
    private String output5;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
    /*增加查询时传参字段*/
    private List<String> input1List;
    private List<String> input2List;
    private List<String> input3List;
    private List<String> input4List;
    private List<String> input5List;
    /*增加查询时传参字段*/
    /**
     * 流水号
     * @return SEQ_NO 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 映射类型
     * @return MAPPING_TYPE 映射类型
     */
    public String getMappingType() {
        return mappingType;
    }

    /**
     * 映射类型
     * @param mappingType 映射类型
     */
    public void setMappingType(String mappingType) {
        this.mappingType = mappingType == null ? null : mappingType.trim();
    }

    /**
     * 输入1
     * @return INPUT1 输入1
     */
    public String getInput1() {
        return input1;
    }

    /**
     * 输入1
     * @param input1 输入1
     */
    public void setInput1(String input1) {
        this.input1 = input1 == null ? null : input1.trim();
    }

    /**
     * 输入2
     * @return INPUT2 输入2
     */
    public String getInput2() {
        return input2;
    }

    /**
     * 输入2
     * @param input2 输入2
     */
    public void setInput2(String input2) {
        this.input2 = input2 == null ? null : input2.trim();
    }

    /**
     * 输入3
     * @return INPUT3 输入3
     */
    public String getInput3() {
        return input3;
    }

    /**
     * 输入3
     * @param input3 输入3
     */
    public void setInput3(String input3) {
        this.input3 = input3 == null ? null : input3.trim();
    }

    /**
     * 输入4
     * @return INPUT4 输入4
     */
    public String getInput4() {
        return input4;
    }

    /**
     * 输入4
     * @param input4 输入4
     */
    public void setInput4(String input4) {
        this.input4 = input4 == null ? null : input4.trim();
    }

    /**
     * 输入5
     * @return INPUT5 输入5
     */
    public String getInput5() {
        return input5;
    }

    /**
     * 输入5
     * @param input5 输入5
     */
    public void setInput5(String input5) {
        this.input5 = input5 == null ? null : input5.trim();
    }

    /**
     * 输出1
     * @return OUTPUT1 输出1
     */
    public String getOutput1() {
        return output1;
    }

    /**
     * 输出1
     * @param output1 输出1
     */
    public void setOutput1(String output1) {
        this.output1 = output1 == null ? null : output1.trim();
    }

    /**
     * 输出2
     * @return OUTPUT2 输出2
     */
    public String getOutput2() {
        return output2;
    }

    /**
     * 输出2
     * @param output2 输出2
     */
    public void setOutput2(String output2) {
        this.output2 = output2 == null ? null : output2.trim();
    }

    /**
     * 输出3
     * @return OUTPUT3 输出3
     */
    public String getOutput3() {
        return output3;
    }

    /**
     * 输出3
     * @param output3 输出3
     */
    public void setOutput3(String output3) {
        this.output3 = output3 == null ? null : output3.trim();
    }

    /**
     * 输出4
     * @return OUTPUT4 输出4
     */
    public String getOutput4() {
        return output4;
    }

    /**
     * 输出4
     * @param output4 输出4
     */
    public void setOutput4(String output4) {
        this.output4 = output4 == null ? null : output4.trim();
    }

    /**
     * 输出5
     * @return OUTPUT5 输出5
     */
    public String getOutput5() {
        return output5;
    }

    /**
     * 输出5
     * @param output5 输出5
     */
    public void setOutput5(String output5) {
        this.output5 = output5 == null ? null : output5.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public List<String> getInput1List() {
		return input1List;
	}

	public void setInput1List(List<String> input1List) {
		this.input1List = input1List;
	}

	public List<String> getInput2List() {
		return input2List;
	}

	public void setInput2List(List<String> input2List) {
		this.input2List = input2List;
	}

	public List<String> getInput3List() {
		return input3List;
	}

	public void setInput3List(List<String> input3List) {
		this.input3List = input3List;
	}

	public List<String> getInput4List() {
		return input4List;
	}

	public void setInput4List(List<String> input4List) {
		this.input4List = input4List;
	}

	public List<String> getInput5List() {
		return input5List;
	}

	public void setInput5List(List<String> input5List) {
		this.input5List = input5List;
	}
    
}