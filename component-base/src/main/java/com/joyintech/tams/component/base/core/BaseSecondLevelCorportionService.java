package com.joyintech.tams.component.base.core;

import java.util.Map;

public interface BaseSecondLevelCorportionService {

	void secondLevelCorporation(Map<String, Object> map);

	/**
	 * 包含省联社
	 * @param map
	 */
	void secondLevelCorporationWithSLS(Map<String,Object> map);
}
