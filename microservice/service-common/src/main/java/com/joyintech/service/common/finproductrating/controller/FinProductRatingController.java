package com.joyintech.service.common.finproductrating.controller;

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
import com.joyintech.service.common.finproductrating.dto.FinProductRatingDto;
import com.joyintech.service.common.finproductrating.service.FinProductRatingService;
import com.joyintech.tams.busicommon.productRating.dto.CommonProductRatingDto;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.MstInnerCreditRating;
/**
 * 产品评级 〈一句话功能简述〉 〈功能详细描述〉
 * @version 1.0 2019年11月6日
 * @see FinProductRatingController
 * @since 1.0
 */
@RestController("FinProductRatingCtr_dtx")
@RequestMapping("/FinProductRating")
public class FinProductRatingController {
	
	@Autowired
    private FinProductRatingService finProductRatingService;
	/**
	 * 分页数据
     * 获取产品评级信息（债项全量、主体全量、债项主体全量）
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/getProductBondRating/{page}/{length}")
    public Page<FinProductRatingDto> getProductBondInnerRating(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return finProductRatingService.getProductBondInnerRating(page,length,map);
    }
    /**
	 * 全量数据
     * 获取产品评级信息（债项全量、主体全量、债项主体全量）
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/getProductBondRatingList")
    public List<CommonProductRatingDto> getProductBondRatingList(@RequestBody Map<String, Object> map)
        throws Exception {
        return finProductRatingService.getProductBondInnerRatingList(map);
    }
    /**
     * 获取产品评级排序规则
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBestProductRatingRule")
    public Map<String, Object> getBestProductRatingRule(@RequestBody Map<String, Object> map)
        throws Exception {
        return finProductRatingService.getBestProductRatingRule(map);
    }
    /**
     * 获取产品评级显示类型   01 原始   02  标准
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getMstDisType")
    public String getMstDisType(@RequestBody Map<String, Object> map)
        throws Exception {
        return finProductRatingService.getMstDisType(map);
    }
    /**
     * 获取最优先产品评级结果
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBestProductRating")
    public List<CommonProductRatingDto> getBestProductRating(@RequestBody Map<String, Object> map)
        throws Exception {
        return finProductRatingService.getBestProductRating(map);
    }
    
	/**
	 * 对行外表的更新，删除操作
	 * 根据主键
	 */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteByOcode")
    public int deleteByOcode(MstBondCreditRating dto) {
    	return finProductRatingService.deleteByPrimaryKey(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(MstBondCreditRating dto) {
    	return finProductRatingService.updateByPrimaryKey(dto);
    }
	/**
	 * 对行内表的更新，删除操作
	 * 根据主键
	 */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteInnerByOcode")
    public int deleteInnerByOcode(MstInnerCreditRating dto) {
    	return finProductRatingService.deleteInnerByPrimaryKey(dto);
    } 
    @Transactional
    @DTXTransaction
    @PostMapping("updateInner")
    public int updateInner(MstInnerCreditRating dto) {
    	return finProductRatingService.updateInnerByPrimaryKey(dto);
    }
    
}
