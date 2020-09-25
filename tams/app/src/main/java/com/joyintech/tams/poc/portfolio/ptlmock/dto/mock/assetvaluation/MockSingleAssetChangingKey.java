/*
 * 文件名：MockSingleAssetChangingKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation;

import com.joyintech.framework.util.StringUtil;

/**
 * 资产变动MAP的key 〈功能详细描述〉
 * 
 * @author admin
 * @version 1.0 2020年4月21日
 * @see MockSingleAssetChangingKey
 * @since 1.0
 */
public class MockSingleAssetChangingKey {
	/**
	 * 金融产品代码
	 */
	private String finprodId;

	/**
	 * 投资目的
	 */
	private String invAim;

	public MockSingleAssetChangingKey(String finprodId, String invAim) {
		this.finprodId = finprodId;
		this.invAim = invAim;
	}

	/**
	 * 获取 finprodId
	 * 
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * 
	 * @param finprodId finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 invAim
	 * 
	 * @return invAim.
	 */
	public String getInvAim() {
		return invAim;
	}

	/**
	 * 设置 invAim
	 * 
	 * @param invAim invAim
	 */
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockSingleAssetChangingKey mockSingleAssetChangingKey = (MockSingleAssetChangingKey) obj;
		if (!StringUtil.equals(mockSingleAssetChangingKey.finprodId, finprodId)) {
			return false;
		}
		if (!StringUtil.equals(mockSingleAssetChangingKey.invAim, invAim)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 3;
		result = 31*result + (finprodId != null ? finprodId.hashCode() : 0);
		result = 31*result + (invAim != null ? invAim.hashCode() : 0);
		return result;
	}

}
