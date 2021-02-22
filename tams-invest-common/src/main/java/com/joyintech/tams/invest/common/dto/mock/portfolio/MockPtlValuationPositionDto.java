/*
 * 文件名：MockPtlValuationPositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.mock.portfolio;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

/**
 * 估值头寸数据dto
 * 
 * @author etliu
 * @version 1.0 2020年4月25日
 * @see MockPtlValuationPositionDto
 * @since 1.0
 */
public class MockPtlValuationPositionDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4316505559991326672L;

    /**
     * 组合资产分析查询 List<组合资产估值列表DTO>
     */
    private List<MockPtlSecValuationDto> ptlSecValuationList;

    /**
     * 组合估值查询 List<组合估值列表DTO>
     */
    private List<MockPtlPortfolioValuationDto> ptlPortfolioValuationList;

    /**
     * 组合费用头寸查询 List<组合费用头寸列表DTO>
     */
    private List<MockPtlFeePositionDto> ptlFeePositionList;

    /**
     * 组合现金查询 List<组合现金DTO>
     */
    private List<MockPtlCashPositionDto> ptlCashPositionList;

    /**
     * 获取 ptlSecValuationList
     * 
     * @return ptlSecValuationList.
     */
    public List<MockPtlSecValuationDto> getPtlSecValuationList() {
        return ptlSecValuationList;
    }

    /**
     * 设置 ptlSecValuationList
     * 
     * @param ptlSecValuationList ptlSecValuationList
     */
    public void setPtlSecValuationList(List<MockPtlSecValuationDto> ptlSecValuationList) {
        this.ptlSecValuationList = ptlSecValuationList;
    }

    /**
     * 获取 ptlPortfolioValuationList
     * 
     * @return ptlPortfolioValuationList.
     */
    public List<MockPtlPortfolioValuationDto> getPtlPortfolioValuationList() {
        return ptlPortfolioValuationList;
    }

    /**
     * 设置 ptlPortfolioValuationList
     * 
     * @param ptlPortfolioValuationList ptlPortfolioValuationList
     */
    public void setPtlPortfolioValuationList(List<MockPtlPortfolioValuationDto> ptlPortfolioValuationList) {
        this.ptlPortfolioValuationList = ptlPortfolioValuationList;
    }

    /**
     * 获取 ptlFeePositionList
     * 
     * @return ptlFeePositionList.
     */
    public List<MockPtlFeePositionDto> getPtlFeePositionList() {
        return ptlFeePositionList;
    }

    /**
     * 设置 ptlFeePositionList
     * 
     * @param ptlFeePositionList ptlFeePositionList
     */
    public void setPtlFeePositionList(List<MockPtlFeePositionDto> ptlFeePositionList) {
        this.ptlFeePositionList = ptlFeePositionList;
    }

    /**
     * 获取 ptlCashPositionList
     * 
     * @return ptlCashPositionList.
     */
    public List<MockPtlCashPositionDto> getPtlCashPositionList() {
        return ptlCashPositionList;
    }

    /**
     * 设置 ptlCashPositionList
     * 
     * @param ptlCashPositionList ptlCashPositionList
     */
    public void setPtlCashPositionList(List<MockPtlCashPositionDto> ptlCashPositionList) {
        this.ptlCashPositionList = ptlCashPositionList;
    }
}
