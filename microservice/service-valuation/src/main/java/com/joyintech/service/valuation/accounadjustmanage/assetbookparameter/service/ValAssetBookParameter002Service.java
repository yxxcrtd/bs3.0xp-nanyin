/*
 * 文件名：ValAssetBookParameter002Service.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dao.ValAssetBookParameter002Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service(value="serviceValAssetBookParameter002Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetBookParameter002Service extends CommonExportService<ValAssetBookParameter002Dto> implements BaseProcessService<ValAssetBookParameter002Dto> {

    /**
     * ValAssetBookParameter002Mapper
     */
    @Autowired
    private ValAssetBookParameter002Mapper mapper;
    
	/**
     * 获取参数 - 下拉框
     * @return
     * @throws Exception
     */
	public Page<SelectOptionDto> findParam(int page,int length,Map<String, Object> parameters) throws Exception {
		String pageModel = String.valueOf(parameters.get("pageModel"));
		Page<SelectOptionDto>  resultPage = new Page<>();
		if (pageModel.equals("0") ){
			resultPage = super.queryForPageForSelectReturn(page,length,()->mapper.selectParamNew(parameters));
		}else{
			resultPage = super.queryForPageForSelectReturn(page,length,()->mapper.selectParam(parameters));
		}
//		List<SelectOptionDto> selectOptionList = new ArrayList<>();
//		for (ValAssetBookParameter002Dto single : resultList) {
//			if(single!=null) {
//				SelectOptionDto  selectOptionDto = new SelectOptionDto();
//				selectOptionDto.setId(single.getParamCode());
//				selectOptionDto.setText(single.getParamCode() + "(" + single.getParamName() + ")");
//				selectOptionList.add(selectOptionDto);
//			}
//		}
//		return selectOptionList;
		return resultPage;
    }
	
	/**
	 * 通过参数名，获取参数值
	 * @param pageParam
	 * @return
	 */
	public ValAssetBookParameter002Dto getParamValue(Map<String, Object> pageParam) {
		String pageModel = String.valueOf(pageParam.get("pageModel"));
		if (pageModel.equals("0") ){
			String booksetId = String.valueOf(pageParam.get("booksetId"));
			String booksetIdTmpl = mapper.getTmpl(booksetId);
			pageParam.put("booksetId", booksetIdTmpl);
		}
		ValAssetBookParameter002Dto tempDto = mapper.getParamValue(pageParam);
		if (tempDto == null){
			pageParam.put("booksetId", null);
			return mapper.getParamValue(pageParam);
		}
		return tempDto;
	}
    
	@Override
	public void doComplete(ValAssetBookParameter002Dto entity, OperationType type) {
		if(OperationType.NEW.equals(type)){
//			String booksetId = entity.getBooksetId();
//			String paramValue = entity.getParamValue();
//			String remark = entity.getRemark();
//			String booksetIdTmpl = mapper.getTmpl(booksetId);
//			entity.setBooksetIdTmpl(booksetIdTmpl);
//			ValAssetBookParameter002Dto pdto = mapper.booksetparam(entity);
//			pdto.setBooksetId(booksetId);
//			pdto.setParamValue(paramValue);
//			pdto.setRemark(remark);
//			mapper.insertparam(pdto);
			mapper.insertparam(entity);
		}else if(OperationType.MODIFY.equals(type)){
			mapper.updateByBooksetId(entity);
      }
}
	
	/**
	 * 获取选中记录
	 * @param pageParam
	 * @return
	 */
	public ValAssetBookParameter002Dto getByKey(Map<String, Object> params) {
		return mapper.getByKey(params);
	}

	/**
	 * 检查是否存在相同的对象
	 */
	public List<ValAssetBookParameter002Dto> checkExistSameDto(ValAssetBookParameter002Dto dto){
		return mapper.checkExistSameDto(dto);
	}
}
