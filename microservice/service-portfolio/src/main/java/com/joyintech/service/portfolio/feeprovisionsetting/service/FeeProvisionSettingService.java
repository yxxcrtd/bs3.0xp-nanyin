package com.joyintech.service.portfolio.feeprovisionsetting.service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.portfolio.feeprovisionsetting.dao.FeeProvisionSettingMapper;
import com.joyintech.service.portfolio.feeprovisionsetting.dto.FeeProvisionSettingDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 费用预提设置Service层
 * @author
 *
 */
@Service
public class FeeProvisionSettingService extends BaseService<FeeProvisionSettingDto>{

	@Autowired
	private FeeProvisionSettingMapper feeProvisionSettingMapper;

	/**
	 * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
	 *
	 * @param page page
	 * @param length length
	 * @param map map
	 * @return Page类 Page
	 * @throws Exception 异常信息
	 */
	public Page<FeeProvisionSettingDto> selectForPage(int page, int length, Map<String, Object> map)
			throws Exception {
		return super.queryForPage(page, length, () -> feeProvisionSettingMapper.selectForPage(map));
	}

	/**
	 * 证券账户联想文本框service层实现
	 * @return SuggestDto
	 */
	public Page<SelectOptionDto> getSecAcctId(int page, int length, Map<String, Object> map) {
		map.put("secAcctType_fund", DictCodeListExt3.证券账户类型_基金账户.getValue());
		return this.queryForPageForSelectReturn(page, length, () -> feeProvisionSettingMapper.getSecAcctId(map));
	}

	/**
	 *
	 * 编辑查询账户维护费分配表查询分页
	 * <功能详细描述>
	 * @param pageParam pageParam
	 * @return Page<TrdAccountmfConfigdetailDto>
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	public Page<TrdAccountmfConfigdetailDto> selectDetail(int page, int length, Map<String, Object> pageParam)
			throws Exception {
		return this.queryForPageForSelectReturn(page,length,() -> feeProvisionSettingMapper.selectDetail(pageParam));
	}

	/**
	 * 增加数据
	 * @param dto
	 * @return
	 */
	public void insert(FeeProvisionSettingDto dto){
		feeProvisionSettingMapper.insert(dto);
	}

	/**
	 * 增加数据
	 * @param dto
	 * @return
	 */
	public void revoke(FeeProvisionSettingDto dto){
		feeProvisionSettingMapper.update(dto);
	}

	/**
	 * 批量增加明细数据
	 * @param list
	 * @return
	 */
	public void insertDetailBatch(List<TrdAccountmfConfigdetailDto> list){
		feeProvisionSettingMapper.insertDetailBatch(list);
	}

	/**
	 * 获取账套信息
	 * @see
	 * @since 1.0
	 * @return 账套实体类
	 */
	public List<SelectOptionDto> getBooksetId(Map<String,Object> map){
		map.put("loginUserName", LoginUserUtil.getUserName());
		map.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		return feeProvisionSettingMapper.getBooksetId(map);
	}

	/**
	 * 获取账套信息
	 * @see
	 * @since 1.0
	 * @return 账套实体类
	 */
	public String selectOrgInfo(Map<String,Object> map){
		return feeProvisionSettingMapper.selectOrgInfo(map);
	}

	/**
	 * 通过主键查询实体信息
	 * @param seqNo
	 * @return
	 */
	public FeeProvisionSettingDto selectEntityByID(String seqNo){
		return feeProvisionSettingMapper.selectEntityByID(seqNo);
	}


	/**
	 * 删除数据
	 * @return
	 */
	public void deleteById(String seqNo){
		feeProvisionSettingMapper.deleteById(seqNo);
	}
	public void deleteDetailById(String seqNo){
		feeProvisionSettingMapper.deleteDetailById(seqNo);
	}

	/**
	 * 获取最大产品起息日
	 * @param list
	 * @return
	 */
	public Date getMaxVdate(List<TrdAccountmfConfigdetailDto> list){
		return feeProvisionSettingMapper.getMaxVdate(list);
	}

	/**
	 * 获取最小的产品到期日
	 * @param list
	 * @return
	 */
	public Date getMinMdate(List<TrdAccountmfConfigdetailDto> list){
		return feeProvisionSettingMapper.getMinMdate(list);
	}

	/**
	 * 查询重复区间
	 * @param dto
	 * @return
	 */
	public Integer selectCount(FeeProvisionSettingDto dto){
		return feeProvisionSettingMapper.selectCount(dto);
	}

	/**
	 * 查询重复区间的最大实际到期日
	 * @param dto
	 * @return
	 */
	public Date getRepetitionDate(FeeProvisionSettingDto dto){
		return feeProvisionSettingMapper.selectRepetitionDate(dto);
	}
	
	/**
	 * 删除流程中的数据
	 * @return
	 */
	public void deleteNewDataDetailByPId(String pSeqNo){
		feeProvisionSettingMapper.deleteNewDataDetailByPId(pSeqNo);
	}
	
	/**
	 * 查询明细数据
	 * @param map
	 * @return
	 */
	public List<TrdAccountmfConfigdetailDto> selectConfigDetail(Map<String,Object> map){
		return feeProvisionSettingMapper.selectConfigDetail(map);
	}
	
}
