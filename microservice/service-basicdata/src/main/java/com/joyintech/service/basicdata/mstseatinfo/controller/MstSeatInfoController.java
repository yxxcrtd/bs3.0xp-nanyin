package com.joyintech.service.basicdata.mstseatinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstseatinfo.dto.MstSeatInfoDto;
import com.joyintech.service.basicdata.mstseatinfo.service.MstSeatInfoService;

@RestController("MstSeatInfoCtr_dtx")
@RequestMapping("/MstSeatInfo")
public class MstSeatInfoController {
	/**
	 * {@Autowired}
	 */
	@Autowired
	private MstSeatInfoService mstSeatInfoService;
	

	/**
	 * 
	 * @param pageParam
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectForPage/{page}/{length}")
	public Page<MstSeatInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		Page<MstSeatInfoDto> Page = mstSeatInfoService.selectForPage(page, length, pageParam);
		return Page;
	}

	/**
	 * 详情、修改、查看、唯一性验证
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/selectByPrimaryKey")
	public MstSeatInfoDto selectByPrimaryKey(@RequestBody MstSeatInfoDto dto) {
		return mstSeatInfoService.selectByPrimaryKey(dto);
	}
	
	/**
	 * 席位号联想查询
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	@PostMapping("/fuzzySearchSeatId")
	public List<SuggestDto> fuzzySearchSeatId(@RequestBody Map<String, String> map) {
		return mstSeatInfoService.fuzzySearchSeatId(map);
	}
	
	/**
	 * 交易对手席位号联想查询
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	@PostMapping("/selectCounterSeat")
	public List<SuggestDto> selectCounterSeat(@RequestBody Map<String, String> map) {
		return mstSeatInfoService.selectCounterSeat(map);
	}

	/**
	 * 删除
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("delete")
	public int delete(@RequestBody MstSeatInfoDto dto) throws Exception {
		return mstSeatInfoService.delete(dto);
	}

	/**
	 * 插入
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insert")
	public int insert(@RequestBody MstSeatInfoDto dto) throws Exception {
		return mstSeatInfoService.insert(dto);
	}

	/**
	 * 更新
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("update")
	public int update(@RequestBody MstSeatInfoDto dto) throws Exception {
		return mstSeatInfoService.update(dto);
	}
}
