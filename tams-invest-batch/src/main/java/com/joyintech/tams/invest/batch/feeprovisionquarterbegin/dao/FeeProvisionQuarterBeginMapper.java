package com.joyintech.tams.invest.batch.feeprovisionquarterbegin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.FinTradeProductAddKey;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigDto;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.invest.batch.feeprovisionquarterbegin.dto.FinTradeProductAddDto;

/**
 * 费用预提每季度初批处理Mapper
 * @author hpp
 *
 */
@Repository
public interface FeeProvisionQuarterBeginMapper {

	/**
	 * 根据平台日 获取需要生成预提交易类标的的配置信息
	 * @param batchDate
	 * @return
	 */
	List<TrdAccountmfConfigDto> selectTACInfoByDate(String batchDate);
	
	/**
	 * 根据主表流水号获取费用预提规则明细信息
	 * @param pSeqNo
	 * @return
	 */
	//List<TrdAccountmfConfigdetailDto> selectTACDetailInfoByPSeqNo(String pSeqNo);
	List<TrdAccountmfConfigdetailDto> selectTACDetailInfoByPSeqNo(Map<String, Object> param);
	
	/**
	 * 根据主键删除交易类金融产品附属表信息
	 * @param key
	 * @return
	 */
	int deleteFTPAByPrimaryKey(FinTradeProductAddDto key);
	/**
	 * 生成交易类金融产品附属
	 * @param record
	 * @return
	 */
    int insertFTPA(FinTradeProductAddDto record);
	
    int insertFTPSelective(FinTradeProduct record);
    
    String getBooksetIdByProdId(String booksetId);
}
