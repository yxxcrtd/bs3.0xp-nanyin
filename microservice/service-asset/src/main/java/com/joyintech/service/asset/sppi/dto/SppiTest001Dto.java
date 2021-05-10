/*
 * 文件名：SppiTest001Dto
 * 版权：Copyright 2017-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020/10/9
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.asset.sppi.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

/**
 * SPPI测试查询界面输入输出DTO
 *
 * @author Jiaoxujin
 * @version 1.0 2020/10/9
 */
public class SppiTest001Dto extends BaseEntity {

    /**
     * SPPI测试ID
     */
    private String sppiTestId;

    /**
     * 资产代码(多选、查询条件用)
     */
    private String assetCodes;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 资产类型<br />
     * 债券类资产、项目类资产、计划类利率型资产
     */
    private String assetType;

    /**
     * SPPI测试状态<br />
     * 未测试、测试中、测试通过、测试不通过
     */
    private String testStatus;

    /**
     * 更新人（中文-realName）
     */
    private String updateUserName;

    /**
     * 不要被BaseEntity中的构造方法初始化的updateTime
     */
    private Date notInitUpdateTime;

    /**
     * SPPI测试模板类型：
     01：债券类资产SPPI测试模板
     02：非标准化债权资产SPPI测试模板
     根据PINAN_ASSET_TYPE（TYPE_8）值区分：
     债券类资产(TYPE_8=F01) --> 01
     项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02
     */
    private String sppiTmpType;

    /**
     * 测试模板编号
     01：普华永道(PwC)
     02：德勤(DTT)
     03：毕马威(KPMG)

     */
    private String sppiTmpNo;
    
    /**
     * 数据来源
     */
    private String dataSource;

    public String getSppiTestId() {
        return sppiTestId;
    }

    public void setSppiTestId(String sppiTestId) {
        this.sppiTestId = sppiTestId;
    }

    public String getAssetCodes() {
        return assetCodes;
    }

    public void setAssetCodes(String assetCodes) {
        this.assetCodes = assetCodes;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getNotInitUpdateTime() {
        return notInitUpdateTime;
    }

    public void setNotInitUpdateTime(Date notInitUpdateTime) {
        this.notInitUpdateTime = notInitUpdateTime;
    }

    public String getSppiTmpType() {
        return sppiTmpType;
    }

    public void setSppiTmpType(String sppiTmpType) {
        this.sppiTmpType = sppiTmpType;
    }

    public String getSppiTmpNo() {
        return sppiTmpNo;
    }

    public void setSppiTmpNo(String sppiTmpNo) {
        this.sppiTmpNo = sppiTmpNo;
    }

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
    
    
}
