package com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao.ValRateSwapValuationInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo001Dto;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Service("seriveValRateSwapValuationInfo001Service")
public class ValRateSwapValuationInfo001Service extends BaseService<ValRateSwapValuationInfo001Dto> {

    @Autowired
    private ValRateSwapValuationInfo001Mapper valRateSwapValuationInfo001Mapper;

    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    public Page<ValRateSwapValuationInfo001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()->valRateSwapValuationInfo001Mapper.selectList(params));
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public List<ValRateSwapValuationInfo001Dto> queryAllList(Map<String, Object> params) {
        return valRateSwapValuationInfo001Mapper.selectList(params);
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
    	 List<SelectOptionDto> resultList = valRateSwapValuationInfo001Mapper.getFinprodId2(params);
    	
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
