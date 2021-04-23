package com.joyintech.service.basicdata.mstseatinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstseatinfo.dao.MstSeatInfoMapper;
import com.joyintech.service.basicdata.mstseatinfo.dto.MstSeatInfoDto;

@Service("backMstSeatInfoService")
public class MstSeatInfoService extends BaseService<MstSeatInfoDto> {
	/**
	 * 席位信息管理mapper
	 */
	@Autowired
	private MstSeatInfoMapper mstSeatInfoMapper;

	/**
	 * 席位信息查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<MstSeatInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
		return super.queryForPage(page, length, () -> mstSeatInfoMapper.findPage(pageParam));
	}

	/**
	 * 主键唯一性验证
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public MstSeatInfoDto selectByPrimaryKey(MstSeatInfoDto dto) {
		return mstSeatInfoMapper.selectBySeatId(dto.getSeatId());
	}
	
	/**
	 * 席位号搜索
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public List<SuggestDto> fuzzySearchSeatId(Map<String, String> map) {
		return mstSeatInfoMapper.fuzzySearchSeatId(map);
	}
	
	/**
	 * 席位号搜索
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public List<SuggestDto> selectCounterSeat(Map<String, String> map) {
		return mstSeatInfoMapper.selectCounterSeat(map);
	}

	/**
	 * 删除操作
	 */
	public int delete(MstSeatInfoDto entity) {
		int res = 0;
		if (entity.getSelectedItem() != null && entity.getSelectedItem().size() > 0) {
			for (MstSeatInfoDto unit : entity.getSelectedItem()) {
				res += mstSeatInfoMapper.deleteByPrimaryKey(unit.getSeatId());
			}
		} else {
			res = mstSeatInfoMapper.deleteByPrimaryKey(entity.getSeatId());
		}
		return res;
	}

	/**
	 * 插入操作
	 */
	public int insert(MstSeatInfoDto dto) {
		return mstSeatInfoMapper.insertSelective(dto);
	}

	/**
	 * 更新操作
	 */
	public int update(MstSeatInfoDto dto) {
		return mstSeatInfoMapper.updateByPrimaryKeySelective(dto);
	}
}
