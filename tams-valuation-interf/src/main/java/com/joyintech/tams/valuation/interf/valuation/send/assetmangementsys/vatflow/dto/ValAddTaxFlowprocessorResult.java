/*
 * 文件名：ValAddTaxFlowprocessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2019年4月23日
 * @see ValAddTaxFlowprocessorResult
 * @since 1.0
 */
public class ValAddTaxFlowprocessorResult extends SendWriterEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    

    /**
     * 数据实体Map
     */
    private Map<String,List<ValAddTaxFlowDto>> datasMap;

    
	public Map<String, List<ValAddTaxFlowDto>> getDatasMap() {
		return datasMap;
	}

	public void setDatasMap(Map<String, List<ValAddTaxFlowDto>> datasMap) {
		this.datasMap = datasMap;
	}

   
}
