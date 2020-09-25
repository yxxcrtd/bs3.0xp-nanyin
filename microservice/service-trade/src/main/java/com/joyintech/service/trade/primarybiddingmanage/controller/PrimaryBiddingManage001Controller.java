package com.joyintech.service.trade.primarybiddingmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.primarybiddingmanage.dto.PrimaryBiddingManageDto;
import com.joyintech.service.trade.primarybiddingmanage.service.PrimaryBiddingManage001Service;

/**
 * 债券一级市场指令管理微服务
 *
 * @author sjj
 * @version 1.0 2019年05月08日
 * @see PrimaryBiddingManage001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/PrimaryBiddingManage001")
public class PrimaryBiddingManage001Controller {

	/**
     * 微服务service注入
     */
	@Autowired
	private PrimaryBiddingManage001Service primaryBiddingManage001Service;
	
	/**
     * 查询
     *
     * @return Page<PrimaryBiddingManageDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<PrimaryBiddingManageDto> findPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return primaryBiddingManage001Service.selectForPage(page, length, param);
    }

    @RequestMapping("findDataByFinprodIdAndCounterId")
    public List<PrimaryBiddingManageDto> findDataByFinprodIdAndCounterId(@RequestBody Map<String, Object> param){
       return primaryBiddingManage001Service.findDataByFinprodIdAndCounterId(param);
    }

    /**
     * 截标或取消截标
     * @return
     */
    @RequestMapping("closeOrCancelBid")
    public String closeOrCancelBid(@RequestBody PrimaryBiddingManageDto dto){
        return primaryBiddingManage001Service.closeOrCancelBid(dto);
    }
}
