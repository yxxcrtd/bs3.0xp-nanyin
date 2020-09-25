package com.joyintech.service.product.nolimit.nonstProUplimitMain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.nolimit.nonstProUplimitMain.dao.NonstProUplimitMainMapper;
import com.joyintech.service.product.nolimit.nonstProUplimitMain.dto.NonstProUplimitMainDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * NonstProUplimitMainService
 * @author cxq	
 */

@Service
public class NonstProUplimitMainService extends BaseService<NonstProUplimitMainDto> {
	@Autowired
	NonstProUplimitMainMapper nonstProUplimitMainMapper;

	/**
	 * 产品系列下拉框查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> selectPrdFamily(int page, int length, @RequestBody Map<String, Object> pageParam) {
		pageParam.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
		pageParam.put("isNew", DictCodeList.是否_是.getValue());
		return this.queryForPageForSelectReturn(page, length, () ->nonstProUplimitMainMapper.selectPrdFamily(pageParam));
	}
	
	/**
	 * 画面查询方法
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Page<NonstProUplimitMainDto> selectForPage(int page, int length, Map<String, Object> param)  throws Exception{
		return super.queryForPage(page, length, () -> nonstProUplimitMainMapper.selectForPage(param));
	}

	/**
	 * 根据系列id 查name
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */
	public Page<NonstProUplimitMainDto> queryPrdPlanSerName(int page, int length, Map<String, Object> param) {
		param.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
		param.put("isNew", DictCodeList.是否_是.getValue());
		return this.queryForPageForSelectReturn(page, length, () ->nonstProUplimitMainMapper.queryPrdPlanSerName(param));
	}
	
	/**
	 * 	插入方法
	 * @param entity
	 * @return
	 */
	public Integer insert(NonstProUplimitMainDto entity) {
		List<NonstProUplimitMainDto> tableList = entity.getTableList();
		int count = 0;
		//循环插入数据
		if(!StringUtil.isEmptyList(tableList)) {
		for (NonstProUplimitMainDto dto : tableList) {
			dto.setEffDate(entity.getEffDate());
			nonstProUplimitMainMapper.insert(dto);
			count++;
			}
		}
		return count;
	}
	
	/**
	 * 	根据时间和编号判断是否有重复数据
	 * @param entity
	 * @return
	 */
	public Integer selectPageData(NonstProUplimitMainDto entity) {
		return nonstProUplimitMainMapper.selectPageData(entity);
	}

	/**
	     *                            修改
	 * @param entity
	 * @return
	 */
	public Integer update(NonstProUplimitMainDto entity) {
		return nonstProUplimitMainMapper.update(entity);
	}

	/**
	 *  删除
	 * @param entity
	 * @return
	 */
	public Integer delete(NonstProUplimitMainDto entity) {
		return nonstProUplimitMainMapper.delete(entity);
	}
	
}
