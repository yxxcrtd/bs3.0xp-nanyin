package com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dao.ComRateSwapValuationInfo001Mapper;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Service("seriveComRateSwapValuationInfo001Service")
public class ComRateSwapValuationInfo001Service extends BaseService<ComRateSwapValuationInfo001Dto> {

    @Autowired
    private ComRateSwapValuationInfo001Mapper comRateSwapValuationInfo001Mapper;

    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    public Page<ComRateSwapValuationInfo001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> comRateSwapValuationInfo001Mapper.selectList(params));
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public List<ComRateSwapValuationInfo001Dto> queryAllList(Map<String, Object> params) {
        return comRateSwapValuationInfo001Mapper.selectList(params);
    }

	    /**
     * 交易编号获取
     * @param length 
     * @param page 
     * @param params 
     *
     * @return 返回的数据
     */
    public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {
    	 List<SelectOptionDto> resultList = comRateSwapValuationInfo001Mapper.getFinprodId2(params);
    	
    	List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
        for (SelectOptionDto single : resultList) { // 组装select节点对象
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(single.getId());
            selectOptionDto.setText(single.getId());
            selectOptionDtoList.add(selectOptionDto);
        }
        return selectOptionDtoList;
    	 
	}
   

}
