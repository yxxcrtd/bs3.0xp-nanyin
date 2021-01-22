package com.joyintech.service.portfolio.feeprovisionsetting.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.feeprovisionsetting.dto.FeeProvisionSettingDto;
import com.joyintech.service.portfolio.feeprovisionsetting.service.FeeProvisionSettingService;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;

/**
 * 费用预提设置Controller层
 * @author
 *
 */
@RestController("feeProvisionSettingController_dtx")
@RequestMapping("/FeeProvisionSetting")
public class FeeProvisionSettingController {

	@Autowired
	private FeeProvisionSettingService feeProvisionSettingService;

	/**
	 * 查询产品费用支付信息
	 * @param param 查询条件
	 * @param page 第几页
	 * @param length 每页展示个数
	 * @return 费用计划信息
	 * @throws Exception 异常信息
	 */
	@PostMapping("selectForPage/{page}/{length}")
	public Page<FeeProvisionSettingDto> selectForPage(@RequestBody Map<String, Object> param,
													  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		String positionStartDateStr = (String)param.get("positionStartDate");
		String positionEndDateStr = (String)param.get("positionEndDate");
		String actPositionEndDateStr = (String)param.get("actPositionEndDate");
		param.put("positionStartDate", DateUtil.parse(positionStartDateStr));
		param.put("positionEndDate", DateUtil.parse(positionEndDateStr));
		param.put("actPositionEndDate",DateUtil.parse(actPositionEndDateStr));
		return feeProvisionSettingService.selectForPage(page, length, param);
	}

	/**
	 * 证券账户账号联想文本框 <功能详细描述>
	 *
	 * @return List
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getSecAcctId/{page}/{length}")
	public Page<SelectOptionDto> getSecAcctId(@RequestBody Map<String, Object> param,
											  @PathVariable("page") int page, @PathVariable("length") int length)
			throws Exception {
		return feeProvisionSettingService.getSecAcctId(page, length, param);
	}

	/**
	 *
	 * 编辑查询账户维护费分配表查询分页
	 * <功能详细描述>
	 * @param pageParam pageParam
	 * @return Page<MstBondCreditRating>
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("selectDetail/{page}/{length}")
	public Page<TrdAccountmfConfigdetailDto> selectDetail(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
		return feeProvisionSettingService.selectDetail(page,length,pageParam);
	}

	/**
	 * 增加数据
	 * @param dto
	 * @return
	 */
	@PostMapping("/insert")
	public void insert(@RequestBody FeeProvisionSettingDto dto){
		feeProvisionSettingService.insert(dto);  //插入
		feeProvisionSettingService.insertDetailBatch(dto.getTrdAccountmfConfigdetail()); //批量插入
	}

	/**
	 * 增加数据
	 * @param dto
	 * @return
	 */
	@PostMapping("/modify")
	public void modify(@RequestBody FeeProvisionSettingDto dto){
		feeProvisionSettingService.deleteById(dto.getSeqNo());	//删除
		List<TrdAccountmfConfigdetailDto> list = dto.getTrdAccountmfConfigdetail();
		for(TrdAccountmfConfigdetailDto  detail : list){
			if(StringUtil.isNotEmpty(detail.getSeqNo())){
				feeProvisionSettingService.deleteDetailById(detail.getSeqNo());  	//删除明细
			}else{
				detail.setpSeqNo(dto.getSeqNo());
				detail.setSeqNo(UUID.randomUUID().toString().replace("-","").toUpperCase());
			}
		}
		feeProvisionSettingService.insert(dto);  //插入
		feeProvisionSettingService.insertDetailBatch(list); //批量插入
	}

	/**
	 * 撤销数据
	 * @param dto
	 * @return
	 */
	@PostMapping("/revoke")
	public void revoke(@RequestBody FeeProvisionSettingDto dto){
		feeProvisionSettingService.revoke(dto);  //插入
	}
	
