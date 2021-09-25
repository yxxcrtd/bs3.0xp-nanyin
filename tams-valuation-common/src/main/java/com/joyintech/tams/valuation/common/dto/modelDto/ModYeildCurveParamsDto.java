package com.joyintech.tams.valuation.common.dto.modelDto;

import java.util.Date;
import java.util.List;

/**
 * 曲线行情查询参数对象
 *
 * @author zhangfei
 */
public class ModYeildCurveParamsDto {
	/**
	 * 曲线代码 集合
	 */
	private List<String> yeildIdList;

	/**
	 * 区间开始日期
	 */
	private Date startDate;

	/**
	 * 区间结束日期
	 */
	private Date endDate;

	/**
	 * 	查询日期
	 */
    private Date searchDate;

    public List<String> getYeildIdList() {
        return yeildIdList;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setYeildIdList(List<String> yeildIdList) {
        this.yeildIdList = yeildIdList;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
