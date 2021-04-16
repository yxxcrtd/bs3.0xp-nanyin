package com.joyintech.service.basicdata.mstfundbonusnotice.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstfundbonusnotice.dto.MstFundBonusNoticeDto;
import com.joyintech.service.basicdata.mstfundbonusnotice.service.MstFundBonusNoticeService;
import com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNoticeController
 * @Description 基金分红公告
 * @author zhuhanlu 
 * @date 2020/8/7 13:37 
 * @version 1.0 
 */
@RestController("MstFundBonusNoticeCtr_dtx")
@RequestMapping("/MstFundBonusNotice")
public class MstFundBonusNoticeController {
    @Autowired
    private  MstFundBonusNoticeService mstFundBonusNoticeService;

    //资产代码下拉框
    @PostMapping("/getFundId")
    public List<SelectOptionDto>  getFundId(@RequestBody Map<String,Object> map){
        return mstFundBonusNoticeService.getFundId(map);
    }

    @PostMapping("findPage/{page}/{length}")
    public Page<MstFundBonusNoticeDto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page")int page, @PathVariable("length")int length){
        return mstFundBonusNoticeService.selectForPage(page,length,map);
    }

    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     *
     * @param  Map<String, Object> dto type
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody MstFundBonusNoticeDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        mstFundBonusNoticeService.doComplete(dto);
    }

    /**
     *
     * @Description //根据shareId和regDate查数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto>
     * @version 1.0
     * @see
     **/
    @PostMapping("/selectAllList")
    public List<MstFundBonusNoticeDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return mstFundBonusNoticeService.selectAllList(pageParam);
    }
}
