package com.joyintech.service.product.rddesgin.productSimulationTrial.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductHistoricalDto;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductHistoricalMapper {


	/**
	 * 分页查询画面历史记录数据
	 * @param pageParam 参数
	 * @return 历史记录数据
	 * @see
	 * @since 1.0
	 */
	List<ProductHistoricalDto> findPage(Map<String, Object> pageParam);

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryMulType3(Map<String, Object> pageParam);

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryMulType4(Map<String, Object> pageParam);

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryMulType5(Map<String, Object> pageParam);


	List<String> getCustomAssetTypeThree(Map<String, Object> pageParam);
}
