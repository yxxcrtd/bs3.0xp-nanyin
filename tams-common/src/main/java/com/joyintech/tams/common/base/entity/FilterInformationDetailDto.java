package com.joyintech.tams.common.base.entity;


/**
 * 过滤器详情实体类
 * @author gaojian
 *
 */
public class FilterInformationDetailDto {
	/**
	 * 过滤器详情ID
	 */
	private String filterDetailId;
	
	/**
	 * 对应过滤器ID
	 */
	private String filterId;
	
	/**
	 * 对应基础信息ID
	 */
	private String baseInformationId;

	public String getFilterDetailId() {
		return filterDetailId;
	}

	public void setFilterDetailId(String filterDetailId) {
		this.filterDetailId = filterDetailId;
	}

	public String getFilterId() {
		return filterId;
	}

	public void setFilterId(String filterId) {
		this.filterId = filterId;
	}

	public String getBaseInformationId() {
		return baseInformationId;
	}

	public void setBaseInformationId(String baseInformationId) {
		this.baseInformationId = baseInformationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseInformationId == null) ? 0 : baseInformationId.hashCode());
		result = prime * result + ((filterDetailId == null) ? 0 : filterDetailId.hashCode());
		result = prime * result + ((filterId == null) ? 0 : filterId.hashCode());
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
		FilterInformationDetailDto other = (FilterInformationDetailDto) obj;
		if (baseInformationId == null) {
			if (other.baseInformationId != null) {
				return false;
			}
		} else if (!baseInformationId.equals(other.baseInformationId)) {
			return false;
		}
		if (filterDetailId == null) {
			if (other.filterDetailId != null) {
				return false;
			}
		} else if (!filterDetailId.equals(other.filterDetailId)) {
			return false;
		}
		if (filterId == null) {
			if (other.filterId != null) {
				return false;
			}
		} else if (!filterId.equals(other.filterId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FilterInformationDetailDto [filterDetailId=" + filterDetailId + ", filterId=" + filterId
				+ ", baseInformationId=" + baseInformationId + "]";
	}
	
	
}