	/**
	 * 更新数据
	 * @param map
	 * @return
	 */
	@PostMapping("/updateRecord")
	public void updateRecord(@RequestBody Map<String,Object> map){
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put("booksetId", map.get("booksetId"));
		List<TrdAccountmfConfigdetailDto> list = feeProvisionSettingService.selectConfigDetail(pageParam);
		if(list != null && list.size() > 0) {
			for(TrdAccountmfConfigdetailDto record : list) {
				FeeProvisionSettingDto feeProvisionSettingDto = new FeeProvisionSettingDto();
				feeProvisionSettingDto.setPositionEndDate(DateUtil.parse(map.get("mDate").toString()));
				feeProvisionSettingDto.setActPositionEndDate(DateUtil.parse(map.get("mDate").toString()));
				feeProvisionSettingDto.setSeqNo(record.getpSeqNo());
				feeProvisionSettingService.revoke(feeProvisionSettingDto);  //更新
			}
		}
	}

	/**
	 * 批量增加明细数据
	 * @param list
	 * @return
	 */
	@PostMapping("/insertDetailBatch")
	public void insertDetailBatch(@RequestBody List<TrdAccountmfConfigdetailDto> list){
		feeProvisionSettingService.insertDetailBatch(list);
	}

	/**
	 * 获取账套信息
	 * @see
	 * @since 1.0
	 * @return 账套实体类
	 */
	@PostMapping("/getBooksetId")
	public List<SelectOptionDto> getBooksetId(@RequestBody Map<String,Object> map) {
		if (StringUtil.isEmpty((CharSequence) map.get("positionStartDate"))) return null;
		return feeProvisionSettingService.getBooksetId(map);
	}

	/**
	 * 获取账套信息
	 * @see
	 * @since 1.0
	 * @return 账套实体类
	 */
	@PostMapping("/selectOrgInfo")
	public String selectOrgInfo(@RequestBody Map<String,Object> map) {
		return feeProvisionSettingService.selectOrgInfo(map);
	}

	/**
	 * 通过主键查询实体信息
	 * @param seqNo
	 * @return
	 */
	@PostMapping("/selectEntityByID")
	public FeeProvisionSettingDto selectEntityByID(@RequestBody String seqNo){
		return feeProvisionSettingService.selectEntityByID(seqNo);
	}

	/**
	 * 获取最大产品起息日
	 * @param list
	 * @return
	 */
	@PostMapping("/getMaxVdate")
	public Date getMaxVdate(@RequestBody List<TrdAccountmfConfigdetailDto> list){
		return feeProvisionSettingService.getMaxVdate(list);
	}

	/**
	 * 获取最小的产品到期日
	 * @param list
	 * @return
	 */
	@PostMapping("/getMinMdate")
	public Date getMinMdate(@RequestBody List<TrdAccountmfConfigdetailDto> list){
		return feeProvisionSettingService.getMinMdate(list);
	}

	/**
	 * 查询重复区间
	 * @param dto
	 * @return
	 */
	@PostMapping("/selectCount")
	public Integer selectCount(@RequestBody FeeProvisionSettingDto dto){
		return feeProvisionSettingService.selectCount(dto);
	}

	@PostMapping("/getRepetitionDate")
	public Date getRepetitionDate(@RequestBody FeeProvisionSettingDto dto){
		return feeProvisionSettingService.getRepetitionDate(dto);
	}

	/**
	 * 作废删除流程中的数据
	 * @param dto
	 */
	@PostMapping("/deleteNewData")
	public void deleteNewData(@RequestBody FeeProvisionSettingDto dto){
		FeeProvisionSettingDto qryDto = feeProvisionSettingService.selectEntityByID(dto.getSeqNo());
		if(StringUtil.isNotEmpty(qryDto)  && StringUtil.equals(qryDto.getDealStatus(), "04")){//流程中
			feeProvisionSettingService.deleteById(dto.getSeqNo());	//删除
			feeProvisionSettingService.deleteNewDataDetailByPId(dto.getSeqNo());  	//删除明细
		}
	}
}
