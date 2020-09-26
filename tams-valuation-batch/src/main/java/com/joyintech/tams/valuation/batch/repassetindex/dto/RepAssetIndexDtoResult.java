package com.joyintech.tams.valuation.batch.repassetindex.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.RepAssetIndex;

public class RepAssetIndexDtoResult extends NeedToWriteEntity{
 
	 /**
	  * 账套
	  */
	private  List<RepAssetIndex> listRepAssetIndex;

	public List<RepAssetIndex> getListRepAssetIndex() {
		return listRepAssetIndex;
	}

	public void setListRepAssetIndex(List<RepAssetIndex> listRepAssetIndex) {
		this.listRepAssetIndex = listRepAssetIndex;
	}
	
	
}
