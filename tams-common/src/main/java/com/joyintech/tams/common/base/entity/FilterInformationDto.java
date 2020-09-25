package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 过滤器实体类
 * @author gaojian
 *
 */
public class FilterInformationDto extends BaseEntity{
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -8220382255798936002L;

	/**
	 * 过滤器ID
	 */
	private String filterId;
	
	/**
	 * 过滤器用户ID
	 */
	private String filterUserId;
	
	/**
	 * 过滤器名称
	 */
	private String filterName;

	public String getFilterId() {
		return filterId;
	}

	public void setFilterId(String filterId) {
		this.filterId = filterId;
	}

	public String getFilterUserId() {
		return filterUserId;
	}

	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filterId == null) ? 0 : filterId.hashCode());
		result = prime * result + ((filterName == null) ? 0 : filterName.hashCode());
		result = prime * result + ((filterUserId == null) ? 0 : filterUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FilterInformationDto other = (FilterInformationDto) obj;
		if (filterId == null) {
			if (other.filterId != null) {
				return false;
			}
		} else if (!filterId.equals(other.filterId)) {
			return false;
		}
		if (filterName == null) {
			if (other.filterName != null) {
				return false;
			}
		} else if (!filterName.equals(other.filterName)) {
			return false;
		}
		if (filterUserId == null) {
			if (other.filterUserId != null) {
				return false;
			}
		} else if (!filterUserId.equals(other.filterUserId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FilterInfomationDto [filterId=" + filterId + ", filterUserId=" + filterUserId + ", filterName="
				+ filterName + "]";
	}
	
	
}
